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
        ImageView img_pokemonColor = findViewById(R.id.background_pokemon_circle);

        Picasso.get().load(data.pokemon.imageSpriteUrl).into(img_pokemon);

        txt_pokemonId.setText(data.pokemon.pokedexEntry);
        txt_pokemonName.setText(data.pokemon.pokeName);
        txt_pokemonDescription.setText(data.pokemon.description);

        GradientDrawable shape = (GradientDrawable) img_pokemonColor.getDrawable().mutate();
        ((GradientDrawable) shape).setColor(Color.RED);
//        GradientDrawable shape = (GradientDrawable) img_pokemonColor.getBackground().mutate();
//        ((GradientDrawable) shape).setColor(Color.RED);
        setDrawableColor(img_pokemonColor.getBackground().mutate(), ActivityHelper.getPokemonColor(data.pokemon.pokemonColor));

        for(int i = 0; i< data.pokemon.evolutionChain.size(); i++){
            String evo = data.pokemon.evolutionChain.get(i);
            Toast.makeText(this, evo, Toast.LENGTH_SHORT).show();
        }
    }

    private void setDrawableColor(Drawable drawable, int color){
        if(drawable instanceof ShapeDrawable){
            ShapeDrawable shape = (ShapeDrawable) drawable;
            shape.getPaint().setColor(ContextCompat.getColor(this, color));
            return;
        }

        if(drawable instanceof GradientDrawable){
            GradientDrawable shape = (GradientDrawable) drawable;
            shape.setColor(ContextCompat.getColor(this, color));
            return;
        }

        if(drawable instanceof ColorDrawable){
            ColorDrawable shape = (ColorDrawable) drawable;
            shape.setColor(ContextCompat.getColor(this, color));
            return;
        }



        Toast.makeText(this, "NADA", Toast.LENGTH_SHORT).show();

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