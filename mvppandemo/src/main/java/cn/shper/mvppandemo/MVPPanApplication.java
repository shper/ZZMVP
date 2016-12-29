package cn.shper.mvppandemo;

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
        OkHttpPan.initClient();
        instance = this;
    }

    public static MVPPanApplication getInstance(){
        return instance;
    }

}
