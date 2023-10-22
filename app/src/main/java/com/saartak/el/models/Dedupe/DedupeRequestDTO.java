package com.saartak.el.models.Dedupe;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DedupeRequestDTO {

    @Expose
    @SerializedName("UniqueId")
    private String UniqueId;
    @Expose
    @SerializedName("ServiceType")
    private String ServiceType;
    @Expose
    @SerializedName("RequestString")
    private RequestString RequestString;
    @Expose
    @SerializedName("RequestFrom")
    private String RequestFrom="APP";
    @Expose
    @SerializedName("ExtCustId")
    private String ExtCustId;
    @Expose
    @SerializedName("CreatedDate")
    private String CreatedDate;
    @Expose
    @SerializedName("CreatedBy")
    private String CreatedBy;
    @Expose
    @SerializedName("ClientID")
    private String ClientID;
    @Expose
    @SerializedName("ModuleType")
    private String moduleType;
    @Expose
    @SerializedName("co_applicant_id")
    private String coapplicantId;

    public String getUniqueId() {
        return UniqueId;
    }

    public void setUniqueId(String uniqueId) {
        UniqueId = uniqueId;
    }

    public String getServiceType() {
        return ServiceType;
    }

    public void setServiceType(String serviceType) {
        ServiceType = serviceType;
    }

    public DedupeRequestDTO.RequestString getRequestString() {
        return RequestString;
    }

    public void setRequestString(DedupeRequestDTO.RequestString requestString) {
        RequestString = requestString;
    }

    public String getRequestFrom() {
        return RequestFrom;
    }

    public void setRequestFrom(String requestFrom) {
        RequestFrom = requestFrom;
    }

    public String getExtCustId() {
        return ExtCustId;
    }

    public void setExtCustId(String extCustId) {
        ExtCustId = extCustId;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public String getClientID() {
        return ClientID;
    }

    public void setClientID(String clientID) {
        ClientID = clientID;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public void setCoapplicantId(String coapplicantId) {
        this.coapplicantId = coapplicantId;
    }

    public static class RequestString {
        @Expose
        @SerializedName("dedupeEnquiry")
        private DedupeEnquiry dedupeEnquiry;

        public void setDedupeEnquiry(DedupeEnquiry dedupeEnquiry) {
            this.dedupeEnquiry = dedupeEnquiry;
        }

        public static class DedupeEnquiry {
            @Expose
            @SerializedName("VoterId")
            private String VoterId;
            @Expose
            @SerializedName("UId")
            private String UId;
            @Expose
            @SerializedName("RequestedByAppOrWeb")
            private String RequestedByAppOrWeb;
            @Expose
            @SerializedName("RationCard_Number")
            private String RationCard_Number;
            @Expose
            @SerializedName("Phone_no")
            private String Phone_no;
            @Expose
            @SerializedName("Passport_Number")
            private String Passport_Number;
            @Expose
            @SerializedName("Pan_number_form_60_61")
            private String Pan_number_form_60_61;
            @Expose
            @SerializedName("P8")
            private String P8;
            @Expose
            @SerializedName("P7")
            private String P7;
            @Expose
            @SerializedName("P6")
            private String P6;
            @Expose
            @SerializedName("P5")
            private String P5;
            @Expose
            @SerializedName("P4")
            private String P4;
            @Expose
            @SerializedName("P3")
            private String P3;
            @Expose
            @SerializedName("P2")
            private String P2;
            @Expose
            @SerializedName("P1")
            private String P1;
            @Expose
            @SerializedName("ModuleType")
            private String ModuleType;
            @Expose
            @SerializedName("LastName")
            private String LastName;
            @Expose
            @SerializedName("FirstName")
            private String FirstName;
            @Expose
            @SerializedName("Date_of_birth")
            private String Date_of_birth;
            @Expose
            @SerializedName("DLNo")
            private String DLNo;
            @Expose
            @SerializedName("ApplicationId")
            private String ApplicationId;
            @Expose
            @SerializedName("Aadhar_Number")
            private String Aadhar_Number;

            public void setVoterId(String voterId) {
                VoterId = voterId;
            }

            public void setUId(String UId) {
                this.UId = UId;
            }

            public void setRequestedByAppOrWeb(String requestedByAppOrWeb) {
                RequestedByAppOrWeb = requestedByAppOrWeb;
            }

            public void setRationCard_Number(String rationCard_Number) {
                RationCard_Number = rationCard_Number;
            }

            public void setPhone_no(String phone_no) {
                Phone_no = phone_no;
            }

            public void setPassport_Number(String passport_Number) {
                Passport_Number = passport_Number;
            }

            public void setPan_number_form_60_61(String pan_number_form_60_61) {
                Pan_number_form_60_61 = pan_number_form_60_61;
            }

            public void setP8(String p8) {
                P8 = p8;
            }

            public void setP7(String p7) {
                P7 = p7;
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

            public void setLastName(String lastName) {
                LastName = lastName;
            }

            public void setFirstName(String firstName) {
                FirstName = firstName;
            }

            public void setDate_of_birth(String date_of_birth) {
                Date_of_birth = date_of_birth;
            }

            public void setDLNo(String DLNo) {
                this.DLNo = DLNo;
            }

            public void setApplicationId(String applicationId) {
                ApplicationId = applicationId;
            }

            public void setAadhar_Number(String aadhar_Number) {
                Aadhar_Number = aadhar_Number;
            }
        }


    }


}
