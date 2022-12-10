package com.swadhaar.los.adapter;

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

import com.bfil.uilibrary.helpers.AppHelper;
import com.swadhaar.los.R;
import com.swadhaar.los.database.entity.LoanTable;
import com.swadhaar.los.database.entity.LoanTable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LoanApplicationMemberDetailAdapter extends RecyclerView.Adapter<LoanApplicationMemberDetailAdapter.LoanTypeViewHolder> implements Filterable {

    private Context context;
    List<LoanTable> loanTableList;
    List<LoanTable> loanTableListFiltered;
    SyncCallbackInterface syncCallbackInterface;
    AppHelper appHelper;

    public LoanApplicationMemberDetailAdapter(Context context, List<LoanTable> loanTableList,
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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.loan_application_member_row_item, viewGroup, false);
        return new LoanTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoanTypeViewHolder loanTypeViewHolder, int i) {
        try {
            if (loanTableListFiltered != null && loanTableListFiltered.size() > 0) {
                LoanTable masterTable = loanTableListFiltered.get(i);
                loanTypeViewHolder.tvClientName.setText(masterTable.getClientName());
                loanTypeViewHolder.tvClientId.setText(masterTable.getClientId());
                loanTypeViewHolder.tvCenterName.setText(masterTable.getCenterName());
                loanTypeViewHolder.tvCenterId.setText(masterTable.getCenterId());

//                boolean allDataSynced = masterTable.isSync();
//                if (allDataSynced) {
//                    loanTypeViewHolder.ivStatus.setImageResource(R.mipmap.check);
//                    loanTypeViewHolder.llVillageSurvey.setEnabled(false);
//                } else {
//                    loanTypeViewHolder.ivStatus.setImageResource(R.drawable.loan_application);
//                    loanTypeViewHolder.llVillageSurvey.setEnabled(true);
//                }

                loanTypeViewHolder.llVillageSurvey.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                     syncCallbackInterface.openScreenCallback(i,masterTable);

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
        TextView tvClientName,tvClientId, tvCenterName, tvCenterId;
        ImageView ivStatus;
        LinearLayout cvVillageSurvey;
        LinearLayout llVillageSurvey;

        public LoanTypeViewHolder(@NonNull View itemView) {
            super(itemView);

            tvClientName = (TextView) itemView.findViewById(R.id.tv_client_name_value);
            tvClientId = (TextView) itemView.findViewById(R.id.tv_client_id_value);
            tvCenterName = (TextView) itemView.findViewById(R.id.tv_center_name_value);
            tvCenterId = (TextView) itemView.findViewById(R.id.tv_center_id_value);
            ivStatus = (ImageView) itemView.findViewById(R.id.iv_sync_status);
            llVillageSurvey = (LinearLayout) itemView.findViewById(R.id.ll_village_summary_values);
            cvVillageSurvey = itemView.findViewById(R.id.cv_village_survey);
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
                else if (charString.equalsIgnoreCase("All")) {
                    loanTableListFiltered = loanTableList;
                } else if (charString.equalsIgnoreCase("ASC")) {
                    loanTableListFiltered = loanTableList;
                } else if (charString.equalsIgnoreCase("DESC")) {
                    ArrayList<LoanTable> filteredList = new ArrayList<>();
                    for (int i = loanTableList.size(); i >= 1; i--) {
                        filteredList.add(loanTableListFiltered.get(i - 1));
                    }
                    loanTableListFiltered = filteredList;

                } else if (charString.equalsIgnoreCase("ASCENDING")) {

                    Collections.sort(loanTableList, new Comparator<LoanTable>() {
                        @Override
                        public int compare(LoanTable lhs, LoanTable rhs) {
                            return lhs.getClientName().compareTo(rhs.getClientName());
                        }
                    });

                } else if (charString.equalsIgnoreCase("DESCENDING")){
                    Collections.sort(loanTableList, new Comparator<LoanTable>() {
                        @Override
                        public int compare(LoanTable lhs, LoanTable rhs) {
                            return rhs.getClientName().compareTo(lhs.getClientName());
                        }
                    });

                } else {
                    ArrayList<LoanTable> filteredList = new ArrayList<>();
                    for (LoanTable masterTable : loanTableList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if ((!TextUtils.isEmpty(masterTable.getPhoneNo())
                                && masterTable.getPhoneNo().toLowerCase().contains(charString.toLowerCase()))
                                || (masterTable.getClientName().toLowerCase().contains(charString.toLowerCase()))){
                            filteredList.add(masterTable);
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
        void openScreenCallback(int position, LoanTable masterTable);
    }

}
