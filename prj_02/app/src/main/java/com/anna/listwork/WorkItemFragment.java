package com.anna.listwork;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anna on 09.12.15.
 */
public class WorkItemFragment extends Fragment {
    private static final String TABLE_TASK = "task";
    private static final String TASK_ID = "id";
    private static final String TASK_TEXT = "text";
    private static final String TASK_DONE = "done";
    private static final String TASK_PARENT_ID = "parent_id";

    private SQLManagerTask managerList;
    private SQLiteDatabase database;

    private Button add;
    private ImageButton back;

    private EditText newTask;

    private InteractiveArrayAdapterWorkItem adapter;

    public WorkItemFragment() {}

    private int parentId;

    private MainFrame mainFrame;

    private String title;

    private TextView _title;

    /**
     * Обновляет состоние задачи "Выполнена/Не выполнена"
     * @param id
     * @param done
     */
    public void updateStateTask(int id, int done) {
        ContentValues args = new ContentValues();
        String filter = "id = '" + id + "'";
        args.put(TASK_DONE, done);
        database.update(TABLE_TASK, args, filter, null);
    }

    /**
     * Создает новую задачу, изначально задача считается не выполненной
     * @param list
     * @return
     */
    private long createNewWorkTask(String list){
        ContentValues values = new ContentValues();
        values.put(TASK_PARENT_ID, parentId);
        values.put(TASK_TEXT, list);
        values.put(TASK_DONE, 0);
        return database.insert(TABLE_TASK, null, values);
    }

    /**
     * Выгружает таблицу задач для определенного Списка, соответветствие устанавливается
     * @return
     */
    private Cursor selectWorkItem() {
        String[] cols = new String[] {TASK_ID, TASK_TEXT, TASK_DONE};
        Cursor mCursor = database.query(true, TABLE_TASK, cols, "parent_id = '" + parentId + "'", null, null, null, null, null);
        return mCursor;
    }

    /**
     * Преобразует задачи из базы данных в объекты
     * @param cursor
     * @return
     */
    private List<WorkItem> getWorkItemList(Cursor cursor) {
        List<WorkItem> listItems = new ArrayList<>();
        WorkItem item;

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(TASK_ID));
            String name = cursor.getString(cursor.getColumnIndex(TASK_TEXT));
            int done = cursor.getInt(cursor.getColumnIndex(TASK_DONE));
            System.out.println(id + " " + name + " " + done);
            item = new WorkItem(name, id, done);
            listItems.add(item);
        }

        return listItems;
    }

    /**
     * Перезагружает данные в Adapter
     */
    private void reloadAllData() {
        List<WorkItem> objects = getWorkItemList(selectWorkItem());
        adapter.getList().clear();
        adapter.getList().addAll(objects);
        adapter.notifyDataSetChanged();
    }

    /**
     * Удаляет задачу из списка
     * @param id
     */
    public void deleteItem(int id) {
        database.delete(TABLE_TASK, TASK_ID + "=" + id, null);
        reloadAllData();
    }

    @SuppressLint("ValidFragment")
    public WorkItemFragment(MainFrame mainFrame, int parentId, String title) {
        this.mainFrame = mainFrame;
        this.parentId = parentId;
        this.title = title;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_work, container, false);

        managerList = new SQLManagerTask(mainFrame);
        database = managerList.getWritableDatabase();

        _title = (TextView) rootView.findViewById(R.id.parent_title);
        _title.setText(title);

        List<WorkItem> listItems = getWorkItemList(selectWorkItem());
        adapter = new InteractiveArrayAdapterWorkItem(mainFrame, listItems, this);
        ListView listView = (ListView) rootView.findViewById(R.id.listView);
        listView.setAdapter(adapter);

        newTask = (EditText) rootView.findViewById(R.id.task);

        add = (Button) rootView.findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String list = newTask.getText().toString();
                /**
                 * Если поле с названием списка не было пустым при нажатии кнопки
                 */
                if (!list.trim().equals("")) {
                    createNewWorkTask(list);
                    reloadAllData();
                }
            }
        });

        back = (ImageButton) rootView.findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainFrame.setListView();
            }
        });

        return rootView;
    }
}
