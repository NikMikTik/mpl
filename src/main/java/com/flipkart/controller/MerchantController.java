package com.flipkart.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.flipkart.dto.MerchantDto;
import com.flipkart.dto.ResponseDto;
import com.flipkart.model.Merchant;
import com.flipkart.service.IRedisService;
import com.flipkart.service.MerchantAddressService;
import com.flipkart.service.MerchantService;

@RestController
@RequestMapping("/flipkart")
public class MerchantController {
	Logger logger = LoggerFactory.getLogger(Merchant.class);
	ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private MerchantService merchantService;

	@Autowired
	private MerchantAddressService merchantAddressService;

	@Autowired
	private IRedisService iredis;

	ResponseDto response;

	// GET ALL MERCHANTS LIST
	@RequestMapping(value = "/allMerchants", method = RequestMethod.GET)
	public List<MerchantDto> retrieveAllMerchants(HttpServletRequest request) {
		return merchantService.fetchAllUser();
	}

/*	// ADD MERCHANT'S ADDRESS
	@RequestMapping(value = "/add/address", method = RequestMethod.PUT)
	public ResponseDto addAddress(@RequestBody MerchantDto merchantDto, HttpServletRequest request) {
		if (iredis.checkToken(request)) {
			System.out.println("qweqweqweq");
			return merchantAddressService.addAddress(merchantDto,request);
		} else {
			response.setCode(HttpStatus.FORBIDDEN.value());
			response.setMessage("Merchant Not found");
			response.setResponse("Access Denied");
			return response;
		}
	}*/

}
