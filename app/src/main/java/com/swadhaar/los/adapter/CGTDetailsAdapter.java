package com.swadhaar.los.adapter;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.bfil.uilibrary.helpers.AppHelper;
import com.swadhaar.los.R;
import com.swadhaar.los.database.entity.CGTTable;
import com.swadhaar.los.database.entity.CGTTable;
import com.swadhaar.los.database.entity.CGTTable;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.swadhaar.los.dynamicui.constants.ParametersConstant.CONSTANT_CGT_CYCLE_1;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.CONSTANT_CGT_CYCLE_2;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_AT_LEAST_SIX_MONTHS_IN_BANKING_HISTORY;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_CGT_CYCLE_TWO_NOT_COMPLETED;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_CYCLE_ONE_ACTIVITIES_NOT_DONE;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_CYCLE_TWO_ACTIVITIES_NOT_DONE;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_START_CYCLE_ONE;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_START_CYCLE_TWO;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_UNABLE_TO_GET_TOKEN;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.FINAL_STATUS_END_SESSION;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.FINAL_STATUS_START_SESSION;

public class CGTDetailsAdapter extends RecyclerView.Adapter<CGTDetailsAdapter.LoanTypeViewHolder> implements Filterable {

    private Context context;
    List<List<CGTTable>> cgtTableList;
    List<List<CGTTable>> cgtTableListFiltered;
    SyncCallbackInterface syncCallbackInterface;
    AppHelper appHelper;

    public CGTDetailsAdapter(Context context, List<List<CGTTable>> cgtTableList,
                             SyncCallbackInterface syncCallbackInterface, AppHelper appHelper) {
        this.context = context;
        this.cgtTableList = cgtTableList;
        this.cgtTableListFiltered = cgtTableList;
        this.syncCallbackInterface = syncCallbackInterface;
        this.appHelper = appHelper;
    }

    @NonNull
    @Override
    public LoanTypeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cgt_details_row_item, viewGroup, false);
        return new LoanTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoanTypeViewHolder loanTypeViewHolder, int i) {
        try {
            if (cgtTableListFiltered != null && cgtTableListFiltered.size() > 0) {

                List<CGTTable> cgtTableList = cgtTableListFiltered.get(i);
                if (cgtTableList != null && cgtTableList.size() > 0) {
                    CGTTable cgtTable = cgtTableList.get(0);
                    loanTypeViewHolder.tvCenterName.setText(cgtTable.getCenterName());
                    loanTypeViewHolder.tvVillageName.setText(cgtTable.getVillageName());
                    loanTypeViewHolder.tvCenterId.setText(cgtTable.getCenterId());

                    int noOfCGT1Members = 0;
                    int noOfCGT2Members = 0;

                    for (CGTTable cgtTableToCheckMembers : cgtTableList) {
                        if (cgtTableToCheckMembers.isCycleOneCompleted()) {
                            noOfCGT2Members++;
                        } else {
                            noOfCGT1Members++;
                        }
                    }

                    loanTypeViewHolder.tvCGTCycle1.setText(noOfCGT1Members + " (Members)"); // TODO: Number of CGT1 members
                    loanTypeViewHolder.tvCGTCycle2.setText(noOfCGT2Members + " (Members)"); // TODO: Number of CGT2 members

                    List<CGTTable> cgtTwoCompletedListWithSync = new ArrayList<>();
                    List<CGTTable> cgtTwoCompletedListWithoutSync = new ArrayList<>();
                    for (CGTTable cgtTableFromDB : cgtTableList) {
                        if (cgtTableFromDB.isCycleTwoCompleted()) {
                            if (cgtTableFromDB.isSync()) {
                                cgtTwoCompletedListWithSync.add(cgtTableFromDB);
                            } else {
                                cgtTwoCompletedListWithoutSync.add(cgtTableFromDB);
                            }
                        }
                    }

                    if (cgtTwoCompletedListWithSync.size()>0 && cgtTwoCompletedListWithoutSync.size()==0) {
                        loanTypeViewHolder.ivStatus.setImageResource(R.mipmap.check);
//                        loanTypeViewHolder.btnFinalStatus.setVisibility(View.VISIBLE);
                    } else {
                        loanTypeViewHolder.ivStatus.setImageResource(R.mipmap.sync_pd);
//                        loanTypeViewHolder.btnFinalStatus.setVisibility(View.GONE);
                    }


                    loanTypeViewHolder.ivStatus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO: No need to check this condition
                            if (appHelper.isNetworkAvailable()) {

                                if (cgtTwoCompletedListWithoutSync.size() > 0) {
                                    appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons(
                                            "Would You Like To Sync CGT Details ?",
                                            new ConfirmationDialog.ActionCallback() {
                                                @Override
                                                public void onAction() {

                                                    syncCallbackInterface.syncCallback(i, cgtTwoCompletedListWithoutSync);

                                                }
                                            });
                                } else {
                                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, ERROR_MESSAGE_CGT_CYCLE_TWO_NOT_COMPLETED);
                                }

                            } else {
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Internet Connection Required To Sync Data");
                            }
                        }
                    });


    /*                if(isCGT1Checked){
                        loanTypeViewHolder.swCGTCycle1.setChecked(true);
                        loanTypeViewHolder.swCGTCycle2.setChecked(false);
                    }else if(isCGT2Checked){
                        loanTypeViewHolder.swCGTCycle1.setChecked(false);
                        loanTypeViewHolder.swCGTCycle2.setChecked(true);
                    }

                    if (isCycleOncCompleted && isCycleTwoCompleted) {
                        loanTypeViewHolder.swCGTCycle1.setEnabled(false);
                        loanTypeViewHolder.swCGTCycle2.setEnabled(false);
                    } else if (isCycleOncCompleted ){
                        loanTypeViewHolder.swCGTCycle1.setEnabled(false);
                        loanTypeViewHolder.swCGTCycle2.setEnabled(true);
                    }else{
                        loanTypeViewHolder.swCGTCycle1.setEnabled(true);
                        loanTypeViewHolder.swCGTCycle2.setEnabled(true);
                    }


                        if (sync) {
                            loanTypeViewHolder.ivStatus.setImageResource(R.mipmap.check);
                            loanTypeViewHolder.btnFinalStatus.setVisibility(View.VISIBLE);
                            loanTypeViewHolder.btnEndSession.setVisibility(View.GONE);
                            loanTypeViewHolder.btnStartSession.setVisibility(View.GONE);
                        } else {
                            loanTypeViewHolder.ivStatus.setImageResource(R.mipmap.sync_pd);
                            loanTypeViewHolder.btnFinalStatus.setVisibility(View.GONE);
                            loanTypeViewHolder.btnEndSession.setVisibility(View.VISIBLE);
                            loanTypeViewHolder.btnStartSession.setVisibility(View.VISIBLE);
                        }

                    if (!TextUtils.isEmpty(status)) {
                        if (cgtTable.isCycleOneCompleted()) {
                            // TODO: CGT CYCLE TWO
                            loanTypeViewHolder.btnStartSession.setText(FINAL_STATUS_START_SESSION);
                            loanTypeViewHolder.btnEndSession.setText(FINAL_STATUS_END_SESSION);
                            loanTypeViewHolder.btnStartSession.setEnabled(true);
                            loanTypeViewHolder.btnEndSession.setEnabled(true);
                            if (status.equalsIgnoreCase(FINAL_STATUS_SESSION_STARTED)) {

                                loanTypeViewHolder.btnStartSession.setText(status);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    loanTypeViewHolder.btnStartSession.setBackgroundTintList(context.getColorStateList(R.color.colorBlueLight));
                                }
                                loanTypeViewHolder.btnStartSession.setEnabled(false);
                                loanTypeViewHolder.btnEndSession.setEnabled(true);
                            } else if (status.equalsIgnoreCase(FINAL_STATUS_SESSION_ENDED)) {
                                if (isCycleOncCompleted && isCycleTwoCompleted) {
                                    loanTypeViewHolder.btnEndSession.setText(status);

                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        loanTypeViewHolder.btnEndSession.setBackgroundTintList(context.getColorStateList(R.color.colorRedLight));
                                    }
                                    loanTypeViewHolder.btnStartSession.setEnabled(false);
                                    loanTypeViewHolder.btnEndSession.setEnabled(false);
                                    loanTypeViewHolder.btnFinalStatus.setVisibility(View.VISIBLE);
                                    loanTypeViewHolder.btnEndSession.setVisibility(View.GONE);
                                    loanTypeViewHolder.btnStartSession.setVisibility(View.GONE);

                                }
                            }
                        } else {
                            // TODO: CGT CYCLE ONE
                            if (status.equalsIgnoreCase(FINAL_STATUS_SESSION_STARTED)) {

                                loanTypeViewHolder.btnStartSession.setText(status);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    loanTypeViewHolder.btnStartSession.setBackgroundTintList(context.getColorStateList(R.color.colorBlueLight));
                                }
                                loanTypeViewHolder.btnStartSession.setEnabled(false);
                                loanTypeViewHolder.btnEndSession.setEnabled(true);
                            } else if (status.equalsIgnoreCase(FINAL_STATUS_SESSION_ENDED)) {
                                loanTypeViewHolder.btnEndSession.setText(status);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    loanTypeViewHolder.btnEndSession.setBackgroundTintList(context.getColorStateList(R.color.colorRedLight));
                                }
                                loanTypeViewHolder.btnStartSession.setEnabled(false);
                                loanTypeViewHolder.btnEndSession.setEnabled(false);
                            }
                        }
                    }


                    loanTypeViewHolder.btnStartSession.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            syncCallbackInterface.updateSessionCallback(i, cgtTable, FINAL_STATUS_SESSION_STARTED);
                        }
                    });

                    loanTypeViewHolder.btnEndSession.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            syncCallbackInterface.updateSessionCallback(i, cgtTable, FINAL_STATUS_SESSION_ENDED);
                        }
                    });

                    loanTypeViewHolder.llSummaryDetails.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!isCycleOncCompleted) {
                                if (TextUtils.isEmpty(cycleOneStartSession)) {
                                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                            ERROR_MESSAGE_START_CYCLE_ONE);
                                } else {
                                    syncCallbackInterface.openScreenCallback(i, cgtTable);
                                }
                            } else if (!isCycleTwoCompleted) {
                                if (TextUtils.isEmpty(cycleTwoStartSession)) {
                                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                            ERROR_MESSAGE_START_CYCLE_TWO);
                                } else {
                                    syncCallbackInterface.openScreenCallback(i, cgtTable);
                                }
                            } else {
                                syncCallbackInterface.openScreenCallback(i, cgtTable);
                            }

                        }
                    });

                    loanTypeViewHolder.ivStatus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO: No need to check this condition
                            if (appHelper.isNetworkAvailable()) {
                                if (!cgtTable.isSync()) {
                                    if (cgtTable.isCycleOneCompleted()) {

                                        if (cgtTable.isCycleTwoCompleted()) {
                                            appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons(
                                                    "Would You Like To Sync CGT Details ?",
                                                    new ConfirmationDialog.ActionCallback() {
                                                        @Override
                                                        public void onAction() {

                                                            syncCallbackInterface.syncCallback(i, cgtTable);

                                                        }
                                                    });
                                        } else {
                                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, ERROR_MESSAGE_CYCLE_TWO_ACTIVITIES_NOT_DONE);
                                        }
                                    } else {
                                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, ERROR_MESSAGE_CYCLE_ONE_ACTIVITIES_NOT_DONE);
                                    }
                                } else {
                                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Data already synced ");
                                }
                            } else {
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Internet Connection Required To Sync Data");
                            }
                        }
                    });


                    loanTypeViewHolder.swCGTCycle1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            cgtTable.setCGT1Checked(isChecked);
                            loanTypeViewHolder.swCGTCycle2.setChecked(false);
                        }
                    });

                    loanTypeViewHolder.swCGTCycle2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            cgtTable.setCGT2Checked(isChecked);
                            loanTypeViewHolder.swCGTCycle1.setChecked(false);
                        }
                    });*/


                    loanTypeViewHolder.llSummaryDetails.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            syncCallbackInterface.openScreenCallback(i, cgtTable);

                        }
                    });
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (cgtTableListFiltered != null) {

            return cgtTableListFiltered.size();
        } else {
            return 0;
        }
    }

    public void setItem(List<List<CGTTable>> cgtTableList) {
        try {
            if (this.cgtTableList != null && this.cgtTableList.size() > 0) {
                this.cgtTableList.clear();
                this.cgtTableListFiltered.clear();
                this.cgtTableList = cgtTableList;
                this.cgtTableListFiltered = cgtTableList;
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void clearItems() {
        try {
            if (cgtTableList != null && cgtTableList.size() > 0) {
                cgtTableList.clear();
                cgtTableListFiltered.clear();
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public class LoanTypeViewHolder extends RecyclerView.ViewHolder {
        TextView tvCenterName, tvVillageName, tvCenterId, tvCGTCycle1, tvCGTCycle2;
        ImageView ivStatus;
        LinearLayout llSummaryDetails;
        Button btnSubmit, btnFinalStatus;

        public LoanTypeViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCenterName = (TextView) itemView.findViewById(R.id.tv_center_name_value);
            tvVillageName = (TextView) itemView.findViewById(R.id.tv_village_name_value);
            tvCenterId = (TextView) itemView.findViewById(R.id.tv_center_id_value);
            tvCGTCycle1 = (TextView) itemView.findViewById(R.id.tv_cgt_cycle1_value);
            tvCGTCycle2 = (TextView) itemView.findViewById(R.id.tv_cgt_cycle2_value);
            ivStatus = (ImageView) itemView.findViewById(R.id.iv_sync_status);
            llSummaryDetails = (LinearLayout) itemView.findViewById(R.id.ll_summary_values);
            btnSubmit = (Button) itemView.findViewById(R.id.btn_submit);
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
                    cgtTableListFiltered = cgtTableList;
                }
           /*     // TODO: ALL STATIC DATA ... NEED TO FIX THIS ISSUE
                else if (charString.equalsIgnoreCase("All")) {
                    cgtTableListFiltered = cgtTableList;
                } else if (charString.equalsIgnoreCase("ASC")) {
                    cgtTableListFiltered = cgtTableList;
                } else if (charString.equalsIgnoreCase("DESC")) {
                    ArrayList<CGTTable> filteredList = new ArrayList<>();
                    for (int i = cgtTableList.size(); i >= 1; i--) {
                        filteredList.add(cgtTableListFiltered.get(i - 1));
                    }
                    cgtTableListFiltered = filteredList;

                } else if (charString.equalsIgnoreCase("ASCENDING")) {

                    Collections.sort(cgtTableList, new Comparator<CGTTable>() {
                        @Override
                        public int compare(CGTTable lhs, CGTTable rhs) {
                            return lhs.getCenterName().compareTo(rhs.getCenterName());
                        }
                    });

                } else if (charString.equalsIgnoreCase("DESCENDING")){
                    Collections.sort(cgtTableList, new Comparator<CGTTable>() {
                        @Override
                        public int compare(CGTTable lhs, CGTTable rhs) {
                            return rhs.getCenterName().compareTo(lhs.getCenterName());
                        }
                    });

                } else {
                    ArrayList<CGTTable> filteredList = new ArrayList<>();
                    for (CGTTable cgtTable : cgtTableList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if ((!TextUtils.isEmpty(cgtTable.getCenterId())
                                && cgtTable.getCenterId().toLowerCase().contains(charString.toLowerCase()))
                                || (cgtTable.getCenterName().toLowerCase().contains(charString.toLowerCase()))){
                            filteredList.add(cgtTable);
                        }
                    }

                    cgtTableListFiltered = filteredList;
                }
*/
                FilterResults filterResults = new FilterResults();
                filterResults.values = cgtTableListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                cgtTableListFiltered = (List<List<CGTTable>>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public interface SyncCallbackInterface {
        void syncCallback(int position, List<CGTTable> cgtTwoCompletedList);

        void openScreenCallback(int position, CGTTable cgtTable);

    }

}
