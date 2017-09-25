package com.example.jimmy.mychatapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private EditText et_email,et_pass,et_passcofirm,et_name;
    private TextView tv_readyToLogin,tv_emailhint,tv_passwordhint,tv_confirmpwhint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        getView();

        tv_readyToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });

        et_email.addTextChangedListener(emailWatcher);
        et_pass.addTextChangedListener(passwordWatcher);
        et_passcofirm.addTextChangedListener(passwordconfirmWatcher);
    }

    private void getView() {
        et_email = (EditText)findViewById(R.id.et_reg_email);
        et_pass = (EditText)findViewById(R.id.et_reg_password);
        et_passcofirm = (EditText)findViewById(R.id.et_reg_confirmpw);
        et_name = (EditText)findViewById(R.id.et_reg_name);
        tv_readyToLogin = (TextView)findViewById(R.id.tv_ready_login);
        tv_emailhint = (TextView)findViewById(R.id.tv_hint_email);
        tv_emailhint.setVisibility(View.GONE);
        tv_passwordhint = (TextView)findViewById(R.id.tv_hint_password);
        tv_passwordhint.setVisibility(View.GONE);
        tv_confirmpwhint = (TextView)findViewById(R.id.tv_hint_confirmpassword);
        tv_confirmpwhint.setVisibility(View.GONE);
    }

    private final TextWatcher emailWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            tv_emailhint.setText("");
            tv_emailhint.setVisibility(View.VISIBLE);
        }

        @Override
        public void afterTextChanged(Editable s) {
            String email_regex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
            Pattern pattern = Pattern.compile(email_regex);
            Matcher matcher = pattern.matcher(s.toString());
            if(s.length() == 0){
                tv_emailhint.setText("請輸入email");
            }else if(!matcher.matches()){
                tv_emailhint.setText("email格式錯誤");
            }else{
                tv_emailhint.setVisibility(View.GONE);
            }
        }
    };

    private final TextWatcher passwordWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            tv_passwordhint.setText("");
            tv_passwordhint.setVisibility(View.VISIBLE);
        }

        @Override
        public void afterTextChanged(Editable s) {
            if(s.length()<4 || s.length()>12){
                tv_passwordhint.setText("密碼長度不符");
            }else{
                tv_passwordhint.setVisibility(View.GONE);
            }
        }
    };

    private final TextWatcher passwordconfirmWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            tv_confirmpwhint.setText("");
            tv_confirmpwhint.setVisibility(View.VISIBLE);
        }

        @Override
        public void afterTextChanged(Editable s) {
            if(!s.toString().equals(et_pass.getText().toString())){
                tv_confirmpwhint.setText("密碼錯誤，請重新確認密碼");
            }else{
                tv_confirmpwhint.setVisibility(View.GONE);
            }
        }
    };
}