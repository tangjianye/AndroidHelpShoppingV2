package com.product.helpshopping.module.net.response;

/**
 * Created by Administrator on 2015/12/23 0023.
 */
public class AccountItem {
    private int uid;
    private String user_name;
    private String avatar_file;

    public String getAvatar_file() {
        return avatar_file;
    }

    public void setAvatar_file(String avatar_file) {
        this.avatar_file = avatar_file;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    @Override
    public String toString() {
        return "AccountItem{" +
                "avatar_file='" + avatar_file + '\'' +
                ", uid=" + uid +
                ", user_name='" + user_name + '\'' +
                '}';
    }
}
