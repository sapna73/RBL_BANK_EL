package com.bfil.ekyclibrary.models;

import java.io.Serializable;

public class AadharData implements Serializable {

    private boolean bAaadhaar=false;
    private boolean bVID=false;

    public boolean isbAaadhaar() {
        return bAaadhaar;
    }

    public void setbAaadhaar(boolean bAaadhaar) {
        this.bAaadhaar = bAaadhaar;
    }

    public boolean isbVID() {
        return bVID;
    }

    public void setbVID(boolean bVID) {
        this.bVID = bVID;
    }

    String UID;
    String Name;
    String Gender;
    String YOB;
    String DOB;
    String CareOf;
    String HouseNo;
    String village;
    String PostOffice;
    String District;
    String State;
    String PinCode;
    String Street;
    String loc;
    String subDist;
    String landmark;
    String address;
    String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }
    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getSubDist() {
        return subDist;
    }

    public void setSubDist(String subDist) {
        this.subDist = subDist;
    }



    public AadharData() {
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getYOB() {
        return YOB;
    }

    public void setYOB(String YOB) {
        this.YOB = YOB;
    }

    public String getCareOf() {
        return CareOf;
    }

    public void setCareOf(String careOf) {
        CareOf = careOf;
    }

    public String getHouseNo() {
        return HouseNo;
    }

    public void setHouseNo(String houseNo) {
        HouseNo = houseNo;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getPostOffice() {
        return PostOffice;
    }

    public void setPostOffice(String postOffice) {
        PostOffice = postOffice;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getPinCode() {
        return PinCode;
    }

    public void setPinCode(String pinCode) {
        PinCode = pinCode;
    }


}
