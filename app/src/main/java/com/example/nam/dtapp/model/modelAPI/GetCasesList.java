package com.example.nam.dtapp.model.modelAPI;

/**
 * Created by Hai on 1/21/2017.
 */

public class GetCasesList {
    private String appKey;
    private int forTest,userId,ListType,Page,RowPerPage;

    public GetCasesList(String appKey, int forTest, int userId, int listType, int page, int rowPerPage) {
        this.appKey = appKey;
        this.forTest = forTest;
        this.userId = userId;
        ListType = listType;
        Page = page;
        RowPerPage = rowPerPage;
    }

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

    public int getListType() {
        return ListType;
    }

    public void setListType(int listType) {
        ListType = listType;
    }

    public int getPage() {
        return Page;
    }

    public void setPage(int page) {
        Page = page;
    }

    public int getRowPerPage() {
        return RowPerPage;
    }

    public void setRowPerPage(int rowPerPage) {
        RowPerPage = rowPerPage;
    }
}
