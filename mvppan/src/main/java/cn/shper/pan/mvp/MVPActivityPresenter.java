package cn.shper.pan.mvp;

import android.content.Intent;

import cn.shper.pan.mvp.exception.MVPNotBindException;

/**
 * Author: Shper
 * Description: MVP框架 Activity Presenter基础类,
 * 工程中所有 Activity 的 Presenter 必须继承此类
 *
 * Version: V0.1 2016/12/28
 */
public abstract class MVPActivityPresenter<A extends MVPActivity> {

    private A mActivity;

    public MVPActivityPresenter(A activity) {
        // 绑定 Activity
        this.mActivity = activity;
        Logger.d(this.getClass().getSimpleName(),
                " BindActivity: ", null != mActivity ? mActivity.getClass().getSimpleName() : "null");
    }

    /**
     * 子类根据 具体业务实现此方法
     */
    protected void onCreate() {
        Logger.d(this.getClass().getSimpleName());
    }

    protected void onNewIntent(Intent intent) {
        Logger.d(this.getClass().getSimpleName());
    }

    protected void onStart() {
        Logger.d(this.getClass().getSimpleName());
    }

    protected void onRestart() {
        Logger.d(this.getClass().getSimpleName());
    }

    protected void onResume() {
        Logger.d(this.getClass().getSimpleName());
    }

    protected void onPause() {
        Logger.d(this.getClass().getSimpleName());
    }

    protected void onStop() {
        Logger.d(this.getClass().getSimpleName());
    }

    /**
     * 解除全部绑定
     */
    protected void onDestroy() {
        Logger.d(this.getClass().getSimpleName(),
                " UnBindActivity: ", null != mActivity ? mActivity.getClass().getSimpleName() : "null");
        mActivity = null;
    }

    /**
     * 检测是否 Activity 已经绑定
     */
    public boolean isActivityBind() {
        Logger.d("BindActivity: ", null != mActivity ? mActivity.getClass().getSimpleName() : "null");
        return mActivity != null;
    }

    /**
     * 获取绑定的 Activity
     */
    public A getActivity() {
        Logger.d("BindActivity: ", null != mActivity ? mActivity.getClass().getSimpleName() : "null");
        return mActivity;
    }

    /**
     * 检测是否已经 Activity 绑定，如未绑定抛出 MVPViewNotAttachedException
     */
    public void checkActivityBind() {
        if (!isActivityBind()) {
            throw new MVPNotBindException("View Not Attach");
        }
    }

}
