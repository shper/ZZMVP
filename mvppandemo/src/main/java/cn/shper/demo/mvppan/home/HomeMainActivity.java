package cn.shper.demo.mvppan.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import cn.shper.demo.mvppan.R;
import cn.shper.pan.mvp.MVPActivity;

/**
 * Author: Shper
 * Description: MVPPan Demo Main Activity
 * Version: V0.1 2016/12/28
 */
public class HomeMainActivity extends MVPActivity<HomeMainPresenter> {

    @BindView(R.id.get_weather_force_btn)
    Button getWeatherForceBtn;
    @BindView(R.id.get_weather_btn)
    Button getWeatherBtn;
    @BindView(R.id.weather_info_txt)
    TextView weatherInfoTxt;

    @Override
    protected HomeMainPresenter initPresenter() {
        return new HomeMainPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_activity_main;
    }

    @Override
    protected void initVariables(@Nullable Bundle savedInstanceState) {
    }

    @Override
    protected void initListeners() {
    }

    @OnClick({R.id.get_weather_btn, R.id.get_weather_force_btn})
    public void onGetWeatherClick(View view) {
        boolean isForce = false;
        switch (view.getId()) {
            case R.id.get_weather_btn:
                isForce = false;
                break;
            case R.id.get_weather_force_btn:
                isForce = true;
                break;
        }

        // 模拟获取 已经获取了 杭州 城市ID
        // String cityId = cityTxt.getText();
        getPresenter().getWeather("101210101", isForce);
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

}
