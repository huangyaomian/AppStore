package com.hym.appstore.data.okhttp;

import com.hym.appstore.bean.RecommendBean2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiService {
//    http://112.124.22.238:8081/course_api/cniaoplay/featured?p={%22page%22:0}
    public static final String BASE_URL = "http:112.124.22.238:8081/course_api/cniaoplay/";

    /*@GET("featured")
    public Call<RecommendBean2> getApps(@Query("p") String jsonParam);*/

    @GET("featured")
    public Observable<RecommendBean2> getApps(@Query("p") String jsonParam);
}
