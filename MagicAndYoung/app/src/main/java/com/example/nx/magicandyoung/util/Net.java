package com.example.nx.magicandyoung.util;



import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Net {
    private  Activity mActivity;

    public Net(Activity activity) {
        mActivity = activity;
    }

    /**
     *
     * @param url:Two url can be choosen, get_password or register.
     * @param userName
     * @param password
     * @return The flag of login or register.
     */

    public  boolean SendMessage(String url, String userName, String password) {

        boolean ret = false;
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build();
        final FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("username", userName);
        formBuilder.add("password", password);
        Request request = new Request.Builder().url(url).post(formBuilder.build()).build();

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        try {
            Response response = client.newCall(request).execute();
            if(response.isSuccessful() && response.body().string().equals("OK")) {
                ret = true;
                response.close();
            } else {
                response.close();
            }

        } catch (IOException e) {
            Toast.makeText(mActivity, "Server Error!", Toast.LENGTH_SHORT);
        } finally {

        }
        return ret;
    }

    /**
     *
     * @param url:Four url can be choose, beautify_solve, compress_solve, rw_solve, swap_face_solve
     * @param imagePath
     * @param dir:If the url is rw_slove, dir can be "left_top" or "right_bottom", else the dir is "".
     * @param dest:If the url is beautify_solve, dest can be the name of your idol, else the dir is "".
     * @return A Bitmap object if successful else null.
     */

    public String sendImage(String url, String imagePath, String dir, String dest) {
        String ret = null;
        File file = new File(imagePath);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
        RequestBody fileBody = RequestBody.create(MediaType.parse("image/jpg"), file);
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("image", "src.jpg", fileBody).addFormDataPart("dest", dest).addFormDataPart("dir", dir).build();
        Request request = new Request.Builder().url(url).post(requestBody).build();
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        try {
            Response response = client.newCall(request).execute();
            if(response.isSuccessful()) {
                ret = response.body().string();
                response.close();
            } else {
                response.close();
                return null;
            }
        } catch (IOException e) {
        }
        return ret;
    }

}
