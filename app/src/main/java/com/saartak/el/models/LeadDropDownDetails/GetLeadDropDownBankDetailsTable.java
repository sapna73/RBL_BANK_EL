package com.saartak.el.models.LeadDropDownDetails;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class GetLeadDropDownBankDetailsTable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("ID")
    @Expose(serialize = false, deserialize = false)
    private int id;


    @SerializedName("DSAConnectorCode")
    @Expose
    private String dsaConnectorCode;

    @SerializedName("NameofDealerDSEReferral")
    @Expose
    private String nameofDealerDSEReferral;

    @SerializedName("TypeofChannel")
    @Expose
    private String typeofChannel;

    @SerializedName("Bank")
    @Expose
    private String bank;

    @SerializedName("State")
    @Expose
    private String state;

    @SerializedName("BankBranch")
    @Expose
    private String bankBranch;

    @SerializedName("AccountNo")
    @Expose
    private String accountNo;

    @SerializedName("IFSCCode")
    @Expose
    private String IFSCCode;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getDsaConnectorCode() {
        return dsaConnectorCode;
    }

    public void setDsaConnectorCode(String dsaConnectorCode) {
        this.dsaConnectorCode = dsaConnectorCode;
    }

    public String getNameofDealerDSEReferral() {
        return nameofDealerDSEReferral;
    }

    public void setNameofDealerDSEReferral(String nameofDealerDSEReferral) {
        this.nameofDealerDSEReferral = nameofDealerDSEReferral;
    }

    public String getTypeofChannel() {
        return typeofChannel;
    }

    public void setTypeofChannel(String typeofChannel) {
        this.typeofChannel = typeofChannel;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getIFSCCode() {
        return IFSCCode;
    }

    public void setIFSCCode(String IFSCCode) {
        this.IFSCCode = IFSCCode;
    }
}
