package com.example.pokejournal.application.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pokejournal.R;
import com.example.pokejournal.storage.DatabaseHelper;
import android.widget.Toast;
public class Cadastro extends AppCompatActivity {
    EditText username, password, repassword;
    Button signup;
    DatabaseHelper DB;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        username = findViewById (R.id.txtUser);
        password = findViewById(R.id.txtPassword);
        repassword = findViewById(R.id.txtRepassword);
        signup = findViewById(R.id.btnSingup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                if(user.equals("") ||pass.equals("") || repass.equals(""))
                    Toast.makeText(Cadastro.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkDados(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertDados(user, pass);
                            if(insert==true){
                                Toast.makeText(Cadastro.this, "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(Cadastro.this, "Erro ao logar", Toast.LENGTH_SHORT).show();

                            }
                        }
                        else{
                            Toast.makeText(Cadastro.this, "Usuário já existente", Toast.LENGTH_SHORT).show();

                        }
                    }else{
                        Toast.makeText(Cadastro.this, "Senhas não correspondem", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
    }
}