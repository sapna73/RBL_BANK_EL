package com.saartak.el.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.chaos.view.PinView;
import com.saartak.el.R;
import com.saartak.el.database.entity.DynamicUITable;
import com.saartak.el.models.ParameterInfo;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.HasSupportFragmentInjector;

import static com.saartak.el.constants.AppConstant.PARAM_CLIENT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_PROJECT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_SCREEN_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_SCREEN_NO;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_CBCHECK_BUTTON;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_ENTER_OTP;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_RESEND_OTP_BUTTON;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_SAVE_BUTTON;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OTPAuthenticationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OTPAuthenticationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OTPAuthenticationFragment extends LOSBaseFragment implements LOSBaseFragment.DynamiUIinterfacce, HasSupportFragmentInjector {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    Button btnSubmitOTP;
    PinView pinView;

    private OnFragmentInteractionListener mListener;

    public OTPAuthenticationFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static OTPAuthenticationFragment newInstance(String loanType, String clientId, String projectId, String productId,
                                                   String screenNo,String screenName,String userId,String moduleType) {
        OTPAuthenticationFragment fragment = new OTPAuthenticationFragment();
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ll = (LinearLayout) view.findViewById(R.id.ll_content);
        /*btnSubmitOTP=(Button)view.findViewById(R.id.submit_otp);
        pinView=(PinView)view.findViewById(R.id.pin_view);

        btnSubmitOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pin=pinView.getText().toString();
                if(pin.equalsIgnoreCase("1234")){
                    pinView.setLineColor(Color.GREEN);
                }else{
                    pinView.setLineColor(Color.RED);
                }
            }
        });*/
        setDynamiUIinterfacce(OTPAuthenticationFragment.this);
//        getUIParametersFromServer("19");




    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void dynamicUICallback(List<DynamicUITable> viewParametersList) {
        dynamicUI(viewParametersList);
    }

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
*/
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


    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

   /* @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            this.configureDagger();
            this.configureViewModel();
        }
    }*/

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.configureDagger();
        this.configureViewModel();
    }


    // -----------------
    // CONFIGURATION
    // -----------------

    public void configureDagger(){
        AndroidSupportInjection.inject(this);
    }

    public void configureViewModel(){
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DynamicUIViewModel.class);

        viewModel.init(SCREEN_ID,SCREEN_NAME,LOAN_TYPE,PROJECT_ID,PRODUCT_ID,CLIENT_ID,USER_ID,MODULE_TYPE);
        Observer observer=new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {
                List<DynamicUITable> list=(List<DynamicUITable>)o;
                    viewModel.getDynamicUITableLiveData().removeObserver(this);
//                    Toast.makeText(getActivity(),"OBSERVER REMOVED ===> SCREEN = "+SCREEN_ID, Toast.LENGTH_SHORT).show();
//                    updateUI(list);
                    List<ParameterInfo> parameterInfoList=new ArrayList<>();
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_ENTER_OTP,SCREEN_ID,"",false,true));
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_RESEND_OTP_BUTTON,SCREEN_ID,"",false,true));
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_CBCHECK_BUTTON,SCREEN_ID,"",false,true));
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_SAVE_BUTTON,SCREEN_ID,"",false,true));
                    EnableOrDisableByFieldNameInDB(parameterInfoList,list);
                }
//            }
        };
        viewModel.getDynamicUITableLiveData().observe(getViewLifecycleOwner(), observer);
    }


    // -----------------
    // UPDATE UI
    // -----------------

    private void updateUI(@Nullable List<DynamicUITable> dynamicUITable){
        if (dynamicUITable != null){
//            Toast.makeText(getActivity(),"Initial Update ==> SCREEN = " +SCREEN_ID, Toast.LENGTH_SHORT).show();
            dynamicUI(dynamicUITable);
        }
    }


    @Override
    public Fragment getFragment() {
        return this;
    }
}
