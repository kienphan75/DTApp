package com.example.nam.dtapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 * Created by Phan Trung Kien on 12/25/2016.
 */

public class Status implements Parcelable{
    private String name,avatr,ct_status,time;
    private ArrayList<String> listImg;

    public Status() {
    }

    public Status(String name, String avatr, String ct_status, String time,ArrayList<String> listImg) {
        this.name = name;
        this.avatr = avatr;
        this.ct_status = ct_status;
        this.time = time;
        this.listImg = listImg;
    }


    protected Status(Parcel in) {
        name = in.readString();
        avatr = in.readString();
        ct_status = in.readString();
        time = in.readString();
        listImg = in.createStringArrayList();
    }

    public static final Creator<Status> CREATOR = new Creator<Status>() {
        @Override
        public Status createFromParcel(Parcel in) {
            return new Status(in);
        }

        @Override
        public Status[] newArray(int size) {
            return new Status[size];
        }
    };

    public String getCt_status() {
        return ct_status;
    }

    public void setCt_status(String ct_status) {
        this.ct_status = ct_status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatr() {
        return avatr;
    }

    public void setAvatr(String avatr) {
        this.avatr = avatr;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ArrayList<String> getListImg() {
        return listImg;
    }

    public void setListImg(ArrayList<String> listImg) {
        this.listImg = listImg;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(name);
        dest.writeString(avatr);
        dest.writeString(ct_status);
        dest.writeString(time);
        dest.writeStringList(listImg);
    }

}
