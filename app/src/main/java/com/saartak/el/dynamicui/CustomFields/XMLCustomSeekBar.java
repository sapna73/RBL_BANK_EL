package com.saartak.el.dynamicui.CustomFields;

import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;

import com.bfil.uilibrary.helpers.AppHelper;
import com.saartak.el.R;
import com.saartak.el.database.entity.DynamicUITable;
import com.xw.repo.BubbleSeekBar;

import java.util.List;

public class XMLCustomSeekBar extends LinearLayout {

    private static final String TAG = XMLCustomSeekBar.class.getCanonicalName();
    Context context;
    AppHelper appHelper;
    DynamicUITable dynamicUITable;
    List<DynamicUITable> dynamicUITableList;
    SeekBarListener seekBarListener;

    public XMLCustomSeekBar(Context context) {
        super(context);
    }


    public XMLCustomSeekBar(Context context, DynamicUITable viewParameters, AppHelper appHelp, List<DynamicUITable> dynamicUITableList, SeekBarListener seekBarListener) {
        super(context);
        this.context = context;
        this.appHelper = appHelp;
        this.dynamicUITable = viewParameters;
        this.dynamicUITableList = dynamicUITableList;
        this.seekBarListener = seekBarListener;

        setTag(viewParameters);
        ViewGroup.LayoutParams lLayout = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setLayoutParams(lLayout);

        // TODO: Setting enabled false for view purpose on screen not for edit
        if(viewParameters !=null && viewParameters.isSync()){
            setEnabled(false);
        }

        double dMaximumAmount = 1;
        double dMinimumAmount = 0;
        double selectedAmount = 0;
        int iMaxAmount = 1;
        int iMinAmount = 0;
        int iSelectedAmount = 0;
        int tickCount = 0;

        if (!TextUtils.isEmpty(viewParameters.getLength())) {
            dMaximumAmount = Double.valueOf(viewParameters.getLength());
        }
        if (!TextUtils.isEmpty(viewParameters.getMinLength())) {
            dMinimumAmount = Double.valueOf(viewParameters.getMinLength());
        }
        if (!TextUtils.isEmpty(viewParameters.getValue())) {
            selectedAmount = Double.valueOf(viewParameters.getValue());
        }else{
            selectedAmount = dMinimumAmount;
        }


        iMaxAmount = (int) dMaximumAmount;
        iMinAmount = (int) dMinimumAmount;
        iSelectedAmount = (int) selectedAmount;

        int amount = iMinAmount;
        int tempMaxAmount = 0;
        while (amount <= iMaxAmount) {
            tempMaxAmount = amount;
            amount = amount + 5000;
            if (amount > iMaxAmount) {
                iMaxAmount = tempMaxAmount;
                break;
            }
            tickCount++;
        }

//        IndicatorSeekBar seekBar = IndicatorSeekBar
//                .with(getContext())
//                .max(iMaxAmount)
//                .min(iMinAmount)
////                .progress(tickCount)
//                .tickCount(tickCount + 1)
//                .showTickMarksType(TickMarkType.OVAL)
//                .tickMarksColor(ResourcesCompat.getColor(getResources(), R.color.colorBlue, null))
//                .tickMarksSize(13)//dp
////                .showTickTexts(true)
//                .tickTextsColor(getResources().getColor(R.color.colorBitterSweetDark))
//                .tickTextsSize(13)//sp
//                .tickTextsTypeFace(Typeface.MONOSPACE)
//                .showIndicatorType(IndicatorType.ROUNDED_RECTANGLE)
//                .indicatorColor(getResources().getColor(R.color.colorGrapeFruitDark))
//                .indicatorTextColor(Color.parseColor("#ffffff"))
//                .indicatorTextSize(13)//sp
//                .thumbColor(ResourcesCompat.getColor(getResources(), R.color.colorAccent, null))
//                .thumbSize(14)
//                .trackProgressColor(ResourcesCompat.getColor(getResources(), R.color.colorAccent, null))
//                .trackProgressSize(10)
//                .trackBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.graryDark, null))
//                .trackBackgroundSize(2)
//                .onlyThumbDraggable(false)
//                .build();

        BubbleSeekBar bubbleSeekBar=new BubbleSeekBar(getContext());

        bubbleSeekBar.setLayoutParams(lLayout);

        if (tickCount!=0){
            tickCount--;
        }

        if(iSelectedAmount > iMaxAmount){
            iSelectedAmount=iMaxAmount;
        }

        bubbleSeekBar.getConfigBuilder()
                .min(iMinAmount)
                .max(iMaxAmount)
//                .progress(20)
                .sectionCount(tickCount + 1)
                .trackColor(ContextCompat.getColor(getContext(), R.color.colorBlue))
                .secondTrackColor(ContextCompat.getColor(getContext(), R.color.colorBlue))
                .thumbColor(ContextCompat.getColor(getContext(), R.color.colorAccent))
                .showSectionText()
                .sectionTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimary))
                .sectionTextSize(18)
                .showThumbText()
                .thumbTextColor(ContextCompat.getColor(getContext(), R.color.colorAccent))
                .thumbTextSize(18)
                .bubbleColor(ContextCompat.getColor(getContext(), R.color.graryDark))
                .bubbleTextSize(18)
                .showSectionMark()
                .seekBySection()
                .autoAdjustSectionMark()
                .sectionTextPosition(BubbleSeekBar.TextPosition.BOTTOM_SIDES)
                .build();

        addView(bubbleSeekBar);

        bubbleSeekBar.setProgress(iSelectedAmount);

        bubbleSeekBar.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListener() {
            @Override
            public void onProgressChanged(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {

            }

            @Override
            public void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {

            }

            @Override
            public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {
                bubbleSeekBar.correctOffsetWhenContainerOnScrolling();

                dynamicUITable.setValue(progress + "");
                seekBarListener.callBackForCalculateLoanProposal(dynamicUITable);
            }
        });


//        attachTo(seekBar);
//
//        seekBar.setHorizontalFadingEdgeEnabled(true);
//
//        seekBar.setProgress(iSelectedAmount);
//
//
//        seekBar.setOnSeekChangeListener(new OnSeekChangeListener() {
//            @Override
//            public void onSeeking(SeekParams seekParams) {
//                Log.i(TAG, "seekParams " + seekParams.seekBar);
//                Log.i(TAG, "seekParams " + seekParams.progress);
//                Log.i(TAG, "seekParams " + seekParams.progressFloat);
//                Log.i(TAG, "seekParams " + seekParams.fromUser);
//                //when tick count > 0
//                Log.i(TAG, "seekParams " + seekParams.thumbPosition);
//                Log.i(TAG, "seekParams " + seekParams.tickText);
//
//                int value = seekParams.seekBar.getProgress();
//                dynamicUITable.setValue(value + "");
//                seekBarListener.callBackForCalculateLoanProposal(dynamicUITable);
//            }
//
//            @Override
//            public void onStartTrackingTouch(IndicatorSeekBar seekBar) {
//            }
//
//            @Override
//            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {
//            }
//        });
    }


//    public XMLCustomSeekBar(Context context, @Nullable AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public XMLCustomSeekBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        setOrientation(VERTICAL);
//    }

    public interface SeekBarListener {
        void callBackForCalculateLoanProposal(DynamicUITable dynamicUITable);
    }
}
