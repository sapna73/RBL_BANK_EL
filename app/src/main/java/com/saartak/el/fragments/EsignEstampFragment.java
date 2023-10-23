package com.saartak.el.fragments;

import static com.saartak.el.constants.AppConstant.LOAN_NAME_EL;
import static com.saartak.el.constants.AppConstant.PARAM_CLIENT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_PROJECT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_SCREEN_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_SCREEN_NO;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_CIBIL_STATUS;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_CIBIL_SUCCESS_FAILED;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_ESIGN_ESTAMP;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_ESIGN_ESTAMP_STATUS;
import static com.saartak.el.dynamicui.constants.ParametersConstant.MESSAGE_CIBIL_SUCCESS;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.google.gson.Gson;
import com.saartak.el.R;
import com.saartak.el.adapter.EsignEstampAdapter;
import com.saartak.el.database.entity.CIBILTable;
import com.saartak.el.database.entity.RawDataTable;
import com.saartak.el.interfce.FragmentInterface;
import com.saartak.el.models.CibilResponseModel;
import com.saartak.el.models.SuccessAndFailurModel;
import com.saartak.el.models.CibilTenureMonthsModel;
import com.saartak.el.models.CreditApprovalScreenPricing.CreditApprovalScreenPricingTable;
import com.saartak.el.models.ESignEstamp.ESignEStampResponseDTO;
import com.saartak.el.models.ESignEstamp.ESignEStampStatusResponseDTO;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class EsignEstampFragment extends LOSBaseFragment implements FragmentInterface, EsignEstampAdapter.ESIGNESTAMPSTATUSInterface {

    TextView txt_loan_amount, txt_roi, txt_tenure, txt_eligibleEmi,extTransationId;
    Button btnEsignEstamp, checkStatus;
    List<CIBILTable> cibilTableDB = null;
    LinearLayout statusResponse, pricing;
    RecyclerView rv_esignestampStatus;
    TextView status;
    EsignEstampAdapter esignEstampAdapter;
    String uniquetxnid = "", snsValue = "", currentStage = "",currentStageId="", stpAcceptedORDeclined = "",stpAcceptedORDeclinedStatus="",cibilSuccessAndFailur="";
    private OnFragmentInteractionListener mListener;
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    public EsignEstampFragment() {
        // Required empty public constructor
    }


    String LOAN_TYPE, CLIENT_ID, PROJECT_ID, PRODUCT_ID, SCREEN_ID, SCREEN_NAME, USER_ID, MODULE_TYPE;
    // TODO: Rename and change types

    public static EsignEstampFragment newInstance(String loanType, String clientId, String projectId, String productId,
                                                  String screenNo, String screenName, String userId, String moduleType) {
        EsignEstampFragment fragment = new EsignEstampFragment();
        Bundle args = new Bundle();
        args.putString(PARAM_LOAN_TYPE, loanType);
        args.putString(PARAM_CLIENT_ID, clientId);
        args.putString(PARAM_PROJECT_ID, projectId);
        args.putString(PARAM_PRODUCT_ID, productId);
        args.putString(PARAM_SCREEN_NO, screenNo);
        args.putString(PARAM_SCREEN_NAME, screenName);
        args.putString(PARAM_USER_ID, userId);
        args.putString(PARAM_MODULE_TYPE, moduleType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            LOAN_TYPE = getArguments().getString(PARAM_LOAN_TYPE);
            CLIENT_ID = getArguments().getString(PARAM_CLIENT_ID);
            PROJECT_ID = getArguments().getString(PARAM_PROJECT_ID);
            PRODUCT_ID = getArguments().getString(PARAM_PRODUCT_ID);
            SCREEN_ID = getArguments().getString(PARAM_SCREEN_NO);
            SCREEN_NAME = getArguments().getString(PARAM_SCREEN_NAME);
            USER_ID = getArguments().getString(PARAM_USER_ID);
            MODULE_TYPE = getArguments().getString(PARAM_MODULE_TYPE);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_esign_estamp, container, false);

        txt_loan_amount = (TextView) view.findViewById(R.id.txt_loan_amount);
        txt_roi = (TextView) view.findViewById(R.id.txt_roi);
        txt_tenure = (TextView) view.findViewById(R.id.txt_tenure);
        txt_eligibleEmi = (TextView) view.findViewById(R.id.txt_eligibleEmi);
        btnEsignEstamp = (Button) view.findViewById(R.id.btn_esignEstamp);

        checkStatus = (Button) view.findViewById(R.id.checkStatus);
        statusResponse = (LinearLayout) view.findViewById(R.id.statusResponse);
        pricing = (LinearLayout) view.findViewById(R.id.pricing);
        rv_esignestampStatus = (RecyclerView) view.findViewById(R.id.rv_esignestampStatus);
        extTransationId = (TextView) view.findViewById(R.id.extTransationId);

        status = (TextView) view.findViewById(R.id.status);

        SharedPreferences mPrefs = this.getActivity().getSharedPreferences("CURRENTSTAGE", 0);
        currentStage = mPrefs.getString("currentstage", "");
        currentStageId = mPrefs.getString("currentstageId", "");
        btnEsignEstamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (appHelper.isNetworkAvailable()) {
                    try {
                        if ((!LOAN_TYPE.equalsIgnoreCase(LOAN_NAME_EL) && stpAcceptedORDeclined.equalsIgnoreCase("P")&&stpAcceptedORDeclinedStatus.equalsIgnoreCase("1"))) {
                            if (!TextUtils.isEmpty(txt_roi.getText().toString()) && !TextUtils.isEmpty(txt_eligibleEmi.getText().toString()) &&
                                    Float.valueOf(txt_roi.getText().toString()) > 0
                                    && Float.valueOf(txt_eligibleEmi.getText().toString()) > 0) {
                                getEsignEStampData(CLIENT_ID, txt_tenure.getText().toString(), txt_loan_amount.getText().toString(), txt_roi.getText().toString(), cibilTableDB);
                            } else {
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                        "Please Initiate Pricing", new ConfirmationDialog.ActionCallback() {
                                            @Override
                                            public void onAction() {

                                            }
                                        });
                            }
                        } else {
                            if (!TextUtils.isEmpty(txt_roi.getText().toString()) && !TextUtils.isEmpty(txt_eligibleEmi.getText().toString()) &&
                                    Float.valueOf(txt_roi.getText().toString()) > 0
                                    && Float.valueOf(txt_eligibleEmi.getText().toString()) > 0) {
                                getEsignEStampData(CLIENT_ID, txt_tenure.getText().toString(), txt_loan_amount.getText().toString(), txt_roi.getText().toString(), cibilTableDB);
                            } else {
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                        "Please Initiate Pricing", new ConfirmationDialog.ActionCallback() {
                                            @Override
                                            public void onAction() {

                                            }
                                        });
                            }
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                } else {
                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                            "Please check your internet connection and try again");
                }
            }
        });

        checkStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getEsignEStampStatusData(CLIENT_ID, uniquetxnid);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.configureDagger();
        this.configureViewModel();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    public void configureDagger() {
        AndroidSupportInjection.inject(this);
    }


    public void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DynamicUIViewModel.class);
        getCibilSuccessOrFailurStatus();
        getCibilSTPStatus();
        getEsignEstampInitiationRawData();
    }

    private void getCibilSuccessOrFailurStatus() {
        try {
            viewModel.getRawDataByClientIDAndModuleType(SCREEN_NAME_CIBIL_SUCCESS_FAILED, CLIENT_ID, MODULE_TYPE);
            if (viewModel.getRawTableLiveData() != null) {
                viewModel.getRawTableLiveData().observe(getViewLifecycleOwner(), rawDataTableList -> {
                    for (RawDataTable rawDataTable : rawDataTableList) {
                        String rawData = rawDataTable.getRawdata();
                        SuccessAndFailurModel cibilSuccessAndFailurModel = new Gson().fromJson(rawData, SuccessAndFailurModel.class);
                        if (cibilSuccessAndFailurModel != null) {

                                if (cibilSuccessAndFailurModel.getIsCibilSuccessAndFailur().equalsIgnoreCase(MESSAGE_CIBIL_SUCCESS)) {
                                    btnEsignEstamp.setClickable(true);
                                    btnEsignEstamp.setEnabled(true);
                                } else {
                                    btnEsignEstamp.setClickable(false);
                                    btnEsignEstamp.setEnabled(false);

                                }
                        }

                    }
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void fragmentBecameVisible() {
        try {
            getCibilSuccessOrFailurStatus();
             getCibilSTPStatus();
             getEsignEstampInitiationRawData();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void getCibilSTPStatus() {

        try {
            viewModel.getRawDataByClientIDAndModuleType(SCREEN_NAME_CIBIL_STATUS, CLIENT_ID, MODULE_TYPE);
            if (viewModel.getRawTableLiveData() != null) {
                viewModel.getRawTableLiveData().observe(getViewLifecycleOwner(), rawDataTableList -> {
                    for (RawDataTable rawDataTable : rawDataTableList) {
                        String rawData = rawDataTable.getRawdata();
                        CibilResponseModel cibilResponseModel = new Gson().fromJson(rawData, CibilResponseModel.class);
                        if (cibilResponseModel != null) {
                            if (cibilResponseModel.getFlag().equalsIgnoreCase("P")) {
                                stpAcceptedORDeclined = cibilResponseModel.getFlag();
                                stpAcceptedORDeclinedStatus = cibilResponseModel.getIsAccepctOrDecline();
                                getCIBILTableListFromDB(stpAcceptedORDeclined,stpAcceptedORDeclinedStatus);
                            } else if (currentStage.equalsIgnoreCase("Document Execution")) {
                                getCIBILTableListFromDB("","");
                            }
                        }

                    }
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void getCreditApprovalScreenPricing(String clientId, String projectId, String productId, String stpAcceptedORDeclined) {
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
                                if (stpAcceptedORDeclined.equalsIgnoreCase("P")) {
                                    txt_roi.setText(creditApprovalScreenPricingTable.getRoi());
                                    txt_eligibleEmi.setText(creditApprovalScreenPricingTable.getEligibleEMI());
                                    status.setVisibility(View.GONE);
                                    pricing.setVisibility(View.VISIBLE);
                                    btnEsignEstamp.setVisibility(View.VISIBLE);
                                } else {
                                    if (!creditApprovalScreenPricingTable.getLoanAmount().equalsIgnoreCase("0")) {
                                        txt_loan_amount.setText(creditApprovalScreenPricingTable.getLoanAmount());
                                        txt_roi.setText(creditApprovalScreenPricingTable.getRoi());
                                        txt_tenure.setText(creditApprovalScreenPricingTable.getTenure());
                                        txt_eligibleEmi.setText(creditApprovalScreenPricingTable.getEligibleEMI());
                                        status.setVisibility(View.GONE);
                                        pricing.setVisibility(View.VISIBLE);
                                        btnEsignEstamp.setVisibility(View.VISIBLE);
                                    } else {
                                        status.setVisibility(View.VISIBLE);
                                        pricing.setVisibility(View.GONE);
                                        btnEsignEstamp.setVisibility(View.GONE);
                                    }
                                }
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

    private void getCIBILTableListFromDB(String stpAcceptedORDeclined,String stpAcceptedORDeclinedStatus) {
        try {
            viewModel.getCIBILTableListFromDB(CLIENT_ID, LOAN_TYPE);
            if (viewModel.getCibilTableLiveDataList() != null) {
                Observer getDocumentUploadDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<CIBILTable> cibilTableListFromDB = (List<CIBILTable>) o;
                        viewModel.getCibilTableLiveDataList().removeObserver(this);
                        if (cibilTableListFromDB != null && cibilTableListFromDB.size() > 0) {
                            cibilTableDB = cibilTableListFromDB;
                            CibilTenureMonthsModel cibilLoanAmountAndTM = new Gson().fromJson(cibilTableListFromDB.get(0).getSolutionSetInstanceId(), CibilTenureMonthsModel.class);

                            try {
                                if ((stpAcceptedORDeclined.equalsIgnoreCase("P")&&stpAcceptedORDeclinedStatus.equalsIgnoreCase("1"))) {
                                    txt_loan_amount.setText(cibilLoanAmountAndTM.getRequestedLoanAmount());
                                    txt_tenure.setText(cibilLoanAmountAndTM.getTenureMonths());
                                    status.setVisibility(View.GONE);
                                    pricing.setVisibility(View.VISIBLE);
                                    btnEsignEstamp.setVisibility(View.VISIBLE);
                                    getCreditApprovalScreenPricing(CLIENT_ID, PROJECT_ID, PRODUCT_ID, stpAcceptedORDeclined);
                                } else {
                                    getCreditApprovalScreenPricing(CLIENT_ID, PROJECT_ID, PRODUCT_ID, "");
                                }


                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            getCreditApprovalScreenPricing(CLIENT_ID, PROJECT_ID, PRODUCT_ID, "");
                        }
                    }
                };
                viewModel.getCibilTableLiveDataList().observe(this, getDocumentUploadDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void getEsignEStampData(String clientId, String tenure, String loanAmount, String rateOfInterest, List<CIBILTable> cibilTableDB) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            String uniqueId = String.valueOf(System.currentTimeMillis());
            viewModel.getEsignEStampData(clientId, tenure, loanAmount, rateOfInterest, cibilTableDB);
            if (viewModel.getESignEStampResponseDTOLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        ESignEStampResponseDTO eSignEStampResponseDTO = (ESignEStampResponseDTO) o;
                        viewModel.getESignEStampResponseDTOLiveData().removeObserver(this);
                        if (eSignEStampResponseDTO.getErrorMessage() != null && eSignEStampResponseDTO.getErrorMessage().equalsIgnoreCase("")) {
                            if (eSignEStampResponseDTO.getApiResponse().getStatuscode().equalsIgnoreCase("1")) {
                                btnEsignEstamp.setClickable(false);
                                btnEsignEstamp.setEnabled(false);
                                uniquetxnid = eSignEStampResponseDTO.getApiResponse().getUniquetxnid();
                                checkStatus.setVisibility(View.VISIBLE);
                                getEsignEStampStatusData(CLIENT_ID, uniquetxnid);
                            }else if (eSignEStampResponseDTO.getApiResponse().getStatuscode().equalsIgnoreCase("2")) {
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, eSignEStampResponseDTO.getResponseMessage());
                                uniquetxnid = eSignEStampResponseDTO.getApiResponse().getUniquetxnid();
                                checkStatus.setVisibility(View.VISIBLE);
                                getEsignEStampStatusData(CLIENT_ID, uniquetxnid);
                            } else {
                                checkStatus.setVisibility(View.GONE);
                            }


                        } else {
                            if (eSignEStampResponseDTO.getErrorMessage() != null) {
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR, eSignEStampResponseDTO.getErrorMessage());
                            }
                        }
                    }
                };
                viewModel.getESignEStampResponseDTOLiveData().observe(this, observer);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
        }
    }

    private void getEsignEStampStatusData(String clientId, String uniquetxnid) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.getEsignEStampStatusData(clientId, uniquetxnid);
            if (viewModel.getESignEStampStatusResponseDTOLiveData() != null) {
                viewModel.getESignEStampStatusResponseDTOLiveData().observe(getViewLifecycleOwner(), eSignEStampStatusResponseDTO -> {
                    appHelper.getDialogHelper().getLoadingDialog().closeDialog();

                    try {
                        if (eSignEStampStatusResponseDTO.getErrorMessage() != null && eSignEStampStatusResponseDTO.getErrorMessage().equalsIgnoreCase("")) {
                            statusResponse.setVisibility(View.VISIBLE);
                            checkStatus.setVisibility(View.VISIBLE);
                            rv_esignestampStatus.setHasFixedSize(true);
                            rv_esignestampStatus.setLayoutManager(new GridLayoutManager(getActivity(), 1, RecyclerView.VERTICAL, false));
                            esignEstampAdapter = new EsignEstampAdapter(getActivity(), eSignEStampStatusResponseDTO.getApiResponse().getDocStatus().getStatus(), this, appHelper);
                            rv_esignestampStatus.setAdapter(esignEstampAdapter);
                            extTransationId.setText(eSignEStampStatusResponseDTO.getApiResponse().getExistingtxnid());
                        } else {
                            if (eSignEStampStatusResponseDTO.getErrorMessage() != null) {
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR, eSignEStampStatusResponseDTO.getErrorMessage());
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
        }
    }

    private void getEsignEstampInitiationRawData() {
        try {
            viewModel.getRawDataByClientIDAndModuleType(SCREEN_NAME_ESIGN_ESTAMP, CLIENT_ID, MODULE_TYPE);
            if (viewModel.getRawTableLiveData() != null) {
                viewModel.getRawTableLiveData().observe(getViewLifecycleOwner(), rawDataTableList -> {
                    for (RawDataTable rawDataTable : rawDataTableList) {
                        String rawData = rawDataTable.getRawdata();
                        ESignEStampResponseDTO eSignEStampStatusResponseDTO = new Gson().fromJson(rawData, ESignEStampResponseDTO.class);
                        if (eSignEStampStatusResponseDTO.getErrorMessage() != null && eSignEStampStatusResponseDTO.getErrorMessage() != "") {

                            try {
                                if (eSignEStampStatusResponseDTO.getApiResponse().getStatuscode().equalsIgnoreCase("1")) {
//                                    btnEsignEstamp.setClickable(false);
//                                    btnEsignEstamp.setEnabled(false);
                                    checkStatus.setVisibility(View.VISIBLE);
                                    statusResponse.setVisibility(View.VISIBLE);
                                    uniquetxnid = eSignEStampStatusResponseDTO.getApiResponse().getUniquetxnid();
                                    getRawDataEsignEStampStatus();
                                } else {
                                    uniquetxnid = eSignEStampStatusResponseDTO.getApiResponse().getUniquetxnid();
                                    btnEsignEstamp.setClickable(true);
                                    btnEsignEstamp.setEnabled(true);
                                    checkStatus.setVisibility(View.GONE);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void getRawDataEsignEStampStatus() {
        try {
            viewModel.getRawDataByClientIDAndModuleType(SCREEN_NAME_ESIGN_ESTAMP_STATUS, CLIENT_ID, MODULE_TYPE);
            if (viewModel.getRawTableLiveData() != null) {
                viewModel.getRawTableLiveData().observe(getViewLifecycleOwner(), rawDataTableList -> {
                    for (RawDataTable rawDataTable : rawDataTableList) {
                        String rawData = rawDataTable.getRawdata();
                        ESignEStampStatusResponseDTO eSignEStampStatusResponseDTO = new Gson().fromJson(rawData, ESignEStampStatusResponseDTO.class);
                        try {
                            if (eSignEStampStatusResponseDTO.getErrorMessage() != null && eSignEStampStatusResponseDTO.getErrorMessage() != "") {
                                rv_esignestampStatus.setHasFixedSize(true);
                                rv_esignestampStatus.setLayoutManager(new GridLayoutManager(getActivity(), 1, RecyclerView.VERTICAL, false));
                                esignEstampAdapter = new EsignEstampAdapter(getActivity(), eSignEStampStatusResponseDTO.getApiResponse().getDocStatus().getStatus(), this, appHelper);
                                rv_esignestampStatus.setAdapter(esignEstampAdapter);
                                extTransationId.setText(eSignEStampStatusResponseDTO.getApiResponse().getExistingtxnid());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}