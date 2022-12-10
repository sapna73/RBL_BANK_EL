package com.swadhaar.los.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.swadhaar.los.database.converter.DateConverter;
import com.swadhaar.los.database.dao.DynamicUIDao;
import com.swadhaar.los.database.entity.ApplicationStatusTable;
import com.swadhaar.los.database.entity.BranchProductFeatureMasterTable;
import com.swadhaar.los.database.entity.CBCheckTable;
import com.swadhaar.los.database.entity.CGTAttendanceTable;
import com.swadhaar.los.database.entity.CGTTable;
import com.swadhaar.los.database.entity.CMPhotoTable;
import com.swadhaar.los.database.entity.CMFetchTable;
import com.swadhaar.los.database.entity.CashCollectionSummaryTable;
import com.swadhaar.los.database.entity.CashDenominationTable;
import com.swadhaar.los.database.entity.CenterCreationTable;
import com.swadhaar.los.database.entity.FTOverDueCMTable;
import com.swadhaar.los.database.entity.FetchOtherDayCMTable;
import com.swadhaar.los.database.entity.FetchOtherDayCollectionTable;
import com.swadhaar.los.database.entity.GRTAttendanceTable;
import com.swadhaar.los.database.entity.InitiatePaymentStatusTable;
import com.swadhaar.los.database.entity.KnowledgeBankTable;
import com.swadhaar.los.database.entity.LoanProductCodeTable;
import com.swadhaar.los.database.entity.OverDueCMTable;
import com.swadhaar.los.database.entity.SalesToolTable;
import com.swadhaar.los.database.entity.StaffActivityTable;
import com.swadhaar.los.database.entity.CenterMeetingAttendanceTable;
import com.swadhaar.los.database.entity.CenterMeetingCollectionTable;
import com.swadhaar.los.database.entity.CenterMeetingTable;
import com.swadhaar.los.database.entity.CIBILTable;
import com.swadhaar.los.database.entity.ColdCallTable;
import com.swadhaar.los.database.entity.DocumentMasterTable;
import com.swadhaar.los.database.entity.DocumentUploadTable;
import com.swadhaar.los.database.entity.DocumentUploadTableNew;
import com.swadhaar.los.database.entity.DuplicateDynamicUITable;
import com.swadhaar.los.database.entity.DynamicUITable;
import com.swadhaar.los.database.entity.EkycAttemptTable;
import com.swadhaar.los.database.entity.EligibilityTable;
import com.swadhaar.los.database.entity.GRTTable;
import com.swadhaar.los.database.entity.GroupTable;
import com.swadhaar.los.database.entity.HouseVerificationTable;
import com.swadhaar.los.database.entity.LeadTable;
import com.swadhaar.los.database.entity.LocationTable;
import com.swadhaar.los.database.entity.LogInTable;
import com.swadhaar.los.database.entity.LogTable;
import com.swadhaar.los.database.entity.MasterTable;
import com.swadhaar.los.database.entity.LoanTable;
import com.swadhaar.los.database.entity.NetworkStrengthTable;
import com.swadhaar.los.database.entity.OTPVerificationTable;
import com.swadhaar.los.database.entity.PlannerTable;
import com.swadhaar.los.database.entity.ProductMasterTable;
import com.swadhaar.los.database.entity.QCReSubmissionTable;
import com.swadhaar.los.database.entity.RawDataFromServerTable;
import com.swadhaar.los.database.entity.RawDataTable;
import com.swadhaar.los.database.entity.RoleNameTable;
import com.swadhaar.los.database.entity.SODTable;
import com.swadhaar.los.database.entity.StageDetailsTable;
import com.swadhaar.los.database.entity.SubmitDataTable;
import com.swadhaar.los.database.entity.VillageSurveyTable;
import com.swadhaar.los.database.entity.WorkFlowTable;

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
        GRTAttendanceTable.class, LoanProductCodeTable.class}, version = 11, exportSchema = true)



@TypeConverters(DateConverter.class)
public abstract class DynamicUIDatabase extends RoomDatabase {

    // --- SINGLETON ---
    private static volatile DynamicUIDatabase INSTANCE;

    // --- DAO ---
    public abstract DynamicUIDao dynamicUIDao();
}
