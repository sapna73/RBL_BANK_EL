package com.saartak.el.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.helpers.AppHelper;
import com.saartak.el.R;
import com.saartak.el.models.FetchOtherDayCMDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FetchOtherDayCollectionAdapter extends RecyclerView.Adapter<FetchOtherDayCollectionAdapter.LoanTypeViewHolder> implements Filterable {

    private Context context;
    List<FetchOtherDayCMDTO> fetchOtherDayCollectionTableList;
    List<FetchOtherDayCMDTO> fetchOtherDayCollectionTableListFiltered;
    SyncCallbackInterface syncCallbackInterface;
    AppHelper appHelper;

    public FetchOtherDayCollectionAdapter(Context context, List<FetchOtherDayCMDTO> fetchOtherDayCollectionTableList,
                                          SyncCallbackInterface syncCallbackInterface, AppHelper appHelper) {
        this.context = context;
        this.fetchOtherDayCollectionTableList = fetchOtherDayCollectionTableList;
        this.fetchOtherDayCollectionTableListFiltered = fetchOtherDayCollectionTableList;
        this.syncCallbackInterface = syncCallbackInterface;
        this.appHelper = appHelper;
    }

    @NonNull
    @Override
    public LoanTypeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fetch_other_day_collection_row_item, viewGroup, false);
        return new LoanTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoanTypeViewHolder loanTypeViewHolder, int i) {
        try {
            if (fetchOtherDayCollectionTableListFiltered != null && fetchOtherDayCollectionTableListFiltered.size() > 0) {
                FetchOtherDayCMDTO fetchOtherDayCMDTO = fetchOtherDayCollectionTableListFiltered.get(i);
                if (fetchOtherDayCMDTO!=null) {
                    loanTypeViewHolder.tvVillageName.setText(fetchOtherDayCMDTO.getCenterName());
                    loanTypeViewHolder.cbMemberAttendance.setChecked(fetchOtherDayCMDTO.isSelected());

                    loanTypeViewHolder.tvVillageName.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                     syncCallbackInterface.openScreenCallback(i,fetchOtherDayCMDTO);
                        }
                    });

                    loanTypeViewHolder.cbMemberAttendance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            fetchOtherDayCMDTO.setSelected(isChecked);
                        }
                    });
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public  List<FetchOtherDayCMDTO> getFetchOtherDayCollectionTableList() {
        return fetchOtherDayCollectionTableList;
    }

    @Override
    public int getItemCount() {
        if (fetchOtherDayCollectionTableListFiltered != null) {

            return fetchOtherDayCollectionTableListFiltered.size();
        } else {
            return 0;
        }
    }

    public void setItem(List<FetchOtherDayCMDTO> fetchOtherDayCollectionTableList) {
        try {
            if (this.fetchOtherDayCollectionTableList != null && this.fetchOtherDayCollectionTableList.size() > 0) {
                this.fetchOtherDayCollectionTableList.clear();
                this.fetchOtherDayCollectionTableListFiltered.clear();
                this.fetchOtherDayCollectionTableList = fetchOtherDayCollectionTableList;
                this.fetchOtherDayCollectionTableListFiltered = fetchOtherDayCollectionTableList;
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void clearItems() {
        try {
            if (fetchOtherDayCollectionTableList != null && fetchOtherDayCollectionTableList.size() > 0) {
                fetchOtherDayCollectionTableList.clear();
                fetchOtherDayCollectionTableListFiltered.clear();
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public class LoanTypeViewHolder extends RecyclerView.ViewHolder {
        TextView tvVillageName,tvVillageId, tvNoOfCustomers;
        ImageView ivStatus;
        LinearLayout cvVillageSurvey;
        LinearLayout llVillageSurvey;
        Button btn_submit;
        CheckBox cbMemberAttendance;

        public LoanTypeViewHolder(@NonNull View itemView) {
            super(itemView);

            tvVillageName = (TextView) itemView.findViewById(R.id.tv_village_name_value);
            tvVillageId = (TextView) itemView.findViewById(R.id.tv_village_id_value);
            tvNoOfCustomers = (TextView) itemView.findViewById(R.id.tv_no_of_customers);
            ivStatus = (ImageView) itemView.findViewById(R.id.iv_sync_status);
            llVillageSurvey = (LinearLayout) itemView.findViewById(R.id.ll_village_summary_values);
            cvVillageSurvey = itemView.findViewById(R.id.cv_village_survey);
            btn_submit = itemView.findViewById(R.id.btn_submit);
            cbMemberAttendance=(CheckBox) itemView.findViewById(R.id.cb_member_attendance);
        }
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    fetchOtherDayCollectionTableListFiltered = fetchOtherDayCollectionTableList;
                }else if (charString.equalsIgnoreCase("All")) {
                    fetchOtherDayCollectionTableListFiltered = fetchOtherDayCollectionTableList;
                } else if (charString.equalsIgnoreCase("ASC")) {
                    fetchOtherDayCollectionTableListFiltered = fetchOtherDayCollectionTableList;
                } else if (charString.equalsIgnoreCase("DESC")) {
                    List<FetchOtherDayCMDTO> filteredList = new ArrayList<>();
                    for (int i = fetchOtherDayCollectionTableList.size(); i >= 1; i--) {
                        filteredList.add(fetchOtherDayCollectionTableList.get(i - 1));
                    }
                    fetchOtherDayCollectionTableListFiltered = filteredList;

                }  else if (charString.equalsIgnoreCase("ASCENDING")) {

                    Collections.sort(fetchOtherDayCollectionTableList, new Comparator<FetchOtherDayCMDTO>() {
                        @Override
                        public int compare(FetchOtherDayCMDTO lhs, FetchOtherDayCMDTO rhs) {
                            return lhs.getCenterName().compareTo(rhs.getCenterName());
                        }
                    });

                } else if (charString.equalsIgnoreCase("DESCENDING")){
                    Collections.sort(fetchOtherDayCollectionTableList, new Comparator<FetchOtherDayCMDTO>() {
                        @Override
                        public int compare(FetchOtherDayCMDTO lhs, FetchOtherDayCMDTO rhs) {
                            return rhs.getCenterName().compareTo(lhs.getCenterName());
                        }
                    });

                } else {
//                    List<Contact> filteredList = new ArrayList<>();
                    List<FetchOtherDayCMDTO> filteredList = new ArrayList<>();
                    for (FetchOtherDayCMDTO row : fetchOtherDayCollectionTableList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number matchh
                        if (row.getCenterName().toLowerCase().contains(charString.toLowerCase())
                                || row.getCenterName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    fetchOtherDayCollectionTableListFiltered = filteredList;
                }


                FilterResults filterResults = new FilterResults();
                filterResults.values = fetchOtherDayCollectionTableListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                fetchOtherDayCollectionTableListFiltered = (List<FetchOtherDayCMDTO>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public interface SyncCallbackInterface {
        void openScreenCallback(int position, String centerName);
    }

}
