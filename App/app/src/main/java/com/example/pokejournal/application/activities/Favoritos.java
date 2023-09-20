package com.example.pokejournal.application.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pokejournal.R;
import com.example.pokejournal.application.fetchers.pokejournal.FavoritePokemonFetcher;
import com.example.pokejournal.application.helpers.ActivityHelper;
import com.example.pokejournal.domain.entities.core.Pokemon;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Favoritos extends AppCompatActivity {

    private final ArrayList<Pokemon> pokemonCards = new ArrayList<>();
    private ArrayAdapter<Pokemon> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);
        setupAdapter();
        String userId = "";

        new FavoritePokemonFetcher(e ->
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show()
        )
        .listAll(userId, this::onLoadPokemons);
    }

    private void setupAdapter(){
        adapter = new ArrayAdapter<Pokemon>(this, R.layout.pokemon_card, pokemonCards) {
            @Override
            public View getView(int pos, View convertView, ViewGroup parent){
                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.pokemon_card, parent, false);
                }

                Pokemon pokemon = getItem(pos);

                TextView txt_pokemonName = convertView.findViewById(R.id.pokemon_name);
                TextView txt_pokemonNumber = convertView.findViewById(R.id.pokemon_number);
                ImageView img_pokemon = convertView.findViewById(R.id.img_pokemon);
                ImageView img_pokemonType1 = convertView.findViewById(R.id.pokemon_type_1);
                ImageView img_pokemonType2 = convertView.findViewById(R.id.pokemon_type_2);

                txt_pokemonName.setText(pokemon.pokeName);
                txt_pokemonNumber.setText(pokemon.pokedexEntry);
                Picasso.get().load(pokemon.imageSpriteUrl).into(img_pokemon);

                img_pokemonType1.setVisibility(View.INVISIBLE);
                img_pokemonType2.setVisibility(View.INVISIBLE);

                int t1 = ActivityHelper.getPokemonTypeImage(pokemon.types.get(0));
                if(t1 != -1){
                    img_pokemonType1.setImageResource(t1);
                    img_pokemonType1.setVisibility(View.VISIBLE);
                }

                if(pokemon.types.size() > 1){
                    int t2 = ActivityHelper.getPokemonTypeImage(pokemon.types.get(1));
                    if(t2 != -1){
                        img_pokemonType2.setImageResource(t2);
                        img_pokemonType2.setVisibility(View.VISIBLE);
                    }
                }

                convertView.setOnClickListener((View view) ->
                        goToPokemonDescriptionActivity(pokemon.pokedexEntry)
                );

                return convertView;
            }
        };
    }

    private void goToPokemonDescriptionActivity(String pokedexEntry){
        startActivity(ActivityHelper.getPokemonDetailsIntent(this, pokedexEntry));
    }

    private void onLoadPokemons(List<Pokemon> pokemons) {
        runOnUiThread(() -> {
            pokemonCards.addAll(pokemons);
            adapter.notifyDataSetChanged();
        });
    }
}