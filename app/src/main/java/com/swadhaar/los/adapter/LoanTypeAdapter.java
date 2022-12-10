package com.swadhaar.los.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.swadhaar.los.R;
import com.swadhaar.los.activities.HomeActivity;
import com.swadhaar.los.activities.JLGHomeActivity;
import com.swadhaar.los.activities.ProductActivity;
import com.swadhaar.los.database.entity.MasterTable;
import com.swadhaar.los.models.LoanTypeDto;

import java.util.List;

import static com.swadhaar.los.constants.AppConstant.LOAN_NAME_AHL;
import static com.swadhaar.los.constants.AppConstant.LOAN_NAME_INDIVIDUAL;
import static com.swadhaar.los.constants.AppConstant.LOAN_NAME_JLG;
import static com.swadhaar.los.constants.AppConstant.LOAN_NAME_MSME;
import static com.swadhaar.los.constants.AppConstant.LOAN_NAME_PHL;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_NAME;
import static com.swadhaar.los.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_ROLE_NAME;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_NAME;

public class LoanTypeAdapter extends RecyclerView.Adapter<LoanTypeAdapter.LoanTypeViewHolder> {

    private Context context;
    private List<LoanTypeDto> loanTypeDtoList;
    ProductCallbakInterface productCallbakInterface;

    public LoanTypeAdapter(Context context, List<LoanTypeDto> loanTypeDtoList,
                           ProductCallbakInterface productCallbakInterface) {
        this.context = context;
        this.loanTypeDtoList = loanTypeDtoList;
        this.productCallbakInterface=productCallbakInterface;
    }

    @NonNull
    @Override
    public LoanTypeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_loan_item,viewGroup,false);
        return new LoanTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoanTypeViewHolder loanTypeViewHolder, int i) {
        loanTypeViewHolder.tvLoanType.setText(loanTypeDtoList.get(i).getLoanType());
        loanTypeViewHolder.ivLoanIcon.setImageResource(loanTypeDtoList.get(i).getLoanIcon());

        loanTypeViewHolder.cvLoanType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strLoanType=loanTypeViewHolder.tvLoanType.getText().toString();

                String productId="";
                if(strLoanType.equalsIgnoreCase(LOAN_NAME_INDIVIDUAL))
                {
                    productId="3";
                }
                else if
                (strLoanType.equalsIgnoreCase(LOAN_NAME_JLG))
                {
                    productId="5";
                }
                else if
                (strLoanType.equalsIgnoreCase(LOAN_NAME_AHL))
                {
                    productId="12";
                }
                // TODO: ahl productId is 12
                else if
                (strLoanType.equalsIgnoreCase(LOAN_NAME_PHL)){
                    productId="22";

                }else{
                    productId="1";
                }

                productCallbakInterface.openProductScreenCallback(strLoanType,productId);
                // TODO: New Code [ production release ]
//                    if(strLoanType.equalsIgnoreCase(LOAN_NAME_MSME) || strLoanType.equalsIgnoreCase(LOAN_NAME_AHL)){
//
//                        productCallbakInterface.openProductScreenCallback(strLoanType,productId);
//
//                    }

            }
        });
    }

    @Override
    public int getItemCount() {
        return loanTypeDtoList.size();
    }

    public class LoanTypeViewHolder extends RecyclerView.ViewHolder{
        ImageView ivLoanIcon;
        TextView tvLoanType;
        CardView cvLoanType;
        public LoanTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            ivLoanIcon=(ImageView)itemView.findViewById(R.id.iv_loan_icon);
            tvLoanType=(TextView)itemView.findViewById(R.id.tv_loan_type);
            cvLoanType=(CardView)itemView.findViewById(R.id.cv_loan_type);
        }
    }

    public interface ProductCallbakInterface {
        void openProductScreenCallback(String loanType,String productId);
    }
}
