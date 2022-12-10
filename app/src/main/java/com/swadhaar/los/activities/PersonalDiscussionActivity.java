package com.swadhaar.los.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.swadhaar.los.R;
import com.swadhaar.los.models.DocumentUploadRawdataResponseDTO;
import com.swadhaar.los.view_models.DynamicUIViewModel;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.swadhaar.los.constants.AppConstant.APP_FOLDER;
import static com.swadhaar.los.constants.AppConstant.IMAGE_UPLOAD_FOLDER_NAME;
import static com.swadhaar.los.constants.AppConstant.LOAN_NAME_INDIVIDUAL;
import static com.swadhaar.los.constants.AppConstant.LOAN_NAME_MSME;
import static com.swadhaar.los.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_LOAN_PROPOSAL_PD;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_CLIENT_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_APPLICATION;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_BALANCE_SHEET;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_DECISIONS;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_DOCUMENTS;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_INCOME_ASSESSMENT;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_REFERENCES;
import static com.swadhaar.los.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_NAME;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.SERVICE_CONNECTION_STRING_AUDIT;

public class PersonalDiscussionActivity extends LOSBaseActivity implements View.OnClickListener {
    LinearLayout llApplication,llBalanceSheet,llIncomeAssessment,llReferences,llLoanProposal,llDocuments,llDecisions;
    private static final String TAG = PersonalDiscussionActivity.class.getCanonicalName();
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_personal_discussion);
        llApplication=(LinearLayout)findViewById(R.id.ll_application);
        llApplication.setOnClickListener(this);
        llBalanceSheet=(LinearLayout)findViewById(R.id.ll_balance_sheet);
        llBalanceSheet.setOnClickListener(this);
        llIncomeAssessment=(LinearLayout)findViewById(R.id.ll_income_assessment);
        llIncomeAssessment.setOnClickListener(this);
        llReferences=(LinearLayout)findViewById(R.id.ll_references);
        llReferences.setOnClickListener(this);
        llLoanProposal=(LinearLayout)findViewById(R.id.ll_loan_proposal);
        llLoanProposal.setOnClickListener(this);
        llDocuments=(LinearLayout)findViewById(R.id.ll_documents);
        llDocuments.setOnClickListener(this);
        llDecisions=(LinearLayout)findViewById(R.id.ll_decisions);
        llDecisions.setOnClickListener(this);

        userName = getIntent().getStringExtra(PARAM_USER_NAME);
        userId = getIntent().getStringExtra(PARAM_USER_ID);
        branchId = getIntent().getStringExtra(PARAM_BRANCH_ID);
        branchGSTcode = getIntent().getStringExtra(PARAM_BRANCH_GST_CODE);
        loanType = getIntent().getStringExtra(PARAM_LOAN_TYPE);
        CLIENT_ID = getIntent().getStringExtra(PARAM_CLIENT_ID);
        productId = getIntent().getStringExtra(PARAM_PRODUCT_ID);

        if( ! TextUtils.isEmpty(loanType)
                && loanType.equalsIgnoreCase(LOAN_NAME_MSME)) {
            //hide loan proposal
            llLoanProposal.setVisibility(View.GONE);
        }

        configureDagger();
        configureViewModel();

    }

    private void configureDagger() {
        AndroidInjection.inject(this);
    }

    public void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DynamicUIViewModel.class);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getDocumentRawData();
    }

    @Override
    public void onClick(View v) {
        try{
            switch (v.getId()){
                case R.id.ll_application:
                   /* Intent intentApplication = new Intent(this, BaseActivity.class);
                    intentApplication.putExtra(PARAM_LOAN_TYPE, loanType);
                    intentApplication.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_APPLICATION);
                    startActivity(intentApplication);*/

                    Intent application = new Intent(this, ClientDetailsActivity.class);
//                    ApplicationToPDActivity applicationToPDActivity=(ApplicationToPDActivity)context;
                    application.putExtra(PARAM_LOAN_TYPE, loanType);
                    if( ! TextUtils.isEmpty(loanType)
                            && loanType.equalsIgnoreCase(LOAN_NAME_INDIVIDUAL)) {
                        application.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_APPLICATION);
                    }
                    application.putExtra(PARAM_USER_NAME, userName);
                    application.putExtra(PARAM_BRANCH_ID, branchId);
                    application.putExtra(PARAM_USER_ID, userId);
                    application.putExtra(PARAM_CLIENT_ID, CLIENT_ID);
                    application.putExtra(PARAM_PRODUCT_ID, productId);
                    startActivity(application);
                    break;
                case R.id.ll_balance_sheet:
                    Intent intentBalanceSheet = new Intent(this, BalancesheetStaticActivity.class);
                    intentBalanceSheet.putExtra(PARAM_LOAN_TYPE, loanType);
                    intentBalanceSheet.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_BALANCE_SHEET);
                    intentBalanceSheet.putExtra(PARAM_USER_NAME, userName);
                    intentBalanceSheet.putExtra(PARAM_BRANCH_ID, branchId);
                    intentBalanceSheet.putExtra(PARAM_USER_ID, userId);
                    intentBalanceSheet.putExtra(PARAM_CLIENT_ID, CLIENT_ID);
                    startActivity(intentBalanceSheet);
                    break;
                case R.id.ll_income_assessment:
                    Intent intentIncomeAssessment = new Intent(this, IncomeAssessmentStaticActivity.class);
                    intentIncomeAssessment.putExtra(PARAM_LOAN_TYPE, loanType);
                    intentIncomeAssessment.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_INCOME_ASSESSMENT);
                    intentIncomeAssessment.putExtra(PARAM_USER_NAME, userName);
                    intentIncomeAssessment.putExtra(PARAM_BRANCH_ID, branchId);
                    intentIncomeAssessment.putExtra(PARAM_USER_ID, userId);
                    intentIncomeAssessment.putExtra(PARAM_CLIENT_ID, CLIENT_ID);
                    startActivity(intentIncomeAssessment);
                    break;
                case R.id.ll_references:
                    Intent intentReferences = new Intent(this, BaseActivity.class);
                    intentReferences.putExtra(PARAM_LOAN_TYPE, loanType);
                    intentReferences.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_REFERENCES);
                    intentReferences.putExtra(PARAM_USER_NAME, userName);
                    intentReferences.putExtra(PARAM_BRANCH_ID, branchId);
                    intentReferences.putExtra(PARAM_USER_ID, userId);
                    intentReferences.putExtra(PARAM_CLIENT_ID, CLIENT_ID);
                    startActivity(intentReferences);
                    break;
                case R.id.ll_loan_proposal:
                    Intent intentProposal = new Intent(this, BaseActivity.class);
                    intentProposal.putExtra(PARAM_LOAN_TYPE, loanType);
                    intentProposal.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_LOAN_PROPOSAL_PD);
                    intentProposal.putExtra(PARAM_USER_NAME, userName);
                    intentProposal.putExtra(PARAM_BRANCH_ID, branchId);
                    intentProposal.putExtra(PARAM_USER_ID, userId);
                    intentProposal.putExtra(PARAM_CLIENT_ID, CLIENT_ID);
                    startActivity(intentProposal);
                    break;
                    case R.id.ll_documents:
                    Intent intentDocument = new Intent(this, BaseActivity.class);
                        intentDocument.putExtra(PARAM_LOAN_TYPE, loanType);
                        intentDocument.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_DOCUMENTS);
                        intentDocument.putExtra(PARAM_USER_NAME, userName);
                        intentDocument.putExtra(PARAM_BRANCH_ID, branchId);
                        intentDocument.putExtra(PARAM_USER_ID, userId);
                        intentDocument.putExtra(PARAM_CLIENT_ID, CLIENT_ID);
                    startActivity(intentDocument);
                    break;
                    case R.id.ll_decisions:
                    Intent intentDecision = new Intent(this, BaseActivity.class);
                        intentDecision.putExtra(PARAM_LOAN_TYPE, loanType);
                        intentDecision.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_DECISIONS);
                        intentDecision.putExtra(PARAM_USER_NAME, userName);
                        intentDecision.putExtra(PARAM_BRANCH_ID, branchId);
                        intentDecision.putExtra(PARAM_USER_ID, userId);
                        intentDecision.putExtra(PARAM_CLIENT_ID, CLIENT_ID);
                    startActivity(intentDecision);
                    break;

            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void getDocumentRawData() {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            String folderPath = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + File.separator + APP_FOLDER + File.separator + IMAGE_UPLOAD_FOLDER_NAME + File.separator
                    + CLIENT_ID + File.separator;

            if (!TextUtils.isEmpty(folderPath)) {

                File folder = new File(folderPath);
                if ( ! folder.exists()) {

                    viewModel.getDocumentRawData(CLIENT_ID,userId,loanType,SERVICE_CONNECTION_STRING_AUDIT);
                    if (viewModel.getDocumentUploadRawDataResponseList() != null) {
                        Observer observer = new Observer() {
                            @Override
                            public void onChanged(Object o) {
                                appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                                List<DocumentUploadRawdataResponseDTO> rawdataResponseDTOList = (List<DocumentUploadRawdataResponseDTO>) o;
                                viewModel.getDocumentUploadRawDataResponseList().removeObserver(this);

                                if( rawdataResponseDTOList !=null && rawdataResponseDTOList.size()>0){
                                    downloadDocuments(rawdataResponseDTOList,CLIENT_ID,moduleType);
                                }
                            }
                        };
                        viewModel.getDocumentUploadRawDataResponseList().observe(this, observer);
                    }

                }else{
                    appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                }
            }else{
                appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();

            INSERT_LOG("getDocumentRawData","Exception : "+ex.getMessage());
        }
    }
    private void downloadDocuments(List<DocumentUploadRawdataResponseDTO> rawdataResponseDTOList,String clientId,String moduleType) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            viewModel.downloadDocuments(rawdataResponseDTOList,clientId,moduleType);
            if (viewModel.getStringLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        String response = (String) o;
                        Log.d(TAG, "downloadDocuments ==> " + response);
                        viewModel.getStringLiveData().removeObserver(this);
                    }
                };
                viewModel.getStringLiveData().observe(this, observer);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            INSERT_LOG("downloadDocuments","Exception : "+ex.getMessage());
        }
    }

    private void INSERT_LOG(String methodName,String message){
        try{
            if(viewModel !=null){
                viewModel.insertLog(methodName,message,userId,"","PersonalDiscussionActivity"
                        ,CLIENT_ID,loanType,moduleType);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
