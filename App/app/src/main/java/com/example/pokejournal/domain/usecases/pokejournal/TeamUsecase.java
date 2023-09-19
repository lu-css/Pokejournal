package com.example.pokejournal.domain.usecases.pokejournal;

import com.example.pokejournal.domain.entities.pokejournal.PokemonTeam;
import com.example.pokejournal.domain.exceptions.MalformedException;
import com.example.pokejournal.domain.exceptions.NotFoundException;

import java.io.IOException;
import java.util.List;

public interface TeamUsecase {
    List<PokemonTeam> ListTeams() throws MalformedException, NotFoundException, IOException;
    PokemonTeam ShowTeam(String teamId);
    PokemonTeam CreateTeam(PokemonTeam team);
    void DeleteTeam(String teamId);
    PokemonTeam UpdateTeam(String teamId, String name, String description);
}
