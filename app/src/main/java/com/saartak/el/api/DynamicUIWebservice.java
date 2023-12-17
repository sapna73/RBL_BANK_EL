package com.saartak.el.api;

import com.google.gson.JsonObject;
import com.saartak.el.database.entity.DynamicUITable;
import com.saartak.el.database.entity.StageDetailsTable;
import com.saartak.el.database.entity.SubmitDataTable;
import com.saartak.el.models.AadhaarVaultRequestDTO;
import com.saartak.el.models.AadhaarVaultResponseDTO;
import com.saartak.el.models.BranchProductFeatureMasterRequestDTO;
import com.saartak.el.models.BearerTokenRequestDTO;
import com.saartak.el.models.BearerTokenResponseDTO;
import com.saartak.el.models.BreSnsManageResults.BreSnsManageResultsRequestDTO;
import com.saartak.el.models.CBMFIRequestDTO;
import com.saartak.el.models.CBMFIResponseDTO;
import com.saartak.el.models.CGTFromServerRequestDTO;
import com.saartak.el.models.CGTServiceRequestDTO;
import com.saartak.el.models.CMDataForOtherDayRequestDTO;
import com.saartak.el.models.CPV.CPVRequestDTO;
import com.saartak.el.models.CPV.CPVResponseDTO;
import com.saartak.el.models.CashCollectionDataRequestDTO;
import com.saartak.el.models.CashDenominationTableDataRequestDTO;
import com.saartak.el.models.CenterMeetingAllCollectionMainRequestDTO;
import com.saartak.el.models.CenterMeetingAttendanceDataRequestDTO;
import com.saartak.el.models.CenterMeetingCollectionDataRequestDTO;
import com.saartak.el.models.CenterMeetingDataRequestDTO;
import com.saartak.el.models.CenterNameRequestDTO;
import com.saartak.el.models.ChangePasswordRequestDTO;
import com.saartak.el.models.ChangePasswordResponseDTO;
import com.saartak.el.models.CibilRequestDTO;
import com.saartak.el.models.CibilResponseDTO;
import com.saartak.el.models.ColdCallDataRequestDTO;
import com.saartak.el.models.ColdCallFromServerRequestDTO;
import com.saartak.el.models.CollectionDataRequestDTO;
import com.saartak.el.models.CreditApprovalScreenPricing.CreditApprovalScreenPricingrequestDTO;
import com.saartak.el.models.DBRRequestDTO;
import com.saartak.el.models.Dedupe.DedupeRequestDTO;
import com.saartak.el.models.Dedupe.DedupeResponseDTO;
import com.saartak.el.models.Deliquency.DeliquencyRequestDTO;
import com.saartak.el.models.Deliquency.DeliquencyRequestDataFromTable;
import com.saartak.el.models.Deliquency.DeliquencyResponseDTO;
import com.saartak.el.models.DigitalDocs.DigitaklDocHypothecationDeedTW_UCRequestDTO;
import com.saartak.el.models.DigitalDocs.DigitalDocApplicationFormUCRequestDTO;
import com.saartak.el.models.DigitalDocs.DigitalDocRequestSanctionLetterRuralDTO;
import com.saartak.el.models.DigitalDocs.DigitalDocResponseDTO;
import com.saartak.el.models.DocumentMasterRequestDTO;
import com.saartak.el.models.EKYCResponseDTO;
import com.saartak.el.models.EKYCRootDTO;
import com.saartak.el.models.ENach.ENachRequestDTO;
import com.saartak.el.models.ENach.ENachResponseDTO;
import com.saartak.el.models.ENach.MNachRequestDTO;
import com.saartak.el.models.ESignEstamp.ESignEStampRequestDTO;
import com.saartak.el.models.ESignEstamp.ESignEStampResponseDTO;
import com.saartak.el.models.ESignEstamp.ESignEStampStatusRequestDTO;
import com.saartak.el.models.ESignEstamp.ESignEStampStatusResponseDTO;
import com.saartak.el.models.EligibilityRequestDTO;
import com.saartak.el.models.GetCityNameModel.CityRequestDTO;
import com.saartak.el.models.GetCityNameModel.CityResponseDTO;
import com.saartak.el.models.GetPricingInbox.GetPricingInboxRequestDTO;
import com.saartak.el.models.GroupNameRequestDTO;
import com.saartak.el.models.Hunter.HunterNonIndividualRequestDTO;
import com.saartak.el.models.Hunter.HunterRequestDTO;
import com.saartak.el.models.Hunter.HunterResponseDTO;
import com.saartak.el.models.IBBMasters.GETColorRequestDTO;
import com.saartak.el.models.IBBMasters.GETVariantResponseDTO;
import com.saartak.el.models.IBBMasters.GetColorResponseDTO;
import com.saartak.el.models.IBBMasters.GetMakeRequestDTO;
import com.saartak.el.models.IBBMasters.GetMakeResponseDTO;
import com.saartak.el.models.IBBMasters.GetModelRequest;
import com.saartak.el.models.IBBMasters.GetModelResponseDTO;
import com.saartak.el.models.IBBMasters.GetVariantRequestDTO;
import com.saartak.el.models.IBPModel.IBPRequest;
import com.saartak.el.models.IBPModel.IBPResponse;
import com.saartak.el.models.IFSCRequestDTO;
import com.saartak.el.models.InitiatePaymentStatusRequestDTO;
import com.saartak.el.models.InitiateRequestDTO;
import com.saartak.el.models.InitiateResponseDTO;
import com.saartak.el.models.InsertRawDataBag.InsertRawDataBagRequestDTO;
import com.saartak.el.models.InsertRawDataRequest;
import com.saartak.el.models.KarzaModel.KarzaRequestDTO;
import com.saartak.el.models.KarzaModel.KarzaResponseDTO;
import com.saartak.el.models.KnowledgeBankRequestDTO;
import com.saartak.el.models.LDAP_Login.LoginNewRequestDTO;
import com.saartak.el.models.LeadDropDownDetails.GetLeadDropDownDetailsRequestDTO;
import com.saartak.el.models.LeadMasterRequestDTO;
import com.saartak.el.models.LeadRawDataRequestDTO;
import com.saartak.el.models.LoanAmountWisePricingDefaultValues.LoanAmountWisePricingDefaultValuesRequestDTO;
import com.saartak.el.models.LoanTenure.TenureMonthsRequestDTO;
import com.saartak.el.models.LogOutRequestDTO;
import com.saartak.el.models.LogOutResponseDTO;
import com.saartak.el.models.LogRequestDTO;
import com.saartak.el.models.LogResponseDTO;
import com.saartak.el.models.LoginRequestDTO;
import com.saartak.el.models.LoginResponseDTO;
import com.saartak.el.models.NegitiveProfileList.NegitiveProfileListRequestDTO;
import com.saartak.el.models.PINCodeArea.PinCodeAreaRequestDTO;
import com.saartak.el.models.PINCodeValidationLP.PinCodeRequestDTO;
import com.saartak.el.models.PanValidationRequestDTO;
import com.saartak.el.models.PanValidationResponseDTO;
import com.saartak.el.models.PlannerDataRequestDTO;
import com.saartak.el.models.Posidex.PosidexRequestDTO;
import com.saartak.el.models.Posidex.PosidexResponseDTO;
import com.saartak.el.models.ProcessPricingWF.ProcessPricingWFRequestDTO;
import com.saartak.el.models.ProductMasterRequestDTO;
import com.saartak.el.models.QCReSubmissionDataRequestDTO;
import com.saartak.el.models.RAT.RATRequestDTO;
import com.saartak.el.models.RAT.RATResponseDTO;
import com.saartak.el.models.RATSourseOfIncome.RATSPRequestDTO;
import com.saartak.el.models.Ramp.RampRequestDTO;
import com.saartak.el.models.Ramp.RampResponseDTO;
import com.saartak.el.models.RawDataCBRequestDTO;
import com.saartak.el.models.RawDataForSingleScreenRequestDTO;
import com.saartak.el.models.RawDataRequestDTO;
import com.saartak.el.models.ResetPasswordRequestDTO;
import com.saartak.el.models.ResetPasswordResponseDTO;
import com.saartak.el.models.RoleNamesRequestDTO;
import com.saartak.el.models.SODEODRequestDTO;
import com.saartak.el.models.SalesToolPostDataRequestDTO;
import com.saartak.el.models.SalesToolRequestDTO;
import com.saartak.el.models.SalesToolResponseDTO;
import com.saartak.el.models.ScreenEditValidation.ScreenEditValidationRequestDTO;
import com.saartak.el.models.StaffActivityTableDataRequestDTO;
import com.saartak.el.models.StudentGrade.StudentGradeRequestDTO;
import com.saartak.el.models.SubmitDataDTO;
import com.saartak.el.models.SyncWorkflow.SyncWorkflowRequestDTO;
import com.saartak.el.models.TWLMakeModel.GetTWLDataRequestDTO;
import com.saartak.el.models.TypeOfProfession.TypeOfProfessionRequestDTO;
import com.saartak.el.models.UserLoginMenu.UserLoginMenuRequestDTO;
import com.saartak.el.models.VKYC.DownStreamRequestDTO;
import com.saartak.el.models.VKYC.DownStreamResponseDTO;
import com.saartak.el.models.VKYC.VKYCRequestDTO;
import com.saartak.el.models.VKYC.VKYCResponseDTO;
import com.saartak.el.models.WorkFlowHistoryRequestDTO;
import com.saartak.el.models.WorkflowResponseDTO;

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
                                                      @Path("moduleid") String moduleid, @Header("Authorization") String authHeader);

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
    Call<String> postDataToServer(@Body SubmitDataDTO submitDataDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    /*  Screen data to server on every Screen */
    @POST("DBUtil/Submit")
    Call<String> insertpostALLDataToServer(@Body InsertRawDataRequest insertRawDataRequest, @Header("Authorization") String authHeader, @Header("k1") String k1);

    /*  Post Entered data to server JLG */
    @POST("v1/uat/JLG/SubmitRequest")
    Call<String> postSingleDataToServerJLG(@Body SubmitDataTable submitDataDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    /*  Post Entered data to server using RX Java*/
    @POST("workflow/SubmitRequest")
    Observable<String> postDataToServerRXJAVA(@Body SubmitDataDTO submitDataDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    /*  Post Entered data to server for JLG*/
    @POST("v1/uat/JLG/SubmitRequest")
    Observable<String> postDataToServerJLG(@Body SubmitDataTable submitDataDTO, @Header("Authorization") String authHeader, @Header("k1") String k1);

    @Multipart
    @POST()
    @Headers({"Accept: application/json", "User-Agent: Mozilla/5.0"})
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
    Call<ResponseBody> getCenterNameFromServer(@Body CenterNameRequestDTO centerNameRequestDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    /* Get Group Name tvName server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> getGroupNameFromServer(@Body GroupNameRequestDTO groupNameRequestDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    /* workflow history tvName server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> getWorkflowhistoryFromServer(@Body WorkFlowHistoryRequestDTO workFlowHistoryRequestDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    /* IFSC data tvName server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> getIFSCDataServiceCall(@Body IFSCRequestDTO ifscRequestDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    // TODO: GET ROLE NAMES SERVICE
    @POST("UserList")
    Call<ResponseBody> getRoleNamesServiceCall(@Body RoleNamesRequestDTO roleNamesRequestDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    /* collection tvName server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> getCollectionDataFromServer(@Body CollectionDataRequestDTO collectionDataRequestDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    /* Eligibility data tvName server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> getEligibilityDataFromServer(@Body EligibilityRequestDTO eligibilityRequestDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    /* BranchProductFeatureMaster data tvName server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> getBranchProductFeatureMasterDataFromServer(@Body BranchProductFeatureMasterRequestDTO branchProductFeatureMasterRequestDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    /* Raw data tvName server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> rawDataFromServer(@Body RawDataRequestDTO rawDataRequestDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    /* initiate payment status */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> initiatePaymentStatusFromServer(@Body InitiatePaymentStatusRequestDTO initiatePaymentStatusRequestDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    /* Center Meeting data tvName server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> centerMeetingDataFromServer(@Body CenterMeetingDataRequestDTO centerMeetingDataRequestDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    /* Other Day Center Meeting data tvName server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> cmDetailsFromServerForFetchOtherDay(@Body CMDataForOtherDayRequestDTO cmDataForOtherDayRequestDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    /* cash collection data to server */
    @POST("utility/GetDataUtil")
    Observable<ResponseBody> sendCashCollectionToServer(@Body CashCollectionDataRequestDTO cashCollectionDataRequestDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    /* center meeting collection data to server */
    @POST("utility/GetDataUtil")
    Observable<ResponseBody> sendCenterMeetingCollectionToServer(@Body CenterMeetingCollectionDataRequestDTO centerMeetingCollectionDataRequestDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    /* center meeting collection data to server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> sendCenterMeetingAllCollectionTablesDataToServer(@Body CenterMeetingAllCollectionMainRequestDTO centerMeetingAllCollectionMainRequestDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    /* center meeting attendance data to server */
    @POST("utility/GetDataUtil")
    Observable<ResponseBody> sendCenterMeetingAttendanceToServer(@Body CenterMeetingAttendanceDataRequestDTO centerMeetingAttendanceDataRequestDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    /* cash denomination data to server */
    @POST("utility/GetDataUtil")
    Observable<ResponseBody> sendCashDenominationToServer(@Body CashDenominationTableDataRequestDTO cashDenominationTableDataRequestDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    /* staff activity data to server */
    @POST("utility/GetDataUtil")
    Observable<ResponseBody> sendStaffActivityToServer(@Body StaffActivityTableDataRequestDTO staffActivityTableDataRequestDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    /* QC ReSubmission data tvName server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> qcReSubmissionDataFromServer(@Body QCReSubmissionDataRequestDTO qcReSubmissionDataRequestDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    /* Lead Raw data tvName server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> LeadRawDataFromServer(@Body LeadRawDataRequestDTO rawDataRequestDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    /* Cold call data tvName server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> getColdCallDataFromServer(@Body ColdCallFromServerRequestDTO rawDataRequestDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    /* ColdCall Data post to server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> ColdCallDataToServer(@Body ColdCallDataRequestDTO coldCallDataRequestDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    /* sales tool Data post to server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> SalesToolDataToServer(@Body SalesToolPostDataRequestDTO salesToolPostDataRequestDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    /* Planner Data post to server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> PlannerDataToServer(@Body PlannerDataRequestDTO plannerDataRequestDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    /* Collection Data post to server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> CollectionDataToServer(@Body CollectionDataRequestDTO collectionDataRequestDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    /* Raw data for single screen tvName server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> rawDataForSingleScreenFromServer(@Body RawDataForSingleScreenRequestDTO rawDataForSingleScreenRequestDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    /* CB Amount tvName server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> getCBAmountFromServer(@Body RawDataCBRequestDTO rawDataCBRequestDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    /* SOD EOD Data To Server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> SODEODServiceCall(@Body SODEODRequestDTO sodeodRequestDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    /* CGT Data tvName server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> getCGTDataFromServer(@Body CGTFromServerRequestDTO cgtFromServerRequestDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    /* Product Master tvName server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> getProductMasterFromServer(@Body ProductMasterRequestDTO productMasterRequestDTO, @Header("Authorization") String authHeader, @Header("k1") String k1);

    //Lead - loan scheme and loan product
    @POST("utility/GetDataUtil")
    Call<ResponseBody> getLeadMasterFromServer(@Body LeadMasterRequestDTO leadMasterRequestDTO, @Header("Authorization") String authHeader, @Header("k1") String k1);

    /* Product Master tvName server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> syncCGTDataToServer(@Body CGTServiceRequestDTO cgtServiceRequestDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    /* Document Master tvName server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> getDocumentMasterFromServer(@Body DocumentMasterRequestDTO documentMasterRequestDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    /* Knowledge Bank Master From server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> getKnowledgeBankFromServer(@Body KnowledgeBankRequestDTO documentMasterRequestDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    /* User Login Menu Bank Master From server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> getUserLoginMenuBankFromServer(@Body UserLoginMenuRequestDTO userLoginMenuRequestDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    @POST("utility/GetDataUtil")
    Call<ResponseBody> getLeadDropDownDetailsFromServer(@Body GetLeadDropDownDetailsRequestDTO getLeadDropDownDetailsRequestDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    @POST("utility/GetDataUtil")
    Call<ResponseBody> getLeadDropDownTypeOfProfession(@Body TypeOfProfessionRequestDTO getTypeOfProfessionRequestDTO, @Header("Authorization") String authHeader, @Header("k1") String k1);

    @POST("utility/GetDataUtil")
    Call<ResponseBody> getSPRAT(@Body RATSPRequestDTO ratspRequestDTO, @Header("Authorization") String authHeader, @Header("k1") String k1);

    @POST("utility/GetDataUtil")
    Call<ResponseBody> getTWLGetMakeModelData(@Body GetTWLDataRequestDTO getTWLDataRequestDTO, @Header("Authorization") String authHeader, @Header("k1") String k1);

    @POST("utility/GetDataUtil")
    Call<ResponseBody> eNACH_GetRazorpayFetchToken(@Body MNachRequestDTO mNachRequestDTO, @Header("Authorization") String authHeader, @Header("k1") String k1);

    @POST("utility/GetDataUtil")
    Call<ResponseBody> getPricingInbox(@Body GetPricingInboxRequestDTO getPricingInboxRequestDTO, @Header("Authorization") String authHeader, @Header("k1") String k1);

    @POST("utility/GetDataUtil")
    Call<ResponseBody> getInsertRawDataBag(@Body InsertRawDataBagRequestDTO insertRawDataBagRequestDTO, @Header("Authorization") String authHeader, @Header("k1") String k1);

    @POST("utility/GetDataUtil")
    Call<ResponseBody> getLoanAmountWisePricingDefaultValues(@Body LoanAmountWisePricingDefaultValuesRequestDTO loanAmountWisePricingDefaultValuesRequestDTO, @Header("Authorization") String authHeader, @Header("k1") String k1);

    @POST("utility/GetDataUtil")
    Call<ResponseBody> getCreditApprovalScreenPricing(@Body CreditApprovalScreenPricingrequestDTO creditApprovalScreenPricingrequestDTO, @Header("Authorization") String authHeader, @Header("k1") String k1);

    @POST("utility/GetDataUtil")
    Call<ResponseBody> getSyncWorkFlowData(@Body SyncWorkflowRequestDTO syncWorkflowRequestDTO, @Header("Authorization") String authHeader, @Header("k1") String k1);

    @POST("utility/GetDataUtil")
    Call<ResponseBody> getScreenEditValidation(@Body ScreenEditValidationRequestDTO screenEditValidationRequestDTO, @Header("Authorization") String authHeader, @Header("k1") String k1);

    @POST("utility/GetDataUtil")
    Call<ResponseBody> getStudentGrade(@Body StudentGradeRequestDTO studentGradeRequestDTO, @Header("Authorization") String authHeader, @Header("k1") String k1);

    @POST("utility/GetDataUtil")
    Call<ResponseBody> getTenureInMonths(@Body TenureMonthsRequestDTO studentGradeRequestDTO, @Header("Authorization") String authHeader, @Header("k1") String k1);

    @POST("utility/GetDataUtil")
    Call<ResponseBody> getBreSnsManageResults(@Body BreSnsManageResultsRequestDTO breSnsManageResultsRequestDTO, @Header("Authorization") String authHeader, @Header("k1") String k1);

    @POST("utility/GetDataUtil")
    Call<ResponseBody> getProcessPricingWF(@Body ProcessPricingWFRequestDTO processPricingWFRequestDTO, @Header("Authorization") String authHeader, @Header("k1") String k1);

    @POST("utility/GetDataUtil")
    Call<ResponseBody> getPinCodeMasterData(@Body PinCodeRequestDTO  pinCodeRequestDTO, @Header("Authorization") String authHeader, @Header("k1") String k1);

    @POST("utility/GetDataUtil")
    Call<ResponseBody> getPinCodeAreaMasterData(@Body PinCodeAreaRequestDTO pinCodeAreaRequestDTO, @Header("Authorization") String authHeader, @Header("k1") String k1);

    @POST("utility/GetDataUtil")
    Call<ResponseBody> getNegitiveProfileList(@Body NegitiveProfileListRequestDTO negitiveProfileListRequestDTO, @Header("Authorization") String authHeader, @Header("k1") String k1);


    /* DBR tvName server */
    @POST("utility/GetDataUtil")
    Call<ResponseBody> getDBRFromServer(@Body DBRRequestDTO dbrRequestDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    /* Login service*/
    @POST("Login")
    Call<LoginResponseDTO> loginService(@Body LoginRequestDTO loginRequestDTO,@Header("k1") String k1);

    /* EKYC  Request*/
    @POST("v1/uat/ekyc/enquiry")
    Call<EKYCResponseDTO> EKYCRequest(@Body EKYCRootDTO ekycRootDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);


    /* AADHAAR VAULT SERVICE CALL */
    @POST("AadhaarValut/keygeneration")
    Call<AadhaarVaultResponseDTO> aadhaarVaultServiceCall(@Body AadhaarVaultRequestDTO aadhaarVaultRequestDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);


    /* SALES TOOL SERVICE CALL */
    @POST("sales/calculation")
    Call<SalesToolResponseDTO> salesToolServiceCall(@Body SalesToolRequestDTO salesToolRequestDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);


    /* Initiate SERVICE CALL */
    @POST("payment/AccountSMS")
    Call<InitiateResponseDTO> initiateServiceCall(@Body InitiateRequestDTO initiateRequestDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    /* CIBIL SERVICE CALL */
    @POST("Cibil/enquiry")
    Call<CibilResponseDTO> generateCIBILServiceCall(@Body CibilRequestDTO cibilRequestDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    /* CB MFI SERVICE CALL */
    @POST("CBMFI/Enquiry")
    Call<CBMFIResponseDTO> performCB_MFI_ServiceCall(@Body CBMFIRequestDTO cbMFIRequestDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    /* PAN Validation Request*/
    @POST("PanVerification/WithUserId")
    Call<PanValidationResponseDTO> panValidationServiceCall(@Body PanValidationRequestDTO panValidationRequestDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    /* SEND LOG TO SERVER */
    @POST("logs/add")
    Observable<LogResponseDTO> sendLOGToServer(@Body LogRequestDTO logRequestDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    // TODO: LOGIN SERVICE
    @POST("Login")
    Call<LoginResponseDTO> logInService(@Body LoginRequestDTO loginRequestDTO);

    // TODO: LOGIN SERVICE
    @POST("Login")
    Call<JsonObject> logInLDAPService(@Body LoginNewRequestDTO loginRequestDTO, @Header("k1") String k1);

    // TODO: LOGOUT SERVICE
    @POST("LogOff")
    Call<LogOutResponseDTO> logOutService(@Body LogOutRequestDTO logOutRequestDTO,@Header("ApiKey") String api_key, @Header("k1") String k1);

    // TODO: BEARER TOKEN SERVICE
    @POST("identity/getidentity")
    Call<BearerTokenResponseDTO> getBearerToken(@Body BearerTokenRequestDTO bearerTokenRequestDTO, @Header("k1") String k1);

    // TODO: RESET PASSWORD SERVICE
    @POST("user/resetpassword")
    Call<ResetPasswordResponseDTO> resetPasswordServiceCall(@Body ResetPasswordRequestDTO resetPasswordRequestDTO, @Header("Authorization") String authHeader, @Header("k1") String k1);

    // TODO: CHANGE PASSWORD SERVICE
    @POST("ChangePassword")
    Call<List<ChangePasswordResponseDTO>> changePasswordServiceCall(@Body ChangePasswordRequestDTO changePasswordRequestDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    // TODO: POSIDEX SERVICE
    @POST("Posidex/Enquiry")
    Call<PosidexResponseDTO> getposidexService(@Body PosidexRequestDTO posidexRequestDTO, @Header("Authorization") String authHeader, @Header("k1") String k1);

    @POST("DeliquencyDedupe/Get")
    Call<DeliquencyResponseDTO> getDeliquencyService(@Body DeliquencyRequestDTO deliquencyRequestDTO,  @Header("Authorization") String authHeader,@Header("k1") String k1);

    @POST("utility/GetDataUtil")
    Call<ResponseBody> getDeliquencyPosidexServer(@Body DeliquencyRequestDataFromTable deliquencyRequestDataFromTable, @Header("Authorization") String authHeader, @Header("k1") String k1);

    @POST("Karza/Enquiry")
    Call<KarzaResponseDTO> getKarzaDataFromServer(@Body KarzaRequestDTO karzaRequestDTO, @Header("Authorization") String authHeader,@Header("k1") String k1);

    @POST("IBBCity/Enquiry")
    Call<CityResponseDTO> getCityDataFromServer(@Body CityRequestDTO cityRequestDTO, @Header("Authorization") String authHeader, @Header("k1") String k1);

    @POST("IBBMasters/GetMake")
    Call<GetMakeResponseDTO> getMakeServiceData(@Body GetMakeRequestDTO getMakeRequestDTO, @Header("Authorization") String authHeader, @Header("k1") String k1);

    @POST("IBBMasters/GetModel")
    Call<GetModelResponseDTO> getModelServiceData(@Body GetModelRequest getModelRequest, @Header("Authorization") String authHeader, @Header("k1") String k1);

    @POST("IBBMasters/GetVariant")
    Call<GETVariantResponseDTO> getVariantServiceData(@Body GetVariantRequestDTO variantRequestDTO, @Header("Authorization") String authHeader, @Header("k1") String k1);

    @POST("IBBMasters/GetColor")
    Call<GetColorResponseDTO> getColorServiceData(@Body GETColorRequestDTO colorRequestDTO, @Header("Authorization") String authHeader, @Header("k1") String k1);

    @POST("ramp/enquiry")
    Call<RampResponseDTO> getRampServiceData(@Body RampRequestDTO rawDataRequestDTO, @Header("Authorization") String authHeader, @Header("k1") String k1);

    @POST("Hunterverification/retail")
    Call<HunterResponseDTO> getHunterServiceData(@Body HunterRequestDTO hunterRequestDTO, @Header("Authorization") String authHeader, @Header("k1") String k1);

    @POST("Hunterverification/SME")
    Call<HunterResponseDTO> getHunterNonIndividualServiceData(@Body HunterNonIndividualRequestDTO hunterNonIndividualRequestDTO, @Header("Authorization") String authHeader, @Header("k1") String k1);

    @POST("RazorPayRegistration/Enquiry")
    Call<ENachResponseDTO> getENachServiceData(@Body ENachRequestDTO eNachRequestDTO, @Header("Authorization") String authHeader, @Header("k1") String k1);

    @POST("DeDupe/Enquiry")
    Call<DedupeResponseDTO> getDedupeServiceData(@Body DedupeRequestDTO dedupeRequestDTO, @Header("Authorization") String authHeader, @Header("k1") String k1);

    @POST("CPVEquifax/Enquiry")
    Call<CPVResponseDTO> getCPVServiceData(@Body CPVRequestDTO cpvRequestDTO, @Header("Authorization") String authHeader, @Header("k1") String k1);

    @POST("Esign/Intiation")
    Call<ESignEStampResponseDTO> getEsignEStampData(@Body ESignEStampRequestDTO eSignEStampRequestDTO, @Header("Authorization") String authHeader, @Header("k1") String k1);

    @POST("EsignStatus/Status")
    Call<ESignEStampStatusResponseDTO> getEsignEStampStatusData(@Body ESignEStampStatusRequestDTO eSignEStampStatusRequestDTO, @Header("Authorization") String authHeader, @Header("k1") String k1);

    @POST("Vkyc/Upstream")
    Call<VKYCResponseDTO> getVKYCServiceData(@Body VKYCRequestDTO vkycRequestDTO, @Header("Authorization") String authHeader, @Header("k1") String k1);

    @POST("Vkyc/Downstream")
    Call<DownStreamResponseDTO> getVKYCDownStreamServiceData(@Body DownStreamRequestDTO downStreamRequestDTO, @Header("Authorization") String authHeader, @Header("k1") String k1);

    @POST("RAT/RATAsset")
    Call<RATResponseDTO> getRATServiceData(@Body RATRequestDTO ratRequestDTO, @Header("Authorization") String authHeader, @Header("k1") String k1);

    @POST("DigitalDocs/Genrate")
    Call<DigitalDocResponseDTO> getDigitalDocSanctionLetterServiceData(@Body DigitalDocRequestSanctionLetterRuralDTO digitalDocRequestSanctionLetterRuralDTO, @Header("Authorization") String authHeader, @Header("k1") String k1);

    @POST("DigitalDocs/Genrate")
    Call<DigitalDocResponseDTO> getDigitalDocApplicationFormServiceData(@Body DigitalDocApplicationFormUCRequestDTO digitalDocApplicationFormUCRequestDTO, @Header("Authorization") String authHeader, @Header("k1") String k1);

    @POST("DigitalDocs/Genrate")
    Call<DigitalDocResponseDTO> getDigitalDocHDeedServiceData(@Body DigitaklDocHypothecationDeedTW_UCRequestDTO digitaklDocHypothecationDeedTW_ucRequestDTO, @Header("Authorization") String authHeader, @Header("k1") String k1);

    @POST("IBBPrice/Enquiry")
    Call<IBPResponse> getIBPDataFromServer(@Body IBPRequest ibpRequest, @Header("Authorization") String authHeader, @Header("k1") String k1);
}
