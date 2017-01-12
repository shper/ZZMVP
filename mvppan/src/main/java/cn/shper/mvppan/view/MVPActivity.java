package cn.shper.mvppan.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import cn.shper.mvppan.presenter.MVPPresenter;
import cn.shper.mvppan.utils.Logger;
import cn.shper.mvppan.view.MVPView;

/**
 * Author: Shper
 * Description: MVP框架 Activity 基础类,工程中所有 Activity 必须继承此类
 * Version: V0.1 2016/12/28
 */
public abstract class MVPActivity<P extends MVPPresenter> extends AppCompatActivity implements MVPView {

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // 绑定 Presenter
        mPresenter = initPresenter();
        Logger.d("MvpActivity.onCreate: ", null != mPresenter ? mPresenter.getClass().getName() : "Null");
        // 启动 Presenter 生命周期
        if (null != mPresenter) {
            mPresenter.onCreate();
        }
        super.onCreate(savedInstanceState);

        // 分解 onCreate 使其更符合 单一职能原则
        setContentView(getLayoutId());
        // 初始化控件
        initViews(savedInstanceState);
        // 初始化变量
        initVariables(savedInstanceState);
        // 初始化监听器
        initListeners();
        // 加载数据
        loadDate();
    }

    protected abstract P initPresenter();

    protected abstract int getLayoutId();

    protected abstract void initVariables(@Nullable Bundle savedInstanceState);

    protected abstract void initViews(@Nullable Bundle savedInstanceState);

    protected abstract void initListeners();

    protected abstract void loadDate();

    public P getPresenter() {
        return mPresenter;
    }

    @Override
    protected void onDestroy() {
        if (null != mPresenter) {
            Logger.d("MvpActivity.onDestroy: " + mPresenter.getClass().getName());
            mPresenter.onDestroy();
            mPresenter = null;
        }
        super.onDestroy();
    }

}
