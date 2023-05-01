package com.mapabc.entity;

import com.alibaba.fastjson.JSONObject;
import io.lettuce.core.codec.StringCodec;
import io.lettuce.core.protocol.CommandArgs;

import java.io.Serializable;

/**
 * @Author ke.han
 * @Date 2019/11/18 14:14
 **/
public class Fence extends Element implements Serializable {

    private JSONObject fence;

    @Override
    public CommandArgs<String, String> getCommandArgs(String key, String member) {
        String geojson = JSONObject.toJSONString(fence);
        return new CommandArgs<>(StringCodec.UTF8).add(key).add(member).add("OBJECT").add(geojson);
    }

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
