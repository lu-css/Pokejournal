package com.example.pokejournal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

import com.example.models.Pokemon;
import com.example.requests.PokemonUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class tela_inicial extends AppCompatActivity implements LoaderManager.LoaderCallbacks<TelaInicialModel> {

    private ImageView img_bulbB;
    private ArrayList<Pokemon> pokemonCards = new ArrayList<>();
    private ArrayAdapter<Pokemon> adapter;
    public static String INTENT_POKEMON_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        adapter = new ArrayAdapter<Pokemon>(this, R.layout.pokemon_card, pokemonCards) {
            @Override
            public View getView(int pos, View convertView, ViewGroup parent){
                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.pokemon_card, parent, false);
                }

                Pokemon pokemon = getItem(pos);

                TextView txt_pokemonName = convertView.findViewById(R.id.pokemon_name);
                TextView txt_pokemonNumber = convertView.findViewById(R.id.pokemon_number);

                txt_pokemonName.setText(pokemon.pokeName);
                txt_pokemonNumber.setText(pokemon.pokedexEntry);

                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(), detalhePokemonActivity.class);
                        intent.putExtra(INTENT_POKEMON_ID, pokemon.pokedexEntry);
                        startActivity(intent);
                    }
                });

                return convertView;
            }
        };

        GridView gridView = findViewById(R.id.pokemonCardsList);

        gridView.setAdapter(adapter);
//
//        if (getSupportLoaderManager().getLoader(0) != null) {
//            getSupportLoaderManager().initLoader(0, null, this);
//        }
//
        Bundle query = new Bundle();
//
        if (getSupportLoaderManager().getLoader(0) == null) {
        }
        getSupportLoaderManager().initLoader(0, query, this);

        Log.d("TUE é o CARALHO","b");
    }

    private void addPokemonCard(Pokemon pokemon){
        pokemonCards.add(pokemon);
        adapter.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Loader<TelaInicialModel> onCreateLoader(int id, @Nullable Bundle args) {
        return new TelaInicialLoader(this, args);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<TelaInicialModel> loader, TelaInicialModel data) {

        if(data.hasError()){
            Toast.makeText(this, data.errorMessage,Toast.LENGTH_SHORT).show();
            return;
        }

        for(int i = 0; i < data.pokemons.size(); i++){
            Pokemon pokemon = data.pokemons.get(i);

            addPokemonCard(pokemon);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<TelaInicialModel> loader) { }
}

class TelaInicialModel{
    public final ArrayList<Pokemon> pokemons;
    public final String errorMessage;

    private boolean hasError = false;
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
    private Bundle args;

    public TelaInicialLoader(@NonNull Context context, Bundle args) {
        super(context);
        this.args = args;

    }

    @Override
    protected void onStartLoading(){
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public TelaInicialModel loadInBackground() {
        Log.d("CARREGANDO SAPORRA", "a");
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
