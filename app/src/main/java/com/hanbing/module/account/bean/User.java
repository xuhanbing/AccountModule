package com.hanbing.module.account.bean;

/**
 * Created by hanbing on 2017/3/8
 */

public class User {

    String name;
    int age;
    String address;


    @Override
    public String toString() {
        return "[name = " + name + ", age = " + age + ", address = " + address + "]";
    }
}
