package com.example.jiaw2.mysecondapplication.Activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.catching.uninstallself.UninstallObserver;
import com.example.jiaw2.mysecondapplication.R;
import com.example.jiaw2.mysecondapplication.util.UIHelper;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        listening();
//        new Thread(runnable).start();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    private void listening() {
        UninstallObserver.startWork("/data/data/" + getPackageName(), "https://www.baidu.com",
                android.os.Build.VERSION.SDK_INT);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    if (HaveInternet()) {
                        Log.e("MainActivity", "处在联网状态");
                    } else {
                        Log.e("MainActivity", "没网了");
                    }
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    public boolean HaveInternet() {
        ConnectivityManager manger = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manger.getActiveNetworkInfo();
        return (info != null && info.isConnected());
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
            case R.id.btn_webview:
                UIHelper.showActivity(this, WebViewActivity.class);
                break;
            case R.id.btn_ndk:
                UIHelper.showActivity(this, NDKActivity.class);
                break;
            case R.id.btn_download:
                UIHelper.showAnimalActivity(this, DownLoadActivity.class);
//                UIHelper.showActivity(this, DownLoadActivity.class);
                break;
            case R.id.btn_MVP:
                UIHelper.showActivity(this, MVPActivity.class);
                break;
            case R.id.btn_leak:
                UIHelper.showActivity(this, LeakActivity.class);
                break;
            case R.id.btn_baidulocation:
                UIHelper.showActivity(this, BaiduLocationActivity.class);
                break;
            case R.id.btn_roundDial:
                UIHelper.showActivity(this, RoundDialActivity.class);
                break;
            case R.id.btn_calenderListView:
                UIHelper.showActivity(this, CalendarActivity.class);
                break;
            case R.id.btn_PLVideoTextureView:
                UIHelper.showActivity(this, PLVideoTextureViewActivity.class);
                break;
            case R.id.btn_picCarousel:
                UIHelper.showActivity(this, PicCarouselActivity.class);
                break;
            case R.id.btn_SQLite:
                UIHelper.showActivity(this, SQLiteTestActivity.class);
                break;
            case R.id.btn_dynamicallyAddLayout:
                UIHelper.showActivity(this, DynamicallyAddLActivity.class);
                break;
            case R.id.btn_widgetTest:
                Bundle bundle = new Bundle();
                bundle.putString("value", "Get Value");
                UIHelper.showActivityWithBundle(this, WidgetTestActivity.class, bundle);
                break;
            case R.id.btn_android_location:
                UIHelper.showActivity(this, AndroidLocationActivity.class);
                break;
            case R.id.btn_bindService:
                UIHelper.showActivity(this, BindingActivity.class);
                break;
        }
    }
}
