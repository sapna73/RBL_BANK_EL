package com.swadhaar.los.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.helpers.AppHelper;
import com.swadhaar.los.R;
import com.swadhaar.los.models.EMIDetailsDTO;

import java.util.List;

import static com.swadhaar.los.dynamicui.constants.ParametersConstant.JLG_MINIMUM_OVERDUE_COLLECTION;

public class CenterMeetingODEMIDetailsAdapter extends RecyclerView.Adapter<CenterMeetingODEMIDetailsAdapter.ViewHolder>  {

    private Context context;
    List<EMIDetailsDTO> emiDetailsDTOList;
    AppHelper appHelper;
    EMIDetailsCallBackInterface emiDetailsCallBackInterface;

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvProdCode,tvLoanId,tvInstallmentNo,tvEmi,tvTotalDue;
        EditText etCollection;
        Spinner spReason;
        RelativeLayout rlReason;

        public ViewHolder(View view) {
            super(view);
            tvProdCode=(TextView)view.findViewById(R.id.tv_loan_prod_code_value);
            tvLoanId=(TextView)view.findViewById(R.id.tv_loan_id_value);
            tvInstallmentNo=(TextView)view.findViewById(R.id.tv_installment_no);
            tvEmi=(TextView)view.findViewById(R.id.tv_emi);
            tvTotalDue=(TextView)view.findViewById(R.id.tv_total_due);
            etCollection=(EditText) view.findViewById(R.id.et_collection);

            spReason=(Spinner)view.findViewById(R.id.sp_arrear_reason);
            rlReason=(RelativeLayout) view.findViewById(R.id.rl_reason);

        }
    }

    public CenterMeetingODEMIDetailsAdapter(Context context, List<EMIDetailsDTO> emiDetailsDTOList, AppHelper appHelper,
                                            EMIDetailsCallBackInterface emiDetailsCallBackInterface) {
        this.context = context;
        this.emiDetailsDTOList = emiDetailsDTOList;
        this.appHelper = appHelper;
        this.emiDetailsCallBackInterface=emiDetailsCallBackInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.center_meeting_od_emi_details_adapter_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            if (emiDetailsDTOList != null && emiDetailsDTOList.size() > 0) {
                EMIDetailsDTO emiDetailsDTO = emiDetailsDTOList.get(position);

                if(emiDetailsDTO !=null ) {
                    int maxValue=emiDetailsDTO.getEMI() + emiDetailsDTO.getTotalDue();

                    holder.etCollection.setFilters(new InputFilter[]{new InputFilterMinMax(0, maxValue)});

                    String productCode="3138"; // TODO: HARDCODED
                    holder.tvProdCode.setText(productCode);
                    holder.tvLoanId.setText(emiDetailsDTO.getLAN());
                    holder.tvInstallmentNo.setText(String.valueOf(emiDetailsDTO.getCurrent_Installment())+"/"+String.valueOf(emiDetailsDTO.getTotal_Installment()));
                    holder.tvEmi.setText(String.valueOf(emiDetailsDTO.getEMI()));
                    holder.tvTotalDue.setText(String.valueOf(emiDetailsDTO.getTotalDue()));
                    holder.etCollection.setText(String.valueOf(emiDetailsDTO.getCollection()));

                    if( ! TextUtils.isEmpty(holder.etCollection.getText().toString())) {
                        if (Integer.parseInt(holder.etCollection.getText().toString()) > JLG_MINIMUM_OVERDUE_COLLECTION ) {

                            if (Integer.parseInt(holder.etCollection.getText().toString()) <= emiDetailsDTO.getTotalDue()) {
                                holder.rlReason.setVisibility(View.GONE);
                                holder.etCollection.setTextColor(context.getResources().getColor(R.color.light_green));
                                emiDetailsDTO.setArrearReason("");
                            } else {
                                holder.rlReason.setVisibility(View.GONE);
                                holder.etCollection.setTextColor(context.getResources().getColor(R.color.rbl_red));
                            }
                        }
                        else {
                            holder.rlReason.setVisibility(View.VISIBLE);
                            emiDetailsDTO.setArrearReason("");
                            holder.etCollection.setTextColor(context.getResources().getColor(R.color.rbl_red));
                        }
                    }

//                    if( ! TextUtils.isEmpty(holder.etCollection.getText().toString())) {
//                        if (Integer.parseInt(holder.etCollection.getText().toString()) >= emiDetailsDTO.getTotalDue()) {
//                            holder.rlReason.setVisibility(View.GONE);
//                            holder.etCollection.setTextColor(context.getResources().getColor(R.color.light_green));
//                            emiDetailsDTO.setArrearReason("");
//                        } else {
//                            holder.rlReason.setVisibility(View.VISIBLE);
//                            holder.etCollection.setTextColor(context.getResources().getColor(R.color.rbl_red));
//                        }
//                    }

                    if( holder.rlReason.getVisibility()== View.VISIBLE && ! TextUtils.isEmpty(emiDetailsDTO.getArrearReason())) {
                        holder.spReason.setSelection(appHelper.getSpinnerPosition(holder.spReason, emiDetailsDTO.getArrearReason()));
                    }

                    holder.etCollection.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            try {
                                if (!TextUtils.isEmpty(s.toString())) {
                                    emiDetailsDTO.setCollection(Integer.parseInt(s.toString()));
                                }else {
                                    emiDetailsDTO.setCollection(0);
                                }

                                emiDetailsCallBackInterface.totalCollectionCallBack(position,emiDetailsDTOList);

                                if (Integer.parseInt(holder.etCollection.getText().toString()) > JLG_MINIMUM_OVERDUE_COLLECTION ) {

                                    if (Integer.parseInt(holder.etCollection.getText().toString()) <= emiDetailsDTO.getTotalDue()) {
                                        holder.rlReason.setVisibility(View.GONE);
                                        holder.etCollection.setTextColor(context.getResources().getColor(R.color.light_green));
                                        emiDetailsDTO.setArrearReason("");
                                    } else {
                                        holder.rlReason.setVisibility(View.GONE);
                                        holder.etCollection.setTextColor(context.getResources().getColor(R.color.rbl_red));
                                    }
                                }
                                else {
                                    holder.rlReason.setVisibility(View.VISIBLE);
                                    emiDetailsDTO.setArrearReason("");
                                    holder.etCollection.setTextColor(context.getResources().getColor(R.color.rbl_red));
                                }

                            }catch (Exception ex){
                                ex.printStackTrace();
                                holder.rlReason.setVisibility(View.VISIBLE);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });

                    holder.spReason.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if(position != -1){
                                emiDetailsDTO.setArrearReason(holder.spReason.getSelectedItem().toString());
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public List<EMIDetailsDTO> getEmiDetailsDTOList() {
        return emiDetailsDTOList;
    }


    @Override
    public int getItemCount() {
        if (emiDetailsDTOList != null) {

            return emiDetailsDTOList.size();
        } else {
            return 0;
        }
    }

    public void setItem(List<EMIDetailsDTO> emiDetailsDTOList) {
        try {
            if (emiDetailsDTOList != null && emiDetailsDTOList.size() > 0) {
                this.emiDetailsDTOList.clear();
                this.emiDetailsDTOList = emiDetailsDTOList;
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void clearItems() {
        try {
            if (emiDetailsDTOList != null && emiDetailsDTOList.size() > 0) {
                emiDetailsDTOList.clear();
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

  public  interface  EMIDetailsCallBackInterface{
        void totalCollectionCallBack(int position, List<EMIDetailsDTO> emiDetailsDTOList);
    }

    public class InputFilterMinMax implements InputFilter {
        private int min, max;

        public InputFilterMinMax(int min, int max) {
            this.min = min;
            this.max = max;
        }

        public InputFilterMinMax(String min, String max) {
            this.min = Integer.parseInt(min);
            this.max = Integer.parseInt(max);
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            try {
                // Remove the string out of destination that is to be replaced
                String newVal = dest.toString().substring(0, dstart) + dest.toString().substring(dend, dest.toString().length());
                // Add the new string in
                newVal = newVal.substring(0, dstart) + source.toString() + newVal.substring(dstart, newVal.length());
                int input = Integer.parseInt(newVal);
                if (isInRange(min, max, input))
                    return null;
            } catch (NumberFormatException nfe) { }
            return "";
        }

        private boolean isInRange(int a, int b, int c) {
            return b > a ? c >= a && c <= b : c >= b && c <= a;
        }
    }
}
