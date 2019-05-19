package com.example.firstapp;

public class Notes {
    private int id;
    private String name;
    public Notes(int id, String name){
        this.id = id;
        this.name = name;
    }
    public int GetId(){
        return this.id;
    }
    public void Setid(int id){
        this.id = id;
    }
    public String getName(){
        return this.name;
    }

}
