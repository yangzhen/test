package com.uc.aop;

import java.io.Serializable;

/**
 * Created by yangzhen on 17/3/9.
 */
public class User implements Serializable {

    private int userId;

    private Integer number;

    private String name;

    private transient  String app;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", number=" + number +
                ", name='" + name + '\'' +
                ", app='" + app + '\'' +
                '}';
    }
}
