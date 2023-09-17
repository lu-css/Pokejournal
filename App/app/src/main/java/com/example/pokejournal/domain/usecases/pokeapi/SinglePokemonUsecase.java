package com.example.pokejournal.domain.usecases.pokeapi;

import com.example.pokejournal.domain.entities.core.Pokemon;
import com.example.pokejournal.domain.exceptions.MalformedException;
import com.example.pokejournal.domain.exceptions.NotFoundException;

import java.io.IOException;
import java.util.List;

public interface SinglePokemonUsecase {
    Pokemon pokemonFromQuery(String query) throws MalformedException, NotFoundException, IOException;
    Pokemon randomPokemon() throws MalformedException, NotFoundException, IOException;
    List<Pokemon> evolutionChain(String query) throws MalformedException, NotFoundException, IOException;
}