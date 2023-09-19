package com.example.pokejournal.infra.pokejournal;

import com.example.pokejournal.domain.entities.pokejournal.PokemonTeam;
import com.example.pokejournal.domain.exceptions.MalformedException;

import org.json.JSONException;
import org.json.JSONObject;

public class ParsePokeTeam {
    public PokemonTeam parsePokemonTeam(JSONObject json) throws MalformedException {
        try{
            String id = json.getString("id");
            String name = json.getString("name");
            String description = json.getString("description");

            return new PokemonTeam(id, name, description, null);
        } catch (JSONException e){
            throw new MalformedException(e.getMessage());
        }
    }
}
