package com.example.nam.dtapp.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.nam.dtapp.R;

import java.util.ArrayList;

/**
 * Created by Phan Trung Kien on 12/24/2016.
 */

public class Fragment_Newfeed_step3 extends Fragment {

    private EditText ed;

    public EditText getEd() {
        return ed;
    }

    public void setEd(EditText ed) {
        this.ed = ed;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_newfeed_step3,container,false);
        ed= (EditText) v.findViewById(R.id.ed3);


        return v;
    }
}
