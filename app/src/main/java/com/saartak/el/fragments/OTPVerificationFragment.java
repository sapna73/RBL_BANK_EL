package com.saartak.el.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.saartak.el.R;
import com.saartak.el.adapter.OTPVerificationAdapter;
import com.saartak.el.database.entity.OTPVerificationTable;
import com.saartak.el.interfce.FragmentInterface;
import com.saartak.el.models.OTPTriggerDTO;
import com.saartak.el.models.OTPTriggerRequestDTO;
import com.saartak.el.models.OTPTriggerRequestStringDTO;
import com.saartak.el.models.OTPTriggerResponseDTO;
import com.saartak.el.models.OTPVerifyDTO;
import com.saartak.el.models.OTPVerifyRequestDTO;
import com.saartak.el.models.OTPVerifyRequestStringDTO;
import com.saartak.el.models.OTPVerifyResponseDTO;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

import static com.saartak.el.constants.AppConstant.DATE_FORMAT_DDMMYYYYHHMM;
import static com.saartak.el.constants.AppConstant.OTP_SMS_FORMAT;
import static com.saartak.el.constants.AppConstant.PARAM_CLIENT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_PROJECT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_SCREEN_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_SCREEN_NO;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OTPVerificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OTPVerificationFragment extends LOSBaseFragment implements FragmentInterface, OTPVerificationAdapter.OTPInterface {
    private static final String TAG = OTPVerificationFragment.class.getCanonicalName() ;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    // TODO: Rename and change types of parameters

    private OnFragmentInteractionListener mListener;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    RecyclerView rvOtpVerification;
    OTPVerificationAdapter otpVerificationAdapter;

    public OTPVerificationFragment() {
        // Required empty public constructor
    }


    String LOAN_TYPE,CLIENT_ID,PROJECT_ID,PRODUCT_ID,SCREEN_ID,SCREEN_NAME,USER_ID,MODULE_TYPE;
    // TODO: Rename and change types

    public static OTPVerificationFragment newInstance(String loanType, String clientId, String projectId, String productId,
                                                      String screenNo, String screenName, String userId, String moduleType) {
        OTPVerificationFragment fragment = new OTPVerificationFragment();
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
        return inflater.inflate(R.layout.fragment_otpauthentication, container, false);
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
        rvOtpVerification=(RecyclerView)view.findViewById(R.id.rv_otp);
        rvOtpVerification.setHasFixedSize(true);
        rvOtpVerification.setLayoutManager(new GridLayoutManager( getActivity(), 1,RecyclerView.VERTICAL, false));
        otpVerificationAdapter = new OTPVerificationAdapter( getActivity(), new ArrayList<OTPVerificationTable>(),this,appHelper);
        rvOtpVerification.setAdapter(otpVerificationAdapter);
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



    private void getOTPVerificationData() {
        try {
            viewModel.getOTPVerificationData(CLIENT_ID,LOAN_TYPE);
            if (viewModel.getOTPVerificationLiveData() != null) {
                Observer getDocumentUploadDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<OTPVerificationTable> otpVerificationTableListFromDB = (List<OTPVerificationTable>) o;
                        viewModel.getOTPVerificationLiveData().removeObserver(this);
                        if(otpVerificationTableListFromDB !=null && otpVerificationTableListFromDB.size()>0){
//                            otpVerificationAdapter.setItems(otpVerificationTableListFromDB);
                            otpVerificationAdapter = new OTPVerificationAdapter( getActivity(), otpVerificationTableListFromDB,
                                    OTPVerificationFragment.this,appHelper);
                            rvOtpVerification.setAdapter(otpVerificationAdapter);
                        }
                    }
                };
                viewModel.getOTPVerificationLiveData().observe(this, getDocumentUploadDataObserver);
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
        getOTPVerificationData();
    }


    @Override
    public void generateOTP(int position, OTPVerificationTable otpVerificationTable,OTPVerificationAdapter.OTPVerificationViewHolder holder) {
        try{
            triggerOTP(otpVerificationTable,holder);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    @Override
    public void regenerateOTP(int position, OTPVerificationTable otpVerificationTable,OTPVerificationAdapter.OTPVerificationViewHolder holder) {
        try{
            triggerOTP(otpVerificationTable,holder);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void verifyOTP(int position, OTPVerificationTable otpVerificationTable, String enteredOtp,
                          OTPVerificationAdapter.OTPVerificationViewHolder holder) {
        try{

            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            OTPVerifyDTO otpVerifyDTO=new OTPVerifyDTO();
            otpVerifyDTO.setUniqueId(otpVerificationTable.getClient_id());
            otpVerifyDTO.setClientID(otpVerificationTable.getClient_id());
            otpVerifyDTO.setCreatedDate(appHelper.getCurrentDate(DATE_FORMAT_DDMMYYYYHHMM));
            otpVerifyDTO.setCreatedBy("LOSMobile"); // TODO: Hard coded
            otpVerifyDTO.setServiceType("OTPVerify"); // TODO: Hard coded

            OTPVerifyRequestStringDTO otpVerifyRequestStringDTO=new OTPVerifyRequestStringDTO();

            OTPVerifyRequestDTO otpVerifyRequestDTO=new OTPVerifyRequestDTO();
            otpVerifyRequestDTO.setMobileNumber(otpVerificationTable.getMobileNumber());
            otpVerifyRequestDTO.setPurpose("To validate otp"); // TODO: Hard coded
            otpVerifyRequestDTO.setOTP(enteredOtp);
            otpVerifyRequestDTO.setIMEINumber(appHelper.getIMEI());
            otpVerifyRequestDTO.setRefferenceId(otpVerificationTable.getRefferenceId());

            otpVerifyRequestStringDTO.setOtpverifyRequest(otpVerifyRequestDTO);
            otpVerifyDTO.setRequestString(otpVerifyRequestStringDTO);

            viewModel.verifyOTP(otpVerifyDTO,otpVerificationTable);
            if (viewModel.getOtpVerifyResponseDTOLiveData() != null) {
                Observer verifyOTPObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        OTPVerifyResponseDTO otpVerifyResponseDTO = (OTPVerifyResponseDTO) o;
                        viewModel.getOtpVerifyResponseDTOLiveData().removeObserver(this);

                        String errorMsg="OTP Verification Failed";

                        if(otpVerifyResponseDTO !=null && otpVerifyResponseDTO.getApiResponse() !=null){
                            if( ! TextUtils.isEmpty(otpVerifyResponseDTO.getApiResponse().getStatus())
                                    && otpVerifyResponseDTO.getApiResponse().getStatus().equalsIgnoreCase("1")) {
                                String responseMsg="OTP Verification Success";
                                if(! TextUtils.isEmpty(otpVerifyResponseDTO.getApiResponse().getResponseMessage())) {
                                    responseMsg = otpVerifyResponseDTO.getApiResponse().getResponseMessage();
                                }
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS,
                                        responseMsg, new ConfirmationDialog.ActionCallback() {
                                            @Override
                                            public void onAction() {
                                                // TODO: End Timer
                                                otpVerificationAdapter.entTimer(holder);
                                                // TODO: Clearing shared preference
                                                appHelper.getSharedPrefObj().edit().remove(holder.tvMobileNumber.getText().toString()).apply();
                                                getOTPVerificationData();
                                            }
                                        });
                            }else{

                                if(! TextUtils.isEmpty(otpVerifyResponseDTO.getApiResponse().getResponseMessage())){
                                    errorMsg=otpVerifyResponseDTO.getApiResponse().getResponseMessage();
                                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR,
                                            errorMsg, new ConfirmationDialog.ActionCallback() {
                                                @Override
                                                public void onAction() {
                                                    // TODO: End Timer
                                                    otpVerificationAdapter.entTimer(holder);
                                                    getOTPVerificationData();
                                                }
                                            });
                                }
                            }
                        }else{
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR,
                                    errorMsg, new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {

                                            // TODO: End Timer
                                            otpVerificationAdapter.entTimer(holder);
                                            getOTPVerificationData();
                                        }
                                    });
                        }
                    }
                };
                viewModel.getOtpVerifyResponseDTOLiveData().observe(this, verifyOTPObserver);
            }
        }catch (Exception ex){
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            ex.printStackTrace();
        }

    }

    private void triggerOTP(OTPVerificationTable otpVerificationTable,OTPVerificationAdapter.OTPVerificationViewHolder holder){
        try{
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            OTPTriggerDTO otpTriggerDTO=new OTPTriggerDTO();
            otpTriggerDTO.setUniqueId(otpVerificationTable.getClient_id());
            otpTriggerDTO.setClientID(otpVerificationTable.getClient_id());
            otpTriggerDTO.setCreatedDate(appHelper.getCurrentDate(DATE_FORMAT_DDMMYYYYHHMM));
            otpTriggerDTO.setCreatedBy("LOSMobile"); // TODO: Hard coded
            otpTriggerDTO.setServiceType("OTPTrigger"); // TODO: Hard coded

            OTPTriggerRequestStringDTO otpTriggerRequestStringDTO=new OTPTriggerRequestStringDTO();

            OTPTriggerRequestDTO otpTriggerRequestDTO=new OTPTriggerRequestDTO();
            otpTriggerRequestDTO.setMobileNumber(otpVerificationTable.getMobileNumber());
            otpTriggerRequestDTO.setIMEINumber(appHelper.getIMEI());
            otpTriggerRequestDTO.setMessageText(OTP_SMS_FORMAT); // TODO: Hard coded

            otpTriggerRequestDTO.setPurpose("To trigger otp"); // TODO: Hard coded

            otpTriggerRequestStringDTO.setOTPTriggerRequest(otpTriggerRequestDTO);
            otpTriggerDTO.setRequestString(otpTriggerRequestStringDTO);

            viewModel.generateOTP(otpTriggerDTO,otpVerificationTable);
            if (viewModel.getOtpTriggerResponseDTOLiveData() != null) {
                Observer generateOTPObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        OTPTriggerResponseDTO otpTriggerResponseDTO = (OTPTriggerResponseDTO) o;
                        viewModel.getOtpTriggerResponseDTOLiveData().removeObserver(this);

                        String errorMsg="OTP Trigger Failed";

                        if(otpTriggerResponseDTO !=null && otpTriggerResponseDTO.getApiResponse() !=null){
                            if( ! TextUtils.isEmpty(otpTriggerResponseDTO.getApiResponse().getStatus())
                            && otpTriggerResponseDTO.getApiResponse().getStatus().equalsIgnoreCase("1")) {
                                 String responseMsg="OTP Trigger Success";
                                if(! TextUtils.isEmpty(otpTriggerResponseDTO.getApiResponse().getResponseMessage())) {
                                    responseMsg = otpTriggerResponseDTO.getApiResponse().getResponseMessage();
                                }
                                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS,
                                            responseMsg, new ConfirmationDialog.ActionCallback() {
                                                @Override
                                                public void onAction() {
                                                    // TODO: End Timer
                                                    otpVerificationAdapter.entTimer(holder);

                                                    getOTPVerificationData();
                                                }
                                            });
                            }else{

                                if(! TextUtils.isEmpty(otpTriggerResponseDTO.getApiResponse().getResponseMessage())){
                                    errorMsg=otpTriggerResponseDTO.getApiResponse().getResponseMessage();
                                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR,
                                            errorMsg, new ConfirmationDialog.ActionCallback() {
                                                @Override
                                                public void onAction() {

                                                    // TODO: End Timer
                                                    otpVerificationAdapter.entTimer(holder);

                                                    getOTPVerificationData();
                                                }
                                            });
                                }
                            }
                        }else{
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR,
                                    errorMsg, new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {
                                            // TODO: End Timer
                                            otpVerificationAdapter.entTimer(holder);

                                            getOTPVerificationData();
                                        }
                                    });
                        }

                    }
                };
                viewModel.getOtpTriggerResponseDTOLiveData().observe(this, generateOTPObserver);
            }
        }catch (Exception ex){
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
        }

    }

    @Override
    public void fragmentBecameVisible() {

    }

}
