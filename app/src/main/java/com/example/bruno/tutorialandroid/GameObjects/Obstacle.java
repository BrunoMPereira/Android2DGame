package com.example.bruno.tutorialandroid.GameObjects;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.bruno.tutorialandroid.Constants;

/**
 * Created by Bruno on 28/10/2017.
 */

public class Obstacle implements GameObject {

    private Rect rectangle, rectangle2;
    private int color;

    public Obstacle(int rectHeight, int color, int startX,int startY, int playerGap){
        this.color = color;
        rectangle = new Rect(0, startY, startX, startY+rectHeight);
        rectangle2 = new Rect(startX + playerGap, startY, Constants.SCREEN_WIDTH, startY+rectHeight);
    }

    public Rect getRectangle(){
        return rectangle;
    }

    public boolean playerCollide(RectPlayer player){
        return Rect.intersects(rectangle, player.getRectangle()) || Rect.intersects(rectangle2, player.getRectangle());
    }


    public void incrementY(float dy){
        rectangle.top+=dy;
        rectangle2.top+=dy;

        rectangle.bottom+=dy;
        rectangle2.bottom+=dy;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);

        canvas.drawRect(rectangle, paint);
        canvas.drawRect(rectangle2, paint);
    }

    @Override
    public void update() {

    }
}
