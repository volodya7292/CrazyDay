package com.wadequns.crazyday.Game.Menu;

import android.graphics.Point;

import com.wadequns.crazyday.Engine.Game.Level;
import com.wadequns.crazyday.Engine.Main.Camera;
import com.wadequns.crazyday.Engine.Main.ClickHandler;
import com.wadequns.crazyday.Engine.Main.Graphics;
import com.wadequns.crazyday.Game.Bitmaps;
import com.wadequns.crazyday.Game.Fonts;
import com.wadequns.crazyday.Game.Lang;
import com.wadequns.crazyday.Game.LevelLoader;
import com.wadequns.crazyday.MainActivity;

public class LevelMenu {
    public static Level level;
    public static LevelLoader loader;

    public static void init() {
        MainActivity.levelName = "LevelMenu";

        level = new Level(1280, 720, 3, 500, new Camera(new Point(640, 360)));

        level.addBitmapObject("Background", new Point(640, 360), 0, Bitmaps.ui_menu_background);
        level.addBitmapObject("Plate", new Point(640, 360), 1, Bitmaps.ui_level_complete_plate_2);

        level.addTextObject("title", Lang.title_levels, new Point(200, 90), 1, Fonts.buttonText);
        level.textObjects.get("title").setPSX(90);

        level.addButtonObject("1", "1", new Point(206, 210), 0, 100, Bitmaps.ui_square_button, Bitmaps.ui_square_button, Fonts.levelMenuButtonText, new ClickHandler() {
            @Override
            public void onClick() {
                MainActivity.currLevelNumber = 1;
                loadLevel("Game/Level1/level.glevel");
            }

            @Override
            public void onDown() {

            }

            @Override
            public void onUp() {

            }
        });
        level.addButtonObject("2", "2", new Point(422, 210), 0, 100, Bitmaps.ui_square_button, Bitmaps.ui_square_button, Fonts.levelMenuButtonText, new ClickHandler() {
            @Override
            public void onClick() {
                MainActivity.currLevelNumber = 2;
                loadLevel("Game/Level2/level.glevel");
            }

            @Override
            public void onDown() {

            }

            @Override
            public void onUp() {

            }
        });
        level.addButtonObject("3", "3", new Point(638, 210), 0, 100, Bitmaps.ui_square_button, Bitmaps.ui_square_button, Fonts.levelMenuButtonText, new ClickHandler() {
            @Override
            public void onClick() {
                MainActivity.currLevelNumber = 3;
                loadLevel("Game/Level3/level.glevel");
            }

            @Override
            public void onDown() {

            }

            @Override
            public void onUp() {

            }
        });
        level.addButtonObject("4", "4", new Point(854, 210), 0, 100, Bitmaps.ui_square_button, Bitmaps.ui_square_button, Fonts.levelMenuButtonText, new ClickHandler() {
            @Override
            public void onClick() {
                MainActivity.currLevelNumber = 4;
                loadLevel("Game/Level4/level.glevel");
            }

            @Override
            public void onDown() {

            }

            @Override
            public void onUp() {

            }
        });
        level.addButtonObject("5", "5", new Point(1070, 210), 0, 100, Bitmaps.ui_square_button, Bitmaps.ui_square_button, Fonts.levelMenuButtonText, new ClickHandler() {
            @Override
            public void onClick() {
                MainActivity.currLevelNumber = 5;
                loadLevel("Game/Level5/level.glevel");
            }

            @Override
            public void onDown() {

            }

            @Override
            public void onUp() {

            }
        });
        level.addButtonObject("6", "6", new Point(206, 340), 0, 100, Bitmaps.ui_square_button, Bitmaps.ui_square_button, Fonts.levelMenuButtonText, new ClickHandler() {
            @Override
            public void onClick() {
                MainActivity.currLevelNumber = 6;
                loadLevel("Game/Level6/level.glevel");
            }

            @Override
            public void onDown() {

            }

            @Override
            public void onUp() {

            }
        });
        level.addButtonObject("7", "7", new Point(422, 340), 0, 100, Bitmaps.ui_square_button, Bitmaps.ui_square_button, Fonts.levelMenuButtonText, new ClickHandler() {
            @Override
            public void onClick() {
                MainActivity.currLevelNumber = 7;
                loadLevel("Game/Level7/level.glevel");
            }

            @Override
            public void onDown() {

            }

            @Override
            public void onUp() {

            }
        });
        level.addButtonObject("8", "8", new Point(638, 340), 0, 100, Bitmaps.ui_square_button, Bitmaps.ui_square_button, Fonts.levelMenuButtonText, new ClickHandler() {
            @Override
            public void onClick() {
                MainActivity.currLevelNumber = 8;
                loadLevel("Game/Level8/level.glevel");
            }

            @Override
            public void onDown() {

            }

            @Override
            public void onUp() {

            }
        });
        level.addButtonObject("9", "9", new Point(854, 340), 0, 100, Bitmaps.ui_square_button, Bitmaps.ui_square_button, Fonts.levelMenuButtonText, new ClickHandler() {
            @Override
            public void onClick() {
                MainActivity.currLevelNumber = 9;
                loadLevel("Game/Level9/level.glevel");
            }

            @Override
            public void onDown() {

            }

            @Override
            public void onUp() {

            }
        });
        level.addButtonObject("10", "10", new Point(1070, 340), 0, 100, Bitmaps.ui_square_button, Bitmaps.ui_square_button, Fonts.levelMenuButtonText, new ClickHandler() {
            @Override
            public void onClick() {
                MainActivity.currLevelNumber = 10;
                loadLevel("Game/Level10/level.glevel");
            }

            @Override
            public void onDown() {

            }

            @Override
            public void onUp() {

            }
        });
        level.addButtonObject("11", "11", new Point(206, 470), 0, 100, Bitmaps.ui_square_button, Bitmaps.ui_square_button, Fonts.levelMenuButtonText, new ClickHandler() {
            @Override
            public void onClick() {
                MainActivity.currLevelNumber = 11;
                loadLevel("Game/Level11/level.glevel");
            }

            @Override
            public void onDown() {

            }

            @Override
            public void onUp() {

            }
        });
        level.addButtonObject("12", "12", new Point(422, 470), 0, 100, Bitmaps.ui_square_button, Bitmaps.ui_square_button, Fonts.levelMenuButtonText, new ClickHandler() {
            @Override
            public void onClick() {
                MainActivity.currLevelNumber = 12;
                loadLevel("Game/Level12/level.glevel");
            }

            @Override
            public void onDown() {

            }

            @Override
            public void onUp() {

            }
        });
        level.addButtonObject("13", "13", new Point(638, 470), 0, 100, Bitmaps.ui_square_button, Bitmaps.ui_square_button, Fonts.levelMenuButtonText, new ClickHandler() {
            @Override
            public void onClick() {
                MainActivity.currLevelNumber = 13;
                loadLevel("Game/Level13/level.glevel");
            }

            @Override
            public void onDown() {

            }

            @Override
            public void onUp() {

            }
        });
        level.addButtonObject("14", "14", new Point(854, 470), 0, 100, Bitmaps.ui_square_button, Bitmaps.ui_square_button, Fonts.levelMenuButtonText, new ClickHandler() {
            @Override
            public void onClick() {
                MainActivity.currLevelNumber = 14;
                loadLevel("Game/Level14/level.glevel");
            }

            @Override
            public void onDown() {

            }

            @Override
            public void onUp() {

            }
        });
        level.addButtonObject("15", "15", new Point(1070, 470), 0, 100, Bitmaps.ui_square_button, Bitmaps.ui_square_button, Fonts.levelMenuButtonText, new ClickHandler() {
            @Override
            public void onClick() {
                MainActivity.currLevelNumber = 15;
                loadLevel("Game/Level15/level.glevel");
            }

            @Override
            public void onDown() {

            }

            @Override
            public void onUp() {

            }
        });
        level.addButtonObject("16", "16", new Point(206, 600), 0, 100, Bitmaps.ui_square_button, Bitmaps.ui_square_button, Fonts.levelMenuButtonText, new ClickHandler() {
            @Override
            public void onClick() {
                MainActivity.currLevelNumber = 16;
                loadLevel("Game/Level16/level.glevel");
            }

            @Override
            public void onDown() {

            }

            @Override
            public void onUp() {

            }
        });
        level.addButtonObject("17", "17", new Point(422, 600), 0, 100, Bitmaps.ui_square_button, Bitmaps.ui_square_button, Fonts.levelMenuButtonText, new ClickHandler() {
            @Override
            public void onClick() {
                MainActivity.currLevelNumber = 17;
                loadLevel("Game/Level17/level.glevel");
            }

            @Override
            public void onDown() {

            }

            @Override
            public void onUp() {

            }
        });
        level.addButtonObject("18", "18", new Point(638, 600), 0, 100, Bitmaps.ui_square_button, Bitmaps.ui_square_button, Fonts.levelMenuButtonText, new ClickHandler() {
            @Override
            public void onClick() {
                MainActivity.currLevelNumber = 18;
                loadLevel("Game/Level18/level.glevel");
            }

            @Override
            public void onDown() {

            }

            @Override
            public void onUp() {

            }
        });
        level.addButtonObject("19", "19", new Point(854, 600), 0, 100, Bitmaps.ui_square_button, Bitmaps.ui_square_button, Fonts.levelMenuButtonText, new ClickHandler() {
            @Override
            public void onClick() {
                MainActivity.currLevelNumber = 19;
                loadLevel("Game/Level19/level.glevel");
            }

            @Override
            public void onDown() {

            }

            @Override
            public void onUp() {

            }
        });
        level.addButtonObject("20", "20", new Point(1070, 600), 0, 100, Bitmaps.ui_square_button, Bitmaps.ui_square_button, Fonts.levelMenuButtonText, new ClickHandler() {
            @Override
            public void onClick() {
                MainActivity.currLevelNumber = 20;
                loadLevel("Game/Level20/level.glevel");
            }

            @Override
            public void onDown() {

            }

            @Override
            public void onUp() {

            }
        });
        level.addButtonObject("Prev", Lang.button_prev, new Point(1050, 90), 0, 80, Bitmaps.ui_button, Bitmaps.ui_button, Fonts.pauseButtonText, new ClickHandler() {
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

        MainActivity.prefsEditor.putBoolean("1-Active", true);
        MainActivity.prefsEditor.commit();

        if (!MainActivity.PROP_ALL_LEVELS) {
            for (int i = 1; i < 21; i++) {
                level.buttonObjects.get(String.valueOf(i)).enabled = MainActivity.prefs.getBoolean(i + "-Active", false);
            }
        }

        Graphics.loadLevel(level);
    }

    private static void loadLevel(String path) {
        unload();

        loader = new LevelLoader(path, MainActivity.width, MainActivity.height);
        loader.event = () -> {

        };
        loader.initAsync();
    }

    public static void onDraw() {

    }

    public static void unload() {
        level = null;
    }
}
