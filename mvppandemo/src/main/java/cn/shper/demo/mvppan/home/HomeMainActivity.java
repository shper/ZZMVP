package cn.shper.demo.mvppan.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import cn.shper.demo.mvppan.R;
import cn.shper.pan.mvp.MVPActivity;

/**
 * Author: Shper
 * Description: MVPPan Demo Main Activity
 * Version: V0.1 2016/12/28
 */
public class HomeMainActivity extends MVPActivity<HomeMainPresenter> {

    private Button getWeatherForceBtn;
    private Button getWeatherBtn;
    private TextView weatherInfoTxt;

    @Override
    protected HomeMainPresenter initPresenter() {
        return new HomeMainPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_activity_main;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        getWeatherForceBtn = (Button) findViewById(R.id.get_weather_force_btn);
        getWeatherBtn = (Button) findViewById(R.id.get_weather_btn);
        weatherInfoTxt = (TextView) findViewById(R.id.weather_info_txt);
    }

    @Override
    protected void initVariables(@Nullable Bundle savedInstanceState) {
    }

    @Override
    protected void initListeners() {
        getWeatherForceBtn.setOnClickListener(new GetWeatherListener(true));
        getWeatherBtn.setOnClickListener(new GetWeatherListener());
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    public void showDialog(String title, String msg) {
        new AlertDialog.Builder(this).setTitle(title).setMessage(msg).show();
    }

    /**
     * 显示 天气详情
     */
    public void setWeatherInfo(CharSequence weatherInfo) {
        weatherInfoTxt.setText(weatherInfo);
    }

    /**
     * 获取天气 Listener
     */
    private class GetWeatherListener implements View.OnClickListener {

        private boolean force = false;

        GetWeatherListener() {
            this(false);
        }

        GetWeatherListener(boolean force) {
            this.force = force;
        }

        @Override
        public void onClick(View v) {
            // 模拟获取 已经获取了 杭州 城市ID
            // String cityId = cityTxt.getText();
            getPresenter().getWeather("101210101", force);
        }
    }

}
