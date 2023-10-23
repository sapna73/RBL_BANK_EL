package com.saartak.el.models.Dedupe;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class DedupeResponseDTO {

    @Expose
    @SerializedName("ApiResponse")
    private ApiResponse ApiResponse;
    @Expose
    @SerializedName("ErrorMessage")
    private String ErrorMessage;
    @Expose
    @SerializedName("ErrorCode")
    private String ErrorCode;
    @Expose
    @SerializedName("ResponseMessage")
    private String ResponseMessage;
    @Expose
    @SerializedName("ResponseCode")
    private String ResponseCode;
    @Expose
    @SerializedName("UniqueId")
    private String UniqueId;

    @Expose
    @SerializedName("ModuleType")
    private String moduleType;

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public ApiResponse getApiResponse() {
        return ApiResponse;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public String getErrorCode() {
        return ErrorCode;
    }

    public String getResponseMessage() {
        return ResponseMessage;
    }

    public String getResponseCode() {
        return ResponseCode;
    }

    public String getUniqueId() {
        return UniqueId;
    }

    public static class ApiResponse {
        @Expose
        @SerializedName("GetCustomerResponse")
        private GetCustomerResponse GetCustomerResponse;
        @Expose
        @SerializedName("Message")
        private String Message;
        @Expose
        @SerializedName("Status")
        private String Status;

        public GetCustomerResponse getGetCustomerResponse() {
            return GetCustomerResponse;
        }

        public String getMessage() {
            return Message;
        }

        public String getStatus() {
            return Status;
        }

        public static class GetCustomerResponse {
            @Expose
            @SerializedName("RequestId")
            private String RequestId;
            @Expose
            @SerializedName("Message")
            private String Message;
            @Expose
            @SerializedName("Status")
            private String Status;
            @Expose
            @SerializedName("Results")
            private Results Results;
            @Expose
            @SerializedName("Metadata")
            private Metadata Metadata;

            public String getRequestId() {
                return RequestId;
            }

            public String getMessage() {
                return Message;
            }

            public String getStatus() {
                return Status;
            }

            public Results getResults() {
                return Results;
            }

            public Metadata getMetadata() {
                return Metadata;
            }

            public static class Results {
                @Expose
                @SerializedName("CustomerDetails")
                private List<CustomerDetails> CustomerDetails;

                public List<CustomerDetails> getCustomerDetails() {
                    return CustomerDetails;
                }

                public static class CustomerDetails {
                    @Expose
                    @SerializedName("Email")
                    private Email Email;
                    @Expose
                    @SerializedName("Contact")
                    private Contact Contact;
                    @Expose
                    @SerializedName("Address")
                    private Address Address;
                    @Expose
                    @SerializedName("Demographics")
                    private Demographics Demographics;

                    public Email getEmail() {
                        return Email;
                    }

                    public Contact getContact() {
                        return Contact;
                    }

                    public Address getAddress() {
                        return Address;
                    }

                    public Demographics getDemographics() {
                        return Demographics;
                    }

                    public static  class Demographics {

                        @Expose
                        @SerializedName("RationCardNumber")
                        private String RationCardNumber;
                        @Expose
                        @SerializedName("DrvLicenceNo")
                        private String DrvLicenceNo;
                        @Expose
                        @SerializedName("PassportNo")
                        private String PassportNo;
                        @Expose
                        @SerializedName("FatherName")
                        private String FatherName;
                        @Expose
                        @SerializedName("MatchType")
                        private String MatchType;
                        @Expose
                        @SerializedName("Aadhar")
                        private String Aadhar;
                        @Expose
                        @SerializedName("VoterId")
                        private String VoterId;
                        @Expose
                        @SerializedName("Pan")
                        private String Pan;
                        @Expose
                        @SerializedName("Dob")
                        private String Dob;
                        @Expose
                        @SerializedName("Name")
                        private String Name;
                        @Expose
                        @SerializedName("Base")
                        private String Base;
                        @Expose
                        @SerializedName("SrcSystem")
                        private String SrcSystem;
                        @Expose
                        @SerializedName("CustomerID")
                        private String CustomerID;
                        @Expose
                        @SerializedName("UCICType")
                        private String UCICType;
                        @Expose
                        @SerializedName("UCIC")
                        private String UCIC;

                        public String getRationCardNumber() {
                            return RationCardNumber;
                        }

                        public String getDrvLicenceNo() {
                            return DrvLicenceNo;
                        }

                        public String getPassportNo() {
                            return PassportNo;
                        }

                        public String getFatherName() {
                            return FatherName;
                        }

                        public String getMatchType() {
                            return MatchType;
                        }

                        public String getAadhar() {
                            return Aadhar;
                        }

                        public String getVoterId() {
                            return VoterId;
                        }

                        public String getPan() {
                            return Pan;
                        }

                        public String getDob() {
                            return Dob;
                        }

                        public String getName() {
                            return Name;
                        }

                        public String getBase() {
                            return Base;
                        }

                        public String getSrcSystem() {
                            return SrcSystem;
                        }

                        public String getCustomerID() {
                            return CustomerID;
                        }

                        public String getUCICType() {
                            return UCICType;
                        }

                        public String getUCIC() {
                            return UCIC;
                        }
                    }

                    public static class Email {
                        @Expose
                        @SerializedName("EmailId")
                        private String EmailId;

                        public String getEmailId() {
                            return EmailId;
                        }
                    }

                    public static class Contact {
                        @Expose
                        @SerializedName("PhoneNo")
                        private String PhoneNo;
                        @Expose
                        @SerializedName("ContactType")
                        private String ContactType;

                        public String getPhoneNo() {
                            return PhoneNo;
                        }

                        public String getContactType() {
                            return ContactType;
                        }
                    }

                    public static class Address {
                        @Expose
                        @SerializedName("State")
                        private String State;
                        @Expose
                        @SerializedName("Pincode")
                        private String Pincode;
                        @Expose
                        @SerializedName("City")
                        private String City;
                        @Expose
                        @SerializedName("Areaname")
                        private String Areaname;
                        @Expose
                        @SerializedName("Landmark")
                        private String Landmark;
                        @Expose
                        @SerializedName("Address")
                        private String Address;

                        public String getState() {
                            return State;
                        }

                        public String getPincode() {
                            return Pincode;
                        }

                        public String getCity() {
                            return City;
                        }

                        public String getAreaname() {
                            return Areaname;
                        }

                        public String getLandmark() {
                            return Landmark;
                        }

                        public String getAddress() {
                            return Address;
                        }
                    }
                }
            }
            public static class Metadata {
                @Expose
                @SerializedName("Message")
                private String Message;
                @Expose
                @SerializedName("Matchcount")
                private String Matchcount;
                @Expose
                @SerializedName("Status")
                private String Status;
                @Expose
                @SerializedName("RequestId")
                private String RequestId;

                public String getMessage() {
                    return Message;
                }

                public String getMatchcount() {
                    return Matchcount;
                }

                public String getStatus() {
                    return Status;
                }

                public String getRequestId() {
                    return RequestId;
                }
            }
        }
    }

}
