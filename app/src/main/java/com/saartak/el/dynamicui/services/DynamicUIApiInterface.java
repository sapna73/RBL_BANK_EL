package com.saartak.el.dynamicui.services;


import com.saartak.el.database.entity.DynamicUITable;
import com.saartak.el.database.entity.StageDetailsTable;
import com.saartak.el.database.entity.SubmitDataTable;
import com.saartak.el.models.EKYCResponseDTO;
import com.saartak.el.models.EKYCRootDTO;
import com.saartak.el.models.GetBankDetailsByIfscCodeRequestDTO;
import com.saartak.el.models.GetBankDetailsByIfscCodeResponseDTO;
import com.saartak.el.models.OTPTriggerDTO;
import com.saartak.el.models.OTPTriggerResponseDTO;
import com.saartak.el.models.OTPVerifyDTO;
import com.saartak.el.models.OTPVerifyResponseDTO;
import com.saartak.el.models.PinCode.PinCodeDataFromServerRequestDTO;
import com.saartak.el.models.PinCode.PinCodeDataFromServerResponseDTO;
import com.saartak.el.models.PincodeResponseDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by alihussain on 12-03-2018.
 */

public interface DynamicUIApiInterface {

    /*   Get UI Parameters tvName server */
    @GET("metavalues/Get/1/{screen}/1")
    // TODO: 09-04-2019 Hardcoded
    Call<List<DynamicUITable>> getUIParameters(@Path("screen") String screen);

    /*   Get my stages*/
    @GET("Workflow/getmystage/{userId}")
    Call<List<StageDetailsTable>> getMyStages(@Path("userId") String userId);

/*    *//* OLD Post Entered data to server *//*
    @POST("workflow")
    Call<String> postDataToServer(@Body SubmitDataTable submitDataDTO);*/

    /*  Post Entered data to server */
    @POST("workflow/SubmitRequest")
    Call<String> postDataToServer(@Body SubmitDataTable submitDataDTO);

    /*  Workflow Initiation */
    @POST("Workflow/SubmitRequest")
    Call<String> initiateWorkflow(@Body SubmitDataTable submitDataDTO);

    /*   Get Pincode Details */
    @GET("Pincode/{PINCODE}")
    Call<PincodeResponseDTO> getPincodeDetails(@Path("PINCODE") String pincode, @Header("Authorization") String authHeader);


    @POST("Pincode/PincodeEnquiry")
    Call<String> getPinCodeDataFromServer(@Body String string, @Header("Authorization") String authHeader, @Header("k1") String k1);
   // Call<PinCodeDataFromServerResponseDTO> getPinCodeDataFromServer(@Body PinCodeDataFromServerRequestDTO pinCodeDataFromServerRequestDTO, @Header("Authorization") String authHeader, @Header("k1") String k1);

    @POST("IFSCCode/IFSCCodeEnquiry")
    Call<String> getIFSCDataFromServer(@Body String s, @Header("Authorization") String authHeader, @Header("k1") String k1);
   // Call<GetBankDetailsByIfscCodeResponseDTO> getIFSCDataFromServer(@Body GetBankDetailsByIfscCodeRequestDTO pinCodeDataFromServerRequestDTO, @Header("Authorization") String authHeader, @Header("k1") String k1);

    /* EKYC Login Request*/
    @POST("ekyc/enquiry")
    Call<EKYCResponseDTO> EKYCRequest(@Body EKYCRootDTO ekycRootDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    /* OTP TRIGGER SERVICE */
    @POST("otp/OTPTrigger")
    Call<OTPTriggerResponseDTO> generateOTP(@Body OTPTriggerDTO otpTriggerDTO, @Header("Authorization") String authHeader);

    /* VERIFY OTP SERVICE */
    @POST("otp/OTPVerify")
    Call<OTPVerifyResponseDTO> verifyOTP(@Body OTPVerifyDTO otpVerifyDTO, @Header("Authorization") String authHeader);
}
