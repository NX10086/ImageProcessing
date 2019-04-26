package com.example.nx.magicandyoung.second;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nx.magicandyoung.R;
import com.example.nx.magicandyoung.first.Compress;
import com.example.nx.magicandyoung.home.SimpleToolBar;
import com.hjm.bottomtabbar.BottomTabBar;

import java.util.ArrayList;
import java.util.List;


public class FragmentTwo extends Fragment {

    private List<ShequItem> fruitList = new ArrayList<ShequItem>();
    private SimpleToolBar simpleToolBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);
        final BottomTabBar bar= getActivity().findViewById(R.id.bottom_tab_bar);
        initFruits();
        final RecyclerView recyclerView = (RecyclerView)view. findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager layoutManager = new
                StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        /**
         * 优化rv
         */
        recyclerView.setItemAnimator(null);//取消默认动画
        recyclerView.setHasFixedSize(true);//设置等高item
        RecyclerView.RecycledViewPool mPool = recyclerView.getRecycledViewPool();
        mPool.setMaxRecycledViews(R.layout.shequ_item, 10);
        recyclerView.setItemViewCacheSize(10);//设置所需viewholder数量
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        //优化↑
        ShequAdapter adapter = new ShequAdapter(fruitList);
        recyclerView.setAdapter(adapter);
        recyclerView .addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        simpleToolBar = view.findViewById(R.id.simple_toolbar);
        simpleToolBar.setRightTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),SendMessage.class);
                startActivity(intent);
            }
        });
        return view;
    }
    private void initFruits() {
            int redu1 = 213;
            ShequItem apple = new ShequItem("来有魔有young享受大片质感", R.mipmap.fabu1, "tony喵在减肥",
                    R.mipmap.sq_touxiang1, "213", "0", "2793");
            fruitList.add(apple);
            ShequItem pear = new ShequItem("去掉水印的我腾哥格外“妖娆”~糟了是心肌梗塞的感觉ヽ(•ω• )ゝ",
                    R.mipmap.fabu5, "落地为安Connie",
                    R.mipmap.sq_touxiang5, "472", "0", "4761");
            fruitList.add(pear);
            ShequItem banana = new ShequItem("我在长岛，我有夏天，你在哪里~", R.mipmap.fabu2, "长岛经盛夏",
                    R.mipmap.sq_touxiang2, "109", "0", "3780");
           fruitList.add(banana);
            ShequItem grape = new ShequItem("有魔有young，我看行ヘ(￣ω￣ヘ)",
                    R.mipmap.fabu6, "阿睿瓜而不皮",
                    R.mipmap.sq_touxiang6, "389", "0", "3618");
           fruitList.add(grape);
            ShequItem orange = new ShequItem("和朋友一起走过的花路ヾ(o◕∀◕)ﾉ ", R.mipmap.fabu3, "NeverAncher",
                    R.mipmap.sq_touxiang3, "754", "0", "2075");
           fruitList.add(orange);
            ShequItem pineapple = new ShequItem("APP竟如此硬核。。。魔幻美颜！盘它！",
                    R.mipmap.fabu7, "王大锤",
                    R.mipmap.sq_touxiang7, "721", "0", "6521");
            fruitList.add(pineapple);
            ShequItem watermelon = new ShequItem("把流年印在心里，把阳光写在青春里……", R.mipmap.fabu4, "Dj番薯会发光",
                    R.mipmap.sq_touxiang4, "535", "0", "1982");
             fruitList.add(watermelon);


    }
}