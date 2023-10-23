
package com.saartak.el;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.saartak.el.di.component.DaggerAppComponent;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

import static com.saartak.el.constants.AppConstant.APP_FOLDER;
import static com.saartak.el.constants.AppConstant.DB_FOLDER;
import static com.saartak.el.constants.AppConstant.DB_NAME;
import static com.saartak.el.constants.AppConstant.DB_SUB_FOLDER;


public class App extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    public static Context context;

    private Activity mCurrentActivity = null;


    public Activity getCurrentActivity(){
        return mCurrentActivity;
    }
    public void setCurrentActivity(Activity mCurrentActivity){
        this.mCurrentActivity = mCurrentActivity;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.initDagger();
        context = getApplicationContext();
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    // ---

    private void initDagger(){
        DaggerAppComponent.builder().application(this).build().inject(this);
    }


    public static HashMap<String, Object> createHashMapFromJsonString(String json) {
        JsonParser parser = new JsonParser();
        JsonObject object = (JsonObject) parser.parse(json);
        Set<Map.Entry<String, JsonElement>> set = object.entrySet();
        Iterator<Map.Entry<String, JsonElement>> iterator = set.iterator();
        HashMap<String, Object> map = new HashMap<String, Object>();

        while (iterator.hasNext()) {

            Map.Entry<String, JsonElement> entry = iterator.next();
            String key = entry.getKey();
            JsonElement value = entry.getValue();

            if (null != value) {
                if (!value.isJsonPrimitive()) {
                    if (value.isJsonObject()) {

                        map.put(key, createHashMapFromJsonString(value.toString()));
                    } else if (value.isJsonArray() && value.toString().contains(":")) {

                        List<HashMap<String, Object>> list = new ArrayList<>();
                        JsonArray array = value.getAsJsonArray();
                        if (null != array) {
                            for (JsonElement element : array) {
                                list.add(createHashMapFromJsonString(element.toString()));
                            }
                            map.put(key, list);
                        }
                    } else if (value.isJsonArray() && !value.toString().contains(":")) {
                        map.put(key, value.getAsJsonArray());
                    }
                } else {
                    map.put(key, value.getAsString());
                }
            }
        }
        return map;
    }

    public static String createDBPath(){
        String filePath="";
        try {

            // TODO: DATABASE NAME
            String dbName=DB_NAME;
            String appFolderName=APP_FOLDER;
            String appDBFolderName=DB_FOLDER;
            String appEnvFolderName=DB_SUB_FOLDER;


            // TODO: For LEAD
           /* String rootPath = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + "/LOS_DB/";*/

            // TODO: FOR LOS
            String appFolderPath = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + File.separator + appFolderName + File.separator ;
            String appDBPath = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + File.separator + appFolderName + File.separator + appDBFolderName + File.separator ;
            String appEnvPath = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + File.separator + appFolderName + File.separator + appDBFolderName + File.separator
                    + appEnvFolderName + File.separator ;
            // TODO: FULL FILE PATH
            filePath = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + File.separator + appFolderName + File.separator + appDBFolderName + File.separator
                    + appEnvFolderName + File.separator + dbName ;


            File folder = new File(appFolderPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            folder = new File(appDBPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            folder = new File(appEnvPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            if( !TextUtils.isEmpty(filePath)) {
                File f = new File(filePath);
                if (!f.exists()) {
                    boolean isFileCreated= f.createNewFile();
                    if(isFileCreated) {
                        FileOutputStream out = new FileOutputStream(f);
                        out.flush();
                        out.close();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }

}
