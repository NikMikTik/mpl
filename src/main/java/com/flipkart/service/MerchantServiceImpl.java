package com.flipkart.service;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.flipkart.dto.MerchantAddressDto;
import com.flipkart.dto.MerchantDto;
import com.flipkart.dto.ResponseDto;
import com.flipkart.exception.FlipkartException;
import com.flipkart.model.Merchant;

import com.flipkart.model.MerchantAddress;

import com.flipkart.repository.MerchantRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
@Transactional
public class MerchantServiceImpl implements MerchantService {

	Logger logger = LoggerFactory.getLogger(MerchantServiceImpl.class);
	ModelMapper modelMapper = new ModelMapper();
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	@Autowired
	private MerchantRepository merchantRepository;

/*	@Autowired
	private JavaMailSender sender;
*/
	@Autowired
	private IRedisService iredis;

	String noToken = "******";
	ResponseDto response = new ResponseDto();
	@Override
	public ResponseDto loginFunction(MerchantDto merchantDto) {
		ResponseDto response = new ResponseDto();
		Merchant merchant = merchantRepository.findByMerchantEmail(merchantDto.getMerchantEmail());
		if (merchantRepository.findByMerchantEmail(merchantDto.getMerchantEmail()) == null) {
			response.setCode(HttpStatus.UNAUTHORIZED.value());
			response.setToken("No Token Generated");
			response.setMessage("Not a valid Email..");
			response.setResponse("Access Denied");
			return response;
		}
		if (bCryptPasswordEncoder.matches(merchantDto.getMerchantPassword(), merchant.getMerchantPassword())) {
			String token = null;
			try {
				token = Jwts.builder().setSubject("flipkart" + merchant.getMerchantName() + "Merchant")
						.claim("scope", "FlipkartMerchants")
						.signWith(SignatureAlgorithm.HS256, "secret".getBytes("UTF-8"))
						.setIssuedAt(new Date(System.currentTimeMillis()))
						.setExpiration(new Date(System.currentTimeMillis() + 10000)).compact();
				iredis.setValue(token, merchant.getMerchantEmail());
				response.setCode(HttpStatus.OK.value());
				response.setMessage("Merchant successfully logged In");
				response.setResponse("Access Given");
				response.setToken(token);
				return response;
			} catch (Exception e) {

				e.printStackTrace();
			}

		} else {
			response.setCode(HttpStatus.UNAUTHORIZED.value());
			response.setToken("No Token Generated");
			response.setMessage("Email/Password Not valid");
			response.setResponse("Access Denied");
			return response;
		}
		return response;

	}

	@Override
	public ResponseDto registrationFunction(MerchantDto merchantDto) {
		Merchant merchant;
		ResponseDto response = new ResponseDto();
		merchant = modelMapper.map(merchantDto, Merchant.class);
		if ((merchantRepository.findByMerchantEmail(merchantDto.getMerchantEmail()) == null)
				&& (merchantDto.getMerchantPassword().equals(merchantDto.getMerchantConfirmPassword()))) {
			merchant.setMerchantPassword(bCryptPasswordEncoder.encode(merchantDto.getMerchantPassword()));
			merchantRepository.save(merchant);

			/*MimeMessage message = sender.createMimeMessage();
			try {System.out.println("email sending");
				MimeMessageHelper helper = new MimeMessageHelper(message, true);
				helper.setTo(merchant.getMerchantEmail());
				helper.setText("<html><body><h2>Hi " + merchant.getMerchantName()
						+ ",<br></h2><h3>Welcome to Flipkart' Merchant Site<b></b>!</br>Thank you for Registering on Flipkart... Grow your business with the leader in Indian e-commerce... </br>Doing business on flipkart is really easy... All you need is to have a business of your own & Just 1 Product\r\n"
						+ "<br>Add Products in less than 5 minutes<b></br></br>See you around</br></h3><h2>Team Flipkart :: Nikita & Vikrant</h2><br><br><img src='cid:id101'/><body></html>",
						true);
				helper.setSubject("Welcome to Flipkart Merchant Site");
				ClassPathResource file1 = new ClassPathResource("seller-hub-logo.png");
				helper.addInline("id101", file1);
				System.out.println("email sent");
				
				 * ClassPathResource file = new ClassPathResource("Terms and Conditions.pdf");
				 * helper.addAttachment("Terms and Conditions.pdf", file);
				 
				sender.send(message);
			} catch (MailException e) {
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			}*/


			response.setCode(HttpStatus.OK.value());
			response.setToken(noToken);
			response.setMessage("Registered Successfully...");
			response.setResponse("You can access your account Now");
			return response;
		} else {
			response.setCode(HttpStatus.FORBIDDEN.value());
			response.setMessage("Cannot Register");
			response.setToken(noToken);
			response.setResponse("Enter correct Details");
			return response;
		}
	}

	@Override
	public List<MerchantDto> fetchAllUser() {
		List<Merchant> merchantList = merchantRepository.findAll();
		List<MerchantDto> merchantDtoList = new ArrayList<>();
		MerchantDto merchantDto;
		for (Merchant merchant : merchantList) {
			merchantDto = modelMapper.map(merchant, MerchantDto.class);

			if (merchant.getMerchantAddress() != null) {
				MerchantAddress address = merchant.getMerchantAddress();
				MerchantAddressDto addressDto = modelMapper.map(address, MerchantAddressDto.class);
				merchantDto.setMerchantAddressDto(addressDto);
			}

			merchantDto.setMerchantConfirmPassword("Not saved");
			merchantDtoList.add(merchantDto);
		}
		if (merchantDtoList.isEmpty()) {
			throw new FlipkartException("No Merchants Registered..");
		}
		logger.info("All Merchant's List");
		return merchantDtoList;

	}

	@Override

	public ResponseDto logoutFunction(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		iredis.deleteValue(token);
		ResponseDto responseDto = new ResponseDto();
		responseDto.setCode(HttpStatus.OK.value());
		responseDto.setMessage("Successfully logged out");
		responseDto.setToken("Token Deleted");
		responseDto.setResponse("To get Access, Login Again");

		logger.info("Successfully logged out");

		return responseDto;

	}



}
