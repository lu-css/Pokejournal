package com.example.pokejournal.application.services.pokejournal;

import com.example.pokejournal.adapters.PokeJournalAdapter;
import com.example.pokejournal.domain.entities.core.Pokemon;
import com.example.pokejournal.domain.exceptions.HttpRequestException;
import com.example.pokejournal.domain.exceptions.MalformedException;
import com.example.pokejournal.domain.exceptions.NotFoundException;
import com.example.pokejournal.domain.usecases.pokejournal.FavoritePokemonUsecase;
import com.example.pokejournal.infra.pokejournal.PokeJournalAPI;

import java.io.IOException;
import java.util.List;

public class FavoritePokemonService implements FavoritePokemonUsecase {

    private final PokeJournalAdapter _pokejournal;
    public FavoritePokemonService(String token){
        // TODO: Get token from localstorage.
        _pokejournal = new PokeJournalAPI(token);
    }
    @Override
    public List<Pokemon> showFavoritePokemons(String userId) throws MalformedException, NotFoundException, HttpRequestException, IOException {
        return _pokejournal.showFavoritePokemons(userId);
    }

    @Override
    public Pokemon favoritePokemon(int pokedexEntry) throws MalformedException, NotFoundException, HttpRequestException, IOException {
        return _pokejournal.favoritePokemon(pokedexEntry);
    }

    @Override
    public Pokemon unfavoritePokemon(int pokedexEntry) throws MalformedException, NotFoundException, HttpRequestException, IOException {
        return _pokejournal.unfavoritePokemon(pokedexEntry);
    }
}
