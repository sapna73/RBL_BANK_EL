package com.saartak.el.models.VKYC;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class DownStreamResponseDTO {

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

    public DownStreamResponseDTO.ApiResponse getApiResponse() {
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
        @SerializedName("vcipStatus")
        private String vcipStatus;
        @Expose
        @SerializedName("vcipQuestions")
        private List<VcipQuestions> vcipQuestions;
        @Expose
        @SerializedName("vcipPendingStatus")
        private String vcipPendingStatus;
        @Expose
        @SerializedName("vcipDeclineReason")
        private VcipDeclineReason vcipDeclineReason;
        @Expose
        @SerializedName("vcipCreatedTime")
        private String vcipCreatedTime;
        @Expose
        @SerializedName("vcipCompletionDateTime")
        private String vcipCompletionDateTime;
        @Expose
        @SerializedName("vcipAuditorEmail")
        private String vcipAuditorEmail;
        @Expose
        @SerializedName("vcipAuditorCompletedTime")
        private String vcipAuditorCompletedTime;
        @Expose
        @SerializedName("vcipAuditorCompletedDate")
        private String vcipAuditorCompletedDate;
        @Expose
        @SerializedName("vcipAgentEmail")
        private String vcipAgentEmail;
        @Expose
        @SerializedName("vcipAgentCompletedDate")
        private String vcipAgentCompletedDate;
        @Expose
        @SerializedName("storedVideoUrl")
        private String storedVideoUrl;
        @Expose
        @SerializedName("panData")
        private PanData panData;
        @Expose
        @SerializedName("otherDetails")
        private String otherDetails;
        @Expose
        @SerializedName("locationData")
        private LocationData locationData;
        @Expose
        @SerializedName("listOfDocuments")
        private List<ListOfDocuments> listOfDocuments;
        @Expose
        @SerializedName("isOkyc")
        private String isOkyc;
        @Expose
        @SerializedName("isEkyc")
        private String isEkyc;
        @Expose
        @SerializedName("imageMatchScoreData")
        private ImageMatchScoreData imageMatchScoreData;
        @Expose
        @SerializedName("ekycCompletionDateTime")
        private String ekycCompletionDateTime;
        @Expose
        @SerializedName("deviceInfo")
        private DeviceInfo deviceInfo;
        @Expose
        @SerializedName("customerInfo")
        private CustomerInfo customerInfo;
        @Expose
        @SerializedName("cpuniqueRefNo")
        private String cpuniqueRefNo;
        @Expose
        @SerializedName("applicationDetails")
        private String applicationDetails;
        @Expose
        @SerializedName("applicantDetailsAsPerLoan")
        private String applicantDetailsAsPerLoan;
        @Expose
        @SerializedName("aadhaarGetinfo")
        private AadhaarGetinfo aadhaarGetinfo;

        public String getVcipStatus() {
            return vcipStatus;
        }

        public List<VcipQuestions> getVcipQuestions() {
            return vcipQuestions;
        }

        public String getVcipPendingStatus() {
            return vcipPendingStatus;
        }

        public VcipDeclineReason getVcipDeclineReason() {
            return vcipDeclineReason;
        }

        public String getVcipCreatedTime() {
            return vcipCreatedTime;
        }

        public String getVcipCompletionDateTime() {
            return vcipCompletionDateTime;
        }

        public String getVcipAuditorEmail() {
            return vcipAuditorEmail;
        }

        public String getVcipAuditorCompletedTime() {
            return vcipAuditorCompletedTime;
        }

        public String getVcipAuditorCompletedDate() {
            return vcipAuditorCompletedDate;
        }

        public String getVcipAgentEmail() {
            return vcipAgentEmail;
        }

        public String getVcipAgentCompletedDate() {
            return vcipAgentCompletedDate;
        }

        public String getStoredVideoUrl() {
            return storedVideoUrl;
        }

        public PanData getPanData() {
            return panData;
        }

        public String getOtherDetails() {
            return otherDetails;
        }

        public LocationData getLocationData() {
            return locationData;
        }

        public List<ListOfDocuments> getListOfDocuments() {
            return listOfDocuments;
        }

        public String getIsOkyc() {
            return isOkyc;
        }

        public String getIsEkyc() {
            return isEkyc;
        }

        public ImageMatchScoreData getImageMatchScoreData() {
            return imageMatchScoreData;
        }

        public String getEkycCompletionDateTime() {
            return ekycCompletionDateTime;
        }

        public DeviceInfo getDeviceInfo() {
            return deviceInfo;
        }

        public CustomerInfo getCustomerInfo() {
            return customerInfo;
        }

        public String getCpuniqueRefNo() {
            return cpuniqueRefNo;
        }

        public String getApplicationDetails() {
            return applicationDetails;
        }

        public String getApplicantDetailsAsPerLoan() {
            return applicantDetailsAsPerLoan;
        }

        public AadhaarGetinfo getAadhaarGetinfo() {
            return aadhaarGetinfo;
        }

        public static class VcipQuestions {
            @Expose
            @SerializedName("selfieAadhaarMatchStatus")
            private String selfieAadhaarMatchStatus;
            @Expose
            @SerializedName("questionId")
            private String questionId;
            @Expose
            @SerializedName("question")
            private String question;
            @Expose
            @SerializedName("isCorrect")
            private String isCorrect;
            @Expose
            @SerializedName("answer")
            private String answer;

            public String getSelfieAadhaarMatchStatus() {
                return selfieAadhaarMatchStatus;
            }

            public String getQuestionId() {
                return questionId;
            }

            public String getQuestion() {
                return question;
            }

            public String getIsCorrect() {
                return isCorrect;
            }

            public String getAnswer() {
                return answer;
            }
        }

        public static class VcipDeclineReason {
            @Expose
            @SerializedName("comment")
            private String comment;
            @Expose
            @SerializedName("type")
            private String type;

            public String getComment() {
                return comment;
            }

            public String getType() {
                return type;
            }
        }

        public static class PanData {
            @Expose
            @SerializedName("panStatus")
            private String panStatus;
            @Expose
            @SerializedName("panNumber")
            private String panNumber;
            @Expose
            @SerializedName("panName")
            private String panName;
            @Expose
            @SerializedName("panImage")
            private String panImage;

            public String getPanStatus() {
                return panStatus;
            }

            public String getPanNumber() {
                return panNumber;
            }

            public String getPanName() {
                return panName;
            }

            public String getPanImage() {
                return panImage;
            }
        }

        public static class LocationData {
            @Expose
            @SerializedName("longitude")
            private double longitude;
            @Expose
            @SerializedName("latitude")
            private double latitude;
            @Expose
            @SerializedName("geoAddress")
            private String geoAddress;

            public double getLongitude() {
                return longitude;
            }

            public double getLatitude() {
                return latitude;
            }

            public String getGeoAddress() {
                return geoAddress;
            }
        }

        public static class ListOfDocuments {
            @Expose
            @SerializedName("fileName")
            private String fileName;
            @Expose
            @SerializedName("file")
            private String file;
            @Expose
            @SerializedName("downloadLink")
            private String downloadLink;
            @Expose
            @SerializedName("documentType")
            private String documentType;
            @Expose
            @SerializedName("documentStatus")
            private String documentStatus;

            public String getFileName() {
                return fileName;
            }

            public String getFile() {
                return file;
            }

            public String getDownloadLink() {
                return downloadLink;
            }

            public String getDocumentType() {
                return documentType;
            }

            public String getDocumentStatus() {
                return documentStatus;
            }
        }

        public static class ImageMatchScoreData {
            @Expose
            @SerializedName("selfiePanMatchStatus")
            private String selfiePanMatchStatus;
            @Expose
            @SerializedName("selfiePanMatchScore")
            private int selfiePanMatchScore;
            @Expose
            @SerializedName("selfieImage")
            private String selfieImage;
            @Expose
            @SerializedName("selfieAadhaarMatchStatus")
            private String selfieAadhaarMatchStatus;
            @Expose
            @SerializedName("selfieAadhaarMatchScore")
            private String selfieAadhaarMatchScore;

            public String getSelfiePanMatchStatus() {
                return selfiePanMatchStatus;
            }

            public int getSelfiePanMatchScore() {
                return selfiePanMatchScore;
            }

            public String getSelfieImage() {
                return selfieImage;
            }

            public String getSelfieAadhaarMatchStatus() {
                return selfieAadhaarMatchStatus;
            }

            public String getSelfieAadhaarMatchScore() {
                return selfieAadhaarMatchScore;
            }
        }

        public static class DeviceInfo {
            @Expose
            @SerializedName("operatingSystem")
            private String operatingSystem;
            @Expose
            @SerializedName("locationPermission")
            private String locationPermission;
            @Expose
            @SerializedName("deviceInfo")
            private String deviceInfo;
            @Expose
            @SerializedName("cameraPermisssion")
            private String cameraPermisssion;
            @Expose
            @SerializedName("browserVersion")
            private String browserVersion;
            @Expose
            @SerializedName("browser")
            private String browser;

            public String getOperatingSystem() {
                return operatingSystem;
            }

            public String getLocationPermission() {
                return locationPermission;
            }

            public String getDeviceInfo() {
                return deviceInfo;
            }

            public String getCameraPermisssion() {
                return cameraPermisssion;
            }

            public String getBrowserVersion() {
                return browserVersion;
            }

            public String getBrowser() {
                return browser;
            }
        }

        public static class CustomerInfo {
            @Expose
            @SerializedName("customerName")
            private String customerName;
            @Expose
            @SerializedName("customerMob")
            private String customerMob;
            @Expose
            @SerializedName("customerId")
            private String customerId;
            @Expose
            @SerializedName("customerEmail")
            private String customerEmail;

            public String getCustomerName() {
                return customerName;
            }

            public String getCustomerMob() {
                return customerMob;
            }

            public String getCustomerId() {
                return customerId;
            }

            public String getCustomerEmail() {
                return customerEmail;
            }
        }

        public static class AadhaarGetinfo {
            @Expose
            @SerializedName("referenceId")
            private String referenceId;
            @Expose
            @SerializedName("name")
            private String name;
            @Expose
            @SerializedName("gender")
            private String gender;
            @Expose
            @SerializedName("dob")
            private String dob;
            @Expose
            @SerializedName("address")
            private String address;
            @Expose
            @SerializedName("aadhaarImage")
            private String aadhaarImage;

            public String getReferenceId() {
                return referenceId;
            }

            public String getName() {
                return name;
            }

            public String getGender() {
                return gender;
            }

            public String getDob() {
                return dob;
            }

            public String getAddress() {
                return address;
            }

            public String getAadhaarImage() {
                return aadhaarImage;
            }
        }
    }


}
