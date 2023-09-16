package com.example.pokejournal.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pokejournal.R;
import com.example.pokejournal.fetchers.FetchPokemonList;
import com.example.pokejournal.helpers.ActivityHelper;
import com.example.pokejournal.models.Pokemon;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class tela_inicial extends AppCompatActivity implements FetchPokemonList.FetchPokemonListListener
{
    private ImageView img_bulbB;
    private final ArrayList<Pokemon> pokemonCards = new ArrayList<>();
    private ArrayAdapter<Pokemon> adapter;
    private EditText edit_searchBar;
    private HandlerThread pokemonListThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        this.edit_searchBar = findViewById(R.id.seachbar);

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

        GridView gridView = findViewById(R.id.pokemonCardsList);

        gridView.setAdapter(adapter);

        this.pokemonListThread = new HandlerThread("PokemonListFetcher");
        pokemonListThread.start();

        FetchPokemonList fetch = new FetchPokemonList(new Handler(pokemonListThread.getLooper()),this);
        List<String> pokemonsToShow = Arrays.asList("bulbasaur", "ivysaur", "charmander", "squirtle", "carterpie", "weedle", "pidgey", "rattata",
                "spearow", "ekans", "pikachu", "sandshrew", "clfairy", "ninetales", "jigglypuff");

        fetch.Execute(pokemonsToShow);
    }

    private void goToPokemonDescriptionActivity(String pokedexEntry){
        startActivity(ActivityHelper.getPokemonDetailsIntent(this, pokedexEntry));
    }

    @Override
    public void PokemonListNewItem(Pokemon pokemon) {
        runOnUiThread(() -> {
            pokemonCards.add(pokemon);
            adapter.notifyDataSetChanged();
        });
    }

    @Override
    public void PokemonLIstOnFail(Exception exception) {
        Toast.makeText(tela_inicial.this, exception.toString(), Toast.LENGTH_LONG).show();

        exception.printStackTrace();
    }

    @Override
    public void PokemonListFinish() {
        pokemonListThread.quit();
    }
}