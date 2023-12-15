package com.saartak.el.adapter;

import static com.saartak.el.constants.AppConstant.IMAGE_ENC_PSWD;
import static com.saartak.el.constants.AppConstant.IMAGE_ENC_SALT;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.images.FileUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.MediaStoreSignature;
import com.saartak.el.R;
import com.saartak.el.database.entity.DocumentUploadTableNew;
import com.saartak.el.keystore.JealousSky;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class ImageCaptureAdapter extends RecyclerView.Adapter<ImageCaptureAdapter.ImageCaptureViewHolder> {

    List<DocumentUploadTableNew> documentUploadTableNewList;
    Context context;
    ImageCaptureInterface imageCaptureInterface;

    public ImageCaptureAdapter(List<DocumentUploadTableNew> documentUploadTableNewList, Context context, ImageCaptureInterface imageCaptureInterface) {
        this.documentUploadTableNewList = documentUploadTableNewList;
        this.context = context;
        this.imageCaptureInterface = imageCaptureInterface;
    }

    @NonNull
    @Override
    public ImageCaptureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.view_capture_image,parent,false);
        return new ImageCaptureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageCaptureViewHolder holder, int position) {
        try{
            final DocumentUploadTableNew documentUploadTableNew = documentUploadTableNewList.get(position);
            if (documentUploadTableNew != null) {
                int count=position+1;
                holder.tvImageTitle.setText(documentUploadTableNew.getDocument_name() + " " + count);

                if (!TextUtils.isEmpty(documentUploadTableNew.getFile_path())) {

                    holder.ivDefaultImage.setVisibility(View.GONE);

                    if (documentUploadTableNew.isDocument_status()) {
                        holder.ivImageStatus.setVisibility(View.VISIBLE);
                    } else {
                        holder.ivImageStatus.setVisibility(View.GONE);
                    }

                    holder.ivCaptureImage.setVisibility(View.VISIBLE);

                    holder.ivCaptureImage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageCaptureInterface.
                                    openImageCallBack(documentUploadTableNew,position);
                        }
                    });
                    holder.ivRemoveImage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageCaptureInterface.removeImageCallBack(documentUploadTableNew,position);
                        }
                    });

                    RequestOptions requestOptions = new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.DATA)
                            .sizeMultiplier(0.5f)
                            .signature(new MediaStoreSignature("JPEG", System.currentTimeMillis(), 0))
                            .dontAnimate();


                    JealousSky jealousSky = JealousSky.getInstance();

                    jealousSky.initialize(
                            IMAGE_ENC_PSWD,
                            IMAGE_ENC_SALT);

                    try {

                        InputStream inputStream = new FileInputStream(documentUploadTableNew.getFile_path());
                        byte[] decryptedByteArrayImage = jealousSky.decrypt(inputStream);

                        if (decryptedByteArrayImage != null && decryptedByteArrayImage.length > 0) {

                            Glide.with(context)
                                    .asBitmap()
                                    .load(decryptedByteArrayImage)
                                    .apply(requestOptions)
                                    .thumbnail(
                                            Glide.with(context).asBitmap()
                                                    .load(decryptedByteArrayImage) // TODO: Load Decrypted Image
                                                    .apply(requestOptions))
                                    .into(holder.ivCaptureImage);
                        } else {

                            Glide.with(context)
                                    .asBitmap()
                                    .load(documentUploadTableNew.getFile_path())
                                    .apply(requestOptions)
                                    .thumbnail(
                                            Glide.with(context).asBitmap()
                                                    .load(documentUploadTableNew.getFile_path()) // TODO: Load File Path
                                                    .apply(requestOptions))
                                    .into(holder.ivCaptureImage);
                        }
                    }catch (FileNotFoundException ex){
                        ex.printStackTrace();

                        // TODO: If file is not exist then we are removing the file
                        imageCaptureInterface.removeImageCallBack(documentUploadTableNew,position);
                    }

                } else {
                    holder.ivCaptureImage.setImageBitmap(null);
                    holder.ivDefaultImage.setVisibility(View.VISIBLE);
                    holder.ivImageStatus.setVisibility(View.GONE);

                    // TODO: If file path is not exist then we are removing the file
                    imageCaptureInterface.removeImageCallBack(documentUploadTableNew,position);
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return documentUploadTableNewList.size();
    }

    public class ImageCaptureViewHolder extends RecyclerView.ViewHolder{
        ImageView ivCaptureImage;
        ImageView ivDefaultImage;
        ImageView ivRemoveImage;
        ImageView ivImageStatus;
        TextView tvImageTitle;
        public ImageCaptureViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCaptureImage=(ImageView) itemView.findViewById(R.id.iv_capture_image);
            ivDefaultImage = (ImageView) itemView.findViewById(R.id.iv_default_image);
            tvImageTitle = (TextView) itemView.findViewById(R.id.tv_image_title);
            ivRemoveImage = (ImageView) itemView.findViewById(R.id.iv_remove_image);
            ivImageStatus = (ImageView) itemView.findViewById(R.id.iv_image_status);
        }
    }

    public interface ImageCaptureInterface{
        void removeImageCallBack(DocumentUploadTableNew documentUploadTableNew,int position);
        void openImageCallBack(DocumentUploadTableNew documentUploadTableNew,int position);
    }
}
