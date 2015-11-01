package com.example.prj_02;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Interstellar on 13.10.2015.
 */
public class OptionListFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle onSavedInstance){
        return inflater.inflate(R.layout.optionlist, container, false);
    }

}
