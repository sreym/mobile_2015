package ru.isu.mobile_2015.prj_03;

import android.content.Context;
import android.graphics.*;

import java.io.IOException;

/**
 * Класс Character содержит необходимые методы и свойства персонажа
 */
public class Character extends GameObject {
    /**
     * Константа ACTION_FRAMES содержит количество кадров в строке для спрайта
     */
    final static int ACTION_FRAMES[] = {7,7,7,7, 8,8,8,8, 9,9,9,9, 6,6,6,6, 13,13,13,13, 6};

    /**
     * Действия персонажа (в скобках указан номер группы строк (из 4-х) в спрайте)
     */
    enum Actions {
        SPELLCAST(0),
        THRUST(1),
        WALK(2),
        SLASH(3),
        SHOOT(4),
        HURT(5);

        int v;
        Actions(int v) {this.v = v;}
    }

    /**
     * Направление движения персонажа (в скобках указан номер строки в группе строк, см. Actions)
     * Но! Группа HURT состоит из одного направления
     */
    enum Directions {
        UP(0),
        LEFT(1),
        DOWN(2),
        RIGHT(3);

        int v;
        Directions(int v) {this.v = v;}
    }

    GameView game;
    Bitmap character;

    // положение персонажа
    int x = 0;
    int y = 0;

    // вектор движения (dx,dy) – d – сокращение от delta
    int dx = 0;
    int dy = 0;

    int width = 64;
    int height = 64;
    int action = 0;
    int frame = 0;

    /**
     * Конструктор персонажа, которому нужен контекст context для загрузки файлов
     * и GameView для того, чтобы, если надо будет, знать где находятся другие объекты в игровом мире
     * @param  context контекст приложения
     * @param  game    контекст игры
     */
    public Character(Context context, GameView game) {
        this.game = game;
        this.x = game.getWidth() / 2;
        this.y = game.getHeight() / 2;
        try {
            character = BitmapFactory.decodeStream(context.getAssets().open("character.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * Устанавливает действие для персонажа
     * @param action    действие, например: идти
     * @param direction направление действия, например: вверх
     */
    public void setAction(Actions action, Directions direction) {
        if (action.equals(Actions.HURT))
            this.action = 4 * action.v;
        else
            this.action = 4 * action.v + direction.v;

        switch(direction) {
            case LEFT:  dx = -5; dy = 0; break;
            case RIGHT: dx = 5;  dy = 0; break;
            case UP:    dx = 0;  dy = -3; break;
            case DOWN:  dx = 0;  dy = 3; break;
        }
    }

    @Override
    public void transform() {
        // увеличивается номер фрейма в спрайте (номер по горизонтали)
        frame++;
        frame %= ACTION_FRAMES[action] * 3;

        // согласно значениям в (dx, dy) меняется координата (x,y) персонажа
        if (dx < 0 && x + dx - width/2 >= 0) x += dx;
        if (dx >= 0 && x + dx + width/2 <= game.getWidth()) x += dx;
        if (dy < 0 && y + dy - height/2 >= 0) y += dy;
        if (dy >= 0 && y + dy + height/2 <= game.getHeight()) y += dy;
    }

    @Override
    public void render(Canvas canvas) {
        int dframe = frame / 3;
        // определяем квадрат для кадра в спрайте
        Rect src = new Rect(64 * dframe, 64 * action, 64 * (dframe + 1), 64 * (action + 1));
        // определяем место куда этот квадрат вывести
        Rect dst = new Rect(x - width / 2, y - height / 2, x + width - width / 2, y + height - height / 2);
        
        canvas.drawBitmap(character, src, dst, new Paint());
    }
}
