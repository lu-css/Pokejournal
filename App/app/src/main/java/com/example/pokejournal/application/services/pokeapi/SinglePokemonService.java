package com.example.pokejournal.application.services.pokeapi;

import com.example.pokejournal.adapters.PokeApiAdapter;
import com.example.pokejournal.domain.entities.core.Pokemon;
import com.example.pokejournal.domain.exceptions.MalformedException;
import com.example.pokejournal.domain.exceptions.NotFoundException;
import com.example.pokejournal.domain.usecases.pokeapi.SinglePokemonUsecase;
import com.example.pokejournal.infra.pokeapi.PokeApi;

import java.io.IOException;
import java.util.List;

public class SinglePokemonService implements SinglePokemonUsecase
{
    private final PokeApiAdapter _pokeapi = new PokeApi();

    @Override
    public Pokemon pokemonFromQuery(String query) throws MalformedException, NotFoundException, IOException {
        // TODO: Add cache
        return _pokeapi.pokemonFromQuery(query);
    }

    @Override
    public Pokemon randomPokemon() throws MalformedException, NotFoundException, IOException {
        return _pokeapi.randomPokemon();
    }

    @Override
    public List<Pokemon> evolutionChain(String query) throws MalformedException, NotFoundException, IOException {
        return _pokeapi.evolutionChain(query);
    }
}
