package ru.mg.crazyday.Game.Menu;

import android.graphics.Point;

import ru.mg.crazyday.Engine.Main.Camera;
import ru.mg.crazyday.Engine.Main.ClickHandler;
import ru.mg.crazyday.Engine.Main.Graphics;
import ru.mg.crazyday.Engine.Game.Level;
import ru.mg.crazyday.Game.Bitmaps;
import ru.mg.crazyday.Game.Fonts;
import ru.mg.crazyday.Game.Lang;
import ru.mg.crazyday.Game.Languages.English;
import ru.mg.crazyday.Game.Languages.France;
import ru.mg.crazyday.Game.Languages.German;
import ru.mg.crazyday.Game.Languages.Russian;
import ru.mg.crazyday.Game.Languages.Spanish;
import ru.mg.crazyday.MainActivity;

public class LanguageMenu {
    public static Level level;

    public static void init() {
        MainActivity.levelName = "LanguageMenu";

        level = new Level(1280, 720, 3, 500, new Camera(new Point((int) Graphics.scaleWidth(640), (int)Graphics.scaleHeight(360)), MainActivity.width, MainActivity.height));

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
