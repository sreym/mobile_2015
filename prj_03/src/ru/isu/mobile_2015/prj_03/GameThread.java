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

            canvas.drawRect(0,0, canvas.getWidth(), canvas.getHeight(), new Paint());

            gameView.transform();
            gameView.render(canvas);

            gameView.getHolder().unlockCanvasAndPost(canvas);

            try {
                this.sleep(1000 / 50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
