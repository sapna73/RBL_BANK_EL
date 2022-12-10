package com.swadhaar.los.dynamicui.services.interceptors;

import android.util.Log;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.CertificatePinner;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class UnsafeOkHttpClient {

    public static OkHttpClient getUnsafeOkHttpClient() {
        try {

            CertificatePinner certificatePinner = new CertificatePinner.Builder()
                    .add("losservices.rblfinserve.com", "sha256/yIECk6MVQIgvJdhI/iEPXSDLYkjf5WS8syeVnPbZOPI=")
                    .add("besure.rblfinserve.com", "sha256/yIECk6MVQIgvJdhI/iEPXSDLYkjf5WS8syeVnPbZOPI=")
                    .add("rfs1.rblfinserve.com", "sha256/yIECk6MVQIgvJdhI/iEPXSDLYkjf5WS8syeVnPbZOPI=")
//                    .add("104.211.103.21", "sha256/yIECk6MVQIgvJdhI/iEPXSDLYkjf5WS8syeVnPbZOPI=")
                    .build();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(120, TimeUnit.SECONDS);
            builder.readTimeout(120, TimeUnit.SECONDS);

//            builder.certificatePinner(certificatePinner);

            builder.retryOnConnectionFailure(true);
            builder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    Request modifiedRequest = original.newBuilder().build();
                    return chain.proceed(modifiedRequest);
                }
            });

            // TODO: LOG
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);


            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                            Log.d("TAG","response ==> "+ chain);
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            return builder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
