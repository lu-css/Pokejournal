package com.example.pokejournal.domain.entities.pokejournal;

public class User {
    private String username;
    private String email;
    private String token;

    public User(String username, String email){
        this.username = username;
        this.email = email;
        this.token = "";
    }

    public User(String username, String email, String token){
        this.username = username;
        this.email = email;
        this.token = token;
    }

    public String getUsername(){return username;}
    public String getEmail(){return email;}
    public String getToken(){return token;}

}
