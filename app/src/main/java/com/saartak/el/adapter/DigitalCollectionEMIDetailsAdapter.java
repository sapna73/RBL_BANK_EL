package com.saartak.el.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.bfil.uilibrary.helpers.AppHelper;
import com.saartak.el.R;
import com.saartak.el.models.EMIDetailsDTO;

import java.util.List;

public class DigitalCollectionEMIDetailsAdapter extends RecyclerView.Adapter<DigitalCollectionEMIDetailsAdapter.ViewHolder> {

    private Context context;
    List<EMIDetailsDTO> emiDetailsDTOList;
    AppHelper appHelper;
    EMIDetailsCallBackInterface emiDetailsCallBackInterface;

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvProdCode, tvLoanId, tvEmi, tvTotalDue;
        EditText etCollection;
        AppCompatImageButton btnSms;
        ImageView ivPaymentStatus;

        public ViewHolder(View view) {
            super(view);
            tvProdCode = (TextView) view.findViewById(R.id.tv_loan_prod_code_value);
            tvLoanId = (TextView) view.findViewById(R.id.tv_loan_id_value);
            tvEmi = (TextView) view.findViewById(R.id.tv_emi);
            tvTotalDue = (TextView) view.findViewById(R.id.tv_total_due);
            etCollection = (EditText) view.findViewById(R.id.et_collection);
            btnSms = (AppCompatImageButton) view.findViewById(R.id.btn_sms);
            ivPaymentStatus = (ImageView) itemView.findViewById(R.id.iv_payment_status);
        }
    }

    public DigitalCollectionEMIDetailsAdapter(Context context, List<EMIDetailsDTO> emiDetailsDTOList, AppHelper appHelper,
                                              EMIDetailsCallBackInterface emiDetailsCallBackInterface) {
        this.context = context;
        this.emiDetailsDTOList = emiDetailsDTOList;
        this.appHelper = appHelper;
        this.emiDetailsCallBackInterface = emiDetailsCallBackInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.digital_collection_emi_details_adapter_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            if (emiDetailsDTOList != null && emiDetailsDTOList.size() > 0) {
                EMIDetailsDTO emiDetailsDTO = emiDetailsDTOList.get(position);

                if (emiDetailsDTO != null) {
//                    int maxValue=emiDetailsDTO.getEMI() + emiDetailsDTO.getTotalDue();
                    int maxValue = emiDetailsDTO.getTotalDue();
                    holder.etCollection.setFilters(new InputFilter[]{new InputFilterMinMax(0, maxValue)});
//                    holder.etCollection.setFilters(new InputFilter[]{new InputFilterMinMax(500, maxValue)});

                    String productCode = "3138"; // TODO: HARDCODED
                    holder.tvProdCode.setText(productCode);
                    holder.tvLoanId.setText(emiDetailsDTO.getLAN());
                    holder.tvEmi.setText(String.valueOf(emiDetailsDTO.getEMI()));
                    holder.tvTotalDue.setText(String.valueOf(emiDetailsDTO.getTotalDue()));
                    holder.etCollection.setText(String.valueOf(emiDetailsDTO.getCollection()));

                    if (emiDetailsDTO.isPaymentStatus()) {
                        holder.ivPaymentStatus.setImageResource(R.drawable.ic_done_gray_24dp);
                    } else {
                        holder.ivPaymentStatus.setImageResource(R.drawable.ic_error_outline_gray_24dp);
                    }

                    if (emiDetailsDTO.getEMI() > 0 && emiDetailsDTO.getEMI() < 500) {
                        holder.etCollection.setText("500");
                    } else if (emiDetailsDTO.getEMI() == 0) {
                        holder.etCollection.setText(String.valueOf(emiDetailsDTO.getTotalDue()));
//                        if (emiDetailsDTO.getTotalDue()<500){
//                            holder.etCollection.setFilters(new InputFilter[]{new InputFilterMinMax(emiDetailsDTO.getTotalDue(), maxValue)});
//                        }
                    }

                    if (!TextUtils.isEmpty(holder.etCollection.getText().toString())) {
                        if (Integer.parseInt(holder.etCollection.getText().toString()) >= emiDetailsDTO.getTotalDue()) {
                            holder.etCollection.setTextColor(context.getResources().getColor(R.color.light_green));
                            emiDetailsDTO.setArrearReason("");
                        } else {
                            holder.etCollection.setTextColor(context.getResources().getColor(R.color.rbl_red));
                        }
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
                                } else {
                                    emiDetailsDTO.setCollection(0);
                                }

                                emiDetailsCallBackInterface.totalCollectionCallBack(position, emiDetailsDTOList);

                                if (s.length() >= holder.tvTotalDue.getText().length()) {
                                    if (Integer.parseInt(holder.etCollection.getText().toString()) >= emiDetailsDTO.getTotalDue()) {
                                        holder.etCollection.setTextColor(context.getResources().getColor(R.color.light_green));
                                        emiDetailsDTO.setArrearReason("");
                                    } else {
                                        holder.etCollection.setTextColor(context.getResources().getColor(R.color.rbl_red));
                                    }
                                } else {
                                    holder.etCollection.setTextColor(context.getResources().getColor(R.color.rbl_red));
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });

                    holder.btnSms.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (!TextUtils.isEmpty(holder.etCollection.getText().toString())) {
                                if (Integer.parseInt(holder.etCollection.getText().toString()) < 500) {
                                    if (emiDetailsDTO.getEMI() > 0 && emiDetailsDTO.getEMI() > 500) {
                                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Minimum Amount is 500");
                                    } else if (emiDetailsDTO.getEMI() > 0 && emiDetailsDTO.getEMI() < 500) {
                                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Minimum Amount is 500");
                                    } else if (emiDetailsDTO.getEMI() == 0 && emiDetailsDTO.getTotalDue() > 500) {
                                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Minimum Amount is 500");
                                    } else if (emiDetailsDTO.getEMI() == 0 && emiDetailsDTO.getTotalDue() < 500) {
                                        if (Integer.parseInt(holder.etCollection.getText().toString()) >= emiDetailsDTO.getTotalDue()) {
                                            emiDetailsCallBackInterface.smsTriggerCallBack(position, emiDetailsDTO, emiDetailsDTOList);
                                        } else {
                                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Minimum Amount is" + " " + emiDetailsDTO.getTotalDue());
                                        }
                                    }
                                } else {
                                    emiDetailsCallBackInterface.smsTriggerCallBack(position, emiDetailsDTO, emiDetailsDTOList);
                                }
                            }
//                            emiDetailsCallBackInterface.smsTriggerCallBack(position,emiDetailsDTO,emiDetailsDTOList);
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

    public interface EMIDetailsCallBackInterface {
        void totalCollectionCallBack(int position, List<EMIDetailsDTO> emiDetailsDTOList);

        void smsTriggerCallBack(int position, EMIDetailsDTO emiDetailsDTO, List<EMIDetailsDTO> emiDetailsDTOList);
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
