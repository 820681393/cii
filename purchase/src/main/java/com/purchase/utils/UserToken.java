package com.purchase.utils;

import java.io.Serializable;

/**
 * Created by Owner on 2018/9/27.
 */
public class UserToken implements Serializable {
    private int id;
    private String userName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
