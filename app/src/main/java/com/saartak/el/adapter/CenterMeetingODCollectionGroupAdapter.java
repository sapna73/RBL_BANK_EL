package com.saartak.el.adapter;

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

import com.bfil.uilibrary.helpers.AppHelper;
import com.saartak.el.R;
import com.saartak.el.activities.CenterMeetingODCollectionActivity;
import com.saartak.el.activities.CenterMeetingODEMIDetailsActivity;
import com.saartak.el.database.entity.CenterMeetingCollectionTable;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.util.List;

import static com.saartak.el.constants.AppConstant.MODULE_TYPE_COLLECTION;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_ID;
import static com.saartak.el.constants.AppConstant.PARAM_CENTER_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_CLIENT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_NAME;

public class CenterMeetingODCollectionGroupAdapter extends RecyclerView.Adapter<CenterMeetingODCollectionGroupAdapter.ViewHolder>  {

    private Context context;
    List<CenterMeetingCollectionTable> centerMeetingCollectionTableList;
    AppHelper appHelper;
    DynamicUIViewModel viewModel;

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvClientName,tvClientId,tvEmiDue,tvTotalDue,tvCollectedAmt,tvEmiDetails;
        CheckBox cbConfirm,cbDigitalCollection;

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
            cbDigitalCollection=(CheckBox) view.findViewById(R.id.cb_digital_collection);
        }
    }

    public CenterMeetingODCollectionGroupAdapter(Context context, List<CenterMeetingCollectionTable> centerMeetingCollectionTableList,
                                                 AppHelper appHelper, DynamicUIViewModel viewModel) {
        this.context = context;
        this.centerMeetingCollectionTableList = centerMeetingCollectionTableList;
        this.appHelper = appHelper;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.center_meeting_od_collection_group_adapter_item, viewGroup, false);
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

                    holder.cbConfirm.setChecked(centerMeetingCollectionTable.isConfirm());
                    holder.cbDigitalCollection.setChecked(centerMeetingCollectionTable.isDigitalPayment());

                    // TODO: Check isEditable
                    if (centerMeetingCollectionTable.isSync()) {
                        holder.cbConfirm.setEnabled(false);
                        holder.cbDigitalCollection.setEnabled(false);
                        holder.tvEmiDetails.setEnabled(false);
                    }else {
                        holder.cbConfirm.setEnabled(true);
                        holder.cbDigitalCollection.setEnabled(true);
                        holder.tvEmiDetails.setEnabled(true);
                        // TODO: digital confirm enable false based on sms triggered
                        if (centerMeetingCollectionTable.isSmsTriggered()){
                            holder.cbDigitalCollection.setEnabled(false);
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
                        viewModel.getTotalPaidAmtFromCollection(centerMeetingCollectionTable.getCustomerId());
                        if (viewModel.getIntegerLiveData() != null) {
                            Observer observer = new Observer() {
                                @Override
                                public void onChanged(@Nullable Object o) {
                                    int collectedAmt = (int) o;
                                    viewModel.getIntegerLiveData().removeObserver(this::onChanged);
                                    holder.tvCollectedAmt.setText(String.valueOf(collectedAmt));

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
                            Intent collection = new Intent(context, CenterMeetingODEMIDetailsActivity.class);
                            CenterMeetingODCollectionActivity centerMeetingODCollectionActivity=(CenterMeetingODCollectionActivity)context;
                            collection.putExtra(PARAM_LOAN_TYPE, centerMeetingODCollectionActivity.loanType);
                            collection.putExtra(PARAM_USER_NAME, centerMeetingODCollectionActivity.userName);
                            collection.putExtra(PARAM_BRANCH_ID, centerMeetingODCollectionActivity.branchId);
                            collection.putExtra(PARAM_BRANCH_GST_CODE, centerMeetingODCollectionActivity.branchGSTcode);
                            collection.putExtra(PARAM_USER_ID, centerMeetingODCollectionActivity.userId);
                            collection.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_COLLECTION);
                            collection.putExtra(PARAM_PRODUCT_ID, centerMeetingODCollectionActivity.productId);
                            collection.putExtra(PARAM_CENTER_NAME, centerMeetingODCollectionActivity.CENTER_NAME); // TODO: CenterName
                            collection.putExtra(PARAM_CLIENT_ID, centerMeetingCollectionTable.getCustomerId()); // TODO: CLIENT ID
                            context.startActivity(collection);
                        }
                    });


                    holder.cbConfirm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            try {
                                viewModel.confirmCenterMeetingCollection(centerMeetingCollectionTable,isChecked,
                                       false);

                                if (viewModel.getIntegerLiveData() != null) {
                                    Observer observer = new Observer() {
                                        @Override
                                        public void onChanged(@Nullable Object o) {
                                            int totalAmtPaid = (int) o;
                                            viewModel.getIntegerLiveData().removeObserver(this::onChanged);
                                            holder.tvCollectedAmt.setText(String.valueOf(totalAmtPaid));

                                        }
                                    };
                                    viewModel.getIntegerLiveData().observe((LifecycleOwner)context, observer);
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }

                        }
                    });

                    holder.cbDigitalCollection.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            try {
                                viewModel.confirmDigitalCenterMeetingCollection(centerMeetingCollectionTable,isChecked,false);

                                if (viewModel.getIntegerLiveData() != null) {
                                    Observer observer = new Observer() {
                                        @Override
                                        public void onChanged(@Nullable Object o) {
                                            int totalAmtPaid = (int) o;
                                            viewModel.getIntegerLiveData().removeObserver(this::onChanged);
                                            holder.tvCollectedAmt.setText(String.valueOf(totalAmtPaid));
                                        }
                                    };
                                    viewModel.getIntegerLiveData().observe((LifecycleOwner)context, observer);
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
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

    public void setItem(List<CenterMeetingCollectionTable> centerMeetingCollectionTableList,boolean isEditable) {
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
