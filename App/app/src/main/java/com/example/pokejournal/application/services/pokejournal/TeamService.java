package com.example.pokejournal.application.services.pokejournal;

import com.example.pokejournal.adapters.PokeJournalAdapter;
import com.example.pokejournal.domain.entities.pokejournal.PokemonTeam;
import com.example.pokejournal.domain.exceptions.HttpRequestException;
import com.example.pokejournal.domain.exceptions.MalformedException;
import com.example.pokejournal.domain.exceptions.NotFoundException;
import com.example.pokejournal.domain.usecases.pokejournal.TeamUsecase;
import com.example.pokejournal.infra.pokejournal.PokeJournalAPI;

import java.io.IOException;
import java.util.List;
public class TeamService implements TeamUsecase
{
    private final PokeJournalAdapter _pokejournal;
    public TeamService(String token){
        _pokejournal = new PokeJournalAPI(token);
    }
    @Override
    public List<PokemonTeam> ListTeams() throws MalformedException, NotFoundException, IOException, HttpRequestException {
        // TODO: Get user id by Preferences.
        String userId = "";
       return _pokejournal.ListTeams(userId);
    }

    @Override
    public PokemonTeam ShowTeam(String teamId) throws MalformedException, NotFoundException, HttpRequestException, IOException {
        return _pokejournal.ShowTeam(teamId);
    }

    @Override
    public PokemonTeam CreateTeam(PokemonTeam team) throws MalformedException, NotFoundException, HttpRequestException, IOException {
        return _pokejournal.CreateTeam(team);
    }

    @Override
    public void DeleteTeam(String teamId) throws MalformedException, NotFoundException, HttpRequestException, IOException {
        _pokejournal.DeleteTeam(teamId);
    }

    @Override
    public PokemonTeam UpdateTeam(String teamId, String name, String description) {
        return null;
    }
}
