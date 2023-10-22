package com.saartak.el.di.module;

import com.saartak.el.activities.AddOrRemoveCGTActivity;
import com.saartak.el.activities.ApplicationToPDActivity;
import com.saartak.el.activities.CGTAttendanceActivity;
import com.saartak.el.activities.BalancesheetStaticActivity;
import com.saartak.el.activities.BaseActivity;
import com.saartak.el.activities.CGTActivity;
import com.saartak.el.activities.CGTCycleActivity;
import com.saartak.el.activities.CGTSummaryActivity;
import com.saartak.el.activities.CenterCreationActivity;
import com.saartak.el.activities.CenterMeetingCashCollectionSummaryActivity;
import com.saartak.el.activities.CenterMeetingCollectionActivity;
import com.saartak.el.activities.CenterMeetingCollectionSummaryHomeActivity;
import com.saartak.el.activities.CenterMeetingEMIDetailsActivity;
import com.saartak.el.activities.CenterMeetingODCollectionActivity;
import com.saartak.el.activities.CenterMeetingODEMIDetailsActivity;
import com.saartak.el.activities.CollectionDashBoardActivity;
import com.saartak.el.activities.CollectionHomeActivity;
import com.saartak.el.activities.CreateNewCenterActivity;
import com.saartak.el.activities.CenterCreationHomeActivity;
import com.saartak.el.activities.CenterMeetingHomeActivity;
import com.saartak.el.activities.ChangePasswordActivity;
import com.saartak.el.activities.ClientDetailsActivity;
import com.saartak.el.activities.ColdCallingDetailsActivity;
import com.saartak.el.activities.CollectionSummaryActivity;
import com.saartak.el.activities.DigitalCollectionActivity;
import com.saartak.el.activities.DigitalCollectionEMIDetailsActivity;
import com.saartak.el.activities.DigitalCollectionReportActivity;
import com.saartak.el.activities.DropOutCustomerActivity;
import com.saartak.el.activities.EKYCTestActivity;
import com.saartak.el.activities.EligibilityActivity;
import com.saartak.el.activities.CenterMeetingAttendanceActivity;
import com.saartak.el.activities.EligibleNewLoanHomeActivity;
import com.saartak.el.activities.FetchOtherDayCollectionActivity;
import com.saartak.el.activities.ForgotPasswordActivity;
import com.saartak.el.activities.FullImageScreenActivity;
import com.saartak.el.activities.GRTActivity;
import com.saartak.el.activities.GRTAttendanceActivity;
import com.saartak.el.activities.GRTSummaryActivity;
import com.saartak.el.activities.GroupFormationActivity;
import com.saartak.el.activities.GroupMembersActivity;
import com.saartak.el.activities.HomeActivity;
import com.saartak.el.activities.HouseVerificationMemberDetailsActivity;
import com.saartak.el.activities.HouseVerificationMemberDetailsSummaryActivity;
import com.saartak.el.activities.ImageCaptureActivity;
import com.saartak.el.activities.ImageCaptureActivityJLG;
import com.saartak.el.activities.IncomeAssessmentStaticActivity;
import com.saartak.el.activities.JLGHomeActivity;
import com.saartak.el.activities.KnowledgeBankHeaderActivity;
import com.saartak.el.activities.LOPricingActivity;
import com.saartak.el.activities.LOSProductActivity;
import com.saartak.el.activities.LeadDetailsActivity;
import com.saartak.el.activities.LeadDetailsSummaryActivity;
import com.saartak.el.activities.LeadToApplicationActivity;
import com.saartak.el.activities.LeadToOtherThenLOPricingActivity;
import com.saartak.el.activities.LeadToPricingActivity;
import com.saartak.el.activities.LoanApplicationActivity;
import com.saartak.el.activities.LoanApplicationMemberDetailsActivity;
import com.saartak.el.activities.LoanApplicationSummaryActivity;
import com.saartak.el.activities.LoginActivity;
import com.saartak.el.activities.MemberDetailsActivity;
import com.saartak.el.activities.PDFViewerActivity;
import com.saartak.el.activities.PendingCollectionActivity;
import com.saartak.el.activities.PersonalDiscussionActivity;
import com.saartak.el.activities.PersonalHomeActivity;
import com.saartak.el.activities.PlannerActivity;
import com.saartak.el.activities.PlannerSummaryActivity;
import com.saartak.el.activities.ProductActivity;
import com.saartak.el.activities.QCReCaptureActivity;
import com.saartak.el.activities.ReminderActivity;
import com.saartak.el.activities.CentersSummaryActivity;
import com.saartak.el.activities.SalesToolSummaryActivity;
import com.saartak.el.activities.TargetDetailsActivity;
import com.saartak.el.activities.TodayCollectionScheduledActivity;
import com.saartak.el.activities.VillageSurveyActivity;
import com.saartak.el.activities.WorkflowHistorySummaryActivity;

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
    abstract LeadToPricingActivity contributeLeadToPricingActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract LeadToOtherThenLOPricingActivity contributeLeadToOtherThenLOPricingActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract WorkflowHistorySummaryActivity contributeWorkflowHistorySummaryActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract CollectionSummaryActivity contributeCollectionSummaryActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract ClientDetailsActivity contributeClientDetailsActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract LOPricingActivity contributeLOPricingActivity();

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
