package com.swadhaar.los.dynamicui.CustomFields;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.swadhaar.los.database.entity.DynamicUITable;


public class XmlCustomRadioGroup extends RadioGroup {
    Context context;
    public XmlCustomRadioGroup(Context context, DynamicUITable viewParameters){
        super(context);
        this.context=context;
        setTag(viewParameters);
        setOrientation(HORIZONTAL);
        String[] paramlist=viewParameters.getParamlist();
        if(paramlist!=null && paramlist.length>0) {
            for (String param : paramlist) {
                RadioButton radioButton = new RadioButton(context);
                radioButton.setTransformationMethod(null);
                radioButton.setText(param);
                addView(radioButton);
                setRadioButtonAttributes(radioButton);
            }
//            if(paramlist.length>3){
//                setOrientation(VERTICAL);
//            }
        }

        if (viewParameters != null && TextUtils.isEmpty(viewParameters.getValue())) {
            if (!TextUtils.isEmpty(viewParameters.getDefaultValue())) {
                viewParameters.setValue(viewParameters.getDefaultValue());
            }
        }

        // TODO: Setting enabled false for view purpose on screen not for edit
        if(viewParameters !=null && viewParameters.isSync()){
            setEnabled(false);
        }
    }

    private void setRadioButtonAttributes(RadioButton radioButton) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

      /*  params.setMargins(convertDpToPixel(16),
                convertDpToPixel(16),
                0, 0
        );*/

//        params.gravity= Gravity.CENTER_HORIZONTAL;
//        params.weight=1;
        radioButton.setLayoutParams(params);
    }

    //This function to convert DPs to pixels
    private int convertDpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }

}
