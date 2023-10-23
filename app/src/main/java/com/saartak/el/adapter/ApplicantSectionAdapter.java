package com.saartak.el.adapter;

import android.content.Context;
import android.content.Intent;
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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.bfil.uilibrary.helpers.AppHelper;
import com.saartak.el.R;
import com.saartak.el.activities.BaseActivity;
import com.saartak.el.activities.LOSBaseActivity;
import com.saartak.el.activities.LeadDetailsActivity;
import com.saartak.el.database.entity.RawDataTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_LEAD;
import static com.saartak.el.constants.AppConstant.PARAM_AUTO_FILL;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_ID;
import static com.saartak.el.constants.AppConstant.PARAM_CLIENT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_HASH_MAP;
import static com.saartak.el.constants.AppConstant.PARAM_USER_NAME;
import static com.saartak.el.constants.AppConstant.REQUEST_CODE_UPDATE_LEAD;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_FULL_NAME;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_INTERESTED_IN_LOAN;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_LEAD_STATUS;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_MOBILE_NUMBER;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_TYPE_OF_LOAN;

public class ApplicantSectionAdapter extends RecyclerView.Adapter<ApplicantSectionAdapter.LoanTypeViewHolder> implements Filterable {

    private Context context;
    private ArrayList<HashMap<String,Object>> leadDetailsDTOList;
    private ArrayList<HashMap<String,Object>> leadDetailsDTOListFiltered;
    List<RawDataTable> rawDataTableList;
    List<RawDataTable> rawDataTableListFiltered;
    SyncCallbackInterface syncCallbackInterface;
    AppHelper appHelper;

    public ApplicantSectionAdapter(Context context, ArrayList<HashMap<String,Object>> leadDetailsDTOList,
                                   List<RawDataTable> rawDataTableList,
                                   SyncCallbackInterface syncCallbackInterface, AppHelper appHelper) {
        this.context = context;
        this.leadDetailsDTOList = leadDetailsDTOList;
        this.leadDetailsDTOListFiltered = leadDetailsDTOList;
        this.rawDataTableList=rawDataTableList;
        this.rawDataTableListFiltered=rawDataTableList;
        this.syncCallbackInterface=syncCallbackInterface;
        this.appHelper=appHelper;
    }

    @NonNull
    @Override
    public LoanTypeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lead_row_item,viewGroup,false);
        return new LoanTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoanTypeViewHolder loanTypeViewHolder, int i) {
        try {
            if (leadDetailsDTOListFiltered != null && leadDetailsDTOListFiltered.size() > 0) {
                loanTypeViewHolder.tvName.setText(leadDetailsDTOListFiltered.get(i).get(TAG_NAME_FULL_NAME).toString());
                loanTypeViewHolder.tvPhone.setText(leadDetailsDTOListFiltered.get(i).get(TAG_NAME_MOBILE_NUMBER).toString());
                if (leadDetailsDTOListFiltered.get(i).containsKey(TAG_NAME_TYPE_OF_LOAN)) {
                    loanTypeViewHolder.tvLoanType.setText(leadDetailsDTOListFiltered.get(i).get(TAG_NAME_TYPE_OF_LOAN).toString());
                } else {
                    loanTypeViewHolder.tvLoanType.setText("Not Interested");
                }
                RawDataTable rawDataTable = rawDataTableListFiltered.get(i);
                if (rawDataTable != null && rawDataTable.isSync()) {
                    loanTypeViewHolder.ivStatus.setImageResource(R.drawable.ic_check_circle_black_24dp);
                    loanTypeViewHolder.ivStatus.setBackgroundColor(context.getResources().getColor(R.color.white));
                } else {
                    loanTypeViewHolder.ivStatus.setImageResource(R.drawable.ic_sync_black_24dp);
                    loanTypeViewHolder.ivStatus.setBackgroundColor(context.getResources().getColor(R.color.colorYellow));
                }
                if (leadDetailsDTOListFiltered.get(i).containsKey(TAG_NAME_INTERESTED_IN_LOAN)) {
                    if (leadDetailsDTOListFiltered.get(i).get(TAG_NAME_INTERESTED_IN_LOAN).toString().equalsIgnoreCase("Yes")) {
                        loanTypeViewHolder.cvLead.setBackgroundColor(context.getResources().getColor(R.color.startblue));

                        if (leadDetailsDTOListFiltered.get(i).containsKey(TAG_NAME_LEAD_STATUS)) {
                            String leadStatus = leadDetailsDTOListFiltered.get(i).get(TAG_NAME_LEAD_STATUS).toString();
                            if (!TextUtils.isEmpty(leadStatus) && leadStatus.equalsIgnoreCase("Hot")) {
                                loanTypeViewHolder.cvLead.setBackgroundColor(context.getResources().getColor(R.color.colorGreen1));
                            } else {
                                loanTypeViewHolder.cvLead.setBackgroundColor(context.getResources().getColor(R.color.startblue));
                            }
                        }
                    } else {
                        loanTypeViewHolder.cvLead.setBackgroundColor(context.getResources().getColor(R.color.colorRed));
                    }

                }


//            loanTypeViewHolder.ivStatus.setText("Completed");

                loanTypeViewHolder.llLeadDetails.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    /*String strLoanType=loanTypeViewHolder.tvLoanType.getText().toString();
                    if(strLoanType.equalsIgnoreCase(AppConstant.LOAN_NAME_INDIVIDUAL)){
                        Intent intent = new Intent(context, PersonalDiscussionActivity.class);
                        intent.putExtra(PARAM_LOAN_TYPE, strLoanType);
                        context.startActivity(intent);
                    }else {
                        Intent intent = new Intent(context, BaseActivity.class);
                        intent.putExtra(PARAM_LOAN_TYPE, strLoanType);
                        context.startActivity(intent);
                    }*/
                        // TODO: No need to check this condition now
//                    if(rawDataTable !=null && ! rawDataTable.isSync()) {
                        if (rawDataTable != null && !TextUtils.isEmpty(rawDataTable.getClient_id())) {
                            LOSBaseActivity.CLIENT_ID = rawDataTable.getClient_id();
                        }
                        Intent intentApplication = new Intent(context, BaseActivity.class);
                        intentApplication.putExtra(PARAM_LOAN_TYPE, rawDataTable.getLoan_type());
                        intentApplication.putExtra(PARAM_USER_NAME, leadDetailsDTOListFiltered.get(i).get(TAG_NAME_FULL_NAME).toString());
                        intentApplication.putExtra(PARAM_BRANCH_ID, rawDataTable.getUser_id());
                        intentApplication.putExtra(PARAM_AUTO_FILL, true);
                        intentApplication.putExtra(PARAM_HASH_MAP, leadDetailsDTOListFiltered.get(i));
                        intentApplication.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_LEAD);
                        intentApplication.putExtra(PARAM_CLIENT_ID, LOSBaseActivity.CLIENT_ID);
//                        context.startActivity(intentApplication);
                        LeadDetailsActivity leadDetailsActivity = (LeadDetailsActivity) context;
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
                        if(appHelper.isNetworkAvailable()) {
                            appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons("Would You Like To Sync This Lead ?",
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

                        }else{
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Internet Connection Required To Sync Data");
                        }
                    }
                });
                loanTypeViewHolder.ivCall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO: No need to check this condition
                        if (leadDetailsDTOListFiltered != null && leadDetailsDTOListFiltered.size() > 0) {
                            String phoneNo = leadDetailsDTOListFiltered.get(i).get(TAG_NAME_MOBILE_NUMBER).toString();
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
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if(leadDetailsDTOListFiltered!=null){

            return leadDetailsDTOListFiltered.size();
        }else{
            return 0;
        }
    }

    public class LoanTypeViewHolder extends RecyclerView.ViewHolder{
//         ImageView ivLoanIcon;
         TextView tvName,tvPhone,tvLoanType;
         ImageView ivStatus,ivCall;
         CardView cvLead;
        LinearLayout llLeadDetails;
        public LoanTypeViewHolder(@NonNull View itemView) {
            super(itemView);
//            ivLoanIcon=(ImageView)itemView.findViewById(R.id.iv_loan_icon);
            tvName=(TextView)itemView.findViewById(R.id.tv_lead_name_value);
            tvPhone=(TextView)itemView.findViewById(R.id.tv_lead_phone_value);
            tvLoanType=(TextView)itemView.findViewById(R.id.tv_lead_type);
            ivStatus=(ImageView)itemView.findViewById(R.id.iv_lead_sync_status);
            ivCall=(ImageView)itemView.findViewById(R.id.iv_lead_call);
            llLeadDetails=(LinearLayout) itemView.findViewById(R.id.ll_lead_summary_values);
            cvLead=(CardView)itemView.findViewById(R.id.cv_lead);
        }
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    leadDetailsDTOListFiltered = leadDetailsDTOList;
                }
                // TODO: ALL STATIC DATA ... NEED TO FIX THIS ISSUE
                else if(charString.equalsIgnoreCase("Interested")){
                    ArrayList<HashMap<String,Object>> filteredList=new ArrayList<>();
                    for (HashMap<String,Object> row : leadDetailsDTOList) {
                        if(row.containsKey(TAG_NAME_INTERESTED_IN_LOAN)) {
                            if (row.get(TAG_NAME_INTERESTED_IN_LOAN).toString().toLowerCase().equalsIgnoreCase("yes")) {
                                filteredList.add(row);
                            }
                        }
                    }
                    leadDetailsDTOListFiltered = filteredList;
                } else if(charString.equalsIgnoreCase("Not Interested")){
                    ArrayList<HashMap<String,Object>> filteredList=new ArrayList<>();
                    for (HashMap<String,Object> row : leadDetailsDTOList) {
                        if(row.containsKey(TAG_NAME_INTERESTED_IN_LOAN)) {
                            if (row.get(TAG_NAME_INTERESTED_IN_LOAN).toString().toLowerCase().equalsIgnoreCase("no")) {
                                filteredList.add(row);
                            }
                        }
                    }
                    leadDetailsDTOListFiltered = filteredList;
                }
                else if(charString.equalsIgnoreCase("Affordable Housing Loan")){
                    ArrayList<HashMap<String,Object>> filteredList=new ArrayList<>();
                    for (HashMap<String,Object> row : leadDetailsDTOList) {
                        if(row.containsKey(TAG_NAME_TYPE_OF_LOAN)) {
                            if (row.get(TAG_NAME_TYPE_OF_LOAN).toString().toLowerCase().equalsIgnoreCase("Affordable Housing Loan")) {
                                filteredList.add(row);
                            }
                        }
                    }
                    leadDetailsDTOListFiltered = filteredList;
                } else if(charString.equalsIgnoreCase("Consumer Durable Loan")){
                    ArrayList<HashMap<String,Object>> filteredList=new ArrayList<>();
                    for (HashMap<String,Object> row : leadDetailsDTOList) {
                        if(row.containsKey(TAG_NAME_TYPE_OF_LOAN)) {
                            if (row.get(TAG_NAME_TYPE_OF_LOAN).toString().toLowerCase().equalsIgnoreCase("Consumer Durable Loan")) {
                                filteredList.add(row);
                            }
                        }
                    }
                    leadDetailsDTOListFiltered = filteredList;
                } else if(charString.equalsIgnoreCase("Individual Loan")){
                    ArrayList<HashMap<String,Object>> filteredList=new ArrayList<>();
                    for (HashMap<String,Object> row : leadDetailsDTOList) {
                        if(row.containsKey(TAG_NAME_TYPE_OF_LOAN)) {
                            if (row.get(TAG_NAME_TYPE_OF_LOAN).toString().toLowerCase().equalsIgnoreCase("Individual Loan")) {
                                filteredList.add(row);
                            }
                        }
                    }
                    leadDetailsDTOListFiltered = filteredList;
                } else if(charString.equalsIgnoreCase("JLG")){
                    ArrayList<HashMap<String,Object>> filteredList=new ArrayList<>();
                    for (HashMap<String,Object> row : leadDetailsDTOList) {
                        if(row.containsKey(TAG_NAME_TYPE_OF_LOAN)) {
                            if (row.get(TAG_NAME_TYPE_OF_LOAN).toString().toLowerCase().equalsIgnoreCase("JLG")) {
                                filteredList.add(row);
                            }
                        }
                    }
                    leadDetailsDTOListFiltered = filteredList;
                }else if(charString.equalsIgnoreCase("Loan against property")){
                    ArrayList<HashMap<String,Object>> filteredList=new ArrayList<>();
                    for (HashMap<String,Object> row : leadDetailsDTOList) {
                        if(row.containsKey(TAG_NAME_TYPE_OF_LOAN)) {
                            if (row.get(TAG_NAME_TYPE_OF_LOAN).toString().toLowerCase().equalsIgnoreCase("Loan against property")) {
                                filteredList.add(row);
                            }
                        }
                    }
                    leadDetailsDTOListFiltered = filteredList;
                } else if(charString.equalsIgnoreCase("Two Wheeler")){
                    ArrayList<HashMap<String,Object>> filteredList=new ArrayList<>();
                    for (HashMap<String,Object> row : leadDetailsDTOList) {
                        if(row.containsKey(TAG_NAME_TYPE_OF_LOAN)) {
                            if (row.get(TAG_NAME_TYPE_OF_LOAN).toString().toLowerCase().equalsIgnoreCase("Two Wheeler")) {
                                filteredList.add(row);
                            }
                        }
                    }
                    leadDetailsDTOListFiltered = filteredList;
                } else if(charString.equalsIgnoreCase("MSME/Business loan")){
                    ArrayList<HashMap<String,Object>> filteredList=new ArrayList<>();
                    for (HashMap<String,Object> row : leadDetailsDTOList) {
                        if(row.containsKey(TAG_NAME_TYPE_OF_LOAN)) {
                            if (row.get(TAG_NAME_TYPE_OF_LOAN).toString().toLowerCase().equalsIgnoreCase("MSME/Business loan")) {
                                filteredList.add(row);
                            }
                        }
                    }
                    leadDetailsDTOListFiltered = filteredList;
                } else if(charString.equalsIgnoreCase("All")){
                    leadDetailsDTOListFiltered = leadDetailsDTOList;
                } else if(charString.equalsIgnoreCase("ASC")){
                    leadDetailsDTOListFiltered = leadDetailsDTOList;
                } else if(charString.equalsIgnoreCase("DESC")){
                    ArrayList<HashMap<String,Object>> filteredList=new ArrayList<>();
                    for(int i=leadDetailsDTOList.size();i>=1;i--){
                        filteredList.add(leadDetailsDTOList.get(i-1));
                    }
                    leadDetailsDTOListFiltered = filteredList;

                }else {
//                    List<Contact> filteredList = new ArrayList<>();
                    ArrayList<HashMap<String,Object>> filteredList=new ArrayList<>();
                    for (HashMap<String,Object> row : leadDetailsDTOList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.get(TAG_NAME_MOBILE_NUMBER).toString().toLowerCase().contains(charString.toLowerCase())
                        || row.get(TAG_NAME_FULL_NAME).toString().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    leadDetailsDTOListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = leadDetailsDTOListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                leadDetailsDTOListFiltered =  (ArrayList<HashMap<String,Object>>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public interface SyncCallbackInterface{
        void syncCallback(int position, RawDataTable rawDataTable);
        void callBackForPhoneCall(int position, String phoneNo);
    }

}
