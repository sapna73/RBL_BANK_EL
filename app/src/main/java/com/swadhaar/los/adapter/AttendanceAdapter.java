package com.swadhaar.los.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.helpers.AppHelper;
import com.swadhaar.los.R;
import com.swadhaar.los.database.entity.CGTAttendanceTable;

import java.util.ArrayList;
import java.util.List;

import static com.swadhaar.los.constants.AppConstant.DATE_FORMAT_MM_DD_YYYY;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.CGT_1;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.ViewHolder> implements Filterable {

    private Context context;
    List<CGTAttendanceTable> cgtAttendanceTableList;
    List<CGTAttendanceTable> cgtAttendanceTableListFiltered;
    AppHelper appHelper;
    String cgtCycle;
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvClientName, tvClientId, tvMobileNo, date;
        public ImageView image;
        public RelativeLayout lyt_checked, lyt_image;
        private SwitchCompat swAttendance;
        private LinearLayout llReason;
        private Spinner spReason;

        public ViewHolder(View view) {
            super(view);
            tvClientName = (TextView) view.findViewById(R.id.tv_client_name);
            tvClientId = (TextView) view.findViewById(R.id.tv_client_id);
            tvMobileNo = (TextView) view.findViewById(R.id.tv_mobile_number);
            date = (TextView) view.findViewById(R.id.date);
            image =  (ImageView) view.findViewById(R.id.image);
            lyt_checked = (RelativeLayout) view.findViewById(R.id.lyt_checked);
            lyt_image = (RelativeLayout) view.findViewById(R.id.lyt_image);
            swAttendance = (SwitchCompat) view.findViewById(R.id.sw_attendance);
            llReason = (LinearLayout) view.findViewById(R.id.ll_reason);
            spReason=(Spinner)view.findViewById(R.id.sp_absent_reason);
        }
    }
    public AttendanceAdapter(Context context, List<CGTAttendanceTable> cgtAttendanceTableList, AppHelper appHelper,
                             String cgtCycle) {
        this.context = context;
        this.cgtAttendanceTableList = cgtAttendanceTableList;
        this.cgtAttendanceTableListFiltered = cgtAttendanceTableList;
        this.appHelper = appHelper;
        this.cgtCycle = cgtCycle;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.attendance_row_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            if (cgtAttendanceTableListFiltered != null && cgtAttendanceTableListFiltered.size() > 0) {
                CGTAttendanceTable cgtAttendanceTable = cgtAttendanceTableListFiltered.get(position);
                // displaying text view data
                holder.tvClientName.setText(cgtAttendanceTable.getClientName());
                holder.tvClientId.setText(cgtAttendanceTable.getClientId());
                String currentDate=appHelper.getCurrentDate(DATE_FORMAT_MM_DD_YYYY);
                if( !TextUtils.isEmpty(currentDate)) {
                    holder.date.setText(currentDate);
                }
                if( ! TextUtils.isEmpty(cgtCycle) && cgtCycle.equalsIgnoreCase(CGT_1)) {
                    if (cgtAttendanceTable.isCGT1Attendance()) {
                        holder.swAttendance.setChecked(cgtAttendanceTable.isCGT1Attendance());
                        holder.lyt_image.setVisibility(View.GONE);
                        holder.lyt_checked.setVisibility(View.VISIBLE);
                        holder.llReason.setVisibility(View.GONE);
                    } else {
                        holder.lyt_checked.setVisibility(View.GONE);
                        holder.lyt_image.setVisibility(View.VISIBLE);
                        holder.llReason.setVisibility(View.VISIBLE);
                        // TODO: Absent Reason
                        if(!TextUtils.isEmpty(cgtAttendanceTable.getCGT1AbsentReason())) {
                           int spPosition= appHelper.getSpinnerPosition(holder.spReason, cgtAttendanceTable.getCGT1AbsentReason());
                           holder.spReason.setSelection(spPosition);
                        }
                    }
                }else{
                    if (cgtAttendanceTable.isCGT2Attendance()) {
                        holder.swAttendance.setChecked(cgtAttendanceTable.isCGT2Attendance());
                        holder.lyt_image.setVisibility(View.GONE);
                        holder.lyt_checked.setVisibility(View.VISIBLE);
                        holder.llReason.setVisibility(View.GONE);
                    } else {
                        holder.lyt_checked.setVisibility(View.GONE);
                        holder.lyt_image.setVisibility(View.VISIBLE);
                        holder.llReason.setVisibility(View.VISIBLE);
                        // TODO: Absent Reason
                        if(!TextUtils.isEmpty(cgtAttendanceTable.getCGT2AbsentReason())) {
                            int spPosition= appHelper.getSpinnerPosition(holder.spReason, cgtAttendanceTable.getCGT2AbsentReason());
                            holder.spReason.setSelection(spPosition);
                        }
                    }
                }
                holder.swAttendance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if( ! TextUtils.isEmpty(cgtCycle) && cgtCycle.equalsIgnoreCase(CGT_1)) {
                            cgtAttendanceTable.setCGT1Attendance(isChecked); // TODO: set Attendance one
                            cgtAttendanceTable.setCGT1AbsentReason(""); // TODO: set Attendance one Reason
                        }else{
                            cgtAttendanceTable.setCGT2Attendance(isChecked); // TODO: set Attendance two
                            cgtAttendanceTable.setCGT2AbsentReason(""); // TODO: set Attendance two Reason
                        }
                        if(isChecked){
                            holder.lyt_image.setVisibility(View.GONE);
                            holder.llReason.setVisibility(View.GONE);
                            holder.lyt_checked.setVisibility(View.VISIBLE);

                        }else{
                            holder.lyt_checked.setVisibility(View.GONE);
                            holder.llReason.setVisibility(View.VISIBLE);
                            holder.lyt_image.setVisibility(View.VISIBLE);
                        }

                    }
                });

                holder.spReason.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if(position>0){
                            if( ! TextUtils.isEmpty(cgtCycle) && cgtCycle.equalsIgnoreCase(CGT_1)) {
                                cgtAttendanceTable.setCGT1AbsentReason(holder.spReason.getSelectedItem().toString()); // TODO: set Attendance one absent reason
                            }else{
                                cgtAttendanceTable.setCGT2AbsentReason(holder.spReason.getSelectedItem().toString()); // TODO: set Attendance two absent reason
                            }
                        }else{
                            Toast.makeText(context,"Please Select Absent Reason",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public CGTAttendanceTable getItem(int position) {
        return cgtAttendanceTableListFiltered.get(position);
    }


    public List<CGTAttendanceTable> getCgtAttendanceTableList() {
        return cgtAttendanceTableListFiltered;
    }


    @Override
    public int getItemCount() {
        if (cgtAttendanceTableListFiltered != null) {
            return cgtAttendanceTableListFiltered.size();
        } else {
            return 0;
        }
    }

    public void setItem(List<CGTAttendanceTable> cgtAttendanceTableList,String cgtCycle) {
        try {
            if (cgtAttendanceTableList != null && cgtAttendanceTableList.size() > 0) {
                this.cgtAttendanceTableList.clear();
                this.cgtAttendanceTableListFiltered.clear();
                this.cgtAttendanceTableList = cgtAttendanceTableList;
                this.cgtAttendanceTableListFiltered = cgtAttendanceTableList;
                this.cgtCycle="";
                this.cgtCycle=cgtCycle;
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void clearItems() {
        try {
            if (cgtAttendanceTableList != null && cgtAttendanceTableList.size() > 0) {
                cgtAttendanceTableList.clear();
                cgtAttendanceTableListFiltered.clear();
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();

                if (charString.isEmpty()) {
                    cgtAttendanceTableListFiltered = cgtAttendanceTableList;
                }else {
                    ArrayList<CGTAttendanceTable> filteredList = new ArrayList<>();
                    for (CGTAttendanceTable cgtAttendanceTable : cgtAttendanceTableList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name match
                        if (!TextUtils.isEmpty(cgtAttendanceTable.getClientName())
                             &&  (cgtAttendanceTable.getClientName().toLowerCase().contains(charString.toLowerCase()))){
                            filteredList.add(cgtAttendanceTable);
                        }
                    }

                    cgtAttendanceTableListFiltered = filteredList;
                }


                FilterResults filterResults = new FilterResults();
                filterResults.values = cgtAttendanceTableListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                cgtAttendanceTableListFiltered = (List<CGTAttendanceTable>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}
