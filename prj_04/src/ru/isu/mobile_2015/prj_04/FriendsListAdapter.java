package ru.isu.mobile_2015.prj_04;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sergey on 10/11/15.
 */
public class FriendsListAdapter extends BaseAdapter {
    JSONArray mFriends;
    Context mContext;

    public FriendsListAdapter(Context context, JSONArray friends) {
        mFriends = friends;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mFriends.length();
    }

    @Override
    public JSONObject getItem(int position) {
        try {
            return mFriends.getJSONObject(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        try {
            return mFriends.getJSONObject(position).getLong("uid");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.friends_list_item, parent, false);
        TextView textView1 = (TextView) rowView.findViewById(R.id.text1);
        TextView textView2 = (TextView) rowView.findViewById(R.id.text2);
        try {
            textView1.setText(mFriends.getJSONObject(position).getString("first_name"));
            textView2.setText(mFriends.getJSONObject(position).getString("last_name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return rowView;
    }
}
