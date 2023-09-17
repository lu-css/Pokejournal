package com.example.pokejournal.domain.usecases.pokejournal;

import com.example.pokejournal.domain.entities.core.Pokemon;

import java.util.List;

public interface FavoritePokemonUsecase {
    List<Pokemon> showFavoritePokemons();
    Pokemon favoritePokemon(int pokedexEntry);
    Pokemon unfavoritePokemon(String pokemonId);
}
