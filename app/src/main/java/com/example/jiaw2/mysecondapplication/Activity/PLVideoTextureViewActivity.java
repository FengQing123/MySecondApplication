package com.example.jiaw2.mysecondapplication.Activity;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.jiaw2.mysecondapplication.R;
import com.superplayer.library.SuperPlayer;

/**
 * Created by jiaw2 on 2016/12/23.
 */
public class PLVideoTextureViewActivity extends Activity implements View.OnClickListener, SuperPlayer.OnNetChangeListener {
    private SuperPlayer player;
    private boolean isLive = false;
    private Activity mActivity;

    private String url = "http://baobab.wandoujia.com/api/v1/playUrl?vid=2614&editionType=normal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plvideo_layout);
        mActivity = this;
        initPlayer();
    }


    /**
     * 初始化播放器
     */
    private void initPlayer() {
        player = (SuperPlayer) findViewById(R.id.view_super_player);
        if (isLive) {
            player.setLive(true);//设置该地址是直播的地址
        }
        player.setNetChangeListener(true)//设置监听手机网络的变化
                .setOnNetChangeListener(this)//实现网络变化的回调
                .onPrepared(new SuperPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared() {
                        /**
                         * 监听视频是否已经准备完成开始播放。（可以在这里处理视频封面的显示跟隐藏）
                         */
                    }
                }).onComplete(new Runnable() {
            @Override
            public void run() {
                /**
                 * 监听视频是否已经播放完成了。（可以在这里处理视频播放完成进行的操作）
                 */
            }
        }).onInfo(new SuperPlayer.OnInfoListener() {
            @Override
            public void onInfo(int what, int extra) {
                /**
                 * 监听视频的相关信息。
                 */

            }
        }).onError(new SuperPlayer.OnErrorListener() {
            @Override
            public void onError(int what, int extra) {
                /**
                 * 监听视频播放失败的回调
                 */

            }
        }).setTitle(url)//设置视频的titleName
                .play(url);//开始播放视频
        player.setScaleType(SuperPlayer.SCALETYPE_FITXY);
        player.setPlayerWH(0, player.getMeasuredHeight());//设置竖屏的时候屏幕的高度，如果不设置会切换后按照16:9的高度重置
    }

    @Override
    public void onClick(View view) {
    }

    /**
     * 网络链接监听类
     */
    @Override
    public void onWifi() {
        Toast.makeText(this, "当前网络环境是WIFI", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMobile() {
        Toast.makeText(this, "当前网络环境是手机网络", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDisConnect() {
        Toast.makeText(this, "网络链接断开", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNoAvailable() {
        Toast.makeText(this, "无网络链接", Toast.LENGTH_SHORT).show();
    }


    /**
     * 下面的这几个Activity的生命状态很重要
     */
    @Override
    protected void onPause() {
        super.onPause();
        if (player != null) {
            player.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (player != null) {
            player.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.onDestroy();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (player != null) {
            player.onConfigurationChanged(newConfig);

            if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {//竖屏
                WindowManager.LayoutParams attrs = getWindow().getAttributes();
                attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
                getWindow().setAttributes(attrs);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            } else {
                WindowManager.LayoutParams attrs = getWindow().getAttributes();
                attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
                getWindow().setAttributes(attrs);
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

//                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
//                        RelativeLayout.LayoutParams.FILL_PARENT,
//                        RelativeLayout.LayoutParams.FILL_PARENT);
//                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
//                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
//                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
//                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//                player.setLayoutParams(layoutParams);

            }
        }
    }

    @Override
    public void onBackPressed() {
        if (player != null && player.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }
}
