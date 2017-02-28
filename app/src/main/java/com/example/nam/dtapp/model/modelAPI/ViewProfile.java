package com.example.nam.dtapp.model.modelAPI;

/**
 * Created by Hai on 1/19/2017.
 */

public class ViewProfile {
    private String appKey;
    private int forTest,userId;

    public ViewProfile(String appKey, int forTest,int userId){
        this.appKey =appKey;
        this.forTest =forTest;
        this.userId =userId;
    }
}
