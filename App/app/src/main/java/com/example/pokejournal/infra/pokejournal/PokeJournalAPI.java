package com.example.pokejournal.infra.pokejournal;

import androidx.annotation.Nullable;

import com.example.pokejournal.adapters.PokeJournalAdapter;
import com.example.pokejournal.domain.entities.core.Pokemon;
import com.example.pokejournal.domain.entities.pokejournal.PokemonTeam;
import com.example.pokejournal.domain.entities.pokejournal.User;

import java.util.List;

public class PokeJournalAPI implements PokeJournalAdapter
{
    @Override
    public List<PokemonTeam> ListTeams() {
        return null;
    }

    @Override
    public PokemonTeam ShowTeam(String teamId) {
        return null;
    }

    @Override
    public PokemonTeam CreateTeam(PokemonTeam team) {
        return null;
    }

    @Override
    public void DeleteTeam(String teamId) {

    }

    @Override
    public PokemonTeam UpdateTeam(String teamId, String name, String description) {
        return null;
    }

    @Override
    public List<Pokemon> showFavoritePokemons() {
        return null;
    }

    @Override
    public Pokemon favoritePokemon(int pokedexEntry) {
        return null;
    }

    @Override
    public Pokemon unfavoritePokemon(String pokemonId) {
        return null;
    }

    @Override
    public Pokemon addPokemonToTeam(int pokemonEntry, @Nullable String customName) {
        return null;
    }

    @Override
    public Pokemon removePokemonFromTeam(String pokemonId) {
        return null;
    }

    @Override
    public Pokemon renamePokemonFromTeam(String pokemonId, String newName) {
        return null;
    }

    @Override
    public User registerUser(String username, String email, String password) {
        return null;
    }

    @Override
    public User login(String email, String password) {
        return null;
    }
}
