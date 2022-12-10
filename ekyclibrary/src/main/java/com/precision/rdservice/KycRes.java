package com.precision.rdservice;

import java.io.Serializable;


public class KycRes implements Serializable
{
	public boolean isKycSuccess() {
		return kycSuccess;
	}
	public void setKycSuccess(boolean kycSuccess) {
		this.kycSuccess = kycSuccess;
	}
	public String getStrUIDAINumber() {
		return strUIDAINumber;
	}
	public void setStrUIDAINumber(String strUIDAINumber) {
		this.strUIDAINumber = strUIDAINumber;
	}
	public String getStrName() {
		return strName;
	}
	public void setStrName(String strName) {
		this.strName = strName;
	}
	public String getStrDOB() {
		return strDOB;
	}
	public void setStrDOB(String strDOB) {
		this.strDOB = strDOB;
	}
	public String getStrGender() {
		return strGender;
	}
	public void setStrGender(String strGender) {
		this.strGender = strGender;
	}
	public String getStrPhoneNumber() {
		return strPhoneNumber;
	}
	public void setStrPhoneNumber(String strPhoneNumber) {
		this.strPhoneNumber = strPhoneNumber;
	}
	public String getStrMailID() {
		return strMailID;
	}
	public void setStrMailID(String strMailID) {
		this.strMailID = strMailID;
	}
	public String getStrcoDetails() {
		return strcoDetails;
	}
	public void setStrcoDetails(String strcoDetails) {
		this.strcoDetails = strcoDetails;
	}
	public String getStrHouse() {
		return strHouse;
	}
	public void setStrHouse(String strHouse) {
		this.strHouse = strHouse;
	}
	public String getStrStreet() {
		return strStreet;
	}
	public void setStrStreet(String strStreet) {
		this.strStreet = strStreet;
	}
	public String getStrLM() {
		return strLM;
	}
	public void setStrLM(String strLM) {
		this.strLM = strLM;
	}
	public String getStrloc() {
		return strloc;
	}
	public void setStrloc(String strloc) {
		this.strloc = strloc;
	}
	public String getStrvtc() {
		return strvtc;
	}
	public void setStrvtc(String strvtc) {
		this.strvtc = strvtc;
	}
	public String getStrsubdist() {
		return strsubdist;
	}
	public void setStrsubdist(String strsubdist) {
		this.strsubdist = strsubdist;
	}
	public String getStrdist() {
		return strdist;
	}
	public void setStrdist(String strdist) {
		this.strdist = strdist;
	}
	public String getStrstate() {
		return strstate;
	}
	public void setStrstate(String strstate) {
		this.strstate = strstate;
	}
	public String getStrpc() {
		return strpc;
	}
	public void setStrpc(String strpc) {
		this.strpc = strpc;
	}
	public String getStrpo() {
		return strpo;
	}
	public void setStrpo(String strpo) {
		this.strpo = strpo;
	}
	public String getStrResponsecode() {
		return strResponsecode;
	}
	public void setStrResponsecode(String strResponsecode) {
		this.strResponsecode = strResponsecode;
	}
	public String getStrts() {
		return strts;
	}
	public void setStrts(String strts) {
		this.strts = strts;
	}
	public byte[] getByPhoto() {
		return byPhoto;
	}
	public void setByPhoto(byte[] byPhoto) {
		this.byPhoto = byPhoto;
	}
	public byte[] getByPDF() {
		return byPDF;
	}
	public void setByPDF(byte[] byPDF) {
		this.byPDF = byPDF;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getStrCountry() {
		return strCountry;
	}
	public void setStrCountry(String strCountry) {
		this.strCountry = strCountry;
	}
	
	public String getRRNnumber() {
		return RRNnumber;
	}
	public void setRRNnumber(String rRNnumber) {
		RRNnumber = rRNnumber;
	}
	
	public boolean kycSuccess = false;
	public String strUIDAINumber = "";
    public String strName = "";
    public String strDOB = "";
    public String strGender = "";
    public String strPhoneNumber = "";
    public String strMailID = "";
    public String strcoDetails = "";
    public String strHouse = "";
    public String strStreet = "";
    public String strLM = "";
    public String strloc = "";
    public String strvtc = "";
    public String strsubdist = "";
    public String strdist = "";
    public String strstate = "";
    public String strpc = "";
    public String strpo = "";
    public String strResponsecode = "";
    public String strts = "";
    public byte[] byPhoto = null;
    public byte[] byPDF = null;

    public String result="";
	public String responseCode="";
	public String strCountry="";
	
	public String RRNnumber="";
	public String ret="";
	public String code="";
	public String ts="";
	public String err="";
	public String actn="";
	public String responseMessage="";
	public String txn="";

	
}
