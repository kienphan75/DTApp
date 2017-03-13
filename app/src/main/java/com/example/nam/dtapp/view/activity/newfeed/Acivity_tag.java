package com.example.nam.dtapp.view.activity.newfeed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nam.dtapp.R;
import com.volokh.danylo.hashtaghelper.HashTagHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Phan Trung Kien on 2/25/2017.
 */

public class Acivity_tag extends AppCompatActivity implements HashTagHelper.OnHashTagClickListener,View.OnClickListener {
    public static final String TAG="TAGTEMP";
    @BindView(R.id.edtemp) MultiAutoCompleteTextView edTag;
    @BindView(R.id.tvTemp)TextView tvTag;
    @BindView(R.id.btShow) Button show;
    private HashTagHelper mEditTextHashTagHelper;
    private HashTagHelper mTextViewHashTagHelper;
    String [] listFriend =new String []{"#Hải","#Kiên","#Ngọc"};
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag);
        ButterKnife.bind(this);
        edTag.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listFriend));
        edTag.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        edTag.setThreshold(2);
        Log.e(TAG,"TTTTT "+"Chay vao 1");


//        mEditTextHashTagHelper = HashTagHelper.Creator.create(getResources().getColor(R.color.colorPrimaryDark), null);
//        mEditTextHashTagHelper.handle(edTag);



        mTextViewHashTagHelper = HashTagHelper.Creator.create(getResources().getColor(R.color.colorPrimary), this, null);
        mTextViewHashTagHelper.handle(tvTag);
        Log.e(TAG,"TTTTT "+"Chay vao 2");


        show.setOnClickListener(this);
    }

    @Override
    public void onHashTagClicked(String hashTag) {
        Log.e(TAG,hashTag);
        if(contain(hashTag)){
            Toast.makeText(getApplicationContext(),hashTag,Toast.LENGTH_SHORT).show();
            Log.e(TAG,"TTTTT "+hashTag);
        }
        else {
            Toast.makeText(getApplicationContext(), "Sai dữ liệu", Toast.LENGTH_SHORT).show();
            Log.e(TAG,"TTTTT "+"Khong tagh");
        }
    }
    public boolean contain(String s){
        for(String t:listFriend) {
            Log.e(TAG,"ss: "+t+" "+"#" + s);
            if (t.trim().compareTo(("#" + s).trim()) == 0)
                return true;
        }
        return false;
    }
    private void Inra(){
        String s=edTag.getText().toString().trim();
        Log.e(TAG,"Chuoi ban dau la : "+s);
        String[] words=s.split("\\s");
        for(int i=0;i<words.length;i++){
            Log.e(TAG,"K************");
            Log.e(TAG,"choi la :"+words[i]);
            Log.e(TAG,"Do dai :"+words[i].length()+"");

            Log.e(TAG,"Trim :"+words[i].trim());
            Log.e(TAG,"Do dai sau Trim :"+words[i].trim().length());
            try {
                String s1=words[i].trim().substring(1,(words[i].length()-1));
                Log.e(TAG,"S :"+s1);
                Log.e(TAG,"Do dai sau cuoi :"+s1.length());
            }
            catch (Exception e){}


        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btShow:
                Inra();
                break;
        }
    }
}