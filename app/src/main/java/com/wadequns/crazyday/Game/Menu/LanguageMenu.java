package com.wadequns.crazyday.Game.Menu;

import android.graphics.Point;

import com.wadequns.crazyday.Engine.Game.Level;
import com.wadequns.crazyday.Engine.Main.Camera;
import com.wadequns.crazyday.Engine.Main.ClickHandler;
import com.wadequns.crazyday.Engine.Main.Graphics;
import com.wadequns.crazyday.Game.Languages.English;
import com.wadequns.crazyday.Game.Languages.France;
import com.wadequns.crazyday.Game.Languages.German;
import com.wadequns.crazyday.Game.Languages.Russian;
import com.wadequns.crazyday.Game.Languages.Spanish;
import com.wadequns.crazyday.Game.Bitmaps;
import com.wadequns.crazyday.Game.Fonts;
import com.wadequns.crazyday.Game.Lang;
import com.wadequns.crazyday.MainActivity;

public class LanguageMenu {
    public static Level level;

    public static void init() {
        MainActivity.levelName = "LanguageMenu";

        level = new Level(1280, 720, 3, 500, new Camera(new Point(640, 360)));

        level.addBitmapObject("Background", new Point(640, 360), 0, Bitmaps.ui_menu_background);
        level.addBitmapObject("Plate", new Point(640, 360), 1, Bitmaps.ui_level_complete_plate_2);

        level.addButtonObject("English", "English", new Point(640, 260), 0, 60, Bitmaps.ui_medium_button, Bitmaps.ui_medium_button, Fonts.buttonText, new ClickHandler() {
            @Override
            public void onClick() {
                MainActivity.prefsEditor.putString("Language", "English");
                English.init();
                mainMenu();
            }

            @Override
            public void onDown() {

            }

            @Override
            public void onUp() {

            }
        });
        level.addButtonObject("Russian", "Русский", new Point(250, 260), 0, 60, Bitmaps.ui_medium_button, Bitmaps.ui_medium_button, Fonts.buttonText, new ClickHandler() {
            @Override
            public void onClick() {
                MainActivity.prefsEditor.putString("Language", "Russian");
                Russian.init();
                mainMenu();
            }

            @Override
            public void onDown() {

            }

            @Override
            public void onUp() {

            }
        });
        level.addButtonObject("German", "Deutsch", new Point(1030, 260), 0, 60, Bitmaps.ui_medium_button, Bitmaps.ui_medium_button, Fonts.buttonText, new ClickHandler() {
            @Override
            public void onClick() {
                MainActivity.prefsEditor.putString("Language", "German");
                German.init();
                mainMenu();
            }

            @Override
            public void onDown() {

            }

            @Override
            public void onUp() {

            }
        });
        level.addButtonObject("France", "Français", new Point(640, 100), 0, 60, Bitmaps.ui_medium_button, Bitmaps.ui_medium_button, Fonts.buttonText, new ClickHandler() {
            @Override
            public void onClick() {
                MainActivity.prefsEditor.putString("Language", "France");
                France.init();
                mainMenu();
            }

            @Override
            public void onDown() {

            }

            @Override
            public void onUp() {

            }
        });
        level.addButtonObject("Spanish", "Español", new Point(640, 420), 0, 60, Bitmaps.ui_medium_button, Bitmaps.ui_medium_button, Fonts.buttonText, new ClickHandler() {
            @Override
            public void onClick() {
                MainActivity.prefsEditor.putString("Language", "Spanish");
                Spanish.init();
                mainMenu();
            }

            @Override
            public void onDown() {

            }

            @Override
            public void onUp() {

            }
        });
        level.addButtonObject("Prev", Lang.button_prev, new Point(640, 610), 0, 60, Bitmaps.ui_button, Bitmaps.ui_button, Fonts.buttonText, new ClickHandler() {
            @Override
            public void onClick() {
                MainActivity.prefsEditor.commit();
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

    public static void mainMenu() {
        MainActivity.prefsEditor.commit();
        unload();
        MainMenu.init();
    }
}
