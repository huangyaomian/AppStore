package com.hym.appstore.common.rx.subscriber;

import com.hym.appstore.common.rx.RxErrorHandler;
import com.hym.appstore.ui.BaseView;
import com.hym.appstore.ui.widget.WaitDialog;

public abstract class progressDialogDisposableObserver<T> extends ErrorHandlerDisposableObserver<T>{


    private WaitDialog mWaitDialog;
    private BaseView mBaseView;

    public progressDialogDisposableObserver(BaseView view, RxErrorHandler mRxErrorHandler) {
        super(mRxErrorHandler);
        this.mBaseView = view;
    }

    protected boolean isShowDialog(){
        return true;
    }

    @Override
    protected void onStart() {
        if (isShowDialog()) {
            showProgressDialog();
        }

    }

    @Override
    public void onComplete() {
        if (isShowDialog()) {
            dismissProgressDialog();
        }

    }

    @Override
    public void onError(Throwable t) {
        super.onError(t);
        if (isShowDialog()) {
            dismissProgressDialog();
        }
    }


    private void showProgressDialog(){
      /*  initProgressDialog();
        mWaitDialog.show();*/
      mBaseView.showLoading();
    }

    private void dismissProgressDialog(){
//        mWaitDialog.dismiss();
        mBaseView.dismissLoading();
    }


}
