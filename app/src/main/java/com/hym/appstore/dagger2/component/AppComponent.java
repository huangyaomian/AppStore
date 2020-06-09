package com.hym.appstore.dagger2.component;

import com.hym.appstore.dagger2.module.AppModule;
import com.hym.appstore.dagger2.module.HttpModule;
import com.hym.appstore.dagger2.module.QueueModule;
import com.hym.appstore.data.okhttp.ApiService;
import com.yanzhenjie.nohttp.rest.RequestQueue;

import javax.inject.Singleton;

import dagger.Component;



@Singleton
@Component(modules = {AppModule.class, QueueModule.class, HttpModule.class})
public interface AppComponent {
    public RequestQueue getQueue();

    public ApiService getApiService();
}
