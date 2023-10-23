package com.saartak.el.models.ESignEstamp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class ESignEStampRequestDTO {

    @Expose
    @SerializedName("UniqueId")
    private String UniqueId;
    @Expose
    @SerializedName("ServiceType")
    private String ServiceType;
    @Expose
    @SerializedName("RequestString")
    private RequestString RequestString;
    @Expose
    @SerializedName("KYCId")
    private String KYCId;
    @Expose
    @SerializedName("ModuleType")
    private String ModuleType;
    @Expose
    @SerializedName("co_applicant_id")
    private String coapplicantId;
    @Expose
    @SerializedName("ExternalCustomerId")
    private String ExternalCustomerId;
    @Expose
    @SerializedName("CreatedDate")
    private String CreatedDate;
    @Expose
    @SerializedName("CreatedBy")
    private String CreatedBy;
    @Expose
    @SerializedName("ClientID")
    private String ClientID;

    public void setUniqueId(String uniqueId) {
        UniqueId = uniqueId;
    }

    public void setServiceType(String serviceType) {
        ServiceType = serviceType;
    }

    public void setRequestString(RequestString requestString) {
        RequestString = requestString;
    }

    public void setKYCId(String KYCId) {
        this.KYCId = KYCId;
    }

    public void setModuleType(String moduleType) {
        ModuleType = moduleType;
    }

    public void setCoapplicantId(String coapplicantId) {
        this.coapplicantId = coapplicantId;
    }

    public void setExternalCustomerId(String externalCustomerId) {
        ExternalCustomerId = externalCustomerId;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public void setClientID(String clientID) {
        ClientID = clientID;
    }

    public static class RequestString {
        @Expose
        @SerializedName("EsignIntiationreq")
        private EsignIntiationreq EsignIntiationreq;

        public void setEsignIntiationreq(EsignIntiationreq esignIntiationreq) {
            EsignIntiationreq = esignIntiationreq;
        }

        public static class EsignIntiationreq {
            @Expose
            @SerializedName("loansection")
            private Loansection loansection;
            @Expose
            @SerializedName("prtcptentity")
            private Prtcptentity prtcptentity;
            @Expose
            @SerializedName("estampdetails")
            private Estampdetails estampdetails;
            @Expose
            @SerializedName("entitydetails")
            private Entitydetails entitydetails;
            @Expose
            @SerializedName("statecode")
            private String statecode;
            @Expose
            @SerializedName("regtype")
            private String regtype;

            public void setLoansection(Loansection loansection) {
                this.loansection = loansection;
            }

            public void setPrtcptentity(Prtcptentity prtcptentity) {
                this.prtcptentity = prtcptentity;
            }

            public void setEstampdetails(Estampdetails estampdetails) {
                this.estampdetails = estampdetails;
            }

            public void setStatecode(String statecode) {
                this.statecode = statecode;
            }

            public void setRegtype(String regtype) {
                this.regtype = regtype;
            }

            public void setEntitydetails(Entitydetails entitydetails) {
                this.entitydetails = entitydetails;
            }

            public static class Entitydetails {

                @Expose
                @SerializedName("entitypan")
                private String entitypan="";
                @Expose
                @SerializedName("entityname")
                private String entityname="";
                @Expose
                @SerializedName("lglcnstn")
                private String lglcnstn="";
                @Expose
                @SerializedName("doi")
                private String doi="";
                @Expose
                @SerializedName("emailid")
                private String emailid="";
                @Expose
                @SerializedName("regaddr")
                private String regaddr="";
                @Expose
                @SerializedName("regpin")
                private String regpin="";
                @Expose
                @SerializedName("commaddr")
                private String commaddr="";
                @Expose
                @SerializedName("contactno")
                private String contactno="";
                @Expose
                @SerializedName("commpin")
                private String commpin="";

                public void setEntitypan(String entitypan) {
                    this.entitypan = entitypan;
                }

                public void setEntityname(String entityname) {
                    this.entityname = entityname;
                }

                public void setLglcnstn(String lglcnstn) {
                    this.lglcnstn = lglcnstn;
                }

                public void setDoi(String doi) {
                    this.doi = doi;
                }

                public void setEmailid(String emailid) {
                    this.emailid = emailid;
                }

                public void setRegaddr(String regaddr) {
                    this.regaddr = regaddr;
                }

                public void setRegpin(String regpin) {
                    this.regpin = regpin;
                }

                public void setCommaddr(String commaddr) {
                    this.commaddr = commaddr;
                }

                public void setContactno(String contactno) {
                    this.contactno = contactno;
                }

                public void setCommpin(String commpin) {
                    this.commpin = commpin;
                }
            }

            public static class Loansection {
                @Expose
                @SerializedName("tenure")
                private String tenure;
                @Expose
                @SerializedName("snctamount")
                private String snctamount;
                @Expose
                @SerializedName("roi")
                private String roi;

                public void setTenure(String tenure) {
                    this.tenure = tenure;
                }

                public void setSnctamount(String snctamount) {
                    this.snctamount = snctamount;
                }

                public void setRoi(String roi) {
                    this.roi = roi;
                }

            }

            public static class Estampdetails {
                @Expose
                @SerializedName("estampdetail")
                private List<Estampdetail> estampdetail;

                public void setEstampdetail(List<Estampdetail> estampdetail) {
                    this.estampdetail = estampdetail;
                }

                public static class Estampdetail {
                    @Expose
                    @SerializedName("secondpartyovdvalue")
                    private String secondpartyovdvalue;
                    @Expose
                    @SerializedName("secondpartyovdtype")
                    private String secondpartyovdtype;
                    @Expose
                    @SerializedName("firstpartyovdvalue")
                    private String firstpartyovdvalue;
                    @Expose
                    @SerializedName("firstpartyovdtype")
                    private String firstpartyovdtype;
                    @Expose
                    @SerializedName("secondpartypincode")
                    private String secondpartypincode;
                    @Expose
                    @SerializedName("firstpartypincode")
                    private String firstpartypincode;
                    @Expose
                    @SerializedName("documentid")
                    private String documentid;
                    @Expose
                    @SerializedName("considerationprice")
                    private String considerationprice;
                    @Expose
                    @SerializedName("firstparty")
                    private String firstparty;

                    public void setSecondpartyovdvalue(String secondpartyovdvalue) {
                        this.secondpartyovdvalue = secondpartyovdvalue;
                    }

                    public void setSecondpartyovdtype(String secondpartyovdtype) {
                        this.secondpartyovdtype = secondpartyovdtype;
                    }

                    public void setFirstpartyovdvalue(String firstpartyovdvalue) {
                        this.firstpartyovdvalue = firstpartyovdvalue;
                    }

                    public void setFirstpartyovdtype(String firstpartyovdtype) {
                        this.firstpartyovdtype = firstpartyovdtype;
                    }

                    public void setSecondpartypincode(String secondpartypincode) {
                        this.secondpartypincode = secondpartypincode;
                    }

                    public void setFirstpartypincode(String firstpartypincode) {
                        this.firstpartypincode = firstpartypincode;
                    }

                    public void setDocumentid(String documentid) {
                        this.documentid = documentid;
                    }

                    public void setFirstparty(String firstparty) {
                        this.firstparty = firstparty;
                    }

                    public void setConsiderationprice(String considerationprice) {
                        this.considerationprice = considerationprice;
                    }
                }
            }

            public static class Prtcptentity {
                @Expose
                @SerializedName("participant")
                private List<Participant> participant;

                public void setParticipant(List<Participant> participant) {
                    this.participant = participant;
                }

                public static class Participant {
                    @Expose
                    @SerializedName("seqno")
                    private String seqno;
                    @Expose
                    @SerializedName("documents")
                    private List<Documents> documents;
                    @Expose
                    @SerializedName("partytype")
                    private String partytype;
                    @Expose
                    @SerializedName("legalconstitution")
                    private String legalconstitution;
                    @Expose
                    @SerializedName("doi")
                    private String doi;
                    @Expose
                    @SerializedName("ovdid")
                    private String ovdid;
                    @Expose
                    @SerializedName("ovdtype")
                    private String ovdtype;
                    @Expose
                    @SerializedName("panno")
                    private String panno="";
                    @Expose
                    @SerializedName("alternatemobno")
                    private String alternatemobno="";
                    @Expose
                    @SerializedName("alternateemaild")
                    private String alternateemaild="";
                    @Expose
                    @SerializedName("mobno")
                    private String mobno;
                    @Expose
                    @SerializedName("emaild")
                    private String emaild;
                    @Expose
                    @SerializedName("relationshipwithcontract")
                    private String relationshipwithcontract;
                    @Expose
                    @SerializedName("fullname")
                    private String fullname;
                    @Expose
                    @SerializedName("participantid")
                    private String participantid;

                    public void setSeqno(String seqno) {
                        this.seqno = seqno;
                    }

                    public void setDocuments(List<Documents> documents) {
                        this.documents = documents;
                    }

                    public void setPartytype(String partytype) {
                        this.partytype = partytype;
                    }

                    public void setLegalconstitution(String legalconstitution) {
                        this.legalconstitution = legalconstitution;
                    }

                    public void setDoi(String doi) {
                        this.doi = doi;
                    }

                    public void setOvdid(String ovdid) {
                        this.ovdid = ovdid;
                    }

                    public void setOvdtype(String ovdtype) {
                        this.ovdtype = ovdtype;
                    }

                    public void setPanno(String panno) {
                        this.panno = panno;
                    }

                    public void setAlternatemobno(String alternatemobno) {
                        this.alternatemobno = alternatemobno;
                    }

                    public void setAlternateemaild(String alternateemaild) {
                        this.alternateemaild = alternateemaild;
                    }

                    public void setMobno(String mobno) {
                        this.mobno = mobno;
                    }

                    public void setEmaild(String emaild) {
                        this.emaild = emaild;
                    }

                    public void setRelationshipwithcontract(String relationshipwithcontract) {
                        this.relationshipwithcontract = relationshipwithcontract;
                    }

                    public void setFullname(String fullname) {
                        this.fullname = fullname;
                    }

                    public void setParticipantid(String participantid) {
                        this.participantid = participantid;
                    }

                    public String getSeqno() {
                        return seqno;
                    }

                    public List<Documents> getDocuments() {
                        return documents;
                    }

                    public String getPartytype() {
                        return partytype;
                    }

                    public String getLegalconstitution() {
                        return legalconstitution;
                    }

                    public String getDoi() {
                        return doi;
                    }

                    public String getOvdid() {
                        return ovdid;
                    }

                    public String getOvdtype() {
                        return ovdtype;
                    }

                    public String getPanno() {
                        return panno;
                    }

                    public String getAlternatemobno() {
                        return alternatemobno;
                    }

                    public String getAlternateemaild() {
                        return alternateemaild;
                    }

                    public String getMobno() {
                        return mobno;
                    }

                    public String getEmaild() {
                        return emaild;
                    }

                    public String getFullname() {
                        return fullname;
                    }

                    public String getParticipantid() {
                        return participantid;
                    }

                    public String getRelationshipwithcontract() {
                        return relationshipwithcontract;
                    }

                    public static class Documents {
                        @Expose
                        @SerializedName("documentid")
                        private String documentid;

                        public void setDocumentid(String documentid) {
                            this.documentid = documentid;
                        }

                        public String getDocumentid() {
                            return documentid;
                        }
                    }
                }
            }

        }
    }
}
