package com.swadhaar.los.adapter;

import android.content.Context;
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
import com.swadhaar.los.R;
import com.swadhaar.los.database.entity.LoanTable;

import java.util.ArrayList;
import java.util.List;

import static com.swadhaar.los.dynamicui.constants.ParametersConstant.FINAL_STATUS_PENDING;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.FINAL_STATUS_SUBMITTED;

public class LoanApplicationSummaryAdapter extends RecyclerView.Adapter<LoanApplicationSummaryAdapter.LoanTypeViewHolder> implements Filterable {

    private Context context;
    List<LoanTable> loanTableList;
    List<LoanTable> loanTableListFiltered;
    SyncCallbackInterface syncCallbackInterface;
    AppHelper appHelper;

    public LoanApplicationSummaryAdapter(Context context, List<LoanTable> loanTableList,
                                         SyncCallbackInterface syncCallbackInterface, AppHelper appHelper) {
        this.context = context;
        this.loanTableList = loanTableList;
        this.loanTableListFiltered = loanTableList;
        this.syncCallbackInterface = syncCallbackInterface;
        this.appHelper = appHelper;
    }

    @NonNull
    @Override
    public LoanTypeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.loan_application_summary_row_item, viewGroup, false);
        return new LoanTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoanTypeViewHolder loanTypeViewHolder, int i) {
        try {
            if (loanTableListFiltered != null && loanTableListFiltered.size() > 0) {
                LoanTable loanTable = loanTableListFiltered.get(i);
                loanTypeViewHolder.tvName.setText(loanTable.getClientName());
                loanTypeViewHolder.tvloantype.setText(loanTable.getLoanProductName());
                loanTypeViewHolder.tvamount.setText(loanTable.getLoan_amount());
                loanTypeViewHolder.tvclientid.setText(loanTable.getClientId());
                loanTypeViewHolder.tvLoanProdCode.setText(loanTable.getLoanProductCode());
                loanTypeViewHolder.tvLoanId.setText(loanTable.getLoanId());

                loanTypeViewHolder.llLeadDetails.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        syncCallbackInterface.openScreenCallback(i, loanTable);
                    }
                });

                boolean allDataSynced = loanTable.isSync();
                if (allDataSynced) {
                    loanTypeViewHolder.ivStatus.setImageResource(R.mipmap.check);
                  //  loanTypeViewHolder.ivStatus.setBackgroundColor(context.getResources().getColor(R.color.white));
//                    loanTypeViewHolder.llLeadDetails.setEnabled(false); // TODO: Not for JLG ( we need to allow to view )
                } else {
                    loanTypeViewHolder.ivStatus.setImageResource(R.mipmap.sync_pd);
                    loanTypeViewHolder.llLeadDetails.setEnabled(true);
                }
                boolean allDataCaptured = loanTable.isAllDataCaptured();
                if (allDataCaptured) {
                    loanTypeViewHolder.ivStatus.setVisibility(View.VISIBLE);
                } else {
                    loanTypeViewHolder.ivStatus.setVisibility(View.VISIBLE);
                }

                if (!TextUtils.isEmpty(loanTable.getFinalStatus())
                        && !loanTable.getFinalStatus().equalsIgnoreCase(FINAL_STATUS_PENDING)) {
                    loanTypeViewHolder.btnSubmit.setVisibility(View.GONE);
                    loanTypeViewHolder.btnReject.setVisibility(View.GONE);
                    loanTypeViewHolder.btnFinalStatus.setVisibility(View.VISIBLE);
                    loanTypeViewHolder.btnFinalStatus.setText(loanTable.getFinalStatus());
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (loanTable.getFinalStatus().equalsIgnoreCase(FINAL_STATUS_SUBMITTED)) {
                            //   loanTypeViewHolder.btnFinalStatus.setBackgroundTintList(context.getColorStateList(R.color.colorGreenLight));
                        } else {
                            loanTypeViewHolder.btnFinalStatus.setBackgroundTintList(context.getColorStateList(R.color.colorRedLight));
                        }
                    }
//                                    getLeadRawDataFromLocalDB(SCREEN_NO);
                } else {
                    loanTypeViewHolder.btnSubmit.setVisibility(View.VISIBLE);
//                    loanTypeViewHolder.btnReject.setVisibility(View.VISIBLE);
                    loanTypeViewHolder.btnFinalStatus.setVisibility(View.GONE);
                }

                loanTypeViewHolder.llLeadDetails.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        syncCallbackInterface.openScreenCallback(i, loanTable);
                    }
                });

               /* loanTypeViewHolder.ivStatus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO: No need to check this condition
                        if (loanTable != null && !loanTable.getFinalStatus().equalsIgnoreCase(FINAL_STATUS_PENDING)) {
                            if (appHelper.isNetworkAvailable()) {

                                appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons("Would You Like To Sync This Application ?",
                                        new ConfirmationDialog.ActionCallback() {
                                            @Override
                                            public void onAction() {
                                                if (!loanTable.isSync()) {
                                                    syncCallbackInterface.syncCallback(i, loanTable);
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
                });*/
                loanTypeViewHolder.btnSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons("Would You Like To Submit This Application",
                                new ConfirmationDialog.ActionCallback() {
                                    @Override
                                    public void onAction() {
                                        syncCallbackInterface.submitCallback(i, loanTable);
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
                                            loanTable.setRemarks(comments);
                                            syncCallbackInterface.rejectCallback(i, loanTable);
                                        }
                                    });
//                                                    syncCallbackInterface.rejectCallback(i, loanTable,ACTION_NAME_REJECT);
                                }
                            });



                        appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons("Would You Like To Reject This Application",
                                new ConfirmationDialog.ActionCallback() {
                                    @Override
                                    public void onAction() {
                                        syncCallbackInterface.rejectCallback(i, loanTable);
                                    }
                                });
                    }
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (loanTableListFiltered != null) {

            return loanTableListFiltered.size();
        } else {
            return 0;
        }
    }

    public void setItem(List<LoanTable> loanTableList) {
        try {
            if (this.loanTableList != null && this.loanTableList.size() > 0) {
                this.loanTableList.clear();
                this.loanTableListFiltered.clear();
                this.loanTableList = loanTableList;
                this.loanTableListFiltered = loanTableList;
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void clearItems() {
        try {
            if (loanTableList != null && loanTableList.size() > 0) {
                loanTableList.clear();
                loanTableListFiltered.clear();
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public class LoanTypeViewHolder extends RecyclerView.ViewHolder {
        //         ImageView ivLoanIcon;
        TextView tvName, tvPhone, tvloantype, tvamount, tvclientid,tvLoanProdCode,tvLoanId;
        ImageView ivStatus;
        CardView cvLead;
        LinearLayout llLeadDetails, llApproveOrReject;
        public Button btnSubmit, btnReject, btnFinalStatus;

        public LoanTypeViewHolder(@NonNull View itemView) {
            super(itemView);
//            ivLoanIcon=(ImageView)itemView.findViewById(R.id.iv_loan_icon);
            tvName = (TextView) itemView.findViewById(R.id.tv_lead_name_value);
            //tvPhone = (TextView) itemView.findViewById(R.id.tv_lead_phone_value);
            tvloantype = (TextView) itemView.findViewById(R.id.tv_loan_prod_name_value);
            tvLoanProdCode = (TextView) itemView.findViewById(R.id.tv_loan_prod_code_value);
            tvLoanId = (TextView) itemView.findViewById(R.id.tv_loan_id_value);
            tvamount = (TextView) itemView.findViewById(R.id.tv_lead_amount_value);
            tvclientid = (TextView) itemView.findViewById(R.id.tv_lead_clientid_value);
            ivStatus = (ImageView) itemView.findViewById(R.id.iv_lead_sync_status);
            llLeadDetails = (LinearLayout) itemView.findViewById(R.id.ll_lead_summary_values);
            llApproveOrReject = (LinearLayout) itemView.findViewById(R.id.ll_approve_or_reject);
            cvLead = (CardView) itemView.findViewById(R.id.cv_lead);
            btnSubmit = (Button) itemView.findViewById(R.id.btn_submit);
            btnReject = (Button) itemView.findViewById(R.id.btn_reject);
            btnFinalStatus = (Button) itemView.findViewById(R.id.btn_final_status);
        }
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    loanTableListFiltered = loanTableList;
                }
                // TODO: ALL STATIC DATA ... NEED TO FIX THIS ISSUE
                else if (charString.equalsIgnoreCase("Interested")) {
                    loanTableListFiltered = loanTableList;
                } else if (charString.equalsIgnoreCase("Not Interested")) {
                    loanTableListFiltered = loanTableList;
                } else if (charString.equalsIgnoreCase("Affordable Housing Loan")) {
                    ArrayList<LoanTable> filteredList = new ArrayList<>();
                    for (LoanTable loanTable : loanTableList) {
                        if (loanTable.getLoan_type().equalsIgnoreCase("Affordable Housing Loan")) {
                            filteredList.add(loanTable);
                        }
                    }
                    loanTableListFiltered = filteredList;
                } else if (charString.equalsIgnoreCase("Consumer Durable Loan")) {
                    ArrayList<LoanTable> filteredList = new ArrayList<>();
                    for (LoanTable loanTable : loanTableList) {
                        if (loanTable.getLoan_type().equalsIgnoreCase("Consumer Durable Loan")) {
                            filteredList.add(loanTable);
                        }
                    }
                    loanTableListFiltered = filteredList;
                } else if (charString.equalsIgnoreCase("Individual Loan")) {
                    ArrayList<LoanTable> filteredList = new ArrayList<>();
                    for (LoanTable loanTable : loanTableList) {
                        if (loanTable.getLoan_type().equalsIgnoreCase("Individual Loan")) {
                            filteredList.add(loanTable);
                        }
                    }
                    loanTableListFiltered = filteredList;
                } else if (charString.equalsIgnoreCase("JLG")) {
                    ArrayList<LoanTable> filteredList = new ArrayList<>();
                    for (LoanTable loanTable : loanTableList) {
                        if (loanTable.getLoan_type().equalsIgnoreCase("JLG")) {
                            filteredList.add(loanTable);
                        }
                    }
                    loanTableListFiltered = filteredList;
                } else if (charString.equalsIgnoreCase("Loan against property")) {
                    ArrayList<LoanTable> filteredList = new ArrayList<>();
                    for (LoanTable loanTable : loanTableList) {
                        if (loanTable.getLoan_type().equalsIgnoreCase("Loan against property")) {
                            filteredList.add(loanTable);
                        }
                    }
                    loanTableListFiltered = filteredList;
                } else if (charString.equalsIgnoreCase("Two Wheeler")) {
                    ArrayList<LoanTable> filteredList = new ArrayList<>();
                    for (LoanTable loanTable : loanTableList) {
                        if (loanTable.getLoan_type().equalsIgnoreCase("Two Wheeler")) {
                            filteredList.add(loanTable);
                        }
                    }
                    loanTableListFiltered = filteredList;
                } else if (charString.equalsIgnoreCase("MSME/Business loan")) {
                    ArrayList<LoanTable> filteredList = new ArrayList<>();
                    for (LoanTable loanTable : loanTableList) {
                        if (loanTable.getLoan_type().equalsIgnoreCase("MSME/Business loan")) {
                            filteredList.add(loanTable);
                        }
                    }
                    loanTableListFiltered = filteredList;
                } else if (charString.equalsIgnoreCase("All")) {
                    loanTableListFiltered = loanTableList;
                } else if (charString.equalsIgnoreCase("ASC")) {
                    loanTableListFiltered = loanTableList;
                } else if (charString.equalsIgnoreCase("DESC")) {
                    ArrayList<LoanTable> filteredList = new ArrayList<>();
                    for (int i = loanTableList.size(); i >= 1; i--) {
                        filteredList.add(loanTableListFiltered.get(i - 1));
                    }
                    loanTableListFiltered = filteredList;

                } else {
                    ArrayList<LoanTable> filteredList = new ArrayList<>();
                    for (LoanTable loanTable : loanTableList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if ((!TextUtils.isEmpty(loanTable.getPhoneNo())
                                && loanTable.getPhoneNo().toLowerCase().contains(charString.toLowerCase()))
                                || (loanTable.getClientName().toLowerCase().contains(charString.toLowerCase()))){
                            filteredList.add(loanTable);
                        }
                    }

                    loanTableListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = loanTableListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                loanTableListFiltered = (List<LoanTable>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public interface SyncCallbackInterface {
        void openScreenCallback(int position, LoanTable loanTable);
        void syncCallback(int position, LoanTable loanTable);

        void submitCallback(int position, LoanTable loanTable);

        void rejectCallback(int position, LoanTable loanTable);
    }

}
