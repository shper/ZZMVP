package cn.shper.demo.mvp.home;

import android.text.TextUtils;

import cn.shper.demo.mvp.network.entity.WeatherInfo;
import cn.shper.okhttppan.callback.HttpCallback;
import cn.shper.okhttppan.constant.HttpError;
import cn.shper.mvp.MVPActivityPresenter;

/**
 * Author: Shper
 * Description: MVPPan Demo main Presenter
 * Version: V0.1 2016/12/28
 */
public class HomeMainPresenter extends MVPActivityPresenter<HomeMainActivity> {

    public HomeMainPresenter(HomeMainActivity mvpView) {
        super(mvpView);
    }

    @Override
    public void onCreate() {
    }

    public void getWeather(String cityId, boolean force) {
        if (TextUtils.isEmpty(cityId)) {
            getActivity().showDialog("错误", "未选择城市");
            return;
        }
        // 获取天气详情数据
        HomeMainModel.getWeatherInfo(cityId, force, new HttpCallback<WeatherInfo>() {
            @Override
            public void onSuccess(WeatherInfo weatherInfo) {
                getActivity().showToast("加载完成");
                getActivity().setWeatherInfo(weatherInfo.toString());
            }

            @Override
            public void onFail(HttpError httpError) {
                getActivity().showToast("获取数据失败");
                getActivity().setWeatherInfo("error");
            }
        });
    }

}
