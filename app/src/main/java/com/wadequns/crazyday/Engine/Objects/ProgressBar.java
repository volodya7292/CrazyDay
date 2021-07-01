package com.wadequns.crazyday.Engine.Objects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;

import com.wadequns.crazyday.Engine.Main.CONSTANTS;
import com.wadequns.crazyday.Engine.Main.Graphics;

public class ProgressBar {
    private Paint back, fore, border;
    private float multiplier;
    public int x, y, width, height, value, type;
    private GradientDrawable gd;

    public ProgressBar(int x, int y, int width, int height, int max, int value, int back, int fore, int border) {
        this.type = CONSTANTS.DEFAULT_PROGRESSBAR;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.back = new Paint();
        this.fore = new Paint();
        this.border = new Paint();
        this.value = value;
        this.multiplier = (width - 4f) / max;

        this.back.setColor(back);
        this.fore.setColor(fore);
        this.border.setColor(border);
    }

    public ProgressBar(int x, int y, int width, int height, int max, int value, int border) {
        this.type = CONSTANTS.IMAGE_PROGRESSBAR;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.border = new Paint();
        this.value = value;
        this.multiplier = (width - 4f) / max;

        this.border.setColor(border);

        gd = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{ Color.WHITE, Color.rgb(0, 102, 153) });
        gd.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        gd.setShape(GradientDrawable.RECTANGLE);
        gd.setStroke(3, Color.BLACK);
    }

    public void setMax(int max) {
        this.multiplier = (width - 4f) / max;
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(x, y, x + width, y + height, border);

        if (type == CONSTANTS.DEFAULT_PROGRESSBAR) {
            canvas.drawRect(x + 3, y + 3, x + width - 3, y + height - 3, back);

            if (value > 0)
                canvas.drawRect(x + 3, y + 3, x + Graphics.floor(value * multiplier), y + height - 3, fore);
        } else if (type == CONSTANTS.IMAGE_PROGRESSBAR) {
            gd.setBounds(x, y, x + Graphics.floor(value * multiplier) + 4, y + height);
            gd.draw(canvas);
        }
    }
}
