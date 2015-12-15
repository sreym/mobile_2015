package com.anna.listwork;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainFrame extends AppCompatActivity {
    public ListItemFragment getMyFragment() {
        return myFragment;
    }

    private ListItemFragment myFragment;
    private WorkItemFragment workItemFragment;

    private FragmentTransaction fragmentTransaction;
    /**
     * Устаналивает фрагмент содержащий "Список списков"
     */
    public void setListView() {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        myFragment = new ListItemFragment(this);
        fragmentTransaction.replace(R.id.container, myFragment);
        fragmentTransaction.commit();
    }

    /**
     * Устанавливает фрагмент со списком задач
     * @param id
     */
    public void setWorkItem(int id, String title) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        workItemFragment = new WorkItemFragment(this, id, title);
        fragmentTransaction.replace(R.id.container, workItemFragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_frame);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        myFragment = new ListItemFragment(this);
        fragmentTransaction.add(R.id.container, myFragment);
        fragmentTransaction.commit();
    }
}
