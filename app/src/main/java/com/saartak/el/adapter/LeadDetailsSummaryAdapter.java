package com.saartak.el.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.bfil.uilibrary.helpers.AppHelper;
import com.saartak.el.R;
import com.saartak.el.database.entity.LeadTable;

import java.util.ArrayList;
import java.util.List;
public class LeadDetailsSummaryAdapter extends RecyclerView.Adapter<LeadDetailsSummaryAdapter.LoanTypeViewHolder> implements Filterable {
    private Context context;
    List<LeadTable> rawDataTableList;
    List<LeadTable> rawDataTableListFiltered;
    LeadDetailsSummaryAdapter.SyncCallbackInterface syncCallbackInterface;
    AppHelper appHelper;
    public LeadDetailsSummaryAdapter(Context context, List<LeadTable> rawDataTableList,
                                     LeadDetailsSummaryAdapter.SyncCallbackInterface syncCallbackInterface, AppHelper appHelper) {
        this.context = context;
        this.rawDataTableList = rawDataTableList;
        this.rawDataTableListFiltered = rawDataTableList;
        this.syncCallbackInterface = syncCallbackInterface;
        this.appHelper = appHelper;
    }

    @NonNull
    @Override
    public LeadDetailsSummaryAdapter.LoanTypeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lead_summary_row_item, viewGroup, false);
        return new LeadDetailsSummaryAdapter.LoanTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeadDetailsSummaryAdapter.LoanTypeViewHolder loanTypeViewHolder, int i) {
        try {
            if (rawDataTableListFiltered != null && rawDataTableListFiltered.size() > 0) {

                loanTypeViewHolder.tvName.setText(rawDataTableListFiltered.get(i).getClientName());
                loanTypeViewHolder.tvPhone.setText(rawDataTableListFiltered.get(i).getMobileNo());
                loanTypeViewHolder.tvLoanType.setText(rawDataTableListFiltered.get(i).getLoan_type());

                LeadTable leadTable = rawDataTableListFiltered.get(i);
                if (leadTable != null && leadTable.isSync()) {
                    loanTypeViewHolder.ivStatus.setImageResource(R.drawable.ic_check_circle_black_24dp);

                } else {
                    loanTypeViewHolder.ivStatus.setImageResource(R.drawable.ic_sync_black_24dp);
                    loanTypeViewHolder.llLeadDetails.setEnabled(true);
                }

                if (rawDataTableListFiltered.get(i).isIsDataCaptured()) {
                    loanTypeViewHolder.ivStatus.setVisibility(View.VISIBLE);
                    loanTypeViewHolder.cvLead.setBackgroundColor(context.getResources().getColor(R.color.lead_deatails_interested));
                    loanTypeViewHolder.textViewSeparator.setBackgroundColor(context.getResources().getColor(R.color.lead_deatails_interested_separatoe));

                            String leadStatus =rawDataTableListFiltered.get(i).getLeadStatus();
                            if (!TextUtils.isEmpty(leadStatus) && leadStatus.equalsIgnoreCase("Hot")) {
                                loanTypeViewHolder.cvLead.setBackgroundColor(context.getResources().getColor(R.color.lead_deatails_hot));
                                loanTypeViewHolder.textViewSeparator.setBackgroundColor(context.getResources().getColor(R.color.lead_deatails_hot_separatoe));
                            } else {
                                loanTypeViewHolder.cvLead.setBackgroundColor(context.getResources().getColor(R.color.lead_deatails_interested));
                                loanTypeViewHolder.textViewSeparator.setBackgroundColor(context.getResources().getColor(R.color.lead_deatails_interested_separatoe));
                            }

                } else {
                    loanTypeViewHolder.cvLead.setBackgroundColor(context.getResources().getColor(R.color.lead_deatails_not_interested));
                    loanTypeViewHolder.textViewSeparator.setBackgroundColor(context.getResources().getColor(R.color.lead_deatails_not_interested_separatoe));
                }

                loanTypeViewHolder.llLeadDetails.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (leadTable != null && !leadTable.isSync()) {
                            syncCallbackInterface.openScreenCallback(i, leadTable);
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Data already synced ");
                        }
                    }
                });

                loanTypeViewHolder.ivStatus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO: No need to check this condition
                        if (appHelper.isNetworkAvailable()) {
                            appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons("Would You Like To Proceed/Convert To Application ?",
                                    new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {
                                            if (leadTable != null && !leadTable.isSync()) {
                                                syncCallbackInterface.syncCallback(i, leadTable);
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
                loanTypeViewHolder.ivCall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO: No need to check this condition
                        if (rawDataTableListFiltered != null && rawDataTableListFiltered.size() > 0) {
                            String phoneNo = rawDataTableListFiltered.get(i).getMobileNo();
                            appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons("Would you like to call to this number (" + phoneNo + ")",
                                    new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {
                                            syncCallbackInterface.callBackForPhoneCall(i, phoneNo);
                                        }
                                    });

                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Unable to call");
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
        ImageView ivStatus, ivCall;
        LinearLayout cvLead;
        LinearLayout llLeadDetails;
        private TextView textViewSeparator;

        public LoanTypeViewHolder(@NonNull View itemView) {
            super(itemView);
//            ivLoanIcon=(ImageView)itemView.findViewById(R.id.iv_loan_icon);
            tvName = (TextView) itemView.findViewById(R.id.tv_cold_calling_name_value);
            tvPhone = (TextView) itemView.findViewById(R.id.tv_cold_calling_phone_value);
            tvLoanType = (TextView) itemView.findViewById(R.id.tv_cold_calling_type);
            ivStatus = (ImageView) itemView.findViewById(R.id.iv_cold_calling_sync_status);
            ivCall = (ImageView) itemView.findViewById(R.id.iv_cold_calling_call);
            llLeadDetails = (LinearLayout) itemView.findViewById(R.id.ll_cold_calling_summary_values);
            cvLead = itemView.findViewById(R.id.cv_cold_calling);
            textViewSeparator = itemView.findViewById(R.id.textView_separator);

        }
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    rawDataTableListFiltered = rawDataTableList;
                }
                // TODO: ALL STATIC DATA ... NEED TO FIX THIS ISSUE
                else if (charString.equalsIgnoreCase("Interested")) {
                    List<LeadTable> filteredList = new ArrayList<>();
                    for (LeadTable row : rawDataTableList) {
                        if (row.getInterestedInLoan()==1) {
                            filteredList.add(row);
                        }
                    }
                    rawDataTableListFiltered = filteredList;
                } else if (charString.equalsIgnoreCase("Not Interested")) {
                    List<LeadTable> filteredList = new ArrayList<>();
                    for (LeadTable row : rawDataTableList) {
                        if (row.getInterestedInLoan()==0) {
                            filteredList.add(row);
                        }
                    }
                    rawDataTableListFiltered = filteredList;
                }
                else if (charString.equalsIgnoreCase("All")) {
                    rawDataTableListFiltered = rawDataTableList;
                } else if (charString.equalsIgnoreCase("ASC")) {
                    rawDataTableListFiltered = rawDataTableList;
                } else if (charString.equalsIgnoreCase("DESC")) {
                    List<LeadTable> filteredList = new ArrayList<>();
                    for (int i = rawDataTableList.size(); i >= 1; i--) {
                        filteredList.add(rawDataTableList.get(i - 1));
                    }
                    rawDataTableListFiltered = filteredList;

                } else {
//                    List<Contact> filteredList = new ArrayList<>();
                    List<LeadTable> filteredList = new ArrayList<>();
                    for (LeadTable row : rawDataTableList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getMobileNo().toLowerCase().contains(charString.toLowerCase())
                                || "TESTING TEST".toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    rawDataTableListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = rawDataTableListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                rawDataTableListFiltered = (List<LeadTable>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public void setItem(List<LeadTable> rawDataTableList) {
        try {
            if (rawDataTableList != null && rawDataTableList.size() > 0) {
                if(this.rawDataTableList!=null) {
                    this.rawDataTableList.clear();
                }
                if (this.rawDataTableListFiltered !=null) {
                    this.rawDataTableListFiltered.clear();
                }
                this.rawDataTableList = rawDataTableList;
                this.rawDataTableListFiltered = rawDataTableList;
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    public interface SyncCallbackInterface {
        void syncCallback(int position, LeadTable leadTable);

        void callBackForPhoneCall(int position, String phoneNo);

        void openScreenCallback(int position, LeadTable leadTable);
    }
}
