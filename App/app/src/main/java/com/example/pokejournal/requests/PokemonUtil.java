package com.example.pokejournal.requests;

import android.util.Log;

import com.example.pokejournal.models.Pokemon;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class PokemonUtil {
    private static final String PokeURL = "https://pokeapi.co/api/v2/";
    private static final String PokeEndPoint = "pokemon/";
    private static final String SPECIES_ENDPOINT = "pokemon-species/";

    public static ArrayList<Pokemon> getManyPokemonCards(String... pokeQueries){
        ArrayList<Pokemon> pokemons = new ArrayList<>();

        for(String pokeQuery : pokeQueries){
            try{
                Pokemon pokemon = getPokemonCard(pokeQuery);
                pokemons.add(pokemon);
            } catch (Exception ignored){}
        }

        return pokemons;
    }

    public static ArrayList<Pokemon> getManyPokemon(String... pokeQueries) throws Exception{
        ArrayList<Pokemon> pokemons = new ArrayList<>();

        for(String pokeQuery : pokeQueries){
            Pokemon pokemon = getPokemon(pokeQuery);
            pokemons.add(pokemon);
        }

        return pokemons;
    }
    private static JSONObject makeGetPokemonRequest(String pokeQuery) throws Exception {
        String formatedPokeQuery = pokeQuery.toLowerCase().trim();
        Optional<JSONObject> json = PokeRequest.getRequest(PokeURL + PokeEndPoint + formatedPokeQuery);

        if(!json.isPresent()){
            throw new Exception("Empty request.");
        }

        return json.get();
    }
    public static Pokemon getPokemonCard(String pokeQuery) throws Exception {
        JSONObject json = makeGetPokemonRequest(pokeQuery);
        return PokemonParse.parsePokemonCardInfos(json);
    }

    public static Pokemon getPokemon(String pokeQuery) throws Exception {
        JSONObject json = makeGetPokemonRequest(pokeQuery);
        return PokemonParse.parseFullPokemon(json);
    }

    public static Optional<Pokemon> getPokemonImage(String pokeQuery) throws Exception {
        String formatedPokeQuery = pokeQuery.toLowerCase().trim();
        Optional<JSONObject> json = PokeRequest.getRequest(PokeURL + PokeEndPoint + formatedPokeQuery);

        if (!json.isPresent()){
            return Optional.empty();
        }

        JSONObject response = json.get();

        Pokemon pokemon = new Pokemon();
        pokemon.imageSpriteUrl = response.getJSONObject("sprites").getString("front_default");
        pokemon.pokedexEntry = String.valueOf(response.getInt("id"));
        pokemon.pokeName = response.getString("name");

        return Optional.of(pokemon);
    }

    public static Pokemon getRandomPokemon() throws Exception{
        int randomId = ThreadLocalRandom.current().nextInt(0, 500);

        return getPokemonCard(String.valueOf(randomId));
    }

    public static boolean pokemonExists(String pokeQuery) throws Exception {
        String formatedPokeQuery = pokeQuery.toLowerCase().trim();
        Optional<JSONObject> json = PokeRequest.getRequest(PokeURL + PokeEndPoint + formatedPokeQuery);

        Log.d("POKEMON_EXIST", json.toString());

        if(!json.isPresent()){
            return false;
        }

        JSONObject response = json.get();

        try{
            int id = response.getInt("id");
            Log.d("POKEMON_EXIST", String.valueOf(id));
            return true;
        } catch (Exception e){
            return false;
        }
    }
    public static Optional<JSONObject> getPokemonSpecies(String pokeQuery) throws Exception {
        return PokeRequest.getRequest(PokeURL + SPECIES_ENDPOINT + pokeQuery);
    }

    public static Optional<JSONObject> getLink(String url) throws Exception {
        return PokeRequest.getRequest(url);
    }

    public static String getPokemonFlavorText(JSONObject species) throws JSONException {
        JSONArray flavors = species.getJSONArray("flavor_text_entries");
        JSONObject flavor = null;

        do {
            flavor = getRandomItemInJSONArray(flavors);
        } while (flavor == null || !flavor.getJSONObject("language").getString("name").equals("en"));

        return flavor.getString("flavor_text");
    }

    private static JSONObject getRandomItemInJSONArray(JSONArray arr) throws JSONException {
        int length = arr.length();

        int randomPos = ThreadLocalRandom.current().nextInt(0, length);

        return arr.getJSONObject(randomPos);
    }
}