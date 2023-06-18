package com.example.helpers;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.pokejournal.R;
import com.example.pokejournal.detalhePokemonActivity;

public class ActivityHelper {
    public static final String INTENT_POKEMON_ID = "POKEMON_ID";
    public static int getPokemonTypeImage(String type){
        switch (type){
            case("normal"):
                return R.drawable.normal;
            case("fighting"):
                return R.drawable.lutador;
            case("poison"):
                return R.drawable.venenoso;
            case("ground"):
                return R.drawable.terrestre;
            case("rock"):
                return R.drawable.pedra;
            case("bug"):
                return R.drawable.inseto;
            case("ghost"):
                return R.drawable.fantasma;
            case("steel"):
                return R.drawable.aco;
            case("fire"):
                return R.drawable.fogo;
            case("water"):
                return R.drawable.agua;
            case("grass"):
                return R.drawable.planta;
            case("electric"):
                return R.drawable.eletrico;
            case("psychic"):
                return R.drawable.psiquico;
            case("ice"):
                return R.drawable.gelo;
            case("dragon"):
                return R.drawable.dragao;
            case("dark"):
                return R.drawable.dark;
            case("fairy"):
                return R.drawable.fada;
            case("unknown"):
                return R.drawable.normal;
            case("shadow"):
                return R.drawable.dark;
        }

        return -1;
    }

    public static int getPokemonColor(String color){
        Log.d("POKEMON_COLOR", color);
        switch (color){
            case ("blue"):
                return R.color.pokemon_blue;

            case ("brown"):
                return R.color.pokemon_brown;

            case ("gray"):
                return R.color.pokemon_gray;

            case ("green"):
                return R.color.pokemon_green;

            case ("pink"):
                return R.color.pokemon_pink;

            case ("purple"):
                return R.color.pokemon_purple;

            case ("red"):
                return R.color.pokemon_red;

            case ("white"):
                return R.color.pokemon_white;

            case ("yellow"):
                return R.color.pokemon_blue;
        }

        return R.color.pokemon_black;
    }

    public static Intent getPokemonDetailsIntent(Context context, String pokedexEntry){
        Intent intent = new Intent(context, detalhePokemonActivity.class);
        intent.putExtra(INTENT_POKEMON_ID, pokedexEntry);
        return intent;
    }
}
