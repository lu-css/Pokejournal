package com.example.pokejournal.infra.pokejournal;

import com.example.pokejournal.domain.entities.pokejournal.User;
import com.example.pokejournal.domain.exceptions.MalformedException;

import org.json.JSONException;
import org.json.JSONObject;

public class UserParser {
    public UserParser(){}

    public User parseUser(JSONObject json) throws MalformedException {
        try{
            String username = json.getString("userName");
            String email = json.getString("email");
            String token = json.getString("email");

            return new User(username, email, token);
        }catch (JSONException e){
            throw new MalformedException("Fail to parse user json");
        }
    }
}
