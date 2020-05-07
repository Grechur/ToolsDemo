package com.grechur.net;

import com.google.gson.annotations.SerializedName;

/**
 * Created by han on 2017/3/1.
 */

public class BaseResponse<T> {
    public boolean isSuccess() {
        return errorCode == 0;
    }
    @SerializedName("data")
    public T data;

    @SerializedName("errorCode")
    public int errorCode;

    @SerializedName("errorMsg")
    public String errorMsg;
}
