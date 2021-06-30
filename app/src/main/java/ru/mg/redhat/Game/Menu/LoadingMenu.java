package ru.mg.redhat.Game.Menu;

import android.graphics.Color;
import android.graphics.Point;

import ru.mg.redhat.Engine.Main.Camera;
import ru.mg.redhat.Engine.Main.Graphics;
import ru.mg.redhat.Engine.Game.Level;
import ru.mg.redhat.Game.Bitmaps;
import ru.mg.redhat.Game.Fonts;
import ru.mg.redhat.Game.Lang;
import ru.mg.redhat.MainActivity;

public class LoadingMenu {
    public static Level level;

    public static void init(boolean loadingBar) {
        MainActivity.levelName = "LoadingMenu";

        level = new Level(1280, 720, 3, 500, new Camera(new Point((int) Graphics.scaleWidth(640), (int)Graphics.scaleHeight(360)), MainActivity.width, MainActivity.height));

        level.addBitmapObject("Background", new Point(640, 360), 0, ru.mg.redhat.Game.Bitmaps.ui_menu_background);
        level.addBitmapObject("PersonLeft", new Point(200, 360), 1, Bitmaps.ui_loading_person_left);
        level.addBitmapObject("FlyLeft", new Point(180, 170), 2, Bitmaps.ui_loading_fly_left);
        level.addBitmapObject("PersonRight", new Point(1080, 360), 1, Bitmaps.ui_loading_person_right);
        level.addBitmapObject("FlyRight", new Point(1100, 170), 2, Bitmaps.ui_loading_fly_right);
        level.addTextObject("Title", Lang.title_loading, new Point(640, 360), 0, Fonts.loadingText);

        if (loadingBar)
            level.addProgressBar("loadingBar", 200, 600, 880, 40, 1, 0, Color.BLACK);

        Graphics.loadLevel(level);
    }

    public static void onDraw() {

    }

    public static void unload() {
        level = null;
    }
}
