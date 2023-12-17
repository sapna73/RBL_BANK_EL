package com.saartak.el.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.google.gson.Gson;
import com.saartak.el.R;
import com.saartak.el.activities.ImageCaptureActivity;
import com.saartak.el.activities.ImageCaptureActivityJLG;
import com.saartak.el.adapter.DocumentUploadHeaderAdapter;
import com.saartak.el.adapter.DocumentUploadSubHeaderAdapter;
import com.saartak.el.database.entity.DocumentUploadTableNew;
import com.saartak.el.database.entity.DynamicUITable;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

import static com.saartak.el.constants.AppConstant.LOAN_NAME_EL;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_JLG;
import static com.saartak.el.constants.AppConstant.PARAM_CLIENT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_DOCUMENT_UPLOAD_JSON;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_PROJECT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_SCREEN_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_SCREEN_NO;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.dynamicui.constants.ParametersConstant.DOCUMENT_NAME_APPLICANT_PHOTO;

public class DocumentUploadFragmentNew extends LOSBaseFragment implements LOSBaseFragment.DynamiUIinterfacce , DocumentUploadSubHeaderAdapter.DocumentUploadSubHeaderInterface{
    private static final String TAG = DocumentUploadFragmentNew.class.getCanonicalName() ;
    // TODO: Rename parameter arguments, choose names that match
    // TODO: Rename and change types of parameters

    private OnFragmentInteractionListener mListener;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    public DocumentUploadFragmentNew() {
        // Required empty public constructor
    }

    String LOAN_TYPE, CLIENT_ID, PROJECT_ID, PRODUCT_ID, SCREEN_ID, SCREEN_NAME, USER_ID, MODULE_TYPE;
    private RecyclerView rvDocUploadHeader;
    private Button btnUpload;
    List<DocumentUploadTableNew> documentUploadTableNewList;
    // TODO: Rename and change types and number of parameters

    public static DocumentUploadFragmentNew newInstance(String loanType, String clientId, String projectId, String productId,
                                                        String screenNo, String screenName, String userId, String moduleType) {
        DocumentUploadFragmentNew fragment = new DocumentUploadFragmentNew();
        Bundle args = new Bundle();
        args.putString(PARAM_LOAN_TYPE, loanType);
        args.putString(PARAM_CLIENT_ID, clientId);
        args.putString(PARAM_PROJECT_ID, projectId);
        args.putString(PARAM_PRODUCT_ID, productId);
        args.putString(PARAM_SCREEN_NO, screenNo);
        args.putString(PARAM_SCREEN_NAME, screenName);
        args.putString(PARAM_USER_ID, userId);
        args.putString(PARAM_MODULE_TYPE, moduleType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            LOAN_TYPE = getArguments().getString(PARAM_LOAN_TYPE);
            CLIENT_ID = getArguments().getString(PARAM_CLIENT_ID);
            PROJECT_ID = getArguments().getString(PARAM_PROJECT_ID);
            PRODUCT_ID = getArguments().getString(PARAM_PRODUCT_ID);
            SCREEN_ID = getArguments().getString(PARAM_SCREEN_NO);
            SCREEN_NAME = getArguments().getString(PARAM_SCREEN_NAME);
            USER_ID = getArguments().getString(PARAM_USER_ID);
            MODULE_TYPE = getArguments().getString(PARAM_MODULE_TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_document_upload, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "KYC documents to be uploaded after OSV");
        super.onViewCreated(view, savedInstanceState);
        rvDocUploadHeader = (RecyclerView) view.findViewById(R.id.rv_doc_upload_header);
        btnUpload = (Button) view.findViewById(R.id.btn_upload);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvDocUploadHeader.setLayoutManager(layoutManager);

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* if(documentUploadTableNewList !=null && documentUploadTableNewList.size()>0) {
                    DocumentUploadTableNew documentUploadTableNew=documentUploadTableNewList.get(0);
                    uploadDocumentsToServer( documentUploadTableNew);
                }else{
                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                            "Nothing To Upload");
                }*/
                checkMandatoryDocumentOfApplicant();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.configureDagger();
        this.configureViewModel();
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    @Override
    public void dynamicUICallback(List<DynamicUITable> viewParametersList) {
//        dynamicUI(viewParametersList);
    }

    @Override
    public void openImageCaptureCallBack(DocumentUploadTableNew documentUploadTableNew, int position) {
        try {
            String json = new Gson().toJson(documentUploadTableNew, DocumentUploadTableNew.class);
            if( !TextUtils.isEmpty(json)) {
                if(documentUploadTableNew.getLoan_type().equalsIgnoreCase(LOAN_NAME_EL)){
                    Intent intent = new Intent(getActivity(), ImageCaptureActivity.class);
                    intent.putExtra(PARAM_DOCUMENT_UPLOAD_JSON, json);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(getActivity(), ImageCaptureActivity.class);
                    intent.putExtra(PARAM_DOCUMENT_UPLOAD_JSON, json);
                    startActivity(intent);
                }
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public Fragment getFragment() {
        return this;
    }

    public void configureDagger() {
        AndroidSupportInjection.inject(this);
    }
    public void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DynamicUIViewModel.class);
        getDocumentUploadHeader();
    }

    @Override
    public void onResume() {
        super.onResume();
        try{
            getDocumentUploadHeader();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void getDocumentUploadHeader(){
        try{
            viewModel.getDocumentUploadHeader(CLIENT_ID, LOAN_TYPE, true);
            if(viewModel.getStringListLiveData() != null){
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        List<String> headerList = (List<String>) o;
                        viewModel.getStringListLiveData().removeObserver(this);
                        if(headerList != null && headerList.size() > 0){
                            // TODO: get sub header
                            getDocumentUploadSubHeader(headerList);
                        }
                    }
                };
                viewModel.getStringListLiveData().observe(this,observer);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void uploadDocumentsToServer(DocumentUploadTableNew documentUploadTableNew){
        try{
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            viewModel.uploadDocumentsToServer(documentUploadTableNew);
            if(viewModel.getDocumentUploadLiveDataList()!=null){
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();

                       List<DocumentUploadTableNew> documentUploadTableNewList=(List<DocumentUploadTableNew>) o;
                        viewModel.getDocumentUploadLiveDataList().removeObserver(this);
                        if(documentUploadTableNewList != null && documentUploadTableNewList.size() > 0){
                            // TODO: VALIDATION
                            boolean allDocumentsUploaded = false;
                            for( DocumentUploadTableNew documentUploadTableNew1:documentUploadTableNewList){
                                if( !documentUploadTableNew1.isDocument_status() && !TextUtils.isEmpty(documentUploadTableNew1.getFile_path())){
                                    allDocumentsUploaded = false;
                                    break;
                                }else{
                                    allDocumentsUploaded = true;
                                }
                            }

                            if(allDocumentsUploaded) {
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS,
                                        "Document Uploaded Successfully", new ConfirmationDialog.ActionCallback() {
                                            @Override
                                            public void onAction() {
                                                // TODO: RELOAD THE SCREEN
                                                getDocumentUploadHeader();
                                            }
                                        });
                            }else{
                                appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons(
                                        "Document Upload Failed . Do You Want To Upload Again ? ",
                                        new ConfirmationDialog.ActionCallback() {
                                            @Override
                                            public void onAction() {
                                                // TODO: CALL DOCUMENT UPLOAD AGAIN
                                                uploadDocumentsToServer(documentUploadTableNewList.get(0));
                                            }
                                        });
                            }
                        }else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                    "Nothing To Upload");
                        }
                    }
                };
                viewModel.getDocumentUploadLiveDataList().observe(this,observer);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void getDocumentUploadSubHeader(List<String> headerList){
        try{
            viewModel.getDocumentUploadSubHeader(CLIENT_ID, LOAN_TYPE, true);
            if(viewModel.getDocumentUploadLiveDataList() != null){
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        documentUploadTableNewList = (List<DocumentUploadTableNew>) o;
                        viewModel.getDocumentUploadLiveDataList().removeObserver(this);
                        if(documentUploadTableNewList != null && documentUploadTableNewList.size() > 0){
                           DocumentUploadHeaderAdapter documentUploadHeaderAdapter = new DocumentUploadHeaderAdapter(getActivity(),
                                    documentUploadTableNewList, headerList, DocumentUploadFragmentNew.this :: openImageCaptureCallBack);

                           rvDocUploadHeader.setAdapter(documentUploadHeaderAdapter);
                            documentUploadHeaderAdapter.notifyDataSetChanged();
                        }
                    }
                };
                viewModel.getDocumentUploadLiveDataList().observe(this, observer);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void checkMandatoryDocumentOfApplicant() {
        try {
            viewModel.getDocumentUploadSubHeader(CLIENT_ID, LOAN_TYPE, false);
            if (viewModel.getDocumentUploadLiveDataList() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        documentUploadTableNewList = (List<DocumentUploadTableNew>) o;
                        viewModel.getDocumentUploadLiveDataList().removeObserver(this);
                        if (documentUploadTableNewList != null && documentUploadTableNewList.size() > 0) {
                            if (!isApplicantPhotoAvailable(documentUploadTableNewList)) {
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                        "Applicant photo is mandatory in Documents.");
                            } else {
                                DocumentUploadTableNew documentUploadTableNew = documentUploadTableNewList.get(0);
                                uploadDocumentsToServer(documentUploadTableNew);
                            }
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                    "Nothing To Upload");
                        }
                    }
                };
                viewModel.getDocumentUploadLiveDataList().observe(this, observer);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private boolean isApplicantPhotoAvailable(List<DocumentUploadTableNew> documentUploadTableNewList) {
        boolean isValid = false;
        for (DocumentUploadTableNew docNew : documentUploadTableNewList) {
            if (docNew.getDocument_name().equalsIgnoreCase(DOCUMENT_NAME_APPLICANT_PHOTO)
                    && !TextUtils.isEmpty(docNew.getFile_path())) {
                isValid = true;
                break;
            }
        }
        return isValid;
    }
}
