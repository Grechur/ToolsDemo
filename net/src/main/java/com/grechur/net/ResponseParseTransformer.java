package com.grechur.net;


import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

/**
 * Created by han on 2017/3/1.
 */

public class ResponseParseTransformer<T,R> implements ObservableTransformer<T,R> {
    private Function func1;

    /**
     * 使用BaseResponse类型的json格式解析
     */
    private ResponseParseTransformer(){
        func1=new BaseResponseParseFunc1();
    }

    /**
     * 传入自定义格式解析json
     * @param func1
     */
    private ResponseParseTransformer(Function func1){
        this.func1=func1;
        if(func1==null){
            this.func1=new BaseResponseParseFunc1();
        }

    }

    public static <R> ObservableTransformer<BaseResponse<R>,R> applyTransform(){
        return new ResponseParseTransformer<>();
    }

    public static <T,R> ObservableTransformer<T,R> applyTransform(Function<T,R> func1){
        return new ResponseParseTransformer<T,R>(func1);
    }

    @Override
    public ObservableSource<R> apply(Observable<T> upstream) {
        return upstream
                .compose(new ObservableTransformer<T, R>() {
                    @Override
                    public ObservableSource<R> apply(Observable<T> upstream) {
                        return upstream.map(func1).onErrorResumeNext(new Function<Throwable, ObservableSource>() {
                            @Override
                            public ObservableSource apply(Throwable throwable) throws Exception {
                                return Observable.error(ExceptionHandler.handleException(throwable) );
                            }
                        });
                    }

                });
    }
}
