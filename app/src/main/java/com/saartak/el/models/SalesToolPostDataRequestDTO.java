package com.saartak.el.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SalesToolPostDataRequestDTO {

    @Expose
    private String ConnectionString = "audit";
    @Expose
    private String UserId = "";
    @Expose
    private String IMEINumber = "";
    @Expose
    private String ProjectName = "";
    @Expose
    ArrayList<SpNameWithParameterClass> SpNameWithParameter = new ArrayList<SpNameWithParameterClass>();

    public ArrayList<SpNameWithParameterClass> getSpNameWithParameter() {
        return SpNameWithParameter;
    }

    public void setSpNameWithParameter(ArrayList<SpNameWithParameterClass> spNameWithParameter) {
        SpNameWithParameter = spNameWithParameter;
    }

    // Getter Methods

    public String getConnectionString() {
        return ConnectionString;
    }

    public String getUserId() {
        return UserId;
    }

    public String getIMEINumber() {
        return IMEINumber;
    }

    public String getProjectName() {
        return ProjectName;
    }

    // Setter Methods

    public void setConnectionString(String ConnectionString) {
        this.ConnectionString = ConnectionString;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public void setIMEINumber(String IMEINumber) {
        this.IMEINumber = IMEINumber;
    }

    public void setProjectName(String ProjectName) {
        this.ProjectName = ProjectName;
    }

    public static class SpNameWithParameterClass {
        @Expose
        private String SpName = "";
        @Expose
        private SpParametersClass SpParameters;

        public String getSpName() {
            return SpName;
        }

        public void setSpName(String spName) {
            SpName = spName;
        }

        public SpParametersClass getSpParameters() {
            return SpParameters;
        }

        public void setSpParameters(SpParametersClass spParameters) {
            SpParameters = spParameters;
        }
    }

    public static class SpParametersClass {
        @SerializedName("ClientId")
        @Expose
        private String clientId;

        @SerializedName("TargetName")
        @Expose
        private String clientName;

        @SerializedName("AgeRange")
        @Expose
        private String ageRange;

        @SerializedName("CityOfResidence")
        @Expose
        private String cityOfResidence;

        @SerializedName("ResidenceType")
        @Expose
        private String residentType;

        @SerializedName("ResidentStability")
        @Expose
        private String residentStability;

        @SerializedName("FirmName")
        @Expose
        private String firmName;

        @SerializedName("FirmType")
        @Expose
        private String firmType;

        @SerializedName("BusinessType")
        @Expose
        private String businessType;

        @SerializedName("BusinesssPlace")
        @Expose
        private String businessPlace;

        @SerializedName("YearsInBusiness")
        @Expose
        private String yearsInBusiness;

        @SerializedName("OwnAPlot")
        @Expose
        private String ownAPlot;

        @SerializedName("IncomeProofAvailable")
        @Expose
        private String incomeProofAvailable;

        @SerializedName("ITR_VAT_BankStatement_No_Of_Years")
        @Expose
        private String itrNoOfYears;

        @SerializedName("MonthlyBusinessTurnOverRange")
        @Expose
        private String monthlyBusinessTurnOverrange;

        @SerializedName("AnyExistingLoanRunning")
        @Expose
        private String anyExistingLoanRunning;

        @SerializedName("TypeOfLoan")
        @Expose
        private String typeOfLoan;

        @SerializedName("LookingFor")
        @Expose
        private String lookingFor;

        @SerializedName("TypeOfSecurity")
        @Expose
        private String typeOfSecurity;

        @SerializedName("SecurityOwnership")
        @Expose
        private String securityOwnership;


        @SerializedName("RequestedAmount")
        @Expose
        private String requestedAmount;

        @SerializedName("ComfortableEmi")
        @Expose
        private String comfortableEmi;

        @SerializedName("Tenure")
        @Expose
        private String tenure;

        // TODO: 31-08-2020 added below fields

        @SerializedName("TotalSales")
        @Expose
        private String totalSales;

        @SerializedName("TotalPurchase")
        @Expose
        private String totalPurchase;

        @SerializedName("RentOfBusinessPremise")
        @Expose
        private String rentOfBusinessPremise;

        @SerializedName("Labour_ElectricityCost")
        @Expose
        private String labour_ElectricityCost;

        @SerializedName("TotalExpense")
        @Expose
        private String totalExpense;

        @SerializedName("NetBusinessIncome")
        @Expose
        private String netBusinessIncome;

        @SerializedName("IncomeFromOtherSource")
        @Expose
        private String incomeFromOthersource;

        @SerializedName("MonthlyHouseholdExpenses")
        @Expose
        private String monthlyHouseholdExpenses;

        @SerializedName("MonthlyEMI")
        @Expose
        private String monthlyEMI;

        @SerializedName("TotalMonthlySurplus")
        @Expose
        private String totalMonthlySurplus;

        @SerializedName("DebtServiceRatio")
        @Expose
        private String debtServiceRatio;

        @SerializedName("EMI_NBS")
        @Expose
        private String emi_NBS;
        @SerializedName("FinalEmiEligibility")
        @Expose
        private String finalEMI_Eligibility;

        @SerializedName("ApplicationAmount")
        @Expose
        private String applicationAmount;

        @SerializedName("AmountBasis_FinalEmiEligibility")
        @Expose
        private String amountbasis_FinalEmiEligibility;

        @SerializedName("FinalAmount")
        @Expose
        private String finalAmount;

        @SerializedName("StaffId")
        @Expose
        private String createdBy;

        @SerializedName("BranchId")
        @Expose
        private String branchId;

        @SerializedName("BranchGstCode")
        @Expose
        private String branchGSTCode;

        @SerializedName("Score")
        @Expose
        private String score;

        public String getAgeRange() {
            return ageRange;
        }

        public void setAgeRange(String ageRange) {
            this.ageRange = ageRange;
        }

        public String getCityOfResidence() {
            return cityOfResidence;
        }

        public void setCityOfResidence(String cityOfResidence) {
            this.cityOfResidence = cityOfResidence;
        }

        public String getResidentType() {
            return residentType;
        }

        public void setResidentType(String residentType) {
            this.residentType = residentType;
        }

        public String getResidentStability() {
            return residentStability;
        }

        public void setResidentStability(String residentStability) {
            this.residentStability = residentStability;
        }

        public String getFirmName() {
            return firmName;
        }

        public void setFirmName(String firmName) {
            this.firmName = firmName;
        }

        public String getFirmType() {
            return firmType;
        }

        public void setFirmType(String firmType) {
            this.firmType = firmType;
        }

        public String getBusinessType() {
            return businessType;
        }

        public String getTenure() {
            return tenure;
        }

        public void setTenure(String tenure) {
            this.tenure = tenure;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public void setBusinessType(String businessType) {
            this.businessType = businessType;
        }

        public String getBusinessPlace() {
            return businessPlace;
        }

        public void setBusinessPlace(String businessPlace) {
            this.businessPlace = businessPlace;
        }

        public String getYearsInBusiness() {
            return yearsInBusiness;
        }

        public void setYearsInBusiness(String yearsInBusiness) {
            this.yearsInBusiness = yearsInBusiness;
        }
        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public String getClientName() {
            return clientName;
        }

        public void setClientName(String clientName) {
            this.clientName = clientName;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getOwnAPlot() {
            return ownAPlot;
        }

        public void setOwnAPlot(String ownAPlot) {
            this.ownAPlot = ownAPlot;
        }

        public String getIncomeProofAvailable() {
            return incomeProofAvailable;
        }

        public void setIncomeProofAvailable(String incomeProofAvailable) {
            this.incomeProofAvailable = incomeProofAvailable;
        }

        public String getMonthlyBusinessTurnOverrange() {
            return monthlyBusinessTurnOverrange;
        }

        public void setMonthlyBusinessTurnOverrange(String monthlyBusinessTurnOverrange) {
            this.monthlyBusinessTurnOverrange = monthlyBusinessTurnOverrange;
        }

        public String getAnyExistingLoanRunning() {
            return anyExistingLoanRunning;
        }

        public void setAnyExistingLoanRunning(String anyExistingLoanRunning) {
            this.anyExistingLoanRunning = anyExistingLoanRunning;
        }

        public String getTypeOfLoan() {
            return typeOfLoan;
        }

        public void setTypeOfLoan(String typeOfLoan) {
            this.typeOfLoan = typeOfLoan;
        }

        public String getLookingFor() {
            return lookingFor;
        }

        public void setLookingFor(String lookingFor) {
            this.lookingFor = lookingFor;
        }

        public String getTypeOfSecurity() {
            return typeOfSecurity;
        }

        public void setTypeOfSecurity(String typeOfSecurity) {
            this.typeOfSecurity = typeOfSecurity;
        }

        public String getRequestedAmount() {
            return requestedAmount;
        }

        public void setRequestedAmount(String requestedAmount) {
            this.requestedAmount = requestedAmount;
        }

        public String getComfortableEmi() {
            return comfortableEmi;
        }

        public void setComfortableEmi(String comfortableEmi) {
            this.comfortableEmi = comfortableEmi;
        }

        public String getBranchId() {
            return branchId;
        }

        public void setBranchId(String branchId) {
            this.branchId = branchId;
        }

        public String getBranchGSTCode() {
            return branchGSTCode;
        }

        public void setBranchGSTCode(String branchGSTCode) {
            this.branchGSTCode = branchGSTCode;
        }

        public String getTotalSales() {
            return totalSales;
        }

        public void setTotalSales(String totalSales) {
            this.totalSales = totalSales;
        }

        public String getTotalPurchase() {
            return totalPurchase;
        }

        public void setTotalPurchase(String totalPurchase) {
            this.totalPurchase = totalPurchase;
        }

        public String getRentOfBusinessPremise() {
            return rentOfBusinessPremise;
        }

        public void setRentOfBusinessPremise(String rentOfBusinessPremise) {
            this.rentOfBusinessPremise = rentOfBusinessPremise;
        }

        public String getLabour_ElectricityCost() {
            return labour_ElectricityCost;
        }

        public void setLabour_ElectricityCost(String labour_ElectricityCost) {
            this.labour_ElectricityCost = labour_ElectricityCost;
        }

        public String getTotalExpense() {
            return totalExpense;
        }

        public void setTotalExpense(String totalExpense) {
            this.totalExpense = totalExpense;
        }

        public String getNetBusinessIncome() {
            return netBusinessIncome;
        }

        public void setNetBusinessIncome(String netBusinessIncome) {
            this.netBusinessIncome = netBusinessIncome;
        }

        public String getIncomeFromOthersource() {
            return incomeFromOthersource;
        }

        public void setIncomeFromOthersource(String incomeFromOthersource) {
            this.incomeFromOthersource = incomeFromOthersource;
        }

        public String getMonthlyHouseholdExpenses() {
            return monthlyHouseholdExpenses;
        }

        public void setMonthlyHouseholdExpenses(String monthlyHouseholdExpenses) {
            this.monthlyHouseholdExpenses = monthlyHouseholdExpenses;
        }

        public String getMonthlyEMI() {
            return monthlyEMI;
        }

        public void setMonthlyEMI(String monthlyEMI) {
            this.monthlyEMI = monthlyEMI;
        }

        public String getTotalMonthlySurplus() {
            return totalMonthlySurplus;
        }

        public void setTotalMonthlySurplus(String totalMonthlySurplus) {
            this.totalMonthlySurplus = totalMonthlySurplus;
        }

        public String getDebtServiceRatio() {
            return debtServiceRatio;
        }

        public void setDebtServiceRatio(String debtServiceRatio) {
            this.debtServiceRatio = debtServiceRatio;
        }

        public String getEmi_NBS() {
            return emi_NBS;
        }

        public void setEmi_NBS(String emi_NBS) {
            this.emi_NBS = emi_NBS;
        }

        public String getItrNoOfYears() {
            return itrNoOfYears;
        }

        public void setItrNoOfYears(String itrNoOfYears) {
            this.itrNoOfYears = itrNoOfYears;
        }

        public String getSecurityOwnership() {
            return securityOwnership;
        }

        public void setSecurityOwnership(String securityOwnership) {
            this.securityOwnership = securityOwnership;
        }

        public String getFinalEMI_Eligibility() {
            return finalEMI_Eligibility;
        }

        public void setFinalEMI_Eligibility(String finalEMI_Eligibility) {
            this.finalEMI_Eligibility = finalEMI_Eligibility;
        }

        public String getApplicationAmount() {
            return applicationAmount;
        }

        public void setApplicationAmount(String applicationAmount) {
            this.applicationAmount = applicationAmount;
        }

        public String getAmountbasis_FinalEmiEligibility() {
            return amountbasis_FinalEmiEligibility;
        }

        public void setAmountbasis_FinalEmiEligibility(String amountbasis_FinalEmiEligibility) {
            this.amountbasis_FinalEmiEligibility = amountbasis_FinalEmiEligibility;
        }

        public String getFinalAmount() {
            return finalAmount;
        }

        public void setFinalAmount(String finalAmount) {
            this.finalAmount = finalAmount;
        }
    }
}
