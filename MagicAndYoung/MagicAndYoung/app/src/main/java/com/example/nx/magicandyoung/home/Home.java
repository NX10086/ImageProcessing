package com.example.nx.magicandyoung.home;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nx.magicandyoung.BaseActivity;
import com.example.nx.magicandyoung.Login;
import com.example.nx.magicandyoung.third.FragmentThree;
import com.example.nx.magicandyoung.second.FragmentTwo;
import com.example.nx.magicandyoung.R;
import com.example.nx.magicandyoung.first.FragmentOne;
import com.example.nx.magicandyoung.util.SharedPreferencesHelper;
import com.hjm.bottomtabbar.BottomTabBar;


public class Home extends BaseActivity {
    private MyBottomTabBar bottomTabBar;
    private DrawerLayout mDrawerLayout;
    private SharedPreferencesHelper helper;
    private float x1 = 0;
    private float x2 = 0;
    private int currentPage=0;
    private String []Titles={"首页","社区","商城"};



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        helper = new SharedPreferencesHelper(Home.this,"user");
        bottomTabBar = findViewById(R.id.bottom_tab_bar);


        bottomTabBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    //当手指按下的时候
                    x1 = motionEvent.getX();

                }
                else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    //当手指离开的时候
                    x2 = motionEvent.getX();
                    if (x1 - x2 > 30) {
                        if (currentPage != 2) {
                            bottomTabBar.setCurrentTab(currentPage + 1);
                        }
                    } else if (x2 - x1 > 30) {
                        if (currentPage != 0) {
                            bottomTabBar.setCurrentTab(currentPage - 1);

                        }
                    }
                }
            return true;
            }

        });
        initBottombar(bottomTabBar);
        mDrawerLayout =  findViewById(R.id.drawer_layout);


        NavigationView navView =  findViewById(R.id.nav_view);
        View headerview  = navView.getHeaderView(0);
        TextView textView=  headerview.findViewById(R.id.username);
        String s=(String)helper.getSharedPreference("username","");
       textView.setText(s);
        Resources resource=(Resources)getBaseContext().getResources();
        ColorStateList csl=(ColorStateList)resource.getColorStateList(R.color.navigation_menu_item_color);


        navView.setItemTextColor(csl);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setCheckable(true);
                item.setChecked(true);


                Intent intent = null;
                switch (item.getItemId()){

                    case R.id.nav_person:
                        intent = new Intent(Home.this, Geren.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_news:

                        break;
                    case R.id.nav_orders:
                        intent = new Intent(Home.this, MyOrder.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_friends:
                        break;
                    case R.id.nav_settings:
                        break;
                    case R.id.nav_suggestions:
                        intent = new Intent(Home.this, HelpCenter.class);
                        startActivity(intent);
                         break;
                    case R.id.nav_logout:
                        intent = new Intent(Home.this, Login.class);
                        startActivity(intent);
                        finish();
                        break;
                }
                Toast.makeText(Home.this,item.getTitle().toString(),Toast.LENGTH_SHORT).show();
                Log.i("Tag",item.getTitle().toString());
                return true;
            }
        });




    }



    private void initBottombar(MyBottomTabBar bottomTabBar){
        bottomTabBar.init(getSupportFragmentManager(),720,1280)
                .setImgSize(40,40)
                .setFontSize(12)
                .setTabPadding(4,6,10)
                .setChangeColor(Color.parseColor("#A4D3EE"),Color.parseColor("#c1c1c1"))
                .addTabItem("首页",R.mipmap.first, FragmentOne.class)
                .addTabItem("社区",R.mipmap.community, FragmentTwo.class)
                .addTabItem("商城",R.mipmap.market, FragmentThree.class)

                .isShowDivider(true)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name, View view) {
                        Log.i("TGA", "位置：" + position + "      选项卡的文字内容：" + name);


                    }
                })
                .setCurrentTab(0);
        bottomTabBar.setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
            @Override
            public void onTabChange(int position, String name, View view) {
                currentPage = position;
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}