package com.swadhaar.los.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.helpers.AppHelper;
import com.swadhaar.los.R;
import com.swadhaar.los.database.entity.CenterMeetingCollectionTable;
import com.swadhaar.los.models.CashCollectionSummaryDTO;
import com.swadhaar.los.view_models.DynamicUIViewModel;

import java.util.List;

public class CenterMeetingCashCollectionSummaryAdapter extends RecyclerView.Adapter<CenterMeetingCashCollectionSummaryAdapter.ViewHolder> {

    private Context context;
    List<CashCollectionSummaryDTO.IndividualCenterCollection> individualCenterCollectionList;
    AppHelper appHelper;
    DynamicUIViewModel viewModel;
    boolean isEditable;
    CenterMeetingCollectionGroupAdapter centerMeetingCollectionGroupAdapter;

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvCenterName, tvCenterDue,tvCashCollection,tvSavingsCollection,tvTotalCollection;
        CheckBox cbConfirm;
        public ViewHolder(View view) {
            super(view);
            tvCenterName = (TextView) view.findViewById(R.id.tv_center_name_value);
            tvCenterDue = (TextView) view.findViewById(R.id.tv_center_due);
            tvCashCollection = (TextView) view.findViewById(R.id.tv_cash_collection);
            tvSavingsCollection = (TextView) view.findViewById(R.id.tv_savings_collection);
            tvTotalCollection = (TextView) view.findViewById(R.id.tv_total_collection);

            cbConfirm=(CheckBox) view.findViewById(R.id.cb_confirm);
        }
    }

    public CenterMeetingCashCollectionSummaryAdapter(Context context, List<CashCollectionSummaryDTO.IndividualCenterCollection> individualCenterCollectionList,
                                                     AppHelper appHelper,DynamicUIViewModel viewModel,boolean isEditable) {
        this.context = context;
        this.individualCenterCollectionList = individualCenterCollectionList;
        this.appHelper = appHelper;
        this.viewModel = viewModel;
        this.isEditable =isEditable;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.center_meeting_cash_collection_summary_adapter_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            if (individualCenterCollectionList != null && individualCenterCollectionList.size() > 0) {
                CashCollectionSummaryDTO.IndividualCenterCollection individualCenterCollection=individualCenterCollectionList.get(position);

                if(individualCenterCollection !=null){
                  holder.tvCenterName.setText(individualCenterCollection.getCenterName());
                  holder.tvCenterDue.setText(String.valueOf(individualCenterCollection.getCenterDue()));
                  holder.tvCashCollection.setText(String.valueOf(individualCenterCollection.getCenterCashCollection()));
                  holder.tvSavingsCollection.setText(String.valueOf(individualCenterCollection.getCenterSavingsCollection()));
                  holder.tvTotalCollection.setText(String.valueOf(individualCenterCollection.getCenterTotalCollection()));

                  holder.cbConfirm.setChecked(individualCenterCollection.isCenterConfirm());

//                  holder.cbConfirm.setChecked(isEditable);
                    if (individualCenterCollection.isCenterSync()){
                        holder.cbConfirm.setEnabled(false);

                        holder.tvCenterName.setTextColor(context.getResources().getColor(R.color.light_green));
                        holder.tvCenterDue.setTextColor(context.getResources().getColor(R.color.light_green));
                        holder.tvCashCollection.setTextColor(context.getResources().getColor(R.color.light_green));
                        holder.tvSavingsCollection.setTextColor(context.getResources().getColor(R.color.light_green));
                        holder.tvTotalCollection.setTextColor(context.getResources().getColor(R.color.light_green));
                    }else {
                        holder.cbConfirm.setEnabled(true);
                    }
//                  holder.cbConfirm.setEnabled(isEditable);

                  holder.cbConfirm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                      @Override
                      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                          individualCenterCollection.setCenterConfirm(isChecked);

                          // TODO: Update Cash Collection Summary
                          confirmCashCollectionSummary(individualCenterCollection);
                      }
                  });
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


    public List<CashCollectionSummaryDTO.IndividualCenterCollection> getIndividualCenterCollectionList() {
        return individualCenterCollectionList;
    }


    @Override
    public int getItemCount() {
        if (individualCenterCollectionList != null) {

            return individualCenterCollectionList.size();
        } else {
            return 0;
        }
    }

    public void setItem(List<CashCollectionSummaryDTO.IndividualCenterCollection> individualCenterCollectionList,boolean isEditable) {
        try {
            if (individualCenterCollectionList != null && individualCenterCollectionList.size() > 0) {

                this.isEditable=isEditable;

                this.individualCenterCollectionList.clear();
                this.individualCenterCollectionList = individualCenterCollectionList;
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void clearItems() {
        try {
            if (individualCenterCollectionList != null && individualCenterCollectionList.size() > 0) {
                individualCenterCollectionList.clear();
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
