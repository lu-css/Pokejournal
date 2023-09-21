package com.example.pokejournal.application.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pokejournal.R;
import com.example.pokejournal.application.fetchers.Fetcher;
import com.example.pokejournal.application.services.pokejournal.UserService;
import com.example.pokejournal.domain.entities.pokejournal.User;
import com.example.pokejournal.domain.usecases.pokejournal.UserUsecase;
public class Cadastro extends AppCompatActivity {
    EditText username, password, repassword;
    private final UserUsecase _service = new UserService();
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
        String username = this.username.getText().toString();
        String pass = password.getText().toString();
        String repass = repassword.getText().toString();

        if(username.equals("") ||pass.equals("") || repass.equals("")){
            Toast.makeText(Cadastro.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!pass.equals(repass)) {
            Toast.makeText(Cadastro.this, "Senhas não correspondem", Toast.LENGTH_SHORT).show();
            return;
        }

        Fetcher.async(() -> {
            try{
                User user = _service.registerUser("", username, pass);
                onRegisterUser(user);
            } catch (Exception e){
                Toast.makeText(Cadastro.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onRegisterUser(User user){
        runOnUiThread(() -> {
            Toast.makeText(this, "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });
    }
}