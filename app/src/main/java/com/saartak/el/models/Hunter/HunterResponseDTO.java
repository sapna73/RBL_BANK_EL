package com.saartak.el.models.Hunter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class HunterResponseDTO {

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
        @SerializedName("ResultBlock")
        private ResultBlock ResultBlock;
        @Expose
        @SerializedName("M1")
        private String M1;
        @Expose
        @SerializedName("Z1")
        private String Z1;
        @Expose
        @SerializedName("D1")
        private String D1;
        @Expose
        @SerializedName("ErrorDescription")
        private String ErrorDescription;
        @Expose
        @SerializedName("ErrorCode")
        private String ErrorCode;
        @Expose
        @SerializedName("Status")
        private String Status;
        @Expose
        @SerializedName("MessageDateTime")
        private String MessageDateTime;
        @Expose
        @SerializedName("RequestUUID")
        private String RequestUUID;
        @Expose
        @SerializedName("Identifier")
        private String Identifier;
        @Expose
        @SerializedName("ClientId")
        private String ClientId;

        public ApiResponse.ResultBlock getResultBlock() {
            return ResultBlock;
        }

        public String getM1() {
            return M1;
        }

        public String getZ1() {
            return Z1;
        }

        public String getD1() {
            return D1;
        }

        public String getErrorDescription() {
            return ErrorDescription;
        }

        public String getErrorCode() {
            return ErrorCode;
        }

        public String getStatus() {
            return Status;
        }

        public String getMessageDateTime() {
            return MessageDateTime;
        }

        public String getRequestUUID() {
            return RequestUUID;
        }

        public String getIdentifier() {
            return Identifier;
        }

        public String getClientId() {
            return ClientId;
        }

        public static class ResultBlock {
            @Expose
            @SerializedName("MatchResponse")
            private MatchResponse MatchResponse;

            public MatchResponse getMatchResponse() {
                return MatchResponse;
            }

            public static class MatchResponse {
                @Expose
                @SerializedName("MatchResult")
                private MatchResult MatchResult;


                public MatchResult getMatchResult() {
                    return MatchResult;
                }

                public static class MatchResult {
                    @Expose
                    @SerializedName("ErrorWarnings")
                    private ErrorWarnings ErrorWarnings;
                    @Expose
                    @SerializedName("MatchSummary")
                    private MatchSummary MatchSummary;

                    public ErrorWarnings getErrorWarnings() {
                        return ErrorWarnings;
                    }

                    public MatchSummary getMatchSummary() {
                        return MatchSummary;
                    }

                    public static class MatchSummary {
                        @Expose
                        @SerializedName("MatchSchemes")
                        private MatchSchemes MatchSchemes;
                        @Expose
                        @SerializedName("Rules")
                        private Rules Rules;
                        @Expose
                        @SerializedName("TotalMatchScore")
                        private String TotalMatchScore;
                        @Expose
                        @SerializedName("Matches")
                        private String Matches;

                        public MatchSchemes getMatchSchemes() {
                            return MatchSchemes;
                        }

                        public Rules getRules() {
                            return Rules;
                        }

                        public String getTotalMatchScore() {
                            return TotalMatchScore;
                        }

                        public String getMatches() {
                            return Matches;
                        }

                        public static class Rules {
                            @Expose
                            @SerializedName("Rule")
                            private List<Rule> Rule;
                            @Expose
                            @SerializedName("TotalRuleCount")
                            private String TotalRuleCount;

                            public List<Rule> getRule() {
                                return Rule;
                            }

                            public String getTotalRuleCount() {
                                return TotalRuleCount;
                            }

                            public static class Rule {
                                @Expose
                                @SerializedName("RuleCount")
                                private String RuleCount;
                                @Expose
                                @SerializedName("Score")
                                private String Score;
                                @Expose
                                @SerializedName("RuleID")
                                private String RuleID;

                                public String getRuleCount() {
                                    return RuleCount;
                                }

                                public void setRuleCount(String ruleCount) {
                                    RuleCount = ruleCount;
                                }

                                public String getScore() {
                                    return Score;
                                }

                                public void setScore(String score) {
                                    Score = score;
                                }

                                public String getRuleID() {
                                    return RuleID;
                                }

                                public void setRuleID(String ruleID) {
                                    RuleID = ruleID;
                                }
                            }
                        }
                        public static class MatchSchemes {
                            @Expose
                            @SerializedName("Scheme")
                            private List<Scheme> Scheme;
                            @Expose
                            @SerializedName("SchemeCount")
                            private String SchemeCount;

                            public static class Scheme {
                                @Expose
                                @SerializedName("Score")
                                private String Score;
                                @Expose
                                @SerializedName("SchemeID")
                                private String SchemeID;
                            }
                        }


                    }

                    public static class ErrorWarnings {
                        @Expose
                        @SerializedName("Warnings")
                        private Warnings Warnings;
                        @Expose
                        @SerializedName("Errors")
                        private Errors Errors;

                        public Warnings getWarnings() {
                            return Warnings;
                        }

                        public Errors getErrors() {
                            return Errors;
                        }

                        public static class Warnings {
                            @Expose
                            @SerializedName("WarningList")
                            private List<WarningList> WarningList;
                            @Expose
                            @SerializedName("WarningCount")
                            private String WarningCount;

                            public List<WarningList> getWarningList() {
                                return WarningList;
                            }

                            public String getWarningCount() {
                                return WarningCount;
                            }

                            public static class WarningList {
                                @Expose
                                @SerializedName("Values")
                                private Values Values;
                                @Expose
                                @SerializedName("Message")
                                private String Message;
                                @Expose
                                @SerializedName("Number")
                                private String Number;

                                public Warnings.WarningList.Values getValues() {
                                    return Values;
                                }

                                public String getMessage() {
                                    return Message;
                                }

                                public String getNumber() {
                                    return Number;
                                }

                                public static class Values {
                                    @Expose
                                    @SerializedName("Value")
                                    private List<String> Value;

                                    public List<String> getValue() {
                                        return Value;
                                    }
                                }
                            }
                        }

                        public static class Errors {
                            @Expose
                            @SerializedName("ErrorList")
                            private List<String> ErrorList;
                            @Expose
                            @SerializedName("ErrorCount")
                            private String ErrorCount;

                            public List<String> getErrorList() {
                                return ErrorList;
                            }

                            public String getErrorCount() {
                                return ErrorCount;
                            }
                        }
                    }
                }
            }
        }
    }


}
