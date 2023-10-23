package com.saartak.el.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.helpers.AppHelper;
import com.saartak.el.R;
import com.saartak.el.database.entity.HouseVerificationTable;

import java.util.ArrayList;
import java.util.List;

import static com.saartak.el.dynamicui.constants.ParametersConstant.FINAL_STATUS_APPROVED;
import static com.saartak.el.dynamicui.constants.ParametersConstant.FINAL_STATUS_REJECTED;

public class HouseVerificationMemberDetailsSummaryAdapter extends RecyclerView.Adapter<HouseVerificationMemberDetailsSummaryAdapter.LoanTypeViewHolder> implements Filterable {

    private Context context;
    List<HouseVerificationTable> houseVerificationTableList;
    List<HouseVerificationTable> houseVerificationTableListFiltered;
    SyncCallbackInterface syncCallbackInterface;
    AppHelper appHelper;

    public HouseVerificationMemberDetailsSummaryAdapter(Context context, List<HouseVerificationTable> houseVerificationTableList,
                                                        SyncCallbackInterface syncCallbackInterface, AppHelper appHelper) {
        this.context = context;
        this.houseVerificationTableList = houseVerificationTableList;
        this.houseVerificationTableListFiltered = houseVerificationTableList;
        this.syncCallbackInterface = syncCallbackInterface;
        this.appHelper = appHelper;
    }

    @NonNull
    @Override
    public LoanTypeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.house_verification_member_details_summary_row_item, viewGroup, false);
        return new LoanTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoanTypeViewHolder loanTypeViewHolder, int i) {
        try {
            if (houseVerificationTableListFiltered != null && houseVerificationTableListFiltered.size() > 0) {
                HouseVerificationTable houseVerificationTable = houseVerificationTableListFiltered.get(i);
                loanTypeViewHolder.tvVillageName.setText(houseVerificationTable.getClientName());
                loanTypeViewHolder.tvVillageId.setText(houseVerificationTable.getClientId());
                loanTypeViewHolder.tvLoanType.setText(houseVerificationTable.getLoan_type());

                loanTypeViewHolder.cvVillageSurvey.setBackgroundColor(context.getResources().getColor(R.color.lead_deatails_interested));

                boolean isHouseVerificationDone = houseVerificationTable.isHouseVerification();
                if (isHouseVerificationDone && houseVerificationTable.getStatus().equalsIgnoreCase(FINAL_STATUS_APPROVED)) {
                    loanTypeViewHolder.ivStatus.setImageResource(R.mipmap.check);
//                    loanTypeViewHolder.llVillageSurvey.setEnabled(false);
                }else if(isHouseVerificationDone && houseVerificationTable.getStatus().equalsIgnoreCase(FINAL_STATUS_REJECTED)){

                    loanTypeViewHolder.ivStatus.setImageResource(R.drawable.failure_new);
                } else {
//                    loanTypeViewHolder.ivStatus.setImageResource(R.mipmap.verf_pending);
                    loanTypeViewHolder.ivStatus.setImageResource(R.drawable.verf_pending);
//                    loanTypeViewHolder.llVillageSurvey.setEnabled(true);
                }

                loanTypeViewHolder.llVillageSurvey.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                     syncCallbackInterface.openScreenCallback(i,houseVerificationTable);

                    }
                });

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (houseVerificationTableListFiltered != null) {

            return houseVerificationTableListFiltered.size();
        } else {
            return 0;
        }
    }

    public void setItem(List<HouseVerificationTable> houseVerificationTableList) {
        try {
            if (this.houseVerificationTableList != null && this.houseVerificationTableList.size() > 0) {
                this.houseVerificationTableList.clear();
                this.houseVerificationTableListFiltered.clear();
                this.houseVerificationTableList = houseVerificationTableList;
                this.houseVerificationTableListFiltered = houseVerificationTableList;
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void clearItems() {
        try {
            if (houseVerificationTableList != null && houseVerificationTableList.size() > 0) {
                houseVerificationTableList.clear();
                houseVerificationTableListFiltered.clear();
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public class LoanTypeViewHolder extends RecyclerView.ViewHolder {
        TextView tvVillageName,tvVillageId, tvLoanType;
        ImageView ivStatus;
        LinearLayout cvVillageSurvey;
        LinearLayout llVillageSurvey;

        public LoanTypeViewHolder(@NonNull View itemView) {
            super(itemView);

            tvVillageName = (TextView) itemView.findViewById(R.id.tv_village_name_value);
            tvVillageId = (TextView) itemView.findViewById(R.id.tv_village_id_value);
            tvLoanType = (TextView) itemView.findViewById(R.id.tv_loan_type);
            ivStatus = (ImageView) itemView.findViewById(R.id.iv_sync_status);
            llVillageSurvey = (LinearLayout) itemView.findViewById(R.id.ll_village_summary_values);
            cvVillageSurvey = itemView.findViewById(R.id.cv_village_survey);
        }
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();

                FilterResults filterResults = new FilterResults();
                if(charString.isEmpty()) {
                    houseVerificationTableListFiltered = houseVerificationTableList;
                }
                else {
                    List<HouseVerificationTable> filteredList = new ArrayList<>();
                      for (HouseVerificationTable row : houseVerificationTableList) {

                          // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getClientName().toString().toLowerCase().contains(charString.toLowerCase())
                                || row.getPhoneNo().toString().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                      houseVerificationTableListFiltered = filteredList;
                }

                
                filterResults.values = houseVerificationTableListFiltered;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                houseVerificationTableListFiltered = (List<HouseVerificationTable>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public interface SyncCallbackInterface {
        void openScreenCallback(int position, HouseVerificationTable houseVerificationTable);
    }

}
