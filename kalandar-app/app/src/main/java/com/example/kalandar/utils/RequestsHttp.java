package com.example.kalandar.utils;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.owlike.genson.Genson;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestsHttp extends Activity{

    private static final String uri = "http://10.0.2.2:8080/kalandar_api_war/rest/rdv";

    private String type;
    private String id;
    private String dataJson;

    private String server_response;

    public RequestsHttp(String type, String id, String dataJson){

        this.type=type;
        this.id=id;
        this.dataJson=dataJson;
    }

    public RequestsHttp(String type, String dataJson){

        this.type=type;
        this.id=null;
        this.dataJson=dataJson;
    }

    public RequestsHttp(String type){
        this.type=type;
    }

    public String getServer_response() {
        return server_response;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getDataJson() {
        return dataJson;
    }

    public void requestGetAll(){
        URL url;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL(uri);
            urlConnection = (HttpURLConnection) url.openConnection();
            // urlConnection.setRequestProperty("Content-Type", "application/json");

            int responseCode = urlConnection.getResponseCode();


            if(responseCode == HttpURLConnection.HTTP_OK){
                server_response = readStream(urlConnection.getInputStream());

                // JSONArray json = new JSONArray(server_response);

                //Log.e("Response", "" + server_response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } /*catch (JSONException e) {
            e.printStackTrace();
        }*/
    }

    public void requestPost(){
        URL url;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL(uri);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setDoOutput(true);

            String jsonInputString = this.dataJson;
            try(OutputStream os = urlConnection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = urlConnection.getResponseCode();
            // Log.e("Response", "responseCode : " + responseCode);
            if(responseCode == HttpURLConnection.HTTP_OK){
                server_response = readStream(urlConnection.getInputStream());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }

}
