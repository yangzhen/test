package com.uc.j8;

import java.util.Objects;

public class UserObject {

    private String name;

    private String idNo;

    public UserObject(String name, String idNo) {
        this.name = name;
        this.idNo = idNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserObject that = (UserObject) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(idNo, that.idNo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, idNo);
    }

    public static void main(String[] args) {
        UserObject user1 = new UserObject("yangxinyan", "411327199002102938");
        UserObject user2 = new UserObject("yangxinyan", "411327199002102938");

        Boolean flag1 = user1.equals(user2);

        Boolean flag2 = user2.equals(user1);

        System.out.println(flag1 + "," + flag2);

        ChildObject child1 = new ChildObject("yangxinyan", "411327199002102938");

        ChildObject child2 = new ChildObject("yangxinyan", "411327199002102938");

        Boolean flag3 = user1.equals(child1);
        System.out.println(flag3);
    }
}
