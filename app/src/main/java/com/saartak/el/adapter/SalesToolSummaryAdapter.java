package com.saartak.el.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.bfil.uilibrary.helpers.AppHelper;
import com.saartak.el.R;
import com.saartak.el.database.entity.SalesToolTable;

import java.util.List;

import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_APPLICATION_IS_NOT_ELIGIBLE_FOR_FURTHER_PROCESSING;
import static com.saartak.el.dynamicui.constants.ParametersConstant.RADIO_BUTTON_ITEM_RENTED;

public class SalesToolSummaryAdapter extends RecyclerView.Adapter<SalesToolSummaryAdapter.LoanTypeViewHolder> implements Filterable {

    private Context context;
    List<SalesToolTable> rawDataTableList;
    List<SalesToolTable> rawDataTableListFiltered;
    SyncCallbackInterface syncCallbackInterface;
    AppHelper appHelper;


    public SalesToolSummaryAdapter(Context context, List<SalesToolTable> rawDataTableList,
                                   SyncCallbackInterface syncCallbackInterface, AppHelper appHelper) {
        this.context = context;
        this.rawDataTableList = rawDataTableList;
        this.rawDataTableListFiltered = rawDataTableList;
        this.syncCallbackInterface = syncCallbackInterface;
        this.appHelper = appHelper;
    }

    @NonNull
    @Override
    public LoanTypeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sales_tool_row_item, viewGroup, false);
        return new LoanTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoanTypeViewHolder loanTypeViewHolder, int i) {
        try {
            if (rawDataTableListFiltered != null && rawDataTableListFiltered.size() > 0) {
                loanTypeViewHolder.tvName.setText(rawDataTableListFiltered.get(i).getClientName());
                loanTypeViewHolder.tvPhone.setText(rawDataTableListFiltered.get(i).getMobileNo());
                loanTypeViewHolder.tvLoanType.setText(rawDataTableListFiltered.get(i).getLoan_type());

                SalesToolTable SalesToolTable = rawDataTableListFiltered.get(i);
                if (SalesToolTable != null && SalesToolTable.isSync()) {
                    loanTypeViewHolder.ivStatus.setImageResource(R.drawable.ic_check_circle_black_24dp);
                    loanTypeViewHolder.llSalestoolSummary.setEnabled(false);


                } else {
                    loanTypeViewHolder.ivStatus.setImageResource(R.drawable.ic_sync_black_24dp);
                    loanTypeViewHolder.llSalestoolSummary.setEnabled(true);
                }
                // TODO: not in BRD, considering score as reference to display sync icon

               /* int score = 0;
                if(rawDataTableListFiltered.get(i).getScore()!=null) {
                    score = Integer.parseInt(rawDataTableListFiltered.get(i).getScore());
                }*/
                if (rawDataTableListFiltered.get(i).isDataCaptured()) {
                    loanTypeViewHolder.ivStatus.setVisibility(View.VISIBLE);
                    loanTypeViewHolder.cvSalesTool.setBackgroundColor(context.getResources().getColor(R.color.lead_deatails_interested));
                    loanTypeViewHolder.textViewSeparator.setBackgroundColor(context.getResources().getColor(R.color.lead_deatails_interested_separatoe));
                } else {
                    loanTypeViewHolder.ivStatus.setVisibility(View.GONE);
                    loanTypeViewHolder.cvSalesTool.setBackgroundColor(context.getResources().getColor(R.color.lead_deatails_not_interested));
                    loanTypeViewHolder.textViewSeparator.setBackgroundColor(context.getResources().getColor(R.color.lead_deatails_not_interested_separatoe));
                }

                loanTypeViewHolder.llSalestoolSummary.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        syncCallbackInterface.openScreenCallback(i,SalesToolTable);
                    }
                });

                loanTypeViewHolder.ivStatus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO: No need to check this condition
                        if (appHelper.isNetworkAvailable()) {
                            appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons("Would You Like To Proceed/Convert To Application ?",
                                    new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {
                                            if (SalesToolTable.getResidentType().equalsIgnoreCase(RADIO_BUTTON_ITEM_RENTED)
                                                    && SalesToolTable.getBusinessPlace().equalsIgnoreCase(RADIO_BUTTON_ITEM_RENTED)
                                                    && SalesToolTable.getOwnAPlot().equalsIgnoreCase("NO")) {
                                                appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons(ERROR_MESSAGE_APPLICATION_IS_NOT_ELIGIBLE_FOR_FURTHER_PROCESSING, "Yes,Sync", "No,Cancel", new ConfirmationDialog.PrintActionCallback() {
                                                    @Override
                                                    public void onAction() {

                                                    }

                                                    @Override
                                                    public void onPrint() {
                                                        if (SalesToolTable != null && !SalesToolTable.isSync()) {
                                                            syncCallbackInterface.syncCallback(i, SalesToolTable);
                                                        } else {
                                                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Data already synced ");
                                                        }
                                                    }
                                                });
                                            }
                                            else {
                                                if (SalesToolTable != null && !SalesToolTable.isSync()) {
                                                    syncCallbackInterface.syncCallback(i, SalesToolTable);
                                                } else {
                                                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Data already synced ");
                                                }
                                            }
                                        }
                                    });

                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Internet Connection Required To Sync Data");
                        }
                    }
                });
                loanTypeViewHolder.ivCall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO: No need to check this condition
                        if (rawDataTableListFiltered != null && rawDataTableListFiltered.size() > 0) {
                            String phoneNo = rawDataTableListFiltered.get(i).getMobileNo();
                            appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons("Would you like to call to this number (" + phoneNo + ")",
                                    new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {
                                            syncCallbackInterface.callBackForPhoneCall(i, phoneNo);
                                        }
                                    });

                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Unable to call");
                        }
                    }
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (rawDataTableListFiltered != null) {

            return rawDataTableListFiltered.size();
        } else {
            return 0;
        }
    }



    public class LoanTypeViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPhone, tvLoanType;
        ImageView ivStatus, ivCall;
        LinearLayout cvSalesTool;
        LinearLayout llSalestoolSummary;
        private TextView textViewSeparator;

        public LoanTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_sales_tool_name_value);
            tvPhone = (TextView) itemView.findViewById(R.id.tv_sales_tool_phone_value);
            tvLoanType = (TextView) itemView.findViewById(R.id.tv_sales_tool_type);
            ivStatus = (ImageView) itemView.findViewById(R.id.iv_sales_tool_sync_status);
            ivCall = (ImageView) itemView.findViewById(R.id.iv_sales_tool_call);
            llSalestoolSummary = (LinearLayout) itemView.findViewById(R.id.ll_sales_tool_summary_values);
            cvSalesTool = itemView.findViewById(R.id.cv_sales_tool);
            textViewSeparator = itemView.findViewById(R.id.textView_separator);

        }
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    rawDataTableListFiltered = rawDataTableList;
                }
              /*  // TODO: ALL STATIC DATA ... NEED TO FIX THIS ISSUE
                else if (charString.equalsIgnoreCase("Interested")) {
                    List<SalesToolTable> filteredList = new ArrayList<>();
                    for (SalesToolTable row : rawDataTableList) {
                        if (row.getInterestedInLoan()==1) {
                            filteredList.add(row);
                        }
                    }
                    rawDataTableListFiltered = filteredList;
                } else if (charString.equalsIgnoreCase("Not Interested")) {
                    List<SalesToolTable> filteredList = new ArrayList<>();
                    for (SalesToolTable row : rawDataTableList) {
                        if (row.getInterestedInLoan()==0) {
                            filteredList.add(row);
                        }
                    }
                    rawDataTableListFiltered = filteredList;
                }

                else if (charString.equalsIgnoreCase("All")) {
                    rawDataTableListFiltered = rawDataTableList;
                } else if (charString.equalsIgnoreCase("ASC")) {
                    rawDataTableListFiltered = rawDataTableList;
                } else if (charString.equalsIgnoreCase("DESC")) {
                    List<SalesToolTable> filteredList = new ArrayList<>();
                    for (int i = rawDataTableList.size(); i >= 1; i--) {
                        filteredList.add(rawDataTableList.get(i - 1));
                    }
                    rawDataTableListFiltered = filteredList;

                }else if (charString.equalsIgnoreCase("ASCENDING")) {

                    Collections.sort(rawDataTableListFiltered, new Comparator<SalesToolTable>() {
                        @Override
                        public int compare(SalesToolTable lhs, SalesToolTable rhs) {
                            return lhs.getMarketName().compareTo(rhs.getMarketName());
                        }
                    });


                } else if (charString.equalsIgnoreCase("DESCENDING")){
                    Collections.sort(rawDataTableListFiltered, new Comparator<SalesToolTable>() {
                        @Override
                        public int compare(SalesToolTable lhs, SalesToolTable rhs) {
                            return rhs.getMarketName().compareTo(lhs.getMarketName());
                        }
                    });

                } else {
//                    List<Contact> filteredList = new ArrayList<>();
                    List<SalesToolTable> filteredList = new ArrayList<>();
                    for (SalesToolTable row : rawDataTableList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getMobileNo().toLowerCase().contains(charString.toLowerCase())
                                || row.getMarketName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    rawDataTableListFiltered = filteredList;
                }*/

                FilterResults filterResults = new FilterResults();
                filterResults.values = rawDataTableListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                rawDataTableListFiltered = (List<SalesToolTable>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public interface SyncCallbackInterface {
        void syncCallback(int position, SalesToolTable SalesToolTable);

        void callBackForPhoneCall(int position, String phoneNo);

        void openScreenCallback(int position, SalesToolTable SalesToolTable);
    }
}
