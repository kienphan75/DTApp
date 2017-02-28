package com.example.nam.dtapp.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nam.dtapp.R;

import com.example.nam.dtapp.view.adapter.HomeRVAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by NAM on 12/24/2016.
 */

public class ProfileFragment extends Fragment {
    public static final String TAG_NAME = "ProfileFragment";


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this,v);
        return v;
    }

    public static ProfileFragment getInstance(){
        ProfileFragment f = new ProfileFragment();
        Bundle b = new Bundle();
        // set something
        f.setArguments(b);
        return f;
    }
}
