package com.myown.VKONTOSE;

import android.content.Context;
import android.widget.ArrayAdapter;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Interstellar on 10.11.2015.
 */
public class myArrayAdapter extends ArrayAdapter<JSONObject> {
    public myArrayAdapter(Context context, JSONArray list) {
        super(context,null, list);
    }
}
