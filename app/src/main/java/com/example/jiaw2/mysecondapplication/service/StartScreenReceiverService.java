package com.example.jiaw2.mysecondapplication.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.jiaw2.mysecondapplication.receiver.ScreenReceiver;

/**
 * Created by jiaw2 on 2016/12/5.
 */
public class StartScreenReceiverService extends Service {

    private ScreenReceiver screenReceiver;

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        screenReceiver = new ScreenReceiver();
        registerReceiver(screenReceiver, filter);


//        Intent intents = new Intent("alarm_clock");
//        intents.putExtra("msg", "消息提醒");
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(AppContext.getInstance(), 0, intents, PendingIntent.FLAG_UPDATE_CURRENT);
//        AlarmManager alarmManager = (AlarmManager) AppContext.getInstance().getSystemService(AppContext.getInstance().ALARM_SERVICE);
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000, pendingIntent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        screenReceiver = new ScreenReceiver();
        registerReceiver(screenReceiver, filter);
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
