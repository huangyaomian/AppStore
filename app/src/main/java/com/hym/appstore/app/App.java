package com.hym.appstore.app;

import android.app.Application;

import androidx.multidex.MultiDex;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.hjq.toast.ToastUtils;
import com.xuexiang.xui.XUI;
import com.yanzhenjie.nohttp.InitializationConfig;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;


public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        XUI.init(this); //初始化UI框架
        //XUI.debug(true);  //开启UI框架调试日志

        MultiDex.install(this);

        // 在 Application 中初始化
        ToastUtils.init(this);

        //nohttp
        InitializationConfig initializationConfig = InitializationConfig.newBuilder(this)
                .addHeader("X-Bmob-Application-Id","596a1dc9e9617aedee7505a214dc30b9")
                .addHeader("X-Bmob-REST-API-Key","ebea31aa16b2c1bb1045d9eb967e4494")
                .addHeader("Content-Typ","application/json")
                .connectionTimeout(30*1000)
                .readTimeout(30*1000)
                .build();
        NoHttp.initialize(initializationConfig);
        Logger.setDebug(true);// 开启NoHttp的调试模式, 配置后可看到请求过程、日志和错误信息。
        Logger.setTag("NoHttpSample");// 打印Log的tag。

        //初始化fresco
        Fresco.initialize(this);

    }
}
