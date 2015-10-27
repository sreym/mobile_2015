package ru.isu.mobile_2015.prj_03;

import android.content.Context;
import android.graphics.*;

import java.io.IOException;

/**
 * Created by sergey on 27/10/15.
 */
public class Character extends GameObject {
    final static int ACTION_FRAMES[] = {7,7,7,7, 8,8,8,8, 9,9,9,9, 6,6,6,6, 13,13,13,13, 6};
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
    int x = 0;
    int y = 0;
    int dx = 0;
    int dy = 0;
    int width = 64;
    int height = 64;
    int action = 0;
    int frame = 0;

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
        frame++;
        frame %= ACTION_FRAMES[action] * 3;

        if (dx < 0 && x + dx - width/2 >= 0) x += dx;
        if (dx >= 0 && x + dx + width/2 <= game.getWidth()) x += dx;
        if (dy < 0 && y + dy - height/2 >= 0) y += dy;
        if (dy >= 0 && y + dy + height/2 <= game.getHeight()) y += dy;
    }

    @Override
    public void render(Canvas canvas) {
        int dframe = frame / 3;
        Rect src = new Rect(64 * dframe, 64 * action, 64 * (dframe + 1), 64 * (action + 1));
        Rect dst = new Rect(x - width / 2, y - height / 2, x + width - width / 2, y + height - height / 2);
        canvas.drawBitmap(character, src, dst, new Paint());
    }
}
