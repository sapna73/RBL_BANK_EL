package com.saartak.el.adapter;

import static com.saartak.el.constants.AppConstant.LOAN_NAME_INDIVIDUAL;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_APPLICATION;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_ID;
import static com.saartak.el.constants.AppConstant.PARAM_CLIENT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_ROLE_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_NAME;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.helpers.AppHelper;
import com.saartak.el.R;
import com.saartak.el.activities.LOPricingActivity;
import com.saartak.el.activities.LeadToPricingActivity;
import com.saartak.el.database.entity.MasterTable;
import com.saartak.el.models.CibilUpStreamDownStreamModel;

import java.util.ArrayList;
import java.util.List;

public class LeadToPricingAdapter extends RecyclerView.Adapter<LeadToPricingAdapter.LoanTypeViewHolder> implements Filterable {

    private Context context;
    List<MasterTable> masterTableList;
    List<MasterTable> masterTableListFiltered;
    LeadToPricingAdapter.SyncCallbackInterface syncCallbackInterface;
    AppHelper appHelper;
    CibilUpStreamDownStreamModel cibilUpStreamDownStreamModelList;

    public LeadToPricingAdapter(Context context, List<MasterTable> masterTableList,
                                    LeadToPricingAdapter.SyncCallbackInterface syncCallbackInterface, AppHelper appHelper) {
        this.context = context;
        this.masterTableList = masterTableList;
        this.masterTableListFiltered = masterTableList;
        this.syncCallbackInterface = syncCallbackInterface;
        this.appHelper = appHelper;
    }

    @NonNull
    @Override
    public LeadToPricingAdapter.LoanTypeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lead_to_pricing_row_item, viewGroup, false);
        return new LeadToPricingAdapter.LoanTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeadToPricingAdapter.LoanTypeViewHolder loanTypeViewHolder, int i) {
        try {
            if (masterTableListFiltered != null && masterTableListFiltered.size() > 0) {
                MasterTable masterTable = masterTableListFiltered.get(i);
                loanTypeViewHolder.tvName.setText(masterTable.getClientName());
                loanTypeViewHolder.tvloantype.setText(masterTable.getLoan_type());
                loanTypeViewHolder.tvamount.setText(masterTable.getLoan_amount());
                loanTypeViewHolder.tvclientid.setText(masterTable.getClientId());
                loanTypeViewHolder.tvCurrentStage.setText(masterTable.getCurrentStage());

                loanTypeViewHolder.btnPricIng.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent application = new Intent(context, LOPricingActivity.class);
                        LeadToPricingActivity leadToPricingActivity = (LeadToPricingActivity) context;
                        application.putExtra(PARAM_LOAN_TYPE, leadToPricingActivity.loanType);
                        if (!TextUtils.isEmpty(masterTable.getLoan_type())
                                && masterTable.getLoan_type().equalsIgnoreCase(LOAN_NAME_INDIVIDUAL)) {
                            application.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_APPLICATION);
                        }
                        application.putExtra(PARAM_USER_NAME, masterTable.getClientName());
                        application.putExtra(PARAM_BRANCH_ID, leadToPricingActivity.branchId);
                        application.putExtra(PARAM_BRANCH_GST_CODE, leadToPricingActivity.branchGSTcode);
                        application.putExtra(PARAM_USER_ID, masterTable.getCreatedBy());
                        application.putExtra(PARAM_CLIENT_ID, masterTable.getClientId());
                        application.putExtra(PARAM_PRODUCT_ID, leadToPricingActivity.productId);
                        application.putExtra(PARAM_ROLE_NAME, leadToPricingActivity.roleName);
                        context.startActivity(application);
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
        TextView tvName, tvloantype, tvamount, tvclientid,tvCurrentStage,tv_VkycStageValue;
        CardView cvLead;
        LinearLayout llLeadDetails, llApproveOrReject;
        public Button btnPricIng, btnReject;

        public LoanTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_lead_name_value);
            tvloantype = (TextView) itemView.findViewById(R.id.tv_lead_loantype_value);
            tvamount = (TextView) itemView.findViewById(R.id.tv_lead_amount_value);
            tvclientid = (TextView) itemView.findViewById(R.id.tv_lead_clientid_value);
            tvCurrentStage = (TextView) itemView.findViewById(R.id.tv_current_stage_value);
            llLeadDetails = (LinearLayout) itemView.findViewById(R.id.ll_lead_summary_values);
            llApproveOrReject = (LinearLayout) itemView.findViewById(R.id.ll_approve_or_reject);
            cvLead = (CardView) itemView.findViewById(R.id.cv_lead);
            btnPricIng = (Button) itemView.findViewById(R.id.btnPricIng);
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
                else if (charString.equalsIgnoreCase("Interested")) {
                    masterTableListFiltered = masterTableList;
                } else if (charString.equalsIgnoreCase("Not Interested")) {
                    masterTableListFiltered = masterTableList;
                } else if (charString.equalsIgnoreCase("Affordable Housing Loan")) {
                    ArrayList<MasterTable> filteredList = new ArrayList<>();
                    for (MasterTable masterTable : masterTableList) {
                        if (masterTable.getLoan_type().equalsIgnoreCase("Affordable Housing Loan")) {
                            filteredList.add(masterTable);
                        }
                    }
                    masterTableListFiltered = filteredList;
                } else if (charString.equalsIgnoreCase("Consumer Durable Loan")) {
                    ArrayList<MasterTable> filteredList = new ArrayList<>();
                    for (MasterTable masterTable : masterTableList) {
                        if (masterTable.getLoan_type().equalsIgnoreCase("Consumer Durable Loan")) {
                            filteredList.add(masterTable);
                        }
                    }
                    masterTableListFiltered = filteredList;
                } else if (charString.equalsIgnoreCase("Individual Loan")) {
                    ArrayList<MasterTable> filteredList = new ArrayList<>();
                    for (MasterTable masterTable : masterTableList) {
                        if (masterTable.getLoan_type().equalsIgnoreCase("Individual Loan")) {
                            filteredList.add(masterTable);
                        }
                    }
                    masterTableListFiltered = filteredList;
                } else if (charString.equalsIgnoreCase("JLG")) {
                    ArrayList<MasterTable> filteredList = new ArrayList<>();
                    for (MasterTable masterTable : masterTableList) {
                        if (masterTable.getLoan_type().equalsIgnoreCase("JLG")) {
                            filteredList.add(masterTable);
                        }
                    }
                    masterTableListFiltered = filteredList;
                } else if (charString.equalsIgnoreCase("Loan against property")) {
                    ArrayList<MasterTable> filteredList = new ArrayList<>();
                    for (MasterTable masterTable : masterTableList) {
                        if (masterTable.getLoan_type().equalsIgnoreCase("Loan against property")) {
                            filteredList.add(masterTable);
                        }
                    }
                    masterTableListFiltered = filteredList;
                } else if (charString.equalsIgnoreCase("Two Wheeler")) {
                    ArrayList<MasterTable> filteredList = new ArrayList<>();
                    for (MasterTable masterTable : masterTableList) {
                        if (masterTable.getLoan_type().equalsIgnoreCase("Two Wheeler")) {
                            filteredList.add(masterTable);
                        }
                    }
                    masterTableListFiltered = filteredList;
                } else if (charString.equalsIgnoreCase("MSME/Business loan")) {
                    ArrayList<MasterTable> filteredList = new ArrayList<>();
                    for (MasterTable masterTable : masterTableList) {
                        if (masterTable.getLoan_type().equalsIgnoreCase("MSME/Business loan")) {
                            filteredList.add(masterTable);
                        }
                    }
                    masterTableListFiltered = filteredList;
                } else if (charString.equalsIgnoreCase("All")) {
                    masterTableListFiltered = masterTableList;
                } else if (charString.equalsIgnoreCase("ASC")) {
                    masterTableListFiltered = masterTableList;
                } else if (charString.equalsIgnoreCase("DESC")) {
                    ArrayList<MasterTable> filteredList = new ArrayList<>();
                    for (int i = masterTableList.size(); i >= 1; i--) {
                        filteredList.add(masterTableListFiltered.get(i - 1));
                    }
                    masterTableListFiltered = filteredList;

                } else {
                    ArrayList<MasterTable> filteredList = new ArrayList<>();
                    for (MasterTable masterTable : masterTableList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match

                        if (masterTable !=null && ! TextUtils.isEmpty(masterTable.getClientName()) &&
                                masterTable.getClientName().toLowerCase().contains(charString.toLowerCase())) {

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

    }

}
