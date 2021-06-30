package ru.mg.redhat.Engine.Objects;

import android.graphics.Bitmap;
import android.graphics.Point;

import ru.mg.redhat.Engine.Objects.BitmapObject;

public class MultipleBitmapObject {
    public BitmapObject[] objects;

    public MultipleBitmapObject(Boolean[][] collMap, Bitmap bmp, int count, int priority, Point pos) {
        objects = new BitmapObject[count];

        for (int i = 0; i < count; i++) {
            objects[i] = new BitmapObject(collMap, new Point(pos.x, pos.y + i * bmp.getHeight()), priority, true, bmp);
        }
    }
}
