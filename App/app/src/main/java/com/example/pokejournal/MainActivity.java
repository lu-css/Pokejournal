package com.example.pokejournal;

import static com.example.pokejournal.PokemonUtil.getPokemon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private TextView txtTeste;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtTeste = findViewById(R.id.txtTeste2);
    }
    public void pokeSearch(View view) throws IOException, Exception {
        Pokemon teste = getPokemon("1");
        txtTeste.setText(teste.pokeName);
    }
}