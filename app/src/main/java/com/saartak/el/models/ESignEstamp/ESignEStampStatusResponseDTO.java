package com.saartak.el.models.ESignEstamp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class ESignEStampStatusResponseDTO {

    @Expose
    @SerializedName("ApiResponse")
    private ApiResponse ApiResponse;
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

    public ApiResponse getApiResponse() {
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

    public static class ApiResponse {
        @Expose
        @SerializedName("Uniquetxnid")
        private String Uniquetxnid;
        @Expose
        @SerializedName("Existingtxnid")
        private String Existingtxnid;
        @Expose
        @SerializedName("Sanctionno")
        private String Sanctionno;
        @Expose
        @SerializedName("Loanno")
        private String Loanno;
        @Expose
        @SerializedName("Channel")
        private String Channel;
        @Expose
        @SerializedName("Responsetime")
        private String Responsetime;
        @Expose
        @SerializedName("DocStatus")
        private DocStatus DocStatus;
        @Expose
        @SerializedName("EstampCertificate")
        private EstampCertificate EstampCertificate;
        @Expose
        @SerializedName("Statusmsg")
        private String Statusmsg;
        @Expose
        @SerializedName("Statuscode")
        private int Statuscode;

        public String getUniquetxnid() {
            return Uniquetxnid;
        }

        public String getExistingtxnid() {
            return Existingtxnid;
        }

        public String getSanctionno() {
            return Sanctionno;
        }

        public String getLoanno() {
            return Loanno;
        }

        public String getChannel() {
            return Channel;
        }

        public String getResponsetime() {
            return Responsetime;
        }

        public ApiResponse.DocStatus getDocStatus() {
            return DocStatus;
        }

        public ApiResponse.EstampCertificate getEstampCertificate() {
            return EstampCertificate;
        }

        public String getStatusmsg() {
            return Statusmsg;
        }

        public int getStatuscode() {
            return Statuscode;
        }

        public static class EstampCertificate {
            @Expose
            @SerializedName("Certificateno")
            private String Certificateno;

            public String getCertificateno() {
                return Certificateno;
            }
        }

        public static class DocStatus {
            @Expose
            @SerializedName("Status")
            private List<Status> Status;

            public List<Status> getStatus() {
                return Status;
            }

            public static class Status {
                @Expose
                @SerializedName("Seqno")
                private int Seqno;
                @Expose
                @SerializedName("Docdata")
                private String Docdata;
                @Expose
                @SerializedName("Estampdate")
                private String Estampdate;
                @Expose
                @SerializedName("Esigndate")
                private String Esigndate;
                @Expose
                @SerializedName("Estampstatus")
                private String Estampstatus;
                @Expose
                @SerializedName("Esignstatus")
                private String Esignstatus;
                @Expose
                @SerializedName("Rltnshp")
                private String Rltnshp;
                @Expose
                @SerializedName("Pan")
                private String Pan;
                @Expose
                @SerializedName("SigntryName")
                private String SigntryName;

                public int getSeqno() {
                    return Seqno;
                }

                public String getDocdata() {
                    return Docdata;
                }

                public String getEstampdate() {
                    return Estampdate;
                }

                public String getEsigndate() {
                    return Esigndate;
                }

                public String getEstampstatus() {
                    return Estampstatus;
                }

                public String getEsignstatus() {
                    return Esignstatus;
                }

                public String getRltnshp() {
                    return Rltnshp;
                }

                public String getPan() {
                    return Pan;
                }

                public String getSigntryName() {
                    return SigntryName;
                }
            }
        }


    }




}
