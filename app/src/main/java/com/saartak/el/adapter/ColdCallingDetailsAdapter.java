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
import com.saartak.el.database.entity.ColdCallTable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.saartak.el.constants.AppConstant.LOAN_NAME_AHL;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_PHL;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_EL;
import static com.saartak.el.dynamicui.constants.ParametersConstant.RADIO_BUTTON_WHEN_ITEM_NOW;

public class ColdCallingDetailsAdapter extends RecyclerView.Adapter<ColdCallingDetailsAdapter.LoanTypeViewHolder> implements Filterable {
    private Context context;
    List<ColdCallTable> rawDataTableList;
    List<ColdCallTable> rawDataTableListFiltered;
    SyncCallbackInterface syncCallbackInterface;
    AppHelper appHelper;

    public ColdCallingDetailsAdapter(Context context, List<ColdCallTable> rawDataTableList,
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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cold_calling_row_item, viewGroup, false);
        return new LoanTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoanTypeViewHolder loanTypeViewHolder, int i) {
        try {
            if (rawDataTableListFiltered != null && rawDataTableListFiltered.size() > 0) {
                loanTypeViewHolder.tvName.setText(rawDataTableListFiltered.get(i).getClientName());
                loanTypeViewHolder.tvPhone.setText(rawDataTableListFiltered.get(i).getMobileNo());
                if(rawDataTableListFiltered.get(i).getIsPremium() == 1){
                    loanTypeViewHolder.ivPremium.setVisibility(View.VISIBLE);
                    String mobileNo=rawDataTableListFiltered.get(i).getMobileNo();
                    if( ! TextUtils.isEmpty(mobileNo) && mobileNo.length()==10) {
                        loanTypeViewHolder.tvPhone.setText("**********");
                    }
                }else{
                    loanTypeViewHolder.ivPremium.setVisibility(View.GONE);
                }
                loanTypeViewHolder.tvLoanType.setText(rawDataTableListFiltered.get(i).getLoan_type());

                ColdCallTable coldCallTable = rawDataTableListFiltered.get(i);
                if (coldCallTable != null && coldCallTable.isSync()) {
                    loanTypeViewHolder.ivStatus.setImageResource(R.drawable.ic_check_circle_black_24dp);
                    if (rawDataTableListFiltered.get(i).getInterestedInLoan()==1) {
                        if (rawDataTableListFiltered.get(i).getWhen().equalsIgnoreCase(RADIO_BUTTON_WHEN_ITEM_NOW)) {
                            loanTypeViewHolder.llLeadDetails.setEnabled(false);
                        }else {
                            loanTypeViewHolder.llLeadDetails.setEnabled(true);
                        }
                    }else {
                        loanTypeViewHolder.llLeadDetails.setEnabled(true);
                    }

                } else {
                    loanTypeViewHolder.ivStatus.setImageResource(R.drawable.ic_sync_black_24dp);
                    loanTypeViewHolder.llLeadDetails.setEnabled(true);
                }

                if (rawDataTableListFiltered.get(i).getInterestedInLoan()==1) {
                    loanTypeViewHolder.ivStatus.setVisibility(View.VISIBLE);
                    loanTypeViewHolder.cvLead.setBackgroundColor(context.getResources().getColor(R.color.lead_deatails_interested));
                    loanTypeViewHolder.textViewSeparator.setBackgroundColor(context.getResources().getColor(R.color.lead_deatails_interested_separatoe));

                } else {
                    loanTypeViewHolder.ivStatus.setVisibility(View.VISIBLE);
                    loanTypeViewHolder.cvLead.setBackgroundColor(context.getResources().getColor(R.color.lead_deatails_not_interested));
                    loanTypeViewHolder.textViewSeparator.setBackgroundColor(context.getResources().getColor(R.color.lead_deatails_not_interested_separatoe));
                }


                // TODO: New condition added for Premium cold call
                if(rawDataTableListFiltered.get(i).getLoan_type().equalsIgnoreCase(LOAN_NAME_AHL)
                        && rawDataTableListFiltered.get(i).getIsPremium() ==1){
                    if(rawDataTableListFiltered.get(i).isAllDataCaptured()){
                        loanTypeViewHolder.ivStatus.setVisibility(View.VISIBLE);
                    }else{
                        loanTypeViewHolder.ivStatus.setVisibility(View.GONE);
                    }
                }
                // TODO: New condition added for Premium cold call
                if(rawDataTableListFiltered.get(i).getLoan_type().equalsIgnoreCase(LOAN_NAME_PHL)
                        && rawDataTableListFiltered.get(i).getIsPremium() ==1){
                    if(rawDataTableListFiltered.get(i).isAllDataCaptured()){
                        loanTypeViewHolder.ivStatus.setVisibility(View.VISIBLE);
                    }else{
                        loanTypeViewHolder.ivStatus.setVisibility(View.GONE);
                    }
                }
                if(rawDataTableListFiltered.get(i).getLoan_type().equalsIgnoreCase(LOAN_NAME_EL)
                        && rawDataTableListFiltered.get(i).getIsPremium() ==1){
                    if(rawDataTableListFiltered.get(i).isAllDataCaptured()){
                        loanTypeViewHolder.ivStatus.setVisibility(View.VISIBLE);
                    }else{
                        loanTypeViewHolder.ivStatus.setVisibility(View.GONE);
                    }
                }else if( rawDataTableListFiltered.get(i).getIsPremium() ==1){
                    if(rawDataTableListFiltered.get(i).isAllDataCaptured()){
                        loanTypeViewHolder.ivStatus.setVisibility(View.VISIBLE);
                    }else{
                        loanTypeViewHolder.ivStatus.setVisibility(View.GONE);
                    }
                }

                loanTypeViewHolder.llLeadDetails.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        syncCallbackInterface.openScreenCallback(i,coldCallTable);
                    }
                });

                loanTypeViewHolder.ivStatus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO: No need to check this condition
                        if (appHelper.isNetworkAvailable()) {
                            appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons("Do You Want To Sync This Cold Call ?",
                                    new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {
                                            if (coldCallTable != null && !coldCallTable.isSync()) {
                                                syncCallbackInterface.syncCallback(i, coldCallTable);
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
                            String clientId = rawDataTableListFiltered.get(i).getClientId();
                            appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons("Do you want to call ?",
                                    new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {
                                            syncCallbackInterface.callBackForPhoneCall(i, phoneNo,clientId);
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
        TextView tvName, tvPhone, tvLoanType;
        ImageView ivStatus, ivCall,ivPremium;
        LinearLayout cvLead;
        LinearLayout llLeadDetails;
        private TextView textViewSeparator;

        public LoanTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_cold_calling_name_value);
            tvPhone = (TextView) itemView.findViewById(R.id.tv_cold_calling_phone_value);
            tvLoanType = (TextView) itemView.findViewById(R.id.tv_cold_calling_type);
            ivStatus = (ImageView) itemView.findViewById(R.id.iv_cold_calling_sync_status);
            ivPremium = (ImageView) itemView.findViewById(R.id.iv_premium_cold_call);
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
                    List<ColdCallTable> filteredList = new ArrayList<>();
                    for (ColdCallTable row : rawDataTableList) {
                        if (row.getInterestedInLoan()==1) {
                            filteredList.add(row);
                        }
                    }
                    rawDataTableListFiltered = filteredList;
                } else if (charString.equalsIgnoreCase("Not Interested")) {
                    List<ColdCallTable> filteredList = new ArrayList<>();
                    for (ColdCallTable row : rawDataTableList) {
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
                    List<ColdCallTable> filteredList = new ArrayList<>();
                    for (int i = rawDataTableList.size(); i >= 1; i--) {
                        filteredList.add(rawDataTableList.get(i - 1));
                    }
                    rawDataTableListFiltered = filteredList;

                }else if (charString.equalsIgnoreCase("ASCENDING")) {

                    Collections.sort(rawDataTableListFiltered, new Comparator<ColdCallTable>() {
                        @Override
                        public int compare(ColdCallTable lhs, ColdCallTable rhs) {
                            return lhs.getMarketName().compareTo(rhs.getMarketName());
                        }
                    });


                } else if (charString.equalsIgnoreCase("DESCENDING")){
                    Collections.sort(rawDataTableListFiltered, new Comparator<ColdCallTable>() {
                        @Override
                        public int compare(ColdCallTable lhs, ColdCallTable rhs) {
                            return rhs.getMarketName().compareTo(lhs.getMarketName());
                        }
                    });

                } else {
//                    List<Contact> filteredList = new ArrayList<>();
                    List<ColdCallTable> filteredList = new ArrayList<>();
                    for (ColdCallTable row : rawDataTableList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getMobileNo().toLowerCase().contains(charString.toLowerCase())
                                || row.getMarketName().toLowerCase().contains(charString.toLowerCase())) {
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
                rawDataTableListFiltered = (List<ColdCallTable>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public interface SyncCallbackInterface {
        void syncCallback(int position, ColdCallTable coldCallTable);

        void callBackForPhoneCall(int position, String phoneNo,String clientId);

        void openScreenCallback(int position, ColdCallTable coldCallTable);
    }
}
