package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class IFSCRequestDTO {

   /* {"ConnectionString":"jlg","IMEINumber":"358520071961721","ProjectName":"","SpNameWithParameter":[{"SpName":"Usp_GetIFSC","SpParameters": {
        "BankName": "AXIS BANK","IFSC":"SBIN0021807"
    }
    }]}*/

    @Expose
    private String ConnectionString = "jlg";

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
     /* "SpNameWithParameter":[{"SpName":"Usp_GetIFSC","SpParameters": {
            "BankName": "AXIS BANK","IFSC":"SBIN0021807"
*/
        @Expose
        private String BankName = "";
        @Expose
        private String IFSC = "";

        public String getBankName() {
            return BankName;
        }

        public void setBankName(String bankName) {
            BankName = bankName;
        }

        public String getIFSC() {
            return IFSC;
        }

        public void setIFSC(String IFSC) {
            this.IFSC = IFSC;
        }
    }
}
