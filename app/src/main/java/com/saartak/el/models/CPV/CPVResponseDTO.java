package com.saartak.el.models.CPV;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class CPVResponseDTO {


    @Expose
    @SerializedName("ApiResponse")
    public ApiResponse ApiResponse;
    @Expose
    @SerializedName("ErrorMessage")
    public String ErrorMessage;
    @Expose
    @SerializedName("ErrorCode")
    public String ErrorCode;
    @Expose
    @SerializedName("ResponseMessage")
    public String ResponseMessage;
    @Expose
    @SerializedName("ResponseCode")
    public String ResponseCode;
    @Expose
    @SerializedName("UniqueId")
    public String UniqueId;
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

    public void setApiResponse(ApiResponse apiResponse) {
        ApiResponse = apiResponse;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String errorCode) {
        ErrorCode = errorCode;
    }

    public String getResponseMessage() {
        return ResponseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        ResponseMessage = responseMessage;
    }

    public String getResponseCode() {
        return ResponseCode;
    }

    public void setResponseCode(String responseCode) {
        ResponseCode = responseCode;
    }

    public String getUniqueId() {
        return UniqueId;
    }

    public void setUniqueId(String uniqueId) {
        UniqueId = uniqueId;
    }

    public static class ApiResponse {
        @Expose
        @SerializedName("TelecomResponse")
        public TelecomResponse TelecomResponse;
        @Expose
        @SerializedName("Disclaimer")
        public String Disclaimer;
        @Expose
        @SerializedName("IDAndContactInfo")
        public IDAndContactInfo IDAndContactInfo;
        @Expose
        @SerializedName("StatusCode")
        public String StatusCode;

        public TelecomResponse getTelecomResponse() {
            return TelecomResponse;
        }

        public void setTelecomResponse(TelecomResponse telecomResponse) {
            TelecomResponse = telecomResponse;
        }

        public String getDisclaimer() {
            return Disclaimer;
        }

        public void setDisclaimer(String disclaimer) {
            Disclaimer = disclaimer;
        }

        public IDAndContactInfo getIDAndContactInfo() {
            return IDAndContactInfo;
        }

        public void setIDAndContactInfo(IDAndContactInfo IDAndContactInfo) {
            this.IDAndContactInfo = IDAndContactInfo;
        }

        public String getStatusCode() {
            return StatusCode;
        }

        public void setStatusCode(String statusCode) {
            StatusCode = statusCode;
        }

        public static class IDAndContactInfo {
            @Expose
            @SerializedName("PhoneInfo")
            public PhoneInfo PhoneInfo;
            @Expose
            @SerializedName("AddressInfo")
            public List<AddressInfo> AddressInfo;
            @Expose
            @SerializedName("IdentityInfo")
            public IdentityInfo IdentityInfo;
            @Expose
            @SerializedName("PersonalInfo")
            public PersonalInfo PersonalInfo;

            public PhoneInfo getPhoneInfo() {
                return PhoneInfo;
            }

            public void setPhoneInfo(PhoneInfo phoneInfo) {
                PhoneInfo = phoneInfo;
            }

            public List<AddressInfo> getAddressInfo() {
                return AddressInfo;
            }

            public void setAddressInfo(List<AddressInfo> addressInfo) {
                AddressInfo = addressInfo;
            }

            public IdentityInfo getIdentityInfo() {
                return IdentityInfo;
            }

            public void setIdentityInfo(IdentityInfo identityInfo) {
                IdentityInfo = identityInfo;
            }

            public PersonalInfo getPersonalInfo() {
                return PersonalInfo;
            }

            public void setPersonalInfo(PersonalInfo personalInfo) {
                PersonalInfo = personalInfo;
            }
            public static class PhoneInfo {
                @Expose
                @SerializedName("Number")
                public String Number;

                public String getNumber() {
                    return Number;
                }

                public void setNumber(String number) {
                    Number = number;
                }
            }

            public static class AddressInfo {
                @Expose
                @SerializedName("Type")
                public String Type;
                @Expose
                @SerializedName("Postal")
                public String Postal;
                @Expose
                @SerializedName("State")
                public String State;
                @Expose
                @SerializedName("Address")
                public String Address;

                public String getType() {
                    return Type;
                }

                public void setType(String type) {
                    Type = type;
                }

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

                public String getAddress() {
                    return Address;
                }

                public void setAddress(String address) {
                    Address = address;
                }
            }
            public static class IdentityInfo {
                @Expose
                @SerializedName("PANId")
                public PANId PANId;

                public PANId getPANId() {
                    return PANId;
                }

                public void setPANId(PANId PANId) {
                    this.PANId = PANId;
                }

                public static class PANId {
                    @Expose
                    @SerializedName("IdNumber")
                    public String IdNumber;

                    public String getIdNumber() {
                        return IdNumber;
                    }

                    public void setIdNumber(String idNumber) {
                        IdNumber = idNumber;
                    }
                }
            }

            public static class PersonalInfo {
                @Expose
                @SerializedName("Occupation")
                public String Occupation;
                @Expose
                @SerializedName("Age")
                public int Age;
                @Expose
                @SerializedName("Gender")
                public String Gender;
                @Expose
                @SerializedName("DateOfBirth")
                public String DateOfBirth;
                @Expose
                @SerializedName("Name")
                public Name Name;

                public String getOccupation() {
                    return Occupation;
                }

                public void setOccupation(String occupation) {
                    Occupation = occupation;
                }

                public int getAge() {
                    return Age;
                }

                public void setAge(int age) {
                    Age = age;
                }

                public String getGender() {
                    return Gender;
                }

                public void setGender(String gender) {
                    Gender = gender;
                }

                public String getDateOfBirth() {
                    return DateOfBirth;
                }

                public void setDateOfBirth(String dateOfBirth) {
                    DateOfBirth = dateOfBirth;
                }

                public Name getName() {
                    return Name;
                }

                public void setName(Name name) {
                    Name = name;
                }

                public static class Name {
                    @Expose
                    @SerializedName("LastName")
                    public String LastName;
                    @Expose
                    @SerializedName("MiddleName")
                    public String MiddleName;
                    @Expose
                    @SerializedName("FirstName")
                    public String FirstName;

                    public String getLastName() {
                        return LastName;
                    }

                    public void setLastName(String lastName) {
                        LastName = lastName;
                    }

                    public String getMiddleName() {
                        return MiddleName;
                    }

                    public void setMiddleName(String middleName) {
                        MiddleName = middleName;
                    }

                    public String getFirstName() {
                        return FirstName;
                    }

                    public void setFirstName(String firstName) {
                        FirstName = firstName;
                    }
                }
            }


        }
    }

    public static class TelecomResponse {
        @Expose
        @SerializedName("TRField3")
        public String TRField3;
        @Expose
        @SerializedName("TRField2")
        public String TRField2;
        @Expose
        @SerializedName("TRField1")
        public String TRField1;

        public String getTRField3() {
            return TRField3;
        }

        public void setTRField3(String TRField3) {
            this.TRField3 = TRField3;
        }

        public String getTRField2() {
            return TRField2;
        }

        public void setTRField2(String TRField2) {
            this.TRField2 = TRField2;
        }

        public String getTRField1() {
            return TRField1;
        }

        public void setTRField1(String TRField1) {
            this.TRField1 = TRField1;
        }
    }

}