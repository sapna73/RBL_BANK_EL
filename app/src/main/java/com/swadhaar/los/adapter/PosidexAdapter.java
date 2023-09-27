package com.swadhaar.los.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.helpers.AppHelper;
import com.swadhaar.los.R;
import com.swadhaar.los.database.entity.CIBILTable;
import com.swadhaar.los.database.entity.RawDataTable;

import java.util.ArrayList;
import java.util.List;

public class PosidexAdapter  extends RecyclerView.Adapter<PosidexAdapter.PosidexViewHolder> {
    private static final String TAG = PosidexAdapter.class.getCanonicalName() ;
    private Context context;
    List<RawDataTable> rawDataTableList=new ArrayList<>();
    PosidexAdapter.POSIDEXInterface posidexInterface;
    AppHelper appHelper;

    public PosidexAdapter(Context context, List<RawDataTable> rawDataTableList, PosidexAdapter.POSIDEXInterface posidexInterface, AppHelper appHelper) {
        this.context = context;
        this.rawDataTableList = rawDataTableList;
        this.posidexInterface = posidexInterface;
        this.appHelper = appHelper;
    }

    @NonNull
    @Override
    public PosidexAdapter.PosidexViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.posidex_item,parent,false);
        return new PosidexAdapter.PosidexViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PosidexAdapter.PosidexViewHolder holder, int position) {
        try{
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return rawDataTableList.size();
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
        void generateCIBILCallBack(int position, CIBILTable cibilTable);
    }


}
