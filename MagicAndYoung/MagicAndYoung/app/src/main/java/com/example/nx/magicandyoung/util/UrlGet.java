package com.example.nx.magicandyoung.util;

public class UrlGet {
    private static String ip = "192.168.18.6";

    public static String getUrl(String func) {
        return "http://" + ip + ":8000/" + func + "/";
    }

    public static String getIp() {
        return ip;
    }

    public static String getProIp() {
        return "http://" + ip;
    }
}
