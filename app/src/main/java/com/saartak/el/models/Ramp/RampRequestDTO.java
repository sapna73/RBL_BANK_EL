package com.saartak.el.models.Ramp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RampRequestDTO {

    @Expose
    @SerializedName("UniqueId")
    public String Uniqueld = "";

    @Expose
    @SerializedName("ServiceType")
    public String ServiceType = "";

    @Expose
    @SerializedName("RequestString")
    public RequestString RequestString;

    @Expose
    @SerializedName("RequestFrom")
    public String RequestFrom = "APP";

    @Expose
    @SerializedName("KYCld")
    public String KYCld = "";
    @Expose
    @SerializedName("ExternalCustomerld")
    public String ExternalCustomerld = "";

    @Expose
    @SerializedName("CreatedDate")
    public String CreatedDate = "";

    @Expose
    @SerializedName("CreatedByProject")
    public String CreatedByProject = "";

    @Expose
    @SerializedName("CreatedBy")
    public String CreateBy = "";

    @Expose
    @SerializedName("ClientID")
    public String ClientID = "";

    @Expose
    @SerializedName("ModuleType")
    private String moduleType;

    @Expose
    @SerializedName("co_applicant_id")
    private String coapplicantId;

    public void setUniqueld(String uniqueld) {
        Uniqueld = uniqueld;
    }

    public void setServiceType(String serviceType) {
        ServiceType = serviceType;
    }

    public void setRequestString(RequestString requestString) {
        RequestString = requestString;
    }

    public void setRequestFrom(String requestFrom) {
        RequestFrom = requestFrom;
    }

    public void setKYCld(String KYCld) {
        this.KYCld = KYCld;
    }

    public void setExternalCustomerld(String externalCustomerld) {
        ExternalCustomerld = externalCustomerld;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public void setCreatedByProject(String createdByProject) {
        CreatedByProject = createdByProject;
    }

    public void setCreateBy(String createBy) {
        CreateBy = createBy;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public void setCoapplicantId(String coapplicantId) {
        this.coapplicantId = coapplicantId;
    }

    public void setClientID(String clientID) {
        ClientID = clientID;
    }

    public static class RequestString {
        @Expose
        @SerializedName("RampRequest")
        public RampRequest RampRequest;

        public void setRampRequest(RampRequest rampRequest) {
            RampRequest = rampRequest;
        }

        public static class RampRequest {
            @Expose
            @SerializedName("tranld")
            public String tranld = "";
            @Expose
            @SerializedName("subBusinessUnit")
            public String subBusinessUnit = "";
            @Expose
            @SerializedName("requestVOList")
            public ArrayList<RequestVOList> requestVOList;
            @Expose
            @SerializedName("requestType")
            public String requestType = "";
            @Expose
            @SerializedName("businessUnit")
            public String businessUnit = "";
            @Expose
            @SerializedName("RequestedByApporWeb")
            public String RequestedByApporWeb = "App";
            @Expose
            @SerializedName("P6")
            public String P6 = "";
            @Expose
            @SerializedName("P5")
            public String P5 = "";
            @Expose
            @SerializedName("P4")
            public String P4 = "";
            @Expose
            @SerializedName("P3")
            public String P3 = "";
            @Expose
            @SerializedName("P2")
            public String P2 = "";
            @Expose
            @SerializedName("P1")
            public String P1 = "";
            @Expose
            @SerializedName("ModuleType")
            public String ModuleType = "";
            @Expose
            @SerializedName("ApplicationId")
            public String ApplicationId = "";

            public void setTranld(String tranld) {
                this.tranld = tranld;
            }

            public void setSubBusinessUnit(String subBusinessUnit) {
                this.subBusinessUnit = subBusinessUnit;
            }

            public void setRequestVOList(ArrayList<RequestVOList> requestVOList) {
                this.requestVOList = requestVOList;
            }

            public void setRequestType(String requestType) {
                this.requestType = requestType;
            }

            public void setBusinessUnit(String businessUnit) {
                this.businessUnit = businessUnit;
            }

            public void setRequestedByApporWeb(String requestedByApporWeb) {
                RequestedByApporWeb = requestedByApporWeb;
            }

            public void setP6(String p6) {
                P6 = p6;
            }

            public void setP5(String p5) {
                P5 = p5;
            }

            public void setP4(String p4) {
                P4 = p4;
            }

            public void setP3(String p3) {
                P3 = p3;
            }

            public void setP2(String p2) {
                P2 = p2;
            }

            public void setP1(String p1) {
                P1 = p1;
            }

            public void setModuleType(String moduleType) {
                ModuleType = moduleType;
            }

            public void setApplicationId(String applicationId) {
                ApplicationId = applicationId;
            }


            public static class RequestVOList {
                @Expose
                @SerializedName("voterId")
                public String voterId = "";
                @Expose
                @SerializedName("type")
                public String type = "";
                @Expose
                @SerializedName("tin")
                public String tin = "";
                @Expose
                @SerializedName("state")
                public String state = "";
                @Expose
                @SerializedName("ssn")
                public String ssn = "";
                @Expose
                @SerializedName("rationCardNo")
                public String rationCardNo = "";
                @Expose
                @SerializedName("pincode")
                public String pincode = "";
                @Expose
                @SerializedName("phone")
                public String phone = "";
                @Expose
                @SerializedName("passport")
                public String passport = "";
                @Expose
                @SerializedName("pan")
                public String pan = "";
                @Expose
                @SerializedName("nationality")
                public String nationality = "";
                @Expose
                @SerializedName("name")
                public String name = "";
                @Expose
                @SerializedName("email")
                public String email = "";
                @Expose
                @SerializedName("drivingLicence")
                public String drivingLicence = "";
                @Expose
                @SerializedName("docNumber")
                public String docNumber = "";
                @Expose
                @SerializedName("dob")
                public String dob = "";
                @Expose
                @SerializedName("din")
                public String din = "";
                @Expose
                @SerializedName("digitalID")
                public String digitalID = "";
                @Expose
                @SerializedName("customerType")
                public String customerType = "";
                @Expose
                @SerializedName("customerId")
                public String customerId = "";
                @Expose
                @SerializedName("country")
                public String country = "";
                @Expose
                @SerializedName("city")
                public String city = "";
                @Expose
                @SerializedName("cin")
                public String cin = "";
                @Expose
                @SerializedName("address")
                public String address = "";
                @Expose
                @SerializedName("aadhar")
                public String aadhar = "";
                @Expose
                @SerializedName("P9")
                public String P9 = "";
                @Expose
                @SerializedName("P8")
                public String P8 = "";
                @Expose
                @SerializedName("P7")
                public String P7 = "";
                @Expose
                @SerializedName("P11")
                public String P11 = "";
                @Expose
                @SerializedName("P10")
                public String P10 = "";

                public void setVoterId(String voterId) {
                    this.voterId = voterId;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public void setTin(String tin) {
                    this.tin = tin;
                }

                public void setState(String state) {
                    this.state = state;
                }

                public void setSsn(String ssn) {
                    this.ssn = ssn;
                }

                public void setRationCardNo(String rationCardNo) {
                    this.rationCardNo = rationCardNo;
                }

                public void setPincode(String pincode) {
                    this.pincode = pincode;
                }

                public void setPhone(String phone) {
                    this.phone = phone;
                }

                public void setPassport(String passport) {
                    this.passport = passport;
                }

                public void setPan(String pan) {
                    this.pan = pan;
                }

                public void setNationality(String nationality) {
                    this.nationality = nationality;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public void setEmail(String email) {
                    this.email = email;
                }

                public void setDrivingLicence(String drivingLicence) {
                    this.drivingLicence = drivingLicence;
                }

                public void setDocNumber(String docNumber) {
                    this.docNumber = docNumber;
                }

                public void setDob(String dob) {
                    this.dob = dob;
                }

                public void setDin(String din) {
                    this.din = din;
                }

                public void setDigitalID(String digitalID) {
                    this.digitalID = digitalID;
                }

                public void setCustomerType(String customerType) {
                    this.customerType = customerType;
                }

                public void setCustomerId(String customerId) {
                    this.customerId = customerId;
                }

                public void setCountry(String country) {
                    this.country = country;
                }

                public void setCity(String city) {
                    this.city = city;
                }

                public void setCin(String cin) {
                    this.cin = cin;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public void setAadhar(String aadhar) {
                    this.aadhar = aadhar;
                }

                public void setP9(String p9) {
                    P9 = p9;
                }

                public void setP8(String p8) {
                    P8 = p8;
                }

                public void setP7(String p7) {
                    P7 = p7;
                }

                public void setP11(String p11) {
                    P11 = p11;
                }

                public void setP10(String p10) {
                    P10 = p10;
                }
            }
        }
    }

}
