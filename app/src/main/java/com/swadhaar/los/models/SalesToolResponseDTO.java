package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;

public class SalesToolResponseDTO {

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
        private CalculationResponseClass calculationResponse;

        public CalculationResponseClass getCalculationResponse() {
            return calculationResponse;
        }

        public void setCalculationResponse(CalculationResponseClass calculationResponse) {
            this.calculationResponse = calculationResponse;
        }
    }

    public static class CalculationResponseClass{
        @Expose
        private String Score;
        @Expose
        private String CustomerProfile;
        @Expose
        private String TotalExpense;
        @Expose
        private String NetBusinessIncome;
        @Expose
        private String TotalMonthlySurplus;
        @Expose
        private String DebtServiceRatio;
        @Expose
        private String EMIEligibilityAsperNetBusinessSurplus;
        @Expose
        private String FinalEMIEligibility;
        @Expose
        private String AmountBasisTheFinalEMIEligibility;
        @Expose
        private String FinalAmount;

        public String getScore() {
            return Score;
        }

        public void setScore(String score) {
            Score = score;
        }

        public String getCustomerProfile() {
            return CustomerProfile;
        }

        public void setCustomerProfile(String customerProfile) {
            CustomerProfile = customerProfile;
        }

        public String getTotalExpense() {
            return TotalExpense;
        }

        public void setTotalExpense(String totalExpense) {
            TotalExpense = totalExpense;
        }

        public String getNetBusinessIncome() {
            return NetBusinessIncome;
        }

        public void setNetBusinessIncome(String netBusinessIncome) {
            NetBusinessIncome = netBusinessIncome;
        }

        public String getTotalMonthlySurplus() {
            return TotalMonthlySurplus;
        }

        public void setTotalMonthlySurplus(String totalMonthlySurplus) {
            TotalMonthlySurplus = totalMonthlySurplus;
        }

        public String getDebtServiceRatio() {
            return DebtServiceRatio;
        }

        public void setDebtServiceRatio(String debtServiceRatio) {
            DebtServiceRatio = debtServiceRatio;
        }

        public String getEMIEligibilityAsperNetBusinessSurplus() {
            return EMIEligibilityAsperNetBusinessSurplus;
        }

        public void setEMIEligibilityAsperNetBusinessSurplus(String EMIEligibilityAsperNetBusinessSurplus) {
            this.EMIEligibilityAsperNetBusinessSurplus = EMIEligibilityAsperNetBusinessSurplus;
        }

        public String getFinalEMIEligibility() {
            return FinalEMIEligibility;
        }

        public void setFinalEMIEligibility(String finalEMIEligibility) {
            FinalEMIEligibility = finalEMIEligibility;
        }

        public String getAmountBasisTheFinalEMIEligibility() {
            return AmountBasisTheFinalEMIEligibility;
        }

        public void setAmountBasisTheFinalEMIEligibility(String amountBasisTheFinalEMIEligibility) {
            AmountBasisTheFinalEMIEligibility = amountBasisTheFinalEMIEligibility;
        }

        public String getFinalAmount() {
            return FinalAmount;
        }

        public void setFinalAmount(String finalAmount) {
            FinalAmount = finalAmount;
        }
    }

}
