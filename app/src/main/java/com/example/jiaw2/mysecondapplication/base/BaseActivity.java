package com.example.jiaw2.mysecondapplication.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.example.jiaw2.mysecondapplication.util.UIHelper;

import butterknife.ButterKnife;

/**
 * Created by jiaw2 on 2017/1/6.
 */
public abstract class BaseActivity extends FragmentActivity implements IBaseActivity,View.OnClickListener {

    private View mContextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContextView = LayoutInflater.from(this).inflate(bindLayout(), null);
        setContentView(mContextView);

        //使用ButterKnife绑定控件
        ButterKnife.bind(this);

        //bundle传递数据
        initValue(getIntent().getExtras());

        //初始化控件
        initView(mContextView);

        //初始化数据
        initData();
    }

    public BaseActivity getActivity() {
        return this;
    }
}
