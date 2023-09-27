package com.bhagva.ind.adapters;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class DecimalTextWatcher implements TextWatcher {
    private EditText editText;
    private boolean isFormatting;
    private boolean ignoreChange;
    DecimalFormat dec;
    private String current = "";
    boolean mEditing;
    public DecimalTextWatcher(EditText editText) {
        this.editText = editText;
//        dec = new DecimalFormat("0.00");
        mEditing = false;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // Not used
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
       /* if(!s.toString().equals(current)){
       editText.removeTextChangedListener(this);

            String cleanString = s.toString().replaceAll("[$,.]", "");

            double parsed = Double.parseDouble(cleanString);
            String formatted = NumberFormat.getCurrencyInstance().format((parsed/100));

            current = formatted;
       editText.setText(formatted);
       editText.setSelection(formatted.length());

       editText.addTextChangedListener(this);
        }*/

    }

    @Override
    public void afterTextChanged(Editable s) {
        if(!mEditing) {
            mEditing = true;

            String digits = s.toString().replaceAll("\\D", "");
            NumberFormat nf = NumberFormat.getCurrencyInstance();
            try{
                String formatted = nf.format(Double.parseDouble(digits)/100);
                s.replace(0, s.length(), formatted);
            } catch (NumberFormatException nfe) {
                s.clear();
            }

            mEditing = false;
        }
    }
}

