package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;

public class AadhaarVaultRequestDTO {

    @Expose
    private String UniqueId;
    @Expose
    private String ClientID;
    @Expose
    private String ExternalCustomerId;
    @Expose
    private String KYCId;
    @Expose
    private String CreatedDate;
    @Expose
    private String CreatedBy;
    @Expose
    private String ServiceType;
    @Expose
    private RequestStringClass RequestString;

    public String getUniqueId() {
        return UniqueId;
    }

    public void setUniqueId(String uniqueId) {
        UniqueId = uniqueId;
    }

    public String getClientID() {
        return ClientID;
    }

    public void setClientID(String clientID) {
        ClientID = clientID;
    }

    public String getExternalCustomerId() {
        return ExternalCustomerId;
    }

    public void setExternalCustomerId(String externalCustomerId) {
        ExternalCustomerId = externalCustomerId;
    }

    public String getKYCId() {
        return KYCId;
    }

    public void setKYCId(String KYCId) {
        this.KYCId = KYCId;
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

    public String getServiceType() {
        return ServiceType;
    }

    public void setServiceType(String serviceType) {
        ServiceType = serviceType;
    }

    public RequestStringClass getRequestString() {
        return RequestString;
    }

    public void setRequestString(RequestStringClass requestString) {
        RequestString = requestString;
    }

    public static class RequestStringClass{
        @Expose
        private AadharVaultClass AadharVault;

        public AadharVaultClass getAadharVault() {
            return AadharVault;
        }

        public void setAadharVault(AadharVaultClass aadharVault) {
            AadharVault = aadharVault;
        }
    }

    public static class AadharVaultClass{
        @Expose
        private String Uid;
        @Expose
        private String Vid;
        @Expose
        private String ReqId;
        @Expose
        private String TokenId;
        @Expose
        private String SourceUniqueRefNo;
        @Expose
        private String SourceIdentifier1;
        @Expose
        private String SourceIdentifier2;
        @Expose
        private String SourceIdentifier3;
        @Expose
        private String SourceIdentifier4;
        @Expose
        private String SourceIdentifier5;
        @Expose
        private String SourceIdentifier6;
        @Expose
        private String SourceIdentifier7;
        @Expose
        private String SourceIdentifier8;
        @Expose
        private String SourceIdentifier9;
        @Expose
        private String SourceIdentifier10;
        @Expose
        private String UserConsentFlag;
        @Expose
        private String SourceDescription;

        public String getUid() {
            return Uid;
        }

        public void setUid(String uid) {
            Uid = uid;
        }

        public String getVid() {
            return Vid;
        }

        public void setVid(String vid) {
            Vid = vid;
        }

        public String getReqId() {
            return ReqId;
        }

        public void setReqId(String reqId) {
            ReqId = reqId;
        }

        public String getTokenId() {
            return TokenId;
        }

        public void setTokenId(String tokenId) {
            TokenId = tokenId;
        }

        public String getSourceUniqueRefNo() {
            return SourceUniqueRefNo;
        }

        public void setSourceUniqueRefNo(String sourceUniqueRefNo) {
            SourceUniqueRefNo = sourceUniqueRefNo;
        }

        public String getSourceIdentifier1() {
            return SourceIdentifier1;
        }

        public void setSourceIdentifier1(String sourceIdentifier1) {
            SourceIdentifier1 = sourceIdentifier1;
        }

        public String getSourceIdentifier2() {
            return SourceIdentifier2;
        }

        public void setSourceIdentifier2(String sourceIdentifier2) {
            SourceIdentifier2 = sourceIdentifier2;
        }

        public String getSourceIdentifier3() {
            return SourceIdentifier3;
        }

        public void setSourceIdentifier3(String sourceIdentifier3) {
            SourceIdentifier3 = sourceIdentifier3;
        }

        public String getSourceIdentifier4() {
            return SourceIdentifier4;
        }

        public void setSourceIdentifier4(String sourceIdentifier4) {
            SourceIdentifier4 = sourceIdentifier4;
        }

        public String getSourceIdentifier5() {
            return SourceIdentifier5;
        }

        public void setSourceIdentifier5(String sourceIdentifier5) {
            SourceIdentifier5 = sourceIdentifier5;
        }

        public String getSourceIdentifier6() {
            return SourceIdentifier6;
        }

        public void setSourceIdentifier6(String sourceIdentifier6) {
            SourceIdentifier6 = sourceIdentifier6;
        }

        public String getSourceIdentifier7() {
            return SourceIdentifier7;
        }

        public void setSourceIdentifier7(String sourceIdentifier7) {
            SourceIdentifier7 = sourceIdentifier7;
        }

        public String getSourceIdentifier8() {
            return SourceIdentifier8;
        }

        public void setSourceIdentifier8(String sourceIdentifier8) {
            SourceIdentifier8 = sourceIdentifier8;
        }

        public String getSourceIdentifier9() {
            return SourceIdentifier9;
        }

        public void setSourceIdentifier9(String sourceIdentifier9) {
            SourceIdentifier9 = sourceIdentifier9;
        }

        public String getSourceIdentifier10() {
            return SourceIdentifier10;
        }

        public void setSourceIdentifier10(String sourceIdentifier10) {
            SourceIdentifier10 = sourceIdentifier10;
        }

        public String getUserConsentFlag() {
            return UserConsentFlag;
        }

        public void setUserConsentFlag(String userConsentFlag) {
            UserConsentFlag = userConsentFlag;
        }

        public String getSourceDescription() {
            return SourceDescription;
        }

        public void setSourceDescription(String sourceDescription) {
            SourceDescription = sourceDescription;
        }
    }

}
