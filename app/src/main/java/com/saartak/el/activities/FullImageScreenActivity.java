package com.saartak.el.activities;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bogdwellers.pinchtozoom.ImageMatrixTouchHandler;
import com.google.gson.Gson;
import com.saartak.el.R;
import com.saartak.el.database.entity.KnowledgeBankTable;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.io.InputStream;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import okhttp3.ResponseBody;

import static com.saartak.el.constants.AppConstant.PARAM_KNOWLEDGE_BANK_TABLE;

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