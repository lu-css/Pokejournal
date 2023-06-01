package com.example.pokejournal;

import org.json.JSONObject;

import java.util.ArrayList;

public class Pokemon {
    public String pokeName;
    public String pokedexEntry;
    public ArrayList<String> types;
    public ArrayList<String> evolutionChain;

    public JSONObject pokeArtworks;

    public Pokemon(String pokeName, String pokedexEntry, JSONObject pokeArtworks){
        this.pokeName = pokeName;
        this.pokedexEntry = pokedexEntry;
        this.pokeArtworks = pokeArtworks;
        evolutionChain = new ArrayList<String>();
        types = new ArrayList<String>();
    }
}
