package ru.mg.redhat;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdSize;
//import com.google.android.gms.ads.AdView;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import ru.mg.redhat.Engine.Main.Graphics;
import ru.mg.redhat.Engine.Main.Sound;
import ru.mg.redhat.Game.BitmapLoader;
import ru.mg.redhat.Game.Bitmaps;
import ru.mg.redhat.Game.Languages.English;
import ru.mg.redhat.Game.Languages.France;
import ru.mg.redhat.Game.Languages.German;
import ru.mg.redhat.Game.Languages.Russian;
import ru.mg.redhat.Game.Languages.Spanish;
import ru.mg.redhat.Game.LevelLoader;
import ru.mg.redhat.Game.Menu.CompleteMenu;
import ru.mg.redhat.Game.Menu.HelpMenu;
import ru.mg.redhat.Game.Menu.LanguageMenu;
import ru.mg.redhat.Game.Menu.LevelMenu;
import ru.mg.redhat.Game.Menu.LoadingMenu;
import ru.mg.redhat.Game.Menu.MainMenu;
import ru.mg.redhat.Game.Menu.PauseMenu;
import ru.mg.redhat.Game.Sounds;

public class MainActivity extends AppCompatActivity {
    public static SharedPreferences prefs;
    public static SharedPreferences.Editor prefsEditor;
    public static final int MAX_FPS = 60;
    public static int width, height;
    public static String levelName;
    public static LevelLoader loader;
    private DisplayMetrics metrics = new DisplayMetrics();
    private static boolean levelLoaded = false;
    private static boolean bmpsLoaded = false;
    private static Context thisContext;
    public static int currOrientation;

    //AD
//    private static AdRequest adRequest;
//    public static AdView adView0;
    private RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        thisContext = this;
        prefs = getSharedPreferences("Prefs", Context.MODE_PRIVATE);
        prefsEditor = prefs.edit();

        //Adding an AdView and game view
//        adRequest = new AdRequest.Builder().build();

        RelativeLayout.LayoutParams lp0 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

//        adView0 = new AdView(this);
//        adView0.setAdSize(AdSize.BANNER);
//        adView0.setAdUnitId("ca-app-pub-1480510387927957/3125058111");
//        adView0.setLayoutParams(lp0);

        layout = new RelativeLayout(this);
        layout.addView(new DrawView(this, MAX_FPS));
//        layout.addView(adView0);

//        adView0.loadAd(adRequest);

        setContentView(layout);

        //Game Screen
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
        Graphics.init(getAssets(), getWindow(), MAX_FPS, width, height);
        Sound.init(getAssets(), this, 3, getSystemService(AUDIO_SERVICE));

        //Loading assets
        Bitmaps.loadLoadingBitmaps();
        BitmapLoader bl = new BitmapLoader();
        bl.event = new BitmapLoader.loadEvent() {
            @Override
            public void loaded() {
                BitmapLoader bl = new BitmapLoader();
                bl.event = new BitmapLoader.loadEvent() {
                    @Override
                    public void loaded() {
                        bmpsLoaded = true;
                    }
                };

                bl.state = "Game";
                bl.execute();
            }
        };

        bl.state = "UI";
        bl.execute();

        Sounds.init();

        //Loading language
        switch (prefs.getString("Language", "English")) {
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
//        adView0.destroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Sound.sp.autoPause();
//        adView0.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        adView0.resume();
        setContentView(layout);
        Sound.sp.autoResume();
    }

    public static final Handler enableAdView = new Handler(){
        @Override
        public void handleMessage(Message msg) {
//            adView0.setX(Graphics.scaleWidth(740) - AdSize.BANNER.getWidthInPixels(thisContext) / 2);
//            adView0.setY(Graphics.scaleHeight(620) - AdSize.BANNER.getHeightInPixels(thisContext) / 2);
//            adView0.loadAd(adRequest);
        }
    };

    public static final Handler disableAdView = new Handler(){
        @Override
        public void handleMessage(Message msg) {
//            adView0.setX(width);
//            adView0.setY(height);
//            adView0.destroy();
        }
    };

    public static void onDraw(Canvas canvas) {
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
    }
}