package com.example.pokejournal.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pokejournal.R;
import com.example.pokejournal.fetchers.pokeapi.FetchSearchPokemon;
import com.example.pokejournal.helpers.ActivityHelper;
import com.example.pokejournal.models.Pokemon;
import com.example.pokejournal.storage.DatabaseHelper;
import com.squareup.picasso.Picasso;

public class detalhePokemonActivity extends AppCompatActivity implements FetchSearchPokemon.OnFetchSearchPokemon
{
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_pokemon);

        Intent intentDisplay = getIntent();
        String pokemonId = intentDisplay.getStringExtra(ActivityHelper.INTENT_POKEMON_ID);
        FetchSearchPokemon fetchSearchPokemon = new FetchSearchPokemon(this);
        fetchSearchPokemon.Execute(pokemonId);
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

        ImageView img_EvoChain1 = findViewById(R.id.img_evo_1);
        ImageView img_EvoChain2 = findViewById(R.id.img_evo_2);
        ImageView img_EvoChain3 = findViewById(R.id.img_evo_3);

        Picasso.get().load(pokemon.imageSpriteUrl).into(img_pokemon);

        txt_pokemonId.setText(pokemon.pokedexEntry);
        txt_pokemonName.setText(pokemon.pokeName);
        txt_pokemonDescription.setText(pokemon.description);
        img_fundoPokemon.setImageResource(ActivityHelper.getPokemonColor(pokemon.pokemonColor));

        int evolutionsLength = pokemon.evolutionChain.size();

        if(evolutionsLength >= 1){
            loadPokemonImage(pokemon.evolutionChain.get(0), img_EvoChain1, pokemon.pokedexEntry);
        }

        if(evolutionsLength >= 2){
            loadPokemonImage(pokemon.evolutionChain.get(1), img_EvoChain2, pokemon.pokedexEntry);
        }

        if(evolutionsLength >= 3){
            loadPokemonImage(pokemon.evolutionChain.get(2), img_EvoChain3, pokemon.pokedexEntry);
        }
    }
    private void loadPokemonImage(Pokemon pokemon, ImageView img, String currentPokemonId){
        Picasso.get().load(pokemon.imageSpriteUrl).into(img);

        if(pokemon.pokedexEntry.equals(currentPokemonId)){
            return;
        }

        img.setOnClickListener((View view) -> {
            goToDetailPokemon(pokemon.pokedexEntry);
        });
    }

    private void goToDetailPokemon(String pokemonId){
        Intent intent = ActivityHelper.getPokemonDetailsIntent(this, pokemonId);
        startActivity(intent);
    }

    @Override
    public void onPokemonSearchFinish(Pokemon pokemon) {
        runOnUiThread(() -> {
            renderPokemonInfos(pokemon);
        });
    }

    @Override
    public void onPokemonSearchFail(Exception e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }
}