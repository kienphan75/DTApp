package com.example.nam.dtapp.service;

import com.example.nam.dtapp.model.Case;
import com.example.nam.dtapp.model.modelAPI.AddNewCase;
import com.example.nam.dtapp.model.modelAPI.GetCasesList;
import com.example.nam.dtapp.model.modelAPI.GetFriendList;
import com.example.nam.dtapp.model.modelAPI.GetUserNotification;
import com.example.nam.dtapp.model.modelAPI.Login;
import com.example.nam.dtapp.model.RegisterUser;
import com.example.nam.dtapp.model.modelAPI.UploadImage;
import com.example.nam.dtapp.model.modelAPI.ViewProfile;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by NAM on 12/23/2016.
 */

public interface API {
    public static String BASE_URL = "http://hippocampus.vn/api/hcapi/";
    @POST("Login")
    Call<String> login(@Body Login login);

    @POST("RegisterUser")
    Call<String> registerUser(@Body RegisterUser registerUser);

    @POST("GetCasesList")
    Call<String> getCasesList(@Body GetCasesList getCasesList);


    @POST("ViewProfile")
    Call<String> viewProfile(@Body ViewProfile viewProfile);

    @POST("GetUserNotification")
    Call<String> getUserNotification(@Body GetUserNotification getUserNotification);
    
    @POST("UploadImage")
    Call <String> uploadImage(@Body UploadImage uploadImage);

    @POST("AddNewCase")
    Call<String> addNewCase(@Body AddNewCase addNewCase);

    @POST("GetFriendList")
    Call<String> getFriendList(@Body GetFriendList getFriendList);
}
