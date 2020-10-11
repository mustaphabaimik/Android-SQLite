package com.example.projet.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.projet.Model.Entree;

import java.util.ArrayList;
import java.util.List;

public class EntreeCrud {

    private Connexion mydb;
    private SQLiteDatabase db;
    private  SessionManager sessionManager;
    private UserCrud usercrud;

    public EntreeCrud(Context context) {
        this.mydb=new Connexion(context);
        sessionManager=new SessionManager(context);
        usercrud=new UserCrud(context);
    }

    public boolean addentree(Entree e){
        db=mydb.getWritableDatabase();
        ContentValues values=new ContentValues();


        values.put("libelle",e.getLibelle());
        values.put("description",e.getDescription());
        values.put("emplacement",e.getEmplacement());
        values.put("date",e.getDate());
        values.put("user_id",e.getUser_id());

        long result = db.insert("Entree",null,values);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public List<Entree> getallentrees(){
        db=mydb.getReadableDatabase();
        List<Entree> entrees = new ArrayList<>();

        Cursor cursor=db.rawQuery("select * from Entree where user_id="+usercrud.getuserid(sessionManager.getusername()),null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){

            Entree c = new Entree();
            c.setId(cursor.getInt(0));
            c.setLibelle(cursor.getString(1));
            c.setDescription(cursor.getString(2));
            c.setEmplacement(cursor.getString(3));
            c.setDate(cursor.getString(4));
            c.setUser_id(cursor.getInt(5));

            entrees.add(c);
            cursor.moveToNext();

        }
        cursor.close();

        return entrees;
    }

    public boolean deleteentree(int id){
        db = mydb.getWritableDatabase();
        int result=db.delete("Entree","id = "+id,null);
        if (result == -1 ) return  false;
        else return true;
    }

    public Entree getone(int id){
        Cursor c=null;
        try{
            db=mydb.getReadableDatabase();
            String query="select * from Entree where id='"+id+"'";
            c = db.rawQuery(query,null);
            // c = db.rawQuery(query,new String[]{u.getLogin(),u.getPassword()});
            if(c.moveToFirst()){
                Entree e=new Entree();
                e.setId(c.getInt(0));
                e.setLibelle(c.getString(1));
                e.setDescription(c.getString(2));
                e.setEmplacement(c.getString(3));
                e.setDate(c.getString(4));
                e.setUser_id(c.getInt(5));
                return e;
            }
            else{
                return null;
            }

        }finally {
            c.close();
        }
    }



    public boolean updateentree(Entree e,int id){
        db=mydb.getWritableDatabase();
        ContentValues values=new ContentValues();


        values.put("libelle",e.getLibelle());
        values.put("description",e.getDescription());
        values.put("emplacement",e.getEmplacement());
        values.put("date",e.getDate());


        int result=db.update("Entree",values,"id = "+id,null);

        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }
}
