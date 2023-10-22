package com.saartak.el;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import com.saartak.el.adapter.SearchArrayAdapter;
import com.saartak.el.interfaces.ReturnValue;

import java.util.ArrayList;

public class PopUtils {

    public static Dialog dialogList;
    public static void dialogListReturnValueRV(Context mContext, ArrayList<String> arrayList, final EditText mEditText,final ReturnValue returnValue) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            dialogList = new Dialog(mContext, android.R.style.Theme_Material_Light_Dialog);
        } else {
            dialogList = new Dialog(mContext);
        }
        dialogList.getWindow().setBackgroundDrawableResource(R.color.white);
        dialogList.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogList.setContentView(R.layout.dialog_recycler_view_search);
        dialogList.setCancelable(true);
        dialogList.setCanceledOnTouchOutside(true);

        TextView txtTitle = dialogList.findViewById(R.id.txtTitle);
        TextView txtCancel = dialogList.findViewById(R.id.txtCancel);
        TextView txtSubmit = dialogList.findViewById(R.id.txtSubmit);

        txtCancel.setVisibility(View.GONE);
        txtSubmit.setVisibility(View.GONE);

        ImageView ivCancel = dialogList.findViewById(R.id.ivCancel);
        SearchView edtSearch = dialogList.findViewById(R.id.edtSearch);
        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogList.dismiss();
            }
        });
        RecyclerView recyclerView = dialogList.findViewById(R.id.dialog_recycler_view);
        SearchArrayAdapter arrayListAdapter = new SearchArrayAdapter(mContext, arrayList, returnValue, mEditText, dialogList);
        recyclerView.setAdapter(arrayListAdapter);
        edtSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                try {
                    arrayListAdapter.filterNew(s.trim());
                } catch (Exception e) {

                }
                return false;
            }
        });

        int width = (int) (mContext.getResources().getDisplayMetrics().widthPixels * 0.90);
        int height = (int) (mContext.getResources().getDisplayMetrics().heightPixels * 0.90);
        dialogList.getWindow().setLayout(width, height);

        dialogList.show();
    }
}
