package com.saartak.el.models.KarzaModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KarzaResponseDTO {

    @SerializedName("UniqueId")
    @Expose
    private String uniqueId;


    @SerializedName("ResponseCode")
    @Expose
    private String responseCode;


    @SerializedName("ResponseMessage")
    @Expose
    private String responseMessage;

    @SerializedName("ErrorCode")
    @Expose
    private String errorCode;

    @SerializedName("ErrorMessage")
    @Expose
    private String errorMessage;

    @SerializedName("ApiResponse")
    @Expose
    private ApiResponse ApiResponse;


    public static class ApiResponse {

        @SerializedName("seatingCapacity")
        @Expose
        private String seatingCapacity;

        @SerializedName("taxPaidUpto")
        @Expose
        private String taxPaidUpto;

        @SerializedName("vehicleClassDescription")
        @Expose
        private String vehicleClassDescription;

        @SerializedName("grossVehicleWeight")
        @Expose
        private String loanAccountNo;

        @SerializedName("permanentAddress")
        @Expose
        private String permanentAddress;

        @SerializedName("ownerSerialNumber")
        @Expose
        private String ownerSerialNumber;

        @SerializedName("registrationDate")
        @Expose
        private String registrationDate;

        @SerializedName("fatherName")
        @Expose
        private String fatherName;

        @SerializedName("financier")
        @Expose
        private String financier;

        @SerializedName("registrationNumber")
        @Expose
        private String registrationNumber;

        @SerializedName("chassisNumber")
        @Expose
        private String chassisNumber;

        @SerializedName("numberOfCylinders")
        @Expose
        private String numberOfCylinders;

        @SerializedName("bodyTypeDescription")
        @Expose
        private String bodyTypeDescription;

        @SerializedName("makerDescription")
        @Expose
        private String makerDescription;

        @SerializedName("fuelDescription")
        @Expose
        private String fuelDescription;

        @SerializedName("makerModel")
        @Expose
        private String makerModel;

        @SerializedName("color")
        @Expose
        private String color;

        @SerializedName("ownerName")
        @Expose
        private String ownerName;

        @SerializedName("normsDescription")
        @Expose
        private String normsDescription;

        @SerializedName("insuranceUpto")
        @Expose
        private String insuranceUpto;

        @SerializedName("engineNumber")
        @Expose
        private String engineNumber;

        @SerializedName("presentAddress")
        @Expose
        private String presentAddress;

        @SerializedName("insurancePolicyNumber")
        @Expose
        private String insurancePolicyNumber;

        @SerializedName("registeredAt")
        @Expose
        private String registeredAt;

        @SerializedName("fitnessUpto")
        @Expose
        private String fitnessUpto;

        @SerializedName("manufacturedMonthYear")
        @Expose
        private String manufacturedMonthYear;

        @SerializedName("manufacturedMonth")
        @Expose
        private String month;

        @SerializedName("manufacturedYear")
        @Expose
        private String year;

        @SerializedName("insuranceCompany")
        @Expose
        private String insuranceCompany;

        @SerializedName("vehicleCatgory")
        @Expose
        private String vehicleCatgory;

        @SerializedName("rcStatus")
        @Expose
        private String rcStatus;

        @SerializedName("AgeOfVehicleInMonths")
        @Expose
        private String ageOfVehicleInMonths;

        public String getAgeOfVehicleInMonths() {
            return ageOfVehicleInMonths;
        }

        public void setAgeOfVehicleInMonths(String ageOfVehicleInMonths) {
            this.ageOfVehicleInMonths = ageOfVehicleInMonths;
        }

        public String getSeatingCapacity() {
            return seatingCapacity;
        }

        public void setSeatingCapacity(String seatingCapacity) {
            this.seatingCapacity = seatingCapacity;
        }

        public String getTaxPaidUpto() {
            return taxPaidUpto;
        }

        public void setTaxPaidUpto(String taxPaidUpto) {
            this.taxPaidUpto = taxPaidUpto;
        }

        public String getVehicleClassDescription() {
            return vehicleClassDescription;
        }

        public void setVehicleClassDescription(String vehicleClassDescription) {
            this.vehicleClassDescription = vehicleClassDescription;
        }

        public String getLoanAccountNo() {
            return loanAccountNo;
        }

        public void setLoanAccountNo(String loanAccountNo) {
            this.loanAccountNo = loanAccountNo;
        }

        public String getPermanentAddress() {
            return permanentAddress;
        }

        public void setPermanentAddress(String permanentAddress) {
            this.permanentAddress = permanentAddress;
        }

        public String getOwnerSerialNumber() {
            return ownerSerialNumber;
        }

        public void setOwnerSerialNumber(String ownerSerialNumber) {
            this.ownerSerialNumber = ownerSerialNumber;
        }

        public String getRegistrationDate() {
            return registrationDate;
        }

        public void setRegistrationDate(String registrationDate) {
            this.registrationDate = registrationDate;
        }

        public String getFatherName() {
            return fatherName;
        }

        public void setFatherName(String fatherName) {
            this.fatherName = fatherName;
        }

        public String getFinancier() {
            return financier;
        }

        public void setFinancier(String financier) {
            this.financier = financier;
        }

        public String getRegistrationNumber() {
            return registrationNumber;
        }

        public void setRegistrationNumber(String registrationNumber) {
            this.registrationNumber = registrationNumber;
        }

        public String getChassisNumber() {
            return chassisNumber;
        }

        public void setChassisNumber(String chassisNumber) {
            this.chassisNumber = chassisNumber;
        }

        public String getNumberOfCylinders() {
            return numberOfCylinders;
        }

        public void setNumberOfCylinders(String numberOfCylinders) {
            this.numberOfCylinders = numberOfCylinders;
        }

        public String getBodyTypeDescription() {
            return bodyTypeDescription;
        }

        public void setBodyTypeDescription(String bodyTypeDescription) {
            this.bodyTypeDescription = bodyTypeDescription;
        }

        public String getMakerDescription() {
            return makerDescription;
        }

        public void setMakerDescription(String makerDescription) {
            this.makerDescription = makerDescription;
        }

        public String getFuelDescription() {
            return fuelDescription;
        }

        public void setFuelDescription(String fuelDescription) {
            this.fuelDescription = fuelDescription;
        }

        public String getMakerModel() {
            return makerModel;
        }

        public void setMakerModel(String makerModel) {
            this.makerModel = makerModel;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getOwnerName() {
            return ownerName;
        }

        public void setOwnerName(String ownerName) {
            this.ownerName = ownerName;
        }

        public String getNormsDescription() {
            return normsDescription;
        }

        public void setNormsDescription(String normsDescription) {
            this.normsDescription = normsDescription;
        }

        public String getInsuranceUpto() {
            return insuranceUpto;
        }

        public void setInsuranceUpto(String insuranceUpto) {
            this.insuranceUpto = insuranceUpto;
        }

        public String getEngineNumber() {
            return engineNumber;
        }

        public void setEngineNumber(String engineNumber) {
            this.engineNumber = engineNumber;
        }

        public String getPresentAddress() {
            return presentAddress;
        }

        public void setPresentAddress(String presentAddress) {
            this.presentAddress = presentAddress;
        }

        public String getInsurancePolicyNumber() {
            return insurancePolicyNumber;
        }

        public void setInsurancePolicyNumber(String insurancePolicyNumber) {
            this.insurancePolicyNumber = insurancePolicyNumber;
        }

        public String getRegisteredAt() {
            return registeredAt;
        }

        public void setRegisteredAt(String registeredAt) {
            this.registeredAt = registeredAt;
        }

        public String getFitnessUpto() {
            return fitnessUpto;
        }

        public void setFitnessUpto(String fitnessUpto) {
            this.fitnessUpto = fitnessUpto;
        }

        public String getManufacturedMonthYear() {
            return manufacturedMonthYear;
        }

        public void setManufacturedMonthYear(String manufacturedMonthYear) {
            this.manufacturedMonthYear = manufacturedMonthYear;
        }


        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getInsuranceCompany() {
            return insuranceCompany;
        }

        public void setInsuranceCompany(String insuranceCompany) {
            this.insuranceCompany = insuranceCompany;
        }

        public String getVehicleCatgory() {
            return vehicleCatgory;
        }

        public void setVehicleCatgory(String vehicleCatgory) {
            this.vehicleCatgory = vehicleCatgory;
        }

        public String getRcStatus() {
            return rcStatus;
        }

        public void setRcStatus(String rcStatus) {
            this.rcStatus = rcStatus;
        }
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ApiResponse getApiResponse() {
        return ApiResponse;
    }

    public void setApiResponse(ApiResponse apiResponse) {
        ApiResponse = apiResponse;
    }
}
