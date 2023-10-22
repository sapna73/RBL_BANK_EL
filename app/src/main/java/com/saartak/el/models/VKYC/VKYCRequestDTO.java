package com.saartak.el.models.VKYC;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class VKYCRequestDTO {

    @Expose
    @SerializedName("AADHAR")
    private String AADHAR;
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
    @SerializedName("KYCId")
    private String KYCId;
    @Expose
    @SerializedName("ExternalCustomerId")
    private String ExternalCustomerId;
    @Expose
    @SerializedName("CreatedDate")
    private String CreatedDate;
    @Expose
    @SerializedName("CreatedByProject")
    private String CreatedByProject;
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

    public void setAADHAR(String AADHAR) {
        this.AADHAR = AADHAR;
    }

    public void setUniqueId(String uniqueId) {
        UniqueId = uniqueId;
    }

    public void setServiceType(String serviceType) {
        ServiceType = serviceType;
    }

    public void setRequestString(VKYCRequestDTO.RequestString requestString) {
        RequestString = requestString;
    }

    public void setKYCId(String KYCId) {
        this.KYCId = KYCId;
    }

    public void setExternalCustomerId(String externalCustomerId) {
        ExternalCustomerId = externalCustomerId;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public void setCreatedByProject(String createdByProject) {
        CreatedByProject = createdByProject;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
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
        @SerializedName("customerFinancialInfo")
        private CustomerFinancialInfo customerFinancialInfo;
        @Expose
        @SerializedName("aadhaarData")
        private AadhaarData aadhaarData;
        @Expose
        @SerializedName("applicationDetails")
        private ApplicationDetails applicationDetails;
        @Expose
        @SerializedName("appointmentAddress")
        private String appointmentAddress="";
        @Expose
        @SerializedName("isCustomerInitiated")
        private String isCustomerInitiated="";
        @Expose
        @SerializedName("isEkyc")
        private String isEkyc="";
        @Expose
        @SerializedName("isOkyc")
        private String isOkyc="";
        @Expose
        @SerializedName("ekycCompletionDateTime")
        private String ekycCompletionDateTime="";
        @Expose
        @SerializedName("customerName")
        private String customerName="";
        @Expose
        @SerializedName("customerMob")
        private String customerMob="";
        @Expose
        @SerializedName("customerEmail")
        private String customerEmail="";
        @Expose
        @SerializedName("customerId")
        private String customerId="";
        @Expose
        @SerializedName("agent_id")
        private String agent_id="";
        @Expose
        @SerializedName("send_notification")
        private String send_notification="";
        @Expose
        @SerializedName("link_type")
        private String link_type="";
        @Expose
        @SerializedName("session_type")
        private String session_type="";
        @Expose
        @SerializedName("clientCode")
        private String clientCode="";

        public void setCustomerFinancialInfo(CustomerFinancialInfo customerFinancialInfo) {
            this.customerFinancialInfo = customerFinancialInfo;
        }

        public void setAadhaarData(AadhaarData aadhaarData) {
            this.aadhaarData = aadhaarData;
        }

        public void setApplicationDetails(ApplicationDetails applicationDetails) {
            this.applicationDetails = applicationDetails;
        }

        public void setAppointmentAddress(String appointmentAddress) {
            this.appointmentAddress = appointmentAddress;
        }

        public void setIsCustomerInitiated(String isCustomerInitiated) {
            this.isCustomerInitiated = isCustomerInitiated;
        }

        public void setIsEkyc(String isEkyc) {
            this.isEkyc = isEkyc;
        }

        public void setIsOkyc(String isOkyc) {
            this.isOkyc = isOkyc;
        }

        public void setEkycCompletionDateTime(String ekycCompletionDateTime) {
            this.ekycCompletionDateTime = ekycCompletionDateTime;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public void setCustomerMob(String customerMob) {
            this.customerMob = customerMob;
        }

        public void setCustomerEmail(String customerEmail) {
            this.customerEmail = customerEmail;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public void setAgent_id(String agent_id) {
            this.agent_id = agent_id;
        }

        public void setSend_notification(String send_notification) {
            this.send_notification = send_notification;
        }

        public void setLink_type(String link_type) {
            this.link_type = link_type;
        }

        public void setSession_type(String session_type) {
            this.session_type = session_type;
        }

        public void setClientCode(String clientCode) {
            this.clientCode = clientCode;
        }

        public static class CustomerFinancialInfo {
            @Expose
            @SerializedName("educationalQualification")
            private String educationalQualification;
            @Expose
            @SerializedName("residenceType")
            private String residenceType;
            @Expose
            @SerializedName("isPoliticallyExposed")
            private String isPoliticallyExposed;
            @Expose
            @SerializedName("incomeRange")
            private String incomeRange;
            @Expose
            @SerializedName("occupation")
            private String occupation;
            @Expose
            @SerializedName("sourceOfIncome")
            private String sourceOfIncome;
            @Expose
            @SerializedName("countryOfResidence")
            private String countryOfResidence;
            @Expose
            @SerializedName("customerSubType")
            private String customerSubType;
            @Expose
            @SerializedName("customerType")
            private String customerType;

            public void setEducationalQualification(String educationalQualification) {
                this.educationalQualification = educationalQualification;
            }

            public void setResidenceType(String residenceType) {
                this.residenceType = residenceType;
            }

            public void setIsPoliticallyExposed(String isPoliticallyExposed) {
                this.isPoliticallyExposed = isPoliticallyExposed;
            }

            public void setIncomeRange(String incomeRange) {
                this.incomeRange = incomeRange;
            }

            public void setOccupation(String occupation) {
                this.occupation = occupation;
            }

            public void setSourceOfIncome(String sourceOfIncome) {
                this.sourceOfIncome = sourceOfIncome;
            }

            public void setCountryOfResidence(String countryOfResidence) {
                this.countryOfResidence = countryOfResidence;
            }

            public void setCustomerSubType(String customerSubType) {
                this.customerSubType = customerSubType;
            }

            public void setCustomerType(String customerType) {
                this.customerType = customerType;
            }
        }

        public static class AadhaarData {
            @Expose
            @SerializedName("aadhaarImage")
            private String aadhaarImage="";
            @Expose
            @SerializedName("referenceId")
            private String referenceId="";
            @Expose
            @SerializedName("gender")
            private String gender="";
            @Expose
            @SerializedName("address")
            private String address="";
            @Expose
            @SerializedName("dob")
            private String dob="";
            @Expose
            @SerializedName("name")
            private String name="";

            public void setAadhaarImage(String aadhaarImage) {
                this.aadhaarImage = aadhaarImage;
            }

            public void setReferenceId(String referenceId) {
                this.referenceId = referenceId;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public void setDob(String dob) {
                this.dob = dob;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class ApplicationDetails {
            @Expose
            @SerializedName("productType")
            private String productType;

            public void setProductType(String productType) {
                this.productType = productType;
            }
        }
    }


}
