package com.example.pokejournal.adapters;

import com.example.pokejournal.domain.entities.core.Pokemon;
import com.example.pokejournal.domain.exceptions.MalformedException;
import com.example.pokejournal.domain.exceptions.NotFoundException;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public interface PokeApiAdapter {
    Pokemon pokemonFromQuery(String query) throws NotFoundException, MalformedException, IOException;
    Pokemon randomPokemon() throws NotFoundException, MalformedException, IOException;
    List<Pokemon> evolutionChain(String query) throws NotFoundException, MalformedException, IOException;
}
