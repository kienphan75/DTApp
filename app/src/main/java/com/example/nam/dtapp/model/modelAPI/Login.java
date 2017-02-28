package com.example.nam.dtapp.model.modelAPI;

/**
 * Created by Hai on 1/20/2017.
 */

public class Login {
    private String appKey, email,password;
    private int forTest;

    public Login(String appKey,int forTest,String email, String password) {
        this.appKey = appKey;
        this.email = email;
        this.password = password;
        this.forTest = forTest;
    }
}
