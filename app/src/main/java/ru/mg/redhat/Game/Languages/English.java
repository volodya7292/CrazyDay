package ru.mg.redhat.Game.Languages;

import ru.mg.redhat.Game.Lang;

public final class English {
    public static void init() {
        Lang.complete_coins = "Coins: ";
        Lang.complete_secrets = "Secrets: ";
        Lang.complete_congrat = "The level is passed!";
        Lang.complete_death = "You died!";
        Lang.button_more = "Continue";
        Lang.title_redhat = "Crazy Day";
        Lang.title_loading = "Loading...";
        Lang.title_levels = "Levels";
        Lang.button_play = "Play";
        Lang.button_help = "Help";
        Lang.button_language = "Language";
        Lang.button_exit = "Exit";
        Lang.button_prev = "Back";
        Lang.button_general_menu = "Main Menu";
        Lang.button_pause_menu = "Menu";
        Lang.button_restart = "Start Over";

        Lang.help = new String[] {
                "You are provided with 8 levels at which you can:",
                "————————————————————————————————",
                "—  Collect Coins",
                "—  Find Secrets",
                "—  Perform The Assigned Tasks"
        };
    }
}
