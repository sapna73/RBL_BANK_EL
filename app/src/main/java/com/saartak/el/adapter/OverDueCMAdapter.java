package com.saartak.el.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.helpers.AppHelper;
import com.saartak.el.R;
import com.saartak.el.models.OverDueCMDTO;

import java.util.List;

public class OverDueCMAdapter extends RecyclerView.Adapter<OverDueCMAdapter.ViewHolder>  {

    private Context context;
     List<OverDueCMDTO> overDueCMDTOList;
    AppHelper appHelper;
    OverDueCMGroupAdapter overDueCMGroupAdapter;

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvCenterNameValue;
        AppCompatImageButton btnArrow;
        RecyclerView rvEligibilityGroup;
        LinearLayout llEligibilityGroup;

        public ViewHolder(View view) {
            super(view);
            tvCenterNameValue=(TextView)view.findViewById(R.id.tv_center_name_value);
            btnArrow=(AppCompatImageButton)view.findViewById(R.id.btn_arrow);
            llEligibilityGroup=(LinearLayout) view.findViewById(R.id.ll_eligibility_center_value);

            rvEligibilityGroup = (RecyclerView) view.findViewById(R.id.rv_eligibility_center_value);
            rvEligibilityGroup.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true));
            rvEligibilityGroup.setNestedScrollingEnabled(false);

        }
    }

    public OverDueCMAdapter(Context context, List<OverDueCMDTO> overDueCMDTOList, AppHelper appHelper) {
        this.context = context;
        this.overDueCMDTOList = overDueCMDTOList;
        this.appHelper = appHelper;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.over_due_cm_adapter_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            if (overDueCMDTOList != null && overDueCMDTOList.size() > 0) {
                OverDueCMDTO overDueCMDTO = overDueCMDTOList.get(position);
                holder.tvCenterNameValue.setText(overDueCMDTO.getCenterName());

                if(overDueCMDTO !=null && overDueCMDTO.getOverDueCMTableList()!=null &&
                          overDueCMDTO.getOverDueCMTableList().size()>0) {

                    overDueCMGroupAdapter =new OverDueCMGroupAdapter(context, overDueCMDTO.getOverDueCMTableList(),appHelper);

                    holder.rvEligibilityGroup.setAdapter(overDueCMGroupAdapter);
                    overDueCMGroupAdapter.notifyDataSetChanged();
                }


                holder.btnArrow.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                holder.llEligibilityGroup.setVisibility(View.GONE);

                holder.btnArrow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(holder.llEligibilityGroup.getVisibility()==View.VISIBLE){
                            holder.btnArrow.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                            holder.llEligibilityGroup.setVisibility(View.GONE);
                        }
                        else{
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


    @Override
    public int getItemCount() {
        if (overDueCMDTOList != null) {

            return overDueCMDTOList.size();
        } else {
            return 0;
        }
    }

    public void setItem( List<OverDueCMDTO> overDueCMDTOList) {
        try {
            if (overDueCMDTOList != null && overDueCMDTOList.size() > 0) {
                this.overDueCMDTOList.clear();
                this.overDueCMDTOList = overDueCMDTOList;
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void clearItems() {
        try {
            if (overDueCMDTOList != null && overDueCMDTOList.size() > 0) {
                overDueCMDTOList.clear();
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



}
