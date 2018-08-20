package com.flipkart.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;



import com.flipkart.dto.MerchantDto;
import com.flipkart.dto.ResponseDto;

public interface MerchantService {
	
	public ResponseDto loginFunction(MerchantDto merchantDto);

	public ResponseDto registrationFunction(MerchantDto merchantDto);

	public List<MerchantDto> fetchAllUser() ;

	public ResponseDto logoutFunction(HttpServletRequest request);

}
