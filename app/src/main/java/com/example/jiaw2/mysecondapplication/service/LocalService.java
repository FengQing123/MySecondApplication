package com.example.jiaw2.mysecondapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Random;

/**
 * Created by jiaw2 on 2017/2/15.
 */
public class LocalService extends Service {

    private final IBinder mBinder = new LocalBinder();
    private final Random mGenerator = new Random();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class LocalBinder extends Binder {
        public LocalService getService() {
            return LocalService.this;
        }
    }

    public int getRandomNumber() {
        return mGenerator.nextInt(100);
    }
}
