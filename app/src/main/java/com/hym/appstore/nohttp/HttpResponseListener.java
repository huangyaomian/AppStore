package com.hym.appstore.nohttp;

import android.content.Context;
import android.content.DialogInterface;

import com.hym.appstore.ui.widget.WaitDialog;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

public class HttpResponseListener<T> implements OnResponseListener<T> {

    private HttpListener<T> mListener;

    private WaitDialog mWaitDialog;

    private boolean isLoading;

    private Request<T> mRequest;

    public HttpResponseListener(Context context, HttpListener<T> mListener, Request<T> mRequest, boolean isLoading, boolean canCancel) {
        this.isLoading = isLoading;
        this.mRequest = mRequest;
        this.mListener = mListener;
        if (context != null) {
            mWaitDialog = new WaitDialog(context);
            mWaitDialog.setCancelable(canCancel);
            mWaitDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    mWaitDialog.cancel();
                }
            });
        }
    }

    @Override
    public void onStart(int what) {
        if (isLoading && mWaitDialog != null && !mWaitDialog.isShowing()) {
            mWaitDialog.show();
        }
    }

    @Override
    public void onSucceed(int what, Response<T> response) {
        if (mListener != null) {
            mListener.onSucceed(what, response);
        }
    }

    @Override
    public void onFailed(int what, Response<T> response) {
        if (mListener != null) {
            mListener.onFailed(what, response);
        }
    }

    @Override
    public void onFinish(int what) {
        if (isLoading && mWaitDialog != null && mWaitDialog.isShowing()) {
            mWaitDialog.cancel();
        }
    }
}
