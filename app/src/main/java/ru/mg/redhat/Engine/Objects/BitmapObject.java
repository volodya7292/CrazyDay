package ru.mg.redhat.Engine.Objects;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import java.nio.ByteBuffer;

import ru.mg.redhat.Engine.Main.Graphics;

public class BitmapObject {
    private float x, y;
    private float psx, psy;
    public int collX, collY, collW, collH;
    public BitmapObject bitmapObject, secondBitmapObject;
    public Point motion1, motion2;
    public int priority;
    public boolean collision = true;
    public boolean globalCollision = true;
    public boolean itemState = false;
    public boolean item = false;
    public boolean removing, isThrow;
    public Paint paint = Graphics.anti_alias;
    public Bitmap bmp;
    public String name;

    public BitmapObject(Boolean[][] collMap, Point pos, int priority, boolean collision, Bitmap bmp) {
        this.x = pos.x;
        this.y = pos.y;
        this.psx = pos.x - bmp.getWidth() / 2;
        this.psy = pos.y - bmp.getHeight() / 2;
        this.priority = priority;
        this.collision = collision;
        this.globalCollision = true;
        this.bmp = bmp;
        this.collX = 0;
        this.collY = 0;
        this.collW = bmp.getWidth();
        this.collH = bmp.getHeight();
        this.removing = false;

        if (collision) {
            for (int sy = 0; sy < bmp.getHeight(); sy++) {
                for (int sx = 0; sx < bmp.getWidth(); sx++) {
                    if (Color.alpha(bmp.getPixel(sx, sy)) > 200) {
                        collMap[(int) psx + sx][(int) psy + sy] = true;
                    }
                }
            }
        }
    }

    public BitmapObject(Boolean[][] collMap, Point pos, int priority, int collX, int collY, int collW, int collH, Bitmap bmp) {
        this.x = pos.x;
        this.y = pos.y;
        this.psx = pos.x - bmp.getWidth() / 2;
        this.psy = pos.y - bmp.getHeight() / 2;
        this.priority = priority;
        this.collision = true;
        this.globalCollision = true;
        this.bmp = bmp;
        this.collX = collX;
        this.collY = collY;
        this.removing = false;

        if (collW == 0) this.collW = (int)((psx + bmp.getWidth()) - (psx + collX));
        else this.collW = (int)Graphics.scaleWidth(collW);
        if (collH == 0) this.collH = (int)((psy + bmp.getHeight()) - (psy + collY));
        else this.collH = (int)Graphics.scaleHeight(collH);

        for (int sy = 0; sy < this.collH; sy++) {
            for (int sx = 0; sx < this.collW; sx++) {
                if (Color.alpha(bmp.getPixel(collX + sx, collY + sy)) > 200)
                    collMap[collX + (int)psx + sx][collY + (int)psy + sy] = true;
            }
        }
    }

    public BitmapObject(Boolean[][] collMap, Point pos, int priority, int collX, int collY, int collW, int collH, Bitmap bmp, ProgressBar loadingBar) {
        this.x = pos.x;
        this.y = pos.y;
        this.psx = pos.x - bmp.getWidth() / 2;
        this.psy = pos.y - bmp.getHeight() / 2;
        this.priority = priority;
        this.collision = true;
        this.globalCollision = true;
        this.bmp = bmp;
        this.collX = collX;
        this.collY = collY;
        this.removing = false;

        if (collW == 0) this.collW = (int)((psx + bmp.getWidth()) - (psx + collX));
        else this.collW = (int)Graphics.scaleWidth(collW);
        if (collH == 0) this.collH = (int)((psy + bmp.getHeight()) - (psy + collY));
        else this.collH = (int)Graphics.scaleHeight(collH);

        loadingBar.setMax(this.collW * this.collH);

        int size = bmp.getRowBytes() * bmp.getHeight();
        ByteBuffer byteBuffer = ByteBuffer.allocate(size);
        bmp.copyPixelsToBuffer(byteBuffer);
        byte[] byteArray = byteBuffer.array();

        for (int sy = 1; sy < this.collH; sy++) {
            for (int sx = 1; sx < this.collW; sx++) {

                byte a = byteArray[(bmp.getWidth() * (collY + sy - 1) + (collX + sx)) * 4 - 1];

                if ((a & 0xFF) > 200)
                    collMap[collX + (int)psx + sx][collY + (int)psy + sy] = true;

                loadingBar.value++;
            }
        }
    }

    public BitmapObject(Point pos, int priority, Bitmap bmp) {
        this.x = pos.x;
        this.y = pos.y;
        this.psx = pos.x - bmp.getWidth() / 2;
        this.psy = pos.y - bmp.getHeight() / 2;
        this.priority = priority;
        this.bmp = bmp;
        this.globalCollision = false;
        this.removing = false;
    }

    public BitmapObject(String name, Point pos, int priority, Bitmap bmp) {
        this.x = pos.x;
        this.y = pos.y;
        this.psx = pos.x - bmp.getWidth() / 2;
        this.psy = pos.y - bmp.getHeight() / 2;
        this.priority = priority;
        this.bmp = bmp;
        this.item = true;
        this.globalCollision = false;
        this.motion1 = new Point(pos.x, pos.y - 20);
        this.motion2 = new Point(pos.x, pos.y + 20);
        this.name = name;
        this.removing = false;
    }

    public void setX(float x) {
        this.x = Graphics.scaleWidth(x);
        this.psx = this.x - bmp.getWidth() / 2;
    }

    public void setNewX(float x) {
        this.x = x;
        this.psx = this.x - bmp.getWidth() / 2;
    }

    public float getX() {
        return x;
    }

    public void transformX(float x) {
        float transformedX = this.x + x;
        float speed = Graphics.scaleWidth(x);

        if (transformedX < this.x) {
            this.x -= Graphics.checkBitmapLeftCollision(this, Math.abs((int)speed));
            Graphics.checkUpLeftCollision(this, (Math.abs((int)speed)));
        } else {
            this.x += Graphics.checkBitmapRightCollision(this, (int)speed);
            Graphics.checkUpRightCollision(this, (int)speed);
        }

        this.psx = this.x - bmp.getWidth() / 2;
    }

    public void setPSX(float psx) {
        this.psx = Graphics.scaleWidth(psx);
        this.x = this.psx + bmp.getWidth() / 2;
    }

    public void setNewPSX(float psx) {
        this.psx = psx;
        this.x = this.psx + bmp.getWidth() / 2;
    }

    public float getPSX() {
        return psx;
    }

    public void setY(float y) {
        this.y = Graphics.scaleHeight(y);
        this.psy = this.y - bmp.getHeight() / 2;
    }

    public void setNewY(float y) {
        this.y = y;
        this.psy = this.y - bmp.getHeight() / 2;
    }

    public float getY() {
        return y;
    }

    public void transformY(float y) {
        float transformedX = this.y + y;
        float speed = Graphics.scaleHeight(y);

        if (transformedX < this.y) {
            this.y -= Math.abs(speed);
        } else {
            this.y += speed;
        }

        this.psy = this.y - bmp.getHeight() / 2;
    }

    public void setPSY(float psy) {
        this.psy = Graphics.scaleHeight(psy);
        this.y = this.psy + bmp.getHeight() / 2;
    }

    public void setNewPSY(float psy) {
        this.psy = psy;
        this.y = this.psy + bmp.getHeight() / 2;
    }

    public float getPSY() {
        return psy;
    }
}
