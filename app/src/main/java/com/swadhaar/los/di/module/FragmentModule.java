package com.swadhaar.los.di.module;

import com.swadhaar.los.fragments.AddressDetailsFragment;
import com.swadhaar.los.fragments.ApplicantKYCFragment;
import com.swadhaar.los.fragments.ApplicantSignatureFragmant;
import com.swadhaar.los.fragments.AuditFragment;
import com.swadhaar.los.fragments.BankDetailsFragment;
import com.swadhaar.los.fragments.BusinessAddressProofFragment;
import com.swadhaar.los.fragments.BusinessEntityFragment;
import com.swadhaar.los.fragments.BusinessProofFragment;
import com.swadhaar.los.fragments.ChildFragment;
import com.swadhaar.los.fragments.CoApplicantAddressDetailFragment;
import com.swadhaar.los.fragments.CoApplicantBankDetailsFragment;
import com.swadhaar.los.fragments.CoApplicantKYCFragment;
import com.swadhaar.los.fragments.CoApplicantNomineeDetailFragment;
import com.swadhaar.los.fragments.CoApplicantPersonalDetailFragment;
import com.swadhaar.los.fragments.CoApplicantSocioEconomicDetail;
import com.swadhaar.los.fragments.ColdCallingFragment;
import com.swadhaar.los.fragments.CollectionFragment;
import com.swadhaar.los.fragments.DenominationFragment;
import com.swadhaar.los.fragments.DocumentUploadFragmentNew;
import com.swadhaar.los.fragments.GenerateCIBILFragment;
import com.swadhaar.los.fragments.LeadFragment;
import com.swadhaar.los.fragments.LeadSummaryFragment;
import com.swadhaar.los.fragments.NomineeDetailFragment;
import com.swadhaar.los.fragments.OTPAuthenticationFragment;
import com.swadhaar.los.fragments.OTPVerificationFragment;
import com.swadhaar.los.fragments.OfficeAddressProofFragment;
import com.swadhaar.los.fragments.PersonalDetailFragment;
import com.swadhaar.los.fragments.ReferenceCheckFragment;
import com.swadhaar.los.fragments.SalaryEntityFragment;
import com.swadhaar.los.fragments.SalesToolFragment;
import com.swadhaar.los.fragments.SampleFragment;
import com.swadhaar.los.fragments.SocioEconomicDetailFragment;
import com.swadhaar.los.fragments.balancesheet.AddLiabilityFragment;
import com.swadhaar.los.fragments.balancesheet.AdvancesFragment;
import com.swadhaar.los.fragments.balancesheet.BusinessAssetsFragment;
import com.swadhaar.los.fragments.balancesheet.BusinessDebtsFragment;
import com.swadhaar.los.fragments.balancesheet.BusinessLiabilitiesFragment;
import com.swadhaar.los.fragments.balancesheet.HouseAssetsFragment;
import com.swadhaar.los.fragments.balancesheet.HouseLiabilitiesFragment;
import com.swadhaar.los.fragments.balancesheet.ProductFragment;
import com.swadhaar.los.fragments.balancesheet.msme.BusinessAssertsMsmeFragment;
import com.swadhaar.los.fragments.balancesheet.msme.BusinessLiabilitiesMsmeFragment;
import com.swadhaar.los.fragments.balancesheet.msme.HouseAssetsMsmeFragment;
import com.swadhaar.los.fragments.balancesheet.msme.HouseLiabilitiesMsmeFragment;
import com.swadhaar.los.fragments.decisions.msme.CashFlowSummaryMSMEFragment;
import com.swadhaar.los.fragments.decisions.msme.LoanApprovalMSMEFragment;
import com.swadhaar.los.fragments.decisions.msme.MortgageMSMEFragment;
import com.swadhaar.los.fragments.decisions.ObservationsFragment;
import com.swadhaar.los.fragments.decisions.msme.HypothecationMsmeFragment;
import com.swadhaar.los.fragments.decisions.msme.LoanInformationMsmeFragment;
import com.swadhaar.los.fragments.decisions.msme.SubjectToConditionsMsmeFragment;
import com.swadhaar.los.fragments.incomeassessment.BusinessIncomeFragment;
import com.swadhaar.los.fragments.incomeassessment.DeclaredSalesWeeklyFragment;
import com.swadhaar.los.fragments.incomeassessment.DeclaredSalesYearlyFragment;
import com.swadhaar.los.fragments.incomeassessment.EmployerVerificationFragment;
import com.swadhaar.los.fragments.incomeassessment.FamilyMemberFragment;
import com.swadhaar.los.fragments.incomeassessment.HouseExpensesFragment;
import com.swadhaar.los.fragments.incomeassessment.HouseIncomeFragment;
import com.swadhaar.los.fragments.incomeassessment.OperatingExpenseFragment;
import com.swadhaar.los.fragments.incomeassessment.ProductEstimateFragment;
import com.swadhaar.los.fragments.incomeassessment.PurchaseAnalysisByProductFragment;
import com.swadhaar.los.fragments.incomeassessment.PurchaseAnalysisFragment;
import com.swadhaar.los.fragments.incomeassessment.SummaryFragment;
import com.swadhaar.los.fragments.incomeassessment.msme.BankingHistoryFragment;
import com.swadhaar.los.fragments.incomeassessment.msme.DirectBusinessExpenseFragment;
import com.swadhaar.los.fragments.incomeassessment.msme.GSTFragment;
import com.swadhaar.los.fragments.incomeassessment.msme.GeneralIncomeFragment;
import com.swadhaar.los.fragments.incomeassessment.msme.HouseIncomeSummaryFragment;
import com.swadhaar.los.fragments.incomeassessment.msme.ITRDetailFragment;
import com.swadhaar.los.fragments.incomeassessment.msme.ITRFragment;
import com.swadhaar.los.fragments.incomeassessment.msme.IncomeAssessmentSummaryMsmeFragment;
import com.swadhaar.los.fragments.incomeassessment.msme.MonthlyTransactionDetailFragment;
import com.swadhaar.los.fragments.incomeassessment.msme.OtherIncomeSourceFragment;
import com.swadhaar.los.fragments.incomeassessment.msme.ProductEstimateDetailFragment;
import com.swadhaar.los.fragments.incomeassessment.msme.ProductEstimateMSMEFragment;
import com.swadhaar.los.fragments.incomeassessment.msme.ProductRawMaterialFragment;
import com.swadhaar.los.fragments.incomeassessment.msme.PurchaseBillsDetailFragment;
import com.swadhaar.los.fragments.incomeassessment.msme.PurchaseBillsFragment;
import com.swadhaar.los.fragments.incomeassessment.msme.SalesBillsDetailFragment;
import com.swadhaar.los.fragments.incomeassessment.msme.SalesBillsFragment;
import com.swadhaar.los.fragments.incomeassessment.msme.ServiceEstimateDetailFragment;
import com.swadhaar.los.fragments.incomeassessment.msme.ServiceEstimateFragment;
import com.swadhaar.los.fragments.incomeassessment.msme.ServiceRawMaterialFragment;
import com.swadhaar.los.fragments.loanproposal.ApplicantLoanProposalFragment;
import com.swadhaar.los.fragments.loanproposal.FinancialRatiosFragment;
import com.swadhaar.los.fragments.loanproposal.LoanProposalFragment;
import com.swadhaar.los.fragments.loanproposal.LoanProposalSummaryFragment;
import com.swadhaar.los.fragments.loanproposal.SurPlusFragment;
import com.swadhaar.los.fragments.references.ReferencesFragment;
import com.swadhaar.los.fragments.villagesurvey.CenterCreationFragment;
import com.swadhaar.los.fragments.villagesurvey.VillageSurveyFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract SampleFragment contributeSampleFragment();
    @ContributesAndroidInjector
    abstract ApplicantKYCFragment contributeApplicantKYCFragment();

    @ContributesAndroidInjector
    abstract CoApplicantKYCFragment contributeCoApplicantKYCFragment();
    @ContributesAndroidInjector
    abstract AddressDetailsFragment contributeAddressDetailsFragment();
    @ContributesAndroidInjector
    abstract ChildFragment contributeChildFragment();

    @ContributesAndroidInjector
    abstract ApplicantSignatureFragmant contributeApplicantSignatureFragmant();
    @ContributesAndroidInjector
    abstract BankDetailsFragment contributeBankDetailsFragment();
    @ContributesAndroidInjector
    abstract BusinessEntityFragment contributeBusinessEntityFragment();
    @ContributesAndroidInjector
    abstract BusinessProofFragment contributeBusinessProofFragment();
    @ContributesAndroidInjector
    abstract CoApplicantAddressDetailFragment contributeCoApplicantAddressDetailFragment();
    @ContributesAndroidInjector
    abstract CoApplicantNomineeDetailFragment contributeCoApplicantNomineeDetailFragment();
    @ContributesAndroidInjector
    abstract CoApplicantPersonalDetailFragment contributeCoApplicantPersonalDetailFragment();
    @ContributesAndroidInjector
    abstract NomineeDetailFragment contributeNomineeDetailFragment();
    @ContributesAndroidInjector
    abstract OTPAuthenticationFragment contributeOTPAuthenticationFragment();
    @ContributesAndroidInjector
    abstract PersonalDetailFragment contributePersonalDetailFragment();
    @ContributesAndroidInjector
    abstract ReferenceCheckFragment contributeReferenceCheckFragment();
    @ContributesAndroidInjector
    abstract SocioEconomicDetailFragment contributeSocioEconomicDetailFragment();
    @ContributesAndroidInjector
    abstract HouseAssetsFragment contributeHouseAssetsFragment();
    @ContributesAndroidInjector
    abstract HouseLiabilitiesFragment contributeHouseLiabilitiesFragment();
    @ContributesAndroidInjector
    abstract BusinessAssetsFragment contributeBusinessAssetsFragment();
    @ContributesAndroidInjector
    abstract BusinessLiabilitiesFragment contributeBusinessLiabilitiesFragment();
    @ContributesAndroidInjector
    abstract CoApplicantSocioEconomicDetail contributeCoApplicantSocioEconomicDetail();
    @ContributesAndroidInjector
    abstract AddLiabilityFragment contributeAddLiabilityFragment();
    @ContributesAndroidInjector
    abstract ProductFragment contributeProductFragment();
    @ContributesAndroidInjector
    abstract BusinessDebtsFragment contributeBusinessDebtsFragment();
    @ContributesAndroidInjector
    abstract AdvancesFragment contributeAdavancesFragment();
    @ContributesAndroidInjector
    abstract HouseIncomeFragment contributeHouseIncomeFragment();
    @ContributesAndroidInjector
    abstract FamilyMemberFragment contributeFamilyMemberFragment();
    @ContributesAndroidInjector
    abstract EmployerVerificationFragment contributeEmployerVerificationFragment();
    @ContributesAndroidInjector
    abstract HouseExpensesFragment contributeHouseExpensesFragment();
    @ContributesAndroidInjector
    abstract BusinessIncomeFragment contributeBusinessIncomeFragment();
    @ContributesAndroidInjector
    abstract DeclaredSalesWeeklyFragment contributeDeclaredSalesWeeklyFragment();
    @ContributesAndroidInjector
    abstract DeclaredSalesYearlyFragment contributeDeclaredSalesYearlyFragment();
    @ContributesAndroidInjector
    abstract ProductEstimateFragment contributeProductEstimateFragment();
    @ContributesAndroidInjector
    abstract PurchaseAnalysisFragment contributePurchaseAnalysisFragment();
    @ContributesAndroidInjector
    abstract PurchaseAnalysisByProductFragment contributePurchaseAnalysisByProductFragment();
    @ContributesAndroidInjector
    abstract OperatingExpenseFragment contributeOperatingExpenseFragment();
    @ContributesAndroidInjector
    abstract SummaryFragment contributeSummaryFragment();
    @ContributesAndroidInjector
    abstract LeadFragment contributeLeadFragment();
    @ContributesAndroidInjector
    abstract LeadSummaryFragment contributeLeadSummaryFragment();
    @ContributesAndroidInjector
    abstract ColdCallingFragment contributeColdCallingFragment();
    @ContributesAndroidInjector
    abstract SalesToolFragment contributeSalesToolFragment();
    @ContributesAndroidInjector
    abstract CollectionFragment contributeCollectionFragment();
    @ContributesAndroidInjector
    abstract LoanProposalFragment contributeLoanProposalFragment();
    @ContributesAndroidInjector
    abstract SurPlusFragment contributeSurPlusFragment();
    @ContributesAndroidInjector
    abstract FinancialRatiosFragment contributeFinancialRatiosFragment();
    @ContributesAndroidInjector
    abstract LoanProposalSummaryFragment contributeLoanProposalSummaryFragment();
    @ContributesAndroidInjector
    abstract ObservationsFragment contributeObservationsFragment();
    @ContributesAndroidInjector
    abstract ReferencesFragment contributeReferencesFragment();
    @ContributesAndroidInjector
    abstract CoApplicantBankDetailsFragment contributeCoApplicantBankDetailsFragment();
    @ContributesAndroidInjector
    abstract ApplicantLoanProposalFragment contributeApplicantLoanProposalFragment();
    @ContributesAndroidInjector
    abstract BusinessAddressProofFragment contributeBusinessAddressProofFragment();
    @ContributesAndroidInjector
    abstract OTPVerificationFragment contributeOTPVerificationFragment();
    @ContributesAndroidInjector
    abstract GeneralIncomeFragment contributeGeneralIncomeFragment();
    @ContributesAndroidInjector
    abstract HouseIncomeSummaryFragment contributeHouseIncomeSummaryFragment();
    @ContributesAndroidInjector
    abstract OtherIncomeSourceFragment contributeOtherIncomeSourceFragment();

    @ContributesAndroidInjector
    abstract BankingHistoryFragment contributeBankingHistoryFragment();
    @ContributesAndroidInjector
    abstract MonthlyTransactionDetailFragment contributeMonthlyTransactionDetailFragment();
    @ContributesAndroidInjector
    abstract SalesBillsFragment contributeSalesBillsFragment();
    @ContributesAndroidInjector
    abstract SalesBillsDetailFragment contributeSalesBillsDetailFragment();
    @ContributesAndroidInjector
    abstract PurchaseBillsFragment contributePurchaseBillsFragment();
    @ContributesAndroidInjector
    abstract PurchaseBillsDetailFragment contributePurchaseBillsDetailFragment();
    @ContributesAndroidInjector
    abstract GSTFragment contributeGSTFragment();
    @ContributesAndroidInjector
    abstract ITRFragment contributeITRFragment();
    @ContributesAndroidInjector
    abstract ITRDetailFragment contributeITRDetailFragment();
    @ContributesAndroidInjector
    abstract ProductEstimateMSMEFragment contributeProductEstimateMSMEFragment();
    @ContributesAndroidInjector
    abstract ProductEstimateDetailFragment contributeProductEstimateDetailFragment();
    @ContributesAndroidInjector
    abstract ServiceEstimateFragment contributeServiceEstimateFragment();
    @ContributesAndroidInjector
    abstract ServiceEstimateDetailFragment contributeServiceEstimateDetailFragment();
    @ContributesAndroidInjector
    abstract DirectBusinessExpenseFragment contributeDirectBusinessExpenseFragment();
    @ContributesAndroidInjector
    abstract ServiceRawMaterialFragment contributeServiceRawMaterialFragment();
    @ContributesAndroidInjector
    abstract ProductRawMaterialFragment contributeProductRawMaterialFragment();
    @ContributesAndroidInjector
    abstract HouseLiabilitiesMsmeFragment contributeHouseLiabilitiesmsmeFragment();
    @ContributesAndroidInjector //new
    abstract BusinessLiabilitiesMsmeFragment contributeBusinessLiabilitiesmsmeFragment();
    @ContributesAndroidInjector
    abstract BusinessAssertsMsmeFragment contributeBusinessAssertsmsmeFragment();
    @ContributesAndroidInjector
    abstract IncomeAssessmentSummaryMsmeFragment contributeIncomeAssessmentSummaryMsmeFragment();
    @ContributesAndroidInjector
    abstract HouseAssetsMsmeFragment contributeHouseAssetsMsmeFragment();
    @ContributesAndroidInjector
    abstract MortgageMSMEFragment contributeMortgageMSMEFragment();
	@ContributesAndroidInjector
    abstract LoanInformationMsmeFragment contributeLoanInformationMsmeFragment();
    @ContributesAndroidInjector
    abstract SubjectToConditionsMsmeFragment contributeSubjectToConditionsMsmeFragment();
    @ContributesAndroidInjector
    abstract LoanApprovalMSMEFragment contributeLoanApprovalMSMEFragment();
    @ContributesAndroidInjector
    abstract CashFlowSummaryMSMEFragment contributeCashFlowSummaryMSMEFragment();
	@ContributesAndroidInjector
    abstract HypothecationMsmeFragment contributeHypothecationMsmeFragment();
	@ContributesAndroidInjector
    abstract DocumentUploadFragmentNew contributeDocumentUploadFragmentNew();
    @ContributesAndroidInjector
    abstract AuditFragment contributeAuditFragment();
    @ContributesAndroidInjector
    abstract CenterCreationFragment contributeCenterCreationFragment();
    @ContributesAndroidInjector
    abstract VillageSurveyFragment contributeVillageSurveyFragment();
    @ContributesAndroidInjector
    abstract GenerateCIBILFragment contributeGenerateCIBILFragment();
    @ContributesAndroidInjector
    abstract DenominationFragment contributeDenominationFragment();
    @ContributesAndroidInjector
    abstract SalaryEntityFragment contributeSalaryEntityFragment();
    @ContributesAndroidInjector
    abstract OfficeAddressProofFragment contributeOfficeAddressProofFragment();
}
