package com.grechur.net;

import com.google.gson.annotations.SerializedName;

/**
 * Created by han on 2017/3/1.
 */

public class BaseWanResponse<T> extends BaseResponse<T>{
    @SerializedName("data")
    public T data;

    @SerializedName("errorCode")
    public int errorCode;

    @SerializedName("errorMsg")
    public String errorMsg;

}
