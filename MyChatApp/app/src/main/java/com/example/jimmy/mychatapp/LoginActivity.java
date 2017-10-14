package com.example.jimmy.mychatapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jimmy.mychatapp.user.getUserByEmailTask;
import com.example.jimmy.mychatapp.user.user_main;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class LoginActivity extends AppCompatActivity implements DialogInterface.OnClickListener{

    private TextView tv_newRegister;
    private EditText et_email,et_pass;

    //碰觸EditText以外地方收起鍵盤
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(ev.getAction() == MotionEvent.ACTION_DOWN){
            View v = getCurrentFocus();
            if(isShouldHideInput(v,ev)){
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                if(imm != null){
                    imm.hideSoftInputFromWindow(v.getWindowToken(),0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        if(getWindow().superDispatchTouchEvent(ev)){
            return true ;
        }
        return onTouchEvent(ev);
    }

    private boolean isShouldHideInput(View v, MotionEvent ev) {
        if(v != null && (v instanceof EditText)){
            int[] leftTop = {0,0};

            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if(ev.getX() > left && ev.getX() < right && ev.getY() > top && ev.getY() < bottom){
                return false;
            }else return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        getView();

        tv_newRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });
    }

    private void getView() {
        tv_newRegister = (TextView)findViewById(R.id.tv_new_register);
        et_email = (EditText)findViewById(R.id.et_email);
        et_pass = (EditText)findViewById(R.id.et_password);
    }

    public void Login(View view) {
        String getResult = null;
        String path = et_email.getText().toString();
        String url = user_main.url;
        try{
            getResult = new getUserByEmailTask().execute(url,path).get();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        if(getResult != null){
            String pw_login = et_pass.getText().toString();
            JsonObject jobj = new Gson().fromJson(getResult,JsonObject.class);
            String pw_confirm = jobj.get("userPw").getAsString();

            if(pw_login.equals(pw_confirm)){
                new AlertDialog.Builder(this)
                        .setMessage("登入成功")
                        .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            }
                        })
                        .show();
            }else{
                new AlertDialog.Builder(this)
                        .setMessage("密碼錯誤，請重新輸入密碼")
                        .setPositiveButton("確定",this)
                        .show();
                et_pass.setText("");
            }
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }
}
