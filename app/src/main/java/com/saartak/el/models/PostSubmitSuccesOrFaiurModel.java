package com.saartak.el.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostSubmitSuccesOrFaiurModel {

    @SerializedName("PostSubmitSuccessAndFailur")
    @Expose
    private String postSubmitSuccessAndFailur="";

    public String getPostSubmitSuccessAndFailur() {
        return postSubmitSuccessAndFailur;
    }

    public void setPostSubmitSuccessAndFailur(String postSubmitSuccessAndFailur) {
        this.postSubmitSuccessAndFailur = postSubmitSuccessAndFailur;
    }
}
