package com.saartak.el.fragments;

import static com.saartak.el.constants.AppConstant.PARAM_CLIENT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_PROJECT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_SCREEN_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_SCREEN_NO;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_RAT;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.google.gson.Gson;
import com.saartak.el.PopUtils;
import com.saartak.el.R;
import com.saartak.el.database.entity.RawDataTable;
import com.saartak.el.interfaces.ReturnValue;
import com.saartak.el.interfce.FragmentInterface;
import com.saartak.el.models.RAT.RATResponseDTO;
import com.saartak.el.models.RATSourseOfIncome.SPCustomerTypeResponseTable;
import com.saartak.el.models.RATSourseOfIncome.SPIndustryTypeResponseTable;
import com.saartak.el.models.RATSourseOfIncome.SPSourseOfIncomeResponseTable;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class RATFragment extends LOSBaseFragment implements FragmentInterface {

    TextView riskStatus, dateAndTime, highRisk, tv_error_message;
    Button btnRat;
    CardView card_rat_data;

    ArrayList<String> customerIdList = new ArrayList<>();
    ArrayList<String> customerTypeList = new ArrayList<>();
    ArrayList<String> industryTypeList = new ArrayList<>();
    ArrayList<String> sourceOfIncomeList = new ArrayList<>();
    EditText edCustomerType, edSourceOfIncome, edIndustryType;
    String txtcustomerType = "", txtsourceOfIncome = "", txtIndustryType = "";

    private RATFragment.OnFragmentInteractionListener mListener;


    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    public RATFragment() {
        // Required empty public constructor
    }


    String LOAN_TYPE, CLIENT_ID, PROJECT_ID, PRODUCT_ID, SCREEN_ID, SCREEN_NAME, USER_ID, MODULE_TYPE;
    // TODO: Rename and change types

    public static RATFragment newInstance(String loanType, String clientId, String projectId, String productId,
                                          String screenNo, String screenName, String userId, String moduleType) {
        RATFragment fragment = new RATFragment();
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

        View view = inflater.inflate(R.layout.fragment_r_a_t, container, false);

        edCustomerType = view.findViewById(R.id.edCustomerType);
        edSourceOfIncome = view.findViewById(R.id.edSourceOfIncome);
        edIndustryType = view.findViewById(R.id.edIndustryType);
        riskStatus = view.findViewById(R.id.tv_riskStatus);
        dateAndTime = view.findViewById(R.id.tv_date_time);
        highRisk = view.findViewById(R.id.tv_highRiskMessage);
        btnRat = view.findViewById(R.id.btn_rat);
        card_rat_data = view.findViewById(R.id.card_rat_data);
        tv_error_message = view.findViewById(R.id.tv_error_message);
        card_rat_data.setVisibility(View.GONE);

        btnRat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (appHelper.isNetworkAvailable()) {
                    if (txtcustomerType.equalsIgnoreCase("")) {
                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Please Select Customer Type");
                    } else if (txtsourceOfIncome.equalsIgnoreCase("")) {
                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Please Select Source Of Income");
                    } else if (txtIndustryType.equalsIgnoreCase("")) {
                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Please Select Industry Type");
                    } else {
                        ratServiceData(txtcustomerType, txtsourceOfIncome, txtIndustryType, USER_ID, CLIENT_ID, LOAN_TYPE, MODULE_TYPE);
                    }
                } else {
                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                            "Please check your internet connection and try again");
                }

            }
        });
        edCustomerType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopUtils.dialogListReturnValueRV(getContext(), customerTypeList, edCustomerType, new ReturnValue() {
                    @Override
                    public void returnValues(String s, int adapterPosition) {
                        txtcustomerType = s;
                        getSPSourceOfIncome(customerIdList.get(adapterPosition), "0");
                    }
                });

            }
        });

        edSourceOfIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopUtils.dialogListReturnValueRV(getContext(), sourceOfIncomeList, edSourceOfIncome, new ReturnValue() {
                    @Override
                    public void returnValues(String s, int adapterPosition) {
                        txtsourceOfIncome = s;
                    }
                });
            }
        });

        edIndustryType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopUtils.dialogListReturnValueRV(getContext(), industryTypeList, edIndustryType, new ReturnValue() {
                    @Override
                    public void returnValues(String s, int adapterPosition) {
                        txtIndustryType = s;
                    }
                });
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
            getSPIndustryType("1", "2");
            getSPCustomerType("0", "1");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void getRawData() {
        ArrayList<HashMap<String, Object>> hashMapList = new ArrayList<>();
        try {
            viewModel.getRawDataByClientIDAndModuleType(SCREEN_NAME_RAT, CLIENT_ID, MODULE_TYPE);
            if (viewModel.getRawTableLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<RawDataTable> rawDataTableList = (List<RawDataTable>) o;
                        viewModel.getRawTableLiveData().removeObserver(this);
                        if (rawDataTableList != null && rawDataTableList.size() > 0) {

                            for (RawDataTable rawDataTable : rawDataTableList) {
                                String rawData = rawDataTable.getRawdata();
                                RATResponseDTO ratResponseDTO = new Gson().fromJson(rawData, RATResponseDTO.class);
                                if (ratResponseDTO.getErrorMessage() != null && ratResponseDTO.getErrorMessage().equalsIgnoreCase("")) {
                                    if (ratResponseDTO.getApiResponse() != null) {
                                        card_rat_data.setVisibility(View.VISIBLE);
                                        tv_error_message.setVisibility(View.GONE);
                                        if (ratResponseDTO.getErrorMessage().equalsIgnoreCase("")) {
                                            riskStatus.setText(ratResponseDTO.getApiResponse().getRiskStatus());
                                            highRisk.setText(ratResponseDTO.getApiResponse().getHighRiskMessage());
                                            dateAndTime.setText(ratResponseDTO.getApiResponse().getDateTime());
                                        } else {
                                            tv_error_message.setText(ratResponseDTO.getApiResponse().getErrorDesc() + " " + ratResponseDTO.getErrorMessage());
                                        }
                                    }
                                } else {
                                    if (ratResponseDTO.getErrorMessage() != null) {
                                        tv_error_message.setText(ratResponseDTO.getErrorMessage());
                                        card_rat_data.setVisibility(View.GONE);
                                        tv_error_message.setVisibility(View.VISIBLE);
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

    private void ratServiceData(String txtcustomerType, String txtsourceOfIncome, String txtIndustryType, String userId, String clientId, String loanType, String moduleType) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            String uniqueId = String.valueOf(System.currentTimeMillis());
            viewModel.getRATServiceData(txtcustomerType, txtsourceOfIncome, txtIndustryType, userId, clientId, loanType, moduleType);
            if (viewModel.getRATResponseDTOLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        RATResponseDTO ratResponseDTO = (RATResponseDTO) o;
                        viewModel.getRATResponseDTOLiveData().removeObserver(this);

                        try {
                            if (ratResponseDTO.getErrorMessage() != null && ratResponseDTO.getErrorMessage().equalsIgnoreCase("")) {
                                if (ratResponseDTO.getApiResponse() != null) {
                                    card_rat_data.setVisibility(View.VISIBLE);
                                    tv_error_message.setVisibility(View.GONE);
                                    if (ratResponseDTO.getErrorMessage().equalsIgnoreCase("")) {
                                        riskStatus.setText(ratResponseDTO.getApiResponse().getRiskStatus());
                                        highRisk.setText(ratResponseDTO.getApiResponse().getHighRiskMessage());
                                        dateAndTime.setText(ratResponseDTO.getApiResponse().getDateTime());
                                    } else {
                                        tv_error_message.setText(ratResponseDTO.getErrorMessage());
                                    }
                                }
                            } else {
                                if (ratResponseDTO.getErrorMessage() != null) {
                                    tv_error_message.setText(ratResponseDTO.getErrorMessage());
                                    card_rat_data.setVisibility(View.GONE);
                                    tv_error_message.setVisibility(View.VISIBLE);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                viewModel.getRATResponseDTOLiveData().observe(this, observer);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
        }
    }

    public void getSPSourceOfIncome(String customerId, String type) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.getSPCustomerType(customerId, type);
            if (viewModel.getSPCustomerTypeResponseTableTableLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {

                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        ArrayList<SPCustomerTypeResponseTable> spCustomerTypeResponseTable = (ArrayList<SPCustomerTypeResponseTable>) o;
                        viewModel.getSPCustomerTypeResponseTableTableLiveData().removeObserver(this);


                        if (spCustomerTypeResponseTable != null && spCustomerTypeResponseTable.size() > 0) {
                            sourceOfIncomeList = new ArrayList<String>();

                            for (SPCustomerTypeResponseTable spCustomerTypeResponseTable1 : spCustomerTypeResponseTable) {
                                sourceOfIncomeList.add(spCustomerTypeResponseTable1.getSourceOfIncome());
                            }
                        }

                    }
                };
                viewModel.getSPCustomerTypeResponseTableTableLiveData().observe(this, observer);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
        }
    }

    public void getSPCustomerType(String customerId, String type) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.getSPSourceOfIncome(customerId, type);
            if (viewModel.getSPSourseOfIncomeResponseTableLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {

                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        ArrayList<SPSourseOfIncomeResponseTable> spSourseOfIncomeResponseTable = (ArrayList<SPSourseOfIncomeResponseTable>) o;
                        viewModel.getSPSourseOfIncomeResponseTableLiveData().removeObserver(this);
                        if (spSourseOfIncomeResponseTable != null && spSourseOfIncomeResponseTable.size() > 0) {
                            customerTypeList = new ArrayList<String>();
                            customerIdList = new ArrayList<String>();
                            for (SPSourseOfIncomeResponseTable sourseOfIncomeResponseTable : spSourseOfIncomeResponseTable) {
                                customerTypeList.add(sourseOfIncomeResponseTable.getCustomerType());
                                customerIdList.add(sourseOfIncomeResponseTable.getCustomerId());
                            }
                        }

                    }
                };
                viewModel.getSPSourseOfIncomeResponseTableLiveData().observe(this, observer);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
        }
    }

    public void getSPIndustryType(String customerId, String type) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.getSPIndustryType(customerId, type);
            if (viewModel.getSPIndustryTypeResponseTableTableTableLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {

                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        ArrayList<SPIndustryTypeResponseTable> spIndustryTypeResponseTable = (ArrayList<SPIndustryTypeResponseTable>) o;
                        viewModel.getSPIndustryTypeResponseTableTableTableLiveData().removeObserver(this);
                        if (spIndustryTypeResponseTable != null && spIndustryTypeResponseTable.size() > 0) {
                            industryTypeList = new ArrayList<String>();
                            for (SPIndustryTypeResponseTable industryTypeResponseTable : spIndustryTypeResponseTable) {
                                industryTypeList.add(industryTypeResponseTable.getIndustryType());
                            }

                        }

                    }
                };
                viewModel.getSPIndustryTypeResponseTableTableTableLiveData().observe(this, observer);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
        }
    }
}