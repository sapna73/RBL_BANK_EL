package com.swadhaar.los.fragments.incomeassessment;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.swadhaar.los.R;
import com.swadhaar.los.database.entity.DynamicUITable;
import com.swadhaar.los.fragments.LOSBaseFragment;
import com.swadhaar.los.view_models.DynamicUIViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.HasSupportFragmentInjector;

import static com.swadhaar.los.constants.AppConstant.PARAM_CLIENT_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_PROJECT_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_SCREEN_NAME;
import static com.swadhaar.los.constants.AppConstant.PARAM_SCREEN_NO;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_ID;


public class DeclaredSalesWeeklyFragment extends LOSBaseFragment implements LOSBaseFragment.DynamiUIinterfacce, HasSupportFragmentInjector {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    private OnFragmentInteractionListener mListener;

    public DeclaredSalesWeeklyFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DeclaredSalesWeeklyFragment newInstance(String loanType, String clientId, String projectId, String productId,
                                                   String screenNo,String screenName,String userId,String moduleType) {
        DeclaredSalesWeeklyFragment fragment = new DeclaredSalesWeeklyFragment();
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
        return inflater.inflate(R.layout.fragment_common_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ll = (LinearLayout) view.findViewById(R.id.ll_content);
        setDynamiUIinterfacce(DeclaredSalesWeeklyFragment.this);

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
                if(list !=null && list.size()>0) {
                    getRawDataForParentFragment(SCREEN_NAME,list);
                }else{
                    updateUI(list);
                }
            }
        };
        viewModel.getDynamicUITableLiveData().observe(getViewLifecycleOwner(), observer);
    }


    // -----------------
    // UPDATE UI
    // -----------------

    private void updateUI(@Nullable List<DynamicUITable> dynamicUITable){
        if (dynamicUITable != null){
            dynamicUI(dynamicUITable);
        }
    }


    @Override
    public Fragment getFragment() {
        return this;
    }
}
