package com.saartak.el.activities;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bfil.ekyclibrary.models.ConfigurationDto;
import com.bfil.ekyclibrary.models.TransactionDto;
import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.precision.csp.aua.client.PauaAuth;
import com.precision.csp.authapi.common.RequestType;
import com.precision.rdservice.CaptureResponse;
import com.precision.rdservice.DeviceInfo;
import com.saartak.el.R;
import com.saartak.el.database.entity.DynamicUITable;
import com.saartak.el.models.EKYCResponseDTO;
import com.saartak.el.models.EKYCRootDTO;
import com.saartak.el.models.EkycRequest;
import com.saartak.el.models.RequestString;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.saartak.el.constants.AppConstant.AquirerID;
import static com.saartak.el.constants.AppConstant.INFO_REQUEST;
import static com.saartak.el.constants.AppConstant.env;
import static com.saartak.el.constants.AppConstant.envType;

public class EKYCTestActivity extends LOSBaseActivity {
    EditText etAadhaar;
    Button btnAadhaar,btnLoginRequest;
    String strAadhaar;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    byte[] key, iv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
//                WindowManager.LayoutParams.FLAG_SECURE);

        setContentView(R.layout.activity_ekyctest);
        etAadhaar=(EditText) findViewById(R.id.et_aadhaar);
        btnAadhaar=(Button)findViewById(R.id.btn_ekyc);
        btnLoginRequest=(Button)findViewById(R.id.btn_ekyc_login);

        // Get key
        key=getKey();

        // Get IV
        iv=getIV();

        btnAadhaar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 strAadhaar=etAadhaar.getText().toString();
                  if( ! TextUtils.isEmpty(strAadhaar)){
//                           EKYCRequest();

                      ConfigurationDto configurationDto = new ConfigurationDto();
                      configurationDto.setAquirerID(AquirerID);
                      configurationDto.setEnvType(envType + "");
                      RequestType reqType = RequestType.EB;
                      configurationDto.setEnv(env); //todo added ekyc env UAT or PROD

                      setConfigurationDto(configurationDto);

                      PauaAuth pauaAuth = new PauaAuth();

                      TransactionDto transactionDto = new TransactionDto();
                      transactionDto.setAuthTransType(reqType);
                      transactionDto.setAutoFill(false);

                      setTransactionDto(transactionDto);

                      checkScanner();
                  }else{
                      appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                              "Enter Aadhaar");
                  }
            }
        });


        configureDagger();
        configureViewModel();

    }

    private void configureDagger() {
        AndroidInjection.inject(this);
    }

    public void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DynamicUIViewModel.class);
    }

    public void checkScanner() {
        try {
            Intent sendIntent = new Intent();
            sendIntent.setAction("in.gov.uidai.rdservice.fp.INFO");
            startActivityForResult(sendIntent, INFO_REQUEST);
        } catch (Exception e) {
            Log.e("TAG","Device Not Installed");
            e.printStackTrace();
        }
    }

    public void EKYCRequest(DeviceInfo deviceInfo, CaptureResponse captureResponse){
        try{
            // TODO: EKYC ROOT DTO
            EKYCRootDTO ekycRootDTO=new EKYCRootDTO();
            ekycRootDTO.setUniqueId(strAadhaar);
            ekycRootDTO.setClientID(CLIENT_ID);
            ekycRootDTO.setExternalCustomerId("");
            ekycRootDTO.setKYCId(strAadhaar);
            ekycRootDTO.setCreatedDate("03-09-2019 16:17:29");
            ekycRootDTO.setCreatedBy("BHM");
            ekycRootDTO.setServiceType("Ekyc");

            // TODO: EKYC Request DTO
            EkycRequest ekycRequest=new EkycRequest();
            ekycRequest.setTerminalId("027802456675365");
            ekycRequest.setFreshnessFactor("123435");
            ekycRequest.setTransType("106");
            ekycRequest.setCsrId("RFS0012345");
            ekycRequest.setCsrPassword("");
            ekycRequest.setRequestId("1");
            ekycRequest.setResentCount(1);
            ekycRequest.setDeviceId("FIN00001");
            ekycRequest.setChannel("RBLFINSRV");
            ekycRequest.setVersion("1.2.8.1");
            ekycRequest.setAadhaarNo(strAadhaar);
            ekycRequest.setEncryptedPid(captureResponse.getPiddata());
            ekycRequest.setEncryptedHmac(captureResponse.getHmac());
            ekycRequest.setSessionKeyValue(captureResponse.getSessionKey());
            ekycRequest.setCertificateIdentifier(captureResponse.getCi());
            ekycRequest.setUserConcent("true");
            ekycRequest.setEkycType("1");
            ekycRequest.setAuthVersionToUse("2.5");
            ekycRequest.setEkycPurposeStr("");
            ekycRequest.setOnlyEkycConfirmationRequired("0");
            ekycRequest.setRegisteredDeviceServiceId(deviceInfo.getRdsId());
            ekycRequest.setRegisteredDeviceServiceVersion(deviceInfo.getRdsVer());
            ekycRequest.setRegisteredDeviceProviderId(deviceInfo.getDpId());
            ekycRequest.setRegisteredDeviceCode(deviceInfo.getDc());
            ekycRequest.setRegisteredDeviceModelId(deviceInfo.getMi());
            ekycRequest.setRegisteredDevicePublicKey(deviceInfo.getMc());

            // TODO: Request string DTO
            RequestString requestString=new RequestString();
            requestString.setEkycRequest(ekycRequest);

            ekycRootDTO.setRequestString(requestString);
// TODO: 20-11-2019 just dummy DynamicUITable added
            DynamicUITable dynamicUITable=new DynamicUITable();
            viewModel.EKYCRequest(ekycRootDTO,dynamicUITable);
            if (viewModel.getEKYCResponseLiveData() != null) {
                Observer EKYCRequestObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        EKYCResponseDTO ekycResponseDTO = (EKYCResponseDTO) o;
                        viewModel.getEKYCResponseLiveData().removeObserver(this);

                        if(ekycResponseDTO !=null && ekycResponseDTO.getApiResponse() !=null &&
                                ! TextUtils.isEmpty(ekycResponseDTO.getApiResponse().getAuthenticationMessage())){
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,ekycResponseDTO.getApiResponse().getAuthenticationMessage());
                        }else{
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR,"EKYC Failed");
                        }
                    }
                };
                viewModel.getEKYCResponseLiveData().observe(this, EKYCRequestObserver);
            }
        }catch ( Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void biometricCallBack(DeviceInfo deviceInfo, CaptureResponse captureResponse,String aadhaar) {
        try{
            if(deviceInfo !=null && captureResponse !=null) {
                EKYCRequest(deviceInfo, captureResponse);
            }else{
                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR,"Unable to get biometric information");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    public void encryptFile(View view){

        Bitmap bitmap= BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/img.png");
        // Write image data to ByteArrayOutputStream
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100,baos);
        // Encrypt and save the image
        saveFile(encrypt(key,baos.toByteArray()),"enimg.png");
    }


    public void decryptFile(View view){
        try {
            // Create FileInputStream to read tvName the encrypted image file
            FileInputStream fis = new FileInputStream(Environment.getExternalStorageDirectory()+"/enimg.png");
            // Save the decrypted image
            saveFile(decrypt(key, fis),"deimg.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveFile(byte[] data, String outFileName){
        FileOutputStream fos=null;
        try {
            fos=new FileOutputStream(Environment.getExternalStorageDirectory()+ File.separator+outFileName);
            fos.write(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private byte[] encrypt(byte[] skey, byte[] data){
        SecretKeySpec skeySpec = new SecretKeySpec(skey, "AES");
        Cipher cipher;
        byte[] encrypted=null;
        try {
            // Get Cipher instance for AES algorithm
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            // Initialize cipher
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(iv));
            // Encrypt the image byte data
            encrypted = cipher.doFinal(data);
        }catch(Exception e){
            e.printStackTrace();
        }
        return encrypted;
    }

    private byte[] decrypt(byte[] skey, FileInputStream fis){
        SecretKeySpec skeySpec = new SecretKeySpec(skey, "AES");
        Cipher cipher;
        byte[] decryptedData=null;
        CipherInputStream cis=null;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(iv));
            // Create CipherInputStream to read and decrypt the image data
            cis = new CipherInputStream(fis, cipher);
            // Write encrypted image data to ByteArrayOutputStream
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            byte[] data = new byte[2048];
            while ((cis.read(data)) != -1) {
                buffer.write(data);
            }
            buffer.flush();
            decryptedData=buffer.toByteArray();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try {
                fis.close();
                cis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return decryptedData;
    }

    private static byte[]  getKey(){
        KeyGenerator keyGen;
        byte[] dataKey=null;
        try {
            // Generate 256-bit key
            keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256);
            SecretKey secretKey = keyGen.generateKey();
            dataKey=secretKey.getEncoded();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return dataKey;
    }

    private static byte[] getIV(){
        SecureRandom random = new SecureRandom();
        byte[] iv = random.generateSeed(16);
        return iv;
    }

}
