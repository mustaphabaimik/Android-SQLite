package com.example.projet.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.projet.Model.User;

public class UserCrud {

    private Connexion mydb;
    private SQLiteDatabase db;

    public UserCrud(Context context) {
        this.mydb=new Connexion(context);
    }

    public boolean adduser(User user){
        db=mydb.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("nomPrenom",user.getNomPrenom());
        values.put("login",user.getLogin());
        values.put("password",user.getPassword());
        values.put("email",user.getEmail());
        long result = db.insert("User",null,values);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }

    }

    public boolean checkuser(User u){
        Cursor c=null;
        try{
            db=mydb.getReadableDatabase();
            String query="select * from User where login='"+u.getLogin()+"' and password='"+u.getPassword()+"'";
            c = db.rawQuery(query,null);
            // c = db.rawQuery(query,new String[]{u.getLogin(),u.getPassword()});
            if (c.getCount()>0){
                return true;
            }
            else{
                return false;
            }
        }finally {
            c.close();
        }
    }

    public int getuserid(String username){
        Cursor c=null;
        try{
            db=mydb.getReadableDatabase();
            String query="select * from User where login='"+username+"'";
            c = db.rawQuery(query,null);
            // c = db.rawQuery(query,new String[]{u.getLogin(),u.getPassword()});
           if(c.moveToFirst()){
               return c.getInt(0);
           }
           else{
               return -1;
           }

        }finally {
            c.close();
        }
    }
}
