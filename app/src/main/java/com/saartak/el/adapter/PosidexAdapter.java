package com.saartak.el.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.bfil.uilibrary.helpers.AppHelper;
import com.saartak.el.R;
import com.saartak.el.database.entity.RawDataTable;
import com.saartak.el.models.Posidex.PosidexResponseDTO;

import java.util.ArrayList;
import java.util.List;

public class PosidexAdapter extends RecyclerView.Adapter<PosidexAdapter.PosidexViewHolder> {
    private static final String TAG = PosidexAdapter.class.getCanonicalName() ;
    private Context context;
    List<PosidexResponseDTO.ApiResponse> apiResponse=new ArrayList<>();
    POSIDEXInterface posidexInterface;
    AppHelper appHelper;
    private boolean isGenerateBtnClicked = false;

    public PosidexAdapter(Context context, List<PosidexResponseDTO.ApiResponse> apiResponse, POSIDEXInterface posidexInterface, AppHelper appHelper) {
        this.context = context;
        this.apiResponse = apiResponse;
        this.posidexInterface = posidexInterface;
        this.appHelper = appHelper;
    }

    @NonNull
    @Override
    public PosidexViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.posidex_item,parent,false);
        return new PosidexViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PosidexViewHolder holder, int position) {
        try{
            if(apiResponse!=null){
                holder.ucicId.setText(ChangeStringToStar(apiResponse.get(position).getUCICID()));

                holder.ucic_type.setText(apiResponse.get(position).getUCICtype());
                holder.tv_Message.setText(apiResponse.get(position).getMatchReason());
                if (isGenerateBtnClicked) {
                    holder.btnGenerate.setEnabled(false);
                    holder.btnGenerate.setClickable(false);
                } else {
                    holder.btnGenerate.setEnabled(true);
                    holder.btnGenerate.setClickable(true);
                }
                holder.btnGenerate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (appHelper.isNetworkAvailable()) {
                            isGenerateBtnClicked = true;
                            notifyDataSetChanged();
                            posidexInterface.generatePosidexCallBack(apiResponse.get(position).getUCICID(),apiResponse);
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                    "Please check your internet connection and try again");
                        }
                    }
                });
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


    public class PosidexViewHolder extends RecyclerView.ViewHolder{
        TextView ucicId,ucic_type,tv_Message;
        Button btnGenerate;
        public PosidexViewHolder(@NonNull View itemView) {
            super(itemView);
            ucicId =itemView.findViewById(R.id.ucicId);
            ucic_type =itemView.findViewById(R.id.ucic_type);
            tv_Message =itemView.findViewById(R.id.tv_Message);

            btnGenerate =itemView.findViewById(R.id.btn_generate);
        }
    }

    public interface POSIDEXInterface {
        void generatePosidexCallBack(String ucic_id, List<PosidexResponseDTO.ApiResponse> apiResponse);
    }

    public String ChangeStringToStar(String NUM) {
        String data= NUM.substring((NUM.length()-4));
        String finalNum="********"+data;
        return finalNum;
    }

}
