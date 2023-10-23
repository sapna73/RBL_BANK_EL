package com.saartak.el.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.helpers.AppHelper;
import com.saartak.el.R;
import com.saartak.el.database.entity.GroupTable;

import java.util.List;


public class GroupFormationAdapter extends RecyclerView.Adapter<GroupFormationAdapter.LoanTypeViewHolder> implements Filterable {

    private Context context;
    List<List<GroupTable>> groupTableList;
    List<List<GroupTable>> groupTableListFiltered;
    SyncCallbackInterface syncCallbackInterface;
    AppHelper appHelper;

    public GroupFormationAdapter(Context context, List<List<GroupTable>> groupTableList,
                                 SyncCallbackInterface syncCallbackInterface, AppHelper appHelper) {
        this.context = context;
        this.groupTableList = groupTableList;
        this.groupTableListFiltered = groupTableList;
        this.syncCallbackInterface = syncCallbackInterface;
        this.appHelper = appHelper;
    }

    @NonNull
    @Override
    public LoanTypeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.group_formation_row_item, viewGroup, false);
        return new LoanTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoanTypeViewHolder loanTypeViewHolder, int i) {
        try {
            if (groupTableListFiltered != null && groupTableListFiltered.size() > 0) {
                List<GroupTable> groupTableList = groupTableListFiltered.get(i);
                for(GroupTable groupTable : groupTableList) {
                    loanTypeViewHolder.tvGroupName.setText(groupTable.getGroupName());
                    loanTypeViewHolder.tvGroupId.setText(groupTable.getGroupId());
                    if (groupTable.isTeamLeaderOne()) {
                        loanTypeViewHolder.tvLeaderOneName.setText(groupTable.getClientName());
                    }
                    if (groupTable.isTeamLeaderTwo()) {
                        loanTypeViewHolder.tvLeaderTwoName.setText(groupTable.getClientName());
                    }
                }

                loanTypeViewHolder.llSummaryDetails.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        List<GroupTable> groupTableList=groupTableListFiltered.get(i);
//                            syncCallbackInterface.viewMemberCallBack(i, groupTableList);
                    }
                });

                loanTypeViewHolder.btnAddMember.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        List<GroupTable> groupTableList=groupTableListFiltered.get(i);
                            syncCallbackInterface.addMemberCallBack(i, groupTableList);
                    }
                });
                loanTypeViewHolder.btnChooseLeader.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        List<GroupTable> groupTableListForChoosingLeader=groupTableListFiltered.get(i);
                            syncCallbackInterface.chooseLeaderCallBack(i, groupTableListForChoosingLeader);
                    }
                });

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (groupTableListFiltered != null) {

            return groupTableListFiltered.size();
        } else {
            return 0;
        }
    }

    public void setItem(List<List<GroupTable>> groupTableList) {
        try {
            if (this.groupTableList != null && this.groupTableList.size() > 0) {
                this.groupTableList.clear();
                this.groupTableListFiltered.clear();
                this.groupTableList = groupTableList;
                this.groupTableListFiltered = groupTableList;
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void clearItems() {
        try {
            if (groupTableList != null && groupTableList.size() > 0) {
                groupTableList.clear();
                groupTableListFiltered.clear();
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public class LoanTypeViewHolder extends RecyclerView.ViewHolder {
        TextView tvGroupName,tvGroupId,tvLeaderOneName, tvLeaderTwoName;
        Button btnAddMember,btnChooseLeader;
        LinearLayout llSummaryDetails;

        public LoanTypeViewHolder(@NonNull View itemView) {
            super(itemView);

            tvGroupName = (TextView) itemView.findViewById(R.id.tv_group_name_value);
            tvGroupId = (TextView) itemView.findViewById(R.id.tv_group_id_value);
            tvLeaderOneName = (TextView) itemView.findViewById(R.id.tv_leader_1_value);
            tvLeaderTwoName = (TextView) itemView.findViewById(R.id.tv_leader_2_value);
            llSummaryDetails = (LinearLayout) itemView.findViewById(R.id.ll_summary_values);
            btnAddMember=(Button)itemView.findViewById(R.id.btn_add_member);
            btnChooseLeader=(Button)itemView.findViewById(R.id.btn_choose_leader);
        }
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();


                FilterResults filterResults = new FilterResults();
                filterResults.values = groupTableListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                groupTableListFiltered = (List<List<GroupTable>>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface SyncCallbackInterface {
        void addMemberCallBack(int position, List<GroupTable> groupTableList);
        void viewMemberCallBack(int position, List<GroupTable> groupTableList);
        void chooseLeaderCallBack(int position, List<GroupTable> groupTableList);
    }

}
