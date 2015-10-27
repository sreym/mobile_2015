package ru.isu.mobile_2015.prj_03;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by sergey on 27/10/15.
 */
public class GameThread extends Thread {
    GameView gameView;
    public GameThread(GameView gameView) {
        this.gameView = gameView;
    }

    @Override
    public void run() {
        Canvas canvas;
        while(true) {
            canvas = gameView.getHolder().lockCanvas();

            Paint p = new Paint();
            p.setColor(Color.WHITE);

            canvas.drawRect(10,10,400,400, p);

            gameView.getHolder().unlockCanvasAndPost(canvas);
        }
    }
}
