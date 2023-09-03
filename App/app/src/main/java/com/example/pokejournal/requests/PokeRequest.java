package com.example.pokejournal.requests;

import org.json.JSONObject;

import java.util.Optional;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PokeRequest {
    public static Optional<JSONObject> getRequest(String url) throws Exception {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(url).build();

        try(Response response = client.newCall(request).execute() ) {

            if (response.code() != 200){
                return Optional.empty();
            }

            if(response.body() == null){
                return Optional.empty();
            }

            String strResponse =  response.body().string();

             return Optional.of(new JSONObject(strResponse));
        }
    }
}