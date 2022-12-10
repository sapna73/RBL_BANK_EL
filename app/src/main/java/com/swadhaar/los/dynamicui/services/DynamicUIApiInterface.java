package com.swadhaar.los.dynamicui.services;


import com.swadhaar.los.database.entity.DynamicUITable;
import com.swadhaar.los.database.entity.StageDetailsTable;
import com.swadhaar.los.database.entity.SubmitDataTable;
import com.swadhaar.los.models.EKYCResponseDTO;
import com.swadhaar.los.models.EKYCRootDTO;
import com.swadhaar.los.models.OTPTriggerDTO;
import com.swadhaar.los.models.OTPTriggerResponseDTO;
import com.swadhaar.los.models.OTPVerifyDTO;
import com.swadhaar.los.models.OTPVerifyResponseDTO;
import com.swadhaar.los.models.PincodeResponseDTO;

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

    /* EKYC Login Request*/
    @POST("ekyc/enquiry")
    Call<EKYCResponseDTO> EKYCRequest(@Body EKYCRootDTO ekycRootDTO, @Header("Authorization") String authHeader);

    /* OTP TRIGGER SERVICE */
    @POST("otp/OTPTrigger")
    Call<OTPTriggerResponseDTO> generateOTP(@Body OTPTriggerDTO otpTriggerDTO, @Header("Authorization") String authHeader);

    /* VERIFY OTP SERVICE */
    @POST("otp/OTPVerify")
    Call<OTPVerifyResponseDTO> verifyOTP(@Body OTPVerifyDTO otpVerifyDTO, @Header("Authorization") String authHeader);
}
