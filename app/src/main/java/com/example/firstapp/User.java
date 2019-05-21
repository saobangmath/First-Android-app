package com.example.firstapp;

public class User {
    String name, password, email, telephone;
    public User(String name, String password,String email,String telephone){
        this.name = name;
        this.email=email;
        this.password = password;
        this.telephone = telephone;
    }
    public User(String name , String password){
        this.name = name;
        this.password = password;
        this.email ="";
        this.telephone = "";
    }
}