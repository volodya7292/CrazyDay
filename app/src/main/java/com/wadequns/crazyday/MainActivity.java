package com.wadequns.crazyday;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.wadequns.crazyday.Engine.Main.Graphics;
import com.wadequns.crazyday.Engine.Main.Sound;
import com.wadequns.crazyday.Game.BitmapLoader;
import com.wadequns.crazyday.Game.Bitmaps;
import com.wadequns.crazyday.Game.Languages.English;
import com.wadequns.crazyday.Game.Languages.France;
import com.wadequns.crazyday.Game.Languages.German;
import com.wadequns.crazyday.Game.Languages.Russian;
import com.wadequns.crazyday.Game.Languages.Spanish;
import com.wadequns.crazyday.Game.LevelLoader;
import com.wadequns.crazyday.Game.Menu.CompleteMenu;
import com.wadequns.crazyday.Game.Menu.HelpMenu;
import com.wadequns.crazyday.Game.Menu.LanguageMenu;
import com.wadequns.crazyday.Game.Menu.LevelMenu;
import com.wadequns.crazyday.Game.Menu.LoadingMenu;
import com.wadequns.crazyday.Game.Menu.MainMenu;
import com.wadequns.crazyday.Game.Menu.PauseMenu;
import com.wadequns.crazyday.Game.Sounds;

import java.io.IOException;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    public static SharedPreferences prefs;
    public static SharedPreferences.Editor prefsEditor;
    public static int width, height;
    public static String levelName;
    public static LevelLoader loader;
    private final DisplayMetrics metrics = new DisplayMetrics();
    private static boolean levelLoaded = false;
    private static boolean bmpsLoaded = false;
    public static final boolean PROP_DEBUG = false;
    public static final boolean PROP_ALL_LEVELS = false;
    public static final boolean PROP_AD = true;
    public static int currOrientation, currLevelNumber;

    //AD
//    private static AdRequest adRequest;
//    public static AdView adView0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences("Prefs", Context.MODE_PRIVATE);
        prefsEditor = prefs.edit();

        //Adding an AdView and game view
//        adRequest = new AdRequest.Builder().build();

//        adView0 = new AdView(this);
//        adView0.setAdSize(AdSize.BANNER);
//        adView0.setAdUnitId("ca-app-pub-1480510387927957/7780206116");
//        adView0.setLayoutParams(lp0);

//        layout = new RelativeLayout(this);
//        layout.addView(new DrawView(this));
//        layout.addView(adView0);

//        if (PROP_AD) adView0.loadAd(adRequest);

//        setContentView(layout);

        //Game Screen

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
            layoutParams.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            getWindow().setAttributes(layoutParams);
        }

        getSupportActionBar().hide();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        if (prefs.getInt("orientation", ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        else
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
        currOrientation = getRequestedOrientation();

        Display d = getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        d.getRealMetrics(displayMetrics);

        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;

        //Initializing game engine
        Graphics.init(getAssets(), getWindow(), width, height);
        Sound.init(getAssets(), this, 3, getSystemService(AUDIO_SERVICE));

        //Loading assets
        Bitmaps.loadLoadingBitmaps();
        BitmapLoader bl = new BitmapLoader();
        bl.event = () -> {
            BitmapLoader bl1 = new BitmapLoader();
            bl1.event = () -> bmpsLoaded = true;

            bl1.state = "Game";
            bl1.execute();
        };

        bl.state = "UI";
        bl.execute();

        Sounds.init();


        String defLang;
        switch (Locale.getDefault().getISO3Language()) {
            case "rus":
                defLang = "Russian";
                break;
            case "deu":
                defLang = "German";
                break;
            case "fra":
                defLang = "France";
                break;
            case "spa":
                defLang = "Spanish";
                break;
            default:
                defLang = "English";
                break;
        }

        //Loading language
        switch (prefs.getString("Language", defLang)) {
            case "English":
                English.init();
                break;

            case "Russian":
                Russian.init();
                break;

            case "German":
                German.init();
                break;

            case "France":
                France.init();
                break;

            case "Spanish":
                Spanish.init();
                break;
        }

        //Initializing loading menu
        LoadingMenu.init(false);

//        adView0.setX(Graphics.scaleWidth(740) - AdSize.BANNER.getWidthInPixels(this) / 2);
//        adView0.setY(Graphics.scaleHeight(620) - AdSize.BANNER.getHeightInPixels(this) / 2);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (getRequestedOrientation() != currOrientation)
                    setRequestedOrientation(currOrientation);
            }
        }, 0, 100);
    }

    @Override //Click handler
    public boolean onTouchEvent(MotionEvent event) {
        try {
            return Graphics.checkClick(event);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Graphics.onFocusChanged(hasFocus);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Sound.releaseMP();
//        if (PROP_AD) adView0.destroy();

        try {
            if (loader != null) loader.unload(false);
        } catch (Exception e) {
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Sound.sp.autoPause();
//        if (PROP_AD) adView0.pause();

        try {
            if (loader != null) loader.unload(false);
        } catch (Exception e) {
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if (PROP_AD) adView0.resume();
        Sound.sp.autoResume();
    }

    public static final Handler enableAdView = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (PROP_AD) {
//                adView0.setX(Graphics.scaleWidth(740) - AdSize.BANNER.getWidthInPixels(thisContext) / 2);
//                adView0.setY(Graphics.scaleHeight(620) - AdSize.BANNER.getHeightInPixels(thisContext) / 2);
//                adView0.loadAd(adRequest);
//                adView0.resume();
            }
        }
    };

    public static final Handler disableAdView = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (PROP_AD) {
//                adView0.setX(width);
//                adView0.setY(height);
//                adView0.destroy();
            }
        }
    };

    public static void onDraw(Canvas canvas, int frameTime) {
        if (bmpsLoaded) {
            //Initializing main menu
            LoadingMenu.unload();
            MainMenu.init();
            levelLoaded = true;
            bmpsLoaded = false;
        } else if (levelLoaded) {
            //Drawing
            switch (levelName) {
                case "LoadingMenu":
                    LoadingMenu.onDraw();
                    break;

                case "MainMenu":
                    MainMenu.onDraw();
                    break;

                case "LevelMenu":
                    LevelMenu.onDraw();
                    break;

                case "PauseMenu":
                    PauseMenu.onDraw();
                    break;

                case "LanguageMenu":
                    LanguageMenu.onDraw();
                    break;

                case "HelpMenu":
                    HelpMenu.onDraw();
                    break;

                case "CompleteMenu":
                    CompleteMenu.onDraw();
                    break;

                default:
                    if (loader != null) loader.onDraw();
                    break;
            }
        }

        Graphics.onDraw(canvas, 1000 / frameTime);
    }
}