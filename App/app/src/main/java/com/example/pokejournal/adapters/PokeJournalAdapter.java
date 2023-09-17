package com.example.pokejournal.adapters;

import androidx.annotation.Nullable;

import com.example.pokejournal.domain.entities.core.Pokemon;
import com.example.pokejournal.domain.entities.pokejournal.PokemonTeam;
import com.example.pokejournal.domain.entities.pokejournal.User;

import java.util.List;

public interface PokeJournalAdapter {
    List<PokemonTeam> ListTeams();
    PokemonTeam ShowTeam(String teamId);
    PokemonTeam CreateTeam(PokemonTeam team);
    void DeleteTeam(String teamId);
    PokemonTeam UpdateTeam(String teamId, String name, String description);
    List<Pokemon> showFavoritePokemons();
    Pokemon favoritePokemon(int pokedexEntry);
    Pokemon unfavoritePokemon(String pokemonId);
    Pokemon addPokemonToTeam(int pokemonEntry, @Nullable String customName);
    Pokemon removePokemonFromTeam(String pokemonId);
    Pokemon renamePokemonFromTeam(String pokemonId, String newName);
    User registerUser(String username, String email, String password);
    User login(String email, String password);
}
