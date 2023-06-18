package com.example.requests;

import com.example.models.Pokemon;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Optional;

public class PokemonParse {

    public static Pokemon parsePokemonResponse(JSONObject json) throws Exception {
        Pokemon pokemon = new Pokemon();
        String pokedexEntry = String.valueOf(json.getInt("id"));

        pokemon.pokeName = json.getString("name");
        pokemon.pokedexEntry = pokedexEntry;
        pokemon.imageSpriteUrl = json.getJSONObject("sprites").getJSONObject("other").getJSONObject("official-artwork").getString("front_default");

        // Gets the Species response.
        Optional<JSONObject> opSpecies = PokemonUtil.getPokemonSpecies(pokedexEntry);
        if (!opSpecies.isPresent()){
            throw new Exception("Species not found");
        }
        JSONObject species = opSpecies.get();

        pokemon.description = PokemonUtil.getPokemonFlavorText(species);

        return pokemon;
    }

}
