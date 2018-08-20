package com.flipkart.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;


@Service
public interface IRedisService {
	public Object getValue(String key);

	public void setValue(String key, String value);

	public String deleteValue(String key);

public boolean checkToken(HttpServletRequest request) ;

}
