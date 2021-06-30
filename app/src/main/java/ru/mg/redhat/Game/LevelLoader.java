package ru.mg.redhat.Game;

import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Point;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import ru.mg.redhat.Engine.Objects.BitmapObject;
import ru.mg.redhat.Engine.Main.CONSTANTS;
import ru.mg.redhat.Engine.Main.Camera;
import ru.mg.redhat.Engine.Objects.EnemyObject;
import ru.mg.redhat.Engine.Main.Graphics;
import ru.mg.redhat.Engine.Game.Level;
import ru.mg.redhat.Engine.Objects.SecretObject;
import ru.mg.redhat.Engine.Main.Sound;
import ru.mg.redhat.Game.Menu.CompleteMenu;
import ru.mg.redhat.Game.Menu.LoadingMenu;
import ru.mg.redhat.MainActivity;

public class LevelLoader extends AsyncTask<Void, Void, Void> {
    public String path;
    private String name;
    private int spawnX, spawnY;
    private Level level;
    private String taskType = "taskType";
    private String taskItemName = "taskItemName";
    private String taskItemType = "taskItemType";
    private String taskArea = "taskArea";
    private String musicPath;
    private String healthCare;
    private int taskItemCount;
    private BitmapObject handObject;
    public int width;
    public int height;
    private int personHealth;
    private Config config;

    private int coinTaken;
    private int taskItemTaken;
    private int secretsFound;

    public loadEvent event;
    public boolean redhat = true;

    @Override
    protected Void doInBackground(Void... params) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        try {
            event.loaded();
            //Initializing level
            MainActivity.levelName = name;
            Graphics.loadLevel(level);
            Main.init(level, name, new Point(spawnX, spawnY), redhat);
            if (handObject != null) level.setSecondBitmapObject("game_person", handObject);
            Main.setHealth(config.getInt("health", 10));
            MainActivity.loader = this;
            Sound.playMusic(musicPath);
            Sound.mp.setLooping(true);

            //Set AdView active
            MainActivity.enableAdView.sendEmptyMessage(0);
        } catch (Exception e) {
        }
    }

    public interface loadEvent {
        void loaded();
    }

    public LevelLoader(String path, int width, int height) {
        this.path = path;
        this.width = width;
        this.height = height;
    }

    public void init() throws IOException {
        level = new Level(1, 1, 4, 500, new Camera(new Point(0, 0), MainActivity.width, MainActivity.height));
        load();
        level.camera = new Camera(new Point(spawnX, spawnY), MainActivity.width, MainActivity.height);
    }

    public void initAsync() {
        //Initializing level async
        LoadingMenu.init(true);
        MainActivity.levelName = "LoadingMenu";
        Graphics.loadLevel(LoadingMenu.level);
        execute();
    }

    public void load() throws IOException {
            //Reading .glevel format
            InputStream inputStream = Graphics.assetManager.open(path);
            Scanner scanner = new Scanner(inputStream);
            boolean over = false;

            while (scanner.hasNext()) {
                String next = scanner.next();

                switch (next) {
                    //Level Name
                    case "[NAME]":
                        name = scanner.next();
                        if (MainActivity.prefs.getBoolean(name + "-StartOver", false)) over = true;
                        config = new Config(name);

                        break;

                    //Level Physics Plate
                    case "[MAINPLATE]":
                        String MPpath = scanner.next();
                        int width = scanner.nextInt();
                        int height = scanner.nextInt();
                        int collX = scanner.nextInt();
                        int collY = scanner.nextInt();
                        int collW = scanner.nextInt();
                        int collH = scanner.nextInt();

                        level.collMap = new Boolean[(int)Graphics.scaleWidth(width)][(int)Graphics.scaleHeight(height)];

                        level.addBitmapObject("MAINPLATE", new Point(width / 2, height / 2), CONSTANTS.MAINPLATE_PRIORITY, collX, collY, collW, collH, Graphics.processBitmap(MPpath, width, height), Graphics.level.progressBars.get("loadingBar"));
                        break;

                    //Game Background
                    case "[BACKGROUND]":
                        String backgroundPath = scanner.next();
                        int BGwidth = scanner.nextInt();
                        int BGheight = scanner.nextInt();

                        level.addBitmapObject("BACKGROUND", new Point(0, 0), CONSTANTS.BACKGROUND_PRIORITY, Graphics.processBitmap(backgroundPath, BGwidth, BGheight));
                        break;

                    //Game Person Spawn
                    case "[SPAWN]":
                        if (over) {
                            config.remove("X");
                            config.remove("Y");
                            spawnX = scanner.nextInt();
                            spawnY = scanner.nextInt();
                        } else {
                            spawnX = config.getInt("X", scanner.nextInt());
                            spawnY = config.getInt("Y", scanner.nextInt());
                        }
                        break;

                    //Level Task
                    case "[TASK]":

                        Scanner taskSC = new Scanner(scanner.nextLine());

                        while (taskSC.hasNext()) {
                            String nextTask = taskSC.next();

                            switch (nextTask) {
                                //Task Type
                                case "[TYPE]":
                                    taskType = taskSC.next();
                                    break;

                                //Task Item Name
                                case "[ITEMNAME]":
                                    taskItemName = taskSC.next();
                                    break;

                                //Task Item Type
                                case "[ITEMTYPE]":
                                    taskItemType = taskSC.next();
                                    break;

                                //Task Item Count
                                case "[ITEMCOUNT]":
                                    taskItemCount = taskSC.nextInt();

                                    if (over) {
                                        taskItemTaken = 0;
                                        config.remove(taskItemName + "-Taken");
                                    } else
                                        taskItemTaken = config.getInt(taskItemName + "-Taken", 0);
                                    break;

                                //Task Area
                                case "[AREA]":
                                    taskArea = taskSC.next();
                                    break;
                            }
                        }
                        break;

                    case "[MUSIC]":
                        String musicPath = scanner.next();

                        this.musicPath = musicPath;
                        break;

                    //HealthCare
                    case "[HEALTHCARE]":
                        healthCare = scanner.next();
                        break;

                    //Level Items
                    case "[ITEM]":
                        String itemName = scanner.next();
                        int itemX = scanner.nextInt();
                        int itemY = scanner.nextInt();
                        int itemW = scanner.nextInt();
                        int itemH = scanner.nextInt();
                        boolean isThrow = scanner.nextBoolean();
                        String itemPath = scanner.next();

                        if (over) {
                            config.remove(itemName);
                            level.addBitmapItemObject(itemName, new Point(itemX, itemY), CONSTANTS.ITEM_PRIORITY, Graphics.processBitmap(itemPath, itemW, itemH));
                            level.bitmapObjects.get(itemName).isThrow = isThrow;
                        } else if (config.getBoolean(itemName, true)) {
                            if (isThrow && config.getString("handItem", "").equals(itemName)) {
                                handObject = new BitmapObject("game_person_item", new Point((int)Graphics.scaleWidth(112) - itemW / 2, (int)Graphics.scaleHeight(64 - itemH / 2)), 2, Graphics.processBitmap(itemPath, itemW, itemH));
                            } else {
                                level.addBitmapItemObject(itemName, new Point(itemX, itemY), CONSTANTS.ITEM_PRIORITY, Graphics.processBitmap(itemPath, itemW, itemH));
                                level.bitmapObjects.get(itemName).isThrow = isThrow;
                            }
                        }
                        break;

                    //Level Coins
                    case "[COIN]":
                        String coinName = scanner.next();
                        int coinX = scanner.nextInt();
                        int coinY = scanner.nextInt();
                        int coinW = scanner.nextInt();
                        int coinH = scanner.nextInt();
                        String coinPath = scanner.next();

                        coinTaken = MainActivity.prefs.getInt(name + "-CoinTaken", 0);

                        if (over) {
                            MainActivity.prefsEditor.remove(name + "-" + coinName);
                            level.addBitmapItemObject(coinName, new Point(coinX, coinY), CONSTANTS.COIN_PRIORITY, Graphics.processBitmap(coinPath, coinW, coinH));
                        } else if (MainActivity.prefs.getBoolean(name + "-" + coinName, true))
                            level.addBitmapItemObject(coinName, new Point(coinX, coinY), CONSTANTS.COIN_PRIORITY, Graphics.processBitmap(coinPath, coinW, coinH));
                        break;

                    //Level Enemies
                    case "[ENEMY]":
                        String enemyName = scanner.next();
                        int enemyPower = scanner.nextInt();
                        int enemyX = scanner.nextInt();
                        int enemyY = scanner.nextInt();
                        int enemyWidth = scanner.nextInt();
                        int enemyHeight = scanner.nextInt();
                        String enemyPath = scanner.next();
                        String enemyPath1 = scanner.next();
                        String enemyPath2 = scanner.next();

                        if (over) {
                            config.remove(enemyName + "-Killed");
                            level.addEnemyObject(enemyName, new Point(enemyX, enemyY), CONSTANTS.ENEMY_PRIORITY, 1, 5, enemyPower, true, Graphics.processBitmap(enemyPath, enemyWidth, enemyHeight),
                                    Graphics.processBitmap(enemyPath1, enemyWidth, enemyHeight), Graphics.processBitmap(enemyPath2, enemyWidth, enemyHeight));
                        } else if (!config.getBoolean(enemyName + "-Killed", false)) {
                            level.addEnemyObject(enemyName, new Point(enemyX, enemyY), CONSTANTS.ENEMY_PRIORITY, 1, 5, enemyPower, true, Graphics.processBitmap(enemyPath, enemyWidth, enemyHeight),
                                    Graphics.processBitmap(enemyPath1, enemyWidth, enemyHeight), Graphics.processBitmap(enemyPath2, enemyWidth, enemyHeight));
                        }
                        break;

                    //Level Bitmaps
                    case "[BITMAP]":
                        String bitmapName = scanner.next();
                        int bitmapX = scanner.nextInt();
                        int bitmapY = scanner.nextInt();
                        int bitmapWidth = scanner.nextInt();
                        int bitmapHeight = scanner.nextInt();
                        String bitmapPath = scanner.next();

                        level.addBitmapObject(bitmapName, new Point(0, 0), CONSTANTS.BITMAP_PRIORITY, Graphics.processBitmap(bitmapPath, bitmapWidth, bitmapHeight));
                        level.bitmapObjects.get(bitmapName).setPSX(bitmapX);
                        level.bitmapObjects.get(bitmapName).setPSY(bitmapY);
                        break;

                    //Level Secrets
                    case "[SECRET]":
                        String secretName = scanner.next();
                        int secretX = scanner.nextInt();
                        int secretY = scanner.nextInt();
                        int secretW = scanner.nextInt();
                        int secretH = scanner.nextInt();

                        level.addSecretObject(secretName, secretX, secretY, secretW, secretH);

                        if (over)
                            config.remove(secretName + "-Found");
                        else if (config.getBoolean(secretName + "-Found", false))
                            level.secretObjects.get(secretName).found = true;
                        break;

                    //Hat check
                    case "[HAT]":
                        redhat = scanner.nextBoolean();
                        break;
                }
            }

            if (over) {
                config.remove("health");
                config.remove("handItem");
                config.remove("SecretsFound");
                config.save();
            }

            personHealth = config.getInt("health", 10);
            secretsFound = config.getInt("SecretsFound", 0);

            MainActivity.levelName = name;
    }

    public void unload(boolean startOver) {
        Sound.mp.stop();

        try {
            Graphics.level.bitmapObjects.get("game_person").paint.setColorFilter(null);
        } catch (Exception e) {
        }

        if (startOver) config.remove("CoinTaken");

        //Saving level variable
        config.setInt("health", personHealth);
        config.setBoolean("StartOver", startOver);
        config.save();
        //Unloading level
        Main.reset();
        Main.unload();

        path = null;
        spawnX = 0;
        spawnY = 0;
        level.collMap = null;
        level = null;

        MainActivity.disableAdView.sendEmptyMessage(0);
    }

    public void win() {
        unload(true);

        //Initializing Complete Menu
        CompleteMenu.init(name, coinTaken, secretsFound, false);
        name = null;
    }

    public void onDraw() {
        try {
            Main.onDraw(3000);

            BitmapObject item = Graphics.checkBitmapObjectTouches(level.bitmapObjects.get("game_person"));

            int x = (int)level.bitmapObjects.get("game_person").getPSX();
            int y = (int)level.bitmapObjects.get("game_person").getPSY();
            int w = level.bitmapObjects.get("game_person").bmp.getWidth();
            int h = level.bitmapObjects.get("game_person").bmp.getHeight();

            //Check for founded secrets
            for (SecretObject secret : level.secretObjects.values()) {
                if (!secret.found && x + w >= secret.x && x <= secret.x + secret.width && y + h >= secret.y && y <= secret.y + secret.height) {
                    if (secret.name.contains(taskArea)) {
                        win();
                    } else {
                        secret.found = true;
                        secretsFound++;
                        config.setBoolean(secret.name + "-Found", true);
                        config.setInt("SecretsFound", secretsFound);
                    }
                }
            }

            for (EnemyObject enemy : level.enemyObjects.values()) {
                if (enemy.checkCollision(level.bitmapObjects.get("game_person")) && enemy.enabled && enemy.nextHit) {
                    Sound.playSound(Sounds.game_hit, 1, 0, 0, 1);
                    enemy.hit();
                    Main.setHealth(personHealth - enemy.power);
                    personHealth -= enemy.power;

                    Graphics.level.bitmapObjects.get("game_person").paint.setColorFilter(new LightingColorFilter(Color.RED, 0));

                    Timer tm = new Timer();
                    tm.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            try {
                                Graphics.level.bitmapObjects.get("game_person").paint.setColorFilter(null);
                            } catch (Exception e) {
                            }
                        }
                    }, 500);

                    if (personHealth < 1) {
                        unload(true);
                        //Initializing Complete Menu
                        CompleteMenu.init(name, coinTaken, secretsFound, true);
                        MainActivity.levelName = "Complete";
                        Graphics.loadLevel(CompleteMenu.level);

                        name = null;
                    }
                }
            }

            //Check for founded coins
            if (item != null) {
                if ((taskItemType.equals("COIN") && taskType.equals("COLLECT")) || item.name.contains("CoinItem")) {
                    coinTaken++;
                    Sound.playSound(Sounds.game_coin, 1, 0, 0, 1);
                    level.bitmapObjects.remove(item.name);
                    MainActivity.prefsEditor.putBoolean(name + "-" + item.name, false);
                    if (taskItemType.equals("COIN") && taskType.equals("COLLECT"))
                        MainActivity.prefsEditor.putInt(name + "-" + taskItemName + "-Taken", taskItemTaken);
                    else MainActivity.prefsEditor.putInt(name + "-CoinTaken", coinTaken);

                    if (taskItemType.equals("COIN") && taskType.equals("COLLECT") && coinTaken == taskItemCount)
                        win();
                } else if (item.name.contains(taskItemName) && taskItemType.equals("ITEM") && taskType.equals("COLLECT")) {
                    //Check for founded items
                    taskItemTaken++;
                    Sound.playSound(Sounds.game_item, 1, 0, 0, 1);
                    level.bitmapObjects.remove(item.name);
                    config.setBoolean(item.name, false);
                    config.setInt(taskItemName + "-Taken", taskItemTaken);

                    if (taskItemTaken == taskItemCount)
                        win();
                } else if (item.isThrow) {
                    Sound.playSound(Sounds.game_item, 1, 0, 0, 1);
                    level.bitmapObjects.remove(item.name);
                    config.setString("handItem", item.name);
                    config.save();

                    Main.throwHammer = item.name.contains("hammer");
                    if (Main.statePersonSide) {
                        level.setSecondBitmapObject("game_person", new BitmapObject("game_person_item", new Point((int)Graphics.scaleWidth(112) - item.bmp.getWidth() / 2, (int)Graphics.scaleHeight(64 - item.bmp.getHeight() / 2)), 2, item.bmp));
                        Graphics.level.bitmapObjects.get("game_person").bmp = Bitmaps.game_person;
                    } else {
                        level.setSecondBitmapObject("game_person", new BitmapObject("game_person_item", new Point((int)Graphics.scaleWidth(4) - item.bmp.getWidth() / 2, (int)Graphics.scaleHeight(64 - item.bmp.getHeight() / 2)), 2, item.bmp));
                        Graphics.level.bitmapObjects.get("game_person").bmp = Bitmaps.game_person_flipped;
                    }
                } else if (item.name.contains(healthCare)) {
                    if (personHealth < 10) {
                        Sound.playSound(Sounds.game_healthcare, 1, 0, 0, 1);
                        level.bitmapObjects.remove(item.name);
                        config.setBoolean(item.name, false);

                        if (personHealth == 9) {
                            Main.setHealth(personHealth + 1);
                            personHealth++;
                        } else {
                            Main.setHealth(personHealth + 2);
                            personHealth += 2;
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
    }
}
