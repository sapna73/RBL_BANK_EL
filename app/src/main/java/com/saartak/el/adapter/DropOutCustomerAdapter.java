package com.saartak.el.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.bfil.uilibrary.helpers.AppHelper;
import com.saartak.el.R;
import com.saartak.el.database.entity.CGTTable;

import java.util.List;

import static com.saartak.el.constants.AppConstant.DATE_FORMAT_MM_DD_YYYY;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_DO_YOU_WANT_TO_DROP_OUT;

public class DropOutCustomerAdapter extends RecyclerView.Adapter<DropOutCustomerAdapter.ViewHolder> implements Filterable {

    private Context context;
    List<CGTTable> cgtTableList;
    List<CGTTable> cgtTableListFiltered;
    AppHelper appHelper;
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView from, email, message, date,tvRejected;
        public ImageView image;
        public RelativeLayout lyt_checked, lyt_image;
        private SwitchCompat swAttendance;

        public ViewHolder(View view) {
            super(view);
            from = (TextView) view.findViewById(R.id.from);
            email = (TextView) view.findViewById(R.id.email);
            message = (TextView) view.findViewById(R.id.message);
            date = (TextView) view.findViewById(R.id.date);
            tvRejected = (TextView) view.findViewById(R.id.tv_rejected);
            image =  (ImageView) view.findViewById(R.id.image);
            lyt_checked = (RelativeLayout) view.findViewById(R.id.lyt_checked);
            lyt_image = (RelativeLayout) view.findViewById(R.id.lyt_image);
            swAttendance = (SwitchCompat) view.findViewById(R.id.sw_attendance);
        }
    }
    public DropOutCustomerAdapter(Context context, List<CGTTable> cgtTableList, AppHelper appHelper) {
        this.context = context;
        this.cgtTableList = cgtTableList;
        this.cgtTableListFiltered = cgtTableList;
        this.appHelper = appHelper;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.drop_out_customer_row_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            if (cgtTableListFiltered != null && cgtTableListFiltered.size() > 0) {
                CGTTable cgtTable = cgtTableListFiltered.get(position);

                // displaying text view data
                holder.from.setText(cgtTable.getClientName());
                holder.email.setText(cgtTable.getClientId());
                holder.message.setText(cgtTable.getPhoneNo());
                String currentDate=appHelper.getCurrentDate(DATE_FORMAT_MM_DD_YYYY);
                if( !TextUtils.isEmpty(currentDate)) {
                    holder.date.setText(currentDate);
                }

//                if (cgtTable.isCgtRejected()) {
//                    holder.swAttendance.setChecked(cgtTable.isCgtRejected());
//                    holder.lyt_image.setVisibility(View.GONE);
//                    holder.lyt_checked.setVisibility(View.VISIBLE);
//                    holder.tvRejected.setVisibility(View.VISIBLE);
//                } else {
//                    holder.lyt_checked.setVisibility(View.GONE);
//                    holder.lyt_image.setVisibility(View.VISIBLE);
//                    holder.tvRejected.setVisibility(View.GONE);
//                }

                holder.swAttendance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){

                                appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons(ERROR_MESSAGE_DO_YOU_WANT_TO_DROP_OUT,
                                        "Yes", "No", new ConfirmationDialog.PrintActionCallback() {
                                            @Override
                                            public void onAction() {
                                                holder.swAttendance.setChecked(false);
                                                cgtTable.setDropOutCustomer(false);
                                            }

                                            @Override
                                            public void onPrint() {
                                                holder.swAttendance.setChecked(true);
                                                cgtTable.setDropOutCustomer(true);

                                                holder.lyt_image.setVisibility(View.GONE);
                                                holder.lyt_checked.setVisibility(View.VISIBLE);
                                                holder.tvRejected.setVisibility(View.VISIBLE);
                                            }
                                        });

                        }else{
                            holder.lyt_checked.setVisibility(View.GONE);
                            holder.lyt_image.setVisibility(View.VISIBLE);
                            holder.tvRejected.setVisibility(View.GONE);
                        }

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

}
