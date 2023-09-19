package com.example.pokejournal.infra.pokejournal;

import androidx.annotation.Nullable;

import com.example.pokejournal.adapters.PokeJournalAdapter;
import com.example.pokejournal.adapters.SimpleRequestAdapter;
import com.example.pokejournal.domain.entities.core.Pokemon;
import com.example.pokejournal.domain.entities.pokejournal.PokemonTeam;
import com.example.pokejournal.domain.entities.pokejournal.User;
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

public class PokeJournalAPI implements PokeJournalAdapter
{
    private final String BASE_URL = "https://pokejournal-api.onrender.com";
    private final String POKETEAM_ENDPOINT = "PokeTeam";
    private final String FAVORITE_POKEMON_ENDPOINT = "Pokemon";
    private final SimpleRequestAdapter _simpleRequest;
    private final ParsePokeTeam _parsePoketeam = new ParsePokeTeam();
    private final ParsePokemon _parsePokemon = new ParsePokemon();

    public PokeJournalAPI(String token){
        _simpleRequest = new OkHttpSimpleRequest(token);
    }

    @Override
    public List<PokemonTeam> ListTeams(String userId) throws NotFoundException, MalformedException, IOException {
        List<PokemonTeam> teams = new ArrayList<>();

        String url = String.format("%s/%s/List/%s", BASE_URL, POKETEAM_ENDPOINT, userId);
        Optional<JSONObject> jsonO = _simpleRequest.simpleGet(url);

        if(!jsonO.isPresent()){
            throw new NotFoundException("Teams not founded");
        }

        JSONObject json = jsonO.get();

        try{
            JSONArray teamsJson = json.getJSONArray("data");

            int length = teamsJson.length();

            for(int i = 0; i < length; i++){
                JSONObject teamJson = teamsJson.getJSONObject(i);
                teams.add(_parsePoketeam.parsePokemonTeam(teamJson));
            }

            return teams;
        } catch (JSONException e){
            throw new MalformedException(e.getMessage());
        }
    }

    @Override
    public PokemonTeam ShowTeam(String teamId) throws NotFoundException, MalformedException, IOException {
        String url = String.format("%s/%s", BASE_URL, teamId);
        Optional<JSONObject> jsonO = _simpleRequest.simpleGet(url);

        if(!jsonO.isPresent()){
            String msg = String.format("Team with ID \"%s\" not found", teamId);
            throw new NotFoundException(msg);
        }

        JSONObject json = jsonO.get();

        return _parsePoketeam.parsePokemonTeam(json);
    }

    @Override
    public PokemonTeam CreateTeam(PokemonTeam team) throws NotFoundException, MalformedException, IOException {
        String url = String.format("%s/%s/new", BASE_URL, POKETEAM_ENDPOINT);
        JSONObject body = new JSONObject();
        try{
            body.put("a", "b");
            Optional<JSONObject> jsonO = _simpleRequest.simplePost(url, body);

            if(!jsonO.isPresent()){
                throw new MalformedException("Create team returns nothing");
            }

            return _parsePoketeam.parsePokemonTeam(jsonO.get());

        } catch (JSONException e){
            throw new MalformedException(e.getMessage());
        }
    }

    @Override
    public void DeleteTeam(String teamId) throws NotFoundException, MalformedException, IOException {
        String url = String.format("%s/%s/Delete/%s", BASE_URL, POKETEAM_ENDPOINT, teamId);
        _simpleRequest.simpleDelete(url);
    }

    @Override
    public PokemonTeam UpdateTeam(String teamId, String name, String description) throws NotFoundException, MalformedException, IOException {
        return null;
    }

    @Override
    public List<Pokemon> showFavoritePokemons(String userId) throws NotFoundException, MalformedException, IOException {
        String url = String.format("%s/%s/%s", BASE_URL, FAVORITE_POKEMON_ENDPOINT, userId);
        Optional<JSONObject> jsono = _simpleRequest.simpleGet(url);
        List<Pokemon> pokemons = new ArrayList<>();

        if(!jsono.isPresent()){
            throw new NotFoundException("User not found");
        }

        JSONObject json = jsono.get();

        try {
            JSONArray pokemonJsons = json.getJSONArray("data");

            for(int i = 0; i < pokemonJsons.length(); i++){
                JSONObject j = pokemonJsons.getJSONObject(i);
                Pokemon pokemon = _parsePokemon.parsePokemon(j);
                pokemons.add(pokemon);
            }

            return pokemons;
        } catch (JSONException e){
            throw new MalformedException(e.getMessage());
        }
    }

    @Override
    public Pokemon favoritePokemon(int pokedexEntry) throws NotFoundException, MalformedException, IOException {
        String url = String.format("%s/%s/Unfavorite/%s", BASE_URL, FAVORITE_POKEMON_ENDPOINT, pokedexEntry);
        Optional<JSONObject> jsonO = _simpleRequest.simplePost(url, null);

        if(!jsonO.isPresent()){
            throw new NotFoundException("Pokemon with pokedex entry not found");
        }

        JSONObject json = jsonO.get();

        return _parsePokemon.parsePokemon(json);
    }

    @Override
    public Pokemon unfavoritePokemon(int pokedexEntry) throws NotFoundException, MalformedException, IOException {
        String url = String.format("%s/%s/Unfavorite/%s", BASE_URL, FAVORITE_POKEMON_ENDPOINT, pokedexEntry);
        Optional<JSONObject> jsonO = _simpleRequest.simplePost(url, null);

        if(!jsonO.isPresent()){
            throw new NotFoundException("Pokemon with pokedex entry not found");
        }

        JSONObject json = jsonO.get();

        return _parsePokemon.parsePokemon(json);
    }

    @Override
    public Pokemon addPokemonToTeam(String teamId, int pokemonEntry, @Nullable String customName) throws NotFoundException, MalformedException, IOException {
        String url = String.format("%s/%s/AddPokemon", BASE_URL, POKETEAM_ENDPOINT);

        if(customName == null) customName = "";

        JSONObject json = new JSONObject();

        try {
            json.put("pokemonIndex", pokemonEntry);
            json.put("customName", customName);
            json.put("teamId", teamId);
        } catch (JSONException e) {
            throw new MalformedException(e.getMessage());
        }

        Optional<JSONObject> jsonO = _simpleRequest.simplePost(url, json);

        if(!jsonO.isPresent()){
            throw new NotFoundException("Team with this Id not found");
        }

        JSONObject j = jsonO.get();

        return _parsePokemon.parsePokemon(j);
    }

    @Override
    public Pokemon removePokemonFromTeam(String pokemonId) throws NotFoundException, MalformedException, IOException {
        return null;
    }

    @Override
    public Pokemon renamePokemonFromTeam(String pokemonId, String newName) throws NotFoundException, MalformedException, IOException {
        return null;
    }

    @Override
    public User registerUser(String username, String email, String password) throws NotFoundException, MalformedException, IOException {
        return null;
    }

    @Override
    public User login(String email, String password) throws NotFoundException, MalformedException, IOException {
        return null;
    }
}
