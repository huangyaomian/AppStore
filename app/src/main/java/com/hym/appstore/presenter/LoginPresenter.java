package com.hym.appstore.presenter;

import com.hym.appstore.bean.AppInfoBean;
import com.hym.appstore.bean.BaseBean;
import com.hym.appstore.bean.LoginBean;
import com.hym.appstore.bean.PageBean;
import com.hym.appstore.common.rx.Optional;
import com.hym.appstore.common.rx.RxHttpResponseCompat;
import com.hym.appstore.common.rx.subscriber.ErrorHandlerDisposableObserver;
import com.hym.appstore.common.rx.subscriber.ProgressDisposableObserver;
import com.hym.appstore.data.AppInfoModel;
import com.hym.appstore.presenter.contract.AppInfoContract;
import com.hym.appstore.presenter.contract.LoginContract;

import javax.inject.Inject;

import dagger.Module;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observers.DisposableObserver;

public class LoginPresenter extends BasePresenter<LoginContract.ILoginModel, LoginContract.LoginView> {


    @Inject
    public LoginPresenter(LoginContract.ILoginModel mModel, LoginContract.LoginView mView) {
        super(mModel, mView);
    }


    public void login(String phone,String pwd){
        if (!true){
            mView.checkPhoneError();
            return;
        }else {
            mView.checkPhoneSuccess();
        }

        mModel.login(phone,pwd).compose(RxHttpResponseCompat.compatResult())
                .subscribe(new ErrorHandlerDisposableObserver<LoginBean>(mContext) {
                    @Override
                    public void onNext(@NonNull LoginBean loginBean) {
                        mView.loginSuccess(loginBean);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
