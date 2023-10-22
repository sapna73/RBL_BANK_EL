package com.saartak.el.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.saartak.el.R;
import com.saartak.el.database.entity.CenterMeetingCollectionTable;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.util.List;

public class DigitalCollectionAdapter extends RecyclerView.Adapter<DigitalCollectionAdapter.ViewHolder> {

    private Context context;
    List<String> groupList;
    AppHelper appHelper;
    DynamicUIViewModel viewModel;
    String centerName;
    DigitalCollectionGroupAdapter digitalCollectionGroupAdapter;

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvGroupName, tvCenterName;
        AppCompatImageButton btnArrow;
        RecyclerView rvEligibilityGroup;
        LinearLayout llEligibilityGroup;

        public ViewHolder(View view) {
            super(view);
            tvGroupName = (TextView) view.findViewById(R.id.tv_group_name_value);
            tvCenterName = (TextView) view.findViewById(R.id.tv_center_name_value);
            btnArrow = (AppCompatImageButton) view.findViewById(R.id.btn_arrow);
            llEligibilityGroup = (LinearLayout) view.findViewById(R.id.ll_eligibility_group_value);

            rvEligibilityGroup = (RecyclerView) view.findViewById(R.id.rv_eligibility_group_value);
            rvEligibilityGroup.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true));
            rvEligibilityGroup.setNestedScrollingEnabled(false);

        }
    }

    public DigitalCollectionAdapter(Context context, List<String> groupList,
                                    AppHelper appHelper, DynamicUIViewModel viewModel, String centerName, boolean isEditable) {
        this.context = context;
        this.groupList = groupList;
        this.appHelper = appHelper;
        this.viewModel =viewModel;
        this.centerName=centerName;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.digital_collection_adapter_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            if (groupList != null && groupList.size() > 0) {
                String groupName = groupList.get(position);
                holder.tvGroupName.setText(groupName);

                    holder.tvCenterName.setText(centerName);

                    try {
                        viewModel.getMembersFromCMCollectionTableForDigitalCollectionGroupWise(groupName);
                        if (viewModel.getCenterMeetingCollectionTableLiveDataList() != null) {
                            Observer observer = new Observer() {
                                @Override
                                public void onChanged(@Nullable Object o) {
                                    List<CenterMeetingCollectionTable> centerMeetingCollectionTableList = (List<CenterMeetingCollectionTable>) o;
                                    viewModel.getCenterMeetingCollectionTableLiveDataList().removeObserver(this::onChanged);

                                    if(centerMeetingCollectionTableList !=null && centerMeetingCollectionTableList.size()>0){
                                            digitalCollectionGroupAdapter = new DigitalCollectionGroupAdapter(context,
                                                    centerMeetingCollectionTableList, appHelper,viewModel);

                                            holder.rvEligibilityGroup.setAdapter(digitalCollectionGroupAdapter);
                                            digitalCollectionGroupAdapter.notifyDataSetChanged();
                                    }
                                }
                            };
                            viewModel.getCenterMeetingCollectionTableLiveDataList().observe((LifecycleOwner)context, observer);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }



                holder.btnArrow.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                holder.llEligibilityGroup.setVisibility(View.VISIBLE);

                holder.btnArrow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (holder.llEligibilityGroup.getVisibility() == View.VISIBLE) {
                            holder.btnArrow.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                            holder.llEligibilityGroup.setVisibility(View.GONE);
                        } else {
                            holder.btnArrow.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                            holder.llEligibilityGroup.setVisibility(View.VISIBLE);
                        }
                    }
                });

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public List<String> getGroupList() {
        return groupList;
    }


    @Override
    public int getItemCount() {
        if (groupList != null) {

            return groupList.size();
        } else {
            return 0;
        }
    }

    public void setItem(List<String> groupList) {
        try {
            if (groupList != null && groupList.size() > 0) {

                this.groupList.clear();
                this.groupList = groupList;
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void clearItems() {
        try {
            if (groupList != null && groupList.size() > 0) {
                groupList.clear();
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
