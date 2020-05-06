package com.grechur.net;


import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;

/**
 * Created by han on 2017/3/1.
 */

public class BaseTransformer<T extends BaseResponse<R>,R> implements ObservableTransformer<T,R> {
//    @Override
//    public Observable<R> call(Observable<T> tObservable) {
//        return tObservable.compose(SchedulersCompat.<T>applyIoSchedulers())
//                .compose(ResponseParseTransformer.<R>applyTransform());
//    }

    public static <R> ObservableTransformer<? super BaseResponse<R>,R> applyTransform(){
        return new BaseTransformer<>();
    }

    @Override
    public ObservableSource<R> apply(Observable<T> upstream) {
        return upstream.compose(SchedulersCompat.<T>applyIoSchedulers())
                .compose(ResponseParseTransformer.<R>applyTransform());
    }
}
