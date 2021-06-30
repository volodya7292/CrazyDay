package ru.mg.redhat.Engine.Objects;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

import ru.mg.redhat.Engine.Main.CONSTANTS;
import ru.mg.redhat.Engine.Main.Graphics;

public class EnemyObject {
    private String name;
    public BitmapObject object;
    public Bitmap bmpNormal, bmpLeft, bmpRight;
    public int currentDirection, speed, power;
    public boolean enabled;
    public boolean nextHit = true;
    private int constX1, constX2;
    private Timer hitTimer;
    public Paint paint;
    public boolean removing = false;

    public EnemyObject(Boolean[][] collMap, String name, Point pos, int priority, int currentDirection, int speed, int power, boolean enabled, Bitmap bmpNormal, Bitmap bmpLeft, Bitmap bmpRight) {
        this.name = name;
        this.object = new BitmapObject(collMap, pos, priority, false, bmpNormal);
        this.constX1 = pos.x - 100;
        this.constX2 = pos.x + 100;
        this.currentDirection = currentDirection;
        this.speed = speed;
        this.power = power;
        this.enabled = enabled;
        this.bmpNormal = bmpNormal;
        this.bmpLeft = bmpLeft;
        this.bmpRight = bmpRight;
        this.paint = new Paint(Graphics.anti_alias);
    }

    public void go() {
        if (enabled) {
            if (currentDirection == CONSTANTS.DIRECTION_LEFT && object.getX() <= constX1) {
                currentDirection = CONSTANTS.DIRECTION_RIGHT;
                object.bmp = bmpRight;
            } else if (currentDirection == CONSTANTS.DIRECTION_RIGHT && object.getX() >= constX2) {
                currentDirection = CONSTANTS.DIRECTION_LEFT;
                object.bmp = bmpLeft;
            }

            if (currentDirection == CONSTANTS.DIRECTION_LEFT)
                object.transformX(-Graphics.translate(50));
            else
                object.transformX(Graphics.translate(50));
        }
    }

    public boolean checkCollision(BitmapObject obj) {
        return Graphics.checkBitmapObjectTouch(object, obj);
    }

    public void disableForWhile(long time) {
        if (enabled) {
            paint.setAlpha(75);
            enabled = false;
            Timer tm = new Timer();
            tm.schedule(new TimerTask() {
                @Override
                public void run() {
                    enabled = true;
                    paint.setAlpha(255);
                }
            }, time);
        }
    }

    public void hit() {
        nextHit = false;
        hitTimer = new Timer();
        hitTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                nextHit = true;
            }
        }, 1000);
    }

    public String getName() {
        return name;
    }
}
