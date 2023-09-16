package com.example.pokejournal.fetchers.pokeapi;

import android.os.Handler;
import android.os.HandlerThread;

import com.example.pokejournal.models.Pokemon;
import com.example.pokejournal.requests.PokemonUtil;

public class FetchSearchPokemon {
    public interface OnFetchSearchPokemon{
        void onPokemonSearchFinish(Pokemon pokemon);
        void onPokemonSearchFail(Exception e);
    }

    private final OnFetchSearchPokemon _listener;
    private final HandlerThread _thread;

    public FetchSearchPokemon(OnFetchSearchPokemon listener)
    {
        _listener = listener;
        _thread = new HandlerThread("SearchPokemonThread");
    }

    public void Execute(String pokemonQuery)
    {
        _thread.start();
        Handler handler = new Handler(_thread.getLooper());
        handler.post(() -> {
            try{
                Pokemon pokemon = PokemonUtil.getPokemonCard(pokemonQuery);
                _listener.onPokemonSearchFinish(pokemon);
            } catch (Exception e){
                _listener.onPokemonSearchFail(e);
            }
            _thread.quit();
        });
    }
}
