package com.example.jiaw2.mysecondapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import util.HorizontalSlideAdapter;

/**
 * Created by jiaw2 on 2016/11/8.
 */
public class ListviewItemDeleteActivity extends Activity{


    private ListView mListView;

    private List<String> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_item_delete);

        initData();

        mListView = (ListView) findViewById(R.id.listview);
        HorizontalSlideAdapter adapter = new HorizontalSlideAdapter(this,
                mDataList);
        mListView.setAdapter(adapter);
    }

    private void initData() {
        mDataList = new ArrayList<String>();
        mDataList.add("曹　操");
        mDataList.add("刘　备");
        mDataList.add("孙　权");
        mDataList.add("华　佗");
        mDataList.add("司马懿");
        mDataList.add("关　羽");
        mDataList.add("甘　宁");
        mDataList.add("吕　布");
        mDataList.add("夏侯惇");
        mDataList.add("张　飞");
        mDataList.add("吕　蒙");
        mDataList.add("貂　蝉");
        mDataList.add("张　辽");
        mDataList.add("诸葛亮");
        mDataList.add("黄　盖");
        mDataList.add("袁　绍");
        mDataList.add("许　褚");
        mDataList.add("赵　云");
        mDataList.add("周　瑜");
        mDataList.add("郭　嘉");
        mDataList.add("马　超");
        mDataList.add("大　乔");
        mDataList.add("董　卓");
        mDataList.add("甄　姬");
        mDataList.add("黄月英");
        mDataList.add("陆　逊");
        mDataList.add("贾　诩");
    }


}
