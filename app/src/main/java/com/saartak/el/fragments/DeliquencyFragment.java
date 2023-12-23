package com.saartak.el.fragments;

import static com.saartak.el.constants.AppConstant.PARAM_CLIENT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_PROJECT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_SCREEN_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_SCREEN_NO;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_DELIQUENCY;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.saartak.el.R;
import com.saartak.el.adapter.DeliquencyAdapter;
import com.saartak.el.database.entity.RawDataTable;
import com.saartak.el.interfce.FragmentInterface;
import com.saartak.el.models.Deliquency.DeliquencyResponseDTO;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class DeliquencyFragment extends LOSBaseFragment implements FragmentInterface, DeliquencyAdapter.DELIQUENCYInterface {
    private static final String TAG = DeliquencyFragment.class.getCanonicalName();
    private DeliquencyFragment.OnFragmentInteractionListener mListener;

    RecyclerView rvDeliquencyGeneration;

    DeliquencyAdapter deliquencyAdapter;
    TextView tvErrorMessage,match_count;
    CardView card_match_count;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    public DeliquencyFragment() {
        // Required empty public constructor
    }


    String LOAN_TYPE, CLIENT_ID, PROJECT_ID, PRODUCT_ID, SCREEN_ID, SCREEN_NAME, USER_ID, MODULE_TYPE;
    // TODO: Rename and change types

    public static DeliquencyFragment newInstance(String loanType, String clientId, String projectId, String productId,
                                                 String screenNo, String screenName, String userId, String moduleType) {
        DeliquencyFragment fragment = new DeliquencyFragment();
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

        View view = inflater.inflate(R.layout.fragment_deliquency, container, false);

        rvDeliquencyGeneration = (RecyclerView) view.findViewById(R.id.rv_generate_deliquency);
        tvErrorMessage = (TextView) view.findViewById(R.id.tv_error_message);
        match_count = (TextView) view.findViewById(R.id.match_count);
        card_match_count = (CardView) view.findViewById(R.id.card_match_count);

        return view;
        //return inflater.inflate(R.layout.fragment_deliquency, container, false);
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
    public void generateDeliquencyCallBack(String ucic_id, List<DeliquencyResponseDTO.ApiResponse> apiResponse) {

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
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


   /* private void getDeliquencyDataFromServer(String userId,String productId,String productName,String moduleType) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            String uniqueId= String.valueOf(System.currentTimeMillis());
            viewModel.getDeliquencyDataFromServer(uniqueId,userId,productId,productName,moduleType);
            if (viewModel.getposidexResponseDTOLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        ArrayList<PosidexResponseDataFromTable> posidexResponseDataFromTable = (ArrayList<PosidexResponseDataFromTable>) o;
                        viewModel.getposidexResponseDTOLiveData().removeObserver(this);

                        if (posidexResponseDataFromTable != null && posidexResponseDataFromTable.size() > 0) {

                        } else {

                        }

                    }
                };
                viewModel.getposidexResponseDTOLiveData().observe(this, observer);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
        }
    }*/

    private void getRawData() {
        try {
            viewModel.getRawDataByClientIDAndModuleType(SCREEN_NAME_DELIQUENCY, CLIENT_ID, MODULE_TYPE);
            if (viewModel.getRawTableLiveData() != null) {
                viewModel.getRawTableLiveData().observe(getViewLifecycleOwner(), rawDataTableList -> {
                    for (RawDataTable rawDataTable : rawDataTableList) {
                        String rawData = rawDataTable.getRawdata();
                        DeliquencyResponseDTO deliquencyResponseDTO = new Gson().fromJson(rawData, DeliquencyResponseDTO.class);
                        if (deliquencyResponseDTO.getErrorMessage() != null && deliquencyResponseDTO.getErrorMessage().equalsIgnoreCase( "")) {
                            card_match_count.setVisibility(View.VISIBLE);
                            match_count.setText(deliquencyResponseDTO.getApiResponse().get(0).getMatchCount());
                            rvDeliquencyGeneration.setHasFixedSize(true);
                            rvDeliquencyGeneration.setLayoutManager(new GridLayoutManager(getActivity(), 1, RecyclerView.VERTICAL, false));
                            deliquencyAdapter = new DeliquencyAdapter(getActivity(), deliquencyResponseDTO.getApiResponse(), this, appHelper);
                            rvDeliquencyGeneration.setAdapter(deliquencyAdapter);
                            tvErrorMessage.setText("");
                        } else {
                            tvErrorMessage.setText(deliquencyResponseDTO.getErrorMessage());
                            card_match_count.setVisibility(View.GONE);
                        }
                    }
                });
                /*Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<RawDataTable> rawDataTableList = (List<RawDataTable>) o;
                        viewModel.getRawTableLiveData().removeObserver(this);
                        if(rawDataTableList !=null && rawDataTableList.size()>0){

                            for(RawDataTable rawDataTable:rawDataTableList){
                                String rawData=rawDataTable.getRawdata();
                                DeliquencyResponseDTO deliquencyResponseDTO=new Gson().fromJson(rawData, DeliquencyResponseDTO.class);

                            }
                        }
                    }
                };
                viewModel.getRawTableLiveData().observe(this, getLeadRawDataObserver);*/
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}