package com.wadequns.crazyday.Game;

import com.wadequns.crazyday.Engine.Main.Sound;

public final class Sounds {
    public static int game_item;
    public static int game_coin;
    public static int game_plate;
    public static int game_hit;
    public static int game_ehit;
    public static int game_healthcare;

    public static void init() {
        game_item = Sound.loadSound("Game/sounds/item.ogg");
        game_coin = Sound.loadSound("Game/sounds/coin.ogg");
        game_plate = Sound.loadSound("Game/sounds/plate.ogg");
        game_hit = Sound.loadSound("Game/sounds/hit.ogg");
        game_ehit = Sound.loadSound("Game/sounds/ehit.ogg");
        game_healthcare = Sound.loadSound("Game/sounds/healthcare.ogg");
    }
}
