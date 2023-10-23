package com.saartak.el.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.helpers.AppHelper;
import com.saartak.el.R;
import com.saartak.el.database.entity.RawDataTable;
import com.saartak.el.models.Deliquency.DeliquencyResponseDTO;

import java.util.ArrayList;
import java.util.List;

public class DeliquencyAdapter extends RecyclerView.Adapter<DeliquencyAdapter.DeliquencyViewHolder> {
    private static final String TAG = DeliquencyAdapter.class.getCanonicalName() ;
    private Context context;
    List<DeliquencyResponseDTO.ApiResponse> apiResponse=new ArrayList<>();
    DeliquencyAdapter.DELIQUENCYInterface deliquencyInterface;
    AppHelper appHelper;

    public DeliquencyAdapter(Context context, List<DeliquencyResponseDTO.ApiResponse> apiResponse, DeliquencyAdapter.DELIQUENCYInterface deliquencyInterface, AppHelper appHelper) {
        this.context = context;
        this.apiResponse = apiResponse;
        this.deliquencyInterface = deliquencyInterface;
        this.appHelper = appHelper;
    }

    @NonNull
    @Override
    public DeliquencyAdapter.DeliquencyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.deliquency_item,parent,false);
        return new DeliquencyAdapter.DeliquencyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeliquencyAdapter.DeliquencyViewHolder holder, int position) {
        try{
            if(apiResponse!=null){
                holder.tv_ucic_no.setText(ChangeStringToStar(apiResponse.get(position).getUCICNo()));
                //holder.tv_ucic_no.setText(apiResponse.get(position).getUCICNo());
                holder.tv_name.setText(apiResponse.get(position).getName());
                holder.tv_loan_account_no.setText(apiResponse.get(position).getLoanAccountNo());
                holder.tv_source_system.setText(apiResponse.get(position).getProductSource());
                holder.tv_current_dpd.setText(apiResponse.get(position).getCurrentDPD());
                holder.tv_twrite_off.setText(apiResponse.get(position).getTWriteOff());
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
        TextView tv_ucic_no,tv_name,tv_loan_account_no,tv_source_system,tv_current_dpd,tv_twrite_off;
        Button btn_Deliquency;
        public DeliquencyViewHolder(@NonNull View view) {
            super(view);
            tv_ucic_no =view.findViewById(R.id.tv_ucic_no);
            tv_name =view.findViewById(R.id.tv_name);
            tv_loan_account_no =view.findViewById(R.id.tv_loan_account_no);
            tv_source_system =view.findViewById(R.id.tv_source_system);
            tv_current_dpd =view.findViewById(R.id.tv_current_dpd);
            tv_twrite_off =view.findViewById(R.id.tv_twrite_off);
        }
    }

    public interface DELIQUENCYInterface {
        void generateDeliquencyCallBack(String ucic_id, List<DeliquencyResponseDTO.ApiResponse> apiResponse);
    }

    public String ChangeStringToStar(String NUM) {
        String data= NUM.substring((NUM.length()-4));
        String finalNum="********"+data;
        return finalNum;
    }
}
