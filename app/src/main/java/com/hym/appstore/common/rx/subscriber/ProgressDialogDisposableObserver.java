package com.hym.appstore.common.rx.subscriber;

import android.content.Context;

import com.hym.appstore.common.rx.RxErrorHandler;
import com.hym.appstore.ui.BaseView;
import com.hym.appstore.ui.widget.WaitDialog;

import java.util.ConcurrentModificationException;

public abstract class ProgressDialogDisposableObserver<T> extends ErrorHandlerDisposableObserver<T> implements ProgressDialogHandler.OnProgressCancelListener {


    private ProgressDialogHandler mProgressDialogHandler;

    public ProgressDialogDisposableObserver(Context context) {
        super(context);
        mProgressDialogHandler = new ProgressDialogHandler(mContext,true,this);
    }

    protected boolean isShowProgressDialog(){
        return true;
    }

    @Override
    public void onCancelProgress() {
//        unsubscribe();
    }

//    protected abstract void unsubscribe();

    @Override
    protected void onStart() {
        if (isShowProgressDialog()) {
            this.mProgressDialogHandler.showProgressDialog();
        }

    }

    @Override
    public void onComplete() {
        if (isShowProgressDialog()) {
            this.mProgressDialogHandler.dismissProgressDialog();
        }

    }

    @Override
    public void onError(Throwable t) {
        super.onError(t);
        if (isShowProgressDialog()) {
            this.mProgressDialogHandler.dismissProgressDialog();
        }
    }





}
