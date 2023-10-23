package com.saartak.el.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saartak.el.R;
import com.saartak.el.database.entity.DocumentUploadTableNew;

import java.util.List;

public class DocumentUploadSubHeaderAdapter extends RecyclerView.Adapter<DocumentUploadSubHeaderAdapter.DocumentUploadHeaderViewHolder> {

    private Context context;
    private List<DocumentUploadTableNew> documentUploadTableNewList;
    DocumentUploadSubHeaderInterface documentUploadSubHeaderInterface;

    public DocumentUploadSubHeaderAdapter(Context context, List<DocumentUploadTableNew> documentUploadTableNewList,DocumentUploadSubHeaderInterface documentUploadSubHeaderInterface) {
        this.context = context;
        this.documentUploadTableNewList=documentUploadTableNewList;
        this.documentUploadSubHeaderInterface=documentUploadSubHeaderInterface;
    }

    @NonNull
    @Override
    public DocumentUploadHeaderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_document_upload_sub_header,viewGroup,false);
        return new DocumentUploadHeaderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DocumentUploadHeaderViewHolder holder, int i) {
        try{
            if(documentUploadTableNewList.size()>0) {
                DocumentUploadTableNew documentUploadTableSubHeader = documentUploadTableNewList.get(i);
                    holder.tvSubHeaderName.setText(documentUploadTableSubHeader.getDocument_name());
                    if(documentUploadTableSubHeader.isDocument_status()) {
                        holder.ivUploadStatus.setImageResource(R.drawable.ic_check_circle_black_24dp);
                    }


                holder.tvSubHeaderName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        documentUploadSubHeaderInterface.openImageCaptureCallBack(documentUploadTableSubHeader,i);
                    }
                });
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return documentUploadTableNewList.size();
    }

    public class DocumentUploadHeaderViewHolder extends RecyclerView.ViewHolder{
         TextView tvSubHeaderName;
         ImageView ivUploadStatus;
        public DocumentUploadHeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSubHeaderName=(TextView)itemView.findViewById(R.id.tv_sub_header_name);
            ivUploadStatus=(ImageView) itemView.findViewById(R.id.iv_image_upload_status);
        }
    }

    public interface DocumentUploadSubHeaderInterface{
         void openImageCaptureCallBack(DocumentUploadTableNew documentUploadTableNew,int position);
    }
}
