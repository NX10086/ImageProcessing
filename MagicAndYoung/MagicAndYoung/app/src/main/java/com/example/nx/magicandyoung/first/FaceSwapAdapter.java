package com.example.nx.magicandyoung.first;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nx.magicandyoung.LoadingDialog;
import com.example.nx.magicandyoung.R;
import com.example.nx.magicandyoung.util.ImageGet;
import com.example.nx.magicandyoung.util.Uri2Path;
import com.example.nx.magicandyoung.util.UrlGet;
import com.example.nx.magicandyoung.util.Util;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FaceSwapAdapter extends RecyclerView.Adapter<FaceSwapAdapter.ViewHolder> {
    private List<Func> mFuncList;
    private Uri uri;
    private String imagePath;
    private Util util;
    private ImageView imageView;
    private Activity myactivity;
    private Handler handler;
    private Handler handler_dialog;
    private LoadingDialog dialog;
    private static String FaceSwap="swap_face_solve";


    static class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView funcImage;
        TextView funcName;

        public ViewHolder(View view) {
            super(view);
            funcImage = view.findViewById(R.id.func_image);
            funcName = view.findViewById(R.id.func_name);
        }
    }


    public FaceSwapAdapter(List<Func> funcList, Uri url, Activity activity, Handler handler, Handler handler_dialog, LoadingDialog dialog) {
        mFuncList = funcList;
        this.util = new Util(activity);
        this.imageView  = activity.findViewById(R.id.image_show);
        this.myactivity=activity;
        this.uri=url;
        imagePath= new Uri2Path(myactivity).uri2path(this.uri,1);
        this.handler = handler;
        this.handler_dialog = handler_dialog;
        this.dialog = dialog;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.func_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.funcImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s= holder.funcName.getText().toString();
                String idoname="";
                switch (s){
                    case "王菲":
                        idoname="wangfei";
                        break;
                    case "胡歌":
                        idoname="huge";
                        break;
                    case "古力娜扎":
                        idoname="gulinazha";
                        break;
                    case "陈伟霆":
                        idoname="chenweiting";
                        break;
                    case "唐嫣":
                        idoname="tangyan";
                        break;
                    case "王思聪":
                        idoname="wangsicong";
                        break;
                }
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                final String idol = idoname;
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        String image = new Util(myactivity).swapFace(UrlGet.getUrl(FaceSwap), new Uri2Path(myactivity).uri2path(uri,1), idol);
                        new ImageGet(handler, UrlGet.getIp()+image).imageGet();
                        handler_dialog.sendEmptyMessage(0);
                    }
                }).start();


            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Func func = mFuncList.get(position);
        holder.funcImage.setImageResource(func.getImageId());
        holder.funcName.setText(func.getName());
    }

    @Override
    public int getItemCount() {
        return mFuncList.size();
    }

}
