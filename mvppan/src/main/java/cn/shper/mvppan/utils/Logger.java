package cn.shper.mvppan.utils;

import android.text.TextUtils;
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
        Log.d(TAG, msg);
    }

    public static void d(String... messages) {
        if (null == messages) {
            return;
        }
        StringBuilder builder = new StringBuilder();
        for (String msg : messages) {
            if (!TextUtils.isEmpty(msg)) {
                builder.append(msg);
            }
        }
        Log.d(TAG, builder.toString());
    }

}
