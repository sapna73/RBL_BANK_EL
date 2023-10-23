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
import com.saartak.el.activities.LOSBaseActivity;
import com.saartak.el.activities.PlannerActivity;
import com.saartak.el.activities.PlannerSummaryActivity;
import com.saartak.el.database.entity.PlannerTable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.saartak.el.constants.AppConstant.MODULE_TYPE_PLANNER;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_ID;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_CLIENT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_NAME;
import static com.saartak.el.constants.AppConstant.REQUEST_CODE_UPDATE_LEAD;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TRIP_ENDED_IN_PLANNER;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TRIP_STARTED_IN_PLANNER;

public class PlannerSummaryAdapter extends RecyclerView.Adapter<PlannerSummaryAdapter.LoanTypeViewHolder> implements Filterable {

    private Context context;
    List<PlannerTable> plannerTableList;
    List<PlannerTable> plannerTableListFiltered;
    SyncCallbackInterface syncCallbackInterface;
    AppHelper appHelper;

    public PlannerSummaryAdapter(Context context, List<PlannerTable> plannerTableList,
                                     SyncCallbackInterface syncCallbackInterface, AppHelper appHelper) {
        this.context = context;
        this.plannerTableList = plannerTableList;
        this.plannerTableListFiltered = plannerTableList;
        this.syncCallbackInterface = syncCallbackInterface;
        this.appHelper = appHelper;
    }

    @NonNull
    @Override
    public LoanTypeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.planner_summary_row_item, viewGroup, false);
        return new LoanTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoanTypeViewHolder loanTypeViewHolder, int position) {
        try {
            if (plannerTableListFiltered != null && plannerTableListFiltered.size() > 0) {
                PlannerTable plannerTable = plannerTableListFiltered.get(position);
                loanTypeViewHolder.tvShopName.setText(plannerTable.getShopName());
                loanTypeViewHolder.tvPurposeOfVisit.setText(plannerTable.getPurposeOfVisit());
                if(plannerTable.isTripEnd()){
                    loanTypeViewHolder.tvStatus.setText(TRIP_ENDED_IN_PLANNER);
                    loanTypeViewHolder.cvPlanner.setBackgroundColor(context.getResources().getColor(R.color.colorGreenLight));

                }else{
                    loanTypeViewHolder.tvStatus.setText(TRIP_STARTED_IN_PLANNER);
                    loanTypeViewHolder.cvPlanner.setBackgroundColor(context.getResources().getColor(R.color.colorRed_light_background));

                }
                // TODO:  Status to be display
               // loanTypeViewHolder.tvStatus.setText(plannerTable.());


                if (plannerTable != null && plannerTable.isSync()) {
                    loanTypeViewHolder.ivStatus.setImageResource(R.drawable.ic_check_circle_black_24dp);
                    loanTypeViewHolder.llLeadDetails.setEnabled(false);

                } else {
                    loanTypeViewHolder.ivStatus.setImageResource(R.mipmap.sync_pd);
                    loanTypeViewHolder.llLeadDetails.setEnabled(true);
                }

                loanTypeViewHolder.llLeadDetails.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (plannerTable != null &&  !TextUtils.isEmpty(plannerTable.getClientId())) {
                            LOSBaseActivity.CLIENT_ID = plannerTable.getClientId();
                        }
                        PlannerSummaryActivity plannerSummaryActivity = (PlannerSummaryActivity) context;

                        Intent intentApplication = new Intent(context, PlannerActivity.class);
                        intentApplication.putExtra(PARAM_LOAN_TYPE,plannerTable.getLoan_type());
                        intentApplication.putExtra(PARAM_USER_NAME, plannerSummaryActivity.userName);
                        intentApplication.putExtra(PARAM_BRANCH_ID, plannerSummaryActivity.branchId);
                        intentApplication.putExtra(PARAM_BRANCH_NAME, plannerSummaryActivity.branchName);
                        intentApplication.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_PLANNER);
                        intentApplication.putExtra(PARAM_CLIENT_ID, LOSBaseActivity.CLIENT_ID);
                        intentApplication.putExtra(PARAM_USER_ID, plannerSummaryActivity.userId);
                        intentApplication.putExtra(PARAM_PRODUCT_ID, plannerSummaryActivity.productId);
//                        context.startActivity(intentApplication);
                        plannerSummaryActivity.startActivityForResult(intentApplication, REQUEST_CODE_UPDATE_LEAD);


                    }
                });

                loanTypeViewHolder.ivStatus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(plannerTable.isTripEnd()) {
                            if (!plannerTable.isSync()) {
                                // TODO: No need to check this condition
                                if (appHelper.isNetworkAvailable()) {
                                    appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons("Would You Like To sync data?",
                                            new ConfirmationDialog.ActionCallback() {
                                                @Override
                                                public void onAction() {
                                                    if (plannerTable != null && !plannerTable.isSync()) {
                                                        syncCallbackInterface.syncCallback(position, plannerTable);
                                                    } else {
                                                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Data already synced ");
                                                    }
                                                }
                                            });

                                } else {
                                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Internet Connection Required To Sync Data");
                                }
                                
                            }
                        }else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Trip not ended");

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
        if (plannerTableListFiltered != null) {

            return plannerTableListFiltered.size();
        } else {
            return 0;
        }
    }

    public class LoanTypeViewHolder extends RecyclerView.ViewHolder {
        TextView tvShopName, tvPurposeOfVisit, tvStatus;
        ImageView ivStatus, ivCall;
        CardView cvPlanner;
        LinearLayout llLeadDetails;
        private TextView textViewSeparator;


        public LoanTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvShopName = (TextView) itemView.findViewById(R.id.tv_shop_name_value);
            tvPurposeOfVisit = (TextView) itemView.findViewById(R.id.tv_purpose_of_visit_value);
            tvStatus = (TextView) itemView.findViewById(R.id.tv_status_value);
            ivStatus = (ImageView) itemView.findViewById(R.id.iv_planner_sync_status);
            llLeadDetails = (LinearLayout) itemView.findViewById(R.id.ll_planner_summary_values);
            cvPlanner =(CardView) itemView.findViewById(R.id.cv_planner);
            textViewSeparator = itemView.findViewById(R.id.textView_separator);

        }
    }

    public interface SyncCallbackInterface {
        void syncCallback(int position, PlannerTable plannerTable);


    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    plannerTableListFiltered = plannerTableList;
                }else if (charString.equalsIgnoreCase("All")) {
                    plannerTableListFiltered = plannerTableList;
                } else if (charString.equalsIgnoreCase("ASC")) {
                    plannerTableListFiltered = plannerTableList;
                } else if (charString.equalsIgnoreCase("DESC")) {
                    List<PlannerTable> filteredList = new ArrayList<>();
                    for (int i = plannerTableList.size(); i >= 1; i--) {
                        filteredList.add(plannerTableList.get(i - 1));
                    }
                    plannerTableListFiltered = filteredList;

                }  else if (charString.equalsIgnoreCase("ASCENDING")) {

                    Collections.sort(plannerTableListFiltered, new Comparator<PlannerTable>() {
                        @Override
                        public int compare(PlannerTable lhs, PlannerTable rhs) {
                            return lhs.getShopName().compareTo(rhs.getShopName());
                        }
                    });

                } else if (charString.equalsIgnoreCase("DESCENDING")){
                    Collections.sort(plannerTableListFiltered, new Comparator<PlannerTable>() {
                        @Override
                        public int compare(PlannerTable lhs, PlannerTable rhs) {
                            return rhs.getShopName().compareTo(lhs.getShopName());
                        }
                    });

                }
                // TODO: ALL STATIC DATA ... NEED TO FIX THIS ISSUE
                else {
                    ArrayList<PlannerTable> filteredList = new ArrayList<>();
                    for (PlannerTable PlannerTable : plannerTableList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (PlannerTable.getShopName().toLowerCase().contains(charString.toLowerCase())
                                || PlannerTable.getPurposeOfVisit().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(PlannerTable);
                        }
                    }

                    plannerTableListFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = plannerTableListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                plannerTableListFiltered = (List<PlannerTable>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
