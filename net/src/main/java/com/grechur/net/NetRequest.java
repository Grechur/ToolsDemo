package com.grechur.net;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Created by lkl on 2017/3/11.
 */

public class NetRequest {
    @SerializedName("app_params")
    private Map<String, Object> map;

    public NetRequest(Map<String, Object> map) {
        this.map = map;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}
