package com.saartak.el.models.CPV;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public  class CPVRequestDTO {
    @Expose
    @SerializedName("RequestString")
    private RequestString RequestString;
    @Expose
    @SerializedName("co_applicant_id")
    private String co_applicant_id;
    @Expose
    @SerializedName("ModuleType")
    private String ModuleType;
    @Expose
    @SerializedName("ApplicationId")
    private String ApplicationId;
    @Expose
    @SerializedName("ServiceType")
    private String ServiceType;
    @Expose
    @SerializedName("CreatedByProject")
    private String CreatedByProject;
    @Expose
    @SerializedName("RequestFrom")
    private String RequestFrom="APP";
    @Expose
    @SerializedName("CreatedBy")
    private String CreatedBy;
    @Expose
    @SerializedName("CreatedDate")
    private String CreatedDate;
    @Expose
    @SerializedName("KYCId")
    private String KYCId;
    @Expose
    @SerializedName("ExternalCustomerId")
    private String ExternalCustomerId;
    @Expose
    @SerializedName("ClientID")
    private String ClientID;
    @Expose
    @SerializedName("UniqueId")
    private String UniqueId;


    public void setRequestString(RequestString requestString) {
        RequestString = requestString;
    }

    public void setCo_applicant_id(String co_applicant_id) {
        this.co_applicant_id = co_applicant_id;
    }

    public void setModuleType(String moduleType) {
        ModuleType = moduleType;
    }

    public void setApplicationId(String applicationId) {
        ApplicationId = applicationId;
    }

    public void setServiceType(String serviceType) {
        ServiceType = serviceType;
    }

    public void setCreatedByProject(String createdByProject) {
        CreatedByProject = createdByProject;
    }

    public void setRequestFrom(String requestFrom) {
        RequestFrom = requestFrom;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public void setKYCId(String KYCId) {
        this.KYCId = KYCId;
    }

    public void setExternalCustomerId(String externalCustomerId) {
        ExternalCustomerId = externalCustomerId;
    }

    public void setClientID(String clientID) {
        ClientID = clientID;
    }

    public void setUniqueId(String uniqueId) {
        UniqueId = uniqueId;
    }

    public static class RequestString {
        @Expose
        @SerializedName("CPVEquifaxRequest")
        private CPVEquifaxRequest CPVEquifaxRequest;

        public void setCPVEquifaxRequest(CPVEquifaxRequest CPVEquifaxRequest) {
            this.CPVEquifaxRequest = CPVEquifaxRequest;
        }

        public static class CPVEquifaxRequest {
            @Expose
            @SerializedName("InquiryFieldsDsv")
            private String InquiryFieldsDsv;
            @Expose
            @SerializedName("PANId")
            private String PANId;
            @Expose
            @SerializedName("Gender")
            private String Gender;
            @Expose
            @SerializedName("DOB")
            private String DOB;
            @Expose
            @SerializedName("InquiryPhone")
            private ArrayList<InquiryPhone> InquiryPhone;
            @Expose
            @SerializedName("InquiryAddress")
            private ArrayList<InquiryAddress> InquiryAddress;
            @Expose
            @SerializedName("LastName")
            private String LastName;
            @Expose
            @SerializedName("MiddleName")
            private String MiddleName;
            @Expose
            @SerializedName("FirstName")
            private String FirstName;
            @Expose
            @SerializedName("InquiryPurpose")
            private String InquiryPurpose;
            @Expose
            @SerializedName("AddressOf")
            private String addressOf;

            public void setInquiryFieldsDsv(String inquiryFieldsDsv) {
                InquiryFieldsDsv = inquiryFieldsDsv;
            }

            public void setPANId(String PANId) {
                this.PANId = PANId;
            }

            public void setGender(String gender) {
                Gender = gender;
            }

            public void setDOB(String DOB) {
                this.DOB = DOB;
            }

            public void setInquiryPhone(ArrayList<CPVEquifaxRequest.InquiryPhone> inquiryPhone) {
                InquiryPhone = inquiryPhone;
            }

            public void setInquiryAddress(ArrayList<CPVEquifaxRequest.InquiryAddress> inquiryAddress) {
                InquiryAddress = inquiryAddress;
            }

            public void setLastName(String lastName) {
                LastName = lastName;
            }

            public void setMiddleName(String middleName) {
                MiddleName = middleName;
            }

            public void setFirstName(String firstName) {
                FirstName = firstName;
            }

            public void setInquiryPurpose(String inquiryPurpose) {
                InquiryPurpose = inquiryPurpose;
            }

            public void setAddressOf(String addressOf) {
                this.addressOf = addressOf;
            }

            public static class InquiryPhone {
                @Expose
                @SerializedName("PhoneType")
                private String PhoneType;
                @Expose
                @SerializedName("Number")
                private String Number;

                public String getPhoneType() {
                    return PhoneType;
                }

                public void setPhoneType(String phoneType) {
                    PhoneType = phoneType;
                }

                public String getNumber() {
                    return Number;
                }

                public void setNumber(String number) {
                    Number = number;
                }
            }

            public static class InquiryAddress {
                @Expose
                @SerializedName("Postal")
                private String Postal="";
                @Expose
                @SerializedName("State")
                private String State;
                @Expose
                @SerializedName("AddressLine")
                private String AddressLine;

                public String getPostal() {
                    return Postal;
                }

                public void setPostal(String postal) {
                    Postal = postal;
                }

                public String getState() {
                    return State;
                }

                public void setState(String state) {
                    State = state;
                }

                public String getAddressLine() {
                    return AddressLine;
                }

                public void setAddressLine(String addressLine) {
                    AddressLine = addressLine;
                }
            }
        }
    }
}
