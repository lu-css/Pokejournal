package com.example.pokejournal;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class PokemonUtil {
    private static final String PokeURL = "https://pokeapi.co/api/v2/";
    private static final String PokeEndPoint = "pokemon/";
    public static Pokemon getPokemon(String pokeQuery) throws IOException, Exception {
        JSONObject pokeResponse = PokeRequest.get(PokeURL + PokeEndPoint + pokeQuery);
        String pokeSpeciesURL = pokeResponse.getJSONObject("species").getString("url");

        JSONObject evoChain = getEvoChain(pokeSpeciesURL);

        ArrayList<String> evolutions = getEvolutions(evoChain);


        Pokemon pokemon = new Pokemon();
        return pokemon;
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

        if(evolvesTo != null) {
            JSONObject evolves = evolvesTo.getJSONObject(0);
            JSONObject species = evolves.getJSONObject("species");

            String name = species.getString("name");
            evolutions.add(name);
            
            getEvolutions(evolves);
        }

        return evolutions;
    }
}