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
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anna on 09.12.15.
 */
public class ListItemFragment extends Fragment {
    private static final String TABLE_LIST = "list";
    private static final String LIST_ID = "id";
    private static final String LIST_NAME = "name";

    private SQLManagerList managerList;
    private SQLiteDatabase database;

    private SQLManagerTask managerTask;

    private Button add;

    private EditText newList;

    private InteractiveArrayAdapterListItem adapter;

    private MainFrame mainFrame;

    @SuppressLint("ValidFragment")
    public ListItemFragment(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public ListItemFragment() {}

    /**
     * @param list Новый список задач
     * @return
     */
    private long createNewWorkList(String list){
        ContentValues values = new ContentValues();
        values.put(LIST_NAME, list);
        return database.insert(TABLE_LIST, null, values);
    }

    /**
     * @return Список списков задач
     */
    private Cursor selectListWork() {
        String[] cols = new String[] {LIST_ID, LIST_NAME};
        Cursor mCursor = database.query(true, TABLE_LIST, cols, null, null, null, null, null, null);
        return mCursor;
    }

    /**
     * Преобразуем список списков из базы данных в список объектов
     * @param cursor
     * @return
     */
    private List<ListItem> getListItemArray(Cursor cursor) {
        List<ListItem> listItems = new ArrayList<>();
        ListItem item;

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(managerList.LIST_ID));
            String name = cursor.getString(cursor.getColumnIndex(managerList.LIST_NAME));
            item = new ListItem(name, id);
            listItems.add(item);
        }

        return listItems;
    }

    /**
     * Перезагружает данные в Adapter
     */
    private void reloadAllData() {
        List<ListItem> objects = getListItemArray(selectListWork());
        adapter.getData().clear();
        adapter.getData().addAll(objects);
        adapter.notifyDataSetChanged();
    }

    /**
     * Удаляет элемент из списка
     * @param id
     */
    public void deleteItem(int id) {
        database.delete(TABLE_LIST, LIST_ID + "=" + id, null);

        /**
         * Удаляет все задачи связанные с данным списком
         */
        database = managerTask.getWritableDatabase();
        database.delete("task", "parent_id = " + id, null);

        database = managerList.getWritableDatabase();
        reloadAllData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        managerList = new SQLManagerList(mainFrame);
        managerTask = new SQLManagerTask(mainFrame);

        database = managerList.getWritableDatabase();

        List<ListItem> listItems = getListItemArray(selectListWork());
        adapter = new InteractiveArrayAdapterListItem(mainFrame, listItems);
        ListView listView = (ListView) rootView.findViewById(R.id.listView);
        listView.setAdapter(adapter);

        newList = (EditText) rootView.findViewById(R.id.list);

        add = (Button) rootView.findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String list = newList.getText().toString();
                /**
                 * Если поле с названием списка не было пустым при нажатии кнопки
                 */
                if (!list.trim().equals("")) {
                    createNewWorkList(list);
                    reloadAllData();
                }
            }
        });
        return rootView;
    }
}
