package com.example.prj_02;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

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
        intent = new Intent(this, AddNewActivity.class);
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
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View item, int position, long id) {

            }
        });
    }

    public void onClickAdd(View v) {
        startActivity(intent);
    }

    public void refreshList(View v) {
        adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_1,
                db.getTasksCursor(),
                new String[]{DB.Table.COL_TASK},
                new int[]{android.R.id.text1},
                0);
        listView.setAdapter(adapter);
    }

}
