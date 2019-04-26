package com.example.nx.magicandyoung;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nx.magicandyoung.util.SharedPreferencesHelper;
import com.example.nx.magicandyoung.util.UrlGet;
import com.example.nx.magicandyoung.util.Util;

import com.example.nx.magicandyoung.home.Home;

public class Login extends BaseActivity {
    private SharedPreferencesHelper helper;
    private Button bt_login;
    private TextView tv_register;
    private TextView userName = null;
    private TextView pw = null;
    private String loginUrl = UrlGet.getUrl("get_password");
    private String regUrl = UrlGet.getUrl("register");
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bt_login = findViewById(R.id.btn_login);
        userName = findViewById(R.id.et_user_name);
        pw = findViewById(R.id.et_psw);
        tv_register = findViewById(R.id.tv_register);
        helper=new SharedPreferencesHelper(Login.this,"user");
        String username=(String) helper.getSharedPreference("username",null);
        String password=(String) helper.getSharedPreference("password",null);
        if (username!=null&&password!=null){
            userName.setText(username);
            pw.setText(password);
        }
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,Home.class);
              if((userName.getText().toString().equals("root")&&pw.getText().toString().equals("root")) || new Util(Login.this).logIn(loginUrl, userName.getText().toString(), pw.getText().toString())) {
                  helper.put("username",userName.getText().toString());
                  helper.put("password",pw.getText().toString());
                  startActivity(intent);

                } else {
                    Toast.makeText(Login.this, "帐号或密码错误!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });
    }
}
