package com.hym.appstore.common.rx.subscriber;

import com.hym.appstore.common.exception.BaseException;
import com.hym.appstore.common.rx.RxErrorHandler;

public abstract class ErrorHandlerDisposableObserver<T> extends DefaultDisposableObserver<T> {


    private RxErrorHandler mRxErrorHandler;

    public ErrorHandlerDisposableObserver(RxErrorHandler mRxErrorHandler) {
        this.mRxErrorHandler = mRxErrorHandler;
    }

    @Override
    public void onError(Throwable t) {
        t.printStackTrace();
        BaseException exception = mRxErrorHandler.handleError(t);
        mRxErrorHandler.showErrorMsg(exception);
    }
}
