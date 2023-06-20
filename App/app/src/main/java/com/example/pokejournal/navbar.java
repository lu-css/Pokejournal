package com.example.pokejournal;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class navbar extends AppCompatActivity {

    private ImageView botao_inicio;
    private TextView texto_inicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navbar);
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