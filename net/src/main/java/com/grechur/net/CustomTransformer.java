package com.grechur.net;


import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

/**
 * Created by han on 2017/6/28.
 */

public class CustomTransformer<T,R> implements ObservableTransformer<T,R> {
    private Function<T,R> mFunc1;

//    @Override
//    public Observable<R> call(Observable<T> tObservable) {
//        return tObservable.compose(SchedulersCompat.<T>applyIoSchedulers())
//                .compose(ResponseParseTransformer.applyTransform(mFunc1));
//    }

    private CustomTransformer(Function<T,R> func1){
        this.mFunc1 = func1;
    }

    public static <T,R> ObservableTransformer<T,R> applyTransform(Function<T,R> func1){
        return new CustomTransformer<>(func1);
    }

    @Override
    public ObservableSource<R> apply(Observable<T> upstream) {
        return upstream.compose(SchedulersCompat.<T>applyIoSchedulers())
                .compose(ResponseParseTransformer.applyTransform(mFunc1));
    }
}
