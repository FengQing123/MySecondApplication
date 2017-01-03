package com.example.jiaw2.mysecondapplication.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.jiaw2.mysecondapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiaw2 on 2016/11/3.
 */
public class ViewPagerTestActivity extends Activity {

    private ViewPager viewPager;
    private int[] images = {R.drawable.welcome_1,
            R.drawable.welcome_2,
            R.drawable.welcome_3,
            R.drawable.icon_real_time_video_greenvol0,
            R.drawable.icon_real_time_video_greenvol1,
            R.drawable.icon_real_time_video_greenvol2};
    private List<ImageView> list = new ArrayList<ImageView>();
    private Handler mHandler;
    private int next = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_test);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        // 为ViewPager添加动画事件
//		viewPager.setPageTransformer(true, new DepthPageTransformer());
//        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == next) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                }
            }
        };
        mHandler.sendEmptyMessageDelayed(next, 5000);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (images[position] == R.drawable.welcome_2) {
                    mHandler.sendEmptyMessageDelayed(next, 10000);
                } else {
                    mHandler.sendEmptyMessageDelayed(next, 5000);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        viewPager.setAdapter(new PagerAdapter() {
                                 @Override
                                 public Object instantiateItem(ViewGroup container, int position) {
                                     ImageView imageView = new ImageView(ViewPagerTestActivity.this);
                                     imageView.setImageResource(images[position]);
                                     imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                                     container.addView(imageView);
                                     list.add(imageView);
                                     return imageView;
                                 }

                                 @Override
                                 public void destroyItem(ViewGroup container, int position,
                                                         Object object) {
                                     container.removeView(list.get(position));
                                 }

                                 @Override
                                 public boolean isViewFromObject(View view, Object object) {
                                     return view == object;
                                 }

                                 @Override
                                 public int getCount() {
                                     return images.length;
                                 }
                             }

        );
    }
}
