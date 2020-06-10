package com.hym.appstore.data.okhttp;

import com.hym.appstore.bean.AppInfoBean;
import com.hym.appstore.bean.BaseBean;
import com.hym.appstore.bean.PageBean;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
//    http://112.124.22.238:8081/course_api/cniaoplay/featured?p={%22page%22:0}
    public static final String BASE_URL = "http:112.124.22.238:8081/course_api/cniaoplay/";

    /*@GET("featured")
    public Call<RecommendBean2> getApps(@Query("p") String jsonParam);*/

    @GET("featured")
    public Observable<BaseBean<PageBean<AppInfoBean>>> getApps(@Query("p") String jsonParam);
}
