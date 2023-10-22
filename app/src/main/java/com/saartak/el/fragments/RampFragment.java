package com.saartak.el.fragments;


import static com.saartak.el.activities.LOSBaseActivity.moduleType;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_APPLICANT;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_LEAD;
import static com.saartak.el.constants.AppConstant.PARAM_CLIENT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_PROJECT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_SCREEN_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_SCREEN_NO;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_ADDRESS_DETAIL;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_APPLICANT_KYC;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_COAPPLICANT_ADDRESS_DETAIL;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_CO_APPLICANT_KYC;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_LEAD;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_RAMP;
import static com.saartak.el.constants.AppConstant.UCIC_ID_APPLICANT;
import static com.saartak.el.constants.AppConstant.UCIC_ID_CO_APPLICANT;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_UNABLE_TO_GET_RESPONSE;
import static com.saartak.el.dynamicui.constants.ParametersConstant.FIELD_NAME_UPDATE;
import static com.saartak.el.dynamicui.constants.ParametersConstant.RADIO_BUTTON_ITEM_SENP;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_APPLICATION_DATE_OF_BIRTH;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_CONTAINS_AGE;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_CONTAINS_DOB;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_CUSTOMER_TYPE;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_DIRECTOR;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_FIRST_NAME;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_FULL_NAME;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_GENDER;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_HUF;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_LAST_NAME;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_LLP;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_MIDDLE_NAME;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_PAN;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_PAN_STATUS;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_PARTNER;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_PARTNERSHIP;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_PRIVATE_LIMITED;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_PROPRIETORSHIP;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_PUBLIC_LIMITED;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_SAVE_BUTTON;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_TITLE;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_TYPE_OF_PROFESSION;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.google.gson.Gson;
import com.saartak.el.R;
import com.saartak.el.database.entity.DynamicUITable;
import com.saartak.el.database.entity.RawDataTable;
import com.saartak.el.interfce.FragmentInterface;
import com.saartak.el.models.ParameterInfo;
import com.saartak.el.models.Posidex.PosidexResponseDTO;
import com.saartak.el.models.Ramp.RampResponseDTO;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class RampFragment extends LOSBaseFragment implements FragmentInterface {
    private static final String TAG = RampFragment.class.getCanonicalName();

    TextView caseId, priority, tv_screened_for, tv_userId, tv_totalresult, tv_caseStatus, tv_error_message;
    Button btn_Ramp;
    CardView cardRampData;
    String fullName = "", dob = "";
    Boolean isAddressDetail=false,isKYCScreen=false;

    private RampFragment.OnFragmentInteractionListener mListener;


    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    public RampFragment() {
        // Required empty public constructor
    }


    String LOAN_TYPE, CLIENT_ID, PROJECT_ID, PRODUCT_ID, SCREEN_ID, SCREEN_NAME, USER_ID, MODULE_TYPE;
    // TODO: Rename and change types

    public static RampFragment newInstance(String loanType, String clientId, String projectId, String productId,
                                           String screenNo, String screenName, String userId, String moduleType) {
        RampFragment fragment = new RampFragment();
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

        View view = inflater.inflate(R.layout.fragment_ramp, container, false);

        caseId = view.findViewById(R.id.caseId);
        priority = view.findViewById(R.id.priority);
        tv_screened_for = view.findViewById(R.id.tv_screened_for);
        tv_userId = view.findViewById(R.id.tv_userId);
        tv_totalresult = view.findViewById(R.id.tv_totalresult);
        tv_caseStatus = view.findViewById(R.id.tv_caseStatus);

        btn_Ramp = view.findViewById(R.id.btn_Ramp);
        cardRampData = view.findViewById(R.id.card_ramp_data);
        tv_error_message = view.findViewById(R.id.tv_error_message);
        cardRampData.setVisibility(View.GONE);

        btn_Ramp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isAddressDetail==true&&isKYCScreen==true) {
                    if (appHelper.isNetworkAvailable()) {
                        callRampService(USER_ID, CLIENT_ID, LOAN_TYPE, MODULE_TYPE);
                    } else {
                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                "Please check your internet connection and try again");
                    }
                }else {
                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, MODULE_TYPE+" Address Or KYC Details Is Empty");
                }
               /* if(PRODUCT_ID==PRODUCT_ID_UCL){
                    getLeadDropDownBranchNameServer(USER_ID,PRODUCT_ID,LOAN_NAME_UCL,MODULE_TYPE);}
                else {
                    getLeadDropDownBranchNameServer(USER_ID,PRODUCT_ID,LOAN_NAME_TWL,MODULE_TYPE);
                }*/
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
    }

    @Override
    public void fragmentBecameVisible() {

        try {
            getName();
            getRawData();
            getAddressDetailRawData();
            getKYCData();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void getName() {
        ArrayList<HashMap<String, Object>> hashMapList = new ArrayList<>();
        try {
            if (moduleType.equalsIgnoreCase(MODULE_TYPE_APPLICANT)) {
                viewModel.getRawDataByClientIDAndModuleType(SCREEN_NAME_APPLICANT_KYC, CLIENT_ID, MODULE_TYPE_APPLICANT);
            } else {
                viewModel.getRawDataByClientIDAndModuleType(SCREEN_NAME_CO_APPLICANT_KYC, CLIENT_ID, "CoApplicant1");
            }
            if (viewModel.getRawTableLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<RawDataTable> rawDataTableList = (List<RawDataTable>) o;
                        viewModel.getRawTableLiveData().removeObserver(this);
                        if (rawDataTableList != null && rawDataTableList.size() > 0) {
                            for (RawDataTable rawDataTable : rawDataTableList) {
                                HashMap<String, Object> hashMap = setKeyValueForObject(rawDataTable);
                                hashMapList.add(hashMap);
                            }
                            if (hashMapList != null && hashMapList.size() > 0) {
                                // TODO: Already saved data
                                HashMap<String, Object> hashMap = hashMapList.get(0);
                                if (hashMap != null && hashMap.size() > 0) {
                                    if (hashMap.containsKey(TAG_NAME_FIRST_NAME)) {
                                        fullName = hashMap.get(TAG_NAME_FIRST_NAME).toString();
                                    }
                                    if (hashMap.containsKey(TAG_NAME_FULL_NAME)) {
                                        fullName = hashMap.get(TAG_NAME_FULL_NAME).toString();
                                    }
                                    if (hashMap.containsKey(TAG_NAME_APPLICATION_DATE_OF_BIRTH)) {
                                        dob = hashMap.get(TAG_NAME_APPLICATION_DATE_OF_BIRTH).toString();
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

    private void getRawData() {
        try {
            viewModel.getRawDataByClientIDAndModuleType(SCREEN_NAME_RAMP, CLIENT_ID, MODULE_TYPE);
            if (viewModel.getRawTableLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<RawDataTable> rawDataTableList = (List<RawDataTable>) o;
                        viewModel.getRawTableLiveData().removeObserver(this);
                        if (rawDataTableList != null && rawDataTableList.size() > 0) {

                            for (RawDataTable rawDataTable : rawDataTableList) {
                                String rawData = rawDataTable.getRawdata();
                                RampResponseDTO rampResponseDTO = new Gson().fromJson(rawData, RampResponseDTO.class);

                                if (rampResponseDTO.getErrorMessage() != null && rampResponseDTO.getResponseMessage().equalsIgnoreCase("")) {
                                    cardRampData.setVisibility(View.VISIBLE);
                                    tv_error_message.setVisibility(View.GONE);
                                    caseId.setText(CLIENT_ID);
                                    tv_userId.setText(USER_ID);

                                    try {
                                        if (rampResponseDTO.getApiResponse().getRampResponse().getStatus() != null) {
                                            tv_totalresult.setText(rampResponseDTO.getApiResponse().getRampResponse().getStatus());
                                            tv_caseStatus.setText(rampResponseDTO.getApiResponse().getRampResponse().getCaseStatus());
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    tv_screened_for.setText(fullName + System.lineSeparator() + dob);
                                } else {
                                    if (rampResponseDTO.getErrorMessage() != null) {
                                        tv_error_message.setText(rampResponseDTO.getErrorMessage());
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

    private void callRampService(String userId, String clientId, String loanType, String moduleType) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            String uniqueId = String.valueOf(System.currentTimeMillis());
            viewModel.getRampServiceData(userId, clientId, loanType, moduleType);
            if (viewModel.getRampResponseDTOLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        RampResponseDTO rampResponseDTO = (RampResponseDTO) o;
                        viewModel.getRampResponseDTOLiveData().removeObserver(this);
                        if (rampResponseDTO.getErrorMessage() != null && rampResponseDTO.getErrorMessage().equalsIgnoreCase("")) {
                            if (rampResponseDTO.getApiResponse() != null) {
                                cardRampData.setVisibility(View.VISIBLE);
                                tv_error_message.setVisibility(View.GONE);
                                caseId.setText(CLIENT_ID);
                                tv_userId.setText(USER_ID);

                                try {
                                    tv_totalresult.setText(rampResponseDTO.getApiResponse().getRampResponse().getStatus());
                                    tv_caseStatus.setText(rampResponseDTO.getApiResponse().getRampResponse().getCaseStatus());
                                    tv_screened_for.setText(fullName + System.lineSeparator() + dob);
                                    getRawData();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            tv_error_message.setVisibility(View.VISIBLE);
                            if (rampResponseDTO != null) {
                                tv_error_message.setText(rampResponseDTO.getErrorMessage());
                            } else {
                                tv_error_message.setText(ERROR_MESSAGE_UNABLE_TO_GET_RESPONSE);
                            }

                        }
                    }
                };
                viewModel.getRampResponseDTOLiveData().observe(this, observer);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
        }
    }

    private void getAddressDetailRawData() {
        try {
            if(MODULE_TYPE.equalsIgnoreCase(MODULE_TYPE_APPLICANT)){
                viewModel.getRawDataByClientIDAndModuleType(SCREEN_NAME_ADDRESS_DETAIL, CLIENT_ID, MODULE_TYPE);
            }else {
                viewModel.getRawDataByClientIDAndModuleType(SCREEN_NAME_COAPPLICANT_ADDRESS_DETAIL, CLIENT_ID, MODULE_TYPE);
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
                                    if (hashMap != null && hashMap.size() > 0) {
                                        isAddressDetail=true;
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

    private void getKYCData() {
        try {
            if(MODULE_TYPE.equalsIgnoreCase(MODULE_TYPE_APPLICANT)){
                viewModel.getRawDataByClientIDAndModuleType(SCREEN_NAME_APPLICANT_KYC, CLIENT_ID, MODULE_TYPE);
            }else {
                viewModel.getRawDataByClientIDAndModuleType(SCREEN_NAME_CO_APPLICANT_KYC, CLIENT_ID, MODULE_TYPE);
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
                                    if (hashMap != null && hashMap.size() > 0) {
                                        isKYCScreen=true;
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

}