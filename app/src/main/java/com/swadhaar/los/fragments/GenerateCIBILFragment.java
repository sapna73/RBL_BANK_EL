package com.swadhaar.los.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.swadhaar.los.R;
import com.swadhaar.los.adapter.CIBILAdapter;
import com.swadhaar.los.adapter.CIBILAdapter;
import com.swadhaar.los.database.entity.CIBILTable;
import com.swadhaar.los.database.entity.DynamicUITable;
import com.swadhaar.los.database.entity.OTPVerificationTable;
import com.swadhaar.los.interfce.FragmentInterface;
import com.swadhaar.los.models.OTPTriggerDTO;
import com.swadhaar.los.models.OTPTriggerRequestDTO;
import com.swadhaar.los.models.OTPTriggerRequestStringDTO;
import com.swadhaar.los.models.OTPTriggerResponseDTO;
import com.swadhaar.los.models.OTPVerifyDTO;
import com.swadhaar.los.models.OTPVerifyRequestDTO;
import com.swadhaar.los.models.OTPVerifyRequestStringDTO;
import com.swadhaar.los.models.OTPVerifyResponseDTO;
import com.swadhaar.los.view_models.DynamicUIViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

import static com.swadhaar.los.constants.AppConstant.DATE_FORMAT_DDMMYYYYHHMM;
import static com.swadhaar.los.constants.AppConstant.MESSAGE_ROOTED_DEVICE_NOT_SUPPORTED;
import static com.swadhaar.los.constants.AppConstant.OTP_SMS_FORMAT;
import static com.swadhaar.los.constants.AppConstant.PARAM_CLIENT_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_PROJECT_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_SCREEN_NAME;
import static com.swadhaar.los.constants.AppConstant.PARAM_SCREEN_NO;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_ID;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.MESSAGE_CIBIL_FAILED;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.MESSAGE_CIBIL_SUCCESS;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GenerateCIBILFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GenerateCIBILFragment extends LOSBaseFragment implements FragmentInterface, CIBILAdapter.CIBILInterface {
    private static final String TAG = GenerateCIBILFragment.class.getCanonicalName() ;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    // TODO: Rename and change types of parameters

    private OnFragmentInteractionListener mListener;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    RecyclerView rvCibilGeneration;
    CIBILAdapter cibilAdapter;

    public GenerateCIBILFragment() {
        // Required empty public constructor
    }


    String LOAN_TYPE,CLIENT_ID,PROJECT_ID,PRODUCT_ID,SCREEN_ID,SCREEN_NAME,USER_ID,MODULE_TYPE;
    // TODO: Rename and change types

    public static GenerateCIBILFragment newInstance(String loanType, String clientId, String projectId, String productId,
                                                    String screenNo, String screenName, String userId, String moduleType) {
        GenerateCIBILFragment fragment = new GenerateCIBILFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_generate_cibil, container, false);
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
        rvCibilGeneration=(RecyclerView)view.findViewById(R.id.rv_generate_cibil);
        rvCibilGeneration.setHasFixedSize(true);
        rvCibilGeneration.setLayoutManager(new GridLayoutManager( getActivity(), 1,RecyclerView.VERTICAL, false));
        cibilAdapter = new CIBILAdapter( getActivity(), new ArrayList<>(),this,appHelper);
        rvCibilGeneration.setAdapter(cibilAdapter);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }



    private void getCIBILTableListFromDB() {
        try {
            viewModel.getCIBILTableListFromDB(CLIENT_ID,LOAN_TYPE);
            if (viewModel.getCibilTableLiveDataList() != null) {
                Observer getDocumentUploadDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<CIBILTable> cibilTableListFromDB = (List<CIBILTable>) o;
                        viewModel.getCibilTableLiveDataList().removeObserver(this);
                        if(cibilTableListFromDB !=null && cibilTableListFromDB.size()>0){
//                            cibilAdapter.setItems(otpVerificationTableListFromDB);
                            cibilAdapter = new CIBILAdapter( getActivity(), cibilTableListFromDB,
                                    GenerateCIBILFragment.this,appHelper);
                            rvCibilGeneration.setAdapter(cibilAdapter);
                        }else{
                            // TODO: Checking Applicant kyc & personal details raw data
                            checkKYCAndPersonalDetailForCIBIL(CLIENT_ID,LOAN_TYPE);
                        }
                    }
                };
                viewModel.getCibilTableLiveDataList().observe(this, getDocumentUploadDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void checkKYCAndPersonalDetailForCIBIL(String client_id, String loan_type) {
        try {
            viewModel.checkKYCAndPersonalDetailForCIBIL(CLIENT_ID,LOAN_TYPE);
            if (viewModel.getCibilTableLiveDataList() != null) {
                Observer getDocumentUploadDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<CIBILTable> cibilTableListFromDB = (List<CIBILTable>) o;
                        viewModel.getCibilTableLiveDataList().removeObserver(this);
                        if(cibilTableListFromDB !=null && cibilTableListFromDB.size()>0){
//                            cibilAdapter.setItems(otpVerificationTableListFromDB);
                            cibilAdapter = new CIBILAdapter( getActivity(), cibilTableListFromDB,
                                    GenerateCIBILFragment.this,appHelper);
                            rvCibilGeneration.setAdapter(cibilAdapter);
                        }
                    }
                };
                viewModel.getCibilTableLiveDataList().observe(this, getDocumentUploadDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void configureDagger() {
        AndroidSupportInjection.inject(this);
    }


    public void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DynamicUIViewModel.class);
        getCIBILTableListFromDB();
    }

    @Override
    public void fragmentBecameVisible() {

    }

    @Override
    public void generateCIBILCallBack(int position, CIBILTable cibilTable) {
        try{
            generateCIBILServiceCall(cibilTable,USER_ID);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    // TODO: GENERATE CIBIL SERVICE CALL
    private void generateCIBILServiceCall(CIBILTable cibilTable, String staffId ) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            viewModel.generateCIBILServiceCall(cibilTable,staffId);
            if (viewModel.getStringLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {

                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();

                        String response = (String) o;
                        viewModel.getStringLiveData().removeObserver(this::onChanged);

                        if( ! TextUtils.isEmpty(response)){
                            if(response.equalsIgnoreCase(MESSAGE_CIBIL_FAILED)){
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR, response,
                                        new ConfirmationDialog.ActionCallback() {
                                            @Override
                                            public void onAction() {
                                                getCIBILTableListFromDB();
                                            }
                                        });
                            }else if(response.equalsIgnoreCase(MESSAGE_CIBIL_SUCCESS)){
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS, response,
                                        new ConfirmationDialog.ActionCallback() {
                                            @Override
                                            public void onAction() {
                                                getCIBILTableListFromDB();
                                            }
                                        });
                            }else{
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR, response,
                                        new ConfirmationDialog.ActionCallback() {
                                            @Override
                                            public void onAction() {
                                                getCIBILTableListFromDB();
                                            }
                                        });
                            }

                        }else{
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR, MESSAGE_CIBIL_FAILED,
                                    new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {
                                            getCIBILTableListFromDB();
                                        }
                                    });
                        }
                    }
                };

                viewModel.getStringLiveData().observe(this, observer);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
