package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class EligibilityRequestDTO {

   /* {
    "ConnectionString": "jlg",
    "IMEINumber": "866265031657572",
    "ProjectName": "JLG",
    "SpNameWithParameter": [
        {
            "SpName": "usp_GetDetailsByCMDateAndLO",
            "SpParameters": {
                "CMDate": "20200519",
                "LOID": "SIF1000059"
            }
        }
    ]
}*/

    @Expose
   private String ConnectionString = "jlg";
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
//         "SpParameters": {
//            "CMDate": "20200519",
//                    "LOID": "SIF1000059"
//        }           }

        @Expose
        private String CMDate = "";
        @Expose
        private String LOID = "";

        public String getCMDate() {
            return CMDate;
        }

        public void setCMDate(String CMDate) {
            this.CMDate = CMDate;
        }

        public String getLOID() {
            return LOID;
        }

        public void setLOID(String LOID) {
            this.LOID = LOID;
        }
    }
}
