package com.example.pokejournal.infra.SimpleRequest;

import com.example.pokejournal.adapters.SimpleRequestAdapter;
import com.example.pokejournal.domain.entities.request.RequestResponse;
import com.example.pokejournal.domain.exceptions.HttpRequestException;
import com.example.pokejournal.domain.exceptions.MalformedException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Optional;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpSimpleRequest implements SimpleRequestAdapter
{
    private final String token;

    public OkHttpSimpleRequest(String token){
        this.token = token;
    }

    @Override
    public JSONObject simpleGet(String url) throws IOException, HttpRequestException, MalformedException {
        OkHttpClient client = new OkHttpClient();

        Request request;
        try{
             request = new Request.Builder().url(url).build();
        } catch (Exception e){
            throw new MalformedException("Connection to PokeJournal API Failed, pleace try again later");
        }

        try(Response response = client.newCall(request).execute() ) {

            if(response.body() == null){
                throw new HttpRequestException("Response without a body", response.code(), "");
            }

            String strResponse = response.body().string();

            if (response.code() != 200){
                throw new HttpRequestException("Something went wrong", response.code(), strResponse);
            }

            try{
                return new JSONObject(strResponse);
            } catch (JSONException e){
                throw new MalformedException("Its not an valid json: " + e.getMessage());
            }
        }
    }

    @Override
    public Optional<JSONObject> simplePost(String url, JSONObject body) throws IOException {
        Request.Builder requestbuilder = new Request.Builder().url(url);

        if(token != null){
            requestbuilder = requestbuilder
                    .addHeader("Authorization", "Bearer " + token);
        }

        RequestBody bodyrequest = jsonToRequestBody(body);

        Request request = requestbuilder
                .post(bodyrequest)
                .build();

        Optional<RequestResponse> req = performRequest(request);

        if(!req.isPresent()){
            return Optional.empty();
        }

        try{
            return Optional.of(new JSONObject(req.get().getBody()));
        } catch (JSONException e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<JSONObject> simpleGetWithBearer(String url, String token) throws IOException {

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + token)
                .build();

        Optional<RequestResponse> response = performRequest(request);

        if(!response.isPresent()){
            return Optional.empty();
        }

        RequestResponse res = response.get();

        try {
            return Optional.of(new JSONObject(res.getBody()));
        } catch (JSONException e){
            return Optional.empty();
        }
    }

    @Override
    public RequestResponse simpleDelete(String url) throws IOException {
        Request.Builder requestbuilder = new Request.Builder()
                .url(url);

        if(token != null){
            requestbuilder = requestbuilder
                    .addHeader("Authorization", "Bearer " + token);
        }

        Request request = requestbuilder
                .delete()
                .build();

        return performRequest(request)
                .orElse(new RequestResponse("Something went wrong", 500));
    }

    @Override
    public RequestResponse simplePut(String url, JSONObject body) throws IOException {
        Request.Builder requestbuilder = new Request.Builder()
                .url(url);

        if(token != null){
            requestbuilder = requestbuilder
                    .addHeader("Authorization", "Bearer " + token);
        }

        RequestBody bodyrequest = jsonToRequestBody(body);

        Request request = requestbuilder
                .put(bodyrequest)
                .build();

        return performRequest(request).orElse(new RequestResponse("Something went wrong", 500));
    }

    private Optional<RequestResponse> performRequest(Request request) throws IOException{
        OkHttpClient client = new OkHttpClient();

        try(Response response = client.newCall(request).execute() ) {

            if (response.code() != 200){
                return Optional.empty();
            }

            if(response.body() == null){
                return Optional.empty();
            }

            String strResponse =  response.body().string();

            return Optional.of(new RequestResponse(strResponse, response.code()));
        }
    }
    private RequestBody jsonToRequestBody(JSONObject json){
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");

        return RequestBody.create(json.toString(), mediaType);
    }
}
