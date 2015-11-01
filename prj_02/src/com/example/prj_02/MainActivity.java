package com.example.prj_02;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    private DB db;
    private ListView listView;
    private SimpleCursorAdapter adapter;
    Button add;
    OptionListFragment fragment;
    Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        intent = new Intent(MainActivity.this, AddNewActivity.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        listView = (ListView) findViewById(R.id.listview);
        db = new DB(this);
        add = (Button) findViewById(R.id.add);
        fragment = (OptionListFragment) getFragmentManager().findFragmentById(R.id.options);
        adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_1,
                db.getTasksCursor(),
                new String[]{DB.Table.COL_TASK},
                new int[]{android.R.id.text1},
                0);
        // adapter.getCursor();
        listView.setAdapter(adapter);
    }

    public void onClickAdd(View v) {
        startActivityForResult(intent, 1);
        onActivityResult(1, 1, intent);
    }

    @Override
    protected void onActivityResult(int i, int j, Intent intent) {
        adapter.getCursor();

    }

}
