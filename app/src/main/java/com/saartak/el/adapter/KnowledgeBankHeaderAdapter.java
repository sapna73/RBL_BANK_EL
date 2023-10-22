package com.saartak.el.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.helpers.AppHelper;
import com.saartak.el.R;
import com.saartak.el.database.entity.KnowledgeBankTable;

import java.util.ArrayList;
import java.util.List;

public class KnowledgeBankHeaderAdapter extends RecyclerView.Adapter<KnowledgeBankHeaderAdapter.LoanTypeViewHolder> implements Filterable {

    private Context context;
    List<KnowledgeBankTable> knowledgeBankTableList;
    List<KnowledgeBankTable> knowledgeBankTableListFiltered;
    SyncCallbackInterface syncCallbackInterface;
    AppHelper appHelper;

    public KnowledgeBankHeaderAdapter(Context context, List<KnowledgeBankTable> knowledgeBankTableList,
                                      SyncCallbackInterface syncCallbackInterface, AppHelper appHelper) {
        this.context = context;
        this.knowledgeBankTableList = knowledgeBankTableList;
        this.knowledgeBankTableListFiltered = knowledgeBankTableList;
        this.syncCallbackInterface = syncCallbackInterface;
        this.appHelper = appHelper;
    }

    @NonNull
    @Override
    public LoanTypeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.knowledge_bank_header_row_item, viewGroup, false);
        return new LoanTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoanTypeViewHolder loanTypeViewHolder, int i) {
        try {
            if (knowledgeBankTableListFiltered != null && knowledgeBankTableListFiltered.size() > 0) {
                KnowledgeBankTable centerCreationTable = knowledgeBankTableListFiltered.get(i);
                loanTypeViewHolder.tvVillageName.setText(centerCreationTable.getDocumentName());

                loanTypeViewHolder.tvVillageName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                     syncCallbackInterface.openScreenCallback(i,centerCreationTable);

                    }
                });

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (knowledgeBankTableListFiltered != null) {

            return knowledgeBankTableListFiltered.size();
        } else {
            return 0;
        }
    }

    public void setItem(List<KnowledgeBankTable> knowledgeBankTableList) {
        try {
            if (this.knowledgeBankTableList != null && this.knowledgeBankTableList.size() > 0) {
                this.knowledgeBankTableList.clear();
                this.knowledgeBankTableListFiltered.clear();
                this.knowledgeBankTableList = knowledgeBankTableList;
                this.knowledgeBankTableListFiltered = knowledgeBankTableList;
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void clearItems() {
        try {
            if (knowledgeBankTableList != null && knowledgeBankTableList.size() > 0) {
                knowledgeBankTableList.clear();
                knowledgeBankTableListFiltered.clear();
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public class LoanTypeViewHolder extends RecyclerView.ViewHolder {
        TextView tvVillageName;

        public LoanTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvVillageName = (TextView) itemView.findViewById(R.id.tv_village_name_value);
        }
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    knowledgeBankTableListFiltered = knowledgeBankTableList;
                }else if (charString.equalsIgnoreCase("All")) {
                    knowledgeBankTableListFiltered = knowledgeBankTableList;
                } else if (charString.equalsIgnoreCase("ASC")) {
                    knowledgeBankTableListFiltered = knowledgeBankTableList;
                } else if (charString.equalsIgnoreCase("DESC")) {
                    List<KnowledgeBankTable> filteredList = new ArrayList<>();
                    for (int i = knowledgeBankTableList.size(); i >= 1; i--) {
                        filteredList.add(knowledgeBankTableList.get(i - 1));
                    }
                    knowledgeBankTableListFiltered = filteredList;

                }  else if (charString.equalsIgnoreCase("ASCENDING")) {

//                    Collections.sort(fetchOtherDayCollectionTableList, new Comparator<String>() {
//                        @Override
//                        public int compare(String lhs, String rhs) {
//                            return lhs.compareTo(rhs);
//                        }
//                    });

                } else if (charString.equalsIgnoreCase("DESCENDING")){
//                    Collections.sort(fetchOtherDayCollectionTableList, new Comparator<String>() {
//                        @Override
//                        public int compare(String lhs, String rhs) {
//                            return rhs.compareTo(lhs);
//                        }
//                    });

                } else {
//                    List<Contact> filteredList = new ArrayList<>();
                    List<KnowledgeBankTable> filteredList = new ArrayList<>();
                    for (KnowledgeBankTable row : knowledgeBankTableList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number matchh
                        if (row.getDocumentName().toLowerCase().contains(charString.toLowerCase())
                                || row.getDocumentName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    knowledgeBankTableListFiltered = filteredList;
                }


                FilterResults filterResults = new FilterResults();
                filterResults.values = knowledgeBankTableListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                knowledgeBankTableListFiltered = (List<KnowledgeBankTable>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public interface SyncCallbackInterface {
        void openScreenCallback(int position, KnowledgeBankTable centerCreationTable);
    }

}
