package cn.shper.mvppandemo.home;

import android.text.TextUtils;

import cn.shper.mvppan.presenter.MVPPresenter;
import cn.shper.mvppandemo.network.entity.WeatherInfo;
import cn.shper.okhttppan.callback.HttpCallback;
import cn.shper.okhttppan.constant.HttpError;

/**
 * Author: Shper
 * Description: MVPPan Demo main Presenter
 * Version: V0.1 2016/12/28
 */
public class HomeMainPresenter extends MVPPresenter<HomeMainActivity> {

    public HomeMainPresenter(HomeMainActivity mvpView) {
        super(mvpView);
    }

    @Override
    public void onCreate() {
    }

    public void getWeather(String cityId, boolean force) {
        if (TextUtils.isEmpty(cityId)) {
            mView.showDialog("错误", "未选择城市");
            return;
        }
        // 获取天气详情数据
        HomeMainModel.getWeatherInfo(cityId, force, new HttpCallback<WeatherInfo>() {
            @Override
            public void onSuccess(WeatherInfo weatherInfo) {
                mView.showToast("加载完成");
                mView.setWeatherInfo(weatherInfo.toString());
            }

            @Override
            public void onFail(HttpError httpError) {
                mView.showToast("获取数据失败");
                mView.setWeatherInfo("error");
            }
        });
    }

}
