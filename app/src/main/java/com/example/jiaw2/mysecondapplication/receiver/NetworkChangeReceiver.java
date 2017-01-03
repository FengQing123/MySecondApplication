package com.example.jiaw2.mysecondapplication.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by jiaw2 on 2016/12/5.
 */
public class NetworkChangeReceiver extends BroadcastReceiver {

    private String TAG = NetworkChangeReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG, "网络状态改变");
        boolean success = false;
        //获得网络连接服务
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        // State state = connManager.getActiveNetworkInfo().getState();
        // 获取WIFI网络连接状态
        NetworkInfo.State state = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        // 判断是否正在使用WIFI网络
        if (NetworkInfo.State.CONNECTED == state) {
            success = true;
        }
        // 获取GPRS网络连接状态
        state = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        // 判断是否正在使用GPRS网络
        if (NetworkInfo.State.CONNECTED == state) {
            success = true;
        }
        Toast.makeText(context, String.valueOf(success), Toast.LENGTH_LONG).show();
    }
}
