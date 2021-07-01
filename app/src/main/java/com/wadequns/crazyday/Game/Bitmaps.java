package com.wadequns.crazyday.Game;

import android.graphics.Bitmap;

import com.wadequns.crazyday.Engine.Main.Graphics;

public final class Bitmaps {
    //UI
    public static Bitmap ui_button;
    public static Bitmap ui_medium_button;
    public static Bitmap ui_large_button;
    public static Bitmap ui_square_button;
    public static Bitmap ui_orientation_button;
    public static Bitmap ui_menu_background;
    public static Bitmap ui_level_complete_plate_0;
    public static Bitmap ui_level_complete_plate_1;
    public static Bitmap ui_level_complete_plate_2;

    //GAME
    public static Bitmap game_arrow_left;
    public static Bitmap game_arrow_right;
    public static Bitmap game_arrow_up;
    public static Bitmap game_throw;
    public static Bitmap game_button_close;
    public static Bitmap game_person;
    public static Bitmap game_person_flipped;
    public static Bitmap game_person_noitem;
    public static Bitmap game_person_noitem_flipped;
    public static Bitmap game_person_left;
    public static Bitmap game_person_left_flipped;
    public static Bitmap game_person_left_noitem;
    public static Bitmap game_person_left_noitem_flipped;
    public static Bitmap game_person_right;
    public static Bitmap game_person_right_flipped;
    public static Bitmap game_person_right_noitem;
    public static Bitmap game_person_right_noitem_flipped;
    public static Bitmap game_person_red_hat;
    public static Bitmap game_coin;
    public static Bitmap game_diamond;
    public static Bitmap game_heart;
    public static Bitmap game_heart_half;
    public static Bitmap game_heart_full;

    //LOADING MENU
    public static Bitmap ui_loading_person_left;
    public static Bitmap ui_loading_person_right;
    public static Bitmap ui_loading_fly_left;
    public static Bitmap ui_loading_fly_right;

    //LOAD BACKGROUND
    public static void loadLoadingBitmaps() {
        ui_menu_background = Graphics.processBitmap("UI/menu_background.png", 1280, 720);
        ui_loading_person_left = Graphics.processBitmap("Game/Person/person_left.png", 232, 400);
        ui_loading_person_right = Graphics.flip(Graphics.processBitmap("Game/Person/person_left.png", 232, 400));
        ui_loading_fly_left = Graphics.flip(Graphics.processBitmap("Game/Person/fly.png", 120, 70));
        ui_loading_fly_right = Graphics.processBitmap("Game/Person/fly.png", 120, 70);
    }

    //LOAD UI BITMAPS
    public static void loadUIBitmaps() {
        ui_button = Graphics.processBitmap("UI/button_0.png", 300, 75);
        ui_medium_button = Graphics.processBitmap("UI/button_0.png", 375, 92);
        ui_large_button = Graphics.processBitmap("UI/button_0.png", 450, 110);
        ui_square_button = Graphics.processBitmap("UI/button_1.png", 100, 100);
        ui_orientation_button = Graphics.processBitmap("UI/orientation_button.png", 100, 100);
        ui_level_complete_plate_0 = Graphics.processBitmap("Game/level_complete_plate_0.png", 1200, 500);
        ui_level_complete_plate_1 = Graphics.processBitmap("Game/level_complete_plate_0.png", 1200, 120);
        ui_level_complete_plate_2 = Graphics.processBitmap("Game/level_complete_plate_1.png", 1200, 675);
    }

    //LOAD GAME BITMAPS
    public static void loadGameBitmaps() {
        game_arrow_left = Graphics.processBitmap("UI/arrow_left.png", 100, 100);
        game_arrow_right = Graphics.processBitmap("UI/arrow_right.png", 100, 100);
        game_arrow_up = Graphics.processBitmap("UI/arrow_up.png", 100, 100);
        game_throw = Graphics.processBitmap("UI/throw.png", 100, 100);
        game_button_close = Graphics.processBitmap("UI/close.bmp", 42, 42);
        game_person = Graphics.processBitmap("Game/Person/person.png", 116, 200);
        game_person_flipped = Graphics.flip(Graphics.processBitmap("Game/Person/person.png", 116, 200));
        game_person_noitem = Graphics.processBitmap("Game/Person/person_noitem.png", 116, 200);
        game_person_noitem_flipped = Graphics.flip(Graphics.processBitmap("Game/Person/person_noitem.png", 116, 200));
        game_person_left = Graphics.processBitmap("Game/Person/person_left.png", 116, 200);
        game_person_left_flipped = Graphics.flip(Graphics.processBitmap("Game/Person/person_left.png", 116, 200));
        game_person_left_noitem = Graphics.processBitmap("Game/Person/person_left_noitem.png", 116, 200);
        game_person_left_noitem_flipped = Graphics.flip(Graphics.processBitmap("Game/Person/person_left_noitem.png", 116, 200));
        game_person_right = Graphics.processBitmap("Game/Person/person_right.png", 116, 200);
        game_person_right_flipped = Graphics.flip(Graphics.processBitmap("Game/Person/person_right.png", 116, 200));
        game_person_right_noitem = Graphics.processBitmap("Game/Person/person_right_noitem.png", 116, 200);
        game_person_right_noitem_flipped = Graphics.flip(Graphics.processBitmap("Game/Person/person_right_noitem.png", 116, 200));
        game_person_red_hat = Graphics.processBitmap("Game/Items/red_hat.png", 41, 19);
        game_coin = Graphics.processBitmap("Game/Items/coin.png", 64, 64);
        game_diamond = Graphics.processBitmap("Game/Items/diamond.png", 64, 64);
        game_heart = Graphics.processBitmap("Game/Heart/heart.png", 38, 33);
        game_heart_half = Graphics.processBitmap("Game/Heart/half_heart.png", 38, 33);
        game_heart_full = Graphics.processBitmap("Game/Heart/full_heart.png", 38, 33);
    }
}
