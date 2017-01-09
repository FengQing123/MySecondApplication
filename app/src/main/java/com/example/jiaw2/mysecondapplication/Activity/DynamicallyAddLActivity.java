package com.example.jiaw2.mysecondapplication.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by jiaw2 on 2017/1/6.
 */
public class DynamicallyAddLActivity extends Activity {

    private LinearLayout linearLayout;
    private LinearLayout.LayoutParams params;
    private int MATCH = ViewGroup.LayoutParams.MATCH_PARENT;
    private int CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //实例化一个布局对象
        linearLayout = new LinearLayout(this);
        //设置为垂直布局
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        //实例化布局参数，用来添加view
        params = new LinearLayout.LayoutParams(CONTENT, CONTENT);
        //设置与此视图关联的布局参数
        linearLayout.setLayoutParams(params);
        //加载主屏布局
        setContentView(linearLayout);

        //构造实例化TextView对象
        TextView textView = new TextView(this);
        //设置与此视图关联的布局参数
        textView.setLayoutParams(params);
        //设置text view文本内容
        textView.setText("就是这样");
        //设置textview位置
        textView.setGravity(Gravity.CENTER);
        //把textview添加到布局中
        linearLayout.addView(textView);
    }
}
