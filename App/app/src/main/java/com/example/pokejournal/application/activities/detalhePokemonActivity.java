package com.example.pokejournal.application.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pokejournal.R;
import com.example.pokejournal.application.fetchers.Fetcher;
import com.example.pokejournal.application.helpers.ActivityHelper;
import com.example.pokejournal.application.services.pokeapi.SinglePokemonService;
import com.example.pokejournal.application.services.pokejournal.FavoritePokemonService;
import com.example.pokejournal.domain.entities.core.Pokemon;
import com.example.pokejournal.domain.usecases.pokeapi.SinglePokemonUsecase;
import com.example.pokejournal.domain.usecases.pokejournal.FavoritePokemonUsecase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class detalhePokemonActivity extends AppCompatActivity
{
    private int pokemonEntry;
    private final SinglePokemonUsecase _pokemonService = new SinglePokemonService();
    private FavoritePokemonUsecase _favoriteService;
    private boolean favorited = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_pokemon);

        Intent intentDisplay = getIntent();
        String pokemonId = intentDisplay.getStringExtra(ActivityHelper.INTENT_POKEMON_ID);
        this.pokemonEntry = Integer.parseInt(pokemonId);

        String token = getToken();
        _favoriteService  = new FavoritePokemonService(token);

        Fetcher.async(() -> {
            try{
                Pokemon pokemon = _pokemonService.pokemonFromQuery(pokemonId);

                onPokemonSearchFinish(pokemon);
                ArrayList<Pokemon> evolutionChain = (ArrayList<Pokemon>) _pokemonService.evolutionChain(pokemonId);
                runOnUiThread(() -> loadEvolutionChain(evolutionChain, pokemon.pokedexEntry));
            } catch (Exception e){
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void openNav(View v){
        Intent intent = new Intent(this, navbar.class);
        startActivity(intent);
    }

    private void renderPokemonInfos(Pokemon pokemon){
        TextView txt_pokemonName = findViewById(R.id.txt_pokemonName);
        TextView txt_pokemonId = findViewById(R.id.txt_pokemonId);
        TextView txt_pokemonDescription = findViewById(R.id.pokemon_description);
        ImageView img_pokemon = findViewById(R.id.img_descpokemon);
        ImageView img_fundoPokemon = findViewById(R.id.fundo_pokemon);

        Picasso.get().load(pokemon.imageSpriteUrl).into(img_pokemon);
        txt_pokemonId.setText(pokemon.pokedexEntry);
        txt_pokemonName.setText(pokemon.pokeName);
        txt_pokemonDescription.setText(pokemon.description);
        img_fundoPokemon.setImageResource(ActivityHelper.getPokemonColor(pokemon.pokemonColor));
    }
    private void loadEvolutionChain(ArrayList<Pokemon> evolutionChain, String pokedexEntry){
        ImageView img_EvoChain1 = findViewById(R.id.img_evo_1);
        ImageView img_EvoChain2 = findViewById(R.id.img_evo_2);
        ImageView img_EvoChain3 = findViewById(R.id.img_evo_3);

        int evolutionsLength = evolutionChain.size();
        Log.d("POKEMON_DETAIL", String.valueOf(evolutionsLength));

        if(evolutionsLength >= 1){
            loadPokemonImage(evolutionChain.get(0), img_EvoChain1, pokedexEntry);
        }

        if(evolutionsLength >= 2){
            loadPokemonImage(evolutionChain.get(1), img_EvoChain2, pokedexEntry);
        }

        if(evolutionsLength >= 3){
            loadPokemonImage(evolutionChain.get(2), img_EvoChain3, pokedexEntry);
        }
    }

    private void loadPokemonImage(Pokemon pokemon, ImageView img, String currentPokemonId){
        Picasso.get().load(pokemon.imageSpriteUrl).into(img);

        if(pokemon.pokedexEntry.equals(currentPokemonId)){
            return;
        }

        img.setOnClickListener( view -> goToDetailPokemon(pokemon.pokedexEntry) );
    }

    private void goToDetailPokemon(String pokemonId){
        Intent intent = ActivityHelper.getPokemonDetailsIntent(this, pokemonId);
        startActivity(intent);
    }

    public void onPokemonSearchFinish(Pokemon pokemon) {
        runOnUiThread(() -> renderPokemonInfos(pokemon));
    }

    private String getToken(){
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(this);

       return prefs.getString("BEARER_TOKEN", "");
    }

    public void toggleFavorite(View v){
        Fetcher.async(() -> {
            try {
                if(favorited){
                    _favoriteService.unfavoritePokemon(pokemonEntry);
                } else{
                    _favoriteService.favoritePokemon(pokemonEntry);
                }

                favorited = !favorited;

                updateFavorite(favorited);
            } catch (Exception e){
                runOnUiThread(() -> Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show());
            }
        });
    }

    public void updateFavorite(boolean checked){
        runOnUiThread(() -> {
            Toast.makeText(this, checked ? "Pokemon favoritado" : "Pokemon desfavoritado", Toast.LENGTH_SHORT).show();
            // TODO: Mudar icone no layout
            if(checked){
                return;
            }
        });

    }
}