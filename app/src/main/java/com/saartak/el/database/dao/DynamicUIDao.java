package com.saartak.el.database.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.saartak.el.database.entity.ApplicationStatusTable;
import com.saartak.el.database.entity.BranchProductFeatureMasterTable;
import com.saartak.el.database.entity.CBCheckTable;
import com.saartak.el.database.entity.CGTAttendanceTable;
import com.saartak.el.database.entity.CGTTable;
import com.saartak.el.database.entity.CIBILTable;
import com.saartak.el.database.entity.CMPhotoTable;
import com.saartak.el.database.entity.CMFetchTable;
import com.saartak.el.database.entity.CashCollectionSummaryTable;
import com.saartak.el.database.entity.CashDenominationTable;
import com.saartak.el.database.entity.CenterCreationTable;


import com.saartak.el.database.entity.FTOverDueCMTable;
import com.saartak.el.database.entity.FetchOtherDayCMTable;
import com.saartak.el.database.entity.FetchOtherDayCollectionTable;
import com.saartak.el.database.entity.GRTAttendanceTable;
import com.saartak.el.database.entity.InitiatePaymentStatusTable;
import com.saartak.el.database.entity.KnowledgeBankTable;
import com.saartak.el.database.entity.LoanProductCodeTable;
import com.saartak.el.database.entity.OverDueCMTable;
import com.saartak.el.database.entity.SalesToolTable;
import com.saartak.el.database.entity.StaffActivityTable;
import com.saartak.el.database.entity.CenterMeetingAttendanceTable;
import com.saartak.el.database.entity.CenterMeetingCollectionTable;
import com.saartak.el.database.entity.CenterMeetingTable;
import com.saartak.el.database.entity.ColdCallTable;

import com.saartak.el.database.entity.DocumentMasterTable;
import com.saartak.el.database.entity.DocumentUploadTable;
import com.saartak.el.database.entity.DocumentUploadTableNew;
import com.saartak.el.database.entity.DuplicateDynamicUITable;
import com.saartak.el.database.entity.DynamicUITable;
import com.saartak.el.database.entity.EkycAttemptTable;
import com.saartak.el.database.entity.EligibilityTable;
import com.saartak.el.database.entity.GRTTable;
import com.saartak.el.database.entity.GroupTable;
import com.saartak.el.database.entity.HouseVerificationTable;
import com.saartak.el.database.entity.LeadTable;
import com.saartak.el.database.entity.LoanTable;
import com.saartak.el.database.entity.LocationTable;
import com.saartak.el.database.entity.LogInTable;
import com.saartak.el.database.entity.LogTable;
import com.saartak.el.database.entity.MasterTable;
import com.saartak.el.database.entity.NetworkStrengthTable;
import com.saartak.el.database.entity.OTPVerificationTable;
import com.saartak.el.database.entity.PlannerTable;
import com.saartak.el.database.entity.ProductMasterTable;
import com.saartak.el.database.entity.QCReSubmissionTable;
import com.saartak.el.database.entity.RawDataFromServerTable;
import com.saartak.el.database.entity.RawDataTable;
import com.saartak.el.database.entity.RoleNameTable;
import com.saartak.el.database.entity.SODTable;
import com.saartak.el.database.entity.StageDetailsTable;
import com.saartak.el.database.entity.SubmitDataTable;
import com.saartak.el.database.entity.VillageSurveyTable;
import com.saartak.el.database.entity.WorkFlowTable;
import com.saartak.el.models.LeadDropDownDetails.GetLeadDropDownBankDetailsTable;
import com.saartak.el.models.LeadDropDownDetails.GetLeadDropDownBranchNameTable;
import com.saartak.el.models.LeadDropDownDetails.GetLeadDropDownProductNameTable;
import com.saartak.el.models.LeadDropDownDetails.GetLeadDropDownProductTypeTable;
import com.saartak.el.models.LeadDropDownDetails.GetLeadDropDownSqIdAndNameTable;
import com.saartak.el.models.TypeOfProfession.GetAddressAddressProofTable;
import com.saartak.el.models.TypeOfProfession.GetKYCDropDownIDProofTable;
import com.saartak.el.models.TypeOfProfession.GetLeadCustomerTypeTable;
import com.saartak.el.models.TypeOfProfession.GetLeadDropDownTypeOfProfessionTable;
import com.saartak.el.models.UserLoginMenu.UserLoginMenuTable;

import java.util.Date;
import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;


@Dao
public abstract class DynamicUIDao {

    @Insert(onConflict = REPLACE)
    public abstract void save(List<DynamicUITable> dynamicUITableList);

    @Insert(onConflict = REPLACE)
    public abstract void saveDuplicateTable(List<DuplicateDynamicUITable> duplicateDynamicUITableList);

    @Insert(onConflict = REPLACE)
    public abstract void saveStageDetailsTable(List<StageDetailsTable> stageDetailsTableList);

    @Insert(onConflict = REPLACE)
    public abstract void insertRoleNameListTableList(List<RoleNameTable> roleNameTableList);

    @Insert(onConflict = REPLACE)
    public abstract void saveRawDataFromServerTable(List<RawDataFromServerTable> rawDataFromServerTableList);

    @Insert(onConflict = REPLACE)
    public abstract void insertCenterMeetingTableList(List<CenterMeetingTable> centerMeetingDataFromServerTableList);

    @Insert(onConflict = REPLACE)
    public abstract void insertFetchOtherDayCMTableList(List<FetchOtherDayCMTable> fetchOtherDayCMTableList);

    @Insert(onConflict = REPLACE)
    public abstract void insertOverDueCMTableList(List<OverDueCMTable> overDueCMTableList);

    @Insert(onConflict = REPLACE)
    public abstract void insertFTOverDueCMTableList(List<FTOverDueCMTable> overDueCMTableList);

    @Insert(onConflict = REPLACE)
    public abstract void insertInitiatePaymentStatusTableList(List<InitiatePaymentStatusTable> initiatePaymentStatusTableList);

    @Insert(onConflict = REPLACE)
    public abstract void insertCenterMeetingTable(CenterMeetingTable centerMeetingDataFromServerTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertCMFetchTable(CMFetchTable cmFetchTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertCMCaptionPhotoTable(CMPhotoTable cmCaptionPhotoTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertQCReSubmissionTableList(List<QCReSubmissionTable> qcReSubmissionTableList);

    @Insert(onConflict = REPLACE)
    public abstract void insertEligibilityTableList(List<EligibilityTable> eligibilityTableList);

    @Insert(onConflict = REPLACE)
    public abstract void insertBranchProductFeatureMasterTableList(List<BranchProductFeatureMasterTable> branchProductFeatureMasterTableList);

    @Insert(onConflict = REPLACE)
    public abstract void saveProductMasterTable(List<ProductMasterTable> productMasterTableList);

    @Insert(onConflict = REPLACE)
    public abstract void saveDocumentMasterTable(List<DocumentMasterTable> documentMasterTableList);

    @Insert(onConflict = REPLACE)
    public abstract void saveKnowledgeBankTable(List<KnowledgeBankTable> knowledgeBankTableList);

    @Insert(onConflict = REPLACE)
    public abstract void saveUserLoginMenuTable(List<UserLoginMenuTable> userLoginMenuTableList);

    @Insert(onConflict = REPLACE)
    public abstract void saveUserLeadDropDownProductName(List<GetLeadDropDownProductNameTable> getLeadDropDownProductNameTableList);

    @Insert(onConflict = REPLACE)
    public abstract void saveUserGetAddressAddressProofTable(List<GetAddressAddressProofTable> getGetAddressAddressProofTableList);

    @Insert(onConflict = REPLACE)
    public abstract void saveUserTypeOfCustomer(List<GetLeadCustomerTypeTable> getLeadCustomerTypeTableList);

    @Insert(onConflict = REPLACE)
    public abstract void saveUserLeadTypeOfCustomer(List<GetLeadCustomerTypeTable> getLeadCustomerTypeTableList);
    @Insert(onConflict = REPLACE)
    public abstract void saveUserKYCDropDownIDProofTable(List<GetKYCDropDownIDProofTable> getGetKYCDropDownIDProofTableList);



    @Insert(onConflict = REPLACE)
    public abstract void saveUserLeadDropDownTypeOfProfession(List<GetLeadDropDownTypeOfProfessionTable> getLeadDropDownTypeOfProfessionTableList);

    @Insert(onConflict = REPLACE)
    public abstract void saveUserLeadDropDownProductType(List<GetLeadDropDownProductTypeTable> getLeadDropDownProductTypeTableList);

    @Insert(onConflict = REPLACE)
    public abstract void saveUserLeadDropDownSqIdAndName(List<GetLeadDropDownSqIdAndNameTable> getLeadDropDownSqIdAndNameTableList);

    @Insert(onConflict = REPLACE)
    public abstract void saveUserLeadDropDownBankDetails(List<GetLeadDropDownBankDetailsTable> getLeadDropDownBankDetailsTableList);

    @Insert(onConflict = REPLACE)
    public abstract void saveUserLeadDropDownBranchName(List<GetLeadDropDownBranchNameTable> getLeadDropDownBranchNameTableList);

    @Update(onConflict = REPLACE)
    public abstract void updateDynamicUITable(List<DynamicUITable> dynamicUITableList);

    @Update(onConflict = REPLACE)
    public abstract void updateCenterMeetingTableList(List<CenterMeetingTable> centerMeetingTableList);


   @Update(onConflict = REPLACE)
   public abstract void updateGroupTableList(List<GroupTable> groupTableList);

   @Update(onConflict = REPLACE)
   public abstract void updateHouseVerificationTable(HouseVerificationTable houseVerificationTable);

    @Update(onConflict = REPLACE)
    public abstract void updateCenterMeetingCashCollectionTable(CashCollectionSummaryTable cashCollectionSummaryTable);

   @Update(onConflict = REPLACE)
   public abstract void updateCGTTable(CGTTable cgtTable);

   @Update(onConflict = REPLACE)
   public abstract void updateMasterTableList(List<MasterTable> masterTableList);

   @Update(onConflict = REPLACE)
   public abstract void updateCGTTableList(List<CGTTable> cgtTableList);

   @Update(onConflict = REPLACE)
   public abstract void updateEligibilityTableList(List<EligibilityTable> eligibilityTableList);

    @Query("SELECT * FROM DynamicUITable WHERE screenID = :screenID and plusSignName is null")
    public abstract LiveData<List<DynamicUITable>> load(String screenID);

    @Query("SELECT * FROM DynamicUITable WHERE screenID = :screenNo and plusSignName is null")
    public abstract List<DynamicUITable> loadTableList(String screenNo);


    @Query("SELECT * FROM LogTable ")
    public abstract List<LogTable> loadLOGTable();


    @Query("SELECT * FROM stagedetailstable ")
    public abstract List<StageDetailsTable> loadStageTableDetails();


    @Transaction
    public void deleteAndInsertDynamicUITable(List<DynamicUITable> dynamicUITableList, String screenNo) {
        // Anything inside this method runs in a single transaction.
        deleteRecords(screenNo);
        save(dynamicUITableList);
    }


    @Transaction
    public void deleteAndInsertRoleNameListFromServer(List<RoleNameTable> roleNameTableList) {
        // Anything inside this method runs in a single transaction.
        deleteRoleNameListTable();
        insertRoleNameListTableList(roleNameTableList);
    }

    @Transaction
    public void deleteAndInsertStageList(List<StageDetailsTable> stageDetailsTableList) {
        // Anything inside this method runs in a single transaction.
        deleteStageDetails();
        saveStageDetailsTable(stageDetailsTableList);
    }

    @Transaction
    public void deleteAndInsertRawDataFromServer(List<RawDataFromServerTable> rawDataFromServerTableList) {
        // Anything inside this method runs in a single transaction.
        deleteRawDataFromServerDetails();
        saveRawDataFromServerTable(rawDataFromServerTableList);
    }

    @Transaction
    public void deleteAndInsertCenterMeetingDataFromServer(List<CenterMeetingTable> centerMeetingDataFromServerTableList) {
        // Anything inside this method runs in a single transaction.
        deleteCenterMeetingTable();
        insertCenterMeetingTableList(centerMeetingDataFromServerTableList);
    }

    @Transaction
    public void deleteAndInsertFetchOtherDayCMDataFromServer(List<FetchOtherDayCMTable> fetchOtherDayCMTableList) {
        // Anything inside this method runs in a single transaction.
        deleteFetchOtherDayCMTable();
        insertFetchOtherDayCMTableList(fetchOtherDayCMTableList);
    }

    @Transaction
    public void deleteAndInsertOverDueCMDataFromServer(List<OverDueCMTable> overDueCMTableList) {
        // Anything inside this method runs in a single transaction.
        deleteOverDueCMTable();
        insertOverDueCMTableList(overDueCMTableList);
    }

    @Transaction
    public void deleteAndInsertFTOverDueCMDataFromServer(List<FTOverDueCMTable> overDueCMTableList) {
        // Anything inside this method runs in a single transaction.
        deleteFTOverDueCMTable();
        insertFTOverDueCMTableList(overDueCMTableList);
    }

    @Transaction
    public void deleteAndInsertInitiatePaymentStatusFromServer(List<InitiatePaymentStatusTable> initiatePaymentStatusTableList) {
        // Anything inside this method runs in a single transaction.
        deleteInitiatePaymentStatusTable();
        insertInitiatePaymentStatusTableList(initiatePaymentStatusTableList);
    }

    @Transaction
    public void deleteAndInsertQCReSubmissionDataFromServer(List<QCReSubmissionTable> qcReSubmissionTableList) {
        // Anything inside this method runs in a single transaction.
        deleteQCReSubmissionTable();
        insertQCReSubmissionTableList(qcReSubmissionTableList);
    }

    @Transaction
    public void deleteAndInsertEligibilityFromServer(List<EligibilityTable> eligibilityTableList) {
        // Anything inside this method runs in a single transaction.
        deleteEligibilityTable();
        insertEligibilityTableList(eligibilityTableList);
    }

    @Transaction
    public void deleteAndInsertBranchProductFeatureMasterFromServer(List<BranchProductFeatureMasterTable> branchProductFeatureMasterTableList) {
        // Anything inside this method runs in a single transaction.
        deleteBranchProductFeatureMasterTable();
        insertBranchProductFeatureMasterTableList(branchProductFeatureMasterTableList);
    }

    @Transaction
    public void insertAndDeleteProductMasterTable(List<ProductMasterTable> productMasterTableList) {
        // Anything inside this method runs in a single transaction.
        deleteProductMasterTable();
        saveProductMasterTable(productMasterTableList);
    }
    @Transaction
    public void insertAndDeleteDocumentMasterTable(List<DocumentMasterTable> documentMasterTableList) {
        // Anything inside this method runs in a single transaction.
        deleteDocumentMasterTable();
        saveDocumentMasterTable(documentMasterTableList);
    }
    @Transaction
    public void deleteAndInsertKnowledgeBankTable(List<KnowledgeBankTable> knowledgeBankTableList) {
        // Anything inside this method runs in a single transaction.
        deleteKnowledgeBankTable();
        saveKnowledgeBankTable(knowledgeBankTableList);
    }
    @Transaction
    public void deleteAndInsertUserLoginMenuTable(List<UserLoginMenuTable> userLoginMenuTableList) {
        // Anything inside this method runs in a single transaction.
        deleteUserLoginMenuTable();
        saveUserLoginMenuTable(userLoginMenuTableList);
    }

    public void deleteAndInsertUserDropDownProductNameTable(List<GetLeadDropDownProductNameTable> getLeadDropDownProductNameTableList) {
        // Anything inside this method runs in a single transaction.
        deleteGetLeadDropDownProductTable();
        saveUserLeadDropDownProductName(getLeadDropDownProductNameTableList);
    }

    public void deleteAddressAddressProofTable(List<GetAddressAddressProofTable> getAddressAddressProofTableList) {
        // Anything inside this method runs in a single transaction.
        deleteGetAddressAddressProofTable();
        saveUserGetAddressAddressProofTable(getAddressAddressProofTableList);
    }
    public void deleteAndInsertKYCIdProofTable(List<GetKYCDropDownIDProofTable> getKYCDropDownIDProofTablesList) {
        // Anything inside this method runs in a single transaction.
        deleteGetKYCDropDownIDProofTable();
        saveUserKYCDropDownIDProofTable(getKYCDropDownIDProofTablesList);
    }

    public void deleteAndInsertUserCustomerTypeTable(List<GetLeadCustomerTypeTable> getLeadCustomerTypeTableTable) {
        // Anything inside this method runs in a single transaction.
        deleteGetleadCustomerTypeTable();
        saveUserLeadTypeOfCustomer(getLeadCustomerTypeTableTable);
    }
    public void deleteAndInsertUserDropDownTypeOfProfessionTable(List<GetLeadDropDownTypeOfProfessionTable> getLeadDropDownTypeOfProfessionTableList) {
        // Anything inside this method runs in a single transaction.
        deleteGetLeadDropDownTypeOfProfessionTable();
        saveUserLeadDropDownTypeOfProfession(getLeadDropDownTypeOfProfessionTableList);
    }

    public void deleteAndInsertUserDropDownProductTypeTable(List<GetLeadDropDownProductTypeTable> getLeadDropDownProductTypeTableList) {
        // Anything inside this method runs in a single transaction.
        deleteGetLeadDropDownProductTypeTable();
        saveUserLeadDropDownProductType(getLeadDropDownProductTypeTableList);
    }

    public void deleteAndInsertUserDropDownSqIdAndNameTable(List<GetLeadDropDownSqIdAndNameTable> getLeadDropDownSqIdAndNameTableList) {
        // Anything inside this method runs in a single transaction.
        deleteGetLeadDropDownSqIdAndNameTable();
        saveUserLeadDropDownSqIdAndName(getLeadDropDownSqIdAndNameTableList);
    }

    public void deleteAndInsertUserDropDownBankDetailTable(List<GetLeadDropDownBankDetailsTable> getLeadDropDownBankDetailsTableList) {
        // Anything inside this method runs in a single transaction.
         deleteLeadDropDownBankDetailsTable();
        saveUserLeadDropDownBankDetails(getLeadDropDownBankDetailsTableList);
    }

    public void deleteAndInsertUserDropDownBranchNameTable(List<GetLeadDropDownBranchNameTable> getLeadDropDownBranchNameTableList) {
        // Anything inside this method runs in a single transaction.
        deleteGetLeadDropDownBranchNameTable();
        saveUserLeadDropDownBranchName(getLeadDropDownBranchNameTableList);
    }

    @Transaction
    public void insertAndDeleteInDuplicateTable(List<DuplicateDynamicUITable> duplicateDynamicUITableList, String screenNo) {
        // Anything inside this method runs in a single transaction.
        deleteDuplicateTable(screenNo);
        saveDuplicateTable(duplicateDynamicUITableList);
    }

    @Transaction
    public void insertAndDeleteMasterTable(MasterTable masterTable, String clientId) {
        // Anything inside this method runs in a single transaction.
        deleteMasterTable(clientId);
        insertMasterTableData(masterTable);
    }

    @Transaction
    public void insertAndDeleteSalesToolTable(SalesToolTable salesToolTable, String clientId) {
        // Anything inside this method runs in a single transaction.
        deleteSalesToolTable(clientId);
        insertSalesToolTableData(salesToolTable);
    }

    @Transaction
    public void deleteAndInsertApplicationStatusTable(ApplicationStatusTable applicationStatusTable, String clientId) {
        // Anything inside this method runs in a single transaction.
        deleteApplicationStatusTable(clientId);
        insertApplicationStatusTableData(applicationStatusTable);
    }

    @Transaction
    public void deleteAndInsertLoanProductCodeTable(LoanProductCodeTable loanProductCodeTable) {
        // Anything inside this method runs in a single transaction.
        deleteLoanProductCodeTable();
        insertLoanProductCodeTableData(loanProductCodeTable);
    }

 @Transaction
    public void insertAndDeleteVillageSurveyTable(VillageSurveyTable villageSurveyTable, String villageId) {
        // Anything inside this method runs in a single transaction.
     deleteVillageSurveyTable(villageId);
        insertVillageSurveyTable(villageSurveyTable);
    }

    @Transaction
    public void insertAndDeleteCenterCreationTable(CenterCreationTable centerCreationTable, String centerId) {
        // Anything inside this method runs in a single transaction.
        deleteCenterCreationTable(centerId);
        insertCenterCreationTable(centerCreationTable);
    }

    @Transaction
    public void deleteAndInsertCashDenominationTable(CashDenominationTable cashDenominationTable) {
        // Anything inside this method runs in a single transaction.
        deleteCashDenominationTable(cashDenominationTable.getStaffId(),cashDenominationTable.getCMDate());
        insertCashDenominationTable(cashDenominationTable);
    }

    @Transaction
    public void insertAndDeleteCBCheckTable(CBCheckTable cbCheckTable, String loanId) {
        // Anything inside this method runs in a single transaction.
        deleteCBCheckTable(loanId);
        insertCBCheckTable(cbCheckTable);
    }

    @Transaction
    public void insertAndDeleteGRTTable(GRTTable grtTable, String centerId) {
        // Anything inside this method runs in a single transaction.
        deleteGRTTable(centerId);
        insertGRTTable(grtTable);
    }

    @Transaction
    public void deleteAndInsertCenterMeetingAttendanceTable(List<CenterMeetingAttendanceTable> centerMeetingAttendanceTableList, String centerId) {
        // Anything inside this method runs in a single transaction.
        deleteCenterMeetingAttendanceTable(centerId);
        insertCenterMeetingAttendanceTableList(centerMeetingAttendanceTableList);
    }

    @Transaction
    public void insertAndDeleteGroupTable(GroupTable groupTable, String memberId) {
        // Anything inside this method runs in a single transaction.
       deleteGroupTable(memberId);
       insertGroupTable(groupTable);
    }

    @Transaction
    public void insertAndDeleteCGTTable(CGTTable cgtTable, String centerId) {
        // Anything inside this method runs in a single transaction.
       deleteCGTTable(centerId);
       insertCGTTable(cgtTable);
    }

    @Transaction
    public void insertAndDeleteHouseVerificationTable(HouseVerificationTable houseVerificationTable, String clientId) {
        // Anything inside this method runs in a single transaction.
       deleteHouseVerificationTable(clientId);
       insertHouseVerificationTable(houseVerificationTable);
    }


    @Transaction
    public void deleteAndInsertLoginTable(LogInTable logInTable) {
        // Anything inside this method runs in a single transaction.
        deleteLoginTable();
        insertLoginTable(logInTable);
    }


    @Transaction
    public void insertAndDeleteSubmitDataTable(List<SubmitDataTable> submitDataTableList, String clientId) {
        // Anything inside this method runs in a single transaction.
        deleteSubmitDataTable(clientId);
        insertSubmitTableDataList(submitDataTableList);
    }

    @Transaction
    public void deleteAndInsertWorkFlowTable(List<WorkFlowTable> workFlowTableList) {
        // Anything inside this method runs in a single transaction.
        deleteWorkFlowTable();
        insertWorkFlowTableList(workFlowTableList);
    }

    /* ************************* */

    @Insert(onConflict = REPLACE)
    public abstract void saveResponseData(SubmitDataTable submitDataTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertWorkflowTable(WorkFlowTable workFlowTable);

    @Query("SELECT * FROM submitdatatable WHERE ScreenId = :screenId ")
    public abstract SubmitDataTable loadSubmittedData(String screenId);

    /* ************************* */

    @Insert(onConflict = REPLACE)
    public abstract void insertGPS(LocationTable locationTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertNetworkStrengthTable(NetworkStrengthTable networkStrengthTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertSODTable(SODTable sodTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertKnowledgeBankTable(KnowledgeBankTable knowledgeBankTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertStaffActivityTable(StaffActivityTable staffActivityTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertCenterMeetingCollectionTable(CenterMeetingCollectionTable centerMeetingCollectionTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertPlannerTable(PlannerTable sodTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertColdCallTable(ColdCallTable coldCallTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertLeadTable(LeadTable leadTable);

    @Query("SELECT * FROM LocationTable")
    public abstract LocationTable loadGPS();

    @Query("SELECT * FROM NetworkStrengthTable")
    public abstract NetworkStrengthTable loadNetworkStrengthTable();

    /* ************************* */
    /* ************************* */

    @Insert(onConflict = REPLACE)
    public abstract void insertRawData(RawDataTable rawDataTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertMasterTableData(MasterTable masterTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertSalesToolTableData(SalesToolTable salesToolTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertApplicationStatusTableData(ApplicationStatusTable applicationStatusTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertLoanProductCodeTableData(LoanProductCodeTable loanProductCodeTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertVillageSurveyTable(VillageSurveyTable villageSurveyTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertCenterCreationTable(CenterCreationTable centerCreationTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertCashDenominationTable(CashDenominationTable cashDenominationTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertCenterMeetingCashCollectionTable(CashCollectionSummaryTable cashCollectionSummaryTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertCBCheckTable(CBCheckTable cbCheckTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertLoanTable(LoanTable loanTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertEligibilityTable(EligibilityTable eligibilityTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertGRTTable(GRTTable grtTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertGRTAttendanceTable(GRTAttendanceTable grtAttendanceTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertGroupTable(GroupTable groupTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertCGTTable(CGTTable cgtTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertHouseVerificationTable(HouseVerificationTable houseVerificationTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertCGTAttendanceTable(CGTAttendanceTable cgtAttendanceTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertLoginTable(LogInTable logInTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertSubmitTableDataList(List<SubmitDataTable> submitDataTableList);

    @Insert(onConflict = REPLACE)
    public abstract void insertCenterMeetingAttendanceTableList(List<CenterMeetingAttendanceTable> centerMeetingAttendanceTableList);

    @Insert(onConflict = REPLACE)
    public abstract void insertWorkFlowTableList(List<WorkFlowTable> workFlowTableList);

    @Insert(onConflict = REPLACE)
    public abstract void insertDocumentUpload(DocumentUploadTable documentUploadTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertDocumentUploadNew(DocumentUploadTableNew documentUploadTableNew);

    @Insert(onConflict = REPLACE)
    public abstract void insertLog(LogTable logTable);

//    @Insert(onConflict = REPLACE)
//    public abstract void insertCollectionTable(CollectionTable collectionTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertOTPVerification(OTPVerificationTable otpVerificationTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertCIBILTable(CIBILTable cibilTable);

    @Insert(onConflict = REPLACE)
    public abstract void insertEkycAttemptTable(EkycAttemptTable ekycAttemptTable);

    @Query("SELECT * FROM EkycAttemptTable where screen_no=:screenNo ")
    public abstract List<EkycAttemptTable> loadEkycAttemptData(String screenNo);

    @Query("Update EkycAttemptTable set attempt=:attempt  where aadhaar_no=:aadhaar and screen_no=:screen_no")
    public abstract void updateEkycAttemptTable(String aadhaar,int attempt,String screen_no);

    @Query("DELETE FROM EkycAttemptTable")
    public abstract void deleteEkycAttemptTable();

    @Query("SELECT * FROM RawDataTable where screen_no=:screenNo and sync=0")
    public abstract List<RawDataTable> loadRawData(String screenNo);

    @Query("SELECT * FROM RawDataTable where screen_no=:screenNo and client_id=:clientId and sync=0")
    public abstract List<RawDataTable> loadRawDataFromClientId(String screenNo,String clientId);

    @Query("SELECT * FROM RawDataTable where screen_no=:screenNo and screen_name=:screenName and client_id=:clientid and moduleType=:moduleType and sync=0")
    public abstract RawDataTable loadSingleRawData(String screenNo, String screenName, String clientid, String moduleType);

    @Query("SELECT * FROM RawDataTable where screen_no=:screenNo and user_id=:userId order by id desc")
    public abstract List<RawDataTable> loadRawDataFromDB(String screenNo, String userId);

    @Query("SELECT * FROM RawDataTable where screen_name=:screenName and loan_type=:loanType")
    public abstract List<RawDataTable> LeadRawDataList(String screenName,String loanType);

    @Query("SELECT DISTINCT moduleType from  RawDataTable where client_id=:clientId  and moduleType != 'Lead' " +
            "and moduleType != 'ColdCalling'and moduleType != 'SalesTool'")
    public abstract List<String> getDistinctModuleType(String clientId);

    /*@Query("SELECT DISTINCT SchemeName from  ProductMasterTable where ProductCategory=:product")
    public abstract List<String> getSchemeByProduct(String product);*/

    @Query("SELECT DISTINCT SchemeName from  ProductMasterTable ")
    public abstract List<String>  getSchemeByProduct();

    @Query("SELECT DISTINCT ProductCategory from  ProductMasterTable")
    public abstract List<String> getDistinctProducts();

    @Query("SELECT DISTINCT villageName from  VillageSurveyTable where createdBy=:userId and loan_type=:loanType ")
    public abstract List<String> getDistinctVillageNames(String userId,String loanType);

    @Query("SELECT DISTINCT villageName from  VillageSurveyTable where createdBy=:userId and loan_type=:loanType ")
    public abstract List<String> getDistinctVillageNamesFromVillageSurveyTable(String userId,String loanType);

    @Query("SELECT DISTINCT CenterName from  CenterMeetingTable where LoanOfficerId=:userId ")
    public abstract List<String> getAllCenterNamesFromCenterMeetingTable(String userId);

    @Query("SELECT DISTINCT CenterName from  CenterMeetingTable where LoanOfficerId=:userId and centerMeetingDate=:centerMeetingDate ")
    public abstract List<String> getAllCenterNamesFromCenterMeetingTableByCenterMeetingDate(String userId,String centerMeetingDate);

    @Query("SELECT DISTINCT CenterName from  FetchOtherDayCMTable where LoanOfficerId=:userId ")
    public abstract List<String> getAllCenterNamesFromFetchOtherDayCMTableByUserId(String userId);

    @Query("SELECT DISTINCT centerMeetingDate from  CenterMeetingTable where CenterName=:centerName ")
    public abstract String getCenterMeetingDateFromCenterMeetingTableByCenterName(String centerName);

    @Query("SELECT DISTINCT CenterMeetingTime from  CenterMeetingTable where CenterName=:centerName ")
    public abstract String getCenterMeetingTimeFromCenterMeetingTableByCenterName(String centerName);

    @Query("SELECT DISTINCT GroupName from  CenterMeetingTable where LoanOfficerId=:userId and CenterName=:centerName ")
    public abstract List<String> getDistinctGroupsFromCenterMeetingTable(String userId,String centerName);

    @Query("SELECT DISTINCT CenterName from  OverDueCMTable where LoanOfficerId=:userId ")
    public abstract List<String> getDistinctCentersFromOverDueCMTable(String userId);

    @Query("SELECT DISTINCT CenterName from  FTOverDueCMTable where LoanOfficerId=:userId ")
    public abstract List<String> getDistinctCentersFromFTOverDueCMTable(String userId);

//    @Query("SELECT DISTINCT GroupName from  CenterMeetingTable where LoanOfficerId=:userId and CenterName=:centerName and CollectionType=:collectionType ")
//    public abstract List<String> getDistinctGroupsFromCenterMeetingTableByCollectionType(String userId,String centerName,String collectionType);

    @Query("SELECT DISTINCT GroupName from  CenterMeetingCollectionTable where StaffId=:userId and CenterName=:centerName and DigitalPayment=:isDigitalPayment ")
    public abstract List<String> getDistinctGroupsFromCMCollectionTableByCollectionType(String userId,String centerName,boolean isDigitalPayment);

    @Query("SELECT DISTINCT ProductCode from  EligibilityTable ")
    public abstract List<String> getDistinctProductCodesFromEligibilityTable();

    @Query("SELECT DISTINCT CustomerId from  CenterMeetingTable where LoanOfficerId=:userId  and CenterName=:centerName ")
    public abstract List<String> getDistinctClientIdsFromCenterMeetingTable(String userId,String centerName);

    @Query("SELECT DISTINCT CustomerId from  CenterMeetingTable where LoanOfficerId=:userId  and GroupName=:groupName ")
    public abstract List<String> getDistinctClientIdsFromCenterMeetingTableByGroup(String userId,String groupName);

//    @Query("SELECT * FROM EligibilityTable where clientId = :clientId and loanId=:loanId")
//    public abstract EligibilityTable getEligibilityTableByLoanId(String clientId, String loanId);

    @Query("SELECT * FROM EligibilityTable where CustomerId = :clientId ")
    public abstract List<EligibilityTable> getEligibilityTableList(String clientId);

    @Query("SELECT DISTINCT centerName from  CenterCreationTable where createdBy=:userId and loan_type=:loanType " +
            "and villageName=:villageName ")
    public abstract List<String> getCentersParamList(String userId,String loanType,String villageName);

    @Query("SELECT DISTINCT SchemeName from  ProductMasterTable")
    public abstract List<String> getDistinctSchemes();

    @Query("SELECT DISTINCT ProductCategory from  ProductMasterTable where ProductCategory NOT LIKE '%' || :loanType || '%' ")
    public abstract List<String> getLoanProductsExceptTopUp(String loanType);

    @Query("SELECT * from  ProductMasterTable where SchemeName=:scheme and ProductCategory=:product")
    public abstract ProductMasterTable getTenureByScheme(String scheme,String product);

    @Query("SELECT * from  ProductMasterTable where ProductCategory=:product")
    public abstract ProductMasterTable getTenureByproductonly(String product);

    @Query("SELECT * from  DocumentMasterTable where ScreenId=:screenId and DocumentName=:documentName and CustomerType=:customerType")
    public abstract DocumentMasterTable getDocumentMasterByScreenAndDocumentName(String screenId,String documentName,String customerType);

    @Query("SELECT * from  DocumentMasterTable where ScreenId=:screenId and DocumentName=:documentName and DisplayName=:displayName and CustomerType=:customerType")
    public abstract DocumentMasterTable getDocumentMasterByScreenAndDisplayName(String screenId,String documentName,String displayName,String customerType);

    @Query("SELECT * from  DocumentMasterTable where ScreenId=:screenId and FileFormat=:fileFormat")
    public abstract DocumentMasterTable getDocumentMasterByScreenAndFileFormat(String screenId,String fileFormat);

    @Query("SELECT DISTINCT moduleType from  RawDataTable where client_id=:clientId and loan_type=:loanType" +
            " and moduleType LIKE '%' || :moduleType || '%'")
    public abstract List<String> getNoOfCoApplicants(String clientId, String loanType, String moduleType);

    @Query("SELECT DISTINCT screen_no from  RawDataTable where client_id=:clientId and moduleType=:moduleType")
    public abstract List<String> getDistinctScreenNumbers(String clientId, String moduleType);

    @Query("SELECT DISTINCT screen_name from  RawDataTable where client_id=:clientId and loan_type=:loanType")
    public abstract List<String> getDistinctScreenNamesByLoanType(String clientId, String loanType);

    @Query("SELECT DISTINCT screenID from RawDataFromServerTable")
    public abstract List<String> getDistinctScreenNumbersFromRawDataServerTable();

    @Query("SELECT DISTINCT screen_no from  RawDataTable where client_id=:clientId and loan_type=:loanType")
    public abstract List<String> getDistinctScreensByClientAndLoanType(String clientId, String loanType);

    @Query("SELECT * FROM RawDataTable where screen_no=:screenNo and client_id=:clientId and moduleType=:moduleType")
    public abstract List<RawDataTable> getRawDataByClientAndModuleType(String screenNo, String clientId, String moduleType);

    @Query("SELECT * FROM RawDataTable where  screen_name=:screenName and client_id=:clientId and moduleType=:moduleType")
    public abstract List<RawDataTable> getRawDataByClientIDAndModuleType(String screenName, String clientId, String moduleType);


    @Query("SELECT * FROM RawDataTable where screen_no=:screenNo and client_id=:clientId ")
    public abstract List<RawDataTable> getRawDataByScreenNoAndClientId(String screenNo, String clientId);

    @Query("SELECT * FROM RawDataTable where screen_name=:screenName and client_id=:clientId and moduleType=:moduleType Order by id desc limit 1")
    public abstract RawDataTable getRawDataByClientAndModuleTypeTopOne(String screenName, String clientId, String moduleType);


    @Query("SELECT * FROM RawDataTable where screen_name=:screenName and client_id=:clientId and moduleType=:moduleType and loan_type=:loanTye")
    public abstract List<RawDataTable> getRawDataByScreenNameAndModuleType(String screenName, String clientId, String moduleType, String loanTye);

    @Query("SELECT * FROM RawDataFromServerTable where ScreenId=:screenId and CustomerId=:customerId")
    public abstract List<RawDataFromServerTable> getRawDataFromServerTableNameAndClientId(String screenId, String customerId);

    @Query("SELECT * FROM RawDataTable where screen_no=:fromScreenNo and additional=:additional and client_id=:clientID and moduleType=:moduleType")
    public abstract List<RawDataTable> getRawDataByClientAndAdditional(String fromScreenNo, String additional, String clientID, String moduleType);

    @Query("SELECT * FROM RawDataTable where screen_no=:screenNo and client_id=:clientId Order by id desc limit 1")
    public abstract RawDataTable getRawDataByClient(String screenNo, String clientId);

    @Query("SELECT * FROM RawDataTable where screen_name=:screenName and client_id=:clientId and loan_type=:loanType  Order by id desc limit 1")
    public abstract RawDataTable getRawdataByScreenNameTopOne(String screenName, String clientId, String loanType);

    @Query("SELECT * FROM RoleNameTable where staffId=:userId order by id desc")
    public abstract List<RoleNameTable> getRoleNameListFromDB(String userId);

    @Query("SELECT * FROM RawDataTable where screen_no=:screenNo and client_id=:clientId and loan_type=:loanType  Order by id desc limit 1")
    public abstract RawDataTable getRawDataByScreenNoTopOne(String screenNo, String clientId, String loanType);

    @Query("SELECT * FROM OTPVerificationTable where  client_id=:clientId")
    public abstract List<OTPVerificationTable> getOTPVerifiedTable(String clientId);

    @Query("SELECT * FROM RawDataTable where screen_name=:fromScreenName and client_id=:clientID")
    public abstract List<RawDataTable> getRawDataByClientAndScreenName(String fromScreenName, String clientID);

    @Query("SELECT * FROM RawDataTable where client_id=:clientID and screen_name IN (:screenList)")
    public abstract List<RawDataTable> getRawDataByClientAndScreenNameList(List<String> screenList, String clientID);

    @Query("SELECT * FROM RawDataTable where screen_name=:fromScreenName and client_id=:clientID and loan_type=:loanType")
    public abstract List<RawDataTable> getRawDataListByScreenNameAndLoanType(String fromScreenName, String clientID,String loanType);

    @Query("SELECT * FROM RawDataTable where screen_name=:fromScreenName and loan_type=:loanType and user_id=:userId")
    public abstract List<RawDataTable> getRawDataListONLYByScreenName(String fromScreenName,String loanType,String userId);

    @Query("SELECT * FROM RawDataTable where screen_name=:fromScreenName and client_id=:clientID and CoRelationID=:correlationId")
    public abstract List<RawDataTable> getRawDataByScreenNameAndCorrelationId(String fromScreenName, String clientID,String correlationId);

    @Query("SELECT * FROM RawDataTable where screen_name=:fromScreenName and client_id=:clientID and moduleType=:moduleType")
    public abstract List<RawDataTable> getRawDataByClientAndScreenNameModuleTye(String fromScreenName, String clientID,String moduleType);


    @Query("SELECT * FROM RawDataTable where screen_name=:fromScreenName and client_id=:clientID and loan_type=:loanType")
    public abstract List<RawDataTable> getRawDataByClientAndScreenNameLoaType(String fromScreenName, String clientID,String loanType);

    @Query("SELECT * FROM RawDataTable where screen_no=:screenNo and client_id=:clientID and moduleType=:moduleType")
    public abstract List<RawDataTable> getRawDataByClientAndScreenNoModuleTye(String screenNo, String clientID,String moduleType);


    @Query("SELECT * FROM RawDataTable where screen_no IN (:screenNoS) and client_id=:clientId and sync=0")
    public abstract List<RawDataTable> getMultipleRawData(List<String> screenNoS, String clientId);

    @Query("SELECT * FROM RawDataTable where screen_no IN (:screenNoS) and client_id IN (:clientIdList)and user_id=:userId ")
    public abstract List<RawDataTable> getRawDataForAllClient(List<String> screenNoS, List<String> clientIdList, String userId);

    @Query("SELECT * FROM RawDataTable where client_id IN (:clientIdList)and user_id=:userId ")
    public abstract List<RawDataTable> getRawDataByClientList( List<String> clientIdList, String userId);

    @Query("SELECT * FROM RawDataTable where screen_name IN (:screenNameList) and client_id=:clientId and loan_type=:loanType and moduleType=:moduleType")
    public abstract List<RawDataTable> getRawDataForSingleClient(List<String> screenNameList, String clientId, String loanType, String moduleType);

    @Query("select distinct moduleType from RawDataTable where client_id=:clientId and screen_name=:screenName " +
            "and user_id=:userId and loan_type=:loanType ")
    public abstract List<String> getNoOfCoApplicants(String screenName, String clientId, String userId,
                                                     String loanType);

    @Query("SELECT * FROM RawDataTable where screen_name=:screenName and client_id=:clientId and tag_name=:fieldTag and loan_type=:loan_type ORDER BY id DESC LIMIT 1")
    public abstract RawDataTable getRawDataForChildFragment(String screenName, String clientId, String fieldTag, String loan_type);

    @Query("SELECT * FROM RawDataTable where screen_name=:screenName and client_id=:clientId and moduleType=:moduleType ")
    public abstract List<RawDataTable> getTagNameList(String screenName, String clientId, String moduleType);

    @Query("SELECT * FROM RawDataTable where screen_name=:screenName and client_id=:clientId and CoRelationID=:correlationId and moduleType=:moduleType ")
    public abstract List<RawDataTable> getRawDataListByCorrelationId(String screenName, String clientId, String moduleType,String correlationId);

    @Query("SELECT * FROM RawDataTable where screen_name=:screenName and client_id=:clientId and moduleType=:moduleType and loan_type=:loanType ")
    public abstract List<RawDataTable> getTagNameListForBusinessAddress(String screenName, String clientId, String moduleType, String loanType);

    @Query("SELECT * FROM DocumentUploadTable where client_id=:clientId and loan_type=:loanType")
    public abstract List<DocumentUploadTable> loadDocumentUploadData(String clientId,String loanType);

    @Query("SELECT * FROM DocumentUploadTableNew where client_id=:clientId and loan_type=:loanType and isHeader=:isHeader")
    public abstract List<DocumentUploadTableNew> getDocumentUploadSubHeader(String clientId,String loanType,boolean isHeader);

    @Query("SELECT * FROM DocumentUploadTableNew where client_id=:clientId and loan_type=:loanType and isHeader=:isHeader" +
            " and document_status=:status and file_path is not null ")
    public abstract List<DocumentUploadTableNew> getUnUploadedDocuments(String clientId,String loanType,boolean isHeader,boolean status);

    @Query("SELECT * FROM DocumentUploadTableNew where client_id=:clientId and loan_type=:loanType and isHeader=:isHeader and " +
            "document_name=:documentName and module_type=:moduleType")
    public abstract List<DocumentUploadTableNew> getDocumentListByDocumentName(String clientId, String loanType, boolean isHeader, String documentName,
                                                                               String moduleType);


    @Query("SELECT DISTINCT file_name FROM DocumentUploadTableNew where client_id=:clientId and loan_type=:loanType and isHeader=:isHeader and " +
            "document_name=:documentName and module_type=:moduleType and document_status=:status")
    public abstract List<String> getUploadedDocumentByDocumentName(String clientId,String loanType,boolean isHeader,
                                                                                   String documentName,
                                                                           String moduleType,boolean status);

    @Query("SELECT distinct module_type FROM DocumentUploadTableNew where client_id=:clientId and loan_type=:loanType and isHeader=:isHeader")
    public abstract List<String> getDocumentUploadHeader(String clientId,String loanType,boolean isHeader);

    @Query("SELECT * FROM DocumentUploadTableNew where client_id=:clientId and loan_type=:loanType and isHeader=:isHeader")
    public abstract DocumentUploadTableNew getDocumentUploadHeaderByClientId(String clientId,String loanType,boolean isHeader);

    @Query("SELECT distinct module_type FROM DocumentUploadTableNew where client_id=:clientId and loan_type=:loanType and isHeader=:isHeader and " +
            "module_type=:moduleType")
    public abstract List<String> getDocumentUploadHeaderForJLG(String clientId,String loanType,boolean isHeader,String moduleType);

    @Query("SELECT * FROM OTPVerificationTable where client_id=:clientId and loan_type=:loanType")
    public abstract List<OTPVerificationTable> loadOTPVerificationData(String clientId,String loanType);

    @Query("SELECT * FROM CIBILTable where client_id=:clientId and loan_type=:loanType")
    public abstract List<CIBILTable> getCIBILTableList(String clientId,String loanType);

    @Query("SELECT * FROM DocumentUploadTable where id=:id")
    public abstract List<DocumentUploadTable> getDocumentUploadRow(int id);

    @Query("SELECT * FROM DocumentUploadTable where rawId=:rawId")
    public abstract DocumentUploadTable getDocumentUploadByRawId(int rawId);

    @Query("SELECT * FROM DocumentUploadTable where id IN (:ids)")
    public abstract List<DocumentUploadTable> getDocumentUploadRowByIDs(List<Integer> ids);

    @Query("SELECT * FROM DocumentUploadTable where DocumentTAG=:tag and client_id=:clientId and Position=:postion and DocumentDisplayName=:displayName")
    public abstract DocumentUploadTable getDocumentUploadRowByTAGandClientId(String tag, String clientId, String postion, String displayName);

    @Query("SELECT * FROM DocumentUploadTableNew where file_format=:fileFormat and client_id=:clientId and isHeader=:isHeader")
    public abstract DocumentUploadTableNew getDocumentHeaderByFileFormatAndClientId(String fileFormat, String clientId,boolean isHeader);

    @Query("SELECT * FROM DocumentUploadTableNew where file_format=:fileFormat and client_id=:clientId and module_type=:moduleType and isHeader=:isHeader")
    public abstract DocumentUploadTableNew getDocumentHeaderByFileFormatAndModuleType(String fileFormat, String clientId,String moduleType,boolean isHeader);

    @Query("SELECT * FROM OTPVerificationTable where moduleType=:moduleType and client_id=:clientId and screen_name=:screenName")
    public abstract OTPVerificationTable getOTPVerificationByModuleTypeAndScreenName(String clientId, String moduleType, String screenName);

    @Query("SELECT * FROM OTPVerificationTable where moduleType=:moduleType and client_id=:clientId ")
    public abstract OTPVerificationTable getOTPVerificationByModuleType(String clientId, String moduleType);

    @Query("SELECT * FROM CIBILTable where moduleType=:moduleType and client_id=:clientId")
    public abstract CIBILTable getCIBILTableByModuleType(String clientId, String moduleType);

    @Query("SELECT DISTINCT isOTPVerified FROM OTPVerificationTable where moduleType=:moduleType and client_id=:clientId and screen_name=:screenName")
    public abstract boolean getOTPVerificationByOTPVerifiedStatus(String clientId, String moduleType, String screenName);


    @Query("SELECT * FROM OTPVerificationTable where mobileNumber=:mobile and client_id=:clientid")
    public abstract OTPVerificationTable getOTPVerificationByMobile(String mobile, String clientid);

    @Query("SELECT * FROM RoleNameTable where isSelected=:isSelectedTrue")
    public abstract RoleNameTable getSelectedRolename(boolean isSelectedTrue);

    @Query("Update DocumentUploadTable set DocumentStatus=1 , FilePath=:filePath  where id=:id")
    public abstract void updateDocumentUploadStatus(int id ,String filePath);

    @Query("Update SODTable set response=:response , isEOD=:eodStatus  where id=:id")
    public abstract void updateSODEODResponsebyId(int id,String response,boolean eodStatus);

    @Query("Update RoleNameTable set isSelected=:status")
    public abstract void updateIsselectedStatusForAll(boolean status);

    @Query("Update RoleNameTable set isSelected=:status where rolename=:roleName")
    public abstract void updateRoleNameSelectedStatus(String roleName, boolean status);


    @Query("Update OTPVerificationTable set isOTPVerified=:isOTPVerified , otp=:otp , mobileNumber=:mobileNumber , isOTPGenerated=:isOTPGenerated  where id=:id")
    public abstract void updateOTPVerificationTableMobileNumber(int id ,String mobileNumber,String otp, boolean isOTPVerified, boolean isOTPGenerated);

    @Query("Update CIBILTable set isCBChecked=:isCBChecked , name=:name , mobileNumber=:mobileNumber  where id=:id")
    public abstract void updateCIBILTableMobileAndName(int id ,String mobileNumber,String name, boolean isCBChecked);

    @Query("Update CIBILTable set isCBChecked=:isCBChecked where client_id=:clientId and moduleType=:moduleTye")
    public abstract void updateCIBILTableCBChecked(boolean isCBChecked,String clientId ,String moduleTye);

    @Query("Update RawDataTable set sync=0  where screen_no=:screenNo and moduleType=:moduleType")
    public abstract void updateaForSyncForAPIS(String screenNo,String moduleType);

    @Query("Update RawDataTable set sync=1  where screen_no=:screenNo and moduleType=:moduleType")
    public abstract void updateaForSyncTrueForAPIS(String screenNo,String moduleType);

    @Query("Update CIBILTable set isCBChecked=:isCBChecked , Decision=:decision , " +
            "score=:score,Reason=:reason,ApplicationId=:applicationId,SolutionSetInstanceId=:solutionSetInstanceId , " +
            " timestamp=:timestamp where client_id=:clientId and moduleType=:moduleTye")
    public abstract void updateCIBILTableDataFromServer(String clientId ,String moduleTye,String decision,String score,String reason,String applicationId,
                 String solutionSetInstanceId,String timestamp , boolean isCBChecked);

    @Query("Update OTPVerificationTable set isOTPVerified=0 , isOTPGenerated=:isOTPGenerated ,RefferenceId=:refId where id=:id and mobileNumber=:mobileNumber")
    public abstract void updateOTPGenerated(int id ,String mobileNumber,boolean isOTPGenerated,String refId);

    @Query("Update OTPVerificationTable set isOTPVerified=:isOtpVerified  where id=:id and mobileNumber=:mobileNumber")
    public abstract void updateOTPVerified(int id ,String mobileNumber,boolean isOtpVerified);

    @Query("Update OTPVerificationTable set name=:fullname  where id=:id")
    public abstract void updateOTPVerificationTableFullName(int id , String fullname);

    @Query("Update RawDataTable set sync=1  where screen_no=:screenNo")
    public abstract void updateRawDataForSync(String screenNo);

    @Query("Update ColdCallTable set sync=1  where clientId=:ClientId")
    public abstract void updateColdCallDataForSyncByClientId(String ClientId);

    @Query("Update ColdCallTable set CallTimeStamp =:timestamp where clientId=:ClientId")
    public abstract void updateColdCallDataForCallTimeByClientId(String ClientId,String timestamp);

    @Query("Update PlannerTable set sync=1  where clientId=:ClientId")
    public abstract void updatePlannerDataForSyncByClientId(String ClientId);

    @Query("Update CenterMeetingCollectionTable set Confirm=:isChecked  where CustomerId=:ClientId")
    public abstract void updateCenterMeetingCollectionByClientId(String ClientId,boolean isChecked);

    @Query("Update CenterMeetingCollectionTable set DigitalPayment=:isChecked  where CustomerId=:ClientId")
    public abstract void updateDigitalInCMCollectionByClientId(String ClientId,boolean isChecked);

    @Query("Update CenterMeetingCollectionTable set ConfirmCount=:count  where CustomerId=:ClientId")
    public abstract void updateCountInCMCollectionByClientId(String ClientId,int count);

    @Query("Update CenterMeetingCollectionTable set CashCollectedAmount=:cashCollectedAmount,DigitalCollectedAmount=:digitalCollectedAmount  where CustomerId=:ClientId and LoanID=:loanId")
    public abstract void updateCashDigitalAmountByClientIdAndLoanId(int cashCollectedAmount,int digitalCollectedAmount,String ClientId,String loanId);

    @Query("Update CenterMeetingCollectionTable set SavingsConfirm=:isChecked  where CustomerId=:ClientId")
    public abstract void updateSavingsCollectionByClientId(String ClientId,boolean isChecked);

//    @Query("Update CenterMeetingCollectionTable set SavingsConfirm=:isChecked  where LoanID=:loanId")
//    public abstract void updateSavingsCollectionByLoanId(String loanId,boolean isChecked);

    @Query("Update CenterMeetingCollectionTable set SavingsCollectedAmount=:savingsAmt, SavingsConfirm=:isChecked  where LoanID=:loanId")
    public abstract void updateSavingsCollectionByLoanId(String loanId,boolean isChecked,int savingsAmt);

    @Query("Update CashCollectionSummaryTable set Confirm=:isConfirmed  where CenterName=:centerName")
    public abstract void confirmCashCollectionSummary(String centerName,boolean isConfirmed);

//    @Query("Update CenterMeetingCollectionTable set CollectedAmount=:CollectedAmt, Reason=:reason ,PaidByOtherMember=:isPaidByOtherMember ," +
//            "PTPDate=:PTPDate  where LoanID=:loanId")
//    public abstract void updateCenterMeetingEmiDetails(String loanId,int CollectedAmt,String reason,boolean isPaidByOtherMember,
//                                                       Date PTPDate);

    @Query("Update CenterMeetingCollectionTable set CashCollectedAmount=:CashAmt, Reason=:reason ,PaidByOtherMember=:isPaidByOtherMember ," +
            "PTPDate=:PTPDate  where LoanID=:loanId")
    public abstract void updateCenterMeetingEmiDetails(String loanId,int CashAmt,String reason,boolean isPaidByOtherMember,
                                                       Date PTPDate);

    @Query("Update CenterMeetingCollectionTable set DigitalCollectedAmount=:CollectedAmt, AccountCreated=:accountCreated ,SmsTriggered=:smsTriggered ," +
            "PaymentStatus=:paymentStatus,RequestId=:requestId  where LoanID=:loanId")
    public abstract void updateCenterMeetingDigitalEmiDetails(String loanId,int CollectedAmt,boolean accountCreated,boolean smsTriggered,
                                                              boolean paymentStatus,String requestId);
    @Query("Update CenterMeetingCollectionTable set isSaved=1  where CenterName=:centerName ")
    public abstract void isSavedCenterMeetingCollectionTableByCenterName(String centerName);

//    @Query("Update CenterMeetingCollectionTable set isSaved=1  where CenterName=:centerName and CollectionType=:collectionType")
//    public abstract void isSavedCenterMeetingCollectionTableByCenterNameAndCollectionType(String centerName,String collectionType);

    @Query("Update CenterMeetingCollectionTable set sync=1  where refId=:refID")
    public abstract void syncCenterMeetingCollectionTableByRefId(String refID);

    @Query("Update CashCollectionSummaryTable set sync=1  where refId=:refID")
    public abstract void syncCashCollectionSummaryTableByRefId(String refID);

    @Query("Update CenterMeetingAttendanceTable set sync=1  where refId=:refID")
    public abstract void syncCenterMeetingAttendanceTableByRefId(String refID);

    @Query("Update CashDenominationTable set sync=1  where refId=:refID")
    public abstract void syncCashDenominationTableByRefId(String refID);

    @Query("Update StaffActivityTable set sync=1  where refId=:refID")
    public abstract void syncStaffActivityTableByRefId(String refID);

    @Query("Update CashCollectionSummaryTable set SyncResponse=:response ,Sync=:sync ," +
            "SyncDateTime=:syncDate  where CollectionDate=:cmDate and CenterName=:centerName")
    public abstract void syncCashCollectionSummary(String centerName,
                                                       Date cmDate,Date syncDate,boolean sync,String response);

//    @Query("Update CollectionTable set sync=1  where clientId=:ClientId")
//    public abstract void updateCollectionDataForSyncByClientId(String ClientId);

    @Query("SELECT EXISTS (SELECT *  FROM CashCollectionSummaryTable WHERE Sync=:sync)")
    public abstract boolean checkCollectionSummarySync(boolean sync);

    @Query("SELECT * FROM CenterMeetingCollectionTable WHERE StaffId=:staffId and   Sync=0 and isSaved=1")
    public abstract List<CenterMeetingCollectionTable> getPendingCenterMeetingCollectionTableList(String staffId);

    @Query("SELECT EXISTS (SELECT *  FROM CenterMeetingCollectionTable WHERE Sync=:sync and isSaved=1)")
    public abstract boolean checkCMCollectionSync(boolean sync);

    @Query("Update LeadTable set sync=1  where clientId=:ClientId")
    public abstract void updateLeadDataForSyncByClientId(String ClientId);

    @Query("Update SalesToolTable set sync=1  where clientId=:ClientId")
    public abstract void updateSalesToolDataForSyncByClientId(String ClientId);

    @Query("Update RawDataTable set sync=1  where screen_no=:screenNo and id=:id")
    public abstract void updateRawDataForSyncById(String screenNo, int id);

    @Query("Update MasterTable set FinalStatus=:finalStatus,Remarks=:remarks  where id=:id")
    public abstract void updateMasterTableStatus(String finalStatus,String remarks, int id);

    @Query("Update LoanTable set FinalStatus=:finalStatus,Remarks=:remarks  where id=:id")
    public abstract void updateMemberLoanDetailTableStatus(String finalStatus,String remarks, int id);

    @Query("Update MasterTable set currentStage=:currentStage,ActionId=:actionId,CurrentStageId=:currentStageId," +
            " ApplicationStatus=:applicationStatus ,FinalStatus=:finalStatus ,sync=:sync,AllDataCaptured=:allDataCaptured,reviewBy=:reviewBy,Remarks=:remarks,isDataNeedsToCaptureFromServer=:dataNeedsToCaptureFromServer where id=:id")
    public abstract void updateMasterTableSendBackDetails(int id, String currentStage, int actionId, int currentStageId, String applicationStatus, String finalStatus,
                                                          boolean sync, boolean allDataCaptured, String reviewBy,String remarks,boolean dataNeedsToCaptureFromServer);
    @Query("Update MasterTable set isDataNeedsToCaptureFromServer=:dataNeedsToCaptureFromServer where id=:id")
    public abstract void updateMasterTableDataNeedsToCaptureFromServer(int id,boolean dataNeedsToCaptureFromServer);

    @Query("Update MasterTable set vkycStatus=:vkycStatus where id=:id")
    public abstract void updateMasterTableVKYCStatus(int id,String vkycStatus);

    @Query("Update MasterTable set VKYCStatusCoApp=:VKYCStatusCoApp where id=:id")
    public abstract void updateMasterTableVKYCStatusForCoAPPLICANT(int id,String VKYCStatusCoApp);

    @Query("Update MasterTable set response=:response  where clientId=:clientId and loan_type=:loanType")
    public abstract void updateMasterTableResponse(String response, String clientId, String loanType);

    @Query("Update MasterTable set response=:response , sync=:sync where clientId=:clientId and loan_type=:loanType")
    public abstract void updateMasterTableResponseAndSync(String response, String clientId, String loanType,boolean sync);

    @Query("Update LoanTable set response=:response  where id=:id")
    public abstract void updateMemberLoanDetailTableResponse(String response, int id);

    @Query("Update MasterTable set ActionId=:actionId  where id=:id")
    public abstract void updateMasterTableActionId(int actionId, int id);

    @Query("Update MasterTable set AllDataCaptured=:isAllDataCaptured ,response=:message where id=:id")
    public abstract void updateMasterTableAllDataCaptured(boolean isAllDataCaptured,String message, int id);

    @Query("Update MasterTable set currentStage=:currentStage where clientId=:clientId")
    public abstract void updateMastertableCurrentStage(String currentStage, String clientId);

    @Query("Update ApplicationStatusTable set currentStage=:currentStage where clientId=:clientId")
    public abstract void updateApplicationStatusTableCurrentStage(String currentStage, String clientId);

//    @Query("Update CollectionTable set BusinessAddress=:businessAddress , CommunicationAddress=:communicationAddress," +
//            "ApplicantMobileNo=:applicantMobileNo, CoApplicantMobileNo=:coApplicantMobileNo, POS=:pos, EMI=:emi  where clientId=:customerId")
//    public abstract void updateCollectionTable(String businessAddress, String communicationAddress, String applicantMobileNo, String coApplicantMobileNo, String pos, String emi, String customerId);


    @Query("Update LoanTable set AllDataCaptured=:isAllDataCaptured ,response=:message where id=:id")
    public abstract void updateMemberLoanDetailAllDataCaptured(boolean isAllDataCaptured,String message, int id);

/*    @Query("Update MasterTable set FinalStatus=:finalStatus ,currentStage=:currentStage,CurrentStageId=:currentStageId ," +
            "reviewBy=:userId, sync=:syncFalse  where clientId=:clientId")
    public abstract void updateMasterTableStatusByClientId(String finalStatus, String currentStage, int currentStageId,
                                                           String clientId, String userId, boolean syncFalse);*/


    @Query("Update MasterTable set FinalStatus=:finalStatus ,currentStage=:currentStage,CurrentStageId=:currentStageId ," +
            "reviewBy=:userId, sync=:syncFalse ,AllDataCaptured=:isAllDataCaptured,Remarks=:remarks  where clientId=:clientId")
    public abstract void updateMasterTableStatusByClientId(String finalStatus, String currentStage, int currentStageId,
                                                           String clientId, String userId, boolean syncFalse,boolean isAllDataCaptured,
                                                           String remarks);

    @Query("Update MasterTable set clientName=:clientName  where id=:id")
    public abstract void updateMasterTableClientName(String clientName, int id);

    @Query("Update MasterTable set loan_amount=:loanAmount  where id=:id")
    public abstract void updateMasterTableLoanAmount(String loanAmount, int id);

    @Query("Update MasterTable set phoneNo=:mobileNumber  where id=:id")
    public abstract void updateMasterTableMobileNumber(String mobileNumber, int id);

    @Query("Update CGTTable set phoneNo=:mobileNumber  where id=:id")
    public abstract void updateCGTTableMobileNumber(String mobileNumber, int id);

    @Query("Update MasterTable set loan_amount=:loanAmount  where id=:id")
    public abstract void updateLoanAmountInMasterTable(String loanAmount, int id);



    @Query("Update MasterTable set sync=:sync  where id=:id")
    public abstract void updateMasterTableSync(boolean sync, int id);

    @Query("Update MasterTable set CBStatus=:CBStatus  where id=:id")
    public abstract void updateMasterTableCBStatus(int CBStatus, int id);

    @Query("Update LoanTable set sync=:sync  where id=:id")
    public abstract void updateMemberLoanDetailTableSync(boolean sync, int id);

    @Query("Update CGTTable set cycleOneStartSession=:startSessionTime , status=:status  where id=:id")
    public abstract void updateCycleOneStartSessionCGTTable(String startSessionTime,String status, int id);

    @Query("Update CGTTable set cycleOneStartSession=:startSessionTime  where id=:id")
    public abstract void updateCycleOneStartSessionCGTTable(String startSessionTime, int id);

   @Query("Update CGTTable set cycleTwoStartSession=:startSessionTime , status=:status  where id=:id")
   public abstract void updateCycleTwoStartSessionCGTTable(String startSessionTime,String status, int id);

   @Query("Update CGTTable set cycleTwoStartSession=:startSessionTime  where id=:id")
   public abstract void updateCycleTwoStartSessionCGTTable(String startSessionTime, int id);

    @Query("Update CGTTable set cycleOneEndSession=:endSessionTime,AllDataCaptured=:allDataCaptured ," +
            " isCycleOneCompleted=:cycleOneCompleted where id=:id")
    public abstract void updateCycleOneEndSessionCGTTable(String endSessionTime,boolean cycleOneCompleted,
            boolean allDataCaptured, int id);

   @Query("Update CGTTable set cycleTwoEndSession=:endSessionTime , isCycleTwoCompleted=:cycleTwoCompleted  where id=:id")
   public abstract void updateCycleTwoEndSessionCGTTable(String endSessionTime,boolean cycleTwoCompleted, int id);

    @Query("Update VillageSurveyTable set sync=:sync ,Status=:status where id=:id")
    public abstract void updateVillageSurveyTableSyncAndStatus(boolean sync,String status, int id);

    @Query("Update VillageSurveyTable set villageName=:villageName ,district=:district , pincode=:pincode , city=:city where villageId=:clientID")
    public abstract void updateVillageSurveyTable(String villageName, String district, String pincode, String city, String clientID);
    
    @Query("Update CenterCreationTable set sync=:sync ,Status=:status where id=:id")
    public abstract void updateCenterCreationTableSyncAndStatus(boolean sync,String status, int id);

    @Query("Update CenterCreationTable set NoOfMembers=:count where id=:id")
    public abstract void updateCenterCreationTableMembersCount(int count, int id);

    @Query("Update CGTTable set sync=:sync ,Status=:status where id=:id")
    public abstract void updateCGTTableSyncAndStatus(boolean sync,String status, int id);

    @Query("Update GRTTable set sync=:sync ,Status=:status where id=:id")
    public abstract void updateGRTTableSyncAndStatus(boolean sync,String status, int id);

   @Query("Update CGTTable set response=:responseMessage where id=:id")
   public abstract void updateCGTTableResponseMessage(String responseMessage, int id);

   @Query("Update GRTTable set response=:responseMessage where id=:id")
   public abstract void updateGRTTableResponseMessage(String responseMessage, int id);

    @Query("Update CGTTable set houseVerification=:houseVerification where centerId=:centerId")
    public abstract void updateCGTTableHouseVerification(boolean houseVerification, String centerId);

    @Query("Update CGTTable set houseVerification=:houseVerification where id=:id")
    public abstract void updateCGTTableHouseVerificationById(boolean houseVerification, int id);

    @Query("Update CGTTable set AllDataCaptured=:allDataCaptured where id=:id")
    public abstract void updateCGTTableAllDataCapturedById(boolean allDataCaptured, int id);

    @Query("Update GRTTable set houseVerification=:houseVerification where id=:id")
    public abstract void updateGRTTableHouseVerification(boolean houseVerification, int id);

    @Query("Update GRTTable set memberDetailVerification=:memberVerification,AllDataCaptured=:allDataCaptured where id=:id")
    public abstract void updateGRTTableMemberVerification(boolean memberVerification,boolean allDataCaptured, int id);

    @Query("Update GRTTable set groupFormation=:groupFormation where id=:id")
    public abstract void updateGRTTableGroupFormation(boolean groupFormation, int id);

    @Query("Update GRTTable set status=:status where id=:id")
    public abstract void updateGRTTableStatus(String status, int id);

    @Query("Update GRTTable set groupFormation=:groupFormation where centerId=:centerId")
    public abstract void updateGRTTableGroupFormationByCenterId(boolean groupFormation, String centerId);

   @Query("Update CGTAttendanceTable set CGT2Attendance=:cgtTwoAttendance , CGT2AbsentReason=:cgt2Reason where id=:id")
   public abstract void updateCGTAttendanceTableCGTTwoAttendance(boolean cgtTwoAttendance,String cgt2Reason, int id);

   @Query("Update GRTAttendanceTable set Attendance=:attendance , AbsentReason=:absentReason where id=:id")
   public abstract void updateGRTAttendanceTable(boolean attendance,String absentReason, int id);

    @Query("Update CGTAttendanceTable set CGT1Attendance=:cgtOneAttendance , CGT1AbsentReason=:cgt1Reason where id=:id")
    public abstract void updateCGTAttendanceTableCGTOneAttendance(boolean cgtOneAttendance,String cgt1Reason, int id);

    @Query("Update CGTTable set activity1=:activity1 , activity2=:activity2, activity3=:activity3 where id=:id")
    public abstract void updateCGTTableActivitiesData(boolean activity1, boolean activity2, boolean activity3, int id);

    @Query("select *  from ColdCallTable where clientId=:clientIdd ORDER BY id DESC LIMIT 1")
    public abstract ColdCallTable getTopColdCallData(String clientIdd);

    @Query("select *  from LeadTable where clientId=:clientIdd ORDER BY id DESC LIMIT 1")
    public abstract LeadTable getTopLeadTableData(String clientIdd);

//    @Query("select *  tvName CollectionTable where clientId=:clientId and loanType=:loanType ORDER BY id DESC LIMIT 1")
//    public abstract CollectionTable getTopCollectionData(String clientId, String loanType);

    @Query("SELECT * FROM ColdCallTable where createdBy=:userId and loan_type=:loanType order by id desc")
    public abstract List<ColdCallTable> loadColdCallDataFromDB(String userId, String loanType);

    @Query("SELECT * FROM SalesToolTable where createdBy=:userId and loan_type=:loanType order by id desc")
    public abstract List<SalesToolTable> loadSalesToolDataFromDB(String userId, String loanType);

    @Query("SELECT * FROM PlannerTable where staffId=:userId and loan_type=:loanType order by id desc")
    public abstract List<PlannerTable> loadPlannerTableDataFromDB(String userId, String loanType);

    @Query("select *  from SODTable where staffId=:userId ORDER BY id DESC LIMIT 1")
    public abstract SODTable getTopSODData(String userId);

    @Query("select *  from KnowledgeBankTable where DocumentName=:documentName ORDER BY id DESC LIMIT 1")
    public abstract KnowledgeBankTable getTopKnowledgeBankData(String documentName);

    @Query("select *  from CMFetchTable where staffId=:userId and created_date=:createdDate ORDER BY id DESC LIMIT 1")
    public abstract CMFetchTable getTopCMFetchData(String userId,String createdDate);

    @Query("select *  from CMPhotoTable where CenterName=:centerName ORDER BY id DESC LIMIT 1")
    public abstract CMPhotoTable getTopCMCaptionData(String centerName);

    @Query("select *  from PlannerTable where staffId=:userId and clientId=:clientId ORDER BY id DESC LIMIT 1")
    public abstract PlannerTable getTopPlannerData(String userId, String clientId);

    @Query("select *  from PlannerTable where staffId=:userId and PurposeOfVisit=:purpose and created_date=:currentDate ORDER BY id DESC LIMIT 1")
    public abstract PlannerTable getPlannerDataByPurposeAndCurrentDate(String userId, String purpose, String currentDate);


    @Query("select *  from LeadTable where clientId=:clientIdd ORDER BY id DESC LIMIT 1")
    public abstract LeadTable getTopLeadData(String clientIdd);

    @Query("select *  from SalesToolTable where clientId=:clientIdd ORDER BY id DESC LIMIT 1")
    public abstract SalesToolTable getTopSalesToolData(String clientIdd);

    @Query("SELECT * FROM LeadTable where createdBy=:userId and loan_type=:loanType order by id desc LIMIT 300")
    public abstract List<LeadTable> getLeadTableListFromDBTop300(String userId, String loanType);

    @Query("SELECT * FROM GetKYCDropDownIDProofTable ORDER BY id DESC LIMIT 100")
    public abstract List<GetKYCDropDownIDProofTable> getKYCDropDownIDProofList();

    @Query("SELECT * FROM LeadTable where createdBy=:userId and loan_type=:loanType order by id desc")
    public abstract List<LeadTable> getLeadTableListFromDBbyDesc(String userId, String loanType);

    @Query("SELECT * FROM LeadTable where createdBy=:userId and loan_type=:loanType  order by id desc")
    public abstract List<LeadTable> getLeadTableListFromDB(String userId , String loanType);

    @Query("SELECT * FROM LeadTable where createdBy=:userId and loan_type=:loanType and clientId=:clientId  order by id desc")
    public abstract List<LeadTable> getLeadTableListFromDBWithClientId(String userId , String loanType, String clientId);

//    @Query("SELECT * FROM CollectionTable where createdBy=:userId order by id desc")
//    public abstract List<CollectionTable> loadCollectionDataFromDB(String userId);

//    @Query("SELECT * FROM CollectionTable where createdBy=:userId and loanType=:loanType and clientId=:clientId")
//    public abstract CollectionTable getCollectionDataByClientIdAndLoanType(String userId, String loanType, String clientId);

    @Query("Update RawDataTable set rawdata=:rawdatabag , sync=0 where screen_no=:screenNo and id=:id")
    public abstract void updateRawDataBag(String screenNo, int id, String rawdatabag);

    @Query("Update RawDataTable set sync=:sync where id=:id")
    public abstract void updateRawDataSync(boolean sync, int id);

    @Query("Update RawDataTable set rawdata=:rawdata where id=:id")
    public abstract void updateLoanSchemeRawData(String rawdata, int id);

    @Query("Update RawDataTable set rawdata=:rawdatabag ,dynamic_ui_rawdata=:dynamicRawData,  sync=0 where screen_no=:screenNo and id=:id")
    public abstract void updateRawDataBagAndDynamicRawData(String screenNo, int id, String rawdatabag, String dynamicRawData);

    @Query("Update RawDataTable set dynamic_ui_rawdata=:dynamicRawData,  sync=0 where screen_no=:screenNo and id=:id")
    public abstract void updateDynamicUIRawData(String screenNo, int id, String dynamicRawData);

    @Query("Update RawDataTable set field_name=:fieldName  where screen_no=:screenNo and id=:id")
    public abstract void updateRawDataFieldName(String screenNo, int id, String fieldName);

    @Query("select * from RawDataTable where screen_no=:screenNo and id=:id")
    public abstract List<RawDataTable> checkRawDataBagById(String screenNo, int id);

    @Query("select *  from RawDataTable where screen_no=:screenNo and sync=0 and client_id=:clientId ORDER BY id DESC LIMIT 1")
    public abstract RawDataTable getTopRawData(String screenNo, String clientId);

    @Query("select *  from RawDataTable where screen_no=:screenNo and sync=0 and client_id=:clientId and moduleType=:moduleType ORDER BY id DESC LIMIT 1")
    public abstract RawDataTable getTopRawDataByModuleType(String screenNo, String clientId, String moduleType);

    @Query("select *  from RawDataTable where screen_no=:screenNo and CoRelationID=:correlationId and sync=0 and client_id=:clientId ORDER BY id DESC LIMIT 1")
    public abstract RawDataTable getTopRawDataByCorrelationId(String screenNo, String clientId,String correlationId);

    @Query("select *  from RawDataTable where screen_name=:screenName and loan_type=:loanType and field_name=:fieldName and sync=0 and client_id=:clientId ORDER BY id DESC LIMIT 1")
    public abstract RawDataTable getRawdataByFieldName(String screenName, String loanType, String clientId, String fieldName);

    @Query("select *  from RawDataTable where screen_name=:screen_name and sync=0 and client_id=:clientId and loan_type=:loanType ORDER BY id DESC LIMIT 1")
    public abstract RawDataTable getTopRawDataForBusinessAddress(String screen_name, String clientId, String loanType);

    @Query("select *  from RawDataTable where screen_name=:screenName and additional=:additional and sync=0 and client_id=:clientId ORDER BY id DESC LIMIT 1")
    public abstract RawDataTable getRawdataByAdditional(String screenName, String clientId, String additional);

    @Query("select distinct additional from RawDataTable where screen_name=:screenName and sync=0 and client_id=:clientId and loan_type=:loanType ORDER BY id")
    public abstract List<String> getRawDataForReferences(String screenName, String clientId, String loanType);

    @Query("select distinct field_name from RawDataTable where screen_name=:screenName and sync=0 and client_id=:clientId ORDER BY id")
    public abstract List<String> getRawDataForCoApplicant(String screenName, String clientId);

    @Query("SELECT * FROM RawDataTable  where screen_name=:screenName and sync=0 and client_id=:clientId and loan_type=:loanType ORDER BY id")
    public abstract List<RawDataTable> getRawDataForApplicantCoappnew(String screenName,String clientId,String loanType);

    @Query("select distinct moduleType from RawDataTable where screen_no=:screenNo and sync=0 and client_id=:clientId ORDER BY id")
    public abstract List<String> getRawDataForCoApplicantmoduleType(String screenNo, String clientId);

    @Query("select *  from RawDataTable where screen_no=:screenNo and tag_name=:tag and client_id=:clientId ORDER BY id DESC LIMIT 1")
    public abstract RawDataTable getRawDataByTAG(String screenNo, String clientId, String tag);

    @Query("select *  from RawDataTable where id=:id")
    public abstract RawDataTable getRawDataByID(int id);

    @Query("select *  from RawDataTable where screen_no=:screenNo and additional=:additionalName and client_id=:clientId ORDER BY id DESC LIMIT 1")
    public abstract RawDataTable getRawDataByAdditional(String screenNo, String clientId, String additionalName);

    @Query("select *  from RawDataTable where screen_name=:screenName and additional=:additionalName and client_id=:clientId" +
            " and loan_type=:loanType and moduleType=:moduleType ORDER BY id DESC LIMIT 1")
    public abstract RawDataTable getRawDataByAdditionalColumn(String screenName, String clientId, String additionalName, String loanType,
                                                              String moduleType);

    @Query("select *  from RawDataTable where screen_name=:screenName and additional=:additionalName and client_id=:clientId and loan_type=:loanType ORDER BY id DESC LIMIT 1")
    public abstract RawDataTable getRawDataByAdditionalForBusinessAddress(String screenName, String clientId, String additionalName, String loanType);

    @Query("select *  from RawDataTable where screen_name=:screenName and client_id=:clientId and loan_type=:loanType ORDER BY id DESC LIMIT 1")
    public abstract RawDataTable getRawDataByScreenNameAndLoanType(String screenName, String clientId, String loanType);

    @Query("select rawdata  from RawDataTable where screen_name=:screenName and client_id=:clientId and loan_type=:loanType ORDER BY id DESC")
    public abstract List<String> getRawDataBagByScreenNameAndLoanType(String screenName, String clientId, String loanType);

    @Query("select *  from RawDataTable where screen_name=:screenName and client_id=:clientId and loan_type=:loanType and field_name=:fieldName ORDER BY id DESC LIMIT 1")
    public abstract RawDataTable getRawDataByScreenNameAndFieldName(String screenName, String clientId, String loanType,String fieldName);


    @Query("select *  from documentuploadtablenew where client_id=:clientId")
    public abstract List<DocumentUploadTableNew> getDocumentUploadTableList(String clientId);

    /* ************************* */

    @Query("SELECT * FROM DynamicUITable WHERE screenID = :screenID and plusSignName is null")
    public abstract LiveData<List<DynamicUITable>> loadUpdatedData(String screenID);

    @Query("SELECT * FROM DynamicUITable WHERE screenID = :screenID and plusSignName is null")
    public abstract List<DynamicUITable> loadUpdatedDataNew(String screenID);

    @Query("SELECT * FROM DynamicUITable WHERE screenID =:screenID and id>(SELECT id FROM DynamicUITable " +
            "WHERE screenID =:screenID and FieldTag=:fieldTag )")
    public abstract List<DynamicUITable> getBelowFieldsByFieldTag(String screenID,String fieldTag);

    @Query("SELECT * FROM DynamicUITable WHERE screenName = :screenName and LoanType=:loanType and plusSignName is null")
    public abstract List<DynamicUITable> getMetaDataByScreenName(String screenName,String loanType);

    @Query("SELECT * FROM submitdatatable WHERE screenID = :screenID and UniqueID=:clientID and CreatedBy=:createdBy")
    public abstract SubmitDataTable loadSubmitDataTable(String screenID, String createdBy, String clientID);

    @Query("SELECT * FROM DynamicUITable WHERE screenID = :screenID and FieldTag IN (:tagNames) and plusSignName is null")
    public abstract List<DynamicUITable> getDynamicTableByTagNames(String screenID, List<String> tagNames);

    @Query("SELECT * FROM DynamicUITable WHERE FieldTag=:fieldTag and screenID = :screenID and plusSignName=:selectedItem")
    public abstract DynamicUITable GetSpinnerItemFromDBbyPlusSign(String fieldTag, String screenID, String selectedItem);

    @Query("SELECT * FROM DynamicUITable WHERE FieldTag=:fieldTag and screenID = :screenID and plusSignName is null")
    public abstract DynamicUITable GetSpinnerItemFromDB(String fieldTag, String screenID);

    @Query("SELECT * FROM SODTable WHERE id=:id")
    public abstract SODTable getSODEODRowById(int id);

    @Query("SELECT * FROM DynamicUITable WHERE FieldTag=:fieldTag and screenName = :screenName and clientID=:clientId and plusSignName is null")
    public abstract DynamicUITable GetDynamicTableRowByScreenName(String screenName, String clientId, String fieldTag);

    @Query("SELECT * FROM DynamicUITable WHERE screenID = :screenID")
    public abstract LiveData<DynamicUITable> GetDummyRecord(String screenID);

    @Query("SELECT * FROM DynamicUITable WHERE FieldTag=:fieldTag and screenID = :fromScreen and plusSignName is null LIMIT 1")
    public abstract DynamicUITable GetValueBasedOnFieldName(String fieldTag, String fromScreen);

    @Query("update DynamicUITable set Visibility=:EnableOrDisable WHERE screenID = :screenID " +
            "and FeatureID = :featureId and plusSignName is null")
    public abstract void EnableOrDisableByFeatureId(String screenID, int featureId, boolean EnableOrDisable);

    @Query("update DynamicUITable set IsRequired=:isRequired WHERE screenID = :screenID " +
            "and FeatureID = :featureId and plusSignName is null")
    public abstract void EditableOrNonEditableByFeatureId(String screenID, int featureId, String isRequired);

    @Query("update DynamicUITable set Visibility= :visibility ,value=:val,isEditable=:enabled WHERE screenID = :screenID " +
            "and FieldTag = :FieldTag and plusSignName is null")
    public abstract void EnableOrDisableByFieldTAG(String screenID, boolean visibility, String FieldTag, String val, boolean enabled);

    @Query("update DynamicUITable set Visibility= :visibility ,value=:val,isEditable=:enabled WHERE screenName = :screenName " +
            "and FieldTag = :FieldTag and plusSignName is null")
    public abstract void EnableOrDisableByFieldTAGAndScreenName(String screenName, boolean visibility, String FieldTag, String val, boolean enabled);

    @Query("update DynamicUITable set Visibility= :visibility ,value=:val,isEditable=:enabled WHERE screenID = :screenID " +
            "and FieldName = :FieldName and plusSignName is null")
    public abstract void EnableOrDisableByFieldName(String screenID, boolean visibility, String FieldName, String val, boolean enabled);

    @Query("update DynamicUITable set Visibility= :visibility ,value=:val,isEditable=:enabled ,FieldType =:fieldType, paramlist =:paramvalues " +
            " WHERE screenID = :screenName " +
            "and FieldTag = :FieldTag and plusSignName is null")
    public abstract void changePinCodeFields(String screenName, boolean visibility, String FieldTag, String val, boolean enabled,
                                             String fieldType, String paramvalues);


    @Query("update DynamicUITable set paramlist=:spinnerItems ,isEditable=1 WHERE screenID = :screenNo and FieldTag = :FieldTag and plusSignName is null")
    public abstract void changeSpinnerList(String FieldTag, String spinnerItems, String screenNo);

    /*@Query("update DynamicUITable set plusSignName=:FieldTag , isEditable=:isDatasaved WHERE screenName = :screenNo")
    void updatePlusButtonDataInDB(String FieldTag, String screenNo,boolean isDatasaved,String tablename);*/


/*    @Query("INSERT INTO KYCTable VALUES(:dynamicUITableList)")
    List<KYCTable> insertKYCTable(List<DynamicUITable> dynamicUITableList);*/

  /*  @Query("update DynamicUITable set value=( select value tvName DynamicUITable where FieldTag =:fromFieldTAG and screenID = :fromScreen" +
            " and  plusSignName is null ) ," +
            "spinnerItemPosition=( select spinnerItemPosition tvName DynamicUITable where FieldTag =:fromFieldTAG " +
            "and screenID = :fromScreen and plusSignName is null ) ,"+
            "Visibility= :visibility,isEditable=:enabled  where screenID=:toScreen and FieldTag =:toFieldTAG and plusSignName is null ")
    public abstract void copyValuesBasedOnScreen(String fromFieldTAG,String toFieldTAG,String fromScreen,String toScreen,
                                 boolean visibility,boolean enabled*//*,String plusSign*//*);*/

    @Query("update DynamicUITable set value=( select value from DynamicUITable where FieldTag =:fromFieldTAG and screenID = :fromScreen" +
            " and  plusSignName is null ) ," +
            "Visibility= :visibility,isEditable=:enabled  where screenID=:toScreen and FieldTag =:toFieldTAG and plusSignName is null ")
    public abstract void copyValuesBasedOnScreen(String fromFieldTAG, String toFieldTAG, String fromScreen, String toScreen,
                                                 boolean visibility, boolean enabled);

    @Query("update DynamicUITable set value=( select value from DynamicUITable where FieldTag =:fromFieldTAG and screenID = :fromScreen" +
            " and  plusSignName is null ) ," +
            "Visibility= :visibility,isEditable=:enabled  where screenID=:toScreen and FieldTag =:toFieldTAG and plusSignName is null ")
    public abstract void copyValueForLiability(String fromFieldTAG, String toFieldTAG, String fromScreen, String toScreen,
                                               boolean visibility, boolean enabled/*,String plusSign*/);

    @Query("update DynamicUITable set FieldName=( select value from DynamicUITable where FieldTag =:fromFieldTAG and screenID = :fromScreen" +
            " and  plusSignName is null ) ," +
            "Visibility= :visibility,isEditable=:enabled  where screenID=:toScreen and FieldTag =:toFieldTAG and plusSignName is null ")
    public abstract void copyFieldName(String fromFieldTAG, String toFieldTAG, String fromScreen, String toScreen,
                                       boolean visibility, boolean enabled/*,String plusSign*/);


    @Query("update DynamicUITable set value= CAST(value AS INT) + ( select CAST(value AS INT)from DynamicUITable where FieldTag =:fromFieldTAG and screenID = :fromScreen" +
            " and  plusSignName is null ) ," +
            "Visibility= :visibility,isEditable=:enabled  where screenID=:toScreen and FieldTag =:toFieldTAG and plusSignName is null ")
    public abstract void calculateLiabilityAndUpdate(String fromFieldTAG, String toFieldTAG, String fromScreen, String toScreen,
                                                     boolean visibility, boolean enabled);

    @Query("update DynamicUITable set value= CAST(value AS INT) + ( select CAST(value AS INT)from DynamicUITable where FieldTag =:fromFieldTAG and screenID = :fromScreen" +
            " and  plusSignName is null ) ," +
            "Visibility= :visibility,isEditable=:enabled  where screenID=:toScreen and FieldTag =:toFieldTAG and plusSignName is null ")
    public abstract void calculateAndUpdate(String fromFieldTAG, String toFieldTAG, String fromScreen, String toScreen,
                                            boolean visibility, boolean enabled);

    @Query("update DynamicUITable set value= CAST(value AS INT) + ( select CAST(value AS INT)from DynamicUITable where FieldTag =:fromFieldTAG and screenID = :fromScreen" +
            " and  plusSignName is null ) ," +
            "Visibility= :visibility,isEditable=:enabled  where screenID=:toScreen and FieldTag =:toFieldTAG and plusSignName is null ")
    public abstract void autoSumFields(String fromFieldTAG, String toFieldTAG, String fromScreen, String toScreen,
                                       boolean visibility, boolean enabled);


    @Query("update DynamicUITable set value=  (SELECT SUM(value) FROM DynamicUITable where screenID=:screenId " +
            "and FeatureID =:featureId and plusSignName is null ) where FieldTag=:resultTag and" +
            " screenID=:screenId and plusSignName is null")
    public abstract void sumOfAllFieldsByFeatureId(String resultTag, String screenId, int featureId);

    /*
               OLD QUERY WITH SPINNER POSITION UPDATE
    @Query("update DynamicUITable set value=( select value tvName DynamicUITable where FieldTag =:fromFieldTAG and screenID = :fromScreen" +
            " and plusSignName=:plusSign) ," +
            "spinnerItemPosition=( select spinnerItemPosition tvName DynamicUITable where FieldTag =:fromFieldTAG " +
            "and screenID = :fromScreen and plusSignName=:plusSign) ,"+
            "Visibility= :visibility,isEditable=:enabled  where screenID=:toScreen and FieldTag =:toFieldTAG and plusSignName is null ")
    public abstract void copyValuesBasedOnPlusSignName(String fromFieldTAG,String toFieldTAG,String fromScreen,String toScreen,
                                 boolean visibility,boolean enabled,String plusSign);*/


    @Query("update DynamicUITable set value=( select value from DynamicUITable where FieldTag =:fromFieldTAG and screenID = :fromScreen" +
            " and plusSignName=:plusSign) ," +
            "Visibility= :visibility,isEditable=:enabled  where screenID=:toScreen and FieldTag =:toFieldTAG and plusSignName is null ")
    public abstract void copyValuesBasedOnPlusSignName(String fromFieldTAG, String toFieldTAG, String fromScreen, String toScreen,
                                                       boolean visibility, boolean enabled, String plusSign);


    @Query("update DynamicUITable set value=null ,Visibility= :visibility ,isEditable=:enabled" +
            " where screenID=:screenID and plusSignName is null")
    public abstract void clearValuesBasedOnScreen(String screenID, boolean visibility, boolean enabled);

    @Query("update DynamicUITable set value='' ,isEditable=:enabled" +
            " where screenID=:screenID and plusSignName is null")
    public abstract void clearAllValues(String screenID, boolean enabled);

    @Query("update DynamicUITable set value='' ,paramlist=:spinnerItems,Visibility= :visibility ,isEditable=:enabled" +
            " where screenID=:screenID and FieldTag=:tagName and plusSignName is null")
    public abstract void clearPincodeDetails(String screenID, String spinnerItems, String tagName, boolean visibility, boolean enabled);

    @Query("update DynamicUITable set value=null ,Visibility= :visibility ,isEditable=:enabled" +
            " where screenID=:screenID and FieldTag=:tagName and plusSignName is null and Visibility = 1")
    public abstract void clearValuesBasedOnScreenTAGInDP(String screenID, String tagName, boolean visibility, boolean enabled);

    @Query("SELECT * FROM DynamicUITable WHERE screenID = :screenID and plusSignName =:plusSignName")
    public abstract List<DynamicUITable> getTableBasedOnPlusSign(String screenID, String plusSignName);

    @Query("SELECT DISTINCT plusSignName from DynamicUITable")
    public abstract List<String> getDistinctPlusSignNames();

    @Query("SELECT * from  RawDataTable group by moduleType")
    public abstract List<RawDataTable> getDistinctModuleType();

    @Query("SELECT * from  StageDetailsTable group by CustomerUniqueId")
    public abstract List<StageDetailsTable> getDistinctClientIDFromStageTable();

    @Query("SELECT * from  GroupTable where centerId=:centerId group by groupId")
    public abstract List<GroupTable> getDistinctGroupTable(String centerId);

    @Query("SELECT * from  StageDetailsTable where CustomerUniqueId=:clientId")
    public abstract List<StageDetailsTable> getActionID(String clientId);

    @Query("SELECT value from DynamicUITable WHERE screenID = :screenID and FieldTag =:TAGName and plusSignName is null limit 1")
    public abstract String getValueByTAGname(String screenID, String TAGName);

    @Query("SELECT value from DynamicUITable WHERE screenName = :screenName and FieldTag =:TAGName and plusSignName is null limit 1")
    public abstract String getValueByTAGAndScreenName(String screenName, String TAGName);

    @Query("SELECT value from DynamicUITable WHERE screenID = :screenID and FieldName =:FieldName and plusSignName is null limit 1")
    public abstract String getValueByFieldName(String screenID, String FieldName);

    @Query("SELECT * FROM DynamicUITable WHERE screenID = :screenID and plusSignName is null")
    public abstract List<DynamicUITable> getTableBasedOnScreen(String screenID);

    @Query("SELECT * FROM DynamicUITable WHERE screenName = :screenName and plusSignName is null")
    public abstract List<DynamicUITable> getTableBasedOnScreenName(String screenName);

    @Query("SELECT * FROM MasterTable WHERE createdBy = :userId and currentStage=:currentStage and loan_type=:loanType order by id desc")
    public abstract List<MasterTable> getMasterTableForApplication(String userId, String currentStage, String loanType);

    @Query("SELECT * FROM MasterTable where reviewBy = :userId and currentStage=:currentStage and loan_type=:loanType order by id desc")
    public abstract List<MasterTable> getMasterTableByCurrentStage(String userId, String currentStage, String loanType);

    @Query("SELECT * FROM MasterTable where reviewBy = :userId and loan_type=:loanType order by id desc limit 300")
    public abstract List<MasterTable> getMasterTableByUserIdAndLoanTypeByTop300(String userId, String loanType);

    @Query("SELECT * FROM ApplicationStatusTable where reviewBy = :userId and loan_type=:loanType")
    public abstract List<ApplicationStatusTable> getApplicationStatusTableByUserIdAndLoanType(String userId, String loanType);

    @Query("SELECT * FROM MasterTable where reviewBy = :userId and loan_type=:loanType and created_date BETWEEN :fromDateobj and :toDateobj")
    public abstract List<MasterTable> getmasterTableDatabyuserILoanTypeAndSelectedDates(String userId, String loanType, Date fromDateobj, Date toDateobj);

    @Query("SELECT * FROM ApplicationStatusTable where reviewBy = :userId and loan_type=:loanType and created_date BETWEEN :fromDateobj and :toDateobj")
    public abstract List<ApplicationStatusTable> getApplicationStatusTableDataByUserIDLoanTypeAndSelectedDates(String userId, String loanType, Date fromDateobj, Date toDateobj);

//    @Query("SELECT * FROM CollectionTable where createdBy=:userid and loanType=:loanType")
//    public abstract List<CollectionTable> getCollectionTable(String userid, String loanType);

    @Query("SELECT * FROM KnowledgeBankTable ")
    public abstract List<KnowledgeBankTable> getKnowledgeBankList();

    @Query("SELECT * FROM LoanTable where clientId = :clientId")
    public abstract List<LoanTable> getLoanTableListByClientId(String clientId);

    @Query("SELECT * FROM LoanTable where centerId = :centerId and isInterested=1")
    public abstract List<LoanTable> getLoanTableListByCenterId(String centerId);

    @Query("SELECT * FROM LoanTable where clientId = :clientId and loanProductName=:loanProductName")
    public abstract LoanTable getLoanTableByLoanProductName(String clientId, String loanProductName);

    @Query("SELECT * FROM LoanTable where clientId = :clientId and loanId=:loanId")
    public abstract LoanTable getLoanTableByLoanId(String clientId, String loanId);

    @Query("SELECT * FROM LoanProductCodeTable")
    public abstract LoanProductCodeTable getLoanProductCodeTable();

    @Query("SELECT * FROM CBCheckTable where clientId = :clientId and loanId=:loanId")
    public abstract CBCheckTable getCBCheckTableByLoanId(String clientId,String loanId);

    @Query("SELECT * FROM CBCheckTable where clientId = :clientId")
    public abstract CBCheckTable getCBCheckTableByClientId(String clientId);


    @Query("SELECT * FROM MasterTable where reviewBy = :userId and loan_type=:loanType and centerId=:centerId")
    public abstract List<MasterTable> getMasterTableDetailsByCenter(String userId, String loanType,String centerId);

    @Query("SELECT * FROM LoanTable where createdBy = :userId and loan_type=:loanType and centerId=:centerId and isInterested=:isInterested")
    public abstract List<LoanTable> getLoanTableDetailsByCenter(String userId, String loanType,String centerId,boolean isInterested);

    @Query("SELECT * FROM MasterTable where centerId = :centerId and loan_type=:loanType")
    public abstract List<MasterTable> getMasterTableByCenterId(String centerId,String loanType);

    @Query("SELECT * FROM MasterTable where clientId = :clientId and loan_type=:loanType")
    public abstract List<MasterTable> getMasterTableByClientId(String clientId,String loanType);

    @Query("SELECT * FROM VillageSurveyTable where createdBy = :userId and loan_type=:loanType")
    public abstract List<VillageSurveyTable> getVillageSurveyTable(String userId, String loanType);

    @Query("SELECT * FROM CenterCreationTable where createdBy = :userId and loan_type=:loanType")
    public abstract List<CenterCreationTable> getCenterCreationTable(String userId, String loanType);

    @Query("SELECT * FROM FetchOtherDayCollectionTable where createdBy = :userId")
    public abstract List<FetchOtherDayCollectionTable> getFetchOtherDayCollectionTable(String userId);

    @Query("SELECT * FROM LogInTable where UserID=:userId ")
    public abstract LogInTable getLoginTable(String userId);

    @Query("SELECT * FROM HouseVerificationTable where centerId = :centerId and loan_type=:loanType")
    public abstract List<HouseVerificationTable> getHouseVerificationTable(String centerId, String loanType);

    @Query("SELECT * FROM HouseVerificationTable where clientId = :clientId and loan_type=:loanType")
    public abstract HouseVerificationTable getHouseVerificationMemberDetail(String clientId, String loanType);

    @Query("SELECT * FROM HouseVerificationTable where clientId = :clientId ")
    public abstract HouseVerificationTable getHouseVerificationByClientId(String clientId);

    @Query("SELECT * FROM HouseVerificationTable where clientId = :clientId and loanId=:loanId")
    public abstract HouseVerificationTable getHouseVerificationByLoanId(String clientId, String loanId);

    @Query("SELECT * FROM CGTAttendanceTable where clientId = :clientId and loanId=:loanId")
    public abstract CGTAttendanceTable getCGTAttendanceByLoanId(String clientId, String loanId);

    @Query("SELECT * FROM CGTAttendanceTable where CenterName = :centerName")
    public abstract List<CGTAttendanceTable> getCGTAttendanceByCenterName(String centerName);

    @Query("SELECT * FROM CGTAttendanceTable where CenterId = :centerId")
    public abstract List<CGTAttendanceTable> getCGTAttendanceByCenterId(String centerId);

   @Query("SELECT * FROM HouseVerificationTable where id = :id")
   public abstract HouseVerificationTable getHouseVerificationDetailById(int id);

    @Query("SELECT * FROM CenterCreationTable where centerName=:centerName and createdBy = :userId and loan_type=:loanType")
    public abstract CenterCreationTable getCenterCreationTableByCenterName(String centerName,String userId, String loanType);

    @Query("SELECT * FROM CenterCreationTable where centerId=:centerId and createdBy = :userId and loan_type=:loanType")
    public abstract CenterCreationTable getCenterCreationTableByCenterId(String centerId,String userId, String loanType);

    @Query("SELECT * FROM CenterCreationTable where centerId=:centerId and loan_type=:loanType")
    public abstract CenterCreationTable getCenterCreationTableByCenterID(String centerId, String loanType);

    @Query("SELECT * FROM GRTTable where centerId=:centerId  and loan_type=:loanType")
    public abstract List<GRTTable> getGRTTableByCenterID(String centerId, String loanType);

    @Query("SELECT * FROM GRTTable where centerId=:centerId and loanId =:loanId")
    public abstract GRTTable getGRTTableByLoanId(String centerId,String loanId);

    @Query("SELECT * FROM GRTAttendanceTable where centerId=:centerId and loanId =:loanId")
    public abstract GRTAttendanceTable getGRTAttendanceTableByLoanId(String centerId,String loanId);

    @Query("SELECT * FROM GroupTable where clientId=:clientId")
    public abstract GroupTable getGroupTableByClientId(String clientId);

    @Query("SELECT * FROM GroupTable where centerId = :centerId and loan_type=:loanType")
    public abstract List<GroupTable> getGroupTableList(String centerId, String loanType);

    @Query("SELECT groupId FROM GroupTable where centerId = :centerId and loan_type=:loanType")
    public abstract List<String> getDistinctGroupId(String centerId, String loanType);

   @Query("SELECT * FROM GroupTable where centerId = :centerId and loan_type=:loanType and groupName=:groupName")
   public abstract List<GroupTable> getGroupTableListByGroupName(String centerId, String loanType,String groupName);

   @Query("SELECT * FROM CGTTable where centerId = :centerId and loan_type=:loanType ")
   public abstract List<CGTTable> getCGTTableListByCenterId(String centerId, String loanType);

   @Query("SELECT * FROM CGTTable where centerId = :centerId and loan_type=:loanType and isCycleTwoCompleted=:isCycleTwoCompleted")
   public abstract List<CGTTable> getCGTTableForDropOut(String centerId, String loanType,boolean isCycleTwoCompleted);

   @Query("SELECT * FROM CGTTable where centerId = :centerId and isCycleOneCompleted=:cycleOne and cgtRejected=:cgtRejected")
   public abstract List<CGTTable> getCGTTableByCGTCycle(String centerId, boolean cycleOne,boolean cgtRejected);

   @Query("SELECT * FROM CGTAttendanceTable where CenterId = :CenterId ")
   public abstract List<CGTAttendanceTable> getCGTAttendanceTableForCGT1(String CenterId);

   @Query("SELECT * FROM CGTAttendanceTable where CenterId = :CenterId and CGT1Attendance=1")
   public abstract List<CGTAttendanceTable> getCGTAttendanceTableForCGT2(String CenterId);

   @Query("SELECT * FROM GRTAttendanceTable where CenterId = :CenterId")
   public abstract List<GRTAttendanceTable> getGRTAttendanceTable(String CenterId);

   @Query("SELECT * FROM CGTAttendanceTable where CenterId = :CenterId and CGT1Attendance=1")
   public abstract List<CGTAttendanceTable> getCGTAttendanceTableForCGT1_OnlyPresent(String CenterId);

   @Query("SELECT * FROM CGTAttendanceTable where CenterId = :CenterId and CGT2Attendance=1")
   public abstract List<CGTAttendanceTable> getCGTAttendanceTableForCGT2_OnlyPresent(String CenterId);

   @Query("SELECT * FROM GroupTable where centerId = :centerId and loan_type=:loanType and groupId=:groupId")
   public abstract List<GroupTable> getGroupTableListByGroupId(String centerId, String loanType,String groupId);

    @Query("SELECT DISTINCT groupName from  GroupTable where centerId=:centerId and loan_type=:loanType ")
    public abstract List<String> getDistinctGroupNames(String centerId,String loanType);

    @Query("SELECT DISTINCT centerId from  CGTTable where createdBy=:userId and loan_type=:loanType ")
    public abstract List<String> getDistinctCenterIdFromCGTTable(String userId,String loanType);

    @Query("SELECT DISTINCT centerId from  GRTTable where createdBy=:userId and loan_type=:loanType ")
    public abstract List<String> getDistinctCenterIdFromGRTTable(String userId,String loanType);

    @Query("SELECT * FROM CGTTable where createdBy = :userId and loan_type=:loanType")
    public abstract List<CGTTable> getCGTTableList(String userId, String loanType);

    @Query("SELECT * FROM CenterMeetingCollectionTable where CustomerId=:customerId")
    public abstract List<CenterMeetingCollectionTable> getCenterMeetingCollectionTableList(String customerId);

    @Query("SELECT * FROM CenterMeetingCollectionTable where CMDate=:cmDate group by CenterName")
    public abstract List<CenterMeetingCollectionTable> getDistinctCenterMeetingCollectionTableListByCMDate(Date cmDate);

    @Query("SELECT * FROM CenterMeetingCollectionTable where CMDate=:cmDate and isSaved=:isSaved group by CenterName")
    public abstract List<CenterMeetingCollectionTable> getDistinctCenterMeetingCollectionTableListByCMDateWithIsSaved(Date cmDate,boolean isSaved);

    @Query("SELECT * FROM CenterMeetingCollectionTable where StaffId=:userId and isSaved=:isSaved group by CenterName")
    public abstract List<CenterMeetingCollectionTable> getDistinctCenterMeetingCollectionTableListByIsSaved(String userId,boolean isSaved);

//    @Query("SELECT * FROM CenterMeetingCollectionTable where CMDate=:cmDate and CollectionType=:collectionType group by CenterName")
//    public abstract List<CenterMeetingCollectionTable> getDistinctCenterMeetingCollectionTableListByCMDateAndCollectionType(Date cmDate,String collectionType);

    @Query("SELECT * FROM CenterMeetingAttendanceTable where CenterName=:centerName")
    public abstract List<CenterMeetingAttendanceTable> getCenterMeetingAttendanceTableListByCenterName(String centerName);

    @Query("SELECT * FROM CenterMeetingAttendanceTable where CenterName=:centerName and Sync=0")
    public abstract List<CenterMeetingAttendanceTable> getCMAttendanceTableListByCenterName(String centerName);

    @Query("SELECT * FROM CenterMeetingAttendanceTable WHERE Sync=0 ")
    public abstract List<CenterMeetingAttendanceTable> getCenterMeetingAttendanceTableList();

    @Query("SELECT * FROM CenterMeetingCollectionTable where CenterName=:centerName")
    public abstract List<CenterMeetingCollectionTable> getCMCollectionTableListByCenterName(String centerName);

//    @Query("SELECT * FROM CenterMeetingCollectionTable where CenterName=:centerName and CollectionType=:collectionType")
//    public abstract List<CenterMeetingCollectionTable> getCMCollectionTableListByCenterNameAndCollectionType(String centerName, String collectionType);

    @Query("SELECT * FROM CenterMeetingCollectionTable where CenterName=:centerName group by CustomerName")
    public abstract List<CenterMeetingCollectionTable> getCenterMeetingCollectionTableListByCenterNameAndCustomerName(String centerName);

    @Query("SELECT * FROM CashCollectionSummaryTable where CenterName=:centerName and CollectionDate=:cmDate")
    public abstract CashCollectionSummaryTable getCenterMeetingCashCollectionTableByCenterName(String centerName, Date cmDate);

    @Query("SELECT * FROM CashCollectionSummaryTable WHERE Sync=0 ")
    public abstract List<CashCollectionSummaryTable> getCashCollectionSummaryTableList();

    @Query("SELECT * FROM CashCollectionSummaryTable ")
    public abstract List<CashCollectionSummaryTable> getCashCollectionSummaryTableForUploadedOrNot();

    @Query("SELECT * FROM CashCollectionSummaryTable where CenterName=:centerName and CollectionDate=:cmDate")
    public abstract List<CashCollectionSummaryTable> getCashCollectionSummaryTableForUploadedOrNotByCenterName(String centerName, Date cmDate);

    @Query("SELECT * FROM CenterMeetingTable where CustomerId=:customerId")
    public abstract List<CenterMeetingTable> getCenterMeetingTableList(String customerId);

    @Query("SELECT * FROM FetchOtherDayCMTable where CenterName=:centerName")
    public abstract List<FetchOtherDayCMTable> getFetchOtherDayCMTableList(String centerName);

    @Query("SELECT * FROM CenterMeetingTable where centerMeetingDate=:centerMeetingDate and CenterId=:centerId and LoanAccountNumber=:loanAccountNumber")
    public abstract CenterMeetingTable getCenterMeetingTableByCenterMeetingDateAndCenterIdAndLoanAccountNumber(String centerMeetingDate,String centerId,String loanAccountNumber);

    @Query("SELECT * FROM WorkFlowTable")
    public abstract List<WorkFlowTable> getWorkFlowTableList();

    @Query("SELECT * FROM GRTTable where createdBy = :userId and loan_type=:loanType")
    public abstract List<GRTTable> getGRTTableList(String userId, String loanType);

    @Query("SELECT * FROM CGTTable where id=:id")
    public abstract CGTTable getCGTTableById(int id);

    @Query("SELECT * FROM CGTTable where centerId=:centerId")
    public abstract CGTTable getCGTTableByCenterId(String centerId);

    @Query("SELECT * FROM CGTTable where loanId=:loanId and clientId=:clientId")
    public abstract CGTTable getCGTTableByLoanId(String loanId,String clientId);

    @Query("SELECT * FROM CGTTable where clientId=:clientId order by id desc")
    public abstract CGTTable getCGTTableByClientId(String clientId);

    @Query("SELECT * FROM GRTTable where id=:id")
    public abstract GRTTable getGRTTableById(int id);

    @Query("SELECT distinct clientId FROM MasterTable where reviewBy = :userId and loan_type=:loanType")
    public abstract List<String> getClientsFromMasterTable(String userId, String loanType);

    @Query("SELECT * FROM ProductMasterTable ")
    public abstract List<ProductMasterTable> getProductMasterTable();

    @Query("SELECT * FROM DocumentMasterTable ")
    public abstract List<DocumentMasterTable> getDocumentMasterTable();

    @Query("SELECT * FROM KnowledgeBankTable ")
    public abstract List<KnowledgeBankTable> getKnowledgeBankTableFromLocalDB();

    @Query("SELECT * FROM UserLoginMenuTable ")
    public abstract List<UserLoginMenuTable> getUserLoginMenuTableFromLocalDB();

    @Query("SELECT * FROM GetAddressAddressProofTable ")
    public abstract List<GetAddressAddressProofTable> getGetAddressAddressProofTableFromLocalDB();
    @Query("SELECT * FROM GetKYCDropDownIDProofTable ")
    public abstract List<GetKYCDropDownIDProofTable> getGetKYCDropDownIDProofTableFromLocalDB();
    @Query("SELECT * FROM GetLeadDropDownTypeOfProfessionTable ")
    public abstract List<GetLeadDropDownTypeOfProfessionTable> getLeadDropDownTypeOfProfessionTableFromLocalDB();
    @Query("SELECT * FROM GetLeadCustomerTypeTable ")
    public abstract List<GetLeadCustomerTypeTable> getLeadCustomerTypeTableFromLocalDB();
    @Query("SELECT * FROM GetLeadDropDownProductNameTable ")
    public abstract List<GetLeadDropDownProductNameTable> getLeadDropDownProductNameTableFromLocalDB();

    @Query("SELECT * FROM GetLeadDropDownProductTypeTable ")
    public abstract List<GetLeadDropDownProductTypeTable> getLeadDropDownProductTypeTableFromLocalDB();

    @Query("SELECT * FROM GetLeadDropDownSqIdAndNameTable ")
    public abstract List<GetLeadDropDownSqIdAndNameTable> getLeadDropDownSqIdAndNameTableFromLocalDB();

    @Query("SELECT * FROM GetLeadDropDownBankDetailsTable ")
    public abstract List<GetLeadDropDownBankDetailsTable> getLeadDropDownBankDetailsTableFromLocalDB();

    @Query("SELECT * FROM GetLeadDropDownBranchNameTable ")
    public abstract List<GetLeadDropDownBranchNameTable> getLeadDropDownBranchNameTableFromLocalDB();

    @Query("SELECT * FROM MasterTable WHERE id = :id")
    public abstract MasterTable getMasterTableDetailById(int id);

    @Query("SELECT * FROM LoanTable WHERE id = :id")
    public abstract LoanTable getMemberLoanDetailById(int id);

    @Query("SELECT * FROM LoanTable WHERE clientId = :clientId")
    public abstract LoanTable getMemberLoanDetailByClient(String clientId);

    @Query("SELECT * FROM MasterTable WHERE clientId = :clientId")
    public abstract MasterTable getMasterTableByClientId(String clientId);

    @Query("SELECT * FROM SalesToolTable WHERE clientId = :clientId")
    public abstract SalesToolTable getSalesToolTableByClientId(String clientId);

    @Query("SELECT * FROM ApplicationStatusTable WHERE clientId = :clientId")
    public abstract ApplicationStatusTable getApplicationStatusByClientId(String clientId);

    @Query("SELECT * FROM CenterMeetingTable WHERE CustomerId = :clientId")
    public abstract CenterMeetingTable getCenterMeetingTableByClientId(String clientId);

    @Query("SELECT * FROM CenterMeetingTable WHERE LoanAccountNumber = :loanId")
    public abstract CenterMeetingTable getCenterMeetingTableByLoanId(String loanId);

    @Query("SELECT * FROM CenterMeetingAttendanceTable WHERE CustomerId = :clientId")
    public abstract CenterMeetingAttendanceTable getCenterMeetingAttendanceTableByClientId(String clientId);

    @Query("SELECT * FROM CenterMeetingCollectionTable WHERE LoanID = :loanId")
    public abstract CenterMeetingCollectionTable getCenterMeetingCollectionTableByLoanId(String loanId);

    @Query("SELECT SUM(EMI) FROM CenterMeetingCollectionTable WHERE CustomerId = :clientId")
    public abstract int getTotalEMIByClientId(String clientId);

    @Query("SELECT SUM(TotalDue) FROM CenterMeetingCollectionTable WHERE CustomerId = :clientId")
    public abstract int getTotalDueByClientId(String clientId);

    @Query("SELECT SUM(TotalDue) FROM CenterMeetingCollectionTable WHERE CenterName = :centerName and isSaved=1")
    public abstract int getTotalDueByCenterName(String centerName);

    @Query("SELECT SUM(Total_Due) FROM CenterMeetingTable WHERE CenterName = :centerName")
    public abstract int getTotalDueByCenterNameFromCenterMeetingTable(String centerName);

    @Query("SELECT SUM(TotalDue) FROM CenterMeetingCollectionTable WHERE CMDate = :cmDate and isSaved=1")
    public abstract int getTotalDueByCMDate(Date cmDate);

    @Query("SELECT SUM(TotalDue) FROM CenterMeetingCollectionTable WHERE StaffId=:userId and isSaved=1")
    public abstract int getTotalDueByIsSaved(String userId);

    @Query("SELECT SUM(CashCollectedAmount) FROM CenterMeetingCollectionTable WHERE CenterName = :centerName and DigitalPayment=0 and isSaved=1")
    public abstract int getTotalCashCollectionByCenterName(String centerName);

    @Query("SELECT SUM(CashCollectedAmount) FROM CenterMeetingCollectionTable WHERE CMDate = :cmDate and DigitalPayment=0 and isSaved=1")
    public abstract int getTotalCashCollectionByCMDate(Date cmDate);

    @Query("SELECT SUM(CashCollectedAmount) FROM CenterMeetingCollectionTable WHERE DigitalPayment=0 and isSaved=1")
    public abstract int getTotalCashCollectionByIsSaved();

    @Query("SELECT SUM(CashCollectedAmount) FROM CenterMeetingCollectionTable WHERE StaffId=:userId and DigitalPayment=0 and isSaved=1")
    public abstract int getTotalCashCollectionByIsSavedAndConfirm(String userId);

    @Query("SELECT SUM(CashCollectedAmount) FROM CenterMeetingCollectionTable WHERE StaffId=:userId and DigitalPayment=0 and isSaved=1 and Sync=0")
    public abstract int getTotalCashCollectionByIsSavedAndConfirmSyncFalse(String userId);

    @Query("SELECT SUM(DigitalCollectedAmount) FROM CenterMeetingCollectionTable WHERE CenterName = :centerName and DigitalPayment=1 and isSaved=1")
    public abstract int getTotalDigitalCollectionByCenterName(String centerName);

    @Query("SELECT SUM(DigitalCollectedAmount) FROM CenterMeetingCollectionTable WHERE CMDate = :cmDate and DigitalPayment=1 and isSaved=1")
    public abstract int getTotalDigitalCollectionByCMDate(Date cmDate);

    @Query("SELECT SUM(DigitalCollectedAmount) FROM CenterMeetingCollectionTable WHERE StaffId=:userId and DigitalPayment=1 and isSaved=1")
    public abstract int getTotalDigitalCollectionByIsSaved(String userId);

    @Query("SELECT SUM(CashCollectedAmount) FROM CenterMeetingCollectionTable WHERE CenterName = :centerName")
    public abstract int getTotalCollectionByCenterName(String centerName);

    @Query("SELECT SUM(SavingsCollection) FROM CenterMeetingCollectionTable WHERE CenterName = :centerName and SavingsConfirm=1 group by CustomerName")
    public abstract int getTotalSavingCollectionByCenterName(String centerName);

    @Query("SELECT SUM(CashCollectedAmount) FROM CenterMeetingCollectionTable WHERE CMDate = :cmDate")
    public abstract int getTotalCollectionByCMDate(Date cmDate);

    @Query("SELECT SUM(SavingsCollection) FROM CenterMeetingCollectionTable WHERE CMDate = :cmDate and SavingsConfirm=1 and isSaved=1 group by CustomerName")
    public abstract int getTotalSavingCollectionByCMDate(Date cmDate);

    @Query("SELECT SUM(SavingsCollection) FROM CenterMeetingCollectionTable WHERE SavingsConfirm=1 and isSaved=1 group by CustomerName")
    public abstract int getTotalSavingCollectionByIsSaved();

    @Query("SELECT SavingsCollection FROM CenterMeetingCollectionTable WHERE CustomerId = :clientId")
    public abstract int getTotalSavingByClientId(String clientId);

    @Query("SELECT ConfirmCount FROM CenterMeetingCollectionTable WHERE CustomerId = :clientId")
    public abstract int getConfirmCountByClientId(String clientId);

    @Query("SELECT SUM(CollectedAmount) FROM CenterMeetingCollectionTable WHERE CustomerId = :clientId")
    public abstract int getTotalAmtPaidByClientId(String clientId);

    @Query("SELECT SUM(CashCollectedAmount) FROM CenterMeetingCollectionTable WHERE CustomerId = :clientId")
    public abstract int getTotalCashAmtPaidByClientId(String clientId);

    @Query("SELECT SUM(DigitalCollectedAmount) FROM CenterMeetingCollectionTable WHERE CustomerId = :clientId")
    public abstract int getTotalDigitalAmtPaidByClientId(String clientId);

    @Query("SELECT * FROM CashDenominationTable WHERE StaffId=:staffId and CMDate = :cmDate")
    public abstract CashDenominationTable getCashDenominationTable(String staffId, Date cmDate);

    @Query("SELECT * FROM CashDenominationTable WHERE Sync=0 ")
    public abstract List<CashDenominationTable> getCashDenominationTableList();

    @Query("SELECT * FROM StaffActivityTable WHERE Sync=0 ")
    public abstract List<StaffActivityTable> getStaffActivityTableList();

    @Query("SELECT * FROM CenterMeetingTable WHERE GroupName = :groupName")
    public abstract List<CenterMeetingTable> getCenterMeetingTableListByGroupName(String groupName);

    @Query("SELECT * FROM CenterMeetingTable WHERE GroupName = :groupName group by CustomerId")
    public abstract List<CenterMeetingTable> getMembersFromCenterMeetingTableForAttendance(String groupName);

    @Query("SELECT * FROM OverDueCMTable WHERE CenterName = :centerName group by CustomerId")
    public abstract List<OverDueCMTable> getMembersFromOverDueCMTable(String centerName);

    @Query("SELECT * FROM FTOverDueCMTable WHERE CenterName = :centerName group by CustomerId")
    public abstract List<FTOverDueCMTable> getMembersFromFTOverDueCMTable(String centerName);

    @Query("SELECT * FROM CenterMeetingTable WHERE GroupName = :groupName ")
    public abstract List<CenterMeetingTable> getMembersFromCenterMeetingTableForCollection(String groupName);

//    @Query("SELECT * FROM CenterMeetingTable WHERE GroupName = :groupName and CollectionType = :collectionType ")
//    public abstract List<CenterMeetingTable> getMembersFromCenterMeetingTableForCollectionByCollectionType(String groupName,String collectionType);

//    @Query("SELECT * FROM CenterMeetingTable WHERE CenterName = :centerName and CollectionType = :collectionType ")
//    public abstract List<CenterMeetingTable> getMembersFromCMTableByCenterAndCollectionType(String centerName,String collectionType);

    @Query("SELECT * FROM CenterMeetingTable WHERE GroupName = :groupName group by CustomerId ")
    public abstract List<CenterMeetingTable> getDistinctMembersFromCenterMeetingTableForCollection(String groupName);

    @Query("SELECT * FROM CenterMeetingTable WHERE CenterName = :centerName group by CustomerId ")
    public abstract List<CenterMeetingTable> getDistinctMembersFromCMTableByCenter(String centerName);

    @Query("SELECT * FROM CenterMeetingCollectionTable WHERE GroupName = :groupName group by CustomerId ")
    public abstract List<CenterMeetingCollectionTable> getDistinctMembersFromCenterMeetingCollectionTable(String groupName);

    @Query("SELECT * FROM CenterMeetingCollectionTable WHERE CenterName = :centerName group by CustomerId ")
    public abstract List<CenterMeetingCollectionTable> getDistinctMembersFromCMCollectionTableByCenter(String centerName);

//    @Query("SELECT * FROM CenterMeetingCollectionTable WHERE GroupName = :groupName and CollectionType = :collectionType group by CustomerId ")
//    public abstract List<CenterMeetingCollectionTable> getDistinctMembersFromCenterMeetingCollectionTableByCollectionType(String groupName,String collectionType);

    @Query("SELECT * FROM CenterMeetingCollectionTable WHERE GroupName = :groupName and DigitalPayment = :isDigitalPayment group by CustomerId ")
    public abstract List<CenterMeetingCollectionTable> getDistinctMembersFromCMCollectionTableByDigitalPayment(String groupName,boolean isDigitalPayment);

    @Query("SELECT * FROM CenterMeetingCollectionTable WHERE Sync=0 and isSaved=1")
    public abstract List<CenterMeetingCollectionTable> getCenterMeetingCollectionTableList();

    @Query("SELECT DISTINCT CenterId from CenterMeetingCollectionTable where Sync=1")
    public abstract List<String> getDistinctCenterIdFromCenterMeetingCollectionTable();

    @Query("SELECT DISTINCT CenterName from CenterMeetingCollectionTable where Sync=0 and isSaved=1")
    public abstract List<String> getDistinctCenterNameFromCenterMeetingCollectionTable();

    @Query("SELECT GroupName FROM CenterMeetingTable WHERE CustomerId = :clientId")
    public abstract String getCenterMeetingGroupNameByClientId(String clientId);

    @Query("SELECT * FROM CenterMeetingCollectionTable WHERE CustomerId = :clientId")
    public abstract List<CenterMeetingCollectionTable> getCMCollectionTableListByCustomerId(String clientId);

    @Query("SELECT CollectedAmount FROM CenterMeetingCollectionTable WHERE CustomerId = :clientId and LoanID=:loanId")
    public abstract int getCollectedAmountByClientIdAndLoanId(String clientId,String loanId);

    @Query("SELECT * FROM DynamicUITable WHERE FieldTag =:TAGname and screenID = :screenID and plusSignName is null LIMIT 1")
    public abstract DynamicUITable getRowByTAGandScreen(String TAGname, String screenID);

    @Query("SELECT * FROM DuplicateDynamicUITable WHERE FieldTag =:TAGname and screenID = :screenID and plusSignName is null LIMIT 1")
    public abstract DuplicateDynamicUITable getRowByTAGandScreenInDuplicateTable(String TAGname, String screenID);

    @Query("SELECT * FROM DuplicateDynamicUITable WHERE FieldTag =:TAGname and screenName = :screenName and plusSignName is null LIMIT 1")
    public abstract DuplicateDynamicUITable getRowByTAGAndScreenNameInDuplicateTable(String TAGname, String screenName);

    @Query("SELECT * FROM DynamicUITable WHERE FieldName =:FieldName and screenID = :screenID and plusSignName is null LIMIT 1")
    public abstract DynamicUITable getRowByFieldNameAndScreen(String FieldName, String screenID);


    @Query("SELECT * FROM DynamicUITable WHERE screenID = :screenID and plusSignName is null and clientID !=:clientId ")
    public abstract List<DynamicUITable> getTableByScreenAndClient(String screenID, String clientId);

    @Query("DELETE FROM DynamicUITable WHERE screenID = :screenID ")
    public abstract void deleteRecords(String screenID);

    @Query("DELETE FROM DynamicUITable WHERE screenName = :screenName ")
    public abstract void deleteDynamicTableByScreenName(String screenName);

    @Query("DELETE FROM RawDataTable WHERE screen_name = :screenName ")
    public abstract void deleteRawDataByScreenName(String screenName);

    @Query("DELETE FROM RawDataTable WHERE client_id = :clientId ")
    public abstract void deleteRawDataByClientId(String clientId);

    @Query("DELETE FROM RawDataTable WHERE screen_no = :screenNo and client_id=:clientId and loan_type=:loanType")
    public abstract void deleteRawDataByScreenNoAndLoanType(String screenNo,String clientId,String loanType);

    @Query("DELETE FROM DocumentUploadTable WHERE DocumentDisplayName = :documentDisplayName ")
    public abstract void deleteDocumentFromAddress(String documentDisplayName);

    @Query("DELETE FROM DocumentUploadTable WHERE rawId = :rawId ")
    public abstract void deleteDocumentByRawId(int rawId);

    @Query("DELETE FROM DocumentUploadTableNew WHERE document_name=:documentName and client_id=:clientId and module_type=:moduleType and loan_type=:loanType")
    public abstract void deleteDocumentUploadTableNew(String documentName, String clientId,String moduleType,String loanType);

    @Query("DELETE FROM DocumentUploadTableNew WHERE screen_id=:screenNO and client_id=:clientId and module_type=:moduleType and loan_type=:loanType")
    public abstract void deleteDocumentUploadTableNewByScreenNoClientIDAndLoanType(String screenNO, String clientId,String moduleType,String loanType);

    @Query("DELETE FROM OTPVerificationTable WHERE id = :id ")
    public abstract void deleteOTPVerificationById(int id);

    @Query("DELETE FROM OTPVerificationTable WHERE client_id = :client_id ")
    public abstract void deleteOTPVerificationByClientId(String client_id);

    @Query("DELETE FROM StageDetailsTable")
    public abstract void deleteStageDetails();

    @Query("DELETE FROM RoleNameTable")
    public abstract void deleteRoleNameListTable();

//    @Query("DELETE FROM CollectionTable where id=:id")
//    public abstract void deleteCollectionTable(int id);

    @Query("DELETE FROM RawDataFromServerTable  ")
    public abstract void deleteRawDataFromServerDetails();

    @Query("DELETE FROM CenterMeetingTable  ")
    public abstract void deleteCenterMeetingTable();

    @Query("DELETE FROM FetchOtherDayCMTable  ")
    public abstract void deleteFetchOtherDayCMTable();

    @Query("DELETE FROM OverDueCMTable  ")
    public abstract void deleteOverDueCMTable();

    @Query("DELETE FROM FTOverDueCMTable  ")
    public abstract void deleteFTOverDueCMTable();

    @Query("DELETE FROM InitiatePaymentStatusTable  ")
    public abstract void deleteInitiatePaymentStatusTable();

    @Query("DELETE FROM QCReSubmissionTable  ")
    public abstract void deleteQCReSubmissionTable();

    @Query("DELETE FROM EligibilityTable  ")
    public abstract void deleteEligibilityTable();

    @Query("DELETE FROM BranchProductFeatureMasterTable  ")
    public abstract void deleteBranchProductFeatureMasterTable();

    @Query("DELETE FROM ProductMasterTable  ")
    public abstract void deleteProductMasterTable();

    @Query("DELETE FROM DocumentMasterTable  ")
    public abstract void deleteDocumentMasterTable();

    @Query("DELETE FROM KnowledgeBankTable  ")
    public abstract void deleteKnowledgeBankTable();

    @Query("DELETE FROM UserLoginMenuTable  ")
    public abstract void deleteUserLoginMenuTable();

    @Query("DELETE FROM GetLeadDropDownProductNameTable  ")
    public abstract void deleteGetLeadDropDownProductTable();

    @Query("DELETE FROM GetAddressAddressProofTable  ")
    public abstract void deleteGetAddressAddressProofTable();



    @Query("DELETE FROM GetKYCDropDownIDProofTable  ")
    public abstract void deleteGetKYCDropDownIDProofTable();

    @Query("DELETE FROM GetLeadCustomerTypeTable  ")
    public abstract void deleteGetleadCustomerTypeTable();

    @Query("DELETE FROM GetLeadDropDownTypeOfProfessionTable  ")
    public abstract void deleteGetLeadDropDownTypeOfProfessionTable();

    @Query("DELETE FROM GetLeadDropDownProductTypeTable  ")
    public abstract void deleteGetLeadDropDownProductTypeTable();

    @Query("DELETE FROM GetLeadDropDownSqIdAndNameTable  ")
    public abstract void deleteGetLeadDropDownSqIdAndNameTable();

    @Query("DELETE FROM GetLeadDropDownBankDetailsTable  ")
    public abstract void deleteLeadDropDownBankDetailsTable();

    @Query("DELETE FROM GetLeadDropDownBranchNameTable  ")
    public abstract void deleteGetLeadDropDownBranchNameTable();

    @Query("DELETE FROM DuplicateDynamicUITable WHERE screenID = :screenID ")
    public abstract void deleteDuplicateTable(String screenID);

    @Query("DELETE FROM MasterTable WHERE clientId = :clientId ")
    public abstract void deleteMasterTable(String clientId);

    @Query("DELETE FROM SalesToolTable WHERE clientId = :clientId ")
    public abstract void deleteSalesToolTable(String clientId);

    @Query("DELETE FROM ApplicationStatusTable WHERE clientId = :clientId ")
    public abstract void deleteApplicationStatusTable(String clientId);

    @Query("DELETE FROM LoanProductCodeTable ")
    public abstract void deleteLoanProductCodeTable();

    @Query("DELETE FROM VillageSurveyTable WHERE villageId = :villageId ")
    public abstract void deleteVillageSurveyTable(String villageId);

    @Query("DELETE FROM CenterCreationTable WHERE centerId = :centerId ")
    public abstract void deleteCenterCreationTable(String centerId);

    @Query("DELETE FROM CashDenominationTable WHERE StaffId = :staffId and CMDate=:cmDate ")
    public abstract void deleteCashDenominationTable(String staffId,Date cmDate);

    @Query("DELETE FROM CBCheckTable WHERE loanId = :loanId ")
    public abstract void deleteCBCheckTable(String loanId);

    @Query("DELETE FROM CBCheckTable WHERE clientId = :clientId ")
    public abstract void deleteCBCheckTableByClientId(String clientId);

    @Query("DELETE FROM LoanTable WHERE clientId = :clientId ")
    public abstract void deleteLoanTableByClientId(String clientId);

    @Query("DELETE FROM GRTTable WHERE centerId = :centerId ")
    public abstract void deleteGRTTable(String centerId);

    @Query("DELETE FROM CenterMeetingAttendanceTable WHERE centerId = :centerId ")
    public abstract void deleteCenterMeetingAttendanceTable(String centerId);

    @Query("DELETE FROM GRTTable WHERE clientId = :clientId ")
    public abstract void deleteGRTTableByClientId(String clientId);

    @Query("DELETE FROM GroupTable WHERE clientId = :memberId ")
    public abstract void deleteGroupTable(String memberId);

    @Query("DELETE FROM CGTTable WHERE centerId = :centerId ")
    public abstract void deleteCGTTable(String centerId);

    @Query("DELETE FROM CGTTable WHERE clientId = :clientId ")
    public abstract void deleteCGTTableByClientId(String clientId);

    @Query("DELETE FROM CGTAttendanceTable WHERE ClientId = :ClientId ")
    public abstract void deleteCGTAttendanceTableByClientId(String ClientId);

    @Query("DELETE FROM HouseVerificationTable WHERE clientId = :clientId ")
    public abstract void deleteHouseVerificationTable(String clientId);

    @Query("DELETE FROM LogInTable")
    public abstract void deleteLoginTable();

    @Query("DELETE FROM SubmitDataTable WHERE ApplicationId = :clientId ")
    public abstract void deleteSubmitDataTable(String clientId);

    @Query("DELETE FROM WorkFlowTable ")
    public abstract void deleteWorkFlowTable();

    @Query("UPDATE DynamicUITable set value=:newValue WHERE FieldTag=:tagName and screenID = :screenID ")
    public abstract void updateDynamicTableValue(String tagName, String screenID, String newValue);

    @Query("UPDATE DynamicUITable set optional=:newValue , isEditable=:isEditable  WHERE FieldTag=:tagName and screenID = :screenID ")
    public abstract void updateDynamicTableOptionalValueForPremiumLead(String tagName, String screenID, String newValue,boolean isEditable);


    @Query("DELETE FROM DocumentUploadTableNew WHERE id=:id ")
    public abstract void deleteDocumentFromTable(int id);

    @Query("DELETE FROM LogTable")
    public abstract void deleteLogTable();


    @Query("DELETE FROM DocumentUploadTableNew WHERE client_id=:clientId ")
    public abstract void deleteDocumentFromTableByClientId(String clientId);

    @Query("DELETE FROM CenterMeetingTable WHERE CenterId=:centerId ")
    public abstract void deleteCenterMeetingTableByCenterId(String centerId);

    @Query("DELETE FROM CenterMeetingCollectionTable WHERE Sync=1 ")
    public abstract void deleteCenterMeetingCollectionTableBySync();

    @Query("DELETE FROM CenterMeetingAttendanceTable WHERE Sync=1 ")
    public abstract void deleteCenterMeetingAttendanceTableBySync();

    @Query("DELETE FROM CashDenominationTable WHERE Sync=1 ")
    public abstract void deleteCashDenominationTableBySync();

    @Query("DELETE FROM StaffActivityTable WHERE Sync=1 ")
    public abstract void deleteStaffActivityTableBySync();

    @Query("DELETE FROM CashCollectionSummaryTable WHERE Sync=1 ")
    public abstract void deleteCashCollectionSummaryTableBySync();

    @Query("UPDATE DocumentUploadTableNew set ResponseMessage=:response, document_status=:status" +
            " WHERE client_id=:clientId and loan_type = :loanType " +
            " and isHeader=:isHeader and document_name=:documentName and module_type =:moduleType")
    public abstract void updateDocumentResponseAndStatus(String response,boolean status,String clientId,String loanType,boolean isHeader,
                                                         String documentName,String moduleType);

    @Query("UPDATE DocumentUploadTableNew set ResponseMessage=:response, document_status=:status" +
            " WHERE id=:id ")
    public abstract void updateDocumentResponseAndStatusByID(String response,boolean status,int id);

    @Query("UPDATE DocumentUploadTableNew set file_path=:filePath, document_status=:status" +
            " WHERE id=:id ")
    public abstract void updateDocumentFilePathAndStatusByID(String filePath,boolean status,int id);

    @Query("UPDATE DocumentUploadTableNew set ResponseMessage=:response, document_status=:status" +
            " WHERE client_id=:clientId ")
    public abstract void updateDocumentResponseAndStatusByClientId(String response,boolean status,String clientId);

    @Query("UPDATE DocumentUploadTableNew set file_name=:fileName, file_path=:filePath" +
            " WHERE id=:id ")
    public abstract void updateDocumentFileNameAndFilePathByID(String fileName,String filePath,int id);

    @Query("UPDATE DynamicUITable set value=:newValue ,isEditable=:isEditable  WHERE FieldTag=:tagName and screenName = :screenName ")
    public abstract void updateDynamicTableValueAndNonEditable(String tagName, String screenName, String newValue, boolean isEditable);


    @Query("UPDATE DynamicUITable set CoRelationID=:correlationId WHERE  screenName = :screenName ")
    public abstract void updateDynamicTableCorrelationIdByScreenName(String screenName, String correlationId);

    @Query("UPDATE DynamicUITable set clientID=:clientId WHERE  screenID = :screenId ")
    public abstract void updateDynamicTableClientIdByScreenId(String screenId,String clientId);

    @Query("UPDATE DynamicUITable set moduleType=:moduleType WHERE  screenID = :screenId ")
    public abstract void updateDynamicTableModuleTypeByScreenId(String screenId,String moduleType);

    @Query("UPDATE DynamicUITable set Length=:maxLength ,isEditable=:isEditable  WHERE FieldTag=:tagName and screenName = :screenName ")
    public abstract void updateDynamicTableMAXValueAndNonEditable(String tagName, String screenName, String maxLength, boolean isEditable);

    @Query("UPDATE DynamicUITable set MinLength=:minLength ,isEditable=:isEditable  WHERE FieldTag=:tagName and screenName = :screenName ")
    public abstract void updateDynamicTableMINvalueAndNonEditable(String tagName, String screenName, String minLength, boolean isEditable);

    @Query("UPDATE DynamicUITable set value=:newValue ,isEditable=:isEditable,Visibility=:isVisible  WHERE FieldName=:fieldName and screenName = :screenName ")
    public abstract void updateDynamicTableValueByFieldName(String fieldName, String screenName, String newValue, boolean isEditable,boolean isVisible);

    @Query("UPDATE DynamicUITable set value=:newValue ,isEditable=:isEditable,Visibility=:isVisible WHERE FieldTag=:tagName and screenName = :screenName ")
    public abstract void updateDynamicTableValueAndVisibility(String tagName, String screenName, String newValue, boolean isEditable,boolean isVisible);


    @Query("UPDATE DynamicUITable set optional=:newValue  WHERE FieldTag=:tagName and screenName = :screenName ")
    public abstract void updateDynamicTableOptionalValue(String tagName, String screenName, String newValue);


    @Query("UPDATE DynamicUITable set FieldType=:fieldType ,isEditable=:isEditable,Visibility=:isVisible WHERE FieldTag=:tagName and screenName = :screenName ")
    public abstract void updateDynamicTableFieldType(String tagName, String screenName, String fieldType, boolean isEditable,boolean isVisible);


    @Query("UPDATE DynamicUITable set FieldName=:newValue ,isEditable=:isEditable,Visibility=:isVisible WHERE FieldTag=:tagName and screenName = :screenName ")
    public abstract void updateDynamicTableFieldNameAndVisibility(String tagName, String screenName, String newValue, boolean isEditable,boolean isVisible);

    @Query("UPDATE DynamicUITable set FieldName=:newValue WHERE FieldTag=:tagName and screenID = :screenID ")
    public abstract void updateDynamicTableFieldName(String tagName, String screenID, String newValue);

    @Query("UPDATE DynamicUITable set isValid=:isValid WHERE FieldTag=:tagName and screenID = :screenID ")
    public abstract void updateIsValid(String tagName, String screenID, boolean isValid);

    @Query("UPDATE DynamicUITable set ErrorMessage=:errMsg WHERE FieldTag=:tagName and screenID = :screenID ")
    public abstract void updateErrorMessage(String tagName, String screenID, String errMsg);

    @Query("UPDATE DynamicUITable set ErrorMessage=:errMsg , isValid=:isValid WHERE FieldTag=:tagName and screenName = :screenName ")
    public abstract void updateIsValidAndErrorMessage(String tagName, String screenName, boolean isValid, String errMsg);

    @Query("SELECT * from  OTPVerificationTable where client_id=:clientId and loan_type=:loanType")
    public abstract List<OTPVerificationTable> getOTPTableList(String clientId,String loanType);

}
