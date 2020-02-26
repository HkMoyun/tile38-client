package com.mapabc.entity;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * @Author ke.han
 * @Date 2019/11/18 14:14
 **/
public class Fence extends Element implements Serializable {

    private JSONObject fence;

    public Fence() {}

    public Fence(JSONObject fence) {
        this.fence = fence;
    }

    public JSONObject getFence() {
        return fence;
    }

    public void setFence(JSONObject fence) {
        this.fence = fence;
    }

}
