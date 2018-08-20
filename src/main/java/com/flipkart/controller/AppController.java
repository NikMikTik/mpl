package com.flipkart.controller;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.flipkart.dto.MerchantDto;
import com.flipkart.dto.ResponseDto;
import com.flipkart.model.Merchant;
import com.flipkart.service.MerchantService;

@RestController
@RequestMapping("/flipkart")
public class AppController {

	Logger logger = LoggerFactory.getLogger(Merchant.class);
	ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private MerchantService merchantService;

	// SIGNIN
	@RequestMapping(value = "/signIn", method = RequestMethod.POST)
	public ResponseDto signIn(@RequestBody MerchantDto merchantDto) {
		return merchantService.loginFunction(merchantDto);
	}

	// SIGNUP
	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public ResponseDto signUp(@RequestBody MerchantDto merchantDto) {
		return merchantService.registrationFunction(merchantDto);
	}

	// LOGOUT
	@RequestMapping(value = "/logout", method = RequestMethod.DELETE)
	public ResponseDto logout( HttpServletRequest request) {
		return merchantService.logoutFunction(request);

	}

}
