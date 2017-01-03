package com.example.jiaw2.mysecondapplication.Activity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.jiaw2.mysecondapplication.R;

/**
 * Created by jiaw2 on 2016/10/21.
 */
public class AlarmManagerTestActivity extends Activity {
    private String TAG = AlarmManagerTestActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_manager_test);
        Log.e(TAG, "---------onCreate");

        findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelBroadCast();
            }
        });

        Intent intent = new Intent("alarm_clock");
        intent.putExtra("msg", "消息提醒");

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 5 * 1000, pendingIntent);
    }

    public void cancelBroadCast() {
        Intent intent = new Intent("alarm_clock");
        intent.putExtra("msg", "消息提醒");

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }
}
