package com.example.jiaw2.mysecondapplication.manager;

import com.example.jiaw2.mysecondapplication.interfaces.TaskDataSource;

/**
 * Created by jiaw2 on 2016/11/25.
 * 从数据层获取数据，在这里进行拼装和组合
 */
public class TaskManager {
    TaskDataSource taskDataSource;

    public TaskManager(TaskDataSource taskDataSource) {
        this.taskDataSource = taskDataSource;
    }

    public String getStringContent() {
        return taskDataSource.getStringFromRemote() + "\n" + taskDataSource.getStringFromCache();
    }
}
