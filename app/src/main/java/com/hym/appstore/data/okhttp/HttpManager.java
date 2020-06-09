package com.hym.appstore.data.okhttp;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {
    public OkHttpClient getOkHttpClient(){
        //log用拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        //开发模式记录整个boby，否则只记录基本信息如返回200，http协议版本等
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                //headinterceptor实现了interceptor，用来往request header 添加一些业务相关的数据，如app版本等，token信息
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)//连接超时时间
                .readTimeout(10,TimeUnit.SECONDS)//读取超时时间
                .build();

    }

    public Retrofit getRetrofit(OkHttpClient okHttpClient){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient);

        return builder.build();
    }
}
