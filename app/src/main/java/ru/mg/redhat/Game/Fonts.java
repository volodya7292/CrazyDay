package ru.mg.redhat.Game;

import android.graphics.Color;
import android.graphics.Paint;

import ru.mg.redhat.Engine.Main.Graphics;

public final class Fonts {
    //HELP MENU
    public static Paint helpText = Graphics.textPaint(Color.WHITE, 40, "UI/comic.ttf");

    //LOADING MENU
    public static Paint loadingText = Graphics.textPaint(Color.RED, 85, "UI/comic.ttf");

    //MAIN MENU
    public static Paint titleText = Graphics.textPaint(Color.RED, 65, "UI/comic.ttf");

    //PAUSE MENU
    public static Paint pauseButtonText = Graphics.textPaint(Color.WHITE, 50, "UI/comic.ttf");

    //LEVEL MENU
    public static Paint levelMenuButtonText = Graphics.textPaint(Color.WHITE, 50, "UI/comic.ttf");

    //COMPLETE MENU
    public static Paint completeMenuButtonText = Graphics.textPaint(Color.WHITE, 40, "UI/comic.ttf");

    //OTHER
    public static Paint buttonText = Graphics.textPaint(Color.WHITE, 60, "UI/comic.ttf");
}
