package ru.isu.mobile_2015.prj_03;

import android.graphics.Canvas;

/**
 * Created by sergey on 27/10/15.
 */
public abstract class GameObject {
    abstract void transform();
    abstract void render(Canvas canvas);
}
