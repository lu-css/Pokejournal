package com.example.pokejournal.application.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.pokejournal.R;
import com.example.pokejournal.application.helpers.ActivityHelper;
import com.example.pokejournal.domain.entities.core.Pokemon;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PokemonListAdapter extends ArrayAdapter<Pokemon> {
    public interface GoToPokemonDetail{ void call(Pokemon pokemon); }
    private GoToPokemonDetail onClickListener;
    public PokemonListAdapter(@NonNull Context context, int resource, @NonNull  List<Pokemon> objects, GoToPokemonDetail onclickAction) {
        super(context, resource, objects);
        onClickListener = onclickAction;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent){
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.pokemon_card, parent, false);
        }

        Pokemon pokemon = getItem(pos);

        TextView txt_pokemonName = convertView.findViewById(R.id.pokemon_name);
        TextView txt_pokemonNumber = convertView.findViewById(R.id.pokemon_number);
        ImageView img_pokemon = convertView.findViewById(R.id.img_pokemon);
        ImageView img_pokemonType1 = convertView.findViewById(R.id.pokemon_type_1);
        ImageView img_pokemonType2 = convertView.findViewById(R.id.pokemon_type_2);

        txt_pokemonName.setText(pokemon.pokeName);
        txt_pokemonNumber.setText(pokemon.pokedexEntry);
        Picasso.get().load(pokemon.imageSpriteUrl).into(img_pokemon);

        img_pokemonType1.setVisibility(View.INVISIBLE);
        img_pokemonType2.setVisibility(View.INVISIBLE);

        int t1 = ActivityHelper.getPokemonTypeImage(pokemon.types.get(0));
        if(t1 != -1){
            img_pokemonType1.setImageResource(t1);
            img_pokemonType1.setVisibility(View.VISIBLE);
        }

        if(pokemon.types.size() > 1){
            int t2 = ActivityHelper.getPokemonTypeImage(pokemon.types.get(1));
            if(t2 != -1){
                img_pokemonType2.setImageResource(t2);
                img_pokemonType2.setVisibility(View.VISIBLE);
            }
        }

        convertView.setOnClickListener(view -> {
            onClickListener.call(pokemon);
        });

        return convertView;
    }
}
