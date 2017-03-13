package com.example.nam.dtapp.model.modelAPI;

/**
 * Created by Phan Trung Kien on 3/1/2017.
 */

public class GetFriendList {
    private String appKey;
    private int forTest;
    private int userId;

    public GetFriendList(String appKey, int forTest, int userId) {
        this.appKey = appKey;
        this.forTest = forTest;
        this.userId = userId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public int getForTest() {
        return forTest;
    }

    public void setForTest(int forTest) {
        this.forTest = forTest;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
