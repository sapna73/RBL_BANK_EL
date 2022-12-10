package com.precision.rdservice;

public class DeviceInfo {
	public String dpId= "";
	public String rdsId="";
	public String rdsVer="";
	public String dc="";
	public String mi="";
	public String mc = "";
	public String serialnumber="";
	public String ts="";
	public String Sysid="";


	public String oi="";

	public String getOi() {
		return oi;
	}

	public void setOi(String oi) {
		this.oi = oi;
	}

	public DeviceInfo(){
		  dpId= "";
		  rdsId="";
		  rdsVer="";
		  dc=""; 
		  mi="";
		  mc = "";
		  serialnumber="";
		  ts="";
		  Sysid="";
		oi="";
	}
	
	public String getDpId() {
		return dpId;
	}
	public void setDpId(String dpId) {
		this.dpId = dpId;
	}
	public String getRdsId() {
		return rdsId;
	}
	public void setRdsId(String rdsId) {
		this.rdsId = rdsId;
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
}
