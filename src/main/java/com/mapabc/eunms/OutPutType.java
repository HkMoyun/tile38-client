package com.mapabc.eunms;

/**
 * @Author ke.han
 * @Date 2019/11/14 12:38
 **/
public enum  OutPutType {

    JSON("json"),
    RESP("resp");

    OutPutType(String type){
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
