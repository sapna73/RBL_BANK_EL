package com.saartak.el.models.Posidex;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PosidexRequestDTO {

    @Expose
    private String ClientID = "";

    @Expose
    private String ServiceType = "Posidex";

    @Expose
    private String CreatedByProject = "";

    @Expose
    @SerializedName("RequestFrom")
    private String RequestFrom="APP";

    @Expose
    private String ExternalCustomerId = "";

    @Expose
    private String CreatedDate = "";

    @Expose
    private String CreatedBy = "EL";

    @Expose
    private String UniqueId = "";

    @Expose
    private String AADHAR = "";

    @Expose
    private String KYCId = "";

    @Expose
    private RequestString RequestString;

    @Expose
    @SerializedName("ModuleType")
    private String ModuleType;

    @Expose
    @SerializedName("co_applicant_id")
    private String coapplicantId;


    public static class RequestString {

        @Expose
        private Envelope envelope;

        public static class Envelope {
            @Expose
            private Header Header;

            public static class Header {

                @Expose
                private FindCustomerheadreq FindCustomerheadreq;

                public static class FindCustomerheadreq {
                    @Expose
                    private String RequestUUID = "";

                    @Expose
                    private String ServiceRequestId = "";

                    @Expose
                    private String MessageDateTime = "";

                    @Expose
                    private String ChannelId = "";

                    @Expose
                    private String UserID = "";

                    @Expose
                    private String CorpID = "";


                    public String getRequestUUID() {
                        return RequestUUID;
                    }

                    public void setRequestUUID(String requestUUID) {
                        RequestUUID = requestUUID;
                    }

                    public String getServiceRequestId() {
                        return ServiceRequestId;
                    }

                    public void setServiceRequestId(String serviceRequestId) {
                        ServiceRequestId = serviceRequestId;
                    }

                    public String getMessageDateTime() {
                        return MessageDateTime;
                    }

                    public void setMessageDateTime(String messageDateTime) {
                        MessageDateTime = messageDateTime;
                    }

                    public String getChannelId() {
                        return ChannelId;
                    }

                    public void setChannelId(String channelId) {
                        ChannelId = channelId;
                    }

                    public String getUserID() {
                        return UserID;
                    }

                    public void setUserID(String userID) {
                        UserID = userID;
                    }

                    public String getCorpID() {
                        return CorpID;
                    }

                    public void setCorpID(String corpID) {
                        CorpID = corpID;
                    }
                }

                public Envelope.Header.FindCustomerheadreq getFindCustomerheadreq() {
                    return FindCustomerheadreq;
                }

                public void setFindCustomerheadreq(Envelope.Header.FindCustomerheadreq findCustomerheadreq) {
                    FindCustomerheadreq = findCustomerheadreq;
                }
            }


            @Expose
            private Body Body;

            public static class Body {

                @Expose
                private FindCustomer FindCustomer;

                public static class FindCustomer {

                    @Expose
                    private Metadata Metadata;
                    public static class Metadata {
                        @Expose
                        private String RequestId = "";

                        @Expose
                        private String CustomerID = "";

                        @Expose
                        private String SrcSystem = "";

                        @Expose
                        private String IsUpdateCustomer = "";

                        @Expose
                        private String RequestType = "";

                        @Expose
                        private String isToBeVerified = "";

                        public String getRequestId() {
                            return RequestId;
                        }

                        public void setRequestId(String requestId) {
                            RequestId = requestId;
                        }


                        public String getCustomerID() {
                            return CustomerID;
                        }

                        public void setCustomerID(String customerID) {
                            CustomerID = customerID;
                        }

                        public String getSrcSystem() {
                            return SrcSystem;
                        }

                        public void setSrcSystem(String srcSystem) {
                            SrcSystem = srcSystem;
                        }

                        public String getIsUpdateCustomer() {
                            return IsUpdateCustomer;
                        }

                        public void setIsUpdateCustomer(String isUpdateCustomer) {
                            IsUpdateCustomer = isUpdateCustomer;
                        }

                        public String getRequestType() {
                            return RequestType;
                        }

                        public void setRequestType(String requestType) {
                            RequestType = requestType;
                        }

                        public String getIsToBeVerified() {
                            return isToBeVerified;
                        }

                        public void setIsToBeVerified(String isToBeVerified) {
                            this.isToBeVerified = isToBeVerified;
                        }
                    }

                    @Expose
                    private Demographics Demographics;
                    public static class Demographics {
                        @Expose
                        private String Name = "";

                        @Expose
                        private String Fathername = "";

                        @Expose
                        private String Dob = "";

                        @Expose
                        private String Pan = "";

                        @Expose
                        private String DocNumber = "";

                        @Expose
                        private String PassportNo = "";

                        @Expose
                        private String DrvLicenceNo = "";


                        @Expose
                        private String VoterId = "";

                        @Expose
                        private String UID = "";

                        @Expose
                        private String RationCardNo = "";

                        @Expose
                        private String country1 = "";

                        @Expose
                        private String nationality = "";

                        @Expose
                        private String CIN = "";

                        public String getName() {
                            return Name;
                        }

                        public void setName(String name) {
                            Name = name;
                        }

                        public String getFathername() {
                            return Fathername;
                        }

                        public void setFathername(String fathername) {
                            Fathername = fathername;
                        }

                        public String getDob() {
                            return Dob;
                        }

                        public void setDob(String dob) {
                            Dob = dob;
                        }

                        public String getPan() {
                            return Pan;
                        }

                        public void setPan(String pan) {
                            Pan = pan;
                        }

                        public String getDocNumber() {
                            return DocNumber;
                        }

                        public void setDocNumber(String docNumber) {
                            DocNumber = docNumber;
                        }

                        public String getPassportNo() {
                            return PassportNo;
                        }

                        public void setPassportNo(String passportNo) {
                            PassportNo = passportNo;
                        }

                        public String getDrvLicenceNo() {
                            return DrvLicenceNo;
                        }

                        public void setDrvLicenceNo(String drvLicenceNo) {
                            DrvLicenceNo = drvLicenceNo;
                        }

                        public String getVoterId() {
                            return VoterId;
                        }

                        public void setVoterId(String voterId) {
                            VoterId = voterId;
                        }

                        public String getUID() {
                            return UID;
                        }

                        public void setUID(String UID) {
                            this.UID = UID;
                        }

                        public String getRationCardNo() {
                            return RationCardNo;
                        }

                        public void setRationCardNo(String rationCardNo) {
                            RationCardNo = rationCardNo;
                        }

                        public String getCountry1() {
                            return country1;
                        }

                        public void setCountry1(String country1) {
                            this.country1 = country1;
                        }

                        public String getNationality() {
                            return nationality;
                        }

                        public void setNationality(String nationality) {
                            this.nationality = nationality;
                        }

                        public String getCIN() {
                            return CIN;
                        }

                        public void setCIN(String CIN) {
                            this.CIN = CIN;
                        }
                    }

                    @Expose
                    private Address Address;
                    public static class Address {
                        @Expose
                        private String addresstype = "";

                        @Expose
                        private String Address1 = "";

                        @Expose
                        private String Landmark = "";

                        @Expose
                        private String Areaname = "";

                        @Expose
                        private String City = "";

                        @Expose
                        private String Pincode = "";

                        @Expose
                        private String State = "";


                        public String getAddresstype() {
                            return addresstype;
                        }

                        public void setAddresstype(String addresstype) {
                            this.addresstype = addresstype;
                        }

                        public String getAddress1() {
                            return Address1;
                        }

                        public void setAddress1(String address1) {
                            Address1 = address1;
                        }

                        public String getLandmark() {
                            return Landmark;
                        }

                        public void setLandmark(String landmark) {
                            Landmark = landmark;
                        }

                        public String getAreaname() {
                            return Areaname;
                        }

                        public void setAreaname(String areaname) {
                            Areaname = areaname;
                        }

                        public String getCity() {
                            return City;
                        }

                        public void setCity(String city) {
                            City = city;
                        }

                        public String getPincode() {
                            return Pincode;
                        }

                        public void setPincode(String pincode) {
                            Pincode = pincode;
                        }

                        public String getState() {
                            return State;
                        }

                        public void setState(String state) {
                            State = state;
                        }
                    }

                    @Expose
                    private Contact Contact;
                    public static class Contact {
                        @Expose
                        private String ContactType = "";

                        @Expose
                        private String PhoneNo = "";

                        @Expose
                        private String MobileNo = "";

                        @Expose
                        private String StdCode = "";

                        public String getContactType() {
                            return ContactType;
                        }

                        public void setContactType(String contactType) {
                            ContactType = contactType;
                        }

                        public String getPhoneNo() {
                            return PhoneNo;
                        }

                        public void setPhoneNo(String phoneNo) {
                            PhoneNo = phoneNo;
                        }

                        public String getMobileNo() {
                            return MobileNo;
                        }

                        public void setMobileNo(String mobileNo) {
                            MobileNo = mobileNo;
                        }

                        public String getStdCode() {
                            return StdCode;
                        }

                        public void setStdCode(String stdCode) {
                            StdCode = stdCode;
                        }
                    }

                    @Expose
                    private Email Email;
                    public static class Email {
                        @Expose
                        private String EmailType = "";

                        @Expose
                        private String EmailId = "";

                        public String getEmailType() {
                            return EmailType;
                        }

                        public void setEmailType(String emailType) {
                            EmailType = emailType;
                        }

                        public String getEmailId() {
                            return EmailId;
                        }

                        public void setEmailId(String emailId) {
                            EmailId = emailId;
                        }
                    }

                    public Envelope.Body.FindCustomer.Metadata getMetadata() {
                        return Metadata;
                    }

                    public void setMetadata(Envelope.Body.FindCustomer.Metadata metadata) {
                        Metadata = metadata;
                    }

                    public Envelope.Body.FindCustomer.Demographics getDemographics() {
                        return Demographics;
                    }

                    public void setDemographics(Envelope.Body.FindCustomer.Demographics demographics) {
                        Demographics = demographics;
                    }

                    public Envelope.Body.FindCustomer.Address getAddress() {
                        return Address;
                    }

                    public void setAddress(Envelope.Body.FindCustomer.Address address) {
                        Address = address;
                    }

                    public Envelope.Body.FindCustomer.Contact getContact() {
                        return Contact;
                    }

                    public void setContact(Envelope.Body.FindCustomer.Contact contact) {
                        Contact = contact;
                    }

                    public Envelope.Body.FindCustomer.Email getEmail() {
                        return Email;
                    }

                    public void setEmail(Envelope.Body.FindCustomer.Email email) {
                        Email = email;
                    }
                }


                public Envelope.Body.FindCustomer getFindCustomer() {
                    return FindCustomer;
                }

                public void setFindCustomer(Envelope.Body.FindCustomer findCustomer) {
                    FindCustomer = findCustomer;
                }
            }


            public Envelope.Header getHeader() {
                return Header;
            }

            public void setHeader(Envelope.Header header) {
                Header = header;
            }

            public Envelope.Body getBody() {
                return Body;
            }

            public void setBody(Envelope.Body body) {
                Body = body;
            }
        }

        public Envelope getEnvelope() {
            return envelope;
        }

        public void setEnvelope(Envelope envelope) {
            this.envelope = envelope;
        }
    }


    public String getClientID() {
        return ClientID;
    }

    public void setClientID(String clientID) {
        ClientID = clientID;
    }

    public String getServiceType() {
        return ServiceType;
    }

    public void setServiceType(String serviceType) {
        ServiceType = serviceType;
    }

    public String getCreatedByProject() {
        return CreatedByProject;
    }

    public void setCreatedByProject(String createdByProject) {
        CreatedByProject = createdByProject;
    }

    public String getExternalCustomerId() {
        return ExternalCustomerId;
    }

    public void setExternalCustomerId(String externalCustomerId) {
        ExternalCustomerId = externalCustomerId;
    }
    public void setRequestFrom(String requestFrom) {
        RequestFrom = requestFrom;
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

    public String getUniqueId() {
        return UniqueId;
    }

    public void setUniqueId(String uniqueId) {
        UniqueId = uniqueId;
    }

    public String getAADHAR() {
        return AADHAR;
    }

    public void setAADHAR(String AADHAR) {
        this.AADHAR = AADHAR;
    }

    public String getKYCId() {
        return KYCId;
    }

    public void setKYCId(String KYCId) {
        this.KYCId = KYCId;
    }

    public String getModuleType() {
        return ModuleType;
    }

    public void setModuleType(String moduleType) {
        ModuleType = moduleType;
    }

    public String getCoapplicantId() {
        return coapplicantId;
    }

    public void setCoapplicantId(String coapplicantId) {
        this.coapplicantId = coapplicantId;
    }

    public PosidexRequestDTO.RequestString getRequestString() {
        return RequestString;
    }

    public void setRequestString(PosidexRequestDTO.RequestString requestString) {
        RequestString = requestString;
    }
}
