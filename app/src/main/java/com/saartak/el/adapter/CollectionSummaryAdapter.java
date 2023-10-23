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
import com.saartak.el.activities.CollectionSummaryActivity;
import com.saartak.el.activities.LOSBaseActivity;
import com.saartak.el.database.entity.CollectionTable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.saartak.el.constants.AppConstant.MODULE_TYPE_COLLECTION;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_ID;
import static com.saartak.el.constants.AppConstant.PARAM_CLIENT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_NAME;
import static com.saartak.el.constants.AppConstant.REQUEST_CODE_UPDATE_LEAD;

public class CollectionSummaryAdapter extends RecyclerView.Adapter<CollectionSummaryAdapter.LoanTypeViewHolder> implements Filterable {
    private Context context;
    List<CollectionTable> collectionTableList;
    List<CollectionTable> collectionTableListFiltered;
    SyncCallbackInterface syncCallbackInterface;
    AppHelper appHelper;

    public CollectionSummaryAdapter(Context context, List<CollectionTable> collectionTableList,
                                    SyncCallbackInterface syncCallbackInterface, AppHelper appHelper) {
        this.context = context;
        this.collectionTableList = collectionTableList;
        this.collectionTableListFiltered = collectionTableList;
        this.syncCallbackInterface = syncCallbackInterface;
        this.appHelper = appHelper;
    }

    @NonNull
    @Override
    public LoanTypeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.collection_summary_row_item, viewGroup, false);
        return new LoanTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoanTypeViewHolder loanTypeViewHolder, int i) {
        try {
            if (collectionTableListFiltered != null && collectionTableListFiltered.size() > 0) {
                CollectionTable collectionTable = collectionTableListFiltered.get(i);
                loanTypeViewHolder.tvName.setText(collectionTable.getClientName());
                loanTypeViewHolder.tvloantype.setText(collectionTable.getLoanType());
                loanTypeViewHolder.tvclientid.setText(collectionTable.getClientId());

                if (collectionTable != null && collectionTable.isSync()) {
                    loanTypeViewHolder.ivStatus.setImageResource(R.drawable.ic_check_circle_black_24dp);
                    loanTypeViewHolder.llLeadDetails.setEnabled(true);
                    notifyDataSetChanged();

                } else {
                    loanTypeViewHolder.ivStatus.setImageResource(R.mipmap.sync_pd);
                    loanTypeViewHolder.llLeadDetails.setEnabled(true);
                    notifyDataSetChanged();
                }

                loanTypeViewHolder.llLeadDetails.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (collectionTable != null && !TextUtils.isEmpty(collectionTable.getClientId())) {
                            LOSBaseActivity.CLIENT_ID = collectionTable.getClientId();
                        }
                        CollectionSummaryActivity collectionSummaryActivity = (CollectionSummaryActivity) context;

                        Intent intentApplication = new Intent(context, BaseActivity.class);
                        intentApplication.putExtra(PARAM_LOAN_TYPE, collectionTable.getLoanType());
                        intentApplication.putExtra(PARAM_USER_NAME, collectionSummaryActivity.userName);
                        intentApplication.putExtra(PARAM_BRANCH_ID, collectionSummaryActivity.branchId);
                        intentApplication.putExtra(PARAM_BRANCH_GST_CODE, collectionSummaryActivity.branchGSTcode);
                        intentApplication.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_COLLECTION);
                        intentApplication.putExtra(PARAM_CLIENT_ID, LOSBaseActivity.CLIENT_ID);
                        intentApplication.putExtra(PARAM_USER_ID, collectionSummaryActivity.userId);
//                        context.startActivity(intentApplication);
                        collectionSummaryActivity.startActivityForResult(intentApplication, REQUEST_CODE_UPDATE_LEAD);
                    }
                });

                loanTypeViewHolder.ivStatus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO: No need to check this condition
                        if (appHelper.isNetworkAvailable()) {

                            if(!TextUtils.isEmpty(collectionTable.getTeleCallingStatus())){
                            appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons("Would You Like To sync data?",
                                    new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {
                                            if (collectionTable != null && !collectionTable.isSync()) {
                                                syncCallbackInterface.syncCallback(i, collectionTable);
                                            } else {
                                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Data already synced ");
                                            }
                                        }
                                    });

                        }else {
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Please update Telecalling Status");

                            }

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
        if (collectionTableListFiltered != null) {
            return collectionTableListFiltered.size();
        } else {
            return 0;
        }
    }

    public void setItem(List<CollectionTable> collectionTableList) {
        try {
            if (this.collectionTableList != null && this.collectionTableList.size() > 0) {
                this.collectionTableList.clear();
                this.collectionTableListFiltered.clear();
                this.collectionTableList = collectionTableList;
                this.collectionTableListFiltered = collectionTableList;
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void clearItems() {
        try {
            if (collectionTableList != null && collectionTableList.size() > 0) {
                collectionTableList.clear();
                collectionTableListFiltered.clear();
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
        LinearLayout llLeadDetails;

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
            cvLead = (CardView) itemView.findViewById(R.id.cv_lead);
           }
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    collectionTableListFiltered = collectionTableList;
                }else if (charString.equalsIgnoreCase("All")) {
                    collectionTableListFiltered = collectionTableList;
                } else if (charString.equalsIgnoreCase("ASC")) {
                    collectionTableListFiltered = collectionTableList;
                } else if (charString.equalsIgnoreCase("DESC")) {
                    List<CollectionTable> filteredList = new ArrayList<>();
                    for (int i = collectionTableList.size(); i >= 1; i--) {
                        filteredList.add(collectionTableList.get(i - 1));
                    }
                    collectionTableListFiltered = filteredList;

                }  else if (charString.equalsIgnoreCase("ASCENDING")) {

                    Collections.sort(collectionTableListFiltered, new Comparator<CollectionTable>() {
                        @Override
                        public int compare(CollectionTable lhs, CollectionTable rhs) {
                            return lhs.getClientName().compareTo(rhs.getClientName());
                        }
                    });

                } else if (charString.equalsIgnoreCase("DESCENDING")){
                    Collections.sort(collectionTableListFiltered, new Comparator<CollectionTable>() {
                        @Override
                        public int compare(CollectionTable lhs, CollectionTable rhs) {
                            return rhs.getClientName().compareTo(lhs.getClientName());
                        }
                    });

                }
                // TODO: ALL STATIC DATA ... NEED TO FIX THIS ISSUE
              else {
                    ArrayList<CollectionTable> filteredList = new ArrayList<>();
                    for (CollectionTable CollectionTable : collectionTableList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (CollectionTable.getApplicantMobileNo().toLowerCase().contains(charString.toLowerCase())
                                || CollectionTable.getClientName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(CollectionTable);
                        }
                    }

                    collectionTableListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = collectionTableListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                collectionTableListFiltered = (List<CollectionTable>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public interface SyncCallbackInterface {
        void syncCallback(int position, CollectionTable CollectionTable);

    }
}
