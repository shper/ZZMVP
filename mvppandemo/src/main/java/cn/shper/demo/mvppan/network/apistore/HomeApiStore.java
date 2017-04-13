package cn.shper.demo.mvppan.network.apistore;

import cn.shper.demo.mvppan.network.entity.WeatherInfo;
import cn.shper.okhttppan.OkHttpPan;
import cn.shper.okhttppan.callback.HttpCallback;

/**
 * Author: Shper
 * Description: Main Api
 * Version: V0.1 2016/12/28
 */
public class HomeApiStore {

    private static final String WEATHER_URL = "http://www.weather.com.cn/adat/sk/";

    /**
     * 获取天气详情
     */
    public static void getWeather(String cityId, HttpCallback callback) {
        OkHttpPan.get().url(WEATHER_URL + cityId + ".html").jsonDataKey("weatherinfo")
                .build().execute(WeatherInfo.class, callback);
    }

}
