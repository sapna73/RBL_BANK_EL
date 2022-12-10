package com.swadhaar.los.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.helpers.AppHelper;
import com.swadhaar.los.R;
import com.swadhaar.los.database.entity.TodayCollectionScheduledTable;
import com.swadhaar.los.models.CashCollectionSummaryDTO;
import com.swadhaar.los.view_models.DynamicUIViewModel;

import java.util.List;

public class OtherDayCollectionScheduledAdapter extends RecyclerView.Adapter<OtherDayCollectionScheduledAdapter.ViewHolder> {

    private Context context;
    List<TodayCollectionScheduledTable> todayCollectionScheduledTableList;
    AppHelper appHelper;
    DynamicUIViewModel viewModel;
    boolean isEditable;
    CenterMeetingCollectionGroupAdapter centerMeetingCollectionGroupAdapter;

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvCenterName, tvCMDate,tvCMTime,tvTotalDue,tvMembersCount,tvCollectionStatus,tvCollectionAmount;
        CheckBox cbConfirm;
        public ViewHolder(View view) {
            super(view);
            tvCenterName = (TextView) view.findViewById(R.id.tv_center_name_value);
            tvCMDate = (TextView) view.findViewById(R.id.tv_cm_date);
            tvCMTime = (TextView) view.findViewById(R.id.tv_cm_time);
            tvTotalDue = (TextView) view.findViewById(R.id.tv_total_due);
            tvMembersCount = (TextView) view.findViewById(R.id.tv_members_count);
            tvCollectionStatus = (TextView) view.findViewById(R.id.tv_collection_status);
            tvCollectionAmount = (TextView) view.findViewById(R.id.tv_collection_amount);

            cbConfirm=(CheckBox) view.findViewById(R.id.cb_confirm);
        }
    }

    public OtherDayCollectionScheduledAdapter(Context context, List<TodayCollectionScheduledTable> individualCenterCollectionList,
                                              AppHelper appHelper, DynamicUIViewModel viewModel, boolean isEditable) {
        this.context = context;
        this.todayCollectionScheduledTableList = individualCenterCollectionList;
        this.appHelper = appHelper;
        this.viewModel = viewModel;
        this.isEditable =isEditable;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.today_collection_scheduled_adapter_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            if (todayCollectionScheduledTableList != null && todayCollectionScheduledTableList.size() > 0) {
                TodayCollectionScheduledTable todayCollectionScheduledTable= todayCollectionScheduledTableList.get(position);

                if(todayCollectionScheduledTable !=null){
                  holder.tvCenterName.setText(todayCollectionScheduledTable.getCenterName());
                  holder.tvCMDate.setText(todayCollectionScheduledTable.getCenterMeetingDate());
                    holder.tvCMTime.setText(todayCollectionScheduledTable.getCenterMeetingTime());
                    holder.tvMembersCount.setText(todayCollectionScheduledTable.getMembersCount());
                  holder.tvTotalDue.setText(String.valueOf(todayCollectionScheduledTable.getTotal_Due()));

                    holder.tvCollectionAmount.setText(String.valueOf(todayCollectionScheduledTable.getCollectedAmount()));
                    if (!TextUtils.isEmpty(todayCollectionScheduledTable.getStatus()) && todayCollectionScheduledTable.getStatus().equalsIgnoreCase("Uploaded")){
                        holder.tvCollectionStatus.setText(todayCollectionScheduledTable.getStatus());
                        holder.tvCollectionStatus.setTextColor(context.getResources().getColor(R.color.light_green));
                    }else if (!TextUtils.isEmpty(todayCollectionScheduledTable.getStatus()) && todayCollectionScheduledTable.getStatus().equalsIgnoreCase("Saved")){
                        holder.tvCollectionStatus.setText(todayCollectionScheduledTable.getStatus());
                        holder.tvCollectionStatus.setTextColor(context.getResources().getColor(R.color.rfs_orange));
                    }else {
                        holder.tvCollectionStatus.setText(todayCollectionScheduledTable.getStatus());
                        holder.tvCollectionStatus.setTextColor(context.getResources().getColor(R.color.rbl_red));
                    }

//                  holder.cbConfirm.setChecked(individualCenterCollection.isCenterConfirm());

//                  holder.cbConfirm.setChecked(isEditable);
                  holder.cbConfirm.setEnabled(isEditable);

//                  holder.cbConfirm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                      @Override
//                      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                          individualCenterCollection.setCenterConfirm(isChecked);
//
//                          // TODO: Update Cash Collection Summary
//                          confirmCashCollectionSummary(individualCenterCollection);
//                      }
//                  });
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void confirmCashCollectionSummary(CashCollectionSummaryDTO.IndividualCenterCollection individualCenterCollection) {
        try{
            viewModel.confirmCashCollectionSummary(individualCenterCollection);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }


    public List<TodayCollectionScheduledTable> getTodayCollectionScheduledTableList() {
        return todayCollectionScheduledTableList;
    }


    @Override
    public int getItemCount() {
        if (todayCollectionScheduledTableList != null) {

            return todayCollectionScheduledTableList.size();
        } else {
            return 0;
        }
    }

    public void setItem(List<TodayCollectionScheduledTable> todayCollectionScheduledTableList, boolean isEditable) {
        try {
            if (todayCollectionScheduledTableList != null && todayCollectionScheduledTableList.size() > 0) {

                this.isEditable=isEditable;

                this.todayCollectionScheduledTableList.clear();
                this.todayCollectionScheduledTableList = todayCollectionScheduledTableList;
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void clearItems() {
        try {
            if (todayCollectionScheduledTableList != null && todayCollectionScheduledTableList.size() > 0) {
                todayCollectionScheduledTableList.clear();
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
