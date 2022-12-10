package com.swadhaar.los.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.bfil.uilibrary.helpers.AppHelper;
import com.swadhaar.los.R;
import com.swadhaar.los.activities.CenterMeetingCollectionActivity;
import com.swadhaar.los.activities.CenterMeetingEMIDetailsActivity;
import com.swadhaar.los.activities.DigitalCollectionActivity;
import com.swadhaar.los.activities.DigitalCollectionEMIDetailsActivity;
import com.swadhaar.los.database.entity.CenterMeetingCollectionTable;
import com.swadhaar.los.view_models.DynamicUIViewModel;

import java.util.List;

import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_COLLECTION;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_CENTER_NAME;
import static com.swadhaar.los.constants.AppConstant.PARAM_CLIENT_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_NAME;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_PLEASE_INITIATE_TRANSACTION_AND_CONFIRM;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_UNABLE_TO_GET_DOCUMENT_MASTER;

public class DigitalCollectionGroupAdapter extends RecyclerView.Adapter<DigitalCollectionGroupAdapter.ViewHolder>  {

    private Context context;
    List<CenterMeetingCollectionTable> centerMeetingCollectionTableList;
    AppHelper appHelper;
    DynamicUIViewModel viewModel;
    boolean isEditable;

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvClientName,tvClientId,tvEmiDue,tvTotalDue,tvCollectedAmt,tvEmiDetails;
        CheckBox cbConfirm;

        public ViewHolder(View view) {
            super(view);
            tvClientName=(TextView)view.findViewById(R.id.tv_client_name);
            tvClientId=(TextView)view.findViewById(R.id.tv_client_id);
            tvEmiDue=(TextView)view.findViewById(R.id.tv_emi_due);
            tvTotalDue=(TextView)view.findViewById(R.id.tv_total_due);
            tvEmiDetails=(TextView)view.findViewById(R.id.tv_emi_details);
            tvEmiDetails.setText(Html.fromHtml(String.format(context.getString(R.string.details))));
            tvCollectedAmt=(TextView) view.findViewById(R.id.tv_collected_amt);
            cbConfirm=(CheckBox) view.findViewById(R.id.cb_collection_confirm);

        }
    }

    public DigitalCollectionGroupAdapter(Context context, List<CenterMeetingCollectionTable> centerMeetingCollectionTableList,
                                         AppHelper appHelper, DynamicUIViewModel viewModel) {
        this.context = context;
        this.centerMeetingCollectionTableList = centerMeetingCollectionTableList;
        this.appHelper = appHelper;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.digital_collection_group_adapter_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            if (centerMeetingCollectionTableList != null && centerMeetingCollectionTableList.size() > 0) {
                CenterMeetingCollectionTable centerMeetingCollectionTable = centerMeetingCollectionTableList.get(position);

                if(centerMeetingCollectionTable !=null ) {
                    holder.tvClientName.setText(centerMeetingCollectionTable.getCustomerName());
                    holder.tvClientId.setText(centerMeetingCollectionTable.getCustomerId());
                    holder.tvEmiDue.setText(String.valueOf(centerMeetingCollectionTable.getEMI()));
                    holder.tvTotalDue.setText(String.valueOf(centerMeetingCollectionTable.getTotalDue()));
                    holder.tvCollectedAmt.setText(String.valueOf(centerMeetingCollectionTable.getTotalDue()));

//                    holder.cbConfirm.setChecked(centerMeetingCollectionTable.isConfirm());

                    // TODO: Check isEditable
                    if (centerMeetingCollectionTable.isSync()) {
                        holder.cbConfirm.setEnabled(false);
                        holder.tvEmiDetails.setEnabled(false);
                    }else {
                        holder.cbConfirm.setEnabled(true);
                        holder.tvEmiDetails.setEnabled(true);
                        if (centerMeetingCollectionTable.isSmsTriggered()){
                            holder.cbConfirm.setChecked(true);
                            holder.cbConfirm.setEnabled(false);
                        }
                    }

                    try {
                        viewModel.getTotalEMIForCollection(centerMeetingCollectionTable.getCustomerId());
                        if (viewModel.getIntegerLiveData() != null) {
                            Observer observer = new Observer() {
                                @Override
                                public void onChanged(@Nullable Object o) {
                                    int totalEMI = (int) o;
                                    viewModel.getIntegerLiveData().removeObserver(this::onChanged);
                                    holder.tvEmiDue.setText(String.valueOf(totalEMI));

                                }
                            };
                            viewModel.getIntegerLiveData().observe((LifecycleOwner)context, observer);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    try {
                        viewModel.getTotalDueForCollection(centerMeetingCollectionTable.getCustomerId());
                        if (viewModel.getIntegerLiveData() != null) {
                            Observer observer = new Observer() {
                                @Override
                                public void onChanged(@Nullable Object o) {
                                    int totalDue = (int) o;
                                    viewModel.getIntegerLiveData().removeObserver(this::onChanged);
                                    holder.tvTotalDue.setText(String.valueOf(totalDue));

                                }
                            };
                            viewModel.getIntegerLiveData().observe((LifecycleOwner)context, observer);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    try {
                        viewModel.getTotalPaidAmtFromDigitalCollection(centerMeetingCollectionTable.getCustomerId());
                        if (viewModel.getIntegerLiveData() != null) {
                            Observer observer = new Observer() {
                                @Override
                                public void onChanged(@Nullable Object o) {
                                    int collectedAmt = (int) o;
                                    viewModel.getIntegerLiveData().removeObserver(this::onChanged);
                                    holder.tvCollectedAmt.setText(String.valueOf(collectedAmt));

                                    int totalDueAmt=Integer.parseInt(holder.tvTotalDue.getText().toString());
                                    if(collectedAmt<totalDueAmt){
                                        if (!centerMeetingCollectionTable.isSaved()) {
                                            holder.cbConfirm.setChecked(false);
                                        }

                                    }
                                    else{
                                        if(centerMeetingCollectionTable.isSavingsConfirm()){
                                            collectedAmt=collectedAmt+centerMeetingCollectionTable.getSavingsCollection();
                                            holder.tvCollectedAmt.setText(String.valueOf(collectedAmt));

                                        }

                                    }


                                }
                            };
                            viewModel.getIntegerLiveData().observe((LifecycleOwner)context, observer);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    holder.tvEmiDetails.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent collection = new Intent(context, DigitalCollectionEMIDetailsActivity.class);
                            DigitalCollectionActivity centerMeetingCollectionActivity=(DigitalCollectionActivity)context;
                            collection.putExtra(PARAM_LOAN_TYPE, centerMeetingCollectionActivity.loanType);
                            collection.putExtra(PARAM_USER_NAME, centerMeetingCollectionActivity.userName);
                            collection.putExtra(PARAM_BRANCH_ID, centerMeetingCollectionActivity.branchId);
                            collection.putExtra(PARAM_BRANCH_GST_CODE, centerMeetingCollectionActivity.branchGSTcode);
                            collection.putExtra(PARAM_USER_ID, centerMeetingCollectionActivity.userId);
                            collection.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_COLLECTION);
                            collection.putExtra(PARAM_PRODUCT_ID, centerMeetingCollectionActivity.productId);
                            collection.putExtra(PARAM_CENTER_NAME, centerMeetingCollectionActivity.CENTER_NAME); // TODO: CenterName
                            collection.putExtra(PARAM_CLIENT_ID, centerMeetingCollectionTable.getCustomerId()); // TODO: CLIENT ID
                            context.startActivity(collection);
                        }
                    });


                    holder.cbConfirm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (buttonView.isPressed()) {
                                try {
                                    if (centerMeetingCollectionTable.isSmsTriggered()) {
//                                viewModel.confirmCenterMeetingCollection(centerMeetingCollectionTable.getCustomerId(),isChecked,
//                                        holder.cbSavingsConfirm.isChecked());

                                        if (viewModel.getIntegerLiveData() != null) {
                                            Observer observer = new Observer() {
                                                @Override
                                                public void onChanged(@Nullable Object o) {
                                                    int totalAmtPaid = (int) o;
                                                    viewModel.getIntegerLiveData().removeObserver(this::onChanged);
                                                    holder.tvCollectedAmt.setText(String.valueOf(totalAmtPaid));

                                                }
                                            };
                                            viewModel.getIntegerLiveData().observe((LifecycleOwner) context, observer);
                                        }
                                    } else {
                                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR,
                                                ERROR_MESSAGE_PLEASE_INITIATE_TRANSACTION_AND_CONFIRM, new ConfirmationDialog.ActionCallback() {
                                                    @Override
                                                    public void onAction() {
                                                        if (isChecked) {
                                                            holder.cbConfirm.setChecked(false);
                                                        } else {
                                                            holder.cbConfirm.setChecked(false);
                                                        }
                                                    }
                                                });
                                    }
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    });
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public List<CenterMeetingCollectionTable> getListOfEligibilityTableList() {
        return centerMeetingCollectionTableList;
    }


    @Override
    public int getItemCount() {
        if (centerMeetingCollectionTableList != null) {

            return centerMeetingCollectionTableList.size();
        } else {
            return 0;
        }
    }

    public void setItem(List<CenterMeetingCollectionTable> centerMeetingCollectionTableList) {
        try {
            if (centerMeetingCollectionTableList != null && centerMeetingCollectionTableList.size() > 0) {
                this.centerMeetingCollectionTableList.clear();
                this.centerMeetingCollectionTableList = centerMeetingCollectionTableList;
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void clearItems() {
        try {
            if (centerMeetingCollectionTableList != null && centerMeetingCollectionTableList.size() > 0) {
                centerMeetingCollectionTableList.clear();
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



}
