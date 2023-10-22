package com.saartak.el.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saartak.el.R;
import com.saartak.el.interfaces.ReturnValue;

import java.util.ArrayList;
import java.util.Locale;

public class SearchArrayAdapter  extends RecyclerView.Adapter<SearchArrayAdapter.HunterViewHolder> {
    private Context context;
    ArrayList<String> filterList=new  ArrayList();
    ArrayList<String> arrayList;
    ReturnValue returnValue;
    EditText mEditText;
    Dialog dialogList;
    public SearchArrayAdapter(Context context, ArrayList<String> arrayList, ReturnValue returnValue, EditText mEditText, Dialog dialogList) {
        this.context = context;
        this.arrayList = arrayList;
        this.returnValue = returnValue;
        this.mEditText = mEditText;
        this.dialogList = dialogList;
        filterList.addAll(arrayList);
    }

    @NonNull
    @Override
    public SearchArrayAdapter.HunterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.search_item,parent,false);
        return new SearchArrayAdapter.HunterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchArrayAdapter.HunterViewHolder holder, int position) {


        holder.txtSpinner.setText(arrayList.get(holder.getAdapterPosition()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEditText.setText(arrayList.get(holder.getAdapterPosition()));
                returnValue.returnValues(arrayList.get(holder.getAdapterPosition()), holder.getAdapterPosition());
                dialogList.dismiss();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList==null?0:arrayList.size();
    }

    public void setItems(ArrayList<String> list){
        try {
            if (list != null && list.size() > 0){
                arrayList.clear();
                notifyDataSetChanged();
            }else{
                arrayList = list;
                notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void filterNew(String searchValue) {
        String charText = searchValue.toLowerCase(Locale.getDefault());
        arrayList.clear();
        if (charText != null) {
            if (charText.isEmpty() || charText.length() == 1) {
                arrayList.addAll(filterList);
            } else if(charText.length() >= 2){
                for (String searchKey:filterList) {
                    if(searchKey.toLowerCase(Locale.getDefault()).contains(searchValue)){
                        arrayList.add(searchKey);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    public class HunterViewHolder extends RecyclerView.ViewHolder{

        TextView txtSpinner;
        public HunterViewHolder(@NonNull View view) {
            super(view);
            txtSpinner=view.findViewById(R.id.txtSpinner);
        }
    }

}
/*fun filterNew(searchTxt: String?) {
        val charText = searchTxt?.toLowerCase(Locale.getDefault())
        arrayList.clear()
        if (charText != null) {
            if (charText.isEmpty() || charText.length == 1) {
                arrayList.addAll(filterList)
            } else if(charText.length >= 2){
                filterList.forEach {
                    if (it != null) {
                        val search = it.toLowerCase(Locale.getDefault())
                        if (search.contains(charText)) {
                            arrayList.add(it)
                        }
                    }
                }
            }
        }
        notifyDataSetChanged()
    }*/