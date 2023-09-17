package com.example.pokejournal.domain.exceptions;

public class NotFoundException extends Exception {
    public NotFoundException(String msg){
        super(msg);
    }
}
