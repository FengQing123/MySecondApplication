package com.example.jiaw2.mysecondapplication.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.jiaw2.mysecondapplication.service.StartScreenReceiverService;

/**
 * Created by jiaw2 on 2016/12/5.
 */
public class PhoneRestartReceiver extends BroadcastReceiver {
    private String TAG = PhoneRestartReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "手机重启了");
        Intent startIntent = new Intent(context, StartScreenReceiverService.class);
        context.startService(startIntent);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(3000);
                        Log.e(TAG, "手机重启了。。。。。");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

//        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
//            Log.i(TAG, "手机重启了");
//
//            Intent startIntent = new Intent(context, StartScreenReceiverService.class);
//            context.startService(startIntent);
//
//
//
////            LocationUtil locationUtil = AppContext.getInstance().locationUtil;
////            locationUtil.registerListener(BaiduLocationActivity.mListener);
//
////            Intent intents = new Intent("alarm_clock");
////            intents.putExtra("msg", "消息提醒");
////
////            PendingIntent pendingIntent = PendingIntent.getBroadcast(AppContext.getInstance(), 0, intents, PendingIntent.FLAG_UPDATE_CURRENT);
////            AlarmManager alarmManager = (AlarmManager) AppContext.getInstance().getSystemService(AppContext.getInstance().ALARM_SERVICE);
////            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000, pendingIntent);
//        }
    }
}
