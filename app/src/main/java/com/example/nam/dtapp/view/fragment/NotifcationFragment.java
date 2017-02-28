package com.example.nam.dtapp.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nam.dtapp.R;
import com.example.nam.dtapp.model.Notification;
import com.example.nam.dtapp.view.adapter.NotiRVAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 12/23/2016.
 */
public class NotifcationFragment extends Fragment {
    @BindView(R.id.rv_notification)
    RecyclerView mRecyclerView;
    @BindView(R.id.notication_swipe)
    SwipeRefreshLayout swipeRefreshLayout;
    private NotiRVAdapter adapter;
    private ArrayList<Notification> listNoti;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_notification,container,false);
        ButterKnife.bind(this,v);
        listNoti = new ArrayList<>();
        listNoti.add(new Notification("Message1 Phạm Ngọc Hải đã cmt", R.drawable.photo, R.drawable.ic_heart,17158670));
        listNoti.add(new Notification("Message2 Phạm Ngọc Hải đã cmt", R.drawable.photo, R.drawable.ic_heart,171571235));
        listNoti.add(new Notification("Message3 Phạm Ngọc Hải đã cmt", R.drawable.photo, R.drawable.ic_heart,171582380));
        listNoti.add(new Notification("Message4 Phạm Ngọc Hải đã cmt", R.drawable.photo, R.drawable.ic_heart,1715812680));
        listNoti.add(new Notification("Message5 Phạm Ngọc Hải đã cmt", R.drawable.photo, R.drawable.ic_heart,1715868023));
        listNoti.add(new Notification("Message6 Phạm Ngọc Hải đã cmt", R.drawable.photo, R.drawable.ic_heart,1715868032));
        listNoti.add(new Notification("Message7 Phạm Ngọc Hải đã cmt", R.drawable.photo, R.drawable.ic_heart,1715812680));
        adapter = new NotiRVAdapter(getActivity().getApplicationContext(),listNoti);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(dx == 0)
                    swipeRefreshLayout.setEnabled(true);
                else
                    swipeRefreshLayout.setEnabled(false);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                Handler h = new Handler();
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);

                    }
                },3000);
            }
        });
        return v;
    }
    public static NotifcationFragment getInstance(){
        NotifcationFragment f = new NotifcationFragment();
        Bundle b = new Bundle();
        // set something
        f.setArguments(b);
        return f;
    }
}
