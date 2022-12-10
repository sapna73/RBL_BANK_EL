package com.precision.csp.Uidaiauthentication;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.StringBufferInputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

@SuppressWarnings("deprecation")
public class SplitXML {

	String errMsg;
    String errCode;
    String transactionID;
    String AtmReqID;



    String UidaiAuthcode;
	String MerchantName;
	String MerchantID;
	String UIDNumber;
	String status;
	String mobileNumber;
	String pidType;

	String merchantAccNo;


	String ApkStatus;

	String RRN;

	String AccountNumber;
	String CiffNumber;
	String RDAccountNumber;

	String Hashvalue;
	String SessionKey;

	public String getHashvalue() {
		return Hashvalue;
	}

	public void setHashvalue(String hashvalue) {
		Hashvalue = hashvalue;
	}

	public String getSessionKey() {
		return SessionKey;
	}

	public void setSessionKey(String sessionKey) {
		SessionKey = sessionKey;
	}

	public String getBalance() {
		return Balance;
	}

	public void setBalance(String balance) {
		Balance = balance;
	}

	String Balance;

	String BankFullName,BankShortName;
    String[] BankFullNameStringArray;
    ArrayList<String> BankFullNameArray=new ArrayList<String>();

	private String DOB; //DD-MM-YYYY
	private String Gender;
	private String House;
	private String LM;
	private String MailID;
	private String Name;
	private String PhoneNumber;
	private String Street;
	private String UID_RRN;
	private String TransactionID;
	private String UIDAINumber;
	private String coDetails;
	private String dist;
	private String loc;
	private String pc;
	private String po;
	private String strstate;
	private String subdist;
	private String vtc;
	private String photo;
	private String fullAddress;

    public String getBankShortName() {
		return BankShortName;
	}

	public void setBankShortName(String bankShortName) {
		BankShortName = bankShortName;
	}

	public ArrayList<String> getBankFullNameArray() {
		return BankFullNameArray;
	}

	public void setBankFullNameArray(ArrayList<String> bankFullNameArray) {
		BankFullNameArray = bankFullNameArray;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	ArrayList<String> BankShortNameArray=new ArrayList<String>();
	public void splitFinalResponse(String XMLValue) throws Exception {

		DocumentBuilderFactory dbBuilderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder documentBuilder = dbBuilderFactory.newDocumentBuilder();
		try {
			// transationdata=new TranscationData();
			Document document = documentBuilder
					.parse(new StringBufferInputStream(XMLValue));
			document.getDocumentElement().normalize();
			System.out.println("Root element :"
					+ document.getDocumentElement().getNodeName());
			NodeList nodeList = document.getElementsByTagName("AuaAuthRes");
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node n = (Node) nodeList.item(i);
				if (n.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) n;
					errCode = element.getAttribute("errcode");
					errMsg = element.getAttribute("errmsg");
					transactionID = element.getAttribute("TransactionID");
                    AtmReqID = element.getAttribute("AtmReqID");
                    UidaiAuthcode = element.getAttribute("UIDAIAuth");
					RRN=element.getAttribute("RRN");
					try {
						Log.i("", "Account Number -->"+element.getAttribute("AccountNumber"));
						AccountNumber=element.getAttribute("AccountNumber");
						CiffNumber=element.getAttribute("CiffNumber");
						RDAccountNumber=element.getAttribute("RDAccountNumber");
						Hashvalue=element.getAttribute("Hashvalue");
						SessionKey=element.getAttribute("SessionKey");
					} catch (Exception e) {
						e.printStackTrace();
					}

					Balance=element.getAttribute("Balance");
					DOB=element.getAttribute("DOB");
					Gender=element.getAttribute("Gender");
					House=element.getAttribute("House");
					LM=element.getAttribute("LM");
					MailID=element.getAttribute("MailID");
					Name=element.getAttribute("Name");
					PhoneNumber=element.getAttribute("PhoneNumber");
					Street=element.getAttribute("Street");
					UID_RRN=element.getAttribute("RRN");
					TransactionID=element.getAttribute("TransactionID");
					UIDAINumber=element.getAttribute("UIDAINumber");
					coDetails=element.getAttribute("coDetails");
					dist=element.getAttribute("dist");
					loc=element.getAttribute("loc");
					pc=element.getAttribute("pc");
					po=element.getAttribute("po");
					strstate=element.getAttribute("strstate");
					subdist=element.getAttribute("subdist");
					vtc=element.getAttribute("vtc");
					photo=element.getAttribute("photo");
				}
			}
			if (errCode.equalsIgnoreCase("0")) {
				NodeList merchantNodelist = document
						.getElementsByTagName("MerchantInfo");
				for (int i = 0; i < merchantNodelist.getLength(); i++) {
					Node n = (Node) merchantNodelist.item(i);
					if (n.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) n;
						MerchantName = element.getAttribute("Name");
						MerchantID = element.getAttribute("ID");
						UIDNumber = element.getAttribute("UIDAINumber");
						mobileNumber = element.getAttribute("MobileNumber");
						pidType = element.getAttribute("PidType");
						merchantAccNo=element.getAttribute("MerchantAccNo");
						ApkStatus=element.getAttribute("APKStatus");
						/*AccountNumber=element.getAttribute("AccountNumber");
						CiffNumber=element.getAttribute("CiffNumber");
*/

					}
				}
				
				/*NodeList bankList = document.getElementsByTagName("BankInfos");
				for (int i = 0; i < bankList.getLength(); i++) {
					Node n = (Node) bankList.item(i);
					if (n.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) n;
						BankFullName = element.getAttribute("name");
						BankShortName = element.getAttribute("value");
						System.out.println("BankFullName: "+BankFullName);
						System.out.println("BankShortName: "+BankShortName);
						BankFullNameArray.add(BankFullName);
						BankShortNameArray.add(BankShortName);
						BankFullNameStringArray[i]=BankFullName;

					}
				}*/
				
				
			}

		}

		catch (Exception e1) {
			e1.getMessage();
		}

	}
	public String getApkStatus() {
		return ApkStatus;
	}

	public void setApkStatus(String apkStatus) {
		ApkStatus = apkStatus;
	}
    public String getAtmReqID() {
        return AtmReqID;
    }

    public void setAtmReqID(String atmReqID) {
        AtmReqID = atmReqID;
    }

    public String getUidaiAuthcode() {
        return UidaiAuthcode;
    }

    public void setUidaiAuthcode(String uidaiAuthcode) {
        UidaiAuthcode = uidaiAuthcode;
    }

	public String getRRN() {
		return RRN;
	}

	public void setRRN(String RRN) {
		this.RRN = RRN;
	}
	public String getMerchantAccNo() {
		return merchantAccNo;
	}

	public void setMerchantAccNo(String merchantAccNo) {
		this.merchantAccNo = merchantAccNo;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getMerchantName() {
		return MerchantName;
	}

	public void setMerchantName(String merchantName) {
		MerchantName = merchantName;
	}

	public String getMerchantID() {
		return MerchantID;
	}

	public void setMerchantID(String merchantID) {
		MerchantID = merchantID;
	}

	public String getUIDNumber() {
		return UIDNumber;
	}

	public void setUIDNumber(String uIDNumber) {
		UIDNumber = uIDNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorCode() {
		return this.errCode;
	}

	public String getErrorMessage() {
		return this.errMsg;
	}

	public String getTransactionID() {
		return this.transactionID;
	}
	 public String getPidType() {
			return pidType;
		}

		public void setPidType(String pidType) {
			this.pidType = pidType;
		}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public String getUIDAINumber() {
		return UIDAINumber;
	}

	public void setUIDAINumber(String UIDAINumber) {
		this.UIDAINumber = UIDAINumber;
	}

	public String getCoDetails() {
		return coDetails;
	}

	public void setCoDetails(String coDetails) {
		this.coDetails = coDetails;
	}

	public String getDist() {
		return dist;
	}

	public void setDist(String dist) {
		this.dist = dist;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public String getPc() {
		return pc;
	}

	public void setPc(String pc) {
		this.pc = pc;
	}

	public String getPo() {
		return po;
	}

	public void setPo(String po) {
		this.po = po;
	}

	public String getStrstate() {
		return strstate;
	}

	public void setStrstate(String strstate) {
		this.strstate = strstate;
	}

	public String getSubdist() {
		return subdist;
	}

	public void setSubdist(String subdist) {
		this.subdist = subdist;
	}

	public String getVtc() {
		return vtc;
	}

	public void setVtc(String vtc) {
		this.vtc = vtc;
	}

	public ArrayList<String> getBankShortNameArray() {
		return BankShortNameArray;
	}

	public void setBankShortNameArray(ArrayList<String> bankShortNameArray) {
		BankShortNameArray = bankShortNameArray;
	}

	public String getBankFullName() {
		return BankFullName;
	}

	public void setBankFullName(String bankFullName) {
		BankFullName = bankFullName;
	}

	public String[] getBankFullNameStringArray() {
		return BankFullNameStringArray;
	}

	public void setBankFullNameStringArray(String[] bankFullNameStringArray) {
		BankFullNameStringArray = bankFullNameStringArray;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String DOB) {
		this.DOB = DOB;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getHouse() {
		return House;
	}

	public void setHouse(String house) {
		House = house;
	}

	public String getLM() {
		return LM;
	}

	public void setLM(String LM) {
		this.LM = LM;
	}

	public String getMailID() {
		return MailID;
	}

	public void setMailID(String mailID) {
		MailID = mailID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public String getStreet() {
		return Street;
	}

	public void setStreet(String street) {
		Street = street;
	}

	public String getUID_RRN() {
		return UID_RRN;
	}

	public void setUID_RRN(String UID_RRN) {
		this.UID_RRN = UID_RRN;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getAccountNumber() {
		return AccountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}

	public String getCiffNumber() {
		return CiffNumber;
	}

	public void setCiffNumber(String ciffNumber) {
		CiffNumber = ciffNumber;
	}

	public String getRDAccountNumber() {
		return RDAccountNumber;
	}

	public void setRDAccountNumber(String RDAccountNumber) {
		this.RDAccountNumber = RDAccountNumber;
	}
}
