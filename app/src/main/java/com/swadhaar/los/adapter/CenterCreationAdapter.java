package com.swadhaar.los.adapter;

import android.content.Context;
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
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.bfil.uilibrary.helpers.AppHelper;
import com.swadhaar.los.R;
import com.swadhaar.los.database.entity.CenterCreationTable;
import com.swadhaar.los.database.entity.CenterCreationTable;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CenterCreationAdapter extends RecyclerView.Adapter<CenterCreationAdapter.LoanTypeViewHolder> implements Filterable {

    private Context context;
    List<CenterCreationTable> centerCreationTableList;
    List<CenterCreationTable> centerCreationTableListFiltered;
    SyncCallbackInterface syncCallbackInterface;
    AppHelper appHelper;

    public CenterCreationAdapter(Context context, List<CenterCreationTable> centerCreationTableList,
                                 SyncCallbackInterface syncCallbackInterface, AppHelper appHelper) {
        this.context = context;
        this.centerCreationTableList = centerCreationTableList;
        this.centerCreationTableListFiltered = centerCreationTableList;
        this.syncCallbackInterface = syncCallbackInterface;
        this.appHelper = appHelper;
    }

    @NonNull
    @Override
    public LoanTypeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.center_creation_row_item, viewGroup, false);
        return new LoanTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoanTypeViewHolder loanTypeViewHolder, int i) {
        try {
            if (centerCreationTableListFiltered != null && centerCreationTableListFiltered.size() > 0) {
                CenterCreationTable centerCreationTable = centerCreationTableListFiltered.get(i);
                loanTypeViewHolder.tvVillageName.setText(centerCreationTable.getCenterName());
                loanTypeViewHolder.tvVillageId.setText(centerCreationTable.getCenterId());
                loanTypeViewHolder.tvLoanType.setText(centerCreationTable.getLoan_type());


                boolean allDataSynced = centerCreationTable.isSync();
                if (allDataSynced) {
                    loanTypeViewHolder.ivStatus.setImageResource(R.mipmap.check);
                    loanTypeViewHolder.llVillageSurvey.setEnabled(false);
                } else {
//                    loanTypeViewHolder.ivStatus.setImageResource(R.mipmap.sync_pd);
                    loanTypeViewHolder.ivStatus.setImageResource(R.drawable.branch);
                    loanTypeViewHolder.llVillageSurvey.setEnabled(true);
                }

//                loanTypeViewHolder.llVillageSurvey.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                     syncCallbackInterface.openScreenCallback(i,centerCreationTable);
//
//                    }
//                });

                loanTypeViewHolder.btnAddMember.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                     syncCallbackInterface.openScreenCallback(i,centerCreationTable);

                    }
                });

                loanTypeViewHolder.ivStatus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO: No need to check this condition
                           /* if (appHelper.isNetworkAvailable()) {

                                appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons("Would You Like To Sync This Data ?",
                                        new ConfirmationDialog.ActionCallback() {
                                            @Override
                                            public void onAction() {
                                                if (!centerCreationTable.isSync()) {
                                                    syncCallbackInterface.syncCallback(i, centerCreationTable);
                                                } else {
                                                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Data already synced ");
                                                }
                                            }
                                        });
                            } else {
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Internet Connection Required To Sync Data");
                            }*/
                    }
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (centerCreationTableListFiltered != null) {

            return centerCreationTableListFiltered.size();
        } else {
            return 0;
        }
    }

    public void setItem(List<CenterCreationTable> centerCreationTableList) {
        try {
            if (this.centerCreationTableList != null && this.centerCreationTableList.size() > 0) {
                this.centerCreationTableList.clear();
                this.centerCreationTableListFiltered.clear();
                this.centerCreationTableList = centerCreationTableList;
                this.centerCreationTableListFiltered = centerCreationTableList;
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void clearItems() {
        try {
            if (centerCreationTableList != null && centerCreationTableList.size() > 0) {
                centerCreationTableList.clear();
                centerCreationTableListFiltered.clear();
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public class LoanTypeViewHolder extends RecyclerView.ViewHolder {
        TextView tvVillageName,tvVillageId, tvLoanType;
        ImageView ivStatus;
        LinearLayout cvVillageSurvey;
        LinearLayout llVillageSurvey;
        Button btnAddMember;

        public LoanTypeViewHolder(@NonNull View itemView) {
            super(itemView);

            tvVillageName = (TextView) itemView.findViewById(R.id.tv_village_name_value);
            tvVillageId = (TextView) itemView.findViewById(R.id.tv_village_id_value);
            tvLoanType = (TextView) itemView.findViewById(R.id.tv_loan_type);
            ivStatus = (ImageView) itemView.findViewById(R.id.iv_sync_status);
            btnAddMember = (Button) itemView.findViewById(R.id.btn_add_member);
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
                    centerCreationTableListFiltered = centerCreationTableList;
                }else if (charString.equalsIgnoreCase("All")) {
                    centerCreationTableListFiltered = centerCreationTableList;
                } else if (charString.equalsIgnoreCase("ASC")) {
                    centerCreationTableListFiltered = centerCreationTableList;
                } else if (charString.equalsIgnoreCase("DESC")) {
                    List<CenterCreationTable> filteredList = new ArrayList<>();
                    for (int i = centerCreationTableList.size(); i >= 1; i--) {
                        filteredList.add(centerCreationTableList.get(i - 1));
                    }
                    centerCreationTableListFiltered = filteredList;

                }  else if (charString.equalsIgnoreCase("ASCENDING")) {

                    Collections.sort(centerCreationTableList, new Comparator<CenterCreationTable>() {
                        @Override
                        public int compare(CenterCreationTable lhs, CenterCreationTable rhs) {
                            return lhs.getCenterName().compareTo(rhs.getCenterName());
                        }
                    });

                } else if (charString.equalsIgnoreCase("DESCENDING")){
                    Collections.sort(centerCreationTableList, new Comparator<CenterCreationTable>() {
                        @Override
                        public int compare(CenterCreationTable lhs, CenterCreationTable rhs) {
                            return rhs.getCenterName().compareTo(lhs.getCenterName());
                        }
                    });

                } else {
//                    List<Contact> filteredList = new ArrayList<>();
                    List<CenterCreationTable> filteredList = new ArrayList<>();
                    for (CenterCreationTable row : centerCreationTableList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number matchh
                        if (row.getCenterName().toLowerCase().contains(charString.toLowerCase())
                                || row.getCenterName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    centerCreationTableListFiltered = filteredList;
                }


                FilterResults filterResults = new FilterResults();
                filterResults.values = centerCreationTableListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                centerCreationTableListFiltered = (List<CenterCreationTable>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public interface SyncCallbackInterface {
        void syncCallback(int position, CenterCreationTable centerCreationTable);
        void openScreenCallback(int position, CenterCreationTable centerCreationTable);
    }

}
