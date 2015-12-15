package com.anna.listwork;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by anna on 08.12.15.
 *
 * Adapter для визуализации "Списка списков"
 */
public class InteractiveArrayAdapterListItem extends ArrayAdapter<ListItem> {
    /**
     * Окружение в котором происходит отрисовка списка
     */
    private Activity context;

    /**
     * Список списков
     */
    private List<ListItem> list;

    /**
     * Возвращает список визуализированный адаптером
     * @return
     */
    public List<ListItem> getData() {
        return list;
    }

    public InteractiveArrayAdapterListItem(Activity context, List<ListItem> list) {
        super(context, R.layout.list_layout, list);
        this.list = list;
        this.context = context;
    }

    /**
     * Класс привязки содержимого списка и реализации поведения
     */
    public static class ViewHolder {
        protected TextView text;
        protected ImageButton button;
    }

    /**
     * Добавляет элементы списка ListItem в Adapter
     * @param position позиция в списке
     * @param convertView отоброжающий элемент, сюда добавляются все элементы списка
     * @param parent
     * @return
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            LayoutInflater inf = context.getLayoutInflater();
            view = inf.inflate(R.layout.list_layout, null);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.text = (TextView) view.findViewById(R.id.label);
            viewHolder.button = (ImageButton) view.findViewById(R.id.del_list);
            view.setTag(viewHolder);
        }
        else {
            view = convertView;
        }
        ViewHolder holder = (ViewHolder) view.getTag();

        holder.text.setText(list.get(position).getText());

        /**
         * Срабатывает при нажатии на кнопку DEL в списке дел
         */
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainFrame) context).getMyFragment().deleteItem(list.get(position).getId());
            }
        });

        /**
         * Раскрывает список дел
         */
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainFrame) context).setWorkItem(list.get(position).getId(), list.get(position).getText());
            }
        });

        return view;
    }
}
