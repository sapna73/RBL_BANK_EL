package com.saartak.el.di.module;

import com.saartak.el.fragments.AddressDetailsFragment;
import com.saartak.el.fragments.ApplicantKYCFragment;
import com.saartak.el.fragments.ApplicantPANDetailsFragment;
import com.saartak.el.fragments.ApplicantRegulatoryFieldsFragment;
import com.saartak.el.fragments.ApplicantSignatureFragmant;
import com.saartak.el.fragments.AuditFragment;
import com.saartak.el.fragments.BankDetailsFragment;
import com.saartak.el.fragments.BusinessAddressProofFragment;
import com.saartak.el.fragments.BusinessEntityFragment;
import com.saartak.el.fragments.BusinessProofFragment;
import com.saartak.el.fragments.CPVFragment;
import com.saartak.el.fragments.ChildFragment;
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
import com.saartak.el.fragments.DenominationFragment;
import com.saartak.el.fragments.DigitalDocFragment;
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
import com.saartak.el.fragments.balancesheet.AddLiabilityFragment;
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
import com.saartak.el.fragments.decisions.msme.MortgageMSMEFragment;
import com.saartak.el.fragments.decisions.ObservationsFragment;
import com.saartak.el.fragments.decisions.msme.HypothecationMsmeFragment;
import com.saartak.el.fragments.decisions.msme.LoanInformationMsmeFragment;
import com.saartak.el.fragments.decisions.msme.SubjectToConditionsMsmeFragment;
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
import com.saartak.el.fragments.villagesurvey.CenterCreationFragment;
import com.saartak.el.fragments.villagesurvey.VillageSurveyFragment;

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
    @ContributesAndroidInjector
    abstract PosidexFragment contributeposidexFragment();
    @ContributesAndroidInjector
    abstract DeliquencyFragment contributedeliquencyFragment();
    @ContributesAndroidInjector
    abstract UsedCarDetailFragment contributeUsedCarDetailFragment();
    @ContributesAndroidInjector
    abstract TwoWheelerFragment contributeTwoWheelerFragment();
    @ContributesAndroidInjector
    abstract DEDUPEFragment contributededupeFragment();
    @ContributesAndroidInjector
    abstract RampFragment contributeRampFragment();
    @ContributesAndroidInjector
    abstract HunterFragment contributeHunterFragment();
    @ContributesAndroidInjector
    abstract ENachFragment contributeENachFragment();
    @ContributesAndroidInjector
    abstract CPVFragment contributeCPVFragment();
    @ContributesAndroidInjector
    abstract DigitalDocFragment contributeDigitalDocFragment();
    @ContributesAndroidInjector
    abstract RATFragment contributeRATFragment();
    @ContributesAndroidInjector
    abstract ApplicantRegulatoryFieldsFragment contributeApplicantRegulatoryFields();
    @ContributesAndroidInjector
    abstract CoApplicantRegulatoryFieldsFragment contributeCoApplicantRegulatoryFields ();
    @ContributesAndroidInjector
    abstract DNCRApplicantFragment contributeDNCRApplicantFragment ();
    @ContributesAndroidInjector
    abstract EsignEstampFragment contributeEsignEstampFragment ();
    @ContributesAndroidInjector
    abstract StudentDetailsFragment contributeStudentDetailsFragment ();
    @ContributesAndroidInjector
    abstract ApplicantPANDetailsFragment contributeApplicantPANDetailsFragment ();
    @ContributesAndroidInjector
    abstract CoApplicantPANDetailsFragment contributeCoApplicantPANDetailsFragment ();
    @ContributesAndroidInjector
    abstract CourseDetailsFragment contributeCourseDetailsFragment ();
    @ContributesAndroidInjector
    abstract GuarantorDetailsFragment contributeGuarantorDetailsFragment ();
}
