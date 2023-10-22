package com.saartak.el.models.TypeOfProfession;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TypeOfProfessionRequestDTO {
    @Expose
    private String ConnectionString = "audit";
    //        private String UserId="";
    @Expose
    private String IMEINumber = "";
    @Expose
    private String ProjectName = "EL";
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

//        public String getUserId() {
//            return UserId;
//        }

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

//        public void setUserId( String UserId ) {
//            this.UserId = UserId;
//        }

    public void setIMEINumber(String IMEINumber) {
        this.IMEINumber = IMEINumber;
    }

    public void setProjectName(String ProjectName) {
        this.ProjectName = ProjectName;
    }

    public static class SpNameWithParameterClass {
        @Expose
        private String SpName = "";

        @SerializedName("SpParameters")
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

        @Expose
        private String CustomerType = "";

        @Expose
        private String TypeOfProfession = "";

        @Expose
        private String Type = "";

        @Expose
        private String ISEKYC ;

        @Expose
        private int Screenid ;

        @Expose
        private String IsIndividual = "";

        public String getCustomerType() {
            return CustomerType;
        }

        public void setCustomerType(String customerType) {
            CustomerType = customerType;
        }

        public String getTypeOfProfession() {
            return TypeOfProfession;
        }

        public void setTypeOfProfession(String typeOfProfession) {
            TypeOfProfession = typeOfProfession;
        }

        public String getType() {
            return Type;
        }

        public void setType(String type) {
            Type = type;
        }

        public String getIsIndividual() {
            return IsIndividual;
        }

        public void setIsIndividual(String isIndividual) {
            IsIndividual = isIndividual;
        }

        public String getISEKYC() {
            return ISEKYC;
        }

        public void setISEKYC(String ISEKYC) {
            this.ISEKYC = ISEKYC;
        }

        public int getScreenid() {
            return Screenid;
        }

        public void setScreenid(int screenid) {
            Screenid = screenid;
        }
    }
}
