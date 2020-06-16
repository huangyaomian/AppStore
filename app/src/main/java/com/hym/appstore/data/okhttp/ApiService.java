package com.hym.appstore.data.okhttp;

import com.hym.appstore.bean.AppInfoBean;
import com.hym.appstore.bean.BaseBean;
import com.hym.appstore.bean.HomeBean;
import com.hym.appstore.bean.PageBean;
import com.hym.appstore.bean.requestbean.LoginRequestBean;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
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

    //http://112.124.22.238:8081/course_api/cniaoplay/index?p={"publicParams":{"model":"12","imei":"12","la":"12","os":"12","resolution":"12","sdk":"12","densityScaleFactor":"12"}}
    @GET("index")
    public Observable<BaseBean<HomeBean>> getHome();

    @GET("toplist")
    public Observable<BaseBean<PageBean<AppInfoBean>>> getTopList(@Query("page") int page);

    @POST("login")
    public Observable<BaseBean> login(@Body LoginRequestBean bean);
}
