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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.nx.magicandyoung.LoadingDialog;
import com.example.nx.magicandyoung.R;
import com.example.nx.magicandyoung.home.SimpleToolBar;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class FaceSwap extends Activity {
    private CardView cardView;
    private List<Func> funcList = new ArrayList<>();
    private ImageView imageView;
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
                    Toast.makeText(FaceSwap.this, "Server Error!", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faceswap);
        /**
         * 初始化imageview
         */
        cardView = findViewById(R.id.cardview);
        imageView = findViewById(R.id.image_show);
        Intent intent = getIntent();
        imageUri = intent.getStringExtra("imageUri");
        dialog = new LoadingDialog(FaceSwap.this);

        Uri uri = Uri.parse(imageUri);
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(this.getContentResolver().openInputStream(uri));
            imageView.setImageBitmap(bitmap);
            Saveimage=bitmap;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
                com.example.nx.magicandyoung.Util.saveImageToGallery(FaceSwap.this,Saveimage);
                Toast.makeText(FaceSwap.this,"保存成功",Toast.LENGTH_SHORT).show();
            }
        });
        initFunc();

        RecyclerView recyclerView = findViewById(R.id.faceswap_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayout.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        FaceSwapAdapter adapter = new FaceSwapAdapter(funcList,uri,this, handler, handler_dialog, dialog);
        recyclerView.setAdapter(adapter);
    }
    private void initFunc() {
        Func compress = new Func("王菲", R.mipmap.wangfei);
        Func beautify = new Func("胡歌", R.mipmap.huge);
        Func rmWater = new Func( "古力娜扎", R.mipmap.gulinazha);
        Func faceSwap = new Func("陈伟霆", R.mipmap.chenweiting);
        Func other1 = new Func("唐嫣", R.mipmap.tangyan);
        Func other2 = new Func("王思聪", R.mipmap.wangsicong);

        funcList.add(compress);
        funcList.add(beautify);
        funcList.add(rmWater);
        funcList.add(faceSwap);
        funcList.add(other1);
        funcList.add(other2);
    }
}
