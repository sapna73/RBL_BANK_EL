package com.saartak.el.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.helpers.AppHelper;
import com.saartak.el.R;
import com.saartak.el.database.entity.RawDataTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClientDetailsAdapter extends RecyclerView.Adapter<ClientDetailsAdapter.ClientDetailsViewHolder> {
    Context context;
    ArrayList<HashMap<String,Object>> hashMapArrayList;
    List<RawDataTable> rawDataTableList;
    AppHelper appHelper;
    List<String> screenNameList,screenNumberList;
    ClientDetailsInterface clientDetailsInterface;
    String moduleType;

    public  ClientDetailsAdapter(Context context, List<RawDataTable> rawDataTableList,
                                 ArrayList<HashMap<String,Object>> hashMapArrayList,AppHelper appHelper,List<String> screenNameList,
                                 List<String> screenNumberList,String moduleType,
                                 ClientDetailsInterface clientDetailsInterface){
        this.context=context;
        this.rawDataTableList=rawDataTableList;
        this.hashMapArrayList=hashMapArrayList;
        this.appHelper=appHelper;
        this.screenNameList=screenNameList;
        this.screenNumberList=screenNumberList;
        this.clientDetailsInterface=clientDetailsInterface;
        this.moduleType=moduleType;
    }


    @NonNull
    @Override
    public ClientDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.client_detail_row_item,parent,false);
        return new ClientDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientDetailsViewHolder holder, int position) {
       try{
          if(rawDataTableList.size()>0){
//              String screenName=rawDataTableList.get(position).getScreen_name();
              String screenName=screenNameList.get(position);
              if( !TextUtils.isEmpty(screenName)) {
                  holder.tvScreenName.setText(screenName);

                  for(RawDataTable rawDataTable:rawDataTableList){
                      if(rawDataTable.getScreen_name().equalsIgnoreCase(screenName)){
                          holder.ibScreenStatus.setImageResource(R.drawable.ic_done_gray_24dp);
                      }
                  }
              }

holder.tvScreenName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        String screenNameToCall = screenNameList.get(position);
                        if (!TextUtils.isEmpty(screenNameToCall)) {
                            clientDetailsInterface.callBackForScreen(rawDataTableList.get(0).getModuleType(), screenNameToCall);
                        }
                    }catch (ArrayIndexOutOfBoundsException e){
                        e.printStackTrace();
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
        return screenNameList.size();
    }

    public class ClientDetailsViewHolder extends RecyclerView.ViewHolder{
        TextView tvScreenName;
        ImageButton ibScreenStatus;

        public ClientDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvScreenName=(TextView)itemView.findViewById(R.id.tv_screen_name);
            ibScreenStatus=(ImageButton)itemView.findViewById(R.id.ib_screen_status);
        }
    }

    public interface ClientDetailsInterface{
        void callBackForScreen(String moduleType,String screenName);
    }
}
