package com.example.nx.magicandyoung.first;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class TakePhoto extends AppCompatActivity {

    private List<Func> funcList = new ArrayList<>();
    private SimpleToolBar simpleToolBar;
    private ImageView imageView;
    private String imageUri;
    private Bitmap saveImage;
    private LoadingDialog dialog;

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
                    saveImage=bitmap;
                    break;
                case 0:
                    Toast.makeText(TakePhoto.this, "Server Error!", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.takephoto);

        dialog = new LoadingDialog(TakePhoto.this);
        simpleToolBar = findViewById(R.id.simple_toolbar);


        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.hide();
        }

        initFunc();

        RecyclerView recyclerView = findViewById(R.id.takephoto_recycler_view);
        /**
         * 初始化imageview
         */
        imageView = findViewById(R.id.image_show);
        Intent intent = getIntent();
        imageUri = intent.getStringExtra("imageUri");
        Uri uri = Uri.parse(imageUri);
        Log.d("TAG1", "imageUri "+imageUri+"    uri:"+uri);
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(this.getContentResolver().openInputStream(uri));
            imageView.setImageBitmap(bitmap);
            saveImage=bitmap;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //初始化end
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayout.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        FuncAdapter adapter = new FuncAdapter(funcList,uri,this, handler, handler_dialog, dialog);
        recyclerView.setAdapter(adapter);
        simpleToolBar.setLeftTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        simpleToolBar.setRightTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.example.nx.magicandyoung.Util.saveImageToGallery(TakePhoto.this,saveImage);
                Toast.makeText(TakePhoto.this,"保存成功",Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void initFunc() {
        Func compress = new Func("一键美颜", R.mipmap.xiangji_meiyan);
        Func beautify = new Func("王菲", R.mipmap.wangfei);
        Func rmWater = new Func("胡歌", R.mipmap.huge);
        Func faceSwap = new Func("古力娜扎", R.mipmap.gulinazha);
        Func other1 = new Func("陈伟霆", R.mipmap.chenweiting);
        Func other2 = new Func("唐嫣", R.mipmap.tangyan);
        Func other3 = new Func("王思聪", R.mipmap.wangsicong);
        funcList.add(compress);
        funcList.add(beautify);
        funcList.add(rmWater);
        funcList.add(faceSwap);
        funcList.add(other1);
        funcList.add(other2);
        funcList.add(other3);
    }

}
