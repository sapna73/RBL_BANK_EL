package com.swadhaar.los.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.bfil.uilibrary.helpers.AppHelper;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.swadhaar.los.R;
import com.swadhaar.los.database.entity.MasterTable;
import com.swadhaar.los.database.entity.MasterTable;

import java.util.ArrayList;
import java.util.List;

import static com.swadhaar.los.constants.AppConstant.DATE_FORMAT_MM_DD_YYYY;

public class GroupMemberAdapter extends RecyclerView.Adapter<GroupMemberAdapter.ViewHolder> implements Filterable {

    private Context context;
    List<MasterTable> masterTableList;
    List<MasterTable> masterTableListFiltered;
    OnClickListener onClickListener;
    AppHelper appHelper;
    private SparseBooleanArray selected_items;
    private int current_selected_idx = -1;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView from, email, message, date, image_letter;
        public CircularImageView image;
        public RelativeLayout lyt_checked, lyt_image;
        public View lyt_parent;

        public ViewHolder(View view) {
            super(view);
            from = (TextView) view.findViewById(R.id.from);
            email = (TextView) view.findViewById(R.id.email);
            message = (TextView) view.findViewById(R.id.message);
            date = (TextView) view.findViewById(R.id.date);
            image_letter = (TextView) view.findViewById(R.id.image_letter);
            image =  (CircularImageView) view.findViewById(R.id.image);
            lyt_checked = (RelativeLayout) view.findViewById(R.id.lyt_checked);
            lyt_image = (RelativeLayout) view.findViewById(R.id.lyt_image);
            lyt_parent = (View) view.findViewById(R.id.lyt_parent);
        }
    }
    public GroupMemberAdapter(Context context, List<MasterTable> masterTableList, AppHelper appHelper) {
        this.context = context;
        this.masterTableList = masterTableList;
        this.masterTableListFiltered = masterTableList;
        this.appHelper = appHelper;
        selected_items = new SparseBooleanArray();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.group_members_row_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            if (masterTableListFiltered != null && masterTableListFiltered.size() > 0) {
                MasterTable masterTable = masterTableListFiltered.get(position);

                // displaying text view data
                holder.from.setText(masterTable.getClientName());
                holder.email.setText(masterTable.getClientId());
                holder.message.setText(masterTable.getPhoneNo());
                String currentDate=appHelper.getCurrentDate(DATE_FORMAT_MM_DD_YYYY);
                if( !TextUtils.isEmpty(currentDate)) {
                    holder.date.setText(currentDate);
                }
//                holder.date.setText(masterTable.getCreated_date().toString());
                holder.image_letter.setText(masterTable.getClientName().substring(0, 1));

                holder.lyt_parent.setActivated(selected_items.get(position, false));

                holder.lyt_parent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onClickListener == null) return;
                        onClickListener.onItemClick(v, masterTable, position);
                    }
                });

                holder.lyt_parent.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        if (onClickListener == null) return false;
                        onClickListener.onItemLongClick(v, masterTable, position);
                        return true;
                    }
                });

                toggleCheckedIcon(holder, position);
                displayImage(holder, masterTable);
              
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void displayImage(ViewHolder holder, MasterTable masterTable) {
//        if (masterTable.image != null) {
//            holder.image.setImageResource(masterTable.image);
//            holder.image.setColorFilter(null);
//            holder.image_letter.setVisibility(View.GONE);
//        } else {
            holder.image.setImageResource(R.drawable.shape_circle);
            holder.image.setColorFilter( R.color.red_500);
            holder.image_letter.setVisibility(View.VISIBLE);
//        }
    }

    private void toggleCheckedIcon(ViewHolder holder, int position) {
        if (selected_items.get(position, false)) {
            holder.lyt_image.setVisibility(View.GONE);
            holder.lyt_checked.setVisibility(View.VISIBLE);
            if (current_selected_idx == position) resetCurrentIndex();
        } else {
            holder.lyt_checked.setVisibility(View.GONE);
            holder.lyt_image.setVisibility(View.VISIBLE);
            if (current_selected_idx == position) resetCurrentIndex();
        }
    }

    public MasterTable getItem(int position) {
        return masterTableListFiltered.get(position);
    }
    public void toggleSelection(int pos) {
        current_selected_idx = pos;
        if (selected_items.get(pos, false)) {
            selected_items.delete(pos);
        } else {
            selected_items.put(pos, true);
        }
        notifyItemChanged(pos);
    }

    public void clearSelections() {
        selected_items.clear();
        notifyDataSetChanged();
    }

    public int getSelectedItemCount() {
        return selected_items.size();
    }

    public List<Integer> getSelectedItems() {
        List<Integer> items = new ArrayList<>(selected_items.size());
        for (int i = 0; i < selected_items.size(); i++) {
            items.add(selected_items.keyAt(i));
        }
        return items;
    }

    public List<MasterTable> getSelectedMembers() {
        List<MasterTable> items = new ArrayList<>(selected_items.size());
        for (int i = 0; i < selected_items.size(); i++) {
            items.add(masterTableListFiltered.get(selected_items.keyAt(i)));
        }
        return items;
    }

    public void removeData(int position) {
        masterTableListFiltered.remove(position);
        resetCurrentIndex();
    }

    private void resetCurrentIndex() {
        current_selected_idx = -1;
    }

    @Override
    public int getItemCount() {
        if (masterTableListFiltered != null) {

            return masterTableListFiltered.size();
        } else {
            return 0;
        }
    }

    public void setItem(List<MasterTable> masterTableList) {
        try {
            if (masterTableList != null && masterTableList.size() > 0) {
                this.masterTableList.clear();
                this.masterTableListFiltered.clear();
                this.masterTableList = masterTableList;
                this.masterTableListFiltered = masterTableList;
                selected_items = new SparseBooleanArray();
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void clearItems() {
        try {
            if (masterTableList != null && masterTableList.size() > 0) {
                masterTableList.clear();
                masterTableListFiltered.clear();
                notifyDataSetChanged();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();


                FilterResults filterResults = new FilterResults();
                filterResults.values = masterTableListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                masterTableListFiltered = (List<MasterTable>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface OnClickListener {
        void onItemClick(View view, MasterTable masterTable, int pos);

        void onItemLongClick(View view, MasterTable masterTable, int pos);
    }

}
