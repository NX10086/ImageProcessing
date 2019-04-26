package com.example.nx.magicandyoung;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IntentFilter filter = new IntentFilter();
        filter.addAction("finish");
        registerReceiver(mFinishiReceiver,filter);

    }
    private BroadcastReceiver mFinishiReceiver = new BroadcastReceiver() {//利用广播机制关闭程序
        @Override
        public void onReceive(Context context, Intent intent) {
            if("finish".equals(intent.getAction())){
                context.unregisterReceiver(this);
                finish();
            }
        }
    };

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示")
                .setNegativeButton("取消",null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getApplicationContext().sendBroadcast(new Intent("finish"));
                    }
                })
                .setMessage("确定要退出程序吗？")
                .show();
    }
}
