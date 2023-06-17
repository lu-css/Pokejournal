package com.example.requests;

import android.net.Uri;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PokeRequest {
    /**
     * <p> Makes a generic GET request.</p>
     *
     * @return `JSONObject` with the request response.
     */
    public static JSONObject get(String url) throws Exception  {
        Uri uri = Uri.parse(url);

        return get(new URL(uri.toString()));
    }

    public static Optional<JSONObject> getRequest(String url) throws Exception {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(url).build();

        try(Response response = client.newCall(request).execute() ) {
            assert response.body() != null;

            if (response.code() == 404){
                Log.d("HAHAHAHA", "nada");
                return Optional.empty();
            }

            String strResponse =  response.body().string();

            Log.d("POKERESPONSE", strResponse);
             return Optional.of(new JSONObject(strResponse));
        }

    }

    public static JSONObject get(URL url) throws IOException, Exception  {
        Log.d("REQUEST", "STARTED");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

//        throw new Exception("Deus da morte");

        Log.d("REQUEST", "CONNECTION OPENED");

        urlConnection.connect();
        Log.d("REQUEST", "CONNECTED");

        JSONObject response = buildresponse(urlConnection);
        Log.d("REQUEST", "BUILDED RESPONSE");

        urlConnection.disconnect();
        Log.d("REQUEST", "DISCONNECTED");

        return response;
    }

    /**
     * <p> Builds the response into a `JSONObject`. </p>
     * @params connection A oppened `HttpURLConnection` object.
     *
     * @return The Builded response.
     */
    private static JSONObject buildresponse(HttpURLConnection connection) throws Exception {
        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder builder = new StringBuilder();
        String buffer;

        while ((buffer = reader.readLine()) != null) {
            builder.append(buffer);
        }

        String response = builder.toString();

        reader.close();

        return new JSONObject(response);
    }
}