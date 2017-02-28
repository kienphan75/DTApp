package com.example.nam.dtapp.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * Created by NAM on 12/23/2016.
 */

public class Utils {
    /**
     *  Tinh thoi gian cho toi thoi diem ht
     * @param  time
     * @return khoang cach
     */
    public static String getDistanceTime(long time){
        String s="";
        long m = System.currentTimeMillis();
        if(m-time<60)
            s="Vừa xong";
        else{
            if(m-time<3600)
                s= String.valueOf((int)((m-time)/60))+" phút trước ";
            else{
                if(m-time<86400)
                    s= String.valueOf((int)((m-time)/3600))+" giờ trước ";
                else
                    s= String.valueOf((int)((m-time)/86400))+" ngày trước ";
            }
        }
        return s;
    }

    /**
     * Doi bitmap sang String
     * @param bitmap
     * @return String
     */
    public static String bitmapToString(Bitmap bitmap){
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,bao);
        byte [] b= bao.toByteArray();
        String convert = Base64.encodeToString(b,Base64.DEFAULT);
        return convert;
    }

    /**
     * doi String sang bitmap
     * @param string
     * @return Bitmap
     */
    public static Bitmap stringToBitmap(String string){
        byte[] decodeString = Base64.decode(string,Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decodeString,0,decodeString.length);
        return bitmap;
    }
    public static Bitmap scaleDown(Bitmap realImage, float maxImageSize,
                                   boolean filter) {
        float ratio = Math.min(
                (float) maxImageSize / realImage.getWidth(),
                (float) maxImageSize / realImage.getHeight());
        if(ratio>=1)
            return realImage;
        int width = Math.round((float) ratio * realImage.getWidth());
        int height = Math.round((float) ratio * realImage.getHeight());

        Bitmap newBitmap = Bitmap.createScaledBitmap(realImage, width,
                height, filter);
        return newBitmap;
    }
    public static String convertJson(String s){
        s = s.replace("\n","").replace("\r","").replace("\\\"","\"").replace("\\r\\n", "").trim();
        return s.replace("\"{","{").replace("}\"","}");
        // /return s.substring(1,s.length()-1);
    }

    public static String bitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }
}