package com.example.jiaw2.mysecondapplication.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by jiaw2 on 2016/12/5.
 */
public class ScreenReceiver extends BroadcastReceiver {

    private String TAG = ScreenReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            Log.d(TAG, "screen on");
//            LocationUtil locationUtil = AppContext.getInstance().locationUtil;
//            locationUtil.registerListener(BaiduLocationActivity.mListener);
        }
    }
}
