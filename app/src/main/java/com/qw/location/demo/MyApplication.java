package com.qw.location.demo;

import android.app.Application;

import com.amap.api.location.AMapLocationClient;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AMapLocationClient.updatePrivacyShow(this, true, true);
        AMapLocationClient.updatePrivacyAgree(this, true);
    }
}
