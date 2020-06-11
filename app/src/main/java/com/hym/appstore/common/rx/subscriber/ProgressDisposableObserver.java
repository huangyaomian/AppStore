package com.hym.appstore.common.rx.subscriber;

import android.content.Context;

import com.hym.appstore.common.exception.BaseException;
import com.hym.appstore.ui.BaseView;

public abstract class ProgressDisposableObserver<T> extends ErrorHandlerDisposableObserver<T> implements ProgressDialogHandler.OnProgressCancelListener {


    private BaseView mBaseView;

    public ProgressDisposableObserver(Context context, BaseView baseView) {
        super(context);
        this.mBaseView = baseView;
    }

    protected boolean isShowProgress(){
        return true;
    }

    @Override
    public void onCancelProgress() {
//        unsubscribe();
    }

//    protected abstract void unsubscribe();

    @Override
    protected void onStart() {
        if (isShowProgress()) {
            mBaseView.showLoading();
        }
    }

    @Override
    public void onComplete() {
           mBaseView.dismissLoading();
    }

    @Override
    public void onError(Throwable t) {
        BaseException baseException = mRxErrorHandler.handleError(t);
        mBaseView.showError(baseException.getDisplayMessage());
    }





}
