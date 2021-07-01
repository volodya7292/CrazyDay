package com.wadequns.crazyday.Engine.Main;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.wadequns.crazyday.Engine.Objects.BigTextObject;
import com.wadequns.crazyday.Engine.Objects.BitmapObject;
import com.wadequns.crazyday.Engine.Objects.ButtonObject;
import com.wadequns.crazyday.Engine.Objects.EnemyObject;
import com.wadequns.crazyday.Engine.Objects.MultipleBitmapObject;
import com.wadequns.crazyday.Engine.Objects.ProgressBar;
import com.wadequns.crazyday.Engine.Objects.TextObject;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.wadequns.crazyday.Engine.Game.Level;

public class Graphics {
    public static Paint anti_alias = new Paint(Paint.ANTI_ALIAS_FLAG);
    public static Paint defaultColor = new Paint(Paint.ANTI_ALIAS_FLAG);

    public static Level level;
    public static Window MainWindow;
    public static double scaleX;
    public static double scaleY;
    public static int currFPS;
    public static AssetManager assetManager;
    private static Random rand;

    //ITEM DRAWING
    private static int mult0, mult1;

    //INITIALIZE GRAPHICS ENGINE
    public static void init(AssetManager assMan, Window window, int camW, int camH) {

        MainWindow = window;
        assetManager = assMan;
        rand = new Random();

        if (camW > camH) {
//            scaleX = 1;
//            scaleY = 1;
            scaleX = (double) 1280 / camW;
            scaleY = (double) 720 / camH;
        } else {
            scaleX = (double) 720 / camW;
            scaleY = (double) 1280 / camH;
        }

        mult0 = (int) Graphics.scaleHeight(25);
        mult1 = (int) Graphics.scaleHeight(27);

        defaultColor.setColor(Color.BLACK);
        defaultColor.setTextSize(50);

        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED, WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
    }

    public static int floor(double num) {
        if (num >= Math.floor(num) + 0.5)
            return (int) Math.floor(num) + 1;
        else
            return (int) Math.floor(num);
    }

    public static int minFloor(double num) {
        if ((float) num - (int) num >= 0.0f)
            return 1;

        return (int) num;
    }

    public static int randNum(int min, int max) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return ThreadLocalRandom.current().nextInt(min, max + 1);
        } else {
            return rand.nextInt((max - min) + 1) + min;
        }
    }

    public static Paint textPaint(int color, int textSize, String familyName, int style) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        paint.setTextSize(textSize);
        paint.setTypeface(Typeface.create(familyName, style)); //Arial

        return paint;
    }

    public static Paint textPaint(int color, int textSize, String pathToFont) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Typeface plain = Typeface.createFromAsset(assetManager, pathToFont);
        Typeface bold = Typeface.create(plain, Typeface.BOLD);
        paint.setTypeface(bold);
        paint.setTextSize(textSize);
        paint.setColor(color);

        return paint;
    }

    public static Paint textPaint(int color, int textSize) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        paint.setTextSize(textSize);

        return paint;
    }

    public static void loadLevel(Level l) {
        level = l;
    }

    //TRANSLATE OBJECTS
    public static int translate(int startPoint, int endPoint, int fps, int time) {
        return floor((float) (endPoint - startPoint) / fps * 1000 / time);
    }

    public static float translate(int speed) {
        return (float) speed / currFPS;
    }

    //BITMAP METHODS
    public static Bitmap loadBitmap(String name) {
        try {
            return Bitmap.createBitmap(BitmapFactory.decodeStream(assetManager.open(name)));
        } catch (Exception e) {
        }

        return null;
    }

    public static Bitmap resizeBitmap(Bitmap bm, int width, int height) {
        Bitmap bmp;

        bmp = Bitmap.createScaledBitmap(bm, width, height, false);
        if (bm != bmp) {
            bm.recycle();
        }

        return bmp;
    }

    public static Bitmap scaleBitmap(Bitmap bmp) {
        return resizeBitmap(bmp, (int) (bmp.getWidth() / scaleX), (int) (bmp.getHeight() / scaleY));
    }

    public static Bitmap flip(Bitmap bmp) {
        Matrix m = new Matrix();
        m.preScale(-1, 1);

        Bitmap newBmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), m, false);

        if (bmp != newBmp) {
            bmp.recycle();
        }

        return newBmp;
    }

    public static Bitmap processBitmap(String name, int w, int h) {
        return resizeBitmap(loadBitmap(name), w, h);
    }

    public static Point scalePoint(Point point) {
        return new Point((int) (point.x / scaleX), (int) (point.y / scaleY));
    }

    public static Paint scalePaint(Paint paint) {
        Paint newPaint = new Paint(paint);
        newPaint.setTextSize(Graphics.scaleHeight(newPaint.getTextSize()));

        return newPaint;
    }

    public static float scaleWidth(float width) {
        return (float) (width / scaleX);
    }

    public static float scaleHeight(float height) {
        return (float) (height / scaleY);
    }

    //COLLISIONS
    public static void checkBitmapObjectDownCollision(int fps, int speed, BitmapObject obj) {
        if (!obj.collision && obj.getPSX() + obj.bmp.getWidth() > level.camera.getPSX() && obj.getPSX() < level.camera.getPSX() + level.camera.w &&
                obj.getPSY() + obj.bmp.getHeight() > level.camera.getPSY() && obj.getPSY() < level.camera.getPSY() + level.camera.h) {

            int cy = (int) obj.getPSY() + obj.bmp.getHeight();
            boolean state = true;

            if (obj.getPSX() < level.collMap.length - obj.bmp.getWidth()) {
                try {
                    if (level.collMap[(int) obj.getX()][cy + level.fallSpeed / fps] != null) {
                        state = false;
                        int ost = 0;

                        for (int d = 0; d < level.fallSpeed / fps; d++) {
                            if (level.collMap[(int) obj.getX()][cy + d] == null)
                                ost++;
                        }

                        obj.transformY(ost);
                    }

                    if (state) {
                        int step = 0;
                        boolean success = true;

                        for (int i = 0; i < level.fallSpeed / fps; i++) {
                            if (level.collMap[(int) obj.getX()][cy + i] != null) {
                                success = false;
                                obj.transformY(step);
                                break;
                            } else step++;
                        }

                        if (success) {
                            obj.transformY(translate(speed));
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    public static void checkDownCollision(int fps, int speed) {
        for (BitmapObject obj : level.bitmapObjects.values()) {
            checkBitmapObjectDownCollision(fps, speed, obj);
        }
        for (EnemyObject enemy : level.enemyObjects.values()) {
            checkBitmapObjectDownCollision(fps, speed, enemy.object);
        }
        for (TextObject obj : level.textObjects.values()) {
            if (!obj.collision && obj.getPSX() + obj.paint.measureText(obj.text) > level.camera.getPSX() && obj.getX() < level.camera.getPSX() + level.camera.w &&
                    obj.getPSY() + obj.paint.getTextSize() > level.camera.getPSY() && obj.getPSY() - obj.paint.getTextSize() < level.camera.getPSY() + level.camera.h) {
                int cy = (int) obj.getPSY() + (int) obj.paint.getTextSize() / 3 + 1;
                boolean state = true;

                for (int i = 0; i < obj.paint.measureText(obj.text); i++) {
                    if (level.collMap[(int) obj.getPSX() + i][cy] != null)
                        state = false;
                }

                if (state) {
                    int step = 0;

                    for (int i = 0; i < level.fallSpeed / fps; i++) {
                        if (level.collMap[(int) obj.getX()][cy + i] != null) {
                            state = false;
                            obj.setY(obj.getY() + step);
                            break;
                        } else step++;
                    }
                }
                if (state)
                    obj.setY(obj.getY() + translate(speed));
            }
        }
    }

    public static boolean checkObjectDownCollision(BitmapObject obj) {
        if (obj.getPSX() < level.collMap.length - obj.bmp.getWidth() + 1) {
            return level.collMap[(int) obj.getX()][(int) obj.getPSY() + obj.bmp.getHeight() + 1] != null;
        }

        return false;
    }

    public static boolean checkObjectCollision(BitmapObject obj) {
        if (level.collMap[(int) obj.getPSX() - 1][(int) obj.getPSY() + obj.bmp.getHeight() - obj.bmp.getHeight() / 3] != null ||
                level.collMap[(int) obj.getPSX() + obj.bmp.getWidth()][(int) obj.getPSY() + obj.bmp.getHeight() - obj.bmp.getHeight() / 3] != null)
            return true;

        return false;
    }

    public static void checkUpRightCollision(BitmapObject obj, int step) {
        if (!obj.collision && obj.getPSX() + obj.bmp.getWidth() > level.camera.getPSX() && obj.getPSX() < level.camera.getPSX() + level.camera.w &&
                obj.getPSY() + obj.bmp.getHeight() > level.camera.getPSY() && obj.getPSY() < level.camera.getPSY() + level.camera.h) {

            if (obj.getPSX() + step < level.collMap.length - obj.bmp.getWidth() + 1 && obj.getPSX() + step > 0) {
                int height = 0;

                if (level.collMap[(int) obj.getX() + step][(int) obj.getPSY() + obj.bmp.getHeight()] != null) {

                    for (int i = 0; i > -1; i++) {
                        if ((int) obj.getPSY() + obj.bmp.getHeight() - i + 1 < 0)
                            break;

                        if ((int) obj.getPSY() + obj.bmp.getHeight() - i > -1 && level.collMap[(int) obj.getX() + step][(int) obj.getPSY() + obj.bmp.getHeight() - i] != null) {
                            height++;
                        } else {
                            break;
                        }
                    }

                    if (height <= obj.bmp.getHeight() / 3) {
                        obj.setX((float) (obj.getX() * scaleX) + step);
                        obj.transformY(-height);
                    }
                }
            }
        }
    }

    public static void checkUpLeftCollision(BitmapObject obj, int step) {
        if (!obj.collision && obj.getPSX() + obj.bmp.getWidth() > level.camera.getPSX() && obj.getPSX() < level.camera.getPSX() + level.camera.w &&
                obj.getPSY() + obj.bmp.getHeight() > level.camera.getPSY() && obj.getPSY() < level.camera.getPSY() + level.camera.h) {

            if (obj.getPSX() - step > 0) {
                int height = 0;

                if (level.collMap[(int) obj.getX() - step][((int) obj.getPSY() + obj.bmp.getHeight())] != null) {
                    for (int i = 0; i > -1; i++) {
                        if ((int) obj.getPSY() + obj.bmp.getHeight() - i + 1 < 0)
                            break;

                        if (level.collMap[(int) obj.getX() - step][(int) obj.getPSY() + obj.bmp.getHeight() - i + 1] != null) {
                            height++;
                        } else {
                            break;
                        }
                    }

                    if (height <= obj.bmp.getHeight() / 3) {
                        obj.setX((float) (obj.getX() * scaleX) - step);
                        obj.transformY(-height);
                    }
                }
            }
        }
    }

    public static int checkBitmapRightCollision(BitmapObject obj, int step) throws NullPointerException {
        if (obj.getPSX() + step > level.collMap.length - obj.bmp.getWidth() - 6)
            return 0;

        int newStep = 0;

        for (int d = 0; d < step; d++) {
            if (level.collMap[(int) obj.getPSX() + obj.bmp.getWidth() + d][(int) obj.getPSY() + obj.bmp.getHeight() - obj.bmp.getHeight() / 3] == null)
                newStep++;
            else break;
        }

        if (newStep < step) return newStep;
        else return step;
    }

    public static int checkBitmapLeftCollision(BitmapObject obj, int step) throws NullPointerException {
        if (obj.getPSX() - step < 0) return 0;

        int newStep = 0;

        for (int d = 0; d < step; d++) {
            if (level.collMap[(int) obj.getPSX() - d][(int) obj.getPSY() + obj.bmp.getHeight() - obj.bmp.getHeight() / 3] == null)
                newStep++;
            else break;
        }

        if (newStep < step) return newStep;
        else return step;
    }

    public static boolean checkBitmapObjectTouch(BitmapObject item, BitmapObject obj) {
        if (item != null && obj != null) {
            if (obj.getPSX() + obj.bmp.getWidth() > item.getPSX() && obj.getPSX() < item.getPSX() + item.bmp.getWidth()
                    && obj.getPSY() + obj.bmp.getHeight() > item.getPSY() && obj.getPSY() < item.getPSY() + item.bmp.getHeight()) {
                return true;
            }
        }

        return false;
    }

    public static BitmapObject checkBitmapObjectTouches(BitmapObject obj) {
        for (BitmapObject item : level.bitmapObjects.values()) {
            if (item.item) {
                if (checkBitmapObjectTouch(item, obj))
                    return item;
            }
        }

        return null;
    }

    public static boolean checkEnemyTouch(BitmapObject obj, long time) {
        for (EnemyObject enemy : level.enemyObjects.values()) {
            if (enemy.checkCollision(obj)) {
                enemy.disableForWhile(time);
                return true;
            }
        }

        return false;
    }

    public static EnemyObject checkObjectEnemyTouch(BitmapObject obj) {
        for (EnemyObject enemy : level.enemyObjects.values()) {
            if (checkBitmapObjectTouch(obj, enemy.object)) {
                return enemy;
            }
        }

        return null;
    }

    //DRAW METHODS
    public static boolean checkObjectVisibility(BitmapObject obj) {
        return (int) obj.getPSX() + obj.bmp.getWidth() > level.camera.getPSX() && (int) obj.getPSX() < level.camera.getPSX() +
                level.camera.w && (int) obj.getPSY() + obj.bmp.getHeight() > level.camera.getPSY() && (int) obj.getPSY() < level.camera.getPSY() + level.camera.h;
    }

    public static void drawSubBitmapObject(Canvas canvas, BitmapObject obj, BitmapObject subObj) {
        int x = (int) ((obj.getPSX() - level.camera.getPSX() + subObj.getX()) / scaleX);
        int y = (int) ((obj.getPSY() - level.camera.getPSY() + subObj.getY()) / scaleY);

        canvas.drawBitmap(subObj.bmp, new Rect(0, 0, subObj.bmp.getWidth(), subObj.bmp.getHeight()),
                new Rect(x, y, (int) (x + subObj.bmp.getWidth() / scaleX), (int) (y + subObj.bmp.getHeight() / scaleY)), anti_alias);

        if (subObj.bitmapObject != null)
            drawSubBitmapObject(canvas, subObj, subObj.bitmapObject);
    }

    public static void drawBitmapObject(Canvas canvas, int priority, BitmapObject obj) {
        if (obj.priority == priority && checkObjectVisibility(obj)) {
            int x = (int) ((obj.getPSX() - level.camera.getPSX()) / scaleX);
            int y = (int) ((obj.getPSY() - level.camera.getPSY()) / scaleY);

            canvas.drawBitmap(obj.bmp, new Rect(0, 0, obj.bmp.getWidth(), obj.bmp.getHeight()),
                    new Rect(x, y, (int) (x + obj.bmp.getWidth() / scaleX), (int) (y + obj.bmp.getHeight() / scaleY)), obj.paint);

            if (obj.item) {
                if (obj.itemState) {
                    if ((int) obj.getY() <= obj.motion1.y + 10)
                        obj.transformY(-translate(mult1 - (mult0 - ((int) obj.getY() - obj.motion1.y))));
                    else
                        obj.transformY(-translate(mult0));
                    if ((int) obj.getY() <= obj.motion1.y) obj.itemState = false;
                } else {
                    if ((int) obj.getY() >= obj.motion2.y - 10) {
                        obj.transformY(translate(mult1 - (mult0 - (obj.motion2.y - (int) obj.getY()))));
                    } else
                        obj.transformY(translate(mult0));
                    if ((int) obj.getY() >= obj.motion2.y) obj.itemState = true;
                }
            }
        }
        if (obj.bitmapObject != null) {
            drawSubBitmapObject(canvas, obj, obj.bitmapObject);
        }
        if (obj.secondBitmapObject != null) {
            drawSubBitmapObject(canvas, obj, obj.secondBitmapObject);
        }
    }

    public static void drawTextObject(Canvas canvas, int priority, TextObject obj, Paint paint) {
        if (obj.priority == priority && (obj.getPSX() + obj.paint.measureText(obj.text)) * scaleX > level.camera.getPSX() && obj.getPSX() * scaleX < level.camera.getPSX() +
                level.camera.w && (obj.getPSY() + obj.paint.getTextSize()) * scaleY > level.camera.getPSY() && obj.getPSY() * scaleY < level.camera.getPSY() + level.camera.h) {

            int x = (int) (obj.getPSX() - level.camera.getPSX() / scaleX);
            int y = (int) (obj.getPSY() - level.camera.getPSY() / scaleY);

            canvas.drawText(obj.text, x, y, paint);
        }
    }

    public static void drawTextObject(Canvas canvas, int priority, TextObject obj) {
        drawTextObject(canvas, priority, obj, obj.paint);
    }

    public static void draw(Canvas canvas, int priority) {
        Iterator<Map.Entry<String, BitmapObject>> itBitmap = level.bitmapObjects.entrySet().iterator();
        while (itBitmap.hasNext()) {
            Map.Entry<String, BitmapObject> entry = itBitmap.next();
            BitmapObject obj = entry.getValue();

            if (obj.removing) {
                if (obj.collision && obj.globalCollision) {
                    for (int sy = 0; sy < obj.bmp.getHeight(); sy++) {
                        for (int sx = 0; sx < obj.bmp.getWidth(); sx++)
                            level.collMap[(int) obj.getPSX() + sx][(int) obj.getPSY() + sy] = null;
                    }
                }

                itBitmap.remove();
                if (obj.collision && obj.globalCollision) level.redrawCollisionMap();
            } else
                drawBitmapObject(canvas, priority, entry.getValue());
        }
        Iterator<Map.Entry<String, EnemyObject>> itEnemy = level.enemyObjects.entrySet().iterator();
        while (itEnemy.hasNext()) {
            Map.Entry<String, EnemyObject> entry = itEnemy.next();
            EnemyObject obj = entry.getValue();

            if (obj.removing) {
                itEnemy.remove();
            } else {
                if (checkObjectVisibility(obj.object)) {
                    drawBitmapObject(canvas,obj.object.priority,obj.object);
                }
            }
        }

        for (MultipleBitmapObject obj0 : level.multipleBitmapObjects.values()) {
            for (BitmapObject obj1 : obj0.objects) {
                drawBitmapObject(canvas, priority, obj1);
            }
        }
        for (BigTextObject obj0 : level.bigTextObjects.values()) {
            for (TextObject obj1 : obj0.objects) {
                drawTextObject(canvas, priority, obj1);
            }
        }

        Iterator<Map.Entry<String, TextObject>> it2 = level.textObjects.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry<String, TextObject> entry = it2.next();
            TextObject obj = entry.getValue();

            if (obj.removing) {
                if (obj.collision && obj.globalCollision) {
                    for (int sy = 0; sy < obj.paint.getTextSize(); sy++) {
                        for (int sx = 0; sx < obj.paint.measureText(obj.text); sx++)
                            level.collMap[(int) obj.getPSX() + sx][(int) obj.getPSY() + sy] = null;
                    }
                }

                it2.remove();
                if (obj.collision && obj.globalCollision) level.redrawCollisionMap();
            } else
                drawTextObject(canvas, priority, entry.getValue());
        }


        for (ProgressBar obj : level.progressBars.values()) {
            obj.draw(canvas);
        }
        for (ButtonObject obj : level.buttonObjects.values()) {
            canvas.drawBitmap(obj.base.bmp, new Rect(0, 0, obj.base.bmp.getWidth(), obj.base.bmp.getHeight()),
                    new Rect((int) (obj.base.getPSX() / scaleX), (int) (obj.base.getPSY() / scaleY), (int) ((obj.base.getPSX() + obj.base.bmp.getWidth()) / scaleX),
                            (int) ((obj.base.getPSY() + obj.base.bmp.getHeight()) / scaleY)), obj.base.paint);
            canvas.drawText(obj.title.text, obj.title.getPSX(), obj.title.getPSY(), obj.title.paint);
        }

        if (level.objectsPriority > priority)
            draw(canvas, priority + 1);
    }

    public static void onDraw(Canvas canvas, int _currFPS) {
        currFPS = _currFPS;

        try {
            Graphics.checkDownCollision(currFPS, level.fallSpeed);

            for (EnemyObject enemy : level.enemyObjects.values()) {
                if ((int) enemy.object.getPSX() + enemy.object.bmp.getWidth() > level.camera.getPSX() && (int) enemy.object.getPSX() < level.camera.getPSX() +
                        level.camera.w && (int) enemy.object.getPSY() + enemy.object.bmp.getHeight() > level.camera.getPSY() && (int) enemy.object.getPSY() < level.camera.getPSY() + level.camera.h) {
                    enemy.go();
                }
            }

            Graphics.draw(canvas, 0);
        } catch (Exception e) {

        }
    }

    //EVENT HANDLERS
    public static boolean checkClick(MotionEvent event) throws IOException {
        int count = event.getPointerCount();
        int index = event.getActionIndex();
        int ID = event.getPointerId(index);
        int x2 = (int) (event.getX(index) * scaleX);
        int y2 = (int) (event.getY(index) * scaleY);

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                for (ButtonObject obj : level.buttonObjects.values()) {
                    if (obj.checkClick(x2, y2)) {
                        obj.setActive(true);
                        obj.touchID = 0;
                        obj.method.onDown();
                        break;
                    }
                }
                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                for (ButtonObject obj : level.buttonObjects.values()) {
                    if (obj.checkClick(x2, y2) && obj.touchID == -1) {
                        obj.setActive(true);
                        obj.touchID = ID;
                        obj.method.onDown();
                        break;
                    }
                }
                break;

            case MotionEvent.ACTION_UP:
                boolean state = false;

                for (ButtonObject obj : level.buttonObjects.values()) {
                    if (obj.checkClick(x2, y2) && obj.touchID == ID) {
                        obj.setActive(false);
                        obj.touchID = -1;
                        obj.method.onClick();
                        break;
                    } else state = true;
                }

                if (state) {
                    for (ButtonObject obj : level.buttonObjects.values()) {
                        if (obj.touchID == ID) {
                            obj.setActive(false);
                            obj.touchID = -1;
                            obj.method.onUp();
                            break;
                        }
                    }
                }
                break;

            case MotionEvent.ACTION_POINTER_UP:
                boolean state2 = false;

                for (ButtonObject obj : level.buttonObjects.values()) {
                    if (obj.checkClick(x2, y2) && obj.touchID == ID) {
                        obj.setActive(false);
                        obj.touchID = -1;
                        obj.method.onClick();
                        break;
                    } else state2 = true;
                }

                if (state2) {
                    for (ButtonObject obj : level.buttonObjects.values()) {
                        if (obj.touchID == ID) {
                            obj.setActive(false);
                            obj.touchID = -1;
                            obj.method.onUp();
                            break;
                        }
                    }
                }
                break;

            case MotionEvent.ACTION_MOVE:
                for (int i = 0; i < count; i++) {
                    for (ButtonObject obj : level.buttonObjects.values()) {
                        if (obj.touchID == event.getPointerId(i)) {
                            if (obj.checkClick((int) event.getX(i), (int) event.getY(i))) {
                                obj.setActive(true);
                                break;
                            } else {
                                obj.setActive(false);
                                break;
                            }
                        }
                    }
                }
                break;
        }

        return false;
    }

    public static void onFocusChanged(boolean hasFocus) {
        if (hasFocus) {
            MainWindow.getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}