package cn.shper.pan.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cn.shper.pan.commons.util.Logger;

/**
 * Author: Shper
 * Description: MVP框架 Activity 基础类,工程中所有 Activity 必须继承此类
 * Version: V0.1 2016/12/28
 */
public abstract class MVPActivity<P extends MVPActivityPresenter> extends AppCompatActivity {

    private P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Logger.d(this.getClass().getName());
        super.onCreate(savedInstanceState);

        // 分解 onCreate 使其更符合 单一职能原则
        setContentView(getLayoutId());
        // 初始化控件
        initViews(savedInstanceState);
        // 初始化变量
        initVariables(savedInstanceState);
        // 初始化监听器
        initListeners();
        // 绑定 Presenter
        mPresenter = initPresenter();
        if (null != mPresenter) {
            Logger.d("Presenter: ", mPresenter.getClass().getName());
            mPresenter.onCreate();
        }
    }

    protected abstract int getLayoutId();

    protected abstract void initViews(@Nullable Bundle savedInstanceState);

    protected abstract void initVariables(@Nullable Bundle savedInstanceState);

    protected abstract void initListeners();

    protected abstract P initPresenter();

    @Override
    protected void onNewIntent(Intent intent) {
        Logger.d(this.getClass().getName());
        super.onNewIntent(intent);

        if (null != mPresenter) {
            Logger.d("Presenter: ", mPresenter.getClass().getName());
            mPresenter.onNewIntent(intent);
        }
    }

    @Override
    protected void onStart() {
        Logger.d(this.getClass().getName());
        super.onStart();

        if (null != mPresenter) {
            Logger.d("Presenter: ", mPresenter.getClass().getName());
            mPresenter.onStart();
        }
    }

    @Override
    protected void onRestart() {
        Logger.d(this.getClass().getName());
        super.onRestart();

        if (null != mPresenter) {
            Logger.d("Presenter: ", mPresenter.getClass().getName());
            mPresenter.onRestart();
        }
    }

    @Override
    protected void onResume() {
        Logger.d(this.getClass().getName());
        super.onResume();

        if (null != mPresenter) {
            Logger.d("Presenter: ", mPresenter.getClass().getName());
            mPresenter.onResume();
        }
    }

    @Override
    protected void onPause() {
        Logger.d(this.getClass().getName());
        super.onPause();

        if (null != mPresenter) {
            Logger.d("Presenter: ", mPresenter.getClass().getName());
            mPresenter.onPause();
        }
    }

    @Override
    protected void onStop() {
        Logger.d(this.getClass().getName());
        super.onStop();

        if (null != mPresenter) {
            Logger.d("Presenter: ", mPresenter.getClass().getName());
            mPresenter.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        Logger.d(this.getClass().getName());

        if (null != mPresenter) {
            Logger.d("Presenter: ", mPresenter.getClass().getName());
            mPresenter.onDestroy();
            mPresenter = null;
        }

        super.onDestroy();
    }

    public P getPresenter() {
        Logger.d("Presenter: ", null != mPresenter ? mPresenter.getClass().getName() : "null");
        return mPresenter;
    }

    static {
        Logger.initialization("MVPPan");
        if (BuildConfig.DEBUG) {
            Logger.openDebug();
        }
    }

}
