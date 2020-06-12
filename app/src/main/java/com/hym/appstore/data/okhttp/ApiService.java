package com.hym.appstore.data.okhttp;

import com.hym.appstore.bean.AppInfoBean;
import com.hym.appstore.bean.BaseBean;
import com.hym.appstore.bean.PageBean;
import com.hym.appstore.bean.requestbean.LoginRequestBean;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
//    http://112.124.22.238:8081/course_api/cniaoplay/featured?p={%22page%22:0}
    String BASE_URL = "http:112.124.22.238:8081/course_api/cniaoplay/";

    /*@GET("featured")
    public Call<RecommendBean2> getApps(@Query("p") String jsonParam);*/

    @GET("featured")
    public Observable<BaseBean<List<AppInfoBean>>> getApps(@Query("p") String jsonParam);

    @POST("login")
    public Observable<BaseBean> login(@Body LoginRequestBean bean);
}
