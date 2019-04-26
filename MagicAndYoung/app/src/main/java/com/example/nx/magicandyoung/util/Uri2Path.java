package com.example.nx.magicandyoung.util;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;

import static android.support.constraint.Constraints.TAG;


public class Uri2Path {
    private Activity mActivity = null;

    public Uri2Path(Activity activity) {
        mActivity = activity;
    }
    public  String uri2path(Uri uri, int kind) {
        String imagePath = "";
        if (DocumentsContract.isDocumentUri(mActivity, uri)) {
            // 如果是document类型的Uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1]; // 解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection,kind);
                Log.d(TAG, "uri2path111111: ");
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null,kind);
                Log.d(TAG, "uri2path222222: ");
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            Log.d(TAG, "uri2path3333333: ");
            // 如果是content类型的Uri，则使用普通方式处理
            imagePath = getImagePath(uri, null,kind);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            Log.d(TAG, "uri2path444444: ");
            // 如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.getPath();
        }

        return imagePath;

    }


    private  String getImagePath(Uri uri, String selection,int kind) {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = mActivity.getContentResolver().query(uri, null, selection, null, null);
        while (cursor.moveToNext()){
            Log.i("TAG1111",cursor.toString());
        }
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                Log.i("TAG",cursor.getColumnCount()+","+MediaStore.Images.Media.DATA);
                if (Build.VERSION.SDK_INT < 24) {
                    path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                } else {
                    if (kind == 0){
                        path = cursor.getString(0);//照相
                    }else if (kind==1){
                        path = cursor.getString(1);//选择照片
                    }

                    Log.d(TAG, "getImagePath: " +path);
                }
               //将  cursor.getColumnIndex(MediaStore.Images.Media.DATA)改为0成功解决
            }
            cursor.close();
        }
        return path;
    }


}
