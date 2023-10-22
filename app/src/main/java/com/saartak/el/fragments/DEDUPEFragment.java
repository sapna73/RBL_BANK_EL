package com.saartak.el.fragments;

import static com.saartak.el.constants.AppConstant.MODULE_TYPE_APPLICANT;
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
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_DEDUPE;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.google.gson.Gson;
import com.saartak.el.R;
import com.saartak.el.adapter.DedupAdapter;
import com.saartak.el.database.entity.RawDataTable;
import com.saartak.el.interfce.FragmentInterface;
import com.saartak.el.models.Dedupe.DedupeResponseDTO;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class DEDUPEFragment extends LOSBaseFragment implements FragmentInterface, DedupAdapter.DEDUPInterface {
    private static final String TAG = DEDUPEFragment.class.getCanonicalName();

    TextView status, matchCount, message, tv_error_message,dedupeStatus;
    Button btnDedupe;
    CardView cardDedupeData;
    RecyclerView rvGenerateDedup;
    DedupAdapter dedupAdapter;
    Boolean isAddressDetail=false,isKYCScreen=false;

    private DEDUPEFragment.OnFragmentInteractionListener mListener;


    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    public DEDUPEFragment() {
        // Required empty public constructor
    }


    String LOAN_TYPE, CLIENT_ID, PROJECT_ID, PRODUCT_ID, SCREEN_ID, SCREEN_NAME, USER_ID, MODULE_TYPE;
    // TODO: Rename and change types

    public static DEDUPEFragment newInstance(String loanType, String clientId, String projectId, String productId,
                                             String screenNo, String screenName, String userId, String moduleType) {
        DEDUPEFragment fragment = new DEDUPEFragment();
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

        View view = inflater.inflate(R.layout.fragment_d_e_d_u_p_e, container, false);


        status = view.findViewById(R.id.status);
        matchCount = view.findViewById(R.id.match_count);
        message = view.findViewById(R.id.message);
        dedupeStatus = view.findViewById(R.id.dedupe_status);


        btnDedupe = view.findViewById(R.id.btn_dedupe);
        cardDedupeData = view.findViewById(R.id.card_dedupe_data);
        tv_error_message = view.findViewById(R.id.tv_error_message);
        rvGenerateDedup = view.findViewById(R.id.rv_generate_dedup);
        cardDedupeData.setVisibility(View.GONE);

        btnDedupe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (appHelper.isNetworkAvailable()) {
                    if(isAddressDetail==true&&isKYCScreen==true) {
                        callDedupeService(USER_ID, CLIENT_ID, LOAN_TYPE, MODULE_TYPE);
                    }else {
                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, MODULE_TYPE+" Address Or KYC Details Is Empty");
                    }
                } else {
                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                            "Please check your internet connection and try again");
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

    @Override
    public void generateDedupCallBack(String ucic_id, List<DedupeResponseDTO.ApiResponse.GetCustomerResponse.Results.CustomerDetails> apiResponse) {

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
            getAddressDetailRawData();
            getKYCData();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void getRawData() {
        try {
            viewModel.getRawDataByClientIDAndModuleType(SCREEN_NAME_DEDUPE, CLIENT_ID, MODULE_TYPE);
            if (viewModel.getRawTableLiveData() != null) {

                viewModel.getRawTableLiveData().observe(getViewLifecycleOwner(), rawDataTableList -> {
                    for (RawDataTable rawDataTable : rawDataTableList) {
                        String rawData = rawDataTable.getRawdata();
                        DedupeResponseDTO dedupeResponseDTO = new Gson().fromJson(rawData, DedupeResponseDTO.class);
                        if (dedupeResponseDTO.getErrorMessage()!=null&&dedupeResponseDTO.getErrorMessage().equalsIgnoreCase("")) {
                            cardDedupeData.setVisibility(View.VISIBLE);
                            try {
                                status.setText(dedupeResponseDTO.getApiResponse().getGetCustomerResponse().getMetadata().getStatus());
                                matchCount.setText(dedupeResponseDTO.getApiResponse().getGetCustomerResponse().getMetadata().getMatchcount());
                                if(dedupeResponseDTO.getApiResponse().getGetCustomerResponse().getMetadata().getMatchcount().equalsIgnoreCase("0")){
                                    dedupeStatus.setText("Not Match");
                                }else {
                                    dedupeStatus.setText("Match");
                                }
                                message.setText(dedupeResponseDTO.getApiResponse().getGetCustomerResponse().getMetadata().getMessage());
                                rvGenerateDedup.setHasFixedSize(true);
                                rvGenerateDedup.setLayoutManager(new GridLayoutManager(getActivity(), 1, RecyclerView.VERTICAL, false));
                                dedupAdapter = new DedupAdapter(getActivity(), dedupeResponseDTO.getApiResponse().getGetCustomerResponse().getResults().getCustomerDetails(), this, appHelper);
                                rvGenerateDedup.setAdapter(dedupAdapter);
                                if(Integer.valueOf(dedupeResponseDTO.getApiResponse().getGetCustomerResponse().getMetadata().getMatchcount())>=1){
                                    rvGenerateDedup.setVisibility(View.VISIBLE);
                                }else {
                                    rvGenerateDedup.setVisibility(View.GONE);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }else {
                            tv_error_message.setVisibility(View.VISIBLE);
                            if(dedupeResponseDTO!=null)
                                tv_error_message.setText(dedupeResponseDTO.getErrorMessage());
                        }
                    }
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void callDedupeService(String userId, String clientId, String loanType, String moduleType) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            String uniqueId = String.valueOf(System.currentTimeMillis());
            viewModel.getDedupeServiceData(userId, clientId, loanType, moduleType);
            if (viewModel.getDedupeResponseDTOLiveData() != null) {
                viewModel.getDedupeResponseDTOLiveData().observe(getViewLifecycleOwner(),dedupeResponseDTO  -> {
                    appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                    if (dedupeResponseDTO.getErrorMessage()!=null&&dedupeResponseDTO.getErrorMessage().equalsIgnoreCase("")) {
                        cardDedupeData.setVisibility(View.VISIBLE);
                        try {
                            status.setText(dedupeResponseDTO.getApiResponse().getGetCustomerResponse().getMetadata().getStatus());
                            matchCount.setText(dedupeResponseDTO.getApiResponse().getGetCustomerResponse().getMetadata().getMatchcount());
                            if(dedupeResponseDTO.getApiResponse().getGetCustomerResponse().getMetadata().getMatchcount().equalsIgnoreCase("0")){
                                dedupeStatus.setText("Not Match");
                            }else {
                                dedupeStatus.setText("Match");
                            }
                            message.setText(dedupeResponseDTO.getApiResponse().getGetCustomerResponse().getMetadata().getMessage());
                            rvGenerateDedup.setHasFixedSize(true);
                            rvGenerateDedup.setLayoutManager(new GridLayoutManager(getActivity(), 1, RecyclerView.VERTICAL, false));
                            dedupAdapter = new DedupAdapter(getActivity(), dedupeResponseDTO.getApiResponse().getGetCustomerResponse().getResults().getCustomerDetails(), this, appHelper);
                            rvGenerateDedup.setAdapter(dedupAdapter);
                            if(Integer.valueOf(dedupeResponseDTO.getApiResponse().getGetCustomerResponse().getMetadata().getMatchcount())>=1){
                                rvGenerateDedup.setVisibility(View.VISIBLE);
                            }else {
                                rvGenerateDedup.setVisibility(View.GONE);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }else {
                        tv_error_message.setVisibility(View.VISIBLE);
                        if(dedupeResponseDTO!=null)
                            tv_error_message.setText(dedupeResponseDTO.getErrorMessage());
                    }

                });
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