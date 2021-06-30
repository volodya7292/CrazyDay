package ru.mg.redhat.Game.Menu;

import android.graphics.Point;

import ru.mg.redhat.Engine.Main.Camera;
import ru.mg.redhat.Engine.Main.ClickHandler;
import ru.mg.redhat.Engine.Main.Graphics;
import ru.mg.redhat.Engine.Game.Level;
import ru.mg.redhat.Game.Bitmaps;
import ru.mg.redhat.Game.Fonts;
import ru.mg.redhat.Game.Lang;
import ru.mg.redhat.Game.LevelLoader;
import ru.mg.redhat.MainActivity;

public class LevelMenu {
    public static Level level;
    public static LevelLoader loader;

    public static void init() {
        MainActivity.levelName = "LevelMenu";

        level = new Level(1280, 720, 3, 500, new Camera(new Point((int) Graphics.scaleWidth(640), (int)Graphics.scaleHeight(360)), MainActivity.width, MainActivity.height));

        level.addBitmapObject("Plate", new Point(640, 360), 1, ru.mg.redhat.Game.Bitmaps.ui_level_complete_plate_2);

        level.addTextObject("title", Lang.title_levels, new Point(200, 90), 1, Fonts.buttonText);
        level.textObjects.get("title").setPSX(90);

        level.addButtonObject("1", "1", new Point(206, 210), 0, 100, Bitmaps.ui_square_button, Bitmaps.ui_square_button, Fonts.levelMenuButtonText, new ClickHandler() {
            @Override
            public void onClick() {
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

            }

            @Override
            public void onDown() {

            }

            @Override
            public void onUp() {

            }
        });
        level.addButtonObject("Prev", Lang.button_prev, new Point(1050, 90), 0, 80, ru.mg.redhat.Game.Bitmaps.ui_button, ru.mg.redhat.Game.Bitmaps.ui_button, Fonts.pauseButtonText, new ClickHandler() {
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

    private static void loadLevel(String path) {
        unload();

        loader = new LevelLoader(path, MainActivity.width, MainActivity.height);
        loader.event = new LevelLoader.loadEvent() {
            @Override
            public void loaded() {

            }
        };
        loader.initAsync();
    }

    public static void onDraw() {

    }

    public static void unload() {
        level = null;
    }
}
