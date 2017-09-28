package com.example.jimmy.mychatapp.user;

import android.os.AsyncTask;
import android.util.Log;

import com.example.jimmy.mychatapp.common.user;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jimmy on 2017/9/28.
 */

public class getUserByEmailTask extends AsyncTask<Object,Integer,user> {

    private static final String TAG = "getUserByEmailTask";

    @Override
    protected user doInBackground(Object... params) {
        String url = params[0].toString()+params[1].toString();
        String jsonIn;
        try{
            jsonIn = getUser(url);
        }catch (Exception ex){
            Log.d(TAG,ex.toString());
            return null;
        }
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss+08:00'").create();
        user user = gson.fromJson(jsonIn,user.class);
        return user;
    }

    private String getUser(String url) throws IOException{
        StringBuilder jsonIn = new StringBuilder();
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-length","0");
        connection.setUseCaches(false);
        connection.setAllowUserInteraction(false);

        //set Timeout
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        try{
            int responseCode = connection.getResponseCode();

            switch (responseCode){
                case 200:
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    while((line = br.readLine())!=null){
                        jsonIn.append(line);
                    }
                    break;
            }
        }catch (Exception ex){
            Log.d(TAG,ex.toString());
        }finally {
            connection.disconnect();
            return jsonIn.toString();
        }
    }
}
