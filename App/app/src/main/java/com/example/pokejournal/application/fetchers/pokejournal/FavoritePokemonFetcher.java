package com.example.pokejournal.application.fetchers.pokejournal;

import android.os.Handler;
import android.os.HandlerThread;

import com.example.pokejournal.application.fetchers.pokeapi.ListPokemonFetcher;
import com.example.pokejournal.application.services.pokeapi.SinglePokemonService;
import com.example.pokejournal.application.services.pokejournal.FavoritePokemonService;
import com.example.pokejournal.domain.entities.core.Pokemon;
import com.example.pokejournal.domain.usecases.pokeapi.SinglePokemonUsecase;
import com.example.pokejournal.domain.usecases.pokejournal.FavoritePokemonUsecase;

import java.util.List;

public class FavoritePokemonFetcher {
    private final FavoritePokemonUsecase _service;
    @FunctionalInterface
    public interface OnList { void call(List<Pokemon> pokemons); }
    @FunctionalInterface
    public interface OnFavorite { void call(int pokedexEntry); }
    @FunctionalInterface
    public interface OnFail{ void call(Exception e); }
    private final OnFail _fail;

    public FavoritePokemonFetcher(OnFail fail)
    {
        String token = "";
        _service = new FavoritePokemonService(token);
        _fail = fail;
    }

    public void listAll(String userId, OnList list)
    {
        HandlerThread thread = new HandlerThread(this.getClass().getName() + "ListThread");
        thread.start();
        Handler handler = new Handler(thread.getLooper());

        handler.post(() -> {
            try {
                List<Pokemon> pokemons = _service.showFavoritePokemons(userId);
                list.call(pokemons);
            }catch (Exception e){
                _fail.call(e);
            }
            thread.quit();
        });
    }

    public void favorite(int pokedexEntry, OnFavorite fav)
    {
        HandlerThread thread = new HandlerThread(this.getClass().getName() + "FavoriteThread");
        thread.start();
        Handler handler = new Handler(thread.getLooper());

        handler.post(() -> {
            try {
                _service.favoritePokemon(pokedexEntry);
                fav.call(pokedexEntry);
            } catch (Exception e){
                _fail.call(e);
            }
            thread.quit();
        });
    }

    public void unfavorite(int pokedexEntry, OnFavorite fav)
    {
        HandlerThread thread = new HandlerThread(this.getClass().getName() + "FavoriteThread");
        thread.start();
        Handler handler = new Handler(thread.getLooper());

        handler.post(() -> {
            try {
                _service.unfavoritePokemon(pokedexEntry);
                fav.call(pokedexEntry);
            } catch (Exception e){
                _fail.call(e);
            }
            thread.quit();
        });
    }
}
