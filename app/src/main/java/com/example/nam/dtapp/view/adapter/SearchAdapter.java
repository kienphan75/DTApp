package com.example.nam.dtapp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nam.dtapp.R;

import java.util.ArrayList;

/**
 * Created by Phan Trung Kien on 2/19/2017.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.Holder_Search>  {
    Context context;
    ArrayList<String> list;

    public SearchAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Holder_Search onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search, parent, false);
        return new Holder_Search(view);
    }

    @Override
    public void onBindViewHolder(Holder_Search holder, final int position) {
        holder.textView.setText(list.get(position));


    }

    @Override
    public int getItemCount() {
        return list.size() ;
    }

    public class Holder_Search extends RecyclerView.ViewHolder{

        TextView textView;

        public Holder_Search(View itemView) {
            super(itemView);
            textView= (TextView) itemView.findViewById(R.id.tv_item_search);
        }
    }
}
