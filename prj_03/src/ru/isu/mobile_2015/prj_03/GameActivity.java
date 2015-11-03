package ru.isu.mobile_2015.prj_03;

import android.app.Activity;
import android.os.Bundle;

public class GameActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // в основной активности в качестве единственного view на ней создзаем и устанваливаем GameView
        setContentView(new GameView(this));
    }
}
