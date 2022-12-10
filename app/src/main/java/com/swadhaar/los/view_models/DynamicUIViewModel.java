package com.swadhaar.los.view_models;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.swadhaar.los.adapter.ClientDetailsAdapter;
import com.swadhaar.los.database.converter.DataTypeConverter;
import com.swadhaar.los.database.entity.ApplicationStatusTable;
import com.swadhaar.los.database.entity.CBCheckTable;
import com.swadhaar.los.database.entity.CGTAttendanceTable;
import com.swadhaar.los.database.entity.CGTTable;
import com.swadhaar.los.database.entity.CIBILTable;
import com.swadhaar.los.database.entity.CMPhotoTable;
import com.swadhaar.los.database.entity.CMFetchTable;
import com.swadhaar.los.database.entity.CashDenominationTable;
import com.swadhaar.los.database.entity.CenterCreationTable;

import com.swadhaar.los.database.entity.CenterMeetingAttendanceTable;
import com.swadhaar.los.database.entity.CenterMeetingCollectionTable;
import com.swadhaar.los.database.entity.CenterMeetingTable;
import com.swadhaar.los.database.entity.ColdCallTable;

import com.swadhaar.los.database.entity.DocumentMasterTable;
import com.swadhaar.los.database.entity.DocumentUploadTable;
import com.swadhaar.los.database.entity.DocumentUploadTableNew;
import com.swadhaar.los.database.entity.DynamicUITable;
import com.swadhaar.los.database.entity.EligibilityTable;
import com.swadhaar.los.database.entity.FetchOtherDayCollectionTable;
import com.swadhaar.los.database.entity.GRTAttendanceTable;
import com.swadhaar.los.database.entity.GRTTable;

import com.swadhaar.los.database.entity.CollectionTable;

import com.swadhaar.los.database.entity.GroupTable;
import com.swadhaar.los.database.entity.HouseVerificationTable;
import com.swadhaar.los.database.entity.InitiatePaymentStatusTable;
import com.swadhaar.los.database.entity.KnowledgeBankTable;
import com.swadhaar.los.database.entity.LeadTable;
import com.swadhaar.los.database.entity.LoanProductCodeTable;
import com.swadhaar.los.database.entity.LoanTable;
import com.swadhaar.los.database.entity.LocationTable;
import com.swadhaar.los.database.entity.MasterTable;
import com.swadhaar.los.database.entity.NetworkStrengthTable;
import com.swadhaar.los.database.entity.OTPVerificationTable;
import com.swadhaar.los.database.entity.PlannerTable;
import com.swadhaar.los.database.entity.ProductMasterTable;
import com.swadhaar.los.database.entity.RawDataTable;
import com.swadhaar.los.database.entity.RoleNameTable;
import com.swadhaar.los.database.entity.SODTable;
import com.swadhaar.los.database.entity.SalesToolTable;
import com.swadhaar.los.database.entity.StageDetailsTable;
import com.swadhaar.los.database.entity.SubmitDataTable;
import com.swadhaar.los.database.entity.TodayCollectionScheduledTable;
import com.swadhaar.los.database.entity.VillageSurveyTable;
import com.swadhaar.los.fragments.LOSBaseFragment;
import com.swadhaar.los.models.BearerTokenRequestDTO;
import com.swadhaar.los.models.BearerTokenResponseDTO;
import com.swadhaar.los.models.CGTFromServerResponseDTO;
import com.swadhaar.los.models.CMCollectionLocalResponseDTO;
import com.swadhaar.los.models.CashCollectionSummaryDTO;
import com.swadhaar.los.models.CenterMeetingAttendanceDTO;
import com.swadhaar.los.models.CenterMeetingCollectionDTO;
import com.swadhaar.los.models.ChangePasswordRequestDTO;
import com.swadhaar.los.models.ChangePasswordResponseDTO;
import com.swadhaar.los.models.DocumentUploadRawdataResponseDTO;
import com.swadhaar.los.models.EKYCResponseDTO;
import com.swadhaar.los.models.EKYCRootDTO;
import com.swadhaar.los.models.EMIDetailsDTO;
import com.swadhaar.los.models.EligibilityByGroupDTO;
import com.swadhaar.los.models.FTOverDueCMDTO;
import com.swadhaar.los.models.FetchOtherDayCMDTO;
import com.swadhaar.los.models.GroupNameRequestDTO;
import com.swadhaar.los.models.InitiateResponseDTO;
import com.swadhaar.los.models.LogOutResponseDTO;
import com.swadhaar.los.models.LoginResponseDTO;
import com.swadhaar.los.models.MultipleSyncResponseDTO;
import com.swadhaar.los.models.NumberOfCoApplicant;
import com.swadhaar.los.models.OTPTriggerDTO;
import com.swadhaar.los.models.OTPTriggerResponseDTO;
import com.swadhaar.los.models.OTPVerifyDTO;
import com.swadhaar.los.models.OTPVerifyResponseDTO;
import com.swadhaar.los.models.OverDueCMDTO;
import com.swadhaar.los.models.ParameterInfo;
import com.swadhaar.los.models.PincodeParameterInfo;
import com.swadhaar.los.models.RawDataRequestDTO;
import com.swadhaar.los.models.RawDataResponseDTO;
import com.swadhaar.los.models.ResetPasswordRequestDTO;
import com.swadhaar.los.models.ResetPasswordResponseDTO;
import com.swadhaar.los.models.RoleNamesRequestDTO;
import com.swadhaar.los.models.RoleNamesResponseDTO;
import com.swadhaar.los.models.SubmitDataDTO;
import com.swadhaar.los.repositories.DynamicUIRepository;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import okhttp3.ResponseBody;

public class DynamicUIViewModel extends ViewModel {

    private LiveData<List<DynamicUITable>> dynamicUITableLiveData;
    private LiveData<List<MasterTable>> masterTableLiveDataList;
    private LiveData<List<ApplicationStatusTable>> applicationStatusTableLiveDataList;
    private LiveData<List<RoleNameTable>> roleNameTableLiveDataList;
    private LiveData<List<CollectionTable>> collectionTableLiveDataList;
    private LiveData<List<LoanTable>> memberLoanDetailTableLiveDataList;
    private LiveData<LoanTable> memberLoanDetailTableLiveData;
    private LiveData<List<VillageSurveyTable>> villageSurveyTableLiveDataList;
    private LiveData<List<CenterCreationTable>> centerCreationTableLiveDataList;
    private LiveData<List<FetchOtherDayCollectionTable>> fetchOtherDayCollectionTableLiveDataList;
    private LiveData<CenterCreationTable> centerCreationTableLiveData;
    private LiveData<List<HouseVerificationTable>> houseVerificationTableLiveDataList;
    private LiveData<HouseVerificationTable> houseVerificationTableLiveData;
    private LiveData<CenterMeetingAttendanceDTO> centerMeetingAttendanceDTOLiveData;
    private LiveData<List<CGTTable>> cgtTableLiveDataList;
    private LiveData<List<CGTAttendanceTable>> cgtAttendanceTableLiveDataList;
    private LiveData<List<GRTAttendanceTable>> grtAttendanceTableLiveDataList;
    private LiveData<List<GRTTable>> grtTableLiveDataList;
    private LiveData<GRTTable> grtTableLiveData;
    private LiveData<List<CGTFromServerResponseDTO.Table>> cgtFromServerLiveDataList;
    private LiveData<List<GroupTable>> groupTableLiveDataList;
    private LiveData<List<List<GroupTable>>> listOfGroupTableLiveDataList;
    private LiveData<List<List<CGTTable>>> listOfCGTTableLiveDataList;
    private LiveData<List<List<GRTTable>>> listOfGRTTableLiveDataList;
    private LiveData<List<List<EligibilityTable>>> listOfEligibilityTableLiveDataList;
    private LiveData<List<EligibilityByGroupDTO>> eligibilityByGroupLiveDataList;
    private LiveData<List<CenterMeetingAttendanceDTO>> centerMeetingAttendanceDTOLiveDataList;
    private LiveData<List<FetchOtherDayCMDTO>> fetchOtherDayCMDTOLiveDataList;
    private LiveData<List<CenterMeetingAttendanceTable>> centerMeetingAttendanceTableLiveDataList;
    private LiveData<List<CenterMeetingCollectionDTO>> centerMeetingCollectionDTOLiveDataList;
    private LiveData<List<EMIDetailsDTO>> EMIDetailsDTOLiveDataList;
    private LiveData<List<CenterMeetingCollectionTable>> centerMeetingCollectionTableLiveDataList;
    private LiveData<List<RawDataResponseDTO.Table>> rawDataFromServerList;
    private LiveData<List<ProductMasterTable>> productMasterTableLiveDataList;
    private LiveData<MasterTable> masterTableLiveData;
    private LiveData<MultipleSyncResponseDTO> multipleSyncResponseDTOLiveData;
    private LiveData<CGTTable> cgtTableLiveData;
    private LiveData<Integer> integerLiveData;
    private LiveData<EKYCResponseDTO> EKYCResponseLiveData;
    private LiveData<OTPTriggerResponseDTO> otpTriggerResponseDTOLiveData;
    private LiveData<InitiateResponseDTO> initiateResponseDTOLiveData;
    private LiveData<OTPVerifyResponseDTO> otpVerifyResponseDTOLiveData;
    private LiveData<List<StageDetailsTable>> myStageLiveData;
    private LiveData<DynamicUITable> dynamicUITableRowLiveData;
    private LiveData<SubmitDataTable> submitDataTableLiveData;
    private LiveData<LocationTable> locationTableLiveData;
    private LiveData<NetworkStrengthTable> networkStrengthTableLiveData;
    private LiveData<List<RawDataTable>> rawTableLiveData;
    private LiveData<List<ColdCallTable>> coldCallTableLiveData;
    private LiveData<List<SalesToolTable>> salesToolTableLiveData;
    private LiveData<List<PlannerTable>> plannerTableLiveDataList;
    private LiveData<SODTable> sodTableLiveData;
    private LiveData<CMPhotoTable> cmCaptionPhotoTableLiveData;
    private LiveData<CMFetchTable> cmFetchTableLiveData;
    private LiveData<PlannerTable> plannerTableLiveData;
    private LiveData<List<LeadTable>> leadTableLiveData;
    private LiveData<List<ClientDetailsAdapter>> clientDetailsAdapterList;
    private LiveData<RawDataTable> updatedRawdataRow;
    private LiveData<RawDataTable> rawDataTableLiveData;
    private LiveData<NumberOfCoApplicant> numberOfCoApplicantLiveData;
    private LiveData<List<DocumentUploadTable>> documentLiveData;
    private LiveData<List<DocumentUploadTableNew>> documentUploadLiveDataList;
    private LiveData<List<DocumentUploadRawdataResponseDTO>> documentUploadRawDataResponseList;
    private LiveData<List<DocumentMasterTable>> documentMasterListLiveData;
    private LiveData<List<KnowledgeBankTable>> knowledgeBankTableListLiveData;
    private LiveData<List<OTPVerificationTable>> otpVerificationLiveData;
    private LiveData<List<CIBILTable>> cibilTableLiveDataList;
    private LiveData<List<TodayCollectionScheduledTable>> todayCollectionScheduledTableLiveDataList;
    private LiveData<List<CenterMeetingTable>> centerMeetingTableLiveDataList;
    private LiveData<List<InitiatePaymentStatusTable>> InitiatePaymentStatusTableListLiveData;
    private LiveData<Boolean> booleanLiveData;
    private LiveData<String> stringLiveData;
    private LiveData<LoanProductCodeTable> loanProductCodeTableLiveData;
    private LiveData<List<OverDueCMDTO>> overDueCMDTOListLiveData;
    private LiveData<List<FTOverDueCMDTO>> ftOverDueCMDTOListLiveData;
    private LiveData<ResponseBody> responseBodyLiveData;
    private LiveData<CashCollectionSummaryDTO> cashCollectionSummaryDTOLiveData;
    private LiveData<CashDenominationTable> cashDenominationTableLiveData;
    private LiveData<CMCollectionLocalResponseDTO> CMCollectionLocalResponseDTOLiveData;
    private LiveData<List<String>> stringListLiveData;
    private DynamicUIRepository dynamicUIRepository;
    private LOSBaseFragment losBaseFragment;
    private LiveData<ProductMasterTable> productMasterTableLiveData;
    private LiveData<LoginResponseDTO> loginResponseDTOLiveData;
    private LiveData<LogOutResponseDTO> logOutResponseDTOLiveData;
    private LiveData<BearerTokenResponseDTO> bearerTokenResponseDTOLiveData;
    private LiveData<List<ChangePasswordResponseDTO>> changePasswordResponseDTOLiveDataList;
    private LiveData<ResetPasswordResponseDTO> resetPasswordResponseDTOLiveData;
    private LiveData<RoleNamesResponseDTO> roleNamesResponseDTOLiveData;
    private LiveData<CBCheckTable> cbCheckTableLiveData;

    private final MutableLiveData<Integer> selected = new MutableLiveData<Integer>();

    public void select(Integer item) {
        selected.setValue(item);
    }

    public LiveData<Integer> getSelected() {
        return selected;
    }

    @Inject
    public DynamicUIViewModel(DynamicUIRepository dynamicUIRepository) {
        this.dynamicUIRepository = dynamicUIRepository;
    }

    public void init(String screenID, String screenName,String loanType,String projectId,String moduleId,
                     String clientId,String userId,String moduleType){
        try{
            // TODO: Condition need to check

            /*if(dynamicUITableLiveData!=null){
                return;
            }*/
            dynamicUITableLiveData=dynamicUIRepository.getDynamicUILiveDataList(screenID, screenName,loanType,projectId,moduleId,clientId,userId,moduleType);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getMetaDataWithCorrelationID(String screenID, String screenName,String loanType,String projectId,String moduleId,
                     String clientId,String userId,String moduleType,String correlationId){
        try{
            dynamicUITableLiveData=dynamicUIRepository.getMetaDataWithCorrelationID(screenID, screenName,loanType,projectId,moduleId,
                    clientId,userId,moduleType,correlationId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void EKYCRequest( EKYCRootDTO ekycRootDTO,DynamicUITable dynamicUITable){
        try{
            EKYCResponseLiveData=dynamicUIRepository.EKYCRequest(ekycRootDTO,dynamicUITable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void generateOTP( OTPTriggerDTO otpTriggerDTO,OTPVerificationTable otpVerificationTable){
        try{
            otpTriggerResponseDTOLiveData=dynamicUIRepository.generateOTP(otpTriggerDTO,otpVerificationTable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void generateCollectionSMS( OTPTriggerDTO otpTriggerDTO){
        try{
            otpTriggerResponseDTOLiveData=dynamicUIRepository.generateCollectionSMS(otpTriggerDTO);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void initiateServiceCall(EMIDetailsDTO emiDetailsDTO,String mobileNumber,String userId){
        try{
            initiateResponseDTOLiveData=dynamicUIRepository.initiateServiceCall(emiDetailsDTO,mobileNumber,userId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void verifyOTP(OTPVerifyDTO otpVerifyDTO,OTPVerificationTable otpVerificationTable){
        try{
            otpVerifyResponseDTOLiveData=dynamicUIRepository.verifyOTP(otpVerifyDTO,otpVerificationTable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getMyStages(String userId,String productId,String loanType,String branchId,String branchGSTCode){
        try{
            myStageLiveData=dynamicUIRepository.getMyStages(userId,productId,loanType,branchId,branchGSTCode);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getApplicationStatusFromServer( String userId, String productId, String loanType, String fromdate, String toDate){
        try{
            applicationStatusTableLiveDataList=dynamicUIRepository.getApplicationStatusFromServer(userId,productId,loanType, fromdate, toDate);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getKnowledgeBankFromServer(List<KnowledgeBankTable> knowledgeBankTableList){
        try{
            knowledgeBankTableListLiveData=dynamicUIRepository.getKnowledgeBankFromServer(knowledgeBankTableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getRoleNamesServiceCall(RoleNamesRequestDTO roleNamesRequestDTO, String userID){
        try{
            roleNameTableLiveDataList=dynamicUIRepository.getRoleNamesServiceCall(roleNamesRequestDTO, userID);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getEligibilityDataFromServer( String centerMeetingDate,String userId){
        try{
            stringLiveData=dynamicUIRepository.getEligibilityDataFromServer(centerMeetingDate,userId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getBranchProductFeatureMasterDataFromServer(String userId){
        try{
            stringLiveData=dynamicUIRepository.getBranchProductFeatureMasterDataFromServer(userId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getCollectionSummarydata(String userId, String loanType) {
        try{
            collectionTableLiveDataList=dynamicUIRepository.getCollectionSummarydataFromServer(userId,loanType);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getDocumentRawData(String clientId,String staffId,String loanType,String connectionString){
        try{
            documentUploadRawDataResponseList=dynamicUIRepository.getDocumentRawData(clientId,staffId,loanType,connectionString);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void downloadDocuments(List<DocumentUploadRawdataResponseDTO> rawdataResponseDTOList,String clientId,String moduleType){
        try{
            stringLiveData=dynamicUIRepository.downloadDocuments(rawdataResponseDTOList,clientId,moduleType);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void downloadDocumentsForKnowledgeBank(KnowledgeBankTable knowledgeBankTable){
        try{
            responseBodyLiveData=dynamicUIRepository.downloadDocumentsForKnowledgeBank(knowledgeBankTable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void imageDownloadFromServer(List<RawDataResponseDTO.Table> rawDataFromServerList){
        try{
            stringLiveData=dynamicUIRepository.imageDownloadFromServer(rawDataFromServerList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getQCReSubmissionDataFromServer(String staffId){
        try{
            stringLiveData=dynamicUIRepository.getQCReSubmissionDataFromServer(staffId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getCenterMeetingDetailsFromServer(String currentDate,String staffId){
        try{
            stringLiveData=dynamicUIRepository.getCenterMeetingDetailsFromServer(currentDate,staffId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getCMDetailsFromServerForFetchOtherDay(List<FetchOtherDayCMDTO> fetchOtherDayCMDTOList,String staffId){
        try{
            stringLiveData=dynamicUIRepository.getCMDetailsFromServerForFetchOtherDay(fetchOtherDayCMDTOList,staffId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getCMDetailsFromServerForPendingOD(String staffId){
        try{
            overDueCMDTOListLiveData=dynamicUIRepository.getCMDetailsFromServerForPendingOD(staffId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getCMDetailsFromServerForPendingFTOD(String staffId){
        try{
            ftOverDueCMDTOListLiveData=dynamicUIRepository.getCMDetailsFromServerForPendingFTOD(staffId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getCenterNamesFromServerForFetchOtherDay(String staffId){
        try{
            stringListLiveData=dynamicUIRepository.getCenterNamesFromServerForFetchOtherDay(staffId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getInitiatePaymentStatusFromServer(String currentDate,String staffId){
        try{
            InitiatePaymentStatusTableListLiveData=dynamicUIRepository.getInitiatePaymentStatusFromServer(currentDate,staffId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

 public void sendLOGToServer(String staffId){
        try{
            stringLiveData=dynamicUIRepository.sendLOGToServer(staffId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void sendCollectionToServer(String staffId,List<CashCollectionSummaryDTO.IndividualCenterCollection> cashCollectionSummaryList){
        try{
            stringLiveData=dynamicUIRepository.sendCollectionToServer(staffId,cashCollectionSummaryList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void sendCollectionToServerNew(String staffId,List<CashCollectionSummaryDTO.IndividualCenterCollection> cashCollectionSummaryList){
        try{
            stringLiveData=dynamicUIRepository.sendCollectionToServerNew(staffId,cashCollectionSummaryList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getMasterTableByUserIdAndLoanType(String userId,String loanType){
        try{
            masterTableLiveDataList=dynamicUIRepository.getMasterTableByUserIdAndLoanType(userId,loanType);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

public void getLoanTableByClientId(String clientId){
        try{
            memberLoanDetailTableLiveDataList=dynamicUIRepository.getLoanTableByClientId(clientId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

public void getLoanTableByCenterId(String centerId, CGTTable cgtTable){
        try{
            memberLoanDetailTableLiveDataList=dynamicUIRepository.getLoanTableByCenterId(centerId,cgtTable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getMasterTableDetailsByCenter(CenterCreationTable centerCreationTable){
        try{
            masterTableLiveDataList=dynamicUIRepository.getMasterTableDetailsByCenter(centerCreationTable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void getLoanTableDetailsByCenter(CenterCreationTable centerCreationTable){
        try{
            memberLoanDetailTableLiveDataList=dynamicUIRepository.getLoanTableDetailsByCenter(centerCreationTable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
public void getMasterTableByCenterId(CGTTable cgtTable){
        try{
            masterTableLiveDataList=dynamicUIRepository.getMasterTableByCenterId(cgtTable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
public void getCGTTableByCenterId(CenterCreationTable centerCreationTable){
        try{
            cgtTableLiveDataList=dynamicUIRepository.getCGTTableByCenterId(centerCreationTable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

public void getCGTTableForDropOut(CenterCreationTable centerCreationTable){
        try{
            cgtTableLiveDataList=dynamicUIRepository.getCGTTableForDropOut(centerCreationTable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getMasterTableByCGTTable(CGTTable cgtTable,String action,String groupName){
        try{
            masterTableLiveDataList=dynamicUIRepository.getMasterTableByCGTTable(cgtTable,action,groupName);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getVillageSurveyTable(String userId,String loanType){
        try{
            villageSurveyTableLiveDataList=dynamicUIRepository.getVillageSurveyTable(userId,loanType);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getVillageList(String userId,String loanType){
        try{
            stringListLiveData=dynamicUIRepository.getVillageList(userId,loanType);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void updateCallTimeStamp(String clientId){
        try{
            stringLiveData=dynamicUIRepository.updateCallTimeStamp(clientId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getAllCenterNamesFromCenterMeetingTable(String userId){
        try{
            stringListLiveData=dynamicUIRepository.getAllCenterNamesFromCenterMeetingTable(userId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void getGroupsFromCenterMeetingTable(String userId,String centerName){
        try{
            stringListLiveData=dynamicUIRepository.getGroupsFromCenterMeetingTable(userId,centerName);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void getMembersFromCenterMeetingTable(String userId,String centerName){
        try{
            eligibilityByGroupLiveDataList=dynamicUIRepository.getMembersFromCenterMeetingTable(userId,centerName);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void getMembersFromCenterMeetingTableForAttendance(String userId,String centerName){
        try{
            centerMeetingAttendanceDTOLiveDataList =dynamicUIRepository.getMembersFromCenterMeetingTableForAttendance(userId,centerName);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void getPhotoFromCMCaptionPhotoTable(String userId,String centerName){
        try{
            cmCaptionPhotoTableLiveData =dynamicUIRepository.getPhotoFromCMCaptionPhotoTable(userId,centerName);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void getGroupListForCollection(String userId,String centerName){
        try{
            stringListLiveData =dynamicUIRepository.getGroupListForCollection(userId,centerName);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
//    public void getGroupListForCollectionByCollectionType(String userId,String centerName,String collectionType){
//        try{
//            stringListLiveData =dynamicUIRepository.getGroupListForCollectionByCollectionType(userId,centerName,collectionType);
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
//    }
    public void getGroupListForCollectionByIsDigitalPayment(String userId,String centerName, boolean isDigitalPayment){
        try{
            stringListLiveData =dynamicUIRepository.getGroupListForCollectionByIsDigitalPayment(userId,centerName,isDigitalPayment);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void getCashCollectionSummaryList(String userId, Date cmDate){
        try{
            cashCollectionSummaryDTOLiveData =dynamicUIRepository.getCashCollectionSummaryList(userId,cmDate);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void getTodayCollectionScheduledList(String userId, Date cmDate){
        try{
            todayCollectionScheduledTableLiveDataList =dynamicUIRepository.getTodayCollectionScheduledList(userId,cmDate);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void getCashCollectionSummaryListByCollectionType(String userId, Date cmDate,String collectionType,String centerName){
        try{
            cashCollectionSummaryDTOLiveData =dynamicUIRepository.getCashCollectionSummaryListByCollectionType(userId,cmDate,collectionType,centerName);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void getCashDenominationTable(String userId, Date cmDate){
        try{
            cashDenominationTableLiveData =dynamicUIRepository.getCashDenominationTable(userId,cmDate);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void getEMIDetailsForCollection(String customerId){
        try{
            EMIDetailsDTOLiveDataList =dynamicUIRepository.getEMIDetailsForCollection(customerId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void getEMIDetailsForDigitalCollection(String customerId){
        try{
            EMIDetailsDTOLiveDataList =dynamicUIRepository.getEMIDetailsForDigitalCollection(customerId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void getMembersFromCenterMeetingTableForCollectionGroupWise(String groupName,String collectionType){
        try{
            centerMeetingCollectionTableLiveDataList =dynamicUIRepository.getMembersFromCenterMeetingTableForCollectionGroupWise(groupName,collectionType);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void getMembersFromCenterMeetingTableForCollectionCenterWise(String centerName,String collectionType){
        try{
            centerMeetingCollectionTableLiveDataList =dynamicUIRepository.getMembersFromCenterMeetingTableForCollectionCenterWise(centerName,collectionType);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void getMembersFromCMCollectionTableForDigitalCollectionGroupWise(String groupName){
        try{
            centerMeetingCollectionTableLiveDataList =dynamicUIRepository.getMembersFromCMCollectionTableForDigitalCollectionGroupWise(groupName);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void getHeadersFromEligibilityTable(){
        try{
            stringListLiveData=dynamicUIRepository.getHeadersFromEligibilityTable();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void getCenterCreationTable(String userId,String loanType){
        try{
            centerCreationTableLiveDataList=dynamicUIRepository.getCenterCreationTable(userId,loanType);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getCenterCreationTableByGRT(GRTTable grtTable){
        try{
            centerCreationTableLiveData=dynamicUIRepository.getCenterCreationTableByGRT(grtTable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getCenterCreationTableByMemberLoanTable(LoanTable loanTable){
        try{
            centerCreationTableLiveData=dynamicUIRepository.getCenterCreationTableByMemberLoanTable(loanTable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getCenterCreationTableByCenterId(String userId,String loanType,String centerId) {
        try{
            centerCreationTableLiveData=dynamicUIRepository.getCenterCreationTableByCenterId(userId,loanType,centerId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void approveGRT(List<GRTTable> grtTableList){
        try{
            stringLiveData=dynamicUIRepository.approveGRT(grtTableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

//    public void getHouseVerificationTable(CGTTable cgtTable,GRTTable grtTable){
//        try{
//            houseVerificationTableLiveDataList=dynamicUIRepository.getHouseVerificationTable(cgtTable,grtTable);
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
//    }
    public void getHouseVerificationTable(String centerId,String loanType, CGTTable cgtTable){
        try{
            houseVerificationTableLiveDataList=dynamicUIRepository.getHouseVerificationTable(centerId,loanType,cgtTable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void getHouseVerificationMemberDetail(String centerId,String loanType){
        try{
            houseVerificationTableLiveData=dynamicUIRepository.getHouseVerificationMemberDetail(centerId,loanType);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void approveOrRejectHouseVerification(HouseVerificationTable houseVerificationTable, String status){
        try{
            houseVerificationTableLiveData=dynamicUIRepository.approveOrRejectHouseVerification(houseVerificationTable, status);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void removeHousePhoto(HouseVerificationTable houseVerificationTable){
        try{
            houseVerificationTableLiveData=dynamicUIRepository.removeHousePhoto(houseVerificationTable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void removeCaptionPhoto(CMPhotoTable cmCaptionPhotoTable){
        try{
            cmCaptionPhotoTableLiveData=dynamicUIRepository.removeCaptionPhoto(cmCaptionPhotoTable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void nextHouseVerification(HouseVerificationTable houseVerificationTable){
        try{
            houseVerificationTableLiveData=dynamicUIRepository.nextHouseVerification(houseVerificationTable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void updateCGTTable(CGTTable cgtTable){
        try{
            cgtTableLiveData=dynamicUIRepository.updateCGTTable(cgtTable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void saveActivitiesData(CGTTable cgtTable){
        try{
            cgtTableLiveData=dynamicUIRepository.saveActivitiesData(cgtTable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void getCGTTable(String userId,String loanType){
        try{
            listOfCGTTableLiveDataList=dynamicUIRepository.getCGTTable(userId,loanType);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getCGTTableByCGTCycle(String cgtCycle,String centerId){
        try{
            cgtTableLiveDataList=dynamicUIRepository.getCGTTableByCGTCycle(cgtCycle,centerId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getCGTAttendanceTable(String cgtCycle,String centerId){
        try{
            cgtAttendanceTableLiveDataList=dynamicUIRepository.getCGTAttendanceTable(cgtCycle,centerId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getGRTAttendanceTable(String centerId){
        try{
            grtAttendanceTableLiveDataList=dynamicUIRepository.getGRTAttendanceTable(centerId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void updateCGTStartSession(String cgtCycle,String centerId, List<CGTTable> cgtTableList){
        try{
            cgtTableLiveDataList=dynamicUIRepository.updateCGTStartSession(cgtCycle,centerId,cgtTableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void updateCGTEndSession(String cgtCycle,String centerId, List<CGTTable> cgtTableList){
        try{
            stringLiveData=dynamicUIRepository.updateCGTEndSession(cgtCycle,centerId,cgtTableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void getGRTTable(String userId,String loanType){
        try{
            listOfGRTTableLiveDataList=dynamicUIRepository.getGRTTable(userId,loanType);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void getCGTDataFromServer(String branchGSTCode,String userId,String loanType){
        try{
            cgtFromServerLiveDataList=dynamicUIRepository.getCGTDataFromServer(branchGSTCode,userId,loanType);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void getGroupTable(CGTTable cgtTable){
        try{
            listOfGroupTableLiveDataList=dynamicUIRepository.getGroupTable(cgtTable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void getRawDataByAdditionalColumn(DynamicUITable dynamicUITable){
        try{
            rawDataTableLiveData=dynamicUIRepository.getRawDataByAdditionalColumn(dynamicUITable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getRawDataByScreenNameAndModuleType(DynamicUITable dynamicUITable){
        try{
            rawDataTableLiveData=dynamicUIRepository.getRawDataByScreenNameAndModuleType(dynamicUITable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void getWorkflowHistory(String userId,String loanType){
        try{
            masterTableLiveDataList=dynamicUIRepository.getWorkflowHistory(userId,loanType);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getMasterTableDetailForPD(String userId,String loanType){
        try{
            masterTableLiveDataList=dynamicUIRepository.getMasterTableDetailForPD(userId,loanType);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void getMetaDataForMultipleScreen(List<RawDataResponseDTO.Table> rawDataFromServerList,
                                             String loanType,String userId,String productId,String currentStage){
        try{
            masterTableLiveDataList=dynamicUIRepository.getMetaDataForMultipleScreen(rawDataFromServerList,loanType,userId,productId,currentStage);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getDataForChildFragment(String screenID, String screenName,String loanType,String projectId,String moduleId,String clientId,String userId){
        try{
            dynamicUITableLiveData=dynamicUIRepository.getDataForChildFragment(screenID, screenName,loanType,projectId,moduleId,clientId,userId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void postPlannerDataToServer(PlannerTable plannerTable, String screenNumber){
        try{
            // TODO: Condition need to check
            dynamicUITableLiveData=dynamicUIRepository.postPlannerDataToServer(plannerTable,screenNumber);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void postColdCallDataToServer(ColdCallTable coldCallTable, String screenNumber){
        try{
            // TODO: Condition need to check
            dynamicUITableLiveData=dynamicUIRepository.postColdCallDataToServer(coldCallTable,screenNumber);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void postSalesToolDataToServer(SalesToolTable salesToolTable, String screenNumber){
        try{
            // TODO: Condition need to check
            dynamicUITableLiveData=dynamicUIRepository.postSalesToolDataToServer(salesToolTable,screenNumber);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void postSubmittedDataForColdLead(SubmitDataTable submitDataTables,SubmitDataDTO submitDataDTO, String screenNumber,LeadTable leadTable){
        try{
            // TODO: Condition need to check
            dynamicUITableLiveData=dynamicUIRepository.postSubmittedDataForColdLead(submitDataTables,submitDataDTO,screenNumber,leadTable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void postSubmittedDataForCollection(SubmitDataTable submitDataTables, SubmitDataDTO submitDataDTO, String screenNumber, CollectionTable collectionTable){
        try{
            // TODO: Condition need to check
            dynamicUITableLiveData=dynamicUIRepository.postSubmittedDataForCollection(submitDataTables,submitDataDTO,screenNumber,collectionTable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void postCollectionDataToServer(CollectionTable collectionTable, String screenNumber){
        try{
            // TODO: Condition need to check
            dynamicUITableLiveData=dynamicUIRepository.postCollectionDataToServer(collectionTable,screenNumber);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void postSubmittedData(SubmitDataTable submitDataTables,SubmitDataDTO submitDataDTO,String submittedValues, String screenNumber,RawDataTable rawDataTable){
        try{
            // TODO: Condition need to check
            dynamicUITableLiveData=dynamicUIRepository.postSubmittedLiveData(submitDataTables,submitDataDTO,submittedValues,screenNumber,rawDataTable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void syncDataToServer(SubmitDataTable submitDataTables,SubmitDataDTO submitDataDTO,String submittedValues, String screenNumber,RawDataTable rawDataTable){
        try{
            submitDataTableLiveData=dynamicUIRepository.syncDataToServer(submitDataTables,submitDataDTO,submittedValues,screenNumber,rawDataTable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void syncSingleVillageSurveyData(VillageSurveyTable villageSurveyTable){
        try{
            submitDataTableLiveData=dynamicUIRepository.syncSingleVillageSurveyData(villageSurveyTable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void syncSingleCenterCreationData(CenterCreationTable centerCreationTable){
        try{
            submitDataTableLiveData=dynamicUIRepository.syncSingleCenterCreationData(centerCreationTable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void syncCGTData(List<CGTTable> cgtTwoCompletedList){
        try{
            stringLiveData=dynamicUIRepository.syncCGTData(cgtTwoCompletedList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getCMDateFromCMTableByCenterName(String centerName){
        try{
            stringLiveData=dynamicUIRepository.getCMDateFromCMTableByCenterName(centerName);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

 public void syncGRTData(List<GRTTable> grtTableList){
        try{
            stringLiveData=dynamicUIRepository.syncGRTData(grtTableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

 public void validateGRT(List<GRTTable> grtTableList,String centerId){
        try{
            stringLiveData=dynamicUIRepository.validateGRT(grtTableList,centerId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

public void syncLoanDetailsToBCM(LoanTable loanTable){
        try{
            submitDataTableLiveData=dynamicUIRepository.syncLoanDetailsToBCM(loanTable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void syncAllScreenByClient(MasterTable masterTable){
        try{
            masterTableLiveData=dynamicUIRepository.syncAllScreenByClient(masterTable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    public void syncAllScreenDataForMultipleClient(List<CGTTable> cgtTwoCompletedList){
        try{
            multipleSyncResponseDTOLiveData=dynamicUIRepository.syncAllScreenDataForMultipleClient(cgtTwoCompletedList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

 public void syncMemberLoanApplicationDetail(LoanTable loanTable){
        try{
            memberLoanDetailTableLiveData=dynamicUIRepository.syncMemberLoanApplicationDetail(loanTable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void updateMasterTableStatus(MasterTable masterTable,String finalStatus){
        try{
            masterTableLiveData=dynamicUIRepository.updateMasterTableStatusNew(masterTable,finalStatus);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void updateMemberLoanDetailTableStatus(LoanTable loanTable, String finalStatus){
        try{
            memberLoanDetailTableLiveData=dynamicUIRepository.updateMemberLoanDetailTableStatus(loanTable,finalStatus);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void updateMasterTableStatusForRejectAndSendback(MasterTable masterTable, String finalStatusRejected) {

        try{
            masterTableLiveData=dynamicUIRepository.updateMasterTableStatusForRejectAndSendback(masterTable,finalStatusRejected);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void updateMasterTableFromPD(MasterTable masterTable,String finalStatus,String actionName){
        try{
            masterTableLiveDataList=dynamicUIRepository.updateMasterTableFromPD(masterTable,finalStatus,actionName);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

 public void getRawDataFromServerForMultipleCustomer(RawDataRequestDTO rawDataRequestDTO, String userId, String loanType){
        try{
            rawDataFromServerList=dynamicUIRepository.getRawDataFromServerForMultipleCustomer(rawDataRequestDTO,userId,loanType);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
 public void getMasterTableByClientId(String clientId){
        try{
            masterTableLiveData=dynamicUIRepository.getMasterTableByClientId(clientId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void getRawDataFromServerForSingleCustomerApplication(String clientId, String userId, String loanType,String productId, String connectionString){
        try{
            rawDataFromServerList=dynamicUIRepository.getRawDataFromServerForSingleCustomerApplication(clientId,userId,loanType,productId,connectionString);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
 public void getRawDataFromServerForSingleCustomer(String clientId, String userId, String loanType,String productId){
        try{
            rawDataFromServerList=dynamicUIRepository.getRawDataFromServerForSingleCustomer(clientId,userId,loanType,productId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    public void insertLog(String methodName,String message,String staffId,String screenNo,String screenName,String clientId,
                          String loanType,String moduleType){
        try{
            dynamicUIRepository.insertLog(methodName,message,staffId,screenNo,screenName,clientId,loanType,moduleType);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void updateSelectedRoleName(String selectedRoleName){
        try{
            dynamicUIRepository.updateSelectedRoleName(selectedRoleName);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void logInService(String userId, String password,boolean ldapValue){
        try{
            loginResponseDTOLiveData=dynamicUIRepository.logInService(userId,password,ldapValue);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void logOutService(String userId){
        try{
            logOutResponseDTOLiveData=dynamicUIRepository.logOutService(userId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

 public void uploadZipFolder(String userId,String filePath,String fileName,String loanType){
        try{
            stringLiveData=dynamicUIRepository.uploadZipFolder(userId,filePath,fileName,loanType);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getBearerToken(BearerTokenRequestDTO bearerTokenRequestDTO){
        try{
            bearerTokenResponseDTOLiveData=dynamicUIRepository.getBearerToken(bearerTokenRequestDTO);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void insertOrUpdateSODData(String userName,String userId,String branchId,String branchGSTcode,boolean isChecked){
        try{
            sodTableLiveData=dynamicUIRepository.insertorUpdateSODData(userName,userId,branchId,branchGSTcode,isChecked);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void insertorUpdatePlannerData(PlannerTable plannerTable,boolean isChecked){
        try{
            plannerTableLiveData=dynamicUIRepository.insertorUpdatePlannerData(plannerTable,isChecked);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

     public void insertOrUpdateCenterCreationData(CenterCreationTable centerCreationTable){
        try{
            centerCreationTableLiveData=dynamicUIRepository.insertOrUpdateCenterCreationData(centerCreationTable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
     public void getGroupNameFromServer(GroupNameRequestDTO groupNameRequestDTO){
        try{
            stringLiveData=dynamicUIRepository.getGroupNameFromServer(groupNameRequestDTO);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
     public void saveCashDenomination(CashDenominationTable cashDenominationTable){
        try{
            cashDenominationTableLiveData=dynamicUIRepository.saveCashDenomination(cashDenominationTable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void resetPasswordServiceCall(ResetPasswordRequestDTO resetPasswordRequestDTO){
        try{
            resetPasswordResponseDTOLiveData=dynamicUIRepository.resetPasswordServiceCall(resetPasswordRequestDTO);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void changePasswordServiceCall(ChangePasswordRequestDTO changePasswordRequestDTO){
        try{
            changePasswordResponseDTOLiveDataList=dynamicUIRepository.changePasswordServiceCall(changePasswordRequestDTO);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void updateDynamicUITable(List<DynamicUITable> viewParametersList, String screenName){
        try{
            // TODO: Condition need to check

            /*if(dynamicUITableLiveData!=null){
                return;
            }*/
            dynamicUITableLiveData=dynamicUIRepository.updateDynamicUITable(viewParametersList,screenName);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void deleteAndInsertNewRecord(List<DynamicUITable> viewParametersList, String screenName){
        try{
             dynamicUITableLiveData=dynamicUIRepository.deleteAndInsertNewRecord(viewParametersList,screenName);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void updatePlusButtonDataInDB(String fieldName, String screenNo,List<DynamicUITable> dynamicUITable){
        try{
            // TODO: Condition need to check

            /*if(dynamicUITableLiveData!=null){
                return;
            }*/
            dynamicUITableLiveData=dynamicUIRepository.updatePlusButtonDataInDB(fieldName,screenNo,dynamicUITable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void clearValuesBasedOnScreen(String screen){
        try{
            dynamicUITableLiveData=dynamicUIRepository.clearValuesBasedOnScreen(screen);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void clearValuesBasedOnScreenTAGInDP(String screen,String FieldTAG){
        try{
            dynamicUITableLiveData=dynamicUIRepository.clearValuesBasedOnScreenTAGInDP(screen,FieldTAG);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void copyValuesBasedOnScreen(List<String> FromfieldNameList,List<String> TofieldNameList,
                                        String fromScreen,String toScreen,String plusSign){
        try{
            dynamicUITableLiveData=dynamicUIRepository.copyValuesBasedOnScreen(FromfieldNameList,TofieldNameList,fromScreen,toScreen,plusSign);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    public void calculateAndUpdate(List<String> FromfieldNameList,List<String> TofieldNameList,
                                        String fromScreen,String toScreen,String newRowTAGName,
                                   String lableTagName,String valueTagName,boolean updateButtonClick
            ,List<DynamicUITable> viewParametersList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.calculateAndUpdate(FromfieldNameList,TofieldNameList,fromScreen,toScreen,
                    newRowTAGName,
                     lableTagName, valueTagName,updateButtonClick,viewParametersList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    public void autoSumFields(DynamicUITable dynamicUITable,String  toFieldTagName,
                                        String fromScreen,String toScreen){
        try{
            dynamicUITableLiveData=dynamicUIRepository.autoSumFields(dynamicUITable,toFieldTagName,fromScreen,toScreen);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void sumOfAllFieldsByFeatureId(DynamicUITable dynamicUITable,List<DynamicUITable> dynamicUIList,
                                          String resultTag){
        try{
            dynamicUITableLiveData=dynamicUIRepository.sumOfAllFieldsByFeatureId( dynamicUITable,dynamicUIList,
                     resultTag);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void GSTCalculationByFeatureId(DynamicUITable dynamicUITable,List<DynamicUITable> dynamicUIList,
                                          String resultTag){
        try{
            dynamicUITableLiveData=dynamicUIRepository.GSTCalculationByFeatureId( dynamicUITable,dynamicUIList,
                     resultTag);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void copyDataFromReferenceCheck(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList, String fromScreen) {
        try{
            dynamicUITableLiveData=dynamicUIRepository.copyDataFromReferenceCheck(dynamicUITable,dynamicUITableList,fromScreen);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

 public void EnableOrDisableByFieldName(List<ParameterInfo> parameterInfoList,List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.EnableOrDisableByFieldName(parameterInfoList,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getMetaDataByScreenName(String screenName,String loanType){
        try{
            dynamicUITableLiveData=dynamicUIRepository.getMetaDataByScreenName(screenName,loanType);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void OralVerificationEnableOrDisable(List<ParameterInfo> parameterInfoList, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.OralVerificationEnableOrDisable(parameterInfoList,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void copyValuesFromScreenToScreen(List<ParameterInfo> fromScreenList,List<ParameterInfo> toScreenList,
            List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.copyValuesFromScreenToScreen(fromScreenList,toScreenList,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void copyValuesFromPersonalDetailScreen(String fromScreen, String toScreen, List<DynamicUITable> dynamicUITableList) {
        try{
            dynamicUITableLiveData=dynamicUIRepository.copyValuesFromPersonalScreenToNomineeScreen(fromScreen,toScreen,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }



    }
    public void EnableOrDisableByFeatureId(String enableFeatureTag,String disableFeatureTag,List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.EnableOrDisableByFeatureId(  enableFeatureTag, disableFeatureTag,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void GSTCalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.GSTCalculation(  dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getModuleType(String loanType,String clientId){
        try{
            stringLiveData=dynamicUIRepository.getModuleType(loanType,clientId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    // TODO: AHL SALARY MODULETYPE VALIDATION
    public void getAHLModuleType(String loanType,String clientId){
        try{
            stringLiveData=dynamicUIRepository.getAHLModuleType(loanType,clientId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void EditableOrNonEditableByFeatureId(String enableFeatureTag,String disableFeatureTag,List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.EditableOrNonEditableByFeatureId(  enableFeatureTag, disableFeatureTag,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void setAddressKYCType(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList,String screenToGetData){
        try{
            dynamicUITableLiveData=dynamicUIRepository.setAddressKYCType(dynamicUITable,dynamicUITableList,screenToGetData);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void setNamesByRadioButtonChanges(DynamicUITable dynamicUITable, String selectedRadioButton, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.setNamesByRadioButtonChanges(dynamicUITable, selectedRadioButton, dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void FixedAssetValidation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.FixedAssetValidation(dynamicUITable, dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void setGeneralIncomeScreenChangesByDropDown(DynamicUITable dynamicUITable, String selectedItem, String selectedRadioButton, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.setGeneralIncomeScreenChangesByDropDown(dynamicUITable, selectedItem,selectedRadioButton, dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void setOtherIncomeScreenChangesByDropDown(DynamicUITable dynamicUITable, String selectedItem, String selectedRadioButton, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.setOtherIncomeScreenChangesByDropDown(dynamicUITable, selectedItem,selectedRadioButton, dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void copyAddressBasedOnDropDown(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList,String screenToGetData){
        try{
            dynamicUITableLiveData=dynamicUIRepository.copyAddressBasedOnDropDown(dynamicUITable,dynamicUITableList,screenToGetData);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void addressDetailValidation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.addressDetailValidation(dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void copyBusinessProofAddressBasedOnDropDown(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.copyBusinessProofAddressBasedOnDropDown(dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }



    public void getCoApplicantDetails(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList,String screenTogetData){
        try{
            dynamicUITableLiveData=dynamicUIRepository.getCoApplicantDetails(dynamicUITable,dynamicUITableList,screenTogetData);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getReferenceNames(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList,String screenTogetData){
        try{
            dynamicUITableLiveData=dynamicUIRepository.getReferenceNames(dynamicUITable,dynamicUITableList,screenTogetData);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getReferenceDetails(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList,String screenTogetData){
        try{
            dynamicUITableLiveData=dynamicUIRepository.getReferenceDetails(dynamicUITable,dynamicUITableList,screenTogetData);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getRawDataFromOtherScreenAndUpdate(List<DynamicUITable> dynamicUITableList,String screenNameToUpdate){
        try{
            dynamicUITableLiveData=dynamicUIRepository.getRawDataFromOtherScreenAndUpdate(dynamicUITableList, screenNameToUpdate);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getCollectionTableData(List<DynamicUITable> dynamicUITableList,DynamicUITable dynamicUITable){
        try{
            dynamicUITableLiveData=dynamicUIRepository.getCollectionTableData(dynamicUITableList, dynamicUITable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getCBCheckTableData(String clientID){
        try{
            cbCheckTableLiveData=dynamicUIRepository.getCBCheckTableData(clientID);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void calculatePurchaseFrequency(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.calculatePurchaseFrequency(dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void businessAssetChildScreenChangesByRawData( List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.businessAssetChildScreenChangesByRawData(dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void loanSuggestionCalculation( List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.loanSuggestionCalculation(dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void calculateNBI(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.calculateNBI(dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void calculateHouseIncomeSummary(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.calculateHouseIncomeSummary(dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void bankingHistoryCalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.bankingHistoryCalculation(dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void ITRCalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.ITRCalculation(dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getMaximumLoanAmount(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            integerLiveData=dynamicUIRepository.getMaximumLoanAmount(dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getTotalEMIForCollection(String customerId){
        try{
            integerLiveData=dynamicUIRepository.getTotalEMIForCollection(customerId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getTotalDueForCollection(String customerId){
        try{
            integerLiveData=dynamicUIRepository.getTotalDueForCollection(customerId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getTotalSavingsCollection(String customerId){
        try{
            integerLiveData=dynamicUIRepository.getTotalSavingsCollection(customerId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getTotalPaidAmtFromCollection(String customerId){
        try{
            integerLiveData=dynamicUIRepository.getTotalPaidAmtFromCollection(customerId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getTotalPaidAmtFromDigitalCollection(String customerId){
        try{
            integerLiveData=dynamicUIRepository.getTotalPaidAmtFromDigitalCollection(customerId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void confirmCenterMeetingCollection(CenterMeetingCollectionTable centerMeetingCollectionTable,boolean isChecked,boolean isSavingsConfirmed){
        try{
            integerLiveData=dynamicUIRepository.confirmCenterMeetingCollection(centerMeetingCollectionTable,isChecked,isSavingsConfirmed);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void confirmDigitalCenterMeetingCollection(CenterMeetingCollectionTable centerMeetingCollectionTable,boolean isChecked,boolean isSavingsConfirmed){
        try{
            integerLiveData=dynamicUIRepository.confirmDigitalCenterMeetingCollection(centerMeetingCollectionTable,isChecked,isSavingsConfirmed);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void confirmSavingsCollection(CenterMeetingCollectionTable centerMeetingCollectionTable,boolean isChecked){
        try{
            integerLiveData=dynamicUIRepository.confirmSavingsCollection(centerMeetingCollectionTable,isChecked);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getMinAndMaximumLoanAmount(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            productMasterTableLiveData=dynamicUIRepository.getMinAndMaximumLoanAmount(dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void mortgageCalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.mortgageCalculation(dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void loanApprovalCalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.loanApprovalCalculation(dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
 public void centerCreationScreenValidation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.centerCreationScreenValidation(dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void cashFlowSummaryCalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.cashFlowSummaryCalculation(dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void houseAssetCalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.houseAssetCalculation(dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void businessAssetMSMECalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.businessAssetMSMECalculation(dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void businessAssetILCalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.businessAssetILCalculation(dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void applicantKYCScreenValidation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.applicantKYCScreenValidation(dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void updateVillageAndCenter(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList,CenterCreationTable centerCreationTable){
        try{
            dynamicUITableLiveData=dynamicUIRepository.updateVillageAndCenter(dynamicUITable,dynamicUITableList,centerCreationTable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void referenceCheckScreenValidation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.referenceCheckScreenValidation(dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void referenceVerificationScreenValidation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.referenceVerificationScreenValidation(dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void generalIncomeScreenValidation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.generalIncomeScreenValidation(dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void otherIncomeSourceScreenValidation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.otherIncomeSourceScreenValidation(dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void getDynamicUITableLocalDB(String screenId){
        try{
            dynamicUITableLiveData=dynamicUIRepository.getDynamicUITableLocalDB(screenId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void applicantLoanProposalScreenChangesByDropdown(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.applicantLoanProposalScreenChangesByDropdown(dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void loanApprovalScreenChangesByDropdown(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.loanApprovalScreenChangesByDropdown(dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void cashFlowScreenChangesByDropdown(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.cashFlowScreenChangesByDropdown(dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void IncomeAssessmentSummaryCalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.IncomeAssessmentSummaryCalculation(dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void ProductEstimateDetailCalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.ProductEstimateDetailCalculation(dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void ServiceEstimateDetailCalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.ServiceEstimateDetailCalculation(dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void ProductEstimateCalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.ProductEstimateCalculation(dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void ServiceEstimateCalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.ServiceEstimateCalculation(dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void salesBillsCalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.salesBillsCalculation(dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void directBusinessExpenseCalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.directBusinessExpenseCalculation(dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void hypothecationMsmeCalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.hypothecationMsmeCalculation(dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void houseLiabilitiesILCalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.houseLiabilitiesILCalculation(dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void houseLiabilitiesMsmeCalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.houseLiabilitiesMsmeCalculation(dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void houseIncomeILCalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.houseIncomeILCalculation(dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void businessLiabilitiesILCalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.businessLiabilitiesILCalculation(dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void businessLiabilitiesMsmeCalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.businessLiabilitiesMsmeCalculation(dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    public void purchaseBillsCalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.purchaseBillsCalculation(dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void calculateSurplus(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.calculateSurplus(dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void calculateFinancialRatios(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.calculateFinancialRatios(dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getCBAmountFromServer(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.getCBAmountFromServer(dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void SODEODServiceCall(SODTable sodTable){
        try{
            sodTableLiveData=dynamicUIRepository.SODEODServiceCall(sodTable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getPendingCentersInJLGcollection(String staffID){
        try{
            centerMeetingCollectionTableLiveDataList =dynamicUIRepository.getPendingCentersInJLGcollection(staffID);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getProductMasterFromServer(String productId,String bcId){
        try{
            productMasterTableLiveDataList=dynamicUIRepository.getProductMasterFromServer(productId,bcId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void calculateLoanProposal(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.calculateLoanProposalNew(dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void panValidationServiceCall(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.panValidationServiceCall(dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getIFSCDataFromServer(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.getIFSCDataFromServer(dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void aadhaarVaultServiceCall(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.aadhaarVaultServiceCall(dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void generateCIBILServiceCall(CIBILTable cibilTable, String staffId ){
        try{
            stringLiveData=dynamicUIRepository.generateCIBILServiceCall(cibilTable,staffId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void performCB_MFI_ServiceCall(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList,
                                          String staffId, String loanType ){
        try{
            stringLiveData=dynamicUIRepository.performCB_MFI_ServiceCall(dynamicUITable,dynamicUITableList, staffId, loanType);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void calculateLoanProposalFinalPD(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList,boolean loanAmountChanges ){
        try{
            dynamicUITableLiveData=dynamicUIRepository.calculateLoanProposalFinalPDNew(dynamicUITable,dynamicUITableList,loanAmountChanges);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void calculateAvgSales(List<DynamicUITable> dynamicUITableList, DynamicUITable dynamicUITable,
                                  String tag,String tagToDisplayResult,int totalNo){
        try{
            dynamicUITableLiveData=dynamicUIRepository.calculateAvgSales(  dynamicUITableList,  dynamicUITable,
                     tag, tagToDisplayResult, totalNo);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    public void changePinCodeFields(List<PincodeParameterInfo> parameterInfoList, List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.changePinCodeFields(parameterInfoList,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void changeSpinnerList(String FieldTag,String[] spinnerItemList,String screenNo,List<DynamicUITable> dynamicUITableList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.changeSpinnerList(FieldTag,
                    DataTypeConverter.someObjectListToString(spinnerItemList),screenNo,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void insertGPSLocation(LocationTable locationTable){
        try{
            locationTableLiveData=dynamicUIRepository.insertGPS(locationTable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void insertNetworkStrength(NetworkStrengthTable networkStrengthTable ){
        try{
            networkStrengthTableLiveData=dynamicUIRepository.insertNetworkStrength(networkStrengthTable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void AddMembersInGroupTable(List<MasterTable> masterTableList,String groupName,String groupId){
        try{
            groupTableLiveDataList=dynamicUIRepository.AddMembersInGroupTable(masterTableList,groupName,groupId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void deleteMembersInGroupTable(List<MasterTable> masterTableList,String groupName,String groupId){
        try{
            groupTableLiveDataList=dynamicUIRepository.deleteMembersInGroupTable(masterTableList,groupName,groupId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void insertAndDeleteGroupTableListByMemberId(List<GroupTable> groupTableList){
        try{
            groupTableLiveDataList=dynamicUIRepository.insertAndDeleteGroupTableListByMemberId(groupTableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
  public void updateCGTTableAttendance(List<CGTAttendanceTable> cgtAttendanceTableList,CGTTable cgtTable){
        try{
            cgtAttendanceTableLiveDataList=dynamicUIRepository.updateCGTTableAttendance(cgtAttendanceTableList,cgtTable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

  public void updateGRTTableAttendance(List<GRTAttendanceTable> grtAttendanceTableList,GRTTable grtTable){
        try{
            grtAttendanceTableLiveDataList=dynamicUIRepository.updateGRTTableAttendance(grtAttendanceTableList,grtTable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

  public void updateCenterMeetingAttendance(List<CenterMeetingAttendanceDTO> centerMeetingAttendanceDTOList, CMPhotoTable cmCaptionPhotoTable){
        try{
            centerMeetingAttendanceDTOLiveDataList =dynamicUIRepository.updateCenterMeetingAttendance(centerMeetingAttendanceDTOList,cmCaptionPhotoTable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void uploadCMCaptionImageToServer(File file, String fileName, String staffId){
        try{
            stringLiveData=dynamicUIRepository.uploadCMCaptionImageToServer(file,fileName,staffId);
        }catch (Exception ex){
            Log.e("TAG", ex.toString());
        }
    }

    public void saveCenterMeetingDetails(List<FetchOtherDayCMDTO> fetchOtherDayCMDTOList){
        try{
            fetchOtherDayCMDTOLiveDataList =dynamicUIRepository.saveCenterMeetingDetails(fetchOtherDayCMDTOList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

  public void saveEMIDetailsCenterMeetingCollection(List<EMIDetailsDTO> emiDetailsDTOList){
        try{
            EMIDetailsDTOLiveDataList =dynamicUIRepository.saveEMIDetailsCenterMeetingCollection(emiDetailsDTOList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void saveEMIDetailsCenterMeetingDigitalCollection(List<EMIDetailsDTO> emiDetailsDTOList){
        try{
            EMIDetailsDTOLiveDataList =dynamicUIRepository.saveEMIDetailsCenterMeetingDigitalCollection(emiDetailsDTOList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

  public void syncCashCollectionSummary(List<CashCollectionSummaryDTO.IndividualCenterCollection> cashCollectionSummaryList){
        try{
            stringLiveData =dynamicUIRepository.syncCashCollectionSummary(cashCollectionSummaryList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

  public void checkAttendance(String centerName){
        try{
            centerMeetingAttendanceTableLiveDataList =dynamicUIRepository.checkAttendance(centerName);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

  public void insertStaffActivity(String centerName,String staffId,String activity,int status){
        try{
            stringLiveData =dynamicUIRepository.insertStaffActivity(centerName,staffId,activity,status);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

  public void saveCenterMeetingCollection(String centerName,String collectionType){
        try{
            CMCollectionLocalResponseDTOLiveData =dynamicUIRepository.saveCenterMeetingCollection(centerName,collectionType);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void saveCenterMeetingDigitalCollection(String centerName){
        try{
            CMCollectionLocalResponseDTOLiveData =dynamicUIRepository.saveCenterMeetingDigitalCollection(centerName);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void checkCenterMeetingCollection(String centerName){
        try{
            CMCollectionLocalResponseDTOLiveData =dynamicUIRepository.checkCenterMeetingCollection(centerName);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getFetchOtherDayCollectionTable(String userId){
        try{
            fetchOtherDayCollectionTableLiveDataList=dynamicUIRepository.getFetchOtherDayCollectionTable(userId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void updateCGTRejected(List<CGTTable> cgtTableList){
        try{
            cgtTableLiveDataList=dynamicUIRepository.updateCGTRejected(cgtTableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void deleteDropOutCustomers(List<CGTTable> cgtTableList){
        try{
            cgtTableLiveDataList=dynamicUIRepository.deleteDropOutCustomers(cgtTableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void insertEligibleLoans(List<List<EligibilityTable>> listOfEligibilityList){
        try{
            stringLiveData=dynamicUIRepository.insertEligibleLoans(listOfEligibilityList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void insertLoanProductCodeTable(String loanProductCode , String loanProductName){
        try{
            loanProductCodeTableLiveData=dynamicUIRepository.insertLoanProductCodeTable(loanProductCode , loanProductName);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void insertRawData(RawDataTable rawDataTable,List<DynamicUITable> viewParametersList,DynamicUITable dynamicUITable,boolean dataFromServer){
        try{
            rawTableLiveData=dynamicUIRepository.insertRawData(rawDataTable, viewParametersList,dynamicUITable,dataFromServer);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void salesToolApiCall(RawDataTable rawDataTable,List<DynamicUITable> viewParametersList,DynamicUITable dynamicUITable,boolean dataFromServer){
        try{
            rawTableLiveData=dynamicUIRepository.salesToolApiCall(rawDataTable, viewParametersList,dynamicUITable,dataFromServer);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void insertOrUpdateParentRawData(RawDataTable rawDataTable,List<DynamicUITable> viewParametersList,DynamicUITable dynamicUITable){
        try{
            rawTableLiveData=dynamicUIRepository.insertOrUpdateParentRawDataObserver(rawDataTable, viewParametersList,dynamicUITable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void updateRawDataBag(RawDataTable rawDataTable,List<DynamicUITable> viewParametersList,DynamicUITable dynamicUITable,
                                 boolean isDataFromServer){
        try{
            updatedRawdataRow=dynamicUIRepository.updateRawDataBag(rawDataTable, viewParametersList,dynamicUITable,isDataFromServer);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void getSODDataFromLocalDB(String userId){
        try{
            sodTableLiveData=dynamicUIRepository.getSODDataFromLocalDB(userId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void getCMFetchDataFromLocalDB(String userId,String date){
        try{
            cmFetchTableLiveData=dynamicUIRepository.getCMFetchDataFromLocalDB(userId,date);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void getPlannerDataFromLocalDB(String userId, String clientId){
        try{
            plannerTableLiveData=dynamicUIRepository.getPlannerataFromLocalDB(userId, clientId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getPlannerDataByPurposeAndCurrentDate(String userId, String purpose){
        try{
            plannerTableLiveData=dynamicUIRepository.getPlannerDataByPurposeAndCurrentDate(userId, purpose);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void getLeadRawData(String screenNo,String userId){
        try{
            rawTableLiveData=dynamicUIRepository.getLeadRawData(screenNo,userId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getColdCallingData(String screenNo,String userId, String loanType){
        try{
            coldCallTableLiveData=dynamicUIRepository.getColdCallingData(screenNo,userId,loanType);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getSalesToolData(String screenNo,String userId, String loanType){
        try{
            salesToolTableLiveData=dynamicUIRepository.getSalesToolData(screenNo,userId,loanType);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getPlannerData(String screenNo,String userId, String loanType){
        try{
            plannerTableLiveDataList=dynamicUIRepository.getPlannerData(screenNo,userId, loanType);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getLeadData(String screenNo,String userId, String loanType){
        try{
            leadTableLiveData=dynamicUIRepository.getLeadData(screenNo,userId,loanType);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getCollectiondata(String screenNo,String userId) {
        try{

            collectionTableLiveDataList=dynamicUIRepository.getCollectiondata(screenNo,userId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void getLeadTableDataFromServer(String screenNo,String screenName,String loanType,String userId,
                                         DynamicUITable dynamicUITable,List<DynamicUITable> dynamicUITableList){
        try{
            leadTableLiveData=dynamicUIRepository.getLeadTableDataFromServer(screenNo,screenName,loanType,userId,
                    dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getLeadRawDataFromServer(String screenNo,String screenName,String loanType,String userId,
                                         DynamicUITable dynamicUITable,List<DynamicUITable> dynamicUITableList){
        try{
            rawTableLiveData=dynamicUIRepository.getLeadRawDataFromServer(screenNo,screenName,loanType,userId,
                    dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getColdCallDataFromServer(String screenNo, String screenName, String loanType,
                                          String userId, DynamicUITable dynamicUITable,
                                          List<DynamicUITable> dynamicUITableList){
        try{
            stringLiveData=dynamicUIRepository.getColdCallDataFromServer(screenNo,screenName,loanType,userId,
                    dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getRawDataForAllClient(List<String> screenNoList,List<String> clientIdList,String userId){
        try{
            rawTableLiveData=dynamicUIRepository.getRawDataForAllClient(screenNoList,clientIdList,userId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void getRawDataForSingleClient(List<String> screenNameList,String clientId,String loanType,String moduleType){
        try{
            rawTableLiveData=dynamicUIRepository.getRawDataForSingleClient(screenNameList,clientId,loanType,moduleType);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getRawDataForCoApplicantSection(List<String> screenNameList,List<String> screenNoList,String clientId,
                                                String loanType,String moduleType, Context context,
                                                ClientDetailsAdapter.ClientDetailsInterface clientDetailsInterface){
        try{
            clientDetailsAdapterList=dynamicUIRepository.getRawDataForCoApplicantSection(screenNameList,screenNoList,clientId,loanType,moduleType,context,
                    clientDetailsInterface);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void getNoOfCoApplicants(String screenName, String clientId, String userId,
                                    String loanType,String fieldTag){
        try{
            numberOfCoApplicantLiveData=dynamicUIRepository.getNoOfCoApplicants(screenName,clientId,userId,loanType,fieldTag);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getRawData(String screenNo,String clientId,String moduleType){
        try{
            rawTableLiveData=dynamicUIRepository.getRawData(screenNo,clientId,moduleType);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    public void getSpouseName(DynamicUITable dynamicUITable, String fromScreenNo, List<DynamicUITable> viewParametersList){
        try{
            dynamicUITableLiveData=dynamicUIRepository.getSpouseName(dynamicUITable, fromScreenNo, viewParametersList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

   /* public void getApplicantKycID(String fromScreenNo, String toScreenNO, String additional, String clientID, String moduleType) {
        try{
            dynamicUITableLiveData=dynamicUIRepository.getApplicantKycID( fromScreenNo, toScreenNO, additional,clientID,moduleType);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }*/

    public void getMultipleRawData(List<String> screenNoS,String clientId){
        try{
            rawTableLiveData=dynamicUIRepository.getMultipleRawData(screenNoS,clientId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    // TODO: 11-11-2019
    public void getProductMasterTableData(String screen_id, String clientID, List<DynamicUITable> viewParametersList) {
        try{
            productMasterTableLiveData=dynamicUIRepository.getProductMasterTableData( screen_id, clientID, viewParametersList);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public void loanAmountValidation(DynamicUITable dynamicUITable,List<DynamicUITable> dynamicUITableList) {
        try{
            dynamicUITableRowLiveData=dynamicUIRepository.loanAmountValidation( dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public void ageValidationJLG(DynamicUITable dynamicUITable,List<DynamicUITable> dynamicUITableList) {
        try{
            dynamicUITableRowLiveData=dynamicUIRepository.ageValidationJLG( dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public void ageValidationJLG_Radio(DynamicUITable dynamicUITable,List<DynamicUITable> dynamicUITableList) {
        try{
            dynamicUITableRowLiveData=dynamicUIRepository.ageValidationJLG_Radio( dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public void deDupValidationforMonthInPD(DynamicUITable dynamicUITable,List<DynamicUITable> dynamicUITableList,String month_datepicker) {
        try{
            dynamicUITableRowLiveData=dynamicUIRepository.deDupValidationforMonthInPD( dynamicUITable,dynamicUITableList,month_datepicker);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public void deDupeValidationForAllScreen(DynamicUITable dynamicUITable,List<DynamicUITable> dynamicUITableList) {
        try{
            dynamicUITableRowLiveData=dynamicUIRepository.deDupeValidationForAllScreen( dynamicUITable,dynamicUITableList);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }


    public void getApplicantKycRawData(String fromScreenName, String toScreenNO, String fieldTag, String clientID) {
        try{
            rawTableLiveData=dynamicUIRepository.getApplicantKycRawData( fromScreenName, fieldTag,clientID);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public void getApplicantKycRawData(String fromScreenName,String fieldTag, String clientID) {
        try{
            rawTableLiveData=dynamicUIRepository.getApplicantKycRawData( fromScreenName, fieldTag,clientID);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
    public void getRawDataForChildFragment(String screenName,String clientId,String fieldTag, String loanType){
        try{
            updatedRawdataRow=dynamicUIRepository.getRawDataForChildFragment(screenName,clientId,fieldTag, loanType);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void getTagNameList(String screenName,String clientId,String moduleType,String correlationId){
        try{
            rawTableLiveData=dynamicUIRepository.getTagNameList(screenName,clientId,moduleType,correlationId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getDocumentUploadData(String clientId,String loanType){
        try{
            documentLiveData=dynamicUIRepository.getDocumentUploadData(clientId,loanType);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getDocumentUploadHeader(String clientId,String loanType,boolean isHeader){
        try{
            stringListLiveData=dynamicUIRepository.getDocumentUploadHeader(clientId,loanType,isHeader);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getDocumentUploadSubHeader(String clientId,String loanType,boolean isHeader){
        try{
            documentUploadLiveDataList=dynamicUIRepository.getDocumentUploadSubHeader(clientId,loanType,isHeader);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void uploadDocumentsToServer(DocumentUploadTableNew documentUploadTableNew){
        try{
            documentUploadLiveDataList=dynamicUIRepository.uploadDocumentsToServer(documentUploadTableNew);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void sendDocumentUploadRawData(DocumentUploadTableNew documentUploadTableNew){
        try{
            documentUploadLiveDataList=dynamicUIRepository.uploadDocumentsToServer(documentUploadTableNew);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void insertNewCapturedImageInTable(DocumentUploadTableNew documentUploadTableNewImage){
        try{
            documentUploadLiveDataList=dynamicUIRepository.insertNewCapturedImageInTable(documentUploadTableNewImage);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void insertNewImageListInTable(List<DocumentUploadTableNew> documentUploadTableNewList){
        try{
            documentUploadLiveDataList=dynamicUIRepository.insertNewImageListInTable(documentUploadTableNewList);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void removeCapturedImageFromTable(DocumentUploadTableNew documentUploadTableNewImage){
        try{
            documentUploadLiveDataList=dynamicUIRepository.removeCapturedImageFromTable(documentUploadTableNewImage);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void removeCapturedImageFromTableJLG(DocumentUploadTableNew documentUploadTableNewImage){
        try{
            documentUploadLiveDataList=dynamicUIRepository.removeCapturedImageFromTableJLG(documentUploadTableNewImage);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getDocumentByDocumentName(DocumentUploadTableNew documentUploadTableNewImage){
        try{
            documentUploadLiveDataList=dynamicUIRepository.getDocumentByDocumentName(documentUploadTableNewImage);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getOTPVerificationData(String clientId,String loanType){
        try{
            otpVerificationLiveData=dynamicUIRepository.getOTPVerificationData(clientId,loanType);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getCIBILTableListFromDB(String clientId,String loanType){
        try{
            cibilTableLiveDataList=dynamicUIRepository.getCIBILTableListFromDB(clientId,loanType);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void checkKYCAndPersonalDetailForCIBIL(String clientId,String loanType){
        try{
            cibilTableLiveDataList=dynamicUIRepository.checkKYCAndPersonalDetailForCIBIL(clientId,loanType);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    public void confirmCashCollectionSummary(CashCollectionSummaryDTO.IndividualCenterCollection individualCenterCollection) {
        try{
            dynamicUIRepository.confirmCashCollectionSummary(individualCenterCollection);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getDocumentMasterFromServer(String productId,String projectId){
        try{
            documentMasterListLiveData=dynamicUIRepository.getDocumentMasterFromServer(productId,projectId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void getKnowledgeBankFromServer(String productId,String projectId){
        try{
            knowledgeBankTableListLiveData=dynamicUIRepository.getKnowledgeBankFromServer(productId,projectId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void GetSpinnerItemFromDB(String fieldName, String fromScreen,String selectedItem){
        try{
//            dynamicUITable=dynamicUIRepository.GetSpinnerItemFromDB(fieldName,fromScreen,selectedItem);
            dynamicUIRepository.GetSpinnerItemFromDB(fieldName,fromScreen,selectedItem);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public List<String> GetSpinnerItemListFromDB(){
        List list=null;
        try{
            list= dynamicUIRepository.GetSpinnerItemListFromDB();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return list;
    }

    public LiveData<List<DynamicUITable>> getDynamicUITableLiveData() {
        return dynamicUITableLiveData;
    }

    public LiveData<List<MasterTable>> getmasterTableLiveDataList() {
        return masterTableLiveDataList;
    }

    public LiveData<List<CollectionTable>> getCollectionTableLiveData() {
        return collectionTableLiveDataList;
    }

      public LiveData<List<ProductMasterTable>> getProductMasterTableLiveDataList() {
        return productMasterTableLiveDataList;
    }

    public LiveData<MasterTable> getmasterTableLiveData() {
        return masterTableLiveData;
    }

    public LiveData<List<StageDetailsTable>> getMyStageLiveData() {
        return myStageLiveData;
    }

    public LiveData<List<ApplicationStatusTable>> getCustometviewLiveData() {
        return applicationStatusTableLiveDataList;
    }


    public LiveData<List<RoleNameTable>> getRoleNamesLiveData() {
        return roleNameTableLiveDataList;
    }
    /*public LiveData<DynamicUITable> getDynamicUITable() {
        return dynamicUITable;
    } */

    public LiveData<DynamicUITable> getDynamicUITable() {
        return dynamicUIRepository.getDynamicUITable();
    }

    public LiveData<SubmitDataTable> getSubmitDataTableLiveData() {
        return submitDataTableLiveData;
    }

    public LiveData<LocationTable> getLocationTableLiveData() {
        return locationTableLiveData;
    }

    public LiveData<CBCheckTable> getCBCheckTableLiveData() {
        return cbCheckTableLiveData;
    }

    public LiveData<List<RawDataTable>> getRawTableLiveData() {
        return rawTableLiveData;
    }
    
    public LiveData<List<ColdCallTable>> getColdCallTableLiveData() {
        return coldCallTableLiveData;
    }

    public LiveData<List<SalesToolTable>> getSalesToolTableLiveData() {
        return salesToolTableLiveData;
    }
    public LiveData<List<PlannerTable>> getPlannerTableLiveDataList() {
        return plannerTableLiveDataList;
    }

    public LiveData<SODTable> getSODTableLiveData() {
        return sodTableLiveData;
    }

    public LiveData<CMPhotoTable> getCMCaptionPhotoTableLiveData() {
        return cmCaptionPhotoTableLiveData;
    }

    public LiveData<CMFetchTable> getCMFetchTableLiveData() {
        return cmFetchTableLiveData;
    }

    public LiveData<PlannerTable> getPlannerTableLiveData() {
        return plannerTableLiveData;
    }

    public LiveData<List<LeadTable>> getLeadTableLiveData() {
        return leadTableLiveData;
    }

    public LiveData<ProductMasterTable> getProductMasterTableLiveData(){
        return productMasterTableLiveData;
    }

    public LiveData<List<DocumentUploadTable>> getDocumentUploadLiveData() {
        return documentLiveData;
    }

    public LiveData<List<OTPVerificationTable>> getOTPVerificationLiveData() {
        return otpVerificationLiveData;
    }

    public LiveData<List<ClientDetailsAdapter>> getClientDetailsAdapterList() {
        return clientDetailsAdapterList;
    }

    public LiveData<RawDataTable> getUpdatedRawdataRow() {
        return updatedRawdataRow;
    }

    public LiveData<NumberOfCoApplicant> getNumberOfCoApplicantLiveData() {
        return numberOfCoApplicantLiveData;
    }

    public LiveData<EKYCResponseDTO> getEKYCResponseLiveData() {
        return EKYCResponseLiveData;
    }

    public LiveData<OTPTriggerResponseDTO> getOtpTriggerResponseDTOLiveData() {
        return otpTriggerResponseDTOLiveData;
    }

    public LiveData<InitiateResponseDTO> getInitiateResponseDTOLiveData() {
        return initiateResponseDTOLiveData;
    }

    public LiveData<OTPVerifyResponseDTO> getOtpVerifyResponseDTOLiveData() {
        return otpVerifyResponseDTOLiveData;
    }

    public LiveData<String> getStringLiveData(){
        return stringLiveData;
    }

    public LiveData<List<OverDueCMDTO>> getOverDueCMDTOListLiveData() {
        return overDueCMDTOListLiveData;
    }

    public LiveData<List<FTOverDueCMDTO>> getFTOverDueCMDTOListLiveData() {
        return ftOverDueCMDTOListLiveData;
    }
    public LiveData<ResponseBody> getResponseBodyLiveData(){
        return responseBodyLiveData;
    }
    public LiveData<DynamicUITable> getDynamicUITableRowLiveData(){
        return dynamicUITableRowLiveData;
    }

    public LiveData<Integer> getIntegerLiveData() {
        return integerLiveData;
    }

    public LiveData<List<DocumentMasterTable>> getDocumentMasterListLiveData() {
        return documentMasterListLiveData;
    }

    public LiveData<List<DocumentUploadTableNew>> getDocumentUploadLiveDataList() {
        return documentUploadLiveDataList;
    }

    public LiveData<List<String>> getStringListLiveData() {
        return stringListLiveData;
    }

    public LiveData<LoginResponseDTO> getLoginResponseDTOLiveData() {
        return loginResponseDTOLiveData;
    }

    public LiveData<LogOutResponseDTO> getLogOutResponseDTOLiveData() {
        return logOutResponseDTOLiveData;
    }

    public LiveData<Boolean> getBooleanLiveData() {
        return booleanLiveData;
    }

    public LiveData<BearerTokenResponseDTO> getBearerTokenResponseDTOLiveData() {
        return bearerTokenResponseDTOLiveData;
    }

    public LiveData<List<DocumentUploadRawdataResponseDTO>> getDocumentUploadRawDataResponseList() {
        return documentUploadRawDataResponseList;
    }

    public LiveData<List<ChangePasswordResponseDTO>> getChangePasswordResponseDTOLiveDataList() {
        return changePasswordResponseDTOLiveDataList;
    }

    public LiveData<ResetPasswordResponseDTO> getResetPasswordResponseDTOLiveData() {
        return resetPasswordResponseDTOLiveData;
    }

    public LiveData<List<RawDataResponseDTO.Table>> getRawDataFromServerList() {
        return rawDataFromServerList;
    }

    public LiveData<RawDataTable> getRawDataTableLiveData() {
        return rawDataTableLiveData;
    }

    public LiveData<List<VillageSurveyTable>> getVillageSurveyTableLiveDataList() {
        return villageSurveyTableLiveDataList;
    }

    public LiveData<List<CenterCreationTable>> getCenterCreationTableLiveDataList() {
        return centerCreationTableLiveDataList;
    }

    public LiveData<List<FetchOtherDayCollectionTable>> getFetchOtherDayCollectionTableLiveDataList() {
        return fetchOtherDayCollectionTableLiveDataList;
    }

    public LiveData<List<CGTTable>> getCgtTableLiveDataList() {
        return cgtTableLiveDataList;
    }

    public LiveData<CGTTable> getCgtTableLiveData() {
        return cgtTableLiveData;
    }

    public LiveData<List<GroupTable>> getGroupTableLiveDataList() {
        return groupTableLiveDataList;
    }

    public LiveData<List<List<GroupTable>>> getListOfGroupTableLiveDataList() {
        return listOfGroupTableLiveDataList;
    }

    public LiveData<List<HouseVerificationTable>> getHouseVerificationTableLiveDataList() {
        return houseVerificationTableLiveDataList;
    }

    public LiveData<CenterMeetingAttendanceDTO> getCenterMeetingAttendanceDTOLiveData() {
        return centerMeetingAttendanceDTOLiveData;
    }

    public LiveData<HouseVerificationTable> getHouseVerificationTableLiveData() {
        return houseVerificationTableLiveData;
    }

    public LiveData<List<GRTTable>> getGrtTableLiveDataList() {
        return grtTableLiveDataList;
    }

    public LiveData<List<CGTFromServerResponseDTO.Table>> getCgtFromServerLiveDataList() {
        return cgtFromServerLiveDataList;
    }

    public LiveData<CenterCreationTable> getCenterCreationTableLiveData() {
        return centerCreationTableLiveData;
    }

    public LiveData<GRTTable> getGrtTableLiveData() {
        return grtTableLiveData;
    }

    public LiveData<List<LoanTable>> getMemberLoanDetailTableLiveDataList() {
        return memberLoanDetailTableLiveDataList;
    }

    public LiveData<LoanTable> getMemberLoanDetailTableLiveData() {
        return memberLoanDetailTableLiveData;
    }

    public LiveData<List<List<CGTTable>>> getListOfCGTTableLiveDataList() {
        return listOfCGTTableLiveDataList;
    }

    public LiveData<List<List<GRTTable>>> getListOfGRTTableLiveDataList() {
        return listOfGRTTableLiveDataList;
    }

    public LiveData<List<List<EligibilityTable>>> getListOfEligibilityTableLiveDataList() {
        return listOfEligibilityTableLiveDataList;
    }

    public LiveData<List<CIBILTable>> getCibilTableLiveDataList() {
        return cibilTableLiveDataList;
    }

    public LiveData<List<TodayCollectionScheduledTable>> getTodayCollectionScheduledTableLiveDataList() {
        return todayCollectionScheduledTableLiveDataList;
    }

    public LiveData<List<CenterMeetingTable>> getCenterMeetingTableLiveDataList() {
        return centerMeetingTableLiveDataList;
    }

    public LiveData<List<InitiatePaymentStatusTable>> getInitiatePaymentStatusTableLiveDataList() {
        return InitiatePaymentStatusTableListLiveData;
    }

    public LiveData<List<EligibilityByGroupDTO>> getEligibilityByGroupLiveDataList() {
        return eligibilityByGroupLiveDataList;
    }

    public LiveData<List<CenterMeetingAttendanceDTO>> getCenterMeetingAttendanceDTOLiveDataList() {
        return centerMeetingAttendanceDTOLiveDataList;
    }

    public LiveData<List<FetchOtherDayCMDTO>> getFetchOtherDayCMDTOLiveDataList() {
        return fetchOtherDayCMDTOLiveDataList;
    }

    public LiveData<List<CenterMeetingCollectionDTO>> getCenterMeetingCollectionDTOLiveDataList() {
        return centerMeetingCollectionDTOLiveDataList;
    }

    public LiveData<List<CenterMeetingCollectionTable>> getCenterMeetingCollectionTableLiveDataList() {
        return centerMeetingCollectionTableLiveDataList;
    }

    public LiveData<List<EMIDetailsDTO>> getEMIDetailsDTOLiveDataList() {
        return EMIDetailsDTOLiveDataList;
    }

    public LiveData<List<CenterMeetingAttendanceTable>> getCenterMeetingAttendanceTableLiveDataList() {
        return centerMeetingAttendanceTableLiveDataList;
    }

    public LiveData<NetworkStrengthTable> getNetworkStrengthTableLiveData() {
        return networkStrengthTableLiveData;
    }

    public LiveData<CMCollectionLocalResponseDTO> getCMCollectionLocalResponseDTOLiveData() {
        return CMCollectionLocalResponseDTOLiveData;
    }

    public LiveData<CashCollectionSummaryDTO> getCashCollectionSummaryDTOLiveData() {
        return cashCollectionSummaryDTOLiveData;
    }

    public LiveData<CashDenominationTable> getCashDenominationTableLiveData() {
        return cashDenominationTableLiveData;
    }

    public LiveData<MultipleSyncResponseDTO> getMultipleSyncResponseDTOLiveData() {
        return multipleSyncResponseDTOLiveData;
    }

    public LiveData<List<CGTAttendanceTable>> getCgtAttendanceTableLiveDataList() {
        return cgtAttendanceTableLiveDataList;
    }

    public LiveData<List<KnowledgeBankTable>> getKnowledgeBankTableLiveDataList() {
        return knowledgeBankTableListLiveData;
    }

    public LiveData<List<GRTAttendanceTable>> getGrtAttendanceTableLiveDataList() {
        return grtAttendanceTableLiveDataList;
    }

    public LiveData<LoanProductCodeTable> getLoanProductCodeTableLiveData() {
        return loanProductCodeTableLiveData;
    }
}
