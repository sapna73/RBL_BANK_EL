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
import com.saartak.el.models.ESignEstamp.ESignEStampStatusResponseDTO;

import java.util.ArrayList;
import java.util.List;

public class EsignEstampAdapter extends RecyclerView.Adapter<EsignEstampAdapter.EsignEstampStatusHolder> {
    private static final String TAG = EsignEstampAdapter.class.getCanonicalName() ;
    private Context context;
    List<ESignEStampStatusResponseDTO.ApiResponse.DocStatus.Status> apiResponse=new ArrayList<>();
    ESIGNESTAMPSTATUSInterface esignestampstatusInterface;
    AppHelper appHelper;
    private boolean isGenerateBtnClicked = false;

    public EsignEstampAdapter(Context context, List<ESignEStampStatusResponseDTO.ApiResponse.DocStatus.Status> apiResponse,ESIGNESTAMPSTATUSInterface esignestampstatusInterface, AppHelper appHelper) {
        this.context = context;
        this.apiResponse = apiResponse;
        this.esignestampstatusInterface = esignestampstatusInterface;
        this.appHelper = appHelper;
    }

    @NonNull
    @Override
    public EsignEstampAdapter.EsignEstampStatusHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.esign_estamp_status_item,parent,false);
        return new EsignEstampAdapter.EsignEstampStatusHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EsignEstampAdapter.EsignEstampStatusHolder holder, int position) {
        try{
            if(apiResponse!=null){
                holder.signtryName.setText(apiResponse.get(position).getSigntryName());
                holder.pan.setText(apiResponse.get(position).getPan());
                holder.rltnshp.setText(apiResponse.get(position).getRltnshp());
                holder.esignStatus.setText(apiResponse.get(position).getEsignstatus());

                holder.estampstatus.setText(apiResponse.get(position).getEstampstatus());
                holder.esigndate.setText(apiResponse.get(position).getEsigndate());
                holder.estampdate.setText(apiResponse.get(position).getEstampdate());
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return apiResponse== null ? 0:apiResponse.size();
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


    public class EsignEstampStatusHolder extends RecyclerView.ViewHolder{
        TextView signtryName,pan,rltnshp,esignStatus,estampstatus,esigndate,estampdate;

        public EsignEstampStatusHolder(@NonNull View itemView) {
            super(itemView);
            signtryName =  itemView.findViewById(R.id.signtryName);
            pan = itemView.findViewById(R.id.pan);
            rltnshp = itemView.findViewById(R.id.rltnshp);
            esignStatus = itemView.findViewById(R.id.esignStatus);

            estampstatus =  itemView.findViewById(R.id.estampstatus);
            esigndate = itemView.findViewById(R.id.esigndate);
            estampdate = itemView.findViewById(R.id.estampdate);
        }
    }

    public interface ESIGNESTAMPSTATUSInterface {

    }

}
