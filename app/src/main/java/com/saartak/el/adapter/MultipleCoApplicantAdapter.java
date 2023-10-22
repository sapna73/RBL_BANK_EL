package com.saartak.el.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.helpers.AppHelper;
import com.saartak.el.R;
import com.saartak.el.helper.MyDividerItemDecoration;

import java.util.List;

public class MultipleCoApplicantAdapter extends RecyclerView.Adapter<MultipleCoApplicantAdapter.CoApplicantsAdapterViewHolder> {
    Context context;
    List<ClientDetailsAdapter> clientDetailsAdapterList;
    AppHelper appHelper;

    public MultipleCoApplicantAdapter(Context context, List<ClientDetailsAdapter> clientDetailsAdapterList, AppHelper appHelper){
        this.context=context;
        this.appHelper=appHelper;
        this.clientDetailsAdapterList=clientDetailsAdapterList;
    }


    @NonNull
    @Override
    public CoApplicantsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.multiple_co_applicant_row_item,parent,false);
        return new CoApplicantsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoApplicantsAdapterViewHolder holder, int position) {
       try{
          if(clientDetailsAdapterList.size()>0){
              holder.rvMultipleCoApplicant.setAdapter(clientDetailsAdapterList.get(position));
              clientDetailsAdapterList.get(position).notifyDataSetChanged();
          }
       }catch (Exception ex){
           ex.printStackTrace();
       }
    }

    @Override
    public int getItemCount() {
        return clientDetailsAdapterList.size();
    }

    public class CoApplicantsAdapterViewHolder extends RecyclerView.ViewHolder{
        RecyclerView rvMultipleCoApplicant;

        public CoApplicantsAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            rvMultipleCoApplicant=(RecyclerView) itemView.findViewById(R.id.rv_multiple_co_applicant);
            RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(context);
            rvMultipleCoApplicant.setLayoutManager(layoutManager);
            rvMultipleCoApplicant.addItemDecoration(new MyDividerItemDecoration(context, DividerItemDecoration.VERTICAL, 24));
        }
    }

}
