package com.example.bruno.tutorialandroid.GameObjects;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.bruno.tutorialandroid.Constants;

import java.util.ArrayList;

/**
 * Created by Bruno on 28/10/2017.
 */

public class ObstacleManager {
    //higher index  = lower on screen = higher y value
    private ArrayList<Obstacle> obstacles;
    private int playerGap;
    private int obstacleGap;
    private int obstacleWidth;

    private long startTime;

    private long initTime;

    private int score = 0;

    public ObstacleManager(int playerGap, int obstacleGap, int obstacleWidth){
        this.playerGap = playerGap;
        this.obstacleGap = obstacleGap;
        this.obstacleWidth = obstacleWidth;

        startTime = initTime = System.currentTimeMillis() ;

        obstacles = new ArrayList<>();

        populateObstacles();
    }


    public boolean playerCollide(RectPlayer player){
        for(Obstacle ob : obstacles){
            if(ob.playerCollide(player)) {
                return true;
            }
        }
        return false;
    }

    private void populateObstacles(){
        int currY = -5* Constants.SCREEN_HEIGHT/4;

        while(currY < 0) { //While the last hasn't gone off the screen yet
            int xStart = (int)(Math.random()*(Constants.SCREEN_WIDTH - playerGap)); //0-1(ex)
            obstacles.add(new Obstacle(obstacleWidth, Color.BLACK, xStart, currY, playerGap));
            currY += obstacleWidth + obstacleGap;
        }
    }


    public void update(){
        if(startTime < Constants.INIT_TIME)
            startTime = Constants.INIT_TIME;

        int elapsedTime = (int) (System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();

        float speed = (float) (Math.sqrt(1 + (startTime - initTime)/2000.0) * (Constants.SCREEN_WIDTH/8000.0f));

        for(Obstacle ob : obstacles){
            ob.incrementY(speed * elapsedTime);
        }

        if(obstacles.get(obstacles.size() - 1).getRectangle().top  >= Constants.SCREEN_HEIGHT){
            int xStart = (int)(Math.random()*(Constants.SCREEN_HEIGHT - playerGap));

            obstacles.add(0, new Obstacle(obstacleWidth, Color.BLACK, xStart, obstacles.get(0).getRectangle().top - obstacleWidth - obstacleGap, playerGap));

            obstacles.remove(obstacles.size() - 1);
            score++;
        }

    }

    public void draw(Canvas canvas){
        for(Obstacle ob : obstacles){
            ob.draw(canvas);
        }

        Paint paint = new Paint();
        paint.setTextSize(75);
        paint.setColor(Color.MAGENTA);
        canvas.drawText("Score: " + score, 50, 50 + paint.descent() - paint.ascent(), paint);
    }
}
