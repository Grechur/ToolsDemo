package com.grechur.net;

import com.google.gson.annotations.SerializedName;

/**
 * Created by han on 2017/3/1.
 */

public class BaseRequest<T> {
    @SerializedName("app_params")
    private T requestBody;

    public BaseRequest(T body){
        requestBody=body;
    }

    public T getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(T requestBody) {
        this.requestBody = requestBody;
    }
}
