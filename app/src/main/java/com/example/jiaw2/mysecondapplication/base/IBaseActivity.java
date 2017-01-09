package com.example.jiaw2.mysecondapplication.base;

import android.os.Bundle;
import android.view.View;

/**
 * Created by jiaw2 on 2017/1/6.
 */
public interface IBaseActivity {

    /**
     * Bundle传值
     *
     * @param bundle
     */
    void initValue(Bundle bundle);

    /**
     * 初始化控件
     *
     * @param view
     */
    void initView(final View view);

    /**
     * 初始化数据
     */
    void initData();

    /**
     * 绑定渲染视图的布局文件
     *
     * @return
     */
    int bindLayout();


}
