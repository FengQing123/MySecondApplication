package com.example.jiaw2.mysecondapplication.interfaces;

/**
 * Created by jiaw2 on 2016/11/25.
 * 数据层对外的接口
 */
public interface TaskDataSource {
    String getStringFromRemote();

    String getStringFromCache();
}
