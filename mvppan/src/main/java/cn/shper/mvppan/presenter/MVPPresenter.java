package cn.shper.mvppan.presenter;

import cn.shper.mvppan.exception.MVPNotAttachedException;
import cn.shper.mvppan.utils.Logger;
import cn.shper.mvppan.view.MVPView;

/**
 * Author: Shper
 * Description: MVP框架 Presenter基础类,工程中所有 Presenter 必须继承此类
 * Version: V0.1 2016/12/28
 */
public abstract class MVPPresenter<V extends MVPView> {

    protected V mView;

    public MVPPresenter(V view) {
        // 绑定 View
        this.mView = view;
        Logger.d("attachView: ", null != view ? view.getClass().getName() : "Null");
    }

    /**
     * 子类根据 具体业务实现此方法
     */
    public abstract void onCreate();

    /**
     * 检测是否 View 已经绑定
     *
     * @return boolean
     */
    public boolean isViewAttached() {
        return mView != null;
    }

    /**
     * 获取绑定的 View
     *
     * @return view
     */
    public V getView() {
        return mView;
    }

    /**
     * 检测是否已经 View 绑定，如未绑定抛出 MVPViewNotAttachedException
     */
    public void checkViewAttached() {
        if (!isViewAttached()) {
            throw new MVPNotAttachedException("View Not Attach");
        }
    }

    /**
     * 解除全部绑定
     */
    public void onDestroy() {
        Logger.d("detachView: ", null != mView ? mView.getClass().getName() : "Null");
        mView = null;
    }
}
