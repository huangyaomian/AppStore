package com.hym.appstore.common;

import android.content.Context;

import com.google.gson.Gson;
import com.hym.appstore.bean.requestbean.Constant;
import com.hym.appstore.common.utils.DeviceUtil;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;

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
            HttpUrl url = request.url();
            String oldParamJson = url.queryParameter(Constant.PARAM);
            HashMap<String,Object> hashMap = mGson.fromJson(oldParamJson, HashMap.class);
        }else if (method.equals("POST")){

        }

        return null;
    }
}
