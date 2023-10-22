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
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_ENACH;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.saartak.el.R;
import com.saartak.el.database.entity.RawDataTable;
import com.saartak.el.interfce.FragmentInterface;
import com.saartak.el.models.ENach.ENachResponseDTO;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class DigitalDocFragment extends LOSBaseFragment implements FragmentInterface {

    TextView tv_error_message;
    Button btn_HypothecationDeed,btn_ApplicationForm;

    private DigitalDocFragment.OnFragmentInteractionListener mListener;


    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;
    public DigitalDocFragment() {
        // Required empty public constructor
    }


    String LOAN_TYPE,CLIENT_ID,PROJECT_ID,PRODUCT_ID,SCREEN_ID,SCREEN_NAME,USER_ID,MODULE_TYPE;
    // TODO: Rename and change types

    public static DigitalDocFragment newInstance(String loanType, String clientId, String projectId, String productId,
                                            String screenNo, String screenName, String userId, String moduleType) {
        DigitalDocFragment fragment = new DigitalDocFragment();
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

        View view = inflater.inflate(R.layout.fragment_digital_doc, container, false);

        tv_error_message =view.findViewById(R.id.tv_error_message);

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

        try{
            getRawData();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void getRawData() {
        ArrayList<HashMap<String,Object>> hashMapList=new ArrayList<>();
        try {
            viewModel.getRawDataByClientIDAndModuleType(SCREEN_NAME_ENACH,CLIENT_ID,MODULE_TYPE_APPLICANT);
            if (viewModel.getRawTableLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<RawDataTable> rawDataTableList = (List<RawDataTable>) o;
                        viewModel.getRawTableLiveData().removeObserver(this);
                        if(rawDataTableList !=null && rawDataTableList.size()>0){

                            for(RawDataTable rawDataTable:rawDataTableList){
                                String rawData=rawDataTable.getRawdata();
                                ENachResponseDTO eNachResponseDTO=new Gson().fromJson(rawData, ENachResponseDTO.class);

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
//    private void eNachServiceData(String userId,String clientId,String loanType,String moduleType) {
//        try{
//            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
//            String uniqueId= String.valueOf(System.currentTimeMillis());
//            viewModel.getENachServiceData(userId,clientId,loanType,moduleType);
//            if( viewModel.getENachResponseDTOLiveData() !=null){
//                Observer observer=new Observer() {
//                    @Override
//                    public void onChanged(Object o) {
//                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
//                        ENachResponseDTO eNachResponseDTO = (ENachResponseDTO) o;
//                        viewModel.getENachResponseDTOLiveData().removeObserver(this);
//                    }
//                };
//                viewModel.getENachResponseDTOLiveData().observe(this,observer);
//            }
//        }catch (Exception ex){
//            ex.printStackTrace();
//            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
//        }
//    }

}