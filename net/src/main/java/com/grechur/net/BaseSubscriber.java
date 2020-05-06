package com.grechur.net;
import org.reactivestreams.Subscription;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by han on 2017/3/1.
 */

public abstract class BaseSubscriber<T> implements Observer<T> {
    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onError(Throwable e) {
        onError(ExceptionHandler.handleException(e));
    }

    public abstract void onError(ApiException e);
}
