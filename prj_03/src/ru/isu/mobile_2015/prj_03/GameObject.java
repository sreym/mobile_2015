package ru.isu.mobile_2015.prj_03;

import android.graphics.Canvas;

/**
 * Абстрактный класс для всех объектов игры
 * Подразумевается, что у них появятся ещё какие-то свойства или методы
 * но пока их тут 2 абстрактных.
 */
public abstract class GameObject {

    /**
     * Метод, который вызывается перед отрисовкой.
     * Подразумевается, что здесь будет находиться "физика" игры.
     */
    abstract void transform();

    /**
     * Метод отрисовки игрового объекта
     * @param canvas холст, на котором его надо нарисовать
     */
    abstract void render(Canvas canvas);
}
