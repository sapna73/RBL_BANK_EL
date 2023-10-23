package com.saartak.el.models.Hunter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HunterNonIndividualRequestDTO {
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
    private String ServiceType="HunterVerificationSME";
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
    private String ExternalCustomerId="";
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
        @SerializedName("hunterVerificationSME")
        private HunterVerificationSME hunterVerificationSME;

        public void setHunterVerificationSME(HunterVerificationSME hunterVerificationSME) {
            this.hunterVerificationSME = hunterVerificationSME;
        }

        public static class HunterVerificationSME {
            @Expose
            @SerializedName("Item")
            private Item Item;

            public void setItem(HunterVerificationSME.Item item) {
                Item = item;
            }

            public static class Item {
                @Expose
                @SerializedName("term")
                private String term;
                @Expose
                @SerializedName("submissionNotificationRqd")
                private String submissionNotificationRqd;
                @Expose
                @SerializedName("product")
                private String product;
                @Expose
                @SerializedName("originator")
                private String originator;
                @Expose
                @SerializedName("identifier")
                private String identifier;
                @Expose
                @SerializedName("date")
                private String date;
                @Expose
                @SerializedName("count")
                private String count;
                @Expose
                @SerializedName("classification")
                private String classification;
                @Expose
                @SerializedName("ClntFlg")
                private String ClntFlg;
                @Expose
                @SerializedName("AssVal")
                private int AssVal;
                @Expose
                @SerializedName("assOrigVal")
                private String assOrigVal;
                @Expose
                @SerializedName("LnPrp")
                private String LnPrp="";
                @Expose
                @SerializedName("appVal")
                private String appVal;
                @Expose
                @SerializedName("appDte")
                private String appDte;
                @Expose
                @SerializedName("ModuleType")
                private String ModuleType;
                @Expose
                @SerializedName("TelephoneExt")
                private String TelephoneExt;
                @Expose
                @SerializedName("telephoneNumber")
                private String telephoneNumber;
                @Expose
                @SerializedName("mainPromoter")
                private MainPromoter mainPromoter;
                @Expose
                @SerializedName("companyDetails")
                private CompanyDetails companyDetails;
                @Expose
                @SerializedName("coPromoter")
                private List<CoPromoter> coPromoter;

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

                public void setClntFlg(String clntFlg) {
                    ClntFlg = clntFlg;
                }

                public void setAssVal(int assVal) {
                    AssVal = assVal;
                }

                public void setAssOrigVal(String assOrigVal) {
                    this.assOrigVal = assOrigVal;
                }

                public void setLnPrp(String lnPrp) {
                    LnPrp = lnPrp;
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

                public void setTelephoneExt(String telephoneExt) {
                    TelephoneExt = telephoneExt;
                }

                public void setTelephoneNumber(String telephoneNumber) {
                    this.telephoneNumber = telephoneNumber;
                }

                public void setMainPromoter(MainPromoter mainPromoter) {
                    this.mainPromoter = mainPromoter;
                }

                public void setCompanyDetails(CompanyDetails companyDetails) {
                    this.companyDetails = companyDetails;
                }

                public void setCoPromoter(List<CoPromoter> coPromoter) {
                    this.coPromoter = coPromoter;
                }

                public static class MainPromoter {
                    @Expose
                    @SerializedName("firstName")
                    private String firstName;
                    @Expose
                    @SerializedName("middleName")
                    private String middleName="";
                    @Expose
                    @SerializedName("lastName")
                    private String lastName="";
                    @Expose
                    @SerializedName("recSexCode")
                    private int recSexCode;
                    @Expose
                    @SerializedName("income")
                    private String income;
                    @Expose
                    @SerializedName("residentialAddress")
                    private ResidentialAddress residentialAddress;
                    @Expose
                    @SerializedName("idDocument")
                    private IdDocument idDocument;
                    @Expose
                    @SerializedName("email")
                    private Email email;
                    @Expose
                    @SerializedName("dateOfBirth")
                    private String dateOfBirth;
                    @Expose
                    @SerializedName("bankAccount")
                    private BankAccount bankAccount;


                    public void setResidentialAddress(ResidentialAddress residentialAddress) {
                        this.residentialAddress = residentialAddress;
                    }

                    public void setIdDocument(IdDocument idDocument) {
                        this.idDocument = idDocument;
                    }

                    public void setEmail(Email email) {
                        this.email = email;
                    }

                    public void setDateOfBirth(String dateOfBirth) {
                        this.dateOfBirth = dateOfBirth;
                    }

                    public void setBankAccount(BankAccount bankAccount) {
                        this.bankAccount = bankAccount;
                    }

                    public void setFirstName(String firstName) {
                        this.firstName = firstName;
                    }

                    public void setMiddleName(String middleName) {
                        this.middleName = middleName;
                    }

                    public void setLastName(String lastName) {
                        this.lastName = lastName;
                    }

                    public void setRecSexCode(int recSexCode) {
                        this.recSexCode = recSexCode;
                    }

                    public void setIncome(String income) {
                        this.income = income;
                    }

                    public static class ResidentialAddress {
                        @Expose
                        @SerializedName("state")
                        private String state;
                        @Expose
                        @SerializedName("pincode")
                        private String pincode;
                        @Expose
                        @SerializedName("country")
                        private String country="INDIA";
                        @Expose
                        @SerializedName("city")
                        private String city;
                        @Expose
                        @SerializedName("address")
                        private String address;

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

                    public static class IdDocument {
                        @Expose
                        @SerializedName("CompanyTan")
                        private String CompanyTan;
                        @Expose
                        @SerializedName("AcknowNo")
                        private String AcknowNo;
                        @Expose
                        @SerializedName("FatherHusbandName")
                        private String FatherHusbandName;
                        @Expose
                        @SerializedName("EReciptNo")
                        private String EReciptNo;
                        @Expose
                        @SerializedName("CountryOfIssue")
                        private String CountryOfIssue;
                        @Expose
                        @SerializedName("PlaceOfIssue")
                        private String PlaceOfIssue;
                        @Expose
                        @SerializedName("DocAc")
                        private String DocAc = "";
                        @Expose
                        @SerializedName("recDocCode")
                        private int recDocCode;
                        @Expose
                        @SerializedName("docNumber")
                        private String docNumber = "";

                        public void setCompanyTan(String companyTan) {
                            CompanyTan = companyTan;
                        }

                        public void setAcknowNo(String acknowNo) {
                            AcknowNo = acknowNo;
                        }

                        public void setFatherHusbandName(String fatherHusbandName) {
                            FatherHusbandName = fatherHusbandName;
                        }

                        public void setEReciptNo(String EReciptNo) {
                            this.EReciptNo = EReciptNo;
                        }

                        public void setCountryOfIssue(String countryOfIssue) {
                            CountryOfIssue = countryOfIssue;
                        }

                        public void setPlaceOfIssue(String placeOfIssue) {
                            PlaceOfIssue = placeOfIssue;
                        }

                        public void setDocAc(String docAc) {
                            DocAc = docAc;
                        }

                        public void setRecDocCode(int recDocCode) {
                            this.recDocCode = recDocCode;
                        }

                        public void setDocNumber(String docNumber) {
                            this.docNumber = docNumber;
                        }
                    }

                    public static class Email {
                        @Expose
                        @SerializedName("DomainName")
                        private String DomainName;
                        @Expose
                        @SerializedName("CoAddress")
                        private String CoAddress;
                        @Expose
                        @SerializedName("emailAddress")
                        private String emailAddress;

                        public void setDomainName(String domainName) {
                            DomainName = domainName;
                        }

                        public void setCoAddress(String coAddress) {
                            CoAddress = coAddress;
                        }

                        public void setEmailAddress(String emailAddress) {
                            this.emailAddress = emailAddress;
                        }
                    }

                    public static class BankAccount {
                        @Expose
                        @SerializedName("branch")
                        private String branch;
                        @Expose
                        @SerializedName("bankName")
                        private String bankName;
                        @Expose
                        @SerializedName("accountNumber")
                        private String accountNumber;

                        public void setBranch(String branch) {
                            this.branch = branch;
                        }

                        public void setBankName(String bankName) {
                            this.bankName = bankName;
                        }

                        public void setAccountNumber(String accountNumber) {
                            this.accountNumber = accountNumber;
                        }
                    }
                }

                public static class CompanyDetails {
                    @Expose
                    @SerializedName("businessTelephone")
                    private String businessTelephone;
                    @Expose
                    @SerializedName("EmpNumber")
                    private String EmpNumber;
                    @Expose
                    @SerializedName("TurnOver")
                    private String TurnOver;
                    @Expose
                    @SerializedName("SalesTaxNo")
                    private String SalesTaxNo;
                    @Expose
                    @SerializedName("CregNo")
                    private String CregNo;
                    @Expose
                    @SerializedName("DateOfIncor")
                    private String DateOfIncor;
                    @Expose
                    @SerializedName("TanNo")
                    private String TanNo;
                    @Expose
                    @SerializedName("orgName")
                    private String orgName;
                    @Expose
                    @SerializedName("CompanyAddress")
                    private CompanyAddress CompanyAddress;

                    public void setBusinessTelephone(String businessTelephone) {
                        this.businessTelephone = businessTelephone;
                    }

                    public void setEmpNumber(String empNumber) {
                        EmpNumber = empNumber;
                    }

                    public void setTurnOver(String turnOver) {
                        TurnOver = turnOver;
                    }

                    public void setSalesTaxNo(String salesTaxNo) {
                        SalesTaxNo = salesTaxNo;
                    }

                    public void setCregNo(String cregNo) {
                        CregNo = cregNo;
                    }

                    public void setDateOfIncor(String dateOfIncor) {
                        DateOfIncor = dateOfIncor;
                    }

                    public void setTanNo(String tanNo) {
                        TanNo = tanNo;
                    }

                    public void setOrgName(String orgName) {
                        this.orgName = orgName;
                    }

                    public void setCompanyAddress(CompanyDetails.CompanyAddress companyAddress) {
                        CompanyAddress = companyAddress;
                    }

                    public static class CompanyAddress {
                        @Expose
                        @SerializedName("state")
                        private String state;
                        @Expose
                        @SerializedName("pincode")
                        private String pincode;
                        @Expose
                        @SerializedName("country")
                        private String country="INDIA";
                        @Expose
                        @SerializedName("city")
                        private String city;
                        @Expose
                        @SerializedName("address")
                        private String address;

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

                public static class CoPromoter {
                    @Expose
                    @SerializedName("coPromoterIDDocument")
                    private CoPromoterIDDocument coPromoterIDDocument;
                    @Expose
                    @SerializedName("residentialAddress")
                    private ResidentialAddress residentialAddress;
                    @Expose
                    @SerializedName("nationality")
                    private String nationality;
                    @Expose
                    @SerializedName("income")
                    private String income;
                    @Expose
                    @SerializedName("DateOfBirth")
                    private String DateOfBirth;
                    @Expose
                    @SerializedName("LastName")
                    private String LastName="";
                    @Expose
                    @SerializedName("MiddleName")
                    private String MiddleName="";
                    @Expose
                    @SerializedName("firstName")
                    private String firstName="";
                    @Expose
                    @SerializedName("ModuleType")
                    private String ModuleType;

                    public void setCoPromoterIDDocument(CoPromoterIDDocument coPromoterIDDocument) {
                        this.coPromoterIDDocument = coPromoterIDDocument;
                    }

                    public void setResidentialAddress(ResidentialAddress residentialAddress) {
                        this.residentialAddress = residentialAddress;
                    }

                    public void setNationality(String nationality) {
                        this.nationality = nationality;
                    }

                    public void setIncome(String income) {
                        this.income = income;
                    }

                    public void setDateOfBirth(String dateOfBirth) {
                        DateOfBirth = dateOfBirth;
                    }

                    public void setLastName(String lastName) {
                        LastName = lastName;
                    }

                    public void setMiddleName(String middleName) {
                        MiddleName = middleName;
                    }

                    public void setFirstName(String firstName) {
                        this.firstName = firstName;
                    }

                    public void setModuleType(String moduleType) {
                        ModuleType = moduleType;
                    }

                    public static class CoPromoterIDDocument {
                        @Expose
                        @SerializedName("CompanyTan")
                        private String CompanyTan;
                        @Expose
                        @SerializedName("AcknowNo")
                        private String AcknowNo;
                        @Expose
                        @SerializedName("FatherHusbandName")
                        private String FatherHusbandName;
                        @Expose
                        @SerializedName("EReciptNo")
                        private String EReciptNo;
                        @Expose
                        @SerializedName("CountryOfIssue")
                        private String CountryOfIssue;
                        @Expose
                        @SerializedName("PlaceOfIssue")
                        private String PlaceOfIssue;
                        @Expose
                        @SerializedName("DocAc")
                        private String DocAc="";
                        @Expose
                        @SerializedName("recDocCode")
                        private int recDocCode;
                        @Expose
                        @SerializedName("docNumber")
                        private String docNumber="";

                        public void setCompanyTan(String companyTan) {
                            CompanyTan = companyTan;
                        }

                        public void setAcknowNo(String acknowNo) {
                            AcknowNo = acknowNo;
                        }

                        public void setFatherHusbandName(String fatherHusbandName) {
                            FatherHusbandName = fatherHusbandName;
                        }

                        public void setEReciptNo(String EReciptNo) {
                            this.EReciptNo = EReciptNo;
                        }

                        public void setCountryOfIssue(String countryOfIssue) {
                            CountryOfIssue = countryOfIssue;
                        }

                        public void setPlaceOfIssue(String placeOfIssue) {
                            PlaceOfIssue = placeOfIssue;
                        }

                        public void setDocAc(String docAc) {
                            DocAc = docAc;
                        }

                        public void setRecDocCode(int recDocCode) {
                            this.recDocCode = recDocCode;
                        }

                        public void setDocNumber(String docNumber) {
                            this.docNumber = docNumber;
                        }
                    }

                    public static class ResidentialAddress {
                        @Expose
                        @SerializedName("CompanyTan")
                        private String CompanyTan;
                        @Expose
                        @SerializedName("AcknowNo")
                        private String AcknowNo;
                        @Expose
                        @SerializedName("FatherHusbandName")
                        private String FatherHusbandName;
                        @Expose
                        @SerializedName("EReciptNo")
                        private String EReciptNo;
                        @Expose
                        @SerializedName("CountryOfIssue")
                        private String CountryOfIssue;
                        @Expose
                        @SerializedName("PlaceOfIssue")
                        private String PlaceOfIssue;
                        @Expose
                        @SerializedName("DocAc")
                        private String DocAc="";
                        @Expose
                        @SerializedName("DocNumber")
                        private String DocNumber="";
                        @Expose
                        @SerializedName("RecDocCode")
                        private String RecDocCode;
                        @Expose
                        @SerializedName("TimeAtAddress")
                        private String TimeAtAddress;
                        @Expose
                        @SerializedName("state")
                        private String state;
                        @Expose
                        @SerializedName("pincode")
                        private String pincode;
                        @Expose
                        @SerializedName("country")
                        private String country;
                        @Expose
                        @SerializedName("city")
                        private String city;
                        @Expose
                        @SerializedName("addressline1")
                        private String address;

                        public void setCompanyTan(String companyTan) {
                            CompanyTan = companyTan;
                        }

                        public void setAcknowNo(String acknowNo) {
                            AcknowNo = acknowNo;
                        }

                        public void setFatherHusbandName(String fatherHusbandName) {
                            FatherHusbandName = fatherHusbandName;
                        }

                        public void setEReciptNo(String EReciptNo) {
                            this.EReciptNo = EReciptNo;
                        }

                        public void setCountryOfIssue(String countryOfIssue) {
                            CountryOfIssue = countryOfIssue;
                        }

                        public void setPlaceOfIssue(String placeOfIssue) {
                            PlaceOfIssue = placeOfIssue;
                        }

                        public void setDocAc(String docAc) {
                            DocAc = docAc;
                        }

                        public void setDocNumber(String docNumber) {
                            DocNumber = docNumber;
                        }

                        public void setRecDocCode(String recDocCode) {
                            RecDocCode = recDocCode;
                        }

                        public void setTimeAtAddress(String timeAtAddress) {
                            TimeAtAddress = timeAtAddress;
                        }

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
