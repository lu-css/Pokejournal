package com.example.pokejournal.infra.pokejournal;

import com.example.pokejournal.domain.entities.core.Pokemon;
import com.example.pokejournal.domain.exceptions.MalformedException;

import org.json.JSONException;
import org.json.JSONObject;

public class ParsePokemon {
    public Pokemon parsePokemon(JSONObject json) throws MalformedException {
        try{
            String defaultName = json.getString("defaultName");
            String pokedexEntry = json.getString("pokemonIndex");

            return new Pokemon(defaultName, pokedexEntry, null);
        } catch (JSONException e){
            throw new MalformedException(e.getMessage());
        }
    }
}
