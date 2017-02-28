package com.example.nam.dtapp.model.modelAPI;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.nam.dtapp.model.User;

import java.util.ArrayList;

/**
 * Created by Phan Trung Kien on 2/23/2017.
 */

public class AddNewCase implements Parcelable{
    private int forTest,userId,fieldId;
    private String title,description,appKey;
    private ArrayList<String> imageUrls;
    private ArrayList<User> tagFriends;


    public AddNewCase(String appKey, int forTest, int userId, String title, ArrayList<String> imageUrls, ArrayList<User> tagFriends, int fieldId, String description) {
        this.appKey = appKey;
        this.forTest = forTest;
        this.userId = userId;
        this.fieldId = fieldId;
        this.title = title;
        this.description = description;
        this.imageUrls = imageUrls;
        this.tagFriends = tagFriends;
    }

    public AddNewCase() {
    }

    protected AddNewCase(Parcel in) {
        appKey = in.readString();
        forTest = in.readInt();
        userId = in.readInt();
        fieldId = in.readInt();
        title = in.readString();
        description = in.readString();
        imageUrls = in.createStringArrayList();
    }

    public static final Creator<AddNewCase> CREATOR = new Creator<AddNewCase>() {
        @Override
        public AddNewCase createFromParcel(Parcel in) {
            return new AddNewCase(in);
        }

        @Override
        public AddNewCase[] newArray(int size) {
            return new AddNewCase[size];
        }
    };

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

    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(ArrayList<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public ArrayList<User> getTagFriends() {
        return tagFriends;
    }

    public void setTagFriends(ArrayList<User> tagFriends) {
        this.tagFriends = tagFriends;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(appKey);
        parcel.writeInt(forTest);
        parcel.writeInt(userId);
        parcel.writeInt(fieldId);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeStringList(imageUrls);
    }
}
