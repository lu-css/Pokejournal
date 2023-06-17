package com.example.pokejournal;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class t_deta_bulba extends AppCompatActivity {
    private ImageView icMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tdeta_bulba);

        icMenu = findViewById(R.id.img_menu);
        icMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bulbaIntent = new Intent(t_deta_bulba.this, navbar.class);
                startActivity(bulbaIntent);
            }
        });


    }
}