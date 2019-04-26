package com.example.nx.magicandyoung.home;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.hjm.bottomtabbar.BottomTabBar;

public class MyBottomTabBar extends BottomTabBar {
    private static final String TAG = "MyBottomTab";
    private int current_tab = 0;

    private int mLastXIntercept;
    private int mLastYIntercept;
    public MyBottomTabBar(Context context) {
        super(context);
    }

    public MyBottomTabBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public BottomTabBar setCurrentTab(int index) {
        current_tab=index;
        return super.setCurrentTab(index);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        boolean intercepted = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        final int action = ev.getAction() & MotionEvent.ACTION_MASK;
        Log.i(TAG,"intercepted = "+current_tab);
        if (current_tab!=0){
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    intercepted = false;
                    //调用ViewPager的onInterceptTouchEvent方法初始化mActivePointerId
                    super.onInterceptTouchEvent(ev);
                    break;
                case MotionEvent.ACTION_MOVE:
                    //横坐标位移增量
                    int deltaX = x - mLastXIntercept;
                    //纵坐标位移增量
                    int deltaY = y - mLastYIntercept;
                    if ((Math.abs(deltaX)>Math.abs(deltaY))){
                        intercepted = true;
                    }else{
                        intercepted = false;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    intercepted = false;
                    break;
                default:
                    break;
            }

            mLastXIntercept = x;
            mLastYIntercept = y;

            Log.i(TAG,"intercepted = "+intercepted);
            return intercepted;
        }else {
                super.onInterceptTouchEvent(ev);
           return false;
        }

    }


}
