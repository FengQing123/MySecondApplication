package com.example.jiaw2.mysecondapplication.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.jiaw2.mysecondapplication.R;
import com.example.jiaw2.mysecondapplication.bean.Info;
import com.example.jiaw2.mysecondapplication.widget.CycleViewPager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiaw2 on 2017/1/3.
 */
public class PicCarouselActivity extends Activity {

    /**
     * 模拟请求后得到的数据
     */
    List<Info> mList = new ArrayList<>();

    /**
     * 轮播图
     */
    CycleViewPager mCycleViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pic_carousel_layout);
        initData();
        initView();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mList.add(new Info("标题1",
                "http://img2.3lian.com/2014/c7/25/d/40.jpg"));
        mList.add(new Info("标题2",
                "http://img2.3lian.com/2014/c7/25/d/41.jpg"));
        mList.add(new Info("标题3",
                "http://imgsrc.baidu.com/forum/pic/item/b64543a98226cffc8872e00cb9014a90f603ea30.jpg"));
        mList.add(new Info("标题4",
                "http://imgsrc.baidu.com/forum/pic/item/261bee0a19d8bc3e6db92913828ba61eaad345d4.jpg"));
    }

    /**
     * 初始化View
     */
    private void initView() {
        mCycleViewPager = (CycleViewPager) findViewById(R.id.cycle_view);
        //设置选中和未选中时的图片
        mCycleViewPager.setIndicators(R.drawable.ad_select, R.drawable.ad_unselect);
        //设置轮播间隔时间
        mCycleViewPager.setDelay(2000);
        mCycleViewPager.setData(mList, mAdCycleViewListener);
    }

    /**
     * 轮播图点击监听
     */
    private CycleViewPager.ImageCycleViewListener mAdCycleViewListener = new CycleViewPager.ImageCycleViewListener() {

        @Override
        public void onImageClick(Info info, int position, View imageView) {

            if (mCycleViewPager.isCycle()) {
                position = position - 1;
            }
            Toast.makeText(PicCarouselActivity.this, info.getTitle() +
                    "选择了--" + position, Toast.LENGTH_LONG).show();
        }
    };

    /**
     * 得到轮播图的View
     *
     * @param context
     * @param url
     * @return
     */
    public static View getImageView(Context context, String url) {
        RelativeLayout rl = new RelativeLayout(context);
        //添加一个ImageView，并加载图片
        ImageView imageView = new ImageView(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(layoutParams);
        //使用Picasso来加载图片
        Picasso.with(context).load(url).into(imageView);
        //在Imageview前添加一个半透明的黑色背景，防止文字和图片混在一起
        ImageView backGround = new ImageView(context);
        backGround.setLayoutParams(layoutParams);
        backGround.setBackgroundResource(R.color.cycle_image_bg);
        rl.addView(imageView);
        rl.addView(backGround);
        return rl;
    }

}
