package com.saartak.el.fragments;

import static com.saartak.el.constants.AppConstant.BCID;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_LEAD;
import static com.saartak.el.constants.AppConstant.PARAM_CLIENT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_PROJECT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_SCREEN_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_SCREEN_NO;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_LEAD;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_LEAD_NAME_STATE_NAME;
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

import com.saartak.el.R;
import com.saartak.el.database.entity.DynamicUITable;
import com.saartak.el.database.entity.ProductMasterTable;
import com.saartak.el.database.entity.RawDataTable;
import com.saartak.el.interfce.FragmentInterface;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.HasSupportFragmentInjector;

public class TwoWheelerFragment extends LOSBaseFragment implements LOSBaseFragment.DynamiUIinterfacce, HasSupportFragmentInjector, FragmentInterface {
    private static final String TAG = TwoWheelerFragment.class.getCanonicalName();

    private OnFragmentInteractionListener mListener;
    String leadState="";

    public TwoWheelerFragment() {

    }

    // TODO: Rename and change types and number of parameters
    public static TwoWheelerFragment newInstance(String loanType, String clientId, String projectId, String productId,
                                                 String screenNo, String screenName, String userId, String moduleType) {
        TwoWheelerFragment fragment = new TwoWheelerFragment();
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
        setDynamiUIinterfacce(TwoWheelerFragment.this);
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
   public void configureDagger() {
        AndroidSupportInjection.inject(this);
    }

    public void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DynamicUIViewModel.class);

        getProductMasterFromServer(PRODUCT_ID,BCID);

    }

    @Override
    public void fragmentBecameVisible() {
        try {
            viewModel.init(SCREEN_ID, SCREEN_NAME, LOAN_TYPE, PROJECT_ID, PRODUCT_ID, CLIENT_ID, USER_ID, MODULE_TYPE);
            Observer observer = new Observer() {
                @Override
                public void onChanged(@Nullable Object o) {
                    List<DynamicUITable> list = (List<DynamicUITable>) o;
                    viewModel.getDynamicUITableLiveData().removeObserver(this);
                    getRawDataForParentFragment(SCREEN_NAME, list);
                    getStateFromLead();
                }
            };
            viewModel.getDynamicUITableLiveData().observe(getViewLifecycleOwner(), observer);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void getStateFromLead() {

        try {
            viewModel.getRawDataByClientIDAndModuleType(SCREEN_NAME_LEAD, CLIENT_ID, MODULE_TYPE_LEAD);
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
                                        if (hashMap.containsKey(TAG_LEAD_NAME_STATE_NAME)) {
                                            leadState = hashMap.get(TAG_LEAD_NAME_STATE_NAME).toString();
                                        }
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

    private void updateUI(@Nullable List<DynamicUITable> dynamicUITable) {
        if (dynamicUITable != null) {
            dynamicUI(dynamicUITable);
        }
    }


    @Override
    public Fragment getFragment() {
        return this;
    }


    private void getProductMasterFromServer(String productId, String bcId) {
        try {
            viewModel.getProductMasterFromServer(productId, bcId);
            if (viewModel.getProductMasterTableLiveDataList() != null) {
                Observer getProductMasterFromServerObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<ProductMasterTable> productMasterTableList = (List<ProductMasterTable>) o;
                        viewModel.getProductMasterTableLiveDataList().removeObserver(this);
                        if (productMasterTableList != null && productMasterTableList.size() > 0) {
                            viewModel.init(SCREEN_ID, SCREEN_NAME, LOAN_TYPE, PROJECT_ID, PRODUCT_ID, CLIENT_ID, USER_ID, MODULE_TYPE);
                            Observer observer = new Observer() {
                                @Override
                                public void onChanged(@Nullable Object o) {
                                    List<DynamicUITable> list = (List<DynamicUITable>) o;
                                    viewModel.getDynamicUITableLiveData().removeObserver(this);
                                    getRawDataForParentFragment(SCREEN_NAME, list);
                                    getStateFromLead();
                                }
                            };
                        }
                    }
                };
                viewModel.getProductMasterTableLiveDataList().observe(this, getProductMasterFromServerObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
