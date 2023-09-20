package com.example.pokejournal.application.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pokejournal.R;
import com.example.pokejournal.application.fetchers.pokejournal.UserFetcher;
import com.example.pokejournal.domain.entities.pokejournal.User;
import com.example.pokejournal.storage.DatabaseHelper;
import android.widget.Toast;
public class Cadastro extends AppCompatActivity {
    EditText username, password, repassword;
    DatabaseHelper DB;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        username = findViewById (R.id.txtUser);
        password = findViewById(R.id.txtPassword);
        repassword = findViewById(R.id.txtRepassword);
        Button signup = findViewById(R.id.btnSingup);

        signup.setOnClickListener(this::onSubmitUser);
    }

    private void onSubmitUser(View v){
        String user = username.getText().toString();
        String pass = password.getText().toString();
        String repass = repassword.getText().toString();

        if(user.equals("") ||pass.equals("") || repass.equals("")){
            Toast.makeText(Cadastro.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!pass.equals(repass)) {
            Toast.makeText(Cadastro.this, "Senhas não correspondem", Toast.LENGTH_SHORT).show();
            return;
        }

        new UserFetcher(
                e -> Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show() )
                .register("", user, pass, this::onRegisterUser);
    }

    private void onRegisterUser(User user){
        runOnUiThread(() -> {
            Toast.makeText(this, "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });
    }
}