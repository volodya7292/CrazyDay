package ru.mg.redhat.Game.Languages;

import ru.mg.redhat.Game.Lang;

public final class German {
    public static void init() {
        Lang.complete_coins = "Münzen: ";
        Lang.complete_secrets = "Geheimnisse: ";
        Lang.complete_congrat = "Das Niveau ist bestanden!";
        Lang.complete_death = "Sie starb!";
        Lang.button_more = "Fortsetzen";
        Lang.title_redhat = "Verrückter Tag";
        Lang.title_loading = "Laden...";
        Lang.title_levels = "Das Niveau";
        Lang.button_play = "Spielen";
        Lang.button_help = "Hilfe";
        Lang.button_language = "Sprache";
        Lang.button_exit = "Ausfahrt";
        Lang.button_prev = "Zurück";
        Lang.button_general_menu = "Hauptmenü";
        Lang.button_pause_menu = "Menü";
        Lang.button_restart = "Zuerst";

        Lang.help = new String[] {
                "Du bist mit 8 Stufen versehen, an denen du kannst:",
                "————————————————————————————————",
                "—  Münzen Sammeln",
                "—  Find Secrets",
                "—  Die Zugewiesenen Aufgaben Ausführen"
        };
    }
}
