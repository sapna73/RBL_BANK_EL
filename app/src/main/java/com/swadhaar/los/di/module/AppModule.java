package com.swadhaar.los.di.module;

import android.app.Application;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;

import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.bfil.uilibrary.helpers.AppHelper;
import com.commonsware.cwac.saferoom.SafeHelperFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.swadhaar.los.App;
import com.swadhaar.los.api.DynamicUIWebservice;
import com.swadhaar.los.database.DynamicUIDatabase;
import com.swadhaar.los.database.dao.DynamicUIDao;
import com.swadhaar.los.dynamicui.services.interceptors.NullOnEmptyConverterFactory;
import com.swadhaar.los.dynamicui.services.interceptors.UnsafeOkHttpClient;
import com.swadhaar.los.fragments.LOSBaseFragment;
import com.swadhaar.los.repositories.DynamicUIRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.swadhaar.los.constants.AppConstant.BASE_URL;


@Module(includes = ViewModelModule.class)
public class AppModule {

    // TODO: Production v-5-6 , UAT v-4-5
    // TODO: Production release migration [6-7] , app version 4.1.2
    // TODO: Production release migration [7-8] , app version 5.1.2
    // TODO: Production release migration [8-9] , app version 6.1.2
    // TODO: Production release migration [9-10] , app version 10.1.2

    static final Migration MIGRATION_6_7 = new Migration(6, 7) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
           /* database.execSQL("ALTER TABLE DuplicateDynamicUITable "
                    +"ADD COLUMN ProductId TEXT");
            database.execSQL("ALTER TABLE DuplicateDynamicUITable "
                    +"ADD COLUMN UniqueId TEXT");*/
            /*database.execSQL("ALTER TABLE DynamicUITable "
                    +"ADD COLUMN UniqueId TEXT");
            database.execSQL("ALTER TABLE RawDataTable "
                    +"ADD COLUMN UniqueId TEXT");*/
           /* database.execSQL("CREATE TABLE IF NOT EXISTS ProductMasterTable (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,ProductCode TEXT," +
                    "FinacleCode TEXT,ProductCategory TEXT,SchemeName TEXT,MaxLoanAmount TEXT,MinLoanAmount TEXT,MinROI TEXT,MaxROI TEXT," +
                    "DefaultROI TEXT,MinTenor TEXT,MaxTenor TEXT,ProcessingFee TEXT,InsuranceFee TEXT,Step TEXT,DBRCalMtd TEXT," +
                    "FeeAmt TEXT,Percentage TEXT,GST TEXT,LTVCalMtd TEXT)");*/

            // TODO: Migration 7 to 8
           /* database.execSQL("CREATE TABLE IF NOT EXISTS EkycAttemptTable (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,aadhaar_no TEXT," +
                    "attempt INTEGER NOT NULL,screen_no TEXT,tvMobNo TEXT)");*/


           /* // TODO: Migration 8 to 9
             database.execSQL("CREATE TABLE IF NOT EXISTS DocumentMasterTable (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,ScreenId TEXT," +
                    "CustomerType TEXT,DisplayName TEXT,DocumentName TEXT,TagName TEXT,FileFormat TEXT)");*/

     /*       // TODO: Migration 9 to 10
            database.execSQL("CREATE TABLE IF NOT EXISTS DocumentUploadTableNew (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,screen_id TEXT," +
                    "client_id TEXT,document_name TEXT,document_sub_name TEXT,document_tag TEXT,customer_type TEXT,display_name TEXT" +
                    ",file_name TEXT,position TEXT,document_status INTEGER,loan_type TEXT,module_type TEXT,file_path TEXT)");*/


            // TODO: 08-01-2020 Migration 1 to 2
           /* database.execSQL("ALTER TABLE SubmitDataTable "
                    +"ADD COLUMN IMEINumber TEXT");
            database.execSQL("ALTER TABLE SubmitDataTable "
                    +"ADD COLUMN request TEXT");*/

            // TODO: 13-01-2020  Migration 2 to 3
          /*  database.execSQL("ALTER TABLE DocumentUploadTableNew "
                    +"ADD COLUMN secretKey BLOB");
            database.execSQL("ALTER TABLE DocumentUploadTableNew "
                    +"ADD COLUMN randomNumber BLOB");*/

          /*  // TODO: 20-01-2020  Migration 3 to 4
            database.execSQL("ALTER TABLE OTPVerificationTable "
                    +"ADD COLUMN isOTPGenerated INTEGER NOT NULL DEFAULT 0");*/

            // TODO: 06-02-2020  Migration 4 to 5
//            database.execSQL("CREATE TABLE IF NOT EXISTS LogTable (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,date_time TEXT," +
//                    "staff_id TEXT,methodName TEXT,tvMobNo TEXT,screen_no TEXT,screen_name TEXT,client_id TEXT,loan_type TEXT," +
//                    "module_type TEXT,imei_number TEXT)");
//            // TODO: 15-02-2020  Migration 5 to 6
//            database.execSQL("ALTER TABLE DocumentUploadTableNew "
//                    +"ADD COLUMN FILES TEXT");

            // TODO: **************** v4.1.2 production migration [6-7] *********************
            // TODO: Migration created but not added
            database.execSQL("ALTER TABLE DynamicUITable "
                    + "ADD COLUMN sync INTEGER NOT NULL DEFAULT 0");

            database.execSQL("ALTER TABLE DuplicateDynamicUITable "
                    + "ADD COLUMN sync INTEGER NOT NULL DEFAULT 0");

            database.execSQL("CREATE TABLE IF NOT EXISTS WorkFlowTableTemp (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,LoanProductName TEXT," +
                    "LoanProductCode TEXT,EKYC INTEGER NOT NULL,CB INTEGER NOT NULL,LoanApplication INTEGER NOT NULL," +
                    "CGT INTEGER NOT NULL,GRT INTEGER NOT NULL,ROI TEXT,Tenure TEXT,MinAge TEXT,MaxAge TEXT,MinAmount TEXT,MaxAmount TEXT)");

            // Remove the old table
            database.execSQL("DROP TABLE WorkFlowTable");
            // Change the table name to the correct one
            database.execSQL("ALTER TABLE WorkFlowTableTemp RENAME TO WorkFlowTable");

            database.execSQL("ALTER TABLE MasterTable "
                    + "ADD COLUMN centerId TEXT");

            database.execSQL("ALTER TABLE MasterTable "
                    + "ADD COLUMN centerName TEXT");

            database.execSQL("ALTER TABLE MasterTable "
                    + "ADD COLUMN CBStatus INTEGER NOT NULL DEFAULT 0");

            database.execSQL("ALTER TABLE MasterTable "
                    + "ADD COLUMN isDataNeedsToCaptureFromServer INTEGER NOT NULL DEFAULT 0");

            database.execSQL("ALTER TABLE DocumentUploadTableNew "
                    + "ADD COLUMN NumberOfImages INTEGER NOT NULL DEFAULT 0");

            database.execSQL("ALTER TABLE DocumentUploadTableNew "
                    + "ADD COLUMN isEditable INTEGER NOT NULL DEFAULT 0");

            database.execSQL("CREATE TABLE IF NOT EXISTS \"VillageSurveyTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"villageId\"\tTEXT,\n" +
                    "\t\"villageName\"\tTEXT,\n" +
                    "\t\"district\"\tTEXT,\n" +
                    "\t\"pincode\"\tTEXT,\n" +
                    "\t\"city\"\tTEXT,\n" +
                    "\t\"loan_type\"\tTEXT,\n" +
                    "\t\"Status\"\tTEXT,\n" +
                    "\t\"Remarks\"\tTEXT,\n" +
                    "\t\"sync\"\tINTEGER NOT NULL,\n" +
                    "\t\"createdBy\"\tTEXT,\n" +
                    "\t\"branchId\"\tTEXT,\n" +
                    "\t\"branchGSTcode\"\tTEXT,\n" +
                    "\t\"created_date\"\tTEXT,\n" +
                    "\t\"response\"\tTEXT\n" +
                    ")");

            database.execSQL("CREATE TABLE IF NOT EXISTS \"CenterCreationTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"centerId\"\tTEXT,\n" +
                    "\t\"centerName\"\tTEXT,\n" +
                    "\t\"villageName\"\tTEXT,\n" +
                    "\t\"villageId\"\tTEXT,\n" +
                    "\t\"loan_type\"\tTEXT,\n" +
                    "\t\"Status\"\tTEXT,\n" +
                    "\t\"Remarks\"\tTEXT,\n" +
                    "\t\"sync\"\tINTEGER NOT NULL,\n" +
                    "\t\"houseVerificationStatus\"\tINTEGER NOT NULL,\n" +
                    "\t\"createdBy\"\tTEXT,\n" +
                    "\t\"branchId\"\tTEXT,\n" +
                    "\t\"branchGSTcode\"\tTEXT,\n" +
                    "\t\"created_date\"\tTEXT,\n" +
                    "\t\"response\"\tTEXT,\n" +
                    "\t\"NoOfMembers\"\tINTEGER NOT NULL\n" +
                    ")");

            database.execSQL("CREATE TABLE IF NOT EXISTS \"CGTTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"centerId\"\tTEXT,\n" +
                    "\t\"centerName\"\tTEXT,\n" +
                    "\t\"clientId\"\tTEXT,\n" +
                    "\t\"clientName\"\tTEXT,\n" +
                    "\t\"phoneNo\"\tTEXT,\n" +
                    "\t\"loanId\"\tTEXT,\n" +
                    "\t\"loanProduct\"\tTEXT,\n" +
                    "\t\"loanProductCode\"\tTEXT,\n" +
                    "\t\"villageName\"\tTEXT,\n" +
                    "\t\"villageId\"\tTEXT,\n" +
                    "\t\"loan_type\"\tTEXT,\n" +
                    "\t\"Status\"\tTEXT,\n" +
                    "\t\"Remarks\"\tTEXT,\n" +
                    "\t\"sync\"\tINTEGER NOT NULL,\n" +
                    "\t\"AllDataCaptured\"\tINTEGER NOT NULL,\n" +
                    "\t\"createdBy\"\tTEXT,\n" +
                    "\t\"branchId\"\tTEXT,\n" +
                    "\t\"branchGSTcode\"\tTEXT,\n" +
                    "\t\"created_date\"\tTEXT,\n" +
                    "\t\"response\"\tTEXT,\n" +
                    "\t\"cycleOneStartSession\"\tTEXT,\n" +
                    "\t\"cycleOneEndSession\"\tTEXT,\n" +
                    "\t\"cycleTwoStartSession\"\tTEXT,\n" +
                    "\t\"cycleTwoEndSession\"\tTEXT,\n" +
                    "\t\"cycleOneAttendance\"\tINTEGER NOT NULL,\n" +
                    "\t\"cycleTwoAttendance\"\tINTEGER NOT NULL,\n" +
                    "\t\"activity1\"\tINTEGER NOT NULL,\n" +
                    "\t\"activity2\"\tINTEGER NOT NULL,\n" +
                    "\t\"activity3\"\tINTEGER NOT NULL,\n" +
                    "\t\"houseVerification\"\tINTEGER NOT NULL,\n" +
                    "\t\"isCycleOneCompleted\"\tINTEGER NOT NULL,\n" +
                    "\t\"isCycleTwoCompleted\"\tINTEGER NOT NULL,\n" +
                    "\t\"cgtRejected\"\tINTEGER NOT NULL,\n" +
                    "\t\"isExistingCustomer\"\tINTEGER NOT NULL\n" +
                    ")");

            database.execSQL("CREATE TABLE IF NOT EXISTS \"PlannerTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"clientId\"\tTEXT,\n" +
                    "\t\"staffId\"\tTEXT,\n" +
                    "\t\"staffName\"\tTEXT,\n" +
                    "\t\"branchId\"\tTEXT,\n" +
                    "\t\"branchGSTcode\"\tTEXT,\n" +
                    "\t\"loan_type\"\tTEXT,\n" +
                    "\t\"created_date\"\tTEXT,\n" +
                    "\t\"isTripStart\"\tINTEGER NOT NULL,\n" +
                    "\t\"isTripEnd\"\tINTEGER NOT NULL,\n" +
                    "\t\"PurposeOfVisit\"\tTEXT,\n" +
                    "\t\"ApplicationId\"\tTEXT,\n" +
                    "\t\"ShopName\"\tTEXT,\n" +
                    "\t\"AlongWithStaff\"\tINTEGER NOT NULL,\n" +
                    "\t\"EmployeeId\"\tTEXT,\n" +
                    "\t\"EmployeeName\"\tTEXT,\n" +
                    "\t\"Designation\"\tTEXT,\n" +
                    "\t\"TravelWithOwnVehicle\"\tINTEGER NOT NULL,\n" +
                    "\t\"VehicleType\"\tINTEGER NOT NULL,\n" +
                    "\t\"StartLat\"\tTEXT,\n" +
                    "\t\"StartLong\"\tTEXT,\n" +
                    "\t\"EndLat\"\tTEXT,\n" +
                    "\t\"EndLong\"\tTEXT,\n" +
                    "\t\"Distance\"\tTEXT,\n" +
                    "\t\"sync\"\tINTEGER NOT NULL\n" +
                    ")");

            database.execSQL("CREATE TABLE IF NOT EXISTS \"GroupTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"groupId\"\tTEXT,\n" +
                    "\t\"groupName\"\tTEXT,\n" +
                    "\t\"memberId\"\tTEXT,\n" +
                    "\t\"memberName\"\tTEXT,\n" +
                    "\t\"isTeamLeaderOne\"\tINTEGER NOT NULL,\n" +
                    "\t\"isTeamLeaderTwo\"\tINTEGER NOT NULL,\n" +
                    "\t\"centerId\"\tTEXT,\n" +
                    "\t\"centerName\"\tTEXT,\n" +
                    "\t\"villageName\"\tTEXT,\n" +
                    "\t\"villageId\"\tTEXT,\n" +
                    "\t\"loan_type\"\tTEXT,\n" +
                    "\t\"Remarks\"\tTEXT,\n" +
                    "\t\"createdBy\"\tTEXT,\n" +
                    "\t\"branchId\"\tTEXT,\n" +
                    "\t\"branchGSTcode\"\tTEXT,\n" +
                    "\t\"created_date\"\tTEXT,\n" +
                    "\t\"response\"\tTEXT\n" +
                    ")");

            database.execSQL("CREATE TABLE IF NOT EXISTS \"CBCheckTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"loanId\"\tTEXT,\n" +
                    "\t\"clientId\"\tTEXT,\n" +
                    "\t\"clientName\"\tTEXT,\n" +
                    "\t\"centerId\"\tTEXT,\n" +
                    "\t\"centerName\"\tTEXT,\n" +
                    "\t\"phoneNo\"\tTEXT,\n" +
                    "\t\"loan_type\"\tTEXT,\n" +
                    "\t\"loanProduct\"\tTEXT,\n" +
                    "\t\"loanProductCode\"\tTEXT,\n" +
                    "\t\"Status\"\tTEXT,\n" +
                    "\t\"Remarks\"\tTEXT,\n" +
                    "\t\"isCBSuccess\"\tINTEGER NOT NULL,\n" +
                    "\t\"createdBy\"\tTEXT,\n" +
                    "\t\"branchId\"\tTEXT,\n" +
                    "\t\"branchGSTcode\"\tTEXT,\n" +
                    "\t\"created_date\"\tTEXT,\n" +
                    "\t\"response\"\tTEXT,\n" +
                    "\t\"score\"\tINTEGER NOT NULL,\n" +
                    "\t\"MaxEligibleAmount\"\tINTEGER NOT NULL\n" +
                    ")");

            database.execSQL("CREATE TABLE IF NOT EXISTS \"HouseVerificationTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"clientId\"\tTEXT,\n" +
                    "\t\"clientName\"\tTEXT,\n" +
                    "\t\"loanId\"\tTEXT,\n" +
                    "\t\"loanProduct\"\tTEXT,\n" +
                    "\t\"phoneNo\"\tTEXT,\n" +
                    "\t\"dob\"\tTEXT,\n" +
                    "\t\"age\"\tTEXT,\n" +
                    "\t\"nomineeName\"\tTEXT,\n" +
                    "\t\"distanceFromCenter\"\tTEXT,\n" +
                    "\t\"kyc1_id_proof\"\tTEXT,\n" +
                    "\t\"kyc1_id\"\tTEXT,\n" +
                    "\t\"kyc2_id_proof\"\tTEXT,\n" +
                    "\t\"kyc2_id\"\tTEXT,\n" +
                    "\t\"kyc1_address\"\tTEXT,\n" +
                    "\t\"nominee_id_proof\"\tTEXT,\n" +
                    "\t\"nominee_id\"\tTEXT,\n" +
                    "\t\"centerId\"\tTEXT,\n" +
                    "\t\"centerName\"\tTEXT,\n" +
                    "\t\"villageName\"\tTEXT,\n" +
                    "\t\"villageId\"\tTEXT,\n" +
                    "\t\"loan_type\"\tTEXT,\n" +
                    "\t\"Status\"\tTEXT,\n" +
                    "\t\"Remarks\"\tTEXT,\n" +
                    "\t\"createdBy\"\tTEXT,\n" +
                    "\t\"reviewBy\"\tTEXT,\n" +
                    "\t\"branchId\"\tTEXT,\n" +
                    "\t\"branchGSTcode\"\tTEXT,\n" +
                    "\t\"created_date\"\tTEXT,\n" +
                    "\t\"response\"\tTEXT,\n" +
                    "\t\"isHousePhotoCaptured\"\tINTEGER NOT NULL,\n" +
                    "\t\"isHousePhotoUploaded\"\tINTEGER NOT NULL,\n" +
                    "\t\"file_name\"\tTEXT,\n" +
                    "\t\"file_path\"\tTEXT,\n" +
                    "\t\"houseVerification\"\tINTEGER NOT NULL\n" +
                    ")");

            database.execSQL("CREATE TABLE IF NOT EXISTS \"GRTTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"centerId\"\tTEXT,\n" +
                    "\t\"centerName\"\tTEXT,\n" +
                    "\t\"clientId\"\tTEXT,\n" +
                    "\t\"clientName\"\tTEXT,\n" +
                    "\t\"phoneNo\"\tTEXT,\n" +
                    "\t\"loanId\"\tTEXT,\n" +
                    "\t\"loanProduct\"\tTEXT,\n" +
                    "\t\"loanProductCode\"\tTEXT,\n" +
                    "\t\"villageName\"\tTEXT,\n" +
                    "\t\"villageId\"\tTEXT,\n" +
                    "\t\"loan_type\"\tTEXT,\n" +
                    "\t\"Status\"\tTEXT,\n" +
                    "\t\"Remarks\"\tTEXT,\n" +
                    "\t\"sync\"\tINTEGER NOT NULL,\n" +
                    "\t\"AllDataCaptured\"\tINTEGER NOT NULL,\n" +
                    "\t\"createdBy\"\tTEXT,\n" +
                    "\t\"branchId\"\tTEXT,\n" +
                    "\t\"branchGSTcode\"\tTEXT,\n" +
                    "\t\"created_date\"\tTEXT,\n" +
                    "\t\"response\"\tTEXT,\n" +
                    "\t\"houseVerification\"\tINTEGER NOT NULL,\n" +
                    "\t\"memberDetailVerification\"\tINTEGER NOT NULL,\n" +
                    "\t\"groupFormation\"\tINTEGER NOT NULL,\n" +
                    "\t\"grtRejected\"\tINTEGER NOT NULL,\n" +
                    "\t\"isExistingCustomer\"\tINTEGER NOT NULL\n" +
                    ")");

            database.execSQL("CREATE TABLE IF NOT EXISTS \"LogInTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"Key\"\tTEXT,\n" +
                    "\t\"UserID\"\tTEXT,\n" +
                    "\t\"Username\"\tTEXT,\n" +
                    "\t\"Password\"\tTEXT,\n" +
                    "\t\"ApiKey\"\tTEXT,\n" +
                    "\t\"UserType\"\tTEXT,\n" +
                    "\t\"FName\"\tTEXT,\n" +
                    "\t\"MName\"\tTEXT,\n" +
                    "\t\"LName\"\tTEXT,\n" +
                    "\t\"ErrorMessage\"\tTEXT,\n" +
                    "\t\"ErrorCode\"\tINTEGER NOT NULL,\n" +
                    "\t\"isNewUser\"\tINTEGER NOT NULL,\n" +
                    "\t\"UserIPAddress\"\tTEXT,\n" +
                    "\t\"UserSystemName\"\tTEXT,\n" +
                    "\t\"ModelPermission\"\tTEXT,\n" +
                    "\t\"Error\"\tTEXT,\n" +
                    "\t\"Message\"\tTEXT,\n" +
                    "\t\"DepartmentName\"\tTEXT,\n" +
                    "\t\"BranchID\"\tTEXT,\n" +
                    "\t\"RoleId\"\tINTEGER NOT NULL,\n" +
                    "\t\"BCID\"\tINTEGER NOT NULL,\n" +
                    "\t\"RoleName\"\tTEXT,\n" +
                    "\t\"DeptID\"\tTEXT,\n" +
                    "\t\"Mobile\"\tTEXT,\n" +
                    "\t\"DesignationID\"\tINTEGER NOT NULL,\n" +
                    "\t\"BranchName\"\tTEXT,\n" +
                    "\t\"DesignationName\"\tTEXT,\n" +
                    "\t\"Zone\"\tTEXT,\n" +
                    "\t\"Region\"\tTEXT,\n" +
                    "\t\"BranchGSTCode\"\tTEXT,\n" +
                    "\t\"ApplicationType\"\tTEXT,\n" +
                    "\t\"IsKillPreviousSession\"\tINTEGER NOT NULL\n" +
                    ")");

            database.execSQL("CREATE TABLE IF NOT EXISTS \"LoanTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"loanId\"\tTEXT,\n" +
                    "\t\"clientId\"\tTEXT,\n" +
                    "\t\"clientName\"\tTEXT,\n" +
                    "\t\"isExistingCustomer\"\tINTEGER NOT NULL,\n" +
                    "\t\"phoneNo\"\tTEXT,\n" +
                    "\t\"loan_type\"\tTEXT,\n" +
                    "\t\"loan_amount\"\tTEXT,\n" +
                    "\t\"ApplicationStatus\"\tTEXT,\n" +
                    "\t\"FinalStatus\"\tTEXT,\n" +
                    "\t\"Remarks\"\tTEXT,\n" +
                    "\t\"sync\"\tINTEGER NOT NULL,\n" +
                    "\t\"AllDataCaptured\"\tINTEGER NOT NULL,\n" +
                    "\t\"createdBy\"\tTEXT,\n" +
                    "\t\"branchId\"\tTEXT,\n" +
                    "\t\"branchGSTcode\"\tTEXT,\n" +
                    "\t\"created_date\"\tTEXT,\n" +
                    "\t\"response\"\tTEXT,\n" +
                    "\t\"centerId\"\tTEXT,\n" +
                    "\t\"centerName\"\tTEXT,\n" +
                    "\t\"loanProduct\"\tTEXT,\n" +
                    "\t\"loanProductCode\"\tTEXT,\n" +
                    "\t\"isInterested\"\tINTEGER NOT NULL\n" +
                    ")");

            database.execSQL("CREATE TABLE IF NOT EXISTS \"EligibilityTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"CustomerId\"\tTEXT,\n" +
                    "\t\"CustomerName\"\tTEXT,\n" +
                    "\t\"LoanAccountNumber\"\tTEXT,\n" +
                    "\t\"EMI_Amount\"\tINTEGER NOT NULL,\n" +
                    "\t\"ProductCode\"\tTEXT,\n" +
                    "\t\"ProductName\"\tTEXT,\n" +
                    "\t\"LoanStatus\"\tTEXT,\n" +
                    "\t\"isInterested\"\tINTEGER NOT NULL\n" +
                    ")");

            database.execSQL("CREATE TABLE IF NOT EXISTS \"LeadTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, \n" +
                    "\t\"clientId\"\tTEXT,\n" +
                    "\t\"clientName\"\tTEXT,\n" +
                    "\t\"MarketName\"\tTEXT,\n" +
                    "\t\"BusinessName\"\tTEXT,\n" +
                    "\t\"MobileNo\"\tTEXT,\n" +
                    "\t\"Lat\"\tTEXT,\n" +
                    "\t\"Long\"\tTEXT,\n" +
                    "\t\"loan_type\"\tTEXT,\n" +
                    "\t\"Remarks\"\tTEXT,\n" +
                    "\t\"InterestedInLoan\"\tINTEGER NOT NULL,\n" +
                    "\t\"leadStatus\"\tTEXT,\n" +
                    "\t\"NextFollowUpDate\"\tTEXT,\n" +
                    "\t\"Pincode\"\tTEXT,\n" +
                    "\t\"sync\"\tINTEGER NOT NULL,\n" +
                    "\t\"createdBy\"\tTEXT,\n" +
                    "\t\"created_date\"\tTEXT,\n" +
                    "\t\"response\"\tTEXT,\n" +
                    "\t\"IsDataCaptured\"\tINTEGER NOT NULL,\n" +
                    "\t\"IsPremium\"\tINTEGER NOT NULL\n" +
                    ");");


/*
            database.execSQL("CREATE TABLE IF NOT EXISTS \"LeadTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"clientId\"\tTEXT,\n" +
                    "\t\"clientName\"\tTEXT,\n" +
                    "\t\"MarketName\"\tTEXT,\n" +
                    "\t\"BusinessName\"\tTEXT,\n" +
                    "\t\"MobileNo\"\tTEXT,\n" +
                    "\t\"Lat\"\tTEXT,\n" +
                    "\t\"Long\"\tTEXT,\n" +
                    "\t\"loan_type\"\tTEXT,\n" +
                    "\t\"Remarks\"\tTEXT,\n" +
                    "\t\"InterestedInLoan\"\tINTEGER NOT NULL,\n" +
                    "\t\"leadStatus\"\tTEXT,\n" +
                    "\t\"NextFollowUpDate\"\tTEXT,\n" +
                    "\t\"Pincode\"\tTEXT,\n" +
                    "\t\"sync\"\tINTEGER NOT NULL,\n" +
                    "\t\"createdBy\"\tTEXT,\n" +
                    "\t\"created_date\"\tTEXT,\n" +
                    "\t\"response\"\tTEXT,\n" +
                    "\t\"IsDataCaptured\"\tINTEGER NOT NULL\n" +
                    ")");
*/

            database.execSQL("CREATE TABLE IF NOT EXISTS \"ColdCallTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"clientId\"\tTEXT,\n" +
                    "\t\"clientName\"\tTEXT,\n" +
                    "\t\"MarketName\"\tTEXT,\n" +
                    "\t\"BusinessName\"\tTEXT,\n" +
                    "\t\"MobileNo\"\tTEXT,\n" +
                    "\t\"Lat\"\tTEXT,\n" +
                    "\t\"Long\"\tTEXT,\n" +
                    "\t\"loan_type\"\tTEXT,\n" +
                    "\t\"Remarks\"\tTEXT,\n" +
                    "\t\"InterestedInLoan\"\tINTEGER NOT NULL,\n" +
                    "\t\"When\"\tTEXT,\n" +
                    "\t\"NextFollowUpDate\"\tTEXT,\n" +
                    "\t\"Pincode\"\tTEXT,\n" +
                    "\t\"sync\"\tINTEGER NOT NULL,\n" +
                    "\t\"createdBy\"\tTEXT,\n" +
                    "\t\"created_date\"\tINTEGER,\n" +
                    "\t\"response\"\tTEXT\n" +
                    ")");

            database.execSQL("CREATE TABLE IF NOT EXISTS \"CenterMeetingTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"CenterId\"\tTEXT,\n" +
                    "\t\"CenterName\"\tTEXT,\n" +
                    "\t\"GroupId\"\tTEXT,\n" +
                    "\t\"GroupName\"\tTEXT,\n" +
                    "\t\"CustomerId\"\tTEXT,\n" +
                    "\t\"CustomerName\"\tTEXT,\n" +
                    "\t\"CustomerCode\"\tTEXT,\n" +
                    "\t\"BranchCode\"\tTEXT,\n" +
                    "\t\"BranchName\"\tTEXT,\n" +
                    "\t\"VillageId\"\tTEXT,\n" +
                    "\t\"VillageCode\"\tTEXT,\n" +
                    "\t\"VillageName\"\tTEXT,\n" +
                    "\t\"LoanOfficerId\"\tTEXT,\n" +
                    "\t\"max_eligible_amount\"\tTEXT,\n" +
                    "\t\"product\"\tTEXT,\n" +
                    "\t\"ProductName\"\tTEXT,\n" +
                    "\t\"centerMeetingDate\"\tTEXT\n" +
                    ")");

            database.execSQL("CREATE TABLE IF NOT EXISTS \"QCReSubmissionTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"ExternalCustomerId\"\tTEXT,\n" +
                    "\t\"CustomerId\"\tTEXT,\n" +
                    "\t\"CustomerName\"\tTEXT,\n" +
                    "\t\"CenterId\"\tTEXT,\n" +
                    "\t\"CenterName\"\tTEXT,\n" +
                    "\t\"GroupId\"\tTEXT,\n" +
                    "\t\"GroupName\"\tTEXT,\n" +
                    "\t\"LoanApplicationNumber\"\tTEXT,\n" +
                    "\t\"LoanAmount\"\tTEXT,\n" +
                    "\t\"ProductCode\"\tTEXT,\n" +
                    "\t\"ProductName\"\tTEXT,\n" +
                    "\t\"BarcodeNo\"\tTEXT,\n" +
                    "\t\"RejectReason\"\tTEXT,\n" +
                    "\t\"createdBy\"\tTEXT\n" +
                    ")");

            database.execSQL("CREATE TABLE IF NOT EXISTS \"SODTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"staffId\"\tTEXT,\n" +
                    "\t\"staffName\"\tTEXT,\n" +
                    "\t\"branchId\"\tTEXT,\n" +
                    "\t\"branchGSTcode\"\tTEXT,\n" +
                    "\t\"created_date\"\tTEXT,\n" +
                    "\t\"isSOD\"\tINTEGER NOT NULL,\n" +
                    "\t\"isEOD\"\tINTEGER NOT NULL,\n" +
                    "\t\"SOD_Latitude\"\tTEXT,\n" +
                    "\t\"SOD_Longitue\"\tTEXT,\n" +
                    "\t\"EOD_Latitude\"\tTEXT,\n" +
                    "\t\"EOD_Longitude\"\tTEXT,\n" +
                    "\t\"sync\"\tINTEGER NOT NULL,\n" +
                    "\t\"response\"\tTEXT\n" +
                    ")");

            database.execSQL("CREATE TABLE IF NOT EXISTS \"ApplicationStatusTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"clientId\"\tTEXT,\n" +
                    "\t\"clientName\"\tTEXT,\n" +
                    "\t\"loan_type\"\tTEXT,\n" +
                    "\t\"currentStage\"\tTEXT,\n" +
                    "\t\"CurrentStageId\"\tINTEGER NOT NULL,\n" +
                    "\t\"Remarks\"\tTEXT,\n" +
                    "\t\"createdBy\"\tTEXT,\n" +
                    "\t\"reviewBy\"\tTEXT,\n" +
                    "\t\"created_date\"\tINTEGER\n" +
                    ")");

            database.execSQL("CREATE TABLE IF NOT EXISTS \"CIBILTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"rawId\"\tINTEGER NOT NULL,\n" +
                    "\t\"screen_no\"\tTEXT,\n" +
                    "\t\"screen_name\"\tTEXT,\n" +
                    "\t\"timestamp\"\tTEXT,\n" +
                    "\t\"client_id\"\tTEXT,\n" +
                    "\t\"name\"\tTEXT,\n" +
                    "\t\"mobileNumber\"\tTEXT,\n" +
                    "\t\"loan_type\"\tTEXT,\n" +
                    "\t\"moduleType\"\tTEXT,\n" +
                    "\t\"userId\"\tTEXT,\n" +
                    "\t\"score\"\tTEXT,\n" +
                    "\t\"Decision\"\tTEXT,\n" +
                    "\t\"Reason\"\tTEXT,\n" +
                    "\t\"ApplicationId\"\tTEXT,\n" +
                    "\t\"SolutionSetInstanceId\"\tTEXT,\n" +
                    "\t\"isCBChecked\"\tINTEGER NOT NULL\n" +
                    ")");

            // TODO: **************** v4.1.2 production migration [6-7] *********************
        }
    };

    static final Migration MIGRATION_7_8 = new Migration(7, 8) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {

            // TODO: **************** v5.1.2 production migration [7-8] *********************

//            database.execSQL("ALTER TABLE SubmitDataTable "
//                    + "ADD COLUMN RoleId TEXT");

//            database.execSQL("ALTER TABLE SubmitDataTable "
//                    + "ADD COLUMN StageId TEXT");
//
//            database.execSQL("ALTER TABLE SubmitDataTable "
//                    + "ADD COLUMN FieldId TEXT");
//
//            database.execSQL("ALTER TABLE SubmitDataTable "
//                    + "ADD COLUMN WorkflowId TEXT");

            database.execSQL("CREATE TABLE IF NOT EXISTS \"RoleNameTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"staffId\"\tTEXT,\n" +
                    "\t\"rolename\"\tTEXT,\n" +
                    "\t\"roleId\"\tTEXT,\n" +
                    "\t\"isSelected\"\tINTEGER NOT NULL\n" +
                    ")");

            database.execSQL("CREATE TABLE IF NOT EXISTS \"CashDenominationTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"count_2000\"\tINTEGER NOT NULL,\n" +
                    "\t\"count_1000\"\tINTEGER NOT NULL,\n" +
                    "\t\"count_500\"\tINTEGER NOT NULL,\n" +
                    "\t\"count_200\"\tINTEGER NOT NULL,\n" +
                    "\t\"count_100\"\tINTEGER NOT NULL,\n" +
                    "\t\"count_50\"\tINTEGER NOT NULL,\n" +
                    "\t\"count_20\"\tINTEGER NOT NULL,\n" +
                    "\t\"count_10\"\tINTEGER NOT NULL,\n" +
                    "\t\"count_5\"\tINTEGER NOT NULL,\n" +
                    "\t\"count_2\"\tINTEGER NOT NULL,\n" +
                    "\t\"count_1\"\tINTEGER NOT NULL,\n" +
                    "\t\"total_2000\"\tINTEGER NOT NULL,\n" +
                    "\t\"total_1000\"\tINTEGER NOT NULL,\n" +
                    "\t\"total_500\"\tINTEGER NOT NULL,\n" +
                    "\t\"total_200\"\tINTEGER NOT NULL,\n" +
                    "\t\"total_100\"\tINTEGER NOT NULL,\n" +
                    "\t\"total_50\"\tINTEGER NOT NULL,\n" +
                    "\t\"total_20\"\tINTEGER NOT NULL,\n" +
                    "\t\"total_10\"\tINTEGER NOT NULL,\n" +
                    "\t\"total_5\"\tINTEGER NOT NULL,\n" +
                    "\t\"total_2\"\tINTEGER NOT NULL,\n" +
                    "\t\"total_1\"\tINTEGER NOT NULL,\n" +
                    "\t\"total_count\"\tINTEGER NOT NULL,\n" +
                    "\t\"total_amount\"\tINTEGER NOT NULL,\n" +
                    "\t\"StaffId\"\tTEXT,\n" +
                    "\t\"CMDate\"\tINTEGER,\n" +
                    "\t\"Sync\"\tINTEGER NOT NULL,\n" +
                    "\t\"SyncDateTime\"\tINTEGER,\n" +
                    "\t\"SyncResponse\"\tTEXT,\n" +
                    "\t\"refId\"\tTEXT\n" +
                    ")");

            database.execSQL("CREATE TABLE IF NOT EXISTS \"CashCollectionSummaryTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"CenterName\"\tTEXT,\n" +
                    "\t\"CenterDue\"\tINTEGER NOT NULL,\n" +
                    "\t\"CashCollection\"\tINTEGER NOT NULL,\n" +
                    "\t\"DigitalCollection\"\tINTEGER NOT NULL,\n" +
                    "\t\"TotalCollection\"\tINTEGER NOT NULL,\n" +
                    "\t\"CollectionDate\"\tINTEGER,\n" +
                    "\t\"Confirm\"\tINTEGER NOT NULL,\n" +
                    "\t\"Sync\"\tINTEGER NOT NULL,\n" +
                    "\t\"SyncDateTime\"\tINTEGER,\n" +
                    "\t\"StaffId\"\tTEXT,\n" +
                    "\t\"SyncResponse\"\tTEXT,\n" +
                    "\t\"refId\"\tTEXT\n" +
                    ")");

            database.execSQL("CREATE TABLE IF NOT EXISTS \"NetworkStrengthTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"network_strength\"\tINTEGER NOT NULL\n" +
                    ")");

            database.execSQL("CREATE TABLE IF NOT EXISTS \"StaffActivityTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"CenterName\"\tTEXT,\n" +
                    "\t\"StaffId\"\tTEXT,\n" +
                    "\t\"Activity\"\tTEXT,\n" +
                    "\t\"Status\"\tINTEGER NOT NULL,\n" +
                    "\t\"TimeStamp\"\tTEXT,\n" +
                    "\t\"Battery\"\tINTEGER NOT NULL,\n" +
                    "\t\"NetworkSignal\"\tINTEGER NOT NULL,\n" +
                    "\t\"Latitude\"\tTEXT,\n" +
                    "\t\"Longitude\"\tTEXT,\n" +
                    "\t\"Sync\"\tINTEGER NOT NULL,\n" +
                    "\t\"SyncDateTime\"\tTEXT,\n" +
                    "\t\"SyncResponse\"\tTEXT,\n" +
                    "\t\"refId\"\tTEXT\n" +
                    ")");

            database.execSQL("CREATE TABLE IF NOT EXISTS \"CenterMeetingCollectionTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"LoanID\"\tTEXT ,\n" +
                    "\t\"CustomerId\"\tTEXT,\n" +
                    "\t\"CustomerName\"\tTEXT,\n" +
                    "\t\"GroupId\"\tTEXT,\n" +
                    "\t\"GroupName\"\tTEXT,\n" +
                    "\t\"CenterId\"\tTEXT,\n" +
                    "\t\"CenterName\"\tTEXT,\n" +
                    "\t\"CollectedAmount\"\tINTEGER NOT NULL,\n" +
                    "\t\"EMI\"\tINTEGER NOT NULL,\n" +
                    "\t\"TotalDue\"\tINTEGER NOT NULL,\n" +
                    "\t\"SavingsCollection\"\tINTEGER NOT NULL,\n" +
                    "\t\"Reason\"\tTEXT,\n" +
                    "\t\"CMDate\"\tINTEGER,\n" +
                    "\t\"CollectionDateTime\"\tINTEGER,\n" +
                    "\t\"PaidByOtherMember\"\tINTEGER NOT NULL,\n" +
                    "\t\"PTPDate\"\tINTEGER,\n" +
                    "\t\"DigitalPayment\"\tINTEGER NOT NULL,\n" +
                    "\t\"SavingsConfirm\"\tINTEGER NOT NULL,\n" +
                    "\t\"Confirm\"\tINTEGER NOT NULL,\n" +
                    "\t\"Sync\"\tINTEGER NOT NULL,\n" +
                    "\t\"SyncDateTime\"\tINTEGER,\n" +
                    "\t\"StaffId\"\tTEXT,\n" +
                    "\t\"SyncResponse\"\tTEXT,\n" +
                    "\t\"refId\"\tTEXT,\n" +
                    "\t\"approved\"\tINTEGER NOT NULL,\n" +
                    "\t\"DueDate\"\tINTEGER,\n" +
                    "\t\"CollectionType\"\tTEXT\n" +
                    ")");

            database.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS \"index_CenterMeetingCollectionTable_LoanID\" ON \"CenterMeetingCollectionTable\" (\n" +
                    "\t\"LoanID\"\n" +
                    ");");

            database.execSQL("CREATE TABLE IF NOT EXISTS \"CenterMeetingAttendanceTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"CustomerID\"\tTEXT,\n" +
                    "\t\"CustomerName\"\tTEXT,\n" +
                    "\t\"CenterId\"\tTEXT,\n" +
                    "\t\"CenterName\"\tTEXT,\n" +
                    "\t\"Attentance\"\tINTEGER NOT NULL,\n" +
                    "\t\"Reason\"\tTEXT,\n" +
                    "\t\"DateTime\"\tINTEGER,\n" +
                    "\t\"Sync\"\tINTEGER NOT NULL,\n" +
                    "\t\"SyncDateTime\"\tINTEGER,\n" +
                    "\t\"StaffId\"\tTEXT,\n" +
                    "\t\"SyncResponse\"\tTEXT,\n" +
                    "\t\"refId\"\tTEXT\n" +
                    ")");

            database.execSQL("CREATE TABLE IF NOT EXISTS \"BranchProductFeatureMasterTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"BranchCode\"\tTEXT,\n" +
                    "\t\"ProductCode\"\tTEXT,\n" +
                    "\t\"ProductName\"\tTEXT,\n" +
                    "\t\"ProcessName\"\tTEXT,\n" +
                    "\t\"ActivityName\"\tTEXT,\n" +
                    "\t\"isEnabled\"\tINTEGER NOT NULL\n" +
                    ")");

            // Remove the old table
            database.execSQL("DROP TABLE CenterMeetingTable");

            database.execSQL("CREATE TABLE IF NOT EXISTS \"CenterMeetingTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"CenterId\"\tTEXT,\n" +
                    "\t\"CenterName\"\tTEXT,\n" +
                    "\t\"GroupId\"\tTEXT,\n" +
                    "\t\"GroupName\"\tTEXT,\n" +
                    "\t\"CustomerId\"\tTEXT,\n" +
                    "\t\"CustomerName\"\tTEXT,\n" +
                    "\t\"CustomerCode\"\tTEXT,\n" +
                    "\t\"BranchCode\"\tTEXT,\n" +
                    "\t\"BranchName\"\tTEXT,\n" +
                    "\t\"LoanOfficerId\"\tTEXT,\n" +
                    "\t\"max_eligible_amount\"\tTEXT,\n" +
                    "\t\"product\"\tTEXT,\n" +
                    "\t\"ProductName\"\tTEXT,\n" +
                    "\t\"centerMeetingDate\"\tTEXT,\n" +
                    "\t\"EMI_Amount\"\tINTEGER NOT NULL,\n" +
                    "\t\"Total_Due\"\tINTEGER NOT NULL,\n" +
                    "\t\"SavingsCollection\"\tINTEGER NOT NULL,\n" +
                    "\t\"LoanAccountNumber\"\tTEXT,\n" +
                    "\t\"RequiredInstallmentAmount\"\tINTEGER NOT NULL,\n" +
                    "\t\"Current_Installment\"\tINTEGER NOT NULL,\n" +
                    "\t\"Total_Installment\"\tINTEGER NOT NULL,\n" +
                    "\t\"CollectionType\"\tTEXT\n" +
                    ")");


            // TODO: **************** v5.1.2 production migration [7-8] *********************
        }
    };

    static final Migration MIGRATION_8_9 = new Migration(8, 9) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {

            // TODO: **************** v6.1.2 production migration [8-9] *********************

            database.execSQL("CREATE TABLE IF NOT EXISTS \"KnowledgeBankTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"DocumentName\"\tTEXT,\n" +
                    "\t\"FileName\"\tTEXT,\n" +
                    "\t\"FileType\"\tTEXT,\n" +
                    "\t\"UpdatedBy\"\tTEXT,\n" +
                    "\t\"UpdatedOn\"\tTEXT\n" +
                    ")");

            // Remove the old table
            database.execSQL("DROP TABLE CenterCreationTable");

            database.execSQL("CREATE TABLE IF NOT EXISTS \"CenterCreationTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"centerId\"\tTEXT,\n" +
                    "\t\"centerName\"\tTEXT,\n" +
                    "\t\"villageName\"\tTEXT,\n" +
                    "\t\"villageId\"\tTEXT,\n" +
                    "\t\"zoneName\"\tTEXT,\n" +
                    "\t\"areaName\"\tTEXT,\n" +
                    "\t\"loan_type\"\tTEXT,\n" +
                    "\t\"Status\"\tTEXT,\n" +
                    "\t\"Remarks\"\tTEXT,\n" +
                    "\t\"sync\"\tINTEGER NOT NULL,\n" +
                    "\t\"createdBy\"\tTEXT,\n" +
                    "\t\"branchId\"\tTEXT,\n" +
                    "\t\"branchGSTcode\"\tTEXT,\n" +
                    "\t\"created_date\"\tTEXT,\n" +
                    "\t\"response\"\tTEXT,\n" +
                    "\t\"NoOfMembers\"\tINTEGER NOT NULL\n" +
                    ")");

            database.execSQL("CREATE TABLE IF NOT EXISTS \"FetchOtherDayCollectionTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"centerName\"\tTEXT,\n" +
                    "\t\"createdBy\"\tTEXT,\n" +
                    "\t\"created_date\"\tTEXT\n" +
                    ")");

            database.execSQL("CREATE TABLE IF NOT EXISTS \"CMPhotoTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"CenterName\"\tTEXT,\n" +
                    "\t\"CenterId\"\tTEXT,\n" +
                    "\t\"file_name\"\tTEXT,\n" +
                    "\t\"file_path\"\tTEXT\n" +
                    ")");

            database.execSQL("CREATE TABLE IF NOT EXISTS \"FetchOtherDayCMTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"CenterId\"\tTEXT,\n" +
                    "\t\"CenterName\"\tTEXT,\n" +
                    "\t\"GroupId\"\tTEXT,\n" +
                    "\t\"GroupName\"\tTEXT,\n" +
                    "\t\"CustomerId\"\tTEXT,\n" +
                    "\t\"CustomerName\"\tTEXT,\n" +
                    "\t\"CustomerCode\"\tTEXT,\n" +
                    "\t\"BranchCode\"\tTEXT,\n" +
                    "\t\"BranchName\"\tTEXT,\n" +
                    "\t\"LoanOfficerId\"\tTEXT,\n" +
                    "\t\"max_eligible_amount\"\tTEXT,\n" +
                    "\t\"product\"\tTEXT,\n" +
                    "\t\"ProductName\"\tTEXT,\n" +
                    "\t\"CenterMeetingDate\"\tTEXT,\n" +
                    "\t\"CenterMeetingTime\"\tTEXT,\n" +
                    "\t\"MemberRelationName\"\tTEXT,\n" +
                    "\t\"EMI_Amount\"\tINTEGER NOT NULL,\n" +
                    "\t\"Total_Due\"\tINTEGER NOT NULL,\n" +
                    "\t\"SavingsCollection\"\tINTEGER NOT NULL,\n" +
                    "\t\"LoanAccountNumber\"\tTEXT,\n" +
                    "\t\"RequiredInstallmentAmount\"\tINTEGER NOT NULL,\n" +
                    "\t\"Current_Installment\"\tINTEGER NOT NULL,\n" +
                    "\t\"Total_Installment\"\tINTEGER NOT NULL,\n" +
                    "\t\"CollectionType\"\tTEXT\n" +
                    ")");

            // Remove the old table
            database.execSQL("DROP TABLE CGTTable");

            database.execSQL("CREATE TABLE IF NOT EXISTS \"CGTTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"centerId\"\tTEXT,\n" +
                    "\t\"centerName\"\tTEXT,\n" +
                    "\t\"clientId\"\tTEXT,\n" +
                    "\t\"clientName\"\tTEXT,\n" +
                    "\t\"phoneNo\"\tTEXT,\n" +
                    "\t\"loanId\"\tTEXT,\n" +
                    "\t\"loanProduct\"\tTEXT,\n" +
                    "\t\"loanProductCode\"\tTEXT,\n" +
                    "\t\"villageName\"\tTEXT,\n" +
                    "\t\"villageId\"\tTEXT,\n" +
                    "\t\"loan_type\"\tTEXT,\n" +
                    "\t\"Status\"\tTEXT,\n" +
                    "\t\"Remarks\"\tTEXT,\n" +
                    "\t\"sync\"\tINTEGER NOT NULL,\n" +
                    "\t\"AllDataCaptured\"\tINTEGER NOT NULL,\n" +
                    "\t\"createdBy\"\tTEXT,\n" +
                    "\t\"branchId\"\tTEXT,\n" +
                    "\t\"branchGSTcode\"\tTEXT,\n" +
                    "\t\"created_date\"\tTEXT,\n" +
                    "\t\"response\"\tTEXT,\n" +
                    "\t\"cycleOneStartSession\"\tTEXT,\n" +
                    "\t\"cycleOneEndSession\"\tTEXT,\n" +
                    "\t\"cycleTwoStartSession\"\tTEXT,\n" +
                    "\t\"cycleTwoEndSession\"\tTEXT,\n" +
                    "\t\"activity1\"\tINTEGER NOT NULL,\n" +
                    "\t\"activity2\"\tINTEGER NOT NULL,\n" +
                    "\t\"activity3\"\tINTEGER NOT NULL,\n" +
                    "\t\"houseVerification\"\tINTEGER NOT NULL,\n" +
                    "\t\"isCycleOneCompleted\"\tINTEGER NOT NULL,\n" +
                    "\t\"isCycleTwoCompleted\"\tINTEGER NOT NULL,\n" +
                    "\t\"cgtRejected\"\tINTEGER NOT NULL,\n" +
                    "\t\"isExistingCustomer\"\tINTEGER NOT NULL,\n" +
                    "\t\"isDropOutCustomer\"\tINTEGER NOT NULL\n" +
                    ")");

            // Remove the old table
            database.execSQL("DROP TABLE GroupTable");

            database.execSQL("CREATE TABLE IF NOT EXISTS \"GroupTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"groupId\"\tTEXT,\n" +
                    "\t\"groupName\"\tTEXT,\n" +
                    "\t\"clientId\"\tTEXT,\n" +
                    "\t\"clientName\"\tTEXT,\n" +
                    "\t\"isTeamLeaderOne\"\tINTEGER NOT NULL,\n" +
                    "\t\"isTeamLeaderTwo\"\tINTEGER NOT NULL,\n" +
                    "\t\"centerId\"\tTEXT,\n" +
                    "\t\"centerName\"\tTEXT,\n" +
                    "\t\"villageName\"\tTEXT,\n" +
                    "\t\"villageId\"\tTEXT,\n" +
                    "\t\"loan_type\"\tTEXT,\n" +
                    "\t\"Remarks\"\tTEXT,\n" +
                    "\t\"createdBy\"\tTEXT,\n" +
                    "\t\"branchId\"\tTEXT,\n" +
                    "\t\"branchGSTcode\"\tTEXT,\n" +
                    "\t\"created_date\"\tTEXT,\n" +
                    "\t\"response\"\tTEXT\n" +
                    ")");

            // Remove the old table
            database.execSQL("DROP TABLE LoanTable");

            database.execSQL("CREATE TABLE IF NOT EXISTS \"LoanTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"loanId\"\tTEXT,\n" +
                    "\t\"clientId\"\tTEXT,\n" +
                    "\t\"clientName\"\tTEXT,\n" +
                    "\t\"isExistingCustomer\"\tINTEGER NOT NULL,\n" +
                    "\t\"phoneNo\"\tTEXT,\n" +
                    "\t\"loan_type\"\tTEXT,\n" +
                    "\t\"loan_amount\"\tTEXT,\n" +
                    "\t\"ApplicationStatus\"\tTEXT,\n" +
                    "\t\"FinalStatus\"\tTEXT,\n" +
                    "\t\"Remarks\"\tTEXT,\n" +
                    "\t\"sync\"\tINTEGER NOT NULL,\n" +
                    "\t\"AllDataCaptured\"\tINTEGER NOT NULL,\n" +
                    "\t\"createdBy\"\tTEXT,\n" +
                    "\t\"branchId\"\tTEXT,\n" +
                    "\t\"branchGSTcode\"\tTEXT,\n" +
                    "\t\"created_date\"\tTEXT,\n" +
                    "\t\"response\"\tTEXT,\n" +
                    "\t\"centerId\"\tTEXT,\n" +
                    "\t\"centerName\"\tTEXT,\n" +
                    "\t\"loanProductName\"\tTEXT,\n" +
                    "\t\"loanProductCode\"\tTEXT,\n" +
                    "\t\"isInterested\"\tINTEGER NOT NULL\n" +
                    ")");

            database.execSQL("CREATE TABLE IF NOT EXISTS \"SalesToolTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"clientId\"\tTEXT,\n" +
                    "\t\"clientName\"\tTEXT,\n" +
                    "\t\"ageRange\"\tTEXT,\n" +
                    "\t\"cityOfResidence\"\tTEXT,\n" +
                    "\t\"residentType\"\tTEXT,\n" +
                    "\t\"residentStability\"\tTEXT,\n" +
                    "\t\"firmName\"\tTEXT,\n" +
                    "\t\"firmType\"\tTEXT,\n" +
                    "\t\"businessType\"\tTEXT,\n" +
                    "\t\"businessPlace\"\tTEXT,\n" +
                    "\t\"yearsInBusiness\"\tTEXT,\n" +
                    "\t\"ownAPlot\"\tTEXT,\n" +
                    "\t\"ownership\"\tTEXT,\n" +
                    "\t\"incomeProofAvailable\"\tTEXT,\n" +
                    "\t\"itrNoOfYears\"\tTEXT,\n" +
                    "\t\"monthlyBusinessTurnOverrange\"\tTEXT,\n" +
                    "\t\"anyExistingLoanRunning\"\tTEXT,\n" +
                    "\t\"typeOfLoan\"\tTEXT,\n" +
                    "\t\"lookingFor\"\tTEXT,\n" +
                    "\t\"typeOfSecurity\"\tTEXT,\n" +
                    "\t\"securityOwnership\"\tTEXT,\n" +
                    "\t\"requestedAmount\"\tTEXT,\n" +
                    "\t\"comfortableEmi\"\tTEXT,\n" +
                    "\t\"tenure\"\tTEXT,\n" +
                    "\t\"totalSales\"\tTEXT,\n" +
                    "\t\"totalPurchase\"\tTEXT,\n" +
                    "\t\"rentOfBusinessPremise\"\tTEXT,\n" +
                    "\t\"labour_ElectricityCost\"\tTEXT,\n" +
                    "\t\"totalExpense\"\tTEXT,\n" +
                    "\t\"netBusinessIncome\"\tTEXT,\n" +
                    "\t\"incomeFromOthersource\"\tTEXT,\n" +
                    "\t\"monthlyHouseholdExpenses\"\tTEXT,\n" +
                    "\t\"monthlyEMI\"\tTEXT,\n" +
                    "\t\"totalMonthlySurplus\"\tTEXT,\n" +
                    "\t\"debtServiceRatio\"\tTEXT,\n" +
                    "\t\"emi_NBS\"\tTEXT,\n" +
                    "\t\"finalEMI_Eligibility\"\tTEXT,\n" +
                    "\t\"applicationAmount\"\tTEXT,\n" +
                    "\t\"amountbasis_FinalEmiEligibility\"\tTEXT,\n" +
                    "\t\"finalAmount\"\tTEXT,\n" +
                    "\t\"MobileNo\"\tTEXT,\n" +
                    "\t\"loan_type\"\tTEXT,\n" +
                    "\t\"sync\"\tINTEGER NOT NULL,\n" +
                    "\t\"createdBy\"\tTEXT,\n" +
                    "\t\"branchId\"\tTEXT,\n" +
                    "\t\"branchGSTCode\"\tTEXT,\n" +
                    "\t\"score\"\tTEXT,\n" +
                    "\t\"created_date\"\tINTEGER,\n" +
                    "\t\"isDataCaptured\"\tINTEGER NOT NULL\n" +
                    ")");

            // Remove the old table
            database.execSQL("DROP TABLE CenterMeetingTable");

            database.execSQL("CREATE TABLE IF NOT EXISTS \"CenterMeetingTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"CenterId\"\tTEXT,\n" +
                    "\t\"CenterName\"\tTEXT,\n" +
                    "\t\"GroupId\"\tTEXT,\n" +
                    "\t\"GroupName\"\tTEXT,\n" +
                    "\t\"CustomerId\"\tTEXT,\n" +
                    "\t\"CustomerName\"\tTEXT,\n" +
                    "\t\"CustomerCode\"\tTEXT,\n" +
                    "\t\"BranchCode\"\tTEXT,\n" +
                    "\t\"BranchName\"\tTEXT,\n" +
                    "\t\"LoanOfficerId\"\tTEXT,\n" +
                    "\t\"max_eligible_amount\"\tTEXT,\n" +
                    "\t\"product\"\tTEXT,\n" +
                    "\t\"ProductName\"\tTEXT,\n" +
                    "\t\"CenterMeetingDate\"\tTEXT,\n" +
                    "\t\"CenterMeetingTime\"\tTEXT,\n" +
                    "\t\"MemberRelationName\"\tTEXT,\n" +
                    "\t\"EMI_Amount\"\tINTEGER NOT NULL,\n" +
                    "\t\"Total_Due\"\tINTEGER NOT NULL,\n" +
                    "\t\"SavingsCollection\"\tINTEGER NOT NULL,\n" +
                    "\t\"LoanAccountNumber\"\tTEXT,\n" +
                    "\t\"RequiredInstallmentAmount\"\tINTEGER NOT NULL,\n" +
                    "\t\"Current_Installment\"\tINTEGER NOT NULL,\n" +
                    "\t\"Total_Installment\"\tINTEGER NOT NULL,\n" +
                    "\t\"CollectionType\"\tTEXT\n" +
                    ")");

            database.execSQL("CREATE TABLE IF NOT EXISTS \"InitiatePaymentStatusTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"CustomerId\"\tTEXT,\n" +
                    "\t\"CustomerName\"\tTEXT,\n" +
                    "\t\"LoanAccountNumber\"\tTEXT,\n" +
                    "\t\"CenterName\"\tTEXT,\n" +
                    "\t\"Due_Amount\"\tINTEGER NOT NULL,\n" +
                    "\t\"MobileNumber\"\tTEXT,\n" +
                    "\t\"PaymentStatus\"\tTEXT\n" +
                    ")");


            // Remove the old table
            database.execSQL("DROP TABLE CenterMeetingCollectionTable");

            database.execSQL("CREATE TABLE IF NOT EXISTS \"CenterMeetingCollectionTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"LoanID\"\tTEXT,\n" +
                    "\t\"CustomerId\"\tTEXT,\n" +
                    "\t\"CustomerName\"\tTEXT,\n" +
                    "\t\"GroupId\"\tTEXT,\n" +
                    "\t\"GroupName\"\tTEXT,\n" +
                    "\t\"CenterId\"\tTEXT,\n" +
                    "\t\"CenterName\"\tTEXT,\n" +
                    "\t\"CollectedAmount\"\tINTEGER NOT NULL,\n" +
                    "\t\"CashCollectedAmount\"\tINTEGER NOT NULL,\n" +
                    "\t\"DigitalCollectedAmount\"\tINTEGER NOT NULL,\n" +
                    "\t\"EMI\"\tINTEGER NOT NULL,\n" +
                    "\t\"TotalDue\"\tINTEGER NOT NULL,\n" +
                    "\t\"SavingsCollection\"\tINTEGER NOT NULL,\n" +
                    "\t\"SavingsCollectedAmount\"\tINTEGER NOT NULL,\n" +
                    "\t\"Reason\"\tTEXT,\n" +
                    "\t\"CMDate\"\tINTEGER,\n" +
                    "\t\"CenterMeetingTime\"\tTEXT,\n" +
                    "\t\"MemberRelationName\"\tTEXT,\n" +
                    "\t\"CollectionDateTime\"\tINTEGER,\n" +
                    "\t\"PaidByOtherMember\"\tINTEGER NOT NULL,\n" +
                    "\t\"PTPDate\"\tINTEGER,\n" +
                    "\t\"DigitalPayment\"\tINTEGER NOT NULL,\n" +
                    "\t\"SavingsConfirm\"\tINTEGER NOT NULL,\n" +
                    "\t\"Confirm\"\tINTEGER NOT NULL,\n" +
                    "\t\"ConfirmCount\"\tINTEGER NOT NULL,\n" +
                    "\t\"isSaved\"\tINTEGER NOT NULL,\n" +
                    "\t\"Sync\"\tINTEGER NOT NULL,\n" +
                    "\t\"SyncDateTime\"\tINTEGER,\n" +
                    "\t\"StaffId\"\tTEXT,\n" +
                    "\t\"SyncResponse\"\tTEXT,\n" +
                    "\t\"refId\"\tTEXT,\n" +
                    "\t\"approved\"\tINTEGER NOT NULL,\n" +
                    "\t\"DueDate\"\tINTEGER,\n" +
                    "\t\"CollectionType\"\tTEXT,\n" +
                    "\t\"AccountCreated\"\tINTEGER NOT NULL,\n" +
                    "\t\"SmsTriggered\"\tINTEGER NOT NULL,\n" +
                    "\t\"PaymentStatus\"\tINTEGER NOT NULL,\n" +
                    "\t\"RequestId\"\tTEXT\n" +
                    ")");

            database.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS \"index_CenterMeetingCollectionTable_LoanID\" ON \"CenterMeetingCollectionTable\" (\n" +
                    "\t\"LoanID\"\n" +
                    ");");


            // Remove the old table
            database.execSQL("DROP TABLE CashCollectionSummaryTable");

            database.execSQL("CREATE TABLE IF NOT EXISTS \"CashCollectionSummaryTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"CenterName\"\tTEXT,\n" +
                    "\t\"CenterDue\"\tINTEGER NOT NULL,\n" +
                    "\t\"CashCollection\"\tINTEGER NOT NULL,\n" +
                    "\t\"SavingsCollection\"\tINTEGER NOT NULL,\n" +
                    "\t\"DigitalCollection\"\tINTEGER NOT NULL,\n" +
                    "\t\"TotalCollection\"\tINTEGER NOT NULL,\n" +
                    "\t\"CollectionDate\"\tINTEGER,\n" +
                    "\t\"Confirm\"\tINTEGER NOT NULL,\n" +
                    "\t\"Sync\"\tINTEGER NOT NULL,\n" +
                    "\t\"SyncDateTime\"\tINTEGER,\n" +
                    "\t\"StaffId\"\tTEXT,\n" +
                    "\t\"SyncResponse\"\tTEXT,\n" +
                    "\t\"refId\"\tTEXT\n" +
                    ")");

            database.execSQL("CREATE TABLE IF NOT EXISTS \"OverDueCMTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"CenterId\"\tTEXT,\n" +
                    "\t\"CenterName\"\tTEXT,\n" +
                    "\t\"GroupId\"\tTEXT,\n" +
                    "\t\"GroupName\"\tTEXT,\n" +
                    "\t\"CustomerId\"\tTEXT,\n" +
                    "\t\"CustomerName\"\tTEXT,\n" +
                    "\t\"CustomerCode\"\tTEXT,\n" +
                    "\t\"BranchCode\"\tTEXT,\n" +
                    "\t\"BranchName\"\tTEXT,\n" +
                    "\t\"LoanOfficerId\"\tTEXT,\n" +
                    "\t\"max_eligible_amount\"\tTEXT,\n" +
                    "\t\"product\"\tTEXT,\n" +
                    "\t\"ProductName\"\tTEXT,\n" +
                    "\t\"CenterMeetingDate\"\tTEXT,\n" +
                    "\t\"CenterMeetingTime\"\tTEXT,\n" +
                    "\t\"MemberRelationName\"\tTEXT,\n" +
                    "\t\"EMI_Amount\"\tINTEGER NOT NULL,\n" +
                    "\t\"Total_Due\"\tINTEGER NOT NULL,\n" +
                    "\t\"SavingsCollection\"\tINTEGER NOT NULL,\n" +
                    "\t\"LoanAccountNumber\"\tTEXT,\n" +
                    "\t\"RequiredInstallmentAmount\"\tINTEGER NOT NULL,\n" +
                    "\t\"Current_Installment\"\tINTEGER NOT NULL,\n" +
                    "\t\"Total_Installment\"\tINTEGER NOT NULL,\n" +
                    "\t\"CollectionType\"\tTEXT\n" +
                    ")");

            database.execSQL("CREATE TABLE IF NOT EXISTS \"FTOverDueCMTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"CenterId\"\tTEXT,\n" +
                    "\t\"CenterName\"\tTEXT,\n" +
                    "\t\"GroupId\"\tTEXT,\n" +
                    "\t\"GroupName\"\tTEXT,\n" +
                    "\t\"CustomerId\"\tTEXT,\n" +
                    "\t\"CustomerName\"\tTEXT,\n" +
                    "\t\"CustomerCode\"\tTEXT,\n" +
                    "\t\"BranchCode\"\tTEXT,\n" +
                    "\t\"BranchName\"\tTEXT,\n" +
                    "\t\"LoanOfficerId\"\tTEXT,\n" +
                    "\t\"max_eligible_amount\"\tTEXT,\n" +
                    "\t\"product\"\tTEXT,\n" +
                    "\t\"ProductName\"\tTEXT,\n" +
                    "\t\"CenterMeetingDate\"\tTEXT,\n" +
                    "\t\"CenterMeetingTime\"\tTEXT,\n" +
                    "\t\"MemberRelationName\"\tTEXT,\n" +
                    "\t\"EMI_Amount\"\tINTEGER NOT NULL,\n" +
                    "\t\"Total_Due\"\tINTEGER NOT NULL,\n" +
                    "\t\"SavingsCollection\"\tINTEGER NOT NULL,\n" +
                    "\t\"LoanAccountNumber\"\tTEXT,\n" +
                    "\t\"RequiredInstallmentAmount\"\tINTEGER NOT NULL,\n" +
                    "\t\"Current_Installment\"\tINTEGER NOT NULL,\n" +
                    "\t\"Total_Installment\"\tINTEGER NOT NULL,\n" +
                    "\t\"CollectionType\"\tTEXT\n" +
                    ")");

            database.execSQL("CREATE TABLE IF NOT EXISTS \"CMFetchTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"staffId\"\tTEXT,\n" +
                    "\t\"created_date\"\tTEXT,\n" +
                    "\t\"sync\"\tINTEGER NOT NULL\n" +
                    ")");

            database.execSQL("CREATE TABLE IF NOT EXISTS \"CGTAttendanceTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"loanId\"\tTEXT,\n" +
                    "\t\"ClientId\"\tTEXT,\n" +
                    "\t\"ClientName\"\tTEXT,\n" +
                    "\t\"CenterId\"\tTEXT,\n" +
                    "\t\"CenterName\"\tTEXT,\n" +
                    "\t\"CGT1Attendance\"\tINTEGER NOT NULL,\n" +
                    "\t\"CGT2Attendance\"\tINTEGER NOT NULL,\n" +
                    "\t\"CGT1AbsentReason\"\tTEXT,\n" +
                    "\t\"CGT2AbsentReason\"\tTEXT,\n" +
                    "\t\"CreatedDate\"\tTEXT,\n" +
                    "\t\"Sync\"\tINTEGER NOT NULL,\n" +
                    "\t\"SyncDateTime\"\tTEXT,\n" +
                    "\t\"CreatedBy\"\tTEXT,\n" +
                    "\t\"SyncResponse\"\tTEXT\n" +
                    ")");

            database.execSQL("CREATE TABLE IF NOT EXISTS \"GRTAttendanceTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"loanId\"\tTEXT,\n" +
                    "\t\"ClientId\"\tTEXT,\n" +
                    "\t\"ClientName\"\tTEXT,\n" +
                    "\t\"CenterId\"\tTEXT,\n" +
                    "\t\"CenterName\"\tTEXT,\n" +
                    "\t\"Attendance\"\tINTEGER NOT NULL,\n" +
                    "\t\"AbsentReason\"\tTEXT,\n" +
                    "\t\"CreatedDate\"\tTEXT,\n" +
                    "\t\"Sync\"\tINTEGER NOT NULL,\n" +
                    "\t\"SyncDateTime\"\tTEXT,\n" +
                    "\t\"CreatedBy\"\tTEXT,\n" +
                    "\t\"SyncResponse\"\tTEXT\n" +
                    ")");

            database.execSQL("CREATE TABLE IF NOT EXISTS \"LoanProductCodeTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"loanProductCode\"\tTEXT,\n" +
                    "\t\"loanProductName\"\tTEXT\n" +
                    ")");


            // TODO: **************** v6.1.2 production migration [8-9] *********************
        }
    };

    static final Migration MIGRATION_9_10 = new Migration(9, 10) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {

            // TODO: **************** v10.1.2 production migration [9-10] *********************

            database.execSQL("ALTER TABLE ColdCallTable "
                    + "ADD COLUMN IsPremium INTEGER NOT NULL DEFAULT 0");

            database.execSQL("ALTER TABLE ColdCallTable "
                    + "ADD COLUMN CallTimeStamp TEXT");

            database.execSQL("ALTER TABLE ColdCallTable "
                    + "ADD COLUMN Comments TEXT");

            database.execSQL("ALTER TABLE SubmitDataTable "
                    + "ADD COLUMN EntityId TEXT");

            database.execSQL("ALTER TABLE ColdCallTable "
                    + "ADD COLUMN ProductId TEXT");

            database.execSQL("ALTER TABLE ColdCallTable "
                    + "ADD COLUMN AllDataCaptured INTEGER NOT NULL DEFAULT 0");

            // Remove the old table
            database.execSQL("DROP TABLE SalesToolTable");

            database.execSQL("CREATE TABLE IF NOT EXISTS \"SalesToolTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"clientId\"\tTEXT,\n" +
                    "\t\"clientName\"\tTEXT,\n" +
                    "\t\"ageRange\"\tTEXT,\n" +
                    "\t\"cityOfResidence\"\tTEXT,\n" +
                    "\t\"residentType\"\tTEXT,\n" +
                    "\t\"residentStability\"\tTEXT,\n" +
                    "\t\"firmName\"\tTEXT,\n" +
                    "\t\"firmType\"\tTEXT,\n" +
                    "\t\"businessType\"\tTEXT,\n" +
                    "\t\"businessPlace\"\tTEXT,\n" +
                    "\t\"yearsInBusiness\"\tTEXT,\n" +
                    "\t\"ownAPlot\"\tTEXT,\n" +
                    "\t\"ownership\"\tTEXT,\n" +
                    "\t\"incomeProofAvailable\"\tTEXT,\n" +
                    "\t\"itrNoOfYears\"\tTEXT,\n" +
                    "\t\"monthlyBusinessTurnOverrange\"\tTEXT,\n" +
                    "\t\"anyExistingLoanRunning\"\tTEXT,\n" +
                    "\t\"typeOfLoan\"\tTEXT,\n" +
                    "\t\"lookingFor\"\tTEXT,\n" +
                    "\t\"typeOfSecurity\"\tTEXT,\n" +
                    "\t\"securityOwnership\"\tTEXT,\n" +
                    "\t\"requestedAmount\"\tTEXT,\n" +
                    "\t\"comfortableEmi\"\tTEXT,\n" +
                    "\t\"tenure\"\tTEXT,\n" +
                    "\t\"totalSales\"\tTEXT,\n" +
                    "\t\"totalPurchase\"\tTEXT,\n" +
                    "\t\"rentOfBusinessPremise\"\tTEXT,\n" +
                    "\t\"labour_ElectricityCost\"\tTEXT,\n" +
                    "\t\"totalExpense\"\tTEXT,\n" +
                    "\t\"netBusinessIncome\"\tTEXT,\n" +
                    "\t\"incomeFromOthersource\"\tTEXT,\n" +
                    "\t\"monthlyHouseholdExpenses\"\tTEXT,\n" +
                    "\t\"monthlyEMI\"\tTEXT,\n" +
                    "\t\"totalMonthlySurplus\"\tTEXT,\n" +
                    "\t\"debtServiceRatio\"\tTEXT,\n" +
                    "\t\"emi_NBS\"\tTEXT,\n" +
                    "\t\"finalEMI_Eligibility\"\tTEXT,\n" +
                    "\t\"applicationAmount\"\tTEXT,\n" +
                    "\t\"amountbasis_FinalEmiEligibility\"\tTEXT,\n" +
                    "\t\"finalAmount\"\tTEXT,\n" +
                    "\t\"MobileNo\"\tTEXT,\n" +
                    "\t\"loan_type\"\tTEXT,\n" +
                    "\t\"sync\"\tINTEGER NOT NULL,\n" +
                    "\t\"createdBy\"\tTEXT,\n" +
                    "\t\"branchId\"\tTEXT,\n" +
                    "\t\"branchGSTCode\"\tTEXT,\n" +
                    "\t\"score\"\tTEXT,\n" +
                    "\t\"created_date\"\tINTEGER,\n" +
                    "\t\"isDataCaptured\"\tINTEGER NOT NULL\n" +
                    ")");

            // Remove the old table
            database.execSQL("DROP TABLE LeadTable");
            database.execSQL("CREATE TABLE IF NOT EXISTS \"LeadTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, \n" +
                    "\t\"clientId\"\tTEXT,\n" +
                    "\t\"clientName\"\tTEXT,\n" +
                    "\t\"MarketName\"\tTEXT,\n" +
                    "\t\"BusinessName\"\tTEXT,\n" +
                    "\t\"MobileNo\"\tTEXT,\n" +
                    "\t\"Lat\"\tTEXT,\n" +
                    "\t\"Long\"\tTEXT,\n" +
                    "\t\"loan_type\"\tTEXT,\n" +
                    "\t\"Remarks\"\tTEXT,\n" +
                    "\t\"InterestedInLoan\"\tINTEGER NOT NULL,\n" +
                    "\t\"leadStatus\"\tTEXT,\n" +
                    "\t\"NextFollowUpDate\"\tTEXT,\n" +
                    "\t\"Pincode\"\tTEXT,\n" +
                    "\t\"sync\"\tINTEGER NOT NULL,\n" +
                    "\t\"createdBy\"\tTEXT,\n" +
                    "\t\"created_date\"\tTEXT,\n" +
                    "\t\"response\"\tTEXT,\n" +
                    "\t\"IsDataCaptured\"\tINTEGER NOT NULL,\n" +
                    "\t\"IsPremium\"\tINTEGER NOT NULL\n" +
                    ");");

/*
            database.execSQL("CREATE TABLE IF NOT EXISTS \"LeadTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"CCTokenId\"\tTEXT,\n" +
                    "\t\"TargetName\"\tTEXT,\n" +
                    "\t\"MarketName\"\tTEXT,\n" +
                    "\t\"BusinessName\"\tTEXT,\n" +
                    "\t\"MobileNo\"\tTEXT,\n" +
                    "\t\"Lat\"\tTEXT,\n" +
                    "\t\"Long\"\tTEXT,\n" +
                    "\t\"loan_type\"\tTEXT,\n" +
                    "\t\"Remarks\"\tTEXT,\n" +
                    "\t\"InterestedInLoan\"\tINTEGER NOT NULL,\n" +
                    "\t\"lead_status\"\tTEXT,\n" +
                    "\t\"NextFollowUpDate\"\tTEXT,\n" +
                    "\t\"Pincode\"\tTEXT,\n" +
                    "\t\"sync\"\tINTEGER NOT NULL,\n" +
                    "\t\"createdBy\"\tTEXT,\n" +
                    "\t\"CreatedOn\"\tTEXT,\n" +
                    "\t\"response\"\tTEXT,\n" +
                    "\t\"IsDataCaptured\"\t INTEGER NOT NULL,\n" +
                    "\t\"IsPremium\"\tINTEGER NOT NULL\n" +
                    ")");
*/


            // TODO: **************** v10.1.2 production migration [9-10] *********************
        }
    };

    static final Migration MIGRATION_10_11 = new Migration(10, 11) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {



            database.execSQL("CREATE TABLE IF NOT EXISTS \"SubmitDataTable\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\"BCID\"\tTEXT,\n" +
                    "\t\"BCBRID\"\tTEXT,\n" +
                    "\t\"ScreenId\"\tTEXT,\n" +
                    "\t\"ScreenName\"\tTEXT,\n" +
                    "\t\"moduleType\"\tTEXT,\n" +
                    "\t\"ProductId\"\tTEXT,\n" +
                    "\t\"ModuleId\"\tTEXT,\n" +
                    "\t\"CreatedBy\"\tTEXT,\n" +
                    "\t\"RawData\"\tTEXT,\n" +
                    "\t\"ActionName\"\tTEXT,\n" +
                    "\t\"FieldName\"\tTEXT,\n" +
                    "\t\"value\"\tTEXT,\n" +
                    "\t\"timestamp\"\tTEXT,\n" +
                    "\t\"request\"\tTEXT,\n" +
                    "\t\"response\"\tTEXT,\n" +
                    "\t\"UniqueID\"\tTEXT,\n" +
                    "\t\"ApplicationId\"\tTEXT,\n" +
                    "\t\"EntityId\"\tTEXT,\n" +
                    "\t\"IMEINumber\"\tTEXT,\n" +
                    "\t\"CurrentStageId\"\tTEXT,\n" +
                    "\t\"remarks\"\tTEXT,\n" +
                    "\t\"RoleId\"\tTEXT,\n" +
                    "\t\"StageId\"\tTEXT,\n" +
                    "\t\"FieldId\"\tTEXT,\n" +
                    "\t\"WorkflowId\"\tTEXT,\n" +
                    "\t\"ProjectId\"\tTEXT\n" +
                    ")");



            database.execSQL("ALTER TABLE SubmitDataTable "
                    + "ADD COLUMN EntityId TEXT");
        }
    };

    // --- DATABASE INJECTION ---

    @Provides
    @Singleton
    DynamicUIDatabase provideDynamicUIDatabase(Application application) {
        String currentDBPath = "";

        SafeHelperFactory factory = SafeHelperFactory.fromUser(new SpannableStringBuilder("Rbl@123"));

        try {
            currentDBPath = App.createDBPath();
            if (TextUtils.isEmpty(currentDBPath)) {
                currentDBPath = App.context.getDatabasePath("DynamicUIDatabase.db").getAbsolutePath();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Room.databaseBuilder(application,
                DynamicUIDatabase.class, currentDBPath).addMigrations(MIGRATION_6_7,MIGRATION_7_8,MIGRATION_8_9,MIGRATION_9_10) // TODO: DB Migration
                //               DynamicUIDatabase.class,currentDBPath)  // TODO: Without Migration
                //  .fallbackToDestructiveMigration() // TODO: Database will be cleared
              //  .openHelperFactory(factory) // TODO: Set this encryption only for production release
                .build();

    }


    @Provides
    @Singleton
    DynamicUIDao provideDynamicUIDao(DynamicUIDatabase database) {
        return database.dynamicUIDao();
    }

    // --- REPOSITORY INJECTION ---

    @Provides
    Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Provides
    AppHelper provideAppHelper() {
        return new AppHelper(App.context);
    }

    @Provides
    @Singleton
    DynamicUIRepository provideDynamicUIRepository(DynamicUIWebservice webservice, DynamicUIDao userDao, Executor executor, AppHelper appHelper) {
        return new DynamicUIRepository(webservice, userDao, executor, appHelper);
    }
    // --- NETWORK INJECTION ---

//    private static String BASE_URL = "http://fileupload0330.azurewebsites.net/api/";

    /*public static void changeBaseURL(String newURL){
        BASE_URL=newURL;
    }*/

    private static OkHttpClient httpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();

    @Provides
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    Retrofit provideRetrofit(Gson gson) {
        // TODO: OLD
       /* Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .build(); */
        // TODO: NEW
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .client(httpClient)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    DynamicUIWebservice provideApiWebservice(Retrofit restAdapter) {
        return restAdapter.create(DynamicUIWebservice.class);
    }
}
