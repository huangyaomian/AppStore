package com.hym.appstore.common.rx.subscriber;

import com.google.gson.JsonParseException;
import com.hym.appstore.common.exception.ApiException;
import com.hym.appstore.common.exception.BaseException;
import com.hym.appstore.common.exception.ErrorMessageFactory;
import com.hym.appstore.common.rx.RxErrorhandler;

import org.json.JSONException;

import java.net.SocketException;
import java.net.SocketTimeoutException;

import retrofit2.HttpException;

public abstract class ErrorHandlerSubscriber<T> extends DefaultSubscriber<T> {


    private RxErrorhandler mRxErrorhandler;

    public ErrorHandlerSubscriber(RxErrorhandler mRxErrorhandler) {
        this.mRxErrorhandler = mRxErrorhandler;
    }

    @Override
    public void onError(Throwable t) {
        BaseException exception = mRxErrorhandler.handleError(t);
        mRxErrorhandler.showErrorMsg(exception);
    }
}
