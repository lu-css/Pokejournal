package com.example.pokejournal.application.fetchers.pokeapi;

import android.os.Handler;
import android.os.HandlerThread;

import com.example.pokejournal.application.services.pokeapi.SinglePokemonService;
import com.example.pokejournal.domain.entities.core.Pokemon;
import com.example.pokejournal.domain.usecases.pokeapi.SinglePokemonUsecase;

import java.util.List;

public class ListPokemonFetcher
{
    private final SinglePokemonUsecase _service = new SinglePokemonService();
    @FunctionalInterface
    public interface OnEach { void call(Pokemon pokemon); }
    @FunctionalInterface
    public interface OnFail{ void call(Exception e); }
    private final OnEach _success;
    private final OnFail _fail;

    public ListPokemonFetcher(OnEach success, OnFail fail)
    {
        _success = success;
        _fail = fail;
    }

    public void execute(List<String> pokemonQueries)
    {
        HandlerThread thread = new HandlerThread(this.getClass().getName() + "Thread");
        thread.start();
        Handler handler = new Handler(thread.getLooper());

        handler.post(() -> {
            process(pokemonQueries);
            thread.quit();
        });
    }

    private void process(List<String> pokemonQueries){
        for(String pokemonQuery : pokemonQueries){
            try{
                Pokemon pokemon = _service.pokemonFromQuery(pokemonQuery);
                _success.call(pokemon);
            } catch (Exception e){
                _fail.call(e);
            }
        }
    }
}
