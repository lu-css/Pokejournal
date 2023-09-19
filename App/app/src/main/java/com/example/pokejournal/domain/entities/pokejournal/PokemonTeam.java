package com.example.pokejournal.domain.entities.pokejournal;

import com.example.pokejournal.domain.entities.core.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class PokemonTeam {
    private String id;
    private String name;
    private String description;
    private List<Pokemon> pokemons;

    public PokemonTeam(String id, String name, String description, List<Pokemon> pokemons) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.pokemons = pokemons;
    }

    public PokemonTeam() {
        this.id = "0";
        this.name = "Nothing";
        this.description = "Empty Description";
        this.pokemons = new ArrayList<>();
    }
}
