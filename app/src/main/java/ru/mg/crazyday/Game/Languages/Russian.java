package ru.mg.crazyday.Game.Languages;

import ru.mg.crazyday.Game.Lang;

public final class Russian {
    public static void init() {
        Lang.complete_coins = "Найдено монет: ";
        Lang.complete_secrets = "Найдено секретов: ";
        Lang.complete_congrat = "Уровень пройден!";
        Lang.complete_death = "Вы умерли!";
        Lang.button_more = "Продолжить";
        Lang.title_redhat = "Сумасшедший день";
        Lang.title_loading = "Загрузка...";
        Lang.title_levels = "Уровни";
        Lang.button_play = "Играть";
        Lang.button_help = "Помощь";
        Lang.button_language = "Язык";
        Lang.button_exit = "Выход";
        Lang.button_prev = "Назад";
        Lang.button_general_menu = "Главное Меню";
        Lang.button_pause_menu = "Меню";
        Lang.button_restart = "Заново";

        Lang.help = new String[] {
                "Вам предоставляется 8 уровней, на которых вы можете:",
                "———————————————————————————————",
                "—  Собирать Монеты",
                "—  Находить Секреты",
                "—  Выполнять Поставленные Задачи",
        };
    }
}
