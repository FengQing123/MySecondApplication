package com.example.jiaw2.mysecondapplication.util;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

/**
 * Created by jiaw2 on 2017/1/6.
 */
public class ToolImage {
    /**
     * 使用Glide加载图片（如果在非主线程中使用该方法，请把context参数换成getApplicationContext）
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void UseGlideShowPicture(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).crossFade().into(imageView);
    }
}
