package com.example.nx.magicandyoung;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nx.magicandyoung.util.UrlGet;
import com.example.nx.magicandyoung.util.Util;

import org.w3c.dom.Text;

import java.util.Calendar;

public class Register extends AppCompatActivity{
    private TextView to_login;
    private String url = UrlGet.getUrl("register");
    private ImageView date_choose;
    private TextView userName;
    private TextView pw;
    private TextView pwa;
    private Button reg;
    private TextView birth_tv;
    private static final String TAG = "Register";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        to_login=findViewById(R.id.tv_back);
        userName = findViewById(R.id.et_user_name);
        pw = findViewById(R.id.et_psw);
        pwa = findViewById(R.id.et_psw_again);
        reg = findViewById(R.id.btn_register);
        date_choose=findViewById(R.id.consultation_iv_birthdate);
        birth_tv=findViewById(R.id.birthdate_tv);
        date_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(1);
            }
        });
        to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Register.this,Login.class);
                startActivity(intent);
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pwString = pw.getText().toString();
                String pwaString = pwa.getText().toString();
                if(pwaString.equals(pwString)) {
                    if(new Util(Register.this).register(url, userName.getText().toString(), pw.getText().toString())) {
                        Toast.makeText(Register.this, "注册成功!", Toast.LENGTH_SHORT).show();
                        Intent intent =new Intent(Register.this,Login.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Register.this, "用户名已存在!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Register.this, "两次密码不一致!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private DatePickerDialog.OnDateSetListener mdateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            int mYear = year;
            int mMonth = monthOfYear;
            int mDay = dayOfMonth;
            birth_tv.setText(new StringBuffer().append(mYear).append("年").append(mMonth + 1).append("月").append(mDay).append("日"));
        }
    };

    @Override
    protected Dialog onCreateDialog(int id) {
        final Calendar ca = Calendar.getInstance();
         int mYear = ca.get(Calendar.YEAR);//年
        int mMonth = ca.get(Calendar.MONTH);//月
        int mDay = ca.get(Calendar.DAY_OF_MONTH);//日
/**
 * 设置日期 绑定时间
 */
        switch (id) {
            case 1:
                return new DatePickerDialog(this,R.style.MyDatePickerDialogTheme, mdateListener, mYear, mMonth, mDay);

        }
        return null;
    }


}
