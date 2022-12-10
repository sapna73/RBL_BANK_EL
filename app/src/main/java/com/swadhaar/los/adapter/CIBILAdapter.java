package com.swadhaar.los.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.bfil.uilibrary.helpers.AppHelper;
import com.chaos.view.PinView;
import com.swadhaar.los.R;
import com.swadhaar.los.database.entity.CIBILTable;
import com.swadhaar.los.database.entity.CIBILTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

public class CIBILAdapter extends RecyclerView.Adapter<CIBILAdapter.CIBILGeneratorViewHolder> {
    private static final String TAG = CIBILAdapter.class.getCanonicalName() ;
    private Context context;
    List<CIBILTable> cibilTableList=new ArrayList<>();
    CIBILInterface cibilInterface;
    AppHelper appHelper;

    public CIBILAdapter(Context context, List<CIBILTable> cibilTableList, CIBILInterface cibilInterface, AppHelper appHelper) {
        this.context = context;
        this.cibilTableList = cibilTableList;
        this.cibilInterface = cibilInterface;
        this.appHelper = appHelper;
    }

    @NonNull
    @Override
    public CIBILGeneratorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.view_cibil_adapter,parent,false);
        return new CIBILGeneratorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CIBILGeneratorViewHolder holder, int position) {
        try{
            if(cibilTableList !=null && cibilTableList.size()>0) {
                CIBILTable cibilTable= cibilTableList.get(position);
                if(cibilTable !=null) {

                    if( ! TextUtils.isEmpty(cibilTable.getMobileNumber())){
                        holder.tvMobileNumber.setText(cibilTable.getMobileNumber());
                    }

                    if( ! TextUtils.isEmpty(cibilTable.getName())){
                        holder.tvName.setText(cibilTable.getName());
                    }

                    if(cibilTable.isCBChecked()){
                        holder.llCIBILGenerationSuccess.setVisibility(View.VISIBLE);
                        holder.llCibilResult.setVisibility(View.VISIBLE);

                        holder.tvDecision.setText(cibilTable.getDecision());
                        holder.tvScore.setText(cibilTable.getScore());
                        holder.tvReason.setText(cibilTable.getReason());
                        holder.tvDate.setText(cibilTable.getTimestamp());
                    }else{
                        holder.llCIBILGenerationSuccess.setVisibility(View.GONE);
                        holder.llCibilResult.setVisibility(View.GONE);
                    }

                    holder.btnGenerateCibil.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            cibilInterface.generateCIBILCallBack(position,cibilTable);
                        }
                    });

                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return cibilTableList.size();
    }

    public void setItems(List<CIBILTable> list){
        try {
            if (list != null && list.size() > 0){
                cibilTableList.clear();
                cibilTableList=list;
                notifyDataSetChanged();
            }else{
                cibilTableList.clear();
                notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void addItem(CIBILTable cibilTable){
        if (cibilTable != null){
            cibilTableList.add(cibilTable);
            notifyDataSetChanged();
        }
    }


    public class CIBILGeneratorViewHolder extends RecyclerView.ViewHolder{
        Button btnGenerateCibil;
        TextView tvName, tvMobileNumber,tvDecision,tvScore,tvReason,tvDate;
        LinearLayout llCIBILGenerationSuccess,llCibilResult;
        public CIBILGeneratorViewHolder(@NonNull View itemView) {
            super(itemView);
            btnGenerateCibil=(Button)itemView.findViewById(R.id.btn_generate_cibil);
            tvMobileNumber=(TextView)itemView.findViewById(R.id.tv_mobile_number);
            tvName=(TextView)itemView.findViewById(R.id.tv_name);
            tvDecision=(TextView)itemView.findViewById(R.id.tv_decision);
            tvScore=(TextView)itemView.findViewById(R.id.tv_score);
            tvReason=(TextView)itemView.findViewById(R.id.tv_reason);
            tvDate=(TextView)itemView.findViewById(R.id.tv_date);
            llCIBILGenerationSuccess=(LinearLayout)itemView.findViewById(R.id.ll_cibil_generation_success);
            llCibilResult=(LinearLayout)itemView.findViewById(R.id.ll_cibil_result);
        }
    }

    public interface CIBILInterface {
        void generateCIBILCallBack(int position, CIBILTable cibilTable);
    }


}
