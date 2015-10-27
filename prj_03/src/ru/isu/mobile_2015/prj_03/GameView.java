package ru.isu.mobile_2015.prj_03;

import android.app.Activity;
import android.graphics.*;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergey on 27/10/15.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {
    GameThread gameThread;
    Character player;
    List<GameObject> gameObjects;

    public GameView(Activity context) {
        super(context);

        player = new Character(context, this);

        gameObjects = new ArrayList<>();

        gameObjects.add(player);
        player.setAction(Character.Actions.HURT, Character.Directions.RIGHT);

        this.getHolder().addCallback(this);
        this.setOnTouchListener(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        gameThread = new GameThread(this);
        gameThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    public void transform() {
        for(GameObject obj : gameObjects) {
            obj.transform();
        }
    }

    public void render(Canvas canvas) {
        for(GameObject obj : gameObjects) {
            obj.render(canvas);
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        float x = motionEvent.getX() - this.getWidth() / 2.0f;
        float y = motionEvent.getY() - this.getHeight() / 2.0f;

        if (x >= Math.abs(y))
            player.setAction(Character.Actions.WALK, Character.Directions.RIGHT);
        else if (x <= -Math.abs(y))
            player.setAction(Character.Actions.WALK, Character.Directions.LEFT);
        else if (y >= Math.abs(x))
            player.setAction(Character.Actions.WALK, Character.Directions.DOWN);
        else if (y <= -Math.abs(x))
            player.setAction(Character.Actions.WALK, Character.Directions.UP);

        return false;
    }
}
