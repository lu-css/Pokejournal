package com.example.pokejournal.infra.pokeapi;

import com.example.pokejournal.adapters.PokeApiAdapter;
import com.example.pokejournal.adapters.SimpleRequestAdapter;
import com.example.pokejournal.domain.entities.core.Pokemon;
import com.example.pokejournal.domain.exceptions.HttpRequestException;
import com.example.pokejournal.domain.exceptions.MalformedException;
import com.example.pokejournal.domain.exceptions.NotFoundException;
import com.example.pokejournal.infra.SimpleRequest.OkHttpSimpleRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class PokeApi implements PokeApiAdapter
{
    private final String BASE_URL = "https://pokeapi.co/api/v2/";
    private final String POKEMON_ENDPOINT = "pokemon/";
    private final String SPECIES_ENDPOINT = "pokemon-species/";
    private final SimpleRequestAdapter _simpleRequest = new OkHttpSimpleRequest(null);
    private final PokeApiParser _parser = new PokeApiParser();
    private String formatPokequery(String query){ return query.trim().toLowerCase(); }

    private JSONObject pokemonRawJson(String query) throws NotFoundException, IOException, MalformedException {
        String formatedQuery = formatPokequery(query);

        try{
            return _simpleRequest.simpleGet(BASE_URL + POKEMON_ENDPOINT + formatedQuery);
        }catch (HttpRequestException e){
            throw new NotFoundException("");
        }
    }
    @Override
    public Pokemon pokemonFromQuery(String query) throws NotFoundException, IOException, MalformedException {
        JSONObject json = pokemonRawJson(query);
        JSONObject specie = getEpecies(query);

        return _parser.parsePokemon(json, specie);
    }

    @Override
    public Pokemon randomPokemon() throws NotFoundException, IOException, MalformedException {
        int minPokemonIndex = 1, maxPokemonIndex = 1000;

        int randomId = ThreadLocalRandom.current().nextInt(minPokemonIndex, maxPokemonIndex);
        return pokemonFromQuery(String.valueOf(randomId));
    }

    private JSONObject getEpecies(String query) throws IOException, NotFoundException {
        String pokeQuery = formatPokequery(query);

        try {
            return _simpleRequest.simpleGet(BASE_URL + SPECIES_ENDPOINT + pokeQuery);
        } catch (HttpRequestException e){
            throw new NotFoundException("");
        } catch (MalformedException e) {
            throw new RuntimeException(e);
        }
    }

    private JSONObject getEvolutionChain(String query) throws NotFoundException, IOException, MalformedException {

        JSONObject species = getEpecies(query);

        try{
            String url = species.getJSONObject("evolution_chain").getString("url");
            return _simpleRequest.simpleGet(url);
        } catch (JSONException | HttpRequestException e){
            throw new MalformedException(e.getMessage());
        }
    }

    @Override
    public List<Pokemon> evolutionChain(String query) throws NotFoundException, IOException, MalformedException {
        JSONObject evolutionChain = getEvolutionChain(query);

        ArrayList<Pokemon> allEvolutions = new ArrayList<>();

        try {
            JSONObject chain = evolutionChain.getJSONObject("chain");

            String pokemonName = chain.getJSONObject("species").getString("name");
            Pokemon pokemon = pokemonFromQuery(pokemonName);

            allEvolutions.add(pokemon);

            while (true){
                JSONArray evolvesTo = chain.getJSONArray("evolves_to");

                if(evolvesTo.length() == 0){
                    return allEvolutions;
                }

                chain = evolvesTo.getJSONObject(0);

                pokemonName = chain.getJSONObject("species").getString("name");
                pokemon = pokemonFromQuery(pokemonName);
                allEvolutions.add(pokemon);
            }
        } catch (JSONException ex){
            throw new MalformedException(ex.getMessage());
        }
    }
}
