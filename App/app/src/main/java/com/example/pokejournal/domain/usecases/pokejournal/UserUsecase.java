package com.example.pokejournal.domain.usecases.pokejournal;

import com.example.pokejournal.domain.entities.pokejournal.User;

public interface UserUsecase {
    User registerUser(String username, String email, String password);
    User login(String email, String password);
}