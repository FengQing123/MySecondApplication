package com.catching.uninstallself;

/**
 * Created by jiaw2 on 2016/11/21.
 */
public class UninstallObserver {
    static {
        System.loadLibrary("observer");
    }

    /**
     * @param path    自身的apk路径
     * @param url     卸载后打开的网站
     * @param version 系统版本号
     * @return
     */
    public static native String startWork(String path, String url, int version);
}
