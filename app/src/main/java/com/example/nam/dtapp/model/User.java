package com.example.nam.dtapp.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by NAM on 12/11/2016.
 */

public class User implements Serializable{
    private int userId;
    private String email;
    private String password;
    private String fullname;
    private String workplace;
    private String phone;
    private int mainFieldId;
    private ArrayList<Integer> concernFieldId;
    private String avatar;
    private int objectType;

    public User(int mainFieldId, String fullname, String email) {
        this.mainFieldId = mainFieldId;
        this.fullname = fullname;
        this.email = email;
    }

    public User(int userId, String email, String fullname, String avatar) {
        this.userId = userId;
        this.email = email;
        this.fullname = fullname;
        this.avatar = avatar;
    }

    public User(int userId, String email, String password, String fullname, String workplace, String phone, int mainFieldId, ArrayList<Integer> concernFieldId, String avatar, int objectType) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.workplace = workplace;
        this.phone = phone;
        this.mainFieldId = mainFieldId;
        this.concernFieldId = concernFieldId;
        this.avatar = avatar;
        this.objectType = objectType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getMainFieldId() {
        return mainFieldId;
    }

    public void setMainFieldId(int mainFieldId) {
        this.mainFieldId = mainFieldId;
    }

    public ArrayList<Integer> getConcernFieldId() {
        return concernFieldId;
    }

    public void setConcernFieldId(ArrayList<Integer> concernFieldId) {
        this.concernFieldId = concernFieldId;
    }
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getObjectType() {
        return objectType;
    }

    public void setObjectType(int objectType) {
        this.objectType = objectType;
    }


}
