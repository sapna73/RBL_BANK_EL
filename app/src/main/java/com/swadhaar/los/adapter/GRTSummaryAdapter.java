package com.swadhaar.los.adapter;

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

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.bfil.uilibrary.helpers.AppHelper;
import com.swadhaar.los.R;
import com.swadhaar.los.database.entity.GRTTable;

import java.util.List;

import static com.swadhaar.los.dynamicui.constants.ParametersConstant.FINAL_STATUS_APPROVED;

public class GRTSummaryAdapter extends RecyclerView.Adapter<GRTSummaryAdapter.LoanTypeViewHolder> implements Filterable {

    private Context context;
    List<List<GRTTable>> grtTableList;
    List<List<GRTTable>> grtTableListFiltered;
    SyncCallbackInterface syncCallbackInterface;
    AppHelper appHelper;

    public GRTSummaryAdapter(Context context, List<List<GRTTable>> grtTableList,
                             SyncCallbackInterface syncCallbackInterface, AppHelper appHelper) {
        this.context = context;
        this.grtTableList = grtTableList;
        this.grtTableListFiltered = grtTableList;
        this.syncCallbackInterface = syncCallbackInterface;
        this.appHelper = appHelper;
    }

    @NonNull
    @Override
    public LoanTypeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grt_summary_row_item, viewGroup, false);
        return new LoanTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoanTypeViewHolder loanTypeViewHolder, int i) {
        try {
            if (grtTableListFiltered != null && grtTableListFiltered.size() > 0) {
                List<GRTTable> grtTableList = grtTableListFiltered.get(i);
                if(grtTableList !=null && grtTableList.size()>0) {
                    GRTTable grtTable = grtTableList.get(0);
                    loanTypeViewHolder.tvName.setText(grtTable.getCenterName());
                    loanTypeViewHolder.tvloantype.setText(grtTable.getLoan_type());
                    loanTypeViewHolder.tvclientid.setText(grtTable.getCenterId());
                    // loanTypeViewHolder.tvPhone.setText(grtTable.getPhoneNo());
                    boolean allDataSynced = false;
                    for(GRTTable grtTableFromDB :grtTableList){
                        if(grtTableFromDB.isSync()){
                            allDataSynced=true;
                        }else{
                            allDataSynced=false;
                            break;
                        }
                    }
                    if (allDataSynced) {
                        loanTypeViewHolder.ivStatus.setImageResource(R.mipmap.check);
                        //  loanTypeViewHolder.ivStatus.setBackgroundColor(context.getResources().getColor(R.color.white));
                        loanTypeViewHolder.llLeadDetails.setEnabled(false);
                    } else {
                        loanTypeViewHolder.ivStatus.setImageResource(R.mipmap.sync_pd);
                        loanTypeViewHolder.llLeadDetails.setEnabled(true);
                    }
                    /*boolean allDataCaptured = grtTable.isAllDataCaptured();
                    if (allDataCaptured) {
                        loanTypeViewHolder.ivStatus.setVisibility(View.VISIBLE);
                    } else {
                        loanTypeViewHolder.ivStatus.setVisibility(View.VISIBLE);
                    }*/

                    boolean approved=false;
                    for(GRTTable grtTableFromDB :grtTableList){
                        if( !TextUtils.isEmpty(grtTableFromDB.getStatus()) && grtTableFromDB.getStatus().equalsIgnoreCase(FINAL_STATUS_APPROVED)){
                            approved=true;
                        }else{
                            approved=false;
                            break;
                        }
                    }

                    if ( approved) {
                        loanTypeViewHolder.btnApprove.setVisibility(View.GONE);
                        loanTypeViewHolder.btnReject.setVisibility(View.GONE);
                        loanTypeViewHolder.btnFinalStatus.setVisibility(View.VISIBLE);
                        loanTypeViewHolder.btnFinalStatus.setText(grtTable.getStatus());
                    } else {
                        loanTypeViewHolder.btnApprove.setVisibility(View.VISIBLE);
                        loanTypeViewHolder.btnReject.setVisibility(View.VISIBLE);
                        loanTypeViewHolder.btnFinalStatus.setVisibility(View.GONE);
                    }
                }

                loanTypeViewHolder.llLeadDetails.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        syncCallbackInterface.openCallback(i, grtTableList);

                    }
                });

                loanTypeViewHolder.ivStatus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO: No need to check this condition
//                        if (grtTable != null && !grtTable.getStatus().equalsIgnoreCase(FINAL_STATUS_PENDING)) {
                            if (appHelper.isNetworkAvailable()) {

                                appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons("Would You Like To Sync This Data ?",
                                        new ConfirmationDialog.ActionCallback() {
                                            @Override
                                            public void onAction() {
//                                                if (!grtTable.isSync()) {
                                                    syncCallbackInterface.syncCallback(i, grtTableList);
//                                                } else {
//                                                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Data already synced ");
//                                                }
                                            }
                                        });
                            } else {
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Internet Connection Required To Sync Data");
                            }
                      /*  } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Please perform submit or reject to do sync");
                        }*/
                    }
                });
                loanTypeViewHolder.btnApprove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons("Do you want to Approve ?",
                                new ConfirmationDialog.ActionCallback() {
                                    @Override
                                    public void onAction() {
                                        syncCallbackInterface.approveCallback(i, grtTableList);
                                    }
                                });
                    }
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (grtTableListFiltered != null) {

            return grtTableListFiltered.size();
        } else {
            return 0;
        }
    }

    public void setItem(List<List<GRTTable>> grtTableList) {
        try {
            if (this.grtTableList != null && this.grtTableList.size() > 0) {
                this.grtTableList.clear();
                this.grtTableListFiltered.clear();
                this.grtTableList = grtTableList;
                this.grtTableListFiltered = grtTableList;
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void clearItems() {
        try {
            if (grtTableList != null && grtTableList.size() > 0) {
                grtTableList.clear();
                grtTableListFiltered.clear();
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public class LoanTypeViewHolder extends RecyclerView.ViewHolder {
        //         ImageView ivLoanIcon;
        TextView tvName, tvPhone, tvloantype, tvamount, tvclientid;
        ImageView ivStatus;
        CardView cvLead;
        LinearLayout llLeadDetails, llApproveOrReject;
        public Button btnApprove, btnReject, btnFinalStatus;

        public LoanTypeViewHolder(@NonNull View itemView) {
            super(itemView);
//            ivLoanIcon=(ImageView)itemView.findViewById(R.id.iv_loan_icon);
            tvName = (TextView) itemView.findViewById(R.id.tv_lead_name_value);
            //tvPhone = (TextView) itemView.findViewById(R.id.tv_lead_phone_value);
            tvloantype = (TextView) itemView.findViewById(R.id.tv_lead_loantype_value);
            tvamount = (TextView) itemView.findViewById(R.id.tv_lead_amount_value);
            tvclientid = (TextView) itemView.findViewById(R.id.tv_lead_clientid_value);
            ivStatus = (ImageView) itemView.findViewById(R.id.iv_lead_sync_status);
            llLeadDetails = (LinearLayout) itemView.findViewById(R.id.ll_lead_summary_values);
            llApproveOrReject = (LinearLayout) itemView.findViewById(R.id.ll_approve_or_reject);
            cvLead = (CardView) itemView.findViewById(R.id.cv_lead);
            btnApprove = (Button) itemView.findViewById(R.id.btn_approve);
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
                    grtTableListFiltered = grtTableList;
                }
              /*  // TODO: ALL STATIC DATA ... NEED TO FIX THIS ISSUE
                else if (charString.equalsIgnoreCase("All")) {
                    grtTableListFiltered = grtTableList;
                } else if (charString.equalsIgnoreCase("ASC")) {
                    grtTableListFiltered = grtTableList;
                } else if (charString.equalsIgnoreCase("DESC")) {
                    ArrayList<GRTTable> filteredList = new ArrayList<>();
                    for (int i = grtTableList.size(); i >= 1; i--) {
                        filteredList.add(grtTableListFiltered.get(i - 1));
                    }
                    grtTableListFiltered = filteredList;

                } else if (charString.equalsIgnoreCase("ASCENDING")) {

                    Collections.sort(grtTableList, new Comparator<GRTTable>() {
                        @Override
                        public int compare(GRTTable lhs, GRTTable rhs) {
                            return lhs.getCenterName().compareTo(rhs.getCenterName());
                        }
                    });

                } else if (charString.equalsIgnoreCase("DESCENDING")){
                    Collections.sort(grtTableList, new Comparator<GRTTable>() {
                        @Override
                        public int compare(GRTTable lhs, GRTTable rhs) {
                            return rhs.getCenterName().compareTo(lhs.getCenterName());
                        }
                    });

                } else {
                    ArrayList<GRTTable> filteredList = new ArrayList<>();
                    for (GRTTable grtTable : grtTableList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if ((!TextUtils.isEmpty(grtTable.getCenterId())
                                && grtTable.getCenterId().toLowerCase().contains(charString.toLowerCase()))
                                || (grtTable.getCenterName().toLowerCase().contains(charString.toLowerCase()))){
                            filteredList.add(grtTable);
                        }
                    }

                    grtTableListFiltered = filteredList;
                }*/

                FilterResults filterResults = new FilterResults();
                filterResults.values = grtTableListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                grtTableListFiltered = (List<List<GRTTable>>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public interface SyncCallbackInterface {
        void syncCallback(int position, List<GRTTable> grtTableList);

        void approveCallback(int position, List<GRTTable> grtTableList);

        void openCallback(int position, List<GRTTable> grtTableList);
    }

}
