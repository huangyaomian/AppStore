package com.hym.appstore.data.nohttp;

import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestHandler;
import com.yanzhenjie.nohttp.rest.Response;

public class Interceptor implements com.yanzhenjie.nohttp.rest.Interceptor {
    @Override
    public <T> Response<T> intercept(RequestHandler handler, Request<T> request) {
        request.getRequestMethod();
        return null;
    }
}
