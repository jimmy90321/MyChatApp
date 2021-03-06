package com.example.jimmy.mychatapp.user;

import android.os.AsyncTask;
import android.util.Log;

import com.example.jimmy.mychatapp.common.User;
import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jimmy on 2017/10/1.
 */

public class createUserTask extends AsyncTask<Object,Integer,String>{

    private static final String TAG = "createUserTask";

    @Override
    protected String doInBackground(Object... params) {
        String url = user_main.url;
        User user = new User();
        user.setUserEmail(params[0].toString());
        user.setUserPw(params[1].toString());
        user.setUserName(params[2].toString());
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        Timestamp current = new Timestamp(now.getTime());
        user.setCreatedAt(current);
        String jsonOut = new Gson().toJson(user);
        String jsonIn = null;
        try{
            if(createUser(url,jsonOut)){
                jsonIn = params[0].toString();
            }
        }catch (Exception ex){
            Log.d(TAG,ex.toString());
        }
        return jsonIn;
    }

    private boolean createUser(String url, String jsonOut) {
        try{
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestProperty("Content-type", "Application/json");
            connection.setRequestProperty("Content-length",String.valueOf(jsonOut.length()));
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            bw.write(jsonOut);
            bw.flush();
            bw.close();
            int responseCode = connection.getResponseCode();
            Log.d(TAG,String.valueOf(responseCode).toString());
            if(responseCode == 204){
                return true;
            }
        }catch (Exception ex){
            Log.d(TAG,ex.toString());
        }
        return false;
    }


}
