package com.saartak.el.dynamicui.activities;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.CheckBox;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bfil.ekyclibrary.activities.EKYCActivity;
import com.bfil.uilibrary.helpers.AppHelper;
import com.saartak.el.R;
import com.saartak.el.database.entity.DynamicUITable;
import com.saartak.el.dynamicui.CustomFields.XMLCustomSpinner;
import com.saartak.el.dynamicui.CustomFields.XMLCustomTIL;
import com.saartak.el.dynamicui.CustomFields.XmlCustomCheckBox;
import com.saartak.el.dynamicui.CustomFields.XmlCustomRadioGroup;
import com.saartak.el.dynamicui.services.DynamicUIApiInterface;
import com.saartak.el.dynamicui.services.DynamicUIWebService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.saartak.el.dynamicui.constants.ParametersConstant.FIELD_TYPE_CHECKBOX;
import static com.saartak.el.dynamicui.constants.ParametersConstant.FIELD_TYPE_DROPDOWN;
import static com.saartak.el.dynamicui.constants.ParametersConstant.FIELD_TYPE_LIST;
import static com.saartak.el.dynamicui.constants.ParametersConstant.FIELD_TYPE_LIST_BOX;
import static com.saartak.el.dynamicui.constants.ParametersConstant.FIELD_TYPE_RADIOBUTTON;
import static com.saartak.el.dynamicui.constants.ParametersConstant.FIELD_TYPE_TEXT_BOX;


public class DynamicViewBaseActivity extends EKYCActivity {

    private static final String TAG =DynamicViewBaseActivity.class.getCanonicalName() ;
//    public Map<View,List<View>> dynamicViews = new HashMap<>();
    public List<View> v=new ArrayList<>();
    public List<DynamicViews> dynamicViews = new ArrayList<>();
    // Read in all of the attributes and create an Item
    public LinearLayout ll;
    AppHelper appHelper;

    public class DynamicViews{
     View view;
     List<View> views;

        public DynamicViews(View view, List<View> views) {
            this.view = view;
            this.views = views;
        }

        public View getView() {
            return view;
        }

        public void setView(View view) {
            this.view = view;
        }

        public List<View> getViews() {
            return views;
        }

        public void setViews(List<View> views) {
            this.views = views;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_view_base);
        dynamicViews.clear();
        appHelper=new AppHelper(this);
    }

    public DynamicUIApiInterface getServicesAPI() {
        return DynamicUIWebService.createService(DynamicUIApiInterface.class);
    }


//    public void addButton(LinearLayout linearLayout, String btnLabel, final ButtonClick buttonClick) {
//        try {
//            View btnView = LayoutInflater.tvName(this).inflate(R.layout.view_btn, null);
//            Button button = (Button) btnView.findViewById(R.id.btn_common);
//            button.setText(btnLabel);
//            button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view1) {
//                    try {
//                        boolean isValid = true;
//                        for (int i = 0; i < dynamicViews.size(); i++) {
//                            DynamicViews dynamicViews1=dynamicViews.get(i);
//                                for(View view:dynamicViews1.getViews()){
//                            Log.d("id", "GET TAG ====>" + view.getTag());
////                                ViewParameters tag=(ViewParameters)view.getTag();
//                            if (view instanceof XMLCustomTIL) {
//                                DynamicUITable params = (DynamicUITable) ((XMLCustomTIL) view).getTag();
//                                if (((XMLCustomTIL) view).isMandatory()) {
//                                    Log.i(TAG, "PARAMS----> " + new Gson().toJson(params));
//                                    String text = ((XMLCustomTIL) view).getEditText().getText().toString().trim();
////                                        String displayName = params.getFieldName();
//                                    String error = params.getErrorMessage();
//                                    if (TextUtils.isEmpty(text)) {
//                                        setError((XMLCustomTIL) view, error);
//                                        isValid = false;
//                                    }
//
//                                     /*   String errorId = params.getDtId();
//                                        String error;
//                                        if (TextUtils.isEmpty(errorId)) {
//                                            error = "Required";
//                                        } else {
//                                            error = getDataTypesList.get(errorId).getDtName();
//                                        }
//                                        if (TextUtils.isEmpty(text) || (!TextUtils.isEmpty(params.getMaxLength())
//                                                && Integer.parseInt(params.getMaxLength()) < text.length())
//                                                || (!TextUtils.isEmpty(params.getMinLength())
//                                                && Integer.parseInt(params.getMinLength()) > text.length())) {
//                                            Log.i(TAG, "error----> " + error);
//                                            setError((XMLCustomTIL) view, error);
//                                            isValid = false;
//                                        } else {
//                                            if (displayName.contains("mobile") || displayName.contains("Mobile")) {
//                                                if (!getAppHelper().patternMatch(AppConstants.REGEX_PATTERN_MOBILE_NUMBER, text)) {
//                                                    setError((XMLCustomTIL) view, error);
//                                                    isValid = false;
//                                                }
//                                            }
//                                        }*/
//                                }
//                            } else if (view instanceof SearchableSpinner) {
//                                DynamicUITable params = (DynamicUITable) ((SearchableSpinner) view).getTag();
//                                if (params != null && ((SearchableSpinner) view).getSelectedItemPosition() <= 0) {
//                                    isValid = false;
//                                    ((SearchableSpinner) view).setHasValidInput(false);
//                                }
//                            } else if (view instanceof XmlCustomCheckBox) {
//                                DynamicUITable params = (DynamicUITable) ((XmlCustomCheckBox) view).getTag();
//                                if (params != null && view.getTag() != null) {
//                                    if (!((XmlCustomCheckBox) view).isChecked()) {
//                                        isValid = false;
//                                        ((XmlCustomCheckBox) view).setError("Please check");
//                                        Log.i(TAG, "error with CheckBox" + view.toString());
//                                    } else {
//                                        ((XmlCustomCheckBox) view).setError(null);
//                                        Log.i(TAG, "error with CheckBox cleared" + view.toString());
//                                    }
//                                }
//                            } else if (view instanceof RadioGroup /*&& view.getTag() != null*/) {
//                                if (((RadioGroup) view).getCheckedRadioButtonId() == -1) {
//                                    isValid = false;
//                                    int total = ((RadioGroup) view).getChildCount();
//                                    for (int j = 0; j < total; j++) {
//                                        View v = ((RadioGroup) view).getChildAt(j);
//                                        if (v instanceof RadioButton) {
//                                            ((RadioButton) v).setError("Please check");
//                                        }
//                                    }
//                                    view.getParent().requestChildFocus(view, view);
//                                    Log.i(TAG, "error with RadioGroup" + view.toString());
//                                } else {
//                                    int total = ((RadioGroup) view).getChildCount();
//                                    for (int j = 0; j < total; j++) {
//                                        View v = ((RadioGroup) view).getChildAt(j);
//                                        if (v instanceof RadioButton) {
//                                            ((RadioButton) v).setError(null);
//                                        }
//                                    }
//                                    view.getParent().requestChildFocus(view, view);
//                                    Log.i(TAG, "Error cleared tvName RadioGroup" + view.toString());
//                                }
//                            }
//
//                             /*  else if (view instanceof CheckBox && view.getTag() != null) {
//                                    if (!((CheckBox) view).isChecked()){
//                                        isValid = false;
//                                        ((CheckBox) view).setError("Please check");
//                                        Log.i(TAG, "error with CheckBox" + view.toString());
//                                    }
//                                }*/
//
//                        }
//                    }
//                        if (isValid) {
//                            if (buttonClick != null) {
//                                buttonClick.onButtonClickSuccess();
//                            }
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//            linearLayout.addView(btnView);
//        } catch (Resources.NotFoundException e) {
//            e.printStackTrace();
//        }
//    }

    public interface ButtonClick {
        void onButtonClickSuccess();
    }

    public void setError(XMLCustomTIL xmlCustomTIL, String error) {
        try {
            xmlCustomTIL.setError(error);
            xmlCustomTIL.setErrorEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addCheckBoxes(LinearLayout ll, DynamicUITable viewParameters) {

        LinearLayout checkBoxLayout = new LinearLayout(this);
        checkBoxLayout.setOrientation(LinearLayout.VERTICAL);

        dynamicViews.add(new DynamicViews(checkBoxLayout,null)); // TODO: 04-04-2019  need to remove null
        addViewToParentLayout(checkBoxLayout);

        String[] paramlist=viewParameters.getParamlist();
        if(paramlist!=null && paramlist.length>0) {
            for (String param : paramlist) {
                CheckBox checkBox = new CheckBox(this);
                checkBox.setText(param);
                setCheckBoxAttributes(checkBox);
                checkBoxLayout.addView(checkBox);
            }
            addLineSeperator(ll);
        }
    }

    public void addCheckBoxesHorizontal(LinearLayout ll, DynamicUITable viewParameters) {

        if(viewParameters.getParamlist().length> 0) {
            HorizontalScrollView mScrollView = new HorizontalScrollView(getApplicationContext());
            mScrollView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            mScrollView.setFillViewport(true);
            LinearLayout attrControlsSubContainer = new LinearLayout(getBaseContext());
            attrControlsSubContainer.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams layoutParams= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);                               attrControlsSubContainer.setLayoutParams(layoutParams);
           attrControlsSubContainer.addView(addTextViews(viewParameters));
            String[] paramlist=viewParameters.getParamlist();
            for(String param:paramlist)
            {
                CheckBox chkAttribute = new CheckBox(getBaseContext());
                chkAttribute.setText(param);
                chkAttribute.setTextColor(Color.BLACK);
//                chkAttribute.setId(attrs.getJSONObject(i).getInt("id"));
                attrControlsSubContainer.addView(chkAttribute);
            }
            mScrollView.addView(attrControlsSubContainer);
            List viewList=new ArrayList();
            viewList.add(mScrollView);
            dynamicViews.add(new DynamicViews(mScrollView,viewList));
            addViewToParentLayout(mScrollView);
            addLineSeperator(ll);

        }
    }

    protected void addRadioButtons(LinearLayout ll, DynamicUITable viewParameters) {

        //RadioButtons are always added inside a RadioGroup
        addViewToParentLayout(addTextViews(viewParameters));
        RadioGroup radioGroup = new RadioGroup(this);
        radioGroup.setOrientation(LinearLayout.HORIZONTAL);
        List viewList=new ArrayList();
        viewList.add(radioGroup);
        dynamicViews.add(new DynamicViews(radioGroup,viewList));
        addViewToParentLayout(radioGroup);
        String[] paramlist=viewParameters.getParamlist();
        for (String param: paramlist) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(param);
            radioGroup.addView(radioButton);
            setRadioButtonAttributes(radioButton);
        }
        addLineSeperator(ll);
    }

    private void setRadioButtonAttributes(RadioButton radioButton) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(convertDpToPixel(16),
                convertDpToPixel(16),
                0, 0
        );

        radioButton.setLayoutParams(params);
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
        getTheme().resolveAttribute(android.R.attr.listChoiceIndicatorMultiple,
                typedValue, true);

        checkBox.setButtonDrawable(null);
        checkBox.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                typedValue.resourceId, 0);
    }


    protected void addLineSeperator(LinearLayout ll) {
        LinearLayout lineSeparatorLayout = new LinearLayout(this);
        lineSeparatorLayout.setBackgroundColor(Color.GRAY);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                2);
        params.setMargins(0, convertDpToPixel(10), 0, convertDpToPixel(10));
        lineSeparatorLayout.setLayoutParams(params);
//        dynamicViews.add(lineSeparatorLayout);
        addViewToParentLayout(lineSeparatorLayout);
    }
    //This function to convert DPs to pixels
    private int convertDpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }

    protected View addTextViews(DynamicUITable viewParameters) {
        //Adding a LinearLayout with HORIZONTAL orientation
        LinearLayout textLinearLayout = new LinearLayout(this);
        textLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(convertDpToPixel(2),
                convertDpToPixel(5),
                0, 0
        );
        params.weight=1;

//        ll.addView(textLinearLayout);

//        for (int i = 1; i <= 3; i++) {
            TextView textView = new TextView(this);
            textView.setText(viewParameters.getFieldName() +" : ");
            setTextViewAttributes(textView);
            textLinearLayout.addView(textView);
            textLinearLayout.setLayoutParams(params);
//        }
//        addLineSeperator();
        return textLinearLayout;
    }

    private void setTextViewAttributes(TextView textView) {
     /*   LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(convertDpToPixel(2),
                convertDpToPixel(5),
                0, 0
        );
        params.weight=1;*/
        textView.setTextColor(Color.BLACK);
        textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
//        textView.setLayoutParams(params);
        textView.setTextSize(16);


    }


    protected void getUIParametersFromServer(String screen) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            getServicesAPI().getUIParameters(screen).enqueue(new Callback<List<DynamicUITable>>() {
                @Override
                public void onResponse(Call<List<DynamicUITable>> call, Response<List<DynamicUITable>> response) {
                    appHelper.getDialogHelper().closeDialog();
                    try {
                        if (response.isSuccessful()) {
                            List<DynamicUITable> list = response.body();
                            if (list != null && list.size() > 0) {
                                dynamicUI(list);
                            }
                        } else {
//                            setSomethingWrong();
                        }
//                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<List<DynamicUITable>> call, Throwable t) {
                    t.printStackTrace();
//                    setSomethingWrong(t);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void dynamicUI(List<DynamicUITable> viewParametersList) {
        try {
            dynamicViews =new ArrayList<>();
            for (DynamicUITable viewParameters : viewParametersList) {
                if ((!TextUtils.isEmpty(viewParameters.getFieldType()) && viewParameters.getFieldType().equalsIgnoreCase(FIELD_TYPE_TEXT_BOX))) {
                    final XMLCustomTIL customTextInputLayout = new XMLCustomTIL(this, viewParameters, appHelper, viewParametersList, new XMLCustomTIL.TextInputListener() {
                        @Override
                        public void callBackForEnableGuardianDetails() {

                        }

                        @Override
                        public void callBackForDisableGuardianDetails() {

                        }

                        @Override
                        public void callBackForUpdateUI() {

                        }

                        @Override
                        public void callBackForUpdateSingleField(DynamicUITable dynamicUITable) {

                        }

                        @Override
                        public void callBackForAutoSum(DynamicUITable dynamicUITable,String toTag) {

                        }

                        @Override
                        public void callBackForSumOfAllFieldsByFeatureId(DynamicUITable dynamicUITable, String resultTag) {

                        }

                        @Override
                        public void callBackForCalcAvgSales(DynamicUITable dynamicUITable, String tag, String tagToDisplayResult,int totalNo) {

                        }

                        @Override
                        public void callBackForCalculatePurchaseAnalysis(DynamicUITable dynamicUITable) {

                        }

                        @Override
                        public void callBackForPurchaseBillsCalculation(DynamicUITable dynamicUITable) {

                        }

                        @Override
                        public void callBackForGSTCalculation(DynamicUITable dynamicUITable) {

                        }

                        @Override
                        public void callBackForDeDupeValidation(DynamicUITable dynamicUITable, View view) {

                        }
                        @Override
                        public void callBackForIFSC(DynamicUITable dynamicUITable, View view) {

                        }
                        @Override
                        public void callBackForLoanAmountValidation(DynamicUITable dynamicUITable, View view) {

                        }

                        @Override
                        public void callBackForAgeValidationJLG(DynamicUITable dynamicUITable, View view) {

                        }

                        @Override
                        public void callBackForEditTextValue(Object o1, Object o2, Object o3) {

                        }
                    });
                    List viewList=new ArrayList();
                    viewList.add(customTextInputLayout);
                    dynamicViews.add(new DynamicViews(customTextInputLayout,viewList));
                    addViewToParentLayout(customTextInputLayout);
                } else if ((!TextUtils.isEmpty(viewParameters.getFieldType()) && viewParameters.getFieldType().equalsIgnoreCase(FIELD_TYPE_CHECKBOX))) {
                       /* XmlCustomCheckBox customCheckBox = new XmlCustomCheckBox(this,viewParameters);
                        dynamicViews.add(customCheckBox);
                        ll.addView(customCheckBox);
                        addLineSeperator();*/
//                        addCheckBoxesHorizontal(ll,viewParameters);
//                        final XmlCustomCheckBox customCheckBox = new XmlCustomCheckBox(this, viewParameters,ll);
//                        dynamicViews.add(customCheckBox);
//                        ll.addView(customCheckBox);

                    HorizontalScrollView mScrollView = new HorizontalScrollView(this);
                    mScrollView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    mScrollView.setFillViewport(true);
                    LinearLayout attrControlsSubContainer = new LinearLayout(this);
                    attrControlsSubContainer.setOrientation(LinearLayout.HORIZONTAL);
                    LinearLayout.LayoutParams layoutParams= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);                               attrControlsSubContainer.setLayoutParams(layoutParams);
                    attrControlsSubContainer.addView(addTextViews(viewParameters));
//                        attrControlsSubContainer.setLayoutParams(layoutParams);


//                        ll.addView(addTextViews(viewParameters));
                    String[] parameterList=viewParameters.getParamlist();
                    List viewList = null;
                    XmlCustomCheckBox customCheckBox=null;
                    for(String checkBoxItem:parameterList){
                        customCheckBox = new XmlCustomCheckBox(this, viewParameters,checkBoxItem);
                        viewList=new ArrayList();
                        viewList.add(customCheckBox);
                        dynamicViews.add(new DynamicViews(customCheckBox,viewList));
                        attrControlsSubContainer.addView(customCheckBox);
                    }
                    dynamicViews.add(new DynamicViews(customCheckBox,viewList));
                    mScrollView.addView(attrControlsSubContainer);
                    addViewToParentLayout(mScrollView);

                } else if ((!TextUtils.isEmpty(viewParameters.getFieldType()) && viewParameters.getFieldType().equalsIgnoreCase(FIELD_TYPE_RADIOBUTTON))) {
//                        addRadioButtons(ll,viewParameters);
                    final XmlCustomRadioGroup customRadioGroup = new XmlCustomRadioGroup(this, viewParameters);
                    List viewList=new ArrayList();
                    viewList.add(customRadioGroup);
                    dynamicViews.add(new DynamicViews(customRadioGroup,viewList));
                }else if ((!TextUtils.isEmpty(viewParameters.getFieldType())
                        && viewParameters.getFieldType().equalsIgnoreCase(FIELD_TYPE_LIST_BOX)
                        || viewParameters.getFieldType().equalsIgnoreCase(FIELD_TYPE_LIST)
                        || viewParameters.getFieldType().equalsIgnoreCase(FIELD_TYPE_DROPDOWN))) { // TODO:  SPINNER
//                       List<String> list= Arrays.asList(getResources().getStringArray(R.array.spinner_mobilename));

                    LinearLayout llParent = new LinearLayout(this);
                    LinearLayout.LayoutParams llParentParrams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    llParent.setOrientation(LinearLayout.HORIZONTAL);

                    llParentParrams.setMargins(0,10,0,0);
                    llParent.setLayoutParams(llParentParrams);

                    llParent.addView(addTextViews(viewParameters));


                    LinearLayout llSpinner = new LinearLayout(this);
                    LinearLayout.LayoutParams llSpinnerParrams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    llSpinner.setOrientation(LinearLayout.VERTICAL);
                    llSpinnerParrams.weight=1;
                    llSpinner.setBackground(this.getResources().getDrawable(R.drawable.bg_custom_border));
                    llSpinner.setLayoutParams(llSpinnerParrams);

                    List<String> list= Arrays.asList(viewParameters.getParamlist());
//                        ll.addView(addTextViews(viewParameters));
                    XMLCustomSpinner customSpinner = new XMLCustomSpinner(this,list,"0",viewParameters,ll);
                    List viewList=new ArrayList();
                    viewList.add(customSpinner);
                    dynamicViews.add(new DynamicViews(customSpinner,viewList));
//                        ll.addView(customSpinner);
//                        addLineSeperator(ll);

                    llSpinner.addView(customSpinner);
                    llParent.addView(llSpinner);

                    addViewToParentLayout(llParent);


                }

            }

            /*addButton(ll, "Validate", new ButtonClick() {
                @Override
                public void onButtonClickSuccess() {
//                    searchTransaction();
                    Toast.makeText(DynamicViewBaseActivity.this,"Validation Success",Toast.LENGTH_SHORT).show();
                }
            });*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addViewToParentLayout(View view){
        try{
            ll.addView(view);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
