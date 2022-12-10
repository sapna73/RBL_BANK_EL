package com.swadhaar.los.dynamicui.CustomFields;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.swadhaar.los.database.entity.DynamicUITable;

public class XMLCustomView extends View {
    Context context;
    public XMLCustomView(Context context, DynamicUITable dynamicUITable) {
        super(context);
        this.context=context;
        setTag(dynamicUITable);

        LinearLayout.LayoutParams lLayout = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        setLayoutParams(lLayout);
       /* if(dynamicUITable.isVisibility()){
            setVisibility(View.VISIBLE);
        }else{
            setVisibility(View.GONE);
        }*/

        // TODO: default visibility false always
        setVisibility(View.VISIBLE);
    }

    public XMLCustomView(Context context) {
        super(context);
    }

    public XMLCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public XMLCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


}
