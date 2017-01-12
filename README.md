# MvpPan

MvpPan 是一个 遵循 MVP 和 单一职能 思想的框架。

## Example

### View 层
Activity 继承 MVPActivity，Fragment 继承 MVPFragment

```java
public class HomeMainActivity extends MVPActivity<HomeMainPresenter> {

    // 绑定 Presenter，让Presenter 的 什么周期 和 Activity 一致
    @Override
    protected HomeMainPresenter initPresenter() {
        return new HomeMainPresenter(this);
    }

    // 下面是 分解的 onCreate
    // 设置 Layout
    @Override
    protected int getLayoutId() {
        return R.layout.home_activity_main;
    }

    // 初始化 控件
    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
    }

    // 初始化 变量
    @Override
    protected void initVariables(@Nullable Bundle savedInstanceState) {
    }

    // 设置 Listener
    @Override
    protected void initListeners() {
    }

    // 加载数据
    @Override
    protected void loadDate() {
        // 例子
        mPresenter.loadDate("XXXX");
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }

    public void updateUi(){
        // .....
    }
}
```

### Presenter 层

```java
public class HomeMainPresenter extends MVPPresenter<HomeMainActivity, HomeMainModel> {

    // 构造器 并且绑定 View 层
    public HomeMainPresenter(HomeMainActivity mvpView) {
        super(mvpView);
    }

    // 绑定 Model 层
    @Override
    public HomeMainModel initModel() {
        return new HomeMainModel(this);
    }

    // 例子加载数据
    public void loadData(String string) {
        mView.showLoading();

        if (TextUtils.isEmpty(String)){
            mView.hideLoading();
            mView.showToast("获取数据失败");
            mView.updateUi("error");
            return;
        }

        mModel.getNetworkDataInfo(new HttpCallback() {
            @Override
            public void onSuccess(Object obj) {
                mView.hideLoading();
                mView.showToast("加载完成");
                mView.updateUi(obj.toString());
            }

            @Override
            public void onFail(HttpError httpError) {
                mView.hideLoading();
                mView.showToast("获取数据失败");
                mView.updateUi("error");
            }
        });
    }

}
```

### Model 层

```java
public class HomeMainModel extends MVPModel<HomeMainPresenter> {

    // 构造器 并且绑定 View 层
    public HomeMainModel(HomeMainPresenter mvpPresenter) {
        super(mvpPresenter);
    }

    // 例子
    public void getNetworkDataInfo(final HttpCallback<WeatherInfo> callback) {
        // 规定时间内重复求请，直接读取本地缓存数据
        if (System.currentTimeMillis() - SharedPreferencesUtil.getLong(Constants.KEY_UPDATE_TIME) < Constants.CACHE_TIME) {
            Object obj = CacheUtils.getData();
            // 返回数据
            callback.onSuccess(obj);
        } else {
            HomeApiStore.getNetworkData(new HttpCallback<WeatherInfo>() {
                @Override
                public void onSuccess(Object object) {
                    // 数据获取成功后 保存一份本地缓存
                    CacheUtils.saveData(object);
                    // 返回数据
                    callback.onSuccess(object);
                }

                @Override
                public void onFail(HttpError httpError) {
                    callback.onFail(httpError);
                }
            });

        }
    }

}

```

查看例子源码 >> MvpPanDemo

## License

```
Copyright 1999-2017 Shper.cn Holding Ltd.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```