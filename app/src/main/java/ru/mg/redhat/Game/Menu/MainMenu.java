package ru.mg.redhat.Game.Menu;

import android.content.pm.ActivityInfo;
import android.graphics.Point;

import java.io.IOException;

import ru.mg.redhat.Engine.Main.Camera;
import ru.mg.redhat.Engine.Main.ClickHandler;
import ru.mg.redhat.Engine.Main.Graphics;
import ru.mg.redhat.Engine.Game.Level;
import ru.mg.redhat.Game.Bitmaps;
import ru.mg.redhat.Game.Fonts;
import ru.mg.redhat.Game.Lang;
import ru.mg.redhat.MainActivity;

public class MainMenu {
    public static Level level;

    public static void init() {
        MainActivity.levelName = "MainMenu";
        MainActivity.disableAdView.sendEmptyMessage(0);

        level = new Level(1280, 720, 3, 500, new Camera(new Point((int) Graphics.scaleWidth(640), (int)Graphics.scaleHeight(360)), MainActivity.width, MainActivity.height));

        level.addBitmapObject("Background", new Point(640, 360), 0, Bitmaps.ui_menu_background);

        level.addTextObject("Title", Lang.title_redhat, new Point(640, 120), 0, Fonts.titleText);
        level.addButtonObject("Play", Lang.button_play, new Point(640, 250), 0, 60, Bitmaps.ui_large_button, Bitmaps.ui_large_button, Fonts.buttonText, new ClickHandler() {
            @Override
            public void onClick() {
                unload();
                LevelMenu.init();
            }

            @Override
            public void onDown() {

            }

            @Override
            public void onUp() {

            }
        });
        level.addButtonObject("Help", Lang.button_help, new Point(640, 370), 0, 60, Bitmaps.ui_large_button, Bitmaps.ui_large_button, Fonts.buttonText, new ClickHandler() {
            @Override
            public void onClick() {
                unload();
                HelpMenu.init();
            }

            @Override
            public void onDown() {

            }

            @Override
            public void onUp() {

            }
        });
        level.addButtonObject("Languege", Lang.button_language, new Point(640, 490), 0, 60, Bitmaps.ui_large_button, Bitmaps.ui_large_button, Fonts.buttonText, new ClickHandler() {
            @Override
            public void onClick() {
                unload();
                LanguageMenu.init();
            }

            @Override
            public void onDown() {

            }

            @Override
            public void onUp() {

            }
        });
        level.addButtonObject("Exit", Lang.button_exit, new Point(640, 610), 0, 60, Bitmaps.ui_large_button, Bitmaps.ui_large_button, Fonts.buttonText, new ClickHandler() {
            @Override
            public void onClick() {
                unload();
                System.exit(0);
            }

            @Override
            public void onDown() {

            }

            @Override
            public void onUp() {

            }
        });
        level.addButtonObject("Orientation", "", new Point(100, 610), 0, 60, Bitmaps.ui_orientation_button, Bitmaps.ui_orientation_button, Graphics.anti_alias, new ClickHandler() {
            @Override
            public void onClick() throws IOException {
                if (MainActivity.prefs.getInt("orientation", ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
                    MainActivity.prefsEditor.putInt("orientation", ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
                else
                    MainActivity.prefsEditor.putInt("orientation", ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

                MainActivity.prefsEditor.commit();
                MainActivity.currOrientation = MainActivity.prefs.getInt("orientation", ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
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
