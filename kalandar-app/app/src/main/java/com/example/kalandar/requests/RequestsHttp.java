package com.example.kalandar.requests;

import android.app.Activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/** Represents the requests used by the app
 * @author Simon and Tanguy
 */
public class RequestsHttp extends Activity{

    private static final String uri = "http://10.0.2.2:8080/kalandar_api_war/rest/rdv";

    private final String type;
    private String id;
    private String dataJson;

    private String server_response;

    public RequestsHttp(String type, String id, String dataJson){
        this.type=type;
        this.id=id;
        this.dataJson=dataJson;
    }

    public RequestsHttp(String type, String data){
        this.type=type;

        if (type.equals("POST")){
            this.id=null;
            this.dataJson=data;
        }
        else if (type.equals("DELETE")){
            this.id=data;
            this.dataJson=null;
        }
        else {
            this.id=null;
            this.dataJson=null;
        }
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

    /**
     * Get all the events from the api
     */
    public void requestGetAll(){
        URL url;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL(uri);
            urlConnection = (HttpURLConnection) url.openConnection();

            int responseCode = urlConnection.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK){
                server_response = readStream(urlConnection.getInputStream());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add a new event to the api
     */
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
            if(responseCode == HttpURLConnection.HTTP_OK){
                server_response = readStream(urlConnection.getInputStream());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete an event
     */
    public void requestDelete(){
        URL url;
        HttpURLConnection urlConnection = null;

        try {
            String uri_delete = uri+"/"+this.id;
            url = new URL(uri_delete);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("DELETE");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setDoOutput(true);

            int responseCode = urlConnection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                server_response = readStream(urlConnection.getInputStream());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update an event
     */
    public void requestPut(){
        URL url;
        HttpURLConnection urlConnection = null;

        try {
            String uri_put = uri+"/"+this.id;
            url = new URL(uri_put);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("PUT");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setDoOutput(true);

            String jsonInputString = this.dataJson;
            try(OutputStream os = urlConnection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = urlConnection.getResponseCode();
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
