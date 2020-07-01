package com.hym.appstore.common.rx;


import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;

/**
 * 菜鸟窝http://www.cniao5.com 一个高端的互联网技能学习平台
 *
 * @author Ivan
 * @version V1.0
 * @Package com.cniao5.cniao5market.common.util.rx
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date
 */

public class RxBus {

    private static volatile RxBus defaultInstance;

//    private final Subject<Object, Object> bus;
//    // PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者
//    public RxBus() {
//        bus = new SerializedSubject<>(PublishSubject.create());
//    }
    // 单例RxBus
    public static RxBus getDefault() {
        if (defaultInstance == null) {
            synchronized (RxBus.class) {
                if (defaultInstance == null) {
                    defaultInstance = new RxBus();
                }
            }
        }
        return defaultInstance ;
    }

//    // 发送一个新的事件
//    public void post (Object o) {
//        bus.onNext(o);
//    }
//    // 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
//    public <T> Observable<T> toObservable (Class<T> eventType) {
//        return bus.ofType(eventType);
//
//    }


    private final Subject<Object> mBus;

    private RxBus() {
        // toSerialized method made bus thread safe
        mBus = PublishSubject.create().toSerialized();
    }



    public void post(Object obj) {
        mBus.onNext(obj);
    }

    public <T> Observable<T> toObservable(Class<T> tClass) {
        return mBus.ofType(tClass);
    }

    public Observable<Object> toObservable() {
        return mBus;
    }

    public boolean hasObservers() {
        return mBus.hasObservers();
    }
}
