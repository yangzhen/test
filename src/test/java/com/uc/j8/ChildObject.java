package com.uc.j8;

public class ChildObject extends UserObject{

    private String nickName;

    public ChildObject(String name, String idNo) {
        super(name, idNo);
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }



}
