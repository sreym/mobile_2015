package com.example.prj_02;

import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Interstellar on 13.10.2015.
 */
public class DB extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "tasks.db";
    public static int LAST_ID = 0;

    public Cursor getTasksCursor() {
        return this.getReadableDatabase().query(Table.TABLE_NAME, null, null, null, null, null, null);
    }


    public final static class Table {
        private Table() {
        }

        public static final String TABLE_NAME = "tasks";
        public static final String COL_ID = "_id";
        public static final String COL_TASK = "task";
        //   public static final String TASK_TIME = "time";
    }

    public DB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Table.TABLE_NAME + "(" +
                Table.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Table.COL_TASK + " TEXT NOT NULL" +
                ")");
    }

    public void insertTask(String taskName) {
        ContentValues values = new ContentValues();
        try {
            DB.LAST_ID++;
            values.put(DB.Table.COL_ID, DB.LAST_ID);
            values.put(DB.Table.COL_TASK, taskName);
            getWritableDatabase().insert(DB.Table.TABLE_NAME, null, values);
        } catch (Exception e) {

        }
    }

    public void removeTask(String taskName, long id) {
        ContentValues values = new ContentValues();
        try {

            getWritableDatabase().delete(DB.Table.TABLE_NAME, Table.COL_ID + " = " + id, null );
        } catch (Exception e) {

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        if (oldVersion == 2 && newVersion == 3) {
//            oldVersion++;
//        }
    }

}
