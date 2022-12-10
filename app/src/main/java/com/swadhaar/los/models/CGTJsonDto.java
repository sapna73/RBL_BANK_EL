package com.swadhaar.los.models;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.swadhaar.los.database.entity.CenterCreationTable;
import com.swadhaar.los.database.entity.GroupTable;
import com.swadhaar.los.database.entity.HouseVerificationTable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class CGTJsonDto {

    @SerializedName("CENTRAL DETAIL")
    @Expose
    private CenterCreationTable centerCreationTable;

    @SerializedName("HOUSE DETAIL")
    @Expose
    private List<HouseVerificationTable> houseVerificationTableList;

    @SerializedName("MEMBER DETAIL")
    @Expose
    private List<MemberDetail> memberDetailList;

    public CenterCreationTable getCenterCreationTable() {
        return centerCreationTable;
    }

    public void setCenterCreationTable(CenterCreationTable centerCreationTable) {
        this.centerCreationTable = centerCreationTable;
    }

    public List<HouseVerificationTable> getHouseVerificationTableList() {
        return houseVerificationTableList;
    }

    public void setHouseVerificationTableList(List<HouseVerificationTable> houseVerificationTableList) {
        this.houseVerificationTableList = houseVerificationTableList;
    }

    public List<MemberDetail> getMemberDetailList() {
        return memberDetailList;
    }

    public void setMemberDetailList(List<MemberDetail> memberDetailList) {
        this.memberDetailList = memberDetailList;
    }

    public static class MemberDetail{
        @SerializedName("ClientId")
        @Expose
        private String clientId;
        @SerializedName("ClientName")
        @Expose
        private String clientName;
        @SerializedName("ScreenId")
        @Expose
        private String screenId;
        @SerializedName("ScreenName")
        @Expose
        private String screenName;
        @SerializedName("CenterId")
        @Expose
        private String centerId;
        @SerializedName("ScreenRawData")
        @Expose
        private List<String> rawDataList;

        public List<String> getRawDataList() {
            return rawDataList;
        }

        public void setRawDataList(List<String> rawDataList) {
            this.rawDataList = rawDataList;
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

        public String getScreenId() {
            return screenId;
        }

        public void setScreenId(String screenId) {
            this.screenId = screenId;
        }

        public String getScreenName() {
            return screenName;
        }

        public void setScreenName(String screenName) {
            this.screenName = screenName;
        }

        public String getCenterId() {
            return centerId;
        }

        public void setCenterId(String centerId) {
            this.centerId = centerId;
        }

    }

}
