package com.precision.rdservice;

import com.precision.csp.authapi.encryption.Base64;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.StringWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/*import com.precision.Uidaiauthentication.NPCIConstantValues;
import com.precision.aua.client.AuthInfo;
import com.precision.aua.client.PauaAuth;
import com.precision.aua.client.TransactionInfo;
import com.precision.authapi.authinfo.Uses;
import com.precision.authapi.common.BiometricType;
import com.precision.authapi.common.RequestType;
import com.precision.authapi.common.ResCon;
import com.precision.authapi.common.UsesFlag;
import com.precision.authapi.encryption.Base64;
import com.precision.authapi.pid.data.Pid;*/

public class FormXML {

    String PIDOptionversion = "1.0";
    //String env = "S";
    //String env = "PP";

    String fCount = "1";
    String fType = "0";
    String iCount = "";
    String iType = "";
    String pType = "";
    String pCount = "";
    String format = "0";
    String pidVer = "2.0";
    String timeout = "15000";
    String otp = "";
    String wadh = "";
    String posh = "0";

    String fulxml = null;
	/*UsesFlag mec;
	UsesFlag rc;*/

/////////////////// Wadh values

    //String Ekycver="2.1";
    String Ekycver = "2.5";
    String Authver = "2.0";

    String ra = "F";
    String rcvalue = "Y";
    String lr = "N";
    String de = "N";
    String pfr = "N";
    byte[] digest;

//////////////////

/*public void setEnv(String env){
	this.env=env;
}*/

	/*private String VodafoneLockingKey = "PbV0doL0cK"; 
	private String IdeaLockingKey = "Pb!De@L0cK";*/

    public String FormAuthenticationXML(String strAadhaarNumber,
                                        CaptureResponse captureResponse, DeviceInfo deviceInfo) {
        String authXML = null;
        try {
            StringWriter stringWriter = new StringWriter();
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            document.setXmlStandalone(true);

            Element rootElement = document.createElement("PBAuthInfo");
            document.appendChild(rootElement);
            rootElement.setAttribute("uid", strAadhaarNumber);

            Element usesElement = document.createElement("Uses");
            rootElement.appendChild(usesElement);

            usesElement.setAttribute("pi", "n");
            usesElement.setAttribute("pa", "n");
            usesElement.setAttribute("pfa", "n");
            usesElement.setAttribute("bio", "y");
            usesElement.setAttribute("bt", "FMR");
            usesElement.setAttribute("pin", "n");
            usesElement.setAttribute("otp", "n");

            Element metaElement = document.createElement("Meta");
            rootElement.appendChild(metaElement);

            String udc = deviceInfo.dc.substring(0, 19);
            metaElement.setAttribute("udc", udc);
            metaElement.setAttribute("rdsId", deviceInfo.rdsId);
            metaElement.setAttribute("rdsVer", deviceInfo.rdsVer);
            metaElement.setAttribute("dpId", deviceInfo.dpId);
            metaElement.setAttribute("dc", deviceInfo.dc);
            metaElement.setAttribute("mi", deviceInfo.mi);
            metaElement.setAttribute("mc", deviceInfo.mc);

            Element skeyElement = document.createElement("Skey");
            rootElement.appendChild(skeyElement);

            skeyElement.setAttribute("ci", captureResponse.ci);
            skeyElement.setTextContent(captureResponse.sessionKey);

            Element dataElement = document.createElement("Data");
            rootElement.appendChild(dataElement);

            dataElement.setAttribute("type", "X");
            dataElement.setTextContent(captureResponse.Piddata);

            Element hmacElement = document.createElement("Hmac");
            rootElement.appendChild(hmacElement);

            hmacElement.setTextContent(captureResponse.hmac);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(
                    "{http://xml.apache.org/xslt}indent-amount", "2"); // to

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
            DOMSource source = new DOMSource(document);

            StreamResult result = new StreamResult(stringWriter);
            transformer.transform(source, result);

            authXML = stringWriter.toString();
        } catch (Exception e) {
            authXML = null;
        } finally {

        }
        return authXML;
    }

    public String formCaptureRequestXML(String captureTimeout, String env, String authType) {
        String captureRequestXML = null;
        try {

            //	if (authType.equalsIgnoreCase("EB")) {
			/*	String wadhvalues = Ekycver + ra + rc + lr + de + pfr;
				MessageDigest md = MessageDigest.getInstance("SHA-256");
				md.reset();
				digest = md.digest(wadhvalues.getBytes("UTF-8"));
			//	wadh = Base64.encodeToString(digest, Base64.NO_WRAP);
				wadh = byteArrayToHexString(digest);
				*/

            ////////////////////////////////////////////////////////
            //this.format=formatType;  // XML - 0 or PROTOBUFF - 1

            if (authType.equalsIgnoreCase("EB")) {
                MessageDigest md = null;
                String text = Ekycver + ra + rcvalue + lr + de + pfr;
                // String text = "2.1FYNNN";
                try {
                    md = MessageDigest.getInstance("SHA-256");
                    md.update(text.getBytes("UTF-8")); // Change this to "UTF-16" if needed
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                byte[] digest = md.digest();
                wadh = new String(Base64.EncryptFunc(digest));
            }
            ///////////////////////////////////////////////////////////////


            //   wadh = Base64.encodeToString(digest.toString().getBytes(), Base64.NO_WRAP);
            // wadh="rhVuL7SnJi2W2UmsyukVqY7c93JWyL9O/kVKgdNMfv8=";
            //RDserviceLog.WriteLog("FormXML formCaptureRequestXML: Base 64 encoded wadh value : " +wadh);

            //}
		/*else{
				String wadhvalues = Authver + ra + rc + lr + de + pfr;
				MessageDigest md = MessageDigest.getInstance("SHA-256", "BC");
				md.reset();
				digest = md.digest(wadhvalues.getBytes("UTF-8"));
				wadh = Base64.encodeToString(digest, Base64.NO_WRAP);
				RDserviceLog.WriteLog("FormXML formCaptureRequestXML: Base 64 encoded wadh value : " +wadh);

				
			}*/


            StringWriter writer = new StringWriter();
            DocumentBuilderFactory docFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            doc.setXmlStandalone(true);

            Element rootElement = doc.createElement("PidOptions");
            doc.appendChild(rootElement);
            Attr ver = doc.createAttribute("ver");
            ver.setValue(PIDOptionversion);
            rootElement.setAttributeNode(ver);

            Element optsElement = doc.createElement("Opts");
            rootElement.appendChild(optsElement);

            optsElement.setAttribute("env", env);
            optsElement.setAttribute("fCount", fCount);
            optsElement.setAttribute("fType", fType);
            optsElement.setAttribute("iCount", iCount);
            optsElement.setAttribute("iType", iType);
            optsElement.setAttribute("pCount", pCount);
            optsElement.setAttribute("pType", pType);
            optsElement.setAttribute("format", format);
            optsElement.setAttribute("pidVer", pidVer);
            optsElement.setAttribute("timeout", captureTimeout);
            optsElement.setAttribute("otp", otp);
            optsElement.setAttribute("wadh", wadh);
            optsElement.setAttribute("posh", posh);


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty("omit-xml-declaration", "yes");
            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult(writer);
            transformer.transform(source, result);

            captureRequestXML = writer.toString();

            System.out.println("Capture XML:" + captureRequestXML);
            //		RDserviceLog.WriteLog("Capture XML:"+captureRequestXML);


        } catch (Exception e) {
            captureRequestXML = null;
            //		RDserviceLog.WriteLog(" formCaptureRequestXML: Exception " +e.getMessage().toString());

        } finally {

        }
        return captureRequestXML;
    }
	
	/*public String authBiometric(String strAadhaarNumber,
			CaptureResponse captureResponse,DeviceInfo deviceInfo,Context context)
	{
	//	textViewOut.setText("");
		String reqxml = "";
		try
		{
			
		//	String strAadharID = getIntent().getStringExtra("AadharID").toString();
			//String strAadharID = "602273125954";


			if(lsBio == null ||lsBio.size() == 0){
				textViewOut.setText("Please capture Fingerprint...");
				return;
			}
			AuthInfo authInfo = NPCIConstantValues.getAuthInfo(strAadhaarNumber, RequestType.AB);
			

			TransactionInfo transactionInfo = NPCIConstantValues.gettransactionInfo(RequestType.AB);
			
			Uses uses = new Uses();
			uses.bio(UsesFlag.Y);
			uses.bt(BiometricType.FMR);
			
			Pid pid = new Pid();
		//	pid.bios(lsBio);
			
			//devInfo = NPCIConstantValues.getDeviceInfo(fingerprintCapture.getSerialnumber(),NPCIConstantValues.staffID,RequestType.AB);
					
		//	byte[] uidaicert = NPCIConstantValues.getCertificate(context);
			
		PauaAuth	pauaAuth = new PauaAuth();
			reqxml = pauaAuth.AuaAuthRequest(authInfo, transactionInfo, uses, deviceInfo, pid, null, captureResponse,null,null);
			
		//	reqxml = pauaAuth.AuaAuthRequest(authInfo, transactionInfo, uses, devInfo, pid, uidaicert, null);

		 fulxml = "&sXML=" + reqxml;
			
			//String fulxml = "&sXML=" + SampleRequest;
			
			File direc =new File("/sdcard/RequestXML");
			if(!direc.exists()){
				direc.mkdir();
			}
			try{
				PrintWriter out=new PrintWriter("/sdcard/RequestXML/AuthenticationRequestXml.txt");
				out.write(fulxml);
				out.close();
			}catch(Exception ex){
				ex.printStackTrace();
			}
			
			if(isNetworkAvailable())
			{
			//	new HtppAsyncAuth().execute(fulxml,"AUTH");
			}
			else
			{
				//textViewOut.setText("Network not available");
			}
			
			spinner.setSelection(0);
			HMacData = "";
			SessionData = "";
			PIDData = "";
			strCertExpiryDate = "";
			TimeStamp = "";
			fingerPosition = "";
			
		//	imgview.setImageBitmap(null);
           
		}
		catch(Exception exp)
		{
		//	textViewOut.setText(exp.getMessage());
		}
		return fulxml;

	}*/

    /*public String eKYCBiometric(String strAadhaarNumber,
            CaptureResponse captureResponse,DeviceInfo deviceInfo,String clientId,String screenNo,String aquirerID,String RC)
    {
    //	textViewOut.setText("");
        String reqxml = "";
        try
        {


            AuthInfo authInfo = NPCIConstantValues.getAuthInfo(strAadhaarNumber, RequestType.EB);


            TransactionInfo transactionInfo = NPCIConstantValues.gettransactionInfo(RequestType.EB,aquirerID);

            Uses uses = new Uses();
            uses.bio(UsesFlag.Y);
            uses.bt(BiometricType.FMR);

            Pid pid = new Pid();
        //	pid.bios(lsBio);
            if(RC.equalsIgnoreCase("Y")){
            rc = UsesFlag.Y;
            }else{
                rc=UsesFlag.N;
            }

            ResCon resCon = new ResCon();
            resCon.mec(mec);
            resCon.rc(rc);
            pid.rescon(resCon);
            //pid.bios(lsBio);

            //devInfo = NPCIConstantValues.getDeviceInfo(fingerprintCapture.getSerialnumber(),NPCIConstantValues.staffID,RequestType.AB);

            //byte[] uidaicert = NPCIConstantValues.getCertificate(context);

        PauaAuth	pauaAuth = new PauaAuth();
            reqxml = pauaAuth.AuaAuthRequest(authInfo, transactionInfo, uses, deviceInfo, pid, null, captureResponse,clientId,screenNo,aquirerID);

        //	reqxml = pauaAuth.AuaAuthRequest(authInfo, transactionInfo, uses, devInfo, pid, uidaicert, null);

         fulxml = "&sXML=" + reqxml;

            //String fulxml = "&sXML=" + SampleRequest;

            *//*File direc =new File("/sdcard/RequestXML");
			if(!direc.exists()){
				direc.mkdir();
			}
			try{
				PrintWriter out=new PrintWriter("/sdcard/RequestXML/E-kyc_RdService_RequestXml.txt");
				out.write(fulxml);
				out.close();
			}catch(Exception ex){
				ex.printStackTrace();
			}*//*

     *//*	if(isNetworkAvailable())
			{
			//	new HtppAsyncAuth().execute(fulxml,"AUTH");
			}
			else
			{
				//textViewOut.setText("Network not available");
			}*//*

     *//*spinner.setSelection(0);
			HMacData = "";
			SessionData = "";
			PIDData = "";
			strCertExpiryDate = "";
			TimeStamp = "";
			fingerPosition = "";*//*
			
		//	imgview.setImageBitmap(null);
           
		}
		catch(Exception exp)
		{
			exp.getMessage().toString();
		//	textViewOut.setText(exp.getMessage());
			RDserviceLog.WriteLog("FormEKYCRequestXML: Exception " +exp.getMessage().toString());

		}
		return fulxml;

	}*/
    protected String byteArrayToHexString(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            result.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                    .substring(1));
        }
        return result.toString();
    }

}
