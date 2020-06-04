package com.hym.appstore.dagger2.component;

import com.hym.appstore.dagger2.module.AppModule;
import com.hym.appstore.dagger2.module.QueueModule;
import com.yanzhenjie.nohttp.rest.RequestQueue;

import javax.inject.Singleton;

import dagger.Component;



@Singleton
@Component(modules = {AppModule.class, QueueModule.class})
public interface AppComponent {
    public RequestQueue getQueue();
}
