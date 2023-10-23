package com.saartak.el.adapter;

import android.content.Context;
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

import com.bfil.uilibrary.helpers.AppHelper;
import com.saartak.el.R;
import com.saartak.el.database.entity.ApplicationStatusTable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class WorkflowHistorySummaryAdapter extends RecyclerView.Adapter<WorkflowHistorySummaryAdapter.LoanTypeViewHolder> implements Filterable {

    private Context context;
    List<ApplicationStatusTable> applicationStatusTableList;
    List<ApplicationStatusTable> applicationStatusTableListFiltered;
    SyncCallbackInterface syncCallbackInterface;
    AppHelper appHelper;

    public WorkflowHistorySummaryAdapter(Context context, List<ApplicationStatusTable> applicationStatusTableList,
                                         SyncCallbackInterface syncCallbackInterface, AppHelper appHelper) {
        this.context = context;
        this.applicationStatusTableList = applicationStatusTableList;
        this.applicationStatusTableListFiltered = applicationStatusTableList;
        this.syncCallbackInterface = syncCallbackInterface;
        this.appHelper = appHelper;
    }

    @NonNull
    @Override
    public LoanTypeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.application_status_row_item, viewGroup, false);
        return new LoanTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoanTypeViewHolder loanTypeViewHolder, int i) {
        try {
            if (applicationStatusTableListFiltered != null && applicationStatusTableListFiltered.size() > 0) {
                ApplicationStatusTable applicationStatusTable = applicationStatusTableListFiltered.get(i);
                loanTypeViewHolder.tvName.setText(applicationStatusTable.getClientName());
                loanTypeViewHolder.tvclientid.setText(applicationStatusTable.getClientId());
                loanTypeViewHolder.btnCurrentStage.setText(applicationStatusTable.getCurrentStage());
                loanTypeViewHolder.btnCurrentStage.setEnabled(false);

                loanTypeViewHolder.llLeadDetails.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        // TOdo to be implemented
                        syncCallbackInterface.openScreenCallback(i,applicationStatusTable);
                    }
                });

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (applicationStatusTableListFiltered != null) {

            return applicationStatusTableListFiltered.size();
        } else {
            return 0;
        }
    }

    public void setItem(List<ApplicationStatusTable> applicationStatusTableList) {
        try {
            if (this.applicationStatusTableList != null && this.applicationStatusTableList.size() > 0) {
                this.applicationStatusTableList.clear();
                this.applicationStatusTableListFiltered.clear();
                this.applicationStatusTableList = applicationStatusTableList;
                this.applicationStatusTableListFiltered = applicationStatusTableList;
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void clearItems() {
        try {
            if (applicationStatusTableList != null && applicationStatusTableList.size() > 0) {
                applicationStatusTableList.clear();
                applicationStatusTableListFiltered.clear();
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public class LoanTypeViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvclientid;
        ImageView ivStatus;
        CardView cvLead;
        LinearLayout llLeadDetails, llApproveOrReject;
        public Button btnCurrentStage;

        public LoanTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_lead_name_value);
            tvclientid = (TextView) itemView.findViewById(R.id.tv_lead_clientid_value);
            ivStatus = (ImageView) itemView.findViewById(R.id.iv_lead_sync_status);
            llLeadDetails = (LinearLayout) itemView.findViewById(R.id.ll_lead_summary_values);
            llApproveOrReject = (LinearLayout) itemView.findViewById(R.id.ll_approve_or_reject);
            cvLead = (CardView) itemView.findViewById(R.id.cv_lead);
            btnCurrentStage = (Button) itemView.findViewById(R.id.btn_currentStage);
        }
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    applicationStatusTableListFiltered = applicationStatusTableList;
                }
                // TODO: ALL STATIC DATA ... NEED TO FIX THIS ISSUE
                else if (charString.equalsIgnoreCase("ASCENDING")) {
                    applicationStatusTableListFiltered = applicationStatusTableList;

                    Collections.sort(applicationStatusTableListFiltered, new Comparator<ApplicationStatusTable>() {
                        @Override
                        public int compare(ApplicationStatusTable lhs, ApplicationStatusTable rhs) {
                            if (lhs.getClientName() == null || rhs.getClientName() == null) {
                                return 1;
                            } else {
                                return (lhs.getClientName().compareTo(rhs.getClientName()));
                            }
                        }
                    });

                } else if (charString.equalsIgnoreCase("DESCENDING")) {
                    applicationStatusTableListFiltered = applicationStatusTableList;
                    Collections.sort(applicationStatusTableListFiltered, new Comparator<ApplicationStatusTable>() {
                        @Override
                        public int compare(ApplicationStatusTable lhs, ApplicationStatusTable rhs) {
                            if (lhs.getClientName() == null || rhs.getClientName() == null) {
                                return 1;
                            } else {
                               
                                return rhs.getClientName().compareTo(lhs.getClientName());
                            }
                        }
                    });


                } else {
                    ArrayList<ApplicationStatusTable> filteredList = new ArrayList<>();
                    for (ApplicationStatusTable applicationStatusTable : applicationStatusTableList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if ((!TextUtils.isEmpty(applicationStatusTable.getClientId()) && applicationStatusTable.getClientId().toLowerCase().contains(charString.toLowerCase()))
                                || (!TextUtils.isEmpty(applicationStatusTable.getClientName()) && applicationStatusTable.getClientName().toLowerCase().contains(charString.toLowerCase()))) {
                            filteredList.add(applicationStatusTable);
                        } else if (!TextUtils.isEmpty(applicationStatusTable.getCurrentStage()) && applicationStatusTable.getCurrentStage().toLowerCase().equalsIgnoreCase(charString.toLowerCase())) {
                            filteredList.add(applicationStatusTable);
                        }
                    }

                    applicationStatusTableListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = applicationStatusTableListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                applicationStatusTableListFiltered = (List<ApplicationStatusTable>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public interface SyncCallbackInterface {
        void openScreenCallback(int position, ApplicationStatusTable applicationStatusTable);

        void syncCallback(int position, ApplicationStatusTable applicationStatusTable);

        void submitCallback(int position, ApplicationStatusTable applicationStatusTable);

        void rejectCallback(int position, ApplicationStatusTable applicationStatusTable);
    }

}
