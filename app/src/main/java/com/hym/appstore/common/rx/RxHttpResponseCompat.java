package com.hym.appstore.common.rx;


import com.hym.appstore.bean.BaseBean;
import com.hym.appstore.common.exception.ApiException;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RxHttpResponseCompat {

    public static <T> ObservableTransformer<BaseBean<T>,T> compatResult(){
        return new ObservableTransformer<BaseBean<T>, T>() {
            @Override
            public @NonNull ObservableSource<T> apply(@NonNull Observable<BaseBean<T>> upstream) {
                return upstream.flatMap(new Function<BaseBean<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(BaseBean<T> tBaseBean) throws Throwable {
                        if (tBaseBean.success()) {
                           return Observable.create(new ObservableOnSubscribe<T>() {
                                @Override
                                public void subscribe(@NonNull ObservableEmitter<T> emitter) throws Throwable {
                                    try {
                                        emitter.onNext(tBaseBean.getData());
                                        emitter.onComplete();
                                    }catch (Exception e){
                                        emitter.onError(e);
                                    }

                                }
                            });
                        }else {
                            return Observable.error(new ApiException(tBaseBean.getStatus(),tBaseBean.getMessage()));
                        }
                    }
                }).observeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread());
            }
        };

    }
}
