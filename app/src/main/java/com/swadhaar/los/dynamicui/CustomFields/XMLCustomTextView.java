package com.swadhaar.los.dynamicui.CustomFields;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.swadhaar.los.database.entity.DynamicUITable;

import androidx.appcompat.widget.AppCompatTextView;

public class XMLCustomTextView extends AppCompatTextView {
    public XMLCustomTextView(Context context, DynamicUITable dynamicUITable) {
        super(context);
        this.context=context;
        setTag(dynamicUITable);

        LinearLayout.LayoutParams lLayout = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        setLayoutParams(lLayout);
        setPadding(5,0,0,0);
        setText(dynamicUITable.getFieldName());
//        lLayout.gravity=TEXT_ALIGNMENT_CENTER;
        setLayoutParams(lLayout);
    }

    public XMLCustomTextView(Context context, AttributeSet attrs, Context context1) {
        super(context, attrs);
        this.context = context1;
    }

    public XMLCustomTextView(Context context, AttributeSet attrs, int defStyleAttr, Context context1) {
        super(context, attrs, defStyleAttr);
        this.context = context1;
    }

    Context context;
}
