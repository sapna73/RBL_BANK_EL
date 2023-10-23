package com.saartak.el.adapter;

import android.content.Context;
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
import com.saartak.el.database.entity.VillageSurveyTable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class VillageSurveyAdapter extends RecyclerView.Adapter<VillageSurveyAdapter.LoanTypeViewHolder> implements Filterable {

    private Context context;
    List<VillageSurveyTable> villageSurveyTableList;
    List<VillageSurveyTable> villageSurveyTableListFiltered;
    SyncCallbackInterface syncCallbackInterface;
    AppHelper appHelper;

    public VillageSurveyAdapter(Context context, List<VillageSurveyTable> villageSurveyTableList,
                                SyncCallbackInterface syncCallbackInterface, AppHelper appHelper) {
        this.context = context;
        this.villageSurveyTableList = villageSurveyTableList;
        this.villageSurveyTableListFiltered = villageSurveyTableList;
        this.syncCallbackInterface = syncCallbackInterface;
        this.appHelper = appHelper;
    }

    @NonNull
    @Override
    public LoanTypeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.village_survey_row_item, viewGroup, false);
        return new LoanTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoanTypeViewHolder loanTypeViewHolder, int i) {
        try {
            if (villageSurveyTableListFiltered != null && villageSurveyTableListFiltered.size() > 0) {
                VillageSurveyTable villageSurveyTable = villageSurveyTableListFiltered.get(i);
                loanTypeViewHolder.tvVillageName.setText(villageSurveyTable.getVillageName());
                loanTypeViewHolder.tvVillageId.setText(villageSurveyTable.getVillageId());
                loanTypeViewHolder.tvLoanType.setText(villageSurveyTable.getLoan_type());

                loanTypeViewHolder.cvVillageSurvey.setBackgroundColor(context.getResources().getColor(R.color.lead_deatails_interested));

                boolean allDataSynced = villageSurveyTable.isSync();
                if (allDataSynced) {
                    loanTypeViewHolder.ivStatus.setImageResource(R.mipmap.check);
                    loanTypeViewHolder.llVillageSurvey.setEnabled(false);
                } else {
                    loanTypeViewHolder.ivStatus.setImageResource(R.mipmap.sync_pd);
                    loanTypeViewHolder.llVillageSurvey.setEnabled(true);
                }

                loanTypeViewHolder.llVillageSurvey.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                     syncCallbackInterface.openScreenCallback(i,villageSurveyTable);

                    }
                });

                loanTypeViewHolder.ivStatus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO: No need to check this condition
                            if (appHelper.isNetworkAvailable()) {

                                appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons("Would You Like To Sync This Application ?",
                                        new ConfirmationDialog.ActionCallback() {
                                            @Override
                                            public void onAction() {
                                                if (!villageSurveyTable.isSync()) {
                                                    syncCallbackInterface.syncCallback(i, villageSurveyTable);
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
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (villageSurveyTableListFiltered != null) {

            return villageSurveyTableListFiltered.size();
        } else {
            return 0;
        }
    }

    public void setItem(List<VillageSurveyTable> villageSurveyTableList) {
        try {
            if (this.villageSurveyTableList != null && this.villageSurveyTableList.size() > 0) {
                this.villageSurveyTableList.clear();
                this.villageSurveyTableListFiltered.clear();
                this.villageSurveyTableList = villageSurveyTableList;
                this.villageSurveyTableListFiltered = villageSurveyTableList;
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void clearItems() {
        try {
            if (villageSurveyTableList != null && villageSurveyTableList.size() > 0) {
                villageSurveyTableList.clear();
                villageSurveyTableListFiltered.clear();
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

        public LoanTypeViewHolder(@NonNull View itemView) {
            super(itemView);

            tvVillageName = (TextView) itemView.findViewById(R.id.tv_village_name_value);
            tvVillageId = (TextView) itemView.findViewById(R.id.tv_village_id_value);
            tvLoanType = (TextView) itemView.findViewById(R.id.tv_loan_type);
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
                    villageSurveyTableListFiltered = villageSurveyTableList;
                }else if (charString.equalsIgnoreCase("All")) {
                    villageSurveyTableListFiltered = villageSurveyTableList;
                } else if (charString.equalsIgnoreCase("ASC")) {
                    villageSurveyTableListFiltered = villageSurveyTableList;
                } else if (charString.equalsIgnoreCase("DESC")) {
                    List<VillageSurveyTable> filteredList = new ArrayList<>();
                    for (int i = villageSurveyTableList.size(); i >= 1; i--) {
                        filteredList.add(villageSurveyTableList.get(i - 1));
                    }
                    villageSurveyTableListFiltered = filteredList;

                } else if (charString.equalsIgnoreCase("ASCENDING")) {

                    //  villageSurveyTableListFiltered = villageSurveyTableList;

                    Collections.sort(villageSurveyTableList, new Comparator<VillageSurveyTable>() {
                        @Override
                        public int compare(VillageSurveyTable lhs, VillageSurveyTable rhs) {
                            return lhs.getVillageName().compareTo(rhs.getVillageName());
                        }
                    });

                } else if (charString.equalsIgnoreCase("DESCENDING")){
                    Collections.sort(villageSurveyTableList, new Comparator<VillageSurveyTable>() {
                        @Override
                        public int compare(VillageSurveyTable lhs, VillageSurveyTable rhs) {
                            return rhs.getVillageName().compareTo(lhs.getVillageName());
                        }
                    });

               }  else {
//                    List<Contact> filteredList = new ArrayList<>();
                    List<VillageSurveyTable> filteredList = new ArrayList<>();
                    for (VillageSurveyTable row : villageSurveyTableList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getVillageName().toLowerCase().contains(charString.toLowerCase())
                                || row.getVillageName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    villageSurveyTableListFiltered = filteredList;
                }


                FilterResults filterResults = new FilterResults();
                filterResults.values = villageSurveyTableListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                villageSurveyTableListFiltered = (List<VillageSurveyTable>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public interface SyncCallbackInterface {
        void syncCallback(int position, VillageSurveyTable villageSurveyTable);
        void openScreenCallback(int position, VillageSurveyTable villageSurveyTable);
    }

}
