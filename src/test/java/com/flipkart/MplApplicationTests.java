package com.flipkart;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.flipkart.dto.MerchantDto;
import com.flipkart.dto.ResponseDto;
import com.flipkart.repository.MerchantRepository;
import com.flipkart.service.MerchantService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MplApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@MockBean
	MerchantService merchantService;

	@MockBean
	MerchantRepository merchantRepository;

	ModelMapper modelMapper = new ModelMapper();
	
	@LocalServerPort
	Integer port;

	@Test
	public void fetchAllMerchants() {
		MerchantDto merchantDto1 = new MerchantDto();
		merchantDto1.setMerchantId(1);
		merchantDto1.setMerchantName("Nikku");
		merchantDto1.setMerchantEmail("nikku@gamilcom");
		merchantDto1.setMerchantPassword("qwe");
		merchantDto1.setMerchantConfirmPassword("qwe");

		MerchantDto merchantDto2 = new MerchantDto();
		merchantDto2.setMerchantId(1);
		merchantDto2.setMerchantName("Mikku");
		merchantDto2.setMerchantEmail("mikku@gamilcom");
		merchantDto2.setMerchantPassword("qwee");
		merchantDto2.setMerchantConfirmPassword("qwee");

		List<MerchantDto> merchantList = new ArrayList<>();
		merchantList.add(merchantDto1);
		merchantList.add(merchantDto2);

		Mockito.when(merchantService.fetchAllUser()).thenReturn(merchantList);
		String url = "http://localhost:" + port + "/flipkart/allMerchants";

		List<MerchantDto> response = restTemplate
				.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<MerchantDto>>() {
				}).getBody();
		assertNotNull(response);
		Assertions.assertThat(response.size()).isEqualTo(2);
		Assertions.assertThat(response.get(0).getMerchantName()).isEqualTo("Nikku");
		Assertions.assertThat(response.get(1).getMerchantName()).isEqualTo("Mikku");

	}

	@Test
	public void loginUserTest4() {

		MerchantDto merchantDto = new MerchantDto();
		merchantDto.setMerchantId(1);
		merchantDto.setMerchantName("Nikku");
		merchantDto.setMerchantEmail("nikku@gamilcom");
		merchantDto.setMerchantPassword("qwe");
		merchantDto.setMerchantConfirmPassword("qwe");

		ResponseDto response = new ResponseDto();
		response.setCode(HttpStatus.OK.value());
		response.setMessage("Successfull");
		response.setResponse("Access Given");
		response.setToken("");

		Mockito.when(merchantService.registrationFunction(merchantDto)).thenReturn((response));
		Mockito.when(merchantService.loginFunction(merchantDto)).thenReturn(response);

		String url = "http://localhost:" + port + "/flipkart/signIn";
		HttpEntity<MerchantDto> entity = new HttpEntity<>(merchantDto);
		ResponseEntity<ResponseDto> response121 = restTemplate.postForEntity(url, entity, ResponseDto.class);
		assertEquals(200, response121.getStatusCodeValue());

	}

	@Test
	public void registrationUser() {

		MerchantDto merchantDto = new MerchantDto();
		merchantDto.setMerchantId(1);
		merchantDto.setMerchantName("Nikku");
		merchantDto.setMerchantEmail("nikku@gamilcom");
		merchantDto.setMerchantPassword("qwe");
		merchantDto.setMerchantConfirmPassword("qwe");

		ResponseDto response = new ResponseDto();
		response.setCode(HttpStatus.OK.value());
		response.setMessage("Successfull");
		response.setResponse("Access Given");
		response.setToken("");

		Mockito.when(merchantService.registrationFunction(merchantDto)).thenReturn((response));
				
		String url = "http://localhost:" + port + "/flipkart/signUp";
		HttpEntity<MerchantDto> entity = new HttpEntity<>(merchantDto);
		ResponseEntity<ResponseDto> response121 = restTemplate.postForEntity(url, entity, ResponseDto.class);
		assertEquals(200, response121.getStatusCodeValue());

	}

}
