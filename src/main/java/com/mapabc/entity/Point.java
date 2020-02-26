package com.mapabc.entity;

import java.io.Serializable;

/**
 * @Author ke.han
 * @Date 2019/11/14 15:28
 **/
public class Point extends Element implements Serializable {

    private double lng;
    private double lat;

    public Point() {}

    public Point(double lng, double lat) {
        this.lng = lng;
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "Point{" +
                "lng=" + lng +
                ", lat=" + lat +
                '}';
    }

}
