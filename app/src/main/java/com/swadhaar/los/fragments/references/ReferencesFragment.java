package com.swadhaar.los.fragments.references;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.swadhaar.los.R;
import com.swadhaar.los.database.entity.DynamicUITable;
import com.swadhaar.los.database.entity.RawDataTable;
import com.swadhaar.los.fragments.LOSBaseFragment;
import com.swadhaar.los.models.ParameterInfo;
import com.swadhaar.los.view_models.DynamicUIViewModel;

import java.util.ArrayList;
import java.util.HashMap;
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
import static com.swadhaar.los.constants.AppConstant.SCREEN_NO_REFERENCE_CHECK_IL;
import static com.swadhaar.los.constants.AppConstant.getObjectByTAG;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.FIELD_NAME_UPDATE;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.TAG_NAME_ADDRESS_LINE_1;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.TAG_NAME_ADDRESS_LINE_2;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.TAG_NAME_CITY;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.TAG_NAME_CONTACT_NO;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.TAG_NAME_DISTRICT;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.TAG_NAME_FULL_NAME;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.TAG_NAME_LANDLINE_NUMBER;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.TAG_NAME_PINCODE;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.TAG_NAME_REFERENCE_TYPE;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.TAG_NAME_RELATIONSHIP_WITH_THE_APPLICANT;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.TAG_NAME_RE_ENTER_CONTACT_NO;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.TAG_NAME_SAVE_BUTTON;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.TAG_NAME_STATE;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ReferencesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ReferencesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReferencesFragment extends LOSBaseFragment implements LOSBaseFragment.DynamiUIinterfacce, HasSupportFragmentInjector {
    private static final String TAG = ReferencesFragment.class.getCanonicalName() ;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private OnFragmentInteractionListener mListener;

    public ReferencesFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ReferencesFragment newInstance(String loanType, String clientId, String projectId, String productId,
                                                   String screenNo, String screenName, String userId, String moduleType) {
        ReferencesFragment fragment = new ReferencesFragment();
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
        //return inflater.inflate(R.layout.fragment_common_layout, container, false);
        mRootView=(FrameLayout) inflater.inflate(R.layout.fragment_common_layout, container, false);
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ll = (LinearLayout) view.findViewById(R.id.ll_content);
        setDynamiUIinterfacce(ReferencesFragment.this);
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

    @Override
    public Fragment getFragment() {
        return this;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;

        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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

    public void configureDagger() {
        AndroidSupportInjection.inject(this);
    }


    public void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DynamicUIViewModel.class);
        viewModel.init(SCREEN_ID,SCREEN_NAME,LOAN_TYPE,PROJECT_ID,PRODUCT_ID,CLIENT_ID,USER_ID,MODULE_TYPE);
        Observer observer = new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {
                List<DynamicUITable> list = (List<DynamicUITable>) o;
                viewModel.getDynamicUITableLiveData().removeObserver(this);
//                updateUI(list);
//                getRawData(SCREEN_ID,list);
                //getRawDataForParentFragment(SCREEN_NAME,list);
//                getTagNameList(SCREEN_NAME, list, TAG_NAME_FULL_NAME);
                if(list !=null && list.size()>0) {
                    referenceVerificationScreenValidation(list.get(0),list);
                }
            }
//            }
        };
        viewModel.getDynamicUITableLiveData().observe(getViewLifecycleOwner(), observer);
    }

    // -----------------
    // UPDATE UI
    // -----------------

    private void updateUI(@Nullable List<DynamicUITable> dynamicUITable) {
        if (dynamicUITable != null) {
            dynamicUI(dynamicUITable);
        }
    }

    public void getRawData(String screen,List<DynamicUITable> list) {
        ArrayList<HashMap<String,Object>> hashMapList=new ArrayList<>();
        try {
            viewModel.getRawData(screen,CLIENT_ID,MODULE_TYPE);
            if (viewModel.getRawTableLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<RawDataTable> rawDataTableList = (List<RawDataTable>) o;
                        viewModel.getRawTableLiveData().removeObserver(this);
                        if(rawDataTableList !=null && rawDataTableList.size()>0){
                            for(RawDataTable rawDataTable:rawDataTableList){
                                HashMap<String,Object> hashMap= setKeyValueForObject(rawDataTable);
                                hashMapList.add(hashMap);
                            }
                            Log.d(TAG,"HashMapList ==> "+hashMapList.toString());
                            if(hashMapList !=null && hashMapList.size()>0){
                                // TODO: Already saved data
                                HashMap<String,Object> hashMap=hashMapList.get(0);
                                if(hashMap!=null && hashMap.size()>0){
                                    for(DynamicUITable dynamicUITable:list){
                                        dynamicUITable.setVisibility(false);
                                        if( ! TextUtils.isEmpty(dynamicUITable.getFieldTag())){
                                            if( hashMap.containsKey(dynamicUITable.getFieldTag()) ) {
                                                String value = hashMap.get(dynamicUITable.getFieldTag()).toString();
                                                if (!TextUtils.isEmpty(value)) {
                                                    dynamicUITable.setValue(value);
                                                    dynamicUITable.setVisibility(true);
                                                }
                                            }else if(dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_SAVE_BUTTON)) {
                                                dynamicUITable.setVisibility(true);
                                                dynamicUITable.setFieldName(FIELD_NAME_UPDATE);
                                            }
                                        }
                                    }

                                    updateDynamicUITable(list,SCREEN_ID);
                                }
                            }else{
                                // TODO: Fresh Data
//                                updateUI(list);
                                if(list.size()>0) {
                                 //   copyValuesFromOtherScreen(list);
                                 //   copyValuesFromReferenceCheck(list, SCREEN_NAME_REFERENCE_CHECK);
                                    DynamicUITable dynamicUITable=getObjectByTAG(TAG_NAME_FULL_NAME,list);
                                    if(dynamicUITable!=null) {
                                        getReferenceNames(dynamicUITable,list,SCREEN_NO_REFERENCE_CHECK_IL);
                                    }else {
                                        updateUI(list);
                                    }
                                }
                            }
                        }else{
                            // TODO: Fresh Data
//                            updateUI(list);
                            if(list.size()>0) {
                              //  copyValuesFromOtherScreen(list);
                              //  copyValuesFromReferenceCheck(list, SCREEN_NAME_REFERENCE_CHECK);
                                DynamicUITable dynamicUITable=getObjectByTAG(TAG_NAME_FULL_NAME,list);
                                if(dynamicUITable!=null) {
                                    getReferenceNames(dynamicUITable,list,SCREEN_NO_REFERENCE_CHECK_IL);
                                }else {
                                    updateUI(list);
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



    private void getReferenceNames(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList,String screenToGetData) {
        try {
            viewModel.getReferenceNames(dynamicUITable, dynamicUITableList,screenToGetData);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer getReferenceNamesObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableList1 = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);
                        dynamicUI(dynamicUITableList1);
                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, getReferenceNamesObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void copyValuesFromReferenceCheck(List<DynamicUITable> dynamicUITableList, String fromScreen) {
        try {
            DynamicUITable dynamicUITable = dynamicUITableList.get(0);
            viewModel.copyDataFromReferenceCheck(dynamicUITable,dynamicUITableList, fromScreen);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer copyDataFromReferenceCheckObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableList1 = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);
                        dynamicUI(dynamicUITableList1);
                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, copyDataFromReferenceCheckObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }



    private void copyValuesFromOtherScreen(List<DynamicUITable> dynamicUITableList){
        List<ParameterInfo> fromScreenList = new ArrayList<>();
        fromScreenList.add(new ParameterInfo(TAG_NAME_REFERENCE_TYPE, SCREEN_NO_REFERENCE_CHECK_IL, "", true, true));
        fromScreenList.add(new ParameterInfo(TAG_NAME_FULL_NAME, SCREEN_NO_REFERENCE_CHECK_IL, "", true, true));
        fromScreenList.add(new ParameterInfo(TAG_NAME_CONTACT_NO, SCREEN_NO_REFERENCE_CHECK_IL, "", true, true));
        fromScreenList.add(new ParameterInfo(TAG_NAME_RE_ENTER_CONTACT_NO, SCREEN_NO_REFERENCE_CHECK_IL, "", true, true));
        fromScreenList.add(new ParameterInfo(TAG_NAME_ADDRESS_LINE_1, SCREEN_NO_REFERENCE_CHECK_IL, "", true, true));
        fromScreenList.add(new ParameterInfo(TAG_NAME_ADDRESS_LINE_2, SCREEN_NO_REFERENCE_CHECK_IL, "", true, true));
        fromScreenList.add(new ParameterInfo(TAG_NAME_PINCODE, SCREEN_NO_REFERENCE_CHECK_IL, "", true, true));
        fromScreenList.add(new ParameterInfo(TAG_NAME_CITY, SCREEN_NO_REFERENCE_CHECK_IL, "", true, true));
        fromScreenList.add(new ParameterInfo(TAG_NAME_DISTRICT, SCREEN_NO_REFERENCE_CHECK_IL, "", true, true));
        fromScreenList.add(new ParameterInfo(TAG_NAME_STATE, SCREEN_NO_REFERENCE_CHECK_IL, "", true, true));
        fromScreenList.add(new ParameterInfo(TAG_NAME_LANDLINE_NUMBER, SCREEN_NO_REFERENCE_CHECK_IL, "", true, true));
        fromScreenList.add(new ParameterInfo(TAG_NAME_REFERENCE_TYPE, SCREEN_NO_REFERENCE_CHECK_IL, "", true, true));

        List<ParameterInfo> toScreenList = new ArrayList<>();
        toScreenList.add(new ParameterInfo(TAG_NAME_REFERENCE_TYPE, SCREEN_ID, "", true, false));
        toScreenList.add(new ParameterInfo(TAG_NAME_FULL_NAME, SCREEN_ID, "", true, false));
        toScreenList.add(new ParameterInfo(TAG_NAME_CONTACT_NO, SCREEN_ID, "", true, false));
        toScreenList.add(new ParameterInfo(TAG_NAME_RE_ENTER_CONTACT_NO, SCREEN_ID, "", true, false));
        toScreenList.add(new ParameterInfo(TAG_NAME_ADDRESS_LINE_1, SCREEN_ID, "", true, false));
        toScreenList.add(new ParameterInfo(TAG_NAME_ADDRESS_LINE_2, SCREEN_ID, "", true, false));
        toScreenList.add(new ParameterInfo(TAG_NAME_PINCODE, SCREEN_ID, "", true, false));
        toScreenList.add(new ParameterInfo(TAG_NAME_CITY, SCREEN_ID, "", true, false));
        toScreenList.add(new ParameterInfo(TAG_NAME_DISTRICT, SCREEN_ID, "", true, false));
        toScreenList.add(new ParameterInfo(TAG_NAME_STATE, SCREEN_ID, "", true, false));
        toScreenList.add(new ParameterInfo(TAG_NAME_LANDLINE_NUMBER, SCREEN_ID, "", true, false));
        toScreenList.add(new ParameterInfo(TAG_NAME_RELATIONSHIP_WITH_THE_APPLICANT, SCREEN_ID, "", true, false));
        copyValuesFromScreenToScreen(fromScreenList,toScreenList,dynamicUITableList);

    }
}
