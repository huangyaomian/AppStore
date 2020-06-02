package com.hym.appstore.app;

import android.app.Application;

import androidx.multidex.MultiDex;

import com.hjq.toast.ToastUtils;
import com.xuexiang.xui.XUI;


public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        XUI.init(this); //初始化UI框架
        //XUI.debug(true);  //开启UI框架调试日志

        MultiDex.install(this);

        // 在 Application 中初始化
        ToastUtils.init(this);

    }
}
