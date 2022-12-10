package com.swadhaar.los.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.bfil.uilibrary.images.FileUtils;
import com.bfil.uilibrary.images.ImageCompressionAsyncTask;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.MediaStoreSignature;
import com.google.android.material.navigation.NavigationView;
import com.swadhaar.los.BuildConfig;
import com.swadhaar.los.R;
import com.swadhaar.los.adapter.CenterMeetingAttendanceAdapter;
import com.swadhaar.los.database.entity.CMPhotoTable;
import com.swadhaar.los.database.entity.CenterCreationTable;
import com.swadhaar.los.database.entity.CenterMeetingAttendanceTable;
import com.swadhaar.los.database.entity.RawDataTable;
import com.swadhaar.los.keystore.JealousSky;
import com.swadhaar.los.models.CenterMeetingAttendanceDTO;
import com.swadhaar.los.view_models.DynamicUIViewModel;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION;
import static android.content.Intent.FLAG_GRANT_WRITE_URI_PERMISSION;
import static com.bfil.uilibrary.helpers.AppConstants.IMAGE_CAPTURE_REQUEST;
import static com.swadhaar.los.constants.AppConstant.APP_FOLDER;
import static com.swadhaar.los.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.swadhaar.los.constants.AppConstant.IMAGE_ENC_PSWD;
import static com.swadhaar.los.constants.AppConstant.IMAGE_ENC_SALT;
import static com.swadhaar.los.constants.AppConstant.IMAGE_UPLOAD_FOLDER_NAME;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_CENTER_NAME;
import static com.swadhaar.los.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_NAME;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_ATTENDANCE_SUCCESS;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_CAPTURE_CENTER_MEETING_PHOTO;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_DO_YOU_WANT_TO_PROCEED;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_SELECT_ABSENT_REASON;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.EXTENSION_JPG;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.MESSAGE_SUCCESS_RESPONSE;

public class CenterMeetingAttendanceActivity extends LOSBaseActivity {

    private static final String TAG = CenterMeetingAttendanceActivity.class.getCanonicalName();
    RecyclerView rvLeadDetails;
    CenterMeetingAttendanceAdapter centerMeetingAttendanceAdapter;

    private String CENTER_NAME;

    private Toolbar toolbar;

    private ActionMode actionMode;

    String SCREEN_NO;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    CoordinatorLayout coordinatorLayout;
    ImageView ivStaffImage;
    RelativeLayout rlNoLeads;
    TextView tvCenterName;
    TextView tvAppVersion,tvCurrentDate,tvUserName;
    Button btnProceed;
    CenterCreationTable CENTER_CREATION_TABLE;
    List<RawDataTable> allClientRawDataTableList;

    private ImageView ivHousePhoto, ivDefaultImage, ivRemoveImage;
    Uri fileUri;
    CMPhotoTable cmCaptionPhotoTable;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_center_meeting_attendance);

        tvUserName = (TextView)findViewById(R.id.tv_user_name);
        tvCurrentDate = (TextView)findViewById(R.id.tv_currentdate);
        tvAppVersion = (TextView)findViewById(R.id.tv_app_version);

        rvLeadDetails = (RecyclerView) findViewById(R.id.rv_lead_details);
        rlNoLeads = (RelativeLayout) findViewById(R.id.rl_no_leads);
        toolbar = (Toolbar)findViewById(R.id.toolbar_eligibility);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Insert Into Staff Activity Table
                insertStaffActivity(viewModel,CENTER_NAME,userId,"ATTENDANCE",1);
                // back button pressed
                finish();
            }
        });

        tvCenterName=(TextView)findViewById(R.id.tv_center_name_value);
        btnProceed=(Button)findViewById(R.id.btn_proceed);

        ivHousePhoto = (ImageView) findViewById(R.id.iv_capture_image);
        ivDefaultImage = (ImageView) findViewById(R.id.iv_default_image);
        ivRemoveImage = (ImageView) findViewById(R.id.iv_remove_image);

        userName = getIntent().getStringExtra(PARAM_USER_NAME);
        userId = getIntent().getStringExtra(PARAM_USER_ID);
        branchId = getIntent().getStringExtra(PARAM_BRANCH_ID);
        branchGSTcode = getIntent().getStringExtra(PARAM_BRANCH_GST_CODE);
        loanType = getIntent().getStringExtra(PARAM_LOAN_TYPE);
        productId = getIntent().getStringExtra(PARAM_PRODUCT_ID);
        CENTER_NAME = getIntent().getStringExtra(PARAM_CENTER_NAME);

        if(!TextUtils.isEmpty(CENTER_NAME)){
            tvCenterName.setText(CENTER_NAME);
        }
        if(!TextUtils.isEmpty(userName)){
            tvUserName.setText(userName);
        }

        String appVersion = BuildConfig.VERSION_NAME;
        if (!TextUtils.isEmpty(appVersion)) {
            tvAppVersion.setText(getResources().getString(R.string.version)+" "+appVersion);
        }

        String currentDate = appHelper.getCurrentDate(DATE_FORMAT_DD_MM_YYYY2);
        if (!TextUtils.isEmpty(currentDate)) {
            tvCurrentDate.setText(currentDate);
        }


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvLeadDetails.setLayoutManager(mLayoutManager);
        rvLeadDetails.setItemAnimator(new DefaultItemAnimator());
        rvLeadDetails.setNestedScrollingEnabled(false);

        centerMeetingAttendanceAdapter = new CenterMeetingAttendanceAdapter(CenterMeetingAttendanceActivity.this, new ArrayList<>(),appHelper);
        rvLeadDetails.setAdapter(centerMeetingAttendanceAdapter);

        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cmCaptionPhotoTable != null && !TextUtils.isEmpty(cmCaptionPhotoTable.getFile_path())) {
                    if(centerMeetingAttendanceAdapter !=null && centerMeetingAttendanceAdapter.getCenterMeetingAttendanceDTOList() !=null
                            && centerMeetingAttendanceAdapter.getCenterMeetingAttendanceDTOList().size()>0){
                        boolean isValid=false;
                        parentLoop:
                        for(CenterMeetingAttendanceDTO centerMeetingAttendanceDTO : centerMeetingAttendanceAdapter.getCenterMeetingAttendanceDTOList()){
                            if (centerMeetingAttendanceDTO.getCenterMeetingAttendanceTableList()!=null && centerMeetingAttendanceDTO.getCenterMeetingAttendanceTableList().size()>0){
                                for (CenterMeetingAttendanceTable centerMeetingAttendanceTable: centerMeetingAttendanceDTO.getCenterMeetingAttendanceTableList()){
                                    if (centerMeetingAttendanceTable.isAttentance()){
                                        isValid=true;
                                    }else if(!TextUtils.isEmpty(centerMeetingAttendanceTable.getReason())){
                                        isValid=true;
                                    }else{
                                        isValid=false;
                                        break parentLoop;
                                    }
                                }
                            }
                        }

                        if(isValid) {

                            appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons(ERROR_MESSAGE_DO_YOU_WANT_TO_PROCEED, new ConfirmationDialog.ActionCallback() {
                                @Override
                                public void onAction() {
                                    saveCenterMeetingAttendance(centerMeetingAttendanceAdapter.getCenterMeetingAttendanceDTOList());
                                }
                            });
                        }else{
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,ERROR_MESSAGE_SELECT_ABSENT_REASON);
                        }

                    }
                } else {
                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,ERROR_MESSAGE_CAPTURE_CENTER_MEETING_PHOTO);
                }
            }
        });

        ivHousePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cmCaptionPhotoTable != null && !TextUtils.isEmpty(cmCaptionPhotoTable.getFile_path())) {
                    openImage(cmCaptionPhotoTable);
                } else {
                    cmCaptionPhotoTable=new CMPhotoTable();
                    cmCaptionPhotoTable.setCenterName(CENTER_NAME);
                    cmCaptionPhotoTable.setFile_name(cmCaptionPhotoTable.getCenterId() + "_attendance");
                    sendCameraIntent(cmCaptionPhotoTable);
                }
            }
        });

        ivRemoveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cmCaptionPhotoTable != null) {
                    removeImage(cmCaptionPhotoTable);
                }
            }
        });

        configureDagger();
        configureViewModel();


        // TODO: Insert Into Staff Activity Table
        insertStaffActivity(viewModel,CENTER_NAME,userId,"ATTENDANCE",0);

    }

    private void saveCenterMeetingAttendance(List<CenterMeetingAttendanceDTO> centerMeetingAttendanceDTOList) {
        try{
            viewModel.updateCenterMeetingAttendance(centerMeetingAttendanceDTOList,cmCaptionPhotoTable);
            if (viewModel.getCenterMeetingAttendanceDTOLiveDataList() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        viewModel.getCenterMeetingAttendanceDTOLiveDataList().removeObserver(this);

                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS,
                                ERROR_MESSAGE_ATTENDANCE_SUCCESS, new ConfirmationDialog.ActionCallback() {
                                    @Override
                                    public void onAction() {

                                        // TODO: Insert Into Staff Activity Table
                                        insertStaffActivity(viewModel,CENTER_NAME,userId,"ATTENDANCE",1);

                                        if(fileUri!=null && !TextUtils.isEmpty(fileUri.toString()))
                                        {
//                File file=new File(fileUri.toString());
                                            File file=FileUtils.getFile(CenterMeetingAttendanceActivity.this,fileUri);
                                            if( file.exists()) {
                                                uploadCMCaptionImageToServer(file,cmCaptionPhotoTable,userId);
                                            }else{
                                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                                        "Please Capture Image");
                                            }
                                        }else{
                                            if( !TextUtils.isEmpty(cmCaptionPhotoTable.getFile_path())) {
                                                File file = new File(cmCaptionPhotoTable.getFile_path());
                                                if( file.exists()) {
                                                    uploadCMCaptionImageToServer(file,cmCaptionPhotoTable,userId);
                                                }else{
                                                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                                            "Please Capture Image");
                                                }
                                            }
                                        }


                                    }
                                });
                    }
                };
                viewModel.getCenterMeetingAttendanceDTOLiveDataList().observe(this, observer);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void uploadCMCaptionImageToServer(File file, CMPhotoTable cmCaptionPhotoTable, String staffId) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            viewModel.uploadCMCaptionImageToServer(file, cmCaptionPhotoTable.getFile_name(), staffId);

            if (viewModel.getStringLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {

                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        String response = (String) o;
                        viewModel.getStringLiveData().removeObserver(this);

                        if (!TextUtils.isEmpty(response)) {
                            if (response.equalsIgnoreCase(MESSAGE_SUCCESS_RESPONSE)) {
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS, "Photo Uploaded Successfully",
                                        new ConfirmationDialog.ActionCallback() {
                                            @Override
                                            public void onAction() {
                                                finish();
                                                // btnUpload.setVisibility(View.GONE);
                                            }
                                        });
                            } else {
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR,
                                        response);
                            }

                        }
                    }
                };
                viewModel.getStringLiveData().observe(this, observer);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
        }
    }


    private void configureDagger() {
        AndroidInjection.inject(this);
    }

    public void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DynamicUIViewModel.class);

        // TODO: Getting member list tvName center meeting table
        if(! TextUtils.isEmpty(CENTER_NAME)) {
            getMembersFromCenterMeetingTableForAttendance(userId,CENTER_NAME);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {

            userName = getIntent().getStringExtra(PARAM_USER_NAME);
            userId = getIntent().getStringExtra(PARAM_USER_ID);
            branchId = getIntent().getStringExtra(PARAM_BRANCH_ID);
            branchGSTcode = getIntent().getStringExtra(PARAM_BRANCH_GST_CODE);
            loanType = getIntent().getStringExtra(PARAM_LOAN_TYPE);
            productId = getIntent().getStringExtra(PARAM_PRODUCT_ID);
            CENTER_NAME = getIntent().getStringExtra(PARAM_CENTER_NAME);

//            // TODO: Getting member list tvName center meeting table
//            if(! TextUtils.isEmpty(CENTER_NAME)) {
//                getMembersFromCenterMeetingTableForAttendance(userId,CENTER_NAME);
//            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void onBackPressed() {

        // TODO: Insert Into Staff Activity Table
        insertStaffActivity(viewModel,CENTER_NAME,userId,"ATTENDANCE",1);

        finish();

    }

    // TODO: GETTING CENTER MEETING ATTENDANCE LIST
    public void getMembersFromCenterMeetingTableForAttendance(String userId,String centerName) {
        appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

        try {
            viewModel.getMembersFromCenterMeetingTableForAttendance(userId,centerName);
            if (viewModel.getCenterMeetingAttendanceDTOLiveDataList() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        List<CenterMeetingAttendanceDTO> centerMeetingAttendanceDTOList = (List<CenterMeetingAttendanceDTO>) o;
                        viewModel.getCenterMeetingAttendanceDTOLiveDataList().removeObserver(this);

                        if(centerMeetingAttendanceDTOList !=null && centerMeetingAttendanceDTOList.size()>0){
                            rlNoLeads.setVisibility(View.GONE);
                            rvLeadDetails.setVisibility(View.VISIBLE);

                            if (centerMeetingAttendanceAdapter != null) {
                                centerMeetingAttendanceAdapter.setItem(centerMeetingAttendanceDTOList);
                            }

                            // TODO: show caption image
                            // TODO: GETTING CENTER MEETING CAPTION PHOTO
                            getPhotoFromCMCaptionPhotoTable(userId,centerName);

                        }else{
                            rvLeadDetails.setVisibility(View.GONE);
                            rlNoLeads.setVisibility(View.VISIBLE);
                        }


                    }
                };
                viewModel.getCenterMeetingAttendanceDTOLiveDataList().observe(this, observer);
            }
        } catch (Exception ex) {
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            ex.printStackTrace();
        }
    }

    // TODO: GETTING CENTER MEETING CAPTION PHOTO
    public void getPhotoFromCMCaptionPhotoTable(String userId,String centerName) {
        appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

        try {
            viewModel.getPhotoFromCMCaptionPhotoTable(userId,centerName);
            if (viewModel.getCMCaptionPhotoTableLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        CMPhotoTable cmCaptionPhotoTableDB = (CMPhotoTable) o;
                        viewModel.getCMCaptionPhotoTableLiveData().removeObserver(this);

                        if(cmCaptionPhotoTableDB !=null){
                            if(cmCaptionPhotoTableDB !=null) {
                                cmCaptionPhotoTable = cmCaptionPhotoTableDB;
                            }
                            // TODO: show caption image
                            if (!TextUtils.isEmpty(cmCaptionPhotoTable.getFile_path())) {
                                showImage(cmCaptionPhotoTable);
                            } else {
                                ivHousePhoto.setImageBitmap(null);
                                ivDefaultImage.setVisibility(View.VISIBLE);
                            }
                        }


                    }
                };
                viewModel.getCMCaptionPhotoTableLiveData().observe(this, observer);
            }
        } catch (Exception ex) {
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            ex.printStackTrace();
        }
    }

    private void showImage(CMPhotoTable cmCaptionPhotoTable) {
        try {
            if (cmCaptionPhotoTable != null) {

                if (!TextUtils.isEmpty(cmCaptionPhotoTable.getFile_path())) {

                    ivDefaultImage.setVisibility(View.GONE);
                    ivHousePhoto.setVisibility(View.VISIBLE);

                    RequestOptions requestOptions = new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.DATA)
                            .sizeMultiplier(0.5f)
                            .signature(new MediaStoreSignature("JPEG", System.currentTimeMillis(), 0))
                            .dontAnimate();


                    JealousSky jealousSky = JealousSky.getInstance();

                    jealousSky.initialize(
                            IMAGE_ENC_PSWD,
                            IMAGE_ENC_SALT);

                    InputStream inputStream = new FileInputStream(cmCaptionPhotoTable.getFile_path());
                    byte[] decryptedByteArrayImage = jealousSky.decrypt(inputStream);

                    if (decryptedByteArrayImage != null && decryptedByteArrayImage.length > 0) {

                        Glide.with(this)
                                .asBitmap()
                                .load(decryptedByteArrayImage)
                                .apply(requestOptions)
                                .thumbnail(
                                        Glide.with(this).asBitmap()
                                                .load(decryptedByteArrayImage) // TODO: Load Decrypted Image
                                                .apply(requestOptions))
                                .into(this.ivHousePhoto);
                    } else {

                        Glide.with(this)
                                .asBitmap()
                                .load(cmCaptionPhotoTable.getFile_path())
                                .apply(requestOptions)
                                .thumbnail(
                                        Glide.with(this).asBitmap()
                                                .load(cmCaptionPhotoTable.getFile_path()) // TODO: Load File Path
                                                .apply(requestOptions))
                                .into(ivHousePhoto);
                    }

                } else {
                    ivHousePhoto.setImageBitmap(null);
                    ivDefaultImage.setVisibility(View.VISIBLE);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            ivHousePhoto.setImageBitmap(null);
            ivDefaultImage.setVisibility(View.VISIBLE);
        }
    }

    private void removeImage(CMPhotoTable cmCaptionPhotoTable) {

        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            try {
                viewModel.removeCaptionPhoto(cmCaptionPhotoTable);
                if (viewModel.getCMCaptionPhotoTableLiveData() != null) {
                    Observer getCenterCreationTableObserver = new Observer() {
                        @Override
                        public void onChanged(@Nullable Object o) {
                            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                            CMPhotoTable cmCaptionPhotoTableFromDB = (CMPhotoTable) o;
                            viewModel.getCMCaptionPhotoTableLiveData().removeObserver(this);
                            if (cmCaptionPhotoTableFromDB != null ) {
                                if (!TextUtils.isEmpty(cmCaptionPhotoTableFromDB.getFile_path())) {
                                    showImage(cmCaptionPhotoTableFromDB);
                                } else {
                                    ivHousePhoto.setImageBitmap(null);
                                    ivDefaultImage.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    };
                    viewModel.getCMCaptionPhotoTableLiveData().observe(this, getCenterCreationTableObserver);
                }
            } catch (Exception ex) {
                appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                ex.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void openImage(CMPhotoTable cmCaptionPhotoTable) {
        try {

            if (cmCaptionPhotoTable != null && !TextUtils.isEmpty(cmCaptionPhotoTable.getFile_path())) {
                JealousSky jealousSky = JealousSky.getInstance();

                jealousSky.initialize(
                        IMAGE_ENC_PSWD,
                        IMAGE_ENC_SALT);

                InputStream inputStream = new FileInputStream(cmCaptionPhotoTable.getFile_path());
                jealousSky.decryptToFile(inputStream, cmCaptionPhotoTable.getFile_path());


                File file = new File(cmCaptionPhotoTable.getFile_path());
                Uri uri = Uri.fromFile(file);
                CropImage.activity(uri)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setMultiTouchEnabled(true)
                        .setOutputUri(uri)
                        .start(this);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void sendCameraIntent(CMPhotoTable cmCaptionPhotoTable) {
        try {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.addFlags(FLAG_GRANT_READ_URI_PERMISSION | FLAG_GRANT_WRITE_URI_PERMISSION);
            fileUri = FileUtils.getUri(FileUtils.getOutputFile(this, APP_FOLDER, IMAGE_UPLOAD_FOLDER_NAME,
                    cmCaptionPhotoTable.getCenterName(), cmCaptionPhotoTable.getFile_name(), EXTENSION_JPG));
            Uri uri = FileUtils.getIntentUri(this, fileUri);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(intent, IMAGE_CAPTURE_REQUEST);

        } catch (Exception e) {
            e.printStackTrace();
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
                    Toast.makeText(this,
                            "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                            .show();
                }
            }else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode == RESULT_OK) {
                    Uri resultUri = result.getUri();

                    Log.d(TAG,"Cropped URI ===> "+ resultUri);
                    compressImage(resultUri);


                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    Exception error = result.getError();
                    Toast.makeText(CenterMeetingAttendanceActivity.this,"Image Cropping Failed => "+error,Toast.LENGTH_SHORT).show();
                    // TODO: 30-03-2019 need to display error

                } else if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(this,
                            "User cancelled image selection", Toast.LENGTH_SHORT)
                            .show();

                    if( ! TextUtils.isEmpty(cmCaptionPhotoTable.getFile_path())) {
                        File file = new File(cmCaptionPhotoTable.getFile_path());
                        Uri uri = FileUtils.getUri(file);
                        compressImage(uri);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
                        cmCaptionPhotoTable.setFile_path(filePath);
                        cmCaptionPhotoTable.setFile_name(fileName);

                        try {
                            // TODO: IMAGE ENCRYPTION
                            JealousSky jealousSky = JealousSky.getInstance();

                            jealousSky.initialize(
                                    IMAGE_ENC_PSWD,
                                    IMAGE_ENC_SALT);

                            File fileInput = new File(filePath);
                            FileInputStream fileInputStream = null;

                            if (file.exists()) {
                                fileInputStream = new FileInputStream(fileInput);
                            }

                            jealousSky.encryptToFile(fileInputStream, filePath);

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                        showImage(cmCaptionPhotoTable);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            String path = FileUtils.getPath(this, resultUri);
            imageCompression.execute(path); // imagePath as a string
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
