package ru.mg.redhat.Engine.Objects;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Point;

import ru.mg.redhat.Engine.Main.ClickHandler;

public class ButtonObject {
    public BitmapObject base;
    public TextObject title;
    public Bitmap bmp;
    public Bitmap bmpActive;
    public boolean active = false;
    public ClickHandler method;
    public int touchID = -1;

    public ButtonObject(Point pos, String text, int priority, Bitmap bmp, Bitmap bmpActive, Paint paint, ClickHandler method) {
        this.bmp = bmp;
        this.bmpActive = bmpActive;
        this.method = method;
        base = new BitmapObject(pos, priority, bmp);
        title = new TextObject(text, pos, priority, paint);
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

        if (x >= (int)base.getPSX() && x <= (int)base.getPSX() + base.bmp.getWidth() && y >= (int)base.getPSY() && y <= (int)base.getPSY() + base.bmp.getHeight())
            return true;

        return false;
    }
}
