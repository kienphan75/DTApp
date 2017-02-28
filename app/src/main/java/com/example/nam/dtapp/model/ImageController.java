package com.example.nam.dtapp.model;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * Created by sangc on 2015-11-06.
 */
public class ImageController {
    Context context;
    ImageView imgMain;

    public ImageController(Context context, ImageView imgMain) {
        this.context = context;
        this.imgMain = imgMain;
    }

   public void setImgMain(String path) {
        Picasso
                .with(context)
                .load(new File(path))
                .fit()
                .centerCrop()
                .into(imgMain);
    }
}
