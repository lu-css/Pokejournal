package com.example.pokejournal.application.fetchers.pokejournal;

import android.os.Handler;
import android.os.HandlerThread;

import com.example.pokejournal.application.services.pokejournal.FavoritePokemonService;
import com.example.pokejournal.application.services.pokejournal.UserService;
import com.example.pokejournal.domain.entities.core.Pokemon;
import com.example.pokejournal.domain.entities.pokejournal.User;
import com.example.pokejournal.domain.usecases.pokejournal.FavoritePokemonUsecase;
import com.example.pokejournal.domain.usecases.pokejournal.UserUsecase;

import java.util.List;

public class UserFetcher {
    private final UserUsecase _service;
    @FunctionalInterface
    public interface OnRegister { void call(User user); }
    @FunctionalInterface
    public interface OnLogin { void call(String token); }
    @FunctionalInterface
    public interface OnFail{ void call(Exception e); }
    private final OnFail _fail;

    public UserFetcher(OnFail fail)
    {
        String token = "";
        _service = new UserService();
        _fail = fail;
    }

    public void register(String username, String email, String password, OnRegister func)
    {
        HandlerThread thread = new HandlerThread(this.getClass().getName() + "RegisterThread");
        thread.start();
        Handler handler = new Handler(thread.getLooper());

        handler.post(() -> {
            try {
                User user = _service.registerUser(username, email, password);
                func.call(user);
            }catch (Exception e){
                _fail.call(e);
            }
            thread.quit();
        });
    }

    public void login(String email, String password, OnLogin func)
    {
        HandlerThread thread = new HandlerThread(this.getClass().getName() + "LoginThread");
        thread.start();
        Handler handler = new Handler(thread.getLooper());

        handler.post(() -> {
            try {
                String token = _service.login(email, password);
                func.call(token);
            } catch (Exception e){
                _fail.call(e);
            }
            thread.quit();
        });
    }
}
