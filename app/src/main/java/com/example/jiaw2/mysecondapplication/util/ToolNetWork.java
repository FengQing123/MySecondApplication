package com.example.jiaw2.mysecondapplication.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by jiaw2 on 2016/12/6.
 */
public class ToolNetWork {
    public static void network(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        int type = networkInfo.getType();
        if (type != ConnectivityManager.TYPE_WIFI) {
            String typeName = networkInfo.getSubtypeName();
            int subType = networkInfo.getSubtype();
            switch (subType) {

            }
        }
    }
}
