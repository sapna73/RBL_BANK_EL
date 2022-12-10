package com.swadhaar.los.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.bfil.uilibrary.helpers.AppHelper;
import com.swadhaar.los.R;
import com.swadhaar.los.activities.DigitalCollectionActivity;
import com.swadhaar.los.activities.DigitalCollectionEMIDetailsActivity;
import com.swadhaar.los.database.entity.CenterMeetingCollectionTable;
import com.swadhaar.los.database.entity.OverDueCMTable;
import com.swadhaar.los.view_models.DynamicUIViewModel;

import java.util.List;

import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_COLLECTION;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_CENTER_NAME;
import static com.swadhaar.los.constants.AppConstant.PARAM_CLIENT_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_NAME;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_PLEASE_INITIATE_TRANSACTION_AND_CONFIRM;

public class OverDueCMGroupAdapter extends RecyclerView.Adapter<OverDueCMGroupAdapter.ViewHolder>  {

    private Context context;
    List<OverDueCMTable> overDueCMTableList;
    AppHelper appHelper;
    boolean isEditable;

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvClientName,tvClientId,tvCmDate,tvTotalDue;

        public ViewHolder(View view) {
            super(view);
            tvClientName=(TextView)view.findViewById(R.id.tv_client_name);
            tvClientId=(TextView)view.findViewById(R.id.tv_client_id);
            tvCmDate=(TextView)view.findViewById(R.id.tv_cm_date);
            tvTotalDue=(TextView)view.findViewById(R.id.tv_total_due);
        }
    }

    public OverDueCMGroupAdapter(Context context, List<OverDueCMTable> overDueCMTableList,
                                 AppHelper appHelper) {
        this.context = context;
        this.overDueCMTableList = overDueCMTableList;
        this.appHelper = appHelper;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.over_due_cm_group_adapter_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            if (overDueCMTableList != null && overDueCMTableList.size() > 0) {
                OverDueCMTable overDueCMTable = overDueCMTableList.get(position);

                if(overDueCMTable !=null ) {
                    holder.tvClientName.setText(overDueCMTable.getCustomerName());
//                    holder.tvClientId.setText(overDueCMTable.getCustomerId());
                    holder.tvCmDate.setText(String.valueOf(overDueCMTable.getCenterMeetingDate()));
                    holder.tvTotalDue.setText(String.valueOf(overDueCMTable.getTotal_Due()));
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        if (overDueCMTableList != null) {

            return overDueCMTableList.size();
        } else {
            return 0;
        }
    }

    public void setItem(List<OverDueCMTable> overDueCMTableList) {
        try {
            if (overDueCMTableList != null && overDueCMTableList.size() > 0) {
                this.overDueCMTableList.clear();
                this.overDueCMTableList = overDueCMTableList;
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void clearItems() {
        try {
            if (overDueCMTableList != null && overDueCMTableList.size() > 0) {
                overDueCMTableList.clear();
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
