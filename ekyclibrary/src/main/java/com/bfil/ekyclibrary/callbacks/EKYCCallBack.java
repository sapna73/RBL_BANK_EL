package com.bfil.ekyclibrary.callbacks;

import com.bfil.ekyclibrary.models.AadharData;
import com.precision.csp.Uidaiauthentication.SplitXML;
import com.precision.rdservice.CaptureResponse;
import com.precision.rdservice.DeviceInfo;


public interface EKYCCallBack {
    void callbackCall();
    void failureCallbackCall();
    void exceptionCallbackCall();
    void sendRDPaymentRequest(SplitXML finalResponse, Object openRdAccount);
    void qrScanCallback(AadharData aadharData);
    void qrScanOriginalData(String aadharData);
    void qrScanTimeOut();
    void biometricCallBack(DeviceInfo deviceInfo, CaptureResponse captureResponse,String aadhaar);
}



