package com.example.nx.magicandyoung;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;



public class InitialPage extends BaseActivity {
    private Button jump;

    private  int c= 5;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT>=21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);

        }
        setContentView(R.layout.initialpage);

        jump = (Button)findViewById(R.id.jump);
        jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.removeMessages(1);
                Intent intent =new Intent(InitialPage.this,Login.class);
                startActivity(intent);
                finish();
            }
        });
        Message message = handler.obtainMessage(1);
        handler.sendMessageDelayed(message,1000);
    }

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what){
                case 1:
                    if(c>0){
                        c--;
                        jump.setText("跳过"+"("+c+"s)");
                        Message message1 = handler.obtainMessage(1);
                        handler.sendMessageDelayed(message1,1000);
                    }else {
                        //直接跳转
                        Intent intent = new Intent(InitialPage.this,Login.class);
                        startActivity(intent);
                        finish();
                    }
                    break;
            }
            return false;
        }
    });


}
