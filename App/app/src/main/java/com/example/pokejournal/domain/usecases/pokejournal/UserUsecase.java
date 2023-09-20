package com.example.pokejournal.domain.usecases.pokejournal;

import com.example.pokejournal.domain.entities.pokejournal.User;
import com.example.pokejournal.domain.exceptions.HttpRequestException;
import com.example.pokejournal.domain.exceptions.MalformedException;
import com.example.pokejournal.domain.exceptions.NotFoundException;

import java.io.IOException;

public interface UserUsecase {
    User registerUser(String username, String email, String password) throws MalformedException, NotFoundException, HttpRequestException, IOException;
    String login(String email, String password) throws MalformedException, NotFoundException, HttpRequestException, IOException;
}