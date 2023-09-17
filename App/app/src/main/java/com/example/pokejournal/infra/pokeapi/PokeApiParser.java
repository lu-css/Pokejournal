package com.example.pokejournal.infra.pokeapi;

import com.example.pokejournal.domain.entities.core.Pokemon;
import com.example.pokejournal.domain.exceptions.MalformedException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PokeApiParser
{
    public PokeApiParser(){ }

    public Pokemon parsePokemon(JSONObject json, JSONObject species) throws MalformedException {
        try{
            Pokemon pokemon = new Pokemon();
            String pokedexEntry = String.valueOf(json.getInt("id"));

            pokemon.pokeName = json.getString("name");
            pokemon.pokedexEntry = pokedexEntry;

            pokemon.imageSpriteUrl = json.getJSONObject("sprites").getJSONObject("other").getJSONObject("official-artwork").getString("front_default");
            pokemon.types = parseTypes(json);
            pokemon.pokemonColor = species.getJSONObject("color").getString("name");

            return pokemon;
        } catch (JSONException e){
            throw new MalformedException(e.getMessage());
        }
    }

    public ArrayList<String> parseTypes(JSONObject json) throws MalformedException{
        try{
            ArrayList<String> strTypes = new ArrayList<>();
            JSONArray types = json.getJSONArray("types");

            for(int i = 0; i < types.length(); i++){
                JSONObject type = types.getJSONObject(i).getJSONObject("type");
                strTypes.add(type.getString("name"));
            }

            return strTypes;
        } catch (JSONException e){
            throw new MalformedException(e.getMessage());
        }
    }
}
