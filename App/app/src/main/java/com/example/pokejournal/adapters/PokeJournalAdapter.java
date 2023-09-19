package com.example.pokejournal.adapters;

import androidx.annotation.Nullable;

import com.example.pokejournal.domain.entities.core.Pokemon;
import com.example.pokejournal.domain.entities.pokejournal.PokemonTeam;
import com.example.pokejournal.domain.entities.pokejournal.User;
import com.example.pokejournal.domain.exceptions.MalformedException;
import com.example.pokejournal.domain.exceptions.NotFoundException;

import java.io.IOException;
import java.util.List;

public interface PokeJournalAdapter {
    List<PokemonTeam> ListTeams(String userId) throws NotFoundException, MalformedException, IOException;
    PokemonTeam ShowTeam(String teamId) throws NotFoundException, MalformedException, IOException;
    PokemonTeam CreateTeam(PokemonTeam team) throws NotFoundException, MalformedException, IOException;
    void DeleteTeam(String teamId) throws NotFoundException, MalformedException, IOException;
    PokemonTeam UpdateTeam(String teamId, String name, String description) throws NotFoundException, MalformedException, IOException;
    List<Pokemon> showFavoritePokemons(String userId) throws NotFoundException, MalformedException, IOException;
    Pokemon favoritePokemon(int pokedexEntry) throws NotFoundException, MalformedException, IOException;
    Pokemon unfavoritePokemon(int pokedexEntry) throws NotFoundException, MalformedException, IOException;
    Pokemon addPokemonToTeam(String teamId, int pokemonEntry, @Nullable String customName) throws NotFoundException, MalformedException, IOException;
    Pokemon removePokemonFromTeam(String pokemonId) throws NotFoundException, MalformedException, IOException;
    Pokemon renamePokemonFromTeam(String pokemonId, String newName) throws NotFoundException, MalformedException, IOException;
    User registerUser(String username, String email, String password) throws NotFoundException, MalformedException, IOException;
    User login(String email, String password) throws NotFoundException, MalformedException, IOException;
}
