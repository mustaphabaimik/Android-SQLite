package com.example.projet.Db;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        sharedpreferences = context.getSharedPreferences("AppKey",0);
        editor=sharedpreferences.edit();
        editor.apply();
    }

    public void setlogin(boolean login){
        editor.putBoolean("KEY_LOGIN",login);
    }

    public boolean getlogin(){
        return sharedpreferences.getBoolean("KEY_LOGIN",false);
    }

    public void setusername(String username){
        editor.putString("KEY_USERNAME",username);
        editor.commit();
    }

    public String getusername(){
        return sharedpreferences.getString("KEY_USERNAME","");
    }
}
