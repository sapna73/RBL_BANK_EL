package com.saartak.el.dynamicui.CustomFields;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatCheckBox;

import com.saartak.el.database.entity.DynamicUITable;


/**
 * Created by Ali Hussain on 24-09-2018.
 */

public class XmlCustomCheckBox extends AppCompatCheckBox {


    CheckBox checkBox;
    Context context;

    public XmlCustomCheckBox(Context context, DynamicUITable viewParameters, String checkboxName) {
        super(context);
        this.context=context;
        checkBox = new CheckBox(context);
        checkBox.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setText(checkboxName);
        setTextSize(18);
        setTag(viewParameters);

        if(viewParameters !=null && ! TextUtils.isEmpty(viewParameters.getValue())
            && viewParameters.getValue().equalsIgnoreCase(checkboxName)){
            setChecked(true);
        }
//        addCheckBoxesHorizontal(linearLayout,viewParameters);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isChecked()){
                    viewParameters.setValue(null);
                }else {
                    viewParameters.setValue(checkboxName);
                }
//                viewParameters.setValue(checkboxName);
            }
        });

        // TODO: Setting enabled false for view purpose on screen not for edit
        if(viewParameters !=null && viewParameters.isSync()){
            setEnabled(false);
        }
    }


    public void addCheckBoxesHorizontal(LinearLayout ll, DynamicUITable viewParameters) {

        if(viewParameters.getParamlist().length> 0) {
            HorizontalScrollView mScrollView = new HorizontalScrollView(context);
            mScrollView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            mScrollView.setFillViewport(true);
            LinearLayout attrControlsSubContainer = new LinearLayout(context);
            attrControlsSubContainer.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams layoutParams= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            attrControlsSubContainer.setLayoutParams(layoutParams);
            attrControlsSubContainer.addView(addTextViews(viewParameters));
            String[] paramlist=viewParameters.getParamlist();
            for(String param:paramlist)
            {
                CheckBox chkAttribute = new CheckBox(context);
                chkAttribute.setText(param);
                chkAttribute.setTextColor(Color.BLACK);
//                chkAttribute.setId(attrs.getJSONObject(i).getInt("id"));
                attrControlsSubContainer.addView(chkAttribute);
            }
            mScrollView.addView(attrControlsSubContainer);
//            dynamicViews.add(mScrollView); // TODO: 04-04-2019 need to add
            ll.addView(mScrollView);
            addLineSeperator(ll);

        }
    }

    public XmlCustomCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XmlCustomCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public String getValue() {
        return checkBox.getText().toString();
    }

    public void setValue(String v) {
        checkBox.setText(v);
    }

    private void setCheckBoxAttributes(CheckBox checkBox) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(convertDpToPixel(16),
                convertDpToPixel(16),
                convertDpToPixel(16),
                0
        );

        checkBox.setLayoutParams(params);

        //This is used to place the checkbox on the right side of the textview
        //By default, the checkbox is placed at the left side
        TypedValue typedValue = new TypedValue();
        this.context.getTheme().resolveAttribute(android.R.attr.listChoiceIndicatorMultiple,
                typedValue, true);

        checkBox.setButtonDrawable(null);
        checkBox.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                typedValue.resourceId, 0);
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
        textView.setTextSize(18);
        textView.setLayoutParams(params);
    }



    protected void addLineSeperator(LinearLayout ll) {
        LinearLayout lineSeparatorLayout = new LinearLayout(context);
        lineSeparatorLayout.setBackgroundColor(Color.GRAY);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                2);
        params.setMargins(0, convertDpToPixel(10), 0, convertDpToPixel(10));
        lineSeparatorLayout.setLayoutParams(params);
        ll.addView(lineSeparatorLayout);
    }
    //This function to convert DPs to pixels
    private int convertDpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }

    protected View addTextViews(DynamicUITable viewParameters) {
        //Adding a LinearLayout with HORIZONTAL orientation
        LinearLayout textLinearLayout = new LinearLayout(context);
        textLinearLayout.setOrientation(LinearLayout.HORIZONTAL);

//        ll.addView(textLinearLayout);

//        for (int i = 1; i <= 3; i++) {
        TextView textView = new TextView(context);
        textView.setText(viewParameters.getFieldName() +" : ");
        setTextViewAttributes(textView);
        textLinearLayout.addView(textView);
//        }
//        addLineSeperator();
        return textLinearLayout;
    }

}
