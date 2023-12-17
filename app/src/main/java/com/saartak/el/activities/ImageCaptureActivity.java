package com.saartak.el.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.images.FileUtils;
import com.bfil.uilibrary.images.ImageCompressionAsyncTask;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.saartak.el.R;
import com.saartak.el.adapter.ImageCaptureAdapter;
import com.saartak.el.database.entity.DocumentUploadTableNew;
import com.saartak.el.keystore.JealousSky;
import com.saartak.el.view_models.DynamicUIViewModel;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

import static android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION;
import static android.content.Intent.FLAG_GRANT_WRITE_URI_PERMISSION;
import static com.bfil.uilibrary.helpers.AppConstants.IMAGE_CAPTURE_REQUEST;
import static com.bfil.uilibrary.helpers.AppConstants.IMAGE_GALLERY_REQUEST;
import static com.saartak.el.constants.AppConstant.APP_FOLDER;
import static com.saartak.el.constants.AppConstant.IMAGE_ENC_PSWD;
import static com.saartak.el.constants.AppConstant.IMAGE_ENC_SALT;
import static com.saartak.el.constants.AppConstant.IMAGE_UPLOAD_FOLDER_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_DOCUMENT_UPLOAD_JSON;
import static com.saartak.el.dynamicui.constants.ParametersConstant.EXTENSION_JPG;

public class ImageCaptureActivity extends AppCompatActivity implements View.OnClickListener, HasSupportFragmentInjector,
        ImageCaptureAdapter.ImageCaptureInterface {

    private static final String TAG =ImageCaptureActivity.class.getCanonicalName() ;
    RecyclerView rvCaptureImage;
    TextView tvHeaderName;
    FloatingActionButton addImageButton;
    Uri fileUri;
    DocumentUploadTableNew documentUploadTableNewHeader;
    DocumentUploadTableNew documentUploadTableNewImage;
    List<DocumentUploadTableNew> documentUploadTableNewListFromDB;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
//                WindowManager.LayoutParams.FLAG_SECURE);

        setContentView(R.layout.activity_image_capture);

        rvCaptureImage =(RecyclerView) findViewById(R.id.rv_image_capture);
        tvHeaderName =(TextView) findViewById(R.id.tv_header_name);
        addImageButton =(FloatingActionButton) findViewById(R.id.fb_add_image);
        addImageButton.setOnClickListener(this::onClick);

        rvCaptureImage.setHasFixedSize(true);
        rvCaptureImage.setLayoutManager(new GridLayoutManager( getApplicationContext(), 2,RecyclerView.VERTICAL, false));

        if(getIntent() !=null) {
            String documentUploadJson = getIntent().getStringExtra(PARAM_DOCUMENT_UPLOAD_JSON);

            if (!TextUtils.isEmpty(documentUploadJson)) {

                documentUploadTableNewHeader=new Gson().fromJson(documentUploadJson,DocumentUploadTableNew.class);
                if(documentUploadTableNewHeader !=null) {
                    tvHeaderName.setText(documentUploadTableNewHeader.getDocument_name());
                    configureDagger();
                    configureViewModel(documentUploadTableNewHeader);

                }
            }
        }
    }

    private void configureDagger(){
        AndroidInjection.inject(this);
    }
    public void configureViewModel(DocumentUploadTableNew documentUploadTableNew) {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DynamicUIViewModel.class);
        getDocumentByDocumentName(documentUploadTableNew);
    }
    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        try{
            switch (v.getId()){
                case R.id.fb_add_image:

                    documentUploadTableNewImage=new DocumentUploadTableNew();
                    documentUploadTableNewImage.setScreen_id(documentUploadTableNewHeader.getScreen_id());
                    documentUploadTableNewImage.setClient_id(documentUploadTableNewHeader.getClient_id());
                    documentUploadTableNewImage.setDocument_name(documentUploadTableNewHeader.getDocument_name());
                    documentUploadTableNewImage.setDocument_full_name(documentUploadTableNewHeader.getDocument_full_name());
                    documentUploadTableNewImage.setDocument_tag(documentUploadTableNewHeader.getDocument_tag());
                    documentUploadTableNewImage.setHeader(false); // TODO: Header should be false for new image capture
                    int fileCount=1;
                    if(documentUploadTableNewListFromDB !=null && documentUploadTableNewListFromDB.size()>0) {
                        fileCount=documentUploadTableNewListFromDB.size() + 1; // TODO: incrementing file count
                    }
                    documentUploadTableNewImage.setFileCount(fileCount);
                    documentUploadTableNewImage.setCustomer_type(documentUploadTableNewHeader.getCustomer_type());
                    documentUploadTableNewImage.setDisplay_name(documentUploadTableNewHeader.getDisplay_name());
                    documentUploadTableNewImage.setFull_display_name(documentUploadTableNewHeader.getFull_display_name());
                    documentUploadTableNewImage.setLoan_type(documentUploadTableNewHeader.getLoan_type());
                    documentUploadTableNewImage.setModule_type(documentUploadTableNewHeader.getModule_type());
                    documentUploadTableNewImage.setFile_format(documentUploadTableNewHeader.getFile_format());
                    documentUploadTableNewImage.setUser_id(documentUploadTableNewHeader.getUser_id());
                    documentUploadTableNewImage.setProduct_id(documentUploadTableNewHeader.getProduct_id());
                    // TODO: Sample file name format {0}_apphoupht_housephoto_front_{1}
                    if( ! TextUtils.isEmpty(documentUploadTableNewHeader.getFile_format())) {
                        String fileName = documentUploadTableNewHeader.getFile_format();
                        fileName = fileName.replace("{0}",documentUploadTableNewHeader.getClient_id());
                        fileName = fileName.replace("{1}",String.valueOf(fileCount));
                        documentUploadTableNewImage.setFile_name(fileName);
                    }

                    sendCameraIntent(documentUploadTableNewImage);
                    break;
            }
        }catch (Exception ex){
            ex.printStackTrace();
            INSERT_LOG("onClick","Exception : "+ex.getMessage());
        }
    }



    /**
     * Taking Photos
     */
    public void sendCameraIntent(DocumentUploadTableNew documentUploadTableNew) {
        Log.e(TAG, "sendCameraIntent -->");
        try {

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.addFlags(FLAG_GRANT_READ_URI_PERMISSION | FLAG_GRANT_WRITE_URI_PERMISSION);
            fileUri = FileUtils.getUri(FileUtils.getOutputFile(this,APP_FOLDER,IMAGE_UPLOAD_FOLDER_NAME,
                    documentUploadTableNew.getClient_id(), documentUploadTableNew.getFile_name(), EXTENSION_JPG));
            Uri uri = FileUtils.getIntentUri(this, fileUri);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(intent, IMAGE_CAPTURE_REQUEST);

        } catch (Exception e) {
            e.printStackTrace();
            INSERT_LOG("sendCameraIntent","Exception : "+e.getMessage());
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {

            Log.i(TAG, "inside ImageHelper Activity onActivityResult requestCode ------>"
                    + requestCode + "::resultCode::" + resultCode);
            if (requestCode == IMAGE_CAPTURE_REQUEST) {
                Log.e(TAG, "inside image_capture_code -->");
                if (resultCode == RESULT_OK) {
                    // TODO: IMAGE COMPRESSION
                    compressImage(fileUri);
                } else if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(this,
                                    "User cancelled image capture", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    INSERT_LOG("onActivityResult","Sorry! Failed to capture image");
                    Toast.makeText(this,
                                    "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                            .show();
                }
            } else if (requestCode == IMAGE_GALLERY_REQUEST) {
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    compressImage(uri);
                } else if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(this,
                                    "User cancelled image selection", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    INSERT_LOG("onActivityResult","Sorry! Failed to select image");
                    Toast.makeText(this,
                                    "Sorry! Failed to select image", Toast.LENGTH_SHORT)
                            .show();
                }
            }
            if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode == RESULT_OK) {
                    Uri resultUri = result.getUri();

                    Log.d(TAG,"Cropped URI ===> "+ resultUri);

                    encryptAndStore(resultUri);

                    getDocumentByDocumentName(documentUploadTableNewHeader);

                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    Exception error = result.getError();
                    Toast.makeText(ImageCaptureActivity.this,"Image Cropping Failed => "+error,Toast.LENGTH_SHORT).show();
                    // TODO: 30-03-2019 need to display error
                    encryptAndStore(result.getUri());
                } else if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(this,
                                    "User cancelled image selection", Toast.LENGTH_SHORT)
                            .show();
                    if( ! TextUtils.isEmpty(filePathFromDB)) {
                        File file = new File(filePathFromDB);
                        Uri uri = FileUtils.getUri(file);
                        encryptAndStore(uri);
                    }else{
                        INSERT_LOG("onActivityResult","File Path tvName db is empty");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            INSERT_LOG("onActivityResult","Exception : "+e.getMessage());
        }
    }

    private void compressImage(Uri resultUri) {
        Log.e(TAG, "inside ImageCapture activity compressImage -->");

        try {

            @SuppressLint("StaticFieldLeak")
            ImageCompressionAsyncTask imageCompression = new ImageCompressionAsyncTask() {
                @Override
                protected void onPostExecute(String filePath) {
                    try {

                        Log.i(TAG, "FILE PATH-----> " + filePath);
                        File file = new File(filePath);
                        String fileName = file.getName();
                        Log.i(TAG, "FILE NAME-----> " + fileName);
                        documentUploadTableNewImage.setFile_path(filePath);
                        documentUploadTableNewImage.setFile_name(fileName);

                        try {
                            // TODO: IMAGE ENCRYPTION
                            JealousSky jealousSky = JealousSky.getInstance();

                            jealousSky.initialize(
                                    IMAGE_ENC_PSWD,
                                    IMAGE_ENC_SALT);

                            File fileInput = new File(filePath);
                            FileInputStream fileInputStream = null;

                            if(file.exists()) {
                                fileInputStream = new FileInputStream(fileInput);
                            }

                            jealousSky.encryptToFile(fileInputStream,filePath);

                        }catch (Exception ex){
                            ex.printStackTrace();
                            INSERT_LOG("compressImage","Exception : "+ex.getMessage());
                        }

                        insertNewCapturedImageInTable(documentUploadTableNewImage);

                    } catch (Exception e) {
                        e.printStackTrace();
                        INSERT_LOG("compressImage","Exception : "+e.getMessage());
                    }
                }
            };
            String path = FileUtils.getPath(this, resultUri);
            imageCompression.execute(path); // imagePath as a string
        } catch (Exception e) {
            e.printStackTrace();
            INSERT_LOG("compressImage","Exception : "+e.getMessage());
        }
    }

    private void encryptAndStore(Uri resultUri){
        try{
            String filePath = FileUtils.getPath(this, resultUri);
            Log.i(TAG, "FILE PATH-----> " + filePath);
            File file = new File(filePath);
            String fileName = file.getName();
            Log.i(TAG, "FILE NAME-----> " + fileName);

            try {
                // TODO: IMAGE ENCRYPTION
//                            encryptFile(documentUploadTableNewImage);

                JealousSky jealousSky = JealousSky.getInstance();

                jealousSky.initialize(
                        IMAGE_ENC_PSWD,
                        IMAGE_ENC_SALT);

                File fileInput = new File(filePath);
                FileInputStream fileInputStream = null;

                if(file.exists()) {
                    fileInputStream = new FileInputStream(fileInput);
                }

                jealousSky.encryptToFile(fileInputStream,filePath);


            }catch (Exception ex){
                ex.printStackTrace();
                INSERT_LOG("encryptAndStore","Exception : "+ex.getMessage());
            }

        }catch (Exception ex){
            ex.printStackTrace();
            INSERT_LOG("encryptAndStore","Exception : "+ex.getMessage());
        }
    }

    private void insertNewCapturedImageInTable(DocumentUploadTableNew documentUploadTableNewImage){
        try{
            viewModel.insertNewCapturedImageInTable(documentUploadTableNewImage);
            if(viewModel.getDocumentUploadLiveDataList() !=null){
                Observer observer=new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        documentUploadTableNewListFromDB=(List<DocumentUploadTableNew>) o;
                        viewModel.getDocumentUploadLiveDataList().removeObserver(this);

                        if(documentUploadTableNewListFromDB !=null && documentUploadTableNewListFromDB.size()>0){

                            ImageCaptureAdapter imageCaptureAdapter=new ImageCaptureAdapter(documentUploadTableNewListFromDB,
                                    ImageCaptureActivity.this,
                                    ImageCaptureActivity.this);

                            rvCaptureImage.setAdapter(imageCaptureAdapter);
                            imageCaptureAdapter.notifyDataSetChanged();
                        }
                    }
                };
                viewModel.getDocumentUploadLiveDataList().observe(this,observer);
            }

        }catch (Exception ex){
            ex.printStackTrace();
            INSERT_LOG("insertNewCapturedImageInTable","Exception : "+ex.getMessage());
        }
    }
    private void removeCapturedImageFromTable(DocumentUploadTableNew documentUploadTableNewImage){
        try{
            viewModel.removeCapturedImageFromTable(documentUploadTableNewImage);
            if(viewModel.getDocumentUploadLiveDataList() !=null){
                Observer observer=new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        documentUploadTableNewListFromDB=(List<DocumentUploadTableNew>) o;
                        viewModel.getDocumentUploadLiveDataList().removeObserver(this);

                        if(documentUploadTableNewListFromDB !=null && documentUploadTableNewListFromDB.size()>0){

                            ImageCaptureAdapter imageCaptureAdapter=new ImageCaptureAdapter(documentUploadTableNewListFromDB,
                                    ImageCaptureActivity.this,
                                    ImageCaptureActivity.this);

                            rvCaptureImage.setAdapter(imageCaptureAdapter);
                            imageCaptureAdapter.notifyDataSetChanged();
                        }
                    }
                };
                viewModel.getDocumentUploadLiveDataList().observe(this,observer);
            }

        }catch (Exception ex){
            ex.printStackTrace();
            INSERT_LOG("removeCapturedImageFromTable","Exception : "+ex.getMessage());
        }
    }

    private void getDocumentByDocumentName(DocumentUploadTableNew documentUploadTableNewImage){
        try{
            viewModel.getDocumentByDocumentName(documentUploadTableNewImage);
            if(viewModel.getDocumentUploadLiveDataList() !=null){
                Observer observer=new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        documentUploadTableNewListFromDB=(List<DocumentUploadTableNew>) o;
                        viewModel.getDocumentUploadLiveDataList().removeObserver(this);

                        if(documentUploadTableNewListFromDB !=null && documentUploadTableNewListFromDB.size()>0){

                            ImageCaptureAdapter imageCaptureAdapter=new ImageCaptureAdapter(documentUploadTableNewListFromDB,
                                    ImageCaptureActivity.this,
                                    ImageCaptureActivity.this);

                            rvCaptureImage.setAdapter(imageCaptureAdapter);
                            imageCaptureAdapter.notifyDataSetChanged();
                        }
                    }
                };
                viewModel.getDocumentUploadLiveDataList().observe(this,observer);
            }

        }catch (Exception ex){
            ex.printStackTrace();
            INSERT_LOG("getDocumentByDocumentName","Exception : "+ex.getMessage());
        }
    }


    @Override
    public void removeImageCallBack(DocumentUploadTableNew documentUploadTableNew, int position) {
        try{
            removeCapturedImageFromTable(documentUploadTableNew);
        }catch (Exception ex){
            ex.printStackTrace();
            INSERT_LOG("removeImageCallBack","Exception : "+ex.getMessage());
        }
    }

    String filePathFromDB;
    @Override
    public void openImageCallBack(DocumentUploadTableNew documentUploadTableNew, int position) {
        try{

            if(documentUploadTableNew !=null && ! TextUtils.isEmpty(documentUploadTableNew.getFile_path())) {
                filePathFromDB=documentUploadTableNew.getFile_path();
                JealousSky jealousSky = JealousSky.getInstance();

                jealousSky.initialize(
                        IMAGE_ENC_PSWD,
                        IMAGE_ENC_SALT);

                InputStream inputStream=new FileInputStream(documentUploadTableNew.getFile_path());
                jealousSky.decryptToFile(inputStream,documentUploadTableNew.getFile_path());


                File file = new File(documentUploadTableNew.getFile_path());
                Uri uri = Uri.fromFile(file);
                CropImage.activity(uri)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setMultiTouchEnabled(true)
                        .setOutputUri(uri)
                        .start(this);
            }
        }catch (Exception ex){
            ex.printStackTrace();
            INSERT_LOG("openImageCallBack","Exception : "+ex.getMessage());
        }
    }

    private void INSERT_LOG(String methodName,String message){
        try{
            if(viewModel !=null){
                if(documentUploadTableNewHeader !=null)
                    viewModel.insertLog(methodName,message,documentUploadTableNewHeader.getUser_id(),documentUploadTableNewHeader.getScreen_id()
                            ,documentUploadTableNewHeader.getFull_display_name()
                            ,documentUploadTableNewHeader.getClient_id(),documentUploadTableNewHeader.getLoan_type(),documentUploadTableNewHeader.getModule_type());
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
