package com.example.bruno.tutorialandroid.GameObjects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.example.bruno.tutorialandroid.Animation;
import com.example.bruno.tutorialandroid.Constants;
import com.example.bruno.tutorialandroid.R;

/**
 * Created by Bruno on 28/10/2017.
 */

public class RectPlayer implements GameObject {

    private Rect rectangle;
    private int color; //Cores = INTEIROS

    private Animation idle;
    private Animation walkRight;
    private Animation walkLeft;

    public RectPlayer(Rect rectangle, int color){
        this.rectangle = rectangle;
        this.color = color;

        BitmapFactory bf = new BitmapFactory();
        Bitmap idleimg = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.alienBlue);
        Bitmap walk1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.alienBlue_walk1);
        Bitmap walk2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.alienBlue_walk2);


        idle = new Animation(new Bitmap[]{idleimg}, 2);
        walkRight = new Animation(new Bitmap[]{walk1, walk2}, 0.5f);

        //REFLECT A BITMAP
        Matrix m = new Matrix();
        m.preScale(-1, 1);
        walk1 = Bitmap.createBitmap(walk1, 0, 0, walk1.getWidth(), walk1.getHeight(), m, false);
        walk2 = Bitmap.createBitmap(walk2, 0, 0, walk2.getWidth(), walk2.getHeight(), m, false);

        walkLeft = new Animation(new Bitmap[]{walk1, walk2}, 0.5f);
    }

    public Rect getRectangle(){
        return rectangle;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);

        canvas.drawRect(rectangle, paint);
    }

    @Override
    public void update() {

    }

    public void update(Point point){
        float oldLeft = rectangle.left;

        rectangle.set(point.x - rectangle.width()/2, point.y - rectangle.height()/2, point.x + rectangle.width()/2, point.y + rectangle.height()/2);

        int state = 0;

    }
}
