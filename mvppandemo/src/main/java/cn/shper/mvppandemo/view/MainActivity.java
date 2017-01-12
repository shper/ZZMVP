package cn.shper.mvppandemo.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import cn.shper.mvppan.view.MVPActivity;
import cn.shper.mvppan.utils.Logger;
import cn.shper.mvppan.view.MVPView;
import cn.shper.mvppandemo.R;
import cn.shper.mvppandemo.presenter.MainPresenter;

/**
 * Author: Shper
 * Description: MVPPan Demo Main Activity
 * Version: V0.1 2016/12/28
 */
public class MainActivity extends MVPActivity<MainPresenter> implements MVPView {

    private Button getWeatherBtn;
    private TextView weatherInfoTxt;

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initVariables(@Nullable Bundle savedInstanceState) {
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        getWeatherBtn = (Button) findViewById(R.id.get_weather_btn);
        weatherInfoTxt = (TextView) findViewById(R.id.weather_info_txt);
    }

    @Override
    protected void initListeners() {
        getWeatherBtn.setOnClickListener(new GetWeatherListener());
    }

    @Override
    protected void loadDate() {

    }

    @Override
    public void showLoading() {
        Logger.d("开始Loading");
    }

    @Override
    public void hideLoading() {
        Logger.d("结束Loading");
    }

    private class GetWeatherListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 模拟获取 已经获取了 杭州 城市ID
            // String cityId = cityTxt.getText();
            mPresenter.getWeather("101210101");
        }
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    public void showDialog(String title, String msg) {
        new AlertDialog.Builder(this).setTitle(title).setMessage(msg).show();
    }

    public void setWeatherInfo(CharSequence weatherInfo) {
        weatherInfoTxt.setText(weatherInfo);
    }

}
