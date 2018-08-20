package com.flipkart.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Merchant_Address_Table")
public class MerchantAddress {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int addressId;
	@NotBlank(message = "Adsress cannot be Blank")
	private String name;
	@NotBlank(message = "Street cannot be Blank")
	private String streetAddress;
	@NotBlank(message = "City cannot be Blank")
	private String city;
	@NotBlank(message = "State cannot be Blank")
	private String state;
	@NotNull(message = "zipcode cannot be Blank")
	private long zipCode;
	@NotBlank(message = "Country cannot be Blank")
	private String country;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public long getZipCode() {
		return zipCode;
	}

	public void setZipCode(long zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		return "MerchantAddress [addressId=" + addressId + ", name=" + name + ", streetAddress=" + streetAddress
				+ ", city=" + city + ", state=" + state + ", zipCode=" + zipCode + ", country=" + country + "]";
	}

	public MerchantAddress(int addressId, @NotBlank(message = "Adsress cannot be Blank") String name,
			@NotBlank(message = "Street cannot be Blank") String streetAddress,
			@NotBlank(message = "City cannot be Blank") String city,
			@NotBlank(message = "State cannot be Blank") String state,
			@NotNull(message = "zipcode cannot be Blank") long zipCode,
			@NotBlank(message = "Country cannot be Blank") String country) {
		super();
		this.addressId = addressId;
		this.name = name;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.country = country;
	}

	public MerchantAddress() {
		super();
	}

}
