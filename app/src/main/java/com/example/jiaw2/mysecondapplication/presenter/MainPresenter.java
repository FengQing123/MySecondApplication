package com.example.jiaw2.mysecondapplication.presenter;

import com.example.jiaw2.mysecondapplication.implement.TaskDataSourceImpl;
import com.example.jiaw2.mysecondapplication.implement.TaskDataSourceTestImpl;
import com.example.jiaw2.mysecondapplication.interfaces.MainView;
import com.example.jiaw2.mysecondapplication.manager.TaskManager;

/**
 * Created by jiaw2 on 2016/11/25.
 */
public class MainPresenter {

    MainView mainView;
    TaskManager taskManager;

    public MainPresenter() {
        this.taskManager = new TaskManager(new TaskDataSourceImpl());
    }

    public MainPresenter test() {
        this.taskManager = new TaskManager(new TaskDataSourceTestImpl());
        return this;
    }

    public MainPresenter addMainView(MainView mainView) {
        this.mainView = mainView;
        return this;
    }

    public void getString() {
        String s = taskManager.getStringContent();
        mainView.onShowString(s);
    }
}
