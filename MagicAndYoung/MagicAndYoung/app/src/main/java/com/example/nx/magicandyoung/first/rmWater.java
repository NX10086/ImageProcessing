package com.example.nx.magicandyoung.first;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nx.magicandyoung.LoadingDialog;
import com.example.nx.magicandyoung.R;
import com.example.nx.magicandyoung.home.SimpleToolBar;
import com.example.nx.magicandyoung.util.ImageGet;
import com.example.nx.magicandyoung.util.Uri2Path;
import com.example.nx.magicandyoung.util.UrlGet;
import com.example.nx.magicandyoung.util.Util;

import java.io.FileNotFoundException;

public class rmWater extends Activity {
    private String url = UrlGet.getUrl("rw_solve");
    private CardView cardView;
    private ImageView imageView;
    private ImageView rb;
    private ImageView lt;
    private String imageUri;
    private SimpleToolBar simpleToolBar;
    private LoadingDialog dialog;
    private Bitmap Saveimage;
    private Handler handler_dialog = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            dialog.dismiss();
        }
    };

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    byte[] bytes = (byte[]) msg.obj;
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    imageView.setImageBitmap(bitmap);
                    Saveimage=bitmap;
                    break;
                case 0:
                    Toast.makeText(rmWater.this, "Server Error!", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rmwater);
        /**
         * 初始化imageview
         */
        cardView = findViewById(R.id.cardview);
        imageView = findViewById(R.id.image_show);
        rb=findViewById(R.id.rb);
        lt=findViewById(R.id.lt);

        Intent intent = getIntent();
        imageUri = intent.getStringExtra("imageUri");

        final Uri uri = Uri.parse(imageUri);
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(this.getContentResolver().openInputStream(uri));
            imageView.setImageBitmap(bitmap);
            Saveimage=bitmap;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new LoadingDialog(rmWater.this);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String imagePath = new Util(rmWater.this).removeWater(url, new Uri2Path(rmWater.this).uri2path(uri,1), "right_bottom");
                        new ImageGet(handler, UrlGet.getIp()+imagePath).imageGet();
                        handler_dialog.sendEmptyMessage(0);
                    }
                }).start();

            }
        });
        lt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new LoadingDialog(rmWater.this);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String imagePath = new Util(rmWater.this).removeWater(url, new Uri2Path(rmWater.this).uri2path(uri,1), "left_top");
                        new ImageGet(handler, UrlGet.getIp()+imagePath).imageGet();
                        handler_dialog.sendEmptyMessage(0);
                    }
                }).start();

            }
        });
        simpleToolBar = findViewById(R.id.simple_toolbar);

        simpleToolBar.setLeftTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        simpleToolBar.setRightTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.example.nx.magicandyoung.Util.saveImageToGallery(rmWater.this,Saveimage);
                Toast.makeText(rmWater.this,"保存成功",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
