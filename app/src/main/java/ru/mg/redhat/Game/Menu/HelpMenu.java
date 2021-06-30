package ru.mg.redhat.Game.Menu;

import android.graphics.Point;

import ru.mg.redhat.Engine.Main.Camera;
import ru.mg.redhat.Engine.Main.ClickHandler;
import ru.mg.redhat.Engine.Main.Graphics;
import ru.mg.redhat.Engine.Game.Level;
import ru.mg.redhat.Game.Bitmaps;
import ru.mg.redhat.Game.Fonts;
import ru.mg.redhat.Game.Lang;
import ru.mg.redhat.MainActivity;

public class HelpMenu {
    public static Level level;

    //Initializing level
    public static void init() {
        MainActivity.levelName = "HelpMenu";

        level = new Level(1280, 720, 3, 500, new Camera(new Point((int) Graphics.scaleWidth(640), (int)Graphics.scaleHeight(360)), MainActivity.width, MainActivity.height));

        level.addBitmapObject("Background", new Point(640, 360), 0, Bitmaps.ui_menu_background);
        level.addBitmapObject("Plate", new Point(640, 360), 1, Bitmaps.ui_level_complete_plate_2);
        level.addBitmapObject("Coin", new Point(1100, 210), 2, Bitmaps.game_coin);
        //level.addBitmapObject("Hat", new Point(1100, 360), 2, Graphics.resizeBitmap(Bitmaps.game_person_red_hat, 82, 38));
        //level.addBitmapObject("Diamond", new Point(1100, 290), 2, Graphics.resizeBitmap(Bitmaps.game_diamond, 60, 60));
        level.addBigTextObject("Help", Lang.help, 30, 1, new Point(90, 120), Fonts.helpText);
        level.addButtonObject("Prev", Lang.button_prev, new Point(640, 610), 0, 60, Bitmaps.ui_button, Bitmaps.ui_button, Fonts.buttonText, new ClickHandler() {
            @Override
            public void onClick() {
                unload();
                MainMenu.init();
            }

            @Override
            public void onDown() {

            }

            @Override
            public void onUp() {

            }
        });

        Graphics.loadLevel(level);
    }

    public static void onDraw() {

    }

    public static void unload() {
        level = null;
    }
}
