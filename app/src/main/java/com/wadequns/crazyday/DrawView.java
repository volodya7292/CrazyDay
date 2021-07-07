package com.wadequns.crazyday;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class DrawView extends SurfaceView implements SurfaceHolder.Callback {
    final int MAX_FPS = 60;
    final int MAX_FRAME_TIME = 1000 / MAX_FPS;

    int frameTime = 1;
    int accum = 0;

    Timer fpsTimer;

    public DrawView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setLayerType(View.LAYER_TYPE_HARDWARE, null);
        getHolder().addCallback(this);
        setWillNotDraw(false);
        setFocusable(true);

        fpsTimer = new Timer();
        fpsTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                int fps = accum;
                frameTime = 1000 / Math.max(fps, 1);
                accum = 0;
            }
        }, 0, 1000);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        fpsTimer.cancel();
    }

    @Override
    public void onDraw(Canvas canvas) {
        long t0 = System.nanoTime();
        MainActivity.onDraw(canvas, Math.max(frameTime, 1));
        long t1 = System.nanoTime();
        long renderTime = (int) ((t1 - t0) / 1_000_000);

        long additionalSleep = Math.min(Math.max(MAX_FRAME_TIME - renderTime, 0), MAX_FRAME_TIME);
        try {
            Thread.sleep(additionalSleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        invalidate();
        accum++;
    }
}
