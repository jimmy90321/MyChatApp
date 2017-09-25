package com.example.jimmy.mychatapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ScrollView;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private ScrollView sv_login;
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            View view = LoginActivity.this.getWindow().peekDecorView();
            if(view != null && view.getWindowToken() != null){
                try{
                    ((InputMethodManager)LoginActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(LoginActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }catch (Exception ex){
                    Log.d("Auto-close keyboard","自動隱藏鍵盤異常");
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        sv_login = (ScrollView)findViewById(R.id.sv_login);
        sv_login.setOnClickListener(mOnClickListener);

        TextView newRegister = (TextView)findViewById(R.id.tv_new_register);
        newRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });


    }

}
