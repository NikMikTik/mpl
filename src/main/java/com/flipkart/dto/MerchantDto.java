package com.flipkart.dto;



import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;

import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.flipkart.model.MerchantAddress;

public class MerchantDto {
	private int merchantId;
	@NotBlank(message="Merchant Name cannot be Blank")
	private String merchantName;
	@Column(unique = true)
	@Email(message="Enter Unique Email Id Ex: abc@xyz.pqr")
	@NotBlank(message="Merchant Email cannot be Blank")
	private String merchantEmail;
	@NotNull(message="Merchant Phone No. cannot be Blank")
	@Pattern(regexp = "(0/91)?[7-9][0-9]{9}",message="Enter valid Phone Number")
	private String merchantphoneNo;
	@NotBlank(message="Merchant Password cannot be Blank")
	@Pattern(regexp="^.*(?=.{8,})(?=.*\\d)(?=.*[a-zA-Z])(?!\\s).*$")
	private String merchantPassword;
	@Transient
	@NotBlank(message="Merchant Confirm Password cannot be Blank")
	@Pattern(regexp="^.*(?=.{8,})(?=.*\\d)(?=.*[a-zA-Z])(?!\\s).*$")
	private String merchantConfirmPassword;
	@OneToOne()
	private MerchantAddressDto merchantAddressDto;
	
	
	
	public int getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getMerchantEmail() {
		return merchantEmail;
	}
	public void setMerchantEmail(String merchantEmail) {
		this.merchantEmail = merchantEmail;
	}
	public String getMerchantphoneNo() {
		return merchantphoneNo;
	}
	public void setMerchantphoneNo(String merchantphoneNo) {
		this.merchantphoneNo = merchantphoneNo;
	}
	public String getMerchantPassword() {
		return merchantPassword;
	}
	public void setMerchantPassword(String merchantPassword) {
		this.merchantPassword = merchantPassword;
	}
	public String getMerchantConfirmPassword() {
		return merchantConfirmPassword;
	}
	public void setMerchantConfirmPassword(String merchantConfirmPassword) {
		this.merchantConfirmPassword = merchantConfirmPassword;
	}
	public MerchantAddressDto getMerchantAddressDto() {
		return merchantAddressDto;
	}
	public void setMerchantAddressDto(MerchantAddressDto merchantAddressDto) {
		this.merchantAddressDto = merchantAddressDto;
	}


	
}
