package com.example.pokejournal.fetchers.pokeapi;

import android.os.Handler;
import android.os.HandlerThread;

import com.example.pokejournal.models.Pokemon;
import com.example.pokejournal.requests.PokemonUtil;

import java.util.List;

public class FetchPokemonList {
    public interface FetchPokemonListListener {
         void PokemonListNewItem(Pokemon pokemon);
         void PokemonLIstOnFail(Exception exception);
    }

    private final FetchPokemonListListener _listener;
    private final HandlerThread _thread;

    public FetchPokemonList(FetchPokemonListListener listener){

        _listener = listener;
        _thread = new HandlerThread("PokemonListFetcher");
    }

    public void Execute(List<String> pokemonQueries){
        _thread.start();
        Handler handler = new Handler(_thread.getLooper());
        handler.post(() -> {
            for(String pokemonQuery : pokemonQueries){

                try{
                    Pokemon pokemon = PokemonUtil.getPokemonCard(pokemonQuery);
                    _listener.PokemonListNewItem(pokemon);
                } catch (Exception e){
                    _listener.PokemonLIstOnFail(e);
                }
            }

            Stop();
        });
    }

    public void Stop(){
        _thread.quit();
    }
}
