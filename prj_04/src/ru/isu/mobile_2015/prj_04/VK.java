package ru.isu.mobile_2015.prj_04;

import android.content.Intent;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sergey on 10/11/15.
 */
public class VK {
    public static String getAuthUrl(String clientId, String scope) {
        return "https://oauth.vk.com/authorize" +
                "?client_id=" + clientId +
                "&scope=" + scope +
                "&redirect_uri=https://oauth.vk.com/blank.html" +
                "&display=mobile" +
                "&v=5.40" +
                "&response_type=token";
    }

    public static String findAccessTokenInURL(String url) {
        Matcher matcher = Pattern.compile(
                "access_token=([0-9a-f]+)"
        ).matcher(url);

        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private String token;

    public VK(String token) {
        this.token = token;
    }

    JSONObject callFunction(String method, String params) {
        try {
            if (params.length() > 0 && params.charAt(params.length() - 1) != '&') params += '&';

            URL url = new URL("https://api.vk.com/method/"+method+"?"+params+"access_token=" + token);
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
}
