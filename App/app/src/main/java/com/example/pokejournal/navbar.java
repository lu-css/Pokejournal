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

        botao_inicio = findViewById(R.id.imgInicio);
        texto_inicio = findViewById(R.id.txt_inicio);
        botao_inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inicioIntent = new Intent(navbar.this, tela_inicial.class);
                startActivity(inicioIntent);

            }
        });

        texto_inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iniciooIntent = new Intent(navbar.this, tela_inicial.class);
            }
        });
    }
}