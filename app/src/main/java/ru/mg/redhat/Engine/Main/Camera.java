package ru.mg.redhat.Engine.Main;

import android.graphics.Point;

public class Camera {
    private int x;
    private int y;
    private int psx;
    private int psy;
    public int w;
    public int h;

    public Camera(Point pos, int width, int height) {
        this.x = pos.x;
        this.y = pos.y;
        this.psx = this.x - width / 2;
        this.psy = this.y - height / 2;
        this.w = width;
        this.h = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        this.psx = this.x - w / 2;
    }

    public int getPSX() {
        return psx;
    }

    public void setPSX(int psx) {
        this.psx = psx;
        this.x = this.psx + w / 2;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        this.psy = this.y - h / 2;
    }

    public int getPSY() {
        return psy;
    }

    public void setPSY(int psy) {
        this.psy = psy;
        this.y = this.psy + h / 2;
    }

    public int getWidth() {
        return w;
    }

    public void setWidth(int width) {
        w = width;
        psx = x - width / 2;
    }

    public int getHeight() {
        return h;
    }

    public void setHeight(int height) {
        h = height;
        psy = y - height / 2;
    }
}
