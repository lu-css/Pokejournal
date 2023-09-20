package com.example.pokejournal.domain.exceptions;

public class HttpRequestException extends Exception
{
    private final int statusCode;
    private final String responseBody;
    public HttpRequestException(String msg, int statusCode, String responseBody){
        super(msg);
        this.statusCode = statusCode;
        this.responseBody = responseBody;
    }

    public boolean isSuccess(){
        return statusCode > 200 && statusCode < 300;
    }

    public int getCode(){ return statusCode; }

    public String getBody (){ return responseBody; }
}
