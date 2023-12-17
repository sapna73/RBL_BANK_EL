package com.saartak.el.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saartak.el.R;
import com.saartak.el.database.entity.DocumentUploadTableNew;

import java.util.ArrayList;
import java.util.List;

import static com.precision.csp.Uidaiauthentication.NPCIConstantValues.TAG;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_EL;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_JLG;

public class DocumentUploadHeaderAdapter extends RecyclerView.Adapter<DocumentUploadHeaderAdapter.DocumentUploadHeaderViewHolder> {

    private Context context;
    private List<DocumentUploadTableNew> documentUploadTableNewList;
    List<String> headerList;
    DocumentUploadSubHeaderAdapter.DocumentUploadSubHeaderInterface documentUploadSubHeaderInterface;

    public DocumentUploadHeaderAdapter(Context context, List<DocumentUploadTableNew> documentUploadTableNewList, List<String> headerList,
                                       DocumentUploadSubHeaderAdapter.DocumentUploadSubHeaderInterface documentUploadSubHeaderInterface) {
        this.context = context;
        this.documentUploadTableNewList = documentUploadTableNewList;
        this.headerList = headerList;
        this.documentUploadSubHeaderInterface = documentUploadSubHeaderInterface;
    }

    @NonNull
    @Override
    public DocumentUploadHeaderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_document_upload_header, viewGroup,false);
        return new DocumentUploadHeaderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DocumentUploadHeaderViewHolder holder, int i) {
        try{

            String headerName = headerList.get(i);
            holder.tvHeaderName.setText(headerName);
            Log.d(TAG, "get the header name, " + headerName);
            if(documentUploadTableNewList != null && documentUploadTableNewList.size()>0){
                List<DocumentUploadTableNew> documentUploadTableSubHeaderList = new ArrayList<>();
                for(DocumentUploadTableNew documentUploadTableNew: documentUploadTableNewList){

                    // TODO: For JLG Loan display all documents under applicant
                    if( !TextUtils.isEmpty(documentUploadTableNew.getLoan_type()) &&
                             documentUploadTableNew.getLoan_type().equalsIgnoreCase(LOAN_NAME_EL)){
                        documentUploadTableSubHeaderList.add(documentUploadTableNew);
                    }
                    else if( documentUploadTableNew.getModule_type().equalsIgnoreCase(headerName)){
                        documentUploadTableSubHeaderList.add(documentUploadTableNew);
                    }
                }

                if( documentUploadTableSubHeaderList.size() > 0){
                    // TODO: SUB HEADER ADAPTER ( NESTED RECYCLER VIEW ADAPTER )
                    DocumentUploadSubHeaderAdapter documentUploadSubHeaderAdapter = new DocumentUploadSubHeaderAdapter(context,
                            documentUploadTableSubHeaderList, documentUploadSubHeaderInterface);

                    holder.rvSubHeader.setAdapter(documentUploadSubHeaderAdapter);
                    documentUploadSubHeaderAdapter.notifyDataSetChanged();
                }
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return headerList.size();
    }


    public class DocumentUploadHeaderViewHolder extends RecyclerView.ViewHolder{
         TextView tvHeaderName;
        RecyclerView rvSubHeader;
        public DocumentUploadHeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHeaderName = (TextView)itemView.findViewById(R.id.tv_header_name);
            rvSubHeader = (RecyclerView) itemView.findViewById(R.id.doc_upload_sub_header);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
            rvSubHeader.setLayoutManager(layoutManager);
        }
    }
}
