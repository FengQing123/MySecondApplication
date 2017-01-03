package com.example.jiaw2.mysecondapplication.implement;

import com.example.jiaw2.mysecondapplication.interfaces.TaskDataSource;

/**
 * Created by jiaw2 on 2016/11/25.
 */
public class TaskDataSourceTestImpl implements TaskDataSource {
    @Override
    public String getStringFromRemote() {
        return "从网络获取数据---测试";
    }

    @Override
    public String getStringFromCache() {
        return "从本地获取数据---测试";
    }
}
