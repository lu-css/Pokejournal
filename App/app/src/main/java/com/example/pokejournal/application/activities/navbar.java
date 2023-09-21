package com.example.pokejournal.application.activities;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pokejournal.R;

public class navbar extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navbar);
    }

    private void openAcivity(Class<?> c){
        Intent intent = new Intent (navbar.this, c);
        startActivity(intent);
    }

    public void goCadastro(View v){
        openAcivity(Cadastro.class);
    }

    public void goTelaInicial(View v){
        openAcivity(tela_inicial.class);
    }

    public void goFavoritos(View v){
        openAcivity(Favoritos.class);
    }

    public void goQuiz(View v){
        openAcivity(QuizActivity.class);
    }
}