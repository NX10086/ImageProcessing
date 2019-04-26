package com.example.nx.magicandyoung.first;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nx.magicandyoung.R;

import java.util.List;

public class GridViewAdapter extends BaseAdapter {
    private static final int MeiYan=0;
    private static final int QUYING=1;
    private static final int YASUO=2;
    private static final int HUANLIAN=3;


    Context application_context;
    Fragment my_fragment;
    List<ButtonItem> list;
    public GridViewAdapter(Context app_context, Fragment fragment, List<ButtonItem> _list) {
        this.list = _list;
        this.application_context = app_context;
        this.my_fragment = fragment;
    }
    @Override

    public int getCount() {
        return list.size();
    }



    @Override

    public Object getItem(int i) {
        return list.get(i);
    }



    @Override

    public long getItemId(int i) {
        return i;
    }



    @Override

    public View getView(final int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder = null;

        if (view == null) {

            viewHolder = new ViewHolder();

            view = LayoutInflater.from(application_context).inflate(R.layout.button_item, null);

            viewHolder.ItemImage = (ImageView) view.findViewById(R.id.ItemImage);

            viewHolder.buttonTitle=(TextView)view.findViewById(R.id.buttonTitle);
            viewHolder.itemLayout=view.findViewById(R.id.itemlayout);


            view.setTag(viewHolder);

        } else {

            viewHolder = (ViewHolder) view.getTag();

        }

        viewHolder.ItemImage.setImageResource(list.get(i).getButtonImage());
        viewHolder.buttonTitle.setText(list.get(i).getButtonName());
        viewHolder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int action=-1;
               switch (i){
                   case MeiYan:
                       action=MeiYan;
                       break;
                   case QUYING:
                       action=QUYING;
                       break;
                   case YASUO:
                       action=YASUO;
                       break;
                   case HUANLIAN:
                       action=HUANLIAN;
                       break;
               }
               if (action!=-1){
                   openAlbum(action);
               }

            }
        });
        return view;

    }









    class ViewHolder {
        ImageView ItemImage;
        TextView buttonTitle;
        LinearLayout itemLayout;



    }
    //打开相册
    private void openAlbum(int action) {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
         my_fragment.startActivityForResult(intent,action); // 打开相册
    }
}
