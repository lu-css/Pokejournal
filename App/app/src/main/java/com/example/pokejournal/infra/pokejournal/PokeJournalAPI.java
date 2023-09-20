package com.example.pokejournal.infra.pokejournal;

import androidx.annotation.Nullable;

import com.example.pokejournal.adapters.PokeJournalAdapter;
import com.example.pokejournal.adapters.SimpleRequestAdapter;
import com.example.pokejournal.domain.entities.core.Pokemon;
import com.example.pokejournal.domain.entities.pokejournal.PokemonTeam;
import com.example.pokejournal.domain.entities.pokejournal.User;
import com.example.pokejournal.domain.exceptions.HttpRequestException;
import com.example.pokejournal.domain.exceptions.MalformedException;
import com.example.pokejournal.domain.exceptions.NotFoundException;
import com.example.pokejournal.infra.SimpleRequest.OkHttpSimpleRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpRetryException;
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
    private final UserParser _parseUser = new UserParser();

    public PokeJournalAPI(String token){
        _simpleRequest = new OkHttpSimpleRequest(token);
    }

    @Override
    public List<PokemonTeam> ListTeams(String userId) throws HttpRequestException, MalformedException, IOException {
        List<PokemonTeam> teams = new ArrayList<>();

        String url = String.format("%s/%s/List/%s", BASE_URL, POKETEAM_ENDPOINT, userId);
        JSONObject json = _simpleRequest.simpleGet(url);


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
    public PokemonTeam ShowTeam(String teamId) throws HttpRequestException, MalformedException, IOException {
        String url = String.format("%s/%s", BASE_URL, teamId);
        JSONObject json = _simpleRequest.simpleGet(url);

        return _parsePoketeam.parsePokemonTeam(json);
    }

    @Override
    public PokemonTeam CreateTeam(PokemonTeam team) throws HttpRequestException, MalformedException, IOException {
        String url = String.format("%s/%s/new", BASE_URL, POKETEAM_ENDPOINT);
        JSONObject body = new JSONObject();
        try{
            body.put("a", "b");
            Optional<JSONObject> jsonO = _simpleRequest.simplePost(url, body);

            if(jsonO.isPresent()){
                return null;
            }

            return _parsePoketeam.parsePokemonTeam(jsonO.get());

        } catch (JSONException e){
            throw new MalformedException(e.getMessage());
        }
    }

    @Override
    public void DeleteTeam(String teamId) throws HttpRequestException, IOException {
        String url = String.format("%s/%s/Delete/%s", BASE_URL, POKETEAM_ENDPOINT, teamId);
        _simpleRequest.simpleDelete(url);
    }

    @Override
    public PokemonTeam UpdateTeam(String teamId, String name, String description) throws NotFoundException, MalformedException, IOException {
        // Não implementado.
        return null;
    }

    @Override
    public List<Pokemon> showFavoritePokemons(String userId) throws HttpRequestException, MalformedException, IOException {
        String url = String.format("%s/%s/%s", BASE_URL, FAVORITE_POKEMON_ENDPOINT, userId);
        List<Pokemon> pokemons = new ArrayList<>();

        JSONObject json = _simpleRequest.simpleGet(url);

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
    public Pokemon favoritePokemon(int pokedexEntry) throws HttpRequestException, MalformedException, IOException {
        String url = String.format("%s/%s/Favorite/%s", BASE_URL, FAVORITE_POKEMON_ENDPOINT, pokedexEntry);
        Optional<JSONObject> json = _simpleRequest.simplePost(url, null);

        if(!json.isPresent()){
            throw new HttpRequestException("Cannot favorite pokemon", 404, "");
        }

        return _parsePokemon.parsePokemon(json.get());
    }

    @Override
    public Pokemon unfavoritePokemon(int pokedexEntry) throws HttpRequestException, MalformedException, IOException {
        String url = String.format("%s/%s/Unfavorite/%s", BASE_URL, FAVORITE_POKEMON_ENDPOINT, pokedexEntry);
        Optional<JSONObject> jsonO = _simpleRequest.simplePost(url, null);

        if(!jsonO.isPresent()){
            throw new HttpRequestException("Cannot unfavorite pokemon", 404, "");
        }

        JSONObject json = jsonO.get();

        return _parsePokemon.parsePokemon(json);
    }

    @Override
    public Pokemon addPokemonToTeam(String teamId, int pokemonEntry, @Nullable String customName) throws HttpRequestException, MalformedException, IOException {
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
            throw new HttpRequestException("Team with this Id not found", 404, "");
        }

        JSONObject j = jsonO.get();

        return _parsePokemon.parsePokemon(j);
    }

    @Override
    public Pokemon removePokemonFromTeam(String pokemonId) throws NotFoundException, MalformedException, IOException {
        // Não implementado
        return null;
    }

    @Override
    public Pokemon renamePokemonFromTeam(String pokemonId, String newName) throws NotFoundException, MalformedException, IOException {
        // Não implementado
        return null;
    }

    @Override
    public User registerUser(String username, String email, String password) throws MalformedException, IOException, HttpRequestException {
        // TODO: Change URL
        String url = String.format("%s/%s/AddPokemon", BASE_URL, POKETEAM_ENDPOINT);

        JSONObject json = new JSONObject();

        try {
            json.put("userName", username);
            json.put("email", email);
            json.put("password", password);
        } catch (JSONException e) {
            throw new MalformedException(e.getMessage());
        }

        Optional<JSONObject> jsonO = _simpleRequest.simplePost(url, json);

        if(!jsonO.isPresent()){
            throw new HttpRequestException("Cannot register user", 404, "");
        }

        JSONObject j = jsonO.get();

        return _parseUser.parseUser(j);
    }

    @Override
    public User login(String email, String password) throws MalformedException, IOException, HttpRequestException {
        // TODO: Change URL
        String url = String.format("%s/%s/AddPokemon", BASE_URL, POKETEAM_ENDPOINT);

        JSONObject json = new JSONObject();

        try {
            json.put("email", email);
            json.put("password", password);
        } catch (JSONException e) {
            throw new MalformedException(e.getMessage());
        }

        Optional<JSONObject> jsonO = _simpleRequest.simplePost(url, json);

        if(!jsonO.isPresent()){
            throw new HttpRequestException("Cannot login user", 404, "");
        }

        JSONObject j = jsonO.get();

        return _parseUser.parseUser(j);
    }
}