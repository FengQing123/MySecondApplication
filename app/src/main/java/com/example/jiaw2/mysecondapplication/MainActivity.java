package com.example.jiaw2.mysecondapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import util.UIHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_licenseKeyBoard:
                UIHelper.showActivity(this, LicenseKeyboardActivity.class);
                break;
            case R.id.btn_editText:
                UIHelper.showActivity(this, ShowEditTextActivity.class);
                break;
            case R.id.btn_fragment:
                UIHelper.showActivity(this, FragmentContainActivity.class);
                break;
            case R.id.btn_alarmManager:
                UIHelper.showActivity(this, AlarmManagerTestActivity.class);
                break;
            case R.id.btn_marquee:
                UIHelper.showActivity(this, MarqueeTestActivity.class);
                break;
            case R.id.btn_videoPlayer:
                UIHelper.showActivity(this, VideoPlayerActivity.class);
                break;
            case R.id.btn_viewPager:
                UIHelper.showActivity(this, ViewPagerTestActivity.class);
                break;
            case R.id.btn_itemDelete:
                UIHelper.showActivity(this, ListviewItemDeleteActivity.class);
                break;
            case R.id.btn_defined_marquee:
                UIHelper.showActivity(this, DefinedMarqueeActivity.class);
                break;
        }
    }
}
