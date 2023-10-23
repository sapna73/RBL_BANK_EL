package com.saartak.el.dynamicui.CustomFields;

import android.content.Context;
import android.graphics.Color;
import android.text.InputType;
import android.view.Gravity;

import com.chaos.view.PinView;
import com.saartak.el.R;

import androidx.core.content.res.ResourcesCompat;

public class XMLPinView extends PinView {
    public XMLPinView(Context context) {
        super(context);


        setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorAccent, context.getTheme()));
        setTextColor(ResourcesCompat.getColorStateList(getResources(), R.color.colorPrimaryDark,context.getTheme()));
        setLineColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, context.getTheme()));
        setLineColor(ResourcesCompat.getColorStateList(getResources(), R.color.colorPrimaryDark, context.getTheme()));
        setItemCount(4);
        setItemHeight(getResources().getDimensionPixelSize(R.dimen.pv_pin_view_item_size));
        setItemWidth(getResources().getDimensionPixelSize(R.dimen.pv_pin_view_item_size));
        setItemRadius(getResources().getDimensionPixelSize(R.dimen.pv_pin_view_item_radius));
        setItemSpacing(getResources().getDimensionPixelSize(R.dimen.pv_pin_view_item_spacing));
        setInputType(InputType.TYPE_CLASS_NUMBER);
        setLineWidth(getResources().getDimensionPixelSize(R.dimen.pv_pin_view_item_line_width));
        setAnimationEnable(true);// start animation when adding text
        setCursorVisible(false);
        setCursorColor(ResourcesCompat.getColor(getResources(), R.color.colorGreen, context.getTheme()));
        setCursorWidth(getResources().getDimensionPixelSize(R.dimen.pv_pin_view_cursor_width));
        setGravity(Gravity.CENTER);
        setItemBackgroundColor(Color.WHITE);
        /*setItemBackground(getResources().getDrawable(R.drawable.item_background));
        setItemBackgroundResources(R.drawable.item_background);*/
        setHideLineWhenFilled(false);

    }



}
