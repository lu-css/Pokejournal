package com.example.pokejournal.application.fetchers.pokeapi;

import android.os.Handler;
import android.os.HandlerThread;

import com.example.pokejournal.application.services.pokeapi.SinglePokemonService;
import com.example.pokejournal.domain.entities.core.Pokemon;
import com.example.pokejournal.domain.exceptions.MalformedException;
import com.example.pokejournal.domain.exceptions.NotFoundException;
import com.example.pokejournal.domain.usecases.pokeapi.SinglePokemonUsecase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class SearchPokemonFetcher
{
    private final SinglePokemonUsecase _service = new SinglePokemonService();
    @FunctionalInterface
    public interface OnFind { void call(Pokemon pokemon); }
    @FunctionalInterface
    public interface OnFail{ void call(Exception e); }
    @FunctionalInterface
    public interface WithEvolutionChain{ void call(Pokemon pokemon); }
    private final OnFail _fail;
    private Optional<OnFind> _find = Optional.empty();
    private Optional<WithEvolutionChain> _evolutionChain = Optional.empty();
    private String query;

    public SearchPokemonFetcher(OnFail fail)
    {
        _fail = fail;
    }

    public SearchPokemonFetcher useEvolutionChain(WithEvolutionChain chain){
        this._evolutionChain = Optional.of(chain);
        return this;
    }

    public SearchPokemonFetcher useDefault(OnFind find){
        _find = Optional.of(find);
        return this;
    }

    public void execute(String query)
    {
        this.query = query;
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
            Pokemon pokemon = _service.pokemonFromQuery(query);

            if(_evolutionChain.isPresent()){
                withEvolutionChain(pokemon);
            }

            _find.ifPresent(onFind -> onFind.call(pokemon));
        } catch (Exception e){
            _fail.call(e);
        }
    }

    private void withEvolutionChain(Pokemon pokemon) throws MalformedException, NotFoundException, IOException {
        pokemon.evolutionChain = (ArrayList<Pokemon>) _service.evolutionChain(query);
        _evolutionChain.get().call(pokemon);
    }
}
