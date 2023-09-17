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

    public void goCadastro(View v){
        Intent intent = new Intent (navbar.this, Cadastro.class);
        startActivity(intent);
    }

    public void goTelaInicial(View v){
        Intent intent = new Intent(navbar.this, tela_inicial.class);
        startActivity(intent);
    }

    public void goQuiz(View v){
        Intent intent = new Intent(navbar.this, QuizActivity.class);
        startActivity(intent);
    }
}