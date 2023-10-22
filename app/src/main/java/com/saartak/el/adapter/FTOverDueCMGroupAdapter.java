package com.saartak.el.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.helpers.AppHelper;
import com.saartak.el.R;
import com.saartak.el.database.entity.FTOverDueCMTable;

import java.util.List;

public class FTOverDueCMGroupAdapter extends RecyclerView.Adapter<FTOverDueCMGroupAdapter.ViewHolder>  {

    private Context context;
    List<FTOverDueCMTable> overDueCMTableList;
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

    public FTOverDueCMGroupAdapter(Context context, List<FTOverDueCMTable> overDueCMTableList,
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
                FTOverDueCMTable overDueCMTable = overDueCMTableList.get(position);

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

    public void setItem(List<FTOverDueCMTable> overDueCMTableList) {
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
