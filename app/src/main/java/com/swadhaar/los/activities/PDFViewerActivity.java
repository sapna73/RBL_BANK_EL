package com.swadhaar.los.activities;

import androidx.appcompat.app.AppCompatActivity;
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
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.google.gson.Gson;
import com.swadhaar.los.R;
import com.swadhaar.los.database.entity.KnowledgeBankTable;
import com.swadhaar.los.view_models.DynamicUIViewModel;

import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import okhttp3.ResponseBody;

import static com.swadhaar.los.constants.AppConstant.PARAM_KNOWLEDGE_BANK_TABLE;

public class PDFViewerActivity extends LOSBaseActivity {
    KnowledgeBankTable knowledgeBankTable;
    PDFView pdfView;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_pdf_viewer);

        pdfView=(PDFView)findViewById(R.id.pdfView);

        String knowledgeBankJsonString = getIntent().getStringExtra(PARAM_KNOWLEDGE_BANK_TABLE);
        if (!TextUtils.isEmpty(knowledgeBankJsonString)) {
            knowledgeBankTable = new Gson().fromJson(knowledgeBankJsonString, KnowledgeBankTable.class);
        }

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
                        viewModel.getResponseBodyLiveData().removeObserver(this);

                        if (responseBody != null && responseBody.byteStream() != null) {
                            InputStream inputStream = responseBody.byteStream();
                            try {
                                if(inputStream !=null && inputStream.available()>0) {
                                    pdfView.fromStream(inputStream)
                                    .defaultPage(1)
                                            .enableSwipe(true)

                                            .swipeHorizontal(false)
                                            .onPageChange(new OnPageChangeListener() {
                                                @Override
                                                public void onPageChanged(int page, int pageCount) {

                                                }
                                            })
                                            .enableAnnotationRendering(true)
                                            .onLoad(new OnLoadCompleteListener() {
                                                @Override
                                                public void loadComplete(int nbPages) {

                                                }
                                            })
                                            .scrollHandle(new DefaultScrollHandle(PDFViewerActivity.this))
                                            .load();


                                }
                                else{
                                    // TODO: Error message
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
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