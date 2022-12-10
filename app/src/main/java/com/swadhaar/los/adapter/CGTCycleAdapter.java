package com.swadhaar.los.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.helpers.AppHelper;
import com.swadhaar.los.R;
import com.swadhaar.los.database.entity.CGTTable;

import java.util.List;

import static com.swadhaar.los.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY2;

public class CGTCycleAdapter extends RecyclerView.Adapter<CGTCycleAdapter.ViewHolder> implements Filterable {

    private Context context;
    List<CGTTable> cgtTableList;
    List<CGTTable> cgtTableListFiltered;
    AppHelper appHelper;
    SyncCallbackInterface syncCallbackInterface;
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName, tvClientId, tvMobNo, date;
        public ImageView image;
        public RelativeLayout lyt_checked, lyt_image;
        private SwitchCompat swAttendance;
        private LinearLayout llParent;

        public ViewHolder(View view) {
            super(view);
            tvName = (TextView) view.findViewById(R.id.tv_client_name);
            tvClientId = (TextView) view.findViewById(R.id.tv_client_id);
            tvMobNo = (TextView) view.findViewById(R.id.tv_mobile_number);
            date = (TextView) view.findViewById(R.id.date);
            image =  (ImageView) view.findViewById(R.id.image);
            lyt_checked = (RelativeLayout) view.findViewById(R.id.lyt_checked);
            lyt_image = (RelativeLayout) view.findViewById(R.id.lyt_image);
            swAttendance = (SwitchCompat) view.findViewById(R.id.sw_attendance);
            llParent = (LinearLayout) view.findViewById(R.id.lyt_parent);
        }
    }
    public CGTCycleAdapter(Context context, List<CGTTable> cgtTableList,   SyncCallbackInterface syncCallbackInterface,AppHelper appHelper) {
        this.context = context;
        this.cgtTableList = cgtTableList;
        this.cgtTableListFiltered = cgtTableList;
        this.syncCallbackInterface = syncCallbackInterface;
        this.appHelper = appHelper;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cgt_cycle_row_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            if (cgtTableListFiltered != null && cgtTableListFiltered.size() > 0) {
                CGTTable cgtTable = cgtTableListFiltered.get(position);

                // displaying text view data
                holder.tvName.setText(cgtTable.getClientName());
                holder.tvClientId.setText(cgtTable.getClientId());
                holder.tvMobNo.setText(cgtTable.getPhoneNo());
                String currentDate=appHelper.getCurrentDate(DATE_FORMAT_DD_MM_YYYY2);
                if( !TextUtils.isEmpty(currentDate)) {
                    holder.date.setText(currentDate);
                }

                if(cgtTable.isCycleTwoCompleted() && cgtTable.isSync()){
                    cgtTable.setCgtRejected(true); // TODO: Cycle Two Completed
                    holder.lyt_image.setVisibility(View.GONE);
                    holder.lyt_checked.setVisibility(View.VISIBLE);
                }else{
                    cgtTable.setCgtRejected(false); // TODO: CGT Two Not Completed
                    holder.lyt_checked.setVisibility(View.GONE);
                    holder.lyt_image.setVisibility(View.VISIBLE);
                }

                holder.llParent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        syncCallbackInterface.openScreenCallback(position, cgtTable);
                    }
                });


            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public CGTTable getItem(int position) {
        return cgtTableListFiltered.get(position);
    }


    public List<CGTTable> getCgtTableList() {
        return cgtTableListFiltered;
    }


    @Override
    public int getItemCount() {
        if (cgtTableListFiltered != null) {

            return cgtTableListFiltered.size();
        } else {
            return 0;
        }
    }

    public void setItem(List<CGTTable> cgtTableList) {
        try {
            if (cgtTableList != null && cgtTableList.size() > 0) {
                this.cgtTableList.clear();
                this.cgtTableListFiltered.clear();
                this.cgtTableList = cgtTableList;
                this.cgtTableListFiltered = cgtTableList;
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void clearItems() {
        try {
            if (cgtTableList != null && cgtTableList.size() > 0) {
                cgtTableList.clear();
                cgtTableListFiltered.clear();
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

                FilterResults filterResults = new FilterResults();
                filterResults.values = cgtTableListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                cgtTableListFiltered = (List<CGTTable>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface SyncCallbackInterface {
        void openScreenCallback(int position,  CGTTable cgtTable);
    }

}
