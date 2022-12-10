package com.swadhaar.los.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bogdwellers.pinchtozoom.ImageMatrixTouchHandler;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.MediaStoreSignature;
import com.google.gson.Gson;
import com.swadhaar.los.R;
import com.swadhaar.los.database.entity.CGTTable;
import com.swadhaar.los.database.entity.KnowledgeBankTable;
import com.swadhaar.los.models.DocumentUploadRawdataResponseDTO;
import com.swadhaar.los.view_models.DynamicUIViewModel;

import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import okhttp3.ResponseBody;

import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_CGT_TABLE_JSON;
import static com.swadhaar.los.constants.AppConstant.PARAM_KNOWLEDGE_BANK_TABLE;
import static com.swadhaar.los.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_NAME;

public class FullImageScreenActivity extends LOSBaseActivity {
    KnowledgeBankTable knowledgeBankTable;

    ImageView imageView;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_full_image_screen);

        imageView = (ImageView) findViewById(R.id.img_skyc);

        String knowledgeBankJsonString = getIntent().getStringExtra(PARAM_KNOWLEDGE_BANK_TABLE);
        if (!TextUtils.isEmpty(knowledgeBankJsonString)) {
            knowledgeBankTable = new Gson().fromJson(knowledgeBankJsonString, KnowledgeBankTable.class);
        }

        ImageMatrixTouchHandler imageMatrixTouchHandler = new ImageMatrixTouchHandler(this);
        imageView.setOnTouchListener(imageMatrixTouchHandler);

        configureDagger();
        configureViewModel();
    }

    private void configureDagger() {
        AndroidInjection.inject(this);
    }

    public void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DynamicUIViewModel.class);

        if (knowledgeBankTable != null) {
            downloadDocumentsForKnowledgeBank(knowledgeBankTable);
        }
    }

    private void downloadDocumentsForKnowledgeBank(KnowledgeBankTable knowledgeBankTable) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            viewModel.downloadDocumentsForKnowledgeBank(knowledgeBankTable);
            if (viewModel.getResponseBodyLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        ResponseBody responseBody = (ResponseBody) o;
//                        Log.d(TAG, "downloadDocuments ==> " + response);
                        viewModel.getResponseBodyLiveData().removeObserver(this);

                        if (responseBody != null && responseBody.byteStream() != null) {
                            InputStream inputStream = responseBody.byteStream();
                            Bitmap bmp = BitmapFactory.decodeStream(inputStream);
                            if (bmp != null) {
                                imageView.setImageBitmap(bmp);

//                                RequestOptions requestOptions = new RequestOptions()
//                                        .diskCacheStrategy(DiskCacheStrategy.DATA)
//                                        .sizeMultiplier(0.5f)
//                                        .signature(new MediaStoreSignature("JPEG", System.currentTimeMillis(), 0))
//                                        .dontAnimate();
//
//                                Glide.with(FullImageScreenActivity.this)
//                                        .asBitmap()
//                                        .load(bmp)
//                                        .apply(requestOptions)
//                                        .thumbnail(
//                                                Glide.with(FullImageScreenActivity.this)
//                                                        .asBitmap()
//                                                        .load(bmp)
//                                                        .apply(requestOptions))
//                                        .into(imageView);
                            }
                        }


                    }
                };
                viewModel.getResponseBodyLiveData().observe(this, observer);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
//            INSERT_LOG("downloadDocuments", "Exception : " + ex.getMessage());
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}