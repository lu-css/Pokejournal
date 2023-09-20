package com.example.pokejournal.domain.usecases.pokejournal;

import com.example.pokejournal.domain.entities.core.Pokemon;
import com.example.pokejournal.domain.exceptions.HttpRequestException;
import com.example.pokejournal.domain.exceptions.MalformedException;
import com.example.pokejournal.domain.exceptions.NotFoundException;

import java.io.IOException;
import java.util.List;

public interface FavoritePokemonUsecase {
    List<Pokemon> showFavoritePokemons(String usreId) throws MalformedException, NotFoundException, HttpRequestException, IOException;
    Pokemon favoritePokemon(int pokedexEntry) throws MalformedException, NotFoundException, HttpRequestException, IOException;
    Pokemon unfavoritePokemon(int pokedexEntry) throws MalformedException, NotFoundException, HttpRequestException, IOException;
}
