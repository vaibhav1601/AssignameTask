package com.vaibhav.assignamettask;

import android.app.Application;
import android.content.Context;

import com.vaibhav.assignamettask.services.ConnectivityReceiver;

public class MyApplication extends Application {

    private static Context mContext;
    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mInstance = this;

    }


    public static synchronized MyApplication getInstance() {
        return mInstance;
    }


    public static Context getmContext() {
        return mContext;
    }


    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }

}
