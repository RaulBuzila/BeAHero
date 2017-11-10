package com.example.raulbuzila.beahero;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Arrays;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by paul.stefan on 11/10/2017.
 */

public class UserLocalStore {

    public static  final String SP_NAME = "userDetails";
    SharedPreferences localDB;

    public  UserLocalStore(Context context){
        localDB = context.getSharedPreferences(SP_NAME, 0);
    }

    public  void StoreUser(User user){
        SharedPreferences.Editor spEditor = localDB.edit();
        spEditor.putString("name", user.name);
        spEditor.putString("username", user.username);
        spEditor.putString("password", user.password);
        spEditor.putInt("age", user.age);
        spEditor.commit();
    }

    public  boolean GetUserLoggedIn(){
        return localDB.getBoolean("loggedIn", false);
    }

    public boolean Authenticate(String usName, String usPass)
    {
        String username = localDB.getString("username", "");
        String password = localDB.getString("password", "");
        if(username.equals(usName) && password.equals(usPass))
        {
            SetUserLoggedIn(true);
            return  true;
        }

        return  false;
    }

    public  User GetLoggedInUser(){
        String name = localDB.getString("name", "");
        int age = localDB.getInt("age", 0);
        String username = localDB.getString("username", "");
        String pass = localDB.getString("password", "");
        User user = new User(name, username, pass, age);
        return  user;
    }

    public  void  SetUserLoggedIn(boolean loggedIn){
        SharedPreferences.Editor spEditor = localDB.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();
    }

    public  void ClearUserData(){
        SharedPreferences.Editor spEditor = localDB.edit();
        spEditor.clear();
        spEditor.commit();
    }
}
