package com.example.pokejournal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

import com.example.helpers.ActivityHelper;
import com.example.models.Pokemon;
import com.example.requests.PokemonUtil;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class tela_inicial extends AppCompatActivity implements LoaderManager.LoaderCallbacks<TelaInicialModel> {

    private ImageView img_bulbB;
    private final ArrayList<Pokemon> pokemonCards = new ArrayList<>();
    private ArrayAdapter<Pokemon> adapter;
    private EditText edit_searchBar;
    private final int LIST_POKEMON_LOADER = 0;
    private final int SEARCH_POKEMON_LOADER = 1;
    private LoaderManager loaderManager;

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


                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        goToPokemonDescriptionActivity(pokemon.pokedexEntry);
                    }
                });

                return convertView;
            }
        };

        GridView gridView = findViewById(R.id.pokemonCardsList);

        gridView.setAdapter(adapter);

        Bundle query = new Bundle();

        loaderManager = LoaderManager.getInstance(this);
        Loader<TelaInicialModel> listPokemonLoader = loaderManager.getLoader(LIST_POKEMON_LOADER);

        if(listPokemonLoader == null){
            loaderManager.initLoader(LIST_POKEMON_LOADER, query, this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        loaderManager.destroyLoader(SEARCH_POKEMON_LOADER);
        loaderManager.destroyLoader(LIST_POKEMON_LOADER);
    }

    private void goToPokemonDescriptionActivity(String pokedexEntry){
        startActivity(ActivityHelper.getPokemonDetailsIntent(this, pokedexEntry));
    }

    private void addPokemonCards(ArrayList<Pokemon> pokemons){
        pokemonCards.addAll(pokemons);
        adapter.notifyDataSetChanged();
    }

    public void startSearch(View v){
        String query = edit_searchBar.getText().toString();

        Bundle args = new Bundle();

        args.putString("pokemon_id", query);

        LoaderManager loaderManager = LoaderManager.getInstance(this);
        Loader<TelaInicialModel> searchPokemonLoader = loaderManager.getLoader(SEARCH_POKEMON_LOADER);

        if(searchPokemonLoader == null){
            loaderManager.initLoader(SEARCH_POKEMON_LOADER, args, this);
        }
    }

    @NonNull
    @Override
    public Loader<TelaInicialModel> onCreateLoader(int id, @Nullable Bundle args) {
        if (id == SEARCH_POKEMON_LOADER) {
            return new SearchLoader(this, args);
        }

        return new TelaInicialLoader(this, args);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<TelaInicialModel> loader, TelaInicialModel data) {
        if(!data.searchedPokemon.isEmpty()){
            goToPokemonDescriptionActivity(String.valueOf(data.searchedPokemon));
            return;
        }

        if(data.hasError()){
            Toast.makeText(this, data.errorMessage,Toast.LENGTH_SHORT).show();
            return;
        }

        addPokemonCards(data.pokemons);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<TelaInicialModel> loader) { }
}

class TelaInicialModel{
    public final ArrayList<Pokemon> pokemons;
    public final String errorMessage;
    public String searchedPokemon = "";
    private boolean hasError = false;

    public TelaInicialModel(){
        this.errorMessage = "";
        this.pokemons = new ArrayList<>();
        this.hasError = true;
    }

    public TelaInicialModel(String errorMessage){
        this.errorMessage = errorMessage;
        this.pokemons = new ArrayList<>();
        this.hasError = true;
    }

    public TelaInicialModel(ArrayList<Pokemon> pokemons){
        this.pokemons = pokemons;
        this.errorMessage = "";
    }

    public boolean hasError(){
        return hasError;
    }

}
class TelaInicialLoader extends AsyncTaskLoader<TelaInicialModel> {
    public TelaInicialLoader(@NonNull Context context, Bundle args) {
        super(context);
    }

    @Override
    protected void onStartLoading(){
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public TelaInicialModel loadInBackground() {
        try{
            ArrayList<Pokemon> pokemons = PokemonUtil.getManyPokemon("ditto", "magikarp", "ninguem", "pichu");

            return new TelaInicialModel(pokemons);
        }
        catch (Exception e){
            e.printStackTrace();
            return new TelaInicialModel("ERRO: " + e.getMessage());
        }
    }
}


class SearchLoader extends AsyncTaskLoader<TelaInicialModel> {
    private Bundle args;
    private final String pokemon_id;

    public SearchLoader(@NonNull Context context, Bundle args) {
        super(context);
        this.pokemon_id = args.getString("pokemon_id");
    }

    @Override
    protected void onStartLoading(){
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public TelaInicialModel loadInBackground() {
        try{
            // Some validations
            boolean pokemonExists = PokemonUtil.pokemonExists(pokemon_id.trim().toLowerCase());

            if(!pokemonExists){
                return new TelaInicialModel("Pokemon Does not exists");
            }

            TelaInicialModel tela = new TelaInicialModel();

            tela.searchedPokemon = pokemon_id;

            return tela;
        }catch (Exception e){
            return new TelaInicialModel("ERRO: " + e.getMessage());
        }
    }
}


