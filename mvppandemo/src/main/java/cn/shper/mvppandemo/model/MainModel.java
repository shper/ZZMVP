package cn.shper.mvppandemo.model;

import cn.shper.mvppan.model.MVPModel;
import cn.shper.mvppandemo.apistore.MainApiStore;
import cn.shper.mvppandemo.net.entity.WeatherInfo;
import cn.shper.mvppandemo.presenter.MainPresenter;
import cn.shper.mvppandemo.utils.SharedPreferencesUtil;
import cn.shper.okhttppan.callback.HttpCallback;
import cn.shper.okhttppan.constant.HttpError;

/**
 * Author: Shper
 * Description: MVPPan Demo main Model
 * Version: V0.1 2016/12/28
 */
public class MainModel extends MVPModel<MainPresenter> {

    private static final String KEY_CITY = "city";
    private static final String KEY_WD = "wd";
    private static final String KEY_WS = "ws";
    private static final String KEY_SD = "sd";
    private static final String KEY_WSE = "wse";
    private static final String KEY_TIME = "time";
    private static final String KEY_UPDATE_TIME = "update_time";

    private static final int UPDATE_TIME = 5 * 60 * 1000;

    public MainModel(MainPresenter mvpPresenter) {
        super(mvpPresenter);
    }

    /**
     * @param cityId
     * @param callback
     */
    public void getWeatherInfo(String cityId, final HttpCallback<WeatherInfo> callback) {
        // 规定时间内重复求请，直接读取本地缓存数据
        if (System.currentTimeMillis() - SharedPreferencesUtil.getLong(KEY_UPDATE_TIME) < UPDATE_TIME) {
            WeatherInfo weatherInfo = new WeatherInfo();
            weatherInfo.setUpdateTime(SharedPreferencesUtil.getLong(KEY_UPDATE_TIME));
            weatherInfo.setCity(SharedPreferencesUtil.getString(KEY_CITY));
            weatherInfo.setWD(SharedPreferencesUtil.getString(KEY_WD));
            weatherInfo.setWS(SharedPreferencesUtil.getString(KEY_WS));
            weatherInfo.setSD(SharedPreferencesUtil.getString(KEY_SD));
            weatherInfo.setWSE(SharedPreferencesUtil.getString(KEY_WSE));
            weatherInfo.setTime(SharedPreferencesUtil.getString(KEY_TIME));
            // 返回数据
            callback.onSuccess(weatherInfo, 0);
        } else {
            MainApiStore.getWeather(cityId, new HttpCallback<WeatherInfo>() {
                @Override
                public void onSuccess(WeatherInfo weatherInfo, int i) {
                    // 数据获取成功后 保存一份本地缓存
                    SharedPreferencesUtil.save(KEY_CITY, weatherInfo.getCity());
                    SharedPreferencesUtil.save(KEY_WD, weatherInfo.getWD());
                    SharedPreferencesUtil.save(KEY_WS, weatherInfo.getWS());
                    SharedPreferencesUtil.save(KEY_SD, weatherInfo.getSD());
                    SharedPreferencesUtil.save(KEY_WSE, weatherInfo.getWSE());
                    SharedPreferencesUtil.save(KEY_TIME, weatherInfo.getTime());
                    // 更新 weatherInfo 更新时间
                    long updateTime = System.currentTimeMillis();
                    SharedPreferencesUtil.save(KEY_UPDATE_TIME, updateTime);
                    weatherInfo.setUpdateTime(updateTime);

                    // 返回数据
                    callback.onSuccess(weatherInfo, i);
                }

                @Override
                public void onFail(HttpError httpError, int i) {
                    callback.onFail(httpError, i);
                }
            });

        }
    }

}
