package cn.shper.mvppan.utils;

import android.util.Log;

import cn.shper.mvppan.BuildConfig;

/**
 * Author: Shper
 * Description: 日志输出类
 * Version: V0.1 2016/12/28
 */
public class Logger {

    private static String TAG = "MVPPan";

    public static void d(String msg) {
        //if (BuildConfig.DEBUG) {
            Log.d(TAG, msg);
        //}
    }

}
