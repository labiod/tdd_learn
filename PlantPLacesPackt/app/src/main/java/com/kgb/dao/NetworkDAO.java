package com.kgb.dao;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkDAO {
    public String fetch(String uri) throws IOException {
        StringBuilder sb = new StringBuilder();

        URL url = new URL(uri);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream inupt = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bin = new BufferedReader(new InputStreamReader(inupt));
            String inputLine;

            while ((inputLine = bin.readLine()) != null) {
                sb.append(inputLine);
            }
        } finally {
            urlConnection.disconnect();
        }
        return sb.toString();
    }
}
