package com.saartak.el.fragments;

import static com.saartak.el.constants.AppConstant.MODULE_TYPE_APPLICANT;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_GENERATE_CIBIL;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_LEAD;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_LOAN_PROPOSAL_WITH_NOMINEE;
import static com.saartak.el.constants.AppConstant.PARAM_CLIENT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_PROJECT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_SCREEN_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_SCREEN_NO;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_APPLICANT_KYC;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_APPLICANT_LOAN_PROPOSAL;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_BUSINESS_ADDRESS_PROOF;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_BUSINESS_PROFILE;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_HUNTER;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_LEAD;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_OFFICE_ADDRESS_PROOF;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_SALARY_PROFILE;
import static com.saartak.el.dynamicui.constants.ParametersConstant.RADIO_BUTTON_ITEM_SENP;
import static com.saartak.el.dynamicui.constants.ParametersConstant.RADIO_BUTTON_ITEM_SEP;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_CUSTOMER_TYPE;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_INDIVIDUAL_OR_NONINDIVIDUAL;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_NONINDIVIDUAL;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_TYPE_OF_PROFESSION;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.google.gson.Gson;
import com.saartak.el.R;
import com.saartak.el.adapter.HunterAdapter;
import com.saartak.el.database.entity.RawDataTable;
import com.saartak.el.interfce.FragmentInterface;
import com.saartak.el.models.Hunter.HunterResponseDTO;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class HunterFragment extends LOSBaseFragment implements FragmentInterface, HunterAdapter.HunterInterface {
    private static final String TAG = HunterFragment.class.getCanonicalName();

    TextView rule, score, remarks, status, referenceId, tv_error_message;
    Button btn_Hunter;
    CardView cardHunterData;
    HunterAdapter hunterAdapter;
    RecyclerView rv_hunter;

    private HunterFragment.OnFragmentInteractionListener mListener;


    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    public HunterFragment() {
        // Required empty public constructor
    }


    String LOAN_TYPE, CLIENT_ID, PROJECT_ID, PRODUCT_ID, SCREEN_ID, SCREEN_NAME, USER_ID, MODULE_TYPE;
    String nonIndividual="";
    Boolean isLoanProposal=false,isSalary=false,isBusiness=false,isKYCScreen=false;
    // TODO: Rename and change types

    public static HunterFragment newInstance(String loanType, String clientId, String projectId, String productId,
                                             String screenNo, String screenName, String userId, String moduleType) {
        HunterFragment fragment = new HunterFragment();
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

        View view = inflater.inflate(R.layout.fragment_hunter, container, false);


        rule = view.findViewById(R.id.rule);
        rv_hunter = view.findViewById(R.id.rv_hunter);


        btn_Hunter = view.findViewById(R.id.btn_hunter);
        score = view.findViewById(R.id.score);
        cardHunterData = view.findViewById(R.id.card_hunter_data);
        tv_error_message = view.findViewById(R.id.tv_error_message);

        status = view.findViewById(R.id.hunter_status);
        referenceId = view.findViewById(R.id.hunter_ref_id);
        remarks = view.findViewById(R.id.remarks);
        cardHunterData.setVisibility(View.GONE);

        btn_Hunter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (appHelper.isNetworkAvailable()) {
                    if(nonIndividual.equalsIgnoreCase("")){
                        if(isLoanProposal == true && isKYCScreen == true) {
                            callHunterService(USER_ID, CLIENT_ID, LOAN_TYPE, MODULE_TYPE_GENERATE_CIBIL);
                        }else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "KYC Details and Loan proposal should be mandatory");
                        }
                    }else if(isLoanProposal == true && isKYCScreen == true && (isBusiness == true || isSalary == true)) {
                        if (nonIndividual.equalsIgnoreCase(RADIO_BUTTON_ITEM_SEP) || nonIndividual.equalsIgnoreCase(RADIO_BUTTON_ITEM_SENP)) {
                            getHunterNonIndividualServiceData(USER_ID, CLIENT_ID, LOAN_TYPE, MODULE_TYPE_GENERATE_CIBIL);
                        } else {
                            callHunterService(USER_ID, CLIENT_ID, LOAN_TYPE, MODULE_TYPE_GENERATE_CIBIL);
                        }
                    }else {
                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "KYC Details and Loan proposal And Business/Salary screens should be mandatory");
                    }
                } else {
                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                            "Please check your internet connection and try again");
                }
            }
        });

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

    @Override
    public void generatehuterCallBack(String ucic_id, List<HunterResponseDTO.ApiResponse.ResultBlock.MatchResponse.MatchResult.MatchSummary.Rules.Rule> apiResponse) {

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
        try {
            getLeadRawData();
            getLoanProposalRawData();
            getSalaryProfile();
            getBusinessProfile();
            getRawData();
            getKYCData();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void fragmentBecameVisible() {

    }

    private void getLeadRawData() {

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
                                        if (hashMap.containsKey(TAG_NAME_CUSTOMER_TYPE)) {
                                            nonIndividual = hashMap.get(TAG_NAME_CUSTOMER_TYPE).toString();
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

    private void getLoanProposalRawData() {
        try {
            viewModel.getRawDataByClientIDAndModuleType(SCREEN_NAME_APPLICANT_LOAN_PROPOSAL, CLIENT_ID, MODULE_TYPE_LOAN_PROPOSAL_WITH_NOMINEE);
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
                                        isLoanProposal=true;
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

    private void getSalaryProfile() {
        try {
            viewModel.getRawDataByClientIDAndModuleType(SCREEN_NAME_SALARY_PROFILE, CLIENT_ID, MODULE_TYPE_APPLICANT);
            if (viewModel.getRawTableLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<RawDataTable> rawDataTableList = (List<RawDataTable>) o;
                        viewModel.getRawTableLiveData().removeObserver(this);
                        if (rawDataTableList != null && rawDataTableList.size() > 0) {
                            isSalary=true;
                        }else {
                            isSalary=false;
                        }
                    }
                };
                viewModel.getRawTableLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            viewModel.getRawDataByClientIDAndModuleType(SCREEN_NAME_OFFICE_ADDRESS_PROOF, CLIENT_ID, MODULE_TYPE_APPLICANT);
            if (viewModel.getRawTableLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<RawDataTable> rawDataTableList = (List<RawDataTable>) o;
                        viewModel.getRawTableLiveData().removeObserver(this);
                        if (rawDataTableList != null && rawDataTableList.size() > 0) {
                            isSalary=true;
                        }else {
                            isSalary=false;
                        }
                    }
                };
                viewModel.getRawTableLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    private void getBusinessProfile() {
        try {
            viewModel.getRawDataByClientIDAndModuleType(SCREEN_NAME_BUSINESS_PROFILE, CLIENT_ID, MODULE_TYPE_APPLICANT);
            if (viewModel.getRawTableLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<RawDataTable> rawDataTableList = (List<RawDataTable>) o;
                        viewModel.getRawTableLiveData().removeObserver(this);
                        if (rawDataTableList != null && rawDataTableList.size() > 0) {
                            isBusiness=true;
                        }else {
                            isBusiness=false;
                        }
                    }
                };
                viewModel.getRawTableLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            viewModel.getRawDataByClientIDAndModuleType(SCREEN_NAME_BUSINESS_ADDRESS_PROOF, CLIENT_ID, MODULE_TYPE_APPLICANT);
            if (viewModel.getRawTableLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<RawDataTable> rawDataTableList = (List<RawDataTable>) o;
                        viewModel.getRawTableLiveData().removeObserver(this);
                        if (rawDataTableList != null && rawDataTableList.size() > 0) {
                            isBusiness=true;
                        }else {
                            isBusiness=false;
                        }
                    }
                };
                viewModel.getRawTableLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    private void getRawData() {
        try {
            viewModel.getRawDataByClientIDAndModuleType(SCREEN_NAME_HUNTER, CLIENT_ID, MODULE_TYPE_GENERATE_CIBIL);
            if (viewModel.getRawTableLiveData() != null) {

                viewModel.getRawTableLiveData().observe(getViewLifecycleOwner(), rawDataTableList -> {
                    for (RawDataTable rawDataTable : rawDataTableList) {
                        String rawData = rawDataTable.getRawdata();
                        HunterResponseDTO hunterResponseDTO = new Gson().fromJson(rawData, HunterResponseDTO.class);
                        if (hunterResponseDTO.getErrorMessage() != null && hunterResponseDTO.getErrorMessage().equalsIgnoreCase("")) {
                            cardHunterData.setVisibility(View.VISIBLE);
                            tv_error_message.setVisibility(View.GONE);

                            try {
                                if (hunterResponseDTO.getApiResponse().getResultBlock().getMatchResponse().getMatchResult().getMatchSummary().getTotalMatchScore().equalsIgnoreCase("0")) {
                                    status.setText("Not Match");
                                    score.setVisibility(View.VISIBLE);
                                    score.setText(hunterResponseDTO.getApiResponse().getResultBlock().getMatchResponse().getMatchResult().getMatchSummary().getTotalMatchScore());
                                } else {
                                    status.setText("Match");
                                    rv_hunter.setHasFixedSize(true);
                                    rv_hunter.setLayoutManager(new GridLayoutManager(getActivity(), 1, RecyclerView.VERTICAL, false));
                                    hunterAdapter = new HunterAdapter(getActivity(), hunterResponseDTO.getApiResponse().getResultBlock().getMatchResponse().getMatchResult().getMatchSummary().getRules().getRule(), this, appHelper);
                                    rv_hunter.setAdapter(hunterAdapter);
                                    score.setVisibility(View.GONE);
                                }
                                referenceId.setText(hunterResponseDTO.getUniqueId());
                                rule.setText(hunterResponseDTO.getApiResponse().getResultBlock().getMatchResponse().getMatchResult().getMatchSummary().getRules().getTotalRuleCount());

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            if (hunterResponseDTO.getErrorMessage() != null)
                                tv_error_message.setText(hunterResponseDTO.getErrorMessage());
                        }

                    }
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void callHunterService(String userId, String clientId, String loanType, String moduleType) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            String uniqueId = String.valueOf(System.currentTimeMillis());
            viewModel.getHunterServiceData(userId, clientId, loanType, moduleType);
            if (viewModel.getHunterResponseDTOLiveData() != null) {
                viewModel.getHunterResponseDTOLiveData().observe(getViewLifecycleOwner(), hunterResponseDTO -> {
                    appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                    if (hunterResponseDTO.getErrorMessage() != null && hunterResponseDTO.getErrorMessage().equalsIgnoreCase("")) {
                        cardHunterData.setVisibility(View.VISIBLE);
                        tv_error_message.setVisibility(View.GONE);
                        if (hunterResponseDTO.getApiResponse().getResultBlock().getMatchResponse().getMatchResult().getMatchSummary().getTotalMatchScore().equalsIgnoreCase("0")) {
                            status.setText("Not Match");
                            score.setVisibility(View.VISIBLE);
                            score.setText(hunterResponseDTO.getApiResponse().getResultBlock().getMatchResponse().getMatchResult().getMatchSummary().getTotalMatchScore());
                        } else {
                            status.setText("Match");
                            rv_hunter.setHasFixedSize(true);
                            rv_hunter.setLayoutManager(new GridLayoutManager(getActivity(), 1, RecyclerView.VERTICAL, false));
                            hunterAdapter = new HunterAdapter(getActivity(), hunterResponseDTO.getApiResponse().getResultBlock().getMatchResponse().getMatchResult().getMatchSummary().getRules().getRule(), this, appHelper);
                            rv_hunter.setAdapter(hunterAdapter);
                            score.setVisibility(View.GONE);
                        }
                        referenceId.setText(hunterResponseDTO.getUniqueId());
                        rule.setText(hunterResponseDTO.getApiResponse().getResultBlock().getMatchResponse().getMatchResult().getMatchSummary().getRules().getTotalRuleCount());
                    } else {
                        if (hunterResponseDTO.getErrorMessage() != null)
                            tv_error_message.setText(hunterResponseDTO.getErrorMessage());
                        Log.d(TAG, "get the hunter salaried error message..............." + hunterResponseDTO.getErrorMessage());
                    }

                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
        }
    }

    private void getHunterNonIndividualServiceData(String userId, String clientId, String loanType, String moduleType) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            String uniqueId = String.valueOf(System.currentTimeMillis());
            viewModel.getHunterNonIndividualServiceData(userId, clientId, loanType, moduleType);
            if (viewModel.getHunterResponseDTOLiveData() != null) {
                viewModel.getHunterResponseDTOLiveData().observe(getViewLifecycleOwner(), hunterResponseDTO -> {
                    appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                    if (hunterResponseDTO.getErrorMessage() != null && hunterResponseDTO.getErrorMessage().equalsIgnoreCase("")) {
                        cardHunterData.setVisibility(View.VISIBLE);
                        tv_error_message.setVisibility(View.GONE);
                        try {
                            if (hunterResponseDTO.getApiResponse().getResultBlock().getMatchResponse().getMatchResult().getMatchSummary().getTotalMatchScore().equalsIgnoreCase("0")) {
                                status.setText("Not Match");
                                score.setVisibility(View.VISIBLE);
                                score.setText(hunterResponseDTO.getApiResponse().getResultBlock().getMatchResponse().getMatchResult().getMatchSummary().getTotalMatchScore());
                            } else {
                                status.setText("Match");
                                rv_hunter.setHasFixedSize(true);
                                rv_hunter.setLayoutManager(new GridLayoutManager(getActivity(), 1, RecyclerView.VERTICAL, false));
                                hunterAdapter = new HunterAdapter(getActivity(), hunterResponseDTO.getApiResponse().getResultBlock().getMatchResponse().getMatchResult().getMatchSummary().getRules().getRule(), this, appHelper);
                                rv_hunter.setAdapter(hunterAdapter);
                                score.setVisibility(View.GONE);
                            }
                            referenceId.setText(hunterResponseDTO.getUniqueId());
                            rule.setText(hunterResponseDTO.getApiResponse().getResultBlock().getMatchResponse().getMatchResult().getMatchSummary().getRules().getTotalRuleCount());

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        if (hunterResponseDTO.getErrorMessage() != null)
                            tv_error_message.setText(hunterResponseDTO.getErrorMessage());
                    }

                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
        }
    }

    private void getKYCData() {
        try {
            viewModel.getRawDataByClientIDAndModuleType(SCREEN_NAME_APPLICANT_KYC, CLIENT_ID, MODULE_TYPE_APPLICANT);

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
                                        isKYCScreen = true;
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

}