package com.example.nam.dtapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by NAM on 12/11/2016.
 */

public class Case implements Parcelable {
    private int fieldId,commentNumber,totalUserFollowing;
    private ArrayList<String> imageURLs;
    private String description,createDate,title,caseId;
    private ArrayList<User> usersFollowings,tagFriends;
    private ArrayList<StartComments> startComments;

    public Case() {
    }

    public Case(String caseId, int fieldId, String title, int commentNumber, ArrayList<StartComments> startComments,
                int totalUserFollowing, ArrayList<String> imageURLs,
                String description, String createDate,
                ArrayList<User> usersFollowings, ArrayList<User> tagFriends) {
        this.caseId = caseId;
        this.fieldId = fieldId;
        this.title = title;
        this.commentNumber = commentNumber;
        this.startComments = startComments;
        this.totalUserFollowing = totalUserFollowing;
        this.imageURLs = imageURLs;
        this.description = description;
        this.createDate = createDate;
        this.usersFollowings = usersFollowings;
        this.tagFriends = tagFriends;
    }


    protected Case(Parcel in) {
        caseId = in.readString();
        fieldId = in.readInt();
        commentNumber = in.readInt();
        totalUserFollowing = in.readInt();
        imageURLs = in.createStringArrayList();
        description = in.readString();
        createDate = in.readString();
        title = in.readString();
    }

    public static final Creator<Case> CREATOR = new Creator<Case>() {
        @Override
        public Case createFromParcel(Parcel in) {
            return new Case(in);
        }

        @Override
        public Case[] newArray(int size) {
            return new Case[size];
        }
    };

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
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

    public int getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(int commentNumber) {
        this.commentNumber = commentNumber;
    }

    public ArrayList<StartComments> getStartComments() {
        return startComments;
    }

    public void setStartComments(ArrayList<StartComments> startComments) {
        this.startComments = startComments;
    }

    public int getTotalUserFollowing() {
        return totalUserFollowing;
    }

    public void setTotalUserFollowing(int totalUserFollowing) {
        this.totalUserFollowing = totalUserFollowing;
    }

    public ArrayList<String> getImageURLs() {
        return imageURLs;
    }

    public void setImageURLs(ArrayList<String> imageURLs) {
        this.imageURLs = imageURLs;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public ArrayList<User> getUsersFollowings() {
        return usersFollowings;
    }

    public void setUsersFollowings(ArrayList<User> usersFollowings) {
        this.usersFollowings = usersFollowings;
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
        parcel.writeString(caseId);
        parcel.writeInt(fieldId);
        parcel.writeInt(commentNumber);
        parcel.writeInt(totalUserFollowing);
        parcel.writeStringList(imageURLs);
        parcel.writeString(description);
        parcel.writeString(createDate);
        parcel.writeString(title);
    }
}
