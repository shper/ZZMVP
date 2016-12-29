package cn.shper.mvppandemo.presenter;

import android.text.TextUtils;

import cn.shper.mvppan.presenter.MVPPresenter;
import cn.shper.mvppandemo.model.MainModel;
import cn.shper.mvppandemo.net.entity.WeatherInfo;
import cn.shper.mvppandemo.view.MainActivity;
import cn.shper.okhttppan.callback.HttpCallback;
import cn.shper.okhttppan.constant.HttpError;

/**
 * Author: Shper
 * Description: MVPPan Demo main Presenter
 * Version: V0.1 2016/12/28
 */
public class MainPresenter extends MVPPresenter<MainActivity, MainModel> {

    public MainPresenter(MainActivity mvpView) {
        super(mvpView);
    }

    @Override
    public void onCreate() {
        // 绑定 Model
        attachModel(new MainModel(mView.getPresenter()));
    }

    public void getWeather(String cityId) {
        if (TextUtils.isEmpty(cityId)) {
            mView.showDialog("错误", "未选择城市");
            return;
        }
        // 获取天气详情数据
        mModel.getWeatherInfo(cityId, new HttpCallback<WeatherInfo>() {
            @Override
            public void onSuccess(WeatherInfo weatherInfo, int i) {
                mView.showToast("加载完成");
                mView.setWeatherInfo(weatherInfo.toString());
            }

            @Override
            public void onFail(HttpError httpError, int i) {
                mView.showToast("获取数据失败");
                mView.setWeatherInfo("error");
            }
        });
    }

}
