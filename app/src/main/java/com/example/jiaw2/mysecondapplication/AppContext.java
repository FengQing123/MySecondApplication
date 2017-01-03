package com.example.jiaw2.mysecondapplication;

import android.app.Application;

import com.example.jiaw2.mysecondapplication.baidu.LocationUtil;

/**
 * Created by jiaw2 on 2016/12/5.
 */
public class AppContext extends Application {

    private static AppContext instance;
    public LocationUtil locationUtil;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        /***
         * 初始化定位sdk，建议在Application中创建
         */
        locationUtil = new LocationUtil(getApplicationContext());
//        SDKInitializer.initialize(getApplicationContext());
    }

    public static AppContext getInstance() {
        return instance;
    }
}
