package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;

public class CibilResponseDTO {

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
        private String ApplicationId;
        @Expose
        private String SolutionSetInstanceId;
        @Expose
        private String DateofTUHit;
        @Expose
        private String IsError;
        @Expose
        private String ErrorMsg;
        @Expose
        private String Score;
        @Expose
        private String DisbursementAmount;
        @Expose
        private String CurrentBalance;
        @Expose
        private String Overdue;
        @Expose
        private String DefaultAccount;
        @Expose
        private String TotalInstallmentAmount;
        @Expose
        private String AnyDefaultInPaymentHistory;
        @Expose
        private String Decision;
        @Expose
        private String Reason;
        @Expose
        private String Placeholder1;
        @Expose
        private String Placeholder2;
        @Expose
        private String Placeholder3;
        @Expose
        private CibilResponse CibilResponse;

        public String getApplicationId() {
            return ApplicationId;
        }

        public void setApplicationId(String applicationId) {
            ApplicationId = applicationId;
        }

        public String getSolutionSetInstanceId() {
            return SolutionSetInstanceId;
        }

        public void setSolutionSetInstanceId(String solutionSetInstanceId) {
            SolutionSetInstanceId = solutionSetInstanceId;
        }

        public String getDateofTUHit() {
            return DateofTUHit;
        }

        public void setDateofTUHit(String dateofTUHit) {
            DateofTUHit = dateofTUHit;
        }

        public String getIsError() {
            return IsError;
        }

        public void setIsError(String isError) {
            IsError = isError;
        }

        public String getErrorMsg() {
            return ErrorMsg;
        }

        public void setErrorMsg(String errorMsg) {
            ErrorMsg = errorMsg;
        }

        public String getScore() {
            return Score;
        }

        public void setScore(String score) {
            Score = score;
        }

        public String getDisbursementAmount() {
            return DisbursementAmount;
        }

        public void setDisbursementAmount(String disbursementAmount) {
            DisbursementAmount = disbursementAmount;
        }

        public String getCurrentBalance() {
            return CurrentBalance;
        }

        public void setCurrentBalance(String currentBalance) {
            CurrentBalance = currentBalance;
        }

        public String getOverdue() {
            return Overdue;
        }

        public void setOverdue(String overdue) {
            Overdue = overdue;
        }

        public String getDefaultAccount() {
            return DefaultAccount;
        }

        public void setDefaultAccount(String defaultAccount) {
            DefaultAccount = defaultAccount;
        }

        public String getTotalInstallmentAmount() {
            return TotalInstallmentAmount;
        }

        public void setTotalInstallmentAmount(String totalInstallmentAmount) {
            TotalInstallmentAmount = totalInstallmentAmount;
        }

        public String getAnyDefaultInPaymentHistory() {
            return AnyDefaultInPaymentHistory;
        }

        public void setAnyDefaultInPaymentHistory(String anyDefaultInPaymentHistory) {
            AnyDefaultInPaymentHistory = anyDefaultInPaymentHistory;
        }

        public String getDecision() {
            return Decision;
        }

        public void setDecision(String decision) {
            Decision = decision;
        }

        public String getReason() {
            return Reason;
        }

        public void setReason(String reason) {
            Reason = reason;
        }

        public String getPlaceholder1() {
            return Placeholder1;
        }

        public void setPlaceholder1(String placeholder1) {
            Placeholder1 = placeholder1;
        }

        public String getPlaceholder2() {
            return Placeholder2;
        }

        public void setPlaceholder2(String placeholder2) {
            Placeholder2 = placeholder2;
        }

        public String getPlaceholder3() {
            return Placeholder3;
        }

        public void setPlaceholder3(String placeholder3) {
            Placeholder3 = placeholder3;
        }

        public CibilResponseDTO.CibilResponse getCibilResponse() {
            return CibilResponse;
        }

        public void setCibilResponse(CibilResponseDTO.CibilResponse cibilResponse) {
            CibilResponse = cibilResponse;
        }
    }

    public class CibilResponse {
        @Expose
        private APPLICANTSEGMENT APPLICANTSEGMENT;

        public CibilResponseDTO.APPLICANTSEGMENT getAPPLICANTSEGMENT() {
            return APPLICANTSEGMENT;
        }

        public void setAPPLICANTSEGMENT(CibilResponseDTO.APPLICANTSEGMENT APPLICANTSEGMENT) {
            this.APPLICANTSEGMENT = APPLICANTSEGMENT;
        }
    }

    public class APPLICANTSEGMENT {
        @Expose
        private String NAMESEGMENT;
        @Expose
        private String CONSUMERNAME1;
        @Expose
        private String CONSUMERNAME2;
        @Expose
        private String CONSUMERNAME3;
        @Expose
        private String CONSUMERNAME4;
        @Expose
        private String CONSUMERNAME5;
        @Expose
        private String DATEOFBIRTH;
        @Expose
        private String GENDER;

        public String getNAMESEGMENT() {
            return NAMESEGMENT;
        }

        public void setNAMESEGMENT(String NAMESEGMENT) {
            this.NAMESEGMENT = NAMESEGMENT;
        }

        public String getCONSUMERNAME1() {
            return CONSUMERNAME1;
        }

        public void setCONSUMERNAME1(String CONSUMERNAME1) {
            this.CONSUMERNAME1 = CONSUMERNAME1;
        }

        public String getCONSUMERNAME2() {
            return CONSUMERNAME2;
        }

        public void setCONSUMERNAME2(String CONSUMERNAME2) {
            this.CONSUMERNAME2 = CONSUMERNAME2;
        }

        public String getCONSUMERNAME3() {
            return CONSUMERNAME3;
        }

        public void setCONSUMERNAME3(String CONSUMERNAME3) {
            this.CONSUMERNAME3 = CONSUMERNAME3;
        }

        public String getCONSUMERNAME4() {
            return CONSUMERNAME4;
        }

        public void setCONSUMERNAME4(String CONSUMERNAME4) {
            this.CONSUMERNAME4 = CONSUMERNAME4;
        }

        public String getCONSUMERNAME5() {
            return CONSUMERNAME5;
        }

        public void setCONSUMERNAME5(String CONSUMERNAME5) {
            this.CONSUMERNAME5 = CONSUMERNAME5;
        }

        public String getDATEOFBIRTH() {
            return DATEOFBIRTH;
        }

        public void setDATEOFBIRTH(String DATEOFBIRTH) {
            this.DATEOFBIRTH = DATEOFBIRTH;
        }

        public String getGENDER() {
            return GENDER;
        }

        public void setGENDER(String GENDER) {
            this.GENDER = GENDER;
        }
    }

}
