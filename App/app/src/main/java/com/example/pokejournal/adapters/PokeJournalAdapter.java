package com.example.pokejournal.adapters;

import androidx.annotation.Nullable;

import com.example.pokejournal.domain.entities.core.Pokemon;
import com.example.pokejournal.domain.entities.pokejournal.PokemonTeam;
import com.example.pokejournal.domain.entities.pokejournal.User;
import com.example.pokejournal.domain.exceptions.HttpRequestException;
import com.example.pokejournal.domain.exceptions.MalformedException;
import com.example.pokejournal.domain.exceptions.NotFoundException;

import java.io.IOException;
import java.util.List;

public interface PokeJournalAdapter {
    List<PokemonTeam> ListTeams(String userId) throws NotFoundException, MalformedException, IOException, HttpRequestException;
    PokemonTeam ShowTeam(String teamId) throws NotFoundException, MalformedException, IOException, HttpRequestException;
    PokemonTeam CreateTeam(PokemonTeam team) throws NotFoundException, MalformedException, IOException, HttpRequestException;
    void DeleteTeam(String teamId) throws NotFoundException, MalformedException, IOException, HttpRequestException;
    PokemonTeam UpdateTeam(String teamId, String name, String description) throws NotFoundException, MalformedException, IOException;
    List<Pokemon> showFavoritePokemons(String userId) throws NotFoundException, MalformedException, IOException, HttpRequestException;
    Pokemon favoritePokemon(int pokedexEntry) throws NotFoundException, MalformedException, IOException, HttpRequestException;
    Pokemon unfavoritePokemon(int pokedexEntry) throws NotFoundException, MalformedException, IOException, HttpRequestException;
    Pokemon addPokemonToTeam(String teamId, int pokemonEntry, @Nullable String customName) throws NotFoundException, MalformedException, IOException, HttpRequestException;
    Pokemon removePokemonFromTeam(String pokemonId) throws NotFoundException, MalformedException, IOException;
    Pokemon renamePokemonFromTeam(String pokemonId, String newName) throws NotFoundException, MalformedException, IOException;
    User registerUser(String username, String email, String password) throws NotFoundException, MalformedException, IOException, HttpRequestException;
    User login(String email, String password) throws NotFoundException, MalformedException, IOException, HttpRequestException;
}
