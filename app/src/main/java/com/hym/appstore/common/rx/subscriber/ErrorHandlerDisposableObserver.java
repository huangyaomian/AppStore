package com.hym.appstore.common.rx.subscriber;

import android.content.Context;
import android.util.Log;

import com.hym.appstore.common.exception.BaseException;
import com.hym.appstore.common.rx.RxErrorHandler;

public abstract class ErrorHandlerDisposableObserver<T> extends DefaultDisposableObserver<T> {


    protected RxErrorHandler mRxErrorHandler = null;
    protected Context mContext;

    public ErrorHandlerDisposableObserver(Context context) {
        this.mContext = context;
        mRxErrorHandler = new RxErrorHandler(mContext);
    }

    @Override
    public void onError(Throwable t) {
        BaseException baseException = mRxErrorHandler.handleError(t);

        if (baseException == null) {
            t.printStackTrace();
            Log.d("ErrorHandlerDO",t.getMessage());
        }else {
            mRxErrorHandler.showErrorMsg(baseException);
        }


    }
}
