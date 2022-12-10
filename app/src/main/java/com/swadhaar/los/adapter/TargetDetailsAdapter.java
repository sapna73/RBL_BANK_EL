package com.swadhaar.los.adapter;

import android.content.Context;
import android.os.Build;
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
import com.swadhaar.los.R;
import com.swadhaar.los.database.entity.MasterTable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.swadhaar.los.dynamicui.constants.ParametersConstant.CB_STATUS_ACCEPT;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.CB_STATUS_REJECT;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.CB_STATUS_PENDING;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.INT_CB_STATUS_ACCEPT;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.INT_CB_STATUS_REJECT;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.INT_CB_STATUS_PENDING;

public class TargetDetailsAdapter extends RecyclerView.Adapter<TargetDetailsAdapter.LoanTypeViewHolder> implements Filterable {

    private Context context;
    List<MasterTable> masterTableList;
    List<MasterTable> masterTableListFiltered;
    SyncCallbackInterface syncCallbackInterface;
    AppHelper appHelper;

    public TargetDetailsAdapter(Context context, List<MasterTable> masterTableList,
                                SyncCallbackInterface syncCallbackInterface, AppHelper appHelper) {
        this.context = context;
        this.masterTableList = masterTableList;
        this.masterTableListFiltered = masterTableList;
        this.syncCallbackInterface = syncCallbackInterface;
        this.appHelper = appHelper;
    }

    @NonNull
    @Override
    public LoanTypeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.target_details_row_item, viewGroup, false);
        return new LoanTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoanTypeViewHolder loanTypeViewHolder, int i) {
        try {
            if (masterTableListFiltered != null && masterTableListFiltered.size() > 0) {
                MasterTable masterTable = masterTableListFiltered.get(i);
                loanTypeViewHolder.tvName.setText(masterTable.getClientName());
                loanTypeViewHolder.tvclientid.setText(masterTable.getClientId());

                if (masterTable.getCBStatus() == INT_CB_STATUS_PENDING) {
                    loanTypeViewHolder.tvCBStatus.setText(CB_STATUS_PENDING);
                    loanTypeViewHolder.btnSubmit.setEnabled(false);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        loanTypeViewHolder.tvCBStatus.setTextColor(context.getColorStateList(R.color.red_500));
                        loanTypeViewHolder.btnSubmit.setBackgroundTintList(context.getColorStateList(R.color.colorBlueLight));
                    }
                }
                if (masterTable.getCBStatus() == INT_CB_STATUS_ACCEPT) {
                    loanTypeViewHolder.tvCBStatus.setText(CB_STATUS_ACCEPT);
                    loanTypeViewHolder.btnSubmit.setEnabled(true);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        loanTypeViewHolder.tvCBStatus.setTextColor(context.getColorStateList(R.color.rfs_green));
                        loanTypeViewHolder.btnSubmit.setBackgroundTintList(context.getColorStateList(R.color.colorBlue));
                    }
                }
                if (masterTable.getCBStatus() == INT_CB_STATUS_REJECT) {
                    loanTypeViewHolder.tvCBStatus.setText(CB_STATUS_REJECT);
                    loanTypeViewHolder.btnSubmit.setEnabled(false);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        loanTypeViewHolder.tvCBStatus.setTextColor(context.getColorStateList(R.color.colorBlue));
                        loanTypeViewHolder.btnSubmit.setBackgroundTintList(context.getColorStateList(R.color.colorBlueLight));
                    }
                }


                loanTypeViewHolder.llLeadDetails.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        syncCallbackInterface.openScreenCallback(i, masterTable);

                    }
                });

                loanTypeViewHolder.btnSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        syncCallbackInterface.openLoanApplicationCallback(i, masterTable);
                    }
                });

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (masterTableListFiltered != null) {

            return masterTableListFiltered.size();
        } else {
            return 0;
        }
    }

    public void setItem(List<MasterTable> masterTableList) {
        try {
            if (this.masterTableList != null && this.masterTableList.size() > 0) {
                this.masterTableList.clear();
                this.masterTableListFiltered.clear();
                this.masterTableList = masterTableList;
                this.masterTableListFiltered = masterTableList;
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void clearItems() {
        try {
            if (masterTableList != null && masterTableList.size() > 0) {
                masterTableList.clear();
                masterTableListFiltered.clear();
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public class LoanTypeViewHolder extends RecyclerView.ViewHolder {
        //         ImageView ivLoanIcon;
        TextView tvName, tvclientid, tvCBStatus;
        ImageView ivStatus;
        CardView cvLead;
        LinearLayout llLeadDetails, llApproveOrReject;
        public Button btnSubmit, btnReject, btnFinalStatus;

        public LoanTypeViewHolder(@NonNull View itemView) {
            super(itemView);
//            ivLoanIcon=(ImageView)itemView.findViewById(R.id.iv_loan_icon);
            tvName = (TextView) itemView.findViewById(R.id.tv_lead_name_value);
            tvclientid = (TextView) itemView.findViewById(R.id.tv_lead_clientid_value);
            tvCBStatus = (TextView) itemView.findViewById(R.id.tv_cb_status);
            ivStatus = (ImageView) itemView.findViewById(R.id.iv_lead_sync_status);
            llLeadDetails = (LinearLayout) itemView.findViewById(R.id.ll_lead_summary_values);
            llApproveOrReject = (LinearLayout) itemView.findViewById(R.id.ll_approve_or_reject);
            cvLead = (CardView) itemView.findViewById(R.id.cv_lead);
            btnSubmit = (Button) itemView.findViewById(R.id.btn_submit);
            btnReject = (Button) itemView.findViewById(R.id.btn_reject);
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
                    masterTableListFiltered = masterTableList;
                }
                // TODO: ALL STATIC DATA ... NEED TO FIX THIS ISSUE
                else if (charString.equalsIgnoreCase("All")) {
                    masterTableListFiltered = masterTableList;
                } else if (charString.equalsIgnoreCase("ASC")) {
                    masterTableListFiltered = masterTableList;
                } else if (charString.equalsIgnoreCase("DESC")) {
                    ArrayList<MasterTable> filteredList = new ArrayList<>();
                    for (int i = masterTableList.size(); i >= 1; i--) {
                        filteredList.add(masterTableListFiltered.get(i - 1));
                    }
                    masterTableListFiltered = filteredList;

                } else if (charString.equalsIgnoreCase("ASCENDING")) {

                    Collections.sort(masterTableList, new Comparator<MasterTable>() {
                        @Override
                        public int compare(MasterTable lhs, MasterTable rhs) {
                            return lhs.getClientName().compareTo(rhs.getClientName());
                        }
                    });

                } else if (charString.equalsIgnoreCase("DESCENDING")) {
                    Collections.sort(masterTableList, new Comparator<MasterTable>() {
                        @Override
                        public int compare(MasterTable lhs, MasterTable rhs) {
                            return rhs.getClientName().compareTo(lhs.getClientName());
                        }
                    });

                } else {
                    ArrayList<MasterTable> filteredList = new ArrayList<>();
                    for (MasterTable masterTable : masterTableList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if ((!TextUtils.isEmpty(masterTable.getPhoneNo())
                                && masterTable.getPhoneNo().toLowerCase().contains(charString.toLowerCase()))
                                || (masterTable.getClientName().toLowerCase().contains(charString.toLowerCase()))) {
                            filteredList.add(masterTable);
                        }
                    }

                    masterTableListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = masterTableListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                masterTableListFiltered = (List<MasterTable>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public interface SyncCallbackInterface {
        void openScreenCallback(int position, MasterTable masterTable);

        void syncCallback(int position, MasterTable masterTable);

        void openLoanApplicationCallback(int position, MasterTable masterTable);

        void rejectCallback(int position, MasterTable masterTable);
    }

}
