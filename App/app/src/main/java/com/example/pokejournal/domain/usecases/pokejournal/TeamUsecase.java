package com.example.pokejournal.domain.usecases.pokejournal;

import com.example.pokejournal.domain.entities.pokejournal.PokemonTeam;
import com.example.pokejournal.domain.exceptions.HttpRequestException;
import com.example.pokejournal.domain.exceptions.MalformedException;
import com.example.pokejournal.domain.exceptions.NotFoundException;

import java.io.IOException;
import java.util.List;

public interface TeamUsecase {
    List<PokemonTeam> ListTeams() throws MalformedException, NotFoundException, IOException, HttpRequestException;
    PokemonTeam ShowTeam(String teamId) throws MalformedException, NotFoundException, HttpRequestException, IOException;
    PokemonTeam CreateTeam(PokemonTeam team) throws MalformedException, NotFoundException, HttpRequestException, IOException;
    void DeleteTeam(String teamId) throws MalformedException, NotFoundException, HttpRequestException, IOException;
    PokemonTeam UpdateTeam(String teamId, String name, String description);
}
