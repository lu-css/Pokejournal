package com.example.pokejournal.fetchers;

import android.os.Handler;
import android.os.HandlerThread;

import com.example.pokejournal.models.Pokemon;
import com.example.pokejournal.requests.PokemonUtil;

import java.util.List;

public class FetchPokemonList {
    public interface FetchPokemonListListener {
         void PokemonListNewItem(Pokemon pokemon);
         void PokemonLIstOnFail(Exception exception);
         void PokemonListFinish();
    }

    private final FetchPokemonListListener _listener;
    private final Handler _handler;

    public FetchPokemonList(Handler handler, FetchPokemonListListener listener){
        _handler = handler;
        _listener = listener;
    }

    private Runnable run(List<String> pokemonQueries){
        return new Runnable() {
            @Override
            public void run() {
                    for(String pokemonQuery : pokemonQueries){
                        try{
                        Pokemon pokemon = PokemonUtil.getPokemonCard(pokemonQuery);
                        _listener.PokemonListNewItem(pokemon);
                        } catch (Exception e){
                            _listener.PokemonLIstOnFail(e);
                        }
                    }
                _listener.PokemonListFinish();
            }
        };
    }

    public void Execute(List<String> pokemonIds){
        _handler.post(run(pokemonIds));
    }
}
