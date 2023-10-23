package com.saartak.el.livedata;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.saartak.el.models.ConnectionModel;

import androidx.lifecycle.LiveData;

import static com.saartak.el.constants.AppConstant.MOBILE_CONNECTION;
import static com.saartak.el.constants.AppConstant.NO_INTERNET_CONNECTION;
import static com.saartak.el.constants.AppConstant.WIFI_CONNECTION;

public class ConnectionLiveData extends LiveData<ConnectionModel> {

    private Context context;

    public ConnectionLiveData(Context context) {
        this.context = context;
    }

    @Override
    protected void onActive() {
        super.onActive();
        IntentFilter intentFilter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(networkReceiver,intentFilter);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        context.unregisterReceiver(networkReceiver);
    }


    private BroadcastReceiver networkReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getExtras()!=null){
                NetworkInfo networkInfo=(NetworkInfo) intent.getExtras().get(ConnectivityManager.EXTRA_NETWORK_INFO);
                boolean isConnected=networkInfo!=null && networkInfo.isConnectedOrConnecting();
                if(isConnected){
                    switch (networkInfo.getType()){
                        case ConnectivityManager.TYPE_MOBILE:
                            postValue(new ConnectionModel(MOBILE_CONNECTION,true));
                            break;
                        case ConnectivityManager.TYPE_WIFI:
                            postValue(new ConnectionModel(WIFI_CONNECTION,true));
                            break;
                    }
                }else {
                    postValue(new ConnectionModel(NO_INTERNET_CONNECTION,false));
                }
            }
        }
    };
}
