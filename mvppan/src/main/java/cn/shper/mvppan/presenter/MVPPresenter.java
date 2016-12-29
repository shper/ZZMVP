package cn.shper.mvppan.presenter;

import cn.shper.mvppan.exception.MVPNotAttachedException;
import cn.shper.mvppan.model.MVPModel;
import cn.shper.mvppan.utils.Logger;
import cn.shper.mvppan.view.MVPView;

/**
 * Author: Shper
 * Description: MVP框架 Presenter基础类,工程中所有 Presenter 必须继承此类
 * Version: V0.1 2016/12/28
 */
public abstract class MVPPresenter<V extends MVPView, M extends MVPModel> {

    protected V mView;
    protected M mModel;

    public MVPPresenter(V mvpView){
        Logger.d("attachView: " + mvpView.getClass().getName());
        this.mView = mvpView;
    }

    /**
     * 子类实现此方法 绑定 Model
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
     * 检测是否 Model 已经绑定
     *
     * @return boolean
     */
    public boolean isModelAttached() {
        return mModel != null;
    }

    /**
     * 绑定 Model
     */
    public void attachModel(M mvpModel){
        Logger.d("attachModel: " + mvpModel.getClass().getName());
        this.mModel = mvpModel;
    }

    /**
     * 解绑 Model
     */
    public void detachModel(){
        Logger.d("detachModel: " + mModel.getClass().getName());
        this.mModel = null;
    }

    /**
     * 获取绑定的 Model
     *
     * @return Model
     */
    public M getModel() {
        return mModel;
    }

    /**
     * 检测是否已经 Model 绑定，如未绑定抛出 MVPViewNotAttachedException
     */
    public void checkModelAttached() {
        if (!isModelAttached()) {
            throw new MVPNotAttachedException("Model Not Attach");
        }
    }

    /**
     * 解除全部绑定
     */
    public void onDestroy() {
        Logger.d("detachView: " + mView.getClass().getName());
        this.mView = null;
        detachModel();
    }
}
