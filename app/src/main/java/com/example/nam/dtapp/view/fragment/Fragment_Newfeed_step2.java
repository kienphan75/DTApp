package com.example.nam.dtapp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nam.dtapp.R;
import com.example.nam.dtapp.model.User;
import com.example.nam.dtapp.view.activity.newfeed.UpLoadActivity;
import com.volokh.danylo.hashtaghelper.HashTagHelper;

import java.util.ArrayList;

/**
 * Created by Phan Trung Kien on 12/24/2016.
 */

public class Fragment_Newfeed_step2 extends Fragment implements HashTagHelper.OnHashTagClickListener {
    public static final String TAG="Fragment_Newfeed_step2";
    private EditText ed1;
    private MultiAutoCompleteTextView ed2;
    TextView tvTag;
    LinearLayout layout;
    String s1, s2, s3;
    public static boolean ok = false;
    private Spinner spinner;
    private ArrayList<Integer> listFrends;
    private HashTagHelper mEditTextHashTagHelper;
    private HashTagHelper mTextViewHashTagHelper;
    ArrayList<String> listName= new ArrayList<>();


    public ArrayList<Integer> getListFrends() {
        return listFrends;
    }

    public void setListFrends(ArrayList<Integer> listFrends) {
        this.listFrends = listFrends;
    }

    public Spinner getSpinner() {
        return spinner;
    }

    public void setSpinner(Spinner spinner) {
        this.spinner = spinner;
    }

    public EditText getEd1() {
        return ed1;
    }

    public void setEd1(EditText ed1) {
        this.ed1 = ed1;
    }

    public EditText getEd2() {
        return ed2;
    }

    public void setEd2(MultiAutoCompleteTextView ed2) {
        this.ed2 = ed2;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_newfeed_step2, container, false);
        listName =getArguments().getStringArrayList("listNameFriend");
        Log.e(TAG,"listName : "+listName.size());
        Log.e(TAG,"Name : "+listName.get(0));

        layout = (LinearLayout) v.findViewById(R.id.fragment_step2);
        ed1 = (EditText) v.findViewById(R.id.ed1);
        ed2 = (MultiAutoCompleteTextView) v.findViewById(R.id.ed2);
        spinner= (Spinner) v.findViewById(R.id.spinner_linhvucCLS);
        tvTag= (TextView) v.findViewById(R.id.tvTemp);

        ed2.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,listName));
        ed2.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        ed2.setThreshold(2);
        mEditTextHashTagHelper = HashTagHelper.Creator.create(getResources().getColor(R.color.colorPrimaryDark), null);
        mEditTextHashTagHelper.handle(ed2);

        mTextViewHashTagHelper = HashTagHelper.Creator.create(getResources().getColor(R.color.colorPrimary), this, null);
        mTextViewHashTagHelper.handle(tvTag);


        ed1.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 5){
                    UpLoadActivity.btnnext.setEnabled(true);
                }
            }
        });


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, getJob());
        spinner.setAdapter(dataAdapter);
        listFrends= new ArrayList<>();
        return v;
    }

    public ArrayList<String> getJob(){
        ArrayList<String> ds= new ArrayList<>();
        ds.add("Anatomy");
        ds.add("Anasthesiology");
        ds.add("Audiometry");
        ds.add("Cardiac Sciences");
        ds.add("Clinical Hematology");
        ds.add("Cosmetic Dentistry");
        ds.add("Critical Care & ICU");
        ds.add("Dermatology");
        ds.add("Emergency Medicine");
        return ds;
    }

    @Override
    public void onHashTagClicked(String hashTag) {
        Log.e(TAG,"click : "+hashTag);
        if(contain(hashTag)!=-1){
            listFrends.add(contain(hashTag));
            Log.e(TAG,"click index : "+contain(hashTag));
            Toast.makeText(getActivity().getApplicationContext(),"Tag người "+hashTag,Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getActivity().getApplicationContext(), "Sai dữ liệu", Toast.LENGTH_SHORT).show();
            Log.e(TAG,"click index 1: "+contain(hashTag));
        }
    }
    public int contain(String s){
        for(int i=0;i<listName.size();i++) {
            Log.e(TAG,"ss: "+listName.get(i)+" "+"#" + s);
            if (listName.get(i).trim().compareTo(("#" + s).trim()) == 0) {
                return i;
            }
        }
        return -1;
    }
}
