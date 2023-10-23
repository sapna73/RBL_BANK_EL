package com.saartak.el.models.Posidex;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity
public  class PosidexResponseDTO {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("ID")
    @Expose(serialize = false, deserialize = false)
    private int id;

    private String clientId;
    @Expose
    @SerializedName("ApiResponse")
    private List<ApiResponse> ApiResponse;
    @Expose
    @SerializedName("ErrorMessage")
    private String ErrorMessage;
    @Expose
    @SerializedName("ErrorCode")
    private String ErrorCode;
    @Expose
    @SerializedName("ResponseMessage")
    private String ResponseMessage;
    @Expose
    @SerializedName("ResponseCode")
    private String ResponseCode;
    @Expose
    @SerializedName("UniqueId")
    private String UniqueId;

    @Expose
    @SerializedName("ModuleType")
    private String moduleType;

    public int getId() {
        return id;
    }

    public String getClientId() {
        return clientId;
    }

    public List<ApiResponse> getApiResponse() {
        return ApiResponse;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public String getErrorCode() {
        return ErrorCode;
    }

    public String getResponseMessage() {
        return ResponseMessage;
    }

    public String getResponseCode() {
        return ResponseCode;
    }

    public String getUniqueId() {
        return UniqueId;
    }

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public static class ApiResponse {
        @Expose
        @SerializedName("MatchReason")
        private String MatchReason;
        @Expose
        @SerializedName("MatchBand")
        private String MatchBand;
        @Expose
        @SerializedName("message")
        private String message;
        @Expose
        @SerializedName("matchCount")
        private String matchCount;
        @Expose
        @SerializedName("status")
        private String status;
        @Expose
        @SerializedName("UCICtype")
        private String UCICtype;
        @Expose
        @SerializedName("UCICID")
        private String UCICID;


        public String getMatchReason() {
            return MatchReason;
        }

        public String getMatchBand() {
            return MatchBand;
        }

        public String getMessage() {
            return message;
        }

        public String getMatchCount() {
            return matchCount;
        }

        public String getStatus() {
            return status;
        }

        public String getUCICtype() {
            return UCICtype;
        }

        public String getUCICID() {
            return UCICID;
        }
    }


}
