package com.example.projet.Db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Connexion extends SQLiteOpenHelper {

    public static final String database_name ="contacts";

    public Connexion(Context context) {
          super(context,database_name,null,3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE User(id integer primary key autoincrement,nomPrenom Text,login Text,password Text,email Text);";
        String query2="CREATE TABLE Entree(id integer primary key autoincrement,libelle text,description text,emplacement text,date text,user_id int(11) NOT NULL,Foreign key(user_id) references User(id));";

        db.execSQL(query);
        db.execSQL(query2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists User");
        db.execSQL("drop table if exists Entree");
        onCreate(db);
    }
}
