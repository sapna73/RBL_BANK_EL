package com.saartak.el.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.saartak.el.R;
import com.saartak.el.models.LoanTypeDto;
import com.saartak.el.models.UserLoginMenu.UserLoginMenuTable;

import java.util.ArrayList;
import java.util.List;

import static com.saartak.el.constants.AppConstant.LOAN_NAME_AHL;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_INDIVIDUAL;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_JLG;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_PHL;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_TWL;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_EL;

public class LoanTypeAdapter extends RecyclerView.Adapter<LoanTypeAdapter.LoanTypeViewHolder> {

    private Context context;
    private List<LoanTypeDto> loanTypeDtoList;
    private ArrayList<UserLoginMenuTable> userLoginMenuList;
    ProductCallbakInterface productCallbakInterface;

    public LoanTypeAdapter(Context context, List<LoanTypeDto> loanTypeDtoList,
                           ArrayList<UserLoginMenuTable> userLoginMenuList,ProductCallbakInterface productCallbakInterface) {
        this.context = context;
        this.loanTypeDtoList = loanTypeDtoList;
        this.userLoginMenuList = userLoginMenuList;
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
                String workflowId="";
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
                    productId="25";
                    workflowId="19";

                }else if(strLoanType.equalsIgnoreCase(LOAN_NAME_EL)){
                    productId="26";
                    workflowId="20";

                }else if(strLoanType.equalsIgnoreCase(LOAN_NAME_TWL)){
                    productId="25";
                    workflowId="19";

                }

                //productCallbakInterface.openProductScreenCallback(strLoanType,userLoginMenuList.get(i).getProductId(),userLoginMenuList.get(i).getWorkflowId());
                productCallbakInterface.openProductScreenCallback(strLoanType,productId,workflowId);
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
        void openProductScreenCallback(String loanType,String productId,String workflowId);
    }
}
