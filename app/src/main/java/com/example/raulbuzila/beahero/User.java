package com.example.raulbuzila.beahero;

/**
 * Created by raul.buzila on 11/10/2017.
 */

public class User {
    String name, username, password;
    int age;

    public  User(String name, String username, String password, int age){
        this.name = name;
        this.username = username;
        this.password = password;
        this.age = age;
    }

    public  User(String username, String password){
        this.username = username;
        this.password = password;
    }
}
