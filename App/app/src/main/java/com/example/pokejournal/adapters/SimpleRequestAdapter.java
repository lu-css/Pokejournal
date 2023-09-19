package com.example.pokejournal.adapters;

import com.example.pokejournal.domain.entities.request.RequestResponse;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Optional;

public interface SimpleRequestAdapter
{
    Optional<JSONObject> simpleGet(String url) throws IOException;
    Optional<JSONObject> simplePost(String url, JSONObject body) throws IOException;

    Optional<JSONObject> simpleGetWithBearer(String url, String token) throws IOException;
    RequestResponse simpleDelete(String url) throws IOException;
    RequestResponse simplePut(String url, JSONObject body) throws IOException;

}
