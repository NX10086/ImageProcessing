package com.example.nx.magicandyoung.first;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;

import android.util.Log;
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
import java.security.KeyStore;

public class Beautify extends Activity {

    private String url = UrlGet.getUrl("beautify_solve");
    private LoadingDialog dialog;
    private SimpleToolBar simpleToolBar;
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
                    Toast.makeText(Beautify.this, "美颜", Toast.LENGTH_SHORT).show();
                    break;
                case 0:
                    Toast.makeText(Beautify.this, "Server Error!", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };


    private ImageView imageView;
    private ImageView beautify;
    private String imageUri;
    private static final String TAG = "Beautify";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beautify);
        /**
         * 初始化imageview
         */

        imageView = findViewById(R.id.image_show);
        Intent intent = getIntent();
        imageUri = intent.getStringExtra("imageUri");

        final Uri uri = Uri.parse(imageUri);
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(this.getContentResolver().openInputStream(uri));
            imageView.setImageBitmap(bitmap);
            Saveimage =bitmap;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        beautify = findViewById(R.id.beautify);
        beautify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new LoadingDialog(Beautify.this);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String imagePath = new Util(Beautify.this).beautifyImage(url, new Uri2Path(Beautify.this).uri2path(uri,1));
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
                com.example.nx.magicandyoung.Util.saveImageToGallery(Beautify.this,Saveimage);
                Toast.makeText(Beautify.this,"保存成功",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
