package com.wadequns.crazyday.Engine.Objects;

import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.wadequns.crazyday.Engine.Main.Graphics;

public class BigTextObject {
    public TextObject[] objects;

    public BigTextObject(String[] text, int indent, int priority, Point pos, Paint paint) {
        objects = new TextObject[text.length];

        for (int i = 0; i < text.length; i++) {
            if (i == 0) {
                objects[i] = new TextObject(text[i], new Point(0, 0), priority, paint);
                objects[i].setNewPSY(pos.y);
            } else {
                objects[i] = new TextObject(text[i], new Point(0, 0), priority, paint);

                Rect bounds = new Rect();
                paint.getTextBounds(text[i], 0, text[i].length(), bounds);

                objects[i].setNewPSY((int)(objects[i - 1].getPSY() + bounds.height() + Graphics.scaleHeight(indent)));
            }

            objects[i].setNewPSX(pos.x);
        }
    }
}
