package com.bfil.ekyclibrary.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bfil.ekyclibrary.R;
import com.bfil.ekyclibrary.callbacks.EKYCCallBack;
import com.bfil.ekyclibrary.models.AadharData;
import com.bfil.ekyclibrary.models.ConfigurationDto;
import com.bfil.ekyclibrary.models.DataAttributes;
import com.bfil.ekyclibrary.models.LoanResponseInfo;
import com.bfil.ekyclibrary.models.TransactionDto;
import com.bfil.ekyclibrary.utils.CspLog;
import com.bfil.ekyclibrary.utils.TTSHelper;
import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.bfil.uilibrary.dialogs.DialogHelper;
import com.bfil.uilibrary.helpers.AppConstants;
import com.bfil.uilibrary.helpers.AppHelper;
import com.bfil.uilibrary.widgets.textInputLayout.CustomTextInputLayout;
import com.bfil.uilibrary.widgets.textInputLayout.LengthTextWatcher;
import com.google.zxing.client.android.Intents;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.precision.csp.Uidaiauthentication.NPCIConstantValues;
import com.precision.csp.aua.client.AuthInfo;
import com.precision.csp.aua.client.DevInfo;
import com.precision.csp.aua.client.PauaAuth;
import com.precision.csp.aua.client.RequestInfo;
import com.precision.csp.aua.client.TransactionInfo;
import com.precision.csp.authapi.authinfo.Uses;
import com.precision.csp.authapi.common.BiometricType;
import com.precision.csp.authapi.common.RequestType;
import com.precision.csp.authapi.common.ResCon;
import com.precision.csp.authapi.common.UsesFlag;
import com.precision.csp.authapi.pid.data.Bio;
import com.precision.csp.authapi.pid.data.Pid;
import com.precision.rdservice.CaptureResponse;
import com.precision.rdservice.DeviceInfo;
import com.precision.rdservice.RDServiceInfo;
import com.precision.rdservice.SplitXML;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.math.BigInteger;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import static com.bfil.ekyclibrary.constants.EKYCConstants.CAPTURE_REQUEST;
import static com.bfil.ekyclibrary.constants.EKYCConstants.DEVICE_INFO;
import static com.bfil.ekyclibrary.constants.EKYCConstants.PID_DATA;
import static com.bfil.ekyclibrary.constants.EKYCConstants.PID_OPTIONS;
import static com.bfil.ekyclibrary.constants.EKYCConstants.RD_SERVICE_INFO;
import static com.bfil.uilibrary.helpers.AppConstants.CAMERA_PERMISSION_CODE;
import static com.bfil.uilibrary.helpers.AppConstants.permissions;

/**
 * Created by Ali Hussain on 17-03-2018.
 */

public class EKYCActivity extends AppCompatActivity implements EKYCCallBack,
        RadioGroup.OnCheckedChangeListener {

    private static String TAG = EKYCActivity.class.getCanonicalName();
    // TODO:  QR scan response type

    public static final String QR_CODE_TYPE_SECURE = "secure";
    public static final String QR_CODE_TYPE_XML = "xml";
    public static final String QR_CODE_TYPE_UID_NUMBER = "uid_number";
    public static final String QR_CODE_TYPE_UNKNOWN = "unknown";

    private static final int NUMBER_OF_PARAMS_IN_SECURE_QR_CODE = 15;


    public AppHelper appHelper;
    public DialogHelper dialogHelper;
    public TTSHelper ttsHelper;

    static RDServiceInfo rdServiceInfo = null;

    static DeviceInfo deviceInfo = null;
    static CaptureResponse captureResponse = null;

    PauaAuth pauaAuth = null;
    AuthInfo authInfo = null;
    TransactionInfo transactionInfo = null;
    DevInfo devInfo = null;
    Uses uses = null;
    Pid pid = null;
    UsesFlag mec;
    UsesFlag rc;
    List<Bio> lsBio = new ArrayList<Bio>();

    public boolean isOurClient = true;
    String BankShortName = "Indusind";
    public static String reqxml = "";
    protected String strAmt = "0";

    protected TransactionDto transactionDto;
    protected ConfigurationDto configurationDto;
    protected com.precision.csp.Uidaiauthentication.SplitXML finalResponse1;
    protected LoanResponseInfo loanResponseInfo;

    public ConfigurationDto getConfigurationDto() {
        return configurationDto;
    }

    public void setConfigurationDto(ConfigurationDto configurationDto) {
        this.configurationDto = configurationDto;
    }

    public TransactionDto getTransactionDto() {
        return transactionDto;
    }

    public void setTransactionDto(TransactionDto transactionDto) {
        this.transactionDto = transactionDto;
    }

    public com.precision.csp.Uidaiauthentication.SplitXML getFinalResponse1() {
        return finalResponse1;
    }

    public void setFinalResponse1(com.precision.csp.Uidaiauthentication.SplitXML finalResponse1) {
        this.finalResponse1 = finalResponse1;
    }

    public LoanResponseInfo getLoanResponseInfo() {
        return loanResponseInfo;
    }

    public void setLoanResponseInfo(LoanResponseInfo loanResponseInfo) {
        this.loanResponseInfo = loanResponseInfo;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
//                WindowManager.LayoutParams.FLAG_SECURE);

        try {
            appHelper = new AppHelper(this);
            dialogHelper = appHelper.getDialogHelper();
            ttsHelper = new TTSHelper(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        try {
            if (requestCode == CAMERA_PERMISSION_CODE) {
                if (grantResults.length > 0) {
                    boolean cameraPermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean readExternalFile = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (cameraPermission && readExternalFile) {
                        try {
                            Toast.makeText(EKYCActivity.this, "Permissions granted",
                                    Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(this, "Please enable camera & storage permissions from settings",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void scanQRCode() {
        try {
            if ((ContextCompat.checkSelfPermission(this,
                    Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)) {
                Log.e(TAG, "Requesting for Permissions -->");
                appHelper.askForPermission(this, permissions, CAMERA_PERMISSION_CODE);
            } else {
                IntentIntegrator integrator = new IntentIntegrator(this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                integrator.setPrompt("Place QR Code inside rectangle box");
                integrator.setBeepEnabled(true);
                integrator.setTimeout(30000);
                integrator.setCameraId(0);  // Use a specific camera of the device
                integrator.setCaptureActivity(SmallCaptureActivity.class);
                integrator.initiateScan();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            Log.i(TAG, "inside onActivityResult requestCode ------>" + requestCode + "::resultCode::" + resultCode);
            if (requestCode == 0 || requestCode == 1) {
                biometricResponse(requestCode, data);
            }  else if (requestCode == IntentIntegrator.REQUEST_CODE && data != null) {
                //retrieve scan result
                boolean isValid = data.getBooleanExtra(Intents.Scan.TIMEOUT, false);
                IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
                if (isValid){
                    qrScanTimeOut();
                    Toast.makeText(getApplicationContext(),
                            "Scan Time out", Toast.LENGTH_LONG)
                            .show();
                } else {
                    if (scanningResult != null) {
                        //we have a result
                        String scanContent = scanningResult.getContents();
                        String scanFormat = scanningResult.getFormatName();
                        // process received data
                        if (scanContent != null && !scanContent.isEmpty()) {
                            processScannedData(scanContent);
                        } else {
//                        qrAttempt = qrAttempt + 1;
                            Toast.makeText(getApplicationContext(),
                                    "Scan Cancelled", Toast.LENGTH_SHORT)
                                    .show();
//                        if (qrAttempt == 3){
//                            qrFailCallback();
//                        }
                        }
                    } else {
//                    qrAttempt = qrAttempt + 1;
                        Toast.makeText(getApplicationContext(),
                                "No scan data received!", Toast.LENGTH_SHORT)
                                .show();
                        qrScanTimeOut();
//                    if (qrAttempt == 3){
//                        qrFailCallback();
//                    }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private void processScannedData(String scanData) {
//        Log.d("scanData", scanData);
//        XmlPullParserFactory pullParserFactory;
//
//        try {
//            // init the parserfactory
//            pullParserFactory = XmlPullParserFactory.newInstance();
//            // get the parser
//            XmlPullParser parser = pullParserFactory.newPullParser();
//
//            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
//            parser.setInput(new StringReader(scanData));
//
//            AadharData aadharData = new AadharData();
//
//            // parse the XML
//            int eventType = parser.getEventType();
//            while (eventType != XmlPullParser.END_DOCUMENT) {
//                if (eventType == XmlPullParser.START_DOCUMENT) {
//                    Log.d("QRScannerAc", "Start document");
//                } else if (eventType == XmlPullParser.START_TAG && DataAttributes.AADHAAR_DATA_TAG.equals(parser.getName())) {
//                    // extract data from tag [ AADHAAR DEMOGRAPHICS DETAILS ]
//                    //uid
//                    // setting aadhaar flag as ture
//                    aadharData.setbAaadhaar(true);
//
//                    aadharData.setUID(parser.getAttributeValue(null, DataAttributes.AADHAR_UID_ATTR));
//                    //name
//                    aadharData.setName(parser.getAttributeValue(null, DataAttributes.AADHAR_NAME_ATTR));
//                    //gender
//                    aadharData.setGender(parser.getAttributeValue(null, DataAttributes.AADHAR_GENDER_ATTR));
//                    // year of birth
//                    aadharData.setYOB(parser.getAttributeValue(null, DataAttributes.AADHAR_YOB_ATTR));
//                    // DOB
//                    aadharData.setDOB(parser.getAttributeValue(null, DataAttributes.AADHAR_DOB_ATTR));
//                    // care of
//                    aadharData.setCareOf(parser.getAttributeValue(null, DataAttributes.AADHAR_CO_ATTR));
//                    // House No
//                    aadharData.setHouseNo(parser.getAttributeValue(null, DataAttributes.AADHAR_HOUSE_ATTR));
//                    // village Tehsil
//                    aadharData.setVillage(parser.getAttributeValue(null, DataAttributes.AADHAR_VTC_ATTR));
//                    // Post Office
//                    aadharData.setPostOffice(parser.getAttributeValue(null, DataAttributes.AADHAR_PO_ATTR));
//                    // district
//                    aadharData.setDistrict(parser.getAttributeValue(null, DataAttributes.AADHAR_DIST_ATTR));
//                    // state
//                    aadharData.setState(parser.getAttributeValue(null, DataAttributes.AADHAR_STATE_ATTR));
//                    // Post Code
//                    aadharData.setPinCode(parser.getAttributeValue(null, DataAttributes.AADHAR_PC_ATTR));
//                    //street
//                    aadharData.setStreet(parser.getAttributeValue(null, DataAttributes.AADHAR_STREET_ATTR));
//                    //location
//                    aadharData.setLoc(parser.getAttributeValue(null, DataAttributes.AADHAR_LOC_ATTR));
//                    // sub district
//                    aadharData.setSubDist(parser.getAttributeValue(null, DataAttributes.AADHAR_SUBDIST_ATTR));
//                    //landmark
//                    aadharData.setLandmark(parser.getAttributeValue(null, DataAttributes.AADHAR_LM_ATTR));
//
//                    String strFullAddress = parser.getAttributeValue(null, DataAttributes.AADHAR_HOUSE_ATTR) + ","
//                            + parser.getAttributeValue(null, DataAttributes.AADHAR_VTC_ATTR) + ","
//                            + parser.getAttributeValue(null, DataAttributes.AADHAR_LOC_ATTR) + ","
//                            + parser.getAttributeValue(null, DataAttributes.AADHAR_LM_ATTR) + ","
//                            + parser.getAttributeValue(null, DataAttributes.AADHAR_STREET_ATTR) + ","
//                            + parser.getAttributeValue(null, DataAttributes.AADHAR_DIST_ATTR) + ","
//                            + parser.getAttributeValue(null, DataAttributes.AADHAR_STATE_ATTR) + ","
//                            + parser.getAttributeValue(null, DataAttributes.AADHAR_PC_ATTR);
//                    aadharData.setAddress(strFullAddress);
//
//                } else if (eventType == XmlPullParser.START_TAG && DataAttributes.VID_AADHAAR_DATA_TAG.equals(parser.getName())) {
//                    // extract data from tag [ VID DEMOGRAPHICS DETAILS ]
//                    // setting VID flag as ture
//                    aadharData.setbVID(true);
//                    //uid
//                    aadharData.setUID(parser.getAttributeValue(null, DataAttributes.VID_AADHAR_UID_ATTR));
//                    //name
//                    aadharData.setName(parser.getAttributeValue(null, DataAttributes.VID_AADHAR_NAME_ATTR));
//                    //gender
//                    aadharData.setGender(parser.getAttributeValue(null, DataAttributes.VID_AADHAR_GENDER_ATTR));
//                    // DOB
//                    aadharData.setDOB(parser.getAttributeValue(null, DataAttributes.VID_AADHAR_DOB_ATTR));
//                    //Address
//                    aadharData.setAddress(parser.getAttributeValue(null, DataAttributes.VID_AADHAR_ADDRESS_ATTR));
//                    //Mobile number
//                    aadharData.setMobile(parser.getAttributeValue(null, DataAttributes.VID_AADHAR_MOBILE_ATTR));
//
//                } else if (eventType == XmlPullParser.START_TAG && DataAttributes.VID_AADHAAR_PHOTO_DATA_TAG.equals(parser.getName())) {
//                    // extract data from tag [ VID DEMOGRAPHICS DETAILS WITH PHOTO]
//
//                    // setting VID flag as ture
//                    aadharData.setbVID(true);
//                    //uid
//                    aadharData.setUID(parser.getAttributeValue(null, DataAttributes.VID_AADHAR_UID_ATTR));
//                    //name
//                    aadharData.setName(parser.getAttributeValue(null, DataAttributes.VID_AADHAR_NAME_ATTR));
//                    //gender
//                    aadharData.setGender(parser.getAttributeValue(null, DataAttributes.VID_AADHAR_GENDER_ATTR));
//                    // DOB
//                    aadharData.setDOB(parser.getAttributeValue(null, DataAttributes.VID_AADHAR_DOB_ATTR));
//                    //Address
//                    aadharData.setAddress(parser.getAttributeValue(null, DataAttributes.VID_AADHAR_ADDRESS_ATTR));
//                    //Mobile number
//                    aadharData.setMobile(parser.getAttributeValue(null, DataAttributes.VID_AADHAR_MOBILE_ATTR));
//
//                } else if (eventType == XmlPullParser.END_TAG) {
//                    Log.d("EndTag", "End tag " + parser.getName());
//
//                } else if (eventType == XmlPullParser.TEXT) {
//                    Log.d("Text", "Text " + parser.getText());
//
//                }
//                // update eventType
//                eventType = parser.next();
//            }
//            Log.d("aadhaarData", "" + aadharData.getName());
//
//            qrScanCallback(aadharData);
//
//        } catch (XmlPullParserException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    private void processScannedData(String scanData) {
        Log.d("scanData", scanData);
        XmlPullParserFactory pullParserFactory;
        String type;

        if (scanData.matches("[0-9]*")) {
            type = QR_CODE_TYPE_SECURE;
            byte[] msgInBytes = null;
            try {
                msgInBytes = decompressByteArray(new BigInteger(scanData).toByteArray());
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            if (msgInBytes != null) {
                AadharData aadharData = new AadharData();
                int[] delimiters = locateDelimiters(msgInBytes);
                String referenceId = getValueInRange(msgInBytes, delimiters[0] + 1, delimiters[1]);

                aadharData.setbAaadhaar(true);

                aadharData.setUID(referenceId.substring(0, 4));   //name
                aadharData.setName(getValueInRange(msgInBytes, delimiters[1] + 1, delimiters[2]));
                //gender
                aadharData.setGender(getValueInRange(msgInBytes, delimiters[3] + 1, delimiters[4]));
                // DOB
                Log.e("DOB ", getValueInRange(msgInBytes, delimiters[2] + 1, delimiters[3])+"");
                String dob =getValueInRange(msgInBytes, delimiters[2] + 1, delimiters[3]);

//                dob = appHelper.convertDateToGivenDateFormat(dob, AppConstants.DATE_FORMAT_DD_MM_YYYY_WITH_SLASH,
//                        AppConstants.DATE_FORMAT_DD_MM_YYYY);

                Log.e("DOB after format  ", dob);

                aadharData.setDOB(dob);

                // year of birth
                aadharData.setYOB(dob.substring(0, 4));

                // care of
                aadharData.setCareOf(getValueInRange(msgInBytes, delimiters[4] + 1, delimiters[5]));
                // House No
                aadharData.setHouseNo(getValueInRange(msgInBytes, delimiters[7] + 1, delimiters[8]));
                // village Tehsil
                aadharData.setVillage(getValueInRange(msgInBytes, delimiters[14] + 1, delimiters[15]));
                // Post Office
                aadharData.setPostOffice(getValueInRange(msgInBytes, delimiters[10] + 1, delimiters[11]));
                // district
                aadharData.setDistrict(getValueInRange(msgInBytes, delimiters[5] + 1, delimiters[6]));
                // state
                aadharData.setState(getValueInRange(msgInBytes, delimiters[11] + 1, delimiters[12]));
                // Post Code
                aadharData.setPinCode(getValueInRange(msgInBytes, delimiters[9] + 1, delimiters[10]));
                //street
                aadharData.setStreet(getValueInRange(msgInBytes, delimiters[12] + 1, delimiters[13]));
                //location
                aadharData.setLoc(getValueInRange(msgInBytes, delimiters[8] + 1, delimiters[9]));
                // sub district
                aadharData.setSubDist(getValueInRange(msgInBytes, delimiters[13] + 1, delimiters[14]));
                //landmark
                aadharData.setLandmark(getValueInRange(msgInBytes, delimiters[6] + 1, delimiters[7]));


                StringBuilder stringBuilder = new StringBuilder();
                if (!TextUtils.isEmpty(aadharData.getHouseNo())) {
                    stringBuilder.append(aadharData.getHouseNo());
                    stringBuilder.append(",");
                }
                if (!TextUtils.isEmpty(aadharData.getStreet())) {
                    stringBuilder.append(aadharData.getStreet());
                    stringBuilder.append(",");
                }
               /* if (!TextUtils.isEmpty(aadharData.getLandmark())) {
                    stringBuilder.append(aadharData.getLandmark());
                    stringBuilder.append(",");
                }*/
               /* if (!TextUtils.isEmpty(aadharData.getLoc())) {
                    stringBuilder.append(aadharData.getLoc());
                    stringBuilder.append(",");
                }
                if (!TextUtils.isEmpty(aadharData.getPostOffice())) {
                    stringBuilder.append(aadharData.getPostOffice());
                } else if (!TextUtils.isEmpty(aadharData.getVillage())) {
                    stringBuilder.append(aadharData.getVillage());
                }*/

                aadharData.setAddress(stringBuilder.toString());
                qrScanCallback(aadharData);

                //   Log.e("tag scan QR ", "uid  "+uid+" name "+name+" dob "+dob);

              //  dob = appHelper.convertDateToGivenDateFormat(dob, AppConstants.DATE_FORMAT_DD_MM_YYYY_WITH_SLASH,
                      //  AppConstants.DATE_FORMAT_DD_MM_YYYY);
               // yob = dob.substring(0, 4);
//                gender = getValueInRange(msgInBytes, delimiters[3] + 1, delimiters[4]);
//                co = getValueInRange(msgInBytes, delimiters[4] + 1, delimiters[5]);
//                dist = getValueInRange(msgInBytes, delimiters[5] + 1, delimiters[6]);
//                lm = getValueInRange(msgInBytes, delimiters[6] + 1, delimiters[7]);
//                house = getValueInRange(msgInBytes, delimiters[7] + 1, delimiters[8]);
//                loc = getValueInRange(msgInBytes, delimiters[8] + 1, delimiters[9]);
//                pc = getValueInRange(msgInBytes, delimiters[9] + 1, delimiters[10]);
//                po = getValueInRange(msgInBytes, delimiters[10] + 1, delimiters[11]);
//                state = getValueInRange(msgInBytes, delimiters[11] + 1, delimiters[12]);
//                street = getValueInRange(msgInBytes, delimiters[12] + 1, delimiters[13]);
//                subdist = getValueInRange(msgInBytes, delimiters[13] + 1, delimiters[14]);
//                vtc = getValueInRange(msgInBytes, delimiters[14] + 1, delimiters[15]);

            }
        } else {
            try {
                qrScanOriginalData(scanData);
                // init the parserfactory
                pullParserFactory = XmlPullParserFactory.newInstance();
                // get the parser
                XmlPullParser parser = pullParserFactory.newPullParser();

                parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                parser.setInput(new StringReader(scanData));

                AadharData aadharData = new AadharData();

                // parse the XML
                int eventType = parser.getEventType();
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_DOCUMENT) {
                        Log.d("QRScannerAc", "Start document");
                    } else if (eventType == XmlPullParser.START_TAG && DataAttributes.AADHAAR_DATA_TAG.equals(parser.getName())) {
                        // extract data from tag [ AADHAAR DEMOGRAPHICS DETAILS ]
                        //uid
                        // setting aadhaar flag as ture
                        aadharData.setbAaadhaar(true);

                        aadharData.setUID(parser.getAttributeValue(null, DataAttributes.AADHAR_UID_ATTR));
                        //name
                        aadharData.setName(parser.getAttributeValue(null, DataAttributes.AADHAR_NAME_ATTR));
                        //gender
                        aadharData.setGender(parser.getAttributeValue(null, DataAttributes.AADHAR_GENDER_ATTR));
                        // year of birth
                        aadharData.setYOB(parser.getAttributeValue(null, DataAttributes.AADHAR_YOB_ATTR));
                        // DOB
                        aadharData.setDOB(parser.getAttributeValue(null, DataAttributes.AADHAR_DOB_ATTR));
                        String dob = parser.getAttributeValue(null, DataAttributes.AADHAR_DOB_ATTR);
                        if (!TextUtils.isEmpty(dob) && dob.contains("/")) {
                            dob = "01-01-" + aadharData.getYOB();
                        }

                        aadharData.setDOB(dob);
                        // care of
                        aadharData.setCareOf(parser.getAttributeValue(null, DataAttributes.AADHAR_CO_ATTR));
                        // House No
                        aadharData.setHouseNo(parser.getAttributeValue(null, DataAttributes.AADHAR_HOUSE_ATTR));
                        // village Tehsil
                        aadharData.setVillage(parser.getAttributeValue(null, DataAttributes.AADHAR_VTC_ATTR));
                        // Post Office
                        aadharData.setPostOffice(parser.getAttributeValue(null, DataAttributes.AADHAR_PO_ATTR));
                        // district
                        aadharData.setDistrict(parser.getAttributeValue(null, DataAttributes.AADHAR_DIST_ATTR));
                        // state
                        aadharData.setState(parser.getAttributeValue(null, DataAttributes.AADHAR_STATE_ATTR));
                        // Post Code
                        aadharData.setPinCode(parser.getAttributeValue(null, DataAttributes.AADHAR_PC_ATTR));
                        //street
                        aadharData.setStreet(parser.getAttributeValue(null, DataAttributes.AADHAR_STREET_ATTR));
                        //location
                        aadharData.setLoc(parser.getAttributeValue(null, DataAttributes.AADHAR_LOC_ATTR));
                        // sub district
                        aadharData.setSubDist(parser.getAttributeValue(null, DataAttributes.AADHAR_SUBDIST_ATTR));
                        //landmark
                        aadharData.setLandmark(parser.getAttributeValue(null, DataAttributes.AADHAR_LM_ATTR));

                        StringBuilder stringBuilder = new StringBuilder();
                        if (!TextUtils.isEmpty(aadharData.getHouseNo())) {
                            stringBuilder.append(aadharData.getHouseNo());
                            stringBuilder.append(",");
                        }
                        if (!TextUtils.isEmpty(aadharData.getStreet())) {
                            stringBuilder.append(aadharData.getStreet());
                            stringBuilder.append(",");
                        }
                        if (!TextUtils.isEmpty(aadharData.getLandmark())) {
                            stringBuilder.append(aadharData.getLandmark());
                            stringBuilder.append(",");
                        }
                        if (!TextUtils.isEmpty(aadharData.getLoc())) {
                            stringBuilder.append(aadharData.getLoc());
                            stringBuilder.append(",");
                        }
                        if (!TextUtils.isEmpty(aadharData.getPostOffice())) {
                            stringBuilder.append(aadharData.getPostOffice());
                        } else if (!TextUtils.isEmpty(aadharData.getVillage())) {
                            stringBuilder.append(aadharData.getVillage());
                        }
//                    String strFullAddress = parser.getAttributeValue(null, DataAttributes.AADHAR_HOUSE_ATTR) + ","
//                            + parser.getAttributeValue(null, DataAttributes.AADHAR_VTC_ATTR) + ","
//                            + parser.getAttributeValue(null, DataAttributes.AADHAR_LOC_ATTR) + ","
//                            + parser.getAttributeValue(null, DataAttributes.AADHAR_LM_ATTR) + ","
//                            + parser.getAttributeValue(null, DataAttributes.AADHAR_STREET_ATTR) + ","
//                            + parser.getAttributeValue(null, DataAttributes.AADHAR_DIST_ATTR) + ","
//                            + parser.getAttributeValue(null, DataAttributes.AADHAR_STATE_ATTR) + ","
//                            + parser.getAttributeValue(null, DataAttributes.AADHAR_PC_ATTR);
                        aadharData.setAddress(stringBuilder.toString());

                    } else if (eventType == XmlPullParser.START_TAG && DataAttributes.VID_AADHAAR_DATA_TAG.equals(parser.getName())) {
                        // extract data from tag [ VID DEMOGRAPHICS DETAILS ]
                        // setting VID flag as ture
                        aadharData.setbVID(true);
                        //uid
                        aadharData.setUID(parser.getAttributeValue(null, DataAttributes.VID_AADHAR_UID_ATTR));
                        //name
                        aadharData.setName(parser.getAttributeValue(null, DataAttributes.VID_AADHAR_NAME_ATTR));
                        //gender
                        aadharData.setGender(parser.getAttributeValue(null, DataAttributes.VID_AADHAR_GENDER_ATTR));
                        // DOB
                        String dob = parser.getAttributeValue(null, DataAttributes.VID_AADHAR_DOB_ATTR);
                        if (!TextUtils.isEmpty(dob) && dob.contains("/")) {
                            dob = appHelper.convertDateToGivenDateFormat(dob, AppConstants.DATE_FORMAT_DD_MM_YYYY_WITH_SLASH,
                                    AppConstants.DATE_FORMAT_DD_MM_YYYY);
                        }

                        aadharData.setDOB(dob);
                        //Address
                        aadharData.setAddress(parser.getAttributeValue(null, DataAttributes.VID_AADHAR_ADDRESS_ATTR));
                        //Mobile number
                        aadharData.setMobile(parser.getAttributeValue(null, DataAttributes.VID_AADHAR_MOBILE_ATTR));

                    } else if (eventType == XmlPullParser.START_TAG && DataAttributes.VID_AADHAAR_PHOTO_DATA_TAG.equals(parser.getName())) {
                        // extract data from tag [ VID DEMOGRAPHICS DETAILS WITH PHOTO]

                        // setting VID flag as ture
                        aadharData.setbVID(true);
                        //uid
                        aadharData.setUID(parser.getAttributeValue(null, DataAttributes.VID_AADHAR_UID_ATTR));
                        //name
                        aadharData.setName(parser.getAttributeValue(null, DataAttributes.VID_AADHAR_NAME_ATTR));
                        //gender
                        aadharData.setGender(parser.getAttributeValue(null, DataAttributes.VID_AADHAR_GENDER_ATTR));
                        // DOB
                        aadharData.setDOB(parser.getAttributeValue(null, DataAttributes.VID_AADHAR_DOB_ATTR));
                        String dob = parser.getAttributeValue(null, DataAttributes.VID_AADHAR_DOB_ATTR);
                        if (!TextUtils.isEmpty(dob) && dob.contains("/")) {
                            dob = appHelper.convertDateToGivenDateFormat(dob, AppConstants.DATE_FORMAT_DD_MM_YYYY_WITH_SLASH,
                                    AppConstants.DATE_FORMAT_DD_MM_YYYY);
                        }

                        aadharData.setDOB(dob);
                        //Address
                        aadharData.setAddress(parser.getAttributeValue(null, DataAttributes.VID_AADHAR_ADDRESS_ATTR));
                        //Mobile number
                        aadharData.setMobile(parser.getAttributeValue(null, DataAttributes.VID_AADHAR_MOBILE_ATTR));

                    } else if (eventType == XmlPullParser.END_TAG) {
                        Log.d("EndTag", "End tag " + parser.getName());

                    } else if (eventType == XmlPullParser.TEXT) {
                        Log.d("Text", "Text " + parser.getText());

                    }
                    // update eventType
                    eventType = parser.next();
                }
                Log.d("aadhaarData ==> DOB", "" + aadharData.getDOB());

                qrScanCallback(aadharData);

            } catch (XmlPullParserException e) {
                qrScanTimeOut();
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    private String formatDate(String rawDateString, String[] possibleFormats) {
        if (rawDateString.equals("")) {
            return "";
        }
        SimpleDateFormat toFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        ParseException parseException = null;
        for (String fromFormatPattern : possibleFormats) {
            try {
                SimpleDateFormat fromFormat = new SimpleDateFormat(fromFormatPattern);
                date = fromFormat.parse(rawDateString);
                break;
            } catch (ParseException e) {
                parseException = e;
            }
        }
        if (date != null) {
            return toFormat.format(date);
        } else if (parseException != null) {
            System.err.println("Expected dob to be in dd/mm/yyyy or yyyy-mm-dd format, got " + rawDateString);
            return rawDateString;
        } else {
            throw new AssertionError("This code is unreachable");
        }
    }

    private static byte[] decompressByteArray(byte[] bytes) throws IOException {
        java.io.ByteArrayInputStream bytein = new ByteArrayInputStream(bytes);
        java.util.zip.GZIPInputStream gzin = new GZIPInputStream(bytein);
        java.io.ByteArrayOutputStream byteout = new ByteArrayOutputStream();

        int res = 0;
        byte buf[] = new byte[1024];
        while (res >= 0) {
            res = gzin.read(buf, 0, buf.length);
            if (res > 0) {
                byteout.write(buf, 0, res);
            }
        }
        return byteout.toByteArray();
    }


    private static int[] locateDelimiters(byte[] msgInBytes) {
        int[] delimiters = new int[NUMBER_OF_PARAMS_IN_SECURE_QR_CODE + 1];
        int index = 0;
        int delimiterIndex;
        for (int i = 0; i <= NUMBER_OF_PARAMS_IN_SECURE_QR_CODE; i++) {
            delimiterIndex = getNextDelimiterIndex(msgInBytes, index);
            delimiters[i] = delimiterIndex;
            index = delimiterIndex + 1;
        }
        return delimiters;
    }

    private static int getNextDelimiterIndex(byte[] msgInBytes, int index) {
        int i = index;
        for (; i < msgInBytes.length; i++) {
            if (msgInBytes[i] == -1) {
                break;
            }
        }
        return i;
    }

    private static String getValueInRange(byte[] msgInBytes, int start, int end) {
        return new String(Arrays.copyOfRange(msgInBytes, start, end), Charset.forName("ISO-8859-1"));
    }

    @Override
    protected void onDestroy() {
        try {
            ttsHelper.shutdownTTS();
            isOnCreateCalled = false;
            //clearMobileCameraImage();
            super.onDestroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void biometricResponse(int requestCode, Intent data) {
        try {
            if (requestCode == 0) {
                String dnc = data.getStringExtra("DNC"); //Device Not Connected
                String dnr = data.getStringExtra("DNR"); // Device Not Registered
                String strDeviceInfo = data.getStringExtra(DEVICE_INFO);
                String strRDServiceInfo = data.getStringExtra(RD_SERVICE_INFO);

                CspLog.WriteLog(TAG + "," + "inside onActivityResult dnc -->" + dnc + "::dnr::" + dnr + "::strDeviceInfo::" + strDeviceInfo + "::strRDServiceInfo::" + strRDServiceInfo);
                if (dnc != null) {
                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Please connect the Scanner");
                } else if (dnr != null) {
                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR, "Device is not registered");

                } else if (strRDServiceInfo != null && !strRDServiceInfo.isEmpty() && !strRDServiceInfo.equalsIgnoreCase("null")) {
                    rdServiceInfo = new SplitXML().SplitRDServiceInfo(strRDServiceInfo);
                    if (rdServiceInfo != null) {
                        if (rdServiceInfo.status.equalsIgnoreCase("Ready")) {
                            if (strDeviceInfo != null && !strDeviceInfo.isEmpty()) {
                                deviceInfo = new SplitXML().SplitDeviceInfo(strDeviceInfo);
                                if (deviceInfo == null) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            CspLog.WriteLog("Unable to split device information");
                                        }
                                    });

                                } else if (deviceInfo.dc == null || deviceInfo.dc.isEmpty() || deviceInfo.dpId == null
                                        || deviceInfo.dpId.isEmpty() || deviceInfo.mc == null || deviceInfo.mc.isEmpty()
                                        || deviceInfo.mi == null || deviceInfo.mi.isEmpty() || deviceInfo.rdsId == null
                                        || deviceInfo.rdsId.isEmpty() || deviceInfo.rdsVer == null
                                        || deviceInfo.rdsVer.isEmpty()) {
                                    CspLog.WriteLog("Invalid Device Information");

                                } else {
                                    String captureRequestXML = "";
                                    String requestType = "";
                                    try {
                                        requestType = "EB";
                                        /*if(isOurClient){
                                            requestType="EB";
                                        }else{
                                            requestType="AB";
                                        }*/
//                                        captureRequestXML = new FormXML().formCaptureRequestXML("20000", configurationDto.getEnv(), ((RequestType) transactionDto.getAuthTransType()).value()); // capture timeout & environment & AuthType
                                        captureRequestXML = NPCIConstantValues.getInstance().getCapturedRequestXML(this,
                                                String.valueOf(transactionDto.getAuthTransType()), configurationDto.getEnv());

                                        if (captureRequestXML != null && !captureRequestXML.isEmpty()) {
                                            Intent sendIntent = new Intent();
                                            sendIntent.setAction("in.gov.uidai.rdservice.fp.CAPTURE");
                                            sendIntent.putExtra(PID_OPTIONS, captureRequestXML);
                                            startActivityForResult(sendIntent, CAPTURE_REQUEST);
                                        }
                                    } catch (Exception e) {
                                        CspLog.WriteLog("Exception in Forming Capture XML or Calling Capture functionaltiy");
                                    }
                                }
                            } else {
                                CspLog.WriteLog("Device information is empty");
                            }
                        }
                    } else {
                        CspLog.WriteLog("Unable to get RDservice Information");
                    }
                } else {
                    CspLog.WriteLog("RDserviceInfo=null");
                }
            } else if (requestCode == 1) {
                String CapturedFingerprintXML = data.getStringExtra(PID_DATA);
                captureResponse = new SplitXML().SplitCaptureResponse(CapturedFingerprintXML);
                CspLog.WriteLog(TAG + "," + "CaptureResposne -->" + CapturedFingerprintXML);
                if (captureResponse == null) {
                    CspLog.WriteLog("Capture response is empty");

                } else {
                    if (!appHelper.isNetworkAvailable()) {
                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                "Please check your internet connection and try again");
                        CspLog.WriteLog("Connect to server failed : please check network connectivity");

                    } else {
                        if (captureResponse.errCode.equalsIgnoreCase("0")) {
                            int imageQuality = 0;
                            try {
                                imageQuality = Integer.valueOf(captureResponse.qScore);
                            } catch (NumberFormatException nfe) {
                                nfe.printStackTrace();
                            }
                            if (imageQuality >= 60) {
                                ttsHelper.speak(this, R.string.str_captureSuccess);
                                // TODO: OLD CODE
//                                new FormXml().execute();

                                // TODO: NEW CODE
                                biometricCallBack(deviceInfo,captureResponse,transactionDto.getAadharNo());

                            } else {
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                        "Bad Image Quality: " + captureResponse.qScore);
                                CspLog.WriteLog("Bad Image Quality: " + captureResponse.qScore);
                                ttsHelper.speak(this, R.string.str_BadImageQuality);
                            }

                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR, captureResponse.errInfo);
                            CspLog.WriteLog("Capture Error code: " + captureResponse.errCode + " Captrue Error Info : " + captureResponse.errInfo);
                            ttsHelper.speak(this, R.string.str_captureFailed);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

    }

    private class FormXml extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            try {
                CspLog.WriteLog(TAG + "," + "FormXml onPreExecute -->");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                sendRequest();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(Void... params) {
            return "sendRequest";
        }

        private void sendRequest() {
            PauaAuth.mContext = EKYCActivity.this;
            CspLog.WriteLog(TAG + "," + "sendRequest isOurClient -->" + isOurClient);
            authBiometric();

        }

        @SuppressLint("SdCardPath")
        private void authBiometric() {
            try {
                ConfigurationDto configurationDto = getConfigurationDto();
                TransactionDto transactionDto = getTransactionDto();
                CspLog.WriteLog(TAG + "," + "authBiometric strAadharID -->" + transactionDto.getAadharNo());

                authInfo = NPCIConstantValues.getAuthInfo(transactionDto.getAadharNo(), (RequestType) transactionDto.getAuthTransType());
                transactionInfo = NPCIConstantValues.gettransactionInfo((RequestType) transactionDto.getAuthTransType());

                uses = new Uses();
                uses.bio(UsesFlag.Y);
                uses.bt(BiometricType.FMR);

                pid = new Pid();
                if (transactionDto.getAuthTransType() == RequestType.EB) {
                    rc = UsesFlag.Y;

                    ResCon resCon = new ResCon();
                    resCon.mec(mec);
                    resCon.rc(rc);

                    pid.rescon(resCon);

                }
                pid.bios(lsBio);

                devInfo = NPCIConstantValues.getDeviceInfo(
                        deviceInfo.serialnumber,
                        transactionDto.getMerchantId(),
                        (RequestType) transactionDto.getAuthTransType(), transactionDto.getMerchantCategory());

                String imei = appHelper.getIMEI();
                System.out.println("IMEI no: " + imei);

                //String name = "NHAI";
                /*RequestInfo requestInfo = new RequestInfo(aadhar, amount,
                        name, mobileNO, "English", merchantId, "d",imei,BankShortName,null,merchantAccNo,"1","","cid",Merchat_catagory);*/

                if (strAmt != null && !strAmt.equals("0")) {
                    strAmt += ".00";
                }

                RequestInfo requestInfo = new RequestInfo(transactionDto.getAadharNo(), transactionDto.getAmt(),
                        transactionDto.getUserName(), transactionDto.getMobileNo(), transactionDto.getLanguage(),
                        transactionDto.getMerchantId(), transactionDto.getTransactionType(), imei,
                        transactionDto.getBankShortName(), transactionDto.getGuardianName(),
                        transactionDto.getMerchantAccNo(), transactionDto.getScreeNo(),
                        transactionDto.getDeviceId(), transactionDto.getClientId(), transactionDto.getMerchantCategory());

                /*RequestInfo requestInfo = new RequestInfo(strAadharID, strAmt,
                        name,"7032337999" , "English", merchantId, "d",imei,BankShortName,strGuardianName,merchantAccNo,"6","21", clientId, "A");*/

                pauaAuth = new PauaAuth();
                reqxml = pauaAuth.AuaAuthRequest(authInfo, transactionInfo,
                        uses, deviceInfo, pid, null, requestInfo, captureResponse, configurationDto.getAquirerID());

                CspLog.WriteLog(TAG + "," + "########## EKYC REQUEST INFO ############");
                CspLog.WriteLog(TAG + "," + "Aadhar No : " + transactionDto.getAadharNo());
                CspLog.WriteLog(TAG + "," + "Client ID : " + transactionDto.getClientId());
                CspLog.WriteLog(TAG + "," + "Amount : " + transactionDto.getAmt());
                CspLog.WriteLog(TAG + "," + "Name : " + transactionDto.getUserName());
                CspLog.WriteLog(TAG + "," + "Mobile Number : " + transactionDto.getMobileNo());
                CspLog.WriteLog(TAG + "," + "Merchant ID : " + transactionDto.getMerchantId());
                CspLog.WriteLog(TAG + "," + "Transaction type : " + transactionDto.getTransactionType());
                CspLog.WriteLog(TAG + "," + "IMEI NO : " + transactionDto.getImeiNumber());
                CspLog.WriteLog(TAG + "," + "Bank Name : " + transactionDto.getBankShortName());
                CspLog.WriteLog(TAG + "," + "Transaction ID : " + pauaAuth.getTransactionID());
                CspLog.WriteLog(TAG + "," + "############################################");

                String fulxml = "&Requestxml=" + reqxml + "&proposaldata= ";

                if (transactionDto.getTransactionType().equalsIgnoreCase("si")) {
                    if (EKYCActivity.this.loanResponseInfo != null) {
                        fulxml = "&Requestxml=" + EKYCActivity.reqxml + "&startdate=" + EKYCActivity.this.loanResponseInfo.getStartdate() + "&enddate=" + EKYCActivity.this.loanResponseInfo.getEndDate() + "&frequency=" + EKYCActivity.this.loanResponseInfo.getFrequency();
                    } else {
                        fulxml = "&Requestxml=" + EKYCActivity.reqxml + "&startdate=null&enddate=null&frequency=null";
                    }
                } else if (transactionDto.getTransactionType().equalsIgnoreCase("cc")) {
                    fulxml = "&Requestxml=" + EKYCActivity.reqxml + "&proposaldata=";
                } else {
                    fulxml = "&Requestxml=" + EKYCActivity.reqxml;
                }


                if (appHelper.isNetworkAvailable()) {
                    new HtppAsyncAuth().execute(fulxml, "AUTH");
                } else {
                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                            "Please check your internet connection and try again");
                    ttsHelper.speak(EKYCActivity.this, R.string.str_NetworkUnavailable);
                    CspLog.WriteLog("Network unavailable");
                    ////CSP_DiagnosticLog.Write("Instant Loan Proposal Page , Checking internet method ,","Internet Unavailable please check your internet connection" );

                }

            } catch (Exception exp) {
                try {
//                    LoadingIndicator.getLoader().hideProgress();
                    String strMessage = exp.getMessage();
                    CspLog.WriteLog(TAG + ":: strErrMsg -->" + strMessage);
                    if (strMessage != null && strMessage.equalsIgnoreCase("-1203")) {
                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR,
                                "Aadhaar Number not available for this client code");
                    }
                    exp.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class HtppAsyncAuth extends AsyncTask<String, Void, StringBuffer> {

        @Override
        protected void onPreExecute() {
            CspLog.WriteLog(TAG + "," + "inside HtppAsyncAuth onPreExecute --->");
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
        }

        @Override
        protected StringBuffer doInBackground(final String... args) {
            try {
                return sendHttpRequest(args[0]);
            } catch (Exception e) {
                return null;
            }
        }

        @SuppressWarnings("deprecation")
        @Override
        protected void onPostExecute(final StringBuffer response) {
            CspLog.WriteLog(TAG + "," + "inside onPostExecute resposne--->" + response);
            appHelper.getDialogHelper().closeDialog();
            try {
                if (response == null) {
//                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR, "Connection Timeout!");
                    CspLog.WriteLog("Connection time out");
                    ttsHelper.speak(EKYCActivity.this, R.string.str_connectionTimeout);
                    exceptionCallbackCall();
                } else {
                    com.precision.csp.Uidaiauthentication.SplitXML finalResponse = new com.precision.csp.Uidaiauthentication.SplitXML();
                    CspLog.WriteLog(TAG + "," + "CentersDTO From CB Check Aggregator Service -->" + response.toString());
                    finalResponse.splitFinalResponse(response.toString());
                    CspLog.WriteLog(TAG + "," + "CentersDTO From CB Check finalResponse.getErrorMessage() -->" + finalResponse.getErrorMessage());
                    setFinalResponse1(finalResponse);
                    if (finalResponse.getErrorMessage().equalsIgnoreCase("Success")) {
                        callbackCall();
                    } else {
                        failureCallbackCall();
                    }
                }
            } catch (Exception e) {
                CspLog.WriteLog("Connection exception");
                exceptionCallbackCall();
            }
        }
    }

    public StringBuffer sendHttpRequest(String xmlData) {
        try {
            CspLog.WriteLog(TAG + "," + " sending request to Aggeregator service " + xmlData);

            String strURL = configurationDto.getURL();

            CspLog.WriteLog(TAG + "," + "isOurClient -->" + isOurClient + "::URL ::" + strURL);
            URL obj = new URL(strURL);
            trustEveryone();
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestMethod("POST");
            con.setUseCaches(false);
           /* con.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            con.setRequestProperty("Content-Length",
                    Integer.toString(xmlData.getBytes().length));
            con.setRequestProperty("Content-Language", "en-US");*/
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(xmlData);
            wr.flush();
            wr.close();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            CspLog.WriteLog(TAG + "," + "CentersDTO -------->" + response);
            return response;
        } catch (Exception exp) {
            exp.printStackTrace();
            CspLog.WriteLog("!!!!!!!!!!!! Exception in connect to Aggregator Service !!!!!!!!!!!!!!!!!");
            return null;
        }
    }

    private static void trustEveryone() {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[]{new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }}, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(
                    context.getSocketFactory());
        } catch (Exception e) { // should never happen
            e.printStackTrace();
        }
    }

    private boolean isOnCreateCalled = false;

    @Override
    protected void onResume() {
        if (isOnCreateCalled) {
            isOnCreateCalled = false;
        }
        super.onResume();
    }


    @Override
    public void callbackCall() {
        Log.i(TAG, "inside ekyc activity callbackCall");
    }

    @Override
    public void failureCallbackCall() {
        Log.i(TAG, "inside ekyc activity failureCallbackCall");
    }

    @Override
    public void exceptionCallbackCall() {
        Log.i(TAG, "inside ekyc activity timeOutCallbackCall");
    }

    public CustomTextInputLayout tilEKYCName, tilEKYCDob,
            tilEKYCFatherName, tilEKYCMotherName,
            tilEKYCSpouseName, tilEKYCMobileNumber,
            tilEKYCAddress, tilEKYCLandmark, tilEKYCDistrict,
            tilEKYCState, tilEKYCPincode, tilEKYCSpouseDob, tilEKYCSpouseAge;
    public RadioGroup rgEKYCGender, rgEKYCMaritalStatus;
    public LinearLayout llSpouseDetails;
    private List<CustomTextInputLayout> tilEKYCList = new ArrayList<>();

    public void setupKYCUI() {
        try {
            tilEKYCName = (CustomTextInputLayout) findViewById(R.id.til_ekyc_name);
            tilEKYCDob = (CustomTextInputLayout) findViewById(R.id.til_ekyc_dob);
            tilEKYCFatherName = (CustomTextInputLayout) findViewById(R.id.til_ekyc_father_name);
            tilEKYCMotherName = (CustomTextInputLayout) findViewById(R.id.til_ekyc_mother_name);
            llSpouseDetails = (LinearLayout) findViewById(R.id.ll_spouse_details);
            tilEKYCSpouseName = (CustomTextInputLayout) findViewById(R.id.til_ekyc_spouse_name);
            tilEKYCSpouseDob = (CustomTextInputLayout) findViewById(R.id.til_ekyc_spouse_dob);
            tilEKYCSpouseAge = (CustomTextInputLayout) findViewById(R.id.til_ekyc_spouse_age);
            tilEKYCMobileNumber = (CustomTextInputLayout) findViewById(R.id.til_ekyc_mobile_number);
            tilEKYCAddress = (CustomTextInputLayout) findViewById(R.id.til_ekyc_address);
            tilEKYCLandmark = (CustomTextInputLayout) findViewById(R.id.til_ekyc_landmark);
            tilEKYCDistrict = (CustomTextInputLayout) findViewById(R.id.til_ekyc_district);
            tilEKYCState = (CustomTextInputLayout) findViewById(R.id.til_ekyc_state);
            tilEKYCPincode = (CustomTextInputLayout) findViewById(R.id.til_ekyc_pincode);
            rgEKYCGender = (RadioGroup) findViewById(R.id.rg_ekyc_gender);
            rgEKYCMaritalStatus = (RadioGroup) findViewById(R.id.rg_ekyc_marital_status);

            rgEKYCGender.setOnCheckedChangeListener(this);
            rgEKYCMaritalStatus.setOnCheckedChangeListener(this);

            tilEKYCList.add(tilEKYCName);
            tilEKYCList.add(tilEKYCDob);
            tilEKYCList.add(tilEKYCFatherName);
            tilEKYCList.add(tilEKYCMotherName);
            tilEKYCList.add(tilEKYCSpouseName);
            tilEKYCList.add(tilEKYCSpouseAge);
            tilEKYCList.add(tilEKYCSpouseDob);
            tilEKYCList.add(tilEKYCMobileNumber);
            tilEKYCList.add(tilEKYCAddress);
            tilEKYCList.add(tilEKYCLandmark);
            tilEKYCList.add(tilEKYCDistrict);
            tilEKYCList.add(tilEKYCState);
            tilEKYCList.add(tilEKYCPincode);
            setListener(tilEKYCList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setListener(List<CustomTextInputLayout> tilList) {
        try {
            for (CustomTextInputLayout customTextInputLayout : tilList) {
                customTextInputLayout.getEditText().addTextChangedListener(new LengthTextWatcher(this,
                        customTextInputLayout) {
                    @Override
                    protected void onValidated(boolean b, CustomTextInputLayout customTextInputLayout, String s) {

                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setEKYCData() {
        try {
            if (transactionDto.isAutoFill()) {
                appHelper.setTILText(tilEKYCName, finalResponse1.getName(), false);
                appHelper.setTILText(tilEKYCFatherName, finalResponse1.getCoDetails(), false);
                appHelper.setTILText(tilEKYCDob, finalResponse1.getDOB(), false);
                appHelper.setTILText(tilEKYCMobileNumber, finalResponse1.getMobileNumber(), false);
                String address = finalResponse1.getHouse() + ", " + finalResponse1.getStreet() + "/" + finalResponse1.getVtc();
                appHelper.setTILText(tilEKYCAddress, address, false);
                appHelper.setTILText(tilEKYCLandmark, finalResponse1.getLM(), false);
                appHelper.setTILText(tilEKYCDistrict, finalResponse1.getDist(), false);
                appHelper.setTILText(tilEKYCState, finalResponse1.getStrstate(), false);
                appHelper.setTILText(tilEKYCPincode, finalResponse1.getPc(), false);

                String strGender = null;
                if (finalResponse1.getGender() != null && finalResponse1.getGender().equalsIgnoreCase("M")) {
                    strGender = "Male";
                } else if (finalResponse1.getGender() != null && finalResponse1.getGender().equalsIgnoreCase("F")) {
                    strGender = "Female";
                } else if (finalResponse1.getGender() != null && finalResponse1.getGender().equalsIgnoreCase("T")) {
                    strGender = "Third Gender";
                }
                appHelper.checkRadioButton(rgEKYCGender, strGender,false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendRDPaymentRequest(com.precision.csp.Uidaiauthentication.SplitXML finalResponse, Object openRDAccountDTO) {
        Log.i(TAG, "inside sendRDPaymentRequest finalResponse--->" + finalResponse + "::openRDAccountDTO 11::" + openRDAccountDTO);

    }

    public void qrScanCallback(AadharData aadharData) {
        Log.i(TAG, "inside ekyc activity qrScanCallback-------------->");
    }


    @Override
    public void qrScanOriginalData(String aadharData) {
        Log.i(TAG, "inside ekyc activity qrScanOriginalData------------->");
    }

    @Override
    public void qrScanTimeOut() {
        Log.i(TAG, "inside ekyc activity qrScanTimeOut------------>");
        try {
          /*  dialogHelper.getConfirmationDialog().show(ConfirmationDialog.ALERT,
                    "Unable to scan QR Code");*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void biometricCallBack(DeviceInfo deviceInfo, CaptureResponse captureResponse,String aadhaar) {
        Log.i(TAG, "inside biometricCallBack -------------->");
    }
}
