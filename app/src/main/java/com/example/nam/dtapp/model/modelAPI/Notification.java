package com.example.nam.dtapp.model.modelAPI;

/**
 * Created by Administrator on 12/23/2016.
 */
public class Notification {
    private String message;
    private int linkAvatar,linkIcon;
    private long time;

    public Notification(String message, int linkAvatar, int linkIcon, long time) {
        this.message = message;
        this.linkAvatar = linkAvatar;
        this.linkIcon = linkIcon;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public int getLinkAvatar() {
        return linkAvatar;
    }

    public int getLinkIcon() {
        return linkIcon;
    }

    public long getTime() {
        return time;
    }
}
