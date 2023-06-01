package com.example.pokejournal;

import org.json.JSONObject;

import java.util.ArrayList;

public class Pokemon {
    public String pokeName;
    public String pokedexEntry;
    public ArrayList<String> types;
    public ArrayList<String> evolutionChain;

    public Pokemon(String pokeName, String pokedexEntry){
        this.pokeName = pokeName;
        this.pokedexEntry = pokedexEntry;
        evolutionChain = new ArrayList<String>();
        types = new ArrayList<String>();
    }
}
