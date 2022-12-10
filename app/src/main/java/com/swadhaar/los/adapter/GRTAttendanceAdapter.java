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
import com.swadhaar.los.database.entity.GRTAttendanceTable;

import java.util.ArrayList;
import java.util.List;

import static com.swadhaar.los.constants.AppConstant.DATE_FORMAT_MM_DD_YYYY;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.CGT_1;

public class GRTAttendanceAdapter extends RecyclerView.Adapter<GRTAttendanceAdapter.ViewHolder> implements Filterable {

    private Context context;
    List<GRTAttendanceTable> grtAttendanceTableList;
    List<GRTAttendanceTable> grtAttendanceTableListFiltered;
    AppHelper appHelper;
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
    public GRTAttendanceAdapter(Context context, List<GRTAttendanceTable> grtAttendanceTableList, AppHelper appHelper) {
        this.context = context;
        this.grtAttendanceTableList = grtAttendanceTableList;
        this.grtAttendanceTableListFiltered = grtAttendanceTableList;
        this.appHelper = appHelper;
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
            if (grtAttendanceTableListFiltered != null && grtAttendanceTableListFiltered.size() > 0) {
                GRTAttendanceTable grtAttendanceTable = grtAttendanceTableListFiltered.get(position);
                // displaying text view data
                holder.tvClientName.setText(grtAttendanceTable.getClientName());
                holder.tvClientId.setText(grtAttendanceTable.getClientId());
                String currentDate=appHelper.getCurrentDate(DATE_FORMAT_MM_DD_YYYY);
                if( !TextUtils.isEmpty(currentDate)) {
                    holder.date.setText(currentDate);
                }
                    if (grtAttendanceTable.isAttendance()) {
                        holder.swAttendance.setChecked(grtAttendanceTable.isAttendance());
                        holder.lyt_image.setVisibility(View.GONE);
                        holder.lyt_checked.setVisibility(View.VISIBLE);
                        holder.llReason.setVisibility(View.GONE);
                    } else {
                        holder.lyt_checked.setVisibility(View.GONE);
                        holder.lyt_image.setVisibility(View.VISIBLE);
                        holder.llReason.setVisibility(View.VISIBLE);
                        // TODO: Absent Reason
                        if(!TextUtils.isEmpty(grtAttendanceTable.getAbsentReason())) {
                           int spPosition= appHelper.getSpinnerPosition(holder.spReason, grtAttendanceTable.getAbsentReason());
                           holder.spReason.setSelection(spPosition);
                        }
                    }
                holder.swAttendance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            grtAttendanceTable.setAttendance(isChecked); // TODO: set Attendance
                            grtAttendanceTable.setAbsentReason(""); // TODO: set Attendance Reason
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
                                grtAttendanceTable.setAbsentReason(holder.spReason.getSelectedItem().toString()); // TODO: set Attendance one absent reason
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

    public GRTAttendanceTable getItem(int position) {
        return grtAttendanceTableListFiltered.get(position);
    }


    public List<GRTAttendanceTable> getGRTAttendanceTableList() {
        return grtAttendanceTableListFiltered;
    }


    @Override
    public int getItemCount() {
        if (grtAttendanceTableListFiltered != null) {
            return grtAttendanceTableListFiltered.size();
        } else {
            return 0;
        }
    }

    public void setItem(List<GRTAttendanceTable> grtAttendanceTableList) {
        try {
            if (grtAttendanceTableList != null && grtAttendanceTableList.size() > 0) {
                this.grtAttendanceTableList.clear();
                this.grtAttendanceTableListFiltered.clear();
                this.grtAttendanceTableList = grtAttendanceTableList;
                this.grtAttendanceTableListFiltered = grtAttendanceTableList;
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void clearItems() {
        try {
            if (grtAttendanceTableList != null && grtAttendanceTableList.size() > 0) {
                grtAttendanceTableList.clear();
                grtAttendanceTableListFiltered.clear();
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
                    grtAttendanceTableListFiltered = grtAttendanceTableList;
                }else {
                    ArrayList<GRTAttendanceTable> filteredList = new ArrayList<>();
                    for (GRTAttendanceTable grtAttendanceTable : grtAttendanceTableList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name match
                        if (!TextUtils.isEmpty(grtAttendanceTable.getClientName())
                             &&  (grtAttendanceTable.getClientName().toLowerCase().contains(charString.toLowerCase()))){
                            filteredList.add(grtAttendanceTable);
                        }
                    }

                    grtAttendanceTableListFiltered = filteredList;
                }


                FilterResults filterResults = new FilterResults();
                filterResults.values = grtAttendanceTableListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                grtAttendanceTableListFiltered = (List<GRTAttendanceTable>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}
