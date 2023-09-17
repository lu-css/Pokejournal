package com.example.pokejournal.fetchers.pokeapi;

import android.os.Handler;
import android.os.HandlerThread;

import com.example.pokejournal.models.Pokemon;
import com.example.pokejournal.requests.PokemonUtil;

public class FetchRandomPokemon {
    public interface OnFetchRandomPokemon{
        void onLoadRandomPokemon(Pokemon pokemon);
        void onFailRandomPokemon(Exception exception);
    }

    private final OnFetchRandomPokemon _listener;
    private final HandlerThread _thread;

    public FetchRandomPokemon(OnFetchRandomPokemon listener){
        _listener = listener;
        _thread = new HandlerThread("RandomPokemonFetcher");
    }

    public void Execute(){
        _thread.start();
        Handler handler = new Handler(_thread.getLooper());
        handler.post(() -> {
            try{
                Pokemon pokemon = PokemonUtil.getRandomPokemon();
                _listener.onLoadRandomPokemon(pokemon);
            } catch (Exception e){
                _listener.onFailRandomPokemon(e);
            } finally {
                _thread.quit();
            }
        });
    }
}
