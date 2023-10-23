package com.saartak.el.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.bfil.uilibrary.helpers.AppHelper;
import com.saartak.el.R;
import com.saartak.el.database.converter.TimestampConverter;
import com.saartak.el.database.entity.CashDenominationTable;
import com.saartak.el.models.CashCollectionSummaryDTO;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.HasSupportFragmentInjector;

import static com.saartak.el.constants.AppConstant.DATE_FORMAT_YYYY_MM_DD;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_ID;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_NAME;

public class DenominationFragment extends DialogFragment implements HasSupportFragmentInjector ,TextWatcher{

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    DenominationInterface denominationInterface;

    private EditText etCount2000,etCount1000,etCount500,etCount200,etCount100,etCount50,etCount20,etCount10,etCount5,etCount2,etCount1;
    private TextView tvTotalAmt2000,tvTotalAmt1000,tvTotalAmt500,tvTotalAmt200,tvTotalAmt100,tvTotalAmt50,tvTotalAmt20,
            tvTotalAmt10,tvTotalAmt5,tvTotalAmt2,tvTotalAmt1,tvOverAllTotalAmt,tvTotalCount, tvTotalCashNeedsToCollect;
    private Button btnCancel,btnSave;

    public String loanType,moduleType,branchId,branchName,branchGSTcode,userName, userId,productId, roleName,CENTER_NAME;

    public AppHelper appHelper;

    CashDenominationTable cashDenominationTable;

    public DenominationFragment() {
        // TODO: Empty Constructor
    }


    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        appHelper = new AppHelper(getActivity());

        if (getArguments() != null) {

            userName = getArguments().getString(PARAM_USER_NAME);
            userId = getArguments().getString(PARAM_USER_ID);
            branchId = getArguments().getString(PARAM_BRANCH_ID);
            branchGSTcode = getArguments().getString(PARAM_BRANCH_GST_CODE);
            loanType = getArguments().getString(PARAM_LOAN_TYPE);
            productId = getArguments().getString(PARAM_PRODUCT_ID);
//            CENTER_NAME = getArguments().getString(PARAM_CENTER_NAME);

            denominationInterface=(DenominationInterface)getActivity();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        getDialog().setTitle("Cash Denomination");

        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_cash_denomination, container, false);
//        setCancelable(false);

        etCount2000=(EditText)rootView.findViewById(R.id.et_count_2000);
        etCount1000=(EditText)rootView.findViewById(R.id.et_count_1000);
        etCount500=(EditText)rootView.findViewById(R.id.et_count_500);
        etCount200=(EditText)rootView.findViewById(R.id.et_count_200);
        etCount100=(EditText)rootView.findViewById(R.id.et_count_100);
        etCount50=(EditText)rootView.findViewById(R.id.et_count_50);
        etCount20=(EditText)rootView.findViewById(R.id.et_count_20);
        etCount10=(EditText)rootView.findViewById(R.id.et_count_10);
        etCount5=(EditText)rootView.findViewById(R.id.et_count_5);
        etCount2=(EditText)rootView.findViewById(R.id.et_count_2);
        etCount1=(EditText)rootView.findViewById(R.id.et_count_1);


        tvTotalAmt2000=(TextView) rootView.findViewById(R.id.tv_total_amt_2000);
        tvTotalAmt1000=(TextView) rootView.findViewById(R.id.tv_total_amt_1000);
        tvTotalAmt500=(TextView) rootView.findViewById(R.id.tv_total_amt_500);
        tvTotalAmt200=(TextView) rootView.findViewById(R.id.tv_total_amt_200);
        tvTotalAmt100=(TextView) rootView.findViewById(R.id.tv_total_amt_100);
        tvTotalAmt50=(TextView) rootView.findViewById(R.id.tv_total_amt_50);
        tvTotalAmt20=(TextView) rootView.findViewById(R.id.tv_total_amt_20);
        tvTotalAmt10=(TextView) rootView.findViewById(R.id.tv_total_amt_10);
        tvTotalAmt5=(TextView) rootView.findViewById(R.id.tv_total_amt_5);
        tvTotalAmt2=(TextView) rootView.findViewById(R.id.tv_total_amt_2);
        tvTotalAmt1=(TextView) rootView.findViewById(R.id.tv_total_amt_1);
        tvTotalCount=(TextView) rootView.findViewById(R.id.tv_total_count);
        tvOverAllTotalAmt=(TextView) rootView.findViewById(R.id.tv_overall_total_amt);
        tvTotalCashNeedsToCollect =(TextView) rootView.findViewById(R.id.tv_total_collection_amount);

        btnCancel=(Button) rootView.findViewById(R.id.btn_cancel);
        btnSave=(Button) rootView.findViewById(R.id.btn_save);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (!TextUtils.isEmpty(tvOverAllTotalAmt.getText().toString())) {
                        int totalCollectionAmount = Integer.parseInt(tvTotalCashNeedsToCollect.getText().toString());
                        int overAllTotalAmt = Integer.parseInt(tvOverAllTotalAmt.getText().toString());

                        if (overAllTotalAmt == totalCollectionAmount) {
                            if (cashDenominationTable != null && cashDenominationTable.getTotal_amount() > 0) {
                                saveCashDenomination(cashDenominationTable);
                            } else {
                                Toast.makeText(getActivity(), "Total Amount Does Not Matching With Collection Amount", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Total Amount Does Not Matching With Collection Amount", Toast.LENGTH_SHORT).show();
                        }
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.configureDagger();
        this.configureViewModel();

        // TODO: Getting cash collection summary list
//        Date dateTime = TimestampConverter.toDate(appHelper.getCurrentDateTime(DATE_FORMAT_YYYY_MM_DD));
//        if(dateTime !=null) {
//            getCashCollectionSummaryList(userId,dateTime);
//        }
//        String cmDateFromCalendar = DUMMY_CENTER_MEETING_DATE; // TODO: Hardcoded date
//        Date cmDate = TimestampConverter.toDate(cmDateFromCalendar);
        Date cmDate = TimestampConverter.toDate(appHelper.getCurrentDateTime(DATE_FORMAT_YYYY_MM_DD));
        if(cmDate !=null) {
            getCashCollectionSummaryList(userId,cmDate);
        }
    }

    // -----------------
    // CONFIGURATION
    // -----------------

    public void configureDagger(){
        AndroidSupportInjection.inject(this);
    }

    public void configureViewModel(){
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DynamicUIViewModel.class);
    }


    private void saveCashDenomination(CashDenominationTable cashDenominationTable) {
        try {
            viewModel.saveCashDenomination(cashDenominationTable);
            if (viewModel.getCashDenominationTableLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        CashDenominationTable cashDenominationTableFromDB = (CashDenominationTable) o;
                        viewModel.getCashDenominationTableLiveData().removeObserver(this);

                        if(cashDenominationTableFromDB !=null){
                            Toast.makeText(getActivity(),"Cash Denomination Saved Successfully",Toast.LENGTH_SHORT).show();
                            denominationInterface.successCallBack();
                            dismiss();
                        }
                        else{
                            Toast.makeText(getActivity(),"Cash Denomination Failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                };

                viewModel.getCashDenominationTableLiveData().observe(this, observer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        // TODO: Calculate Denomination
        calculateDenomination();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    private void calculateDenomination(){
        try{
            int count_2000 = 0,count_1000= 0,count_500= 0,count_200= 0,count_100= 0,count_50= 0,
                    count_20= 0,count_10= 0,count_5= 0,count_2= 0,count_1= 0,total_count=0;
            int total_2000= 0,total_1000= 0,total_500= 0,total_200= 0,total_100= 0,total_50= 0,
                    total_20= 0,total_10= 0,total_5= 0,total_2= 0,total_1= 0,overAllTotal=0;

            if( !TextUtils.isEmpty(etCount2000.getText().toString())) {
                count_2000 = Integer.parseInt(etCount2000.getText().toString());
                cashDenominationTable.setCount_2000(count_2000);
            }else{
                count_2000 = 0;
                cashDenominationTable.setCount_2000(count_2000);
            }
            if( !TextUtils.isEmpty(etCount1000.getText().toString())) {
                count_1000 = Integer.parseInt(etCount1000.getText().toString());
                cashDenominationTable.setCount_1000(count_1000);
            }else{
                count_1000 = 0;
                cashDenominationTable.setCount_1000(count_1000);
            }
            if( !TextUtils.isEmpty(etCount500.getText().toString())) {
                count_500 = Integer.parseInt(etCount500.getText().toString());
                cashDenominationTable.setCount_500(count_500);
            }else{
                count_500 = 0;
                cashDenominationTable.setCount_500(count_500);
            }
            if( !TextUtils.isEmpty(etCount200.getText().toString())) {
                count_200 = Integer.parseInt(etCount200.getText().toString());
                cashDenominationTable.setCount_200(count_200);
            }else{
                count_200 = 0;
                cashDenominationTable.setCount_200(count_200);
            }
            if( !TextUtils.isEmpty(etCount100.getText().toString())) {
                count_100 = Integer.parseInt(etCount100.getText().toString());
                cashDenominationTable.setCount_100(count_100);
            }else{
                count_100 = 0;
                cashDenominationTable.setCount_100(count_100);
            }
            if( !TextUtils.isEmpty(etCount50.getText().toString())) {
                count_50 = Integer.parseInt(etCount50.getText().toString());
                cashDenominationTable.setCount_50(count_50);
            }else{
                count_50 = 0;
                cashDenominationTable.setCount_50(count_50);
            }
            if( !TextUtils.isEmpty(etCount20.getText().toString())) {
                count_20 = Integer.parseInt(etCount20.getText().toString());
                cashDenominationTable.setCount_20(count_20);
            }else{
                count_20 = 0;
                cashDenominationTable.setCount_20(count_20);
            }
            if( !TextUtils.isEmpty(etCount10.getText().toString())) {
                count_10 = Integer.parseInt(etCount10.getText().toString());
                cashDenominationTable.setCount_10(count_10);
            }else{
                count_10 = 0;
                cashDenominationTable.setCount_10(count_10);
            }
            if( !TextUtils.isEmpty(etCount5.getText().toString())) {
                count_5 = Integer.parseInt(etCount5.getText().toString());
                cashDenominationTable.setCount_5(count_5);
            }else{
                count_5 = 0;
                cashDenominationTable.setCount_5(count_5);
            }
            if( !TextUtils.isEmpty(etCount2.getText().toString())) {
                count_2 = Integer.parseInt(etCount2.getText().toString());
                cashDenominationTable.setCount_2(count_2);
            }else{
                count_2 = 0;
                cashDenominationTable.setCount_2(count_2);
            }
            if( !TextUtils.isEmpty(etCount1.getText().toString())) {
                count_1 = Integer.parseInt(etCount1.getText().toString());
                cashDenominationTable.setCount_1(count_1);
            }else{
                count_1 = 0;
                cashDenominationTable.setCount_1(count_1);
            }

            total_2000=count_2000 * 2000;
            total_1000=count_1000 * 1000;
            total_500=count_500 * 500;
            total_200=count_200 * 200;
            total_100=count_100 * 100;
            total_50=count_50 * 50;
            total_20=count_20 * 20;
            total_10=count_10 * 10;
            total_5=count_5 * 5;
            total_2=count_2 * 2;
            total_1=count_1 ;

            total_count=count_2000+count_1000+count_500+count_200+count_100+count_50+count_20+count_10+count_5+count_2+count_1;
            overAllTotal =total_2000+total_1000+total_500+total_200+total_100+total_50+total_20+total_10+total_5+total_2+total_1;

            // TODO: Assigning value
            tvTotalAmt2000.setText(String.valueOf(total_2000));
            tvTotalAmt1000.setText(String.valueOf(total_1000));
            tvTotalAmt500.setText(String.valueOf(total_500));
            tvTotalAmt200.setText(String.valueOf(total_200));
            tvTotalAmt100.setText(String.valueOf(total_100));
            tvTotalAmt50.setText(String.valueOf(total_50));
            tvTotalAmt20.setText(String.valueOf(total_20));
            tvTotalAmt10.setText(String.valueOf(total_10));
            tvTotalAmt5.setText(String.valueOf(total_5));
            tvTotalAmt2.setText(String.valueOf(total_2));
            tvTotalAmt1.setText(String.valueOf(total_1));

            tvTotalCount.setText(String.valueOf(total_count));
            tvOverAllTotalAmt.setText(String.valueOf(overAllTotal));

            cashDenominationTable.setTotal_2000(total_2000);
            cashDenominationTable.setTotal_1000(total_1000);
            cashDenominationTable.setTotal_500(total_500);
            cashDenominationTable.setTotal_200(total_200);
            cashDenominationTable.setTotal_100(total_100);
            cashDenominationTable.setTotal_50(total_50);
            cashDenominationTable.setTotal_20(total_20);
            cashDenominationTable.setTotal_10(total_10);
            cashDenominationTable.setTotal_5(total_5);
            cashDenominationTable.setTotal_2(total_2);
            cashDenominationTable.setTotal_1(total_1);
            cashDenominationTable.setTotal_count(total_count);
            cashDenominationTable.setTotal_amount(overAllTotal);

            String strTotalCashCollection= tvTotalCashNeedsToCollect.getText().toString();
            int totalCashNeedsToCollect=0;
            if( ! TextUtils.isEmpty(strTotalCashCollection)){
                totalCashNeedsToCollect=Integer.parseInt(strTotalCashCollection);
            }
            if(cashDenominationTable.getTotal_amount() == totalCashNeedsToCollect){
                tvOverAllTotalAmt.setTextColor(getResources().getColor(R.color.light_green));
            }
            else{
                tvOverAllTotalAmt.setTextColor(getResources().getColor(R.color.rbl_red));
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    // TODO: GETTING CASH COLLECTION SUMMARY LIST
    private void getCashCollectionSummaryList(String userId,Date cmDate ) {
        appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

        try {
            viewModel.getCashCollectionSummaryList(userId,cmDate);
            if (viewModel.getCashCollectionSummaryDTOLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        CashCollectionSummaryDTO cashCollectionSummaryDTO = (CashCollectionSummaryDTO) o;
                        viewModel.getCashCollectionSummaryDTOLiveData().removeObserver(this);

                        if(cashCollectionSummaryDTO !=null && cashCollectionSummaryDTO.getIndividualCenterCollectionList() !=null
                                && cashCollectionSummaryDTO.getIndividualCenterCollectionList().size()>0){

                            // TODO: Setting Total Collection Amount
//                            tvTotalCashNeedsToCollect.setText(String.valueOf(cashCollectionSummaryDTO.getOverAllTotalCollection()));
                            tvTotalCashNeedsToCollect.setText(String.valueOf(cashCollectionSummaryDTO.getOverAllTotalCollectionSyncFalse()));

                            // TODO: Getting Cash Denomination Table
//                            getCashDenominationTable(userId,cmDate);
                            if(cashDenominationTable ==null ) {
                                cashDenominationTable = new CashDenominationTable();
                                cashDenominationTable.setStaffId(userId);
                                cashDenominationTable.setCMDate(cmDate);

                                String timeStamp = new SimpleDateFormat("yyMMddHHmmssSSS",
                                        Locale.getDefault()).format(new Date());
                                String EMP_LAST_5_DIGIT = userId.substring(3);
                                String REF_ID = EMP_LAST_5_DIGIT + timeStamp;
                                cashDenominationTable.setRefId(REF_ID);
                            }

                            // TODO: Initiating Listener
                            etCount2000.addTextChangedListener(DenominationFragment.this);
                            etCount1000.addTextChangedListener(DenominationFragment.this);
                            etCount500.addTextChangedListener(DenominationFragment.this);
                            etCount200.addTextChangedListener(DenominationFragment.this);
                            etCount100.addTextChangedListener(DenominationFragment.this);
                            etCount50.addTextChangedListener(DenominationFragment.this);
                            etCount20.addTextChangedListener(DenominationFragment.this);
                            etCount10.addTextChangedListener(DenominationFragment.this);
                            etCount5.addTextChangedListener(DenominationFragment.this);
                            etCount2.addTextChangedListener(DenominationFragment.this);
                            etCount1.addTextChangedListener(DenominationFragment.this);
                        }
                        else{
                            // TODO: Initiating Listener
                            etCount2000.addTextChangedListener(DenominationFragment.this);
                            etCount1000.addTextChangedListener(DenominationFragment.this);
                            etCount500.addTextChangedListener(DenominationFragment.this);
                            etCount200.addTextChangedListener(DenominationFragment.this);
                            etCount100.addTextChangedListener(DenominationFragment.this);
                            etCount50.addTextChangedListener(DenominationFragment.this);
                            etCount20.addTextChangedListener(DenominationFragment.this);
                            etCount10.addTextChangedListener(DenominationFragment.this);
                            etCount5.addTextChangedListener(DenominationFragment.this);
                            etCount2.addTextChangedListener(DenominationFragment.this);
                            etCount1.addTextChangedListener(DenominationFragment.this);
                        }
                    }
                };
                viewModel.getCashCollectionSummaryDTOLiveData().observe(this, observer);
            }
        } catch (Exception ex) {
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            ex.printStackTrace();
        }
    }

    // TODO: GETTING CASH DENOMINATION TABLE
    private void getCashDenominationTable(String userId,Date cmDate ) {
        appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

        try {
            viewModel.getCashDenominationTable(userId,cmDate);
            if (viewModel.getCashDenominationTableLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        cashDenominationTable = (CashDenominationTable) o;
                        viewModel.getCashDenominationTableLiveData().removeObserver(this);

                        if(cashDenominationTable !=null ){
                            // TODO: Assigning value
                            etCount2000.setText(String.valueOf(cashDenominationTable.getCount_2000()));
                            etCount1000.setText(String.valueOf(cashDenominationTable.getCount_1000()));
                            etCount500.setText(String.valueOf(cashDenominationTable.getCount_500()));
                            etCount200.setText(String.valueOf(cashDenominationTable.getCount_200()));
                            etCount100.setText(String.valueOf(cashDenominationTable.getCount_100()));
                            etCount50.setText(String.valueOf(cashDenominationTable.getCount_50()));
                            etCount20.setText(String.valueOf(cashDenominationTable.getCount_20()));
                            etCount10.setText(String.valueOf(cashDenominationTable.getCount_10()));
                            etCount5.setText(String.valueOf(cashDenominationTable.getCount_5()));
                            etCount2.setText(String.valueOf(cashDenominationTable.getCount_2()));
                            etCount1.setText(String.valueOf(cashDenominationTable.getCount_1()));

                            tvTotalAmt2000.setText(String.valueOf(cashDenominationTable.getTotal_2000()));
                            tvTotalAmt1000.setText(String.valueOf(cashDenominationTable.getTotal_1000()));
                            tvTotalAmt500.setText(String.valueOf(cashDenominationTable.getTotal_500()));
                            tvTotalAmt200.setText(String.valueOf(cashDenominationTable.getTotal_200()));
                            tvTotalAmt100.setText(String.valueOf(cashDenominationTable.getTotal_100()));
                            tvTotalAmt50.setText(String.valueOf(cashDenominationTable.getTotal_50()));
                            tvTotalAmt20.setText(String.valueOf(cashDenominationTable.getTotal_20()));
                            tvTotalAmt10.setText(String.valueOf(cashDenominationTable.getTotal_10()));
                            tvTotalAmt5.setText(String.valueOf(cashDenominationTable.getTotal_5()));
                            tvTotalAmt2.setText(String.valueOf(cashDenominationTable.getTotal_2()));
                            tvTotalAmt1.setText(String.valueOf(cashDenominationTable.getTotal_1()));

                            tvTotalCount.setText(String.valueOf(cashDenominationTable.getTotal_count()));
                            tvOverAllTotalAmt.setText(String.valueOf(cashDenominationTable.getTotal_amount()));

                            String strTotalCashCollection= tvTotalCashNeedsToCollect.getText().toString();
                            int totalCashNeedsToCollect=0;
                            if( ! TextUtils.isEmpty(strTotalCashCollection)){
                                totalCashNeedsToCollect=Integer.parseInt(strTotalCashCollection);
                            }
                            if(cashDenominationTable.getTotal_amount() == totalCashNeedsToCollect){
                                tvOverAllTotalAmt.setTextColor(getResources().getColor(R.color.light_green));
                            }
                            else{
                                tvOverAllTotalAmt.setTextColor(getResources().getColor(R.color.rbl_red));
                            }
                        }
                        else{
                            cashDenominationTable=new CashDenominationTable();
                            cashDenominationTable.setStaffId(userId);
                            cashDenominationTable.setCMDate(cmDate);

                            String timeStamp = new SimpleDateFormat("yyMMddHHmmssSSS",
                                    Locale.getDefault()).format(new Date());
                            String EMP_LAST_5_DIGIT = userId.substring(3);
                            String REF_ID = EMP_LAST_5_DIGIT + timeStamp;
                            cashDenominationTable.setRefId(REF_ID);
                        }

                        // TODO: Initiating Listener
                        etCount2000.addTextChangedListener(DenominationFragment.this);
                        etCount1000.addTextChangedListener(DenominationFragment.this);
                        etCount500.addTextChangedListener(DenominationFragment.this);
                        etCount200.addTextChangedListener(DenominationFragment.this);
                        etCount100.addTextChangedListener(DenominationFragment.this);
                        etCount50.addTextChangedListener(DenominationFragment.this);
                        etCount20.addTextChangedListener(DenominationFragment.this);
                        etCount10.addTextChangedListener(DenominationFragment.this);
                        etCount5.addTextChangedListener(DenominationFragment.this);
                        etCount2.addTextChangedListener(DenominationFragment.this);
                        etCount1.addTextChangedListener(DenominationFragment.this);

                    }
                };
                viewModel.getCashDenominationTableLiveData().observe(this, observer);
            }
        } catch (Exception ex) {
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            ex.printStackTrace();
        }
    }

    public interface DenominationInterface {
        void successCallBack();
    }
}
