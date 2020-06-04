package com.hym.appstore.app;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.hjq.toast.ToastUtils;
import com.hym.appstore.dagger2.component.DaggerAppComponent;
import com.hym.appstore.dagger2.module.AppModule;
import com.xuexiang.xui.XUI;
import com.yanzhenjie.nohttp.InitializationConfig;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.RequestQueue;

import javax.inject.Inject;


public class MyApplication extends Application {


    private DaggerAppComponent mAppComponent;

//    @Inject
    RequestQueue mRequestQueue;


    public static MyApplication get(Context context){
        return (MyApplication) context.getApplicationContext();
    }

    public DaggerAppComponent getAppComponent(){
        return mAppComponent;
    }

    public RequestQueue getRequestQueue(){
        return mRequestQueue;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = (DaggerAppComponent) DaggerAppComponent.builder().appModule(new AppModule(this)).build();

        XUI.init(this); //初始化UI框架
        //XUI.debug(true);  //开启UI框架调试日志

        MultiDex.install(this);

        // 在 Application 中初始化
        ToastUtils.init(this);


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
