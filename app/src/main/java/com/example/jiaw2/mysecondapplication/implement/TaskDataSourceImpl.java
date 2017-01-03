package com.example.jiaw2.mysecondapplication.implement;

import com.example.jiaw2.mysecondapplication.interfaces.TaskDataSource;

/**
 * Created by jiaw2 on 2016/11/25.
 * 负责数据获取（本地或者远程）
 */
public class TaskDataSourceImpl implements TaskDataSource {
    @Override
    public String getStringFromRemote() {
        return "从网络获取数据";
    }

    @Override
    public String getStringFromCache() {
        return "从本地获取数据";
    }
}
