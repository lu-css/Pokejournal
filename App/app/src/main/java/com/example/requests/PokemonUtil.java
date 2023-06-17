package com.example.requests;

import android.net.Uri;
import android.util.Log;

import com.example.models.Pokemon;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;

public class PokemonUtil {
    private static final String PokeURL = "https://pokeapi.co/api/v2/";
    private static final String PokeEndPoint = "pokemon/";

    public static ArrayList<Pokemon> getManyPokemon(String... pokeQueries) throws Exception{
        ArrayList<Pokemon> pokemons = new ArrayList<>();

        for(String pokeQuery : pokeQueries){
            Optional<Pokemon> pokemon = getPokemon(pokeQuery);

            pokemon.ifPresent(pokemons::add);
        }

        return pokemons;
    }
    public static Optional<Pokemon> getPokemon(String pokeQuery) throws Exception {
            Pokemon pokemon = new Pokemon();

            Optional<JSONObject> json = PokeRequest.getRequest(PokeURL + PokeEndPoint + pokeQuery);

            if (!json.isPresent()){
                return Optional.empty();
            }

            JSONObject response = json.get();
            pokemon.pokeName = response.getString("name");
            pokemon.pokedexEntry = String.valueOf(response.getInt("id"));

            return Optional.of(pokemon);
//        JSONObject pokeArtworks = getArtworks(PokeURL + PokeEndPoint + pokeQuery);
//
//        String pokeSpeciesURL = pokeResponse.getJSONObject("species").getString("url");
//
//        JSONObject evoChain = getEvoChain(pokeSpeciesURL);
//        String pokeEntry = getEntry(pokeSpeciesURL);
//
//        ArrayList<String> evolutions = getEvolutions(evoChain);
//
//        int i = 0;
//        ArrayList<JSONObject> chainArtworks = new ArrayList<JSONObject>();
//        while(evolutions.get(i) != null){
//            JSONObject evoArtworks = getArtworks(PokeURL + PokeEndPoint + evolutions.get(i));
//            chainArtworks.add(evoArtworks);
//            i++;
//        }
//
//        Pokemon pokemon = new Pokemon(evolutions.get(0), pokeEntry, pokeArtworks);
//        return pokemon;
    }

    public static ArrayList<Pokemon> getPokemonsByIds(int... ids) throws Exception{
        ArrayList<Pokemon> pokemons = new ArrayList<>();

        for(int id : ids) {
            Optional<Pokemon> pokemon = getPokemon(String.valueOf(id));
            pokemon.ifPresent(pokemons::add);
        }

        return pokemons;
    }

    private static String getEntry(String pokeURL) throws IOException, Exception{
        JSONObject species = PokeRequest.get(pokeURL);
        String pokeEntry = species.getJSONArray("flavor_text_entries").getJSONObject(0).getString("flavor_text");
        return pokeEntry;
    }

    private static JSONObject getArtworks(String pokeURL) throws IOException, Exception{
        JSONObject pokeResponse = PokeRequest.get(pokeURL);
        JSONObject pokeArtworks = pokeResponse.getJSONObject("sprites").getJSONObject("other").getJSONObject("official-artwork");
        return pokeArtworks;
    }
    private static JSONObject getEvoChain(String pokeURL) throws IOException, Exception{
        JSONObject pokeSpeciesResponse = PokeRequest.get(pokeURL);
        String pokeEvoChainURL = pokeSpeciesResponse.getJSONObject("evolution_chain").getString("url");

        JSONObject pokeEvoChain = PokeRequest.get(pokeEvoChainURL).getJSONObject("chain");
        return pokeEvoChain;
    }

    private static ArrayList<String> getEvolutions(JSONObject chain) throws IOException, Exception {
        ArrayList<String> evolutions = new ArrayList<String>();

        evolutions.add(chain.getJSONObject("species").getString("name"));

        JSONArray evolvesTo = chain.getJSONArray("evolves_to");

        while(evolvesTo != null) {
            JSONObject evolves = evolvesTo.getJSONObject(0);
            JSONObject species = evolves.getJSONObject("species");

            String name = species.getString("name");
            evolutions.add(name);
            evolvesTo = chain.getJSONArray("evolves_to");
        }

        return evolutions;
    }
}