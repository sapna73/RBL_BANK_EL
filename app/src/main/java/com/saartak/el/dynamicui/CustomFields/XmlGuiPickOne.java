/*
 * XmlGui application.
 * Written by Frank Ableson for IBM Developerworks
 * June 2010
 * Use the code as you wish -- no warranty of fitness, etc, etc.
 */
package com.saartak.el.dynamicui.CustomFields;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bfil.uilibrary.widgets.customSpinner.SearchableSpinner;
import com.saartak.el.R;
import com.saartak.el.database.entity.DynamicUITable;

public class XmlGuiPickOne extends LinearLayout {

    private static final String TAG = XmlGuiPickOne.class.getCanonicalName();

    public XmlGuiPickOne(Context context, String labelText, String[] options, String optional, DynamicUITable tagName) {
        super(context);
        try {
            ViewGroup.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
            ((LayoutParams) params).setMargins(5, 5, 0, 10);
            setLayoutParams(params);

            TextView label = new TextView(context, null, R.style.CustomRadioBtnLblStyle);
            label.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,0.5f));
            label.setText(labelText);
            label.setTextColor(context.getResources().getColor(android.R.color.black));
            ViewGroup.LayoutParams params1 = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0.5f);
            SearchableSpinner spinner = new SearchableSpinner(new ContextThemeWrapper(context, R.style.mySpinnerItemStyle), null, 0);
            spinner.setLayoutParams(params1);
            ArrayAdapter<String> aa = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, options);
            spinner.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_custom_border1));
            spinner.setAdapter(aa);
            int id = View.generateViewId();
            spinner.setId(id);
            this.setOrientation(LinearLayout.HORIZONTAL);
            this.addView(label);
            this.addView(spinner);

            if (optional.equalsIgnoreCase("0")){
                setTag(tagName);
                spinner.setTag(tagName);
            } else {
                setTag(null);
                spinner.setTag(null);
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    public XmlGuiPickOne(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public String getValue() {
        String value = "";
        try {
            int i = 0;
            for(int count = getChildCount(); i < count; ++i) {
                View view = getChildAt(i);
                Log.i(TAG, "Checking with" + view.toString());
                if (view instanceof SearchableSpinner && view.getTag() != null) {
                    value = ((SearchableSpinner) view).getSelectedItem().toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }
}
