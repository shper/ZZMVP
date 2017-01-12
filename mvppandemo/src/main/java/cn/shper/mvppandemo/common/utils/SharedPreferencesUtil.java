package cn.shper.mvppandemo.common.utils;

import android.content.Context;
import android.content.SharedPreferences;

import cn.shper.mvppandemo.MVPPanApplication;

/**
 * Author: Shper
 * Description: SharedPreferences 工具类
 * Version: V0.1 2016/12/29
 */
public class SharedPreferencesUtil {

    private static final String NAME = "WeatherInfo";

    /**
     * 保存信息
     */
    public static void save(String key, String value) {
        SharedPreferences sharedPreferences = MVPPanApplication.getInstance()
                .getSharedPreferences(NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * 保存信息
     */
    public static void save(String key, long value) {
        SharedPreferences sharedPreferences = MVPPanApplication.getInstance()
                .getSharedPreferences(NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    /**
     * 读取信息
     */
    public static String getString(String key) {
        SharedPreferences sharedPreferences = MVPPanApplication.getInstance()
                .getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }

    /**
     * 读取信息
     */
    public static long getLong(String key) {
        SharedPreferences sharedPreferences = MVPPanApplication.getInstance()
                .getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getLong(key, 0);
    }

}
