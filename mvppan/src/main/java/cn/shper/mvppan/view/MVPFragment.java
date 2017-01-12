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

    protected P mvpPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
        Logger.d("MVPFragment.onCreateView: " + mvpPresenter.getClass().getName());
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected abstract P createPresenter();

    @Override
    public void onDestroyView() {
        if (null != mvpPresenter) {
            Logger.d("MVPFragment.onDestroyView: " + mvpPresenter.getClass().getName());
            mvpPresenter.onDestroy();
        }
        super.onDestroyView();
    }
}
