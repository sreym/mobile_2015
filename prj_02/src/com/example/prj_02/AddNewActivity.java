package com.example.prj_02;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Created by Interstellar on 13.10.2015.
 */
public class AddNewActivity extends Activity {
    private DB db;
    EditText textfield;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnew);
        textfield = (EditText) findViewById(R.id.textfield);
        db = new DB(this);
    }

    public void onClickAdd2(View v) {
        db.insertTask(textfield.getText().toString());
        this.setResult(2);
        this.finish();
    }
}


