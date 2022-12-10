package com.swadhaar.los.dynamicui.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.swadhaar.los.R;
import com.swadhaar.los.fragments.ChildFragment;
import com.swadhaar.los.fragments.LOSBaseFragment;

import static com.swadhaar.los.constants.AppConstant.SCREEN_NAME_APPLICANT_KYC;
import static com.swadhaar.los.constants.AppConstant.SCREEN_NAME_BANKING_HISTORY_MSME;
import static com.swadhaar.los.constants.AppConstant.SCREEN_NAME_CO_APPLICANT_KYC;
import static com.swadhaar.los.constants.AppConstant.SCREEN_NAME_GENERAL_INCOME;
import static com.swadhaar.los.constants.AppConstant.SCREEN_NAME_OTHER_INCOME_SOURCE;
import static com.swadhaar.los.constants.AppConstant.SCREEN_NAME_REFERENCE_CHECK;

public class MyCustomDialogFragment extends DialogFragment implements ChildFragment.ChildFragmentInterface {
    Fragment mChildFragment;
    String mTitile;
    public MyCustomDialogFragment(Fragment childFragment,String title) {
        mChildFragment=childFragment;
        mTitile=title;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        getDialog().setTitle(mTitile);

        // TODO: Set Back press false for the following screens
        if(mTitile.equalsIgnoreCase(SCREEN_NAME_APPLICANT_KYC)
            || mTitile.equalsIgnoreCase(SCREEN_NAME_CO_APPLICANT_KYC)
            || mTitile.equalsIgnoreCase(SCREEN_NAME_REFERENCE_CHECK)
            || mTitile.equalsIgnoreCase(SCREEN_NAME_GENERAL_INCOME)
            || mTitile.equalsIgnoreCase(SCREEN_NAME_OTHER_INCOME_SOURCE)
        ){
        setCancelable(false);
        }

        getDialog().setCanceledOnTouchOutside(false);
        return inflater.inflate(R.layout.container_fragment, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//        ChildFragment childFragment1 = new ChildFragment();
        transaction.replace(R.id.fragment_container, mChildFragment);
        transaction.commit();
    }


    @Override
    public void closeDialogFragment() {
        try{
            if(getDialog() !=null){
                getDialog().dismiss();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
