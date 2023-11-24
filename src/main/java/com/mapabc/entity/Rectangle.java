package com.mapabc.entity;


import com.alibaba.fastjson.JSONObject;
import io.lettuce.core.codec.StringCodec;
import io.lettuce.core.protocol.CommandArgs;

import java.io.Serializable;

/**
 * @Author ke.han
 * @Date 2019/11/14 15:29
 **/
public class Rectangle extends Element implements Serializable {

    private Point leftDown;
    private Point rightUpper;

    @Override
    public CommandArgs<String, String> getCommandArgs(String key, String member) {
        double lng1 = leftDown.getLng();
        double lat1 = leftDown.getLat();
        double lng2 = rightUpper.getLng();
        double lat2 = rightUpper.getLat();
        return new CommandArgs<>(StringCodec.UTF8).add(key).add(member).add("BOUNDS").add(lng1).add(lat1).add(lng2).add(lat2);
    }

    public Rectangle() {}

    public Rectangle(Point leftDown, Point rightUpper) {
        this.leftDown = leftDown;
        this.rightUpper = rightUpper;
    }

    public Point getLeftDown() {
        return leftDown;
    }

    public void setLeftDown(Point leftDown) {
        this.leftDown = leftDown;
    }

    public Point getRightUpper() {
        return rightUpper;
    }

    public void setRightUpper(Point rightUpper) {
        this.rightUpper = rightUpper;
    }


}
