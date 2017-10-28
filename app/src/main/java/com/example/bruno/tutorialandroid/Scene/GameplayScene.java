package com.example.bruno.tutorialandroid.Scene;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.example.bruno.tutorialandroid.Constants;
import com.example.bruno.tutorialandroid.GameObjects.ObstacleManager;
import com.example.bruno.tutorialandroid.GameObjects.RectPlayer;

/**
 * Created by Bruno on 28/10/2017.
 */

public class GameplayScene implements Scene {

    private Rect r = new Rect(); //Usado para o texto

    private RectPlayer player;
    private Point playerPoint;
    private ObstacleManager obstacleManager;

    private boolean movingPlayer = false;


    private boolean gameOver = false;
    private long gameOverTime;


    public GameplayScene(){


        player = new RectPlayer(new Rect(100, 100, 200, 200), Color.rgb(255, 0, 0));

        playerPoint = new Point(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT - 150);
        player.update(playerPoint);

        obstacleManager = new ObstacleManager(200, 275, 60);
    }


    public void reset() {
        playerPoint = new Point(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT - 150);
        player.update(playerPoint);
        obstacleManager = new ObstacleManager(200, 275, 60);
        movingPlayer = false;
    }

    @Override
    public void receiveTouch(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:  //CLICK
                if (!gameOver && player.getRectangle().contains((int) event.getX(), (int) event.getY())) {
                    movingPlayer = true;
                }
                if (gameOver && (System.currentTimeMillis() - gameOverTime) >= 2000) {
                    reset();
                    gameOver = false;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (!gameOver && movingPlayer) {
                    playerPoint.set((int) event.getX(), playerPoint.y);
                }
                break;
            case MotionEvent.ACTION_UP:
                movingPlayer = false;
                break;
        }
    }

    @Override
    public void update() {
        if (!gameOver) {
            player.update(playerPoint);
            obstacleManager.update();
            if (obstacleManager.playerCollide(player)) {
                gameOver = true;
                gameOverTime = System.currentTimeMillis();
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);

        player.draw(canvas);

        obstacleManager.draw(canvas);

        if (gameOver) { //GameOver Screen

            Paint p = new Paint();
            p.setTextSize(100);
            p.setColor(Color.MAGENTA);

            drawCenterText(canvas, p, "Game Over");
        }
    }

    private void drawCenterText(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = cHeight / 2f + r.height() / 2f - r.bottom;
        canvas.drawText(text, x, y, paint);
    }

    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 0;
    }
}
