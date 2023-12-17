package com.saartak.el.fragments;

import static com.saartak.el.constants.AppConstant.PARAM_CLIENT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_PROJECT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_SCREEN_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_SCREEN_NO;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.dynamicui.constants.ParametersConstant.FIELD_NAME_UPDATE;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SPINNER_ITEM_FIELD_NAME_AADHAAR;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SPINNER_ITEM_FIELD_NAME_GUARANTOR_AADHAAR;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SPINNER_ITEM_FIELD_NAME_GUARANTOR_DRIVING_LICENSE;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SPINNER_ITEM_FIELD_NAME_GUARANTOR_PASSPORT;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SPINNER_ITEM_FIELD_NAME_GUARANTOR_VOTER_ID;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_ADD_ANOTHER_KYC_PLUS_BUTTON;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_GUARANTOR_EKYC_BUTTON;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_GUARANTOR_KYC_ID;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_GUARANTOR_KYC_TYPE;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_KYC_ID;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_KYC_TYPE;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_QR_READING_BUTTON;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_RE_ENTER_KYC_ID;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_SAVE_BUTTON;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.saartak.el.R;
import com.saartak.el.database.entity.DynamicUITable;
import com.saartak.el.database.entity.RawDataTable;
import com.saartak.el.interfce.FragmentInterface;
import com.saartak.el.models.DataTypeInfo;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.HasSupportFragmentInjector;

public class GuarantorDetailsFragment extends LOSBaseFragment implements LOSBaseFragment.DynamiUIinterfacce, HasSupportFragmentInjector, FragmentInterface {
    private GuarantorDetailsFragment.OnFragmentInteractionListener mListener;

    public GuarantorDetailsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static GuarantorDetailsFragment newInstance(String loanType, String clientId, String projectId, String productId,
                                                       String screenNo, String screenName, String userId, String moduleType) {
        GuarantorDetailsFragment fragment = new GuarantorDetailsFragment();
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
        mRootView = (FrameLayout) inflater.inflate(R.layout.fragment_common_layout, container, false);
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ll = (LinearLayout) view.findViewById(R.id.ll_content);
        setDynamiUIinterfacce(GuarantorDetailsFragment.this);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void dynamicUICallback(List<DynamicUITable> viewParametersList) {
        try {
            for (DynamicUITable viewParameters : viewParametersList) {
                if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE)
                        || viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_ID)) {

                    if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE)) {
                        viewParameters.setValue(SPINNER_ITEM_FIELD_NAME_GUARANTOR_AADHAAR);
                        viewParameters.setValue(SPINNER_ITEM_FIELD_NAME_GUARANTOR_DRIVING_LICENSE);
                        viewParameters.setValue(SPINNER_ITEM_FIELD_NAME_GUARANTOR_PASSPORT);
                        viewParameters.setValue(SPINNER_ITEM_FIELD_NAME_GUARANTOR_VOTER_ID);

                        if (viewParameters.getFieldTag().equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_GUARANTOR_DRIVING_LICENSE)
                                || viewParameters.getFieldTag().equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_GUARANTOR_PASSPORT)
                                || viewParameters.getFieldTag().equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_GUARANTOR_VOTER_ID)) {
                            viewParameters.setValue(TAG_NAME_GUARANTOR_EKYC_BUTTON);
                        }
                        viewParameters.setVisibility(true);
                    } else {
                        viewParameters.setVisibility(false);
                    }
                }
            }
            updateDynamicUITable(viewParametersList, SCREEN_ID);
        }
            catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Fragment getFragment() {
        return this;
    }

    private void updateUI(@Nullable List<DynamicUITable> dynamicUITable) {
        if (dynamicUITable != null) {
            dynamicUI(dynamicUITable);
        }
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
                    guarantorDetails(list);
                }
            };
            viewModel.getDynamicUITableLiveData().observe(getViewLifecycleOwner(), observer);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        /*try {
            viewModel.init(SCREEN_ID, SCREEN_NAME, LOAN_TYPE, PROJECT_ID, PRODUCT_ID, CLIENT_ID, USER_ID, MODULE_TYPE);
            Observer observer = new Observer() {
                @Override
                public void onChanged(@Nullable Object o) {
                    List<DynamicUITable> list = (List<DynamicUITable>) o;
                    viewModel.getDynamicUITableLiveData().removeObserver(this);
                    if(!TextUtils.isEmpty(LOAN_TYPE)) {
                        guarantorDetails(list.get(0), list);
                        getTagNameList(SCREEN_NAME, list, TAG_NAME_ADD_ANOTHER_KYC_PLUS_BUTTON );
                    }else{
                        getRawDataForParentFragment(SCREEN_NAME, list);
                    }
                }
            };
            viewModel.getDynamicUITableLiveData().observe(getViewLifecycleOwner(), observer);
        } catch (Exception ex) {
            ex.printStackTrace();
        }*/
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
        viewModel.init(SCREEN_ID, SCREEN_NAME, LOAN_TYPE, PROJECT_ID, PRODUCT_ID, CLIENT_ID, USER_ID, MODULE_TYPE);
        Observer observer = new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {
                List<DynamicUITable> list = (List<DynamicUITable>) o;
                viewModel.getDynamicUITableLiveData().removeObserver(this);

                if(list != null && list.size() > 0) {
                    guarantorDetails(list);
                    //applicantKYCScreenValidation(list.get(0),list);
                }
            }
        };
        viewModel.getDynamicUITableLiveData().observe(getViewLifecycleOwner(), observer);
    }

    public void getRawData(String screen,List<DynamicUITable> list) {
        ArrayList<HashMap<String, Object>> hashMapList = new ArrayList<>();
        try {
            viewModel.getRawData(screen, CLIENT_ID, MODULE_TYPE);
            if (viewModel.getRawTableLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<RawDataTable> rawDataTableList = (List<RawDataTable>) o;
                        viewModel.getRawTableLiveData().removeObserver(this);
                        if(rawDataTableList != null && rawDataTableList.size() > 0){
                            for(RawDataTable rawDataTable:rawDataTableList){
                                HashMap<String,Object> hashMap= setKeyValueForObject(rawDataTable);
                                hashMapList.add(hashMap);
                            }
                            if(hashMapList != null && hashMapList.size() > 0){

//                                removeAllChildFragments();

                                // TODO: Already saved data
                                HashMap<String,Object> hashMap = hashMapList.get(0);
//                                for(HashMap<String,EKYCLoginRequestObject> hashMap:hashMapList) {
                                if (hashMap != null && hashMap.size() > 0) {
                                    for (DynamicUITable dynamicUITable : list) {
                                        dynamicUITable.setVisibility(false);
                                        if (!TextUtils.isEmpty(dynamicUITable.getFieldTag())) {
                                            if (hashMap.containsKey(dynamicUITable.getFieldTag())) {
                                                String value = hashMap.get(dynamicUITable.getFieldTag()).toString();
                                                if (!TextUtils.isEmpty(value)) {
                                                    dynamicUITable.setValue(value);
                                                    dynamicUITable.setVisibility(true);
                                                }
                                            } else if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_SAVE_BUTTON)) {
                                                dynamicUITable.setVisibility(true);
                                                dynamicUITable.setFieldName(FIELD_NAME_UPDATE);
                                            }
                                        }
                                    }
//                                        initChild(list);
                                    updateDynamicUITable(list, SCREEN_ID);
                                }
//                                }
                            }else{
                                // TODO: Fresh Data
                                for (DynamicUITable dynamicUITable : list) {
                                    dynamicUITable.setVisibility(false);
                                    if (!TextUtils.isEmpty(dynamicUITable.getFieldTag()) &&
                                            dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE)
                                            || dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_ID)
                                            || dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_RE_ENTER_KYC_ID)
                                            || dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_QR_READING_BUTTON)) {
                                        dynamicUITable.setVisibility(true);

                                        if(dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE)){
                                            dynamicUITable.setValue(SPINNER_ITEM_FIELD_NAME_AADHAAR);
                                        }
                                        // TODO: Get kYC Type based on loan
                       /* if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE)) {
                            String[] newSpinnerList = getNewSpinnerList(TAG_NAME_KYC_TYPE, loanType);
                            dynamicUITable.setParamlist(newSpinnerList);
                            dynamicUITable.setValue(SPINNER_ITEM_FIELD_NAME_AADHAAR);
                        }*/

                                        // TODO: NEED TO REMOVE THIS CONDITION
                                        if (dynamicUITable.getFieldName().equalsIgnoreCase(TAG_NAME_KYC_ID)) {
                                            DataTypeInfo datatypeInfo = new DataTypeInfo(SPINNER_ITEM_FIELD_NAME_AADHAAR, dynamicUITable);
                                            // TODO: Only here we need to check with field name
                                            dynamicUITable.setLength(datatypeInfo.getLength());
                                            dynamicUITable.setHint(datatypeInfo.getHint());
                                            dynamicUITable.setDataType(datatypeInfo.getInputType());
                                            dynamicUITable.setDataEntryType(datatypeInfo.getDataEntryType());
                                            dynamicUITable.setFieldTag(datatypeInfo.getHintTag());
                                        }
                                        if (dynamicUITable.getFieldName().equalsIgnoreCase(TAG_NAME_RE_ENTER_KYC_ID)) {
                                            DataTypeInfo datatypeInfo = new DataTypeInfo(SPINNER_ITEM_FIELD_NAME_AADHAAR, dynamicUITable);
                                            // TODO: Only here we need to check with field name
                                            dynamicUITable.setLength(datatypeInfo.getLength());
                                            dynamicUITable.setHint("Re " + datatypeInfo.getHint());
                                            dynamicUITable.setDataType(datatypeInfo.getInputType());
                                            dynamicUITable.setDataEntryType(datatypeInfo.getDataEntryType());
                                            dynamicUITable.setFieldTag(datatypeInfo.getHintTag());
                                        }
                                    }
                                }
                                updateDynamicUITable(list, SCREEN_ID);
                            }
                        }else{
                            // TODO: Fresh Data
                            for (DynamicUITable dynamicUITable : list) {
                                dynamicUITable.setVisibility(false);
                                if (!TextUtils.isEmpty(dynamicUITable.getFieldTag()) &&
                                        dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE)
                                        || dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_ID)
                                        || dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_RE_ENTER_KYC_ID)
                                        || dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_QR_READING_BUTTON)) {
                                    dynamicUITable.setVisibility(true);

                                    if(dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE)){
                                        dynamicUITable.setValue(SPINNER_ITEM_FIELD_NAME_AADHAAR);
                                    }
                                    // TODO: Get KYC Type based on loan
                       /* if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE)) {
                            String[] newSpinnerList = getNewSpinnerList(TAG_NAME_KYC_TYPE, loanType);
                            dynamicUITable.setParamlist(newSpinnerList);
                            dynamicUITable.setValue(SPINNER_ITEM_FIELD_NAME_AADHAAR);
                        }*/

                                    // TODO: NEED TO REMOVE THIS CONDITION
                                    if (dynamicUITable.getFieldName().equalsIgnoreCase(TAG_NAME_KYC_ID)) {
                                        DataTypeInfo datatypeInfo = new DataTypeInfo(SPINNER_ITEM_FIELD_NAME_AADHAAR, dynamicUITable);
                                        // TODO: Only here we need to check with field name
                                        dynamicUITable.setLength(datatypeInfo.getLength());
                                        dynamicUITable.setHint(datatypeInfo.getHint());
                                        dynamicUITable.setDataType(datatypeInfo.getInputType());
                                        dynamicUITable.setDataEntryType(datatypeInfo.getDataEntryType());
                                        dynamicUITable.setFieldTag(datatypeInfo.getHintTag());
                                    }
                                    if (dynamicUITable.getFieldName().equalsIgnoreCase(TAG_NAME_RE_ENTER_KYC_ID)) {
                                        DataTypeInfo datatypeInfo = new DataTypeInfo(SPINNER_ITEM_FIELD_NAME_AADHAAR,dynamicUITable);
                                        // TODO: Only here we need to check with field name
                                        dynamicUITable.setLength(datatypeInfo.getLength());
                                        dynamicUITable.setHint("Re " + datatypeInfo.getHint());
                                        dynamicUITable.setDataType(datatypeInfo.getInputType());
                                        dynamicUITable.setDataEntryType(datatypeInfo.getDataEntryType());
                                        dynamicUITable.setFieldTag(datatypeInfo.getHintTag());
                                    }
                                }
                            }
                            updateDynamicUITable(list, SCREEN_ID);
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