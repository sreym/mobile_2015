package ru.isu.mobile_2015.prj_03;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Класс для потока основного цикла игры
 */
public class GameThread extends Thread {
    GameView gameView;

    /**
     * Конструктор потока основного цикла просто запоминает GameView, из которого он был создан
     * @param  gameView
     */
    public GameThread(GameView gameView) {
        this.gameView = gameView;
    }

    @Override
    public void run() {
        // в цикле будем постоянно получать canvas (холст) для рисования, поэтому объявим эту переменную тут
        Canvas canvas;
        while(true) {
            // захват и блокировка холста
            canvas = gameView.getHolder().lockCanvas();

            // очищаем холст
            canvas.drawRect(0,0, canvas.getWidth(), canvas.getHeight(), new Paint());

            // вызываем все необходимые трансформации
            gameView.transform();
            // вызываем все необходимые отрисовки (для отрисовки передаем холст)
            gameView.render(canvas);

            // отпускаем и "рисуем" холст на GameView
            gameView.getHolder().unlockCanvasAndPost(canvas);

            // делаем паусу в 1000/50 секунды
            try {
                Thread.sleep(1000 / 50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
