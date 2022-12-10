package com.precision.rdservice;

public class CaptureResponse {

	public String errCode = "";
	public String errInfo = "";
	public String fCount = "";
	public String fType = "";
	public String iCount = "";
	public String iType = "";
	public String pCount = "";
	public String pType = "";
	public String nmPoints = "";
	public String qScore = "";
	
	public String dpID = "";
	public String rdsID = "";
	public String rdsVer = "";
	public String dc = "";
	public String mi = "";
	public String mc = "";
	
	public String ci = "";
	public String sessionKey = "";
	
	public String hmac = "";
	public String PidDatatype = "";
	public String Piddata = "";
	
	public String serialnumber="";
	public String ts="";
	public String Sysid="";
	public String Locking="";
	

	public CaptureResponse(){
		 errCode = "";
		 errInfo = "";
		 fCount = "";
		 fType = "";
		 iCount = "";
		 iType = "";
		 pCount = "";
		 pType = "";
		 nmPoints = "";
		 qScore = "";
		
		dpID = "";
	    rdsID = "";
	    rdsVer = "";
		 dc = "";
		 mi = "";
		 mc = "";
		
		 ci = "";
		sessionKey = "";
		
		 hmac = "";
		 PidDatatype = "";
		 Piddata = "";
		 
		  serialnumber="";
		  ts="";
		  Sysid="";
		 Locking="";
			
	}
	
	public String getSerialnumber() {
		return serialnumber;
	}
	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}
	public String getTs() {
		return ts;
	}
	public void setTs(String ts) {
		this.ts = ts;
	}
	public String getSysid() {
		return Sysid;
	}
	public void setSysid(String sysid) {
		Sysid = sysid;
	}
	public String getLocking() {
		return Locking;
	}
	public void setLocking(String locking) {
		Locking = locking;
	}
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public String getErrInfo() {
		return errInfo;
	}
	public void setErrInfo(String errInfo) {
		this.errInfo = errInfo;
	}
	public String getfCount() {
		return fCount;
	}
	public void setfCount(String fCount) {
		this.fCount = fCount;
	}
	public String getfType() {
		return fType;
	}
	public void setfType(String fType) {
		this.fType = fType;
	}
	public String getiCount() {
		return iCount;
	}
	public void setiCount(String iCount) {
		this.iCount = iCount;
	}
	public String getiType() {
		return iType;
	}
	public void setiType(String iType) {
		this.iType = iType;
	}
	public String getpCount() {
		return pCount;
	}
	public void setpCount(String pCount) {
		this.pCount = pCount;
	}
	public String getpType() {
		return pType;
	}
	public void setpType(String pType) {
		this.pType = pType;
	}
	public String getNmPoints() {
		return nmPoints;
	}
	public void setNmPoints(String nmPoints) {
		this.nmPoints = nmPoints;
	}
	public String getqScore() {
		return qScore;
	}
	public void setqScore(String qScore) {
		this.qScore = qScore;
	}
	public String getDpID() {
		return dpID;
	}
	public void setDpID(String dpID) {
		this.dpID = dpID;
	}
	public String getRdsID() {
		return rdsID;
	}
	public void setRdsID(String rdsID) {
		this.rdsID = rdsID;
	}
	public String getRdsVer() {
		return rdsVer;
	}
	public void setRdsVer(String rdsVer) {
		this.rdsVer = rdsVer;
	}
	public String getDc() {
		return dc;
	}
	public void setDc(String dc) {
		this.dc = dc;
	}
	public String getMi() {
		return mi;
	}
	public void setMi(String mi) {
		this.mi = mi;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	public String getCi() {
		return ci;
	}
	public void setCi(String ci) {
		this.ci = ci;
	}
	public String getSessionKey() {
		return sessionKey;
	}
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	public String getHmac() {
		return hmac;
	}
	public void setHmac(String hmac) {
		this.hmac = hmac;
	}
	public String getPidDatatype() {
		return PidDatatype;
	}
	public void setPidDatatype(String pidDatatype) {
		PidDatatype = pidDatatype;
	}
	public String getPiddata() {
		return Piddata;
	}
	public void setPiddata(String piddata) {
		Piddata = piddata;
	}
	
	
	/*public int getImageQuality(CaptureResponse captureResponse){
		int result=Integer.valueOf(captureResponse.qScore);
			
		if(result>=0 && result<=20){
			result=5;
		}else if(result>=20 && result<=40){
			result=4;
		}else if(result>=40 && result<=60){
			result=3;
		}else if(result>=60 && result<=80){
			result=2;
		}else if(result>=80 && result<=100){
			result=1;
		}
		return result;
	}*/
}
