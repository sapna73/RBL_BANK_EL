package com.saartak.el.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.gson.Gson;
import com.saartak.el.R;
import com.saartak.el.database.entity.DynamicUITable;
import com.saartak.el.database.entity.RawDataTable;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.HasSupportFragmentInjector;

import static com.saartak.el.constants.AppConstant.PARAM_CLIENT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_CORRELATION_ID;
import static com.saartak.el.constants.AppConstant.PARAM_DYNAMIC_UI;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_PROJECT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_SCREEN_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_SCREEN_NO;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_CASH_BUSINESS_ASSETS_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_DEPOSITS_BUSINESS_ASSETS_BONDS_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_INSURANCE_BUSINESS_ASSETS_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_LOAN_SUGGESTION_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_RECEIVABLES_BUSINESS_ASSETS_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_SAVINGS_CHITS_BUSINESS_ASSETS_MSME;


public class ChildFragment extends LOSBaseFragment implements LOSBaseFragment.DynamiUIinterfacce, HasSupportFragmentInjector {
    private static final String TAG = ChildFragment.class.getCanonicalName();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    List<DynamicUITable> DYNAMIC_UI_LIST;
    DynamicUITable DYNAMIC_UI_TABLE;

    private OnFragmentInteractionListener mListener;

    public ChildFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ChildFragment newInstance(String param1, String param2, String projectId, String productid,
                                            String screenNo, String screenName, List<DynamicUITable> dynamicUITableListFromParentScreen,
                                            DynamicUITable dynamicUITable, String userId, String moduleType,String correlationId) {
        ChildFragment fragment = new ChildFragment();
        Bundle args = new Bundle();
        args.putString(PARAM_LOAN_TYPE, param1);
        args.putString(PARAM_CLIENT_ID, param2);
        args.putString(PARAM_PROJECT_ID, projectId);
        args.putString(PARAM_PRODUCT_ID, productid);
        args.putString(PARAM_SCREEN_NO, screenNo);
        args.putString(PARAM_SCREEN_NAME, screenName);
        args.putString(PARAM_USER_ID, userId);
        args.putString(PARAM_MODULE_TYPE, moduleType);
        args.putString(PARAM_CORRELATION_ID, correlationId);
//        args.putSerializable(PARAM_DYNAMIC_UI_LIST, (Serializable) list);
        args.putSerializable(PARAM_DYNAMIC_UI, dynamicUITable);
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
//            DYNAMIC_UI_LIST = (List<DynamicUITable>) getArguments().getSerializable(PARAM_DYNAMIC_UI_LIST);
            DYNAMIC_UI_TABLE = (DynamicUITable) getArguments().getSerializable(PARAM_DYNAMIC_UI);
            USER_ID = getArguments().getString(PARAM_USER_ID);
            MODULE_TYPE = getArguments().getString(PARAM_MODULE_TYPE);
            CORRELATION_ID = getArguments().getString(PARAM_CORRELATION_ID);

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_child_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ll = (LinearLayout) view.findViewById(R.id.ll_child);
        setDynamiUIinterfacce(ChildFragment.this);
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
      /*if(DYNAMIC_UI_LIST !=null && DYNAMIC_UI_LIST.size()>0){
          updateUI(DYNAMIC_UI_LIST);
      }*/

    }


    // -----------------
    // CONFIGURATION
    // -----------------

    public void configureDagger() {
        AndroidSupportInjection.inject(this);
    }


    public void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DynamicUIViewModel.class);
        try {
            if (DYNAMIC_UI_TABLE != null) {
                getApplicantPersonalDetails(viewModel);
                getCoApplicantPersonalDetails(viewModel);
                // TODO: AUTO POPULATE SCREEN
                viewModel.getDataForChildFragment(SCREEN_ID, SCREEN_NAME, LOAN_TYPE, PROJECT_ID, PRODUCT_ID, CLIENT_ID, USER_ID);
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> list = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);
                        if (DYNAMIC_UI_TABLE != null && !TextUtils.isEmpty(DYNAMIC_UI_TABLE.getFieldTag())) {
                            getRawDataForChildFragmentNew(SCREEN_NAME, list, DYNAMIC_UI_TABLE.getFieldTag(),DYNAMIC_UI_TABLE.getLoanType());
                        } else {
                            updateUI(list);
                        }
                    }
                };
                viewModel.getDynamicUITableLiveData().observe(getViewLifecycleOwner(), observer);
            } else {
                // TODO: EMPTY SCREEN

                viewModel.getMetaDataWithCorrelationID(SCREEN_ID, SCREEN_NAME, LOAN_TYPE, PROJECT_ID, PRODUCT_ID, CLIENT_ID,
                        USER_ID, MODULE_TYPE,CORRELATION_ID);
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> list = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);
                            if(SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_CASH_BUSINESS_ASSETS_MSME)
                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_SAVINGS_CHITS_BUSINESS_ASSETS_MSME)
                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_DEPOSITS_BUSINESS_ASSETS_BONDS_MSME)
                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_INSURANCE_BUSINESS_ASSETS_MSME)
                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_RECEIVABLES_BUSINESS_ASSETS_MSME)
                            ){
                                businessAssetChildScreenChangesByRawData(list);
                            }
                            else if(SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_LOAN_SUGGESTION_MSME)
                            ){
                                loanSuggestionCalculation(list);
                            }
                            else {
                                updateUI(list);
                            }
                    }
                };
                viewModel.getDynamicUITableLiveData().observe(getViewLifecycleOwner(), observer);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    // -----------------
    // UPDATE UI
    // -----------------

    private void updateUI(@Nullable List<DynamicUITable> dynamicUITable) {
        if (dynamicUITable != null) {
            dynamicUI(dynamicUITable);
        }
    }


    @Override
    public Fragment getFragment() {
        return this;
    }

    public interface ChildFragmentInterface {
        void closeDialogFragment();
    }

    public void callCloseDialogFragment() {
        ChildFragmentInterface listener = (ChildFragmentInterface) getParentFragment();
        listener.closeDialogFragment();
    }

    private void businessAssetChildScreenChangesByRawData(List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.businessAssetChildScreenChangesByRawData( dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer businessAssetChildScreenChangesByRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListFromDB = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);
                        updateUI(dynamicUITableListFromDB);
                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, businessAssetChildScreenChangesByRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void loanSuggestionCalculation(List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.loanSuggestionCalculation( dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer loanSuggestionCalculationObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListFromDB = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);

                        // TODO: Update UI
                        dynamicUI(dynamicUITableListFromDB);

                        // TODO: save parent screen data
                        String submittedValues = getSubmittedValuesFromUI(dynamicUITableListFromDB);
//                        DynamicUITable dynamicUITable = dynamicUITableListFromDB.get(dynamicUITableListFromDB.size() - 1);
                        DynamicUITable dynamicUITable = dynamicUITableListFromDB.get(0);//did

                        final RawDataTable rawDataTable = new RawDataTable(submittedValues, dynamicUITable.getScreenID()
                                , dynamicUITable.getScreenName(), "", dynamicUITable.getClientID(),
                                dynamicUITable.getLoanType(), dynamicUITable.getUser_id(),
                                dynamicUITable.getModuleType(),dynamicUITable.getCoRelationID());

                        String dynamicUIRawData = new Gson().toJson(dynamicUITableListFromDB);
                        rawDataTable.setDynamic_ui_rawdata(dynamicUIRawData);

                        // TODO: To save parent screen raw data
                        saveParentScreenRawData(rawDataTable, dynamicUITableListFromDB,
                                dynamicUITable, dynamicUITable.getFieldTag());
                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, loanSuggestionCalculationObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
