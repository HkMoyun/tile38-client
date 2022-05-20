package com.mapabc.entity;

import java.io.Serializable;

/**
 * 2022/5/20 17:37
 * 扇形
 * @auther ke.han
 */
public class Sector extends Element implements Serializable {

    private double lng;
    private double lat;
    private int r;
    private int startAngle;
    private int endAngle;

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

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getStartAngle() {
        return startAngle;
    }

    public void setStartAngle(int startAngle) {
        this.startAngle = startAngle;
    }

    public int getEndAngle() {
        return endAngle;
    }

    public void setEndAngle(int endAngle) {
        this.endAngle = endAngle;
    }

    @Override
    public String toString() {
        return "Sector{" +
                "lng=" + lng +
                ", lat=" + lat +
                ", r=" + r +
                ", startAngle=" + startAngle +
                ", endAngle=" + endAngle +
                '}';
    }


}
