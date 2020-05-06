package com.grechur.net;

/**
 * Created by han on 2017/3/1.
 */

public interface RequestCallback<T> {
    void onNext(T t);
    void onError(ApiException e);
}
