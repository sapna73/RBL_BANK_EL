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

import java.util.List;

public class EligibilityHeaderAdapter extends RecyclerView.Adapter<EligibilityHeaderAdapter.ViewHolder>  {

    private Context context;
    List<String> productHeaderList;
    AppHelper appHelper;
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvProductCode;

        public ViewHolder(View view) {
            super(view);
            tvProductCode=(TextView)view.findViewById(R.id.tv_product_code);
        }
    }

    public EligibilityHeaderAdapter(Context context, List<String> productHeaderList, AppHelper appHelper) {
        this.context = context;
        this.productHeaderList = productHeaderList;
        this.appHelper = appHelper;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.eligibility_header_adapter_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            if (productHeaderList != null && productHeaderList.size() > 0) {
                 String productCode=productHeaderList.get(position);
                 holder.tvProductCode.setText(productCode);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        if (productHeaderList != null) {

            return productHeaderList.size();
        } else {
            return 0;
        }
    }

    public void setItem(List<String> productHeaderList) {
        try {
            if (productHeaderList != null && productHeaderList.size() > 0) {
                this.productHeaderList.clear();
                this.productHeaderList = productHeaderList;
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void clearItems() {
        try {
            if (productHeaderList != null && productHeaderList.size() > 0) {
                productHeaderList.clear();
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



}
