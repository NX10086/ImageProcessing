package com.example.nx.magicandyoung.second;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.nx.magicandyoung.R;

import java.util.ArrayList;
import java.util.List;

public class ReviewListAdapter extends BaseAdapter  {
    private Context mContext;
    private List<ReviewItem> mList = new ArrayList<>();



    public ReviewListAdapter(Context context, List<ReviewItem> list) {

        mContext = context;
        mList = list;

    }



    @Override

    public int getCount() {
        return mList.size();
    }



    @Override

    public Object getItem(int i) {
        return mList.get(i);
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

            view = LayoutInflater.from(mContext).inflate(R.layout.shequ_list_item, null);

           viewHolder.String=view.findViewById(R.id.list_item_text);





            view.setTag(viewHolder);

        } else {

            viewHolder = (ViewHolder) view.getTag();

        }

        viewHolder.String.setText(mList.get(i).getString());





        return view;

    }



    /**

     * 删除按钮的监听接口

     */

    public interface onItemDeleteListener {

        void onDeleteClick(int i);

    }



    private onItemDeleteListener mOnItemDeleteListener;



    public void setOnItemDeleteClickListener(onItemDeleteListener mOnItemDeleteListener) {

        this.mOnItemDeleteListener = mOnItemDeleteListener;

    }



    class ViewHolder {
        TextView String;




    }
}
