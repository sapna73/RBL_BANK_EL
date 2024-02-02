package com.saartak.el.dynamicui.services;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.saartak.el.dynamicui.services.interceptors.DownloadOkHtttpClient;
import com.saartak.el.dynamicui.services.interceptors.NullOnEmptyConverterFactory;
import com.saartak.el.dynamicui.services.interceptors.UnsafeOkHttpClient;

import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DynamicUIWebService {

    //    public static String apiBaseUrl = "http://fileupload0330.azurewebsites.net/api/";
//    public static String apiBaseUrl = "http://hyd-devcenter.southindia.cloudapp.azure.com/fileupload/api/";
    public static String apiBaseUrl = "http://rfs-azu-w004.centralindia.cloudapp.azure.com/fileupload/api/";

    private static OkHttpClient httpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();
//    private static OkHttpClient httpClient = new OkHttpClient();

    private static Gson gson = new GsonBuilder().setLenient()
//            .setPrettyPrinting()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .addConverterFactory(new NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(apiBaseUrl)
            .client(httpClient);

    public static void changeApiBaseUrl(String newApiBaseUrl) {
        apiBaseUrl = newApiBaseUrl;
        builder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .baseUrl(apiBaseUrl)
                .client(httpClient);
    }

    
    public static void changeDownloadBaseUrl(String newApiBaseUrl,
                                             DownloadOkHtttpClient.ProgressListener progressListener) {
        OkHttpClient downloadHttpClient = DownloadOkHtttpClient.getDownloadOkHttpClient(progressListener);
        apiBaseUrl = newApiBaseUrl;
        builder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .baseUrl(apiBaseUrl)
                .client(downloadHttpClient);
    }

    public static <S> S createService(Class<S> serviceClass) {
        return builder.build().create(serviceClass);
    }

}
