package com.example.nam.dtapp.model.modelAPI;

/**
 * Created by Hai on 2/21/2017.
 */

public class UploadImage {
    private String appKey, Extention,  ImageContent;
    private int forTest;

    public UploadImage(String appKey, int forTest, String extention, String imageContent) {
        this.appKey = appKey;
        this.forTest = forTest;
        Extention = extention;
        ImageContent = imageContent;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public void setExtention(String extention) {
        Extention = extention;
    }

    public void setImageContent(String imageContent) {
        ImageContent = imageContent;
    }

    public void setForTest(int forTest) {
        this.forTest = forTest;
    }
}
