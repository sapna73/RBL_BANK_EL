package com.saartak.el.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.helpers.AppHelper;
import com.saartak.el.R;
import com.saartak.el.database.entity.EligibilityTable;
import com.saartak.el.models.EligibilityByGroupDTO;

import java.util.List;

public class EligibilityAdapter extends RecyclerView.Adapter<EligibilityAdapter.ViewHolder>  {

    private Context context;
    List<EligibilityByGroupDTO> listOfEligibilityTableList;
    List<String> productCodeHeadList;
    AppHelper appHelper;
    EligibilityGroupAdapter eligibilityGroupAdapter;

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvGroupCode;
        AppCompatImageButton btnArrow;
        RecyclerView rvEligibilityGroup;
        LinearLayout llEligibilityGroup;

        public ViewHolder(View view) {
            super(view);
            tvGroupCode=(TextView)view.findViewById(R.id.tv_group_code_value);
            btnArrow=(AppCompatImageButton)view.findViewById(R.id.btn_arrow);
            llEligibilityGroup=(LinearLayout) view.findViewById(R.id.ll_eligibility_group_value);

            rvEligibilityGroup = (RecyclerView) view.findViewById(R.id.rv_eligibility_group_value);
            rvEligibilityGroup.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true));
            rvEligibilityGroup.setNestedScrollingEnabled(false);

        }
    }

    public EligibilityAdapter(Context context, List<EligibilityByGroupDTO> listOfEligibilityTableList,List<String> productCodeHeadList, AppHelper appHelper) {
        this.context = context;
        this.listOfEligibilityTableList = listOfEligibilityTableList;
        this.productCodeHeadList=productCodeHeadList;
        this.appHelper = appHelper;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.eligibility_adapter_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            if (listOfEligibilityTableList != null && listOfEligibilityTableList.size() > 0) {
                holder.tvGroupCode.setText(listOfEligibilityTableList.get(position).getGroupName());
                List<List<EligibilityTable>> eligibilityTableList = listOfEligibilityTableList.get(position).getListOfEligibilityProductList();

                if(eligibilityTableList !=null && eligibilityTableList.size()>0) {

                    eligibilityGroupAdapter=new EligibilityGroupAdapter(context,eligibilityTableList,productCodeHeadList,appHelper);

                    holder.rvEligibilityGroup.setAdapter(eligibilityGroupAdapter);
                    eligibilityGroupAdapter.notifyDataSetChanged();

                    /*if (eligibilityGroupAdapter != null) {
                        eligibilityGroupAdapter.setItem(eligibilityTableList);
                    }*/

                }

//                if(holder.llEligibilityGroup.getVisibility()==View.VISIBLE){
//                   holder.btnArrow.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
//                }
//                else{
//                    holder.btnArrow.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
//                }

                holder.btnArrow.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                holder.llEligibilityGroup.setVisibility(View.GONE);

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


    public List<EligibilityByGroupDTO> getListOfEligibilityTableList() {
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

    public void setItem(List<EligibilityByGroupDTO> listOfEligibilityTableList,List<String> productCodeHeadList) {
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
