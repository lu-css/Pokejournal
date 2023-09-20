package com.example.pokejournal.application.services.pokejournal;

import com.example.pokejournal.adapters.PokeJournalAdapter;
import com.example.pokejournal.domain.entities.pokejournal.User;
import com.example.pokejournal.domain.exceptions.HttpRequestException;
import com.example.pokejournal.domain.exceptions.MalformedException;
import com.example.pokejournal.domain.exceptions.NotFoundException;
import com.example.pokejournal.domain.usecases.pokejournal.UserUsecase;
import com.example.pokejournal.infra.pokejournal.PokeJournalAPI;

import java.io.IOException;

public class UserService implements UserUsecase
{
    private final PokeJournalAdapter _pokejournal;
    public UserService(){
        _pokejournal = new PokeJournalAPI("");
    }

    @Override
    public User registerUser(String username, String email, String password) throws MalformedException, NotFoundException, HttpRequestException, IOException {
        return _pokejournal.registerUser(username, email, password);
    }

    @Override
    public String login(String email, String password) throws MalformedException, NotFoundException, HttpRequestException, IOException {
        User user = _pokejournal.login(email, password);
        // TODO: Save token in local preferences.
        return user.getToken();
    }
}
