package com.saartak.el.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.bfil.uilibrary.helpers.AppHelper;
import com.saartak.el.R;
import com.saartak.el.activities.BaseActivity;
import com.saartak.el.activities.LOSBaseActivity;
import com.saartak.el.activities.ReminderActivity;
import com.saartak.el.database.entity.ColdCallTable;

import java.util.List;

import static com.saartak.el.constants.AppConstant.MODULE_TYPE_COLD_CALLING;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_ID;
import static com.saartak.el.constants.AppConstant.PARAM_CLIENT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_NAME;
import static com.saartak.el.constants.AppConstant.REQUEST_CODE_UPDATE_LEAD;

public class ReminderColdCallingAdapter extends RecyclerView.Adapter<ReminderColdCallingAdapter.LoanTypeViewHolder> {
    private Context context;
    List<ColdCallTable> rawDataTableList;
    List<ColdCallTable> rawDataTableListFiltered;
    SyncCallbackInterface syncCallbackInterface;
    AppHelper appHelper;

    public ReminderColdCallingAdapter(Context context, List<ColdCallTable> rawDataTableList,
                                      SyncCallbackInterface syncCallbackInterface, AppHelper appHelper) {
        this.context = context;
        this.rawDataTableList = rawDataTableList;
        this.rawDataTableListFiltered = rawDataTableList;
        this.syncCallbackInterface = syncCallbackInterface;
        this.appHelper = appHelper;
    }

    @NonNull
    @Override
    public LoanTypeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.reminder_row_item, viewGroup, false);
        return new LoanTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoanTypeViewHolder loanTypeViewHolder, int i) {
        try {
            if (rawDataTableListFiltered != null && rawDataTableListFiltered.size() > 0) {
                loanTypeViewHolder.tvName.setText(rawDataTableListFiltered.get(i).getClientName());
                loanTypeViewHolder.tvPhone.setText(rawDataTableListFiltered.get(i).getMobileNo());
                loanTypeViewHolder.tvLoanType.setText(rawDataTableListFiltered.get(i).getLoan_type());

                ColdCallTable rawDataTable = rawDataTableListFiltered.get(i);
                if (rawDataTable != null && rawDataTable.isSync()) {
                    loanTypeViewHolder.ivStatus.setImageResource(R.drawable.reminder_small);
                    loanTypeViewHolder.ivStatus.setEnabled(false);
                    loanTypeViewHolder.llLeadDetails.setEnabled(false);

                } else {
                    loanTypeViewHolder.ivStatus.setImageResource(R.drawable.reminder_small);
                    loanTypeViewHolder.ivStatus.setEnabled(false);
                    loanTypeViewHolder.llLeadDetails.setEnabled(false);
                }

                loanTypeViewHolder.llLeadDetails.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (rawDataTable != null && !TextUtils.isEmpty(rawDataTable.getClientId())) {
                            LOSBaseActivity.CLIENT_ID = rawDataTable.getClientId();
                        }

                        Intent intentApplication = new Intent(context, BaseActivity.class);
                        intentApplication.putExtra(PARAM_LOAN_TYPE, rawDataTable.getLoan_type());
                        intentApplication.putExtra(PARAM_USER_NAME, rawDataTableListFiltered.get(i).getMarketName());
                        intentApplication.putExtra(PARAM_BRANCH_ID, rawDataTable.getCreatedBy());
//                        intentApplication.putExtra(PARAM_AUTO_FILL, true);
//                        intentApplication.putExtra(PARAM_HASH_MAP, rawDataTableListFiltered.get(i));
                        intentApplication.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_COLD_CALLING);
                        intentApplication.putExtra(PARAM_CLIENT_ID, LOSBaseActivity.CLIENT_ID);
                        intentApplication.putExtra(PARAM_USER_ID, rawDataTable.getCreatedBy());
//                        context.startActivity(intentApplication);
                        ReminderActivity leadDetailsActivity = (ReminderActivity) context;
                        leadDetailsActivity.startActivityForResult(intentApplication, REQUEST_CODE_UPDATE_LEAD);
                   /* } else{
                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,"Data already synced ");
                    }*/
                    }
                });

                loanTypeViewHolder.ivStatus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO: No need to check this condition
                        if (appHelper.isNetworkAvailable()) {
                            //appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons("Would You Like To Sync This Lead ?",
                            appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons("Would You Like To Proceed/Convert To Application ?",
                                    new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {
                                            if (rawDataTable != null && !rawDataTable.isSync()) {
                                                syncCallbackInterface.syncCallback(i, rawDataTable);
                                            } else {
                                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Data already synced ");
                                            }
                                        }
                                    });

                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Internet Connection Required To Sync Data");
                        }
                    }
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (rawDataTableListFiltered != null) {

            return rawDataTableListFiltered.size();
        } else {
            return 0;
        }
    }



    public class LoanTypeViewHolder extends RecyclerView.ViewHolder {
        //         ImageView ivLoanIcon;
        TextView tvName, tvPhone, tvLoanType;
        ImageView ivStatus;
        LinearLayout cvLead;
        LinearLayout llLeadDetails;
        private TextView textViewSeparator;

        public LoanTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_cold_calling_name_value);
            tvPhone = (TextView) itemView.findViewById(R.id.tv_cold_calling_phone_value);
            tvLoanType = (TextView) itemView.findViewById(R.id.tv_cold_calling_type);
            ivStatus = (ImageView) itemView.findViewById(R.id.iv_cold_calling_sync_status);
            llLeadDetails = (LinearLayout) itemView.findViewById(R.id.ll_cold_calling_summary_values);
            cvLead = itemView.findViewById(R.id.cv_cold_calling);
            textViewSeparator = itemView.findViewById(R.id.textView_separator);

        }
    }


    public interface SyncCallbackInterface {
        void syncCallback(int position, ColdCallTable rawDataTable);
    }
}