package com.example.pokejournal.application.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pokejournal.R;
import com.example.pokejournal.application.adapters.PokemonListAdapter;
import com.example.pokejournal.application.fetchers.Fetcher;
import com.example.pokejournal.application.helpers.ActivityHelper;
import com.example.pokejournal.application.services.pokeapi.SinglePokemonService;
import com.example.pokejournal.domain.entities.core.Pokemon;
import com.example.pokejournal.domain.usecases.pokeapi.SinglePokemonUsecase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class tela_inicial extends AppCompatActivity
{
    private final ArrayList<Pokemon> pokemonCards = new ArrayList<>();
    private final SinglePokemonUsecase _pokemonService = new SinglePokemonService();
    private ArrayAdapter<Pokemon> adapter;
    private EditText edit_searchBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        this.edit_searchBar = findViewById(R.id.seachbar);

        adapter = new PokemonListAdapter(this, R.layout.pokemon_card, pokemonCards, this::goToPokemonDescriptionActivity);

        GridView gridView = findViewById(R.id.pokemonCardsList);

        gridView.setAdapter(adapter);

        List<String> pokemonsToShow = Arrays.asList("bulbasaur", "charmander", "squirtle", "carterpie", "weedle", "pidgey", "rattata",
                "spearow", "ekans", "pikachu", "sandshrew", "clfairy", "ninetales", "jigglypuff");

        Fetcher.async(() -> {
            for(String pokemonQuery : pokemonsToShow){
                try{
                    Pokemon pokemon = _pokemonService.pokemonFromQuery(pokemonQuery);
                    PokemonListNewItem(pokemon);
                } catch (Exception e){
                    Toast.makeText(tela_inicial.this, e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void goToPokemonDescriptionActivity(Pokemon pokemon){
        startActivity(ActivityHelper.getPokemonDetailsIntent(this, pokemon.pokedexEntry));
    }

    public void startSearch(View v){
        String pokemonQuery = edit_searchBar.getText().toString();

        Fetcher.async(() -> {
            try{
                Pokemon pokemon = _pokemonService.pokemonFromQuery(pokemonQuery);
                onPokemonSearchFinish(pokemon);
            } catch (Exception e){
                Toast.makeText(tela_inicial.this, e.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void PokemonListNewItem(Pokemon pokemon) {
        runOnUiThread(() -> {
            pokemonCards.add(pokemon);
            adapter.notifyDataSetChanged();
        });
    }

    public void onPokemonSearchFinish(Pokemon pokemon) {
        runOnUiThread(() -> goToPokemonDescriptionActivity(pokemon) );
    }
}