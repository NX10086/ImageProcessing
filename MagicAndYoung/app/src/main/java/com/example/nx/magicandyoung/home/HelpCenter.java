package com.example.nx.magicandyoung.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.nx.magicandyoung.R;

public class HelpCenter extends AppCompatActivity {
    private SimpleToolBar simpleToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_center);
        simpleToolBar = findViewById(R.id.simple_toolbar);
        simpleToolBar.setLeftTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
