package com.example.prj_04;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MyActivity extends Activity {
    public static final int ACCESS_TOKEN_REQEST = 1;

    ListView listView;
    String access_token;

    AsyncTask<Void,Void,JSONArray> updateFriendsList = new AsyncTask<Void,Void,JSONArray>() {
        @Override
        protected JSONArray doInBackground(Void... params) {
            try {
                JSONArray friends = callVK("friends.get", "fields=nickname")
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
            String[] friendsNames = new String[friends.length()];
            try {
                for(int i = 0; i < friends.length(); i++) {
                    friendsNames[i] = friends.getJSONObject(i).getString("first_name") +
                            " " + friends.getJSONObject(i).getString("last_name");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            ArrayAdapter<String> adapter = new ArrayAdapter(
                    MyActivity.this,
                    android.R.layout.simple_list_item_1,
                    friendsNames);

            listView.setAdapter(adapter);
        }
    };

    // http://pastebin.com/xBea9zhK
    JSONObject callVK(String method, String params) {
        try {
            if (params.length() > 0 && params.charAt(params.length() - 1) != '&') params += '&';

            URL url = new URL("https://api.vk.com/method/"+method+"?"+params+"access_token=" + access_token);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder sb = new StringBuilder();

                char[] tl = new char[8 * 1024];
                while(true) {
                    int len = in.read(tl);
                    if (len > 0) {
                        sb.append(tl, 0, len);
                    } else {
                        break;
                    }
                }

                return new JSONObject(sb.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode) {
            case ACCESS_TOKEN_REQEST:
                if (resultCode == AuthActivity.ACCESS_TOKEN_TAKEN) {
                    access_token = data.getStringExtra("access_token");
                    updateFriendsList.execute();
                }
                break;
        }
    }
}
