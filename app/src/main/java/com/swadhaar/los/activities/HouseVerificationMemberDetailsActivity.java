package com.swadhaar.los.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.bfil.ekyclibrary.activities.EKYCActivity;
import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.bfil.uilibrary.images.FileUtils;
import com.bfil.uilibrary.images.ImageCompressionAsyncTask;
import com.bfil.uilibrary.widgets.textInputLayout.CustomTextInputLayout;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.MediaStoreSignature;
import com.swadhaar.los.R;
import com.swadhaar.los.adapter.HouseVerificationMemberDetailsSummaryAdapter;
import com.swadhaar.los.database.entity.DocumentUploadTableNew;
import com.swadhaar.los.database.entity.HouseVerificationTable;
import com.swadhaar.los.keystore.JealousSky;
import com.swadhaar.los.models.ChangePasswordRequestDTO;
import com.swadhaar.los.models.ChangePasswordResponseDTO;
import com.swadhaar.los.view_models.DynamicUIViewModel;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

import static android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION;
import static android.content.Intent.FLAG_GRANT_WRITE_URI_PERMISSION;
import static com.bfil.uilibrary.helpers.AppConstants.IMAGE_CAPTURE_REQUEST;
import static com.bfil.uilibrary.helpers.AppConstants.IMAGE_GALLERY_REQUEST;
import static com.bfil.uilibrary.helpers.AppConstants.REGEX_PATTERN_PASSWORD;
import static com.swadhaar.los.constants.AppConstant.APP_FOLDER;
import static com.swadhaar.los.constants.AppConstant.IMAGE_ENC_PSWD;
import static com.swadhaar.los.constants.AppConstant.IMAGE_ENC_SALT;
import static com.swadhaar.los.constants.AppConstant.IMAGE_UPLOAD_FOLDER_NAME;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_CLIENT_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_OLD_PASSWORD;
import static com.swadhaar.los.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_NAME;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_CAPTURE_ALL_DETAILS;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_CAPTURE_HOUSE_IMAGE;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_DATA_SAVED_SUCCESSFULLY;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_HOUSE_VERIFICATION_DONE_FOR_ALL;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_PASSWORD_CHANGED_SUCCESSFULLY;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_UNABLE_TO_CHANGE_PASSWORD;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.EXTENSION_JPG;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.FIELD_NAME_SAVE;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.FIELD_NAME_UPDATE;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.FINAL_STATUS_APPROVED;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.FINAL_STATUS_REJECTED;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.MAXIMUM_DISTANCE_FROM_CENTER_IN_METER_JLG;

public class HouseVerificationMemberDetailsActivity extends LOSBaseActivity implements HasSupportFragmentInjector, View.OnClickListener {

    private static String TAG = HouseVerificationMemberDetailsActivity.class.getCanonicalName();

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;
    private Toolbar toolbar;

    private EditText etDistance, etKyc1IDProof, etKyc1Id,
            etKyc2IdProof, etKyc2Id, etKyc1Address, etNomineeKycProof, etNomineeKycId, etRemarks;

    private TextView tvClientId, tvClientName, tvDob, tvAge, tvNomineeName;

    private TextView tv_label_distance, tv_label_kyc1ID, tv_label_kyc2ID, tv_label_nominee_kycID, tv_label_remark, tv_label_house_photo;

    private ImageView ivHousePhoto, ivDefaultImage, ivRemoveImage;

    private TextView tvImageTitle;

    private Button btnSave, btnNext, btnReject;

    private LinearLayout llParentView;

    HouseVerificationTable houseVerificationTable;
    Uri fileUri;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_verification_member_details);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        toolbar = (Toolbar) findViewById(R.id.toolbar_changePassword);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                finish();
            }
        });

        userName = getIntent().getStringExtra(PARAM_USER_NAME);
        userId = getIntent().getStringExtra(PARAM_USER_ID);
        branchId = getIntent().getStringExtra(PARAM_BRANCH_ID);
        branchGSTcode = getIntent().getStringExtra(PARAM_BRANCH_GST_CODE);
        loanType = getIntent().getStringExtra(PARAM_LOAN_TYPE);
        productId = getIntent().getStringExtra(PARAM_PRODUCT_ID);
        CLIENT_ID = getIntent().getStringExtra(PARAM_CLIENT_ID);

        tvClientId = (TextView) findViewById(R.id.tv_member_code);
        tvClientName = (TextView) findViewById(R.id.tv_member_name);
        tvDob = (TextView) findViewById(R.id.tv_dob);
        tvAge = (TextView) findViewById(R.id.tv_age);
        tvNomineeName = (TextView) findViewById(R.id.tv_nominee_name);

        tv_label_distance = (TextView) findViewById(R.id.tv_label_distance);
        SpannableStringBuilder builder_dis = new SpannableStringBuilder("Distance From Center:*");
        builder_dis.setSpan(new ForegroundColorSpan(Color.RED), builder_dis.length() - 1, builder_dis.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_label_distance.setText(builder_dis);

        tv_label_kyc1ID = (TextView) findViewById(R.id.tv_label_kyc1ID);
        SpannableStringBuilder builder_kyc1id = new SpannableStringBuilder("Kyc1 Number:*");
        builder_kyc1id.setSpan(new ForegroundColorSpan(Color.RED), builder_kyc1id.length() - 1, builder_kyc1id.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_label_kyc1ID.setText(builder_kyc1id);

        tv_label_kyc2ID = (TextView) findViewById(R.id.tv_label_kyc2ID);
//        SpannableStringBuilder builder_kyc2id = new SpannableStringBuilder("Kyc2 Number:*");
//        builder_kyc2id.setSpan(new ForegroundColorSpan(Color.RED), builder_kyc2id.length() - 1, builder_kyc2id.length(),
//                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        tv_label_kyc2ID.setText(builder_kyc2id);

        tv_label_nominee_kycID = (TextView) findViewById(R.id.tv_label_nominee_kycID);
        SpannableStringBuilder builder_nominee_kycID = new SpannableStringBuilder("Nominee Kyc Number:*");
        builder_nominee_kycID.setSpan(new ForegroundColorSpan(Color.RED), builder_nominee_kycID.length() - 1, builder_nominee_kycID.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_label_nominee_kycID.setText(builder_nominee_kycID);

        tv_label_remark = (TextView) findViewById(R.id.tv_label_remark);
        SpannableStringBuilder builder_remark = new SpannableStringBuilder("Remarks:*");
        builder_remark.setSpan(new ForegroundColorSpan(Color.RED), builder_remark.length() - 1, builder_remark.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_label_remark.setText(builder_remark);

        tv_label_house_photo = (TextView) findViewById(R.id.tv_label_house_photo);
        SpannableStringBuilder builder_house_photo = new SpannableStringBuilder("House photo:*");
        builder_house_photo.setSpan(new ForegroundColorSpan(Color.RED), builder_house_photo.length() - 1, builder_house_photo.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_label_house_photo.setText(builder_house_photo);

        etDistance = (EditText) findViewById(R.id.et_distance_from_center);
        etKyc1IDProof = (EditText) findViewById(R.id.et_kyc1_id_proof);
        etKyc1Id = (EditText) findViewById(R.id.et_kyc1_id);
        etKyc2IdProof = (EditText) findViewById(R.id.et_kyc2_id_proof);
        etKyc2Id = (EditText) findViewById(R.id.et_kyc2_id);
        etKyc1Address = (EditText) findViewById(R.id.et_kyc1_address);
        etNomineeKycProof = (EditText) findViewById(R.id.et_nominee_kyc_type);
        etNomineeKycId = (EditText) findViewById(R.id.et_nominee_kyc_id);
        etRemarks = (EditText) findViewById(R.id.et_remarks);

        ivHousePhoto = (ImageView) findViewById(R.id.iv_capture_image);
        ivHousePhoto.setOnClickListener(this);
        ivDefaultImage = (ImageView) findViewById(R.id.iv_default_image);
        ivRemoveImage = (ImageView) findViewById(R.id.iv_remove_image);
        ivRemoveImage.setOnClickListener(this);
        tvImageTitle = (TextView) findViewById(R.id.tv_image_title);

        llParentView = (LinearLayout) findViewById(R.id.ll_parentView);

        btnSave = (Button) findViewById(R.id.btn_save);
        btnSave.setOnClickListener(this);
        btnNext = (Button) findViewById(R.id.btn_next);
        btnNext.setOnClickListener(this);
        btnReject = (Button) findViewById(R.id.btn_reject);
        btnReject.setOnClickListener(this);

        configureDagger();
        configureViewModel();

        etDistance.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    int distance=Integer.parseInt(s.toString());
                    if(distance<=MAXIMUM_DISTANCE_FROM_CENTER_IN_METER_JLG) {
                        if (houseVerificationTable != null) {
                            houseVerificationTable.setDistanceFromCenter(s.toString());
                        }
                    }else{
                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,"Distance from center should be within "
                                +MAXIMUM_DISTANCE_FROM_CENTER_IN_METER_JLG+" meter");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etRemarks.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    if (houseVerificationTable != null) {
                        houseVerificationTable.setRemarks(s.toString());
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        // TODO: Get House Verification Detail
        getHouseVerificationMemberDetail();
    }

    private void configureDagger() {
        AndroidInjection.inject(this);
    }

    public void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DynamicUIViewModel.class);
    }


    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }


    public void getHouseVerificationMemberDetail() {
        appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

        try {
            viewModel.getHouseVerificationMemberDetail(CLIENT_ID, loanType);
            if (viewModel.getHouseVerificationTableLiveData() != null) {
                Observer getCenterCreationTableObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        houseVerificationTable = (HouseVerificationTable) o;
                        viewModel.getHouseVerificationTableLiveData().removeObserver(this);

                        if (houseVerificationTable != null) {
                            autoFillData(houseVerificationTable);
                        }
                    }
                };
                viewModel.getHouseVerificationTableLiveData().observe(this, getCenterCreationTableObserver);
            }
        } catch (Exception ex) {
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            ex.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.btn_save:
                    if (houseVerificationTable != null) {

                        boolean isValid = appHelper.checkErrors(llParentView, true);
                        if (isValid) {

                            int distance=Integer.parseInt(etDistance.getText().toString());
                            if(distance<=MAXIMUM_DISTANCE_FROM_CENTER_IN_METER_JLG) {
                                if (houseVerificationTable != null) {
                                    houseVerificationTable.setDistanceFromCenter(String.valueOf(distance));
                                }
                            }else{
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,"Distance from center should be within "
                                        +MAXIMUM_DISTANCE_FROM_CENTER_IN_METER_JLG+" meter");
                               return;
                            }

                            if (!TextUtils.isEmpty(houseVerificationTable.getFile_path())) {
                                approveOrRejectHouseVerification(FINAL_STATUS_APPROVED);
                            } else {
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                        ERROR_MESSAGE_CAPTURE_HOUSE_IMAGE);
                            }
                        }
                    }
                    break;
                case R.id.btn_next:
                    if (houseVerificationTable != null) {
                        nextHouseVerification();
                    } else {
                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                ERROR_MESSAGE_HOUSE_VERIFICATION_DONE_FOR_ALL);
                    }
                    break;
                case R.id.btn_reject:
                    appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons("Do you want to reject ?", new ConfirmationDialog.ActionCallback() {
                        @Override
                        public void onAction() {
                            approveOrRejectHouseVerification(FINAL_STATUS_REJECTED);
                        }
                    });
                    break;
                case R.id.iv_capture_image:
                    if (houseVerificationTable != null) {
                        if (!TextUtils.isEmpty(houseVerificationTable.getFile_path())) {
                            openImage();
                        } else {
                            houseVerificationTable.setFile_name(houseVerificationTable.getClientId() + "_housePhoto");
                            sendCameraIntent(houseVerificationTable);
                        }
                    }
                    break;
                case R.id.iv_remove_image:
                    removeImage();
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void removeImage() {

       /* if (houseVerificationTable != null) {
            File file = new File(houseVerificationTable.getFile_path());
            if (file.delete()) {
                houseVerificationTable.setHouseVerification(false);
                houseVerificationTable.setFile_name("");
                houseVerificationTable.setFile_path("");
                ivDefaultImage.setVisibility(View.VISIBLE);
                ivHousePhoto.setVisibility(View.GONE);
            }
        }*/

        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            try {
                viewModel.removeHousePhoto(houseVerificationTable);
                if (viewModel.getHouseVerificationTableLiveData() != null) {
                    Observer getCenterCreationTableObserver = new Observer() {
                        @Override
                        public void onChanged(@Nullable Object o) {
                            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                            HouseVerificationTable houseVerificationTableFromDB = (HouseVerificationTable) o;
                            viewModel.getHouseVerificationTableLiveData().removeObserver(this);
                            if (houseVerificationTableFromDB != null ) {
                                houseVerificationTable = houseVerificationTableFromDB;
                                autoFillData(houseVerificationTableFromDB);

                            }
                        }
                    };
                    viewModel.getHouseVerificationTableLiveData().observe(this, getCenterCreationTableObserver);
                }
            } catch (Exception ex) {
                appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                ex.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void sendCameraIntent(HouseVerificationTable houseVerificationTable) {
        try {

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.addFlags(FLAG_GRANT_READ_URI_PERMISSION | FLAG_GRANT_WRITE_URI_PERMISSION);
            fileUri = FileUtils.getUri(FileUtils.getOutputFile(this, APP_FOLDER, IMAGE_UPLOAD_FOLDER_NAME,
                    houseVerificationTable.getClientId(), houseVerificationTable.getFile_name(), EXTENSION_JPG));
            Uri uri = FileUtils.getIntentUri(this, fileUri);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(intent, IMAGE_CAPTURE_REQUEST);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openImage() {
        try {

            if (houseVerificationTable != null && !TextUtils.isEmpty(houseVerificationTable.getFile_path())) {
                JealousSky jealousSky = JealousSky.getInstance();

                jealousSky.initialize(
                        IMAGE_ENC_PSWD,
                        IMAGE_ENC_SALT);

                InputStream inputStream = new FileInputStream(houseVerificationTable.getFile_path());
                jealousSky.decryptToFile(inputStream, houseVerificationTable.getFile_path());


                File file = new File(houseVerificationTable.getFile_path());
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
            } else if (requestCode == IMAGE_GALLERY_REQUEST) {
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    compressImage(uri);
                } else if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(this,
                            "User cancelled image selection", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    Toast.makeText(this,
                            "Sorry! Failed to select image", Toast.LENGTH_SHORT)
                            .show();
                }
            }
            if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode == RESULT_OK) {
                    Uri resultUri = result.getUri();

                    Log.d(TAG, "Cropped URI ===> " + resultUri);

                    encryptAndStore(resultUri);

                    showImage();

                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    Exception error = result.getError();
                    Toast.makeText(HouseVerificationMemberDetailsActivity.this, "Image Cropping Failed => " + error, Toast.LENGTH_SHORT).show();
                    // TODO: 30-03-2019 need to display error
                    encryptAndStore(result.getUri());
                } else if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(this,
                            "User cancelled image selection", Toast.LENGTH_SHORT)
                            .show();
                    if (!TextUtils.isEmpty(houseVerificationTable.getFile_path())) {
                        File file = new File(houseVerificationTable.getFile_path());
                        Uri uri = FileUtils.getUri(file);
                        encryptAndStore(uri);
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
                        houseVerificationTable.setFile_path(filePath);
                        houseVerificationTable.setFile_name(fileName);

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

                        showImage();

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

    private void approveOrRejectHouseVerification(String status) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            try {
                viewModel.approveOrRejectHouseVerification(houseVerificationTable, status);
                if (viewModel.getHouseVerificationTableLiveData() != null) {
                    Observer getCenterCreationTableObserver = new Observer() {
                        @Override
                        public void onChanged(@Nullable Object o) {
                            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                            HouseVerificationTable houseVerificationTableFromDB = (HouseVerificationTable) o;
                            viewModel.getHouseVerificationTableLiveData().removeObserver(this);
                            if (houseVerificationTableFromDB != null && houseVerificationTableFromDB.isHouseVerification()) {
                                houseVerificationTable = houseVerificationTableFromDB;
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS,
                                        ERROR_MESSAGE_DATA_SAVED_SUCCESSFULLY, new ConfirmationDialog.ActionCallback() {
                                            @Override
                                            public void onAction() {
                                                autoFillData(houseVerificationTableFromDB);
                                            }
                                        });
                            }
                        }
                    };
                    viewModel.getHouseVerificationTableLiveData().observe(this, getCenterCreationTableObserver);
                }
            } catch (Exception ex) {
                appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                ex.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void nextHouseVerification() {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            try {
                viewModel.nextHouseVerification(houseVerificationTable);
                if (viewModel.getHouseVerificationTableLiveData() != null) {
                    Observer getCenterCreationTableObserver = new Observer() {
                        @Override
                        public void onChanged(@Nullable Object o) {
                            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                            HouseVerificationTable houseVerificationTableFromDB = (HouseVerificationTable) o;
                            viewModel.getHouseVerificationTableLiveData().removeObserver(this);

                            if (houseVerificationTableFromDB != null) {
                                houseVerificationTable = houseVerificationTableFromDB;
                                autoFillData(houseVerificationTable);
                            } else {
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                        ERROR_MESSAGE_HOUSE_VERIFICATION_DONE_FOR_ALL);
                            }

                        }
                    };
                    viewModel.getHouseVerificationTableLiveData().observe(this, getCenterCreationTableObserver);
                }
            } catch (Exception ex) {
                appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                ex.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void encryptAndStore(Uri resultUri) {
        try {

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

                if (file.exists()) {
                    fileInputStream = new FileInputStream(fileInput);
                }

                jealousSky.encryptToFile(fileInputStream, filePath);


            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void showImage() {
        try {
            if (houseVerificationTable != null) {
//                int count=position+1;
//                holder.tvImageTitle.setText(documentUploadTableNew.getDocument_name() + " " + count);

                if (!TextUtils.isEmpty(houseVerificationTable.getFile_path())) {

                    ivDefaultImage.setVisibility(View.GONE);

                   /* if (houseVerificationTable.isDocument_status()) {
                        holder.ivImageStatus.setVisibility(View.VISIBLE);
                    } else {
                        holder.ivImageStatus.setVisibility(View.GONE);
                    }*/

                    ivHousePhoto.setVisibility(View.VISIBLE);

                  /*  ivHousePhoto.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageCaptureInterface.openImageCallBack(documentUploadTableNew,position);
                        }
                    });
                    holder.ivRemoveImage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageCaptureInterface.removeImageCallBack(documentUploadTableNew,position);
                        }
                    });*/

                    RequestOptions requestOptions = new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.DATA)
                            .sizeMultiplier(0.5f)
                            .signature(new MediaStoreSignature("JPEG", System.currentTimeMillis(), 0))
                            .dontAnimate();


                    JealousSky jealousSky = JealousSky.getInstance();

                    jealousSky.initialize(
                            IMAGE_ENC_PSWD,
                            IMAGE_ENC_SALT);

                    InputStream inputStream = new FileInputStream(houseVerificationTable.getFile_path());
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
                                .load(houseVerificationTable.getFile_path())
                                .apply(requestOptions)
                                .thumbnail(
                                        Glide.with(this).asBitmap()
                                                .load(houseVerificationTable.getFile_path()) // TODO: Load File Path
                                                .apply(requestOptions))
                                .into(ivHousePhoto);
                    }

                } else {
                    ivHousePhoto.setImageBitmap(null);
                    ivDefaultImage.setVisibility(View.VISIBLE);
//                    ivImageStatus.setVisibility(View.GONE);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void autoFillData(HouseVerificationTable houseVerificationTable) {
        if (houseVerificationTable != null) {

            appHelper.clearAllData(llParentView);

            if (!TextUtils.isEmpty(houseVerificationTable.getClientId())) {
                tvClientId.setText(houseVerificationTable.getClientId());
            }
            if (!TextUtils.isEmpty(houseVerificationTable.getClientName())) {
                tvClientName.setText(houseVerificationTable.getClientName());
            }
            if (!TextUtils.isEmpty(houseVerificationTable.getDob())) {
                tvDob.setText(houseVerificationTable.getDob());
            }
            if (!TextUtils.isEmpty(houseVerificationTable.getAge())) {
                tvAge.setText(houseVerificationTable.getAge());
            }
            if (!TextUtils.isEmpty(houseVerificationTable.getNomineeName())) {
                tvNomineeName.setText(houseVerificationTable.getNomineeName());
            }
            if (!TextUtils.isEmpty(houseVerificationTable.getDistanceFromCenter())) {
                etDistance.setText(houseVerificationTable.getDistanceFromCenter());
            }
            if (!TextUtils.isEmpty(houseVerificationTable.getKyc1_id_proof())) {
                etKyc1IDProof.setText(houseVerificationTable.getKyc1_id_proof());
            }
            if (!TextUtils.isEmpty(houseVerificationTable.getKyc1_id())) {
                etKyc1Id.setText(houseVerificationTable.getKyc1_id());
            }
            if (!TextUtils.isEmpty(houseVerificationTable.getKyc2_id_proof())) {
                etKyc2IdProof.setText(houseVerificationTable.getKyc2_id_proof());
            }
            if (!TextUtils.isEmpty(houseVerificationTable.getKyc2_id())) {
                etKyc2Id.setText(houseVerificationTable.getKyc2_id());
            }
            if (!TextUtils.isEmpty(houseVerificationTable.getKyc1_address())) {
                etKyc1Address.setText(houseVerificationTable.getKyc1_address());
            }
            if (!TextUtils.isEmpty(houseVerificationTable.getNominee_id_proof())) {
                etNomineeKycProof.setText(houseVerificationTable.getNominee_id_proof());
            }
            if (!TextUtils.isEmpty(houseVerificationTable.getNominee_id())) {
                etNomineeKycId.setText(houseVerificationTable.getNominee_id());
            }
            if (!TextUtils.isEmpty(houseVerificationTable.getRemarks())) {
                etRemarks.setText(houseVerificationTable.getRemarks());
            }
            if (houseVerificationTable.isHouseVerification()) {
                btnSave.setText(FIELD_NAME_UPDATE);
            } else {
                btnSave.setText(FIELD_NAME_SAVE);
            }

            if (!TextUtils.isEmpty(houseVerificationTable.getFile_path())) {
                showImage();
            } else {
                ivHousePhoto.setImageBitmap(null);
                ivDefaultImage.setVisibility(View.VISIBLE);
            }

        }
    }
}
