package com.swadhaar.los.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.helpers.AppHelper;
import com.swadhaar.los.R;
import com.swadhaar.los.database.entity.EligibilityTable;

import java.util.List;

public class EligibilityGroupAdapter extends RecyclerView.Adapter<EligibilityGroupAdapter.ViewHolder>  {

    private Context context;
    List<List<EligibilityTable>> listOfEligibilityTableList;
    List<String> productCodeHeadList;
    AppHelper appHelper;
    EligibilityValueAdapter eligibilityValueAdapter;

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvClientName,tvClientId;
        RecyclerView rvEligibilityGroup;

        public ViewHolder(View view) {
            super(view);
            tvClientName=(TextView)view.findViewById(R.id.tv_client_name);
            tvClientId=(TextView)view.findViewById(R.id.tv_client_id);

            rvEligibilityGroup = (RecyclerView) view.findViewById(R.id.rv_eligibility_value);
            rvEligibilityGroup.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true));
            rvEligibilityGroup.setNestedScrollingEnabled(false);

        }
    }

    public EligibilityGroupAdapter(Context context, List<List<EligibilityTable>> listOfEligibilityTableList, List<String> productCodeHeadList, AppHelper appHelper) {
        this.context = context;
        this.listOfEligibilityTableList = listOfEligibilityTableList;
        this.productCodeHeadList=productCodeHeadList;
        this.appHelper = appHelper;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.eligibility_group_adapter_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            if (listOfEligibilityTableList != null && listOfEligibilityTableList.size() > 0) {
                List<EligibilityTable> eligibilityTableList = listOfEligibilityTableList.get(position);

                if(eligibilityTableList !=null && eligibilityTableList.size()>0) {

                    holder.tvClientName.setText(eligibilityTableList.get(0).getCustomerName());
                    holder.tvClientId.setText(eligibilityTableList.get(0).getCustomerId());

                    eligibilityValueAdapter=new EligibilityValueAdapter(context,eligibilityTableList,productCodeHeadList,appHelper);

                    holder.rvEligibilityGroup.setAdapter(eligibilityValueAdapter);
                    eligibilityValueAdapter.notifyDataSetChanged();

                    /*if (eligibilityValueAdapter != null) {
                        eligibilityValueAdapter.setItem(eligibilityTableList);
                    }*/

                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public List<EligibilityTable> getItem(int position) {
        return listOfEligibilityTableList.get(position);
    }


    public List<List<EligibilityTable>> getListOfEligibilityTableList() {
        return listOfEligibilityTableList;
    }


    @Override
    public int getItemCount() {
        if (listOfEligibilityTableList != null) {

            return listOfEligibilityTableList.size();
        } else {
            return 0;
        }
    }

    public void setItem(List<List<EligibilityTable>> listOfEligibilityTableList,List<String> productCodeHeadList) {
        try {
            if (listOfEligibilityTableList != null && listOfEligibilityTableList.size() > 0) {
                this.listOfEligibilityTableList.clear();
                this.listOfEligibilityTableList = listOfEligibilityTableList;
                this.productCodeHeadList.clear();
                this.productCodeHeadList=productCodeHeadList;
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void clearItems() {
        try {
            if (listOfEligibilityTableList != null && listOfEligibilityTableList.size() > 0) {
                listOfEligibilityTableList.clear();
                productCodeHeadList.clear();
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



}
