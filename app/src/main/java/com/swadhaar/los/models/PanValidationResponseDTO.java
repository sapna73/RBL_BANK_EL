package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;

import java.util.List;

public class PanValidationResponseDTO {

    @Expose
    private String ResponseCode;
    @Expose
    private String ResponseMessage;
    @Expose
    private String ErrorCode;
    @Expose
    private String ErrorMessage;
    @Expose
    private ApiResponseClass ApiResponse;

    public String getResponseCode() {
        return ResponseCode;
    }

    public void setResponseCode(String responseCode) {
        ResponseCode = responseCode;
    }

    public String getResponseMessage() {
        return ResponseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        ResponseMessage = responseMessage;
    }

    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String errorCode) {
        ErrorCode = errorCode;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    public ApiResponseClass getApiResponse() {
        return ApiResponse;
    }

    public void setApiResponse(ApiResponseClass apiResponse) {
        ApiResponse = apiResponse;
    }

    public class ApiResponseClass {
        @Expose
        private String TranID;
        @Expose
        private String Status;
        @Expose
        private String ErrorCode;
        @Expose
        private String ErrorMessage;
        @Expose
        private List<PanDetailsClass> panDetails;

        public String getTranID() {
            return TranID;
        }

        public void setTranID(String tranID) {
            TranID = tranID;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String status) {
            Status = status;
        }

        public String getErrorCode() {
            return ErrorCode;
        }

        public void setErrorCode(String errorCode) {
            ErrorCode = errorCode;
        }

        public String getErrorMessage() {
            return ErrorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            ErrorMessage = errorMessage;
        }

        public List<PanDetailsClass> getPanDetails() {
            return panDetails;
        }

        public void setPanDetails(List<PanDetailsClass> panDetails) {
            this.panDetails = panDetails;
        }
    }

    public class PanDetailsClass {
        @Expose
        private String pan;
        @Expose
        private String panstatus;
        @Expose
        private String firstname;
        @Expose
        private String lastname;
        @Expose
        private String middlename;
        @Expose
        private String pantitle;
        @Expose
        private String lastupdatedate;
        @Expose
        private String filler1;
        @Expose
        private String filler2;
        @Expose
        private String filler3;

        public String getPan() {
            return pan;
        }

        public void setPan(String pan) {
            this.pan = pan;
        }

        public String getPanstatus() {
            return panstatus;
        }

        public void setPanstatus(String panstatus) {
            this.panstatus = panstatus;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getMiddlename() {
            return middlename;
        }

        public void setMiddlename(String middlename) {
            this.middlename = middlename;
        }

        public String getPantitle() {
            return pantitle;
        }

        public void setPantitle(String pantitle) {
            this.pantitle = pantitle;
        }

        public String getLastupdatedate() {
            return lastupdatedate;
        }

        public void setLastupdatedate(String lastupdatedate) {
            this.lastupdatedate = lastupdatedate;
        }

        public String getFiller1() {
            return filler1;
        }

        public void setFiller1(String filler1) {
            this.filler1 = filler1;
        }

        public String getFiller2() {
            return filler2;
        }

        public void setFiller2(String filler2) {
            this.filler2 = filler2;
        }

        public String getFiller3() {
            return filler3;
        }

        public void setFiller3(String filler3) {
            this.filler3 = filler3;
        }
    }
}
