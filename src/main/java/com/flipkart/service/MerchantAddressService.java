package com.flipkart.service;

import javax.servlet.http.HttpServletRequest;

import com.flipkart.dto.MerchantAddressDto;
import com.flipkart.dto.MerchantDto;
import com.flipkart.dto.ResponseDto;

public interface MerchantAddressService {
	
	public ResponseDto addAddress(MerchantDto merchantDto, HttpServletRequest request);

}
