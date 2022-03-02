package com.androidteamproject.span;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.LineBackgroundSpan;

public class LinearSpan implements LineBackgroundSpan {

    //public static final float DEFAULT_RADIUS = 3;

    //private final float radius;
    private final int color;

    public LinearSpan() {
        this.color = 0;
    }

    public LinearSpan(int color) {
        this.color = color;
    }

    @Override
    public void drawBackground(
            Canvas canvas, Paint paint,
            int left, int right, int top, int baseline, int bottom,
            CharSequence charSequence,
            int start, int end, int lineNum
    ) {
        int oldColor = paint.getColor();
        if (color != 0) {
            paint.setColor(color);
        }
        canvas.drawRect(left,bottom, right, bottom+((top+bottom)/3), paint);
        paint.setColor(oldColor);
    }
}