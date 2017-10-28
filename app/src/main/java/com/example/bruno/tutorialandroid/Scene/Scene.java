package com.example.bruno.tutorialandroid.Scene;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by Bruno on 28/10/2017.
 */

public interface Scene {
    /**
     * PODERÁ SER UMA CLASSE ABSTRACTA
     */
    public void update();
    public void draw(Canvas canvas);
    public void terminate();
    public void receiveTouch(MotionEvent event);




}
