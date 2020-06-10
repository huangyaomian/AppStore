package com.hym.appstore.common.rx.subscriber;

import com.hym.appstore.common.exception.BaseException;
import com.hym.appstore.common.rx.RxErrorHandler;

public abstract class ErrorHandlerSubscriber<T> extends DefaultSubscriber<T> {


    private RxErrorHandler mRxErrorHandler;

    public ErrorHandlerSubscriber(RxErrorHandler mRxErrorHandler) {
        this.mRxErrorHandler = mRxErrorHandler;
    }

    @Override
    public void onError(Throwable t) {
        t.printStackTrace();
        BaseException exception = mRxErrorHandler.handleError(t);
        mRxErrorHandler.showErrorMsg(exception);
    }
}
