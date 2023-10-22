package com.saartak.el.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.helpers.AppHelper;
import com.saartak.el.R;
import com.saartak.el.database.entity.EligibilityTable;

import java.util.List;

public class EligibilityValueAdapter extends RecyclerView.Adapter<EligibilityValueAdapter.ViewHolder>  {

    private Context context;
    List<EligibilityTable> eligibilityTableList;
    List<String> productCodeHeadList;
    AppHelper appHelper;
    public class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout rl;
        CheckBox cb;
        public ViewHolder(View view) {
            super(view);
            rl=(RelativeLayout) view.findViewById(R.id.relativeLayout1);
            cb=(CheckBox) view.findViewById(R.id.checkbox1);

            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    String productCode=productCodeHeadList.get(getAdapterPosition());
                    setInterest(eligibilityTableList,isChecked,productCode);
                }
            });
        }
    }

    private void setInterest(List<EligibilityTable> eligibilityTableList, boolean isChecked,String productCode) {
        try{
            if(eligibilityTableList !=null && eligibilityTableList.size()>0) {
                for (EligibilityTable eligibilityTable : eligibilityTableList) {
                    if(eligibilityTable.getProductCode().equalsIgnoreCase(productCode)){
                        eligibilityTable.setInterested(isChecked);
                    }
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public EligibilityValueAdapter(Context context, List<EligibilityTable> eligibilityTableList,List<String> productCodeHeadList, AppHelper appHelper) {
        this.context = context;
        this.eligibilityTableList = eligibilityTableList;
        this.productCodeHeadList=productCodeHeadList;
        this.appHelper = appHelper;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.eligibility_value_adapter_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            if (productCodeHeadList != null && productCodeHeadList.size() > 0) {
                String productCode=productCodeHeadList.get(position);
                if(eligibilityTableList !=null && eligibilityTableList.size()>0){
                    for(EligibilityTable eligibilityTable : eligibilityTableList){
                        if(eligibilityTable.getProductCode().equalsIgnoreCase(productCode)){
                            setColor(eligibilityTable,holder.rl,holder.cb);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void setColor(EligibilityTable eligibilityTable, RelativeLayout relativeLayout,CheckBox checkBox) {
        //TODO: status A - Active
        //TODO: status P - Pending
        //TODO: status C - Closed
        //TODO: status R - Rejected
        try{
            if(eligibilityTable.getLoanStatus().equalsIgnoreCase("A")){
//                relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.colorBlueJeansDark));
//                 checkBox.setBackgroundColor(context.getResources().getColor(R.color.colorBlueJeansDark));
                checkBox.setEnabled(false);
                checkBox.setChecked(true);
            }
            else if(eligibilityTable.getLoanStatus().equalsIgnoreCase("P")){
//                relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.colorGrassDark));
//                checkBox.setBackgroundColor(context.getResources().getColor(R.color.colorGrassDark));
                checkBox.setEnabled(true);
                    checkBox.setChecked(eligibilityTable.isInterested());

            }
            else if(eligibilityTable.getLoanStatus().equalsIgnoreCase("C")){
//                relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.colorGreyLight));
//                checkBox.setBackgroundColor(context.getResources().getColor(R.color.colorGreyLight));
                checkBox.setEnabled(false);
                checkBox.setChecked(false);

            }
//            else{
//                checkBox.setBackgroundColor(context.getResources().getColor(R.color.colorGreyLight));
//                checkBox.setEnabled(false);
//                checkBox.setChecked(false);
//            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }



    public List<EligibilityTable>getListOfEligibilityTableList() {
        return eligibilityTableList;
    }


    @Override
    public int getItemCount() {
        if (productCodeHeadList != null) {

            return productCodeHeadList.size();
        } else {
            return 0;
        }
    }

    public void setItem(List<EligibilityTable>eligibilityTableList,List<String> productCodeHeadList) {
        try {
            if (eligibilityTableList != null && eligibilityTableList.size() > 0) {
                this.eligibilityTableList.clear();
                this.eligibilityTableList = eligibilityTableList;
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
            if (eligibilityTableList != null && eligibilityTableList.size() > 0) {
                eligibilityTableList.clear();
            }
            if(productCodeHeadList !=null && productCodeHeadList.size()>0){
                productCodeHeadList.clear();
            }
            notifyDataSetChanged();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



}
