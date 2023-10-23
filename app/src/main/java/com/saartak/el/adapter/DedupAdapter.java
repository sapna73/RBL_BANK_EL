package com.saartak.el.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.helpers.AppHelper;
import com.saartak.el.R;
import com.saartak.el.database.entity.RawDataTable;
import com.saartak.el.models.Dedupe.DedupeResponseDTO;

import java.util.ArrayList;
import java.util.List;

public class DedupAdapter extends RecyclerView.Adapter<DedupAdapter.DeliquencyViewHolder> {
    private Context context;
    List<DedupeResponseDTO.ApiResponse.GetCustomerResponse.Results.CustomerDetails> apiResponse=new ArrayList<>();
    DedupAdapter.DEDUPInterface dedupInterface;
    AppHelper appHelper;

    public DedupAdapter(Context context, List<DedupeResponseDTO.ApiResponse.GetCustomerResponse.Results.CustomerDetails> apiResponse, DedupAdapter.DEDUPInterface dedupInterface, AppHelper appHelper) {
        this.context = context;
        this.apiResponse = apiResponse;
        this.dedupInterface = dedupInterface;
        this.appHelper = appHelper;
    }

    @NonNull
    @Override
    public DedupAdapter.DeliquencyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.dedup_item,parent,false);
        return new DedupAdapter.DeliquencyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DedupAdapter.DeliquencyViewHolder holder, int position) {
        try{
            if(apiResponse!=null){
                holder.tv_customerId.setText(apiResponse.get(holder.getAdapterPosition()).getDemographics().getCustomerID());
                holder.tv_CustomerName.setText(apiResponse.get(holder.getAdapterPosition()).getDemographics().getName());
                holder.tv_source.setText(apiResponse.get(holder.getAdapterPosition()).getDemographics().getBase());
                holder.tv_matchedType.setText(apiResponse.get(holder.getAdapterPosition()).getDemographics().getMatchType());

                holder.tv_ucicId.setText(apiResponse.get(holder.getAdapterPosition()).getDemographics().getUCIC());
                holder.tv_dob.setText(apiResponse.get(holder.getAdapterPosition()).getDemographics().getDob());

                if(!apiResponse.get(holder.getAdapterPosition()).getDemographics().getPan().equalsIgnoreCase("")){
                    holder.tv_kyc.setText(apiResponse.get(holder.getAdapterPosition()).getDemographics().getPan());
                }else if(!apiResponse.get(holder.getAdapterPosition()).getDemographics().getAadhar().equalsIgnoreCase("")){
                    holder.tv_kyc.setText(apiResponse.get(holder.getAdapterPosition()).getDemographics().getAadhar());
                }else if(!apiResponse.get(holder.getAdapterPosition()).getDemographics().getVoterId().equalsIgnoreCase("")){
                    holder.tv_kyc.setText(apiResponse.get(holder.getAdapterPosition()).getDemographics().getVoterId());
                }else if(!apiResponse.get(holder.getAdapterPosition()).getDemographics().getDrvLicenceNo().equalsIgnoreCase("")){
                    holder.tv_kyc.setText(apiResponse.get(holder.getAdapterPosition()).getDemographics().getDrvLicenceNo());
                }else if(!apiResponse.get(holder.getAdapterPosition()).getDemographics().getPassportNo().equalsIgnoreCase("")){
                    holder.tv_kyc.setText(apiResponse.get(holder.getAdapterPosition()).getDemographics().getPassportNo());
                }else if(!apiResponse.get(holder.getAdapterPosition()).getDemographics().getRationCardNumber().equalsIgnoreCase("")){
                    holder.tv_kyc.setText(apiResponse.get(holder.getAdapterPosition()).getDemographics().getRationCardNumber());
                }
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return apiResponse==null?0:apiResponse.size();
    }

    public void setItems(List<RawDataTable> list){
        try {
            /*if (list != null && list.size() > 0){
                rawDataTableList.clear();
                notifyDataSetChanged();
            }else{
                notifyDataSetChanged();
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public class DeliquencyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_customerId,tv_CustomerName,tv_source,tv_matchedType,tv_ucicId,tv_dob,tv_kyc;
        public DeliquencyViewHolder(@NonNull View view) {
            super(view);
            tv_customerId =view.findViewById(R.id.tv_customerId);
            tv_CustomerName =view.findViewById(R.id.tv_CustomerName);
            tv_source =view.findViewById(R.id.tv_source);
            tv_matchedType =view.findViewById(R.id.tv_matchedType);
            tv_ucicId =view.findViewById(R.id.tv_ucicId);
            tv_dob =view.findViewById(R.id.tv_dob);
            tv_kyc =view.findViewById(R.id.tv_kyc);
        }
    }

    public interface DEDUPInterface {
        void generateDedupCallBack(String ucic_id, List<DedupeResponseDTO.ApiResponse.GetCustomerResponse.Results.CustomerDetails> apiResponse);
    }


}
