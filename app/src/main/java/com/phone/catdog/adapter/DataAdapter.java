package com.phone.catdog.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.phone.catdog.R;


import java.util.ArrayList;



public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> implements View.OnClickListener {

    public ArrayList<Data> outDataList;
    Context context;

    ClickListener clickListener;

    public DataAdapter(Context context, ArrayList<Data> outDataList) {
        this.context = context;
        this.outDataList = outDataList;
    }

    @Override
    public DataAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(DataAdapter.MyViewHolder holder, int position) {

        Data data = outDataList.get(position);
        MultiTransformation option = new MultiTransformation(new CenterCrop(), new RoundedCorners(8));


        if(data.getPath()!=null && !data.getPath().equals("")){
            Glide.with(context).load(data.getPath())
                    .apply(RequestOptions.bitmapTransform(option))
                    .into(holder.iv_photo);
        }

        //내용
        holder.tv_text.setText(data.getContent()+"\n"+data.getPrice());
        //URL
        holder.tv_url.setText(data.getUrl());
        if(data.isFav) {
            holder.iv_fav.setBackgroundResource(R.drawable.ic_star_on);
            data.isFav = true;
        }else{
            holder.iv_fav.setBackgroundResource(R.drawable.ic_star_off);
            data.isFav = false;
        }
        holder.iv_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.isFav = !data.isFav;
                notifyDataSetChanged();
            }
        });

        //즐겨찾기 횟수
        holder.btn_move.setTag(position);
        holder.btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(data.url));
                context.startActivity(browserIntent);
            }
        });


        holder.myLayout.setTag(position);
        holder.myLayout.setOnClickListener(this);


    }
    @Override
    public void onClick(View view) {
        //아이템 클릭시 세부정보 확인
        int position  = (int) view.getTag();
        if(clickListener!=null){
            clickListener.setOnClick(position);
        }
    }

    public interface ClickListener {
        void setOnClick(int positon);
    }


    @Override
    public int getItemCount() {
        return outDataList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        //이미지
        public ImageView iv_photo;
        //내용
        public TextView tv_text;
        //즐겨찾기상태
        public ImageView iv_fav;
        //쇼핑몰주소
        public TextView tv_url;
        //이동버튼
        public Button btn_move;


        LinearLayout myLayout;

        Context context;

        public MyViewHolder(View itemView) {
            super(itemView);
            myLayout = (LinearLayout) itemView;

            //myLayout.setBackgroundColor(Color.RED);


            iv_photo = itemView.findViewById(R.id.iv_photo);
            tv_text = itemView.findViewById(R.id.tv_text);
            iv_fav = itemView.findViewById(R.id.iv_fav);
            tv_url = itemView.findViewById(R.id.tv_url);
            btn_move = itemView.findViewById(R.id.btn_move);




            context = itemView.getContext();

        }


    }
}