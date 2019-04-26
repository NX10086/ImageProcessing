package com.example.nx.magicandyoung.util;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ImageGet {
    private OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(120,TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build();
    private Handler handler;
    private String imageUrl;
    public ImageGet(Handler handler, String imageUrl) {
        this.handler = handler;
        this.imageUrl = "http://" + imageUrl;
    }

    public void imageGet() {
        final Request request = new Request.Builder().get()
                .url(imageUrl)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message message = handler.obtainMessage();
                if(response.isSuccessful()) {
                    message.what = 1;
                    message.obj = response.body().bytes();
                } else {
                    message.what = 0;
                }
                handler.sendMessage(message);

            }
        });
    }
}
