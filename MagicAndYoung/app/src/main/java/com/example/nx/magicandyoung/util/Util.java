package com.example.nx.magicandyoung.util;


import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Handler;


public class Util {
    private Net net;
    public Util(Activity activity) {
        net = new Net(activity);
    }
    public String beautifyImage(String url, String imagePath) {
      return net.sendImage(url, imagePath, "", "");
    }
    public String swapFace(String url, String imagePath, String idolName) {
        return net.sendImage(url, imagePath, "", idolName);
    }
    public String removeWater(String url, String imagePath, String dir) {
        return net.sendImage(url, imagePath, dir, "");
    }
    public String compressImage(String url, String imagePath) {
        return net.sendImage(url, imagePath, "", "");
    }
    public boolean register(String url, String userName, String password) {
        return net.SendMessage(url, userName, password);
    }
    public boolean logIn(String url, String userName, String password) {
        return net.SendMessage(url, userName, password);
    }
}
