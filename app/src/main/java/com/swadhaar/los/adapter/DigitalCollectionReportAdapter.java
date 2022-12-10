package com.swadhaar.los.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.bfil.uilibrary.helpers.AppHelper;
import com.swadhaar.los.R;
import com.swadhaar.los.database.entity.InitiatePaymentStatusTable;
import com.swadhaar.los.models.EMIDetailsDTO;

import java.util.List;

public class DigitalCollectionReportAdapter extends RecyclerView.Adapter<DigitalCollectionReportAdapter.ViewHolder> {

    private Context context;
    List<InitiatePaymentStatusTable> initiatePaymentStatusTableList;
    AppHelper appHelper;

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvProdCode, tvLoanId, tvCustomerName, tvCenterName,tvCollectedAmt,tvPaymentStatus;

        public ViewHolder(View view) {
            super(view);
            tvProdCode = (TextView) view.findViewById(R.id.tv_loan_prod_code_value);
            tvLoanId = (TextView) view.findViewById(R.id.tv_loan_id_value);
            tvCustomerName = (TextView) view.findViewById(R.id.tv_customer_name);
            tvCenterName = (TextView) view.findViewById(R.id.tv_center_name);
            tvCollectedAmt = (TextView) view.findViewById(R.id.tv_collected_amt);
            tvPaymentStatus = (TextView) view.findViewById(R.id.tv_payment_status);
        }
    }

    public DigitalCollectionReportAdapter(Context context, List<InitiatePaymentStatusTable> initiatePaymentStatusTableList, AppHelper appHelper) {
        this.context = context;
        this.initiatePaymentStatusTableList = initiatePaymentStatusTableList;
        this.appHelper = appHelper;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.digital_collection_report_adapter_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            if (initiatePaymentStatusTableList != null && initiatePaymentStatusTableList.size() > 0) {
                InitiatePaymentStatusTable initiatePaymentStatusTable = initiatePaymentStatusTableList.get(position);

                if (initiatePaymentStatusTable != null) {
                    String productCode = "3138"; // TODO: HARDCODED
                    holder.tvProdCode.setText(productCode);
                    holder.tvLoanId.setText(initiatePaymentStatusTable.getLoanAccountNumber());
                    holder.tvCustomerName.setText(String.valueOf(initiatePaymentStatusTable.getCustomerName()));
                    holder.tvCenterName.setText(String.valueOf(initiatePaymentStatusTable.getCenterName()));
                    holder.tvCollectedAmt.setText(String.valueOf(initiatePaymentStatusTable.getDue_Amount()));
                    holder.tvPaymentStatus.setText(String.valueOf(initiatePaymentStatusTable.getPaymentStatus()));

//                    holder.tvPaymentStatus.setTextColor(context.getResources().getColor(R.color.light_green));
//                    holder.tvPaymentStatus.setTextColor(context.getResources().getColor(R.color.rbl_red));

                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public List<InitiatePaymentStatusTable> getInitiatePaymentStatusTableList() {
        return initiatePaymentStatusTableList;
    }


    @Override
    public int getItemCount() {
        if (initiatePaymentStatusTableList != null) {

            return initiatePaymentStatusTableList.size();
        } else {
            return 0;
        }
    }

    public void setItem(List<InitiatePaymentStatusTable> initiatePaymentStatusTableList) {
        try {
            if (initiatePaymentStatusTableList != null && initiatePaymentStatusTableList.size() > 0) {
                this.initiatePaymentStatusTableList.clear();
                this.initiatePaymentStatusTableList = initiatePaymentStatusTableList;
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void clearItems() {
        try {
            if (initiatePaymentStatusTableList != null && initiatePaymentStatusTableList.size() > 0) {
                initiatePaymentStatusTableList.clear();
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
