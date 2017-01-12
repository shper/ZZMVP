package cn.shper.mvppan.model;

import cn.shper.mvppan.exception.MVPNotAttachedException;
import cn.shper.mvppan.presenter.MVPPresenter;
import cn.shper.mvppan.utils.Logger;

/**
 * Author: Shper
 * Description: MVP Model 类
 * Version: V0.1 2016/12/28
 */
public abstract class MVPModel<P extends MVPPresenter> {

    protected P mPresenter;

    public MVPModel(P presenter) {
        // 绑定 Presenter
        Logger.d("attachPresenter: ", null != presenter ? presenter.getClass().getName() : "Null");
        this.mPresenter = presenter;
    }

    /**
     * 检测是否 View 已经绑定
     *
     * @return boolean
     */
    public boolean isPresenterAttached() {
        return mPresenter != null;
    }

    /**
     * 获取绑定的 View
     *
     * @return view
     */
    public P getPresenter() {
        return mPresenter;
    }

    /**
     * 检测是否已经 View 绑定，如未绑定抛出 MVPViewNotAttachedException
     */
    public void checkViewAttached() {
        if (!isPresenterAttached()) {
            throw new MVPNotAttachedException("Presenter Not Attached");
        }
    }

    /**
     * 解除绑定
     */
    public void onDestroy() {
        Logger.d("detachView: ", null != mPresenter ? mPresenter.getClass().getName() : "Null");
        this.mPresenter = null;
    }
}
