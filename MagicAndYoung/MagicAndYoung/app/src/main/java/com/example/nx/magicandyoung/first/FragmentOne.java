package com.example.nx.magicandyoung.first;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.nx.magicandyoung.R;
import com.example.nx.magicandyoung.Util;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;


public class FragmentOne extends Fragment implements OnBannerListener{


    private Banner banner;
    private ArrayList<String> list_path;
    private ArrayList<String> list_title;
    private FloatingActionButton f_button;
    List<ButtonItem> ButtonList;
    RelativeLayout itmel;
    int colorcount=0;
    LinearLayout linearLayout ;
    MyGridView gridView;
    public static final int TAKE_PHOTO = 20;

    private static final int MeiYan=0;
    private static final int QUYING=1;
    private static final int YASUO=2;
    private static final int HUANLIAN=3;
    private static final String TAG = "FragmentOne";

    private ImageView picture;

    private Uri imageUri;






    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment1,container,false);

        banner=view.findViewById(R.id.banner);
        linearLayout = view.findViewById(R.id.firstbg);
        f_button = view.findViewById(R.id.takephoto);
        f_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getContext(), "" + getContext().getExternalCacheDir(),Toast.LENGTH_SHORT).show();
                // 创建File对象，用于存储拍照后的图片
                Log.e(TAG, "onClick: " + getContext().getExternalCacheDir());
//                Toast.makeText(FragmentOne.this, "")
                File outputImage = new File(getContext().getExternalCacheDir(), "output_image.jpg");
                try {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT < 24) {
                    imageUri = Uri.fromFile(outputImage);

                } else {

                    imageUri = FileProvider.getUriForFile(getContext(), "com.example.nx.magicandyoung.fileprovider", outputImage);
                    Log.d(TAG, "onClick: "+imageUri+"");

                }
                // 启动相机程序
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO);
            }
        });
        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:linearLayout.setBackgroundResource(R.mipmap.bg11);
                        break;
                    case 1:linearLayout.setBackgroundResource(R.mipmap.bg2);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });


        gridView = (MyGridView) view. findViewById(R.id.grid);

        setData();
        setGridView();

        initView();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap= null;
        Intent intent;
        switch (requestCode){
            case TAKE_PHOTO:
                if (resultCode==RESULT_OK){

                    try {
                         intent  = new Intent(getContext(),TakePhoto.class);
                        intent.putExtra("imageUri",imageUri.toString());
                        bitmap = BitmapFactory.decodeStream(getContext().getContentResolver().openInputStream(imageUri));
                        Util.saveImageToGallery(getContext(),bitmap);
                        startActivity(intent);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case MeiYan:

                if (resultCode==RESULT_OK){

                    intent  = new Intent(getContext(),Beautify.class);
                    Uri uri = data.getData();
                    Log.d(TAG, "onActivityResult: " + uri.toString());
                    intent.putExtra("imageUri",uri.toString());
                    startActivity(intent);

                }
                break;
            case QUYING:

                if (resultCode==RESULT_OK){

                    intent  = new Intent(getContext(),rmWater.class);

                    Uri uri = data.getData();
                    intent.putExtra("imageUri",uri.toString());
                    startActivity(intent);

                }
                break;
            case YASUO:

                if (resultCode==RESULT_OK){

                    intent  = new Intent(getContext(),Compress.class);

                    Uri uri = data.getData();
                    intent.putExtra("imageUri",uri.toString());
                    startActivity(intent);

                }
                break;
            case HUANLIAN:

                if (resultCode==RESULT_OK){

                    intent  = new Intent(getContext(),FaceSwap.class);

                    Uri uri = data.getData();

                    intent.putExtra("imageUri",uri.toString());
                    startActivity(intent);

                }
                break;
        }
    }




    // 初始化轮播图
    private void initView() {

        //放图片地址的集合
        list_path = new ArrayList<>();
        //放标题的集合
        list_title = new ArrayList<>();

        list_path.add(Util.imageTranslateUri(getContext(),R.mipmap.baner1));
        list_path.add(Util.imageTranslateUri(getContext(),R.mipmap.baner2));

        list_title.add("");
        list_title.add("");

        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new FragmentOne.MyLoader());
        //设置图片网址或地址的集合
        banner.setImages(list_path);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        banner.setBannerAnimation(Transformer.Default);
        //设置轮播图的标题集合
        banner.setBannerTitles(list_title);
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true);
        //设置指示器的位置，小点点，左中右。
        banner.setIndicatorGravity(BannerConfig.CENTER)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(this)
                //必须最后调用的方法，启动轮播图。
                .start();


    }
    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(getContext(),position+"",Toast.LENGTH_SHORT).show();
    }

    //自定义的图片加载器
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).into(imageView);
        }
    }

    /**
     * 设置按钮数据
     */
    private void setData() {
        ButtonList = new ArrayList<ButtonItem>();
        ButtonItem item = new ButtonItem();
        item.setButtonName("魔幻美颜");
        item.setButtonImage(R.mipmap.meiyan);
        ButtonList.add(item);
        item = new ButtonItem();
        item.setButtonName("魔法去印");
        item.setButtonImage(R.mipmap.shuiying);
        ButtonList.add(item);
        item = new ButtonItem();
        item.setButtonName("神奇压缩");
        item.setButtonImage(R.mipmap.yasuo);
        ButtonList.add(item);
        item = new ButtonItem();
        item.setButtonName("魔力换脸");
        item.setButtonImage(R.mipmap.huanlian);
        ButtonList.add(item);
        item = new ButtonItem();
        item.setButtonName("");
        item.setButtonImage(R.mipmap.left_border);
        ButtonList.add(item);
        item = new ButtonItem();
        item.setButtonName("");
        item.setButtonImage(R.mipmap.left_border);
        ButtonList.add(item);

    }

    /**设置GirdView参数，绑定数据*/
    private void setGridView() {
        int size = ButtonList.size();
        int length = 100;
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        int gridviewWidth = (int) (size * (length + 4) * density);
        int itemWidth = (int) (length * density);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                gridviewWidth, LinearLayout.LayoutParams.FILL_PARENT);
        gridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        gridView.setColumnWidth(itemWidth); // 设置列表项宽
        gridView.setHorizontalSpacing(5); // 设置列表项水平间距
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setNumColumns(size); // 设置列数量=列表集合数

        GridViewAdapter adapter = new GridViewAdapter(getActivity().getApplicationContext(),FragmentOne.this,
                ButtonList);
        gridView.setAdapter(adapter);
    }



}
