package com.mapabc.entity;


import java.io.Serializable;

/**
 * @Author ke.han
 * @Date 2019/11/14 15:29
 **/
public class Rectangle extends Element implements Serializable {

    private Point leftDown;
    private Point rightUpper;

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
