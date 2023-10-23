package com.saartak.el.models;

import com.google.gson.annotations.Expose;

public class InitiateResponseDTO {

    /**
     * ResponseCode : 200
     * ResponseMessage :
     * ErrorCode :
     * ErrorMessage :
     * ApiResponse : {"AccountSmsResponse":{"LoanId":"","ReferenceId":"","CreatedDate":"","Status":"0","Message":"AccountCreated,SMSSent,","AccountCreate_Status":"1","AccountCreate_Message":"Updated successfully","SMS_Status":"1","SMS_Message":"Success","InsertDB_Status":"0","InsertDB_Message":""}}
     */
    @Expose
    private String ResponseCode;
    @Expose
    private String ResponseMessage;
    @Expose
    private String ErrorCode;
    @Expose
    private String ErrorMessage;
    @Expose
    private ApiResponseBean ApiResponse;

    public String getResponseCode() {
        return ResponseCode;
    }

    public void setResponseCode(String ResponseCode) {
        this.ResponseCode = ResponseCode;
    }

    public String getResponseMessage() {
        return ResponseMessage;
    }

    public void setResponseMessage(String ResponseMessage) {
        this.ResponseMessage = ResponseMessage;
    }

    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String ErrorCode) {
        this.ErrorCode = ErrorCode;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String ErrorMessage) {
        this.ErrorMessage = ErrorMessage;
    }

    public ApiResponseBean getApiResponse() {
        return ApiResponse;
    }

    public void setApiResponse(ApiResponseBean ApiResponse) {
        this.ApiResponse = ApiResponse;
    }

    public static class ApiResponseBean {
        /**
         * AccountSmsResponse : {"LoanId":"","ReferenceId":"","CreatedDate":"","Status":"0","Message":"AccountCreated,SMSSent,","AccountCreate_Status":"1","AccountCreate_Message":"Updated successfully","SMS_Status":"1","SMS_Message":"Success","InsertDB_Status":"0","InsertDB_Message":""}
         */

        @Expose
        private AccountSmsResponseBean AccountSmsResponse;

        public AccountSmsResponseBean getAccountSmsResponse() {
            return AccountSmsResponse;
        }

        public void setAccountSmsResponse(AccountSmsResponseBean AccountSmsResponse) {
            this.AccountSmsResponse = AccountSmsResponse;
        }

        public static class AccountSmsResponseBean {
            /**
             * LoanId :
             * ReferenceId :
             * CreatedDate :
             * Status : 0
             * Message : AccountCreated,SMSSent,
             * AccountCreate_Status : 1
             * AccountCreate_Message : Updated successfully
             * SMS_Status : 1
             * SMS_Message : Success
             * InsertDB_Status : 0
             * InsertDB_Message :
             */
            @Expose
            private String LoanId;
            @Expose
            private String ReferenceId;
            @Expose
            private String CreatedDate;
            @Expose
            private String Status;
            @Expose
            private String Message;
            @Expose
            private String AccountCreate_Status;
            @Expose
            private String AccountCreate_Message;
            @Expose
            private String SMS_Status;
            @Expose
            private String SMS_Message;
            @Expose
            private String InsertDB_Status;
            @Expose
            private String InsertDB_Message;

            public String getLoanId() {
                return LoanId;
            }

            public void setLoanId(String LoanId) {
                this.LoanId = LoanId;
            }

            public String getReferenceId() {
                return ReferenceId;
            }

            public void setReferenceId(String ReferenceId) {
                this.ReferenceId = ReferenceId;
            }

            public String getCreatedDate() {
                return CreatedDate;
            }

            public void setCreatedDate(String CreatedDate) {
                this.CreatedDate = CreatedDate;
            }

            public String getStatus() {
                return Status;
            }

            public void setStatus(String Status) {
                this.Status = Status;
            }

            public String getMessage() {
                return Message;
            }

            public void setMessage(String Message) {
                this.Message = Message;
            }

            public String getAccountCreate_Status() {
                return AccountCreate_Status;
            }

            public void setAccountCreate_Status(String AccountCreate_Status) {
                this.AccountCreate_Status = AccountCreate_Status;
            }

            public String getAccountCreate_Message() {
                return AccountCreate_Message;
            }

            public void setAccountCreate_Message(String AccountCreate_Message) {
                this.AccountCreate_Message = AccountCreate_Message;
            }

            public String getSMS_Status() {
                return SMS_Status;
            }

            public void setSMS_Status(String SMS_Status) {
                this.SMS_Status = SMS_Status;
            }

            public String getSMS_Message() {
                return SMS_Message;
            }

            public void setSMS_Message(String SMS_Message) {
                this.SMS_Message = SMS_Message;
            }

            public String getInsertDB_Status() {
                return InsertDB_Status;
            }

            public void setInsertDB_Status(String InsertDB_Status) {
                this.InsertDB_Status = InsertDB_Status;
            }

            public String getInsertDB_Message() {
                return InsertDB_Message;
            }

            public void setInsertDB_Message(String InsertDB_Message) {
                this.InsertDB_Message = InsertDB_Message;
            }
        }
    }
}
