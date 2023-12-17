package com.saartak.el.fragments;

import static com.saartak.el.constants.AppConstant.LOAN_NAME_EL;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_GENERATE_CIBIL;
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
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_ENACH;
import static com.saartak.el.dynamicui.constants.ParametersConstant.MESSAGE_CIBIL_SUCCESS;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.saartak.el.R;
import com.saartak.el.database.entity.CIBILTable;
import com.saartak.el.database.entity.RawDataTable;
import com.saartak.el.interfce.FragmentInterface;
import com.saartak.el.models.CibilResponseModel;
import com.saartak.el.models.SuccessAndFailurModel;
import com.saartak.el.models.CibilTenureMonthsModel;
import com.saartak.el.models.CreditApprovalScreenPricing.CreditApprovalScreenPricingTable;
import com.saartak.el.models.ENach.ENachResponseDTO;
import com.saartak.el.models.ENach.MNachResponseTable;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class ENachFragment extends LOSBaseFragment implements FragmentInterface {

    TextView txt_loan_amount, txt_roi, txt_tenure, txt_eligibleEmi;
    TextView response, tv_error_message,status;
    Button btn_enach;
    CardView card_encah_data;
    SwipeRefreshLayout swipe_to_refresh;
    LinearLayout pricing;
    String requestedLoanAmount = "", tenureMonths = "", emiAmount = "",rateOfInterst = "", currentStage = "",stpAcceptedORDeclined = "",stpAcceptedORDeclinedStatus="",currentStageId="";

    private ENachFragment.OnFragmentInteractionListener mListener;


    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    public ENachFragment() {
        // Required empty public constructor
    }


    String LOAN_TYPE, CLIENT_ID, PROJECT_ID, PRODUCT_ID, SCREEN_ID, SCREEN_NAME, USER_ID, MODULE_TYPE;
    // TODO: Rename and change types

    public static ENachFragment newInstance(String loanType, String clientId, String projectId, String productId,
                                            String screenNo, String screenName, String userId, String moduleType) {
        ENachFragment fragment = new ENachFragment();
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

        View view = inflater.inflate(R.layout.fragment_e_nach, container, false);

        response = view.findViewById(R.id.response);
        txt_loan_amount = view.findViewById(R.id.txt_loan_amount);
        txt_roi = (TextView) view.findViewById(R.id.txt_roi);
        txt_tenure = (TextView) view.findViewById(R.id.txt_tenure);
        txt_eligibleEmi = (TextView) view.findViewById(R.id.txt_eligibleEmi);
        pricing = (LinearLayout) view.findViewById(R.id.pricing);
        btn_enach = view.findViewById(R.id.btn_enach);
        card_encah_data = view.findViewById(R.id.card_encah_data);
        tv_error_message = view.findViewById(R.id.tv_error_message);
        swipe_to_refresh = view.findViewById(R.id.swipe_to_refresh);
        status = (TextView) view.findViewById(R.id.statusmsg);
        card_encah_data.setVisibility(View.GONE);

        SharedPreferences mPrefs = this.getActivity().getSharedPreferences("CURRENTSTAGE", 0);
        currentStage = mPrefs.getString("currentstage", "");
        currentStageId = mPrefs.getString("currentstageId", "");

        btn_enach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (appHelper.isNetworkAvailable()) {
                    try {
                        if (!TextUtils.isEmpty(requestedLoanAmount) && Float.valueOf(requestedLoanAmount) > 0 &&
                                !TextUtils.isEmpty(tenureMonths) && Float.valueOf(tenureMonths) > 0 &&
                                !TextUtils.isEmpty(emiAmount) && Float.valueOf(emiAmount) > 0&&
                                !TextUtils.isEmpty(rateOfInterst) && Float.valueOf(rateOfInterst) > 0) {
                            eNachServiceData(USER_ID, CLIENT_ID, LOAN_TYPE, MODULE_TYPE, requestedLoanAmount, tenureMonths, emiAmount);
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                    "Please Initiate Pricing", new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {

                                        }
                                    });
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
       /* swipe_to_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                eNACH_GetRazorpayFetchToken(CLIENT_ID);
            }
        });*/

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
        getRawData();
    }

    @Override
    public void fragmentBecameVisible() {
        getCibilSuccessOrFailurStatus();
        getCibilSTPStatus();
        getRawData();
        try {
            if(LOAN_TYPE.equalsIgnoreCase(LOAN_NAME_EL)&&currentStage.equalsIgnoreCase("Document Execution")){
                btn_enach.setVisibility(View.VISIBLE);
                pricing.setVisibility(View.VISIBLE);
                status.setVisibility(View.GONE);
            }else{
                if ((stpAcceptedORDeclined.equalsIgnoreCase("p")&&stpAcceptedORDeclinedStatus.equalsIgnoreCase("1"))||currentStage.equalsIgnoreCase("Document Execution")) {
                    btn_enach.setVisibility(View.VISIBLE);
                    pricing.setVisibility(View.VISIBLE);
                    status.setVisibility(View.GONE);
                } else {
                    status.setVisibility(View.VISIBLE);
                    btn_enach.setVisibility(View.GONE);
                    pricing.setVisibility(View.GONE);
                }
            }
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
                                stpAcceptedORDeclined=cibilResponseModel.getFlag();
                                stpAcceptedORDeclinedStatus=cibilResponseModel.getIsAccepctOrDecline();
                                try {
                                    if(LOAN_TYPE.equalsIgnoreCase(LOAN_NAME_EL)&&currentStage.equalsIgnoreCase("Document Execution")){
                                        btn_enach.setVisibility(View.VISIBLE);
                                        pricing.setVisibility(View.VISIBLE);
                                        status.setVisibility(View.GONE);
                                    }else{
                                        if ((stpAcceptedORDeclined.equalsIgnoreCase("p")&&stpAcceptedORDeclinedStatus.equalsIgnoreCase("1"))||currentStage.equalsIgnoreCase("Document Execution")) {
                                            btn_enach.setVisibility(View.VISIBLE);
                                            pricing.setVisibility(View.VISIBLE);
                                            status.setVisibility(View.GONE);
                                        } else {
                                            status.setVisibility(View.VISIBLE);
                                            btn_enach.setVisibility(View.GONE);
                                            pricing.setVisibility(View.GONE);
                                        }
                                    }
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                                getCIBILTableListFromDB(stpAcceptedORDeclined);
                            }else if (currentStage.equalsIgnoreCase("Document Execution")) {
                                getCIBILTableListFromDB("");
                            }

                        }

                    }
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void getCIBILTableListFromDB(String stpAcceptedORDeclined) {
        try {
            viewModel.getCIBILTableListFromDB(CLIENT_ID, LOAN_TYPE);
            if (viewModel.getCibilTableLiveDataList() != null) {
                Observer getDocumentUploadDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<CIBILTable> cibilTableListFromDB = (List<CIBILTable>) o;
                        viewModel.getCibilTableLiveDataList().removeObserver(this);
                        if (cibilTableListFromDB != null && cibilTableListFromDB.size() > 0) {
                            if (cibilTableListFromDB.get(0).getSolutionSetInstanceId() != null) {

                                CibilTenureMonthsModel cibilLoanAmountAndTM = null;
                                try {
                                    cibilLoanAmountAndTM = new Gson().fromJson(cibilTableListFromDB.get(0).getSolutionSetInstanceId(), CibilTenureMonthsModel.class);
                                    if (LOAN_TYPE.equalsIgnoreCase("TWL")&&(stpAcceptedORDeclined.equalsIgnoreCase("P")&&stpAcceptedORDeclinedStatus.equalsIgnoreCase("1"))) {
                                        try {
                                            if (cibilLoanAmountAndTM.getRequestedLoanAmount() != null) {
                                                requestedLoanAmount = cibilLoanAmountAndTM.getRequestedLoanAmount();
                                                tenureMonths = cibilLoanAmountAndTM.getTenureMonths();
                                                //emiAmount = cibilLoanAmountAndTM.getEmiAmount();
                                                getCreditApprovalScreenPricing(CLIENT_ID, PROJECT_ID, PRODUCT_ID,tenureMonths,requestedLoanAmount);
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    } else {
                                        getCreditApprovalScreenPricing(CLIENT_ID, PROJECT_ID, PRODUCT_ID,"","");
                                    }
                                } catch (JsonSyntaxException e) {
                                    e.printStackTrace();
                                }
                            }

                        }
                    }
                };
                viewModel.getCibilTableLiveDataList().observe(this, getDocumentUploadDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void getRawData() {
        try {
            viewModel.getRawDataByClientIDAndModuleType(SCREEN_NAME_ENACH, CLIENT_ID, MODULE_TYPE_GENERATE_CIBIL);
            if (viewModel.getRawTableLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<RawDataTable> rawDataTableList = (List<RawDataTable>) o;
                        viewModel.getRawTableLiveData().removeObserver(this);
                        if (rawDataTableList != null && rawDataTableList.size() > 0) {

                            for (RawDataTable rawDataTable : rawDataTableList) {
                                String rawData = rawDataTable.getRawdata();
                                ENachResponseDTO eNachResponseDTO = new Gson().fromJson(rawData, ENachResponseDTO.class);
                                card_encah_data.setVisibility(View.VISIBLE);
                                if (eNachResponseDTO.getApiResponse() != "") {
                                    response.setText("Email and SMS sent to registered mail id and Mobile Number");
                                } else {
                                    if(eNachResponseDTO.getResponseMessage()!=null){
                                    response.setText(eNachResponseDTO.getResponseMessage() + " " + eNachResponseDTO.getErrorMessage());}
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

    private void eNachServiceData(String userId, String clientId, String loanType, String moduleType, String requestLoanAmount, String tenureMonths, String emiAmount) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            String uniqueId = String.valueOf(System.currentTimeMillis());
            viewModel.getENachServiceData(userId, clientId, loanType, moduleType, requestLoanAmount, tenureMonths, emiAmount);
            if (viewModel.getENachResponseDTOLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        ENachResponseDTO eNachResponseDTO = (ENachResponseDTO) o;
                        viewModel.getENachResponseDTOLiveData().removeObserver(this);
                        card_encah_data.setVisibility(View.VISIBLE);
                        if (eNachResponseDTO.getErrorMessage() != null && eNachResponseDTO.getErrorMessage().equalsIgnoreCase("")) {
                            response.setText("Email and SMS sent to registered mail id and Mobile Number");
                            getRawData();
                        } else {
                            if (eNachResponseDTO.getErrorMessage() != null)
                                response.setText(eNachResponseDTO.getResponseMessage() + " " + eNachResponseDTO.getErrorMessage());
                        }
                    }
                };
                viewModel.getENachResponseDTOLiveData().observe(this, observer);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
        }
    }

    public void eNACH_GetRazorpayFetchToken(String clientId) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.eNACH_GetRazorpayFetchToken(clientId);
            if (viewModel.getMNachResponseTableLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        ArrayList<MNachResponseTable> data = (ArrayList<MNachResponseTable>) o;
                        viewModel.getMNachResponseTableLiveData().removeObserver(this);
                        if (data != null && data.size() > 0) {
                            ArrayList<String> arrayList = new ArrayList<String>();
                            for (MNachResponseTable mNachResponseTable : data) {
                                arrayList.add(mNachResponseTable.getTokenId());
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS, arrayList.get(0));
                                swipe_to_refresh.setRefreshing(false);
                            }
                        }
                    }
                };
                swipe_to_refresh.setRefreshing(false);
                viewModel.getMNachResponseTableLiveData().observe(this, observer);
            }
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
        }
    }

    public void getCreditApprovalScreenPricing(String clientId, String projectId, String productId ,String tenureInMonth,String loanAmount) {
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
                                if(!TextUtils.isEmpty(loanAmount) && Float.valueOf(loanAmount) > 0){
                                    requestedLoanAmount=loanAmount;
                                    tenureMonths=tenureInMonth;
                                    txt_loan_amount.setText(loanAmount);
                                    txt_roi.setText(creditApprovalScreenPricingTable.getRoi());
                                    txt_tenure.setText(tenureInMonth);
                                    txt_eligibleEmi.setText(creditApprovalScreenPricingTable.getEligibleEMI());
                                }else {
                                    requestedLoanAmount = creditApprovalScreenPricingTable.getLoanAmount();
                                    tenureMonths = creditApprovalScreenPricingTable.getTenure();
                                    txt_loan_amount.setText(creditApprovalScreenPricingTable.getLoanAmount());
                                    txt_roi.setText(creditApprovalScreenPricingTable.getRoi());
                                    txt_tenure.setText(creditApprovalScreenPricingTable.getTenure());
                                    txt_eligibleEmi.setText(creditApprovalScreenPricingTable.getEligibleEMI());
                                }
                                emiAmount = creditApprovalScreenPricingTable.getEligibleEMI();
                                rateOfInterst = creditApprovalScreenPricingTable.getRoi();
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
                                    btn_enach.setClickable(true);
                                    btn_enach.setEnabled(true);
                                } else {
                                    btn_enach.setClickable(false);
                                    btn_enach.setEnabled(false);
                                }
                        }
                    }
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}