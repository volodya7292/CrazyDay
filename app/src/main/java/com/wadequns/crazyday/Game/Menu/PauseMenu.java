package com.wadequns.crazyday.Game.Menu;

import android.graphics.Point;

import com.wadequns.crazyday.Engine.Game.Level;
import com.wadequns.crazyday.Engine.Main.Camera;
import com.wadequns.crazyday.Engine.Main.ClickHandler;
import com.wadequns.crazyday.Engine.Main.Graphics;
import com.wadequns.crazyday.Engine.Main.Sound;
import com.wadequns.crazyday.Game.Bitmaps;
import com.wadequns.crazyday.Game.Fonts;
import com.wadequns.crazyday.Game.Lang;
import com.wadequns.crazyday.Game.LevelLoader;
import com.wadequns.crazyday.Game.Main;
import com.wadequns.crazyday.MainActivity;

public class PauseMenu {
    public static Level level;

    public static void init(final Level pausedLevel, final String levelName) {
        level = new Level(1280, 720, 3, 500, new Camera(new Point(640, 360)));

        MainActivity.disableAdView.sendEmptyMessage(0);

        //Background
        level.addBitmapObject("Background", new Point(640, 360), 0, Bitmaps.ui_menu_background);

        //Title
        level.addTextObject("Title", Lang.title_redhat, new Point(640, 120), 0, Fonts.titleText);

        //Buttons
        level.addButtonObject("Continue", Lang.button_more, new Point(640, 300), 0, 60, Bitmaps.ui_large_button, Bitmaps.ui_large_button, Fonts.pauseButtonText, new ClickHandler() {
            @Override
            public void onClick() {
                unload();
                Graphics.loadLevel(pausedLevel);
                MainActivity.levelName = levelName;
                Main.pause = false;
                Main.tmStart();
                Sound.sp.autoResume();
                Sound.mp.start();

                MainActivity.enableAdView.sendEmptyMessage(0);
            }

            @Override
            public void onDown() {

            }

            @Override
            public void onUp() {

            }
        });
        level.addButtonObject("Restart", Lang.button_restart, new Point(640, 420), 0, 60, Bitmaps.ui_large_button, Bitmaps.ui_large_button, Fonts.pauseButtonText, new ClickHandler() {
            @Override
            public void onClick() {
                unload();

                String path = LevelMenu.loader.path;
                int width = LevelMenu.loader.width;
                int height = LevelMenu.loader.height;

                Main.pause = true;
                LevelMenu.loader.unload(true);
                LevelMenu.loader = new LevelLoader(path, width, height);
                LevelMenu.loader.event = new LevelLoader.loadEvent() {
                    @Override
                    public void loaded() {
                        Main.pause = false;
                        Main.tmStart();
                        Sound.sp.autoResume();
                        Sound.mp.start();
                    }
                };
                LevelMenu.loader.initAsync();
            }

            @Override
            public void onDown() {

            }

            @Override
            public void onUp() {

            }
        });
        level.addButtonObject("Exit", Lang.button_general_menu, new Point(640, 540), 0, 60, Bitmaps.ui_large_button, Bitmaps.ui_large_button, Fonts.pauseButtonText, new ClickHandler() {
            @Override
            public void onClick() {
                LevelMenu.loader.unload(false);
                unload();
                MainMenu.init();
                Main.reset();
                Main.pause = false;
            }

            @Override
            public void onDown() {

            }

            @Override
            public void onUp() {

            }
        });

        //Save Game Person X, Y
        MainActivity.prefsEditor.putInt(levelName + "-X", (int)(pausedLevel.bitmapObjects.get("game_person").getX()));
        MainActivity.prefsEditor.putInt(levelName + "-Y", (int)(pausedLevel.bitmapObjects.get("game_person").getY()));
        Graphics.loadLevel(level);
        MainActivity.levelName = "PauseMenu";
    }

    public static void onDraw() {

    }

    public static void unload() {
        level = null;
    }
}
