package com.example.jimmy.mychatapp.chatroom;

import android.os.AsyncTask;
import android.util.Log;

import com.example.jimmy.mychatapp.common.Message;
import com.example.jimmy.mychatapp.message.message_main;
import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jimmy on 2017/11/12.
 */

public class sendMessageTask extends AsyncTask<Object,Integer,String> {

    private static final String TAG = "sendMessageTask";

    @Override
    protected String doInBackground(Object... params) {
        String url = message_main.url;
        Message message = new Message();
        message.setMessage(params[0].toString());
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        Timestamp current = new Timestamp(now.getTime());
        message.setCreatedAt(current);
        String jsonOut = new Gson().toJson(message);
        String jsonIn = null;
        try{
            if(createMessage(url,jsonOut)){
                jsonIn = params[0].toString();
            }
        }catch (Exception ex){
            Log.d(TAG,ex.toString());
        }
        return jsonIn;
    }

    private boolean createMessage(String url, String jsonOut) {
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
