package com.example.pokejournal.infra.SimpleRequest;

import com.example.pokejournal.adapters.SimpleRequestAdapter;
import com.example.pokejournal.domain.exceptions.MalformedException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Optional;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpSimpleRequest implements SimpleRequestAdapter
{
    @Override
    public Optional<JSONObject> simpleGet(String url) throws IOException  {
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

            try{
                return Optional.of(new JSONObject(strResponse));
            } catch (JSONException e){
                return Optional.empty();
            }
        }
    }
}
