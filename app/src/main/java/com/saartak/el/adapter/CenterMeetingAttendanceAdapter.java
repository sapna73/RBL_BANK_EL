package com.saartak.el.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.helpers.AppHelper;
import com.saartak.el.R;
import com.saartak.el.database.entity.CenterMeetingAttendanceTable;
import com.saartak.el.models.CenterMeetingAttendanceDTO;

import java.util.List;

public class CenterMeetingAttendanceAdapter extends RecyclerView.Adapter<CenterMeetingAttendanceAdapter.ViewHolder>  {

    private Context context;
     List<CenterMeetingAttendanceDTO> centerMeetingAttendanceDTOList;
    AppHelper appHelper;
    CenterMeetingAttendanceGroupAdapter centerMeetingAttendanceGroupAdapter;

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvGroupCode;
        AppCompatImageButton btnArrow;
        RecyclerView rvEligibilityGroup;
        LinearLayout llEligibilityGroup;
        CheckBox cbGroupAttendance;

        public ViewHolder(View view) {
            super(view);
            tvGroupCode=(TextView)view.findViewById(R.id.tv_group_code_value);
            btnArrow=(AppCompatImageButton)view.findViewById(R.id.btn_arrow);
            llEligibilityGroup=(LinearLayout) view.findViewById(R.id.ll_eligibility_group_value);
            cbGroupAttendance=(CheckBox) view.findViewById(R.id.cb_group_attendance);

            rvEligibilityGroup = (RecyclerView) view.findViewById(R.id.rv_eligibility_group_value);
            rvEligibilityGroup.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true));
            rvEligibilityGroup.setNestedScrollingEnabled(false);

        }
    }

    public CenterMeetingAttendanceAdapter(Context context, List<CenterMeetingAttendanceDTO> centerMeetingAttendanceDTOList, AppHelper appHelper) {
        this.context = context;
        this.centerMeetingAttendanceDTOList = centerMeetingAttendanceDTOList;
        this.appHelper = appHelper;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.center_meeting_attendance_adapter_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            if (centerMeetingAttendanceDTOList != null && centerMeetingAttendanceDTOList.size() > 0) {
                CenterMeetingAttendanceDTO centerMeetingAttendanceDTO = centerMeetingAttendanceDTOList.get(position);
                holder.tvGroupCode.setText(centerMeetingAttendanceDTO.getGroupName());

                if(centerMeetingAttendanceDTO !=null && centerMeetingAttendanceDTO.getCenterMeetingAttendanceTableList()!=null &&
                          centerMeetingAttendanceDTO.getCenterMeetingAttendanceTableList().size()>0) {

                    centerMeetingAttendanceGroupAdapter =new CenterMeetingAttendanceGroupAdapter(context, centerMeetingAttendanceDTO.getCenterMeetingAttendanceTableList(),appHelper);

                    holder.rvEligibilityGroup.setAdapter(centerMeetingAttendanceGroupAdapter);
                    centerMeetingAttendanceGroupAdapter.notifyDataSetChanged();

                    boolean allAttendanceMarked=true;
                    boolean allSyncMarked=true;
                    for(CenterMeetingAttendanceTable centerMeetingAttendanceTable : centerMeetingAttendanceDTO.getCenterMeetingAttendanceTableList()){
                        if( ! centerMeetingAttendanceTable.isAttentance()){
                            allAttendanceMarked=false;
                            break;
                        }
                    }
                    for(CenterMeetingAttendanceTable centerMeetingAttendanceTable : centerMeetingAttendanceDTO.getCenterMeetingAttendanceTableList()){
                        if( ! centerMeetingAttendanceTable.isSync()){
                            allSyncMarked=false;
                            break;
                        }
                    }

                    if(allAttendanceMarked){
                        holder.cbGroupAttendance.setChecked(true);
                    }else{
                        holder.cbGroupAttendance.setChecked(false);
                    }

                    if(allSyncMarked){
                        holder.cbGroupAttendance.setEnabled(false);
                    }else{
                        holder.cbGroupAttendance.setEnabled(true);
                    }
                }


                holder.btnArrow.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                holder.llEligibilityGroup.setVisibility(View.GONE);

//                if(holder.llEligibilityGroup.getVisibility()==View.VISIBLE){
//                   holder.btnArrow.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
//                }
//                else{
//                    holder.btnArrow.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
//                }

                holder.cbGroupAttendance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(centerMeetingAttendanceDTO !=null && centerMeetingAttendanceDTO.getCenterMeetingAttendanceTableList()!=null &&
                                centerMeetingAttendanceDTO.getCenterMeetingAttendanceTableList().size()>0) {

                            for(CenterMeetingAttendanceTable centerMeetingAttendanceTable : centerMeetingAttendanceDTO.getCenterMeetingAttendanceTableList()){
                                centerMeetingAttendanceTable.setAttentance(isChecked);
                            }


                            centerMeetingAttendanceGroupAdapter =new CenterMeetingAttendanceGroupAdapter(context, centerMeetingAttendanceDTO.getCenterMeetingAttendanceTableList(),appHelper);

                            holder.rvEligibilityGroup.setAdapter(centerMeetingAttendanceGroupAdapter);
                            centerMeetingAttendanceGroupAdapter.notifyDataSetChanged();

                        }
                    }
                });

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


    public  List<CenterMeetingAttendanceDTO> getCenterMeetingAttendanceDTOList() {
        return centerMeetingAttendanceDTOList;
    }


    @Override
    public int getItemCount() {
        if (centerMeetingAttendanceDTOList != null) {

            return centerMeetingAttendanceDTOList.size();
        } else {
            return 0;
        }
    }

    public void setItem( List<CenterMeetingAttendanceDTO> centerMeetingAttendanceDTOList) {
        try {
            if (centerMeetingAttendanceDTOList != null && centerMeetingAttendanceDTOList.size() > 0) {
                this.centerMeetingAttendanceDTOList.clear();
                this.centerMeetingAttendanceDTOList = centerMeetingAttendanceDTOList;
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void clearItems() {
        try {
            if (centerMeetingAttendanceDTOList != null && centerMeetingAttendanceDTOList.size() > 0) {
                centerMeetingAttendanceDTOList.clear();
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



}
