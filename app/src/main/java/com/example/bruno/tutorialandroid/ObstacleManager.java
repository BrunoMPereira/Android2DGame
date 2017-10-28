package com.example.bruno.tutorialandroid;


import android.graphics.Canvas;
import android.graphics.Color;

import java.util.ArrayList;

/**
 * Created by Bruno on 28/10/2017.
 */

public class ObstacleManager {
    //higher index  = lower on screen = higher y value
    private ArrayList<Obstacle> obstacles;
    private int playerGap;
    private int obstacleGap;
    private int obstacleHeight;

    private long startTime;

    public ObstacleManager(int playerGap, int obstacleGap, int obstacleHeight){
        this.playerGap = playerGap;
        this.obstacleGap = obstacleGap;
        this.obstacleHeight = obstacleHeight;

        startTime = System.currentTimeMillis();

        obstacles = new ArrayList<>();

        populateObstacles();
    }


    private void populateObstacles(){
        int currY = -5*Constants.SCREEN_HEIGHT/4;

        while(currY < 0) { //While the last hasn't gone off the screen yet
            int xStart = (int)(Math.random()*(Constants.SCREEN_WIDTH - playerGap)); //0-1(ex)
            obstacles.add(new Obstacle(obstacleHeight, Color.BLACK, xStart, currY, playerGap));
            currY += obstacleHeight + obstacleGap;
        }
    }

    public void update(){
        int elapsedTime = (int) (System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();

        float speed = (Constants.SCREEN_HEIGHT/8000.0f);

        for(Obstacle ob : obstacles){
            ob.incrementY(speed * elapsedTime);
        }

        if(obstacles.get(obstacles.size() - 1).getRectangle().top  >= Constants.SCREEN_HEIGHT){
            int xStart = (int)(Math.random()*(Constants.SCREEN_WIDTH - playerGap));
            obstacles.add(0, new Obstacle(obstacleHeight, Color.BLACK, xStart, obstacles.get(0).getRectangle().top - obstacleHeight - obstacleGap, playerGap));
            obstacles.remove(obstacles.size() - 1);
        }
    }

    public void draw(Canvas canvas){
        for(Obstacle ob : obstacles){
            ob.draw(canvas);
        }
    }
}
