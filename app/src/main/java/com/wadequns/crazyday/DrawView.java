package com.wadequns.crazyday;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class DrawView extends SurfaceView implements SurfaceHolder.Callback {

    private DrawThread drawThread;
    int frameTime = 1;

    final int MAX_FPS = 60;
    final int MAX_FRAME_TIME = 1000 / MAX_FPS;

    public DrawView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setLayerType(View.LAYER_TYPE_HARDWARE, null);
        getHolder().addCallback(this);
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        drawThread = new DrawThread(getHolder());
        drawThread.setRunning(true);
        drawThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        drawThread.setRunning(false);
        while (retry) {
            try {
                drawThread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }

    class DrawThread extends Thread {
        private boolean running = false;
        private final SurfaceHolder surfaceHolder;

        public DrawThread(SurfaceHolder surfaceHolder) {
            this.surfaceHolder = surfaceHolder;
        }

        public void setRunning(boolean running) {
            this.running = running;
        }

        @Override
        public void run() {
            while (running) {
                Canvas canvas = null;
                try {
                    canvas = surfaceHolder.lockCanvas();

                    synchronized (surfaceHolder) {
                        long t0 = System.nanoTime();
                        MainActivity.onDraw(canvas, Math.max(frameTime, 1));
                        long t1 = System.nanoTime();
                        frameTime = (int) ((t1 - t0) / 1_000_000);
                        Thread.sleep(Math.min(Math.max(MAX_FRAME_TIME - frameTime, 0), MAX_FRAME_TIME));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (canvas != null) {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
            }
        }
    }
}
