package com.example.jiaw2.mysecondapplication.Activity;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TextView;

import com.example.jiaw2.mysecondapplication.R;

/**
 * Created by jiaw2 on 2016/12/2.
 * 屏幕旋转的时候系统会销毁当前的activity。
 * 但是当drawable和view关联后，drawable保存了view的 reference，
 * 即drawable保存了label的引用，而label保存了activity的引用。
 * 既然drawable不能销毁，它所引用和间接引用的都不能销毁，这样系统就没有办法销毁当前的activity，
 * 于是造成了内存泄露
 */
public class LeakActivity extends Activity {

    private static Drawable drawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setText("hello");
        if (drawable == null) {
            drawable = getDrawable(R.drawable.icon_real_time_video_greenvol0);
        }
        textView.setBackgroundDrawable(drawable);
        setContentView(textView);
    }

}
