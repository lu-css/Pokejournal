package com.example.pokejournal;

import org.json.JSONObject;

import java.util.ArrayList;

public class Pokemon {
    public String pokeName;
    public int weight;
    public int height;
    public ArrayList<String> types;
    public ArrayList<String> evolutionChain;

    public Pokemon(String pokeName, int weight, int height){
        this.pokeName = pokeName;
        this.weight = weight;
        this.height = height;
        evolutionChain = new ArrayList<String>();
        types = new ArrayList<String>();
    }
}
