package com.saartak.el.constants;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.text.TextUtils;
import android.util.Base64;

import androidx.fragment.app.Fragment;

import com.bfil.uilibrary.helpers.AppHelper;
import com.saartak.el.App;
import com.saartak.el.R;
import com.saartak.el.database.entity.DynamicUITable;
import com.saartak.el.database.entity.RawDataTable;
import com.saartak.el.fragments.AddressDetailsFragment;
import com.saartak.el.fragments.ApplicantKYCFragment;
import com.saartak.el.fragments.ApplicantPANDetailsFragment;
import com.saartak.el.fragments.ApplicantRegulatoryFieldsFragment;
import com.saartak.el.fragments.ApplicantSignatureFragmant;
import com.saartak.el.fragments.BankDetailsFragment;
import com.saartak.el.fragments.BusinessAddressProofFragment;
import com.saartak.el.fragments.BusinessEntityFragment;
import com.saartak.el.fragments.BusinessProofFragment;
import com.saartak.el.fragments.CPVFragment;
import com.saartak.el.fragments.CoApplicantAddressDetailFragment;
import com.saartak.el.fragments.CoApplicantBankDetailsFragment;
import com.saartak.el.fragments.CoApplicantKYCFragment;
import com.saartak.el.fragments.CoApplicantNomineeDetailFragment;
import com.saartak.el.fragments.CoApplicantPANDetailsFragment;
import com.saartak.el.fragments.CoApplicantPersonalDetailFragment;
import com.saartak.el.fragments.CoApplicantRegulatoryFieldsFragment;
import com.saartak.el.fragments.CoApplicantSocioEconomicDetail;
import com.saartak.el.fragments.ColdCallingFragment;
import com.saartak.el.fragments.CollectionFragment;
import com.saartak.el.fragments.CourseDetailsFragment;
import com.saartak.el.fragments.DEDUPEFragment;
import com.saartak.el.fragments.DNCRApplicantFragment;
import com.saartak.el.fragments.DeliquencyFragment;
import com.saartak.el.fragments.DocumentUploadFragmentNew;
import com.saartak.el.fragments.ENachFragment;
import com.saartak.el.fragments.EsignEstampFragment;
import com.saartak.el.fragments.GenerateCIBILFragment;
import com.saartak.el.fragments.GuarantorDetailsFragment;
import com.saartak.el.fragments.HunterFragment;
import com.saartak.el.fragments.LeadFragment;
import com.saartak.el.fragments.LeadSummaryFragment;
import com.saartak.el.fragments.NomineeDetailFragment;
import com.saartak.el.fragments.OTPAuthenticationFragment;
import com.saartak.el.fragments.OTPVerificationFragment;
import com.saartak.el.fragments.OfficeAddressProofFragment;
import com.saartak.el.fragments.PersonalDetailFragment;
import com.saartak.el.fragments.PosidexFragment;
import com.saartak.el.fragments.RATFragment;
import com.saartak.el.fragments.RampFragment;
import com.saartak.el.fragments.ReferenceCheckFragment;
import com.saartak.el.fragments.SalaryEntityFragment;
import com.saartak.el.fragments.SalesToolFragment;
import com.saartak.el.fragments.SampleFragment;
import com.saartak.el.fragments.SocioEconomicDetailFragment;
import com.saartak.el.fragments.StudentDetailsFragment;
import com.saartak.el.fragments.TwoWheelerFragment;
import com.saartak.el.fragments.UsedCarDetailFragment;
import com.saartak.el.fragments.balancesheet.AdvancesFragment;
import com.saartak.el.fragments.balancesheet.BusinessAssetsFragment;
import com.saartak.el.fragments.balancesheet.BusinessDebtsFragment;
import com.saartak.el.fragments.balancesheet.BusinessLiabilitiesFragment;
import com.saartak.el.fragments.balancesheet.HouseAssetsFragment;
import com.saartak.el.fragments.balancesheet.HouseLiabilitiesFragment;
import com.saartak.el.fragments.balancesheet.ProductFragment;
import com.saartak.el.fragments.balancesheet.msme.BusinessAssertsMsmeFragment;
import com.saartak.el.fragments.balancesheet.msme.BusinessLiabilitiesMsmeFragment;
import com.saartak.el.fragments.balancesheet.msme.HouseAssetsMsmeFragment;
import com.saartak.el.fragments.balancesheet.msme.HouseLiabilitiesMsmeFragment;
import com.saartak.el.fragments.decisions.msme.CashFlowSummaryMSMEFragment;
import com.saartak.el.fragments.decisions.msme.LoanApprovalMSMEFragment;
import com.saartak.el.fragments.decisions.ObservationsFragment;
import com.saartak.el.fragments.decisions.msme.LoanInformationMsmeFragment;
import com.saartak.el.fragments.incomeassessment.BusinessIncomeFragment;
import com.saartak.el.fragments.incomeassessment.DeclaredSalesWeeklyFragment;
import com.saartak.el.fragments.incomeassessment.DeclaredSalesYearlyFragment;
import com.saartak.el.fragments.incomeassessment.EmployerVerificationFragment;
import com.saartak.el.fragments.incomeassessment.FamilyMemberFragment;
import com.saartak.el.fragments.incomeassessment.HouseExpensesFragment;
import com.saartak.el.fragments.incomeassessment.HouseIncomeFragment;
import com.saartak.el.fragments.incomeassessment.OperatingExpenseFragment;
import com.saartak.el.fragments.incomeassessment.ProductEstimateFragment;
import com.saartak.el.fragments.incomeassessment.PurchaseAnalysisByProductFragment;
import com.saartak.el.fragments.incomeassessment.PurchaseAnalysisFragment;
import com.saartak.el.fragments.incomeassessment.SummaryFragment;
import com.saartak.el.fragments.incomeassessment.msme.BankingHistoryFragment;
import com.saartak.el.fragments.incomeassessment.msme.DirectBusinessExpenseFragment;
import com.saartak.el.fragments.incomeassessment.msme.GSTFragment;
import com.saartak.el.fragments.incomeassessment.msme.GeneralIncomeFragment;
import com.saartak.el.fragments.incomeassessment.msme.HouseIncomeSummaryFragment;
import com.saartak.el.fragments.incomeassessment.msme.ITRDetailFragment;
import com.saartak.el.fragments.incomeassessment.msme.ITRFragment;
import com.saartak.el.fragments.incomeassessment.msme.IncomeAssessmentSummaryMsmeFragment;
import com.saartak.el.fragments.incomeassessment.msme.MonthlyTransactionDetailFragment;
import com.saartak.el.fragments.incomeassessment.msme.OtherIncomeSourceFragment;
import com.saartak.el.fragments.incomeassessment.msme.ProductEstimateDetailFragment;
import com.saartak.el.fragments.incomeassessment.msme.ProductEstimateMSMEFragment;
import com.saartak.el.fragments.incomeassessment.msme.ProductRawMaterialFragment;
import com.saartak.el.fragments.incomeassessment.msme.PurchaseBillsDetailFragment;
import com.saartak.el.fragments.incomeassessment.msme.PurchaseBillsFragment;
import com.saartak.el.fragments.incomeassessment.msme.SalesBillsDetailFragment;
import com.saartak.el.fragments.incomeassessment.msme.SalesBillsFragment;
import com.saartak.el.fragments.incomeassessment.msme.ServiceEstimateDetailFragment;
import com.saartak.el.fragments.incomeassessment.msme.ServiceEstimateFragment;
import com.saartak.el.fragments.incomeassessment.msme.ServiceRawMaterialFragment;
import com.saartak.el.fragments.loanproposal.ApplicantLoanProposalFragment;
import com.saartak.el.fragments.loanproposal.FinancialRatiosFragment;
import com.saartak.el.fragments.loanproposal.LoanProposalFragment;
import com.saartak.el.fragments.loanproposal.LoanProposalSummaryFragment;
import com.saartak.el.fragments.loanproposal.SurPlusFragment;
import com.saartak.el.fragments.references.ReferencesFragment;
import com.saartak.el.models.ScreenDetailsDTO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.saartak.el.dynamicui.constants.ParametersConstant.PROOF_TYPE_SPINNER_ITEM_ADDRESS_PROOF;
import static com.saartak.el.dynamicui.constants.ParametersConstant.PROOF_TYPE_SPINNER_ITEM_ID_CUM_ADDRESS_PROOF;
import static com.saartak.el.dynamicui.constants.ParametersConstant.PROOF_TYPE_SPINNER_ITEM_ID_CUM_SIGNATURE_PROOF;
import static com.saartak.el.dynamicui.constants.ParametersConstant.PROOF_TYPE_SPINNER_ITEM_SIGNATURE_PROOF;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SPINNER_ITEM_FIELD_NAME_AADHAAR;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SPINNER_ITEM_FIELD_NAME_AUTHORIZED_SIGNATURE_PROOF;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SPINNER_ITEM_FIELD_NAME_BANK_PASSBOOK_WITH_PHOTOGRAPH;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SPINNER_ITEM_FIELD_NAME_BANK_STATEMENT;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SPINNER_ITEM_FIELD_NAME_DEEMED_OVD;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SPINNER_ITEM_FIELD_NAME_DRIVING_LICENSE;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SPINNER_ITEM_FIELD_NAME_NREGA_JOB_CARD;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SPINNER_ITEM_FIELD_NAME_PAN_CARD;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SPINNER_ITEM_FIELD_NAME_PASSPORT;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SPINNER_ITEM_FIELD_NAME_RATION_CARD;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SPINNER_ITEM_FIELD_NAME_UIDAI_ELIGIBLE_DOCUMENTS;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SPINNER_ITEM_FIELD_NAME_UTILITY_BILLS;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SPINNER_ITEM_FIELD_NAME_VID;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SPINNER_ITEM_FIELD_NAME_VOTER_ID;

public class AppConstant {

    // TODO: LATEST UAT URLS FOR TESTING 2 December
    public static String env = "PP"; // TODO: UAT ENVIRONMENT
    public static String APP_ENVIRONMENT = "UAT"; // TODO: UAT APP ENVIRONMENT
    public static String RESET_PASSWORD_TOKEN = "Bearer GqQa12BcEWqPP6x7F0UqTzCzTdias6K8mESlhNmFPH7v6EqFnfXXk8mkP1VZ+Dyetb8l952pne6yHl5XCcrpPFRG4M1K8U3mFQECWctEyx2X0deT4A1Xw9x/QL+64TJu+nenKBVy1dNa27irWPTiQ6FE3DhP5pFxQhVI92xBEIaVmwHHaGGNZ7hHw22vI950V7+a8mPgprIQl1ln4lk3k8jfUKeyA05AHWbo2h92vaWhij3ulutCxXpgG+o2m8jQuyEkk1AU7+90e6MCmF+bw6pRhbQLsFtnGzFVBtzfbfnLYcIyMx6pq2J4lMyHHWq4F6Uh5vtNBE9ms4xQmpH2GQ=="; // TODO: HARDCODED TOKEN FOR RESET PASSWORD
    public static String EKYC_TOKEN = " Bearer EBy1rxqFbAwXLWUvSdoEb+GrDn2XiLuOLsSCDWJQ2n7r2S+/fp98pIoK1ZlUZecF4lPWB12BhXtQNkQcQAzcMPLWnaG6yQjx65xbVtaNszzcLtP9/9mkH1SUBvkXMHXX/zQQV9tswzto01fGcL99ACnCqO/YmHmopracZmuNM1rYuaMZ+eHbGdz5ZqfJ0lCHMtH45uZRIaCkzXL04ErF3uG7iVRbISsIgsYaZQEphEBjQ6B5xfcTN5GcMgei21W9Lq0Yfmhw2kCpHFY8F+h/fyQ86Ev23kcGkfjvHzXN3zYXt70Lke0r+gm3o7RMC4biy38jlMQV0QsXAV/BKZdIOg=="; // TODO: HARDCODED TOKEN FOR RESET PASSWORD
    public static String LOGIN_URL = "https://saartakuat.rblbank.com/balApi/"; // TODO: LOGIN URL
    public static String LDAP_LOGIN_URL = "https://losservicesuat.rblbank.com/LosLdap/api/v1/uat/RVF/"; // TODO:LDAP LOGIN URL
    public static String LOGOUT_URL = "https://saartakuat.rblbank.com/balApi/"; // TODO: LOGOUT URL
    public static String BEARER_TOKEN_URL = "https://losservicesuat.rblbank.com/identityservice/api/v1/uat/"; // TODO: BEARER TOKEN URL
    //public static String BEARER_TOKEN_URL = "https://losservices.rblbank.com/identityservice/api/v1/prod/"; // TODO: BEARER TOKEN URL
    public static String STAGE_URL = "https://losservicesuat.rblbank.com/Tranuat/api/"; // TODO: STAGE URL
    public static String WORKFLOW_URL = "https://losservicesuat.rblbank.com/Tranuat/api/"; // TODO: WORKFLOW URL
    public static String BASE_URL = "https://losservicesuat.rblbank.com/metaservice/api/"; // TODO: META DATA URL
    public static String SUBMIT_DATA_POST_URL = "https://losservicesuat.rblbank.com/Tranuat/api/"; // TODO: SYNC & WORKFLOW
    public static String SUBMIT_DATA_POST_URL_LEAD = "https://losservicesuat.rblbank.com/LossrvDatastore/api/"; // TODO: ONLY SYNC
    public static String SUBMIT_DATA_POST_URL_JLG = "https://losservicesuat.rblbank.com/JLGoperations/api/"; // TODO: JLG DATA SYNC [ dummy url , not received tvName service team ]
    public static String RAW_DATA_URL = "https://losservicesuat.rblbank.com/DBUtil/api/v1/uat/"; // TODO: RAW DATA URL
    //https://losservicesuat.rblbank.com/Pincode/api/WorkflowUser/v1/
    public static String PIN_CODE_URL = "http://www.postalpincode.in/api/"; // TODO: PIN CODE URL
     public static String EKYC_URL= "https://losservicesuat.rblbank.com/ekyc/api/v1/uat/"; // TODO: EKYC URL
    //public static String EKYC_URL= "https://losservicesuat.rblbank.com/EkycLOS/api/"; // TODO: EKYC URL
    public static String OTP_TRIGGER_URL = "https://losservicesuat.rblbank.com/otp/api/v1/uat/"; // TODO: OTP TRIGGER
    public static String OTP_VERIFY_URL = "https://losservicesuat.rblbank.com/otp/api/v1/uat/"; // TODO: OTP VERIFY
        public static String DOCUMENT_UPLOAD_URL = "https://losservicesuat.rblbank.com/Docupload/api/uat/V1/uploadImages"; // TODO: DOCUMENT UPLOAD URL UAT EL
    //public static String DOCUMENT_UPLOAD_URL = "https://losservicesuat.rblbank.com/Docupload1/api/uat/V1/uploadImages1"; // TODO: DOCUMENT UPLOAD URL
//    public static String DOCUMENT_UPLOAD_URL = "https://losservices.rblbank.com/LosDocupload1/api/uat/V1/uploadImages1"; // TODO: DOCUMENT UPLOAD URL
    public static String DOCUMENT_DOWNLOAD_URL = "https://losservicesuat.rblbank.com/DownloadImg/api/uat/"; // TODO: DOCUMENT DOWNLOAD URL
    public static String LOG_TO_SERVER_URL = "https://losservicesuat.rblbank.com/LogsService/api/v1/prod/"; // TODO: LOG TO SERVER URL
    public static String PAN_VALIDATION_URL = "https://losservicesuat.rblbank.com/bankproxy/api/v1/uat/"; // TODO: PAN VALIDATION URL
    public static String AADHAAR_VAULT_URL = "https://losservicesuat.rblbank.com/bankproxy/api/v1/uat/"; // TODO: AADHAAR VAULT URL
    //public static String AADHAAR_VAULT_URL = "https://besure.rblbank.com/ServiceGateway/api/v1/prod/"; // TODO: AADHAAR VAULT URL
    public static String CHANGE_PASSWORD_URL = "https://Saartakuat.rblbank.com/balApi/"; // TODO: CHANGE PASSWORD
    public static String RESET_PASSWORD_URL = "https://losservicesuat.rblbank.com/managecredentials/api/v1/uat/"; // TODO: RESET PASSWORD
    public static String CIBIL_URL = "https://losservicesuat.rblbank.com/bankproxy/api/v1/uat/"; // TODO: CIBIL
    public static String GET_ROLE_NAMES_URL = "https://saartakuat.rblbank.com/balApi/"; // TODO: GET ROLE NAMES
    public static String CB_MFI_URL = " https://losservicesuat.rblbank.com/bankproxy/api/v1/prod/"; // TODO: CB MFI
    public static String SALES_TOOL_URL = "https://losservicesuat.rblbank.com/SalesTool/api/v1/uat/"; // TODO: SALES TOOL
    public static String INITIATE_TRANSACTION_URL = " https://losservicesuat.rblbank.com/collections/api/v1/prod/"; // TODO: TWINLINE FOR DIGITAL PAYMENT
    public static String POSIDEX_DILIQUENCY = "https://losservicesuat.rblbank.com/PosidexService/api/";
    public static String RAMP_HUNTER = "https://losservicesuat.rblbank.com/LosBankService/api/v1/uat/";
    public static String SENTINEL_SERVICE = "https://losservicesuat.rblbank.com/SentinelService/api/v1/uat/";
    public static String ESIGN_ESTAMP = "https://losservicesuat.rblbank.com/EsignService/api/";
    public static String ESIGN_ESTAMP_INITIATION = "https://losservicesuat.rblbank.com/EsignService/api/v1/uat/";
    public static String PRE_SUBMIT_DATA = "https://losservicesuat.rblbank.com/UatLosNew/api/";

    // TODO: LATEST PRODUCTION URLS FOR TESTING
//      public static String env="P"; // TODO: PRODUCTION ENVIRONMENT
//      public static String APP_ENVIRONMENT="PROD"; // TODO: PRODUCTION APP ENVIRONMENT
//      public static String RESET_PASSWORD_TOKEN="Bearer Kwd8+kP9GFeupOwUOIFhkpnUPUekWFRs23ixOhAwLLjHcx+F2l/4gTEn6Cu9262Vhma8+1dSyeQ6O9GS+5xcbiahSBa0vJDt0eAF+crAiPOsp8cpXMWagTaalzwnt6Mh0ifJfSzidDwTrXCK5NI0iJRXbngxdC8SsBvlEr9ogdc5glG6WEHEBpbwzx/ec6nxAVa4G4l3Ujqxq//4/hZjMfJEbPXSqL+KV2ZviCRW+h9I7ByB3TH0az+QNyaz9/yCVcU/KkJbJffJw2zbG03zRsPvxuEMs8h6YQychZRijzi5VG jNEG17S5bf8NwRqFbDBzB6Q3GS6Rr5xn5CSnVQ=="; // TODO: HARDCODED TOKEN FOR RESET PASSWORD
//      public static String RESET_GET_IDENTITY_TOKEN="Bearer EPBeiZeGzCVDuRD/7qvjJmOZw/AV1TcPtmgDNvqmBjWJE7V1HLTOlLAnS01zcNaZeZslkY8FKxm9I3nGGx4Hx/qJ+w6HKydSJJIJnooSEBKDOzIaccfeGWeQvEzZKkdGcySWLIt+kcQNfjhpfyEcLEs5BLmldC5r3M6QLqumrnwEPZpUFyHSjV9YfFUuJuFvL+g0345Ww+CbetK5fHEZ2Akzvg6MzScs24pF08uMrZIAE8nh+OrEp2LMKlZX1NA14s5HjHQNH3A9mjMfsO7zK2H+KSWwMTEBOS73tpr8iAsmmUbUKv9aDYo69hOfeEi/hx8SkGt5pqvXhuflKfh4jg=="; // TODO: HARDCODED TOKEN FOR RESET PASSWORD
//      public static String LOGIN_URL = " https://Saartak.rblbank.com/RFSONEBAL/balApi/"; // TODO: LOGIN URL
//      public static String LDAP_LOGIN_URL = "https://losservices.rblbank.com/LosLdap/api/v1/Prod/RVF/"; // TODO: LOGIN URL
//      public static String LOGOUT_URL = " https://Saartak.rblbank.com/RFSONEBAL/balApi/"; // TODO: LOGOUT URL
//      public static String BEARER_TOKEN_URL = "https://losservices.rblbank.com/identityservice/api/v1/prod/"; // TODO: BEARER TOKEN URL
//      public static String STAGE_URL = "https://losservices.rblbank.com/LosTransProd/api/"; // TODO: STAGE URL
//      public static String WORKFLOW_URL ="https://losservices.rblbank.com/LosTransProd/api/"; // TODO: WORKFLOW URL
//      public static String BASE_URL = "https://losservices.rblbank.com/LosMetaService/api/"; // TODO: META DATA URL
//      public static String SUBMIT_DATA_POST_URL = "https://losservices.rblbank.com/LosTransProd/api/"; // TODO: SYNC & WORKFLOW
//      public static String SUBMIT_DATA_POST_URL_LEAD = "https://losservices.rblbank.com/LossrvDatastoreProd/api/"; // TODO: ONLY SYNC
//      public static String SUBMIT_DATA_POST_URL_JLG = "https://losservices.rblbank.com/JLGoperations/api/"; // TODO: JLG DATA SYNC [ dummy url , not received tvName service team ]
//      public static String RAW_DATA_URL = "https://losservices.rblbank.com/Losdbutil/api/v1/prod/"; // TODO: RAW DATA URL
//      public static String PIN_CODE_URL = "http://www.postalpincode.in/api/"; // TODO: PIN CODE URL
//      public static String EKYC_URL = "https://losservices.rblbank.com/ekyc/api/v1/Prod/"; // TODO: EKYC URL
//      public static String OTP_TRIGGER_URL = "https://besure.rblbank.com/otp/api/v1/prod/"; // TODO: OTP TRIGGER
//      public static String OTP_VERIFY_URL = "https://besure.rblbank.com/otp/api/v1/prod/"; // TODO: OTP VERIFY
//  //    public static String DOCUMENT_UPLOAD_URL = "https://losservices.rblbank.com/LosDocuploadIL/api/uat/V1/uploadImages"; // TODO: DOCUMENT UPLOAD URL
//      public static String DOCUMENT_UPLOAD_URL = "https://losservices.rblbank.com/LosDocupload1/api/uat/V1/uploadImages1"; // TODO: DOCUMENT UPLOAD URL
//      public static String DOCUMENT_DOWNLOAD_URL = "https://losservices.rblbank.com/DownloadImg/api/prod/"; // TODO: DOCUMENT DOWNLOAD URL
//      public static String LOG_TO_SERVER_URL = "https://losservices.rblbank.com/LogsService/api/v1/prod/"; // TODO: LOG TO SERVER URL
//      public static String PAN_VALIDATION_URL = "https://besure.rblbank.com/ServiceGateway/api/v1/prod/"; // TODO: PAN VALIDATION URL
//      public static String AADHAAR_VAULT_URL = "https://losservices.rblbank.com/LosServiceGateway/api/v1/prod/"; // TODO: AADHAAR VAULT URL
//      public static String CHANGE_PASSWORD_URL = "https://Saartak.rblbank.com/RFSONEBAL/balApi/"; // TODO: CHANGE PASSWORD
//      public static String RESET_PASSWORD_URL = "https://losservices.rblbank.com/managecredentials/api/v1/prod/"; // TODO: RESET PASSWORD
//      public static String CIBIL_URL = "https://losservices.rblbank.com/LosServiceGateway/api/v1/prod/"; // TODO: CIBIL
//      public static String GET_ROLE_NAMES_URL = "https://Saartak.rblbank.com/RFSONEBAL/balApi/"; // TODO: GET ROLE NAMES
//      public static String CB_MFI_URL = " https://losservices.rblbank.com/bankproxy/api/v1/prod/"; // TODO: CB MFI
//      public static String SALES_TOOL_URL = " https://losservices.rblbank.com/SalesTool/api/v1/prod/"; // TODO: SALES TOOL
//      public static String INITIATE_TRANSACTION_URL = " https://losservices.rblbank.com/collections/api/v1/prod/"; // TODO: TWINLINE FOR DIGITAL PAYMENT
//
//      public static String POSIDEX_DILIQUENCY ="https://losservices.rblbank.com/LosServiceGateway/api/";
//      public static String RAMP_HUNTER ="https://losservices.rblbank.com/LosServiceGateway/api/v1/prod/";
//      public static String SENTINEL_SERVICE ="https://losservices.rblbank.com/LosServiceGateway/api/v1/prod/";
//      public static String ESIGN_ESTAMP ="https://losservices.rblbank.com/EsignService/api/";
//      public static String ESIGN_ESTAMP_INITIATION ="https://losservices.rblbank.com/EsignService/api/v1/prod/";
//      public static String PRE_SUBMIT_DATA ="https://losservices.rblbank.com/LosServiceGateway/api/";


  // TODO: APP FOLDER INFORMATION
    public static final String APP_FOLDER = "EL";
    public static final String DB_FOLDER = "EL_DB";
    public static final String DB_SUB_FOLDER = APP_ENVIRONMENT;
    public static final String DB_NAME = "DynamicUIDatabase.db";
    public static final String IMAGE_UPLOAD_FOLDER_NAME = "ImageUpload";

    // TODO: BIOMETRIC INFORMATION
    public final static int INFO_REQUEST = 0;
    public final static int CAPTURE_REQUEST = 1;
    public final static String DEVICE_INFO_KEY = "DEVICE_INFO";
    public final static String RD_SERVICE_INFO = "RD_SERVICE_INFO";
    public final static String PID_DATA = "PID_DATA";
    public final static String PID_OPTIONS = "PID_OPTIONS";

    public static String AquirerID = "210039";//production
    public static int envType=0;  // production

    public static String QUERY_PARAM_CLIENT_ID = "dc3012af-2b1c-4cc3-ae50-85d5bd5a7855";
    public static String QUERY_PARAM_CLIENT_SECRET = "aA5pU1cU2kO0tM1gS1vU5nK0tQ2bI5pO5mV2lD6aQ7hQ5hJ1mX";

    // concatenate username and password with colon for authentication
    public static String AUTHENTICATION_USERNAME = "SANDEEPPAN";
    public static String AUTHENTICATION_PASSWORD="PL<qaz@123";
    public static String CREDENTIALS = AUTHENTICATION_USERNAME + ":" + AUTHENTICATION_PASSWORD;


    public static String IMAGE_ENC_PSWD = "longestPasswordEverCreatedInAllTheUniverseOrMore";
    public static String IMAGE_ENC_SALT = "FFD7BADF2FBB1999";

    public static  final String EKYC = "EKYC";
    public static  final String DOCUMENT_FILE = "DOCUMENT_FILE";


    // TODO: OTP SMS FORMAT
    public static final String OTP_SMS_FORMAT = "As per your request we are processing loan application. Enter OTP XXXXXX as consent on Loan application, Credit Bureau Check & Submission of scanned original docs";
    // TODO: COLLECTION OTP SMS FORMAT
    public static final String COLLECTION_SMS_FORMAT = "XXXXXX, we have received Rs YYYYYY against the installment amount due for ZZZZZZ";
    // TODO: DAILY COLLECTION OTP SMS FORMAT
    public static final String DAILY_COLLECTION_SMS_FORMAT = "Dear XXXXXX, we have received collection of YYYYYY for your center ZZZZZZ. Thanks for the payment.";

    public static String MESSAGE_ROOTED_DEVICE_NOT_SUPPORTED = "App does not support rooted device due to security reasons";

    public static final String PARAM_USER_NAME = "USER NAME";
    public static final String PARAM_USER_ID = "USER ID";
    public static final String PARAM_EMPLOYEE_ID = "EMPLOYEE ID";
    public static final String PARAM_BRANCH_ID = "BRANCH ID";
    public static final String PARAM_BRANCH_NAME = "BRANCH NAME";
    public static final String PARAM_BRANCH_GST_CODE = "BRANCH GST CODE";
    public static final String PARAM_CLIENT_ID = "CLIENT ID";
    public static final String PARAM_CURRENT_STAGE = "CURRENT STAGE";
    public static final String PARAM_CURRENT_STAGE_ID = "CURRENT STAGE ID";
    public static final String PARAM_HASH_MAP = "HASH MAP";
    public static final String PARAM_AUTO_FILL = "AUTO FILL";
    public static final String PARAM_DYNAMIC_UI_LIST = "DYNAMIC UI LIST";
    public static final String PARAM_DYNAMIC_UI = "DYNAMIC UI";
    public static final String PARAM_DOCUMENT_UPLOAD_JSON = "DOCUMENT UPLOAD JSON";
    public static final String PARAM_OLD_PASSWORD = "OLD PASSWORD";
    public static final String PARAM_GET_APPLICATION_ID = "GET APPLICATION ID";
    public static final String PARAM_CALLING_FROM_CONNECT_APP = "CALLING FROM CONNECT APP";
    public static final String PARAM_REQUEST_FROM_SAARTHI_APP = "REQUEST_FROM_SAARTHI";

    public static final String PARAM_KNOWLEDGE_BANK_TABLE = "knowledgeBankTableParam";

    public static final String AUTHORIZATION_TOKEN_KEY = "AUTHORIZATION TOKEN";

    public static final String UCIC_ID = "UCICID";
    public static final String UCIC_ID_APPLICANT = "UCICID APPLICANT";

    public static final String UCIC_ID_CO_APPLICANT = "UCICID CO APPLICANT";
    public static final String AUTHORIZATION_PROD_TOKEN_KEY = "AUTHORIZATION PROD TOKEN";
    public static final String USER_API_KEY = "USER API KEY";

    public static final String PARAM_LOAN_TYPE = "LoanType";
    public static final String PARAM_PROJECT_ID = "ProjectId";
    //    public static final String PARAM_MODULE_ID = "ModuleId";
    public static final String PARAM_PRODUCT_ID = "ProductId";
    public static final String PARAM_WORKFLOW_ID = "WorkFlowId";
    public static final String PARAM_SCREEN_NO = "ScreenNo";
    public static final String PARAM_SCREEN_NAME = "ScreenName";
    public static final String PARAM_MODULE_TYPE = "ModuleType";
    public static final String PARAM_CORRELATION_ID = "CorrelationId";
    public static final String PARAM_CGT_TABLE_JSON = "CGTTableJson";
    public static final String PARAM_GRT_TABLE_JSON = "GRTTableJson";
    public static final String PARAM_CENTER_TABLE_JSON = "CenterTableJson";
    public static final String PARAM_GROUP_NAME = "GroupName";
    public static final String PARAM_CENTER_NAME = "CenterName";
    public static final String PARAM_ACTION_TO_DO = "ActionToDo";
    public static final String PARAM_ROLE_NAME = "ParamRoleName";

    public static final int REQUEST_CODE_NEW_LEAD = 301;
    public static final int REQUEST_CODE_UPDATE_LEAD = 302;
    public static final int REQUEST_CODE_UPDATE_SALES_TOOL = 303;

    // TODO: BCID
    public static final String BCID = "1031";
    public static final String BCID_PHL = "1031";

    // TODO: PROJECT NAME
    public static final String PROJECT_NAME_LOS = "EL";

    // TODO: PROJECT ID
    public static final String PROJECT_ID_EL = "1";

    // TODO: PRODUCT ID
    public static final String PRODUCT_ID_MSME = "1";
    public static final String PRODUCT_ID_IL = "3";
    public static final String PRODUCT_ID_TW = "4";
    public static final String PRODUCT_ID_JLG = "5";
    // TODO: ahl product id 12
    public static final String PRODUCT_ID_AHL = "12";
    // TODO: phl product id 22
    //public static final String PRODUCT_ID_PHL = "22";
    public static final String PRODUCT_ID_PHL = "25";
    public static final String PRODUCT_ID_EL = "26";
    public static final String PRODUCT_ID_TWL = "25";

    // TODO: WORKFLOW ID
    public static final String WORKFLOW_ID_JLG = "1";
    public static final String WORKFLOW_ID_IL = "3";
    public static final String WORKFLOW_ID_MSME = "4";
    public static final String WORKFLOW_ID_AHL = "12";
    // TODO: phl workflow id 16
    public static final String WORKFLOW_ID_PHL = "19";//WORKFLOW_ID_PHL="16";
    public static final String WORKFLOW_ID_EL = "20";//WORKFLOW_ID_EL="20";
    public static final String WORKFLOW_ID_TWL = "19";//WORKFLOW_ID_TWL="20";

    // TODO: STAGE ID
    public static final String STAGE_ID_ZERO = "0";
    public static final String STAGE_ID_LEAD = "1";
    public static final String STAGE_ID_APPLICATION = "2";
    public static final String STAGE_ID_PRE_PD = "3";
    public static final String STAGE_ID_PD = "4";

    // TODO: ROLE ID
    public static final String ROLE_ID_LO = "4001";
    public static final String ROLE_ID_BM = "4002";
    public static final String ROLE_ID_BCM = "8004";


  // TODO: google verification api start
  public static String safetynet_api_key = "AIzaSyDbw9jUbisAtC6hWz2J4-dY7-BMzvJmWlU";
  public static final String CERTIFICATE_SHA256_KEY = "CERTIFICATE SHA256 KEY";
  public static final String GOOGLE_API_VERIFY_URL = "https://www.googleapis.com/androidcheck/v1/attestations/";
  public static String Update_Google_Play_Services ="Update Google Play Services";
  public static String internet_connection ="No internet connection";
  public static String CTS_Profile ="Authenticate Error, CTS Profile Match Failed";
  public static String Integrity_Failed = "Authenticate Error, Basic Integrity Failed";
  public static String Integrity_Checks ="Application Integrity Checks Failed";
  public static String Verification_Error = "Verification Error!";
  public static String rooted_device = "App does not support rooted device due to security reasons";

    public static final String SCREEN_NAME_APPLICANT_KYC = "APPLICANT KYC";
    public static final String SCREEN_NAME_APPLICANT_PAN_DETAILS = "APPLICANT PAN DETAILS";
    public static final String SCREEN_NAME_CO_APPLICANT_KYC = "CO APPLICANT KYC";
    public static final String SCREEN_NAME_ADDRESS_DETAIL = "ADDRESS DETAIL";
    public static final String SCREEN_NAME_APPLICANT_SIGNATURE = "APPLICANT SIGNATURE";
    public static final String SCREEN_NAME_PERSONAL_DETAIL = "PERSONAL DETAIL";
    public static final String SCREEN_NAME_SOCIO_ECONOMIC_DETAIL = "SOCIO-ECONOMIC DETAIL";
    public static final String SCREEN_NAME_COAPPLICANT_PERSONAL_DETAIL = "COAPPLICANT PERSONAL DETAIL";
    public static final String SCREEN_NAME_COAPPLICANT_ADDRESS_DETAIL = "COAPPLICANT ADDRESS DETAIL";
    public static final String SCREEN_NAME_BUSINESS_ENTITY = "BUSINESS ENTITY";
    public static final String SCREEN_NAME_BUSINESS_PROFILE = "BUSINESS PROFILE";
    public static final String SCREEN_NAME_BUSINESS_PROOF = "BUSINESS PROOF";
    public static final String SCREEN_NAME_BUSINESS_ADDRESS_PROOF = "BUSINESS ADDRESS PROOF";
    public static final String SCREEN_NAME_BANK_DETAILS = "BANK DETAILS";
    public static final String SCREEN_NAME_STUDENT_DETAILS = "STUDENT DETAILS";
    public static final String SCREEN_NAME_CO_APPLICANT_BANK_DETAILS = "CO APPLICANT BANK DETAILS";
    public static final String SCREEN_NAME_CO_APPLICANT_PAN = "CO APPLICANT PAN DETAILS";
    public static final String SCREEN_NAME_NOMINEE_DETAIL = "NOMINEE DETAIL";
    public static final String SCREEN_NAME_SALARY_PROFILE = "SALARY PROFILE"; // TODO: AHL
    public static final String SCREEN_NAME_SALARY_PROOF = "SALARY PROOF"; // TODO: AHL
    public static final String SCREEN_NAME_OFFICE_ADDRESS_PROOF = "OFFICE ADDRESS"; // TODO: AHL
    public static final String SCREEN_NAME_INCOME_PROOF = "INCOME PROOF"; // TODO: AHL

    public static final String SCREEN_NAME_APPLICANT_LOAN_PROPOSAL = "APPLICANT LOAN PROPOSAL";
    public static final String SCREEN_NAME_COURSE_DETAILS = "COURSE DETAILS";
    public static final String SCREEN_NAME_GUARANTOR_DETAILS = "GUARANTOR DETAILS";
    public static final String SCREEN_NAME_APPLICANT_USED_CAR_DETAIL = "USED CAR DETAILS";
    public static final String SCREEN_NAME_APPLICANT_TWO_WHEELER_DETAIL = "TWO WHEELER DETAILS";
    public static final String SCREEN_NAME_COAPPLICANT_NOMINEE_DETAIL = "COAPPLICANT NOMINEE DETAIL";
    public static final String SCREEN_NAME_APPLICANT_REGULATORY_FIELDS = "APPLICANT REGULATORY FIELDS";
    public static final String SCREEN_NAME_DNCR_APPLICANT = "DNCR";
    public static final String SCREEN_NAME_CO_APPLICANT_REGULATORY_FIELDS = "COAPPLICANT REGULATORY FIELDS";
    public static final String SCREEN_NAME_REFERENCE_CHECK = "REFERENCE CHECK";

    public static final String SCREEN_NAME_DELINQUENCY = "DELINQUENCY";
    public static final String SCREEN_NAME_POSIDEX = "POSIDEX";
    public static final String SCREEN_NAME_RAMP = "RAMP";
    public static final String SCREEN_NAME_HUNTER = "HUNTER";

    public static final String SCREEN_NAME_DEDUPE = "DEDUPE";
    public static final String SCREEN_NAME_DOCUMENT_UPLOAD = "DOCUMENT UPLOAD";
    public static final String SCREEN_NAME_OTP_VERIFICATION = "OTP VERIFICATION";
    public static final String SCREEN_NAME_GENERATE_CIBIL = "GENERATE CIBIL";
    public static final String SCREEN_NAME_ENACH = "ENACH";
    public static final String SCREEN_NAME_ESIGN_ESTAMP = "E-Sign E-Stamp";
    //public static final String SCREEN_NAME_ESIGN_ESTAMP_INTIATION = "E-Sign E-Stamp Initiation";
    public static final String SCREEN_NAME_ESIGN_ESTAMP_STATUS = "E-Sign E-Stamp Status";
    public static final String SCREEN_NAME_CPV = "CPV";
    public static final String SCREEN_NAME_VKYC_UP_STREAM = "VKYC UP STREAM";
    public static final String SCREEN_NAME_VKYC_DOWN_STREAM = "VKYC DOWN STREAM";
    public static final String SCREEN_NAME_RAT = "RAT";
    public static final String SCREEN_NAME_CIBIL_STATUS = "CIBIL STATUS";
    public static final String SCREEN_NAME_CIBIL_FLAGS = "CIBIL FlAGS";
    public static final String SCREEN_NAME_CIBIL_SUCCESS_FAILED = "CIBIL SUCCESS OR FAILED";
    public static final String SCREEN_NAME_POST_SUBMIT_STATUS = "POST SUBMIT STATUS";
    public static final String SCREEN_NAME_UPSTREAM_DOWNSTREAM = "VKYC DATA";
    public static final String SCREEN_NAME_DIGITAL_DOCS = "DIGITAL DOC";
    public static final String SCREEN_NAME_OTP_AUTHENTICAION = "OTP AUTHENTICAION";
    public static final String SCREEN_NAME_BALANCE_SHEET = "BALANCE SHEET";
    public static final String SCREEN_NAME_HOUSE_ASSETS = "HOUSE ASSETS";
    public static final String SCREEN_NAME_HOUSE_LIABILITIES = "HOUSE LIABILITIES";
    public static final String SCREEN_NAME_HOUSE_DEBTS = "HOUSE DEBTS";
    public static final String SCREEN_NAME_LIABILITIES = "LIABILITIES";
    public static final String SCREEN_NAME_PRODUCT = "PRODUCT";
    public static final String SCREEN_NAME_BUSINESS_DEBTS = "BUSINESS DEBTS";
    public static final String SCREEN_NAME_ADVANCES = "ADVANCES";
    public static final String SCREEN_NAME_BUSINESS_ASSETS = "BUSINESS ASSETS";
    public static final String SCREEN_NAME_BUSINESS_LIABILITIES = "BUSINESS LIABILITIES";
    public static final String SCREEN_NAME_LOAN_PROPOSAL = "LOAN PROPOSAL";
    public static final String SCREEN_NAME_SURPLUS = "SUPLUS";
    public static final String SCREEN_NAME_FINANCIAL_RATIOS = "FINANCIAL RATIOS";
    public static final String SCREEN_NAME_SUMMARY_LOAN_PROPOSAL = "LOAN PROPOSAL SUMMARY";
    public static final String SCREEN_NAME_OBSERVATIONS = "OBSERVATIONS";
    public static final String SCREEN_NAME_REFERENCES = "REFERENCES";
    public static final String SCREEN_NAME_COAPPLICANT_SOCIO_ECONOMIC_DETAIL = "COAPPLICANT SOCIO ECONOMIC DETAIL";

    // TODO: INCOME ASSESSMENT SCREEN NAMES IL
    public static final String SCREEN_NAME_HOUSE_INCOME = "HOUSE INCOME";
    public static final String SCREEN_NAME_FAMILY_MEMBER_INCOME = "FAMILY MEMBER INCOME";
    public static final String SCREEN_NAME_EMPLOYER_VERIFICATION = "EMPLOYER VERIFICATION";
    public static final String SCREEN_NAME_HOUSE_EXPENSES = "HOUSE EXPENSES";
    public static final String SCREEN_NAME_BUSINESS_INCOME = "BUSINESS INCOME";
    public static final String SCREEN_NAME_DECLARED_SALES_WEEKLY = "DECLARED SALES WEEKLY";
    public static final String SCREEN_NAME_DECLARED_SALES_YEARLY = "DECLARED SALES YEARLY";
    public static final String SCREEN_NAME_PRODUCT_ESTIMATE = "PRODUCT ESTIMATE";
    public static final String SCREEN_NAME_PURCHASE_ANALYSIS = "PURCHASE ANALYSIS";
    public static final String SCREEN_NAME_PRODUCT_PURCHASE_ANALYSIS = "PRODUCT PURCHASE ANALYSIS";
    public static final String SCREEN_NAME_OPERATING_EXPENSE = "OPERATING EXPENSE";
    public static final String SCREEN_NAME_SUMMARY = "SUMMARY";
    public static final String SCREEN_NAME_ADD_PRODUCT_ESTIMATE = "PRODUCT";

    // TODO: INCOME ASSESSMENT SCREEN NAMES MSME
    public static final String SCREEN_NAME_BANKING_HISTORY_MSME = "BANKING HISTORY";
    public static final String SCREEN_NAME_MONTHLY_TRANSACTION_DETAIL_MSME = "MONTHLY TRANSACTION DETAIL";
    public static final String SCREEN_NAME_SALES_BILLS_MSME = "SALES BILLS";
    public static final String SCREEN_NAME_SALES_BILLS_DETAIL_MSME = "SALES BILLS DETAIL";
    public static final String SCREEN_NAME_PURCHASE_BILLS_MSME = "PURCHASE BILLS";
    public static final String SCREEN_NAME_PURCHASE_BILLS_DETAIL_MSME = "PURCHASE BILLS DETAIL";
    public static final String SCREEN_NAME_GST_MSME = "GST";
    public static final String SCREEN_NAME_ITR_MSME = "ITR";
    public static final String SCREEN_NAME_ITR_DETAIL_MSME = "ITR DETAIL";
    public static final String SCREEN_NAME_PRODUCT_ESTIMATE_MSME = "PRODUCT ESTIMATE";
    public static final String SCREEN_NAME_PRODUCT_ESTIMATE_DETAIL_MSME = "PRODUCT ESTIMATE DETAIL";
    public static final String SCREEN_NAME_PRODUCT_RAW_MATERIAL_MSME = "PRODUCT RAW MATERIAL";
    public static final String SCREEN_NAME_SERVICE_ESTIMATE_MSME = "SERVICE ESTIMATE";
    public static final String SCREEN_NAME_SERVICE_ESTIMATE_DETAIL_MSME = "SERVICE ESTIMATE DETAIL";
    public static final String SCREEN_NAME_SERVICE_RAW_MATERIAL_MSME = "SERVICE RAW MATERIAL";

    public static final String SCREEN_NAME_DIRECT_BUSINESS_EXPENSE_MSME = "DIRECT BUSINESS EXPENSE";
    public static final String SCREEN_NAME_DIRECT_BUSINESS_EXPENSE_DETAIL_MSME = "DIRECT BUSINESS EXPENSE DETAIL";
    public static final String SCREEN_NAME_BUSINESS_LIABILITIES_MSME = "BUSINESS LIABILITIES";
    public static final String SCREEN_NAME_BUSINESS_ACCOUNT_PAYABLE = "ACCOUNT PAYABLE";
    public static final String SCREEN_NAME_BUSINESS_DEBTS_DETAIL_MSME = "BUSINESS DEBTS DETAIL";
    public static final String SCREEN_NAME_ADVANCE_DETAIL__MSME = "ADVANCE DETAIL";
    public static final String SCREEN_NAME_OPERATING_EXPENSE_MSME = "OPERATING EXPENSE";
    public static final String SCREEN_NAME_INCOME_ASSESSMENT_SUMMARY_MSME = "INCOME ASSESSMENT SUMMARY";

    // TODO: DECISION SCREEN NAMES MSME
    public static final String SCREEN_NAME_LOAN_INFORMATION_MSME = "LOAN INFORMATION";
    public static final String SCREEN_NAME_SUBJECT_TO_CONDITIONS_MSME = "SUBJECT TO CONDITIONS";
    public static final String SCREEN_NAME_CASH_FLOW_SUMMARY_MSME = "CASH FLOW SUMMARY";
    public static final String SCREEN_NAME_LOAN_APPROVAL_MSME = "LOAN APPROVAL";
    public static final String SCREEN_NAME_LOAN_SUGGESTION_MSME = "LOAN SUGGESTION";
    public static final String SCREEN_NAME_HYPOTHECATION_MSME = "HYPOTHECATION";
    public static final String SCREEN_NAME_HYPOTHECATION_DETAIL_MSME = "HYPOTHECATION DETAIL";

    // TODO: BALANCE SHEET SCREEN NAMES MSME
    public static final String SCREEN_NAME_HOUSE_LIABILITIES_MSME = "HOUSE LIABILITIES";
    public static final String SCREEN_NAME_HOUSE_ASSETS_MSME = "HOUSE ASSETS";
    public static final String SCREEN_NAME_BUSINESS_ASSETS_MSME = "BUSINESS ASSETS";
    public static final String SCREEN_NAME_CASH_BUSINESS_ASSETS_MSME = "CASH";
    public static final String SCREEN_NAME_SAVINGS_CHITS_BUSINESS_ASSETS_MSME = "SAVINGS/CHITS";
    public static final String SCREEN_NAME_DEPOSITS_BUSINESS_ASSETS_BONDS_MSME = "DEPOSITS AND BONDS";
    public static final String SCREEN_NAME_INSURANCE_BUSINESS_ASSETS_MSME = "INSURANCE";
    public static final String SCREEN_NAME_INVENTORY_BUSINESS_ASSETS_MSME = "INVENTORY";
    public static final String SCREEN_NAME_RECEIVABLES_BUSINESS_ASSETS_MSME = "RECEIVABLES";

    // TODO: HOUSE ASSETS SCREEN NAMES MSME
    public static final String SCREEN_NAME_HOUSE_ASSETS_BUILDING_DETAIL_MSME = "HOUSE ASSETS - BUILDING";
    public static final String SCREEN_NAME_HOUSE_ASSETS_LAND_DETAIL_MSME = "HOUSE ASSETS - LAND";
    public static final String SCREEN_NAME_HOUSE_ASSETS_OPEN_PLOT_DETAIL_MSME = "HOUSE ASSETS - OPEN PLOT";
    public static final String SCREEN_NAME_HOUSE_ASSETS_TWO_WHEELER_DETAIL_MSME = "HOUSE ASSETS - TWO WHEELER";
    public static final String SCREEN_NAME_HOUSE_ASSETS_CAR_DETAIL_MSME = "HOUSE ASSETS - CAR";
    public static final String SCREEN_NAME_HOUSE_ASSETS_VEHICLES_DETAIL_MSME = "HOUSE ASSETS - VEHICLES";
    public static final String SCREEN_NAME_HOUSE_ASSETS_PLANT_MACHINERY_DETAIL_MSME = "HOUSE ASSETS - PLANT MACHINERY";
    public static final String SCREEN_NAME_HOUSE_ASSETS_UTILITIES_DETAIL_MSME = "HOUSE ASSETS - PLANT UTILITIES";
    public static final String SCREEN_NAME_HOUSE_ASSETS_LIVESTOCK_DETAIL_MSME = "HOUSE ASSETS - LIVESTOCK";
    public static final String SCREEN_NAME_HOUSE_ASSETS_EQUIPMENT_DETAIL_MSME = "HOUSE ASSETS - EQUIPMENT";
    public static final String SCREEN_NAME_HOUSE_ASSETS_TELEVISION_DETAIL_MSME = "HOUSE ASSETS - TELEVISION";
    public static final String SCREEN_NAME_HOUSE_ASSETS_OTHERS_DETAIL_MSME = "HOUSE ASSETS - OTHERS";
    public static final String SCREEN_NAME_HOUSE_ASSETS_FURNITURE_DETAIL_MSME = "HOUSE ASSETS - FURNITURE";
    public static final String SCREEN_NAME_HOUSE_ASSETS_COMMERCIAL_VEHICLE_DETAIL_MSME = "HOUSE ASSETS - COMMERCIAL VEHICLE";

    // TODO: BUSINESS ASSETS SCREEN NAMES MSME
    public static final String SCREEN_NAME_BUSINESS_ASSETS_BUILDING_DETAIL_MSME = "BUSINESS ASSETS - BUILDING";
    public static final String SCREEN_NAME_BUSINESS_ASSETS_LAND_DETAIL_MSME = "BUSINESS ASSETS - LAND";
    public static final String SCREEN_NAME_BUSINESS_ASSETS_OPEN_PLOT_DETAIL_MSME = "BUSINESS ASSETS - OPEN PLOT";
    public static final String SCREEN_NAME_BUSINESS_ASSETS_TWO_WHEELER_DETAIL_MSME = "BUSINESS ASSETS - TWO WHEELER";
    public static final String SCREEN_NAME_BUSINESS_ASSETS_CAR_DETAIL_MSME = "BUSINESS ASSETS - CAR";
    public static final String SCREEN_NAME_BUSINESS_ASSETS_VEHICLES_DETAIL_MSME = "BUSINESS ASSETS - VEHICLES";
    public static final String SCREEN_NAME_BUSINESS_ASSETS_PLANT_MACHINERY_DETAIL_MSME = "BUSINESS ASSETS - PLANT MACHINERY";
    public static final String SCREEN_NAME_BUSINESS_ASSETS_UTILITIES_DETAIL_MSME = "BUSINESS ASSETS - PLANT UTILITIES";
    public static final String SCREEN_NAME_BUSINESS_ASSETS_LIVESTOCK_DETAIL_MSME = "BUSINESS ASSETS - LIVESTOCK";
    public static final String SCREEN_NAME_BUSINESS_ASSETS_EQUIPMENT_DETAIL_MSME = "BUSINESS ASSETS - EQUIPMENT";
    public static final String SCREEN_NAME_BUSINESS_ASSETS_TELEVISION_DETAIL_MSME = "BUSINESS ASSETS - TELEVISION";
    public static final String SCREEN_NAME_BUSINESS_ASSETS_OTHERS_DETAIL_MSME = "BUSINESS ASSETS - OTHERS";
    public static final String SCREEN_NAME_BUSINESS_ASSETS_FURNITURE_DETAIL_MSME = "BUSINESS ASSETS - FURNITURE";
    public static final String SCREEN_NAME_BUSINESS_ASSETS_COMMERCIAL_VEHICLE_DETAIL_MSME = "BUSINESS ASSETS - COMMERCIAL VEHICLE";

    // TODO: INCOME ASSESSMENT SCREEN NAMES MSME
    public static final String SCREEN_NAME_HOUSE_INCOME_SUMMARY = "HOUSE INCOME SUMMARY";
    public static final String SCREEN_NAME_GENERAL_INCOME = "GENERAL INCOME";
    public static final String SCREEN_NAME_OTHER_INCOME_SOURCE = "OTHER INCOME SOURCE";

    // TODO: MORTGAGE SCREEN NAMES MSME
    public static final String SCREEN_NAME_MORTGAGE_MSME = "MORTGAGE";
    public static final String SCREEN_NAME_MORTGAGE_BUILDING_MSME = "MORTGAGE - BUILDING";
    public static final String SCREEN_NAME_MORTGAGE_LAND_MSME = "MORTGAGE - LAND";
    public static final String SCREEN_NAME_MORTGAGE_OPEN_PLOT_MSME = "MORTGAGE - OPEN PLOT";

    // TODO: VILLAGE SURVEY SCREEN NAME - JLG
    public static final String SCREEN_NAME_VILLAGE_SURVEY = "VILLAGE SURVEY";

    // TODO: CENTER CREATION SCREEN NAME - JLG
    public static final String SCREEN_NAME_CENTER_CREATION = "CENTER CREATION";

    // TODO: COLD CALLING SCREEN NAME - MSME
    public static final String SCREEN_NAME_COLD_CALLING = "COLD CALLING";

    // TODO: SALES TOOL SCREEN NAME - MSME
    public static final String SCREEN_NAME_SALES_TOOL = "SALES TOOL";

    // TODO: COLLECTION SCREEN NAME - MSMEe
    public static final String SCREEN_NAME_COLLECTION = "COLLECTION";

    // TODO: LEAD SCREEN NAME
    public static final String SCREEN_NAME_LEAD = "LEAD"; //

    // TODO: AUDIT SCREEN NAME
    public static final String SCREEN_NAME_AUDIT = "AUDIT"; // TODO: need to remove

    // TODO: DUMMY SCREEN NUMBER
    public static final String SCREEN_NO_ZERO = "0";

    // TODO: MSME SCREEN NUMBERS
    public static final String SCREEN_NO_APPLICANT_KYC = "2";
    public static final String SCREEN_NO_CO_APPLICANT_KYC = "3";
    public static final String SCREEN_N0_OTP_AUTHENTICAION = "4";
    public static final String SCREEN_NO_ADDRESS_DETAIL = "5";
    public static final String SCREEN_NO_APPLICANT_SIGNATURE = "6";
    public static final String SCREEN_NO_PERSONAL_DETAIL = "7";
    public static final String SCREEN_NO_SOCIO_ECONOMIC_DETAIL = "8";
    public static final String SCREEN_NO_COAPPLICANT_PERSONAL_DETAIL = "9";
    public static final String SCREEN_NO_COAPPLICANT_ADDRESS_DETAIL = "10";
    public static final String SCREEN_NO_BUSINESS_ENTITY = "11";
    public static final String SCREEN_NO_BUSINESS_PROOF = "12";
    public static final String SCREEN_NO_BANK_DETAILS = "13";
    public static final String SCREEN_NO_NOMINEE_DETAIL = "14";
    public static final String SCREEN_NO_COAPPLICANT_NOMINEE_DETAIL = "15";
    public static final String SCREEN_NO_REFERENCE_CHECK = "16";
    public static final String SCREEN_NO_DOCUMENT_UPLOAD = "17";

    // TODO: CIBIL GENERATION SCREEN NUMBER [COMMON FOR ALL LOANS]
    public static final String SCREEN_NO_GENERATE_CIBIL = "235";
    public static final String SCREEN_NO_GENERATE_CIBIL_AHL = "773";
    // public static final String SCREEN_NO_GENERATE_CIBIL_PHL = "1299";
    public static final String SCREEN_NO_GENERATE_CIBIL_PHL = "773";

   /* public static final String SCREEN_N0_HOUSE_ASSETS="26";
    public static final String SCREEN_N0_HOUSE_LIABILITIES="27";
    public static final String SCREEN_N0_BUSINESS_ASSETS="28";
    public static final String SCREEN_N0_BUSINESS_LIABILITIES="30";*/

    // TODO: IL BALANCE SHEET
    public static final String SCREEN_N0_BALANCE_SHEET = "56";
    public static final String SCREEN_N0_HOUSE_ASSETS = "26";
    public static final String SCREEN_N0_HOUSE_LIABILITIES = "57";
    public static final String SCREEN_N0_LIABILITIES = "58";
    public static final String SCREEN_N0_BUSINESS_ASSETS = "59";
    public static final String SCREEN_N0_PRODUCT = "60";
    public static final String SCREEN_N0_BUSINESS_LIABILITIES = "61";
    public static final String SCREEN_N0_BUSINESS_DEBTS = "62";
    public static final String SCREEN_N0_ADVANCES = "63";

    // TODO: IL SCREEN NUMBERS
    public static final String SCREEN_NO_APPLICANT_KYC_IL = "27";
    public static final String SCREEN_NO_CO_APPLICANT_KYC_IL = "28";
    public static final String SCREEN_NO_LOAN_PROPOSAL_IL = "29";
    public static final String SCREEN_NO_NOMINEE_DETAIL_IL = "30";
    public static final String SCREEN_NO_BUSINESS_PROFILE_IL = "31";
    public static final String SCREEN_NO_BUSINESS_PROOF_IL = "91";
    public static final String SCREEN_NO_BUSINESS_ADDRESS_PROOF_IL = "190";

    public static final String SCREEN_NO_BANK_DETAILS_IL = "32";
    public static final String SCREEN_NO_CO_APPLICANT_BANK_DETAILS_IL = "130"; // TODO: Newly Added for co applicant
    public static final String SCREEN_NO_GUARANTOR_EL = "1383";

    public static final String SCREEN_NO_ADDRESS_DETAIL_IL = "33";
    public static final String SCREEN_NO_PERSONAL_DETAIL_IL = "34";
    public static final String SCREEN_NO_SOCIO_ECONOMIC_DETAIL_IL = "35";
    public static final String SCREEN_NO_REFERENCE_CHECK_IL = "36";
    public static final String SCREEN_NO_COAPPLICANT_ADDRESS_DETAIL_IL = "37";
    public static final String SCREEN_NO_COAPPLICANT_PERSONAL_DETAIL_IL = "38";
    public static final String SCREEN_NO_COAPPLICANT_SOCIO_EOCONOMIC_DETAIL_IL = "39";

    // TODO: JLG SCREEN NUMBERS
    public static final String SCREEN_NO_APPLICANT_KYC_JLG = "238";
    public static final String SCREEN_NO_PERSONAL_DETAIL_JLG = "239";
    public static final String SCREEN_NO_APPLICANT_LOAN_PROPOSAL_JLG = "240";
    public static final String SCREEN_NO_NOMINEE_DETAILS_JLG = "241";
    public static final String SCREEN_NO_BANK_DETAILS_JLG = "242";
    public static final String SCREEN_NO_ADDRESS_DETAIL_JLG = "243";
    public static final String SCREEN_NO_SOCIO_ECONOMIC_DETAIL_JLG = "244";
    public static final String SCREEN_NO_VILLAGE_SURVEY_JLG = "245";
    public static final String SCREEN_NO_CENTER_CREATION_JLG = "246";
    public static final String SCREEN_NO_CGT_JLG = "247"; // TODO: Dummy Screen without metadata
    public static final String SCREEN_NO_GRT_JLG = "249";


    // TODO: PHL INCOME ASSESSMENT
    public static final String SCREEN_N0_HOUSE_INCOME_SUMMARY_PHL = "1254";
    public static final String SCREEN_N0_GENERAL_INCOME_PHL = "1255";
    public static final String SCREEN_N0_OTHER_INCOME_SOURCE_PHL = "1256";
    public static final String SCREEN_N0_HOUSE_EXPENSES_PHL = "1257";

    // TODO: PHL BUSINESS INCOME
    public static final String SCREEN_N0_DECALRED_SALES_WEEKLY_PHL = "1224";
    public static final String SCREEN_N0_DECALRED_SALES_YEARLY_PHL = "1225";
    public static final String SCREEN_N0_BANKING_HISTORY_PHL = "1226";
    public static final String SCREEN_N0_MONTHLY_TRANSACTION_DETAIL_PHL = "1227";
    public static final String SCREEN_N0_SALES_BILLS_PHL = "1228";
    public static final String SCREEN_N0_SALES_BILLS_DETAIL_PHL = "1229";
    public static final String SCREEN_N0_PURCHASE_BILLS_PHL = "1230";
    public static final String SCREEN_N0_PURCHASE_BILLS_DETAIL_PHL = "1231";
    public static final String SCREEN_N0_GST_PHL = "1232";
    public static final String SCREEN_N0_ITR_PHL = "1233";
    public static final String SCREEN_N0_ITR_DETAIL_PHL = "160";
    public static final String SCREEN_N0_PRODUCT_ESTIMATE_PHL = "161";
    public static final String SCREEN_N0_PRODUCT_ESTIMATE_DETAIL_PHL = "162";
    public static final String SCREEN_N0_PRODUCT_RAW_MATERIAL_PHL = "163";
    public static final String SCREEN_N0_SERVICE_ESTIMATE_PHL = "164";
    public static final String SCREEN_N0_SERVICE_ESTIMATE_DETAIL_PHL = "165";
    public static final String SCREEN_N0_SERVICE_RAW_MATERIAL_PHL = "166";
    public static final String SCREEN_N0_DIRECT_BUSINESS_EXPENSE_PHL = "167";
    public static final String SCREEN_N0_DIRECT_BUSINESS_EXPENSE_DETAIL_PHL = "168";
    public static final String SCREEN_N0_OPERATING_EXPENSE_PHL = "169";
    public static final String SCREEN_N0_INCOME_ASSESSMENT_SUMMARY_PHL = "171";

    // TODO:AHL REFERENCES
    public static final String SCREEN_NO_REFERENCES_PHL = "210";

    // TODO: DOCUMENT UPLOAD SCREEN NUMBER PHL
    public static final String SCREEN_N0_DOCUMENT_UPLOAD_EL = "739";//1271

  public static final String SCREEN_N0_DOCUMENT_UPLOAD_PHL = "1271";

    // TODO: PHL BALANCE SHEET
    public static final String SCREEN_N0_HOUSE_LIABILITIES_PHL = "136";
    public static final String SCREEN_N0_DEBTS_PHL = "137";
    public static final String SCREEN_N0_HOUSE_ASSETS_PHL = "134";
    public static final String SCREEN_N0_BUSINESS_LIABILITIES_PHL = "146";
    public static final String SCREEN_N0_ACCOUNT_PAYABLE_PHL = "147";
    public static final String SCREEN_N0_BUSINESS_DEBTS_PHL = "148";
    public static final String SCREEN_N0_ADVANCES_DEBTS_PHL = "214";
    public static final String SCREEN_N0_BUSINESS_ASSETS_PHL = "138";
    public static final String SCREEN_N0_CASH_BUSINESS_ASSETS_PHL = "139";
    public static final String SCREEN_N0_SAVING_CHITS_BUSINESS_ASSETS_PHL = "140";
    public static final String SCREEN_N0_DEPOSITS_BONDS_BUSINESS_ASSETS_PHL = "141";
    public static final String SCREEN_N0_INSURANCE_BUSINESS_ASSETS_PHL = "142";
    public static final String SCREEN_N0_INVENTORY_BUSINESS_ASSETS_PHL = "143";
    public static final String SCREEN_N0_RECEIVABLES_BUSINESS_ASSETS_PHL = "144";

    // TODO: DECISIONS SCREEN NUMBERS PHL
    public static final String SCREEN_N0_DECISIONS_LOAN_INFORMATION_PHL = "1251";
    public static final String SCREEN_N0_DECISIONS_SUBJECT_TO_CONDITIONS_PHL = "708";
    public static final String SCREEN_N0_CASH_FLOW_SUMMARY_PHL = "213";
    public static final String SCREEN_N0_LOAN_APPROVAL_PHL = "216";
    public static final String SCREEN_N0_LOAN_SUGGESTION_PHL = "218";
    public static final String SCREEN_N0_DECISIONS_HYPOTHECATION_PHL = "176";
    public static final String SCREEN_N0_DECISIONS_HYPOTHECATION_DETAIL_PHL = "196";

    // TODO: AHL SCREEN NUMBERS
    public static final String SCREEN_NO_COLD_CALLING_PHL = "550";
    public static final String SCREEN_NO_COLLECTION_PHL = "551";
    public static final String SCREEN_NO_APPLICANT_KYC_EL = "532";
    public static final String SCREEN_NO_CO_APPLICANT_KYC_EL = "533";
    public static final String SCREEN_NO_APPLICANT_LOAN_PROPOSAL_EL = "620";
    public static final String SCREEN_NO_NOMINEE_DETAIL_EL = "544";
    public static final String SCREEN_NO_BUSINESS_PROFILE_EL = "541";
    public static final String SCREEN_NO_BUSINESS_PROOF_EL = "663";
    public static final String SCREEN_NO_BUSINESS_ADDRESS_PROOF_EL = "721";
    public static final String SCREEN_NO_SALARY_PROFILE_PHL = "1061";
    public static final String SCREEN_NO_OFFICE_ADDRESS_PROOF_PHL = "1062";
    public static final String SCREEN_NO_BANK_DETAILS_EL = "543";
    public static final String SCREEN_NO_CO_APPLICANT_BANK_DETAILS_PHL = "662";

    public static final String SCREEN_NO_ADDRESS_DETAIL_EL = "535";
    public static final String SCREEN_NO_PERSONAL_DETAIL_EL = "537";
    public static final String SCREEN_NO_SOCIO_ECONOMIC_DETAIL_EL = "538";
    public static final String SCREEN_NO_REFERENCE_CHECK_EL = "546";
    public static final String SCREEN_NO_APPLICANT_PAN_DETAILS_EL = "1384";
    public static final String SCREEN_NO_CO_APPLICANT_PAN_DETAILS_EL = "1385";

    public static final String SCREEN_NO_POSIDEX = "11111";
    public static final String SCREEN_NO_DELIQUENCY = "22222";
    public static final String SCREEN_NO_RAMP = "33333";
    public static final String SCREEN_NO_HUNTER = "44444";
    public static final String SCREEN_NO_ENACH = "55555";
    public static final String SCREEN_NO_DEDUPE = "666666";
    public static final String SCREEN_NO_CPV = "777777";
    public static final String SCREEN_NO_RAT = "888888";
    public static final String SCREEN_NO_VKYC_UP_STREAM = "999999";
    public static final String SCREEN_NO_VKYC_DOWN_STREAM = "999991";
    public static final String SCREEN_NO_CIBIL_RESPONSE = "101010";
    public static final String SCREEN_NO_CIBIL_FLAGS = "10102";
    public static final String SCREEN_NO_CIBIL_SUCCESS_FAILED = "10106";
    public static final String SCREEN_NO_POST_SUBMIT_STATUS = "10107";
    public static final String SCREEN_NO_UPSTREAM_DOWNSTREAM = "10103";
    public static final String SCREEN_NO_ESIGN_ESTAMP = "10104";
    public static final String SCREEN_NO_ESIGN_ESTAMP_STATUS = "10105";
    public static final String SCREEN_NO_SANCTION_LETTER = "0";
    public static final String SCREEN_NO_APPLICATION_FORM = "1";
    public static final String SCREEN_NO_HYPOTHECATION_DEED = "2";
    public static final String SCREEN_NO_COAPPLICANT_ADDRESS_DETAIL_PHL = "540";
    public static final String SCREEN_NO_COAPPLICANT_PERSONAL_DETAIL_PHL = "539";
    public static final String SCREEN_NO_COAPPLICANT_SOCIO_EOCONOMIC_DETAIL_PHL = "661";


    // TODO: AHL SCREEN NUMBERS
    public static final String SCREEN_NO_COLD_CALLING_AHL = "550";
    public static final String SCREEN_NO_COLLECTION_AHL = "551";
    public static final String SCREEN_NO_APPLICANT_KYC_AHL = "532";
    public static final String SCREEN_NO_CO_APPLICANT_KYC_AHL = "533";
    public static final String SCREEN_NO_APPLICANT_LOAN_PROPOSAL_AHL = "620";
    public static final String SCREEN_NO_APPLICANT_USED_CAR_DETAIL = "1366";
    public static final String SCREEN_NO_APPLICANT_TWO_WHEELER_DETAIL = "1367";
    public static final String SCREEN_NO_APPLICANT_REGULATORY_FIELDS = "1377";
    public static final String SCREEN_NO_CO_APPLICANT_REGULATORY_FIELDS = "1378";
    public static final String SCREEN_NO_DNCR_APPLICANT= "1379";
    public static final String SCREEN_NO_STUDENT_DETAILS_APPLICANT= "1381";
    public static final String SCREEN_NO_NOMINEE_DETAIL_AHL = "544";
    public static final String SCREEN_NO_COURSE_DETAIL_EL = "1386";
    public static final String SCREEN_NO_BUSINESS_PROFILE_AHL = "541";
    public static final String SCREEN_NO_BUSINESS_PROOF_AHL = "663";
    public static final String SCREEN_NO_BUSINESS_ADDRESS_PROOF_AHL = "721";
    public static final String SCREEN_NO_SALARY_PROFILE_EL = "1061";
    public static final String SCREEN_NO_OFFICE_ADDRESS_PROOF_EL = "1062";
    public static final String SCREEN_NO_BANK_DETAILS_AHL = "543";
    public static final String SCREEN_NO_CO_APPLICANT_BANK_DETAILS_AHL = "662";
    public static final String SCREEN_NO_ADDRESS_DETAIL_AHL = "535";
    public static final String SCREEN_NO_PERSONAL_DETAIL_AHL = "537";
    public static final String SCREEN_NO_SOCIO_ECONOMIC_DETAIL_AHL = "538";
    public static final String SCREEN_NO_REFERENCE_CHECK_AHL = "546";
    public static final String SCREEN_NO_COAPPLICANT_ADDRESS_DETAIL_AHL = "540";
    public static final String SCREEN_NO_COAPPLICANT_PERSONAL_DETAIL_AHL = "539";
    public static final String SCREEN_NO_COAPPLICANT_SOCIO_EOCONOMIC_DETAIL_AHL = "661";

    // TODO: AHL INCOME ASSESSMENT
    public static final String SCREEN_N0_HOUSE_INCOME_SUMMARY_AHL = "722";
    public static final String SCREEN_N0_GENERAL_INCOME_AHL = "723";
    public static final String SCREEN_N0_OTHER_INCOME_SOURCE_AHL = "724";
    public static final String SCREEN_N0_HOUSE_EXPENSES_AHL = "725";

    // TODO: AHL BUSINESS INCOME
    public static final String SCREEN_N0_DECALRED_SALES_WEEKLY_AHL = "680";
    public static final String SCREEN_N0_DECALRED_SALES_YEARLY_AHL = "681";
    public static final String SCREEN_N0_BANKING_HISTORY_AHL = "682";
    public static final String SCREEN_N0_MONTHLY_TRANSACTION_DETAIL_AHL = "683";
    public static final String SCREEN_N0_SALES_BILLS_AHL = "684";
    public static final String SCREEN_N0_SALES_BILLS_DETAIL_AHL = "685";
    public static final String SCREEN_N0_PURCHASE_BILLS_AHL = "686";
    public static final String SCREEN_N0_PURCHASE_BILLS_DETAIL_AHL = "687";
    public static final String SCREEN_N0_GST_AHL = "688";
    public static final String SCREEN_N0_ITR_AHL = "689";
    public static final String SCREEN_N0_ITR_DETAIL_AHL = "160";
    public static final String SCREEN_N0_PRODUCT_ESTIMATE_AHL = "161";
    public static final String SCREEN_N0_PRODUCT_ESTIMATE_DETAIL_AHL = "162";
    public static final String SCREEN_N0_PRODUCT_RAW_MATERIAL_AHL = "163";
    public static final String SCREEN_N0_SERVICE_ESTIMATE_AHL = "164";
    public static final String SCREEN_N0_SERVICE_ESTIMATE_DETAIL_AHL = "165";
    public static final String SCREEN_N0_SERVICE_RAW_MATERIAL_AHL = "166";
    public static final String SCREEN_N0_DIRECT_BUSINESS_EXPENSE_AHL = "167";
    public static final String SCREEN_N0_DIRECT_BUSINESS_EXPENSE_DETAIL_AHL = "168";
    public static final String SCREEN_N0_OPERATING_EXPENSE_AHL = "169";
    public static final String SCREEN_N0_INCOME_ASSESSMENT_SUMMARY_AHL = "171";

    // TODO:AHL REFERENCES
    public static final String SCREEN_NO_REFERENCES_AHL = "210";

    // TODO: DOCUMENT UPLOAD SCREEN NUMBER AHL
    public static final String SCREEN_N0_DOCUMENT_UPLOAD_AHL = "1272";//739

    // TODO: AHL BALANCE SHEET
    public static final String SCREEN_N0_HOUSE_LIABILITIES_AHL = "136";
    public static final String SCREEN_N0_DEBTS_AHL = "137";
    public static final String SCREEN_N0_HOUSE_ASSETS_AHL = "134";
    public static final String SCREEN_N0_BUSINESS_LIABILITIES_AHL = "146";
    public static final String SCREEN_N0_ACCOUNT_PAYABLE_AHL = "147";
    public static final String SCREEN_N0_BUSINESS_DEBTS_AHL = "148";
    public static final String SCREEN_N0_ADVANCES_DEBTS_AHL = "214";
    public static final String SCREEN_N0_BUSINESS_ASSETS_AHL = "138";
    public static final String SCREEN_N0_CASH_BUSINESS_ASSETS_AHL = "139";
    public static final String SCREEN_N0_SAVING_CHITS_BUSINESS_ASSETS_AHL = "140";
    public static final String SCREEN_N0_DEPOSITS_BONDS_BUSINESS_ASSETS_AHL = "141";
    public static final String SCREEN_N0_INSURANCE_BUSINESS_ASSETS_AHL = "142";
    public static final String SCREEN_N0_INVENTORY_BUSINESS_ASSETS_AHL = "143";
    public static final String SCREEN_N0_RECEIVABLES_BUSINESS_ASSETS_AHL = "144";

    // TODO: DECISIONS SCREEN NUMBERS AHL
    public static final String SCREEN_N0_DECISIONS_LOAN_INFORMATION_AHL = "707";
    public static final String SCREEN_N0_DECISIONS_SUBJECT_TO_CONDITIONS_AHL = "708";
    public static final String SCREEN_N0_CASH_FLOW_SUMMARY_AHL = "213";
    public static final String SCREEN_N0_LOAN_APPROVAL_AHL = "216";
    public static final String SCREEN_N0_LOAN_SUGGESTION_AHL = "218";
    public static final String SCREEN_N0_DECISIONS_HYPOTHECATION_AHL = "176";
    public static final String SCREEN_N0_DECISIONS_HYPOTHECATION_DETAIL_AHL = "196";

    // TODO: MORTGAGE SCREEN NUMBERS AHL
    public static final String SCREEN_NO_MORTGAGE_AHL = "702";
    public static final String SCREEN_NO_MORTGAGE_BUILDING_AHL = "703";
    public static final String SCREEN_NO_MORTGAGE_LAND_AHL = "704";
    public static final String SCREEN_NO_MORTGAGE_OPEN_PLOT_AHL = "705";

    // TODO: MSME SCREEN NUMBERS
    public static final String SCREEN_NO_COLD_CALLING_MSME = "20";
    public static final String SCREEN_NO_COLLECTION_MSME = "21";
    public static final String SCREEN_NO_SALES_TOOL_MSME = "1066"; // TODO: need to change
    public static final String SCREEN_NO_APPLICANT_KYC_MSME = "2";
    public static final String SCREEN_NO_CO_APPLICANT_KYC_MSME = "3";
    public static final String SCREEN_NO_APPLICANT_LOAN_PROPOSAL_MSME = "90";
    public static final String SCREEN_NO_NOMINEE_DETAIL_MSME = "14";
    public static final String SCREEN_NO_BUSINESS_PROFILE_MSME = "11";
    public static final String SCREEN_NO_BUSINESS_PROOF_MSME = "133";
    public static final String SCREEN_NO_BUSINESS_ADDRESS_PROOF_MSME = "191";
    public static final String SCREEN_NO_BANK_DETAILS_MSME = "13";
    public static final String SCREEN_NO_CO_APPLICANT_BANK_DETAILS_MSME = "132";
    public static final String SCREEN_NO_ADDRESS_DETAIL_MSME = "5";
    public static final String SCREEN_NO_PERSONAL_DETAIL_MSME = "7";
    public static final String SCREEN_NO_SOCIO_ECONOMIC_DETAIL_MSME = "8";
    public static final String SCREEN_NO_REFERENCE_CHECK_MSME = "16";
    public static final String SCREEN_NO_COAPPLICANT_ADDRESS_DETAIL_MSME = "10";
    public static final String SCREEN_NO_COAPPLICANT_PERSONAL_DETAIL_MSME = "9";
    public static final String SCREEN_NO_COAPPLICANT_SOCIO_EOCONOMIC_DETAIL_MSME = "131";

    // TODO: MSME BALANCE SHEET
    public static final String SCREEN_N0_HOUSE_LIABILITIES_MSME = "136";
    public static final String SCREEN_N0_DEBTS_MSME = "137";
    public static final String SCREEN_N0_HOUSE_ASSETS_MSME = "134";
    public static final String SCREEN_N0_BUSINESS_LIABILITIES_MSME = "146";
    public static final String SCREEN_N0_ACCOUNT_PAYABLE_MSME = "147";
    public static final String SCREEN_N0_BUSINESS_DEBTS_MSME = "148";
    public static final String SCREEN_N0_ADVANCES_DEBTS_MSME = "214";
    public static final String SCREEN_N0_BUSINESS_ASSETS_MSME = "138";
    public static final String SCREEN_N0_CASH_BUSINESS_ASSETS_MSME = "139";
    public static final String SCREEN_N0_SAVING_CHITS_BUSINESS_ASSETS_MSME = "140";
    public static final String SCREEN_N0_DEPOSITS_BONDS_BUSINESS_ASSETS_MSME = "141";
    public static final String SCREEN_N0_INSURANCE_BUSINESS_ASSETS_MSME = "142";
    public static final String SCREEN_N0_INVENTORY_BUSINESS_ASSETS_MSME = "143";
    public static final String SCREEN_N0_RECEIVABLES_BUSINESS_ASSETS_MSME = "144";

    // TODO: DOCUMENT UPLOAD SCREEN NUMBER MSME
    public static final String SCREEN_N0_DOCUMENT_UPLOAD_MSME = "209";

    // TODO: HOUSE ASSETS SCREEN NUMBERS MSME
    public static final String SCREEN_N0_HOUSE_ASSETS_BUILDING_DETAIL_MSME = "197";
    public static final String SCREEN_N0_HOUSE_ASSETS_LAND_DETAIL_MSME = "198";
    public static final String SCREEN_N0_HOUSE_ASSETS_OPEN_PLOT_DETAIL_MSME = "199";
    public static final String SCREEN_N0_HOUSE_ASSETS_TWO_WHEELER_DETAIL_MSME = "200";
    public static final String SCREEN_N0_HOUSE_ASSETS_CAR_DETAIL_MSME = "201";
    public static final String SCREEN_N0_HOUSE_ASSETS_VEHICLES_DETAIL_MSME = "202";
    public static final String SCREEN_N0_HOUSE_ASSETS_PLANT_MACHINERY_DETAIL_MSME = "203";
    public static final String SCREEN_N0_HOUSE_ASSETS_UTILITIES_DETAIL_MSME = "204";
    public static final String SCREEN_N0_HOUSE_ASSETS_LIVESTOCK_DETAIL_MSME = "205";
    public static final String SCREEN_N0_HOUSE_ASSETS_EQUIPMENT_DETAIL_MSME = "206";
    public static final String SCREEN_N0_HOUSE_ASSETS_TELEVISION_DETAIL_MSME = "207";
    public static final String SCREEN_N0_HOUSE_ASSETS_OTHERS_DETAIL_MSME = "208";
    public static final String SCREEN_N0_HOUSE_ASSETS_FURNITURE_DETAIL_MSME = "219";
    public static final String SCREEN_N0_HOUSE_ASSETS_COMMERCIAL_VEHICLE_DETAIL_MSME = "220";

    // TODO: BUSINESS ASSETS SCREEN NUMBERS MSME 221 to 234
    public static final String SCREEN_N0_BUSINESS_ASSETS_BUILDING_DETAIL_MSME = "197";
    public static final String SCREEN_N0_BUSINESS_ASSETS_LAND_DETAIL_MSME = "198";
    public static final String SCREEN_N0_BUSINESS_ASSETS_OPEN_PLOT_DETAIL_MSME = "199";
    public static final String SCREEN_N0_BUSINESS_ASSETS_TWO_WHEELER_DETAIL_MSME = "200";
    public static final String SCREEN_N0_BUSINESS_ASSETS_CAR_DETAIL_MSME = "201";
    public static final String SCREEN_N0_BUSINESS_ASSETS_VEHICLES_DETAIL_MSME = "202";
    public static final String SCREEN_N0_BUSINESS_ASSETS_PLANT_MACHINERY_DETAIL_MSME = "203";
    public static final String SCREEN_N0_BUSINESS_ASSETS_UTILITIES_DETAIL_MSME = "204";
    public static final String SCREEN_N0_BUSINESS_ASSETS_LIVESTOCK_DETAIL_MSME = "205";
    public static final String SCREEN_N0_BUSINESS_ASSETS_EQUIPMENT_DETAIL_MSME = "206";
    public static final String SCREEN_N0_BUSINESS_ASSETS_TELEVISION_DETAIL_MSME = "207";
    public static final String SCREEN_N0_BUSINESS_ASSETS_OTHERS_DETAIL_MSME = "208";
    public static final String SCREEN_N0_BUSINESS_ASSETS_FURNITURE_DETAIL_MSME = "219";
    public static final String SCREEN_N0_BUSINESS_ASSETS_COMMERCIAL_VEHICLE_DETAIL_MSME = "220";

    // TODO: DECISIONS SCREEN NUMBERS MSME
    public static final String SCREEN_N0_DECISIONS_LOAN_INFORMATION_MSME = "177";
    public static final String SCREEN_N0_DECISIONS_SUBJECT_TO_CONDITIONS_MSME = "178";
    public static final String SCREEN_N0_CASH_FLOW_SUMMARY_MSME = "213";
    public static final String SCREEN_N0_LOAN_APPROVAL_MSME = "216";
    public static final String SCREEN_N0_LOAN_SUGGESTION_MSME = "218";
    public static final String SCREEN_N0_DECISIONS_HYPOTHECATION_MSME = "176";
    public static final String SCREEN_N0_DECISIONS_HYPOTHECATION_DETAIL_MSME = "196";


    // TODO: IL LOAN PROPOSAL
    public static final String SCREEN_NO_LOAN_PROPOSAL_MODULE_IL = "83";
    public static final String SCREEN_NO_SURPLUS_IL = "84";
    public static final String SCREEN_NO_FINANCIAL_RATIOS_IL = "85";
    public static final String SCREEN_NO_SUMMARY_LOAN_PROPOSAL_IL = "127";


    // TODO: OBSERVATION
    public static final String SCREEN_NO_OBSERVATION_IL = "86";
    // TODO: REFERENCES
    public static final String SCREEN_NO_REFERENCES_IL = "128";
    // TODO:MSME REFERENCES
    public static final String SCREEN_NO_REFERENCES_MSME = "210";

    public static final String SCREEN_N0_AUDIT = "19"; // TODO: need to remove

    // TODO: LEAD SCREEN NUMBER
    public static final String SCREEN_N0_LEAD = "18";
    public static final String SCREEN_N0_LEAD_MSME = "237";
    public static final String SCREEN_N0_LEAD_AHL = "530"; // TODO: AHL lead screen no
    public static final String SCREEN_N0_LEAD_EL = "530"; // TODO: PHL lead screen no 1127
    public static final String SCREEN_N0_LEAD_IL = "54";


    // TODO: IL INCOME ASSESSMENT
    public static final String SCREEN_N0_HOUSE_INCOME = "65";
    public static final String SCREEN_N0_FAMILY_MEMBER_INCOME = "66";
    public static final String SCREEN_N0_EMPLOYER_VERIFICATION = "67";
    public static final String SCREEN_N0_HOUSE_EXPENSES = "68";
    public static final String SCREEN_N0_MANUFACTURING_BUSINESS_INCOME = "69";
    public static final String SCREEN_N0_MANUFACTURING_DECALRED_SALES_WEEKLY = "70";
    public static final String SCREEN_N0_MANUFACTURING_DECALRED_SALES_YEARLY = "71";
    public static final String SCREEN_N0_MANUFACTURING_PRODUCT_ESTIMATE = "72";
    public static final String SCREEN_N0_MANUFACTURING_PURCHASE_ANALYSIS = "73";
    public static final String SCREEN_N0_MANUFACTURING_PURCHASE_ANALYSIS_BY_PRODUCT = "74";
    public static final String SCREEN_N0_MANUFACTURING_OPERATING_EXPENSE = "75";
    public static final String SCREEN_N0_MANUFACTURING_SUMMARY = "76";
    public static final String SCREEN_N0_TRADING_BUSINESS_INCOME = "77";
    public static final String SCREEN_N0_TRADING_DECALRED_SALES_WEEKLY = "78";
    public static final String SCREEN_N0_TRADING_DECALRED_SALES_YEARLY = "79";
    public static final String SCREEN_N0_TRADING_PURCHASE_ANALYSIS = "80";
    public static final String SCREEN_N0_TRADING_OPERATING_EXPENSE = "81";
    public static final String SCREEN_N0_TRADING_SUMMARY = "82";
    public static final String SCREEN_N0_ADD_PRODUCT_ESTIMATE = "126";

    // TODO: MSME BUSINESS INCOME
    public static final String SCREEN_N0_DECALRED_SALES_WEEKLY_MSME = "150";
    public static final String SCREEN_N0_DECALRED_SALES_YEARLY_MSME = "151";
    public static final String SCREEN_N0_BANKING_HISTORY_MSME = "152";
    public static final String SCREEN_N0_MONTHLY_TRANSACTION_DETAIL_MSME = "153";
    public static final String SCREEN_N0_SALES_BILLS_MSME = "154";
    public static final String SCREEN_N0_SALES_BILLS_DETAIL_MSME = "155";
    public static final String SCREEN_N0_PURCHASE_BILLS_MSME = "156";
    public static final String SCREEN_N0_PURCHASE_BILLS_DETAIL_MSME = "157";
    public static final String SCREEN_N0_GST_MSME = "158";
    public static final String SCREEN_N0_ITR_MSME = "159";
    public static final String SCREEN_N0_ITR_DETAIL_MSME = "160";
    public static final String SCREEN_N0_PRODUCT_ESTIMATE_MSME = "161";
    public static final String SCREEN_N0_PRODUCT_ESTIMATE_DETAIL_MSME = "162";
    public static final String SCREEN_N0_PRODUCT_RAW_MATERIAL_MSME = "163";
    public static final String SCREEN_N0_SERVICE_ESTIMATE_MSME = "164";
    public static final String SCREEN_N0_SERVICE_ESTIMATE_DETAIL_MSME = "165";
    public static final String SCREEN_N0_SERVICE_RAW_MATERIAL_MSME = "166";
    public static final String SCREEN_N0_DIRECT_BUSINESS_EXPENSE_MSME = "167";
    public static final String SCREEN_N0_DIRECT_BUSINESS_EXPENSE_DETAIL_MSME = "168";
    public static final String SCREEN_N0_OPERATING_EXPENSE_MSME = "169";
    public static final String SCREEN_N0_INCOME_ASSESSMENT_SUMMARY_MSME = "171";


    // TODO: MSME INCOME ASSESSMENT
    public static final String SCREEN_N0_HOUSE_INCOME_SUMMARY_MSME = "192";
    public static final String SCREEN_N0_GENERAL_INCOME_MSME = "193";
    public static final String SCREEN_N0_OTHER_INCOME_SOURCE_MEME = "194";
    public static final String SCREEN_N0_HOUSE_EXPENSES_MSME = "195";

    // TODO: MODULE NAMES
    public static final String MODULE_TYPE_APPLICATION = "Application";

    public static final String MODULE_TYPE_COLD_CALLING = "ColdCalling";
    public static final String MODULE_TYPE_COLLECTION = "Collection";
    public static final String MODULE_TYPE_PLANNER = "Planner";
    public static final String MODULE_TYPE_SALES_TOOL = "SalesTool";

    public static final String MODULE_TYPE_TARGET_DETAILS = "TargetDetails";
    public static final String MODULE_TYPE_LOAN_APPLICATION = "LoanApplication";
    public static final String MODULE_TYPE_VILLAGE_SURVEY = "VillageSurvey";
    public static final String MODULE_TYPE_CENTER_CREATION = "CenterCreation";
    public static final String MODULE_TYPE_ELIGIBILITY = "Eligibility";
    public static final String MODULE_TYPE_CGT = "CGT";
    public static final String MODULE_TYPE_GRT = "GRT";
    public static final String MODULE_TYPE_GROUP_FORMATION = "GroupFormation";
    public static final String MODULE_TYPE_HOUSE_VERIFICATION_CGT = "HouseVerification CGT";
    public static final String MODULE_TYPE_ATTENDANCE = "Attendance";
    public static final String MODULE_TYPE_ADD_OR_REMOVE_MEMBER = "AddOrRemoveMember";
    public static final String MODULE_TYPE_DROP_OUT_CUSTOMER = "DropOutCustomer";
    public static final String MODULE_TYPE_TASK_ACTIVITIES = "Task Activities";
    public static final String MODULE_TYPE_APPLICANT = "Applicant";
    public static final String MODULE_TYPE_APPLICANT_BUSINESS = "Applicant_Business";
    public static final String MODULE_TYPE_CO_APPLICANT = "CoApplicant";
    public static final String MODULE_TYPE_GUARANTOR = "Guarantor";
    public static final String MODULE_TYPE_BUSINESS = "Business";
    public static final String MODULE_TYPE_SALARY = "Salary"; // TODO: AHL Module
    public static final String MODULE_TYPE_LOAN_PROPOSAL_WITH_NOMINEE = "LoanProWithNominee";

    public static final String MODULE_TYPE_BALANCE_SHEET = "BalanceSheet";
    public static final String MODULE_TYPE_HOUSE_LIABILITY = "HouseLiability";
    public static final String MODULE_TYPE_HOUSE_ASSETS = "HouseAssets";
    public static final String MODULE_TYPE_HOUSE_INCOME = "HouseIncome";
    public static final String MODULE_TYPE_HOUSE_EXPENSE = "HouseExpense";
    public static final String MODULE_TYPE_MANUFACTURE_BUSINESS_INCOME = "ManufactureBusinessIncome";
    public static final String MODULE_TYPE_SERVICE_BUSINESS_INCOME = "ServiceBusinessIncome";
    public static final String MODULE_TYPE_TRADE_BUSINESS_INCOME = "TradeBusinessIncome";
    public static final String MODULE_TYPE_BUSINESS_LIABILITY = "BusinessLiability";
    public static final String MODULE_TYPE_BUSINESS_ASSETS = "BusinessAssets";
    public static final String MODULE_TYPE_INCOME_ASSESSMENT = "IncomeAssessment";
    public static final String MODULE_TYPE_REFERENCES = "References";
    public static final String MODULE_TYPE_LOAN_PROPOSAL = "LoanProposal";
    public static final String MODULE_TYPE_LOAN_PROPOSAL_PD = "LoanProposalPD";
    public static final String MODULE_TYPE_DOCUMENTS = "Documents";
    public static final String MODULE_OTP_VERIFICATION = "OTPVerification";
    public static final String MODULE_TYPE_GENERATE_CIBIL = "GenerateCibil";
    public static final String MODULE_TYPE_DECISIONS = "Decisions";

    // TODO: APPLICANT MODULE NAME LIST
    public static String[] APPLICANT_MODULE_NAME_LIST_OUT_SIDE_INDIA = {
            MODULE_TYPE_APPLICANT,
            MODULE_TYPE_CO_APPLICANT + 1, // TODO: for co applicant validation
            MODULE_TYPE_LOAN_PROPOSAL_WITH_NOMINEE
    };
    public static String[] APPLICANT_MODULE_NAME_LIST = {
            MODULE_TYPE_APPLICANT,
            //MODULE_TYPE_CO_APPLICANT + 1, // TODO: for co applicant validation
            MODULE_TYPE_LOAN_PROPOSAL_WITH_NOMINEE
    };
    // TODO: LEAD MODULE
    public static final String MODULE_TYPE_LEAD = "Lead";
    // TODO: LOAN NAMES
    public static final String LOAN_NAME_JLG = "JLG";
    public static final String LOAN_NAME_INDIVIDUAL = "INDIVIDUAL";
    public static final String LOAN_NAME_MSME = "MSME";
    public static final String LOAN_NAME_TW = "TW";
    public static final String LOAN_NAME_AHL = "AHL";
    public static final String LOAN_NAME_PHL = "TWL";
    public static final String LOAN_NAME_EL = "EL";
    public static final String LOAN_NAME_TWL = "TWL";

    // TODO: INDIVIDUAL


  public static String[] APPLICANT_TAB_SCREEN_NAMES_JLG = {
         SCREEN_NAME_APPLICANT_KYC,
         SCREEN_NAME_PERSONAL_DETAIL,
         SCREEN_NAME_SOCIO_ECONOMIC_DETAIL,
  };
  public static String[] LOAN_PROPOSAL_WITH_NOMINEE_MODULE_SCREEN_NAMES_JLG = {
         MODULE_TYPE_APPLICANT,
         MODULE_TYPE_LOAN_PROPOSAL_WITH_NOMINEE,
  };
    // TODO: APPLICANT IL
    public static String[] APPLICANT_TAB_SCREEN_NAMES_BUSINESS_IL = {
            SCREEN_NAME_APPLICANT_KYC,
            SCREEN_NAME_APPLICANT_PAN_DETAILS,
            SCREEN_NAME_PERSONAL_DETAIL,
            SCREEN_NAME_SOCIO_ECONOMIC_DETAIL,
            SCREEN_NAME_ADDRESS_DETAIL,
            SCREEN_NAME_BUSINESS_PROFILE,
            SCREEN_NAME_BUSINESS_PROOF,
            SCREEN_NAME_BUSINESS_ADDRESS_PROOF,
            SCREEN_NAME_STUDENT_DETAILS,
            SCREEN_NAME_BANK_DETAILS,
            SCREEN_NAME_REFERENCE_CHECK,
            SCREEN_NAME_CPV,
            SCREEN_NAME_RAMP,
            SCREEN_NAME_DEDUPE,
            SCREEN_NAME_APPLICANT_REGULATORY_FIELDS,
            SCREEN_NAME_DNCR_APPLICANT
    };

    public static String[] APPLICANT_TAB_SCREEN_NUMBERS_BUSINESS_IL = {
            SCREEN_NO_APPLICANT_KYC_EL,
            SCREEN_NO_APPLICANT_PAN_DETAILS_EL,
            SCREEN_NO_PERSONAL_DETAIL_EL,
            SCREEN_NO_SOCIO_ECONOMIC_DETAIL_EL,
            SCREEN_NO_ADDRESS_DETAIL_EL,
            SCREEN_NO_BUSINESS_PROFILE_EL,
            SCREEN_NO_BUSINESS_PROOF_EL,
            SCREEN_NO_BUSINESS_ADDRESS_PROOF_EL,
            SCREEN_NO_STUDENT_DETAILS_APPLICANT,
            SCREEN_NO_BANK_DETAILS_EL,
            SCREEN_NO_REFERENCE_CHECK_EL,
            SCREEN_NO_CPV,
            SCREEN_NO_RAMP,
            SCREEN_NO_DEDUPE,
            SCREEN_NO_APPLICANT_REGULATORY_FIELDS,
            SCREEN_NO_DNCR_APPLICANT
    };

  public static String[] APPLICANT_TAB_SCREEN_NAMES_IL = {
          SCREEN_NAME_APPLICANT_KYC,
          SCREEN_NAME_APPLICANT_PAN_DETAILS,
          SCREEN_NAME_PERSONAL_DETAIL,
          SCREEN_NAME_SOCIO_ECONOMIC_DETAIL,
          SCREEN_NAME_ADDRESS_DETAIL,
          SCREEN_NAME_SALARY_PROFILE,
          SCREEN_NAME_OFFICE_ADDRESS_PROOF,
          SCREEN_NAME_STUDENT_DETAILS,
          SCREEN_NAME_BANK_DETAILS,
          SCREEN_NAME_REFERENCE_CHECK,
          SCREEN_NAME_CPV,
          SCREEN_NAME_RAMP,
          SCREEN_NAME_DEDUPE,
          SCREEN_NAME_APPLICANT_REGULATORY_FIELDS,
          SCREEN_NAME_DNCR_APPLICANT
  };

  public static String[] APPLICANT_TAB_SCREEN_NAMES_NO_CUSTOMERTYPE_IL = {
          SCREEN_NAME_APPLICANT_KYC,
          SCREEN_NAME_APPLICANT_PAN_DETAILS,
          SCREEN_NAME_PERSONAL_DETAIL,
          SCREEN_NAME_SOCIO_ECONOMIC_DETAIL,
          SCREEN_NAME_ADDRESS_DETAIL,
          SCREEN_NAME_STUDENT_DETAILS,
          SCREEN_NAME_BANK_DETAILS,
          SCREEN_NAME_REFERENCE_CHECK,
          SCREEN_NAME_CPV,
          SCREEN_NAME_RAMP,
          SCREEN_NAME_DEDUPE,
          SCREEN_NAME_APPLICANT_REGULATORY_FIELDS,
          SCREEN_NAME_DNCR_APPLICANT
  };

  public static String[] APPLICANT_TAB_SCREEN_NUMBERS_IL = {
          SCREEN_NO_APPLICANT_KYC_EL,
          SCREEN_NO_APPLICANT_PAN_DETAILS_EL,
          SCREEN_NO_PERSONAL_DETAIL_EL,
          SCREEN_NO_SOCIO_ECONOMIC_DETAIL_EL,
          SCREEN_NO_ADDRESS_DETAIL_EL,
          SCREEN_NO_SALARY_PROFILE_EL,
          SCREEN_NO_OFFICE_ADDRESS_PROOF_EL,
          SCREEN_NO_STUDENT_DETAILS_APPLICANT,
          SCREEN_NO_BANK_DETAILS_EL,
          SCREEN_NO_REFERENCE_CHECK_EL,
          SCREEN_NO_CPV,
          SCREEN_NO_RAMP,
          SCREEN_NO_DEDUPE,
          SCREEN_NO_APPLICANT_REGULATORY_FIELDS,
          SCREEN_NO_DNCR_APPLICANT
  };

    // TODO: APPLICANT AHL
    public static String[] APPLICANT_TAB_SCREEN_NAMES_EL = {
            SCREEN_NAME_APPLICANT_KYC,
            SCREEN_NAME_APPLICANT_PAN_DETAILS,
            SCREEN_NAME_PERSONAL_DETAIL,
            SCREEN_NAME_SOCIO_ECONOMIC_DETAIL,
            SCREEN_NAME_ADDRESS_DETAIL,
            SCREEN_NAME_SALARY_PROFILE,
            SCREEN_NAME_OFFICE_ADDRESS_PROOF,
            SCREEN_NAME_STUDENT_DETAILS,
            SCREEN_NAME_BANK_DETAILS,
            SCREEN_NAME_REFERENCE_CHECK,
            SCREEN_NAME_CPV,
            SCREEN_NAME_RAMP,
            SCREEN_NAME_DEDUPE,
            SCREEN_NAME_APPLICANT_REGULATORY_FIELDS,
            SCREEN_NAME_DNCR_APPLICANT
    };
  public static String[] APPLICANT_TAB_SCREEN_NAMES_NO_CUSTOMERTYPE_EL = {
          SCREEN_NAME_APPLICANT_KYC,
          SCREEN_NAME_APPLICANT_PAN_DETAILS,
          SCREEN_NAME_PERSONAL_DETAIL,
          SCREEN_NAME_SOCIO_ECONOMIC_DETAIL,
          SCREEN_NAME_ADDRESS_DETAIL,
          SCREEN_NAME_STUDENT_DETAILS,
          SCREEN_NAME_BANK_DETAILS,
          SCREEN_NAME_REFERENCE_CHECK,
          SCREEN_NAME_CPV,
          SCREEN_NAME_RAMP,
          SCREEN_NAME_DEDUPE,
          SCREEN_NAME_APPLICANT_REGULATORY_FIELDS,
          SCREEN_NAME_DNCR_APPLICANT
  };
    public static String[] APPLICANT_TAB_SCREEN_NUMBERS_EL = {
            SCREEN_NO_APPLICANT_KYC_EL,
            SCREEN_NO_APPLICANT_PAN_DETAILS_EL,
            SCREEN_NO_PERSONAL_DETAIL_EL,
            SCREEN_NO_SOCIO_ECONOMIC_DETAIL_EL,
            SCREEN_NO_ADDRESS_DETAIL_EL,
            SCREEN_NO_SALARY_PROFILE_EL,
            SCREEN_NO_OFFICE_ADDRESS_PROOF_EL,
            SCREEN_NO_STUDENT_DETAILS_APPLICANT,
            SCREEN_NO_BANK_DETAILS_EL,
            SCREEN_NO_REFERENCE_CHECK_EL,
            SCREEN_NO_CPV,
            SCREEN_NO_RAMP,
            SCREEN_NO_DEDUPE,
            SCREEN_NO_APPLICANT_REGULATORY_FIELDS,
            SCREEN_NO_DNCR_APPLICANT
    };
    public static String[] APPLICANT_TAB_SCREEN_NUMBERS_NO_CUSTOMERTYPE_EL = {
            SCREEN_NO_APPLICANT_KYC_EL,
            SCREEN_NO_APPLICANT_PAN_DETAILS_EL,
            SCREEN_NO_PERSONAL_DETAIL_EL,
            SCREEN_NO_SOCIO_ECONOMIC_DETAIL_EL,
            SCREEN_NO_ADDRESS_DETAIL_EL,
            SCREEN_NO_STUDENT_DETAILS_APPLICANT,
            SCREEN_NO_BANK_DETAILS_EL,
            SCREEN_NO_REFERENCE_CHECK_EL,
            SCREEN_NO_CPV,
            SCREEN_NO_RAMP,
            SCREEN_NO_DEDUPE,
            SCREEN_NO_APPLICANT_REGULATORY_FIELDS,
            SCREEN_NO_DNCR_APPLICANT
    };

    public static int[] APPLICANT_TAB_ICONS_NAMES_EL = {
            R.drawable.ic_fingerprint_24dp,
            R.drawable.ic_person_white_24dp,
            R.drawable.ic_domain_black_24dp,
            R.drawable.ic_home_black_24dp,
            R.drawable.ic_business_center_black_24dp,
            R.drawable.ic_business_add_proof2_24,
            R.drawable.ic_person_pin_black_24dp,
            R.drawable.ic_account_bank_24dp,
            R.drawable.ic_person_pin_black_24dp,
            R.drawable.ic_person_pin_black_24dp,
            R.drawable.ic_person_pin_black_24dp,
            R.drawable.ic_person_pin_black_24dp,
            R.drawable.ic_person_pin_black_24dp,
            R.drawable.ic_person_pin_black_24dp,
            R.drawable.ic_person_pin_black_24dp
    };
    public static int[] APPLICANT_TAB_ICONS_NO_CUSTOMERTYPE_EL = {
            R.drawable.ic_fingerprint_24dp,
            R.drawable.ic_person_white_24dp,
            R.drawable.ic_domain_black_24dp,
            R.drawable.ic_home_black_24dp,
            R.drawable.ic_person_pin_black_24dp,
            R.drawable.ic_account_bank_24dp,
            R.drawable.ic_person_pin_black_24dp,
            R.drawable.ic_person_pin_black_24dp,
            R.drawable.ic_person_pin_black_24dp,
            R.drawable.ic_person_pin_black_24dp,
            R.drawable.ic_person_pin_black_24dp,
            R.drawable.ic_person_pin_black_24dp,
            R.drawable.ic_person_pin_black_24dp
    };

  public static String[] APPLICANT_TAB_SCREEN_NAMES_BUSINESS_EL = {
          SCREEN_NAME_APPLICANT_KYC,
          SCREEN_NAME_APPLICANT_PAN_DETAILS,
          SCREEN_NAME_PERSONAL_DETAIL,
          SCREEN_NAME_SOCIO_ECONOMIC_DETAIL,
          SCREEN_NAME_ADDRESS_DETAIL,
          SCREEN_NAME_BUSINESS_PROFILE,
          SCREEN_NAME_BUSINESS_PROOF,
          SCREEN_NAME_BUSINESS_ADDRESS_PROOF,
          SCREEN_NAME_STUDENT_DETAILS,
          SCREEN_NAME_BANK_DETAILS,
          SCREEN_NAME_REFERENCE_CHECK,
          SCREEN_NAME_CPV,
          SCREEN_NAME_RAMP,
          SCREEN_NAME_DEDUPE,
          SCREEN_NAME_APPLICANT_REGULATORY_FIELDS,
          SCREEN_NAME_DNCR_APPLICANT
  };
  public static String[] APPLICANT_TAB_SCREEN_NUMBERS_BUSINESS_EL = {
          SCREEN_NO_APPLICANT_KYC_EL,
          SCREEN_NO_APPLICANT_PAN_DETAILS_EL,
          SCREEN_NO_PERSONAL_DETAIL_EL,
          SCREEN_NO_SOCIO_ECONOMIC_DETAIL_EL,
          SCREEN_NO_ADDRESS_DETAIL_EL,
          SCREEN_NO_BUSINESS_PROFILE_EL,
          SCREEN_NO_BUSINESS_PROOF_EL,
          SCREEN_NO_BUSINESS_ADDRESS_PROOF_EL,
          SCREEN_NO_STUDENT_DETAILS_APPLICANT,
          SCREEN_NO_BANK_DETAILS_EL,
          SCREEN_NO_REFERENCE_CHECK_EL,
          SCREEN_NO_CPV,
          SCREEN_NO_RAMP,
          SCREEN_NO_DEDUPE,
          SCREEN_NO_APPLICANT_REGULATORY_FIELDS,
          SCREEN_NO_DNCR_APPLICANT
  };
  public static String[] CO_APPLICANT_TAB_SCREEN_NUMBERS_BUSINESS_EL = {
          SCREEN_NO_APPLICANT_KYC_EL,
          SCREEN_NO_APPLICANT_PAN_DETAILS_EL,
          SCREEN_NO_PERSONAL_DETAIL_EL,
          SCREEN_NO_SOCIO_ECONOMIC_DETAIL_EL,
          SCREEN_NO_ADDRESS_DETAIL_EL,
          SCREEN_NO_BUSINESS_PROFILE_EL,
          SCREEN_NO_BUSINESS_PROOF_EL,
          SCREEN_NO_BUSINESS_ADDRESS_PROOF_EL,
          SCREEN_NO_STUDENT_DETAILS_APPLICANT,
          SCREEN_NO_BANK_DETAILS_EL,
          SCREEN_NO_REFERENCE_CHECK_EL,
          SCREEN_NO_CPV,
          SCREEN_NO_RAMP,
          SCREEN_NO_DEDUPE,
          SCREEN_NO_APPLICANT_REGULATORY_FIELDS,
          SCREEN_NO_DNCR_APPLICANT
  };

  public static int[] APPLICANT_TAB_ICONS_NAMES_BUSINESS_EL = {
          R.drawable.ic_fingerprint_24dp,
          R.drawable.ic_person_white_24dp,
          R.drawable.ic_domain_black_24dp,
          R.drawable.ic_home_black_24dp,
          R.drawable.ic_business_center_black_24dp,
          R.drawable.ic_business_proof_24,
          R.drawable.ic_business_add_proof2_24,
          R.drawable.ic_account_bank_24dp,
          R.drawable.ic_person_pin_black_24dp,
          R.drawable.ic_person_pin_black_24dp,
          R.drawable.ic_person_pin_black_24dp,
          R.drawable.ic_person_pin_black_24dp,
          R.drawable.ic_person_pin_black_24dp,
          R.drawable.ic_person_pin_black_24dp,
          R.drawable.ic_person_pin_black_24dp,
          R.drawable.ic_person_pin_black_24dp
  };
  public static int[] CO_APPLICANT_TAB_ICONS_NAMES_BUSINESS_EL = {
          R.drawable.ic_fingerprint_24dp,
          R.drawable.ic_person_white_24dp,
          R.drawable.ic_domain_black_24dp,
          R.drawable.ic_home_black_24dp,
          R.drawable.ic_business_center_black_24dp,
          R.drawable.ic_business_proof_24,
          R.drawable.ic_business_add_proof2_24,
          R.drawable.ic_account_bank_24dp,
          R.drawable.ic_person_pin_black_24dp,
          R.drawable.ic_person_pin_black_24dp,
          R.drawable.ic_person_pin_black_24dp,
          R.drawable.ic_person_pin_black_24dp,
          R.drawable.ic_person_pin_black_24dp,
          R.drawable.ic_person_pin_black_24dp,
          R.drawable.ic_person_pin_black_24dp
  };

    // TODO: CO APPLICANT IL
    public static String[] CO_APPLICANT_TAB_SCREEN_NAMES_IL = {
            SCREEN_NAME_CO_APPLICANT_KYC,
            SCREEN_NAME_CO_APPLICANT_PAN,
            SCREEN_NAME_COAPPLICANT_PERSONAL_DETAIL,
            SCREEN_NAME_COAPPLICANT_SOCIO_ECONOMIC_DETAIL,
            SCREEN_NAME_COAPPLICANT_ADDRESS_DETAIL,
            SCREEN_NAME_CO_APPLICANT_BANK_DETAILS,
            SCREEN_NAME_CPV,
            SCREEN_NAME_RAMP,
            SCREEN_NAME_DEDUPE,
            SCREEN_NAME_CO_APPLICANT_REGULATORY_FIELDS,
            SCREEN_NAME_DNCR_APPLICANT
    };

    public static String[] CO_APPLICANT_TAB_SCREEN_NUMBERS_IL = {
            SCREEN_NO_CO_APPLICANT_KYC_IL,
            SCREEN_NO_CO_APPLICANT_PAN_DETAILS_EL,
            SCREEN_NO_COAPPLICANT_PERSONAL_DETAIL_IL,
            SCREEN_NO_COAPPLICANT_SOCIO_EOCONOMIC_DETAIL_IL,
            SCREEN_NO_COAPPLICANT_ADDRESS_DETAIL_IL,
            SCREEN_NO_CO_APPLICANT_BANK_DETAILS_IL,
            SCREEN_NO_CPV,
            SCREEN_NO_RAMP,
            SCREEN_NO_DEDUPE,
            SCREEN_NO_APPLICANT_REGULATORY_FIELDS,
            SCREEN_NAME_DNCR_APPLICANT
            /*,
            SCREEN_NO_DEDUPE,
            SCREEN_NO_RAMP,
            SCREEN_NO_RAT,
            SCREEN_NO_CO_APPLICANT_REGULATORY_FIELDS,*/
    };

    // TODO: CO APPLICANT AHL
    public static String[] CO_APPLICANT_TAB_SCREEN_NAMES_EL = {
            SCREEN_NAME_CO_APPLICANT_KYC,
            SCREEN_NAME_CO_APPLICANT_PAN,
            SCREEN_NAME_COAPPLICANT_PERSONAL_DETAIL,
            SCREEN_NAME_COAPPLICANT_SOCIO_ECONOMIC_DETAIL,
            SCREEN_NAME_COAPPLICANT_ADDRESS_DETAIL,
            SCREEN_NAME_CO_APPLICANT_BANK_DETAILS,
            SCREEN_NAME_CPV,
            SCREEN_NAME_RAMP,
            SCREEN_NAME_DEDUPE,
            SCREEN_NAME_CO_APPLICANT_REGULATORY_FIELDS,
            SCREEN_NAME_DNCR_APPLICANT
    };

    public static String[] GUARANTOR_TAB_SCREEN_NAMES_EL = {
            SCREEN_NAME_GUARANTOR_DETAILS
    };

  public static String[] GUARANTOR_TAB_SCREEN_NUMBER_EL = {
          SCREEN_NO_GUARANTOR_EL
  };

    public static String[] CO_APPLICANT_TAB_SCREEN_NUMBERS_EL = {
            SCREEN_NO_CO_APPLICANT_KYC_EL,
            SCREEN_NO_CO_APPLICANT_PAN_DETAILS_EL,
            SCREEN_NO_COAPPLICANT_PERSONAL_DETAIL_PHL,
            SCREEN_NO_COAPPLICANT_SOCIO_EOCONOMIC_DETAIL_PHL,
            SCREEN_NO_COAPPLICANT_ADDRESS_DETAIL_PHL,
            SCREEN_NO_CO_APPLICANT_BANK_DETAILS_PHL,
            SCREEN_NO_CPV,
            SCREEN_NO_RAMP,
            SCREEN_NO_DEDUPE,
            SCREEN_NO_CO_APPLICANT_REGULATORY_FIELDS,
            SCREEN_NO_DNCR_APPLICANT
    };

    public static int[] CO_APPLICANT_TAB_ICONS_NAMES_EL = {
            R.drawable.ic_fingerprint_24dp,
            R.drawable.ic_person_white_24dp,
            R.drawable.ic_domain_black_24dp,
            R.drawable.ic_home_black_24dp,
            R.drawable.ic_account_bank_24dp,
            R.drawable.ic_person_pin_black_24dp,
            R.drawable.ic_person_pin_black_24dp,
            R.drawable.ic_person_pin_black_24dp,
            R.drawable.ic_person_pin_black_24dp,
            R.drawable.ic_person_pin_black_24dp,
            R.drawable.ic_person_pin_black_24dp,
            R.drawable.ic_person_pin_black_24dp
    };

    public static int[] GUARANTOR_TAB_ICONS_NAMES_EL ={
            R.drawable.ic_person_pin_black_24dp,
    };

    // TODO: SALARY AHL
    public static String[] SALARY_TAB_SCREEN_NAMES_AHL = {
            SCREEN_NAME_SALARY_PROFILE,
            SCREEN_NAME_OFFICE_ADDRESS_PROOF,
            SCREEN_NAME_CPV
    };

    public static String[] REFERENCES_MODULE_SCREEN_NUMBERS_IL = {
            SCREEN_NO_REFERENCES_IL
    };

    // TODO: LOAN PROPOSAL WITH NOMINEE MODULE IL
    public static String[] LOAN_PROPOSAL_WITH_NOMINEE_MODULE_SCREEN_NAMES_IL = {
            //  SCREEN_NAME_APPLICANT_LOAN_PROPOSAL,
            SCREEN_NAME_APPLICANT_LOAN_PROPOSAL,
            SCREEN_NAME_APPLICANT_USED_CAR_DETAIL,
            SCREEN_NAME_NOMINEE_DETAIL
    };
    public static String[] LOAN_PROPOSAL_WITH_NOMINEE_MODULE_SCREEN_NUMBERS_IL = {
            SCREEN_NO_LOAN_PROPOSAL_IL,
            SCREEN_NO_NOMINEE_DETAIL_IL
    };
    // TODO: LOAN PROPOSAL WITH NOMINEE MODULE AHL
    public static String[] LOAN_PROPOSAL_WITH_NOMINEE_MODULE_SCREEN_NAMES_EL = {
            SCREEN_NAME_APPLICANT_LOAN_PROPOSAL,
            SCREEN_NAME_COURSE_DETAILS,
            SCREEN_NAME_NOMINEE_DETAIL
    };
    public static String[] LOAN_PROPOSAL_WITH_NOMINEE_MODULE_SCREEN_NAMES_PHL = {
            SCREEN_NAME_APPLICANT_LOAN_PROPOSAL,
            SCREEN_NAME_APPLICANT_TWO_WHEELER_DETAIL,
            SCREEN_NAME_NOMINEE_DETAIL
    };
    public static String[] LOAN_PROPOSAL_WITH_NOMINEE_MODULE_SCREEN_NUMBERS_EL = {
            SCREEN_NO_APPLICANT_LOAN_PROPOSAL_EL,
            SCREEN_NO_COURSE_DETAIL_EL,
            SCREEN_NO_NOMINEE_DETAIL_AHL
    };
    public static int[] LOAN_PROPOSAL_WITH_NOMINEE_MODULE_ICONS_EL = {
            R.drawable.ic_collections_black_24dp,
            R.drawable.ic_nominee_24dp,
            R.drawable.ic_nominee_24dp
    };

    public static String[] LOAN_PROPOSAL_WITH_NOMINEE_MODULE_SCREEN_NUMBERS_PHL = {
            SCREEN_NO_APPLICANT_LOAN_PROPOSAL_EL,
            SCREEN_NO_APPLICANT_TWO_WHEELER_DETAIL,
            SCREEN_NO_NOMINEE_DETAIL_EL
    };

    // TODO: LOAN PROPOSAL MODULE IL
    public static String[] LOAN_PROPOSAL_MODULE_SCREEN_NAMES_IL = {
            SCREEN_NAME_LOAN_PROPOSAL
    };

    // TODO: LOAN PROPOSAL MODULE AHL
    public static String[] LOAN_PROPOSAL_MODULE_SCREEN_NAMES_AHL = {
            SCREEN_NAME_LOAN_PROPOSAL
    };
  public static String[] LOAN_PROPOSAL_MODULE_SCREEN_NAMES_EL = {
          SCREEN_NAME_LOAN_PROPOSAL
  };
    public static String[] LOAN_PROPOSAL_MODULE_SCREEN_NUMBERS_PHL = {
            SCREEN_NO_APPLICANT_LOAN_PROPOSAL_EL
    };
    public static int[] LOAN_PROPOSAL_MODULE_ICONS_AHL = {
            R.drawable.ic_collections_black_24dp
    };
    // TODO: DOCUMENTS MODULE AHL
    public static String[] DOCUMENTS_MODULE_SCREEN_NAMES_AHL = {
            SCREEN_NAME_DOCUMENT_UPLOAD
    };
  public static String[] DOCUMENTS_MODULE_SCREEN_NAMES_EL = {
          SCREEN_NAME_DOCUMENT_UPLOAD
  };
    public static String[] DOCUMENTS_MODULE_SCREEN_NUMBERS_PHL = {
            SCREEN_N0_DOCUMENT_UPLOAD_PHL
    };
  public static String[] DOCUMENTS_MODULE_SCREEN_NUMBERS_EL = {
          SCREEN_N0_DOCUMENT_UPLOAD_EL
  };
    public static int[] DOCUMENTS_MODULE_ICONS_AHL = {
            R.drawable.ic_collections_black_24dp
    };
  public static int[] DOCUMENTS_MODULE_ICONS_EL = {
          R.drawable.ic_collections_black_24dp
  };

    // TODO: OTP VERIFICATION ( common for all loan )
    public static String[] OTP_VERIFICATION_MODULE_SCREEN_NAMES = {
            SCREEN_NAME_OTP_VERIFICATION
    };
    public static String[] OTP_VERIFICATION_MODULE_SCREEN_NUMBERS = {
            SCREEN_NAME_OTP_VERIFICATION
    };
    public static int[] OTP_VERIFICATION_MODULE_ICONS = {
            R.drawable.otp_verification_icon
    };

    public static String[] GENERATE_CIBIL_MODULE_SCREEN_NUMBERS = {
            SCREEN_NO_GENERATE_CIBIL
    };
    // TODO: GENERATE CIBIL AHL
    public static String[] GENERATE_CIBIL_MODULE_SCREEN_NAMES_EL = {
            SCREEN_NAME_HUNTER,
            SCREEN_NAME_GENERATE_CIBIL,
            /*SCREEN_NAME_ENACH,
            SCREEN_NAME_ESIGN_ESTAMP*/
    };
    public static String[] GENERATE_CIBIL_MODULE_SCREEN_NUMBERS_EL = {
            SCREEN_NO_HUNTER,
            SCREEN_NO_GENERATE_CIBIL_PHL,
         /*   SCREEN_NO_ENACH,
            SCREEN_NO_ESIGN_ESTAMP*/
    };
    public static int[] GENERATE_CIBIL_MODULE_ICONS_EL = {
            R.drawable.ic_assignment_ind_black_24dp,
            R.drawable.otp_verification_icon,
            /*R.drawable.otp_verification_icon,
            R.drawable.otp_verification_icon*/
    };

    public static String[] HOUSE_LIABILITY_MODULE_SCREEN_NUMBERS_IL = {
            SCREEN_N0_HOUSE_LIABILITIES
    };


    // TODO: HOUSE LIABILITY MSME
    public static String[] HOUSE_LIABILITY_MODULE_SCREEN_NAMES_MSME = {
            SCREEN_NAME_HOUSE_LIABILITIES_MSME
    };

    public static String[] HOUSE_ASSETS_MODULE_SCREEN_NUMBERS_MSME = {
            SCREEN_N0_HOUSE_ASSETS_MSME
    };


    public static String[] HOUSE_LIABILITY_MODULE_SCREEN_NUMBERS_AHL = {
            SCREEN_N0_HOUSE_LIABILITIES_AHL
    };
    public static String[] HOUSE_LIABILITY_MODULE_SCREEN_NUMBERS_PHL = {
            SCREEN_N0_HOUSE_LIABILITIES_PHL
    };

    // TODO: BUSINESS ASSETS IL
    public static String[] BUSINESS_ASSETS_MODULE_SCREEN_NAMES_IL = {
            SCREEN_NAME_BUSINESS_ASSETS
    };


    // TODO: BUSINESS ASSETS MSME
    public static String[] BUSINESS_ASSETS_MODULE_SCREEN_NAMES_MSME = {
            SCREEN_NAME_BUSINESS_ASSETS_MSME
    };

    // TODO: TRADING BUSINESS INCOME
    public static String[] TRADING_BUSINESS_INCOME_SCREEN_NAMES = {
            SCREEN_NAME_BUSINESS_INCOME,
            SCREEN_NAME_DECLARED_SALES_WEEKLY,
            SCREEN_NAME_DECLARED_SALES_YEARLY,
            SCREEN_NAME_PURCHASE_ANALYSIS,
            SCREEN_NAME_OPERATING_EXPENSE,
            SCREEN_NAME_SUMMARY
    };

    public static int[] TRADING_BUSINESS_INCOME_SCREEN_NAMES_ICONS = {
            R.drawable.ic_business_center_black_24dp,
            R.drawable.ic_attach_money_black_24dp,
            R.drawable.ic_monetization_on_black_24dp,
            R.drawable.ic_add_shopping_cart_black_24dp,
            R.drawable.ic_local_mall_black_24dp,
            R.drawable.ic_playlist_add_check_black_24dp
    };

    // TODO: COLLECTION AHL
    public static String[] COLLECTION_TAB_SCREEN_NAMES_AHL = {
            SCREEN_NAME_COLLECTION
    };
    public static String[] COLLECTION_TAB_SCREEN_NUMBERS_PHL = {
            SCREEN_NO_COLLECTION_PHL
    };
    public static int[] COLLECTION_TAB_ICONS_AHL = {
            R.drawable.ic_streetview_black_24dp
    };

    // TODO: LEAD AHL
    public static String[] LEAD_TAB_SCREEN_NAMES_AHL = {
            SCREEN_NAME_LEAD
    };
  public static String[] LEAD_TAB_SCREEN_NAMES_EL = {
          SCREEN_NAME_LEAD
  };
    public static String[] LEAD_TAB_SCREEN_NUMBERS_PHL = {
            SCREEN_N0_LEAD_EL
    };
    public static int[] LEAD_TAB_ICONS_AHL = {
            R.drawable.ic_streetview_black_24dp
    };

    // TODO: LEAD TW
    public static String[] LEAD_TAB_SCREEN_NAMES_TW = {
            SCREEN_NAME_LEAD
    };

    // TODO: LOAN PROPOSAL SCREEN NAMES IL
    public static String[] LOAN_PROPOSAL_PD_TAB_SCREEN_NAMES_IL = {
            SCREEN_NAME_SUMMARY_LOAN_PROPOSAL,
//            SCREEN_NAME_SURPLUS,
//            SCREEN_NAME_FINANCIAL_RATIOS,
            SCREEN_NAME_LOAN_PROPOSAL
    };

    // TODO: LOAN PROPOSAL SCREEN NAMES AHL
    public static String[] LOAN_PROPOSAL_PD_TAB_SCREEN_NAMES_AHL = {
            SCREEN_NAME_SUMMARY_LOAN_PROPOSAL,
            SCREEN_NAME_SURPLUS,
            SCREEN_NAME_FINANCIAL_RATIOS,
            SCREEN_NAME_LOAN_PROPOSAL
    };

    public static Fragment getFragment(String screenName, String screenNo, String loanType, String clientId,
                                       String projectId, String productId, String moduleType, String userId) {
        Fragment fragment = SampleFragment.newInstance(loanType, clientId, projectId, productId, SCREEN_NO_APPLICANT_KYC,
                SCREEN_NAME_APPLICANT_KYC, userId, moduleType);
        if (screenName.equalsIgnoreCase(SCREEN_NAME_APPLICANT_KYC)) {
            fragment = ApplicantKYCFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_APPLICANT_KYC, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_CO_APPLICANT_KYC)) {
            fragment = CoApplicantKYCFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_CO_APPLICANT_KYC, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_ADDRESS_DETAIL)) {
            fragment = AddressDetailsFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_ADDRESS_DETAIL, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_APPLICANT_SIGNATURE)) {
            fragment = ApplicantSignatureFragmant.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_APPLICANT_SIGNATURE, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_PERSONAL_DETAIL)) {
            fragment = PersonalDetailFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_PERSONAL_DETAIL, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_SOCIO_ECONOMIC_DETAIL)) {
            fragment = SocioEconomicDetailFragment.newInstance(loanType, clientId, projectId, productId,
                    screenNo, SCREEN_NAME_SOCIO_ECONOMIC_DETAIL, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_COAPPLICANT_PERSONAL_DETAIL)) {
            fragment = CoApplicantPersonalDetailFragment.newInstance(loanType, clientId, projectId, productId,
                    screenNo, SCREEN_NAME_COAPPLICANT_PERSONAL_DETAIL, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_COAPPLICANT_ADDRESS_DETAIL)) {
            fragment = CoApplicantAddressDetailFragment.newInstance(loanType, clientId, projectId, productId,
                    screenNo, SCREEN_NAME_COAPPLICANT_ADDRESS_DETAIL, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_BUSINESS_ENTITY)) {
            fragment = BusinessEntityFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_BUSINESS_ENTITY, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_BUSINESS_PROFILE)) {
            fragment = BusinessEntityFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_BUSINESS_PROFILE, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_BUSINESS_PROOF)) {
            fragment = BusinessProofFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_BUSINESS_PROOF, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_BUSINESS_ADDRESS_PROOF)) {
            fragment = BusinessAddressProofFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_BUSINESS_ADDRESS_PROOF, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_SALARY_PROFILE)) {
            fragment = SalaryEntityFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_SALARY_PROFILE, userId, moduleType); // TODO: AHL
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_OFFICE_ADDRESS_PROOF)) {
            fragment = OfficeAddressProofFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_OFFICE_ADDRESS_PROOF, userId, moduleType); // TODO: AHL
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_BANK_DETAILS)) {
            fragment = BankDetailsFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_BANK_DETAILS, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_STUDENT_DETAILS)) {
            fragment = StudentDetailsFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_STUDENT_DETAILS, userId, moduleType);
        }else if (screenName.equalsIgnoreCase(SCREEN_NAME_APPLICANT_PAN_DETAILS)) {
            fragment = ApplicantPANDetailsFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_APPLICANT_PAN_DETAILS, userId, moduleType);
        }else if (screenName.equalsIgnoreCase(SCREEN_NAME_CO_APPLICANT_PAN)) {
            fragment = CoApplicantPANDetailsFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_CO_APPLICANT_PAN, userId, moduleType);
        }else if (screenName.equalsIgnoreCase(SCREEN_NAME_COURSE_DETAILS)) {
            fragment = CourseDetailsFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_COURSE_DETAILS, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_NOMINEE_DETAIL)) {
            fragment = NomineeDetailFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_NOMINEE_DETAIL, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_APPLICANT_LOAN_PROPOSAL)) {
            fragment = ApplicantLoanProposalFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_APPLICANT_LOAN_PROPOSAL, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_APPLICANT_USED_CAR_DETAIL)) {
            fragment = UsedCarDetailFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_APPLICANT_USED_CAR_DETAIL, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_APPLICANT_TWO_WHEELER_DETAIL)) {
            fragment = TwoWheelerFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_APPLICANT_TWO_WHEELER_DETAIL, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_COAPPLICANT_NOMINEE_DETAIL)) {
            fragment = CoApplicantNomineeDetailFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_COAPPLICANT_NOMINEE_DETAIL, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_REFERENCE_CHECK)) {
            fragment = ReferenceCheckFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_REFERENCE_CHECK, userId, moduleType);
        }else if (screenName.equalsIgnoreCase(SCREEN_NAME_CPV)) {
          fragment = CPVFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                  SCREEN_NAME_CPV, userId, moduleType);
        }else if (screenName.equalsIgnoreCase(SCREEN_NAME_POSIDEX)) {
            fragment = PosidexFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_POSIDEX, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_DELINQUENCY)) {
          fragment = DeliquencyFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                  SCREEN_NAME_DELINQUENCY, userId, moduleType);
        }else if (screenName.equalsIgnoreCase(SCREEN_NAME_DEDUPE)) {
          fragment = DEDUPEFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                  SCREEN_NAME_DEDUPE, userId, moduleType);
        }else if (screenName.equalsIgnoreCase(SCREEN_NAME_RAMP)) {
          fragment = RampFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                  SCREEN_NAME_RAMP, userId, moduleType);
        }else if (screenName.equalsIgnoreCase(SCREEN_NAME_RAT)) {
          fragment = RATFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                  SCREEN_NAME_RAT, userId, moduleType);
        }else if (screenName.equalsIgnoreCase(SCREEN_NAME_APPLICANT_REGULATORY_FIELDS)) {
          fragment = ApplicantRegulatoryFieldsFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                  SCREEN_NAME_APPLICANT_REGULATORY_FIELDS, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_DNCR_APPLICANT)) {
          fragment = DNCRApplicantFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                  SCREEN_NAME_DNCR_APPLICANT, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_DOCUMENT_UPLOAD)) {
            fragment = DocumentUploadFragmentNew.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_DOCUMENT_UPLOAD, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_OTP_VERIFICATION)) {
            fragment = OTPVerificationFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_OTP_VERIFICATION, userId, moduleType);
        }else if (screenName.equalsIgnoreCase(SCREEN_NAME_HUNTER)) {
          fragment = HunterFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                  SCREEN_NAME_HUNTER, userId, moduleType);
        }else if (screenName.equalsIgnoreCase(SCREEN_NAME_GENERATE_CIBIL)) {
            fragment = GenerateCIBILFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_OTP_VERIFICATION, userId, moduleType);
        }else if (screenName.equalsIgnoreCase(SCREEN_NAME_ENACH)) {
          fragment = ENachFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                  SCREEN_NAME_ENACH, userId, moduleType);
        }else if (screenName.equalsIgnoreCase(SCREEN_NAME_ESIGN_ESTAMP)) {
          fragment = EsignEstampFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                  SCREEN_NAME_ESIGN_ESTAMP, userId, moduleType);
        }/*else if (screenName.equalsIgnoreCase(SCREEN_NAME_DIGITAL_DOCS)) {
          fragment = DigitalDocFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                  SCREEN_NAME_DIGITAL_DOCS, userId, moduleType);
        }*/ else if (screenName.equalsIgnoreCase(SCREEN_NAME_OTP_AUTHENTICAION)) {
            fragment = OTPAuthenticationFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_OTP_AUTHENTICAION, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_BALANCE_SHEET)) {
            fragment = BusinessLiabilitiesFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_BALANCE_SHEET, userId, moduleType);
        } else if (loanType.equalsIgnoreCase(LOAN_NAME_INDIVIDUAL) && screenName.equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS)) {
            fragment = HouseAssetsFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_HOUSE_ASSETS, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_PRODUCT)) {
            fragment = ProductFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_PRODUCT, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_BUSINESS_DEBTS)) {
            fragment = BusinessDebtsFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_BUSINESS_DEBTS, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_ADVANCES)) {
            fragment = AdvancesFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_ADVANCES, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_LIABILITIES)) {
            fragment = HouseLiabilitiesFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_LIABILITIES, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_HOUSE_INCOME)) {
            fragment = HouseIncomeFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_HOUSE_INCOME, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_FAMILY_MEMBER_INCOME)) {
            fragment = FamilyMemberFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_FAMILY_MEMBER_INCOME, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_EMPLOYER_VERIFICATION)) {
            fragment = EmployerVerificationFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_EMPLOYER_VERIFICATION, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_HOUSE_EXPENSES)) {
            fragment = HouseExpensesFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_HOUSE_EXPENSES, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_BUSINESS_INCOME)) {
            fragment = BusinessIncomeFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_BUSINESS_INCOME, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_DECLARED_SALES_WEEKLY)) {
            fragment = DeclaredSalesWeeklyFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_DECLARED_SALES_WEEKLY, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_DECLARED_SALES_YEARLY)) {
            fragment = DeclaredSalesYearlyFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_DECLARED_SALES_YEARLY, userId, moduleType);
        } else if (loanType.equalsIgnoreCase(LOAN_NAME_INDIVIDUAL) && screenName.equalsIgnoreCase(SCREEN_NAME_PRODUCT_ESTIMATE)) {
            fragment = ProductEstimateFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_PRODUCT_ESTIMATE, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_PURCHASE_ANALYSIS)) {
            fragment = PurchaseAnalysisFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_PURCHASE_ANALYSIS, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_PRODUCT_PURCHASE_ANALYSIS)) {
            fragment = PurchaseAnalysisByProductFragment.newInstance(loanType, clientId, projectId, productId,
                    screenNo, SCREEN_NAME_PRODUCT_PURCHASE_ANALYSIS, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_OPERATING_EXPENSE)) {
            fragment = OperatingExpenseFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_OPERATING_EXPENSE, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_SUMMARY)) {
            fragment = SummaryFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_SUMMARY, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_HOUSE_LIABILITIES) && loanType.equalsIgnoreCase(LOAN_NAME_INDIVIDUAL)) {
            fragment = HouseLiabilitiesFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_HOUSE_LIABILITIES, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS) && loanType.equalsIgnoreCase(LOAN_NAME_INDIVIDUAL)) {
            fragment = BusinessAssetsFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_BUSINESS_ASSETS, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_BUSINESS_LIABILITIES) && loanType.equalsIgnoreCase(LOAN_NAME_INDIVIDUAL)) {
            fragment = BusinessLiabilitiesFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_BUSINESS_LIABILITIES, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_LOAN_PROPOSAL)) {
            fragment = LoanProposalFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_LOAN_PROPOSAL, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_SURPLUS)) {
            fragment = SurPlusFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_SURPLUS, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_FINANCIAL_RATIOS)) {
            fragment = FinancialRatiosFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_FINANCIAL_RATIOS, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_SUMMARY_LOAN_PROPOSAL)) {
            fragment = LoanProposalSummaryFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_SUMMARY_LOAN_PROPOSAL, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_OBSERVATIONS)) {
            fragment = ObservationsFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_OBSERVATIONS, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_REFERENCES)) {
            fragment = ReferencesFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                    SCREEN_NAME_REFERENCES, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_COAPPLICANT_SOCIO_ECONOMIC_DETAIL)) {
            fragment = CoApplicantSocioEconomicDetail.newInstance(loanType, clientId, projectId, productId,
                    screenNo, SCREEN_NAME_COAPPLICANT_SOCIO_ECONOMIC_DETAIL, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_COLD_CALLING)) {
            // TODO: cold calling fragment
            fragment = ColdCallingFragment.newInstance(loanType, clientId, projectId, productId, screenNo, SCREEN_NAME_COLD_CALLING, userId, moduleType);
        }else if (screenName.equalsIgnoreCase(SCREEN_NAME_LEAD)&& loanType.equalsIgnoreCase(LOAN_NAME_MSME)) {
            fragment = LeadSummaryFragment.newInstance(loanType, clientId, projectId, productId, screenNo, SCREEN_NAME_LEAD, userId, moduleType);
        }else if (screenName.equalsIgnoreCase(SCREEN_NAME_SALES_TOOL)&& loanType.equalsIgnoreCase(LOAN_NAME_MSME)) {
            fragment = SalesToolFragment.newInstance(loanType, clientId, projectId, productId, screenNo, SCREEN_NAME_SALES_TOOL, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_LEAD)&& loanType.equalsIgnoreCase(LOAN_NAME_PHL)) {
            fragment = LeadSummaryFragment.newInstance(loanType, clientId, projectId, productId, screenNo, SCREEN_NAME_LEAD, userId, moduleType);
        }else if (screenName.equalsIgnoreCase(SCREEN_NAME_LEAD)&& loanType.equalsIgnoreCase(LOAN_NAME_EL)) {
            fragment = LeadSummaryFragment.newInstance(loanType, clientId, projectId, productId, screenNo, SCREEN_NAME_LEAD, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_LEAD)&& loanType.equalsIgnoreCase(LOAN_NAME_AHL)) {
            fragment = LeadSummaryFragment.newInstance(loanType, clientId, projectId, productId, screenNo, SCREEN_NAME_LEAD, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_LEAD)&& loanType.equalsIgnoreCase(LOAN_NAME_INDIVIDUAL)) {
            fragment = LeadFragment.newInstance(loanType, clientId, projectId, productId, screenNo, SCREEN_NAME_LEAD, userId, moduleType);
        }else if (screenName.equalsIgnoreCase(SCREEN_NAME_LEAD)) {
            fragment = LeadFragment.newInstance(loanType, clientId, projectId, productId, screenNo, SCREEN_NAME_LEAD, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_COLLECTION)) {
            // TODO: collection fragment
            fragment = CollectionFragment.newInstance(loanType, clientId, projectId, productId, screenNo, SCREEN_NAME_COLLECTION, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_CO_APPLICANT_BANK_DETAILS)) {
            fragment = CoApplicantBankDetailsFragment.newInstance(loanType, clientId, projectId, productId,
                    screenNo, SCREEN_NAME_CO_APPLICANT_BANK_DETAILS, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_GUARANTOR_DETAILS)) {
          fragment = GuarantorDetailsFragment.newInstance(loanType, clientId, projectId, productId,
                  screenNo, SCREEN_NAME_GUARANTOR_DETAILS, userId, moduleType);
        }else if (screenName.equalsIgnoreCase(SCREEN_NAME_CO_APPLICANT_REGULATORY_FIELDS)) {
            fragment = CoApplicantRegulatoryFieldsFragment.newInstance(loanType, clientId, projectId, productId,
                    screenNo, SCREEN_NAME_CO_APPLICANT_REGULATORY_FIELDS, userId, moduleType);
        }else if (screenName.equalsIgnoreCase(SCREEN_NAME_DNCR_APPLICANT)) {
          fragment = DNCRApplicantFragment.newInstance(loanType, clientId, projectId, productId, screenNo,
                  SCREEN_NAME_DNCR_APPLICANT, userId, moduleType);
        }
        // ----------------CO APPLICANT FRAGMENTS---------ABOVE
        else if (screenName.equalsIgnoreCase(SCREEN_NAME_GENERAL_INCOME)) {
            fragment = GeneralIncomeFragment.newInstance(loanType, clientId, projectId, productId,
                    screenNo, screenName, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_OTHER_INCOME_SOURCE)) {
            fragment = OtherIncomeSourceFragment.newInstance(loanType, clientId, projectId, productId,
                    screenNo, screenName, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_HOUSE_INCOME_SUMMARY)) {
            fragment = HouseIncomeSummaryFragment.newInstance(loanType, clientId, projectId, productId,
                    screenNo, screenName, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_BANKING_HISTORY_MSME)) {
            fragment = BankingHistoryFragment.newInstance(loanType, clientId, projectId, productId,
                    screenNo, screenName, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_MONTHLY_TRANSACTION_DETAIL_MSME)) {
            fragment = MonthlyTransactionDetailFragment.newInstance(loanType, clientId, projectId, productId,
                    screenNo, screenName, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_SALES_BILLS_MSME)) {
            fragment = SalesBillsFragment.newInstance(loanType, clientId, projectId, productId,
                    screenNo, screenName, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_SALES_BILLS_DETAIL_MSME)) {
            fragment = SalesBillsDetailFragment.newInstance(loanType, clientId, projectId, productId,
                    screenNo, screenName, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_PURCHASE_BILLS_MSME)) {
            fragment = PurchaseBillsFragment.newInstance(loanType, clientId, projectId, productId,
                    screenNo, screenName, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_PURCHASE_BILLS_DETAIL_MSME)) {
            fragment = PurchaseBillsDetailFragment.newInstance(loanType, clientId, projectId, productId,
                    screenNo, screenName, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_GST_MSME)) {
            fragment = GSTFragment.newInstance(loanType, clientId, projectId, productId,
                    screenNo, screenName, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_ITR_MSME)) {
            fragment = ITRFragment.newInstance(loanType, clientId, projectId, productId,
                    screenNo, screenName, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_ITR_DETAIL_MSME)) {
            fragment = ITRDetailFragment.newInstance(loanType, clientId, projectId, productId,
                    screenNo, screenName, userId, moduleType);
        } else if (loanType.equalsIgnoreCase(LOAN_NAME_MSME) && screenName.equalsIgnoreCase(SCREEN_NAME_PRODUCT_ESTIMATE_MSME)) {
            fragment = ProductEstimateMSMEFragment.newInstance(loanType, clientId, projectId, productId,
                    screenNo, screenName, userId, moduleType);
        } else if (loanType.equalsIgnoreCase(LOAN_NAME_MSME) && screenName.equalsIgnoreCase(SCREEN_NAME_PRODUCT_ESTIMATE_DETAIL_MSME)) {
            fragment = ProductEstimateDetailFragment.newInstance(loanType, clientId, projectId, productId,
                    screenNo, screenName, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_SERVICE_ESTIMATE_MSME)) {
            fragment = ServiceEstimateFragment.newInstance(loanType, clientId, projectId, productId,
                    screenNo, screenName, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_SERVICE_ESTIMATE_DETAIL_MSME)) {
            fragment = ServiceEstimateDetailFragment.newInstance(loanType, clientId, projectId, productId,
                    screenNo, screenName, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_DIRECT_BUSINESS_EXPENSE_MSME)) {
            fragment = DirectBusinessExpenseFragment.newInstance(loanType, clientId, projectId, productId,
                    screenNo, screenName, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_SERVICE_RAW_MATERIAL_MSME)) {
            fragment = ServiceRawMaterialFragment.newInstance(loanType, clientId, projectId, productId,
                    screenNo, screenName, userId, moduleType);
        } else if (screenName.equalsIgnoreCase(SCREEN_NAME_PRODUCT_RAW_MATERIAL_MSME)) {
            fragment = ProductRawMaterialFragment.newInstance(loanType, clientId, projectId, productId,
                    screenNo, screenName, userId, moduleType);
        } else if (loanType.equalsIgnoreCase(LOAN_NAME_MSME) && screenName.equalsIgnoreCase(SCREEN_NAME_HOUSE_LIABILITIES_MSME)) {
            fragment = HouseLiabilitiesMsmeFragment.newInstance(loanType, clientId, projectId, productId,
                    screenNo, screenName, userId, moduleType);
        } else if (loanType.equalsIgnoreCase(LOAN_NAME_MSME) && screenName.equalsIgnoreCase(SCREEN_NAME_BUSINESS_LIABILITIES_MSME)) {
            fragment = BusinessLiabilitiesMsmeFragment.newInstance(loanType, clientId, projectId, productId,
                    screenNo, screenName, userId, moduleType);
        } else if (loanType.equalsIgnoreCase(LOAN_NAME_MSME) && screenName.equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_MSME)) {
            fragment = BusinessAssertsMsmeFragment.newInstance(loanType, clientId, projectId, productId,
                    screenNo, screenName, userId, moduleType);
        } else if (loanType.equalsIgnoreCase(LOAN_NAME_MSME) && screenName.equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_MSME)) {
            fragment = HouseAssetsMsmeFragment.newInstance(loanType, clientId, projectId, productId,
                    screenNo, screenName, userId, moduleType);
        } else if (loanType.equalsIgnoreCase(LOAN_NAME_MSME) && screenName.equalsIgnoreCase(SCREEN_NAME_OPERATING_EXPENSE_MSME)) {
            fragment = OperatingExpenseFragment.newInstance(loanType, clientId, projectId, productId,
                    screenNo, screenName, userId, moduleType);
        } else if (loanType.equalsIgnoreCase(LOAN_NAME_MSME) && screenName.equalsIgnoreCase(SCREEN_NAME_INCOME_ASSESSMENT_SUMMARY_MSME)) {
            fragment = IncomeAssessmentSummaryMsmeFragment.newInstance(loanType, clientId, projectId, productId,
                    screenNo, screenName, userId, moduleType);
        } else if (loanType.equalsIgnoreCase(LOAN_NAME_MSME) && screenName.equalsIgnoreCase(SCREEN_NAME_LOAN_INFORMATION_MSME)) {
            fragment = LoanInformationMsmeFragment.newInstance(loanType, clientId, projectId, productId,
                    screenNo, screenName, userId, moduleType);
        } else if (loanType.equalsIgnoreCase(LOAN_NAME_MSME) && screenName.equalsIgnoreCase(SCREEN_NAME_CASH_FLOW_SUMMARY_MSME)) {
            fragment = CashFlowSummaryMSMEFragment.newInstance(loanType, clientId, projectId, productId,
                    screenNo, screenName, userId, moduleType);
        } else if (loanType.equalsIgnoreCase(LOAN_NAME_MSME) && screenName.equalsIgnoreCase(SCREEN_NAME_LOAN_APPROVAL_MSME)) {
            fragment = LoanApprovalMSMEFragment.newInstance(loanType, clientId, projectId, productId,
                    screenNo, screenName, userId, moduleType);
        } else if (loanType.equalsIgnoreCase(LOAN_NAME_EL) && screenName.equalsIgnoreCase(SCREEN_NAME_GUARANTOR_DETAILS)) {
          fragment = GuarantorDetailsFragment.newInstance(loanType, clientId, projectId, productId,
                  screenNo, screenName, userId, moduleType);
        }
        return fragment;
    }

    public static ScreenDetailsDTO getScreenDetailsByScreenId(String screenId, String moduleType){
        ScreenDetailsDTO screenDetailsDTO = null;
        try{
            if( TextUtils.isEmpty(screenId)){
                return screenDetailsDTO;
            }else {
                screenDetailsDTO = new ScreenDetailsDTO();
            }

            if(screenId.equalsIgnoreCase(SCREEN_NO_APPLICANT_KYC_IL)){
                screenDetailsDTO.setScreenId(screenId);
                screenDetailsDTO.setScreenName(SCREEN_NAME_APPLICANT_KYC);
                screenDetailsDTO.setProductId(PRODUCT_ID_IL);
                screenDetailsDTO.setModuleType(MODULE_TYPE_APPLICANT);
            }else if(screenId.equalsIgnoreCase(SCREEN_NO_ADDRESS_DETAIL_IL)){
                screenDetailsDTO.setScreenId(screenId);
                screenDetailsDTO.setScreenName(SCREEN_NAME_ADDRESS_DETAIL);
                screenDetailsDTO.setProductId(PRODUCT_ID_IL);
                screenDetailsDTO.setModuleType(MODULE_TYPE_APPLICANT);
            }else if(screenId.equalsIgnoreCase(SCREEN_NO_PERSONAL_DETAIL_IL)){
                screenDetailsDTO.setScreenId(screenId);
                screenDetailsDTO.setScreenName(SCREEN_NAME_PERSONAL_DETAIL);
                screenDetailsDTO.setProductId(PRODUCT_ID_IL);
                screenDetailsDTO.setModuleType(MODULE_TYPE_APPLICANT);
            }else if(screenId.equalsIgnoreCase(SCREEN_NO_SOCIO_ECONOMIC_DETAIL_IL)){
                screenDetailsDTO.setScreenId(screenId);
                screenDetailsDTO.setScreenName(SCREEN_NAME_SOCIO_ECONOMIC_DETAIL);
                screenDetailsDTO.setProductId(PRODUCT_ID_IL);
                screenDetailsDTO.setModuleType(MODULE_TYPE_APPLICANT);
            }else if(screenId.equalsIgnoreCase(SCREEN_NO_BANK_DETAILS_IL)){
                screenDetailsDTO.setScreenId(screenId);
                screenDetailsDTO.setScreenName(SCREEN_NAME_BANK_DETAILS);
                screenDetailsDTO.setProductId(PRODUCT_ID_IL);
                screenDetailsDTO.setModuleType(MODULE_TYPE_APPLICANT);
            }else if(screenId.equalsIgnoreCase(SCREEN_NO_REFERENCE_CHECK_IL)){
                screenDetailsDTO.setScreenId(screenId);
                screenDetailsDTO.setScreenName(SCREEN_NAME_REFERENCE_CHECK);
                screenDetailsDTO.setProductId(PRODUCT_ID_IL);
                screenDetailsDTO.setModuleType(MODULE_TYPE_APPLICANT);
            }else if(screenId.equalsIgnoreCase(SCREEN_NO_APPLICANT_REGULATORY_FIELDS)){
              screenDetailsDTO.setScreenId(screenId);
              screenDetailsDTO.setScreenName(SCREEN_NAME_APPLICANT_REGULATORY_FIELDS);
              screenDetailsDTO.setProductId(PRODUCT_ID_IL);
              screenDetailsDTO.setModuleType(MODULE_TYPE_APPLICANT);
            } else if(screenId.equalsIgnoreCase(SCREEN_NO_DNCR_APPLICANT)){
              screenDetailsDTO.setScreenId(screenId);
              screenDetailsDTO.setScreenName(SCREEN_NAME_DNCR_APPLICANT);
              screenDetailsDTO.setProductId(PRODUCT_ID_IL);
              screenDetailsDTO.setModuleType(MODULE_TYPE_APPLICANT);
            }

            // TODO: CoApplicant IL

            else if(screenId.equalsIgnoreCase(SCREEN_NO_CO_APPLICANT_KYC_IL)){
                screenDetailsDTO.setScreenId(screenId);
                screenDetailsDTO.setScreenName(SCREEN_NAME_CO_APPLICANT_KYC);
                screenDetailsDTO.setProductId(PRODUCT_ID_IL);
                screenDetailsDTO.setModuleType(MODULE_TYPE_CO_APPLICANT);
            }else if(screenId.equalsIgnoreCase(SCREEN_NO_COAPPLICANT_ADDRESS_DETAIL_IL)){
                screenDetailsDTO.setScreenId(screenId);
                screenDetailsDTO.setScreenName(SCREEN_NAME_COAPPLICANT_ADDRESS_DETAIL);
                screenDetailsDTO.setProductId(PRODUCT_ID_IL);
                screenDetailsDTO.setModuleType(MODULE_TYPE_CO_APPLICANT);
            }else if(screenId.equalsIgnoreCase(SCREEN_NO_COAPPLICANT_PERSONAL_DETAIL_IL)){
                screenDetailsDTO.setScreenId(screenId);
                screenDetailsDTO.setScreenName(SCREEN_NAME_COAPPLICANT_PERSONAL_DETAIL);
                screenDetailsDTO.setProductId(PRODUCT_ID_IL);
                screenDetailsDTO.setModuleType(MODULE_TYPE_CO_APPLICANT);
            }else if(screenId.equalsIgnoreCase(SCREEN_NO_COAPPLICANT_SOCIO_EOCONOMIC_DETAIL_IL)){
                screenDetailsDTO.setScreenId(screenId);
                screenDetailsDTO.setScreenName(SCREEN_NAME_COAPPLICANT_SOCIO_ECONOMIC_DETAIL);
                screenDetailsDTO.setProductId(PRODUCT_ID_IL);
                screenDetailsDTO.setModuleType(MODULE_TYPE_CO_APPLICANT);
            }else if(screenId.equalsIgnoreCase(SCREEN_NO_CO_APPLICANT_BANK_DETAILS_IL)){
                screenDetailsDTO.setScreenId(screenId);
                screenDetailsDTO.setScreenName(SCREEN_NAME_CO_APPLICANT_BANK_DETAILS);
                screenDetailsDTO.setProductId(PRODUCT_ID_IL);
                screenDetailsDTO.setModuleType(MODULE_TYPE_CO_APPLICANT);
            }else if(screenId.equalsIgnoreCase(SCREEN_NO_CO_APPLICANT_REGULATORY_FIELDS)){
                screenDetailsDTO.setScreenId(screenId);
                screenDetailsDTO.setScreenName(SCREEN_NAME_CO_APPLICANT_REGULATORY_FIELDS);
                screenDetailsDTO.setProductId(PRODUCT_ID_IL);
                screenDetailsDTO.setModuleType(MODULE_TYPE_CO_APPLICANT);
            } else if(screenId.equalsIgnoreCase(SCREEN_NO_DNCR_APPLICANT)){
              screenDetailsDTO.setScreenId(screenId);
              screenDetailsDTO.setScreenName(SCREEN_NAME_DNCR_APPLICANT);
              screenDetailsDTO.setProductId(PRODUCT_ID_IL);
              screenDetailsDTO.setModuleType(MODULE_TYPE_CO_APPLICANT);
            }

            //TODO: GUARANTOR DETAILS FOR EL
            else if(screenId.equalsIgnoreCase(SCREEN_NO_GUARANTOR_EL)) {
              screenDetailsDTO.setScreenId(screenId);
              screenDetailsDTO.setScreenName(SCREEN_NAME_GUARANTOR_DETAILS);
              screenDetailsDTO.setProductId(PRODUCT_ID_EL);
              screenDetailsDTO.setModuleType(MODULE_TYPE_GUARANTOR);
            }

            // TODO: BUSINESS PROOF IL
            else if(screenId.equalsIgnoreCase(SCREEN_NO_BUSINESS_PROFILE_IL)){
                screenDetailsDTO.setScreenId(screenId);
                screenDetailsDTO.setScreenName(SCREEN_NAME_BUSINESS_PROFILE);
                screenDetailsDTO.setProductId(PRODUCT_ID_IL);
                screenDetailsDTO.setModuleType(MODULE_TYPE_BUSINESS);
            }else if(screenId.equalsIgnoreCase(SCREEN_NO_BUSINESS_PROOF_IL)){
                screenDetailsDTO.setScreenId(screenId);
                screenDetailsDTO.setScreenName(SCREEN_NAME_BUSINESS_PROOF);
                screenDetailsDTO.setProductId(PRODUCT_ID_IL);
                screenDetailsDTO.setModuleType(MODULE_TYPE_BUSINESS);
            }else if(screenId.equalsIgnoreCase(SCREEN_NO_BUSINESS_ADDRESS_PROOF_IL)){
                screenDetailsDTO.setScreenId(screenId);
                screenDetailsDTO.setScreenName(SCREEN_NAME_BUSINESS_ADDRESS_PROOF);
                screenDetailsDTO.setProductId(PRODUCT_ID_IL);
                screenDetailsDTO.setModuleType(MODULE_TYPE_BUSINESS);
            }
             // START tusar added this code
            else if (screenId.equalsIgnoreCase(SCREEN_NO_APPLICANT_KYC_EL)) {
              screenDetailsDTO.setScreenId(screenId);
              screenDetailsDTO.setScreenName(SCREEN_NAME_APPLICANT_KYC);
              screenDetailsDTO.setProductId(PRODUCT_ID_TWL);
              screenDetailsDTO.setModuleType(MODULE_TYPE_APPLICANT);
            }

            else if (screenId.equalsIgnoreCase(SCREEN_NO_CO_APPLICANT_KYC_EL)) {
              screenDetailsDTO.setScreenId(screenId);
              screenDetailsDTO.setScreenName(SCREEN_NAME_CO_APPLICANT_KYC);
              screenDetailsDTO.setProductId(PRODUCT_ID_TWL);
              screenDetailsDTO.setModuleType("CoApplicant 1");
            }
///////EL product Condition/////////////////
            else if (screenId.equalsIgnoreCase(SCREEN_NO_APPLICANT_KYC_EL)) {
              screenDetailsDTO.setScreenId(screenId);
              screenDetailsDTO.setScreenName(SCREEN_NAME_APPLICANT_KYC);
              screenDetailsDTO.setProductId(PRODUCT_ID_EL);
              screenDetailsDTO.setModuleType(MODULE_TYPE_APPLICANT);
            }

            else if (screenId.equalsIgnoreCase(SCREEN_NO_CO_APPLICANT_KYC_EL)) {
              screenDetailsDTO.setScreenId(screenId);
              screenDetailsDTO.setScreenName(SCREEN_NAME_CO_APPLICANT_KYC);
              screenDetailsDTO.setProductId(PRODUCT_ID_EL);
              screenDetailsDTO.setModuleType("CoApplicant 1");
            }
          //END tusar added this code

            // TODO: LOAN PROPOSAL WITH NOMINEE DETAILS

            if(screenId.equalsIgnoreCase(SCREEN_NO_LOAN_PROPOSAL_IL)){
                screenDetailsDTO.setScreenId(screenId);
                screenDetailsDTO.setScreenName(SCREEN_NAME_APPLICANT_LOAN_PROPOSAL);
                screenDetailsDTO.setProductId(PRODUCT_ID_IL);
                screenDetailsDTO.setModuleType(MODULE_TYPE_LOAN_PROPOSAL_WITH_NOMINEE);
            }else if(screenId.equalsIgnoreCase(SCREEN_NO_NOMINEE_DETAIL_IL)){
                screenDetailsDTO.setScreenId(screenId);
                screenDetailsDTO.setScreenName(SCREEN_NAME_NOMINEE_DETAIL);
                screenDetailsDTO.setProductId(PRODUCT_ID_IL);
                screenDetailsDTO.setModuleType(MODULE_TYPE_LOAN_PROPOSAL_WITH_NOMINEE);
            }

            // TODO: DOCUMENT UPLOAD
            if(screenId.equalsIgnoreCase(SCREEN_N0_DOCUMENT_UPLOAD_MSME)){
                screenDetailsDTO.setScreenId(screenId);
                screenDetailsDTO.setScreenName(SCREEN_NAME_DOCUMENT_UPLOAD);
                screenDetailsDTO.setProductId(PRODUCT_ID_IL);
                screenDetailsDTO.setModuleType(MODULE_TYPE_DOCUMENTS);
            }
            // TODO: MSME screens
            if(screenId.equalsIgnoreCase(SCREEN_NO_APPLICANT_KYC_MSME)){
                screenDetailsDTO.setScreenId(screenId);
                screenDetailsDTO.setScreenName(SCREEN_NAME_APPLICANT_KYC);
                screenDetailsDTO.setProductId(PRODUCT_ID_MSME);
                screenDetailsDTO.setModuleType(MODULE_TYPE_APPLICANT);
            }else if(screenId.equalsIgnoreCase(SCREEN_NO_ADDRESS_DETAIL_MSME)){
                screenDetailsDTO.setScreenId(screenId);
                screenDetailsDTO.setScreenName(SCREEN_NAME_ADDRESS_DETAIL);
                screenDetailsDTO.setProductId(PRODUCT_ID_MSME);
                screenDetailsDTO.setModuleType(MODULE_TYPE_APPLICANT);
            }else if(screenId.equalsIgnoreCase(SCREEN_NO_PERSONAL_DETAIL_MSME)){
                screenDetailsDTO.setScreenId(screenId);
                screenDetailsDTO.setScreenName(SCREEN_NAME_PERSONAL_DETAIL);
                screenDetailsDTO.setProductId(PRODUCT_ID_MSME);
                screenDetailsDTO.setModuleType(MODULE_TYPE_APPLICANT);
            }else if(screenId.equalsIgnoreCase(SCREEN_NO_SOCIO_ECONOMIC_DETAIL_MSME)){
                screenDetailsDTO.setScreenId(screenId);
                screenDetailsDTO.setScreenName(SCREEN_NAME_SOCIO_ECONOMIC_DETAIL);
                screenDetailsDTO.setProductId(PRODUCT_ID_MSME);
                screenDetailsDTO.setModuleType(MODULE_TYPE_APPLICANT);
            }else if(screenId.equalsIgnoreCase(SCREEN_NO_BANK_DETAILS_MSME)){
                screenDetailsDTO.setScreenId(screenId);
                screenDetailsDTO.setScreenName(SCREEN_NAME_BANK_DETAILS);
                screenDetailsDTO.setProductId(PRODUCT_ID_MSME);
                screenDetailsDTO.setModuleType(MODULE_TYPE_APPLICANT);
            }else if(screenId.equalsIgnoreCase(SCREEN_NO_REFERENCE_CHECK_MSME)){
                screenDetailsDTO.setScreenId(screenId);
                screenDetailsDTO.setScreenName(SCREEN_NAME_REFERENCE_CHECK);
                screenDetailsDTO.setProductId(PRODUCT_ID_MSME);
                screenDetailsDTO.setModuleType(MODULE_TYPE_APPLICANT);
            }

            // TODO: CoApplicant MSME

            else if(screenId.equalsIgnoreCase(SCREEN_NO_CO_APPLICANT_KYC_MSME)){
                screenDetailsDTO.setScreenId(screenId);
                screenDetailsDTO.setScreenName(SCREEN_NAME_CO_APPLICANT_KYC);
                screenDetailsDTO.setProductId(PRODUCT_ID_MSME);
                screenDetailsDTO.setModuleType(MODULE_TYPE_CO_APPLICANT);
            }else if(screenId.equalsIgnoreCase(SCREEN_NO_COAPPLICANT_ADDRESS_DETAIL_MSME)){
                screenDetailsDTO.setScreenId(screenId);
                screenDetailsDTO.setScreenName(SCREEN_NAME_COAPPLICANT_ADDRESS_DETAIL);
                screenDetailsDTO.setProductId(PRODUCT_ID_MSME);
                screenDetailsDTO.setModuleType(MODULE_TYPE_CO_APPLICANT);
            }else if(screenId.equalsIgnoreCase(SCREEN_NO_COAPPLICANT_PERSONAL_DETAIL_MSME)){
                screenDetailsDTO.setScreenId(screenId);
                screenDetailsDTO.setScreenName(SCREEN_NAME_COAPPLICANT_PERSONAL_DETAIL);
                screenDetailsDTO.setProductId(PRODUCT_ID_MSME);
                screenDetailsDTO.setModuleType(MODULE_TYPE_CO_APPLICANT);
            }else if(screenId.equalsIgnoreCase(SCREEN_NO_COAPPLICANT_SOCIO_EOCONOMIC_DETAIL_MSME)){
                screenDetailsDTO.setScreenId(screenId);
                screenDetailsDTO.setScreenName(SCREEN_NAME_COAPPLICANT_SOCIO_ECONOMIC_DETAIL);
                screenDetailsDTO.setProductId(PRODUCT_ID_MSME);
                screenDetailsDTO.setModuleType(MODULE_TYPE_CO_APPLICANT);
            }else if(screenId.equalsIgnoreCase(SCREEN_NO_CO_APPLICANT_BANK_DETAILS_MSME)){
                screenDetailsDTO.setScreenId(screenId);
                screenDetailsDTO.setScreenName(SCREEN_NAME_CO_APPLICANT_BANK_DETAILS);
                screenDetailsDTO.setProductId(PRODUCT_ID_MSME);
                screenDetailsDTO.setModuleType(MODULE_TYPE_CO_APPLICANT);
            }

            // TODO: BUSINESS PROOF MSME
            else if(screenId.equalsIgnoreCase(SCREEN_NO_BUSINESS_PROFILE_MSME)){
                screenDetailsDTO.setScreenId(screenId);
                screenDetailsDTO.setScreenName(SCREEN_NAME_BUSINESS_PROFILE);
                screenDetailsDTO.setProductId(PRODUCT_ID_MSME);
                screenDetailsDTO.setModuleType(MODULE_TYPE_BUSINESS);
            }else if(screenId.equalsIgnoreCase(SCREEN_NO_BUSINESS_PROOF_MSME)){
                screenDetailsDTO.setScreenId(screenId);
                screenDetailsDTO.setScreenName(SCREEN_NAME_BUSINESS_PROOF);
                screenDetailsDTO.setProductId(PRODUCT_ID_MSME);
                screenDetailsDTO.setModuleType(MODULE_TYPE_BUSINESS);
            }else if(screenId.equalsIgnoreCase(SCREEN_NO_BUSINESS_ADDRESS_PROOF_MSME)){
                screenDetailsDTO.setScreenId(screenId);
                screenDetailsDTO.setScreenName(SCREEN_NAME_BUSINESS_ADDRESS_PROOF);
                screenDetailsDTO.setProductId(PRODUCT_ID_MSME);
                screenDetailsDTO.setModuleType(MODULE_TYPE_BUSINESS);
            }

            // TODO: LOAN PROPOSAL WITH NOMINEE DETAILS MSME

            if(screenId.equalsIgnoreCase(SCREEN_NO_APPLICANT_LOAN_PROPOSAL_MSME)){
                screenDetailsDTO.setScreenId(screenId);
                screenDetailsDTO.setScreenName(SCREEN_NAME_APPLICANT_LOAN_PROPOSAL);
                screenDetailsDTO.setProductId(PRODUCT_ID_MSME);
                screenDetailsDTO.setModuleType(MODULE_TYPE_LOAN_PROPOSAL_WITH_NOMINEE);
            }else if(screenId.equalsIgnoreCase(SCREEN_NO_NOMINEE_DETAIL_MSME)){
                screenDetailsDTO.setScreenId(screenId);
                screenDetailsDTO.setScreenName(SCREEN_NAME_NOMINEE_DETAIL);
                screenDetailsDTO.setProductId(PRODUCT_ID_MSME);
                screenDetailsDTO.setModuleType(MODULE_TYPE_LOAN_PROPOSAL_WITH_NOMINEE);
            }

            // TODO: DOCUMENT UPLOAD MSME
            if(screenId.equalsIgnoreCase(SCREEN_N0_DOCUMENT_UPLOAD_MSME)){
                screenDetailsDTO.setScreenId(screenId);
                screenDetailsDTO.setScreenName(SCREEN_NAME_DOCUMENT_UPLOAD);
                screenDetailsDTO.setProductId(PRODUCT_ID_MSME);
                screenDetailsDTO.setModuleType(MODULE_TYPE_DOCUMENTS);
            }


            // TODO: JLG screens
            if(screenId.equalsIgnoreCase(SCREEN_NO_APPLICANT_KYC_JLG)){
                screenDetailsDTO.setScreenId(screenId);
                screenDetailsDTO.setScreenName(SCREEN_NAME_APPLICANT_KYC);
                screenDetailsDTO.setProductId(PRODUCT_ID_JLG);
                screenDetailsDTO.setModuleType(MODULE_TYPE_APPLICANT);
            }else if(screenId.equalsIgnoreCase(SCREEN_NO_PERSONAL_DETAIL_JLG)){
                screenDetailsDTO.setScreenId(screenId);
                screenDetailsDTO.setScreenName(SCREEN_NAME_PERSONAL_DETAIL);
                screenDetailsDTO.setProductId(PRODUCT_ID_JLG);
                screenDetailsDTO.setModuleType(MODULE_TYPE_APPLICANT);
            }else if(screenId.equalsIgnoreCase(SCREEN_NO_SOCIO_ECONOMIC_DETAIL_JLG)){
                screenDetailsDTO.setScreenId(screenId);
                screenDetailsDTO.setScreenName(SCREEN_NAME_SOCIO_ECONOMIC_DETAIL);
                screenDetailsDTO.setProductId(PRODUCT_ID_JLG);
                screenDetailsDTO.setModuleType(MODULE_TYPE_APPLICANT);
            }

            // TODO: LOAN PROPOSAL WITH NOMINEE DETAILS JLG
            if(screenId.equalsIgnoreCase(SCREEN_NO_APPLICANT_LOAN_PROPOSAL_JLG)){
                screenDetailsDTO.setScreenId(screenId);
                screenDetailsDTO.setScreenName(SCREEN_NAME_APPLICANT_LOAN_PROPOSAL);
                screenDetailsDTO.setProductId(PRODUCT_ID_JLG);
                screenDetailsDTO.setModuleType(MODULE_TYPE_LOAN_PROPOSAL_WITH_NOMINEE);
            }
            else if(screenId.equalsIgnoreCase(SCREEN_NO_NOMINEE_DETAILS_JLG)){
                screenDetailsDTO.setScreenId(screenId);
                screenDetailsDTO.setScreenName(SCREEN_NAME_NOMINEE_DETAIL);
                screenDetailsDTO.setProductId(PRODUCT_ID_JLG);
                screenDetailsDTO.setModuleType(MODULE_TYPE_LOAN_PROPOSAL_WITH_NOMINEE);
            }
            else if(screenId.equalsIgnoreCase(SCREEN_NO_ADDRESS_DETAIL_JLG)){
                screenDetailsDTO.setScreenId(screenId);
                screenDetailsDTO.setScreenName(SCREEN_NAME_ADDRESS_DETAIL);
                screenDetailsDTO.setProductId(PRODUCT_ID_JLG);
                screenDetailsDTO.setModuleType(MODULE_TYPE_LOAN_PROPOSAL_WITH_NOMINEE);
            }
            else if(screenId.equalsIgnoreCase(SCREEN_NO_BANK_DETAILS_JLG)){
                screenDetailsDTO.setScreenId(screenId);
                screenDetailsDTO.setScreenName(SCREEN_NAME_BANK_DETAILS);
                screenDetailsDTO.setProductId(PRODUCT_ID_JLG);
                screenDetailsDTO.setModuleType(MODULE_TYPE_LOAN_PROPOSAL_WITH_NOMINEE);
            }

            if(!TextUtils.isEmpty(moduleType)){
                screenDetailsDTO.setModuleType(moduleType);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return screenDetailsDTO;
    }

    // TODO: DOCUMENTS UPLOAD TAG NAMES
    public static final String TAG_APPLICANT_PHOTO = "ApplicantPhoto";
    public static final String TAG_HOUSE_PHOTO_1 = "HousePhoto1";
    public static final String TAG_HOUSE_PHOTO_2 = "HousePhoto2";
    public static final String TAG_HOUSE_PHOTO_3 = "HousePhoto3";
    public static final String TAG_ID_PROOF_FRONT = "IDProofFront";
    public static final String TAG_ID_PROOF_BACK = "IDProofBack";
    public static final String TAG_ADDRESS_PROOF = "AddressProof";
    public static final String TAG_BANK_STATEMENT = "BankStatement";

    public static String[] TAG_DOCUMENT_UPLOAD_NAMES = {
            TAG_APPLICANT_PHOTO,
            TAG_HOUSE_PHOTO_1,
            TAG_HOUSE_PHOTO_2,
            TAG_HOUSE_PHOTO_3,
            TAG_ID_PROOF_FRONT,
            TAG_ID_PROOF_BACK,
            TAG_ADDRESS_PROOF,
            TAG_BANK_STATEMENT
    };

    public static String[] DOCUMENT_UPLOAD_DISPLAY_NAMES = {
            "Add Applicant Photo",
            "Add House Photo 1",
            "Add House Photo 2",
            "Add House Photo 3",
            "Add ID Proof Front",
            "Add ID Proof Back",
            "Add Address Proof",
            "Add Bank Statement"
    };


    public static final String TAG_KYCIMAGEFRONT = "KYCImageFront";
    public static final String TAG_KYCIMAGEBACK = "KYCImageBack";
    public static final String TAG_MID = "MID";
    public static final String TAG_CUSTOMERPHOTO = "CustomerPhoto";
    public static final String TAG_CAKYCIMAGEFRONT = "CoapplicantKYCImageFront";
    public static final String TAG_CAKYCIMAGEBACK = "CoapplicantKYCImageBack";
    public static final String TAG_RDCONSENTFORMIMAGE = "RDConsentFormImage";
    public static final String TAG_POA_IMAGE = "POA";
    public static final String TAG_SEC_KYC_BACK_IMAGE = "SecondaryKYCBack";


    public static final String IMAGE_CAPTURE_KYC_ID_1 = "KYC ID 1";
    public static final String IMAGE_CAPTURE_KYC_ID_2 = "KYC ID 2";
    public static final String IMAGE_CAPTURE_KYC_ID_3 = "KYC ID 3";
    public static final String IMAGE_CAPTURE_KYC_ID_4 = "KYC ID 4";
    public static final String IMAGE_CAPTURE_KYC_ID_5 = "KYC ID 5";
    public static final String IMAGE_CAPTURE_PERMANENT_ADDRESS_IMAGE_1 = "Permanent Address Image 1";
    public static final String IMAGE_CAPTURE_PERMANENT_ADDRESS_IMAGE_2 = "Permanent Address Image 2";
    public static final String IMAGE_CAPTURE_PERMANENT_ADDRESS_IMAGE_3 = "Permanent Address Image 3";
    public static final String IMAGE_CAPTURE_PERMANENT_ADDRESS_IMAGE_4 = "Permanent Address Image 4";
    public static final String IMAGE_CAPTURE_PERMANENT_ADDRESS_IMAGE_5 = "Permanent Address Image 5";

    public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";
    public static final String DATE_FORMAT_DD_MM_YYYY = "dd/MM/yyyy";
    public static final String DATE_FORMAT_DD_MM_YYYY2 = "dd-MM-yyyy";
    public static final String DATE_FORMAT_MM_DD_YYYY = "MM-dd-yyyy";
    public static final String DATE_FORMAT_DDMMYYYYHHMM = "dd-MM-yyyy HH:mm";
    public static final String DATE_FORMAT_DDMMYYYYHHMM1 = "dd-MM-yyyy HH:mm a";
    public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String DATE_FORMAT_MMM_DD_YYYY = "MMM dd, yyyy";
    public static final String DATE_FORMAT_DD_MMM_YYYY = "dd MMM yyyy";
    public static final String DATE_FORMAT_DD_MMM_YY = "dd MMM yy";
    public static final String DATE_FORMAT_DD_MMM_YYYY2 = "dd-MMM-yyyy";
    public static final String DATE_FORMAT_MMM_DD_YYYY_HH_MM_SS = "MMM dd, yyyy HH:mm:ss";
    public static final String DATE_FORMAT_MMM_DD_YYYY_HH_MM_SS_SSS = "MMM dd, yyyy HH:mm:ss.SSS";
    public static final String DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_SSS = "yyyy/MM/dd HH:mm:ss.SSS";
    public static final String DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_SSS = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    public static final String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy/MM/dd HH:mm:ss";
    public static final String DATE_FORMAT_DDMMYYYY = "ddMMyyyyhhmmss";
    public static final String DATE_FORMAT_DDMMYYYYSMS = "ddMMyyyyhhmmssSSS";
    public static final String DATE_FORMAT_DD_MM_YYYY_1 = "dd/MM/yyyy";
    public static final String DATE_FORMAT_HH_MM_SS = "HH:mm a";
    public final static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_DDMMMYYYY = "ddMMMyyyy";
    public static final String DATE_FORMAT_DD_MM_YYYY_HH_MM_SS = "MM/dd/yyyy HH:mm:ss a";
    public static final String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_SSS2 = "yyyy-MM-dd HH:mm:ss:SSS";

    public static final String DUMMY_CENTER_MEETING_DATE = "2020-05-19";

    // TODO: NETWORK STATUS
    public static final int MOBILE_CONNECTION = 1;
    public static final int WIFI_CONNECTION = 2;
    public static final int NO_INTERNET_CONNECTION = 0;

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 1001;

    public static String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // tvName 0 to 999999
        SecureRandom rnd = new SecureRandom();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }

    public static HashMap<String, Object> setKeyValueForObject(RawDataTable rawDataTable) {
        HashMap<String, Object> rawDataHashMap = new HashMap<>();
        try {
            if (!TextUtils.isEmpty(rawDataTable.getRawdata())) {
                String rawData = rawDataTable.getRawdata();

                rawDataHashMap = App.createHashMapFromJsonString(rawData);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rawDataHashMap;
    }

    public static String removeSpace(String toLowerCase) {
        String withoutSpace = "";
        try {
            if (!TextUtils.isEmpty(toLowerCase)) {
                withoutSpace = toLowerCase.replaceAll(" ", "");
                withoutSpace = withoutSpace.toLowerCase();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return withoutSpace;
    }

    public static String getProofType(String kycType) {
        String proofType = "";
        try {
            if (!TextUtils.isEmpty(kycType)) {
                if (kycType.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_AADHAAR)) {
                    proofType = PROOF_TYPE_SPINNER_ITEM_ID_CUM_ADDRESS_PROOF;
                } else if (kycType.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_VID)) {
                    proofType = PROOF_TYPE_SPINNER_ITEM_ID_CUM_ADDRESS_PROOF;
                } else if (kycType.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_PAN_CARD)) {
                    proofType = PROOF_TYPE_SPINNER_ITEM_SIGNATURE_PROOF;
                } else if (kycType.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_VOTER_ID)) {
                    proofType = PROOF_TYPE_SPINNER_ITEM_ID_CUM_ADDRESS_PROOF;
                } else if (kycType.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_PASSPORT)) {
                    proofType = PROOF_TYPE_SPINNER_ITEM_ID_CUM_ADDRESS_PROOF + "," + PROOF_TYPE_SPINNER_ITEM_ID_CUM_SIGNATURE_PROOF;
                } else if (kycType.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_DRIVING_LICENSE)) {
                    proofType = PROOF_TYPE_SPINNER_ITEM_ID_CUM_ADDRESS_PROOF + "," + PROOF_TYPE_SPINNER_ITEM_ID_CUM_SIGNATURE_PROOF;
                } else if (kycType.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_UTILITY_BILLS)) {
                    proofType = PROOF_TYPE_SPINNER_ITEM_ADDRESS_PROOF;
                } else if (kycType.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_RATION_CARD)) {
                    proofType = PROOF_TYPE_SPINNER_ITEM_ID_CUM_ADDRESS_PROOF;
                } else if (kycType.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_BANK_STATEMENT)) {
                    proofType = PROOF_TYPE_SPINNER_ITEM_ADDRESS_PROOF;
                } else if (kycType.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_BANK_PASSBOOK_WITH_PHOTOGRAPH)) {
                    proofType = PROOF_TYPE_SPINNER_ITEM_ADDRESS_PROOF;
                } else if (kycType.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_AUTHORIZED_SIGNATURE_PROOF)) {
                    proofType = PROOF_TYPE_SPINNER_ITEM_SIGNATURE_PROOF;
                } else if (kycType.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_NREGA_JOB_CARD)) {
                    proofType = PROOF_TYPE_SPINNER_ITEM_ID_CUM_ADDRESS_PROOF;
                } else if (kycType.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_DEEMED_OVD)) {
                    proofType = PROOF_TYPE_SPINNER_ITEM_ADDRESS_PROOF;
                } else if (kycType.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_UIDAI_ELIGIBLE_DOCUMENTS)) {
                    proofType = PROOF_TYPE_SPINNER_ITEM_ADDRESS_PROOF;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return proofType;
    }

    public static DynamicUITable getObjectByTAG(String tagName, List<DynamicUITable> viewParametersList) {
        DynamicUITable dynamicUITable1 = null;
        try {
            for (DynamicUITable dynamicUITable : viewParametersList) {
                if (dynamicUITable != null && !TextUtils.isEmpty(dynamicUITable.getFieldTag())
                        && dynamicUITable.getFieldTag().trim().equalsIgnoreCase(tagName)) {

                    dynamicUITable1 = dynamicUITable;
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dynamicUITable1;
    }

    public static DynamicUITable getObjectByFieldName(String fieldName, List<DynamicUITable> viewParametersList) {
        DynamicUITable dynamicUITable1 = null;
        try {
            for (DynamicUITable dynamicUITable : viewParametersList) {
                if (dynamicUITable != null && !TextUtils.isEmpty(dynamicUITable.getFieldName())
                        && dynamicUITable.getFieldName().trim().equalsIgnoreCase(fieldName.trim())) {

                    dynamicUITable1 = dynamicUITable;
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dynamicUITable1;
    }

    public static double calculateEMI(
            int loanAmount, int termInMonths, double interestRate) {

        // Convert interest rate into a decimal
        // eg. 6.5% = 0.065

        interestRate /= 100.0;

        // Monthly interest rate
        // is the yearly rate divided by 12

        double monthlyRate = interestRate / 12.0;

        // The length of the term in months
        // is the number of years times 12

        //int termInMonths = termInYears ;

        // Calculate the monthly payment
        // Typically this formula is provided so
        // we won't go into the details

        // The Math.pow() method is used calculate values raised to a power

        double monthlyPayment =
                (loanAmount * monthlyRate) /
                        (1 - Math.pow(1 + monthlyRate, -termInMonths));

        return monthlyPayment;
    }

    public static int getPositionToInsertNewRow(List<DynamicUITable> dynamicUITableList, String tagName) {
        int position = 0;
        try {
            if (dynamicUITableList.size() > 0) {
                position = dynamicUITableList.size() - 1;
                for (int i = 0; i < dynamicUITableList.size(); i++) {
                    DynamicUITable dynamicUITableFromUI = dynamicUITableList.get(i);
                    if (dynamicUITableFromUI.getFieldTag().equalsIgnoreCase(tagName)) {
                        position = i + 1;
                        break;
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return position;
    }


    public static String getCMDate(AppHelper appHelper){
        try{
            return appHelper.getCurrentDate(DATE_FORMAT_YYYYMMDD);
//           return "20200519";
//           return "20180405";
        }catch (Exception ex){
            ex.printStackTrace();
            return "";
        }
    }

    public static String getCMDateForTodayCollectionScheduled(AppHelper appHelper){
        try{
            return appHelper.getCurrentDate(DATE_FORMAT_YYYY_MM_DD);
//           return "20200519";
//           return "20180405";
        }catch (Exception ex){
            ex.printStackTrace();
            return "";
        }
    }

  public static List<String> getApkCertificateDigests(Context context) {
    List<String> encodedSignatures = new ArrayList<String>();
    // Get signatures from package manager
    PackageManager pm = context.getPackageManager();
    PackageInfo packageInfo;
    try {
      packageInfo = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
    } catch (PackageManager.NameNotFoundException e) {
      return encodedSignatures;
    }
    Signature[] signatures = packageInfo.signatures;
    // Calculate b64 encoded sha256 hash of signatures
    for (Signature signature : signatures) {
      try {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(signature.toByteArray());
        byte[] digest = md.digest();
        encodedSignatures.add(Base64.encodeToString(digest, Base64.NO_WRAP));
      } catch (NoSuchAlgorithmException e) {
      }
    }
    return encodedSignatures;
  }

}
