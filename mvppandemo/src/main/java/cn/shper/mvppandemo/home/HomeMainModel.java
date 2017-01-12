package cn.shper.mvppandemo.home;

import cn.shper.mvppan.model.MVPModel;
import cn.shper.mvppandemo.common.constants.Constants;
import cn.shper.mvppandemo.network.apistore.HomeApiStore;
import cn.shper.mvppandemo.network.entity.WeatherInfo;
import cn.shper.mvppandemo.common.utils.SharedPreferencesUtil;
import cn.shper.okhttppan.callback.HttpCallback;
import cn.shper.okhttppan.constant.HttpError;

/**
 * Author: Shper
 * Description: MVPPan Demo main Model
 * Version: V0.1 2016/12/28
 */
public class HomeMainModel extends MVPModel<HomeMainPresenter> {

    public HomeMainModel(HomeMainPresenter mvpPresenter) {
        super(mvpPresenter);
    }

    /**
     * @param cityId
     * @param callback
     */
    public void getWeatherInfo(String cityId, boolean force, final HttpCallback<WeatherInfo> callback) {
        // 规定时间内重复求请，直接读取本地缓存数据
        if (System.currentTimeMillis() - SharedPreferencesUtil.getLong(Constants.KEY_UPDATE_TIME) < Constants.CACHE_TIME && !force) {
            WeatherInfo weatherInfo = new WeatherInfo();
            weatherInfo.setUpdateTime(SharedPreferencesUtil.getLong(Constants.KEY_UPDATE_TIME));
            weatherInfo.setCity(SharedPreferencesUtil.getString(Constants.KEY_CITY));
            weatherInfo.setWD(SharedPreferencesUtil.getString(Constants.KEY_WD));
            weatherInfo.setWS(SharedPreferencesUtil.getString(Constants.KEY_WS));
            weatherInfo.setSD(SharedPreferencesUtil.getString(Constants.KEY_SD));
            weatherInfo.setWSE(SharedPreferencesUtil.getString(Constants.KEY_WSE));
            weatherInfo.setTime(SharedPreferencesUtil.getString(Constants.KEY_TIME));
            // 返回数据
            callback.onSuccess(weatherInfo);
        } else {
            HomeApiStore.getWeather(cityId, new HttpCallback<WeatherInfo>() {
                @Override
                public void onSuccess(WeatherInfo weatherInfo) {
                    // 数据获取成功后 保存一份本地缓存
                    SharedPreferencesUtil.save(Constants.KEY_CITY, weatherInfo.getCity());
                    SharedPreferencesUtil.save(Constants.KEY_WD, weatherInfo.getWD());
                    SharedPreferencesUtil.save(Constants.KEY_WS, weatherInfo.getWS());
                    SharedPreferencesUtil.save(Constants.KEY_SD, weatherInfo.getSD());
                    SharedPreferencesUtil.save(Constants.KEY_WSE, weatherInfo.getWSE());
                    SharedPreferencesUtil.save(Constants.KEY_TIME, weatherInfo.getTime());
                    // 更新 weatherInfo 更新时间
                    long updateTime = System.currentTimeMillis();
                    SharedPreferencesUtil.save(Constants.KEY_UPDATE_TIME, updateTime);
                    weatherInfo.setUpdateTime(updateTime);

                    // 返回数据
                    callback.onSuccess(weatherInfo);
                }

                @Override
                public void onFail(HttpError httpError) {
                    callback.onFail(httpError);
                }
            });

        }
    }

}
