package com.example.nx.magicandyoung.second;

import java.util.Random;

import static java.lang.Integer.parseInt;

public class ShequItem {

    private String name;

    private int imageId;

    private  String userName;

    private int touXiangId;

    private  String reDu;

    private  String pingLun;

    private  String dianZhan;

    public ShequItem(String name, int imageId,String userName,int touXiangId,String reDu,String pingLun,String dianZhan) {
        this.name = name;
        this.imageId = imageId;
        this.userName=userName;
        this.touXiangId=touXiangId;
        this.reDu=reDu;
        this.pingLun=pingLun;
        this.dianZhan=dianZhan;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setTouXiangId(int touXiangId) {
        this.touXiangId = touXiangId;
    }

    public void setReDu(String reDu) {
        this.reDu = reDu;
    }

    public void setPingLun(String pingLun) {
        this.pingLun = pingLun;
    }

    public void setDianZhan(String dianZhan) {
        this.dianZhan = dianZhan;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public String getUserName() {
        return userName;
    }

    public int getTouXiangId() {
        return touXiangId;
    }

    public String getReDu() {
        return reDu;
    }

    public String getPingLun() {
        return pingLun;
    }

    public String getDianZhan() {
        return dianZhan;
    }


    public void setZhanInc(){
        setDianZhan(Integer.parseInt(getDianZhan())+1+"");
    }
    public void setZhanDec(){
        setDianZhan(Integer.parseInt(getDianZhan())-1+"");
    }
    public void setHotInc(){
        Random random = new Random();
        int num=random.nextInt(11);
        setReDu(Integer.parseInt(getReDu())+num+"");
    }
    public void setReviewInc(){
        setPingLun(Integer.parseInt(getPingLun())+1+"");
    }
}

