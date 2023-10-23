package com.saartak.el.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.bfil.uilibrary.helpers.AppHelper;
import com.saartak.el.R;
import com.saartak.el.activities.ClientDetailsActivity;
import com.saartak.el.activities.LeadToApplicationActivity;
import com.saartak.el.database.entity.MasterTable;

import java.util.ArrayList;
import java.util.List;

import static com.saartak.el.constants.AppConstant.LOAN_NAME_INDIVIDUAL;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_EL;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.saartak.el.constants.AppConstant.PARAM_CURRENT_STAGE;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_APPLICATION;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_ID;
import static com.saartak.el.constants.AppConstant.PARAM_CLIENT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_NAME;
import static com.saartak.el.dynamicui.constants.ParametersConstant.FINAL_STATUS_PENDING;
import static com.saartak.el.dynamicui.constants.ParametersConstant.FINAL_STATUS_SUBMITTED;

public class LeadToApplicationAdapter extends RecyclerView.Adapter<LeadToApplicationAdapter.LoanTypeViewHolder> implements Filterable {

    private Context context;
    List<MasterTable> masterTableList;
    List<MasterTable> masterTableListFiltered;
    SyncCallbackInterface syncCallbackInterface;
    AppHelper appHelper;

    public LeadToApplicationAdapter(Context context, List<MasterTable> masterTableList,
                                    SyncCallbackInterface syncCallbackInterface, AppHelper appHelper) {
        this.context = context;
        this.masterTableList = masterTableList;
        this.masterTableListFiltered = masterTableList;
        this.syncCallbackInterface = syncCallbackInterface;
        this.appHelper = appHelper;
    }

    @NonNull
    @Override
    public LoanTypeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lead_to_application_row_item, viewGroup, false);
        return new LoanTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoanTypeViewHolder loanTypeViewHolder, int i) {
        try {
            if (masterTableListFiltered != null && masterTableListFiltered.size() > 0) {
                MasterTable masterTable = masterTableListFiltered.get(i);
                loanTypeViewHolder.tvName.setText(masterTable.getClientName());
                loanTypeViewHolder.tvloantype.setText(masterTable.getLoan_type());
                loanTypeViewHolder.tvamount.setText(masterTable.getLoan_amount());
                loanTypeViewHolder.tvclientid.setText(masterTable.getClientId());
                loanTypeViewHolder.tvCurrentStage.setText(masterTable.getCurrentStage());
                loanTypeViewHolder.tv_remarks.setText(masterTable.getRemarks());
               // loanTypeViewHolder.tvPhone.setText(masterTable.getPhoneNo());
                boolean allDataSynced = masterTable.isSync();

                if(masterTable.getVkycStatus()!=null&&masterTable.getVkycStatus().equalsIgnoreCase("S")){
                    loanTypeViewHolder.tv_vkyc_ekyc.setText("VKYC");
                    if(masterTable.getVKYCStatusCoApp()!=null&&masterTable.getVKYCStatusCoApp().equalsIgnoreCase("S")){
                        loanTypeViewHolder.tv_VkycStageValue.setText("VKYC Is Initiated SuccessFully For Applicant And Co-Applicant");
                    }else {
                        loanTypeViewHolder.tv_VkycStageValue.setText("VKYC Is Initiated SuccessFully For Applicant");
                    }
                    loanTypeViewHolder.tv_VkycStageValue.setTextColor(context.getResources().getColor(R.color.light_green));
                }else if(masterTable.getVkycStatus()!=null&&masterTable.getVkycStatus().equalsIgnoreCase("E")){
                    loanTypeViewHolder.tv_vkyc_ekyc.setText("EKYC");
                    if(masterTable.getVKYCStatusCoApp()!=null&&masterTable.getVKYCStatusCoApp().equalsIgnoreCase("E")){
                        loanTypeViewHolder.tv_VkycStageValue.setText("EKYC Is Initiated SuccessFully For Applicant And Co-Applicant");
                    }else {
                        loanTypeViewHolder.tv_VkycStageValue.setText("EKYC Is Initiated SuccessFully For Applicant");
                    }
                    loanTypeViewHolder.tv_VkycStageValue.setTextColor(context.getResources().getColor(R.color.light_green));
                } else if(masterTable.getVKYCStatusCoApp()!=null&&masterTable.getVKYCStatusCoApp().equalsIgnoreCase("S")){
                    loanTypeViewHolder.tv_vkyc_ekyc.setText("VKYC");
                    loanTypeViewHolder.tv_VkycStageValue.setText("VKYC Is Initiated SuccessFully For Co-Applicant");
                    loanTypeViewHolder.tv_VkycStageValue.setTextColor(context.getResources().getColor(R.color.light_green));
                } else if(masterTable.getVKYCStatusCoApp()!=null&&masterTable.getVKYCStatusCoApp().equalsIgnoreCase("E")){
                    loanTypeViewHolder.tv_vkyc_ekyc.setText("EKYC");
                    loanTypeViewHolder.tv_VkycStageValue.setText("EKYC Is Initiated SuccessFully For Co-Applicant");
                    loanTypeViewHolder.tv_VkycStageValue.setTextColor(context.getResources().getColor(R.color.light_green));
                }else {
                    loanTypeViewHolder.tv_VkycStageValue.setText("EKYC/VKYC is pending to be initiated");
                    loanTypeViewHolder.tv_VkycStageValue.setTextColor(context.getResources().getColor(R.color.rbl_red));
                }
                if (allDataSynced) {
                    loanTypeViewHolder.ivStatus.setImageResource(R.mipmap.check);
                    if(masterTable.getCurrentStage().equalsIgnoreCase("Application")){
                        if(masterTable.getLoan_type().equalsIgnoreCase(LOAN_NAME_EL)){
                            loanTypeViewHolder.tvCurrentStage.setText("Document Upload");
                        }else {
                            loanTypeViewHolder.tvCurrentStage.setText("PD");
                        }
                    }else {
                        loanTypeViewHolder.tvCurrentStage.setText(masterTable.getCurrentStage());
                    }
                    loanTypeViewHolder.tv_remarks.setText(masterTable.getRemarks());
                    //loanTypeViewHolder.tvCurrentStage.setText(masterTable.getCurrentStage());
                    //  loanTypeViewHolder.ivStatus.setBackgroundColor(context.getResources().getColor(R.color.white));
                    loanTypeViewHolder.llLeadDetails.setEnabled(false);
                } else {
                    loanTypeViewHolder.ivStatus.setImageResource(R.mipmap.sync_pd);
                    loanTypeViewHolder.llLeadDetails.setEnabled(true);
                }
                boolean allDataCaptured = masterTable.isAllDataCaptured();
                if (allDataCaptured) {
                    loanTypeViewHolder.ivStatus.setVisibility(View.VISIBLE);
                } else {
                    loanTypeViewHolder.ivStatus.setVisibility(View.VISIBLE);
                }

                if (!TextUtils.isEmpty(masterTable.getFinalStatus())
                        && !masterTable.getFinalStatus().equalsIgnoreCase(FINAL_STATUS_PENDING)) {
                    loanTypeViewHolder.btnSubmit.setVisibility(View.GONE);
                    loanTypeViewHolder.btnReject.setVisibility(View.GONE);
                    loanTypeViewHolder.btnFinalStatus.setVisibility(View.VISIBLE);
                    loanTypeViewHolder.btnFinalStatus.setText(masterTable.getFinalStatus());
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (masterTable.getFinalStatus().equalsIgnoreCase(FINAL_STATUS_SUBMITTED)) {
                            //   loanTypeViewHolder.btnFinalStatus.setBackgroundTintList(context.getColorStateList(R.color.colorGreenLight));
                        } else {
                            loanTypeViewHolder.btnFinalStatus.setBackgroundTintList(context.getColorStateList(R.color.colorRedLight));
                        }
                    }
                } else {
                    if (masterTable.getCurrentStage().equalsIgnoreCase("Application") || masterTable.getCurrentStage().equalsIgnoreCase("") || masterTable.getCurrentStage().equalsIgnoreCase("Document Execution")/*||masterTable.getCurrentStage().equalsIgnoreCase("PD")*/) {
                        loanTypeViewHolder.btnSubmit.setVisibility(View.VISIBLE);
                        loanTypeViewHolder.btnReject.setVisibility(View.VISIBLE);
                        loanTypeViewHolder.btnFinalStatus.setVisibility(View.GONE);
                    } else {
                        loanTypeViewHolder.btnSubmit.setVisibility(View.GONE);
                        loanTypeViewHolder.btnReject.setVisibility(View.GONE);
                        loanTypeViewHolder.btnFinalStatus.setVisibility(View.VISIBLE);
                        loanTypeViewHolder.btnFinalStatus.setText(FINAL_STATUS_SUBMITTED);

                        loanTypeViewHolder.ivStatus.setImageResource(R.mipmap.check);
                        loanTypeViewHolder.tvCurrentStage.setText(masterTable.getCurrentStage());
                        //  loanTypeViewHolder.ivStatus.setBackgroundColor(context.getResources().getColor(R.color.white));
                        loanTypeViewHolder.llLeadDetails.setEnabled(false);
                        loanTypeViewHolder.tv_remarks.setText(masterTable.getRemarks());
                    }
                }
                loanTypeViewHolder.llLeadDetails.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent application = new Intent(context, ClientDetailsActivity.class);
                        LeadToApplicationActivity leadToApplicationActivity = (LeadToApplicationActivity) context;
                        application.putExtra(PARAM_LOAN_TYPE, leadToApplicationActivity.loanType);
                        if (!TextUtils.isEmpty(masterTable.getLoan_type())
                                && masterTable.getLoan_type().equalsIgnoreCase(LOAN_NAME_INDIVIDUAL)) {
                            application.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_APPLICATION);
                        }
                        application.putExtra(PARAM_USER_NAME, leadToApplicationActivity.userName);
                        application.putExtra(PARAM_BRANCH_ID, leadToApplicationActivity.branchId);
                        application.putExtra(PARAM_BRANCH_GST_CODE, leadToApplicationActivity.branchGSTcode);
                        application.putExtra(PARAM_USER_ID, masterTable.getCreatedBy());
                        application.putExtra(PARAM_CLIENT_ID, masterTable.getClientId());
                        application.putExtra(PARAM_CURRENT_STAGE, masterTable.getCurrentStage());
                        application.putExtra(PARAM_PRODUCT_ID, leadToApplicationActivity.productId);
                        context.startActivity(application);

                    }
                });
                loanTypeViewHolder.ivStatus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO: No need to check this condition
                        if (masterTable != null && !masterTable.getFinalStatus().equalsIgnoreCase(FINAL_STATUS_PENDING)) {
                            if (appHelper.isNetworkAvailable()) {

                                appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons("Would You Like To Sync This Application ?",
                                        new ConfirmationDialog.ActionCallback() {
                                            @Override
                                            public void onAction() {
                                                if (!masterTable.isSync()) {
                                                    syncCallbackInterface.syncCallback(i, masterTable);
                                                } else {
                                                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Data already synced ");
                                                }
                                            }
                                        });
                            } else {
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Internet Connection Required To Sync Data");
                            }
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Please perform submit or reject to do sync");
                        }
                    }
                });
                loanTypeViewHolder.btnSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons("Would You Like To Submit This Application",
                                new ConfirmationDialog.ActionCallback() {
                                    @Override
                                    public void onAction() {
                                        syncCallbackInterface.submitCallback(i, masterTable);
                                    }
                                });
                    }
                });
                loanTypeViewHolder.btnReject.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {  appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons("Would You Like To Reject This Application",
                            new ConfirmationDialog.ActionCallback() {
                                @Override
                                public void onAction() {
                                    appHelper.getDialogHelper().getConfirmationDialog().showCommentBox(new ConfirmationDialog.CommentActionCallback() {
                                        @Override
                                        public void onAction(String comments) {
                                            masterTable.setRemarks(comments);
                                            syncCallbackInterface.rejectCallback(i, masterTable);
                                        }
                                    });
//                                                    syncCallbackInterface.rejectCallback(i, masterTable,ACTION_NAME_REJECT);
                                }
                            });



                      /*  appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons("Would You Like To Reject This Application",
                                new ConfirmationDialog.ActionCallback() {
                                    @Override
                                    public void onAction() {
                                        syncCallbackInterface.rejectCallback(i, masterTable);
                                    }
                                });*/
                    }
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (masterTableListFiltered != null) {
            return masterTableListFiltered.size();
        } else {
            return 0;
        }
    }

    public void setItem(List<MasterTable> masterTableList) {
        try {
            if (this.masterTableList != null && this.masterTableList.size() > 0) {
                this.masterTableList.clear();
                this.masterTableListFiltered.clear();
                this.masterTableList = masterTableList;
                this.masterTableListFiltered = masterTableList;
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void clearItems() {
        try {
            if (masterTableList != null && masterTableList.size() > 0) {
                masterTableList.clear();
                masterTableListFiltered.clear();
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public class LoanTypeViewHolder extends RecyclerView.ViewHolder {
        //         ImageView ivLoanIcon;
        TextView tvName, tvPhone, tvloantype, tvamount, tvclientid,tvCurrentStage,tv_VkycStageValue,tv_vkyc_ekyc,tv_remarks;
        ImageView ivStatus;
        CardView cvLead;
        LinearLayout llLeadDetails, llApproveOrReject,linear_vkyc;
        public Button btnSubmit, btnReject, btnFinalStatus;

        public LoanTypeViewHolder(@NonNull View itemView) {
            super(itemView);
//            ivLoanIcon=(ImageView)itemView.findViewById(R.id.iv_loan_icon);
            tvName = (TextView) itemView.findViewById(R.id.tv_lead_name_value);
            //tvPhone = (TextView) itemView.findViewById(R.id.tv_lead_phone_value);
            tvloantype = (TextView) itemView.findViewById(R.id.tv_lead_loantype_value);
            tvamount = (TextView) itemView.findViewById(R.id.tv_lead_amount_value);
            tvclientid = (TextView) itemView.findViewById(R.id.tv_lead_clientid_value);
            tvCurrentStage = (TextView) itemView.findViewById(R.id.tv_current_stage_value);
            ivStatus = (ImageView) itemView.findViewById(R.id.iv_lead_sync_status);
            llLeadDetails = (LinearLayout) itemView.findViewById(R.id.ll_lead_summary_values);
            llApproveOrReject = (LinearLayout) itemView.findViewById(R.id.ll_approve_or_reject);
            linear_vkyc = (LinearLayout) itemView.findViewById(R.id.linear_vkyc);
            cvLead = (CardView) itemView.findViewById(R.id.cv_lead);
            btnSubmit = (Button) itemView.findViewById(R.id.btn_submit);
            btnReject = (Button) itemView.findViewById(R.id.btn_reject);
            btnFinalStatus = (Button) itemView.findViewById(R.id.btn_final_status);
            tv_VkycStageValue = (TextView) itemView.findViewById(R.id.tv_current_vkyc_stage_value);
            tv_vkyc_ekyc = (TextView) itemView.findViewById(R.id.vkyc_ekyc);
            tv_remarks = (TextView) itemView.findViewById(R.id.tv_remarks);
        }
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    masterTableListFiltered = masterTableList;
                }
                // TODO: ALL STATIC DATA ... NEED TO FIX THIS ISSUE
                else if (charString.equalsIgnoreCase("Interested")) {
                    masterTableListFiltered = masterTableList;
                } else if (charString.equalsIgnoreCase("Not Interested")) {
                    masterTableListFiltered = masterTableList;
                } else if (charString.equalsIgnoreCase("Affordable Housing Loan")) {
                    ArrayList<MasterTable> filteredList = new ArrayList<>();
                    for (MasterTable masterTable : masterTableList) {
                        if (masterTable.getLoan_type().equalsIgnoreCase("Affordable Housing Loan")) {
                            filteredList.add(masterTable);
                        }
                    }
                    masterTableListFiltered = filteredList;
                } else if (charString.equalsIgnoreCase("Consumer Durable Loan")) {
                    ArrayList<MasterTable> filteredList = new ArrayList<>();
                    for (MasterTable masterTable : masterTableList) {
                        if (masterTable.getLoan_type().equalsIgnoreCase("Consumer Durable Loan")) {
                            filteredList.add(masterTable);
                        }
                    }
                    masterTableListFiltered = filteredList;
                } else if (charString.equalsIgnoreCase("Individual Loan")) {
                    ArrayList<MasterTable> filteredList = new ArrayList<>();
                    for (MasterTable masterTable : masterTableList) {
                        if (masterTable.getLoan_type().equalsIgnoreCase("Individual Loan")) {
                            filteredList.add(masterTable);
                        }
                    }
                    masterTableListFiltered = filteredList;
                } else if (charString.equalsIgnoreCase("JLG")) {
                    ArrayList<MasterTable> filteredList = new ArrayList<>();
                    for (MasterTable masterTable : masterTableList) {
                        if (masterTable.getLoan_type().equalsIgnoreCase("JLG")) {
                            filteredList.add(masterTable);
                        }
                    }
                    masterTableListFiltered = filteredList;
                } else if (charString.equalsIgnoreCase("Loan against property")) {
                    ArrayList<MasterTable> filteredList = new ArrayList<>();
                    for (MasterTable masterTable : masterTableList) {
                        if (masterTable.getLoan_type().equalsIgnoreCase("Loan against property")) {
                            filteredList.add(masterTable);
                        }
                    }
                    masterTableListFiltered = filteredList;
                } else if (charString.equalsIgnoreCase("Two Wheeler")) {
                    ArrayList<MasterTable> filteredList = new ArrayList<>();
                    for (MasterTable masterTable : masterTableList) {
                        if (masterTable.getLoan_type().equalsIgnoreCase("Two Wheeler")) {
                            filteredList.add(masterTable);
                        }
                    }
                    masterTableListFiltered = filteredList;
                } else if (charString.equalsIgnoreCase("MSME/Business loan")) {
                    ArrayList<MasterTable> filteredList = new ArrayList<>();
                    for (MasterTable masterTable : masterTableList) {
                        if (masterTable.getLoan_type().equalsIgnoreCase("MSME/Business loan")) {
                            filteredList.add(masterTable);
                        }
                    }
                    masterTableListFiltered = filteredList;
                } else if (charString.equalsIgnoreCase("All")) {
                    masterTableListFiltered = masterTableList;
                } else if (charString.equalsIgnoreCase("ASC")) {
                    masterTableListFiltered = masterTableList;
                } else if (charString.equalsIgnoreCase("DESC")) {
                    ArrayList<MasterTable> filteredList = new ArrayList<>();
                    for (int i = masterTableList.size(); i >= 1; i--) {
                        filteredList.add(masterTableListFiltered.get(i - 1));
                    }
                    masterTableListFiltered = filteredList;

                } else {
                    ArrayList<MasterTable> filteredList = new ArrayList<>();
                    for (MasterTable masterTable : masterTableList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match

                        if (masterTable !=null && ! TextUtils.isEmpty(masterTable.getClientName()) &&
                                masterTable.getClientName().toLowerCase().contains(charString.toLowerCase())) {

                            filteredList.add(masterTable);
                        }
                    }

                    masterTableListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = masterTableListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                masterTableListFiltered = (List<MasterTable>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public interface SyncCallbackInterface {
        void syncCallback(int position, MasterTable masterTable);

        void submitCallback(int position, MasterTable masterTable);

        void rejectCallback(int position, MasterTable masterTable);
    }

}
