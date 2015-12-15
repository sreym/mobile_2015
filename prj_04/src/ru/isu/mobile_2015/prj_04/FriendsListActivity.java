package ru.isu.mobile_2015.prj_04;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import org.json.JSONArray;
import org.json.JSONException;

public class FriendsListActivity extends Activity {
    public static final int ACCESS_TOKEN_REQEST = 1;

    ListView listView;
    FriendsListAdapter listViewAdapter;
    VK vk;

    AsyncTask<Void,Void,JSONArray> updateFriendsList = new AsyncTask<Void,Void,JSONArray>() {
        @Override
        protected JSONArray doInBackground(Void... params) {
            try {
                JSONArray friends = vk.callFunction("friends.get", "fields=nickname")
                        .getJSONArray("response");
                return friends;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(JSONArray friends) {
            super.onPostExecute(friends);
            listViewAdapter = new FriendsListAdapter(FriendsListActivity.this, friends);
            listView.setAdapter(listViewAdapter);
        }
    };

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        startActivityForResult(
                new Intent(this,AuthActivity.class),
                ACCESS_TOKEN_REQEST);

        listView = (ListView)findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(FriendsListActivity.this, id + "", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode) {
            case ACCESS_TOKEN_REQEST:
                if (resultCode == AuthActivity.ACCESS_TOKEN_TAKEN) {
                    vk = new VK(data.getStringExtra("access_token"));
                    updateFriendsList.execute();
                }
                break;
        }
    }
}
