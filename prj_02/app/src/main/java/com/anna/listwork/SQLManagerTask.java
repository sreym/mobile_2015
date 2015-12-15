package com.anna.listwork;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by anna on 08.12.15.
 *
 * Создает и обновляет "Список задач"
 */
public class SQLManagerTask extends SQLiteOpenHelper {

    private static int DATABASE_VERSION = 1;

    private static final String TABLE_TASK = "task";
    private static final String TASK_PARENT_ID = "parent_id";
    private static final String TASK_ID = "id";
    private static final String TASK_TEXT = "text";
    private static final String TASK_DONE = "done";

    private static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_TASK +
            "(" + TASK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + TASK_PARENT_ID + " INTEGER NOT NULL, "
            + TASK_TEXT + " TEXT, " + TASK_DONE + " INTEGER);";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
        onCreate(db);

    }

    public SQLManagerTask(Context context) {
        super(context, TABLE_TASK, null, DATABASE_VERSION);
    }
}
