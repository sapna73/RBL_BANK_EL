package com.saartak.el.adapter;

import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_ID;
import static com.saartak.el.constants.AppConstant.PARAM_CLIENT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
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
import com.saartak.el.activities.LeadToOtherThenLOPricingActivity;
import com.saartak.el.models.GetPricingInbox.GetPricingInboxResponseTable;

import java.util.ArrayList;
import java.util.List;

public class OtherThenLOLeadPricingAdapter extends RecyclerView.Adapter<OtherThenLOLeadPricingAdapter.LoanTypeViewHolder> implements Filterable {

    private Context context;
    List<GetPricingInboxResponseTable> masterTableList;
    List<GetPricingInboxResponseTable> masterTableListFiltered;
    OtherThenLOLeadPricingAdapter.SyncCallbackInterface syncCallbackInterface;
    AppHelper appHelper;

    public OtherThenLOLeadPricingAdapter(Context context, List<GetPricingInboxResponseTable> masterTableList,
                                         OtherThenLOLeadPricingAdapter.SyncCallbackInterface syncCallbackInterface, AppHelper appHelper) {
        this.context = context;
        this.masterTableList = masterTableList;
        this.masterTableListFiltered = masterTableList;
        this.syncCallbackInterface = syncCallbackInterface;
        this.appHelper = appHelper;
    }

    @NonNull
    @Override
    public OtherThenLOLeadPricingAdapter.LoanTypeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lead_to_pricing_row_item, viewGroup, false);
        return new OtherThenLOLeadPricingAdapter.LoanTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OtherThenLOLeadPricingAdapter.LoanTypeViewHolder loanTypeViewHolder, int i) {
        try {
            if (masterTableListFiltered != null && masterTableListFiltered.size() > 0) {
                GetPricingInboxResponseTable masterTable = masterTableListFiltered.get(i);
                loanTypeViewHolder.tvName.setText(masterTable.getCustomerName());
                if(masterTable.getProductId().equalsIgnoreCase("26")){
                    loanTypeViewHolder.tvloantype.setText("EL");
                }else if(masterTable.getProductId().equalsIgnoreCase("25")){
                    loanTypeViewHolder.tvloantype.setText("TWL");
                }
                loanTypeViewHolder.tvclientid.setText(masterTable.getCustomerId());

                loanTypeViewHolder.btnPricIng.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent application = new Intent(context, LOPricingActivity.class);
                        LeadToOtherThenLOPricingActivity leadToOtherThenLOPricingActivity = (LeadToOtherThenLOPricingActivity) context;
                        application.putExtra(PARAM_LOAN_TYPE, leadToOtherThenLOPricingActivity.loanType);
                        application.putExtra(PARAM_USER_NAME, leadToOtherThenLOPricingActivity.userName);
                        application.putExtra(PARAM_BRANCH_ID, leadToOtherThenLOPricingActivity.branchId);
                        application.putExtra(PARAM_BRANCH_GST_CODE, leadToOtherThenLOPricingActivity.branchGSTcode);
                        application.putExtra(PARAM_USER_ID, masterTable.getCreatedBy());
                        application.putExtra(PARAM_CLIENT_ID, masterTable.getCustomerId());
                        application.putExtra(PARAM_PRODUCT_ID, leadToOtherThenLOPricingActivity.productId);
                        application.putExtra(PARAM_ROLE_NAME, leadToOtherThenLOPricingActivity.roleName);
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

    public void setItem(List<GetPricingInboxResponseTable> masterTableList) {
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
        TextView tvName, tvloantype, tvamount, tvclientid, tvCurrentStage, tv_VkycStageValue;
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
                } else {
                    ArrayList<GetPricingInboxResponseTable> filteredList = new ArrayList<>();
                    for (GetPricingInboxResponseTable masterTable : masterTableList) {
                        if (masterTable != null && !TextUtils.isEmpty(masterTable.getCustomerName()) &&
                                masterTable.getCustomerName().toLowerCase().contains(charString.toLowerCase())) {
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
                masterTableListFiltered = (List<GetPricingInboxResponseTable>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public interface SyncCallbackInterface {

    }

}
