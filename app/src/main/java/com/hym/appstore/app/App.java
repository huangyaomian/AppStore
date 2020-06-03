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


       /* X-Os-Version-Name: 9
        X-Locale: zh_HK
        X-Patch-Code: 48
        X-Version-Code: 307
        X-Version-Name: 8.0.7
        X-Device-Id: c2306fac-1064-4304-bc07-a44ee9478072
        X-Device-OS: 9
        X-ADID: 8c8aaebb-f25a-4a7a-9c94-f7691fbe0c24
        X-SDK-Version: 28
        X-System-Locale: zh_HK
        X-Device-Rooted: 0
        X-Device-Model: SM-G950U
        X-SA-Distinct-Id:
        X-Device-ABIs: arm64-v8a,armeabi-v7a,armeabi
        X-Device-UUID: c2306fac-1064-4304-bc07-a44ee9478072
        X-User-Token: 6d37a4e107faa97d5b966e6f6d4464ec5fcc1a1d
        X-Android-Id: c8c79909485d075e
        Accept: application/json
        Content-Type: application/json; charset=utf-8
        Host: testing-api.qoo-app.com
        Connection: Keep-Alive
        Accept-Encoding: gzip
        User-Agent: okhttp/3.12.0
        If-Modified-Since: Tue, 02 Jun 2020 11:25:34 GMT*/

        //nohttp
        InitializationConfig initializationConfig = InitializationConfig.newBuilder(this)
                .addHeader("X-System-Locale","zh_HK")
                .addHeader("X-Locale","zh_HK")
                .addHeader("X-Patch-Code","48")
                .addHeader("X-Version-Code","307")
                .addHeader("X-Device-Id","c2306fac-1064-4304-bc07-a44ee9478072")
                .addHeader("X-Device-OS","9")
                .addHeader("X-ADID","8c8aaebb-f25a-4a7a-9c94-f7691fbe0c24")
                .addHeader("X-Device-Rooted","0")
                .addHeader("X-Device-Model","SM-G950U")
                .addHeader("X-Device-ABIs","arm64-v8a,armeabi-v7a,armeabi")
                .addHeader("X-Device-UUID","c2306fac-1064-4304-bc07-a44ee9478072")
                .addHeader("X-User-Token","6d37a4e107faa97d5b966e6f6d4464ec5fcc1a1d")
                .addHeader("Accept","application/json")
                .addHeader("Content-Type","application/json; charset=utf-8")
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
