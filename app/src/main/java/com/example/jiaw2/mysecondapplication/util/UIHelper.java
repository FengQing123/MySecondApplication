package com.example.jiaw2.mysecondapplication.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by jiaw2 on 2016/10/17.
 */
public class UIHelper {
    public static void showActivity(Activity context, Class clz) {
        Intent intent = new Intent(context, clz);
        context.startActivity(intent);
    }

    @TargetApi(16)
    public static void showAnimalActivity(Activity context, Class clz) {
        Intent intent = new Intent(context, clz);
        context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(context).toBundle());
    }

    public static void ToastMessage(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
