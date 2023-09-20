package com.example.pokejournal.domain.usecases.pokejournal;

import androidx.annotation.Nullable;

import com.example.pokejournal.domain.entities.core.Pokemon;
import com.example.pokejournal.domain.exceptions.HttpRequestException;
import com.example.pokejournal.domain.exceptions.MalformedException;
import com.example.pokejournal.domain.exceptions.NotFoundException;

import java.io.IOException;

public interface PokemonInTeamUsecase {
    Pokemon addPokemonToTeam(String teamId, int pokemonEntry, @Nullable String customName) throws MalformedException, NotFoundException, HttpRequestException, IOException;
    Pokemon removePokemonFromTeam(String pokemonId);
    Pokemon renamePokemonFromTeam(String pokemonId, String newName);
}
