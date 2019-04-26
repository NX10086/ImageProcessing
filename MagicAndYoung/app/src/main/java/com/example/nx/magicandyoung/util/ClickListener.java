package com.example.nx.magicandyoung.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//kind == 0 beautify, kind == 1 swap_face, kind == 2 compress, kind == 3 remove water.

public class ClickListener implements View.OnClickListener {
    private Activity mActivity = null;
    private String imageUri = null;
    private String url = null;
    private ImageView imageView = null;
    private String idolName = null;
    private String direction = null;
    private TextView textView = null;
    private int kind = 0;

    public ClickListener(Activity activity, String imageUri, String url, ImageView imageView, int kind, String idolName, String direction) {
        mActivity = activity;
        this.imageUri = imageUri;
        this.url = url;
        this.imageView = imageView;
        this.kind = kind;
        this.direction = direction;
        this.idolName = idolName;
    }

    public ClickListener(Activity activity, String imageUri, String url, ImageView imageView, TextView textView) {
        mActivity = activity;
        this.imageUri = imageUri;
        this.url = url;
        this.imageView = imageView;
        this.textView = textView;
        this.kind = 2;
    }

    @Override
    public void onClick(View view) {
        Util util = new Util(mActivity);
        Uri uri = Uri.parse(imageUri);
        String imagePath = new Uri2Path(mActivity).uri2path(uri,1);

        if(kind == 0) {
            String imageUrl = util.beautifyImage(url,imagePath);
            byte[] imageByteArray = com.example.nx.magicandyoung.Util.UrlToByteArray(UrlGet.getIp(), imageUrl);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);
            imageView.setImageBitmap(bitmap);
            Toast.makeText(mActivity,"美颜",Toast.LENGTH_SHORT).show();
        } else if(kind == 1) {
            String imageUrl = util.swapFace(url,imagePath, idolName);
            byte[] imageByteArray = com.example.nx.magicandyoung.Util.UrlToByteArray(UrlGet.getIp(), imageUrl);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);
            imageView.setImageBitmap(bitmap);
            Toast.makeText(mActivity,"换脸",Toast.LENGTH_SHORT).show();
        } else if(kind == 2) {
            String imageUrl = util.compressImage(url, imagePath);
            byte[] imageByteArray = com.example.nx.magicandyoung.Util.UrlToByteArray(UrlGet.getIp(), imageUrl);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);
            imageView.setImageBitmap(bitmap);
            textView.setText(String.format("%.2f", imageByteArray.length/1024.0) + "KB");
            imageView.setImageBitmap(bitmap);

            Toast.makeText(mActivity,"压缩",Toast.LENGTH_SHORT).show();

        } else if(kind == 3) {
            String imageUrl = util.removeWater(url,imagePath, direction);
            byte[] imageByteArray = com.example.nx.magicandyoung.Util.UrlToByteArray(UrlGet.getIp(), imageUrl);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);
            imageView.setImageBitmap(bitmap);
            Toast.makeText(mActivity,"去印",Toast.LENGTH_SHORT).show();

        }
    }
}
