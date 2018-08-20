package com.flipkart.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlipkartException extends RuntimeException{

	Logger logger = LoggerFactory.getLogger(FlipkartException.class);

	public FlipkartException(String message) {
		super(message);
		logger.info(message);
	}

	

}
