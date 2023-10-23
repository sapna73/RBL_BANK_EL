package com.saartak.el.models;

import com.google.gson.annotations.Expose;

public class ApiResponse{
	@Expose
	private String responseCode;
	@Expose
	private String responseMessage;
	@Expose
	private String requestId;
	@Expose
	private String nextFreshnessFactor;
	@Expose
	private String obj_responseCode;
	@Expose
	private String authenticationCode;
	@Expose
	private String authenticationMessage;
	@Expose
	private String authXmlErrorCode;
	@Expose
	private String name;
	@Expose
	private String gender;
	@Expose
	private String dob;
	@Expose
	private String age;
	@Expose
	private String building;
	@Expose
	private String street;
	@Expose
	private String area;
	@Expose
	private String city;
	@Expose
	private String district;
	@Expose
	private String state;
	@Expose
	private String pin;
	@Expose
	private String careOf;
	@Expose
	private String rrn;
	@Expose
	private String country;
	@Expose
	private String txnId;
	@Expose
	private String uidToken;
	@Expose
	private String photo;

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getNextFreshnessFactor() {
		return nextFreshnessFactor;
	}

	public void setNextFreshnessFactor(String nextFreshnessFactor) {
		this.nextFreshnessFactor = nextFreshnessFactor;
	}

	public String getObj_responseCode() {
		return obj_responseCode;
	}

	public void setObj_responseCode(String obj_responseCode) {
		this.obj_responseCode = obj_responseCode;
	}

	public String getAuthenticationCode() {
		return authenticationCode;
	}

	public void setAuthenticationCode(String authenticationCode) {
		this.authenticationCode = authenticationCode;
	}

	public String getAuthenticationMessage() {
		return authenticationMessage;
	}

	public void setAuthenticationMessage(String authenticationMessage) {
		this.authenticationMessage = authenticationMessage;
	}

	public String getAuthXmlErrorCode() {
		return authXmlErrorCode;
	}

	public void setAuthXmlErrorCode(String authXmlErrorCode) {
		this.authXmlErrorCode = authXmlErrorCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getCareOf() {
		return careOf;
	}

	public void setCareOf(String careOf) {
		this.careOf = careOf;
	}

	public String getRrn() {
		return rrn;
	}

	public void setRrn(String rrn) {
		this.rrn = rrn;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	public String getUidToken() {
		return uidToken;
	}

	public void setUidToken(String uidToken) {
		this.uidToken = uidToken;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
}
