package com.swadhaar.los.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.bfil.uilibrary.helpers.AppHelper;
import com.swadhaar.los.R;
import com.swadhaar.los.database.entity.CenterMeetingAttendanceTable;
import com.swadhaar.los.database.entity.CenterMeetingAttendanceTable;

import java.util.List;

public class CenterMeetingAttendanceGroupAdapter extends RecyclerView.Adapter<CenterMeetingAttendanceGroupAdapter.ViewHolder>  {

    private Context context;
    List<CenterMeetingAttendanceTable> centerMeetingAttendanceTableList;
    AppHelper appHelper;

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvClientName,tvClientId;
        CheckBox cbMemberAttendance;
        Spinner spAbscentReason;
        RelativeLayout rlReason;

        public ViewHolder(View view) {
            super(view);
            tvClientName=(TextView)view.findViewById(R.id.tv_client_name);
            tvClientId=(TextView)view.findViewById(R.id.tv_client_id);
            cbMemberAttendance=(CheckBox) view.findViewById(R.id.cb_member_attendance);
            spAbscentReason=(Spinner) view.findViewById(R.id.sp_abscent_reason);
            rlReason=(RelativeLayout) view.findViewById(R.id.rl_reason);

        }
    }

    public CenterMeetingAttendanceGroupAdapter(Context context, List<CenterMeetingAttendanceTable> centerMeetingAttendanceTableList, AppHelper appHelper) {
        this.context = context;
        this.centerMeetingAttendanceTableList = centerMeetingAttendanceTableList;
        this.appHelper = appHelper;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.center_meeting_attendance_group_adapter_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            if (centerMeetingAttendanceTableList != null && centerMeetingAttendanceTableList.size() > 0) {
                CenterMeetingAttendanceTable centerMeetingAttendanceTable = centerMeetingAttendanceTableList.get(position);

                if(centerMeetingAttendanceTable !=null ) {

                    holder.tvClientName.setText(centerMeetingAttendanceTable.getCustomerName());
                    holder.tvClientId.setText(centerMeetingAttendanceTable.getCustomerID());
                    holder.cbMemberAttendance.setChecked(centerMeetingAttendanceTable.isAttentance());

                    if (centerMeetingAttendanceTable.isSync()) {
                        holder.cbMemberAttendance.setEnabled(false);
                        holder.spAbscentReason.setEnabled(false);
                    }else {
                        holder.cbMemberAttendance.setEnabled(true);
                        holder.spAbscentReason.setEnabled(true);
                    }

                    if(centerMeetingAttendanceTable.isAttentance()){
                        holder.rlReason.setVisibility(View.GONE);
                        centerMeetingAttendanceTable.setReason("");
                    }else{
                        holder.rlReason.setVisibility(View.VISIBLE);
                    }

                    if( holder.rlReason.getVisibility()== View.VISIBLE &&  ! TextUtils.isEmpty(centerMeetingAttendanceTable.getReason())) {
                        holder.spAbscentReason.setSelection(appHelper.getSpinnerPosition(holder.spAbscentReason, centerMeetingAttendanceTable.getReason()));
                    }

                    holder.cbMemberAttendance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            centerMeetingAttendanceTable.setAttentance(isChecked);
                            centerMeetingAttendanceTable.setReason("");

                            if(isChecked) {
                                holder.rlReason.setVisibility(View.GONE);
                            }
                            else {
                                holder.rlReason.setVisibility(View.VISIBLE);
                            }
                        }
                    });

                    holder.spAbscentReason.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if(position != -1) {
                                if(position == 0) {
                                    centerMeetingAttendanceTable.setReason("");
                                }else {
                                    centerMeetingAttendanceTable.setReason(holder.spAbscentReason.getSelectedItem().toString());
                                }
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public List<CenterMeetingAttendanceTable> getListOfEligibilityTableList() {
        return centerMeetingAttendanceTableList;
    }


    @Override
    public int getItemCount() {
        if (centerMeetingAttendanceTableList != null) {

            return centerMeetingAttendanceTableList.size();
        } else {
            return 0;
        }
    }

    public void setItem(List<CenterMeetingAttendanceTable> centerMeetingAttendanceTableList) {
        try {
            if (centerMeetingAttendanceTableList != null && centerMeetingAttendanceTableList.size() > 0) {
                this.centerMeetingAttendanceTableList.clear();
                this.centerMeetingAttendanceTableList = centerMeetingAttendanceTableList;
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void clearItems() {
        try {
            if (centerMeetingAttendanceTableList != null && centerMeetingAttendanceTableList.size() > 0) {
                centerMeetingAttendanceTableList.clear();
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



}
