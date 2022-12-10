package com.swadhaar.los.di.module;

import com.swadhaar.los.activities.AddOrRemoveCGTActivity;
import com.swadhaar.los.activities.ApplicationToPDActivity;
import com.swadhaar.los.activities.CGTAttendanceActivity;
import com.swadhaar.los.activities.BalancesheetStaticActivity;
import com.swadhaar.los.activities.BaseActivity;
import com.swadhaar.los.activities.CGTActivity;
import com.swadhaar.los.activities.CGTCycleActivity;
import com.swadhaar.los.activities.CGTSummaryActivity;
import com.swadhaar.los.activities.CenterCreationActivity;
import com.swadhaar.los.activities.CenterMeetingCashCollectionSummaryActivity;
import com.swadhaar.los.activities.CenterMeetingCollectionActivity;
import com.swadhaar.los.activities.CenterMeetingCollectionSummaryHomeActivity;
import com.swadhaar.los.activities.CenterMeetingEMIDetailsActivity;
import com.swadhaar.los.activities.CenterMeetingODCollectionActivity;
import com.swadhaar.los.activities.CenterMeetingODEMIDetailsActivity;
import com.swadhaar.los.activities.CollectionDashBoardActivity;
import com.swadhaar.los.activities.CollectionHomeActivity;
import com.swadhaar.los.activities.CreateNewCenterActivity;
import com.swadhaar.los.activities.CenterCreationHomeActivity;
import com.swadhaar.los.activities.CenterMeetingHomeActivity;
import com.swadhaar.los.activities.ChangePasswordActivity;
import com.swadhaar.los.activities.ClientDetailsActivity;
import com.swadhaar.los.activities.ColdCallingDetailsActivity;
import com.swadhaar.los.activities.CollectionSummaryActivity;
import com.swadhaar.los.activities.DigitalCollectionActivity;
import com.swadhaar.los.activities.DigitalCollectionEMIDetailsActivity;
import com.swadhaar.los.activities.DigitalCollectionReportActivity;
import com.swadhaar.los.activities.DropOutCustomerActivity;
import com.swadhaar.los.activities.EKYCTestActivity;
import com.swadhaar.los.activities.EligibilityActivity;
import com.swadhaar.los.activities.CenterMeetingAttendanceActivity;
import com.swadhaar.los.activities.EligibleNewLoanHomeActivity;
import com.swadhaar.los.activities.FetchOtherDayCollectionActivity;
import com.swadhaar.los.activities.ForgotPasswordActivity;
import com.swadhaar.los.activities.FullImageScreenActivity;
import com.swadhaar.los.activities.GRTActivity;
import com.swadhaar.los.activities.GRTAttendanceActivity;
import com.swadhaar.los.activities.GRTSummaryActivity;
import com.swadhaar.los.activities.GroupFormationActivity;
import com.swadhaar.los.activities.GroupMembersActivity;
import com.swadhaar.los.activities.HomeActivity;
import com.swadhaar.los.activities.HouseVerificationMemberDetailsActivity;
import com.swadhaar.los.activities.HouseVerificationMemberDetailsSummaryActivity;
import com.swadhaar.los.activities.ImageCaptureActivity;
import com.swadhaar.los.activities.ImageCaptureActivityJLG;
import com.swadhaar.los.activities.IncomeAssessmentStaticActivity;
import com.swadhaar.los.activities.JLGHomeActivity;
import com.swadhaar.los.activities.KnowledgeBankHeaderActivity;
import com.swadhaar.los.activities.LOSProductActivity;
import com.swadhaar.los.activities.LeadDetailsActivity;
import com.swadhaar.los.activities.LeadDetailsSummaryActivity;
import com.swadhaar.los.activities.LeadToApplicationActivity;
import com.swadhaar.los.activities.LoanApplicationActivity;
import com.swadhaar.los.activities.LoanApplicationMemberDetailsActivity;
import com.swadhaar.los.activities.LoanApplicationSummaryActivity;
import com.swadhaar.los.activities.LoginActivity;
import com.swadhaar.los.activities.MemberDetailsActivity;
import com.swadhaar.los.activities.PDFViewerActivity;
import com.swadhaar.los.activities.PendingCollectionActivity;
import com.swadhaar.los.activities.PersonalDiscussionActivity;
import com.swadhaar.los.activities.PersonalHomeActivity;
import com.swadhaar.los.activities.PlannerActivity;
import com.swadhaar.los.activities.PlannerSummaryActivity;
import com.swadhaar.los.activities.ProductActivity;
import com.swadhaar.los.activities.QCReCaptureActivity;
import com.swadhaar.los.activities.ReminderActivity;
import com.swadhaar.los.activities.CentersSummaryActivity;
import com.swadhaar.los.activities.SalesToolSummaryActivity;
import com.swadhaar.los.activities.TargetDetailsActivity;
import com.swadhaar.los.activities.TodayCollectionScheduledActivity;
import com.swadhaar.los.activities.VillageSurveyActivity;
import com.swadhaar.los.activities.WorkflowHistorySummaryActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityModule {
    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract BaseActivity contributeBaseActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract ProductActivity contributeProductActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract BalancesheetStaticActivity contributeBalancesheetStaticActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract IncomeAssessmentStaticActivity contributeIncomeAssessmentStaticActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract PlannerActivity contributePlannerActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract PlannerSummaryActivity contributePlannerSummaryActivity();


    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract ColdCallingDetailsActivity contributeColdCallingDetailsActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract SalesToolSummaryActivity contributeSalesToolSummaryActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract ReminderActivity contributeReminderActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract LeadDetailsSummaryActivity contributeLeadDetailsSummaryActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract LeadDetailsActivity contributeLeadDetailsActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract LeadToApplicationActivity contributeLeadToApplicationActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract WorkflowHistorySummaryActivity contributeWorkflowHistorySummaryActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract CollectionSummaryActivity contributeCollectionSummaryActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract ClientDetailsActivity contributeClientDetailsActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract ApplicationToPDActivity contributeApplicationToPDActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract EKYCTestActivity contributeEKYCTestActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract PersonalDiscussionActivity contributePersonalDiscussionActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract HomeActivity contributeHomeActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract JLGHomeActivity contributeJLGHomeActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract PersonalHomeActivity contributePersonalHomeActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract CenterCreationHomeActivity contributeCenterCreationHomeActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract CenterMeetingHomeActivity contributeCenterMeetingHomeActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract QCReCaptureActivity contributeQCReCaptureActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract FetchOtherDayCollectionActivity contributeFetchOtherDayCollectionActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract ImageCaptureActivity contributeImageCaptureActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract LoginActivity contributeLoginActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract ChangePasswordActivity contributeChangePasswordActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract ForgotPasswordActivity contributeForgotPasswordActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract TargetDetailsActivity contributeTargetDetailsActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract MemberDetailsActivity contributeMemberDetailsActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract VillageSurveyActivity contributeVillageSurveyActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract CenterCreationActivity contributeCenterCreationActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract CGTSummaryActivity contributeCGTSummaryActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract CGTActivity contributeCGTActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract GroupFormationActivity contributeGroupFormationActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract GroupMembersActivity contributeGroupMembersActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract CGTAttendanceActivity contributeAttendanceActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract HouseVerificationMemberDetailsSummaryActivity contributeHouseVerificationMemberDetailsSummaryActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract HouseVerificationMemberDetailsActivity contributeHouseVerificationMemberDetailsActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract CentersSummaryActivity contributeTargetDetailCentersActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract GRTSummaryActivity contributeGRTSummaryActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract GRTActivity contributeGRTActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract ImageCaptureActivityJLG contributeImageCaptureActivityJLG();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract AddOrRemoveCGTActivity contributeAddOrRemoveCGTActivity();

 @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract LoanApplicationMemberDetailsActivity contributeLoanApplicationMemberDetailsActivity();

 @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract LoanApplicationSummaryActivity contributeLoanApplicationSummaryActivity();

 @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract LoanApplicationActivity contributeLoanLoanApplicationActivity();

 @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract CGTCycleActivity contributeCGTCycleActivity();

 @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract EligibilityActivity contributeEligibilityActivity();

 @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract DropOutCustomerActivity contributeDropOutCustomerActivity();

 @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract CreateNewCenterActivity contributeCreateNewCenterActivity();

 @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract EligibleNewLoanHomeActivity contributeEligibleNewLoanHomeActivity();

 @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract CenterMeetingAttendanceActivity contributeCenterMeetingAttendanceActivity();

 @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract CenterMeetingCollectionActivity contributeCenterMeetingCollectionActivity();

 @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract CenterMeetingEMIDetailsActivity contributeCenterMeetingEMIDetailsActivity();

 @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract CenterMeetingODCollectionActivity contributeCenterMeetingODCollectionActivity();

 @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract CenterMeetingODEMIDetailsActivity contributeCenterMeetingODEMIDetailsActivity();

 @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract TodayCollectionScheduledActivity contributeTodayCollectionScheduledActivity();

 @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract KnowledgeBankHeaderActivity contributeKnowledgeBankHeaderActivity();

 @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract FullImageScreenActivity contributeFullImageScreenActivity();

 @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract PDFViewerActivity contributePDFViewerActivity();

 @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract CollectionHomeActivity contributeCollectionHomeActivity();

 @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract CollectionDashBoardActivity contributeCollectionDashBoardActivity();

 @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract DigitalCollectionActivity contributeDigitalCollectionActivity();

 @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract PendingCollectionActivity contributePendingCollectionActivity();

 @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract CenterMeetingCollectionSummaryHomeActivity contributeCenterMeetingCollectionSummaryHomeActivity();

 @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract CenterMeetingCashCollectionSummaryActivity contributeCenterMeetingCashCollectionSummaryActivity();

  @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract DigitalCollectionReportActivity contributeDigitalCollectionReportActivity();

 @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract DigitalCollectionEMIDetailsActivity contributeDigitalCollectionEMIDetailsActivity();
 @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract LOSProductActivity contributeLOSProductActivity();

 @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract GRTAttendanceActivity contributeGRTAttendanceActivity();

}
