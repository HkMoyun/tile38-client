package com.mapabc.eunms;

/**
 * @Author ke.han
 * @Date 2019/11/13 19:34
 **/
public enum ElementType {
    POINT_OBJ("Point"),//点
    OBJECT_OBJ("Polygon"),//多边形
    POINT("POINT"),//点
    BOUNDS("BOUNDS"),//矩形
    OBJECT("OBJECT");//多边形

    ElementType(String type){
        this.type = type;
    }

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
