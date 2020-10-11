package com.example.projet.Model;

import java.util.Date;

public class Entree {

    private int id;
    private String libelle;
    private String description;
    private String emplacement;
    private String date;
    private int user_id;



    public Entree(String libelle, String description, String emplacement, String date) {
        this.libelle = libelle;
        this.description = description;
        this.emplacement = emplacement;
        this.date = date;
    }

    public Entree() {
    }


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public String getDescription() {
        return description;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Entree{" +
                "libelle='" + libelle + '\'' +
                ", description='" + description + '\'' +
                ", emplacement='" + emplacement + '\'' +
                ", date='" + date + '\'' +
                ", user_id=" + user_id +
                '}';


    }
}
