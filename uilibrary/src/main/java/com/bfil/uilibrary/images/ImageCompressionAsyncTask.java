package com.bfil.uilibrary.images;

import android.os.AsyncTask;


public abstract class ImageCompressionAsyncTask extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... strings) {
        if(strings.length == 0 || strings[0] == null)
            return null;
        return ImageUtils.compressImage(strings[0]);
    }

    protected abstract void onPostExecute(String imageBytes) ;
}
