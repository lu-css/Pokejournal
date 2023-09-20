package com.example.pokejournal.adapters;

import com.example.pokejournal.domain.entities.request.RequestResponse;
import com.example.pokejournal.domain.exceptions.HttpRequestException;
import com.example.pokejournal.domain.exceptions.MalformedException;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Optional;

public interface SimpleRequestAdapter
{
    JSONObject simpleGet(String url) throws IOException, HttpRequestException, MalformedException;
    Optional<JSONObject> simplePost(String url, JSONObject body) throws IOException, HttpRequestException;

    Optional<JSONObject> simpleGetWithBearer(String url, String token) throws IOException, HttpRequestException;
    RequestResponse simpleDelete(String url) throws IOException, HttpRequestException;
    RequestResponse simplePut(String url, JSONObject body) throws IOException, HttpRequestException;

}
