package ru.mg.redhat.Engine.Objects;

import android.graphics.Paint;
import android.graphics.Point;

import ru.mg.redhat.Engine.Main.Graphics;

public class TextObject {
    public String text;
    private float x, y;
    private float psx, psy;
    public int priority;
    public boolean collision;
    public boolean globalCollision;
    public boolean removing;
    public Paint paint;

    public TextObject(Boolean[][] collMap, String text, Point pos, int priority, boolean collision, Paint paint) {
        this.text = text;
        this.x = pos.x;
        this.y = pos.y;
        this.psx = pos.x - (int)paint.measureText(text) / 2;
        this.psy = pos.y + (int)paint.getTextSize() / 3;
        this.priority = priority;
        this.collision = collision;
        this.globalCollision = true;
        this.paint = paint;
        this.removing = false;

        if (collision) {
            for (int sy = 0; sy < paint.getTextSize(); sy++) {
                for (int sx = 0; sx < paint.measureText(text); sx++)
                    collMap[(int)psx + sx][(int)psy + sy] = true;
            }
        }
    }

    public TextObject(String text, Point pos, int priority, Paint paint) {
        this.text = text;
        this.x = pos.x;
        this.y = pos.y;
        this.psx = pos.x - (int)paint.measureText(text) / 2;
        this.psy = pos.y + (int)paint.getTextSize() / 3;
        this.priority = priority;
        this.collision = true;
        this.globalCollision = false;
        this.paint = paint;
        this.removing = false;
    }

    public void setX(float x) {
        this.x = Graphics.scaleWidth(x);
        this.psx = this.x - paint.measureText(text) / 2;
    }

    public void setNewX(float x) {
        this.x = x;
        this.psx = this.x - paint.measureText(text) / 2;
    }

    public float getX() {
        return x;
    }

    public void setPSX(float psx) {
        this.psx = Graphics.scaleWidth(psx);
        this.x = this.psx + paint.measureText(text) / 2;
    }

    public void setNewPSX(float psx) {
        this.psx = psx;
        this.x = this.psx + paint.measureText(text) / 2;
    }

    public float getPSX() {
        return psx;
    }

    public void setY(float y) {
        this.y = Graphics.scaleHeight(y);
        this.psy = this.y + paint.getTextSize() / 3;
    }

    public void setNewY(float y) {
        this.y = y;
        this.psy = this.y + paint.getTextSize() / 3;
    }

    public float getY() {
        return y;
    }

    public void setPSY(float psy) {
        this.psy = Graphics.scaleHeight(psy);
        this.y = this.psy + paint.getTextSize() / 3;
    }

    public void setNewPSY(float psy) {
        this.psy = psy;
        this.y = this.psy + paint.getTextSize() / 3;
    }

    public float getPSY() {
        return psy;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        this.psx = x - (int)paint.measureText(text) / 2;
    }
}
