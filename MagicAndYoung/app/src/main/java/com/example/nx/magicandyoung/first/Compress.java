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
import android.widget.TextView;
import android.widget.Toast;

import com.example.nx.magicandyoung.LoadingDialog;
import com.example.nx.magicandyoung.R;
import com.example.nx.magicandyoung.home.SimpleToolBar;
import com.example.nx.magicandyoung.util.ImageGet;
import com.example.nx.magicandyoung.util.Uri2Path;
import com.example.nx.magicandyoung.util.UrlGet;
import com.example.nx.magicandyoung.util.Util;

import java.io.IOException;

public class Compress extends Activity {
    private String url = UrlGet.getUrl("compress_solve");
    private CardView cardView;
    private ImageView imageView;
    private ImageView compress;
    private TextView size;
    private String imageUri;
    private LoadingDialog dialog;
    private SimpleToolBar simpleToolBar;
    private Bitmap saveImage;
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
                    size.setText(String.format("%.2f", bytes.length/1024.0) + "KB");

                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    imageView.setImageBitmap(bitmap);
                    saveImage=bitmap;
                    Toast.makeText(Compress.this, "压缩", Toast.LENGTH_SHORT).show();
                    break;
                case 0:
                    Toast.makeText(Compress.this, "Server Error!", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compress);
        /**
         * 初始化imageview
         */
        cardView = findViewById(R.id.cardview);
        imageView = findViewById(R.id.image_show);
        compress = findViewById(R.id.compress);
        size=findViewById(R.id.size);
        Intent intent = getIntent();
        imageUri = intent.getStringExtra("imageUri");

        final Uri uri = Uri.parse(imageUri);
        try {
            size.setText(String.format("%.2f", this.getContentResolver().openInputStream(uri).available() / 1024.0)+ "KB");
            Bitmap bitmap = BitmapFactory.decodeStream(this.getContentResolver().openInputStream(uri));
            imageView.setImageBitmap(bitmap);
            saveImage=bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        compress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new LoadingDialog(Compress.this);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String imagePath = new Util(Compress.this).compressImage(url, new Uri2Path(Compress.this).uri2path(uri,1));
                        new ImageGet(handler, UrlGet.getIp()+imagePath).imageGet();
                        handler_dialog.sendEmptyMessage(0);
                    }
                }).start();

            }
        });
        //初始化end

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
                com.example.nx.magicandyoung.Util.saveImageToGallery(Compress.this,saveImage);
                Toast.makeText(Compress.this,"保存成功",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
