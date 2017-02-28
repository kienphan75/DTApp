package com.example.nam.dtapp.view.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nam.dtapp.R;
import com.example.nam.dtapp.model.ImageController;
import com.example.nam.dtapp.view.activity.newfeed.UpLoadActivity;
import com.example.nam.dtapp.view.adapter.HomeRVAdapter;
import com.example.nam.dtapp.view.adapter.UpLoadNewFeedAdapter;
import com.sangcomz.fishbun.FishBun;
import com.sangcomz.fishbun.define.Define;

import java.util.ArrayList;

/**
 * Created by Phan Trung Kien on 12/24/2016.
 */

public class Fragment_Newfeed_step1 extends Fragment {
    public static boolean ok=false;
    TextView btn_addImage;

    private ArrayList<String> path = new ArrayList<>();
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
//    HomeRVAdapter.ImageAdapter mainAdapter;
    UpLoadNewFeedAdapter.Adapter_step1 adapter_step1;
    ImageController mainController;
    private ImageView imgMain;


    public ImageView getImgMain() {
        return imgMain;
    }

    public void setImgMain(ImageView imgMain) {
        this.imgMain = imgMain;
    }

    public ArrayList<String> getPath() {
        return path;
    }

    public void setPath(ArrayList<String> path) {
        this.path = path;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_newfeed_step1,container,false);
        btn_addImage= (TextView) v.findViewById(R.id.btn_addImage);
        recyclerView= (RecyclerView) v.findViewById(R.id.step1_recyclerview);
        imgMain = (ImageView) v.findViewById(R.id.img_main);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mainController = new ImageController(getActivity(), imgMain);

        adapter_step1= new UpLoadNewFeedAdapter.Adapter_step1(path,getActivity(),mainController);
//        mainAdapter = new HomeRVAdapter.ImageAdapter(getActivity(), mainController, path);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter_step1);

        btn_addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FishBun.with(Fragment_Newfeed_step1.this)
                        .setPickerCount(20)
                        .setPickerSpanCount(3)
                        .setActionBarColor(Color.parseColor("#26ae90"), Color.parseColor("#303F9F"))
                        .textOnImagesSelectionLimitReached("Limit Reached!")
                        .textOnNothingSelected("Nothing Selected")
                        .setArrayPaths(path)
                        .setAlbumSpanCount(2, 4)
                        .setButtonInAlbumActivity(true)
                        .setCamera(true)
                        .setReachLimitAutomaticClose(true)
                        .startAlbum();
            }
        });
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Define.ALBUM_REQUEST_CODE:
                if (resultCode == getActivity().RESULT_OK) {
                    Log.d("KKKK","da lay .....");
                    path = data.getStringArrayListExtra(Define.INTENT_PATH);
                    adapter_step1.changePath(path);
                    ok=true;
                    UpLoadActivity.btnnext.setEnabled(true);

                    break;
                }
        }
    }

}
