package com.wadequns.crazyday.Game;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

import com.wadequns.crazyday.Engine.Game.Level;
import com.wadequns.crazyday.Engine.Main.CONSTANTS;
import com.wadequns.crazyday.Engine.Main.ClickHandler;
import com.wadequns.crazyday.Engine.Main.Graphics;
import com.wadequns.crazyday.Engine.Main.Sound;
import com.wadequns.crazyday.Engine.Objects.BitmapObject;
import com.wadequns.crazyday.Engine.Objects.EnemyObject;
import com.wadequns.crazyday.Game.Menu.PauseMenu;

import java.util.Timer;
import java.util.TimerTask;

import com.wadequns.crazyday.MainActivity;

public class Main {
    private static boolean stateLeft = false;
    private static boolean stateRight = false;
    private static boolean stateJump = false;
    private static boolean stateTimer = true;
    private static boolean statePersonLeftTimer = false;
    private static boolean statePersonRightTimer = false;
    private static boolean statePersonLeg = true;
    public static boolean statePersonSide = true;
    private static boolean timersState = true;
    private static boolean throwState = false;
    public static boolean throwHammer = false;
    public static boolean throwEHammer = false;
    private static int throwDirection;
    private static int personSpeed = 200;
    public static boolean pause = false;
    public static boolean stateGlobalTimer = true;
    public static String backgroundID, mainObjectID;
    public static Timer tm;

    public static void init(final Level level, final String levelName, Point pPos, boolean redhat) {
        level.addBitmapObject("game_person", pPos, CONSTANTS.PERSON_PRIORITY, false, Bitmaps.game_person);
        mainObjectID = "game_person";
        backgroundID = "BACKGROUND";
        timersState = true;
        throwState = false;

        Paint white = new Paint(Graphics.defaultColor);
        white.setColor(Color.WHITE);

        if (redhat)
            level.setBitmapObject(mainObjectID, new BitmapObject("game_person_redhat", new Point(38, 1), 2, Bitmaps.game_person_red_hat));

        level.addBitmapObject("heart1", new Point(0, 0), 4, Bitmaps.game_heart);
        level.addBitmapObject("heart2", new Point(0, 0), 4, Bitmaps.game_heart);
        level.addBitmapObject("heart3", new Point(0, 0), 4, Bitmaps.game_heart);
        level.addBitmapObject("heart4", new Point(0, 0), 4, Bitmaps.game_heart);
        level.addBitmapObject("heart5", new Point(0, 0), 4, Bitmaps.game_heart);

        if (MainActivity.PROP_DEBUG) {
            level.addTextObject("FPS-GEN", "FPS : 30  X : 0  Y : 0", new Point(level.camera.getX(), level.camera.getPSY() + 30), 3, white);
            level.addTextObject("FPS-UP-LEFT", "FPS : 30  X : 0  Y : 0", new Point(level.camera.getX(), level.camera.getPSY() + 30), 2, Graphics.defaultColor);
            level.addTextObject("FPS-UP-RIGHT", "FPS : 30  X : 0  Y : 0", new Point(level.camera.getX(), level.camera.getPSY() + 30), 2, Graphics.defaultColor);
            level.addTextObject("FPS-DOWN-LEFT", "FPS : 30  X : 0  Y : 0", new Point(level.camera.getX(), level.camera.getPSY() + 30), 2, Graphics.defaultColor);
            level.addTextObject("FPS-DOWN-RIGHT", "FPS : 30  X : 0  Y : 0", new Point(level.camera.getX(), level.camera.getPSY() + 30), 2, Graphics.defaultColor);
        }

        level.addButtonObject("GameLeftButton", "", new Point(100, 620), 1, 30, Bitmaps.game_arrow_left, Bitmaps.game_arrow_left,
                Graphics.defaultColor, new ClickHandler() {
                    @Override
                    public void onClick() {
                        try {
                            if (Graphics.level.bitmapObjects.get(mainObjectID).secondBitmapObject == null)
                                Graphics.level.bitmapObjects.get(mainObjectID).bmp = Bitmaps.game_person_noitem_flipped;
                            else
                                Graphics.level.bitmapObjects.get(mainObjectID).bmp = Bitmaps.game_person_flipped;

                            stateLeft = false;
                        } catch (Exception e) {  }
                    }

                    @Override
                    public void onDown() {
                        if (Graphics.level.bitmapObjects.get(mainObjectID).secondBitmapObject == null)
                            Graphics.level.bitmapObjects.get(mainObjectID).bmp = Bitmaps.game_person_noitem_flipped;
                        else
                            Graphics.level.bitmapObjects.get(mainObjectID).bmp = Bitmaps.game_person_flipped;

                        stateLeft = true;
                        statePersonLeftTimer = true;
                    }

                    @Override
                    public void onUp() {
                        stateLeft = false;
                    }
                });
        level.addButtonObject("GameRightButton", "", new Point(300, 620), 1, 30, Bitmaps.game_arrow_right, Bitmaps.game_arrow_right,
                Graphics.defaultColor, new ClickHandler() {
                    @Override
                    public void onClick() {
                        try {
                            if (Graphics.level.bitmapObjects.get(mainObjectID).secondBitmapObject == null)
                                Graphics.level.bitmapObjects.get(mainObjectID).bmp = Bitmaps.game_person_noitem;
                            else
                                Graphics.level.bitmapObjects.get(mainObjectID).bmp = Bitmaps.game_person;

                            stateRight = false;
                        } catch (Exception e) {  }
                    }

                    @Override
                    public void onDown() {
                        if (Graphics.level.bitmapObjects.get(mainObjectID).secondBitmapObject == null)
                            Graphics.level.bitmapObjects.get(mainObjectID).bmp = Bitmaps.game_person_noitem;
                        else
                            Graphics.level.bitmapObjects.get(mainObjectID).bmp = Bitmaps.game_person;

                        stateRight = true;
                        statePersonRightTimer = true;
                    }

                    @Override
                    public void onUp() {
                        stateRight = false;
                    }

                });
        level.addButtonObject("GameUpButton", "", new Point(1180, 620), 1, 30, Bitmaps.game_arrow_up, Bitmaps.game_arrow_up,
                Graphics.defaultColor, new ClickHandler() {
                    @Override
                    public void onClick() {
                        try {
                            if (Graphics.checkObjectDownCollision(Graphics.level.bitmapObjects.get(mainObjectID))) {
                                if (statePersonSide) {
                                    if (Graphics.level.bitmapObjects.get(mainObjectID).secondBitmapObject == null)
                                        Graphics.level.bitmapObjects.get(mainObjectID).bmp = Bitmaps.game_person_noitem;
                                    else
                                        Graphics.level.bitmapObjects.get(mainObjectID).bmp = Bitmaps.game_person;
                                } else {
                                    if (Graphics.level.bitmapObjects.get(mainObjectID).secondBitmapObject == null)
                                        Graphics.level.bitmapObjects.get(mainObjectID).bmp = Bitmaps.game_person_noitem_flipped;
                                    else
                                        Graphics.level.bitmapObjects.get(mainObjectID).bmp = Bitmaps.game_person_flipped;
                                }

                                personSpeed = 500;
                                stateJump = true;
                                Timer tm = new Timer();

                                tm.schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                        personSpeed = 200;
                                        stateJump = false;
                                    }
                                }, 400);
                            }
                        } catch (Exception e) {  }
                    }

                    @Override
                    public void onDown() {
                    }

                    @Override
                    public void onUp() {

                    }
                });
        level.addButtonObject("GameThrowButton", "", new Point(1180, 500), 1, 30, Bitmaps.game_throw, Bitmaps.game_throw,
                Graphics.defaultColor, new ClickHandler() {
                    @Override
                    public void onClick() {
                        try {
                            if (!throwState && Graphics.level.bitmapObjects.get(mainObjectID).secondBitmapObject != null) {
                                if (statePersonSide) {
                                    throwDirection = CONSTANTS.DIRECTION_RIGHT;
                                    Graphics.level.addBitmapObject("throwItem", new Point((int)(Graphics.level.bitmapObjects.get(mainObjectID).getX()) + 50 - Graphics.level.bitmapObjects.get(mainObjectID).secondBitmapObject.bmp.getWidth() / 2,
                                            (int)(Graphics.level.bitmapObjects.get(mainObjectID).getPSY()) + 70), 2, Graphics.level.bitmapObjects.get(mainObjectID).secondBitmapObject.bmp);
                                } else {
                                    throwDirection = CONSTANTS.DIRECTION_LEFT;

                                    Graphics.level.addBitmapObject("throwItem", new Point((int)(Graphics.level.bitmapObjects.get(mainObjectID).getX()) - 50,
                                            (int)(Graphics.level.bitmapObjects.get(mainObjectID).getPSY()) + 70), 2, Graphics.level.bitmapObjects.get(mainObjectID).secondBitmapObject.bmp);
                                }

                                MainActivity.prefsEditor.putBoolean(levelName + "-" + MainActivity.prefs.getString(levelName + "-handItem", ""), false);
                                MainActivity.prefsEditor.commit();
                                Graphics.level.bitmapObjects.get(mainObjectID).secondBitmapObject = null;
                                throwState = true;
                            }
                        } catch (Exception e) {  }
                    }

                    @Override
                    public void onDown() {
                    }

                    @Override
                    public void onUp() {

                    }
                });
        level.addButtonObject("Menu", "", new Point(1238, 42), 1, 30, Bitmaps.game_button_close, Bitmaps.game_button_close, Graphics.anti_alias, new ClickHandler() {
            @Override
            public void onClick() {
                if (tm != null) tm.cancel();
                pause = true;
                Sound.sp.autoPause();
                Sound.mp.pause();
                PauseMenu.init(level, levelName);
            }

            @Override
            public void onDown() {
            }

            @Override
            public void onUp() {

            }
        });
    }

    public static void onDraw(int jumpValue) {
        if (!pause) {
            if (stateLeft) {
                Graphics.level.bitmapObjects.get(mainObjectID).transformX(-Graphics.translate(personSpeed));

                if (Graphics.level.bitmapObjects.get(mainObjectID).secondBitmapObject != null)
                    Graphics.level.bitmapObjects.get(mainObjectID).secondBitmapObject.setNewX(4 - Graphics.level.bitmapObjects.get(mainObjectID).secondBitmapObject.bmp.getWidth() / 2);

                if (statePersonLeftTimer) {
                    Timer tm = new Timer();
                    tm.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            if (timersState && Graphics.level.bitmapObjects.get(mainObjectID) != null && Graphics.checkBitmapLeftCollision(Graphics.level.bitmapObjects.get(mainObjectID),
                                    (int)Graphics.translate(200)) == (int)Graphics.translate(200)) {
                                if (statePersonLeg) {
                                    if (Graphics.level.bitmapObjects.get(mainObjectID).secondBitmapObject == null)
                                        Graphics.level.bitmapObjects.get(mainObjectID).bmp = Bitmaps.game_person_right_noitem_flipped;
                                    else
                                        Graphics.level.bitmapObjects.get(mainObjectID).bmp = Bitmaps.game_person_right_flipped;

                                    statePersonLeg = false;
                                } else {
                                    if (Graphics.level.bitmapObjects.get(mainObjectID).secondBitmapObject == null)
                                        Graphics.level.bitmapObjects.get(mainObjectID).bmp = Bitmaps.game_person_left_noitem_flipped;
                                    else
                                        Graphics.level.bitmapObjects.get(mainObjectID).bmp = Bitmaps.game_person_left_flipped;
                                    statePersonLeg = true;
                                }
                            }

                            statePersonLeftTimer = true;
                        }
                    }, 100);

                    statePersonSide = false;
                    statePersonLeftTimer = false;
                }
            }
            if (stateRight) {
                Graphics.level.bitmapObjects.get(mainObjectID).transformX(Graphics.translate(personSpeed));

                if (Graphics.level.bitmapObjects.get(mainObjectID).secondBitmapObject != null)
                    Graphics.level.bitmapObjects.get("game_person").secondBitmapObject.setNewX(112 - Graphics.level.bitmapObjects.get(mainObjectID).secondBitmapObject.bmp.getWidth() / 2);

                if (statePersonRightTimer) {
                    Timer tm = new Timer();
                    tm.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            if (timersState && Graphics.level.bitmapObjects.get(mainObjectID) != null && Graphics.checkBitmapRightCollision(Graphics.level.bitmapObjects.get(mainObjectID),
                                    (int)Graphics.translate(200)) == (int)Graphics.translate(200)) {
                                if (statePersonLeg) {
                                    if (Graphics.level.bitmapObjects.get(mainObjectID).secondBitmapObject == null)
                                        Graphics.level.bitmapObjects.get(mainObjectID).bmp = Bitmaps.game_person_right_noitem;
                                    else
                                        Graphics.level.bitmapObjects.get(mainObjectID).bmp = Bitmaps.game_person_right;

                                    statePersonLeg = false;
                                } else {
                                    if (Graphics.level.bitmapObjects.get(mainObjectID).secondBitmapObject == null)
                                        Graphics.level.bitmapObjects.get(mainObjectID).bmp = Bitmaps.game_person_left_noitem;
                                    else
                                        Graphics.level.bitmapObjects.get(mainObjectID).bmp = Bitmaps.game_person_left;

                                    statePersonLeg = true;
                                }
                            }

                            statePersonRightTimer = true;
                        }
                    }, 100);

                    statePersonSide = true;
                    statePersonRightTimer = false;
                }
            }
            if (stateJump) {
                if (Graphics.level.bitmapObjects.get(mainObjectID) != null && Graphics.level.bitmapObjects.get(mainObjectID).getY() -
                        Graphics.level.bitmapObjects.get(mainObjectID).bmp.getHeight() / 2 > 0)

                    Graphics.level.bitmapObjects.get(mainObjectID).transformY(-Graphics.translate(1000));
            }
            if (throwState) {
                if (Graphics.checkEnemyTouch(Graphics.level.bitmapObjects.get("throwItem"), 15000)) {
                    if (throwHammer) {
                        try {
                            EnemyObject enemy = Graphics.checkObjectEnemyTouch(Graphics.level.bitmapObjects.get("throwItem"));

                            if (!enemy.getName().contains("BOSS")) {
                                MainActivity.prefsEditor.putBoolean(enemy.getName() + "-Killed", true);
                                enemy.removing = true;
                            }

                            throwHammer = false;
                        } catch (Exception e) {  }
                    } else if (throwEHammer) {
                        try {
                            EnemyObject enemy = Graphics.checkObjectEnemyTouch(Graphics.level.bitmapObjects.get("throwItem"));
                            if (enemy.getName().contains("BOSS")) {
                                MainActivity.prefsEditor.putBoolean(enemy.getName() + "-Killed", true);
                                enemy.removing = true;
                                Sound.playSound(Sounds.game_ehit, 1, 0, 0, 1);
                                MainActivity.loader.win();
                            }

                            throwEHammer = false;
                        } catch (Exception e) {  }
                    }
                    Sound.playSound(Sounds.game_plate, 5, 0, 0, 1);
                    throwState = false;
                    Graphics.level.removeBitmapObject("throwItem");
                } else if (Graphics.checkObjectCollision(Graphics.level.bitmapObjects.get("throwItem"))) {
                    throwState = false;
                }

                if (throwDirection == CONSTANTS.DIRECTION_LEFT)
                    Graphics.level.bitmapObjects.get("throwItem").transformX(-Graphics.translate(1000));
                else
                    Graphics.level.bitmapObjects.get("throwItem").transformX(Graphics.translate(1000));
            }
            if (stateTimer && MainActivity.PROP_DEBUG) {
                tm = new Timer();
                tm.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        stateTimer = true;
                        if (!pause) {
                            try {
                                Graphics.level.textObjects.get("FPS-GEN").text = "FPS : " + Graphics.currFPS + "  X : " + Graphics.level.bitmapObjects.get("game_person").getX()
                                        + "  Y : " + Graphics.level.bitmapObjects.get("game_person").getY();
                                Graphics.level.textObjects.get("FPS-UP-LEFT").text = "FPS : " + Graphics.currFPS + "  X : " + Graphics.level.bitmapObjects.get("game_person").getX()
                                        + "  Y : " + Graphics.level.bitmapObjects.get("game_person").getY();
                                Graphics.level.textObjects.get("FPS-UP-RIGHT").text = "FPS : " + Graphics.currFPS + "  X : " + Graphics.level.bitmapObjects.get("game_person").getX()
                                        + "  Y : " + Graphics.level.bitmapObjects.get("game_person").getY();
                                Graphics.level.textObjects.get("FPS-DOWN-LEFT").text = "FPS : " + Graphics.currFPS + "  X : " + Graphics.level.bitmapObjects.get("game_person").getX()
                                        + "  Y : " + Graphics.level.bitmapObjects.get("game_person").getY();
                                Graphics.level.textObjects.get("FPS-DOWN-RIGHT").text = "FPS : " + Graphics.currFPS + "  X : " + Graphics.level.bitmapObjects.get("game_person").getX()
                                        + "  Y : " + Graphics.level.bitmapObjects.get("game_person").getY();
                            } catch (Exception e) {  }
                        }
                    }
                }, 200);

                stateTimer = false;
            }

            //Set background coordinates to person coordinates;
            Graphics.level.camera.setX((int)Graphics.level.bitmapObjects.get(Main.mainObjectID).getX());

            int mapWidth = Graphics.level.bitmapObjects.get("MAINPLATE").bmp.getWidth();

            //Check if person in the mainplate
            if (Graphics.level.bitmapObjects.get(Main.mainObjectID).getY() < Graphics.scaleHeight(jumpValue)) {
                Graphics.level.camera.setY((int)Graphics.level.bitmapObjects.get(Main.mainObjectID).getY());
            }
            if (Graphics.level.camera.getPSX() < 0) {
                Graphics.level.camera.setPSX(0);
            } else if (Graphics.level.camera.getPSX() + Graphics.level.camera.getWidth() > mapWidth) {
                Graphics.level.camera.setPSX(mapWidth - Graphics.level.camera.getWidth());
            }

            if (MainActivity.PROP_DEBUG) {
                Graphics.level.textObjects.get("FPS-GEN").setNewX(Graphics.level.camera.getX() + 1);
                Graphics.level.textObjects.get("FPS-GEN").setNewY(Graphics.level.camera.getPSY() + 31);
                Graphics.level.textObjects.get("FPS-UP-LEFT").setNewX(Graphics.level.camera.getX());
                Graphics.level.textObjects.get("FPS-UP-LEFT").setNewY(Graphics.level.camera.getPSY() + 30);
                Graphics.level.textObjects.get("FPS-UP-RIGHT").setNewX(Graphics.level.camera.getX() + 2);
                Graphics.level.textObjects.get("FPS-UP-RIGHT").setNewY(Graphics.level.camera.getPSY() + 30);
                Graphics.level.textObjects.get("FPS-DOWN-LEFT").setNewX(Graphics.level.camera.getX());
                Graphics.level.textObjects.get("FPS-DOWN-LEFT").setNewY(Graphics.level.camera.getPSY() + 32);
                Graphics.level.textObjects.get("FPS-DOWN-RIGHT").setNewX(Graphics.level.camera.getX() + 3); // DEFAULT : 2
                Graphics.level.textObjects.get("FPS-DOWN-RIGHT").setNewY(Graphics.level.camera.getPSY() + 33); // DEFAULT : 32
            }

            int heartsY = 12 + Graphics.level.camera.getPSY();
            int indent = 42;
            Graphics.level.bitmapObjects.get("heart1").setNewPSX(Graphics.level.camera.getPSX() + indent);
            Graphics.level.bitmapObjects.get("heart1").setNewPSY(heartsY);
            Graphics.level.bitmapObjects.get("heart2").setNewX(Graphics.level.bitmapObjects.get("heart1").getX() + indent);
            Graphics.level.bitmapObjects.get("heart2").setNewPSY(heartsY);
            Graphics.level.bitmapObjects.get("heart3").setNewX(Graphics.level.bitmapObjects.get("heart2").getX() + indent);
            Graphics.level.bitmapObjects.get("heart3").setNewPSY(heartsY);
            Graphics.level.bitmapObjects.get("heart4").setNewX(Graphics.level.bitmapObjects.get("heart3").getX() + indent);
            Graphics.level.bitmapObjects.get("heart4").setNewPSY(heartsY);
            Graphics.level.bitmapObjects.get("heart5").setNewX(Graphics.level.bitmapObjects.get("heart4").getX() + indent);
            Graphics.level.bitmapObjects.get("heart5").setNewPSY(heartsY);

            Graphics.level.bitmapObjects.get(backgroundID).setNewX(Graphics.level.camera.getX());
            Graphics.level.bitmapObjects.get(backgroundID).setNewY(Graphics.level.camera.getY());
        }
    }

    public static void tmStart() {
        tm = new Timer();
        tm.schedule(new TimerTask() {
            @Override
            public void run() {
                if (stateGlobalTimer) {
                    if (Graphics.level.textObjects.get("FPS") != null)
                        Graphics.level.textObjects.get("FPS").text = "FPS : " + Graphics.currFPS;

                    stateTimer = true;
                }
            }
        }, 250);
    }

    public static void setHealth(int health) {
        for (String name : new String[] {"heart1", "heart2", "heart3", "heart4", "heart5"}) {
            Graphics.level.bitmapObjects.get(name).bmp = Bitmaps.game_heart;
        }

        if (health > 0)
            Graphics.level.bitmapObjects.get("heart1").bmp = Bitmaps.game_heart_half;
        if (health > 1)
            Graphics.level.bitmapObjects.get("heart1").bmp = Bitmaps.game_heart_full;
        if (health > 2)
            Graphics.level.bitmapObjects.get("heart2").bmp = Bitmaps.game_heart_half;
        if (health > 3)
            Graphics.level.bitmapObjects.get("heart2").bmp = Bitmaps.game_heart_full;
        if (health > 4)
            Graphics.level.bitmapObjects.get("heart3").bmp = Bitmaps.game_heart_half;
        if (health > 5)
            Graphics.level.bitmapObjects.get("heart3").bmp = Bitmaps.game_heart_full;
        if (health > 6)
            Graphics.level.bitmapObjects.get("heart4").bmp = Bitmaps.game_heart_half;
        if (health > 7)
            Graphics.level.bitmapObjects.get("heart4").bmp = Bitmaps.game_heart_full;
        if (health > 8)
            Graphics.level.bitmapObjects.get("heart5").bmp = Bitmaps.game_heart_half;
        if (health > 9)
            Graphics.level.bitmapObjects.get("heart5").bmp = Bitmaps.game_heart_full;
    }

    public static void reset() {
        stateLeft = false;
        stateRight = false;
        stateJump = false;
        stateTimer = true;
        statePersonLeftTimer = false;
        statePersonRightTimer = false;
        statePersonLeg = true;
        statePersonSide = true;
        pause = false;
        stateGlobalTimer = true;
        timersState = true;
    }

    public static void unload() {
        timersState = false;
        if (tm != null) tm.cancel();
    }
}