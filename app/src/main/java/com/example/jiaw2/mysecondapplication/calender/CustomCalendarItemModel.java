package com.example.jiaw2.mysecondapplication.calender;

import com.kelin.calendarlistview.library.BaseCalendarItemModel;

/**
 * Created by jiaw2 on 2016/12/23.
 */
public class CustomCalendarItemModel extends BaseCalendarItemModel {
    private int newsCount;
    private boolean isFav;

    public int getNewsCount() {
        return newsCount;
    }

    public void setNewsCount(int newsCount) {
        this.newsCount = newsCount;
    }

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }
}
