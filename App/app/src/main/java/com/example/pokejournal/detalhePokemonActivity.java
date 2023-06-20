package com.example.pokejournal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helpers.ActivityHelper;
import com.example.models.Pokemon;
import com.example.requests.PokemonUtil;
import com.example.storage.DatabaseHelper;
import com.squareup.picasso.Picasso;

public class detalhePokemonActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Model>{
    private String pokemonId = "";
    private boolean finishLoad = false;
    DatabaseHelper db;

    private String pokeId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_pokemon);

        Intent intentDisplay = getIntent();
        pokemonId = intentDisplay.getStringExtra(ActivityHelper.INTENT_POKEMON_ID);

        if (getSupportLoaderManager().getLoader(0) != null) {
            getSupportLoaderManager().initLoader(0, null, this);
        }

        Bundle args = new Bundle();
        args.putString("pokemon_id", pokemonId);

        getSupportLoaderManager().restartLoader(0, args, this);
        db = new DatabaseHelper(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LoaderManager.getInstance(this).destroyLoader(0);
    }

    public void openNav(View v){
        Intent intent = new Intent(this, navbar.class);
        startActivity(intent);
    }

    public void favButtonToggle(View v){
        if(!finishLoad){
            Toast.makeText(this, "Carregando!", Toast.LENGTH_LONG).show();
            return;
        }

        if(pokeId.isEmpty()){
            Toast.makeText(this, "Vazio", Toast.LENGTH_LONG).show();
            return;
        }

        if(db.insertPokemon(pokeId)){
            Toast.makeText(this, "FAVORITADO", Toast.LENGTH_LONG).show();
        }

        Toast.makeText(this, "Erro ao salvar", Toast.LENGTH_LONG).show();
    }

    @NonNull
    @Override
    public Loader<Model> onCreateLoader(int id, @Nullable Bundle args) {
        Log.d("FINISH", "START");
        return new DetalheLoader(this, args);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Model> loader, Model data) {
        if (data.hasError()){
            Toast.makeText(this, data.errorMessage, Toast.LENGTH_SHORT).show();
            return;
        }

        renderPokemonInfos(data.pokemon);
        pokeId = data.pokemon.pokedexEntry;
        finishLoad = true;
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

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToDetailPokemon(pokemon.pokedexEntry);
            }
        });
    }

    private void goToDetailPokemon(String pokemonId){
        Intent intent = ActivityHelper.getPokemonDetailsIntent(this, pokemonId);
        startActivity(intent);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Model> loader) { }

}

class Model {
    public final String errorMessage;
    public final Pokemon pokemon;
    private boolean hasError = false;

    Model(String errorMessage) {
        this.errorMessage = errorMessage;
        this.pokemon = null;
        hasError = true;
    }

    Model(Pokemon pokemon){
        this.pokemon = pokemon;
        this.errorMessage = "";
    }

    public boolean hasError(){
        return hasError;
    }
}

class DetalheLoader extends AsyncTaskLoader<Model> {

    private String pokemonId;
    public DetalheLoader(@NonNull Context context, Bundle args) {
        super(context);
        this.pokemonId = args.getString("pokemon_id");
    }

    @Override
    protected void onStartLoading(){
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public Model loadInBackground() {
        try{
            Pokemon pokemon = PokemonUtil.getPokemon(pokemonId);

            return new Model(pokemon);
        }
        catch (Exception e){
            return new Model("ERROR: " + e.getMessage());
        }
    }
}