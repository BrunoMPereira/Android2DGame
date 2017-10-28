package com.example.bruno.tutorialandroid;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
/**
 * Created by Bruno on 29/10/2017.
 */

public class Animation {

    private Bitmap[] frames; //images
    private int frameIndex;

    private boolean isPlaying = false;

    public boolean isPlaying(){
        return isPlaying;
    }

    public void play(){
        frameIndex = 0;
        this.isPlaying = true;
        lastFrame = System.currentTimeMillis();
    }

    public void stop(){
        this.isPlaying = false;
    }

    private float frameTime;

    private long lastFrame;

    public Animation(Bitmap[] frames, float animTime){
        this.frames = frames;
        this.frameIndex = 0;

        this.frameTime = animTime/frames.length;

        this.lastFrame = System.currentTimeMillis();
    }

    public void update(){
        if(!isPlaying)
            return;

        if(System.currentTimeMillis() - lastFrame > frameTime*1000){
            frameIndex++;
            frameIndex = frameIndex >= frames.length ? 0 : frameIndex;
            lastFrame = System.currentTimeMillis();

        }
    }

    public void draw(Canvas canvas, Rect destination){
        if(isPlaying)
            return;


        canvas.drawBitmap(frames[frameIndex], null, destination, new Paint());
    }



}
