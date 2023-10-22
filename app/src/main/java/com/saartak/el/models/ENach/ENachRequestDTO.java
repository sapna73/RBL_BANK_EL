package com.saartak.el.models.ENach;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class ENachRequestDTO {

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

    public String getAADHAR() {
        return AADHAR;
    }

    public void setAADHAR(String AADHAR) {
        this.AADHAR = AADHAR;
    }

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

    public ENachRequestDTO.RequestString getRequestString() {
        return RequestString;
    }

    public void setRequestString(ENachRequestDTO.RequestString requestString) {
        RequestString = requestString;
    }

    public String getKYCId() {
        return KYCId;
    }

    public void setKYCId(String KYCId) {
        this.KYCId = KYCId;
    }

    public String getExternalCustomerId() {
        return ExternalCustomerId;
    }

    public void setExternalCustomerId(String externalCustomerId) {
        ExternalCustomerId = externalCustomerId;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public String getCreatedByProject() {
        return CreatedByProject;
    }

    public void setCreatedByProject(String createdByProject) {
        CreatedByProject = createdByProject;
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

    public static class RequestString {
        @Expose
        @SerializedName("email_notify")
        private String email_notify;
        @Expose
        @SerializedName("sms_notify")
        private String sms_notify;
        @Expose
        @SerializedName("subscription_registration")
        private Subscription_registration subscription_registration;
        @Expose
        @SerializedName("description")
        private String description;
        @Expose
        @SerializedName("currency")
        private String currency;
        @Expose
        @SerializedName("amount")
        private String amount;
        @Expose
        @SerializedName("type")
        private String type;
        @Expose
        @SerializedName("customer")
        private Customer customer;

        public void setEmail_notify(String email_notify) {
            this.email_notify = email_notify;
        }

        public void setSms_notify(String sms_notify) {
            this.sms_notify = sms_notify;
        }

        public void setSubscription_registration(Subscription_registration subscription_registration) {
            this.subscription_registration = subscription_registration;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setCustomer(Customer customer) {
            this.customer = customer;
        }

        public static class Subscription_registration {
            @Expose
            @SerializedName("bank_account")
            private Bank_account bank_account;
            @Expose
            @SerializedName("emistartdate")
            private String emistartdate;
            @Expose
            @SerializedName("totalamount")
            private String totalamount;
            @Expose
            @SerializedName("noofmonths")
            private String noofmonths;
            @Expose
            @SerializedName("emiamount")
            private String emiamount;
            @Expose
            @SerializedName("max_amount")
            private String max_amount;
            @Expose
            @SerializedName("auth_type")
            private String auth_type;
            @Expose
            @SerializedName("method")
            private String method;

            public void setBank_account(Bank_account bank_account) {
                this.bank_account = bank_account;
            }

            public void setEmistartdate(String emistartdate) {
                this.emistartdate = emistartdate;
            }

            public void setTotalamount(String totalamount) {
                this.totalamount = totalamount;
            }

            public void setNoofmonths(String noofmonths) {
                this.noofmonths = noofmonths;
            }

            public void setEmiamount(String emiamount) {
                this.emiamount = emiamount;
            }

            public void setMax_amount(String max_amount) {
                this.max_amount = max_amount;
            }

            public void setAuth_type(String auth_type) {
                this.auth_type = auth_type;
            }

            public void setMethod(String method) {
                this.method = method;
            }

            public static class Bank_account {
                @Expose
                @SerializedName("ifsc_code")
                private String ifsc_code;
                @Expose
                @SerializedName("account_type")
                private String account_type;
                @Expose
                @SerializedName("account_number")
                private String account_number;
                @Expose
                @SerializedName("beneficiary_name")
                private String beneficiary_name;

                public void setIfsc_code(String ifsc_code) {
                    this.ifsc_code = ifsc_code;
                }

                public void setAccount_type(String account_type) {
                    this.account_type = account_type;
                }

                public void setAccount_number(String account_number) {
                    this.account_number = account_number;
                }

                public void setBeneficiary_name(String beneficiary_name) {
                    this.beneficiary_name = beneficiary_name;
                }
            }


        }

        public static class Customer {
            @Expose
            @SerializedName("contact")
            private String contact;
            @Expose
            @SerializedName("email")
            private String email;
            @Expose
            @SerializedName("name")
            private String name;

            public void setContact(String contact) {
                this.contact = contact;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }

}
