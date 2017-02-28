package com.example.nam.dtapp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nam.dtapp.R;
import com.example.nam.dtapp.config.Config;
import com.example.nam.dtapp.model.Notification;
import com.example.nam.dtapp.util.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 12/23/2016.
 */

public class NotiRVAdapter extends RecyclerView.Adapter<NotiRVAdapter.NotiHolder> {
    private ArrayList<Notification> list;
    private Context mContext;
    public NotiRVAdapter(Context c, ArrayList<Notification> list){
        mContext = c;
        this.list = list;

    }

    @Override
    public NotiHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_notification,parent,false);
        NotiHolder notiHolder = new NotiHolder(v);
        notiHolder.avatar.getLayoutParams().height= Math.min(Config.widthScreen,Config.heightScreen)/6;
        notiHolder.avatar.getLayoutParams().width=Math.min(Config.widthScreen,Config.heightScreen)/6;
        return notiHolder;
    }

    @Override
    public void onBindViewHolder(NotiHolder holder, int position) {
        Log.e("test",list.get(0).getLinkAvatar()+"");
        holder.avatar.setImageResource(list.get(position).getLinkAvatar());
        holder.icon.setImageResource(list.get(position).getLinkIcon());
        holder.message.setText(list.get(position).getMessage());
        String s="";
        long t = list.get(position).getTime();

        holder.time.setText(Utils.getDistanceTime(t));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class NotiHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.notication_avatar) ImageView avatar;
        @BindView(R.id.notication_message) TextView message;
        @BindView(R.id.notication_icon) ImageView icon;
        @BindView(R.id.notication_time) TextView time;
        public NotiHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
