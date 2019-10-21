package cn.shper.demo.mvp;

import android.app.Application;

import cn.shper.okhttppan.OkHttpPan;

/**
 * Author: Shper
 * Description: MVPPan Demo Main Application
 * Version: V0.1 2016/12/28
 */
public class MVPPanApplication extends Application {

    private static MVPPanApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        // 初始化 OkHttpPan
        OkHttpPan.initialization();
        OkHttpPan.setDebug(true);
    }

    public static MVPPanApplication getInstance() {
        return instance;
    }

}
