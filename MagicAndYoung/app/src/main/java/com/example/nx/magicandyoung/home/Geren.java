package com.example.nx.magicandyoung.home;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.nx.magicandyoung.R;
import com.leon.lib.settingview.LSettingItem;

public class Geren extends AppCompatActivity {
    private SimpleToolBar simpleToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geren);
        simpleToolBar = findViewById(R.id.simple_toolbar);
        simpleToolBar.setLeftTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        LSettingItem mSettingItemFive = findViewById(R.id.item_five);

        mSettingItemFive.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                Toast.makeText(getApplicationContext(), "已开启获取位置权限", Toast.LENGTH_SHORT).show();
            }
        });
        LSettingItem mSettingItemSix = findViewById(R.id.item_six);
        mSettingItemSix.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                Toast.makeText(getApplicationContext(), "已开启拍照权限", Toast.LENGTH_SHORT).show();
            }
        });
        LSettingItem mSettingItemSeven = findViewById(R.id.item_seven);
        mSettingItemSeven.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                Toast.makeText(getApplicationContext(), "已开启匿名发布评论", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
