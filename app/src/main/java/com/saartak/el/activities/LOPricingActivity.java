package com.saartak.el.activities;

import static com.saartak.el.constants.AppConstant.LOAN_NAME_EL;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_APPLICANT;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_GENERATE_CIBIL;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_LOAN_PROPOSAL_WITH_NOMINEE;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_ID;
import static com.saartak.el.constants.AppConstant.PARAM_CLIENT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_ROLE_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_NAME;
import static com.saartak.el.constants.AppConstant.PROJECT_ID_EL;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_ADDRESS_DETAIL;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_APPLICANT_LOAN_PROPOSAL;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_APPLICANT_TWO_WHEELER_DETAIL;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_CIBIL_STATUS;
import static com.saartak.el.constants.AppConstant.setKeyValueForObject;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_COMMUNICATION_CITY;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_REQUESTED_LOAN_AMOUNT;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_TWO_WHEELER_ON_ROAD_PRICE;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.google.gson.Gson;
import com.saartak.el.R;
import com.saartak.el.database.entity.CIBILTable;
import com.saartak.el.database.entity.RawDataTable;
import com.saartak.el.models.CibilResponseModel;
import com.saartak.el.models.CibilTenureMonthsModel;
import com.saartak.el.models.CreditApprovalScreenPricing.CreditApprovalScreenPricingTable;
import com.saartak.el.models.GetPricingInbox.GetPricingInboxResponseTable;
import com.saartak.el.models.LoanAmountWisePricingDefaultValues.LoanAmountWisePricingDefaultValuesResponseTable;
import com.saartak.el.models.ProcessPricingWF.ProcessPricingWFResponseTable;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class LOPricingActivity extends LOSBaseActivity {

    private static final String TAG = LOPricingActivity.class.getCanonicalName();
    String loanType;
    TextView txt_loan_amount, txt_roi, txt_processing_fee, txt_document_charge,txt_city;
    EditText ed_roi, ed_processingFee, ed_documentCharges;
    Button submit, button_rejected, button_approve;
    LinearLayout ll_accept_decline;
    String totalLoanAmount = "", city = "", approvelLoanAmount;
    String getROI, getpf, getDc, getCity, stpAcceptedORDeclined = "", cibilLoanAmount = "";
    String submitValidationLoanAmount = "", submitValidationRoi;
    String pricingValidationMSG = "Pricing has already been approved. Are you sure want to resubmit it ?", pricingStatus = "";
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lopricing);

        txt_loan_amount = (TextView) findViewById(R.id.txt_loan_amount);
        txt_city = (TextView) findViewById(R.id.txt_city);
        txt_roi = (TextView) findViewById(R.id.txt_roi);
        txt_processing_fee = (TextView) findViewById(R.id.txt_processing_fee);
        txt_document_charge = (TextView) findViewById(R.id.txt_document_charge);
        ll_accept_decline = (LinearLayout) findViewById(R.id.ll_accept_decline);
        button_rejected = (Button) findViewById(R.id.button_rejected);
        button_approve = (Button) findViewById(R.id.button_approve);

        ed_roi = (EditText) findViewById(R.id.ed_roi);
        ed_processingFee = (EditText) findViewById(R.id.ed_processingFee);
        ed_documentCharges = (EditText) findViewById(R.id.ed_documentCharges);
        submit = (Button) findViewById(R.id.submit);


        //ed_roi.addTextChangedListener(new DecimalTextWatcher(ed_roi));
        //ed_processingFee.addTextChangedListener(new DecimalTextWatcher(ed_processingFee));

        ed_documentCharges.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        button_approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitLoPricingcreen(userId, "1", getDc, productId, "1", branchGSTcode, userName, CLIENT_ID, totalLoanAmount, getCity, getROI, getpf);
            }
        });
        button_rejected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitLoPricingcreen(userId, "2", getDc, productId, "1", branchGSTcode, userName, CLIENT_ID, totalLoanAmount, getCity, getROI, getpf);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ((pricingStatus.equalsIgnoreCase("2") || pricingStatus.equalsIgnoreCase("1"))) {
                    pricingValidationMSG = (pricingStatus.equalsIgnoreCase("2")) ? pricingValidationMSG.replace("has already been approved", "has been rejected") : pricingValidationMSG;
                    appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons(pricingValidationMSG, new ConfirmationDialog.ActionCallback() {
                        @Override
                        public void onAction() {
                            submitLoPricingcreen(userId, "0", ed_documentCharges.getText().toString(), productId, "1", branchGSTcode, userName, CLIENT_ID, totalLoanAmount, city, ed_roi.getText().toString(), ed_processingFee.getText().toString());
                        }
                    });
                } else {
                    submitLoPricingcreen(userId, "0", ed_documentCharges.getText().toString(), productId, "1", branchGSTcode, userName, CLIENT_ID, totalLoanAmount, city, ed_roi.getText().toString(), ed_processingFee.getText().toString());
                }

            }
        });

        configureDagger();
        configureViewModel();
    }

    private void configureDagger() {
        AndroidInjection.inject(this);
    }

    public void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DynamicUIViewModel.class);

        loanType = getIntent().getStringExtra(PARAM_LOAN_TYPE);
        moduleType = getIntent().getStringExtra(PARAM_MODULE_TYPE);
        userName = getIntent().getStringExtra(PARAM_USER_NAME);
        userId = getIntent().getStringExtra(PARAM_USER_ID);
        branchId = getIntent().getStringExtra(PARAM_BRANCH_ID);
        CLIENT_ID = getIntent().getStringExtra(PARAM_CLIENT_ID);
        productId = getIntent().getStringExtra(PARAM_PRODUCT_ID);
        branchGSTcode = getIntent().getStringExtra(PARAM_BRANCH_GST_CODE);
        roleName = getIntent().getStringExtra(PARAM_ROLE_NAME);


        if (roleName != null && roleName.equalsIgnoreCase("LOAN OFFICER")) {
            getCreditApprovalScreenPricing(CLIENT_ID, "1", productId);
            getCibilSTPStatus(CLIENT_ID, loanType);
            getRawDatafromCommunicationCity();
            getDataFromOtherThenLoLogin();
            ll_accept_decline.setVisibility(View.GONE);
            submit.setVisibility(View.VISIBLE);
        } else {
            getCreditApprovalScreenPricing(CLIENT_ID, "1", productId);
            getLoanAmountWisePricingDefaultValues(totalLoanAmount, getCity, "1", productId);
            getDataFromOtherThenLoLogin();
            ll_accept_decline.setVisibility(View.VISIBLE);
            submit.setVisibility(View.GONE);
        }

    }


    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void getCreditApprovalScreenPricing(String clientId, String projectId, String productId) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.getCreditApprovalScreenPricing(clientId, projectId, productId);
            if (viewModel.getGetCreditApprovalScreenPricingTableLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        ArrayList<CreditApprovalScreenPricingTable> data = (ArrayList<CreditApprovalScreenPricingTable>) o;
                        viewModel.getGetCreditApprovalScreenPricingTableLiveData().removeObserver(this);
                        if (data != null && data.size() > 0) {
                            for (CreditApprovalScreenPricingTable creditApprovalScreenPricingTable : data) {
                                approvelLoanAmount = creditApprovalScreenPricingTable.getLoanAmount();
                            }
                        }
                    }
                };
                viewModel.getGetCreditApprovalScreenPricingTableLiveData().observe(this, observer);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
        }
    }

    public void getLoanAmountWisePricingDefaultValues(String amount, String city, String projectId, String productId) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.getLoanAmountWisePricingDefaultValues(amount, city, projectId, productId);
            if (viewModel.getLoanAmountWisePricingDefaultValuesResponseTableLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        ArrayList<LoanAmountWisePricingDefaultValuesResponseTable> data = (ArrayList<LoanAmountWisePricingDefaultValuesResponseTable>) o;
                        viewModel.getLoanAmountWisePricingDefaultValuesResponseTableLiveData().removeObserver(this);
                        if (data != null && data.size() > 0) {
                            for (LoanAmountWisePricingDefaultValuesResponseTable loanAmountWisePricingDefaultValuesResponseTable : data) {
                                if (!TextUtils.isEmpty(approvelLoanAmount) && Float.valueOf(approvelLoanAmount) > 0) {
                                    txt_loan_amount.setText("₹ " + approvelLoanAmount);
                                } else {
                                    txt_loan_amount.setText("₹ " + totalLoanAmount);
                                }
                                txt_city.setText(city);
                                txt_roi.setText(loanAmountWisePricingDefaultValuesResponseTable.getDefaultROI() + " %");
                                txt_processing_fee.setText(loanAmountWisePricingDefaultValuesResponseTable.getDefaultProcessingFee() + " %");
                                txt_document_charge.setText("₹ " + loanAmountWisePricingDefaultValuesResponseTable.getDefaultDocumentationCharges());
                            }
                        }
                    }
                };
                viewModel.getLoanAmountWisePricingDefaultValuesResponseTableLiveData().observe(this, observer);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
        }
    }

    public void submitLoPricingcreen(String userId, String status, String requestedDocCharges, String productId,
                                     String projectId, String branchId, String customerName, String customerId,
                                     String requestedLoanAmount_VehicleCost, String city, String requestedROI, String requestedPF) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.getProcessPricingWF(userId, status, requestedDocCharges, productId, projectId, branchId, customerName, customerId, requestedLoanAmount_VehicleCost, city, requestedROI, requestedPF);
            if (viewModel.getProcessPricingWFResponseTableLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        ArrayList<ProcessPricingWFResponseTable> data = (ArrayList<ProcessPricingWFResponseTable>) o;
                        viewModel.getProcessPricingWFResponseTableLiveData().removeObserver(this);
                        if (data != null && data.size() > 0) {
                            ArrayList<String> arrayList = new ArrayList<String>();
                            for (ProcessPricingWFResponseTable processPricingWFResponseTable : data) {
                                //appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, processPricingWFResponseTable.getStatus());
                                arrayList.add(processPricingWFResponseTable.getStatus());
                            }
                            if (data.get(0).getStatusCode() == 0) {
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, arrayList.get(0));
                            } else {
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS,
                                        arrayList.get(0), new ConfirmationDialog.ActionCallback() {
                                            @Override
                                            public void onAction() {
                                                if (status.equalsIgnoreCase("0")) {
                                                    LOPricingActivity.super.onBackPressed();
                                                } else {
                                                    Intent application = new Intent(LOPricingActivity.this, LeadToOtherThenLOPricingActivity.class);
                                                    application.putExtra(PARAM_LOAN_TYPE, loanType);
                                                    application.putExtra(PARAM_USER_NAME, userName);
                                                    application.putExtra(PARAM_BRANCH_ID, branchId);
                                                    application.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                                                    application.putExtra(PARAM_USER_ID, userId);
                                                    application.putExtra(PARAM_CLIENT_ID, customerId);
                                                    application.putExtra(PARAM_PRODUCT_ID, productId);
                                                    application.putExtra(PARAM_ROLE_NAME, roleName);
                                                    LOPricingActivity.this.startActivity(application);
                                                    finish();
                                                }

                                            }
                                        });
                            }


                        }
                    }
                };
                viewModel.getProcessPricingWFResponseTableLiveData().observe(this, observer);
                appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
        }

    }

    private void getRawDatafromCommunicationCity() {
        try {
            viewModel.getRawDataByClientIDAndModuleType(SCREEN_NAME_ADDRESS_DETAIL, CLIENT_ID, MODULE_TYPE_APPLICANT);
            if (viewModel.getRawTableLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<RawDataTable> rawDataTableList = (List<RawDataTable>) o;
                        viewModel.getRawTableLiveData().removeObserver(this);
                        if (rawDataTableList != null && rawDataTableList.size() > 0) {

                            for (RawDataTable rawDataTable : rawDataTableList) {
                                if (rawDataTable != null) {
                                    HashMap<String, Object> hashMap = setKeyValueForObject(rawDataTable);
                                    if (hashMap != null && hashMap.size() > 0) {
                                        if (hashMap.containsKey(TAG_NAME_COMMUNICATION_CITY)) {
                                            city = hashMap.get(TAG_NAME_COMMUNICATION_CITY).toString();
                                            if (city != "") {
                                                getRawDataFromLoanProposal();
                                            } else {

                                                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                                                "City Is Empty Please Make City in Address Detail Screen", new ConfirmationDialog.ActionCallback() {
                                                                    @Override
                                                                    public void onAction() {
                                                                        LOPricingActivity.super.onBackPressed();
                                                                    }
                                                                });        }
                                                }, 1000);
                                                Toast.makeText(getApplicationContext(),"City Is Empty Please Make City in Address Detail Screen",Toast.LENGTH_LONG).show();

                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                    "City Is Empty Please Make City in Address Detail Screen", new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {
                                            LOPricingActivity.super.onBackPressed();
                                        }
                                    });
                        }
                    }
                };
                viewModel.getRawTableLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void getRawDataFromLoanProposal() {

        try {
            if (loanType.equalsIgnoreCase(LOAN_NAME_EL)) {
                viewModel.getRawDataByClientIDAndModuleType(SCREEN_NAME_APPLICANT_LOAN_PROPOSAL, CLIENT_ID, MODULE_TYPE_LOAN_PROPOSAL_WITH_NOMINEE);
            } else {
                viewModel.getRawDataByClientIDAndModuleType(SCREEN_NAME_APPLICANT_TWO_WHEELER_DETAIL, CLIENT_ID, MODULE_TYPE_LOAN_PROPOSAL_WITH_NOMINEE);
            }
            if (viewModel.getRawTableLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<RawDataTable> rawDataTableList = (List<RawDataTable>) o;
                        viewModel.getRawTableLiveData().removeObserver(this);
                        if (rawDataTableList != null && rawDataTableList.size() > 0) {
                            for (RawDataTable rawDataTable : rawDataTableList) {
                                if (rawDataTable != null) {
                                    HashMap<String, Object> hashMap = setKeyValueForObject(rawDataTable);
                                    if (!TextUtils.isEmpty(approvelLoanAmount) && Float.valueOf(approvelLoanAmount) > 0) {
                                        totalLoanAmount = approvelLoanAmount;
                                    } else {
                                        if (loanType.equalsIgnoreCase(LOAN_NAME_EL)) {
                                            if (hashMap != null && hashMap.size() > 0) {
                                                if (hashMap.containsKey(TAG_NAME_REQUESTED_LOAN_AMOUNT)) {
                                                    if (stpAcceptedORDeclined != null && stpAcceptedORDeclined.equalsIgnoreCase("p") && !TextUtils.isEmpty(cibilLoanAmount)) {
                                                        totalLoanAmount = cibilLoanAmount;
                                                    } else {
                                                        totalLoanAmount = hashMap.get(TAG_NAME_REQUESTED_LOAN_AMOUNT).toString();
                                                    }

                                                    if (city != "" && totalLoanAmount != "") {
                                                        getLoanAmountWisePricingDefaultValues(totalLoanAmount, city, "1", productId);
                                                    }
                                                }
                                            }
                                        } else {
                                            if (hashMap != null && hashMap.size() > 0) {
                                                if (hashMap.containsKey(TAG_NAME_TWO_WHEELER_ON_ROAD_PRICE)) {
                                                    if (stpAcceptedORDeclined != null && stpAcceptedORDeclined.equalsIgnoreCase("p") && !TextUtils.isEmpty(cibilLoanAmount)) {
                                                        totalLoanAmount = cibilLoanAmount;
                                                    } else {
                                                        totalLoanAmount = hashMap.get(TAG_NAME_TWO_WHEELER_ON_ROAD_PRICE).toString();
                                                    }
                                                    if (city != "" && totalLoanAmount != "") {
                                                        getLoanAmountWisePricingDefaultValues(totalLoanAmount, city, "1", productId);
                                                    } else {
                                                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                                                        "On Road Price Is Empty Please Make On Road Price in Two Wheeler Screen", new ConfirmationDialog.ActionCallback() {
                                                                            @Override
                                                                            public void onAction() {
                                                                                LOPricingActivity.super.onBackPressed();
                                                                            }
                                                                        });
                                                            }
                                                        }, 1000);
                                                        Toast.makeText(getApplicationContext(),"On Road Price Is Empty Please Make On Road Price in Two Wheeler Screen",Toast.LENGTH_LONG).show();
                                                    }
                                                } else {
                                                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                                                    "On Road Price Is Empty Please Make On Road Price in Two Wheeler Screen", new ConfirmationDialog.ActionCallback() {
                                                                        @Override
                                                                        public void onAction() {
                                                                            LOPricingActivity.super.onBackPressed();
                                                                        }
                                                                    });
                                                        }
                                                    }, 1000);
                                                    Toast.makeText(getApplicationContext(),"On Road Price Is Empty Please Make On Road Price in Two Wheeler Screen",Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        }
                                    }

                                }
                            }
                        } else {
                            if (loanType.equalsIgnoreCase("EL")) {
                                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                                "Loan Amount Is Empty Please Make Loan Amout in Loan Proposal Screen", new ConfirmationDialog.ActionCallback() {
                                                    @Override
                                                    public void onAction() {
                                                        LOPricingActivity.super.onBackPressed();
                                                    }
                                                });
                                    }
                                }, 1000);

                            } else {
                                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                                "On Road Price Is Empty Please Make On Road Price in Two Wheeler Screen", new ConfirmationDialog.ActionCallback() {
                                                    @Override
                                                    public void onAction() {
                                                        LOPricingActivity.super.onBackPressed();
                                                    }
                                                });
                                    }
                                }, 1000);

                            }

                        }
                    }
                };
                viewModel.getRawTableLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void getDataFromOtherThenLoLogin() {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.getPricingInbox("0", CLIENT_ID, branchGSTcode, roleName, PROJECT_ID_EL, productId);
            if (viewModel.getGetPricingInboxResponseTableLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        ArrayList<GetPricingInboxResponseTable> getPricingInboxResponseList = (ArrayList<GetPricingInboxResponseTable>) o;
                        viewModel.getGetPricingInboxResponseTableLiveData().removeObserver(this);

                        if (getPricingInboxResponseList != null && getPricingInboxResponseList.size() > 0) {
                            getLoanAmountWisePricingDefaultValues(getPricingInboxResponseList.get(0).getReqLoanAmount(), getPricingInboxResponseList.get(0).getCity(), "1", productId);
                            for (GetPricingInboxResponseTable data : getPricingInboxResponseList) {
                                if (data.getStatus().equalsIgnoreCase("0")) {
                                    ed_roi.setClickable(false);
                                    ed_roi.setFocusable(false);
                                    ed_roi.setFocusableInTouchMode(false);
                                    ed_documentCharges.setClickable(false);
                                    ed_documentCharges.setFocusable(false);
                                    ed_documentCharges.setFocusableInTouchMode(false);
                                    ed_processingFee.setClickable(false);
                                    ed_processingFee.setFocusable(false);
                                    ed_processingFee.setFocusableInTouchMode(false);
                                }
                                totalLoanAmount = data.getReqLoanAmount();
                                txt_loan_amount.setText("₹ " + data.getReqLoanAmount());
                                txt_city.setText(data.getCity());
                                ed_roi.setText(data.getReqROI());
                                ed_processingFee.setText(data.getReqPF());
                                ed_documentCharges.setText(data.getReqDocCharges());
                                submitValidationLoanAmount = data.getReqLoanAmount();
                                submitValidationRoi = data.getReqROI();
                                pricingStatus = data.getStatus();
                                getCity = data.getCity();
                            }
                        }
                    }
                };
                viewModel.getGetPricingInboxResponseTableLiveData().observe(this, observer);
                appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
        }
    }

    private void getCibilSTPStatus(String clientId, String loanType) {
        try {
            viewModel.getRawDataByClientIDAndModuleType(SCREEN_NAME_CIBIL_STATUS, clientId, MODULE_TYPE_GENERATE_CIBIL);
            if (viewModel.getRawTableLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<RawDataTable> rawDataTableList = (List<RawDataTable>) o;
                        viewModel.getRawTableLiveData().removeObserver(this);
                        if (rawDataTableList != null && rawDataTableList.size() > 0) {
                            for (RawDataTable rawDataTable : rawDataTableList) {
                                String rawData = rawDataTable.getRawdata();
                                CibilResponseModel cibilResponseModel = new Gson().fromJson(rawData, CibilResponseModel.class);
                                if (cibilResponseModel != null) {
                                    if (cibilResponseModel.getFlag().equalsIgnoreCase("P")) {
                                        stpAcceptedORDeclined = cibilResponseModel.getFlag();
                                        getCIBILTableListFromDB(clientId, loanType);
                                    }
                                }
                            }
                        }
                    }
                };
                viewModel.getRawTableLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void getCIBILTableListFromDB(String clientId, String loanType) {
        try {
            viewModel.getCIBILTableListFromDB(clientId, loanType);
            if (viewModel.getCibilTableLiveDataList() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<CIBILTable> cibilTableListFromDB = (List<CIBILTable>) o;
                        viewModel.getCibilTableLiveDataList().removeObserver(this);
                        if (cibilTableListFromDB != null && cibilTableListFromDB.size() > 0) {
                            CibilTenureMonthsModel cibilLoanAmountAndTM = new Gson().fromJson(cibilTableListFromDB.get(0).getSolutionSetInstanceId(), CibilTenureMonthsModel.class);
                            try {
                                if (cibilLoanAmountAndTM != null) {
                                    cibilLoanAmount = cibilLoanAmountAndTM.getRequestedLoanAmount();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };
                viewModel.getCibilTableLiveDataList().observe(this, observer);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}