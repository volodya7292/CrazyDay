package ru.mg.crazyday.Game.Menu;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import ru.mg.crazyday.Engine.Main.Camera;
import ru.mg.crazyday.Engine.Main.ClickHandler;
import ru.mg.crazyday.Engine.Main.Graphics;
import ru.mg.crazyday.Engine.Game.Level;
import ru.mg.crazyday.Game.Bitmaps;
import ru.mg.crazyday.Game.Fonts;
import ru.mg.crazyday.Game.Lang;
import ru.mg.crazyday.MainActivity;

public class CompleteMenu {
    public static Level level;

    public static void init(String name, String backgroundPath, int coins, int secrets, boolean death) {
        MainActivity.levelName = "CompleteMenu";

        level = new Level(1280, 720, 3, 500, new Camera(new Point((int)Graphics.scaleWidth(640), (int)Graphics.scaleHeight(360)), MainActivity.width, MainActivity.height));

        Paint text = Graphics.textPaint(Color.CYAN, 100);
        Paint text2 = Graphics.textPaint(Color.GREEN, 80);

        level.addBitmapObject("Background", new Point(640, 360), 0, Graphics.processBitmap(backgroundPath, 1280, 720));
        level.addBitmapObject("Plate", new Point(640, 432), 1, Bitmaps.ui_level_complete_plate_0);
        level.addBitmapObject("Plate2", new Point(640, 100), 1, Bitmaps.ui_level_complete_plate_1);

        if (death)
            level.addTextObject("Congrat", Lang.complete_death, new Point((int)(level.camera.getX() * Graphics.scaleX), 0), 1, text);
        else
            level.addTextObject("Congrat", Lang.complete_congrat, new Point((int)(level.camera.getX() * Graphics.scaleX), 0), 1, text);

        level.addTextObject("Coins", Lang.complete_coins + coins, new Point(0, 0), 1, text2);
        level.addTextObject("Secrets", Lang.complete_secrets + secrets, new Point(0, 0), 1, text2);

        Rect bounds = new Rect();
        level.textObjects.get("Coins").paint.getTextBounds(Lang.complete_coins, 0, Lang.complete_coins.length(), bounds);
        level.textObjects.get("Coins").setNewPSX(level.bitmapObjects.get("Plate").getPSX() + Graphics.scaleWidth(50));
        level.textObjects.get("Coins").setNewPSY(level.bitmapObjects.get("Plate").getPSY() + Graphics.scaleHeight(50) + bounds.height());
        level.textObjects.get("Secrets").paint.getTextBounds(Lang.complete_secrets, 0, Lang.complete_secrets.length(), bounds);
        level.textObjects.get("Secrets").setNewPSX(level.bitmapObjects.get("Plate").getPSX() + Graphics. scaleWidth(50));
        level.textObjects.get("Secrets").setNewPSY(level.textObjects.get("Coins").getPSY() + Graphics.scaleHeight(50) + bounds.height());
        level.textObjects.get("Congrat").setNewY((int)level.bitmapObjects.get("Plate2").getY());

        level.addButtonObject("Continue", Lang.button_more, new Point(
                (int)(((level.bitmapObjects.get("Plate").getPSX() + Bitmaps.ui_level_complete_plate_0.getWidth()) -
                        Bitmaps.ui_button.getWidth() / 2) * Graphics.scaleX) - 20,
                (int)(((level.bitmapObjects.get("Plate").getPSY() + Bitmaps.ui_level_complete_plate_0.getHeight()) -
                        Bitmaps.ui_button.getHeight() / 2) * Graphics.scaleY) - 20),
                0, 60, Bitmaps.ui_button, Bitmaps.ui_button, Fonts.completeMenuButtonText, new ClickHandler() {
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

            }});

        Graphics.loadLevel(level);
    }

    public static void onDraw() {

    }

    public static void unload() {
        level = null;
    }
}
