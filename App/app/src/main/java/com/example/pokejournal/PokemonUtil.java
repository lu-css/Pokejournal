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
        JSONObject pokeArtworks = getArtworks(PokeURL + PokeEndPoint + pokeQuery);

        String pokeSpeciesURL = pokeResponse.getJSONObject("species").getString("url");

        JSONObject evoChain = getEvoChain(pokeSpeciesURL);
        String pokeEntry = getEntry(pokeSpeciesURL);

        ArrayList<String> evolutions = getEvolutions(evoChain);

        int i = 0;
        ArrayList<JSONObject> chainArtworks = new ArrayList<JSONObject>();
        while(evolutions.get(i) != null){
            JSONObject evoArtworks = getArtworks(PokeURL + PokeEndPoint + evolutions.get(i));
            chainArtworks.add(evoArtworks);
            i++;
        }

        Pokemon pokemon = new Pokemon(evolutions.get(0), pokeEntry, pokeArtworks);
        return pokemon;
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