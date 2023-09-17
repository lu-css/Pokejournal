package com.example.pokejournal.domain.usecases.pokejournal;

import com.example.pokejournal.domain.entities.pokejournal.PokemonTeam;

import java.util.List;

public interface TeamUsecase {
    List<PokemonTeam> ListTeams();
    PokemonTeam ShowTeam(String teamId);
    PokemonTeam CreateTeam(PokemonTeam team);
    void DeleteTeam(String teamId);
    PokemonTeam UpdateTeam(String teamId, String name, String description);
}
