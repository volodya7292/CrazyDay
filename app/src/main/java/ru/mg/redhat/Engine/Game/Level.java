package ru.mg.redhat.Engine.Game;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.HashMap;
import java.util.Map;

import ru.mg.redhat.Engine.Main.Camera;
import ru.mg.redhat.Engine.Main.ClickHandler;
import ru.mg.redhat.Engine.Main.Graphics;
import ru.mg.redhat.Engine.Objects.BigTextObject;
import ru.mg.redhat.Engine.Objects.BitmapObject;
import ru.mg.redhat.Engine.Objects.ButtonObject;
import ru.mg.redhat.Engine.Objects.EnemyObject;
import ru.mg.redhat.Engine.Objects.MultipleBitmapObject;
import ru.mg.redhat.Engine.Objects.ProgressBar;
import ru.mg.redhat.Engine.Objects.SecretObject;
import ru.mg.redhat.Engine.Objects.TextObject;

public class Level {
    public Map <String, BitmapObject> bitmapObjects;
    public Map <String, TextObject> textObjects;
    public HashMap <String, ButtonObject> buttonObjects;
    public HashMap <String, MultipleBitmapObject> multipleBitmapObjects;
    public HashMap <String, BigTextObject> bigTextObjects;
    public HashMap<String, SecretObject> secretObjects;
    public HashMap<String, EnemyObject> enemyObjects;
    public HashMap<String, ProgressBar> progressBars;
    public Boolean[][] collMap;
    public Camera camera;
    public int objectsPriority;
    public int fallSpeed;

    public Level(int collW, int collH, int objectsPriority, int fallSpeed, Camera camera) {
        this.bitmapObjects = new HashMap<>();
        this.textObjects = new HashMap<>();
        this.buttonObjects = new HashMap<>();
        this.multipleBitmapObjects = new HashMap<>();
        this.bigTextObjects = new HashMap<>();
        this.secretObjects = new HashMap<>();
        this.enemyObjects = new HashMap<>();
        this.progressBars = new HashMap<>();
        this.collMap = new Boolean[(int)Graphics.scaleWidth(collW) + 1][(int)Graphics.scaleHeight(collH) + 1];
        this.objectsPriority = objectsPriority;
        this.fallSpeed = fallSpeed;
        this.camera = camera;
    }

    //BITMAP OBJECT
    public void addBitmapObject(String name, Point pos, int priority, boolean collision, Bitmap bmp) {
        bitmapObjects.put(name, new BitmapObject(collMap, Graphics.scalePoint(pos), priority, collision, bmp));
    }

    public void addBitmapObject(String name, Point pos, int priority, Bitmap bmp) {
        bitmapObjects.put(name, new BitmapObject(Graphics.scalePoint(pos), priority, bmp));
    }

    public void addBitmapObject(String name, Point pos, int priority, int collX, int collY, int collW, int collH, Bitmap bmp) {
        bitmapObjects.put(name, new BitmapObject(collMap, Graphics.scalePoint(pos), priority, collX, collY, collW, collH, bmp));
    }

    public void addBitmapObject(String name, Point pos, int priority, int collX, int collY, int collW, int collH, Bitmap bmp, ProgressBar loadingBar) {
        bitmapObjects.put(name, new BitmapObject(collMap, Graphics.scalePoint(pos), priority, collX, collY, collW, collH, bmp, loadingBar));
    }

    public void addBitmapItemObject(String name, Point pos, int priority, Bitmap bmp) {
        bitmapObjects.put(name, new BitmapObject(name, Graphics.scalePoint(pos), priority, bmp));
    }

    public void removeBitmapObject(String name) {
        if (bitmapObjects.get(name) != null)
            bitmapObjects.get(name).removing = true;
    }

    //ENEMY OBJECT
    public void addEnemyObject(String name, Point pos, int priority, int currentDirection, int speed, int power, boolean enabled, Bitmap bmpNormal, Bitmap bmpLeft, Bitmap bmpRight) {
        enemyObjects.put(name, new EnemyObject(collMap, name, Graphics.scalePoint(pos), priority, currentDirection, speed, power, enabled, bmpNormal, bmpLeft, bmpRight));
    }

    public void removeEnemyObject(String name) {
        if (enemyObjects.get(name) != null)
            enemyObjects.get(name).removing = true;
    }

    //SECRET OBJECT
    public void addSecretObject(String name, int x, int y, int width, int height) {
        secretObjects.put(name, new SecretObject(name, (int)Graphics.scaleWidth(x), (int)Graphics.scaleHeight(y), (int)Graphics.scaleWidth(width), (int)Graphics.scaleHeight(height)));
    }

    //TEXT OBJECT
    public void addTextObject(String name, String text, Point pos, int priority, boolean collision, Paint paint) {
        textObjects.put(name, new TextObject(collMap, text, Graphics.scalePoint(pos), priority, collision, Graphics.scalePaint(paint)));
    }

    public void addTextObject(String name, String text, Point pos, int priority, Paint paint) {
        textObjects.put(name, new TextObject(text, Graphics.scalePoint(pos), priority, Graphics.scalePaint(paint)));
    }

    public void removeTextObject(String name) {
        if (textObjects.get(name) != null)
            textObjects.get(name).removing = true;
    }

    //BUTTON OBJECT
    public void addButtonObject(String name, String text, Point pos, int priority, int opacity, Bitmap bmp, Bitmap bmpActive, Paint paint, ClickHandler method) {
        Paint newPaint = new Paint(Graphics.scalePaint(paint));
        newPaint.setAlpha((int)(2.55f * opacity));

        buttonObjects.put(name, new ButtonObject(Graphics.scalePoint(pos), text, priority, bmp, bmpActive, newPaint, method));
    }

    public void removeButtonObject(String name) {
        buttonObjects.remove(name);
    }

    //MULTIPLE BITMAP OBJECT
    public void addMultipleBitmapObject(String name, Bitmap bmp, int count, int priority, Point pos) {
        multipleBitmapObjects.put(name, new MultipleBitmapObject(collMap, bmp, count, priority, Graphics.scalePoint(pos)));
    }

    public void removeMultipleBitmapObject(String name) {
        multipleBitmapObjects.remove(name);
    }

    //BIGTEXT OBJECT
    public void addBigTextObject(String name, String[] text, int indent, int priority, Point pos, Paint paint) {
        bigTextObjects.put(name, new BigTextObject(text, indent, priority, Graphics.scalePoint(pos), Graphics.scalePaint(paint)));
    }

    public void removeBitTextObject(String name) {
        bigTextObjects.remove(name);
    }

    //RECTANGLE OBJECT
    public void addProgressBar(String name, int x, int y, int width, int height, int max, int value, int back, int fore, int border) {
        progressBars.put(name, new ProgressBar((int)(x / Graphics.scaleX), (int)(y / Graphics.scaleY), (int)(width / Graphics.scaleX), (int)(height / Graphics.scaleY), max, value, back, fore, border));
    }

    public void addProgressBar(String name, int x, int y, int width, int height, int max, int value, int border) {
        progressBars.put(name, new ProgressBar((int)(x / Graphics.scaleX), (int)(y / Graphics.scaleY), (int)(width / Graphics.scaleX), (int)(height / Graphics.scaleY), max, value, border));
    }

    public void setBitmapObject(String name, BitmapObject obj) {
        bitmapObjects.get(name).bitmapObject = obj;
    }

    public void setSecondBitmapObject(String name, BitmapObject obj) {
        bitmapObjects.get(name).secondBitmapObject = obj;
    }

    public void redrawCollisionMap() {
        for (BitmapObject obj : bitmapObjects.values()) {
            if (obj.collision && obj.globalCollision) {
                for (int sy = 0; sy < obj.collH; sy++) {
                    for (int sx = 0; sx < obj.collW; sx++) {
                        if (Color.alpha(obj.bmp.getPixel(sx, sy)) > 200)
                            collMap[obj.collX + (int)obj.getPSX() + sx][obj.collY + (int)obj.getPSY() + sy] = true;
                    }
                }
            }
        }
        for (TextObject obj : textObjects.values()) {
            if (obj.collision && obj.globalCollision) {
                for (int sy = 0; sy < obj.paint.getTextSize(); sy++) {
                    for (int sx = 0; sx < obj.paint.measureText(obj.text); sx++)
                        collMap[(int)obj.getPSX() + sx][(int)obj.getPSY() + sy] = true;
                }
            }
        }
    }
}