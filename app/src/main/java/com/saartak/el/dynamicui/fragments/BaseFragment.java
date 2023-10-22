package com.saartak.el.dynamicui.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;

import com.bfil.ekyclibrary.activities.SmallCaptureActivity;
import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.bfil.uilibrary.helpers.AppHelper;
import com.bfil.uilibrary.widgets.customEditText.CustomEditText;
import com.bfil.uilibrary.widgets.customSpinner.SearchableSpinner;
import com.bfil.uilibrary.widgets.textInputLayout.CustomTextInputLayout;
import com.google.zxing.integration.android.IntentIntegrator;
import com.saartak.el.App;
import com.saartak.el.R;
import com.saartak.el.database.entity.DynamicUITable;
import com.saartak.el.database.entity.RawDataTable;
import com.saartak.el.dynamicui.CustomFields.XMLCustomTIL;
import com.saartak.el.dynamicui.services.DynamicUIApiInterface;
import com.saartak.el.dynamicui.services.DynamicUIWebService;
import com.saartak.el.fragments.ChildFragment;
import com.saartak.el.interfce.FragmentToActivityInterface;
import com.saartak.el.models.ReferenceCheckContactDTO;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static com.bfil.uilibrary.dialogs.ConfirmationDialog.ERROR;
import static com.bfil.uilibrary.helpers.AppConstants.CAMERA_PERMISSION_CODE;
import static com.bfil.uilibrary.helpers.AppConstants.permissions;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_AHL;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_INDIVIDUAL;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_JLG;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_MSME;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_PHL;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_TW;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_TWL;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_EL;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_APPLICANT;
import static com.saartak.el.constants.AppConstant.PRODUCT_ID_IL;
import static com.saartak.el.constants.AppConstant.PRODUCT_ID_MSME;
import static com.saartak.el.constants.AppConstant.PROJECT_ID_EL;
import static com.saartak.el.constants.AppConstant.SCREEN_N0_ACCOUNT_PAYABLE_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_N0_ADVANCES;
import static com.saartak.el.constants.AppConstant.SCREEN_N0_ADVANCES_DEBTS_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_N0_BUSINESS_DEBTS;
import static com.saartak.el.constants.AppConstant.SCREEN_N0_BUSINESS_DEBTS_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_N0_DEBTS_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_N0_DECISIONS_HYPOTHECATION_DETAIL_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_N0_DIRECT_BUSINESS_EXPENSE_DETAIL_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_N0_EMPLOYER_VERIFICATION;
import static com.saartak.el.constants.AppConstant.SCREEN_N0_FAMILY_MEMBER_INCOME;
import static com.saartak.el.constants.AppConstant.SCREEN_N0_GENERAL_INCOME_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_N0_ITR_DETAIL_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_N0_LIABILITIES;
import static com.saartak.el.constants.AppConstant.SCREEN_N0_MONTHLY_TRANSACTION_DETAIL_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_N0_OTHER_INCOME_SOURCE_MEME;
import static com.saartak.el.constants.AppConstant.SCREEN_N0_PRODUCT;
import static com.saartak.el.constants.AppConstant.SCREEN_N0_PRODUCT_RAW_MATERIAL_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_N0_PURCHASE_BILLS_DETAIL_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_N0_SALES_BILLS_DETAIL_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_N0_SERVICE_RAW_MATERIAL_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_ADVANCE_DETAIL__MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_ADVANCES;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_APPLICANT_KYC;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_BANKING_HISTORY_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_BANK_DETAILS;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_BUSINESS_ACCOUNT_PAYABLE;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_BUSINESS_ASSETS;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_BUSINESS_DEBTS;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_BUSINESS_DEBTS_DETAIL_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_BUSINESS_LIABILITIES;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_BUSINESS_LIABILITIES_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_BUSINESS_PROOF;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_COAPPLICANT_PERSONAL_DETAIL;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_CO_APPLICANT_BANK_DETAILS;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_CO_APPLICANT_KYC;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_DIRECT_BUSINESS_EXPENSE_DETAIL_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_DIRECT_BUSINESS_EXPENSE_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_EMPLOYER_VERIFICATION;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_FAMILY_MEMBER_INCOME;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_GENERAL_INCOME;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_HOUSE_DEBTS;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_HOUSE_INCOME;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_HOUSE_LIABILITIES;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_HOUSE_LIABILITIES_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_HYPOTHECATION_DETAIL_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_HYPOTHECATION_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_ITR_DETAIL_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_ITR_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_LIABILITIES;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_LOAN_APPROVAL_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_LOAN_PROPOSAL;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_MONTHLY_TRANSACTION_DETAIL_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_OTHER_INCOME_SOURCE;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_PERSONAL_DETAIL;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_PRODUCT;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_PRODUCT_ESTIMATE_DETAIL_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_PRODUCT_ESTIMATE_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_PRODUCT_RAW_MATERIAL_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_PURCHASE_BILLS_DETAIL_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_PURCHASE_BILLS_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_REFERENCES;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_REFERENCE_CHECK;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_SALES_BILLS_DETAIL_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_SALES_BILLS_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_SERVICE_ESTIMATE_DETAIL_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_SERVICE_ESTIMATE_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_SERVICE_RAW_MATERIAL_MSME;
import static com.saartak.el.dynamicui.constants.ParametersConstant.FIELD_TYPE_NEW_LIABILITY;
import static com.saartak.el.dynamicui.constants.ParametersConstant.FIELD_TYPE_NEW_ROW;
import static com.saartak.el.dynamicui.constants.ParametersConstant.RADIO_BUTTON_ITEM_MANUFACTURING;
import static com.saartak.el.dynamicui.constants.ParametersConstant.RADIO_BUTTON_ITEM_SERVICE;
import static com.saartak.el.dynamicui.constants.ParametersConstant.RADIO_BUTTON_ITEM_TRADE;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SPINNER_ITEM_FIELD_NAME_AGRICULTURE;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SPINNER_ITEM_FIELD_NAME_GUARANTOR_HUF;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SPINNER_ITEM_FIELD_NAME_GUARANTOR_INDIVIDUAL;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SPINNER_ITEM_FIELD_NAME_GUARANTOR_PARTNERSHIP_LLP;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SPINNER_ITEM_FIELD_NAME_HOUSEWIFE;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SPINNER_ITEM_FIELD_NAME_HOUSE_WIFE;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SPINNER_ITEM_FIELD_NAME_LIVESTOCK;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SPINNER_ITEM_FIELD_NAME_MANUFACTURE;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SPINNER_ITEM_FIELD_NAME_NOT_WORKING;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SPINNER_ITEM_FIELD_NAME_PENSIONER;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SPINNER_ITEM_FIELD_NAME_SALARIED;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SPINNER_ITEM_FIELD_NAME_SELF_EMPLOYED;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SPINNER_ITEM_FIELD_NAME_SERVICE;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SPINNER_ITEM_FIELD_NAME_STUDENT;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SPINNER_ITEM_FIELD_NAME_TRADE;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_ACCOUNT_NUMBER;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_BANK_NAME;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_BUSINESS_ID;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_BUSINESS_SUB_SECTOR;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_CONTACT_NO;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_CONTACT_NO_1;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_FULL_NAME;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_GUARANTOR;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_KYC_ID;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_KYC_TYPE;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_LOAN_REQUESTED_AMOUNT;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_MONTHLY_INCOME_IN_GENERAL_INCOME;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_MONTHLY_INCOME_IN_OTHER_INCOME;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_NAME_IN_GENERAL_INCOME;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_NAME_IN_OTHER_INCOME;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_OCCUPATION;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_REFERENCE_TYPE;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_REFERRAL_INFO;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_TOTAL_MONTHLY_SALES_IN_LOAN_APPROVAL_MSME;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_TYPE_OF_BUSINESS;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_VERIFIED_INCOME_IN_GENERAL_INCOME;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_VERIFIED_INCOME_IN_OTHER_INCOME;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_VERIFIED_IN_GENERAL_INCOME;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_VERIFIED_IN_OTHER_INCOME;

import org.json.JSONException;
import org.json.JSONObject;

public class BaseFragment extends Fragment {

    private static final String TAG = BaseFragment.class.getCanonicalName();

    public List<View> v = new ArrayList<>();
    //    public List<DynamicViews> dynamicViews = new ArrayList<>();
    public List<View> dynamicViews = new ArrayList<>();

    public LinearLayout ll;

    public FrameLayout mRootView;

    public AppHelper appHelper;

    public String SCREEN_ID;

    public String SCREEN_NAME;

    public String USER_ID;

    // TODO: Rename and change types of parameters
    public String LOAN_TYPE;

    public String CLIENT_ID;

    public String PROJECT_ID,PRODUCT_ID,MODULE_TYPE,CORRELATION_ID;
    private static final AtomicInteger NEXT_GENERATED_ID = new AtomicInteger(1);
    private static final Map<String, List<String>> CHILD_FRAGMENT_TAGS_MAP = new HashMap<>();
//    private FrameLayout mRootView;
//    private String mParentId;

    public List<ReferenceCheckContactDTO> applicantPersonalDetailList;

    public List<ReferenceCheckContactDTO> coApplicantPersonalDetailList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_dynamic_view_base);
        dynamicViews.clear();
        appHelper = new AppHelper(getActivity());
    }

    public DynamicUIApiInterface getServicesAPI() {
        return DynamicUIWebService.createService(DynamicUIApiInterface.class);
    }

    public interface ButtonClick {
        void onButtonClick();
    }

    public void setError(XMLCustomTIL xmlCustomTIL, String error) {
        try {
            xmlCustomTIL.setError(error);
            xmlCustomTIL.setErrorEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    protected void addLineSeperator(LinearLayout ll) {
        LinearLayout lineSeparatorLayout = new LinearLayout(getActivity());
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
        LinearLayout textLinearLayout = new LinearLayout(getActivity());
        textLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(convertDpToPixel(2),
                convertDpToPixel(2),
                0,  convertDpToPixel(4)
        );
        params.weight = 1;

        TextView textView = new TextView(getActivity());
        //textView.setText(viewParameters.getFieldName() + " ");
        if (!TextUtils.isEmpty(viewParameters.getIsRequired())&&viewParameters.getIsRequired().equalsIgnoreCase("Y")&&!TextUtils.isEmpty(viewParameters.getFieldName())) {
            textView.setText(viewParameters.getFieldName() + "*");
        }else if (!TextUtils.isEmpty(viewParameters.getIsRequired())&&viewParameters.getIsRequired().equalsIgnoreCase("N")&&!TextUtils.isEmpty(viewParameters.getFieldName())) {
            textView.setText(viewParameters.getFieldName() + " ");
        }else {
            textView.setText(viewParameters.getFieldName() + " ");
        }
        setTextViewAttributes(textView);
        textLinearLayout.addView(textView);
        textLinearLayout.setLayoutParams(params);
        return textLinearLayout;
    }

    // TODO: TEXT VIEW LABLE
    protected View addTextViewLable(DynamicUITable viewParameters,List<DynamicUITable> dynamicUITableList) {
        //Adding a LinearLayout with HORIZONTAL orientation
        LinearLayout textLinearLayout = new LinearLayout(getActivity());
        textLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(convertDpToPixel(5),
                convertDpToPixel(15),
                0, 0);
        params.weight = convertDpToPixel(0.5f);

        // TODO:
        textLinearLayout.setTag(viewParameters);

        TextView textView = new TextView(getActivity());
        textView.setTag(viewParameters);
        if(viewParameters!=null && viewParameters.getFieldName()!=null)
            textView.setText(viewParameters.getFieldName().toUpperCase());

        setTextViewAttributesForLable(textView, viewParameters);
        if(viewParameters != null && !TextUtils.isEmpty(viewParameters.getFieldTag()) &&
                viewParameters.getFieldTag().equalsIgnoreCase(FIELD_TYPE_NEW_LIABILITY)){
            textView.setTextColor(Color.DKGRAY);
        }

        textLinearLayout.addView(textView);
        textLinearLayout.setLayoutParams(params);
        return textLinearLayout;
    }

    // TODO: TEXT VIEW LABEL FOR NEW ROW
    protected View addTextViewLableForNewRow(DynamicUITable viewParameters, List<DynamicUITable> dynamicUITableList) {
        //Adding a LinearLayout with HORIZONTAL orientation
        LinearLayout textLinearLayout = new LinearLayout(getActivity());
        textLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(convertDpToPixel(5),
                convertDpToPixel(15),
                0, 0
        );
        params.weight = convertDpToPixel(0.5f);

        // TODO:
        textLinearLayout.setTag(viewParameters);

        TextView textView = new TextView(getActivity());
        textView.setTag(viewParameters);
        if(viewParameters!=null && viewParameters.getFieldName() != null)
            textView.setText(viewParameters.getFieldName().toUpperCase());

        // TODO: set attributes for new row label
        setTextViewAttributesForNewRowLable(textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getTag() != null){
                    DynamicUITable dynamicUITable = (DynamicUITable)v.getTag();
                    if(dynamicUITable != null){
                        if (dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_HOUSE_LIABILITIES)
                                && dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_INDIVIDUAL)) {

                            removeAllChildFragments(ll.getId() + "");
                            initChild(LOAN_TYPE,CLIENT_ID, PROJECT_ID_EL,PRODUCT_ID_IL,SCREEN_N0_LIABILITIES,
                                    SCREEN_NAME_LIABILITIES, dynamicUITableList, dynamicUITable, dynamicUITable.getCoRelationID());

                        } else if (dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS)&&dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_INDIVIDUAL)) {
                            removeAllChildFragments(ll.getId()+"");
                            initChild(LOAN_TYPE, CLIENT_ID, PROJECT_ID_EL, PRODUCT_ID_IL, SCREEN_N0_PRODUCT,
                                    SCREEN_NAME_PRODUCT,dynamicUITableList,dynamicUITable,dynamicUITable.getCoRelationID());
                        }else if(dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_BUSINESS_LIABILITIES)
                                && dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_INDIVIDUAL)){

                            if(dynamicUITable.getFieldTag().contains(SCREEN_NAME_BUSINESS_DEBTS)){
                                removeAllChildFragments(ll.getId() + "");
                                initChild(LOAN_TYPE, CLIENT_ID, PROJECT_ID_EL, PRODUCT_ID_IL, SCREEN_N0_BUSINESS_DEBTS,
                                        SCREEN_NAME_BUSINESS_DEBTS, dynamicUITableList, dynamicUITable,dynamicUITable.getCoRelationID());
                            }else if (dynamicUITable.getFieldTag().contains(SCREEN_NAME_ADVANCES)){
                                removeAllChildFragments(ll.getId()+"");
                                initChild(LOAN_TYPE,CLIENT_ID, PROJECT_ID_EL,PRODUCT_ID_IL,SCREEN_N0_ADVANCES,
                                        SCREEN_NAME_ADVANCES,dynamicUITableList,dynamicUITable,dynamicUITable.getCoRelationID());
                            }
                        }else if (dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_HOUSE_INCOME)
                                && dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_INDIVIDUAL)) {

                            removeAllChildFragments(ll.getId()+"");
                            initChild(LOAN_TYPE,CLIENT_ID, PROJECT_ID_EL,PRODUCT_ID_IL,SCREEN_N0_FAMILY_MEMBER_INCOME,
                                    SCREEN_NAME_FAMILY_MEMBER_INCOME,dynamicUITableList,dynamicUITable,dynamicUITable.getCoRelationID());
                        }else if (dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_APPLICANT_KYC)) {

                            removeAllChildFragments(ll.getId()+"");
                            initChild(LOAN_TYPE,CLIENT_ID, PROJECT_ID_EL,PRODUCT_ID_IL,dynamicUITable.getScreenID(),
                                    SCREEN_NAME_APPLICANT_KYC,dynamicUITableList,dynamicUITable,dynamicUITable.getCoRelationID());
                        }else if (dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_CO_APPLICANT_KYC)) {

                            removeAllChildFragments(ll.getId()+"");
                            initChild(LOAN_TYPE,CLIENT_ID, PROJECT_ID_EL,PRODUCT_ID_IL,dynamicUITable.getScreenID(),
                                    SCREEN_NAME_CO_APPLICANT_KYC,dynamicUITableList,dynamicUITable,dynamicUITable.getCoRelationID());

                        }else if (dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_REFERENCE_CHECK)) {
                            removeAllChildFragments(ll.getId()+"");
                            initChild(LOAN_TYPE,CLIENT_ID, PROJECT_ID_EL,PRODUCT_ID_IL,dynamicUITable.getScreenID(),
                                    SCREEN_NAME_REFERENCE_CHECK,dynamicUITableList,dynamicUITable,dynamicUITable.getCoRelationID());
                        }else if(dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_MSME)
                                && dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_BANK_DETAILS)){

                            removeAllChildFragments(ll.getId()+"");
                            initChild(LOAN_TYPE,CLIENT_ID, PROJECT_ID_EL,PRODUCT_ID_MSME,dynamicUITable.getScreenID(),
                                    SCREEN_NAME_BANK_DETAILS,dynamicUITableList,dynamicUITable,dynamicUITable.getCoRelationID());

                        }else if(dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_AHL)
                                && dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_BANK_DETAILS)){
                            // TODO: AHL bank details update
                            removeAllChildFragments(ll.getId()+"");
                            initChild(LOAN_TYPE,CLIENT_ID, PROJECT_ID_EL,PRODUCT_ID_MSME,dynamicUITable.getScreenID(),
                                    SCREEN_NAME_BANK_DETAILS,dynamicUITableList,dynamicUITable,dynamicUITable.getCoRelationID());

                        }
                        else if(dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_PHL)
                                && dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_BANK_DETAILS)){
                            // TODO: PHL bank details update
                            removeAllChildFragments(ll.getId()+"");
                            initChild(LOAN_TYPE,CLIENT_ID, PROJECT_ID_EL,PRODUCT_ID_MSME,dynamicUITable.getScreenID(),
                                    SCREEN_NAME_BANK_DETAILS,dynamicUITableList,dynamicUITable,dynamicUITable.getCoRelationID());

                        }else if(dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_EL)
                                && dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_BANK_DETAILS)){
                            // TODO: PHL bank details update
                            removeAllChildFragments(ll.getId()+"");
                            initChild(LOAN_TYPE,CLIENT_ID, PROJECT_ID_EL,PRODUCT_ID_MSME,dynamicUITable.getScreenID(),
                                    SCREEN_NAME_BANK_DETAILS,dynamicUITableList,dynamicUITable,dynamicUITable.getCoRelationID());

                        }else if(dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_TWL)
                                && dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_BANK_DETAILS)){
                            // TODO: PHL bank details update
                            removeAllChildFragments(ll.getId()+"");
                            initChild(LOAN_TYPE,CLIENT_ID, PROJECT_ID_EL,PRODUCT_ID_MSME,dynamicUITable.getScreenID(),
                                    SCREEN_NAME_BANK_DETAILS,dynamicUITableList,dynamicUITable,dynamicUITable.getCoRelationID());

                        }
                        else if(dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_MSME)
                                && dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_CO_APPLICANT_BANK_DETAILS)){

                            removeAllChildFragments(ll.getId()+"");
                            initChild(LOAN_TYPE,CLIENT_ID, PROJECT_ID_EL,PRODUCT_ID_MSME,dynamicUITable.getScreenID(),
                                    SCREEN_NAME_CO_APPLICANT_BANK_DETAILS,dynamicUITableList,dynamicUITable,dynamicUITable.getCoRelationID());

                        }else if(dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_AHL)
                                && dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_CO_APPLICANT_BANK_DETAILS)){
                            // TODO: AHL co bank details update
                            removeAllChildFragments(ll.getId()+"");
                            initChild(LOAN_TYPE,CLIENT_ID, PROJECT_ID_EL,PRODUCT_ID_MSME,dynamicUITable.getScreenID(),
                                    SCREEN_NAME_CO_APPLICANT_BANK_DETAILS,dynamicUITableList,dynamicUITable,dynamicUITable.getCoRelationID());

                        }
                        else if(dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_PHL)
                                && dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_CO_APPLICANT_BANK_DETAILS)){
                            // TODO: PHL co bank details update
                            removeAllChildFragments(ll.getId()+"");
                            initChild(LOAN_TYPE,CLIENT_ID, PROJECT_ID_EL,PRODUCT_ID_MSME,dynamicUITable.getScreenID(),
                                    SCREEN_NAME_CO_APPLICANT_BANK_DETAILS,dynamicUITableList,dynamicUITable,dynamicUITable.getCoRelationID());

                        }else if(dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_EL)
                                && dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_CO_APPLICANT_BANK_DETAILS)){
                            // TODO: PHL co bank details update
                            removeAllChildFragments(ll.getId()+"");
                            initChild(LOAN_TYPE,CLIENT_ID, PROJECT_ID_EL,PRODUCT_ID_MSME,dynamicUITable.getScreenID(),
                                    SCREEN_NAME_CO_APPLICANT_BANK_DETAILS,dynamicUITableList,dynamicUITable,dynamicUITable.getCoRelationID());

                        }else if(dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_TWL)
                                && dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_CO_APPLICANT_BANK_DETAILS)){
                            // TODO: PHL co bank details update
                            removeAllChildFragments(ll.getId()+"");
                            initChild(LOAN_TYPE,CLIENT_ID, PROJECT_ID_EL,PRODUCT_ID_MSME,dynamicUITable.getScreenID(),
                                    SCREEN_NAME_CO_APPLICANT_BANK_DETAILS,dynamicUITableList,dynamicUITable,dynamicUITable.getCoRelationID());

                        }
                        else if(dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_MSME)
                                && dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_CO_APPLICANT_KYC)){

                            removeAllChildFragments(ll.getId()+"");
                            initChild(LOAN_TYPE,CLIENT_ID, PROJECT_ID_EL,PRODUCT_ID_MSME,dynamicUITable.getScreenID(),
                                    SCREEN_NAME_CO_APPLICANT_KYC,dynamicUITableList,dynamicUITable,dynamicUITable.getCoRelationID());

                        }else if (dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_REFERENCES)) {

                            removeAllChildFragments(ll.getId()+"");
                            initChild(LOAN_TYPE,CLIENT_ID, PROJECT_ID_EL,PRODUCT_ID_IL,dynamicUITable.getScreenID(),
                                    SCREEN_NAME_REFERENCES,dynamicUITableList,dynamicUITable,dynamicUITable.getCoRelationID());
                        }else if(dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_BUSINESS_PROOF)){

                            removeAllChildFragments(ll.getId()+"");
                            initChild(LOAN_TYPE,CLIENT_ID, PROJECT_ID_EL,PRODUCT_ID_IL,dynamicUITable.getScreenID(),
                                    SCREEN_NAME_BUSINESS_PROOF,dynamicUITableList,dynamicUITable,dynamicUITable.getCoRelationID());
                        }else if(dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_GENERAL_INCOME)){

                            removeAllChildFragments(ll.getId()+"");
                            initChild(LOAN_TYPE,CLIENT_ID, PROJECT_ID_EL,PRODUCT_ID_IL,SCREEN_N0_GENERAL_INCOME_MSME,
                                    dynamicUITable.getScreenName(),dynamicUITableList,dynamicUITable,dynamicUITable.getCoRelationID());
                        }else if(dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_OTHER_INCOME_SOURCE)){

                            removeAllChildFragments(ll.getId()+"");
                            initChild(LOAN_TYPE,CLIENT_ID, PROJECT_ID_EL,PRODUCT_ID_MSME,SCREEN_N0_OTHER_INCOME_SOURCE_MEME,
                                    SCREEN_NAME_OTHER_INCOME_SOURCE,dynamicUITableList,dynamicUITable,dynamicUITable.getCoRelationID());
                        }else if(dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_BANKING_HISTORY_MSME)){

                            removeAllChildFragments(ll.getId()+"");
                            initChild(LOAN_TYPE,CLIENT_ID, PROJECT_ID_EL,PRODUCT_ID_MSME,SCREEN_N0_MONTHLY_TRANSACTION_DETAIL_MSME,
                                    SCREEN_NAME_MONTHLY_TRANSACTION_DETAIL_MSME,dynamicUITableList,dynamicUITable,dynamicUITable.getCoRelationID());
                        }else if(dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_SALES_BILLS_MSME)){

                            removeAllChildFragments(ll.getId()+"");
                            initChild(LOAN_TYPE,CLIENT_ID, PROJECT_ID_EL,PRODUCT_ID_MSME,SCREEN_N0_SALES_BILLS_DETAIL_MSME,
                                    SCREEN_NAME_SALES_BILLS_DETAIL_MSME,dynamicUITableList,dynamicUITable,dynamicUITable.getCoRelationID());
                        }else if(dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_PURCHASE_BILLS_MSME)){

                            removeAllChildFragments(ll.getId()+"");
                            initChild(LOAN_TYPE,CLIENT_ID, PROJECT_ID_EL,PRODUCT_ID_MSME,SCREEN_N0_PURCHASE_BILLS_DETAIL_MSME,
                                    SCREEN_NAME_PURCHASE_BILLS_DETAIL_MSME,dynamicUITableList,dynamicUITable,dynamicUITable.getCoRelationID());
                        }else if(dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_ITR_MSME)){

                            removeAllChildFragments(ll.getId()+"");
                            initChild(LOAN_TYPE,CLIENT_ID, PROJECT_ID_EL,PRODUCT_ID_MSME,SCREEN_N0_ITR_DETAIL_MSME,
                                    SCREEN_NAME_ITR_DETAIL_MSME,dynamicUITableList,dynamicUITable,dynamicUITable.getCoRelationID());

                        }else if(dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_PRODUCT_ESTIMATE_DETAIL_MSME)){

                            removeAllChildFragments(ll.getId()+"");
                            initChild(LOAN_TYPE,CLIENT_ID, PROJECT_ID_EL,PRODUCT_ID_MSME,SCREEN_N0_PRODUCT_RAW_MATERIAL_MSME,
                                    SCREEN_NAME_PRODUCT_RAW_MATERIAL_MSME,dynamicUITableList,dynamicUITable,dynamicUITable.getCoRelationID());
                        }else if(dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_SERVICE_ESTIMATE_DETAIL_MSME)){

                            removeAllChildFragments(ll.getId()+"");
                            initChild(LOAN_TYPE,CLIENT_ID, PROJECT_ID_EL,PRODUCT_ID_MSME,SCREEN_N0_SERVICE_RAW_MATERIAL_MSME,
                                    SCREEN_NAME_SERVICE_RAW_MATERIAL_MSME,dynamicUITableList,dynamicUITable,dynamicUITable.getCoRelationID());
                        }else if(dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_MSME)
                                && dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_PRODUCT_ESTIMATE_MSME)){
                            FragmentToActivityInterface fragmentToActivityInterface = (FragmentToActivityInterface) getActivity();
                            fragmentToActivityInterface.oneFragmentToAnotherFragment(SCREEN_NAME, SCREEN_NAME_PRODUCT_ESTIMATE_DETAIL_MSME,
                                    dynamicUITable,dynamicUITableList);

                        }else if(dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_MSME)
                                && dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_SERVICE_ESTIMATE_MSME)){
                            FragmentToActivityInterface fragmentToActivityInterface = (FragmentToActivityInterface) getActivity();
                            fragmentToActivityInterface.oneFragmentToAnotherFragment(SCREEN_NAME, SCREEN_NAME_SERVICE_ESTIMATE_DETAIL_MSME,
                                    dynamicUITable,dynamicUITableList);

                        }else if(dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_DIRECT_BUSINESS_EXPENSE_MSME)){

                            removeAllChildFragments(ll.getId()+"");
                            initChild(LOAN_TYPE,CLIENT_ID, PROJECT_ID_EL,PRODUCT_ID_MSME,SCREEN_N0_DIRECT_BUSINESS_EXPENSE_DETAIL_MSME,
                                    SCREEN_NAME_DIRECT_BUSINESS_EXPENSE_DETAIL_MSME,dynamicUITableList,dynamicUITable,dynamicUITable.getCoRelationID());

                        }else if(dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_HOUSE_LIABILITIES_MSME)&&dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_MSME)){

                            removeAllChildFragments(ll.getId()+"");
                            initChild(LOAN_TYPE,CLIENT_ID, PROJECT_ID_EL,PRODUCT_ID_MSME,SCREEN_N0_DEBTS_MSME,
                                    SCREEN_NAME_HOUSE_DEBTS,dynamicUITableList,dynamicUITable,dynamicUITable.getCoRelationID());

                        }else if(dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_BUSINESS_LIABILITIES_MSME)
                                && dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_MSME)){

                            if(dynamicUITable.getFieldTag().contains(SCREEN_NAME_BUSINESS_ACCOUNT_PAYABLE)){
                                removeAllChildFragments(ll.getId()+"");
                                initChild(LOAN_TYPE,CLIENT_ID, PROJECT_ID_EL,PRODUCT_ID_MSME,SCREEN_N0_ACCOUNT_PAYABLE_MSME,
                                        SCREEN_NAME_BUSINESS_ACCOUNT_PAYABLE,dynamicUITableList,dynamicUITable,dynamicUITable.getCoRelationID());
                            }else if (dynamicUITable.getFieldTag().contains(SCREEN_NAME_BUSINESS_DEBTS_DETAIL_MSME)){
                                removeAllChildFragments(ll.getId()+"");
                                initChild(LOAN_TYPE,CLIENT_ID, PROJECT_ID_EL,PRODUCT_ID_MSME,SCREEN_N0_BUSINESS_DEBTS_MSME,
                                        SCREEN_NAME_BUSINESS_DEBTS_DETAIL_MSME,dynamicUITableList,dynamicUITable,dynamicUITable.getCoRelationID());
                            }else if (dynamicUITable.getFieldTag().contains(SCREEN_NAME_ADVANCE_DETAIL__MSME)){
                                removeAllChildFragments(ll.getId()+"");
                                initChild(LOAN_TYPE,CLIENT_ID, PROJECT_ID_EL,PRODUCT_ID_MSME,SCREEN_N0_ADVANCES_DEBTS_MSME,
                                        SCREEN_NAME_ADVANCE_DETAIL__MSME,dynamicUITableList,dynamicUITable,dynamicUITable.getCoRelationID());
                            }
                        }else if(dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_HYPOTHECATION_MSME)
                                && dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_MSME)){
                            removeAllChildFragments(ll.getId() + "");
                            initChild(LOAN_TYPE, CLIENT_ID, PROJECT_ID_EL, PRODUCT_ID_MSME, SCREEN_N0_DECISIONS_HYPOTHECATION_DETAIL_MSME,
                                    SCREEN_NAME_HYPOTHECATION_DETAIL_MSME, dynamicUITableList, dynamicUITable, dynamicUITable.getCoRelationID());
                        }
                    }

                }
            }
        });

        textLinearLayout.addView(textView);
        textLinearLayout.setLayoutParams(params);
        return textLinearLayout;
    }

    // TODO: TEXT VIEW VALUE
    protected View addTextViewValue(DynamicUITable viewParameters,List<DynamicUITable> dynamicUITableList) {
        //Adding a LinearLayout with HORIZONTAL orientation
        LinearLayout textLinearLayout = new LinearLayout(getActivity());
        textLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(convertDpToPixel(2),
                convertDpToPixel(15),
                0, 0
        );
        params.weight = 1;


        TextView textView = new TextView(getActivity());
        textView.setTag(viewParameters);
        if(!TextUtils.isEmpty(viewParameters.getValue())){
//            viewParameters.setValue(viewParameters.getDefaultValue());
            if (!TextUtils.isEmpty(viewParameters.getFieldTag())) {
                textView.setText(viewParameters.getValue());
            }
        }else{
            viewParameters.setValue("");
            textView.setText(viewParameters.getValue());
        }

        if(viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_LOAN_PROPOSAL) &&
                viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_LOAN_REQUESTED_AMOUNT)){
            setTextViewAttributesForHeading(textView,viewParameters);
        }else {
            setTextViewAttributesForValues(textView);
        }
        textLinearLayout.addView(textView);
        textLinearLayout.setLayoutParams(params);
        return textLinearLayout;
    }

    // TODO: TEXT VIEW VALUE FOR NEW ROW
    protected View addTextViewValueForNewRow(DynamicUITable viewParameters,List<DynamicUITable> dynamicUITableList) {
        //Adding a LinearLayout with HORIZONTAL orientation
        LinearLayout textLinearLayout = new LinearLayout(getActivity());
        textLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(convertDpToPixel(2),
                convertDpToPixel(15),
                0, 0
        );
        params.weight = 1;


        TextView textView = new TextView(getActivity());
        textView.setTag(viewParameters);
        if(!TextUtils.isEmpty(viewParameters.getValue())){
//            viewParameters.setValue(viewParameters.getDefaultValue());
            if (!TextUtils.isEmpty(viewParameters.getFieldTag())) {
                textView.setText(viewParameters.getValue());
            }
        }else{
            viewParameters.setValue("");
            textView.setText(viewParameters.getValue());
        }

        if(viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_LOAN_PROPOSAL) &&
                viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_LOAN_REQUESTED_AMOUNT)){
            setTextViewAttributesForHeading(textView,viewParameters);
        }else {
            // TODO: set attributes for new row value
            setTextViewAttributesForNewRowValues(textView);
        }
        textLinearLayout.addView(textView);
        textLinearLayout.setLayoutParams(params);
        return textLinearLayout;
    }

    // TODO: IMAGE VIEW
    protected View addImageView(DynamicUITable viewParameters,List<DynamicUITable> dynamicUITableList) {
        //Adding a LinearLayout with HORIZONTAL orientation
        LinearLayout imageLinearLayout = new LinearLayout(getActivity());
        imageLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(convertDpToPixel(2),
                convertDpToPixel(15),
                0, 0
        );
        params.weight = 1;

        if(viewParameters !=null){
            imageLinearLayout.setTag(viewParameters);
        }

        ImageButton imageButton = new ImageButton(getActivity());
        if(!TextUtils.isEmpty(viewParameters.getValue()) &&
                viewParameters.getValue().equalsIgnoreCase("Verified")){
//            viewParameters.setValue(viewParameters.getDefaultValue());
            imageButton.setBackgroundResource(R.drawable.ic_attach_money_black_24dp);
            imageButton.setBackgroundColor(getResources().getColor(R.color.colorYellow));
        }else{
            imageButton.setBackgroundResource(R.drawable.ic_close_red_24dp);
        }

        LinearLayout.LayoutParams imageButtonParams = new LinearLayout.LayoutParams(
                convertDpToPixel(30),
                convertDpToPixel(30));
        imageButtonParams.gravity=Gravity.END;
        imageButton.setLayoutParams(imageButtonParams);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeAllChildFragments(ll.getId()+"");
                initChild(LOAN_TYPE,CLIENT_ID, PROJECT_ID_EL,PRODUCT_ID_IL,SCREEN_N0_EMPLOYER_VERIFICATION,
                        SCREEN_NAME_EMPLOYER_VERIFICATION,dynamicUITableList,null,dynamicUITableList.get(0).getCoRelationID());

            }
        });

        imageLinearLayout.addView(imageButton);
        imageLinearLayout.setLayoutParams(params);
        return imageLinearLayout;
    }

    protected View addTextViewHeading(DynamicUITable viewParameters) {
        //Adding a LinearLayout with HORIZONTAL orientation
        LinearLayout textLinearLayout = new LinearLayout(getActivity());
        textLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        textLinearLayout.setTag(viewParameters);

        TextView textView = new TextView(getActivity());

        params.setMargins(convertDpToPixel(2),
                convertDpToPixel(10),
                0, 0
        );
        textView.setText(viewParameters.getFieldName() /*+ " : "*/);
        params.weight = 1;

        setTextViewAttributesForHeading(textView,viewParameters);
        textLinearLayout.addView(textView);
        textLinearLayout.setLayoutParams(params);
        return textLinearLayout;
    }

    private void setTextViewAttributes(TextView textView) {
        textView.setTextColor(Color.BLACK);
        textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
        textView.setTextSize(18);
    }
    private void setTextViewAttributesForValues(TextView textView) {
        textView.setTextColor(Color.DKGRAY);
        textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
        textView.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity=Gravity.CENTER;
        textView.setLayoutParams(params);
        textView.setTextSize(18);
    }

    private void setTextViewAttributesForLable(TextView textView, DynamicUITable dynamicUITable) {

        textView.setTextColor(Color.BLACK);

        textView.setTypeface(textView.getTypeface(), Typeface.BOLD);

        textView.setTextSize(18);
    }

    private void setTextViewAttributesForNewRowLable(TextView textView) {
        textView.setTextColor(Color.DKGRAY);
        textView.setTypeface(textView.getTypeface(), Typeface.BOLD_ITALIC);
        textView.setTextSize(20);
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

    }

    private void setTextViewAttributesForNewRowValues(TextView textView) {
        textView.setTextColor(Color.DKGRAY);
        textView.setTypeface(textView.getTypeface(), Typeface.BOLD_ITALIC);
        textView.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity=Gravity.CENTER;
        textView.setLayoutParams(params);
        textView.setTextSize(18);
    }
    private void setTextViewAttributesForHeading(TextView textView, DynamicUITable viewParameters) {
//        textView.setTextColor(getActivity().getResources().getColor(R.color.primaryDarkColor));
        if(viewParameters !=null && ! TextUtils.isEmpty(viewParameters.getFieldTag())
                && viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_REFERRAL_INFO)){
            textView.setTextColor(Color.BLACK);
            textView.setTextSize(18);
        }else if(viewParameters !=null && ! TextUtils.isEmpty(viewParameters.getFieldTag())
                && viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_LOAN_APPROVAL_MSME) &&
                viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_TOTAL_MONTHLY_SALES_IN_LOAN_APPROVAL_MSME)){
            textView.setTextColor(Color.RED);
            textView.setTextSize(20);
            textView.setGravity(Gravity.END);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.gravity=Gravity.END;
            textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            textView.setLayoutParams(params);
        }else {
            textView.setTextColor(Color.BLUE);
            textView.setTextSize(20);

        }
        textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
    }

    public void addViewToParentLayout(View view) {
        try {
            ll.addView(view);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setSomethingWrong(Throwable t) {
        try {
            appHelper.getDialogHelper().closeDialog();
            if (t instanceof SocketTimeoutException
                    || t instanceof UnknownHostException
                    || t instanceof ConnectException) {
                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR,
                        "Unable to connect server", null);
            } else {
                setSomethingWrong();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setSomethingWrong() {
        try {
            appHelper.getDialogHelper().getConfirmationDialog().show(ERROR,
                    "Something went wrong. Please try again later", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void clearDataUptoTheFieldTAG(ViewGroup group, String FieldTAG) {
        try {
            for (int i = 0, count = group.getChildCount(); i < count; ++i) {
                View view = group.getChildAt(i);
                if(view.getTag()!=null && ((DynamicUITable)view.getTag()).getFieldTag().equalsIgnoreCase(FieldTAG)) { // TODO: TAG equals
                    break;
                }else if(view.getTag()!=null && ! ((DynamicUITable)view.getTag()).getFieldTag().equalsIgnoreCase(FieldTAG)) { // TODO: TAG not equal

                    if (view.getVisibility() == View.VISIBLE) {
                        if (view instanceof CustomTextInputLayout && view.getVisibility() == View.VISIBLE) {
                            ((CustomTextInputLayout) view).getEditText().setText("");
                            ((CustomTextInputLayout) view).getEditText().setError(null);
                            ((CustomTextInputLayout) view).getEditText().setEnabled(true);
                        }

                        if (view instanceof CustomEditText && view.getVisibility() == View.VISIBLE) {
                            ((CustomEditText) view).setText("");
                            ((CustomEditText) view).setError(null);
                            ((CustomEditText) view).setEnabled(true);
                        }

                        if (view instanceof SearchableSpinner && view.getVisibility() == View.VISIBLE) {
                            if (((SearchableSpinner) view).getAdapter() != null
                                    && ((SearchableSpinner) view).getAdapter().getCount() > 0) {
                                ((SearchableSpinner) view).setSelection(0);
                            }
                        }

                        if (view instanceof Spinner && view.getVisibility() == View.VISIBLE) {
                            if (((Spinner) view).getAdapter() != null
                                    && ((Spinner) view).getAdapter().getCount() > 0) {
                                ((Spinner) view).setSelection(0);
                            }
                        }

                        if (view instanceof RadioGroup && view.getVisibility() == View.VISIBLE) {
                            ((RadioGroup) view).clearCheck();
                            ((RadioGroup) view).setEnabled(true);
                        }

                        if (view instanceof CheckBox && view.getVisibility() == View.VISIBLE) {
                            ((CheckBox) view).setChecked(false);
                            ((CheckBox) view).setEnabled(true);
                        }

                        if (view instanceof ViewGroup && (((ViewGroup) view).getChildCount() > 0))
                            clearDataUptoTheFieldTAG((ViewGroup) view,FieldTAG);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String[] getNewSpinnerList(String tagName, String spinnerItem ) {
        String newSpinnerlist[]={};
        if(tagName.equalsIgnoreCase(TAG_NAME_KYC_TYPE)){
            if(spinnerItem.equalsIgnoreCase(LOAN_NAME_JLG)){
                newSpinnerlist= new String[]{ "Aadhaar", "VID","Voter-ID","PAN Card","Driving License", "Passport", "Bank Passbook with Photograph","Job Card"};
            }
            else if(spinnerItem.equalsIgnoreCase(LOAN_NAME_INDIVIDUAL)){
                newSpinnerlist= new String[]{"Aadhaar", "VID","Voter-ID","Driving License", "Passport", "NREGA Job Card"};
            }
            else if(spinnerItem.equalsIgnoreCase(LOAN_NAME_MSME)){
                newSpinnerlist= new String[]{ "Aadhaar", "VID","Voter-ID","PAN Card","Driving License", "Passport", "Bank Passbook with Photograph","Job Card"};
            }
            else if(spinnerItem.equalsIgnoreCase(LOAN_NAME_TW)){
                newSpinnerlist= new String[]{ "Aadhaar", "VID","Voter-ID","PAN Card","Driving License", "Passport", "Bank Passbook with Photograph","Job Card"};
            }
        }
        // TODO: Business Sub Sector
        else if(tagName.equalsIgnoreCase(TAG_NAME_BUSINESS_SUB_SECTOR)){
            if (spinnerItem.equalsIgnoreCase(RADIO_BUTTON_ITEM_MANUFACTURING)) {
                newSpinnerlist = new String[]{"Engineering Allied Products and Components",
                        "Engineering Job Works",
                        "Mfg and Job work of Electronic Pro",
                        "Plastic Paper Chemical and Allied",
                        "Mfg iron and Steels",
                        "Mfg of Textile Goods",
                        "Textile Goods Job works",
                        "Hardware and Software Industry",
                        "Mfg of Household Items",
                        "Mfg  of Building Materials",
                        "Mfg of food and eatable items",
                        "Powerloom or Handloom ",
                        "Mfg  of Agri allied products",
                        "Rice Mill ",
                        "Flour Mill",
                        "Dal Mill",
                        "Mfg Jobwork of Leather and AI",
                        "Mfg of Dairy Products"};
            }
            else if (spinnerItem.equalsIgnoreCase(RADIO_BUTTON_ITEM_SERVICE)) {
                newSpinnerlist = new String[]{"Dairy ",
                        "Restaurant Shop",
                        "Tea Shop",
                        "Medical Practitioners",
                        "Purohit",
                        "Cable Operators",
                        "Photo Studios",
                        "Tailors",
                        "Auto-mechanic ",
                        "Courier Commission",
                        "Goldsmiths",
                        "Lawyer",
                        "STD Booths Internet Café",
                        "Xerox Shops",
                        "DTP",
                        "Travel Agents/Transport Agents/Operators",
                        "Beauty Parlors",
                        "Book Building Shops",
                        "Security Agencies",
                        "Two Wheeler Mechanic Shop",
                        "Contractors",
                        "Teaching/Coaching Institute",
                        "Computer and Electronic Shop"};
            }
            else if (spinnerItem.equalsIgnoreCase(RADIO_BUTTON_ITEM_TRADE)) {
                newSpinnerlist = new String[]{"Chicken/Mutton Shops(Butchers)",
                        "Commission Agents without visible stocks",
                        "Provisional Departmental Fancy Store",
                        "Stationary Shop",
                        "Commodity Store",
                        "FMCG WholeSale",
                        "Fruits Vegetable Wholesale",
                        "Medical Shop Distributor",
                        "Scrap Dealer",
                        "Agro Merchant",
                        "Snacks Parlour",
                        "Footwear Leather Dealer/Distributor",
                        "Textile Readymade Garment Shop",
                        "Building Material Paints Trader",
                        "Plywood and Glass Shop",
                        "Automobile and spareparts shop",
                        "Domestic Products Trader",
                        "Computer and Electronic Shop"};
            }
        }else if(tagName.equalsIgnoreCase(TAG_NAME_OCCUPATION)){
            if (spinnerItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_SALARIED)) {

                // Private Sector,Public Sector,Government Sector
                newSpinnerlist = new String[]{"Government", "Private", "Non-Registered Entity"};
            } else if (spinnerItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_SELF_EMPLOYED)) {
                newSpinnerlist = new String[]{"Service Provider", "Shop Owner", "Street Vendor", "Labourer",  "Manufacturer", "Trader"};
            }   else if (spinnerItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_PENSIONER)) {
                newSpinnerlist = new String[]{ "Army","Government Employee", "Others"};
            } else if(spinnerItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_NOT_WORKING)){
                newSpinnerlist = new String[]{ "Not Working"};
            } else if(spinnerItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_STUDENT)){
                newSpinnerlist = new String[]{ "Student"};
            }else if(spinnerItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_HOUSEWIFE)||spinnerItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_HOUSE_WIFE)){
                newSpinnerlist = new String[]{ "HomeMaker"};
            }else{
                newSpinnerlist = new String[]{};
            }

            /*else if (spinnerItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_AGRICULTURE_AND_ALLIED_ACTIVITIES)
                    || spinnerItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_STUDENT)
                    || spinnerItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_NON_WORKING)){
                newSpinnerlist = new String[]{};

            }*/
        } else if (tagName.equalsIgnoreCase(TAG_NAME_GUARANTOR)) {
            if(spinnerItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_GUARANTOR_PARTNERSHIP_LLP)){
                newSpinnerlist = new String[]{"HUF", "Individual", "Partnership firm"};
            } else if (spinnerItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_GUARANTOR_HUF)) {
                newSpinnerlist = new String[]{"Individual", "Partnership firm", "Limited Liability Partnership"};
            } else if (spinnerItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_GUARANTOR_INDIVIDUAL)) {
                newSpinnerlist = new String[]{"HUF", "Partnership firm", "Limited Liability Partnership"};
            }

        } else if(tagName.equalsIgnoreCase(TAG_NAME_TYPE_OF_BUSINESS)){
            if (!TextUtils.isEmpty(spinnerItem) && spinnerItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_MANUFACTURE)) {
                newSpinnerlist = new String[] {"Engineering Allied Products and Components", "Engineering Job Works",
                        "Mfg and Job work of Electronic Pro", "Plastic Paper Chemical and Allied",
                        "Mfg iron and Steels", "Mfg Textile goods", "Textile Goods Job works", "Hardware and Software Industry",
                        "Mfg of wood and steel furniture", "Mfg Bricks", "Wood Saw industry", "Mfg of Household Items",
                        "Mfg  of Building Materials", "Mfg of food and eatable items", "Powerloom and Handloom", "Mfg of agriculture allied products",
                        "Rice Mills/ Flour Mills/ Dal Mills", "Mfg and Jobwork of Leather and AI", "Mfg of dairy products"};
            } else if (!TextUtils.isEmpty(spinnerItem) && spinnerItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_SERVICE)) {
                newSpinnerlist = new String[]{"Dairy", "Restaurants", "Tea Shop", "Medical Practitioners", "Purohit", "Cable operators",
                        "Saloon Shops", "Carpenter", "Building Contractors", "Photo Studios", "Tailors", "Auto Mechanic",
                        "Courier Commission", "Goldsmiths", "Lawyers", "STD Booths/Internet Café/Xerox Shops/ DTP",
                        "Travel Agent/ Operator/ Transport Agent", "Beauty Parlors", "Book Binding Shops", "Security Agencies",
                        "Two-Wheeler Mechanic Shop", "Contractors", "Teaching/Coaching Institute", "Computer Electronic Repair Shop", "Catering"};
            } else if (!TextUtils.isEmpty(spinnerItem) && spinnerItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_TRADE)) {
                newSpinnerlist = new String[] {"Bakeries", "Chicken/Mutton Shops(Butchers)", "Commission Agents without visible stocks",
                        "Provisional Departmental Fancy Store", "Stationary Shop", "Commodity Store", "FMCG WholeSale",
                        "Fruits Vegetable Wholesale", "Medical Shop Distributor", "Scrap Dealer", "Agro Merchant", "Snacks Parlour",
                        "Footwear Leather Dealer/Distributor", "Textile Readymade Garment Shop", "Building Material Paints Trader",
                        "Plywood and Glass Shop", "Automobile and spareparts shop", "Domestic Products Trader", "Computer and Electronic Shop"};
            }
            else if (!TextUtils.isEmpty(spinnerItem) && (spinnerItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_AGRICULTURE)
                    || spinnerItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_LIVESTOCK))){
                newSpinnerlist = new String[]{};
            }
        }
        return newSpinnerlist;
    }

    public void scanQRCode() {
        try {
            if ((ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)) {
                Log.e(TAG, "Requesting for Permissions -->");
                appHelper.askForPermission(getActivity(), permissions, CAMERA_PERMISSION_CODE);
            } else {
                IntentIntegrator integrator = new IntentIntegrator(getActivity());
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                integrator.setPrompt("Place QR Code inside rectangle box");
                integrator.setBeepEnabled(true);
                integrator.setTimeout(60000);
                integrator.setCameraId(0);  // Use a specific camera of the device
                integrator.setCaptureActivity(SmallCaptureActivity.class);
                integrator.initiateScan();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public CardView createCardView(Context context){
        // Initialize a new CardView
        CardView card = new CardView(context);

        // Set the CardView layoutParams
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(5,convertDpToPixel(30),5,5);
        card.setLayoutParams(params);

        // Set CardView corner radius
        card.setRadius(9);

        // Set cardView content padding
        card.setContentPadding(15, 15, 15, 15);

        // Set a background color for CardView
//        card.setCardBackgroundColor(Color.parseColor("#FFC6D6C3"));

        // Set the CardView maximum elevation
        card.setMaxCardElevation(convertDpToPixel(25));

       /* card.setOutlineSpotShadowColor(Color.BLUE);
        card.setOutlineAmbientShadowColor(Color.BLUE);*/

//        GradientDrawable drawable = (GradientDrawable)card.getBackground();
//        drawable.setStroke(3, Color.RED);

        // Set CardView elevation
        card.setCardElevation(convertDpToPixel(15));

        LinearLayout attrControlsSubContainer = new LinearLayout(context);
        attrControlsSubContainer.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutParams= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        attrControlsSubContainer.setLayoutParams(layoutParams);

       /* FrameLayout frameLayout=new FrameLayout(context);
        LinearLayout.LayoutParams frameLayoutParams= new LinearLayout.LayoutParams(convertDpToPixel(4),LinearLayout.LayoutParams.WRAP_CONTENT);
        frameLayout.setLayoutParams(frameLayoutParams);
        frameLayout.setBackground(ContextCompat.getDrawable(context, R.color.colorBlue));
        attrControlsSubContainer.addView(frameLayout);*/

        card.addView(attrControlsSubContainer);

        return card;
    }

    public void removeAllChildFragments(String mParentId) {
        List<String> childFragmentTags = CHILD_FRAGMENT_TAGS_MAP.get(mParentId);
        if (childFragmentTags != null && !childFragmentTags.isEmpty()) {
            List<Fragment> childFragments = getChildFragmentManager().getFragments();
            if (childFragments != null && !childFragments.isEmpty()) {
                FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                for (Fragment childFragment : childFragments) {
                    if (childFragment != null) {
                        if (childFragmentTags.contains(childFragment.getTag())) {
                            fragmentTransaction.remove(childFragment);
                        }
                    }
                }
                fragmentTransaction.commitAllowingStateLoss();
                childFragmentTags.clear();
            }
        }
    }

    public void initChild(String loanType,String ClientId,String projectId,String moduleId,String screenNO,
                          String SCREEN_NAME,List<DynamicUITable> dynamicUITableListFromParentScreen,
                          DynamicUITable dynamicUITable,String correlationId) {
        addFragmentToContainer(ChildFragment.newInstance(loanType,ClientId,projectId,moduleId,screenNO,SCREEN_NAME,
                dynamicUITableListFromParentScreen,dynamicUITable,USER_ID,MODULE_TYPE,correlationId),
                SCREEN_NAME);
    }

    private void addFragmentToContainer(Fragment childFragment,String screenName) {

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        layoutParams.setMargins(0, 10, 0, 0);

        int id = generateViewId();
        String tag = String.valueOf(id);
        addTagToChildFragmentTagsMap(tag,mRootView.getId()+"");

        FrameLayout frameLayout = new FrameLayout(getActivity());
        frameLayout.setId(id);
        addViewToContainer(frameLayout, layoutParams,mRootView);

        /*getChildFragmentManager()
                .beginTransaction()
                .replace(frameLayout.getId(), childFragment, tag)
                .commitAllowingStateLoss();*/

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        DialogFragment dialogFragment = new MyCustomDialogFragment(childFragment,screenName);
        dialogFragment.show(ft, "dialog");
    }

    private void addTagToChildFragmentTagsMap(String childFragmentTag,String mParentId) {
        List<String> childFragmentTags = CHILD_FRAGMENT_TAGS_MAP.get(mParentId);
        if (childFragmentTags == null) {
            List<String> initialChildFragmentTags = new ArrayList<>();
            initialChildFragmentTags.add(childFragmentTag);
            CHILD_FRAGMENT_TAGS_MAP.put(mParentId, initialChildFragmentTags);
        } else {
            childFragmentTags.add(childFragmentTag);
        }
    }

    /**
     * Add view to container with configurable layout params
     */
    private void addViewToContainer(View view, LinearLayout.LayoutParams layoutParams,FrameLayout mRootView) {
        view.setFocusable(false);
        view.setFocusableInTouchMode(false);
        mRootView.addView(view, layoutParams);
    }

    public static int generateViewId() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            for (; ; ) {
                final int result = NEXT_GENERATED_ID.get();
                // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
                int newValue = result + 1;
                if (newValue > 0x00FFFFFF) {
                    newValue = 1; // Roll over to 1, not 0.
                }
                if (NEXT_GENERATED_ID.compareAndSet(result, newValue)) {
                    return result;
                }
            }
        } else {
            return View.generateViewId();
        }
    }

    public HashMap<String,Object> setKeyValueForObject(RawDataTable rawDataTable) {
        HashMap<String,Object> rawDataHashMap=new HashMap<>();
        try{
            if( !TextUtils.isEmpty(rawDataTable.getRawdata())){
                String rawData=rawDataTable.getRawdata();

                rawDataHashMap= App.createHashMapFromJsonString(rawData);
                Log.d(TAG,"Hashmap ==> "+rawDataHashMap);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return rawDataHashMap;
    }


    public DynamicUITable createNewRow(DynamicUITable dynamicUITable, RawDataTable rawDataTable,String fieldName, HashMap<String, Object> hashMap){
        DynamicUITable newDynamicUITable=new DynamicUITable();
        try {
            newDynamicUITable.setScreenID(dynamicUITable.getScreenID());
            newDynamicUITable.setScreenName(dynamicUITable.getScreenName());
            newDynamicUITable.setFieldTag(rawDataTable.getTag_name());// TODO: New Field TAG as LIABILITY
            newDynamicUITable.setFieldName(dynamicUITable.getValue());
            if( ! TextUtils.isEmpty(fieldName)) {
                newDynamicUITable.setFieldName(fieldName);
            }
            newDynamicUITable.setIsRequired(dynamicUITable.getIsRequired());
//            newDynamicUITable.setFieldType(dynamicUITable.getFieldType());
            newDynamicUITable.setFieldType(FIELD_TYPE_NEW_ROW); // TODO: Hardcoded field type
            newDynamicUITable.setDataType(dynamicUITable.getDataType());
            newDynamicUITable.setLoanType(dynamicUITable.getLoanType());
            newDynamicUITable.setVisibility(true); // TODO: Always true
            newDynamicUITable.setClientID(dynamicUITable.getClientID());
            newDynamicUITable.setValue(dynamicUITable.getValue());
            if(dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_APPLICANT_KYC)
                    ||dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_CO_APPLICANT_KYC)){
                if(hashMap!=null && hashMap.containsKey(TAG_NAME_KYC_ID)) {
                    newDynamicUITable.setValue(hashMap.get(TAG_NAME_KYC_ID).toString());
                }
            }

            if(dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_BUSINESS_PROOF)){
                if(hashMap!=null && hashMap.containsKey(TAG_NAME_BUSINESS_ID)) {
                    newDynamicUITable.setValue(hashMap.get(TAG_NAME_BUSINESS_ID).toString());
                }
            }
            if(dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_BANK_DETAILS)
                    || dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_CO_APPLICANT_BANK_DETAILS)){

                if(hashMap!=null && hashMap.containsKey(TAG_NAME_BANK_NAME)) {
                    newDynamicUITable.setFieldName(hashMap.get(TAG_NAME_BANK_NAME).toString());
                }
                if(hashMap!=null && hashMap.containsKey(TAG_NAME_ACCOUNT_NUMBER)) {
                    newDynamicUITable.setValue(hashMap.get(TAG_NAME_ACCOUNT_NUMBER).toString());
                }
            }
            if(dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_REFERENCE_CHECK)){
                if(hashMap!=null && hashMap.containsKey(TAG_NAME_REFERENCE_TYPE)) {
                    newDynamicUITable.setValue(hashMap.get(TAG_NAME_REFERENCE_TYPE).toString());
                }
                if(hashMap!=null && hashMap.containsKey(TAG_NAME_FULL_NAME)) {
                    newDynamicUITable.setFieldName(hashMap.get(TAG_NAME_FULL_NAME).toString());
                }
            }
            if(dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_GENERAL_INCOME)){
                if (hashMap!=null &&hashMap.containsKey(TAG_NAME_VERIFIED_IN_GENERAL_INCOME)) {
                    String verified = hashMap.get(TAG_NAME_VERIFIED_IN_GENERAL_INCOME).toString();
                    if (!TextUtils.isEmpty(verified)) {
                        if (verified.equalsIgnoreCase("Yes")) {
                            if(hashMap.containsKey(TAG_NAME_VERIFIED_INCOME_IN_GENERAL_INCOME)) {
                                newDynamicUITable.setValue(hashMap.get(TAG_NAME_VERIFIED_INCOME_IN_GENERAL_INCOME).toString());
                            }
                        }else {
                            if(hashMap.containsKey(TAG_NAME_MONTHLY_INCOME_IN_GENERAL_INCOME)) {
                                newDynamicUITable.setValue(hashMap.get(TAG_NAME_MONTHLY_INCOME_IN_GENERAL_INCOME).toString());
                            }
                        }
                    }
                }

                if(hashMap!=null && hashMap.containsKey(TAG_NAME_NAME_IN_GENERAL_INCOME)) {
                    newDynamicUITable.setFieldName(hashMap.get(TAG_NAME_NAME_IN_GENERAL_INCOME).toString());
                }
            }
            if(dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_OTHER_INCOME_SOURCE)){
                if (hashMap!=null &&hashMap.containsKey(TAG_NAME_VERIFIED_IN_OTHER_INCOME)) {
                    String verified = hashMap.get(TAG_NAME_VERIFIED_IN_OTHER_INCOME).toString();
                    if (!TextUtils.isEmpty(verified)) {
                        if (verified.equalsIgnoreCase("Yes")) {
                            if(hashMap.containsKey(TAG_NAME_VERIFIED_INCOME_IN_OTHER_INCOME)) {
                                newDynamicUITable.setValue(hashMap.get(TAG_NAME_VERIFIED_INCOME_IN_OTHER_INCOME).toString());
                            }
                        }else {
                            if(hashMap.containsKey(TAG_NAME_MONTHLY_INCOME_IN_OTHER_INCOME)) {
                                newDynamicUITable.setValue(hashMap.get(TAG_NAME_MONTHLY_INCOME_IN_OTHER_INCOME).toString());
                            }
                        }
                    }
                }

                if(hashMap!=null && hashMap.containsKey(TAG_NAME_NAME_IN_OTHER_INCOME)) {
                    newDynamicUITable.setFieldName(hashMap.get(TAG_NAME_NAME_IN_OTHER_INCOME).toString());
                }
            }
            if(dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_REFERENCES)){
                if(hashMap!=null && hashMap.containsKey(TAG_NAME_CONTACT_NO)) {
                    newDynamicUITable.setValue(hashMap.get(TAG_NAME_CONTACT_NO).toString());
                }
                if(hashMap!=null && hashMap.containsKey(TAG_NAME_FULL_NAME)) {
                    newDynamicUITable.setFieldName(hashMap.get(TAG_NAME_FULL_NAME).toString());
                }
            }
            if(hashMap !=null && hashMap.size()>0 && hashMap.containsKey(rawDataTable.getTag_name())){
                newDynamicUITable.setValue(hashMap.get(rawDataTable.getTag_name()).toString());
            }
            newDynamicUITable.setDataEntryType(dynamicUITable.getDataEntryType());
            newDynamicUITable.setParamlist(dynamicUITable.getParamlist());
            newDynamicUITable.setPlusSignName(dynamicUITable.getPlusSignName());
            newDynamicUITable.setEditable(dynamicUITable.isEditable());
            newDynamicUITable.setSpinnerItemPosition(dynamicUITable.getSpinnerItemPosition());
            newDynamicUITable.setLength(dynamicUITable.getLength());
            newDynamicUITable.setDefaultValue(dynamicUITable.getDefaultValue());
            newDynamicUITable.setErrorMessage(dynamicUITable.getErrorMessage());
            newDynamicUITable.setHint(dynamicUITable.getHint());
            newDynamicUITable.setFeatureID(2); // TODO: Hardcoded as 2
            newDynamicUITable.setUser_id(dynamicUITable.getUser_id());
            newDynamicUITable.setModuleType(dynamicUITable.getModuleType());
            newDynamicUITable.setCoRelationID(dynamicUITable.getCoRelationID());
            newDynamicUITable.setUniqueId(rawDataTable.getUniqueId());
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return newDynamicUITable;
    }

    public List<ReferenceCheckContactDTO> refactorRawData(List<RawDataTable> rawDataTableList) {
        List<ReferenceCheckContactDTO> referenceCheckContactDTOList = new ArrayList<>();
        if (rawDataTableList != null) {
            for (RawDataTable rawDataTables : rawDataTableList) {
                if (rawDataTables.getRawdata() != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(rawDataTables.getRawdata());
                        String fullName = jsonObject.optString(TAG_NAME_FULL_NAME, "");
                        String contactNo = jsonObject.optString(TAG_NAME_CONTACT_NO, "");
                        if (TextUtils.isEmpty(contactNo)) {
                            contactNo = jsonObject.optString(TAG_NAME_CONTACT_NO_1, "");
                        }
                        referenceCheckContactDTOList.add(new ReferenceCheckContactDTO(fullName, contactNo));
                        Log.d("Tusar", "Contact No: "+contactNo);
                    } catch (JSONException err) {
                        Log.d("Error", err.toString());
                    }
                }
            }
        }
        return referenceCheckContactDTOList;
    }

    public void getApplicantPersonalDetails(DynamicUIViewModel viewModel) {
        viewModel.getRawDataByScreenNameAndModuleType(prepareStaticDynamicTable(SCREEN_NAME_PERSONAL_DETAIL, MODULE_TYPE_APPLICANT));
        if (viewModel.getRawDataTableLiveData() != null) {
            Observer observer = new Observer() {
                @Override
                public void onChanged(Object o) {
                    RawDataTable rawDataTable = (RawDataTable) o;
                    viewModel.getRawDataTableLiveData().removeObserver(this);
                    if (rawDataTable != null) {
                        List<RawDataTable> newRawDataTableList = new ArrayList<RawDataTable>();
                        newRawDataTableList.add(rawDataTable);
                        applicantPersonalDetailList = refactorRawData(newRawDataTableList);
                        if (applicantPersonalDetailList != null && applicantPersonalDetailList.size() > 0) {
                        }
                    }
                }
            };
            viewModel.getRawDataTableLiveData().observe(this, observer);
        }
    }

    public void getCoApplicantPersonalDetails(DynamicUIViewModel viewModel) {
        viewModel.getRawDataByScreenNameAndModuleType(prepareStaticDynamicTable(SCREEN_NAME_COAPPLICANT_PERSONAL_DETAIL, "CoApplicant1"/*MODULE_TYPE_CO_APPLICANT*/));
        if (viewModel.getRawDataTableLiveData() != null) {
            Observer observer = new Observer() {
                @Override
                public void onChanged(Object o) {
                    RawDataTable rawDataTable = (RawDataTable) o;
                    viewModel.getRawDataTableLiveData().removeObserver(this);
                    if (rawDataTable != null) {
                        List<RawDataTable> newRawDataTableList = new ArrayList<RawDataTable>();
                        newRawDataTableList.add(rawDataTable);
                        coApplicantPersonalDetailList = refactorRawData(newRawDataTableList);
                        if (coApplicantPersonalDetailList != null && coApplicantPersonalDetailList.size() > 0) {
                            Log.d("tusar", "getCoApplicantPersonalDetails:: " + coApplicantPersonalDetailList.get(0).getContactNo());
                        }
                    }
                }
            };
            viewModel.getRawDataTableLiveData().observe(this, observer);
        }
    }
    private DynamicUITable prepareStaticDynamicTable(String screenName, String moduleType){
        DynamicUITable dynamicUITable = new DynamicUITable();
        dynamicUITable.setScreenName(screenName);
        dynamicUITable.setClientID(CLIENT_ID);
        dynamicUITable.setModuleType(moduleType);
        dynamicUITable.setLoanType(LOAN_TYPE);
        return dynamicUITable;
    }

}
