package com.grechur.net;

import com.google.gson.annotations.SerializedName;

/**
 * @ProjectName: ToolsDemo
 * @ClassName: OtherResponse
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/5/6 17:47
 */
public class OtherResponse<T> extends BaseResponse<T> {
    @SerializedName("heads")
    public Heads heads;

    @SerializedName("body")
    public T body;

    public boolean isSuccess() {
        return heads.code == 200;
    }

    public static final class Heads {
        @SerializedName("message")
        public String message;
        @SerializedName("code")
        public int code;
        public String errorMsg;
    }
}
