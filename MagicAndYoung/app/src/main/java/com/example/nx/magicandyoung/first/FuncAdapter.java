package com.example.nx.magicandyoung.first;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.nx.magicandyoung.LoadingDialog;
import com.example.nx.magicandyoung.R;
import com.example.nx.magicandyoung.util.ImageGet;
import com.example.nx.magicandyoung.util.Uri2Path;
import com.example.nx.magicandyoung.util.UrlGet;
import com.example.nx.magicandyoung.util.Util;


import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.support.constraint.Constraints.TAG;

public class FuncAdapter extends RecyclerView.Adapter<FuncAdapter.ViewHolder> {
    private List<Func> mFuncList;
    private Uri uri;
    private String imagePath;
    private Util util;
    private Handler handler;
    private LoadingDialog dialog;
    private Handler handler_dialog;
    private ImageView imageView;
    private Activity myactivity;
    private static String Beautify="beautify_solve";
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


    public FuncAdapter(List<Func> funcList, Uri uri, Activity activity, Handler handler, Handler handler_dialog, LoadingDialog dialog) {
        mFuncList = funcList;
        this.util = new Util(activity);
        this.imageView  = activity.findViewById(R.id.image_show);
        this.myactivity=activity;
        this.uri=uri;
        imagePath= new Uri2Path(activity).uri2path(uri,0);
        if(Build.VERSION.SDK_INT > 24) {
            imagePath=activity.getExternalCacheDir()+"/" + imagePath;
        }

        Log.d(TAG, "FuncAdapter: "+imagePath);
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
                boolean isBeautify=false;
                switch (s){
                    case "一键美颜":
                        isBeautify=true;
                        break;
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
                if (!isBeautify){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            String image = new Util(myactivity).swapFace(UrlGet.getUrl(FaceSwap), imagePath, idol);
                            new ImageGet(handler, UrlGet.getIp()+image).imageGet();
                            handler_dialog.sendEmptyMessage(0);
                        }
                    }).start();

                } else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            String image = new Util(myactivity).beautifyImage(UrlGet.getUrl(Beautify), imagePath);
                            Log.d(TAG, "run: " + image);
                            new ImageGet(handler, UrlGet.getIp()+image).imageGet();
                            handler_dialog.sendEmptyMessage(0);
                        }
                    }).start();

                }




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
