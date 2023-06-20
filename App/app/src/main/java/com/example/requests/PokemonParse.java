package com.example.requests;

import com.example.models.Pokemon;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Optional;

public class PokemonParse {

    public static Pokemon parsePokemonCardInfos(JSONObject json) throws Exception{
        Pokemon pokemon = new Pokemon();
        String pokedexEntry = String.valueOf(json.getInt("id"));

        pokemon.pokeName = json.getString("name");
        pokemon.pokedexEntry = pokedexEntry;

        pokemon.imageSpriteUrl = json.getJSONObject("sprites").getJSONObject("other").getJSONObject("official-artwork").getString("front_default");
        pokemon.types = getTypes(json);

        return pokemon;
    }

    public static Pokemon parseFullPokemon(JSONObject json) throws Exception{
        Pokemon pokemon = parsePokemonCardInfos(json);
        String pokedexEntry = String.valueOf(json.getInt("id"));

        // Gets the Species response.
        JSONObject species = getSpecies(pokedexEntry);

        pokemon.description = PokemonUtil.getPokemonFlavorText(species);
        pokemon.pokemonColor = species.getJSONObject("color").getString("name");

        JSONObject evolutionChain = getEvolutionChain(species);

        pokemon.evolutionChain = getAllEvolutions(evolutionChain);

        return pokemon;
    }

    private static JSONObject getSpecies(String pokedexEntry) throws Exception {
        Optional<JSONObject> opSpecies = PokemonUtil.getPokemonSpecies(pokedexEntry);
        if (!opSpecies.isPresent()){
            throw new Exception("Species not found");
        }
        return opSpecies.get();
    }

    private static ArrayList<String> getTypes(JSONObject json) throws Exception{
        ArrayList<String> strTypes = new ArrayList<>();
        JSONArray types = json.getJSONArray("types");

        for(int i = 0; i < types.length(); i++){
            JSONObject type = types.getJSONObject(i).getJSONObject("type");
            strTypes.add(type.getString("name"));
        }

        return strTypes;
    }

    private static JSONObject getEvolutionChain(JSONObject species) throws Exception{
        String url = species.getJSONObject("evolution_chain").getString("url");
        
        Optional<JSONObject> evolutionChain = PokemonUtil.getLink(url);
        if (!evolutionChain.isPresent()){
            throw new Exception("Evolution Chain not found");
        }
        return evolutionChain.get();
    }

    private static Optional<Pokemon> getPokemonInChain(JSONObject chain){
        try{
            String pokemonName = chain.getJSONObject("species").getString("name");
            return Optional.of(PokemonUtil.getPokemonCard(pokemonName));
        } catch (Exception e){
            return Optional.empty();
        }
    }

    private static ArrayList<Pokemon> getAllEvolutions(JSONObject evolutionChain) throws Exception{
        ArrayList<Pokemon> allEvolutions = new ArrayList<>();

        JSONObject chain = evolutionChain.getJSONObject("chain");

        Optional<Pokemon> pokemon = getPokemonInChain(chain);
        pokemon.ifPresent(allEvolutions::add);

        while (true){
            JSONArray evolvesTo = chain.getJSONArray("evolves_to");

            if(evolvesTo.length() == 0){
                return allEvolutions;
            }

            chain = evolvesTo.getJSONObject(0);

            pokemon = getPokemonInChain(chain);
            pokemon.ifPresent(allEvolutions::add);
        }
    }
}
