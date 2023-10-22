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
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_POSIDEX;

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
import com.saartak.el.adapter.PosidexAdapter;
import com.saartak.el.database.entity.RawDataTable;
import com.saartak.el.interfce.FragmentInterface;
import com.saartak.el.models.Deliquency.DeliquencyResponseDTO;
import com.saartak.el.models.Posidex.PosidexResponseDTO;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class PosidexFragment extends LOSBaseFragment implements FragmentInterface, PosidexAdapter.POSIDEXInterface {
    private static final String TAG = PosidexFragment.class.getCanonicalName();
    TextView ucicId, ucic_type, tv_Message, tv_error_message;
    Button btn_Posidex;
    CardView cardPosidexData;
    List<RawDataTable> rawDataTableDataList;

    RecyclerView rvPosidexGeneration;
    PosidexAdapter posidexAdapter;
    Boolean isAddressDetail=false,isKYCScreen=false;

    private PosidexFragment.OnFragmentInteractionListener mListener;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    public PosidexFragment() {
        // Required empty public constructor
    }


    String LOAN_TYPE, CLIENT_ID, PROJECT_ID, PRODUCT_ID, SCREEN_ID, SCREEN_NAME, USER_ID, MODULE_TYPE;
    // TODO: Rename and change types

    public static PosidexFragment newInstance(String loanType, String clientId, String projectId, String productId,
                                              String screenNo, String screenName, String userId, String moduleType) {
        PosidexFragment fragment = new PosidexFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_posidex, container, false);
        btn_Posidex = view.findViewById(R.id.btn_posidex);
        tv_error_message = view.findViewById(R.id.tv_error_message);
        rvPosidexGeneration = (RecyclerView) view.findViewById(R.id.rv_generate_posidex);


        btn_Posidex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isAddressDetail==true&&isKYCScreen==true) {
                    if (appHelper.isNetworkAvailable()) {
                        callPosidexService(USER_ID, CLIENT_ID, LOAN_TYPE, MODULE_TYPE);
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

    @Override
    public void generatePosidexCallBack(String ucic_id, List<PosidexResponseDTO.ApiResponse> apiResponse) {

        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            String uniqueId = String.valueOf(System.currentTimeMillis());
            viewModel.getDeliquencyServiceData(ucic_id, CLIENT_ID, MODULE_TYPE, LOAN_TYPE);
            if (viewModel.getDeliquencyResponseDTOLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        DeliquencyResponseDTO deliquencyResponseDTO = (DeliquencyResponseDTO) o;
                        viewModel.getDeliquencyResponseDTOLiveData().removeObserver(this);
                    }
                };
                viewModel.getDeliquencyResponseDTOLiveData().observe(this, observer);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
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
            getAddressDetailRawData();
            getKYCData();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void getRawData() {
        try {
            viewModel.getRawDataByClientIDAndModuleType(SCREEN_NAME_POSIDEX, CLIENT_ID, MODULE_TYPE);
            if (viewModel.getRawTableLiveData() != null) {
                viewModel.getRawTableLiveData().observe(getViewLifecycleOwner(), rawDataTableList -> {
                    for (RawDataTable rawDataTable : rawDataTableList) {
                        String rawData = rawDataTable.getRawdata();
                        PosidexResponseDTO posidexResponseDTO = new Gson().fromJson(rawData, PosidexResponseDTO.class);
                        if (posidexResponseDTO.getErrorMessage() != null && posidexResponseDTO.getErrorMessage().equalsIgnoreCase("")) {
                            rvPosidexGeneration.setHasFixedSize(true);
                            rvPosidexGeneration.setLayoutManager(new GridLayoutManager(getActivity(), 1, RecyclerView.VERTICAL, false));
                            posidexAdapter = new PosidexAdapter(getActivity(), posidexResponseDTO.getApiResponse(), this, appHelper);
                            rvPosidexGeneration.setAdapter(posidexAdapter);
                            rvPosidexGeneration.setVisibility(View.VISIBLE);
                        } else {
                            if(posidexResponseDTO.getErrorMessage()!=null) {
                                tv_error_message.setText(posidexResponseDTO.getErrorMessage());
                                rvPosidexGeneration.setVisibility(View.GONE);
                            }
                        }
                    }
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void callPosidexService(String userId, String clientId, String loanType, String moduleType) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            String uniqueId = String.valueOf(System.currentTimeMillis());
            viewModel.getposidexServiceData(userId, clientId, loanType, uniqueId, moduleType);
            if (viewModel.getposidexResponseDTOLiveData() != null) {
                viewModel.getposidexResponseDTOLiveData().observe(getViewLifecycleOwner(), posidexResponseDTO -> {

                    appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                    if (posidexResponseDTO.getErrorMessage() != null && posidexResponseDTO.getErrorMessage().equalsIgnoreCase("")) {
                        rvPosidexGeneration.setHasFixedSize(true);
                        rvPosidexGeneration.setLayoutManager(new GridLayoutManager(getActivity(), 1, RecyclerView.VERTICAL, false));
                        posidexAdapter = new PosidexAdapter(getActivity(), posidexResponseDTO.getApiResponse(), this, appHelper);
                        rvPosidexGeneration.setAdapter(posidexAdapter);
                        rvPosidexGeneration.setVisibility(View.VISIBLE);
                        tv_error_message.setText("");
                    } else {
                        if(posidexResponseDTO.getErrorMessage()!=null) {
                            tv_error_message.setText(posidexResponseDTO.getErrorMessage());
                            rvPosidexGeneration.setVisibility(View.GONE);
                        }
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


