package com.saartak.el.di.module;

import android.app.Application;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;

import androidx.room.Room;

import com.bfil.uilibrary.helpers.AppHelper;
import com.commonsware.cwac.saferoom.SafeHelperFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.saartak.el.App;
import com.saartak.el.api.DynamicUIWebservice;
import com.saartak.el.database.DynamicUIDatabase;
import com.saartak.el.database.dao.DynamicUIDao;
import com.saartak.el.dynamicui.services.interceptors.NullOnEmptyConverterFactory;
import com.saartak.el.dynamicui.services.interceptors.UnsafeOkHttpClient;
import com.saartak.el.repositories.DynamicUIRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.saartak.el.constants.AppConstant.BASE_URL;


@Module(includes = ViewModelModule.class)
public class AppModule {

    @Provides
    @Singleton
    DynamicUIDatabase provideDynamicUIDatabase(Application application) {
        String currentDBPath = "";

        SafeHelperFactory factory = SafeHelperFactory.fromUser(new SpannableStringBuilder("Rbl@123"));

        try {
            currentDBPath = App.createDBPath();
            if (TextUtils.isEmpty(currentDBPath)) {
                currentDBPath = App.context.getDatabasePath("DynamicUIDatabase.db").getAbsolutePath();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Room.databaseBuilder(application,
                //DynamicUIDatabase.class, currentDBPath).addMigrations() // TODO: DB Migration
                               DynamicUIDatabase.class,currentDBPath)  // TODO: Without Migration
                 .fallbackToDestructiveMigration() // TODO: Database will be cleared
                 //.openHelperFactory(factory) // TODO: Set this encryption only for production release
                .build();

    }


    @Provides
    @Singleton
    DynamicUIDao provideDynamicUIDao(DynamicUIDatabase database) {
        return database.dynamicUIDao();
    }

    // --- REPOSITORY INJECTION ---

    @Provides
    Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Provides
    AppHelper provideAppHelper() {
        return new AppHelper(App.context);
    }

    @Provides
    @Singleton
    DynamicUIRepository provideDynamicUIRepository(DynamicUIWebservice webservice, DynamicUIDao userDao, Executor executor, AppHelper appHelper) {
        return new DynamicUIRepository(webservice, userDao, executor, appHelper);
    }
    // --- NETWORK INJECTION ---

//    private static String BASE_URL = "http://fileupload0330.azurewebsites.net/api/";

    /*public static void changeBaseURL(String newURL){
        BASE_URL=newURL;
    }*/

    private static OkHttpClient httpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();

    @Provides
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    Retrofit provideRetrofit(Gson gson) {
        // TODO: OLD
       /* Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .build(); */
        // TODO: NEW
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .client(httpClient)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    DynamicUIWebservice provideApiWebservice(Retrofit restAdapter) {
        return restAdapter.create(DynamicUIWebservice.class);
    }
}
