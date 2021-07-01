package com.wadequns.crazyday.Engine.Main;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.widget.Toast;

import java.io.IOException;

public class Sound {
    private static AssetManager assetManager;
    public static SoundPool sp;
    public static MediaPlayer mp;
    private static AudioManager am;
    private static Context context;

    //INITIALIZE SOUND ENGINE
    public static void init(AssetManager assetMan, Context con, int maxStreams, Object audioServ) {
        am = (AudioManager) audioServ;
        assetManager = assetMan;
        context = con;

        mp = new MediaPlayer();

        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            sp = new SoundPool(maxStreams, AudioManager.STREAM_MUSIC, 0);
        } else {
            AudioAttributes attributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build();

            sp = new SoundPool.Builder().setAudioAttributes(attributes).setMaxStreams(maxStreams).build();
        }
    }

    //LOAD SOUND
    public static int loadSound(String fileName) {
        AssetFileDescriptor afd;

        try {
            afd = assetManager.openFd(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Не удалось загрузить звук " + fileName, Toast.LENGTH_SHORT).show();
            return -1;
        }

        return sp.load(afd, 1);
    }

    //PLAY SOUND | MUSIC
    public static int playSound(int soundID, float volume, int priority, int repeatCount, float speed) {
        return sp.play(soundID, volume, volume, priority, repeatCount, speed);
    }

    public static void playMusic(String path) {
        try {
            AssetFileDescriptor afd = assetManager.openFd(path);
            mp = new MediaPlayer();
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });
            mp.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //RELEASE MEDIA PLAYER
    public static void releaseMP() {
        if (mp != null) {
            try {
                mp.release();
                mp = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
