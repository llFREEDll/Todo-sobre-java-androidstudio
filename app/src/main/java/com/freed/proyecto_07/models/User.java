package com.freed.proyecto_07.models;

public class User {
    String newId;
    public User(String us){
        this.newId= us;

    }
    public String getUser(){
        return this.newId;
    }

    public void setUser(String user){
         this.newId = user;
    }

}
