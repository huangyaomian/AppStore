package com.hym.appstore.common;

import android.content.Context;

import com.google.gson.Gson;
import com.hym.appstore.bean.requestbean.Constant;
import com.hym.appstore.common.utils.DeviceUtil;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CommonParamsInterceptor implements Interceptor {

    private Gson mGson;
    private Context mContext;

    public CommonParamsInterceptor(Context context, Gson mGson) {
        this.mGson = mGson;
        this.mContext = context;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {

        HashMap<String,Object> commonParamsMap = new HashMap<>();
        commonParamsMap.put(Constant.IMEI,"");
        commonParamsMap.put(Constant.LANGUAGE, DeviceUtil.getSystemLanguage());
        commonParamsMap.put(Constant.OS,DeviceUtil.getSystemVersion());
        commonParamsMap.put(Constant.RESOLUTION,"");
        commonParamsMap.put(Constant.SDK,"");
        commonParamsMap.put(Constant.IMEI,"");

        Request request = chain.request();

        String method = request.method();

        if (method.equals("GET")) {
            HttpUrl httpUrl = request.url();
            HashMap<String,Object> rootMap = new HashMap<>();
            Set<String> paramNames = httpUrl.queryParameterNames();
            for (String key : paramNames){
                if (Constant.PARAM.equals(key)) {
                    String oldParamJson = httpUrl.queryParameter(Constant.PARAM);
                    if (oldParamJson != null) {
                        HashMap<String,Object> p = mGson.fromJson(oldParamJson, HashMap.class);//原始参数
                        if (p != null) {
                            for (Map.Entry<String,Object> entry : p.entrySet()){
                                rootMap.put(entry.getKey(),entry.getValue());
                            }
                        }
                    }
                }else {
                    rootMap.put(key,httpUrl.queryParameter(key));
                }
            }

            rootMap.put("publicParams",commonParamsMap);//重新组装
            String newJsonParams = mGson.toJson(rootMap);//组装好的参数

            String url = httpUrl.toString();
            int index = url.indexOf("?");
            if (index > 0) {
                url = url.substring(0,index);
            }
            url = url + "?" + Constant.PARAM + "=" + newJsonParams;
            request = request.newBuilder().url(url).build();

        }else if (method.equals("POST")){

        }

        return chain.proceed(request);
    }
}
