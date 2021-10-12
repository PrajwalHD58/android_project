package com.example.task_internship;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.Random;

public class Drawcircle extends View {
    int red = (int) (Math.random() * 255);
    int green = (int) (Math.random() * 255);
    int blue = (int) (Math.random() * 255);
    public Drawcircle(Context con)
    {
        super(con);
    }
    @SuppressLint("DrawAllocation")
    @Override
    public void onDraw(Canvas c)
    {
        super.onDraw(c);
        int color = Color.rgb(red, green, blue);
        int minRadius = 150;
        Random random = new Random();//define this outside you onDraw fucntion
        int w = getWidth();
        int h = getHeight();

        int randX = random.nextInt(w);
        int randY = random.nextInt(h);
        int randR = minRadius + random.nextInt(100);

        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setAntiAlias(true);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(100);
        p.setColor(color);
        p.setStyle(Paint.Style.FILL);
        c.drawCircle(randX, randY, randR, p);
        clear(c);

    }
    public void clear(Canvas c)
    {
        c.drawColor(Color.TRANSPARENT);
    }
}
