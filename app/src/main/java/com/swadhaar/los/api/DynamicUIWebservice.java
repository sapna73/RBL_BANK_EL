package com.swadhaar.los.api;

import com.swadhaar.los.database.entity.DynamicUITable;
import com.swadhaar.los.database.entity.StageDetailsTable;
import com.swadhaar.los.database.entity.SubmitDataTable;
import com.swadhaar.los.models.AadhaarVaultRequestDTO;
import com.swadhaar.los.models.AadhaarVaultResponseDTO;
import com.swadhaar.los.models.BranchProductFeatureMasterRequestDTO;
import com.swadhaar.los.models.BearerTokenRequestDTO;
import com.swadhaar.los.models.BearerTokenResponseDTO;
import com.swadhaar.los.models.CBMFIRequestDTO;
import com.swadhaar.los.models.CBMFIResponseDTO;
import com.swadhaar.los.models.CGTFromServerRequestDTO;
import com.swadhaar.los.models.CGTServiceRequestDTO;
import com.swadhaar.los.models.CMDataForOtherDayRequestDTO;
import com.swadhaar.los.models.CashCollectionDataRequestDTO;
import com.swadhaar.los.models.CashDenominationTableDataRequestDTO;
import com.swadhaar.los.models.CenterMeetingAllCollectionMainRequestDTO;
import com.swadhaar.los.models.CenterMeetingAllCollectionTablesDataRequestDTO;
import com.swadhaar.los.models.CenterMeetingAttendanceDataRequestDTO;
import com.swadhaar.los.models.CenterMeetingCollectionDataRequestDTO;
import com.swadhaar.los.models.CenterMeetingDataRequestDTO;
import com.swadhaar.los.models.CenterNameRequestDTO;
import com.swadhaar.los.models.ChangePasswordRequestDTO;
import com.swadhaar.los.models.ChangePasswordResponseDTO;
import com.swadhaar.los.models.CibilRequestDTO;
import com.swadhaar.los.models.CibilResponseDTO;
import com.swadhaar.los.models.ColdCallDataRequestDTO;
import com.swadhaar.los.models.ColdCallFromServerRequestDTO;
import com.swadhaar.los.models.CollectionDataRequestDTO;
import com.swadhaar.los.models.DBRRequestDTO;
import com.swadhaar.los.models.DocumentMasterRequestDTO;
import com.swadhaar.los.models.EKYCResponseDTO;
import com.swadhaar.los.models.EKYCRootDTO;
import com.swadhaar.los.models.EligibilityRequestDTO;
import com.swadhaar.los.models.GroupNameRequestDTO;
import com.swadhaar.los.models.IFSCRequestDTO;
import com.swadhaar.los.models.InitiatePaymentStatusRequestDTO;
import com.swadhaar.los.models.InitiateRequestDTO;
import com.swadhaar.los.models.InitiateResponseDTO;
import com.swadhaar.los.models.KnowledgeBankRequestDTO;
import com.swadhaar.los.models.LeadRawDataRequestDTO;
import com.swadhaar.los.models.LogOutRequestDTO;
import com.swadhaar.los.models.LogOutResponseDTO;
import com.swadhaar.los.models.LogRequestDTO;
import com.swadhaar.los.models.LogResponseDTO;
import com.swadhaar.los.models.LoginRequestDTO;
import com.swadhaar.los.models.LoginResponseDTO;
import com.swadhaar.los.models.PanValidationRequestDTO;
import com.swadhaar.los.models.PanValidationResponseDTO;
import com.swadhaar.los.models.PlannerDataRequestDTO;
import com.swadhaar.los.models.ProductMasterRequestDTO;
import com.swadhaar.los.models.QCReSubmissionDataRequestDTO;
import com.swadhaar.los.models.RawDataCBRequestDTO;
import com.swadhaar.los.models.RawDataForSingleScreenRequestDTO;
import com.swadhaar.los.models.RawDataRequestDTO;
import com.swadhaar.los.models.ResetPasswordRequestDTO;
import com.swadhaar.los.models.ResetPasswordResponseDTO;
import com.swadhaar.los.models.RoleNamesRequestDTO;
import com.swadhaar.los.models.SODEODRequestDTO;
import com.swadhaar.los.models.SalesToolPostDataRequestDTO;
import com.swadhaar.los.models.SalesToolRequestDTO;
import com.swadhaar.los.models.SalesToolResponseDTO;
import com.swadhaar.los.models.StaffActivityTableDataRequestDTO;
import com.swadhaar.los.models.SubmitDataDTO;
import com.swadhaar.los.models.WorkFlowHistoryRequestDTO;
import com.swadhaar.los.models.WorkflowResponseDTO;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface  DynamicUIWebservice {

    /*   Get DynamicUI Parameters tvName server */
    @GET("metavalues/Get/{projectid}/{screen}/{moduleid}")
    Call<List<DynamicUITable>> getDynamicUIFromServer(@Path("screen") String screen,
                                                      @Path("projectid") String projectid,
                                                      @Path("moduleid") String moduleid, @Header("Authorization") String authHeader);

    /*   Get DynamicUI Parameters tvName server */
    @GET("metavalues/Get/{projectid}/{screen}/{moduleid}")
    Observable<List<DynamicUITable>> getMetaDataServer(@Path("screen") String screen,
                                                      @Path("projectid") String projectid,
                                                      @Path("moduleid") String moduleid
            , @Header("Authorization") String authHeader);


    /*   Get my stages*/
    @GET("Workflow/getmystage/{userId}/{productId}")
    Call<List<StageDetailsTable>> getMyStages(@Path("userId") String userId,@Path("productId") String productId,
                                              @Header("Authorization") String authHeader);

    /*   Get Workflow History */
    @GET("Workflow/GetCustomerWorkflowHistory/{clientId}")
    Observable<List<WorkflowResponseDTO>> getWorkflowHistory(@Path("clientId") String clientId,
                                                             @Header("Authorization") String authHeader);

    /*   IMAGE DOWNLOAD */
    @GET("V1/downloadImages/{filename}/{extension}")
    Observable<ResponseBody> imageDownload(@Path("filename") String filename,@Path("extension") String extension,
                                              @Header("Authorization") String authHeader);


    /*   IMAGE DOWNLOAD */
    @GET("V1/downloadImages/{filename}/{extension}")
    Call<ResponseBody> imageDownloadForKnowledgeBank(@Path("filename") String filename,@Path("extension") String extension,
                                              @Header("Authorization") String authHeader);


    /*    *//* OLD Post Entered data to server *//*
    @POST("workflow")
    Call<String> postDataToServer(@Body SubmitDataTable submitDataDTO);*/

    /*  Post Entered data to server */
    @POST("workflow/SubmitRequest")
    Call<String> postDataToServer(@Body SubmitDataDTO submitDataDTO, @Header("Authorization") String authHeader);

    /*  Post Entered data to server JLG */
    @POST("v1/uat/JLG/SubmitRequest")
    Call<String> postSingleDataToServerJLG(@Body SubmitDataTable submitDataDTO, @Header("Authorization") String authHeader);

    /*  Post Entered data to server using RX Java*/
    @POST("workflow/SubmitRequest")
    Observable<String> postDataToServerRXJAVA(@Body SubmitDataDTO submitDataDTO, @Header("Authorization") String authHeader);

    /*  Post Entered data to server for JLG*/
    @POST("v1/uat/JLG/SubmitRequest")
    Observable<String> postDataToServerJLG(@Body SubmitDataTable submitDataDTO, @Header("Authorization") String authHeader);


    @Multipart
    @POST()
    @Headers({
            "Accept: application/json",
            "User-Agent: Mozilla/5.0"
    })
    Observable<String> uploadImageToWebServerNew(@Url String url,
                                           @Part("ClientId") RequestBody ClientId,
                                           @Part("FileName") RequestBody FileName,
                                           @Part("Extension") RequestBody Extension,
                                           @Part("FileType") RequestBody FileType,
                                           @Part("ProductName") RequestBody ProductName,
                                           @Part MultipartBody.Part UploadedFile, @Header("Authorization") String authHeader);


    @Multipart
    @POST()
    Call<String> uploadZipFolderToServer(@Url String url,
                                                 @Part("ClientId") RequestBody ClientId,
                                                 @Part("FileName") RequestBody FileName,
                                                 @Part("Extension") RequestBody Extension,
                                                 @Part("FileType") RequestBody FileType,
                                                 @Part("ProductName") RequestBody ProductName,
                                                 @Part MultipartBody.Part UploadedFile, @Header("Authorization") String authHeader);

    @Multipart
    @POST()
    Call<String> uploadCMImageToWebServerNew(@Url String url,
                                             @Part("ClientId") RequestBody ClientId,
                                             @Part("FileName") RequestBody FileName,
                                             @Part("Extension") RequestBody Extension,
                                             @Part("FileType") RequestBody FileType,
                                             @Part("ProductName") RequestBody ProductName,
                                             @Part MultipartBody.Part UploadedFile, @Header("Authorization") String authHeader);


    /* Get Center Name tvName server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> getCenterNameFromServer(@Body CenterNameRequestDTO centerNameRequestDTO, @Header("Authorization") String authHeader);

    /* Get Group Name tvName server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> getGroupNameFromServer(@Body GroupNameRequestDTO groupNameRequestDTO, @Header("Authorization") String authHeader);

    /* workflow history tvName server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> getWorkflowhistoryFromServer(@Body WorkFlowHistoryRequestDTO workFlowHistoryRequestDTO, @Header("Authorization") String authHeader);

    /* IFSC data tvName server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> getIFSCDataServiceCall(@Body IFSCRequestDTO ifscRequestDTO, @Header("Authorization") String authHeader);

    // TODO: GETROLE NAMES SERVICE
    @POST("UserList")
    Call<ResponseBody> getRoleNamesServiceCall(@Body RoleNamesRequestDTO roleNamesRequestDTO, @Header("Authorization") String authHeader);

    /* collection tvName server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> getCollectionDataFromServer(@Body CollectionDataRequestDTO collectionDataRequestDTO, @Header("Authorization") String authHeader);

    /* Eligibility data tvName server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> getEligibilityDataFromServer(@Body EligibilityRequestDTO eligibilityRequestDTO, @Header("Authorization") String authHeader);

    /* BranchProductFeatureMaster data tvName server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> getBranchProductFeatureMasterDataFromServer(@Body BranchProductFeatureMasterRequestDTO branchProductFeatureMasterRequestDTO, @Header("Authorization") String authHeader);

    /* Raw data tvName server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> rawDataFromServer(@Body RawDataRequestDTO rawDataRequestDTO, @Header("Authorization") String authHeader);

    /* initiate payment status */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> initiatePaymentStatusFromServer(@Body InitiatePaymentStatusRequestDTO initiatePaymentStatusRequestDTO, @Header("Authorization") String authHeader);

    /* Center Meeting data tvName server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> centerMeetingDataFromServer(@Body CenterMeetingDataRequestDTO centerMeetingDataRequestDTO, @Header("Authorization") String authHeader);

    /* Other Day Center Meeting data tvName server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> cmDetailsFromServerForFetchOtherDay(@Body CMDataForOtherDayRequestDTO cmDataForOtherDayRequestDTO, @Header("Authorization") String authHeader);

    /* cash collection data to server */
    @POST("utility/GetDataUtil")
    Observable<ResponseBody> sendCashCollectionToServer(@Body CashCollectionDataRequestDTO cashCollectionDataRequestDTO, @Header("Authorization") String authHeader);

    /* center meeting collection data to server */
    @POST("utility/GetDataUtil")
    Observable<ResponseBody> sendCenterMeetingCollectionToServer(@Body CenterMeetingCollectionDataRequestDTO centerMeetingCollectionDataRequestDTO, @Header("Authorization") String authHeader);

    /* center meeting collection data to server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> sendCenterMeetingAllCollectionTablesDataToServer(@Body CenterMeetingAllCollectionMainRequestDTO centerMeetingAllCollectionMainRequestDTO, @Header("Authorization") String authHeader);

    /* center meeting attendance data to server */
    @POST("utility/GetDataUtil")
    Observable<ResponseBody> sendCenterMeetingAttendanceToServer(@Body CenterMeetingAttendanceDataRequestDTO centerMeetingAttendanceDataRequestDTO, @Header("Authorization") String authHeader);

    /* cash denomination data to server */
    @POST("utility/GetDataUtil")
    Observable<ResponseBody> sendCashDenominationToServer(@Body CashDenominationTableDataRequestDTO cashDenominationTableDataRequestDTO, @Header("Authorization") String authHeader);

    /* staff activity data to server */
    @POST("utility/GetDataUtil")
    Observable<ResponseBody> sendStaffActivityToServer(@Body StaffActivityTableDataRequestDTO staffActivityTableDataRequestDTO, @Header("Authorization") String authHeader);

    /* QC ReSubmission data tvName server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> qcReSubmissionDataFromServer(@Body QCReSubmissionDataRequestDTO qcReSubmissionDataRequestDTO, @Header("Authorization") String authHeader);


    /* Lead Raw data tvName server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> LeadRawDataFromServer(@Body LeadRawDataRequestDTO rawDataRequestDTO, @Header("Authorization") String authHeader);

    /* Cold call data tvName server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> getColdCallDataFromServer(@Body ColdCallFromServerRequestDTO rawDataRequestDTO, @Header("Authorization") String authHeader);

    /* ColdCall Data post to server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> ColdCallDataToServer(@Body ColdCallDataRequestDTO coldCallDataRequestDTO, @Header("Authorization") String authHeader);

    /* sales tool Data post to server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> SalesToolDataToServer(@Body SalesToolPostDataRequestDTO salesToolPostDataRequestDTO, @Header("Authorization") String authHeader);

    /* Planner Data post to server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> PlannerDataToServer(@Body PlannerDataRequestDTO plannerDataRequestDTO, @Header("Authorization") String authHeader);


    /* Collection Data post to server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> CollectionDataToServer(@Body CollectionDataRequestDTO collectionDataRequestDTO, @Header("Authorization") String authHeader);

    /* Raw data for single screen tvName server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> rawDataForSingleScreenFromServer(@Body RawDataForSingleScreenRequestDTO rawDataForSingleScreenRequestDTO, @Header("Authorization") String authHeader);

    /* CB Amount tvName server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> getCBAmountFromServer(@Body RawDataCBRequestDTO rawDataCBRequestDTO, @Header("Authorization") String authHeader);

    /* SOD EOD Data To Server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> SODEODServiceCall(@Body SODEODRequestDTO sodeodRequestDTO, @Header("Authorization") String authHeader);

    /* CGT Data tvName server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> getCGTDataFromServer(@Body CGTFromServerRequestDTO cgtFromServerRequestDTO, @Header("Authorization") String authHeader);

    /* Product Master tvName server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> getProductMasterFromServer(@Body ProductMasterRequestDTO productMasterRequestDTO, @Header("Authorization") String authHeader);

    /* Product Master tvName server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> syncCGTDataToServer(@Body CGTServiceRequestDTO cgtServiceRequestDTO, @Header("Authorization") String authHeader);

    /* Document Master tvName server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> getDocumentMasterFromServer(@Body DocumentMasterRequestDTO documentMasterRequestDTO, @Header("Authorization") String authHeader);

    /* Knowledge Bank Master From server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> getKnowledgeBankFromServer(@Body KnowledgeBankRequestDTO documentMasterRequestDTO, @Header("Authorization") String authHeader);

    /* DBR tvName server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> getDBRFromServer(@Body DBRRequestDTO dbrRequestDTO, @Header("Authorization") String authHeader);


    /* Login service*/
    @POST("Login")
    Call<LoginResponseDTO> loginService(@Body LoginRequestDTO loginRequestDTO);

    /* EKYC  Request*/
    @POST("v1/uat/ekyc/enquiry")
    Call<EKYCResponseDTO> EKYCRequest(@Body EKYCRootDTO ekycRootDTO, @Header("Authorization") String authHeader);


    /* AADHAAR VAULT SERVICE CALL */
    @POST("AadhaarValut/keygeneration")
    Call<AadhaarVaultResponseDTO> aadhaarVaultServiceCall(@Body AadhaarVaultRequestDTO aadhaarVaultRequestDTO, @Header("Authorization") String authHeader);


    /* SALES TOOL SERVICE CALL */
    @POST("sales/calculation")
    Call<SalesToolResponseDTO> salesToolServiceCall(@Body SalesToolRequestDTO salesToolRequestDTO, @Header("Authorization") String authHeader);


    /* Initiate SERVICE CALL */
    @POST("payment/AccountSMS")
    Call<InitiateResponseDTO> initiateServiceCall(@Body InitiateRequestDTO initiateRequestDTO, @Header("Authorization") String authHeader);

    /* CIBIL SERVICE CALL */
    @POST("cibil/enquiry")
    Call<CibilResponseDTO> generateCIBILServiceCall(@Body CibilRequestDTO cibilRequestDTO, @Header("Authorization") String authHeader);

    /* CB MFI SERVICE CALL */
    @POST("CBMFI/Enquiry")
    Call<CBMFIResponseDTO> performCB_MFI_ServiceCall(@Body CBMFIRequestDTO cbMFIRequestDTO, @Header("Authorization") String authHeader);


    /* PAN Validation Request*/
    @POST("PanVerification/WithUserId")
    Call<PanValidationResponseDTO> panValidationServiceCall(@Body PanValidationRequestDTO panValidationRequestDTO, @Header("Authorization") String authHeader);


    /* SEND LOG TO SERVER */
    @POST("logs/add")
    Observable<LogResponseDTO> sendLOGToServer(@Body LogRequestDTO logRequestDTO, @Header("Authorization") String authHeader);

    // TODO: LOGIN SERVICE
    @POST("Login")
    Call<LoginResponseDTO> logInService(@Body LoginRequestDTO loginRequestDTO);

    // TODO: LOGOUT SERVICE
    @POST("LogOff")

    Call<LogOutResponseDTO> logOutService(@Body LogOutRequestDTO logOutRequestDTO,@Header("ApiKey") String api_key);

    // TODO: BEARER TOKEN SERVICE
    @POST("identity/getidentity")
    Call<BearerTokenResponseDTO> getBearerToken(@Body BearerTokenRequestDTO bearerTokenRequestDTO);


    // TODO: RESET PASSWORD SERVICE
    @POST("user/resetpassword")
    Call<ResetPasswordResponseDTO> resetPasswordServiceCall(@Body ResetPasswordRequestDTO resetPasswordRequestDTO, @Header("Authorization") String authHeader);

    // TODO: CHANGE PASSWORD SERVICE
    @POST("ChangePassword")
    Call<List<ChangePasswordResponseDTO>> changePasswordServiceCall(@Body ChangePasswordRequestDTO changePasswordRequestDTO, @Header("Authorization") String authHeader);

}
