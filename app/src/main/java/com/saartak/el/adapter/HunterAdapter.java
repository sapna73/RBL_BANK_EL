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
import com.saartak.el.models.Hunter.HunterResponseDTO;

import java.util.ArrayList;
import java.util.List;

public class HunterAdapter extends RecyclerView.Adapter<HunterAdapter.HunterViewHolder> {
    private Context context;
    List<HunterResponseDTO.ApiResponse.ResultBlock.MatchResponse.MatchResult.MatchSummary.Rules.Rule> apiResponse=new ArrayList<>();
    HunterAdapter.HunterInterface hunterInterface;
    AppHelper appHelper;

    public HunterAdapter(Context context, List<HunterResponseDTO.ApiResponse.ResultBlock.MatchResponse.MatchResult.MatchSummary.Rules.Rule> apiResponse, HunterAdapter.HunterInterface hunterInterface, AppHelper appHelper) {
        this.context = context;
        this.apiResponse = apiResponse;
        this.hunterInterface = hunterInterface;
        this.appHelper = appHelper;
    }

    @NonNull
    @Override
    public HunterAdapter.HunterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.hunter_item,parent,false);
        return new HunterAdapter.HunterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HunterAdapter.HunterViewHolder holder, int position) {
        try{
            if(apiResponse!=null){
                 holder.tv_Score.setText(apiResponse.get(holder.getAdapterPosition()).getRuleID()+" : "+apiResponse.get(holder.getAdapterPosition()).getScore());
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


    public class HunterViewHolder extends RecyclerView.ViewHolder{
        TextView tv_Score;
        public HunterViewHolder(@NonNull View view) {
            super(view);
            tv_Score =view.findViewById(R.id.tv_Score);
        }
    }

    public interface HunterInterface {
        void generatehuterCallBack(String ucic_id, List<HunterResponseDTO.ApiResponse.ResultBlock.MatchResponse.MatchResult.MatchSummary.Rules.Rule> apiResponse);
    }


}
