package com.example.pokejournal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.helpers.ImageHelper;
import com.example.models.Pokemon;
import com.example.requests.PokemonUtil;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class QuizActivity extends AppCompatActivity  implements LoaderManager.LoaderCallbacks<QuizModel> {
    private static final int SEARCH_POKEMON_LOADER_ID = 0;

    private ImageView img_pokemonQuiz;
    private EditText txt_pokemon;
    private String pokemonName;

    private ImageView img_menu2;

    private Bitmap originalBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        loadViews();
        startLoaders();
    }

    private void loadViews(){
        img_pokemonQuiz = findViewById(R.id.pokemon_quiz_img);
        txt_pokemon = findViewById(R.id.resposta_quiz);
    }

    private void startLoaders(){
        LoaderManager loaderManager = LoaderManager.getInstance(this);
        loaderManager.initLoader(SEARCH_POKEMON_LOADER_ID, null, this);
    }

    @NonNull
    @Override
    public Loader<QuizModel> onCreateLoader(int id, @Nullable Bundle args) {
        return new QuizLoader(this, args);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<QuizModel> loader, QuizModel data) {
        if(data.hasError()){
            Toast.makeText(this, data.errorMessage, Toast.LENGTH_SHORT).show();
            return;
        }

        renderPokemon(data.pokemon);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<QuizModel> loader) { }

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

}

class QuizModel{
    public Pokemon pokemon;
    public final String errorMessage;
    private boolean hasError = false;

    public QuizModel(){
        this.errorMessage = "";
        this.hasError = false;
    }

    public QuizModel(Pokemon pokemon){
        this.errorMessage = "";
        this.hasError = false;
        this.pokemon = pokemon;
    }

    public QuizModel(String errorMessage){
        this.errorMessage = errorMessage;
        this.hasError = true;
    }

    public boolean hasError(){
        return hasError;
    }

}
class QuizLoader extends AsyncTaskLoader<QuizModel> {
    public QuizLoader(@NonNull Context context, Bundle args) {
        super(context);
    }

    @Override
    protected void onStartLoading(){
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public QuizModel loadInBackground() {
        try{
            Pokemon pokemon = PokemonUtil.getRandomPokemon();
            return new QuizModel(pokemon);
        }
        catch (Exception e){
            e.printStackTrace();
            return new QuizModel("ERRO: " + e.getMessage());
        }
    }
}


