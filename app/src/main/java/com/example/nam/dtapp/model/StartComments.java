package com.example.nam.dtapp.model;

/**
 * Created by Phan Trung Kien on 2/22/2017.
 */

public class StartComments {
    private int commentId,userId;
    private String content,imageUrl,createdTime;
    private boolean isDeleted;


    public StartComments() {
    }

    public StartComments(int commentId, int userId, String content, String imageUrl, String createdTime, boolean isDeleted) {
        this.commentId = commentId;
        this.userId = userId;
        this.content = content;
        this.imageUrl = imageUrl;
        this.createdTime = createdTime;
        this.isDeleted = isDeleted;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
