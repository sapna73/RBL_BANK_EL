package com.swadhaar.los.dynamicui.CustomFields;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bfil.uilibrary.widgets.customSpinner.SearchableSpinner;
import com.swadhaar.los.R;
import com.swadhaar.los.database.entity.DynamicUITable;

import java.util.ArrayList;
import java.util.List;

public class XMLCustomSpinner extends SearchableSpinner {
Context context;
    public ArrayAdapter arrayAdapter;
    public XMLCustomSpinner(Context context, List<String> options, String optional, DynamicUITable params, LinearLayout ll) {
        super(context);
        this.context=context;
        try {
            LayoutParams lLayout = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT);

            setLayoutParams(lLayout);
//            setBackground(context.getResources().getDrawable(R.drawable.bg_custom_border));

//            SearchableSpinner searchableSpinner=new SearchableSpinner(this.context);


            int id = View.generateViewId();
            setId(id);
            setTag(params); // TODO: 04-04-2019  need to check

            if( params != null && TextUtils.isEmpty(params.getValue())) {
                if ( !TextUtils.isEmpty(params.getDefaultValue())) {
                    params.setValue(params.getDefaultValue());
                }
            }

            if( params != null && ! params.isEditable()) {
                setEnabled(false);
            }
            List<String> newOptions=new ArrayList<>();
            newOptions.add("Select "+params.getFieldName()); // TODO: optional condition
            if( options !=null && options.size()>0) {
                String firstItem=options.get(0);
                if( ! TextUtils.isEmpty(firstItem) &&  ! firstItem.equalsIgnoreCase("null")) {
                    newOptions.addAll(options);
                }
            }
             arrayAdapter = new ArrayAdapter(context, R.layout.view_spinner_textheight, newOptions);
            setAdapter(arrayAdapter);

            // TODO: Setting enabled false for view purpose on screen not for edit
            if(params !=null && params.isSync()){
                setEnabled(false);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public XMLCustomSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XMLCustomSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    //This function to convert DPs to pixels
    private int convertDpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }

    protected View addTextViews(DynamicUITable viewParameters) {
        //Adding a LinearLayout with HORIZONTAL orientation
        LinearLayout textLinearLayout = new LinearLayout(this.context);
        textLinearLayout.setOrientation(LinearLayout.HORIZONTAL);

//        ll.addView(textLinearLayout);

//        for (int i = 1; i <= 3; i++) {
        TextView textView = new TextView(this.context);
        textView.setText(viewParameters.getFieldName() +" : ");
        setTextViewAttributes(textView);
        textLinearLayout.addView(textView);
//        }
//        addLineSeperator();
        return textLinearLayout;
    }

    private void setTextViewAttributes(TextView textView) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(convertDpToPixel(2),
                convertDpToPixel(5),
                0, 0
        );
        textView.setTextColor(Color.BLACK);
        textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
        textView.setLayoutParams(params);
    }

}
