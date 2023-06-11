package com.example.pokejournal;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class tela_inicial extends AppCompatActivity {

    private ImageView img_bulbB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

    img_bulbB = findViewById(R.id.img_bulba);
    img_bulbB.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(tela_inicial.this,t_deta_bulba.class);
            startActivity(intent);
        }
    });

    }
}