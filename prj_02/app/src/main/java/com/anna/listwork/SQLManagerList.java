package com.anna.listwork;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by anna on 08.12.15.
 *
 * Создает и обновляет таблицу "Списков списков задач"
 */
public class SQLManagerList extends SQLiteOpenHelper {
    /**
     * Имя таблицы
     */
    private static final String TABLE_LIST = "list";

    /**
     * Номер версии схемы таблицы
     */
    private static int DATABASE_VERSION = 1;

    /**
     * Поля содержащиеся в таблице
     */
    public static final String LIST_ID = "id";
    public static final String LIST_NAME = "name";

    /**
     * Запрос на создание таблицы
     */
    private static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_LIST +
            " (" + LIST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + LIST_NAME + " TEXT);";

    /**
     * Вызывается при создании таблицы
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    /**
     * Вызывается при обновлении таблицы
     * @param db
     * @param oldVersion номер версии старой таблицы
     * @param newVersion номер версии новой таблицы
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIST);
        onCreate(db);
    }

    public SQLManagerList(Context context) {
        super(context, TABLE_LIST, null, DATABASE_VERSION);
    }
}
