package com.saartak.el.models.Hunter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HunterRequestDTO {
    @Expose
    @SerializedName("AADHAR")
    private String AADHAR = "";
    @Expose
    @SerializedName("UniqueId")
    private String UniqueId = "";
    @Expose
    @SerializedName("ServiceType")
    private String ServiceType = "HunterVerificationRetail";
    @Expose
    @SerializedName("RequestString")
    private RequestString RequestString;

    @Expose
    @SerializedName("RequestFrom")
    private String RequestFrom="APP";
    @Expose
    @SerializedName("KYCId")
    private String KYCId = "";
    @Expose
    @SerializedName("ExternalCustomerId")
    private String ExternalCustomerId = "";
    @Expose
    @SerializedName("CreatedDate")
    private String CreatedDate = "";
    @Expose
    @SerializedName("CreatedByProject")
    private String CreatedByProject = "";
    @Expose
    @SerializedName("CreatedBy")
    private String CreatedBy = "";
    @Expose
    @SerializedName("ClientID")
    private String ClientID = "";

    public void setAADHAR(String AADHAR) {
        this.AADHAR = AADHAR;
    }

    public void setUniqueId(String uniqueId) {
        UniqueId = uniqueId;
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

    public void setClientID(String clientID) {
        ClientID = clientID;
    }

    public static class RequestString {
        @Expose
        @SerializedName("hunterVerificationRetail")
        private HunterVerificationRetail hunterVerificationRetail;

        public void setHunterVerificationRetail(HunterVerificationRetail hunterVerificationRetail) {
            this.hunterVerificationRetail = hunterVerificationRetail;
        }

        public static class HunterVerificationRetail {
            @Expose
            @SerializedName("term")
            private String term = "0";
            @Expose
            @SerializedName("submissionNotificationRqd")
            private String submissionNotificationRqd = "";
            @Expose
            @SerializedName("product")
            private String product = "";
            @Expose
            @SerializedName("originator")
            private String originator = "";
            @Expose
            @SerializedName("Item")
            private Item Item;
            @Expose
            @SerializedName("identifier")
            private String identifier = "";
            @Expose
            @SerializedName("date")
            private String date = "";
            @Expose
            @SerializedName("count")
            private String count = "";
            @Expose
            @SerializedName("classification")
            private String classification = "";
            @Expose
            @SerializedName("assOrigVal")
            private String assOrigVal = "0";
            @Expose
            @SerializedName("appVal")
            private String appVal = "";
            @Expose
            @SerializedName("appDte")
            private String appDte = "";
            @Expose
            @SerializedName("ModuleType")
            private String ModuleType = "";

            public void setTerm(String term) {
                this.term = term;
            }

            public void setSubmissionNotificationRqd(String submissionNotificationRqd) {
                this.submissionNotificationRqd = submissionNotificationRqd;
            }

            public void setProduct(String product) {
                this.product = product;
            }

            public void setOriginator(String originator) {
                this.originator = originator;
            }

            public void setItem(Item item) {
                Item = item;
            }

            public void setIdentifier(String identifier) {
                this.identifier = identifier;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public void setClassification(String classification) {
                this.classification = classification;
            }

            public void setAssOrigVal(String assOrigVal) {
                this.assOrigVal = assOrigVal;
            }

            public void setAppVal(String appVal) {
                this.appVal = appVal;
            }

            public void setAppDte(String appDte) {
                this.appDte = appDte;
            }

            public void setModuleType(String moduleType) {
                ModuleType = moduleType;
            }

            public static class Item {
                @Expose
                @SerializedName("MainApplicant")
                private MainApplicant MainApplicant;
                @Expose
                @SerializedName("JointApplicant")
                private List<JointApplicant> JointApplicant;

                public void setMainApplicant(MainApplicant mainApplicant) {
                    MainApplicant = mainApplicant;
                }

                public void setJointApplicant(List<JointApplicant> jointApplicant) {
                    JointApplicant = jointApplicant;
                }

                public static class JointApplicant {
                    @Expose
                    @SerializedName("residentialAddress")
                    private ResidentialAddress residentialAddress;
                    @Expose
                    @SerializedName("pan")
                    private String pan = "";
                    @Expose
                    @SerializedName("nationality")
                    private String nationality = "";
                    @Expose
                    @SerializedName("ModuleType")
                    private String ModuleType = "";
                    @Expose
                    @SerializedName("income")
                    private String income = "0";
                    @Expose
                    @SerializedName("firstName")
                    private String firstName = "";
                    @Expose
                    @SerializedName("VoterId")
                    private String VoterId = "";
                    @Expose
                    @SerializedName("UId")
                    private String UId = "";
                    @Expose
                    @SerializedName("RationCard_Number")
                    private String RationCard_Number = "";
                    @Expose
                    @SerializedName("Passport_Number")
                    private String Passport_Number = "";
                    @Expose
                    @SerializedName("DLNo")
                    private String DLNo = "";

                    public void setResidentialAddress(ResidentialAddress residentialAddress) {
                        this.residentialAddress = residentialAddress;
                    }

                    public static class ResidentialAddress {
                        @Expose
                        @SerializedName("state")
                        private String state = "";
                        @Expose
                        @SerializedName("pincode")
                        private String pincode = "";
                        @Expose
                        @SerializedName("country")
                        private String country = "INDIA";
                        @Expose
                        @SerializedName("city")
                        private String city = "";
                        @Expose
                        @SerializedName("address")
                        private String address = "";

                        public void setState(String state) {
                            this.state = state;
                        }

                        public void setPincode(String pincode) {
                            this.pincode = pincode;
                        }

                        public void setCountry(String country) {
                            this.country = country;
                        }

                        public void setCity(String city) {
                            this.city = city;
                        }

                        public void setAddress(String address) {
                            this.address = address;
                        }
                    }

                    public void setPan(String pan) {
                        this.pan = pan;
                    }

                    public void setNationality(String nationality) {
                        this.nationality = nationality;
                    }

                    public void setModuleType(String moduleType) {
                        ModuleType = moduleType;
                    }

                    public void setIncome(String income) {
                        this.income = income;
                    }

                    public void setFirstName(String firstName) {
                        this.firstName = firstName;
                    }

                    public void setVoterId(String voterId) {
                        VoterId = voterId;
                    }

                    public void setUId(String UId) {
                        this.UId = UId;
                    }

                    public void setRationCard_Number(String rationCard_Number) {
                        RationCard_Number = rationCard_Number;
                    }

                    public void setPassport_Number(String passport_Number) {
                        Passport_Number = passport_Number;
                    }

                    public void setDLNo(String DLNo) {
                        this.DLNo = DLNo;
                    }
                }

                public static class MainApplicant {
                    @Expose
                    @SerializedName("residentialAddress")
                    private ResidentialAddress residentialAddress;
                    @Expose
                    @SerializedName("mobile")
                    private String mobile = "";
                    @Expose
                    @SerializedName("lastName")
                    private String lastName = "";
                    @Expose
                    @SerializedName("idDocument")
                    private IdDocument idDocument;
                    @Expose
                    @SerializedName("firstName")
                    private String firstName = "";
                    @Expose
                    @SerializedName("employer")
                    private Employer employer;
                    @Expose
                    @SerializedName("email")
                    private Email email;
                    @Expose
                    @SerializedName("dateOfBirth")
                    private String dateOfBirth = "";
                    @Expose
                    @SerializedName("businessTelephone")
                    private String businessTelephone = "";
                    @Expose
                    @SerializedName("bankAccount")
                    private BankAccount bankAccount;

                    public void setResidentialAddress(ResidentialAddress residentialAddress) {
                        this.residentialAddress = residentialAddress;
                    }

                    public void setMobile(String mobile) {
                        this.mobile = mobile;
                    }

                    public void setLastName(String lastName) {
                        this.lastName = lastName;
                    }

                    public void setIdDocument(IdDocument idDocument) {
                        this.idDocument = idDocument;
                    }

                    public void setFirstName(String firstName) {
                        this.firstName = firstName;
                    }

                    public void setEmployer(Employer employer) {
                        this.employer = employer;
                    }

                    public void setEmail(Email email) {
                        this.email = email;
                    }

                    public void setDateOfBirth(String dateOfBirth) {
                        this.dateOfBirth = dateOfBirth;
                    }

                    public void setBusinessTelephone(String businessTelephone) {
                        this.businessTelephone = businessTelephone;
                    }

                    public void setBankAccount(BankAccount bankAccount) {
                        this.bankAccount = bankAccount;
                    }

                    public static class ResidentialAddress {
                        @Expose
                        @SerializedName("state")
                        private String state = "";
                        @Expose
                        @SerializedName("pincode")
                        private String pincode = "";
                        @Expose
                        @SerializedName("country")
                        private String country = "INDIA";
                        @Expose
                        @SerializedName("city")
                        private String city = "";
                        @Expose
                        @SerializedName("address")
                        private String address = "";

                        public void setState(String state) {
                            this.state = state;
                        }

                        public void setPincode(String pincode) {
                            this.pincode = pincode;
                        }

                        public void setCountry(String country) {
                            this.country = country;
                        }

                        public void setCity(String city) {
                            this.city = city;
                        }

                        public void setAddress(String address) {
                            this.address = address;
                        }
                    }

                    public static class Email {
                        @Expose
                        @SerializedName("emailAddress")
                        private String emailAddress = "";

                        public String getEmailAddress() {
                            return emailAddress;
                        }

                        public void setEmailAddress(String emailAddress) {
                            this.emailAddress = emailAddress;
                        }
                    }

                    public static class BankAccount {
                        @Expose
                        @SerializedName("bankName")
                        private String bankName = "";
                        @Expose
                        @SerializedName("accountInteger")
                        private String accountInteger = "";

                        public void setBankName(String bankName) {
                            this.bankName = bankName;
                        }

                        public void setAccountInteger(String accountInteger) {
                            this.accountInteger = accountInteger;
                        }
                    }

                    public static class IdDocument {
                        @Expose
                        @SerializedName("recDocCode")
                        private String recDocCode = "";
                        @Expose
                        @SerializedName("docNumber")
                        private String docNumber = "";

                        public void setRecDocCode(String recDocCode) {
                            this.recDocCode = recDocCode;
                        }

                        public void setDocNumber(String docNumber) {
                            this.docNumber = docNumber;
                        }
                    }

                    public static class Employer {
                        @Expose
                        @SerializedName("orgName")
                        private String orgName = "";
                        @Expose
                        @SerializedName("employerAddress")
                        private EmployerAddress employerAddress;

                        public void setOrgName(String orgName) {
                            this.orgName = orgName;
                        }

                        public void setEmployerAddress(EmployerAddress employerAddress) {
                            this.employerAddress = employerAddress;
                        }

                        public static class EmployerAddress {
                            @Expose
                            @SerializedName("state")
                            private String state = "";
                            @Expose
                            @SerializedName("pincode")
                            private String pincode = "";
                            @Expose
                            @SerializedName("country")
                            private String country = "INDIA";
                            @Expose
                            @SerializedName("city")
                            private String city = "";
                            @Expose
                            @SerializedName("address")
                            private String address = "";

                            public void setState(String state) {
                                this.state = state;
                            }

                            public void setPincode(String pincode) {
                                this.pincode = pincode;
                            }

                            public void setCountry(String country) {
                                this.country = country;
                            }

                            public void setCity(String city) {
                                this.city = city;
                            }

                            public void setAddress(String address) {
                                this.address = address;
                            }
                        }


                    }
                }


            }
        }
    }

}
