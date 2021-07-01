package com.wadequns.crazyday.Game.Menu;

import android.graphics.Color;
import android.graphics.Point;

import com.wadequns.crazyday.Engine.Game.Level;
import com.wadequns.crazyday.Engine.Main.Camera;
import com.wadequns.crazyday.Engine.Main.Graphics;
import com.wadequns.crazyday.Game.Bitmaps;
import com.wadequns.crazyday.Game.Fonts;
import com.wadequns.crazyday.Game.Lang;
import com.wadequns.crazyday.MainActivity;

public class LoadingMenu {
    public static Level level;

    public static void init(boolean loadingBar) {
        MainActivity.levelName = "LoadingMenu";

        level = new Level(1280, 720, 3, 500, new Camera(new Point(640, 360)));

        level.addBitmapObject("Background", new Point(640, 360), 0, Bitmaps.ui_menu_background);
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
