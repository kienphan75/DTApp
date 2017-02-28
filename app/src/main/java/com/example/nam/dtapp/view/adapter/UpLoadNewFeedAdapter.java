package com.example.nam.dtapp.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nam.dtapp.R;
import com.example.nam.dtapp.model.ImageController;
import com.example.nam.dtapp.view.activity.home.HomeActivity;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Phan Trung Kien on 12/25/2016.
 * chứa các Adapter của các fragment trong activity UpLoad và Adapter của Fragment step3
 */

public class UpLoadNewFeedAdapter {


    public UpLoadNewFeedAdapter(FragmentManager manager, List<Fragment> listfragment) {
    }

    public static class Adapter extends FragmentPagerAdapter {
        List<Fragment> fragments;

        public Adapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }


        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    public static class Adapter_step1 extends RecyclerView.Adapter<Adapter_step1.Holder_step1> {
        ArrayList<String> list;
        Context c;
        ImageController mainController;

        public Adapter_step1(ArrayList<String> list, Context c,ImageController mainController) {
            this.list = list;
            this.c = c;
            this.mainController=mainController;
        }

        @Override
        public Holder_step1 onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(c).inflate(R.layout.item_load_image, parent, false);
            Holder_step1 holder_step1 = new Holder_step1(v);
            return holder_step1;
        }
        public void changePath(ArrayList<String> imagePaths) {
            this.list = imagePaths;
            mainController.setImgMain(imagePaths.get(0));

            notifyDataSetChanged();
        }

        @Override
        public void onBindViewHolder(final Holder_step1 holder, final int position) {
            final String imagePath = list.get(position);
            Picasso
                    .with(c)
                    .load(new File(imagePath))
                    .fit()
                    .centerCrop()
                    .into(holder.img_Image);

            holder.btn_remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(list.size()==1){
                        Snackbar.make(view, "Không thể xóa tiếp!", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                    else {
                        list.remove(position);
                        mainController.setImgMain(list.get(0));
                        notifyDataSetChanged();
                    }

                }
            });

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class Holder_step1 extends RecyclerView.ViewHolder {
            ImageView img_Image;
            Button btn_remove;

            public Holder_step1(View itemView) {
                super(itemView);
                img_Image= (ImageView) itemView.findViewById(R.id.img_Image);
                btn_remove= (Button) itemView.findViewById(R.id.btn_remove);
            }
        }

    }


}
