package com.swadhaar.los.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.swadhaar.los.R;
import com.swadhaar.los.activities.BaseActivity;
import com.swadhaar.los.models.BalancesheetDTO;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import static com.swadhaar.los.constants.AppConstant.PARAM_LOAN_TYPE;

public class BalancesheetAdapter extends RecyclerView.Adapter<BalancesheetAdapter.LoanTypeViewHolder> {

    private Context context;
    private List<BalancesheetDTO> balancesheetDTOList;
    private String strLoanType;

    public BalancesheetAdapter(Context context, List<BalancesheetDTO> BalancesheetDTOList, String loanType) {
        this.context = context;
        this.balancesheetDTOList = BalancesheetDTOList;
        this.strLoanType=loanType;
    }

    @NonNull
    @Override
    public LoanTypeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_balance_sheet,viewGroup,false);
        return new LoanTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoanTypeViewHolder loanTypeViewHolder, int i) {
            loanTypeViewHolder.tvButtonName.setText(balancesheetDTOList.get(i).getButtonName());
            loanTypeViewHolder.tvButtonName.setBackgroundColor(balancesheetDTOList.get(i).getColorLight());
            loanTypeViewHolder.ivButtonIcon.setImageResource(balancesheetDTOList.get(i).getButtonIcon());
            loanTypeViewHolder.llBalanceSheet.setBackgroundColor(balancesheetDTOList.get(i).getColorDark());

            loanTypeViewHolder.cvBalancesheet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        Intent intent = new Intent(context, BaseActivity.class);
                        intent.putExtra(PARAM_LOAN_TYPE, strLoanType);
                        context.startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return balancesheetDTOList.size();
    }

    public class LoanTypeViewHolder extends RecyclerView.ViewHolder{
         ImageView ivButtonIcon;
         TextView tvButtonName;
         CardView cvBalancesheet;
         LinearLayout llBalanceSheet;
        public LoanTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            ivButtonIcon=(ImageView)itemView.findViewById(R.id.iv_loan_icon);
            tvButtonName=(TextView)itemView.findViewById(R.id.tv_loan_type);
            cvBalancesheet=(CardView)itemView.findViewById(R.id.cv_loan_type);
            llBalanceSheet=(LinearLayout) itemView.findViewById(R.id.ll_bal_sheet);
        }
    }
}
