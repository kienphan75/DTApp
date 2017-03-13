package com.example.nam.dtapp.model;

import java.util.ArrayList;

/**
 * Created by Phan Trung Kien on 3/1/2017.
 */

public class UserFriend {
    private String email;
    private String fullname;
    private String avatar;
    private int userId;

    public UserFriend(String email, String fullname, String avatar, int userId) {
        this.email = email;
        this.fullname = fullname;
        this.avatar = avatar;
        this.userId = userId;
    }

    public UserFriend() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
