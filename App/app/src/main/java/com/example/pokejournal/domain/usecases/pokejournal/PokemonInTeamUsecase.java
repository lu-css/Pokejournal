package com.example.pokejournal.domain.usecases.pokejournal;

import androidx.annotation.Nullable;

import com.example.pokejournal.domain.entities.core.Pokemon;

public interface PokemonInTeamUsecase {
    Pokemon addPokemonToTeam(int pokemonEntry, @Nullable String customName);
    Pokemon removePokemonFromTeam(String pokemonId);
    Pokemon renamePokemonFromTeam(String pokemonId, String newName);
}
