package com.example.pokejournal.adapters;

import com.example.pokejournal.domain.exceptions.MalformedException;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Optional;

public interface SimpleRequestAdapter
{
    Optional<JSONObject> simpleGet(String url) throws IOException;
}
