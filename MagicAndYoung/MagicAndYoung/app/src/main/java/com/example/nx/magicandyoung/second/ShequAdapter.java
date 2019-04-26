package com.example.nx.magicandyoung.second;


import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nx.magicandyoung.R;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShequAdapter extends RecyclerView.Adapter<ShequAdapter.ViewHolder> {

    private List<ShequItem> mFruitList;
    private Map<Integer,List<ReviewItem>> map=new HashMap<>();

    static class ViewHolder extends RecyclerView.ViewHolder {
        View fruitView;
        ImageView fruitImage;
        TextView fruitName;
        TextView userName;
        ImageView userTouXiang;
        TextView userReDu;
        TextView userPingLun;
        TextView userDianZan;
        LikeButton shou;
        ImageView review;
        LinearLayout linner;
        Button review_sent;
        EditText review_edit;
        MyListView listView;

        public ViewHolder(View view) {
            super(view);
            fruitView = view;
            fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
            fruitName = (TextView) view.findViewById(R.id.fruit_name);
            userName=(TextView) view.findViewById(R.id.user_name);
            userTouXiang = (ImageView) view.findViewById(R.id.user_touxiang);
            userReDu = (TextView) view.findViewById(R.id.user_rudu);
            userPingLun= view.findViewById(R.id.user_pinglun);
            userDianZan= (TextView) view.findViewById(R.id.user_dianzan);
            shou=(LikeButton) view.findViewById(R.id.q_shou);
            review = view.findViewById(R.id.review);
            linner=view.findViewById(R.id.shequ_liner);
            review_edit=view.findViewById(R.id.review_edit);
            review_sent=view.findViewById(R.id.review_sent);
            listView = view.findViewById(R.id.shequ_list);

        }
    }

    public ShequAdapter(List<ShequItem> fruitList) {
        mFruitList = fruitList;
        for(int i=0;i<fruitList.size();i++){
            map.put(i,new ArrayList<ReviewItem>());
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shequ_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.fruitView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                ShequItem fruit = mFruitList.get(position);
                Toast.makeText(v.getContext(), "you clicked view " + fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.fruitImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                ShequItem fruit = mFruitList.get(position);
                Toast.makeText(v.getContext(), "you clicked image " + fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.shou.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                ShequItem fruit = mFruitList.get(holder.getAdapterPosition());
                fruit.setZhanInc();
                fruit.setHotInc();
                notifyItemChanged(holder.getAdapterPosition());
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                ShequItem fruit = mFruitList.get(holder.getAdapterPosition());
                fruit.setZhanDec();

                notifyItemChanged(holder.getAdapterPosition());
            }
        });
        holder.review.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                holder.linner.setVisibility(View.VISIBLE);
                holder.review_edit.requestFocus();
            }
        });
        holder.review_sent.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

                ReviewItem item= new ReviewItem(holder.review_edit.getText().toString());
                holder.review_edit.setText("");

                holder.linner.setVisibility(View.GONE);
                List<ReviewItem> list= map.get(holder.getAdapterPosition());
                list.add(item);
                ShequItem fruit = mFruitList.get(holder.getAdapterPosition());
                fruit.setReviewInc();
                fruit.setHotInc();
                ReviewListAdapter adapter = new ReviewListAdapter(view.getContext(),list);
                holder.listView.setAdapter(adapter);

                notifyItemChanged(holder.getAdapterPosition());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ShequItem fruit = mFruitList.get(position);
        holder.fruitImage.setImageResource(fruit.getImageId());
        holder.fruitName.setText(fruit.getName());
        holder.userName.setText(fruit.getUserName());
        holder.userTouXiang.setImageResource(fruit.getTouXiangId());
        holder.userReDu.setText(fruit.getReDu());
        holder.userPingLun.setText(fruit.getPingLun());
        holder.userDianZan.setText(fruit.getDianZhan());
        //DividerItemDecoration recyclerView=recyclerView(Context context, int orientation);
        //recyclerView .addItemDecoration(new DividerItemDecoration(MainActivity.this,LinearLayoutManager.VERTICAL));
    }


    @Override
    public int getItemCount() {
        return mFruitList.size();
    }



}


