package com.example.pokejournal.application.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pokejournal.R;
import com.example.pokejournal.application.adapters.PokemonListAdapter;
import com.example.pokejournal.application.fetchers.Fetcher;
import com.example.pokejournal.application.helpers.ActivityHelper;
import com.example.pokejournal.application.services.pokejournal.FavoritePokemonService;
import com.example.pokejournal.domain.entities.core.Pokemon;
import com.example.pokejournal.domain.exceptions.HttpRequestException;
import com.example.pokejournal.domain.exceptions.MalformedException;
import com.example.pokejournal.domain.exceptions.NotFoundException;
import com.example.pokejournal.domain.usecases.pokejournal.FavoritePokemonUsecase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Favoritos extends AppCompatActivity {
    private final ArrayList<Pokemon> pokemonCards = new ArrayList<>();
    private ArrayAdapter<Pokemon> adapter;
    private FavoritePokemonUsecase _favoriteService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        adapter = new PokemonListAdapter(this, R.layout.pokemon_card, pokemonCards, this::goToPokemonDescriptionActivity);
        // TODO: Change to helper
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(this);

        String token = prefs.getString("BEARER_TOKEN", "");
        String userId = prefs.getString("USER_ID", "");

        _favoriteService = new FavoritePokemonService(token);

        Fetcher.async(() -> {
            try {
                List<Pokemon> pokemons = _favoriteService.showFavoritePokemons(userId);
                onLoadPokemons(pokemons);
            } catch (HttpRequestException | MalformedException | NotFoundException | IOException e) {
                runOnUiThread(() -> Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show());
            }
        });
    }

    private void goToPokemonDescriptionActivity(Pokemon pokemon){
        startActivity(ActivityHelper.getPokemonDetailsIntent(this, pokemon.pokedexEntry));
    }

    private void onLoadPokemons(List<Pokemon> pokemons) {
        runOnUiThread(() -> {
            pokemonCards.addAll(pokemons);
            adapter.notifyDataSetChanged();
        });
    }
}