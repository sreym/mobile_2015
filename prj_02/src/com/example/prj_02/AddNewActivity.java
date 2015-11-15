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
import android.widget.Toast;

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
        if (textfield.getText().toString() != "") {
            db.insertTask(textfield.getText().toString());
            this.finish();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Empty Text Field", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public void onClickField(View v) {
        textfield.setText("");
    }
}


