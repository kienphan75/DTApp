package com.example.nam.dtapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Phan Trung Kien on 12/16/2016.
 */

public class Comment implements Parcelable {
    private String name;
    private String avatar;
    private String time,content;

    public Comment(String name, String avatar, String time, String content) {
        this.name = name;
        this.avatar = avatar;
        this.time = time;
        this.content = content;
    }

    protected Comment(Parcel in) {
        name = in.readString();
        avatar = in.readString();
        time = in.readString();
        content = in.readString();
    }

    public static final Creator<Comment> CREATOR = new Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel in) {
            return new Comment(in);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(avatar);
        parcel.writeString(time);
        parcel.writeString(content);
    }
}
