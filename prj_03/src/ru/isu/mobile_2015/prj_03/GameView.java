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
 * Игровое представление
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {
    GameThread gameThread;          // указатель на поток игрового цикла
    Character player;               // объект игрока
    List<GameObject> gameObjects;   // массив всех игровых объектов

    public GameView(Activity context) {
        super(context);

        gameObjects = new ArrayList<>();

        // чтобы работать с событиями холста, в качестве обработчика событий сделаем класс GameView
        // для этого он реализует интерфейс SurfaceHolder.Callback
        this.getHolder().addCallback(this);

        // чтобы обрабатывать касания по экрану в качестве обработчика этих событий делам класс GameView
        // для этого он реализует интефрейс View.OnTouchListener
        this.setOnTouchListener(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        // когда поверхность холста создана инициализируем все необходимые нам объекты:
        // игрок и игровой поток
        player = new Character(this.getContext(), this);
        gameObjects.add(player);
        player.setAction(Character.Actions.HURT, Character.Directions.RIGHT);

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
        // вызов трансформаций всех игровых объектов
        for(GameObject obj : gameObjects) {
            obj.transform();
        }
    }

    public void render(Canvas canvas) {
        // вызов отрисовки всех игровых объектов
        for(GameObject obj : gameObjects) {
            obj.render(canvas);
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        // определяем координаты касания и сдвигаем их в центр (на половину ширины и половину высоты),
        // чтобы координата (0,0) находилась в центре экрана
        float x = motionEvent.getX() - this.getWidth() / 2.0f;
        float y = motionEvent.getY() - this.getHeight() / 2.0f;

        // определяем в каком из "треугольников" экрана произошел "клик"
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
