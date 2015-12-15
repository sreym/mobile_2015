package com.anna.listwork;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;


public class InteractiveArrayAdapterWorkItem extends ArrayAdapter<WorkItem> {

    public List<WorkItem> getList() {
        return list;
    }

    private final List<WorkItem> list;
    private final Activity context;

    /**
     * Ссылка на фрагмент в котором отображается текущий "Список дел", нужен для вызова функций удаления и изменения элементов списка
     */
    private WorkItemFragment itemFragment;

    public InteractiveArrayAdapterWorkItem(Activity context, List<WorkItem> list, WorkItemFragment itemFragment) {
        super(context, R.layout.work_item, list);
        this.context = context;
        this.list = list;
        this.itemFragment = itemFragment;
    }

    static class ViewHolder {
        protected TextView text;
        protected CheckBox checkbox;
        protected ImageButton button;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            LayoutInflater inf = context.getLayoutInflater();
            view = inf.inflate(R.layout.work_item, null);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.text = (TextView) view.findViewById(R.id.label);
            viewHolder.checkbox = (CheckBox) view.findViewById(R.id.check);
            viewHolder.button = (ImageButton) view.findViewById(R.id.del_work);

            view.setTag(viewHolder);
        }
        else {
            view = convertView;
        }
        final ViewHolder holder = (ViewHolder) view.getTag();

        holder.text.setText(list.get(position).getName());
        holder.checkbox.setChecked(list.get(position).isSelected());

        /**
         * Удаляет задачу из списка
         */
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemFragment.deleteItem(list.get(position).getId());
//                System.out.println("Попытка удаления: " + list.get(position).getName());
            }
        });

        /**
         * Обновляет состояние списка
         */
        holder.checkbox.setOnClickListener(new View.OnClickListener() {
            /**
             * Обновляет состояние задачи в списке
             * @param v
             */
            @Override
            public void onClick(View v) {
                System.out.println(list.get(position).getId());
                if (holder.checkbox.isChecked()) {

                    itemFragment.updateStateTask(list.get(position).getId(), 1);
                }
                else {
                    itemFragment.updateStateTask(list.get(position).getId(), 0);
                }
            }
        });

        return view;
    }
}