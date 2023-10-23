package com.saartak.el.models.StudentGrade;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.saartak.el.models.ScreenEditValidation.ScreenEditValidationRequestDTO;

import java.util.List;

public class StudentGradeRequestDTO {

    @Expose
    @SerializedName("SpNameWithParameter")
    private List<SpNameWithParameter> SpNameWithParameter;
    @Expose
    @SerializedName("ProjectName")
    private String ProjectName;
    @Expose
    @SerializedName("IMEINumber")
    private String IMEINumber;
    @Expose
    @SerializedName("UserId")
    private String UserId;
    @Expose
    @SerializedName("ConnectionString")
    private String ConnectionString="audit";

    public void setSpNameWithParameter(List<SpNameWithParameter> spNameWithParameter) {
        SpNameWithParameter = spNameWithParameter;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public void setIMEINumber(String IMEINumber) {
        this.IMEINumber = IMEINumber;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public void setConnectionString(String connectionString) {
        ConnectionString = connectionString;
    }

    public static class SpNameWithParameter {
        @Expose
        @SerializedName("SpParameters")
        private SpNameWithParameter.SpParameters SpParameters;
        @Expose
        @SerializedName("SpName")
        private String SpName;

        public void setSpParameters(SpNameWithParameter.SpParameters spParameters) {
            SpParameters = spParameters;
        }

        public void setSpName(String spName) {
            SpName = spName;
        }

        public static class SpParameters {
            @Expose
            @SerializedName("Qualification")
            private String qualification;

            public String getQualification() {
                return qualification;
            }

            public void setQualification(String qualification) {
                this.qualification = qualification;
            }
        }
    }

}
