package com.flipkart.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "Merchant_Table")
public class Merchant {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int merchantId;
	@NotBlank(message = "Merchant Name cannot be Blank")
	private String merchantName;
	@Column(unique = true)
	@Email(message = "Enter Unique Email Id Ex: abc@xyz.pqr")
	@NotBlank(message = "Merchant Email cannot be Blank")
	private String merchantEmail;
	@NotNull(message = "Merchant Phone No. cannot be Blank")
	@Pattern(regexp = "(0/91)?[7-9][0-9]{9}")
	private String merchantphoneNo;
	@NotBlank(message = "Merchant Password cannot be Blank")
	@Pattern(regexp = "^.*(?=.{8,})(?=.*\\d)(?=.*[a-zA-Z]).*$")
	private String merchantPassword;
	@Transient
	@NotBlank(message = "Merchant Confirm Password cannot be Blank")
	@Pattern(regexp = "^.*(?=.{8,})(?=.*\\d)(?=.*[a-zA-Z]).*$")
	private String merchantConfirmPassword;
	@OneToOne()
	private MerchantAddress merchantAddress;

	
	public Merchant() {
		super();
		// TODO Auto-generated constructor stub
	}


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


	public MerchantAddress getMerchantAddress() {
		return merchantAddress;
	}


	public void setMerchantAddress(MerchantAddress merchantAddress) {
		this.merchantAddress = merchantAddress;
	}


	@Override
	public String toString() {
		return "Merchant [merchantId=" + merchantId + ", merchantName=" + merchantName + ", merchantEmail="
				+ merchantEmail + ", merchantphoneNo=" + merchantphoneNo + ", merchantPassword=" + merchantPassword
				+ ", merchantConfirmPassword=" + merchantConfirmPassword + ", merchantAddress=" + merchantAddress + "]";
	}


	public Merchant(int merchantId, @NotBlank(message = "Merchant Name cannot be Blank") String merchantName,
			@Email(message = "Enter Unique Email Id Ex: abc@xyz.pqr") @NotBlank(message = "Merchant Email cannot be Blank") String merchantEmail,
			@NotNull(message = "Merchant Phone No. cannot be Blank") @Pattern(regexp = "(0/91)?[7-9][0-9]{9}") String merchantphoneNo,
			@NotBlank(message = "Merchant Password cannot be Blank") @Pattern(regexp = "^.*(?=.{8,})(?=.*\\d)(?=.*[a-zA-Z]).*$") String merchantPassword,
			@NotBlank(message = "Merchant Confirm Password cannot be Blank") @Pattern(regexp = "^.*(?=.{8,})(?=.*\\d)(?=.*[a-zA-Z]).*$") String merchantConfirmPassword,
			MerchantAddress merchantAddress) {
		super();
		this.merchantId = merchantId;
		this.merchantName = merchantName;
		this.merchantEmail = merchantEmail;
		this.merchantphoneNo = merchantphoneNo;
		this.merchantPassword = merchantPassword;
		this.merchantConfirmPassword = merchantConfirmPassword;
		this.merchantAddress = merchantAddress;
	}
	
	

}
