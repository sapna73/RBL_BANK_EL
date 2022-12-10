package com.bfil.ekyclibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.speech.tts.TextToSpeech;
import android.util.DisplayMetrics;
import android.util.Log;

import com.bfil.uilibrary.helpers.AppHelper;

import java.util.Locale;

public class TTSHelper {

    private static final String TAG = TTSHelper.class.getCanonicalName();
    private Context context;
    private TextToSpeech textToSpeechObj;
    private Locale locale;
    private AppHelper appHelper;

    public TTSHelper(Context context) {
        this.context = context;
        appHelper = new AppHelper(context);
        try {
            textToSpeechObj = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    try {
                        if (status == TextToSpeech.SUCCESS) {
                            Log.d("Init","Text to speech init success");
                            if (textToSpeechObj.isLanguageAvailable(Locale.ENGLISH) == TextToSpeech.LANG_AVAILABLE){
                                Log.d("Init","Text to speech US available");
                                textToSpeechObj.setLanguage(Locale.ENGLISH);}
                            else{
                                Log.d("Init","Text to speech US not available");
                            }
                        } else if (status == TextToSpeech.ERROR) {
                            Log.d("Init","Text to speech init failed");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void speak(Context context, int iStringTextId) {
        try {
            if(textToSpeechObj == null) {
                Log.e(TAG, "textToSpeechObj is null----->");
                return;
            }
            String strTextToSpeak = context.getResources().getString(iStringTextId);
            textToSpeechObj.setSpeechRate(1.0f);
            int iSpeak = textToSpeechObj.speak(strTextToSpeak, TextToSpeech.QUEUE_FLUSH, null);
            Log.i(TAG, "Speak Status -->" + iSpeak);
        } catch(Exception e) {
            Log.e(TAG, "Exception in textToSpeech -->"+e);
        }
    }

    public void speak(Context context, String iStringTextId) {
        try {
            if(textToSpeechObj == null) {
                Log.e(TAG, "textToSpeechObj is null----->");
                return;
            }
            textToSpeechObj.setSpeechRate(1.0f);
            int iSpeak = textToSpeechObj.speak(iStringTextId, TextToSpeech.QUEUE_FLUSH, null);
            Log.i(TAG, "Speak Status -->" + iSpeak);
        } catch(Exception e) {
            Log.e(TAG, "Exception in textToSpeech -->"+e);
        }
    }

    public void stopTTS() {
        try {
            if (textToSpeechObj != null){
                textToSpeechObj.stop();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void shutdownTTS(){
        try {
            if (textToSpeechObj != null){
                textToSpeechObj.shutdown();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public Locale changeLanguage(Context context, String strLanguage) {
        try {
            locale = new Locale(strLanguage);
            Resources res = context.getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = locale;
            res.updateConfiguration(conf, dm);
            SharedPreferences.Editor editorObj= appHelper.getAndCreateEditorObj();
            editorObj.putString("language",strLanguage);
            editorObj.commit();
        }  catch(Exception e) {
            Log.e(TAG, "Exception in changeLanguage -->"+e);
        }
        return locale;
    }

    public Locale getLocale() {
        if(locale == null) {
            Log.i(TAG, "Create New Locale Object--->");
        }
        return locale;
    }
}
