package com.example.pokejournal.application.activities;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pokejournal.R;
import com.example.pokejournal.application.fetchers.pokeapi.RandomPokemonFetcher;
import com.example.pokejournal.application.helpers.ImageHelper;
import com.example.pokejournal.domain.entities.core.Pokemon;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class QuizActivity extends AppCompatActivity
{
    private ImageView img_pokemonQuiz;
    private EditText txt_pokemon;
    private String pokemonName;
    private Bitmap originalBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        loadViews();
        loadRandomPokemon(null);
    }

    private void loadRandomPokemon(View v){
        new RandomPokemonFetcher
                (this::onLoadRandomPokemon,this::onFailRandomPokemon)
            .execute();
    }

    private void loadViews(){
        img_pokemonQuiz = findViewById(R.id.pokemon_quiz_img);
        txt_pokemon = findViewById(R.id.resposta_quiz);
    }

    private void renderPokemon(Pokemon pokemon){
        this.pokemonName = pokemon.pokeName;
        Picasso.get()
                .load(pokemon.imageSpriteUrl)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        originalBitmap = bitmap;
                        img_pokemonQuiz.setImageBitmap(ImageHelper.convertToBlack(bitmap));
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                        Toast.makeText(getBaseContext(), "ERRO: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {
                        Toast.makeText(getBaseContext(), "CARREGANDO", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void quessPokemon(View v){
        String pokeName = txt_pokemon.getText().toString();

        if(!pokeName.equals(pokemonName)){
            Toast.makeText(this, pokeName + "Wrong, was " + pokemonName , Toast.LENGTH_LONG).show();
            return;
        }

        Toast.makeText(this, "Congretulations!!!", Toast.LENGTH_LONG).show();
        showRealPokemonImage();
    }

    private void showRealPokemonImage(){
        img_pokemonQuiz.setImageBitmap(originalBitmap);
    }

    public void onLoadRandomPokemon(Pokemon pokemon) {
        runOnUiThread(() -> renderPokemon(pokemon) );
    }

    public void onFailRandomPokemon(Exception exception) {
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
    }
}