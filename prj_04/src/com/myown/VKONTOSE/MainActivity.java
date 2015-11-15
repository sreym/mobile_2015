package com.myown.VKONTOSE;

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
import java.util.List;

/**
 * Created by Interstellar on 10.11.2015.
 */
public class MainActivity extends Activity {
    public final int ACCESS_TOKEN_REQUEST = 1;
    String access_token;
    ListView listView;
    AsyncTask<Void, Void, JSONArray> updateFriendList = new AsyncTask<Void, Void, JSONArray>() {
        @Override
        protected JSONArray doInBackground(Void... params) {
            try {
                JSONArray friends = callVK("friends.get", "fields=nickname").getJSONArray("response");
                return friends;

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onPostExecute(JSONArray friends) {
            super.onPostExecute(friends);
            String[] friendsNames = new String[friends.length()];
            for (int i = 0; i < friends.length(); i++) {
                try {
                    friendsNames[i] = friends.getJSONObject(i).getString("first_name") + " " + friends.getJSONObject(i).getString("last_name");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, friendsNames);
        }
    };

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.main);
        startActivityForResult(new Intent(this, AuthActivity.class), ACCESS_TOKEN_REQUEST);
        listView = (ListView) findViewById(R.id.listView);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ACCESS_TOKEN_REQUEST:
                access_token = data.getStringExtra("access_token");
                updateFriendList.execute();
        }
    }

    JSONObject callVK(String method, String params) {
        try {
            if (params.length() > 0 && params.charAt(params.length() - 1) != '&') params += '&';

            URL url = new URL("https://api.vk.com/method/" + method + "?" + params + "access_token=" + access_token);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder sb = new StringBuilder();

                char[] tl = new char[8 * 1024];
                while (true) {
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
}
