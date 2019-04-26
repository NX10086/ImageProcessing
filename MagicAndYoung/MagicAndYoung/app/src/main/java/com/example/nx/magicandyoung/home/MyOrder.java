package com.example.nx.magicandyoung.home;

import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.Toast;

import com.example.nx.magicandyoung.R;

public class MyOrder extends Activity {
    private SimpleToolBar simpleToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        simpleToolBar = findViewById(R.id.simple_toolbar);
        simpleToolBar.setLeftTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        // 获取该Activity里面的TabHost组件
        // 方法1使用：
        // TabHost tabHost = getTabHost();
        // 方法1使用：
        // LayoutInflater.from(this).inflate(R.layout.main_tab, tabHost.getTabContentView(), true);
        // 方法2、3使用
        TabHost tabHost = findViewById(R.id.tabhost);
        tabHost.setup();
        // 方法3使用，动态载入xml，不需要Activity
        LayoutInflater.from(this).inflate(R.layout.myorder1, tabHost.getTabContentView());
        LayoutInflater.from(this).inflate(R.layout.myorder2, tabHost.getTabContentView());
        LayoutInflater.from(this).inflate(R.layout.myorder3, tabHost.getTabContentView());
        LayoutInflater.from(this).inflate(R.layout.myorder4, tabHost.getTabContentView());
        // 创建第一个Tab页
        /*TabHost.TabSpec tab1 = tabHost.newTabSpec("tab1")
                .setIndicator("标签页一") // 设置标题
                .setContent(R.id.tab01); //设置内容
        // 添加第一个标签页
        tabHost.addTab(tab1);
        TabHost.TabSpec tab2 = tabHost.newTabSpec("tab2")
                .setIndicator("标签页二")
                .setContent(R.id.tab02);
        // 添加第二个标签页
        tabHost.addTab(tab2);
        TabHost.TabSpec tab3 = tabHost.newTabSpec("tab3")
                .setIndicator("标签页三")
                .setContent(R.id.tab03);
        // 添加第三个标签页
        tabHost.addTab(tab3);*/

        /* 以上创建和添加标签页也可以用如下代码实现 */
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("全部").setContent(R.id.myorder01));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("待付款").setContent(R.id.myorder02));
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("待收货").setContent(R.id.myorder03));
        tabHost.addTab(tabHost.newTabSpec("tab4").setIndicator("待评价").setContent(R.id.myorder04));
        //标签切换事件处理，setOnTabChangedListener
        tabHost.setOnTabChangedListener(new OnTabChangeListener(){
            @Override
            // tabId是newTabSpec第一个参数设置的tab页名，并不是layout里面的标识符id
            public void onTabChanged(String tabId) {
                if (tabId.equals("tab1")) {   //第一个标签
                    Toast.makeText(MyOrder.this, "您的全部订单信息", Toast.LENGTH_SHORT).show();
                }
                if (tabId.equals("tab2")) {   //第二个标签
                    Toast.makeText(MyOrder.this, "您的待付款订单", Toast.LENGTH_SHORT).show();
                }
                if (tabId.equals("tab3")) {   //第三个标签
                    Toast.makeText(MyOrder.this, "您的待收货订单", Toast.LENGTH_SHORT).show();
                }
                if (tabId.equals("tab4")) {   //第三个标签
                    Toast.makeText(MyOrder.this, "您的待评价订单", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
