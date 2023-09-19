package com.example.pokejournal.domain.entities.request;

public class RequestResponse {
    private int statusCode;
    private String body;

    public RequestResponse(String body, int statusCode){
        this.body = body;
        this.statusCode = statusCode;
    }

    public String getBody(){ return body; }
}
