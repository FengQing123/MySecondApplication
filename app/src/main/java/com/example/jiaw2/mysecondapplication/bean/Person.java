package com.example.jiaw2.mysecondapplication.bean;

/**
 * Created by jiaw2 on 2017/1/3.
 */
public class Person {
    public int _id;
    public String name;
    public int age;
    public String info;

    public Person() {
    }

    public Person(String name, int age, String info) {
        this.name = name;
        this.age = age;
        this.info = info;
    }
}
