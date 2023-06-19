package com.example.pokejournal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helpers.ActivityHelper;
import com.example.models.Pokemon;
import com.example.requests.PokemonUtil;
import com.squareup.picasso.Picasso;

import java.util.Optional;

public class detalhePokemonActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Model>{
    private String pokemonId = "";
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
    @NonNull
    @Override
    public Loader<Model> onCreateLoader(int id, @Nullable Bundle args) {
        Log.d("FINISH", "START");
        return new DetalheLoader(this, args);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Model> loader, Model data) {
        Log.d("FINISH", "LOAD");
        if (data.hasError()){
            Toast.makeText(this, data.errorMessage, Toast.LENGTH_SHORT).show();
            return;
        }

        TextView txt_pokemonName = findViewById(R.id.txt_pokemonName);
        TextView txt_pokemonId = findViewById(R.id.txt_pokemonId);
        TextView txt_pokemonDescription = findViewById(R.id.pokemon_description);
        ImageView img_pokemon = findViewById(R.id.img_descpokemon);
        ImageView img_fundoPokemon = findViewById(R.id.fundo_pokemon);

        ImageView img_EvoChain1 = findViewById(R.id.evo_chain_1);
        ImageView img_EvoChain2 = findViewById(R.id.evo_chain_2);
        ImageView img_EvoChain3 = findViewById(R.id.evo_chain_3);

        Picasso.get().load(data.pokemon.imageSpriteUrl).into(img_pokemon);

        txt_pokemonId.setText(data.pokemon.pokedexEntry);
        txt_pokemonName.setText(data.pokemon.pokeName);
        txt_pokemonDescription.setText(data.pokemon.description);
        img_fundoPokemon.setImageResource(ActivityHelper.getPokemonColor(data.pokemon.pokemonColor));

        int evolutionsLength = data.pokemon.evolutionChain.size();

        if(evolutionsLength >= 1){
            String evo = data.pokemon.evolutionChain.get(0);
            Picasso.get().load(evo).into(img_EvoChain1);
        }

        if(evolutionsLength >= 2){
            String evo = data.pokemon.evolutionChain.get(1);
            Picasso.get().load(evo).into(img_EvoChain2);
        }

        if(evolutionsLength >= 3){
            String evo = data.pokemon.evolutionChain.get(2);
            Picasso.get().load(evo).into(img_EvoChain3);
        }
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
            Optional<Pokemon> pokemon = PokemonUtil.getPokemon(pokemonId);

            if (!pokemon.isPresent()){
                throw new Exception("Pokemon Not found");
            }

            return new Model(pokemon.get());
        }
        catch (Exception e){
            return new Model("ERROR: " + e.getMessage());
        }
    }
}