package cn.shper.mvppan.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.shper.mvppan.presenter.MVPPresenter;
import cn.shper.mvppan.utils.Logger;

/**
 * Author: Shper
 * Description: MVP框架 Fragment 基础类,工程中所有 Fragment 必须继承此类
 * Version: V0.1 2016/12/28
 */
public abstract class MVPFragment<P extends MVPPresenter> extends Fragment implements MVPView {

    protected P mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mPresenter = createPresenter();
        Logger.d("MVPFragment.onCreateView: ", null != mPresenter ? mPresenter.getClass().getName() : "Null");

        // 分解 onCreateView 使其更符合 单一职能原则
        // 初始化变量
        initVariables(savedInstanceState);
        // 初始化监听器
        initListeners();
        // 加载数据
        loadDate();

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    protected abstract P createPresenter();

    protected abstract void initVariables(@Nullable Bundle savedInstanceState);

    protected abstract void initListeners();

    protected abstract void loadDate();

    @Override
    public void onDestroyView() {
        if (null != mPresenter) {
            Logger.d("MVPFragment.onDestroyView: " + mPresenter.getClass().getName());
            mPresenter.onDestroy();
        }
        super.onDestroyView();
    }
}
