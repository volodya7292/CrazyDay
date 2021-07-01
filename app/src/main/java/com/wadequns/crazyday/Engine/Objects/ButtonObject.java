package com.wadequns.crazyday.Engine.Objects;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Point;

import com.wadequns.crazyday.Engine.Main.ClickHandler;
import com.wadequns.crazyday.Engine.Main.Graphics;

public class ButtonObject {
    public BitmapObject base;
    public TextObject title;
    public Bitmap bmp;
    public Bitmap bmpActive;
    public boolean active = false;
    public boolean enabled = true;
    public ClickHandler method;
    public int touchID = -1;

    public ButtonObject(Point pos, String text, int priority, Bitmap bmp, Bitmap bmpActive, Paint paint, ClickHandler method) {
        this.bmp = bmp;
        this.bmpActive = bmpActive;
        this.method = method;
        base = new BitmapObject(pos, priority, bmp);
        title = new TextObject(text, Graphics.scalePoint(pos), priority, paint);
    }

    public void toggle() {
        if (active) {
            active = false;
            base.bmp = bmp;
        } else {
            active = true;
            base.bmp = bmpActive;
        }
    }

    public void setActive(boolean active) {
        if (active) {
            this.active = true;
            base.bmp = bmpActive;
        } else {
            this.active = false;
            base.bmp = bmp;
        }
    }

    public boolean checkClick(int clickedX, int clickedY) {
        int x = clickedX;
        int y = clickedY;

        if (enabled && x >= (int)base.getPSX() && x <= (int)base.getPSX() + base.bmp.getWidth() && y >= (int)base.getPSY() && y <= (int)base.getPSY() + base.bmp.getHeight())
            return true;

        return false;
    }
}
