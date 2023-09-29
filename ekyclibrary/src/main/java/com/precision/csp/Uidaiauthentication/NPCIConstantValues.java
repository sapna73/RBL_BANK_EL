package com.precision.csp.Uidaiauthentication;
/*Created By : Ali Hussain
 * Date : 18/4/2017
 * Application : CSP (customer service point)
 */

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import com.precision.csp.aua.client.AuthInfo;
import com.precision.csp.aua.client.DevInfo;
import com.precision.csp.aua.client.TransactionInfo;
import com.precision.csp.authapi.common.LocationType;
import com.precision.csp.authapi.common.RequestType;
import com.precision.rdservice.FormXML;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;

import static com.bfil.ekyclibrary.constants.EKYCConstants.EKYC_UAT_ENVIRONMENT;

public class NPCIConstantValues {

	public static String TAG=NPCIConstantValues.class.getCanonicalName();
	public String ACTION_USB_PERMISSION = "com.bfil.csp.USB_PERMISSION";
	boolean isPermissionAvailable = false;
	boolean isScannerConnected = false;

	public static NPCIConstantValues npciConstant=null;

	public static NPCIConstantValues getInstance() {
		if(npciConstant == null) {
			npciConstant = new NPCIConstantValues();
		}
		return  npciConstant;
	}

	public static String strResidentConsent = "I hereby state that I have no objection in authenticating myself with Aadhaar based authentication system " +
			" and consent to providing my Aadhaar number, Biometric and/or One Time Pin (OTP) data (and/or any similar authentication mechanism) " +
			" for Aadhaar based authentication.I here by give my consent to access and store my personal details.";

	public static String strMerchantNameAddr = "Bharat Financial";//
	public static String strCityName = "Hyderabad";
	public static String strState = "TS";
	public static String strCountry = "IN";
	public static String strCA_TA = String.format("%1$-" + 23 + "s",
			strMerchantNameAddr)
			+ String.format("%1$-" + 13 + "s", strCityName)
			+ String.format("%1$-" + 2 + "s", strState)
			+ String.format("%1$-" + 2 + "s", strCountry);

	public static String Vendor_Code = "BFI001";
	public static String udc = "BFI000000006000";

	public static String AquirerID = "210039";

	public static int INFO_REQUEST = 0;
	public static int CAPTURE_REQUEST = 1;

	public static byte[] getCertificate(Context context) {
		byte[] certificate = null;
		try {
			InputStream ins = context.getAssets().open("uidai_auth_prod.cer");
			certificate = new byte[ins.available()];
			ins.read(certificate);
			ins.close();
		} catch (Exception e) {
			certificate = null;
			e.printStackTrace();
		}
		return certificate;
	}

	public static AuthInfo getAuthInfo(String aadhaarID, RequestType requesttype) {
		AuthInfo authInfo = null;
		try {
			authInfo = new AuthInfo();
			authInfo.rt(requesttype);
			authInfo.uid(aadhaarID);
			//	authInfo.tid("public");  For public device
			authInfo.tid("registered"); // For Register service
			authInfo.vc(Vendor_Code);
		} catch (Exception e) {
			authInfo = null;
		}
		return authInfo;
	}

	public static TransactionInfo gettransactionInfo(RequestType requestType) {
		TransactionInfo transactionInfo = null;
		try {
			transactionInfo = new TransactionInfo();
			if (requestType.compareTo(RequestType.BFD) == 0)
				transactionInfo.Proc_code("110000");
			else if (requestType.compareTo(RequestType.EO) == 0
					|| requestType.compareTo(RequestType.EB) == 0
					|| requestType.compareTo(RequestType.EBO) == 0)
				transactionInfo.Proc_code("130000");
			else if (requestType.compareTo(RequestType.GO) == 0)
				transactionInfo.Proc_code("140000");
			else if (requestType.compareTo(RequestType.D) == 0)
				transactionInfo.Proc_code("120000");
			else
				transactionInfo.Proc_code("100000");
			transactionInfo.Pos_code("05");
			transactionInfo.AcqId(AquirerID);
			transactionInfo.CA_ID("BFI"
					+ String.format("%01$" + 12 + "s", "000000006000"));
			transactionInfo.CA_TA(strCA_TA);
			//	transactionInfo.CA_Tid(String.format("%1$" + 8 + "s", "public"));  // For public device
			transactionInfo.CA_Tid(String.format("%1$" + 8 + "s", "register"));  // For register device
			transactionInfo.Mcc("6012");
			if (requestType.compareTo(RequestType.D) == 0)
				transactionInfo.Pos_entry_mode("010");
			else
				transactionInfo.Pos_entry_mode("019");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return transactionInfo;
	}

	public static DevInfo getDeviceInfo(String serialnumber, String operatorID,
                                        RequestType requestType, String Merchat_catagory) {
		DevInfo devInfo = null;
		try {
			devInfo = new DevInfo();
			devInfo.di(serialnumber);
			/*if (requestType.compareTo(RequestType.EO) == 0
					|| requestType.compareTo(RequestType.AO) == 0
					|| requestType.compareTo(RequestType.D) == 0) {
				devInfo.fdc("NA");
			} else {
				devInfo.fdc("NC");
			}*/
			devInfo.fdc(Merchat_catagory);
			devInfo.idc("NA");
			devInfo.lot(LocationType.P);
			devInfo.lov("500016");
			devInfo.oi(operatorID);
			devInfo.pip("192.168.49.89");
			devInfo.mi("70:ff:5c:00:bb:63");
		} catch (Exception e) {
			devInfo = null;
			e.printStackTrace();
		}
		return devInfo;
	}

	public  boolean checkIsPrecisionScanner(Context context) {
		boolean isScannerConnected = false;
		boolean isPermissionAvailable = false;

		try {
			UsbManager manager = (UsbManager) context
					.getSystemService(Context.USB_SERVICE);

			PendingIntent mPermissionIntent = PendingIntent.getBroadcast(
					context, 0, new Intent(ACTION_USB_PERMISSION), 0);
			@SuppressWarnings("rawtypes")
			HashMap deviceList = manager.getDeviceList();
			@SuppressWarnings("rawtypes")
			Iterator deviceIterator = deviceList.values().iterator();

			while (deviceIterator.hasNext()) {
				UsbDevice device = (UsbDevice) deviceIterator.next();
				Log.i("", "Device Info --->" + device);
				Log.i("", "Device Vendor Info --->" + device.getVendorId());
				if (device.getVendorId() == 8210
						|| device.getVendorId() == 8457
						|| device.getVendorId() == 11576
						|| device.getVendorId() == 5246) {
					isScannerConnected = true;
					if (!manager.hasPermission(device)) {
						isPermissionAvailable = false;
						manager.requestPermission(device, mPermissionIntent);
						break;
					} else {
						isPermissionAvailable = true;
						break;
					}
				} else {
					isScannerConnected = false;
				}
			}
		} catch (Exception e) {
			isScannerConnected = false;
			isPermissionAvailable = false;
		}
		return isScannerConnected;
	}


	public String getCapturedRequestXML(Context context, String requestType, String env) {
		String captureRequestXML = null;
		try {

			boolean isScannerConnected=checkIsPrecisionScanner(context);
			Log.i(TAG, "isScannerConnected -->"+ isScannerConnected);
			if(isScannerConnected) {
				captureRequestXML = new FormXML().formCaptureRequestXML("20000",
						env, requestType); // capture timeout & environment & AuthType
			}else {

				if(requestType.equalsIgnoreCase("AB")) {
					/*For Mantra Authentication*/
					captureRequestXML = "<PidOptions ver=\"1.0\">" +
							"   <Opts env=\"PP\" fCount=\"1\" fType=\"2\" format=\"0\" iCount=\"0\" iType=\"0\" " +
							"pCount=\"0\" pType=\"0\" pidVer=\"2.0\" posh=\"UNKNOWN\" timeout=\"20000\"/>" +
							"</PidOptions>";
				}else if(requestType.equalsIgnoreCase("EB")) {

					if( !TextUtils.isEmpty(env) && env.equalsIgnoreCase(EKYC_UAT_ENVIRONMENT)) {
						//Mantra Ekyc UAT
						captureRequestXML = "<PidOptions ver=\"1.0\">" +
								"   <Opts env=\"PP\" fCount=\"1\" fType=\"2\" format=\"0\" iCount=\"0\" iType=\"0\" " +
								"pCount=\"0\" pType=\"0\" pidVer=\"2.0\" " +
								"wadh=\"TF/lfPuh1n4ZY1xizYpqikIBm+gv65r51MFNek4uwNw=\" " +
								"posh=\"UNKNOWN\" timeout=\"20000\"/>" +
								"</PidOptions>";
					}else {
						//Mantra Ekyc PROD
						captureRequestXML = "<PidOptions ver=\"1.0\">" +
								"   <Opts env=\"P\" fCount=\"1\" fType=\"2\" format=\"0\" iCount=\"0\" iType=\"0\" " +
								"pCount=\"0\" pType=\"0\" pidVer=\"2.0\" " +
								"wadh=\"TF/lfPuh1n4ZY1xizYpqikIBm+gv65r51MFNek4uwNw=\" " +
								"posh=\"UNKNOWN\" timeout=\"20000\"/>" +
								"</PidOptions>";
					}
				}
			}
			Log.i(TAG, "captureRequestXML -->"+ captureRequestXML);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return captureRequestXML;
	}

}
