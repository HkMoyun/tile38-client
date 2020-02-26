package com.mapabc.eunms;

/**
 * @Author ke.han
 * @Date 2019/11/14 11:19
 **/
public enum DetectType {
    /**
     EMPTY = ""
     inside 当对象在指定区域内时。
     outside 当对象在指定区域之外时。
     enter 当先前不在栅栏中的物体进入该区域时。
     exit 是当一个对象是预先在篱笆已经退出的区域。
     cross 当先前不在围栏中的对象进入和离开该区域时。
     **/
    EMPTY(""),
    INSIDE("inside"),
    OUTSIDE("outside"),
    ENTER("enter"),
    EXIT("exit"),
    CROSS("cross");

    private String type;

    DetectType(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
