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

    private ImageView menu;
    private TextView menu2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navbar);

        menu = findViewById(R.id.fundo_fav);
        menu2= findViewById(R.id.txtfavorito);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (navbar.this, Favoritos.class);
                startActivity(intent);
            }
        });

        menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (navbar.this, Favoritos.class);
                startActivity(intent);
            }
        });

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