package com.saartak.el.fragments;

import static com.saartak.el.constants.AppConstant.MODULE_TYPE_APPLICANT;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_BUSINESS;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_SALARY;
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
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_BUSINESS_ADDRESS_PROOF;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_COAPPLICANT_ADDRESS_DETAIL;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_CO_APPLICANT_KYC;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_CPV;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_OFFICE_ADDRESS_PROOF;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_UNABLE_TO_GET_RESPONSE;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.google.gson.Gson;
import com.saartak.el.R;
import com.saartak.el.database.entity.RawDataTable;
import com.saartak.el.interfce.FragmentInterface;
import com.saartak.el.models.CPV.CPVResponseDTO;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class CPVFragment extends LOSBaseFragment implements FragmentInterface {

    TextView response, tv_error_message;
    Button btn_enach;
    CardView card_encah_data;

    Boolean isAddressDetail=false,isKYCScreen=false;

    private CPVFragment.OnFragmentInteractionListener mListener;


    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    public CPVFragment() {
        // Required empty public constructor
    }


    String LOAN_TYPE, CLIENT_ID, PROJECT_ID, PRODUCT_ID, SCREEN_ID, SCREEN_NAME, USER_ID, MODULE_TYPE;
    // TODO: Rename and change types

    public static CPVFragment newInstance(String loanType, String clientId, String projectId, String productId,
                                          String screenNo, String screenName, String userId, String moduleType) {
        CPVFragment fragment = new CPVFragment();
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

        View view = inflater.inflate(R.layout.fragment_c_p_v, container, false);

        response = view.findViewById(R.id.response);
        btn_enach = view.findViewById(R.id.btn_enach);
        card_encah_data = view.findViewById(R.id.card_encah_data);
        tv_error_message = view.findViewById(R.id.tv_error_message);
        card_encah_data.setVisibility(View.GONE);

        btn_enach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isAddressDetail == true && isKYCScreen == true) {
                    if (appHelper.isNetworkAvailable()) {
                        cpvServiceData(USER_ID, CLIENT_ID, LOAN_TYPE, MODULE_TYPE);
                    } else {
                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                "Please check your internet connection and try again");
                    }
                }else {
                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, MODULE_TYPE+" Address Or KYC Details Is Empty");
                }
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
            getRawData();
            getKYCData();
            getAddressDetailRawData();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void getRawData() {
        try {
            viewModel.getRawDataByClientIDAndModuleType(SCREEN_NAME_CPV, CLIENT_ID, MODULE_TYPE);
            if (viewModel.getRawTableLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<RawDataTable> rawDataTableList = (List<RawDataTable>) o;
                        viewModel.getRawTableLiveData().removeObserver(this);
                        if (rawDataTableList != null && rawDataTableList.size() > 0) {

                            for (RawDataTable rawDataTable : rawDataTableList) {
                                String rawData = rawDataTable.getRawdata();
                                CPVResponseDTO cpvResponseDTO = new Gson().fromJson(rawData, CPVResponseDTO.class);
                                card_encah_data.setVisibility(View.VISIBLE);
                                if (cpvResponseDTO.getErrorMessage() != null && cpvResponseDTO.getErrorMessage().equalsIgnoreCase("")) {
                                        response.setText(cpvResponseDTO.getResponseMessage());
                                        tv_error_message.setText("");
                                } else {
                                    if (cpvResponseDTO.getErrorMessage() != null){
                                        tv_error_message.setText(cpvResponseDTO.getErrorMessage());
                                    }else {
                                        tv_error_message.setText(ERROR_MESSAGE_UNABLE_TO_GET_RESPONSE);
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

    private void cpvServiceData(String userId, String clientId, String loanType, String moduleType) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            String uniqueId = String.valueOf(System.currentTimeMillis());
            viewModel.getCPVServiceData(userId, clientId, loanType, moduleType);
            if (viewModel.getCpvResponseDTOLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        CPVResponseDTO cpvResponseDTO = (CPVResponseDTO) o;
                        viewModel.getCpvResponseDTOLiveData().removeObserver(this);
                        card_encah_data.setVisibility(View.VISIBLE);
                        if (cpvResponseDTO.getErrorMessage() != null && cpvResponseDTO.getErrorMessage().equalsIgnoreCase("")) {
                            response.setText(cpvResponseDTO.getResponseMessage());
                            tv_error_message.setText("");
                            getRawData();
                        } else {
                            if (cpvResponseDTO.getErrorMessage() != null){
                                tv_error_message.setText(cpvResponseDTO.getErrorMessage());
                            }else {
                                 tv_error_message.setText(ERROR_MESSAGE_UNABLE_TO_GET_RESPONSE);
                            }
                        }
                    }
                };
                viewModel.getCpvResponseDTOLiveData().observe(this, observer);
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
            }else if(MODULE_TYPE.equalsIgnoreCase(MODULE_TYPE_SALARY)){
                isKYCScreen=true;
                viewModel.getRawDataByClientIDAndModuleType(SCREEN_NAME_OFFICE_ADDRESS_PROOF, CLIENT_ID, MODULE_TYPE);
            }else if(MODULE_TYPE.equalsIgnoreCase(MODULE_TYPE_BUSINESS)){
                isKYCScreen=true;
                viewModel.getRawDataByClientIDAndModuleType(SCREEN_NAME_BUSINESS_ADDRESS_PROOF, CLIENT_ID, MODULE_TYPE);
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