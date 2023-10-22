package com.saartak.el.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.saartak.el.database.converter.DateConverter;
import com.saartak.el.database.dao.DynamicUIDao;
import com.saartak.el.database.entity.ApplicationStatusTable;
import com.saartak.el.database.entity.BranchProductFeatureMasterTable;
import com.saartak.el.database.entity.CBCheckTable;
import com.saartak.el.database.entity.CGTAttendanceTable;
import com.saartak.el.database.entity.CGTTable;
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
import com.saartak.el.database.entity.CIBILTable;
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
import com.saartak.el.database.entity.LocationTable;
import com.saartak.el.database.entity.LogInTable;
import com.saartak.el.database.entity.LogTable;
import com.saartak.el.database.entity.MasterTable;
import com.saartak.el.database.entity.LoanTable;
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

@Database(entities = {DynamicUITable.class, SubmitDataTable.class,
        LocationTable.class, RawDataTable.class, DocumentUploadTable.class, DuplicateDynamicUITable.class,
        WorkFlowTable.class, StageDetailsTable.class, RawDataFromServerTable.class,
        MasterTable.class, OTPVerificationTable.class, ProductMasterTable.class, EkycAttemptTable.class,
        DocumentMasterTable.class, DocumentUploadTableNew.class, KnowledgeBankTable.class, LogTable.class,
        VillageSurveyTable.class, CenterCreationTable.class, FetchOtherDayCollectionTable.class, CMPhotoTable.class, FetchOtherDayCMTable.class, CGTTable.class, PlannerTable.class,
        GroupTable.class, CBCheckTable.class, HouseVerificationTable.class, GRTTable.class,
        LogInTable.class, LoanTable.class, EligibilityTable.class, BranchProductFeatureMasterTable.class,
        LeadTable.class, ColdCallTable.class, SalesToolTable.class, CenterMeetingTable.class, InitiatePaymentStatusTable.class,
        QCReSubmissionTable.class, SODTable.class, ApplicationStatusTable.class, CIBILTable.class,
        CenterMeetingAttendanceTable.class, CenterMeetingCollectionTable.class,
        StaffActivityTable.class, NetworkStrengthTable.class, CashCollectionSummaryTable.class, OverDueCMTable.class, FTOverDueCMTable.class,
        CMFetchTable.class,CashDenominationTable.class,RoleNameTable.class, CGTAttendanceTable.class,
        GRTAttendanceTable.class, LoanProductCodeTable.class, UserLoginMenuTable.class, GetLeadDropDownProductNameTable.class, GetLeadCustomerTypeTable.class,
        GetLeadDropDownProductTypeTable.class, GetLeadDropDownSqIdAndNameTable.class, GetLeadDropDownBankDetailsTable.class,
        GetLeadDropDownBranchNameTable.class, GetLeadDropDownTypeOfProfessionTable.class, GetKYCDropDownIDProofTable.class, GetAddressAddressProofTable.class},
        version = 1, exportSchema = true)

    @TypeConverters(DateConverter.class)
    public abstract class DynamicUIDatabase extends RoomDatabase {

    // --- SINGLETON ---
    private static volatile DynamicUIDatabase INSTANCE;

    // --- DAO ---
    public abstract DynamicUIDao dynamicUIDao();
}
