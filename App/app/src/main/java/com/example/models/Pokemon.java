package com.example.models;

import org.json.JSONObject;

import java.util.ArrayList;

public class Pokemon {
    public String pokeName;
    public String pokedexEntry;
    public ArrayList<String> types;
    public ArrayList<String> evolutionChain;
    public String description;
    public String imageSpriteUrl;
    public String pokemonColor;

    public Pokemon(){
        this.pokeName = "";
        this.pokedexEntry = "";
        evolutionChain = new ArrayList<String>();
        types = new ArrayList<String>();
    }
    public Pokemon(String pokeName, String pokedexEntry, JSONObject pokeArtworks){
        this.pokeName = pokeName;
        this.pokedexEntry = pokedexEntry;
        evolutionChain = new ArrayList<String>();
        types = new ArrayList<String>();
    }
}
