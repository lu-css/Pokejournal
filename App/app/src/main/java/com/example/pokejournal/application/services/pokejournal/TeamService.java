package com.example.pokejournal.application.services.pokejournal;

import com.example.pokejournal.adapters.PokeJournalAdapter;
import com.example.pokejournal.domain.entities.pokejournal.PokemonTeam;
import com.example.pokejournal.domain.usecases.pokejournal.TeamUsecase;
import com.example.pokejournal.infra.pokejournal.PokeJournalAPI;

import java.util.List;
public class TeamService implements TeamUsecase
{
    private final PokeJournalAdapter _pokejournal;
    public TeamService(){
        _pokejournal = new PokeJournalAPI();
    }
    @Override
    public List<PokemonTeam> ListTeams() {
        return _pokejournal.ListTeams();
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
}
