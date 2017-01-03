package com.example.jiaw2.mysecondapplication.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.jiaw2.mysecondapplication.R;
import com.example.jiaw2.mysecondapplication.interfaces.MainView;
import com.example.jiaw2.mysecondapplication.presenter.MainPresenter;

/**
 * Created by jiaw2 on 2016/11/25.
 */
public class MVPActivity extends Activity implements MainView {

    private TextView textView;
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mvp_layout);
        textView = (TextView) findViewById(R.id.text);
        loadData();
    }

    public void loadData() {
        mainPresenter = new MainPresenter();
        mainPresenter.addMainView(this);
        mainPresenter.getString();
    }

    @Override
    public void onShowString(String json) {
        textView.setText(json);
    }
}
