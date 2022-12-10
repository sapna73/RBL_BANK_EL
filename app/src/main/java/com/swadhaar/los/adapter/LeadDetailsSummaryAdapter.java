package com.swadhaar.los.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
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
import com.swadhaar.los.R;
import com.swadhaar.los.activities.BaseActivity;
import com.swadhaar.los.activities.LOSBaseActivity;
import com.swadhaar.los.activities.LeadDetailsSummaryActivity;
import com.swadhaar.los.database.entity.LeadTable;
import com.swadhaar.los.database.entity.MasterTable;

import java.util.ArrayList;
import java.util.List;

import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_COLD_CALLING;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_LEAD;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_CLIENT_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_NAME;
import static com.swadhaar.los.constants.AppConstant.REQUEST_CODE_UPDATE_LEAD;

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
                    if (rawDataTableListFiltered.get(i).getInterestedInLoan()==1) {
                        loanTypeViewHolder.llLeadDetails.setEnabled(false);
                    }else {
                        loanTypeViewHolder.llLeadDetails.setEnabled(true);
                    }

                } else {
                    loanTypeViewHolder.ivStatus.setImageResource(R.drawable.ic_sync_black_24dp);
                    loanTypeViewHolder.llLeadDetails.setEnabled(true);

                }

                if (rawDataTableListFiltered.get(i).isIsDataCaptured()) {
                    loanTypeViewHolder.ivStatus.setVisibility(View.VISIBLE);
                }else {
                    loanTypeViewHolder.ivStatus.setVisibility(View.INVISIBLE);
                }

                // TODO: 05-03-2021  Added recently for premium cold call
                try {
                    if (rawDataTableListFiltered.get(i).getIsPremium() == 1) {
//                        loanTypeViewHolder.ivPremium.setVisibility(View.VISIBLE);
                        String mobileNo = rawDataTableListFiltered.get(i).getMobileNo();
                        if (!TextUtils.isEmpty(mobileNo) && mobileNo.length() == 10) {
                            loanTypeViewHolder.tvPhone.setText("**********");
                        }
                    } else {
//                        loanTypeViewHolder.ivPremium.setVisibility(View.GONE);
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }


                if (rawDataTableListFiltered.get(i).getInterestedInLoan()==1) {
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

                        syncCallbackInterface.openScreenCallback(i,leadTable);
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
//                else if (charString.equalsIgnoreCase("Affordable Housing Loan")) {
//                    ArrayList<HashMap<String, Object>> filteredList = new ArrayList<>();
//                    for (HashMap<String, Object> row : leadDetailsDTOList) {
//                        if (row.containsKey(TAG_NAME_TYPE_OF_LOAN)) {
//                            if (row.get(TAG_NAME_TYPE_OF_LOAN).toString().toLowerCase().equalsIgnoreCase("Affordable Housing Loan")) {
//                                filteredList.add(row);
//                            }
//                        }
//                    }
//                    rawDataTableListFiltered = filteredList;
//                }
//                else if (charString.equalsIgnoreCase("Consumer Durable Loan")) {
//                    ArrayList<HashMap<String, Object>> filteredList = new ArrayList<>();
//                    for (HashMap<String, Object> row : leadDetailsDTOList) {
//                        if (row.containsKey(TAG_NAME_TYPE_OF_LOAN)) {
//                            if (row.get(TAG_NAME_TYPE_OF_LOAN).toString().toLowerCase().equalsIgnoreCase("Consumer Durable Loan")) {
//                                filteredList.add(row);
//                            }
//                        }
//                    }
//                    rawDataTableListFiltered = filteredList;
//                } else if (charString.equalsIgnoreCase("Individual Loan")) {
//                    ArrayList<HashMap<String, Object>> filteredList = new ArrayList<>();
//                    for (HashMap<String, Object> row : leadDetailsDTOList) {
//                        if (row.containsKey(TAG_NAME_TYPE_OF_LOAN)) {
//                            if (row.get(TAG_NAME_TYPE_OF_LOAN).toString().toLowerCase().equalsIgnoreCase("Individual Loan")) {
//                                filteredList.add(row);
//                            }
//                        }
//                    }
//                    rawDataTableListFiltered = filteredList;
//                } else if (charString.equalsIgnoreCase("JLG")) {
//                    ArrayList<HashMap<String, Object>> filteredList = new ArrayList<>();
//                    for (HashMap<String, Object> row : leadDetailsDTOList) {
//                        if (row.containsKey(TAG_NAME_TYPE_OF_LOAN)) {
//                            if (row.get(TAG_NAME_TYPE_OF_LOAN).toString().toLowerCase().equalsIgnoreCase("JLG")) {
//                                filteredList.add(row);
//                            }
//                        }
//                    }
//                    rawDataTableListFiltered = filteredList;
//                } else if (charString.equalsIgnoreCase("Loan against property")) {
//                    ArrayList<HashMap<String, Object>> filteredList = new ArrayList<>();
//                    for (HashMap<String, Object> row : leadDetailsDTOList) {
//                        if (row.containsKey(TAG_NAME_TYPE_OF_LOAN)) {
//                            if (row.get(TAG_NAME_TYPE_OF_LOAN).toString().toLowerCase().equalsIgnoreCase("Loan against property")) {
//                                filteredList.add(row);
//                            }
//                        }
//                    }
//                    rawDataTableListFiltered = filteredList;
//                } else if (charString.equalsIgnoreCase("Two Wheeler")) {
//                    ArrayList<HashMap<String, Object>> filteredList = new ArrayList<>();
//                    for (HashMap<String, Object> row : leadDetailsDTOList) {
//                        if (row.containsKey(TAG_NAME_TYPE_OF_LOAN)) {
//                            if (row.get(TAG_NAME_TYPE_OF_LOAN).toString().toLowerCase().equalsIgnoreCase("Two Wheeler")) {
//                                filteredList.add(row);
//                            }
//                        }
//                    }
//                    rawDataTableListFiltered = filteredList;
//                } else if (charString.equalsIgnoreCase("MSME/Business loan")) {
//                    ArrayList<HashMap<String, Object>> filteredList = new ArrayList<>();
//                    for (HashMap<String, Object> row : leadDetailsDTOList) {
//                        if (row.containsKey(TAG_NAME_TYPE_OF_LOAN)) {
//                            if (row.get(TAG_NAME_TYPE_OF_LOAN).toString().toLowerCase().equalsIgnoreCase("MSME/Business loan")) {
//                                filteredList.add(row);
//                            }
//                        }
//                    }
//                    rawDataTableListFiltered = filteredList;
//                }
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
                                || row.getClientName().toLowerCase().contains(charString.toLowerCase())) {
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
