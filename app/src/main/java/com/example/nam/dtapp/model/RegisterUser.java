package com.example.nam.dtapp.model;

import java.util.ArrayList;

/**
 * Created by Hai on 1/23/2017.
 */

public class RegisterUser {
    private String appKey, Email,Password,Fullname,Workplace,Phone,Avatar;
    private int forTest ,MainFieldId , ObjectType;
    private ArrayList<Integer> ConcernFieldsId;
    public RegisterUser(){}

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getWorkplace() {
        return Workplace;
    }

    public void setWorkplace(String workplace) {
        Workplace = workplace;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public int getForTest() {
        return forTest;
    }

    public void setForTest(int forTest) {
        this.forTest = forTest;
    }

    public int getMainFieldId() {
        return MainFieldId;
    }

    public void setMainFieldId(int mainFieldId) {
        MainFieldId = mainFieldId;
    }

    public int getObjectType() {
        return ObjectType;
    }

    public void setObjectType(int objectType) {
        ObjectType = objectType;
    }

    public ArrayList<Integer> getConcernFieldsId() {
        return ConcernFieldsId;
    }

    public void setConcernFieldsId(ArrayList<Integer> concernFieldsId) {
        ConcernFieldsId = concernFieldsId;
    }
}
