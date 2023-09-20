package com.example.pokejournal.application.services.pokejournal;

import androidx.annotation.Nullable;

import com.example.pokejournal.adapters.PokeJournalAdapter;
import com.example.pokejournal.domain.entities.core.Pokemon;
import com.example.pokejournal.domain.exceptions.HttpRequestException;
import com.example.pokejournal.domain.exceptions.MalformedException;
import com.example.pokejournal.domain.exceptions.NotFoundException;
import com.example.pokejournal.domain.usecases.pokejournal.PokemonInTeamUsecase;
import com.example.pokejournal.infra.pokejournal.PokeJournalAPI;

import java.io.IOException;

public class PokemonInTeamService implements PokemonInTeamUsecase {

    private final PokeJournalAdapter _pokejournal;
    public PokemonInTeamService(String token){
        // TODO: Get token from localstorage.
        _pokejournal = new PokeJournalAPI(token);
    }

    @Override
    public Pokemon addPokemonToTeam(String teamId, int pokemonEntry, @Nullable String customName) throws MalformedException, NotFoundException, HttpRequestException, IOException {
        return _pokejournal.addPokemonToTeam(teamId, pokemonEntry, customName);
    }

    @Override
    public Pokemon removePokemonFromTeam(String pokemonId) {
        // Não implementado.
        return null;
    }

    @Override
    public Pokemon renamePokemonFromTeam(String pokemonId, String newName) {
        // Não implementado.
        return null;
    }
}
