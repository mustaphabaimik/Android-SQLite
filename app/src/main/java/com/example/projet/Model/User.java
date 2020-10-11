package com.example.projet.Model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private int id;
    private String nomPrenom;
    private String login;
    private String password;
    private String email;
    private List<Entree> entrees;
    public User() {

    }

    public User(String nomPrenom, String login, String password, String email) {
        this.nomPrenom = nomPrenom;
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public List<Entree> getEntrees() {
        return entrees;
    }

    public void setEntrees(List<Entree> entrees) {
        this.entrees = entrees;
    }

    public int getId() {
        return id;
    }

    public String getNomPrenom() {
        return nomPrenom;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNomPrenom(String nomPrenom) {
        this.nomPrenom = nomPrenom;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
