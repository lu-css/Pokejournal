package com.example.pokejournal.application.fetchers.pokeapi;

import android.os.Handler;
import android.os.HandlerThread;

import com.example.pokejournal.application.services.pokeapi.SinglePokemonService;
import com.example.pokejournal.domain.entities.core.Pokemon;
import com.example.pokejournal.domain.usecases.pokeapi.SinglePokemonUsecase;

public class RandomPokemonFetcher {
    private final SinglePokemonUsecase _service = new SinglePokemonService();
    @FunctionalInterface
    public interface OnFind { void call(Pokemon pokemon); }
    @FunctionalInterface
    public interface OnFail{ void call(Exception e); }
    private final OnFind _find;
    private final OnFail _fail;

    public RandomPokemonFetcher(OnFind find, OnFail fail)
    {
        _find = find;
        _fail = fail;
    }

    public void execute()
    {
        HandlerThread thread = new HandlerThread(this.getClass().getName() + "Thread");
        thread.start();
        Handler handler = new Handler(thread.getLooper());

        handler.post(() -> {
            process();
            thread.quit();
        });
    }

    private void process(){
        try{
            Pokemon pokemon = _service.randomPokemon();
            _find.call(pokemon);
        } catch (Exception e){
            _fail.call(e);
        }
    }
}
