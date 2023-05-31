package com.example.pokejournal;

import android.net.Uri;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PokeRequest {
    public static JSONObject get(String url) throws IOException, Exception  {
        Uri uri = Uri.parse(url);
        URL buildedUrl = new URL(uri.toString());

        HttpURLConnection urlConnection = (HttpURLConnection) buildedUrl.openConnection();

        urlConnection.connect();

        JSONObject response = buildresponse(urlConnection);

        urlConnection.disconnect();

        return response;
    }
    private static JSONObject buildresponse(HttpURLConnection connection) throws IOException, Exception {
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
