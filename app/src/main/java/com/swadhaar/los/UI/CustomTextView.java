package com.swadhaar.los.UI;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;

import com.swadhaar.los.R;

public class CustomTextView extends AppCompatTextView {
    Context context;

    public CustomTextView(Context context) {
        super(context);
            applyCustomFont(context, null);
    }

//    public static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        applyCustomFont(context, attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        applyCustomFont(context, attrs);
    }

    private void applyCustomFont(Context context, AttributeSet attrs) {
        TypedArray attributeArray = context.obtainStyledAttributes(
                attrs,
                R.styleable.CustomTextView, 0, 0);

        int fontName = attributeArray.getInt(R.styleable.CustomTextView_font_typeface, 0);
        Typeface font;
        switch (fontName) {



            case 0:
                font = Typeface.createFromAsset(context.getAssets(), "fonts/OpenSans-Light.ttf");
                setTypeface(font);
                break;
            case 1:
                font = Typeface.createFromAsset(context.getAssets(), "fonts/OpenSans-Medium.ttf");
                setTypeface(font);
                break;
            case 2:
                font = Typeface.createFromAsset(context.getAssets(), "fonts/OpenSans-Bold.ttf");
                setTypeface(font);
                break;
            case 3:
                font = Typeface.createFromAsset(context.getAssets(), "fonts/OpenSans-BoldItalic.ttf");
                setTypeface(font);
                break;

            case 4:
                font = Typeface.createFromAsset(context.getAssets(), "fonts/OpenSans-ExtraBold.ttf");
                setTypeface(font);
                break;

            case 5:
                font = Typeface.createFromAsset(context.getAssets(), "fonts/OpenSans-ExtraBoldItalic.ttf");
                setTypeface(font);
                break;
            case 6:
                font = Typeface.createFromAsset(context.getAssets(), "fonts/OpenSans-Italic.ttf");
                setTypeface(font);
                break;
            case 7:
                font = Typeface.createFromAsset(context.getAssets(), "fonts/OpenSans-LightItalic.ttf");
                setTypeface(font);
                break;
            case 8:
                font = Typeface.createFromAsset(context.getAssets(), "fonts/OpenSans-Regular.ttf");
                setTypeface(font);
                break;
            case 9:
                font = Typeface.createFromAsset(context.getAssets(), "fonts/OpenSans-Semibold.ttf");
                setTypeface(font);
                break;

            default:
                setTypeface(Typeface.DEFAULT);
                break;


        }


        attributeArray.recycle();
    }


    @Override
    public void setTypeface(Typeface tf) {

        // TODO Auto-generated method stub
        super.setTypeface(tf);
    }

}

